package com.stephen.test.vo;

public class MessageVO {
	 
    String name;
    String text;
 
    public MessageVO(String name, String text) {
        this.name = name;
        this.text = text;
    }
 
    public String getName() {
        return name;
    }
 
    public String getText() {
        return text;
    }
 
}
