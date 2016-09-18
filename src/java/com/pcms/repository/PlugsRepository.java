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
import org.springframework.stereotype.Repository;

/**
 *
 * @author wx.pan
 */
@Repository("_plugsRepository")
public class PlugsRepository extends BaseRepository {

    @Override
    public String getTableName() {
        return "plugs"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("id", new SqlField(Field.STRING));
        params.put("name", new SqlField(Field.STRING));
        params.put("type", new SqlField(Field.INT));
        params.put("params", new SqlField(Field.STRING));
        params.put("action", new SqlField(Field.STRING));
        return params; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlField getKey() {
         return new SqlField("id", Field.STRING); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderBy getOrderBy() {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCacheQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
