/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.service;

import com.pcms.repository.LanguageEnRepository;
import com.pcms.repository.LanguageChRepository;
import com.pcms.repository.LanguageRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wx.pan
 */
@Service("_languageService")
public class LanguageService extends com.pcms.common.Common {

    @Autowired
    private LanguageEnRepository _languageEnRepository;

    @Autowired
    private LanguageChRepository _languageChRepository;

    private String _type;

    public int add(Map<String, String> values) {
        return this.factory().add(values);
    }
   
    public int update(Map<String, String> values) {
        return -1;
    }

     public List<Map<String, String>> query(int currentPage,int pageSize) {
        return this.factory().query();
    }
    
    public List<Map<String, String>> query() {
        return this.factory().query();
    }

    private LanguageRepository factory() {
        if (this._type.equals("en")) {
            return _languageEnRepository;
        } else if (this._type.equals("zh")) {
            return _languageChRepository;
        }
        return _languageChRepository;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }

}
