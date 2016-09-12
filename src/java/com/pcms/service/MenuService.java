package com.pcms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcms.repository.MenuRepository;

@Service("_menuService")
public class MenuService {

    @Autowired
    private MenuRepository _menuRepository;

    public List<Map<String, String>> get() {
        return _menuRepository.query();
    }
}
