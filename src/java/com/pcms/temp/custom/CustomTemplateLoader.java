package com.pcms.temp.custom;

import com.pcms.core.util.FileUtil;
import com.pcms.modal.ModalTemplateLoader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CustomTemplateLoader implements TemplateLoader {

    private final Map<String, ModalTemplateLoader> _templates;

    private final Logger _log;

    private String _folder;

    public CustomTemplateLoader() {
        _log = Logger.getLogger(getClass());
        _templates = new HashMap();
    }

    public void AddTemplate(String name, ModalTemplateLoader tempModel) {
        if (!StringUtils.isNotEmpty(name) || tempModel == null) {
            return;
        }
        if (!_templates.containsKey(name)) {
            _templates.put(name, tempModel);
        }
    }

    @Override
    public void closeTemplateSource(Object templateSource)
            throws IOException {
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        if (_templates.isEmpty()) {
            _log.error("没有预存的模板文件");
            return null;
        }
        if (name == null || name.equals("")) {
            _log.error("无法找到对应的模板名称");
            return null;
        }
        ModalTemplateLoader tempModel = _templates.get(name);

        if (tempModel.getType().equals(ModalTemplateLoader.TemplateLoaderModalType.STRING)) {
            return tempModel.getValue();
        } else if (tempModel.getType().equals(ModalTemplateLoader.TemplateLoaderModalType.FILE)) {
            return FileUtil.readFileByBytes(this._folder + tempModel.getValue());
        }
        _log.error("没有找到对应的读取类型");
        return null;
    }

    @Override
    public long getLastModified(Object templateSource) {
        return 0;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding)
            throws IOException {
        if (templateSource == null) {
            return new StringReader("");
        }
        return new StringReader((String) templateSource);
    }

    /**
     * @return the _folder
     */
    public String getFolder() {
        return _folder;
    }

    /**
     * @param _folder the _folder to set
     */
    public void setFolder(String _folder) {
        this._folder = _folder;
    }

}
