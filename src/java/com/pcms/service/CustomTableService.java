package com.pcms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcms.modal.ModalResult;
import com.pcms.modal.sql.SqlField;
import com.pcms.repository.CustomTableRepository;
import java.util.List;

@Service("_customTableService ")
public class CustomTableService {

    @Autowired
    private CustomTableRepository _customformRepository;

    public int create(String table, Map<String, SqlField> param) {
        return _customformRepository.create(table, param);
    }
    
    public  List<Map<String, String>> getTables(){
        return null;
    }
    
    public ModalResult query(String table) {
        return null;
    }
}
