package com.KnorexAssignment;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class VASTParser {

    public String readXmlFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            throw new IOException("Failed to read XML from file: " + filePath, e);
        }
    }

    public String readXmlFromUrl(String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            throw new IOException("Failed to read XML from URL: " + url, e);
        }
    }

    public VASTTag parseFromXml(String xmlContent) {
        VASTTag vastTag = new VASTTag();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
            doc.getDocumentElement().normalize();

            // Parsing VAST Tag
            vastTag.setVersion(getTextContent((Element) doc, "version"));
            vastTag.setId(getTextContent((Element) doc, "id"));
            vastTag.setTitle(getTextContent((Element) doc, "title"));
            vastTag.setDescription(getTextContent((Element) doc, "description"));

            Element impressionElement = (Element) doc.getElementsByTagName("Impression").item(0);
            if (impressionElement != null) {
                vastTag.setImpressionId(getTextContent(impressionElement, "id"));
                vastTag.setImpressionUrl(getTextContent(impressionElement, "url"));
            }

            // Parse creatives
            NodeList creativeNodes = doc.getElementsByTagName("Creative");
            List<Creative> creatives = new ArrayList<>();

            for (int i = 0; i < creativeNodes.getLength(); i++) {
                Element creativeElement = (Element) creativeNodes.item(i);
                Creative creative = new Creative();
                creative.setId(getTextContent(creativeElement, "id"));
                creative.setWidth(getTextContent(creativeElement, "width"));
                creative.setHeight(getTextContent(creativeElement, "height"));
                creative.setType(getTextContent(creativeElement, "type"));
                creative.setSource(getTextContent(creativeElement, "source"));
                creative.setClickThroughUrl(getTextContent(creativeElement, "clickThroughUrl"));

                // Parse Companion Banners
                NodeList companionBannerNodes = creativeElement.getElementsByTagName("CompanionBanner");
                List<CompanionBanner> companionBanners = new ArrayList<>();
                for (int j = 0; j < companionBannerNodes.getLength(); j++) {
                    Element companionBannerElement = (Element) companionBannerNodes.item(j);
                    CompanionBanner companionBanner = new CompanionBanner();
                    companionBanner.setId(getTextContent(companionBannerElement, "id"));
                    companionBanner.setWidth(getTextContent(companionBannerElement, "width"));
                    companionBanner.setHeight(getTextContent(companionBannerElement, "height"));
                    companionBanner.setType(getTextContent(companionBannerElement, "type"));
                    companionBanner.setSource(getTextContent(companionBannerElement, "source"));
                    companionBanner.setClickThroughUrl(getTextContent(companionBannerElement, "clickThroughUrl"));
                    companionBanners.add(companionBanner);
                }
                creative.setCompanionBanners(companionBanners);

                // Parse Media Files
                NodeList mediaFileNodes = creativeElement.getElementsByTagName("MediaFile");
                List<MediaFile> mediaFiles = new ArrayList<>();
                for (int j = 0; j < mediaFileNodes.getLength(); j++) {
                    Element mediaFileElement = (Element) mediaFileNodes.item(j);
                    MediaFile mediaFile = new MediaFile();
                    mediaFile.setType(getTextContent(mediaFileElement, "type"));
                    mediaFile.setSource(getTextContent(mediaFileElement, "source"));
                    mediaFile.setBitrate(getTextContent(mediaFileElement, "bitrate"));
                    mediaFile.setWidth(getTextContent(mediaFileElement, "width"));
                    mediaFile.setHeight(getTextContent(mediaFileElement, "height"));
                    mediaFiles.add(mediaFile);
                }
                creative.setMediaFiles(mediaFiles);

                // Parse Video Clicks
                NodeList videoClickNodes = creativeElement.getElementsByTagName("VideoClick");
                List<VideoClick> videoClicks = new ArrayList<>();
                for (int j = 0; j < videoClickNodes.getLength(); j++) {
                    Element videoClickElement = (Element) videoClickNodes.item(j);
                    VideoClick videoClick = new VideoClick();
                    videoClick.setId(getTextContent(videoClickElement, "id"));
                    videoClick.setUrl(getTextContent(videoClickElement, "url"));
                    videoClicks.add(videoClick);
                }
                creative.setVideoClicks(videoClicks);

                // Parse Tracking Events
                NodeList trackingEventNodes = creativeElement.getElementsByTagName("TrackingEvent");
                List<TrackingEvent> trackingEvents = new ArrayList<>();
                for (int j = 0; j < trackingEventNodes.getLength(); j++) {
                    Element trackingEventElement = (Element) trackingEventNodes.item(j);
                    TrackingEvent trackingEvent = new TrackingEvent();
                    trackingEvent.setType(getTextContent(trackingEventElement, "type"));
                    trackingEvent.setUrl(getTextContent(trackingEventElement, "url"));
                    trackingEvents.add(trackingEvent);
                }
                creative.setTrackingEvents(trackingEvents);

                creatives.add(creative);
            }

            vastTag.setCreatives(creatives);

        } catch (Exception e) {
            System.err.println("Error parsing XML content: " + e.getMessage());
        }

        return vastTag;
    }

    private String getTextContent(Element parent, String tagName) {
        Node node = parent.getElementsByTagName(tagName).item(0);
        return node != null ? node.getTextContent() : null;
    }
}

