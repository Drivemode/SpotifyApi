package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class AlbumSimple {
    public String id;
    public String href;
    public String name;
    public String uri;
    public String type;
    public List<Image> images;
    @SerializedName("album_type") public String albumType;
    @SerializedName("available_markets") public List<String> availableMarkets;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
}
