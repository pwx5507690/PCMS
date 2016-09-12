package com.pcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcms.service.*;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
    private AccountService _accountService;
    
	@RequestMapping("add")
	@ResponseBody
	public String add(){
    	return _accountService.cc()+"";
    }
}
