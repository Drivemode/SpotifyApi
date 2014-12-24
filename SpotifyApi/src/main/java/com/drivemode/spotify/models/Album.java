package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#album-object-full">Album object model</a>
 */
public class Album {
    public String id;
    public String href;
    public String name;
    public String uri;
    public String type;
    public List<Image> images;
    public Pager<TrackSimple> tracks;
    public List<ArtistSimple> artists;
    public List<Copyright> copyrights;
    public List<String> genres;
    public int popularity;
    @SerializedName("album_type") public String albumType;
    @SerializedName("available_markets") public List<String> availableMarkets;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    @SerializedName("external_ids") public Map<String, String> externalIds;
    @SerializedName("release_date") public String releaseDate;
    @SerializedName("release_date_precision") public String releaseDatePrecision;


}