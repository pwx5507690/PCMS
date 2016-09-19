/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.custom;

import org.apache.commons.lang3.StringUtils;
import com.pcms.service.CustomTableService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wx.pan
 */
public class CustomProgramTempParam extends CustomTempParam {

    private String _where;
    private int _count;
    private String _order;

    public CustomProgramTempParam(CustomTableService customformService) {
        super(customformService);
    }
    
    @Override
    public void fill(){
      // super._customformService.query(table,_where)
    }
    
    public Map<String,List<Map>> queryForPage(){
        return null;
    }
    
    public void clear() {
        _result = null;
    }

    /**
     * @return the _where
     */
    public String getWhere() {
        return _where;
    }

    /**
     * @param _where the _where to set
     */
    public void setWhere(String _where) {
        this._where = _where;
    }

    /**
     * @return the _count
     */
    public int getCount() {
        return _count;
    }

    /**
     * @param _count the _count to set
     */
    public void setCount(int _count) {
        this._count = _count;
    }

    /**
     * @return the _order
     */
    public String getOrder() {
        return _order;
    }

    /**
     * @param _order the _order to set
     */
    public void setOrder(String _order) {
        this._order = _order;
    }
}
