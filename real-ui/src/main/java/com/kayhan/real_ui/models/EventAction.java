package com.kayhan.real_ui.models;

import java.util.Date;

public class EventAction {

    private String id;
    private String name;
    private String category;
    private String updatedByID;


    private String receivedBy;


    private Long createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdatedByID() {
        return updatedByID;
    }

    public void setUpdatedByID(String updatedByID) {
        this.updatedByID = updatedByID;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
