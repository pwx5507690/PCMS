package com.pcms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcms.modal.ModalResult;
import com.pcms.repository.CustomformRepository;
import java.util.List;

@Service("_customformService ")
public class CustomformService {

    @Autowired
    private CustomformRepository _customformRepository;

    public ModalResult create(String table, Map<String, String> param) {
        return _customformRepository.create(table, param);
    }
    
    public  List<Map<String, String>> getTables(){
        return null;
    }
    
    public ModalResult query(String table) {
        return null;
    }
}
