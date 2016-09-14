package com.pcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcms.service.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService _accountService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody Map<String, String> param) {
        return _accountService.add(param);
    }
}
