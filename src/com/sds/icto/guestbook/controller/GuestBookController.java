package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.sds.icto.guestbookDao.GuestbookDao;
import com.sds.icto.guestbookVo.GuestbookVo;



@Controller
public class GuestBookController {
	
	@Autowired
	GuestbookDao guestbookDao;
	
	@RequestMapping( "/index" )
	public String index( Model model) {
		List<GuestbookVo> list = guestbookDao.fetchList();
		model.addAttribute( "list", list );
		return "/views/list.jsp";
	}
	
	@RequestMapping("h1")
	public String h1(){
		return "/views/list.jsp";
	}

	
	
	@RequestMapping( value="/insert", method=RequestMethod.POST)
	public String insert( 
		@RequestParam( "name" ) String name,
		@RequestParam( "password" ) String pass,
		@RequestParam("message") String message 
		) 
	{
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(pass);
		vo.setMessage(message);
	
		guestbookDao.insert(vo);
		
		return "redirect:/index";
	}
}
