/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.repository;

import com.pcms.modal.sql.Field;
import com.pcms.modal.sql.OrderBy;
import com.pcms.modal.sql.SqlField;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wx.pan
 */
public abstract class LanguageRepository extends BaseRepository {

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("name", new SqlField(Field.STRING));
        params.put("value", new SqlField(Field.INT));
        return params;
    }

    @Override
    public SqlField getKey() {
        return new SqlField("id", Field.STRING);
    }

    @Override
    public OrderBy getOrderBy() {
        return null;
    }

    @Override
    public boolean isCacheQuery() {
        return false;
    }

}
