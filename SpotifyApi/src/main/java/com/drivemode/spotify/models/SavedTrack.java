package com.drivemode.spotify.models;

import com.google.gson.annotations.SerializedName;

/**
 * <a href="https://developer.spotify.com/web-api/object-model/#saved-track-object">Saved track object model</a>
 */
public class SavedTrack {
    @SerializedName("added_at") public String addedAt;
    public Track track;
}
