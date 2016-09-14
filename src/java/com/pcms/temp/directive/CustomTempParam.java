/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.directive;

import com.pcms.modal.ModalResult;
import com.pcms.service.CustomTableService;


public class CustomTempParam {
    private String _tableName;
    private String _where; 
    private ModalResult _result;
    private int _count;  
    private String _order;
    private final CustomTableService _customformService;
    
    public CustomTempParam(CustomTableService customformService){
        this._customformService = customformService;
    }
    
    public ModalResult getQueryResult(){
       return this._customformService.query(getTableName());
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
