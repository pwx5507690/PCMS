/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.repository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author wx.pan
 */
@Repository("_languageChRepository")
public class LanguageChRepository extends LanguageRepository {

    @Override
    public String getTableName() {
        return "zh_cn";
    }  
}
