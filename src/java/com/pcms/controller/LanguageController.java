package com.pcms.controller;

import com.pcms.modal.ModalResult;
import com.pcms.service.AssignmentService;
import com.pcms.service.LanguageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author wx.pan
 */
@Controller
@RequestMapping("/Language")
public class LanguageController extends BaseController {

    @Autowired
    private LanguageService _languageService;
    @Autowired
    private AssignmentService _assignmentService;

    @RequestMapping("init/{type}")
    @ResponseBody
    public Map<String, List<Map<String, String>>> init(@PathVariable(value = "type") String type) {
        Map<String, List<Map<String, String>>> initData = new HashMap<String, List<Map<String, String>>>();
        initData.put("languageData", this.get(type));
        initData.put("language", _assignmentService.Query("type", "LANGUAGE"));
        return initData;
    }

    @RequestMapping(value = "update/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ModalResult update(@PathVariable(value = "type") String type, @ModelAttribute("values") Map<String, String> values) {
        _languageService.setType(type);
        return this.setClientResult(_languageService.add(values)).getModalResult();
    }

    @RequestMapping(value = "add/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ModalResult add(@PathVariable(value = "type") String type, @ModelAttribute("values") Map<String, String> values) {
        _languageService.setType(type);
        return this.setClientResult(_languageService.add(values)).getModalResult();
    }

    @RequestMapping("{type}")
    @ResponseBody
    public List<Map<String, String>> get(@PathVariable(value = "type") String type) {
        _languageService.setType(type);
        return _languageService.query();
    }
}
