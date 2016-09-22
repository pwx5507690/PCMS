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
@Repository("_languageEnRepository")
public class LanguageEnRepository extends LanguageRepository {

    @Override
    public String getTableName() {
        return "en_us";
    }
}
