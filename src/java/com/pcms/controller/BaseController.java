package com.pcms.controller;

import javax.servlet.http.HttpServletRequest;
import com.pcms.modal.ModalResult;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController extends com.pcms.common.Common {

    @Autowired
    protected HttpServletRequest request;

    private static final ModalResult MODAL_RESULT = new ModalResult();

    protected BaseController setClientResult(int result) {
        if (result > 0) {
            setClientMsg("success");
        } else {
            setClientMsg("error");
        }
        MODAL_RESULT.setExecResult(result);
        return this;
    }

    protected BaseController setClientMsg(String msg) {
        MODAL_RESULT.setMsg(msg);
        return this;
    }

    public ModalResult getModalResult() {
        return MODAL_RESULT;
    }
}
