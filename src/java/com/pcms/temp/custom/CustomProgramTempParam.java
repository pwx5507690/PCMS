/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.custom;

import com.pcms.service.CustomTableService;

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
