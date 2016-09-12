package com.pcms.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ModelResult
 * @author taomk
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public final class ModelResult {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    public static final int CODE_0 = 0;
    public static final int CODE_1 = 1;
    public static final int CODE_200 = 200;
    public static final int CODE_404 = 404;
    public static final int CODE_500 = 500;

    private int code;

    private final Map<String,Object> data = new HashMap<String,Object>();

    public ModelResult(int code) {
        this.code = code;
    }

    public ModelResult addObject(String attrName,Object obj){
        data.put(attrName,obj);
        return  this;
    }

    public ModelResult setResultPage(ResultPage resultPage){
    	
        data.put("total",resultPage.getTotalCount());
        data.put("page",resultPage.getPageCount());
        data.put("size",resultPage.getPageSize());
        data.put("list",resultPage.getItems());
        
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setMessage(String message){
        data.put("message", message);
    }

    public void setList(List list){
        data.put("list", list);
    }

    public void setEntity(Object entity){
        data.put("entity", entity);
    }
}
