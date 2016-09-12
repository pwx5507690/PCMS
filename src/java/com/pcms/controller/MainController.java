package com.pcms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pcms.service.MenuService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
    private MenuService _menuService;
    
  	@RequestMapping("init")
  	@ResponseBody
    public Map<String,List<Map<String, String>>> init(){
  		Map<String,List<Map<String, String>>> initData = new HashMap<String, List<Map<String, String>>>();
  		initData.put("menu", _menuService.get());
  		return initData;
    }
}
