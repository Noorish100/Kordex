package com.KnorexAssignment;

import org.json.JSONObject;

public class VideoClick {
    private String id;
    private String url;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("url", this.url);
        return json;
    }
}
