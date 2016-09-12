package com.pcms.temp.directive;

import com.pcms.modal.ModalResult;
import com.pcms.service.CustomformService;
import freemarker.core.Environment;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;

public class CustomDirective implements TemplateDirectiveModel {
    
    private static final Logger _log = Logger.getLogger(CustomTemplateLoader.class);
    
    private final CustomformService _customformService;
    
    public  CustomDirective(CustomformService customformService) {
        this._customformService = customformService;
    }
    
    @Override
    public void execute(Environment env, Map map, TemplateModel[] tms, TemplateDirectiveBody body) throws TemplateException, IOException {
        CustomTempParam customTempParam = new CustomTempParam(this._customformService);
        
        if (!map.containsKey(CustomPageProperty.TABLE)) {
            throw new TemplateModelException("模板中不包含表名称");
        }
        
        if (!map.containsKey(CustomPageProperty.SOURCE)) {
            throw new TemplateModelException("模板中不包含数据源名称");
        }
        
        customTempParam.setTableName(map.get(CustomPageProperty.TABLE).toString());
        
        if (map.containsKey(CustomPageProperty.ORDER)) {
            customTempParam.setOrder(map.get(CustomPageProperty.ORDER).toString());
        }
        
        if (map.containsKey(CustomPageProperty.WHERE)) {
            customTempParam.setWhere(map.get(CustomPageProperty.WHERE).toString());
        }
        
        if (map.containsKey(CustomPageProperty.COUNT)) {
            customTempParam.setCount(Integer.valueOf(map.get(CustomPageProperty.COUNT).toString()));
        } else {
            customTempParam.setCount(-1);
        }
        
        ModalResult result = customTempParam.getQueryResult();
        
        try {
     //       env.setVariable(CustomPageProperty.SOURCE, DEFAULT_WRAPPER.wrap(news));
            body.render(env.getOut());
            
        } catch (TemplateException ex) {
            _log.error(ex.getMessage());
        }
    }
    
}
