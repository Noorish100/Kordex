package com.KnorexAssignment;

import org.json.JSONObject;

public class CompanionBanner {
    private String id;
    private String width;
    private String height;
    private String type;
    private String source;
    private String clickThroughUrl;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClickThroughUrl() {
        return clickThroughUrl;
    }

    public void setClickThroughUrl(String clickThroughUrl) {
        this.clickThroughUrl = clickThroughUrl;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("width", this.width);
        json.put("height", this.height);
        json.put("type", this.type);
        json.put("source", this.source);
        json.put("clickThroughUrl", this.clickThroughUrl);
        return json;
    }
}
