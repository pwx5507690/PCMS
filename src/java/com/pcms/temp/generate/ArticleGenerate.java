/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.generate;

import com.pcms.temp.custom.CustomTempParam;
import com.pcms.temp.directive.ArticleDirective;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleGenerate extends BaseGenerate {

    @SuppressWarnings("empty-statement")
    @Override
    public void Create(String tableName, String savePath) {

        Map<String, CustomTempParam> root = getRoot();
        if (!root.containsKey(tableName)) {
            _log.error("表明不存在");
            return;
        }
        CustomTempParam result = (CustomTempParam) root.get(tableName);
        List<Map<String, String>> data = result.getQueryResult();
        
        if (data.isEmpty()) {
            _log.info(String.format("%s %s", tableName, "数据表为空"));
            return;
        }
        // 生成一个副本 避免操作数据缓存
        Map<String, Object> clone = new HashMap();
        clone.putAll(root);

        for (Map<String, String> item : data) {
            clone.remove(tableName);
            ArticleDirective articleDirective = new ArticleDirective(item, _customTempData.getTableByName(tableName));
            clone.put(tableName, articleDirective);
            _markeWrite.save(savePath, item.get("temp"), null, clone);
        }
    }
}
