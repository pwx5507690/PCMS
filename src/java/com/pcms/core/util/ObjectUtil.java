/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.core.util;

import java.util.Map;
import net.sf.json.JSONObject;

/**
 *
 * @author wx.pan
 */
public class ObjectUtil {

    public static Map<String, String> jsonToMap(String json) {
        return (Map<String, String>) ObjectUtil.jsonToObject(json, Map.class);
    }

    public static <T> T jsonToObject(String json, Class<T> t) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return (T) JSONObject.toBean(jsonObject, t);
    }
}
