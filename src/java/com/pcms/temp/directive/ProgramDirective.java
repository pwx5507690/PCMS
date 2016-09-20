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
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import java.util.List;

/**
 *
 * @author wx.pan
 */
public class ProgramDirective extends com.pcms.common.Common implements TemplateDirectiveModel {

    private final List<Map<String, String>> _data;

    private final Map<String, String> _tableInfo;

    public ProgramDirective(List<Map<String, String>> data, Map<String, String> tableInfo) {
        _data = data;
        _tableInfo = tableInfo;
    }

    @Override
    public void execute(Environment env, Map map, TemplateModel[] tms, TemplateDirectiveBody body) throws TemplateException, IOException {
        try {
            env.setVariable(_tableInfo.get("tagName"),
                    DEFAULT_WRAPPER.wrap(_data));
            body.render(env.getOut());
        } catch (TemplateException ex) {
            _log.error(ex.getMessage());
        }
    }

}
