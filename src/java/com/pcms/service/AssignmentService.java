/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.service;

import com.pcms.repository.AssignmentRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author wx.pan
 */
@Service("_assignmentService")
public class AssignmentService {
    @Autowired
    AssignmentRepository _assignmentRepository;
    
    public List<Map<String,String>> Query(){
        return _assignmentRepository.query();
    }
    
     public List<Map<String,String>> Query(String name,String value){
       return  _assignmentRepository.queryObject(name, value);
    }
}
