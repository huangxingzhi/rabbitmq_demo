package com.demo.controller;

import com.demo.model.User;
import com.demo.sender.Sender1;
import com.demo.sender.Sender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试类
 */
@RestController
public class RabbitController {

    @Autowired
    private Sender1 helloSender1;

    @Autowired
    private Sender2 helloSender2;

    @RequestMapping("/hello")
    public String hello() {
        helloSender1.send();
        helloSender2.send();
        return "ok";
    }

    @RequestMapping("/user")
    public String user() {
        User user=new User();
        user.setUserName("a");
        user.setPassword("1");
        user.setSex("m");
        user.setLevel("1");
        helloSender1.sendUser(user);
        helloSender2.sendUser(user);
        return "ok";
    }

    @RequestMapping("/topMessage")
    public String topMessage() {
        helloSender1.testTopPicMessage();
        helloSender2.testTopPicMessage();
        return "ok";
    }

    @RequestMapping("/fanoutMessage")
    public String fanoutMessage() {
        helloSender1.testFanoutMessage();
        helloSender2.testFanoutMessage();
        return "ok";
    }
}