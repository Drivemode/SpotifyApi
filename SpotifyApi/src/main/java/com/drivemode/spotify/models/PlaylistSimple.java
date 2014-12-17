package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#playlist-object-simplified">Playlist object model (simplified)</a>.
 */
public class PlaylistSimple {
    @SerializedName("collaborative") public boolean isCollaborative;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    public String href;
    public String id;
    public List<Image> images;
    public String name;
    public UserSimple owner;
    @SerializedName("public") public Boolean isPublic;
    @SerializedName("tracks") public PlaylistTracksInformation tracksInfo;
    public String type;
    public String uri;

    @Override
    public String toString() {
        return "Simplified Playlist[" + id + "]: " + name;
    }
}
