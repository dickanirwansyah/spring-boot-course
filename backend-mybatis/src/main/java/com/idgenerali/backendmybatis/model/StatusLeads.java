package com.idgenerali.backendmybatis.model;

import java.io.Serializable;

/** tabel master **/
public class StatusLeads implements Serializable {

    private String statusLeads;
    private String description;

    public StatusLeads(){}

    public StatusLeads(String statusLeads, String description) {
        this.statusLeads = statusLeads;
        this.description = description;
    }

    public String getStatusLeads() {
        return statusLeads;
    }

    public void setStatusLeads(String statusLeads) {
        this.statusLeads = statusLeads;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
