package com.KnorexAssignment;

import org.json.JSONObject;

public class TrackingEvent {
    private String type;
    private String url;

    // Getters and Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", this.type);
        json.put("url", this.url);
        return json;
    }
}
