package com.pcms.modal;

public class ModalResult implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int _execResult;

    private String _msg = "";

    public boolean getExecResult() {
        return _execResult > 0;
    }

    public void setExecResult(int execResult) {
        this._execResult = execResult;
    }

    public String getMsg() {
        return this._msg;
    }

    public void setMsg(String msg) {
        this._msg = msg;
    }
}
