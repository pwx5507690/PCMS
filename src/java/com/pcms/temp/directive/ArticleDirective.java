/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.directive;

import freemarker.core.Environment;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;
import org.apache.log4j.Logger;

public class ArticleDirective  implements TemplateDirectiveModel {
      private static final Logger _log = Logger.getLogger(CustomTemplateLoader.class);
      private final Map<String,String> _articleItem;
     
      public ArticleDirective(Map<String,String> articleItem){
          this._articleItem = articleItem;
      }
      
      @Override
    public void execute(Environment env, Map map, TemplateModel[] tms, TemplateDirectiveBody body) throws TemplateException, IOException {           
        try {
            env.setVariable("", DEFAULT_WRAPPER.wrap(_articleItem));
            body.render(env.getOut());          
        } catch (TemplateException ex) {
            _log.error(ex.getMessage());
        }
    }
}
