package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ArtistSimple {
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    public String href;
    public String id;
    public String name;
    public String type;
    public String uri;
}
