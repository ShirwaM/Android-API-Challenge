package com.shirwa.guidebook.utils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class Guide {
    String name;
    String iconUrl;
    String linkUrl;
    Date startDate;
    Date endDate;
    Venue venue;

    public Guide() {
    }

    public Guide(JSONObject object) throws Exception {
        name = object.getString("name");
        iconUrl = object.optString("icon");
        linkUrl = object.optString("url");
        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        startDate = format.parse(object.optString("startDate"));
        endDate = format.parse(object.optString("endDate"));
        venue = new Venue(object.getJSONObject("venue"));
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
