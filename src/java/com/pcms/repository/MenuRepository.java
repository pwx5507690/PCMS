package com.pcms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;

@Repository("_menuRepository")
public class MenuRepository {

    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;

    public List<Map<String, String>> getMenu() {
        try {
            return iDataSource.getMap(_read.getConfigByName("queryMenu"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
