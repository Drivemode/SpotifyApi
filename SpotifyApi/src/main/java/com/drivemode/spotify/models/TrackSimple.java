package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class TrackSimple {
    public List<ArtistSimple> artists;
    @SerializedName("available_markets") public List<String> availableMarkets;
    @SerializedName("disc_number") public int discNumber;
    @SerializedName("duration_ms") public long durationMillis;
    public boolean explicit;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    public String href;
    public String id;
    public String name;
    @SerializedName("preview_url") public String previewUrl;
    @SerializedName("track_number") public int trackNumber;
    public String type;
    public String uri;
}
