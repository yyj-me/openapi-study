package com.stephen.test.springmvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.test.vo.MessageVO;

@RestController
public class JsonpRestController {
 
    @RequestMapping("/jsonptest/{user}")
    public MessageVO message(@PathVariable("user") String user) {
 
        MessageVO msg = new MessageVO(user, "Hello " + user + "!!!");
        return msg;
    }
 
}