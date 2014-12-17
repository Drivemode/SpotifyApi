package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#user-object-private">User object model</a>
 */
public class User {
    public String href;
    public String id;
    public String type;
    public String uri;
    public String email;
    public Followers followers;
    public String country;
    public List<Image> images;
    public String product;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    @SerializedName("display_name") public String displayName;

    @Override
    public String toString() {
        return "User[" + id + "]: " + displayName;
    }
}