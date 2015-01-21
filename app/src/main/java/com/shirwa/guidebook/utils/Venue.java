package com.shirwa.guidebook.utils;

import org.json.JSONObject;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class Venue {
    public String city;
    public String state;

    public Venue() {

    }

    public Venue(JSONObject object) throws Exception {
        city = object.optString("city");
        state = object.optString("state");
    }
}
