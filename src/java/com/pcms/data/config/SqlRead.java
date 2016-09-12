package com.pcms.data.config;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.*;

public class SqlRead {

    private final Map<String, String> _sqlConfig;

    private final Log log = LogFactory.getLog(getClass());

    public SqlRead() {
        _sqlConfig = new HashMap<String, String>();
    }

    private String getPath() {
        return com.pcms.core.util.FileUtil.getRootPath() + "config\\sql.xml";
    }

    @SuppressWarnings({"unchecked"})
    private void readConfig() {
        String path = getPath();
        File config = new File(path);
        log.info(config.exists());
        Document document = null;
        try {
            document = new SAXReader().read(config);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();

            for (Element ele : elementList) {
                String name = ele.attribute("id").getValue();

                if (_sqlConfig.containsKey(name)) {
                    continue;
                }
                _sqlConfig.put(name, ele.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public String getConfigByName(String name) throws Exception {
        if (_sqlConfig.isEmpty()) {
            readConfig();
        }
        if (_sqlConfig.containsKey(name)) {
            return _sqlConfig.get(name);
        }

        String error = "not fount name in config";
        log.error(error);
        throw new Exception(error);
    }
}
