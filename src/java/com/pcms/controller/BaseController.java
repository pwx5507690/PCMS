package com.pcms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	 protected final Log log = LogFactory.getLog(getClass());
	 @Autowired
	 protected  HttpServletRequest request;
}
