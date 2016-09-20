/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.custom;

import com.pcms.service.CustomTableService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wx.pan
 */
public class CustomProgramTempParam extends CustomTempParam {

    private String _where;
    private String _order;
    private int _count;

    public CustomProgramTempParam(CustomTableService customformService) {
        super(customformService);
    }

    @Override
    public void fill() {
        if (super._result == null) {
            super._result = super._customformService.query(super.getTableName(),
                    _where, _order);
        }

    }

    public int getPagination() {
        this.fill();
        int size = super._result.size();
        int pagination = size / _count;
        if (size % _count != 0) {
            pagination++;
        }
        return pagination;
    }

    public Map<String, List<Map<String, String>>> queryForPage() {
        this.fill();
        Map<String, List<Map<String, String>>> pageResult = new HashMap();
        int pageSize = _count - 1;
        int pageNumbers = 1;

        List<Map<String, String>> excess = new ArrayList();
        for (int i = 0; i < super._result.size(); i++) {
            excess.add(super._result.get(i));
            if (i != 0 && i % pageSize == 0) {
                pageResult.put(String.valueOf(pageNumbers), excess);
                pageNumbers++;
                excess.clear();
            }
        }
        if (excess.size() > 0) {
            pageResult.put(String.valueOf(pageNumbers + 1), excess);
        }

        return pageResult;
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
