package com.kayhan.real_ui.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventProgress {

    private String id;

    private Long createdAt;

    private List<EventAction> eventActions = new ArrayList<EventAction>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public List<EventAction> getEventActions() {
        return eventActions;
    }

    public void setEventActions(List<EventAction> eventActions) {
        this.eventActions = eventActions;
    }
}
