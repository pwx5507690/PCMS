/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.temp.custom.CustomTempData;
import com.pcms.temp.custom.CustomTempParam;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGenerate extends com.pcms.common.Common implements IGenerate {

    @Autowired
    protected CustomTempData _customTempData;
    @Autowired
    protected MarkeWrite _markeWrite;
    
    protected Map<String, CustomTempParam> getCustomData(){
         return _customTempData.GetCustomTempParams();
    }
    
    protected Map<String, CustomTempParam> getRoot() {
        Map<String, CustomTempParam> root = new HashMap<String, CustomTempParam>();
        Map<String, CustomTempParam> customTempParam = this.getCustomData();
        
        Iterator iterator = customTempParam.entrySet().iterator();
      
        while (iterator.hasNext()) {
            Map.Entry<String, CustomTempParam> entry = (Map.Entry<String, CustomTempParam>) iterator.next(); 
            root.put(entry.getKey(), entry.getValue());
        }
        
        return root;
    }
}
