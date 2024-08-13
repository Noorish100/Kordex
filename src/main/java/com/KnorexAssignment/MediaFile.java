package com.KnorexAssignment;

import org.json.JSONObject;

public class MediaFile {
    private String type;
    private String source;
    private String bitrate;
    private String width;
    private String height;

    // Getters and Setters

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

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", this.type);
        json.put("source", this.source);
        json.put("bitrate", this.bitrate);
        json.put("width", this.width);
        json.put("height", this.height);
        return json;
    }
}
