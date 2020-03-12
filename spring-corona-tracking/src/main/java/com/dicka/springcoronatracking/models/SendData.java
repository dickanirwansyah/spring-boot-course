package com.dicka.springcoronatracking.models;

public class SendData {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SendData{" +
                "data='" + data + '\'' +
                '}';
    }
}
