/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.service.CustomformService;
import com.pcms.temp.directive.MarkeWrite;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public class CustomTempData {
    @Autowired
    public CustomformService _customformService;

    private Map<String,CustomTempParam> _customTempParams;

    public Map<String,CustomTempParam> GetCustomTempParams() {
        if (_customTempParams != null) {
            return _customTempParams;
        }
        
        List<Map<String, String>> tables = getTables();

        for (Map<String, String> item : tables) {
            CustomTempParam cp = new CustomTempParam(_customformService);
            cp.setTableName(item.get("name"));
            cp.fill();
            this._customTempParams.put(item.get("name"),cp);
        }
        
        return _customTempParams;
    }
   
    
    private List<Map<String, String>> getTables() {
        return _customformService.getTables();
    }
}
