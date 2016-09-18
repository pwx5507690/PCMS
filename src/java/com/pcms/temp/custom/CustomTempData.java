/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.custom;

import com.pcms.service.CustomTableService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * 自定义模板数据
 *
 * @author wx.pan
 */
public class CustomTempData {

    @Autowired
    private CustomTableService _customformService;

    private List<Map<String, String>> _tables;

    private Map<String, CustomTempParam> _customTempParams;

    /**
     * 获取所有自定义表数据
     *
     * @return Map<String,CustomTempParam>
     */
    public Map<String, CustomTempParam> GetCustomTempParams() {
        if (_customTempParams == null) {
            List<Map<String, String>> tables = getTables();
            for (Map<String, String> item : tables) {
                CustomTempParam cp = new CustomTempParam(_customformService);
                cp.setTableName(item.get("name"));
                cp.fill();
                this._customTempParams.put(item.get("name"), cp);
            }
        }
        return _customTempParams;
    }

    /**
     * 清空数据表
     *
     */
    public void clear() {
        _tables = null;
        _customTempParams = null;
    }

    /**
     * 获取所有自定义表
     *
     * @return List<Map<String, String>>
     */
    public List<Map<String, String>> getTables() {
        if (_tables == null) {
            _tables = _customformService.getTables();
        }
        return _tables;
    }

    public Map<String, String> getTableByName(String name) {
        List<Map<String, String>> tab = this.getTables();
        for (Map<String, String> item : tab) {
            if (item.get("name").equals(name)) {
                return item;
            }
        }
        return null;
    }
}
