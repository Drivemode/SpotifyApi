package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#playlist-track-object">Playlist track object model</a>
 */
public class PlaylistTrack {
    @SerializedName("added_at") public String addedAt;
    @SerializedName("external_by") public UserSimple addedBy;
    public Track track;
}
