package com.pcms.modal;

import java.util.List;
import java.util.Map;

public class ModalResult implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success = false;

    private String msg = "";

    private List<Map<String, String>> result = null;

    public boolean isSuccess() {
        return success;
    }

    public ModalResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public ModalResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<Map<String, String>> getResult() {
        return result;
    }

    public ModalResult setResult(List<Map<String, String>> obj) {
        this.result = obj;
        return this;
    }
}
