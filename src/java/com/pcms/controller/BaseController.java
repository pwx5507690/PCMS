package com.pcms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController extends com.pcms.common.Common {
    @Autowired
    protected HttpServletRequest request;
}
