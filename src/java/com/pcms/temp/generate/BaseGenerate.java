/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.temp.directive.MarkeWrite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGenerate implements IGenerate {

    @Autowired
    protected CustomTempData _customTempData;
    @Autowired
    protected MarkeWrite _markeWrite;

    protected Map<String, Object> getRoot(Map<String, CustomTempParam> customTempParam) {
        Map<String, Object> root = new HashMap<String, Object>();
        Iterator iterator = customTempParam.entrySet().iterator();
      
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            CustomTempParam customTempParams = customTempParam.get(key);
            root.put(key, customTempParams.getQueryResult());
        }
        
        return root;
    }
}
