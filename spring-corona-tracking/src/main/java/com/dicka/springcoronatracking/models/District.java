package com.dicka.springcoronatracking.models;

import java.io.Serializable;

public class District implements Serializable {

    private String idDistrict;
    private String codeDistrict;
    private String nameDistrict;

    public String getIdDistrict(){
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict){
        this.idDistrict = idDistrict;
    }

    public String getCodeDistrict(){
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict){
        this.codeDistrict = codeDistrict;
    }

    public String getNameDistrict(){
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict){
        this.nameDistrict = nameDistrict;
    }

    @Override
    public String toString() {
        return "District{" +
                "idDistrict='" + idDistrict + '\'' +
                ", codeDistrict='" + codeDistrict + '\'' +
                ", nameDistrict='" + nameDistrict + '\'' +
                '}';
    }
}
