/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.core.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FreeMarkertUtil {

    public static void processTemplate(String templatePath, String templateName, String templateEncoding, Map<?, ?> root, Writer out) {
        try {
            Configuration config = new Configuration();
            File file = new File(templatePath);
            config.setDirectoryForTemplateLoading(file);
            config.setObjectWrapper(new DefaultObjectWrapper());
            Template template = config.getTemplate(templateName, templateEncoding);
            template.process(root, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException ex) {
            Logger.getLogger(FreeMarkertUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
