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
public class LanguageService {

    @Autowired
    private LanguageEnRepository _languageEnRepository;

    @Autowired
    private LanguageChRepository _languageChRepository;

    private String _type;

    public int add(Map<String, String> values) {
        return this.factory().add(values);
    }

    public List<Map<String, String>> query() {
        return this.factory().query();
    }

    private LanguageRepository factory() {
        if (this._type.equals("en")) {
            return _languageEnRepository;
        } else if (this._type.equals("ch")) {
            return _languageChRepository;
        }
        return _languageChRepository;
    }

    /**
     * @return the _type
     */
    public String getType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void setType(String _type) {
        this._type = _type;
    }

}
