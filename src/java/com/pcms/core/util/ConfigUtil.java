package com.pcms.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;

/**
 * 项目参数工具类
 * 
 * @author 孙宇
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}
	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoHomeName() {
		return bundle.getString("sessionInfoHomeName");
	}
	/**
	 * 获得考试类型名字
	 * 
	 * @return
	 */
	public static final String getExamTypeCodeName() {
		return bundle.getString("examtypecode");
	}
	
	/**
	 * 参数格式转换
	 * @throws UnsupportedEncodingException 
	 */
	public static String ChangeCode(String str) throws UnsupportedEncodingException{
		String newCode = new String(str.getBytes("ISO-8859-1"), "utf-8");
		return newCode;
	}
	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		if(bundle.containsKey(key)){
			return bundle.getString(key);
		}else{
			return key;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(FilenameUtils.getBaseName("A.txt"));
	}
	
	

}
