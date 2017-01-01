package com.journaldev.youplusmegaevent.bean;

import java.io.Serializable;

/**
 * Created by Naveen on 5/18/2015.
 */
public class Error implements Serializable {

    private String code;
    private String property;
    private String message;
    private String developerMessage;
    private String moreInfo;

    public Error(){}
    public Error(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", property='" + property + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}
