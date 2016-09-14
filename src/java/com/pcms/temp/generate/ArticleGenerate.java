/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.modal.ModalResult;
import com.pcms.temp.directive.ArticleDirective;
import java.util.List;
import java.util.Map;

public class ArticleGenerate extends BaseGenerate{

    @SuppressWarnings("empty-statement")
    @Override
    public void Create(String tableName, String savePath) {

        Map<String, CustomTempParam> customTempParams = _customTempData.GetCustomTempParams();
       
        Map<String, Object> root = getRoot(customTempParams);
        ModalResult modalResult = customTempParams.get(tableName).getQueryResult();

        //if (modalResult.isSuccess()) {
          //  List<Map<String, String>> data = modalResult.getResult();
          
            //for (Map<String, String> item : data) {
              //  ArticleDirective articleDirective = new ArticleDirective(item);
               // root.put(tableName, articleDirective);
                
               // _markeWrite.save(savePath, item.get("temp"), null, root);
                //root.remove(tableName);
            //}
        //}
    }
}
