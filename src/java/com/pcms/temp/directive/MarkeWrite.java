/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.temp.directive;

import com.alibaba.druid.util.StringUtils;
import com.pcms.core.util.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class MarkeWrite {

    @Autowired
    private Configuration _config;
    @Autowired
    private CustomTemplateLoader _customTemplateLoader;

    private static final String _defaultEncoding = "UTF-8";

    private static final org.apache.log4j.Logger _log = org.apache.log4j.Logger.getLogger(MarkeWrite.class);

    public void init() {
        _config = new Configuration();
        _config.setTemplateLoader(_customTemplateLoader);
        _config.setObjectWrapper(new DefaultObjectWrapper());
    }

    public void save(String savePath, String templateName, String templateEncoding, Map<?, ?> root) {
        String path = savePath + "/" + templateName + ".html";
        FileUtil.delete(path);

        try {
            File file = FileUtil.createFile(path);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            this.processTemplate(templateName, templateEncoding, root, out);
        } catch (FileNotFoundException ex) {
            _log.error(ex.getMessage());
        } catch (IOException ex) {
            _log.error(ex.getMessage());
        }
    }

    public void processTemplate(String templateName, String templateEncoding, Map<?, ?> root, Writer out) {
        try {
            if (StringUtils.isEmpty(templateEncoding)) {
                templateEncoding = _defaultEncoding;
            }
            Template template = _config.getTemplate(templateName, templateEncoding);
            template.process(root, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            _log.error(e.getMessage());
        } catch (TemplateException ex) {
            _log.error(ex.getMessage());
        }
    }
}
