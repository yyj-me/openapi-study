package com.multi.contacts.view.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.multi.contacts.biz.domain.Contact;
import com.multi.contacts.biz.domain.ContactList;
import com.multi.contacts.biz.domain.RESTResult;
import com.multi.contacts.biz.service.ContactService;

@Controller
@RequestMapping(value="/contacts")
public class ContactController {
	@Resource(name="xmlView")
	private View xmlView;
	@Resource(name="jsonView")
	private View jsonView;
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getContacts(
			@RequestParam(value="pageno", required=false, defaultValue="0") int pageno, 
			@RequestParam(value="pagesize", required=false, defaultValue="3") int pagesize) {
		
		ContactList contactList = null;
		if(pageno < 1) {
			contactList = contactService.getSelectAll();
		} else {
			contactList = contactService.getSelectAll(pageno, pagesize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject("data", contactList);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{no}")
	public ModelAndView getContactOne(@PathVariable int no) {
		
		HttpServletResponse response;
		
		Contact contact = new Contact();
		contact.setNo(no);
		
		Contact contactResult = contactService.getSelectOne(contact);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);		
		mav.addObject("data", contactResult);
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertContact(@RequestBody Contact contact) {
		RESTResult result = contactService.insertContact(contact);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject("data", result);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{no}")
	public ModelAndView updateContact(@PathVariable int no, @RequestBody Contact contact) {
		if(no != contact.getNo()) {
			return null;
		}
		
		RESTResult result = contactService.updateContact(contact);
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject("data", result);
		return mav;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public @ResponseBody ContactList getContacts2() {
		return contactService.getSelectAll();
		
	}
}
