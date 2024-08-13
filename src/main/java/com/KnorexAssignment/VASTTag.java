package com.KnorexAssignment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class VASTTag {
    private String version;
    private String id;
    private String title;
    private String description;
    private String impressionId;
    private String impressionUrl;
    private List<Creative> creatives;

    // Getters and Setters

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpressionId() {
        return impressionId;
    }

    public void setImpressionId(String impressionId) {
        this.impressionId = impressionId;
    }

    public String getImpressionUrl() {
        return impressionUrl;
    }

    public void setImpressionUrl(String impressionUrl) {
        this.impressionUrl = impressionUrl;
    }

    public List<Creative> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<Creative> creatives) {
        this.creatives = creatives;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("version", this.version);
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("description", this.description);

        JSONObject impression = new JSONObject();
        impression.put("id", this.impressionId);
        impression.put("url", this.impressionUrl);
        json.put("impression", impression);

        JSONArray creativesJson = new JSONArray();
        for (Creative creative : creatives) {
            creativesJson.put(creative.toJson());
        }
        json.put("creatives", creativesJson);

        return json;
    }
}


