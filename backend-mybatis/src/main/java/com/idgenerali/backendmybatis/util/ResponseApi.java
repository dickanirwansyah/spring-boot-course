package com.idgenerali.backendmybatis.util;

import java.util.Date;

public class ResponseApi<T> {

    private Date timestamp;
    private int status;
    private String messages;
    private T data;

    public ResponseApi(){}

    public ResponseApi(Date timestamp, int status, String messages, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
