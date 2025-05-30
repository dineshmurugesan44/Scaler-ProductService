package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

public class ErrorDTO {

    private String errorMsg;
    private String errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
