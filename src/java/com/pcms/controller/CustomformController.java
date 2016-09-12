package com.pcms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcms.modal.ModalResult;
import com.pcms.service.CustomformService;

@Controller
@RequestMapping("/customform")
public class CustomformController extends BaseController {

    private CustomformService _customformService;

    @RequestMapping(value = "add/{table}", method = RequestMethod.POST)
    @ResponseBody
    public ModalResult create(@PathVariable String table, @RequestBody Map<String, String> param) {
        return _customformService.create(table, param);
    }

    @RequestMapping(value = "{table}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> get(@PathVariable String table) {
        return null;
    }
}
