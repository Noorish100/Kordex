package com.KnorexAssignment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Creative {
    private String id;
    private String width;
    private String height;
    private String type;
    private String source;
    private String clickThroughUrl;
    private List<TrackingEvent> trackingEvents;
    private List<VideoClick> videoClicks;
    private List<MediaFile> mediaFiles;
    private List<CompanionBanner> companionBanners;

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

    public List<TrackingEvent> getTrackingEvents() {
        return trackingEvents;
    }

    public void setTrackingEvents(List<TrackingEvent> trackingEvents) {
        this.trackingEvents = trackingEvents;
    }

    public List<VideoClick> getVideoClicks() {
        return videoClicks;
    }

    public void setVideoClicks(List<VideoClick> videoClicks) {
        this.videoClicks = videoClicks;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    public List<CompanionBanner> getCompanionBanners() {
        return companionBanners;
    }

    public void setCompanionBanners(List<CompanionBanner> companionBanners) {
        this.companionBanners = companionBanners;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("width", this.width);
        json.put("height", this.height);
        json.put("type", this.type);
        json.put("source", this.source);
        json.put("clickThroughUrl", this.clickThroughUrl);

        JSONArray trackingEventsJson = new JSONArray();
        for (TrackingEvent event : trackingEvents) {
            trackingEventsJson.put(event.toJson());
        }
        json.put("trackingEvents", trackingEventsJson);

        JSONArray videoClicksJson = new JSONArray();
        for (VideoClick videoClick : videoClicks) {
            videoClicksJson.put(videoClick.toJson());
        }
        json.put("videoClicks", videoClicksJson);

        JSONArray mediaFilesJson = new JSONArray();
        for (MediaFile mediaFile : mediaFiles) {
            mediaFilesJson.put(mediaFile.toJson());
        }
        json.put("mediaFiles", mediaFilesJson);

        JSONArray companionBannersJson = new JSONArray();
        for (CompanionBanner companionBanner : companionBanners) {
            companionBannersJson.put(companionBanner.toJson());
        }
        json.put("companionBanners", companionBannersJson);

        return json;
    }
}

