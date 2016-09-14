package com.pcms.repository;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import freemarker.template.*;

@Repository("_tempReposiory ")
public class TempReposiory {

    private Configuration cfg;
    @Autowired
    private AssignmentRepository _assignmentRepository;

    private final Log _log = LogFactory.getLog(getClass());

    public TempReposiory() {
        cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(com.pcms.core.util.FileUtil.getRootPath() + "Temp"));
        } catch (IOException e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void resolve(String name) {
        try {

            Template temp = cfg.getTemplate(name);

        } catch (IOException e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void write() {

    }
}
