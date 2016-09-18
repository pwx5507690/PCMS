/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.temp.custom.CustomTempParam;
import com.pcms.temp.directive.ProgramDirective;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wx.pan
 */
public class ProgramGenerate extends BaseGenerate {

    @Override
    public void Create(String tableName, String savePath) {
        Map<String, CustomTempParam> root = getRoot();
        if (!root.containsKey(tableName)) {
            _log.error("表明不存在");
            return;
        }
        CustomTempParam result = (CustomTempParam) root.get(tableName);

        // 生成一个副本 避免操作数据缓存
        Map<String, Object> clone = new HashMap();
        clone.putAll(root);
        clone.remove(tableName);
        
        Map<String, String> tableInfo = _customTempData.getTableByName(tableName);
        ProgramDirective programDirective = new ProgramDirective(result, tableInfo);
        clone.put(tableName, programDirective);
        _markeWrite.save(savePath, tableInfo.get("temp"), null, clone);
    }

}