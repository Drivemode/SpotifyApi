package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UserSimple {
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    public String href;
    public String id;
    public String type;
    public String uri;

    @Override
    public String toString() {
        return "User[" + id + "]";
    }
}
