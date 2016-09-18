/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;
import com.pcms.temp.custom.CustomProgramTempParam;
import com.pcms.temp.custom.CustomTempParam;
import com.pcms.temp.custom.CustomPageProperty;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 *
 * @author wx.pan
 */
public class ProgramDirective extends com.pcms.common.Common implements TemplateDirectiveModel {

    private final CustomProgramTempParam _programItem;

    private final Map<String, String> _tableInfo;

    public ProgramDirective(CustomTempParam programItem, Map<String, String> tableInfo) {
        _programItem = programItem.converToCustomProgramTempParam();
        _tableInfo = tableInfo;
    }

    @Override
    public void execute(Environment env, Map map, TemplateModel[] tms, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (map.containsKey(CustomPageProperty.ORDER)) {
            _programItem.setOrder(map.get(CustomPageProperty.ORDER).toString());
        }

        if (map.containsKey(CustomPageProperty.WHERE)) {
            _programItem.setWhere(map.get(CustomPageProperty.WHERE).toString());
        }

        if (map.containsKey(CustomPageProperty.COUNT)) {
            _programItem.setCount(Integer.valueOf(map.get(CustomPageProperty.COUNT).toString()));
        } else {
            _programItem.setCount(-1);
        }

        try {
            env.setVariable(_tableInfo.get("tagName"),
                    DEFAULT_WRAPPER.wrap(_programItem.getQueryResult()));
            body.render(env.getOut());
        } catch (TemplateException ex) {
            _log.error(ex.getMessage());
        }
    }

}
