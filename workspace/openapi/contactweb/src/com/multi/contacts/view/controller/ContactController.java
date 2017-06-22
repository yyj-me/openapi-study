package com.multi.contacts.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.contacts.biz.domain.Contact;
import com.multi.contacts.biz.service.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public ModelAndView getSelectAll() {
		List<Contact> contacts = contactService.getSelectAll(); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		mav.addObject("data", contacts);
		return mav;		
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String addContact(Contact contact) {
		contactService.insertContact(contact);
		return "redirect:index.do";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateContact(Contact contact) {
		contactService.updateContact(contact);
		return "redirect:index.do";
	}
}
