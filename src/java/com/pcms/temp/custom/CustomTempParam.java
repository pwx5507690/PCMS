/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.custom;

import com.pcms.service.CustomTableService;
import java.util.List;
import java.util.Map;

public class CustomTempParam {

    private String _tableName;
    private String _where;
    private List<Map<String,String>> _result;
    private int _count;
    private String _order;
    private final CustomTableService _customformService;

    public CustomTempParam(CustomTableService customformService) {
        this._customformService = customformService;
    }

    public List<Map<String,String>> getQueryResult() {
        if (_result == null) {
            _result = this._customformService.query(getTableName());
        }
        return _result;
    }

    public void fill() {
        _result = this._customformService.query(getTableName());
    }

    /**
     * @return the _tableName
     */
    public String getTableName() {
        return _tableName;
    }

    /**
     * @param _tableName the _tableName to set
     */
    public void setTableName(String _tableName) {
        this._tableName = _tableName;
    }
    
    public CustomProgramTempParam converToCustomProgramTempParam(){
        return new CustomProgramTempParam(this._customformService);
    }
}
