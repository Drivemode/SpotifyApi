package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#playlist-object-full">Playlist object model</a>.
 */
public class Playlist {
    @SerializedName("collaborative") public boolean isCollaborative;
    @SerializedName("external_urls") public Map<String, String> externalUrls;
    public String href;
    public String id;
    public List<Image> images;
    public String name;
    public UserSimple owner;
    @SerializedName("public") public Boolean isPublic;
    public String type;
    public String uri;
    public String description;
    public Followers followers;
    @SerializedName("snapshot_id") public String snapshotId;
    public Pager<PlaylistTrack> tracks;

    @Override
    public String toString() {
        return "Playlist[" + id + "]: " + name;
    }
}