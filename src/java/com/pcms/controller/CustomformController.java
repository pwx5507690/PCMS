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
import com.pcms.modal.sql.SqlField;
import com.pcms.service.CustomTableService;

@Controller
@RequestMapping("/customform")
public class CustomformController extends BaseController {

    private CustomTableService _customTableService;

    @RequestMapping(value = "add/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ModalResult create(@PathVariable String table, @RequestBody Map<String, SqlField> param) {
        return new ModalResult();
       // return _customTableService.create(table, param);
    }

    @RequestMapping(value = "{table}", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> get(@PathVariable String table) {
        return null;
    }
}
