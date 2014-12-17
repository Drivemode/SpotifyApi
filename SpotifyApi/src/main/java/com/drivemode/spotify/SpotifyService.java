package com.drivemode.spotify;

import com.drivemode.spotify.annotations.DELETEWITHBODY;
import com.drivemode.spotify.models.Album;
import com.drivemode.spotify.models.Albums;
import com.drivemode.spotify.models.AlbumsPager;
import com.drivemode.spotify.models.Artist;
import com.drivemode.spotify.models.Artists;
import com.drivemode.spotify.models.ArtistsPager;
import com.drivemode.spotify.models.FeaturedPlaylists;
import com.drivemode.spotify.models.NewReleases;
import com.drivemode.spotify.models.Pager;
import com.drivemode.spotify.models.Playlist;
import com.drivemode.spotify.models.PlaylistTrack;
import com.drivemode.spotify.models.PlaylistsPager;
import com.drivemode.spotify.models.SavedTrack;
import com.drivemode.spotify.models.SnapshotId;
import com.drivemode.spotify.models.Track;
import com.drivemode.spotify.models.Tracks;
import com.drivemode.spotify.models.TracksPager;
import com.drivemode.spotify.models.TracksToRemove;
import com.drivemode.spotify.models.TracksToRemoveWithPosition;
import com.drivemode.spotify.models.User;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface SpotifyService {

    /**
     * Profiles *
     */

    @GET("/me")
    public void getMe(Callback<User> callback);

    @GET("/me")
    public User getMe();

    @GET("/user/{id}")
    public void getUser(@Path("id") String userId, Callback<User> callback);

    @GET("/user/{id}")
    public User getUser(@Path("id") String userId);

    /**
     * Playlists *
     */

    @GET("/users/{id}/playlists")
    public void getPlaylists(@Path("id") String userId, @Query("offset") int offset, @Query("limit") int limit, Callback<Pager<Playlist>> callback);

    @GET("/users/{id}/playlists")
    public Pager<Playlist> getPlaylists(@Path("id") String userId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/users/{id}/playlists")
    public void getPlaylists(@Path("id") String userId, Callback<Pager<Playlist>> callback);

    @GET("/users/{id}/playlists")
    public Pager<Playlist> getPlaylists(@Path("id") String userId);

    @GET("/users/{user_id}/playlists/{playlist_id}")
    public void getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, Callback<Playlist> callback);

    @GET("/users/{user_id}/playlists/{playlist_id}")
    public Playlist getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("offset") int offset, @Query("limit") int limit, Callback<Pager<PlaylistTrack>> callback);

    @GET("/users/{user_id}/playlists/{playlist_id}/tracks")
    public Pager<PlaylistTrack> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, Callback<Pager<PlaylistTrack>> callback);

    @GET("/users/{user_id}/playlists/{playlist_id}/tracks")
    public Pager<PlaylistTrack> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId);


    @POST("/users/{user_id}/playlists")
    public void createPlaylist(@Path("user_id") String userId, @Query("name") String name, Callback<Playlist> callback);

    @POST("/users/{user_id}/playlists")
    public Playlist createPlaylist(@Path("user_id") String userId, @Query("name") String name);

    @POST("/users/{user_id}/playlists")
    public void createPlaylist(@Path("user_id") String userId, @Query("name") String name, @Query("public") boolean is_public, Callback<Playlist> callback);

    @POST("/users/{user_id}/playlists")
    public Playlist createPlaylist(@Path("user_id") String userId, @Query("name") String name, @Query("public") boolean is_public);

    @POST("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, Callback<SnapshotId> callback);

    @POST("/users/{user_id}/playlists/{playlist_id}/tracks")
    public SnapshotId addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris);

    @POST("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Query("position") int position, Callback<SnapshotId> callback);

    @POST("/users/{user_id}/playlists/{playlist_id}/tracks")
    public SnapshotId addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Query("position") int position);

    @DELETEWITHBODY("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove, Callback<SnapshotId> callback);

    @DELETEWITHBODY("/users/{user_id}/playlists/{playlist_id}/tracks")
    public SnapshotId removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    @DELETEWITHBODY("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition, Callback<SnapshotId> callback);

    @DELETEWITHBODY("/users/{user_id}/playlists/{playlist_id}/tracks")
    public SnapshotId removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}/tracks")
    public void replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, Callback<Boolean> callback);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}/tracks")
    public boolean replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}")
    public void changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("name") String name, Callback<Boolean> callback);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}")
    public boolean changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("name") String name);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}")
    public void changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("public") boolean is_public, Callback<Boolean> callback);

    // todo: process status code and return boolean
    @PUT("/users/{user_id}/playlists/{playlist_id}")
    public boolean changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("public") boolean is_public);

    /**
     * Albums *
     */

    @GET("/albums/{id}")
    public void getAlbum(@Path("id") String albumId, Callback<Album> callback);

    @GET("/albums/{id}")
    public Album getAlbum(@Path("id") String albumId);

    @GET("/albums")
    public void getAlbums(@Query("ids") String albumIds, Callback<Albums> callback);

    @GET("/albums")
    public Albums getAlbums(@Query("ids") String albumIds);

    @GET("/albums/{id}/tracks")
    public Pager<Track> getAlbumTracks(@Path("id") String albumId);

    @GET("/albums/{id}/tracks")
    public void getAlbumTracks(@Path("id") String albumId, Callback<Pager<Track>> callback);

    @GET("/albums/{id}/tracks")
    public void getAlbumTracks(@Path("id") String albumId, @Query("offset") int offset, @Query("limit") int limit, Callback<Pager<Track>> callback);

    @GET("/albums/{id}/tracks")
    public Pager<Track> getAlbumTracks(@Path("id") String albumId, @Query("offset") int offset, @Query("limit") int limit);

    /**
     * Artists *
     */

    @GET("/artists/{id}/albums")
    public void getArtistAlbums(@Path("id") String artistId, Callback<Pager<Album>> callback, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/artists/{id}/albums")
    public Pager<Album> getArtistAlbums(@Path("id") String artistId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/artists/{id}/albums")
    public void getArtistAlbums(@Path("id") String artistId, Callback<Pager<Album>> callback);

    @GET("/artists/{id}/albums")
    public Pager<Album> getArtistAlbums(@Path("id") String artistId);

    @GET("/artists/{id}/top-tracks")
    public void getArtistTopTrack(@Path("id") String artistId, @Query("offset") int offset, @Query("limit") int limit, Callback<Pager<Track>> callback);

    @GET("/artists/{id}/top-tracks")
    public Pager<Track> getArtistTopTrack(@Path("id") String artistId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/artists/{id}/top-tracks")
    public void getArtistTopTrack(@Path("id") String artistId, Callback<Pager<Track>> callback);

    @GET("/artists/{id}/top-tracks")
    public Pager<Track> getArtistTopTrack(@Path("id") String artistId);

    @GET("/artists/{id}/related-artists")
    public void getRelatedArtists(@Path("id") String artistId, @Query("offset") int offset, @Query("limit") int limit, Callback<Pager<Artist>> callback);

    @GET("/artists/{id}/related-artists")
    public Pager<Artist> getRelatedArtists(@Path("id") String artistId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/artists/{id}/related-artists")
    public void getRelatedArtists(@Path("id") String artistId, Callback<Pager<Artist>> callback);

    @GET("/artists/{id}/related-artists")
    public Pager<Artist> getRelatedArtists(@Path("id") String artistId);

    @GET("/artists/{id}")
    public void getArtist(@Path("id") String artistId, Callback<Artist> callback);

    @GET("/artists/{id}")
    public Artist getArtist(@Path("id") String artistId);

    @GET("/artists")
    public void getArtists(@Query("ids") String artistIds, Callback<Artists> callback);

    @GET("/artists")
    public Artists getArtists(@Query("ids") String artistIds);

    /**
     * Tracks *
     */

    @GET("/tracks/{id}")
    public void getTrack(@Path("id") String trackId, Callback<Track> callback);

    @GET("/tracks/{id}")
    public Track getTrack(@Path("id") String trackId);

    @GET("/tracks")
    public void getTracks(@Query("ids") String trackIds, Callback<Tracks> callback);

    @GET("/tracks")
    public Tracks getTracks(@Query("ids") String trackIds);

    /**
     * Browse *
     */

    @GET("/browse/featured-playlists")
    public void getFeaturedPlaylists(Callback<FeaturedPlaylists> callback);

    @GET("/browse/featured-playlists")
    public FeaturedPlaylists getFeaturedPlaylists();

    @GET("/browse/featured-playlists")
    public void getFeaturedPlaylists(@QueryMap Map<String, String> options, Callback<FeaturedPlaylists> callback);

    @GET("/browse/featured-playlists")
    public FeaturedPlaylists getFeaturedPlaylists(@QueryMap Map<String, String> options);

    @GET("/browse/featured-playlists")
    public void getFeaturedPlaylists(@QueryMap Map<String, String> options, @Query("offset") int offset, @Query("limit") int limit, Callback<FeaturedPlaylists> callback);

    @GET("/browse/featured-playlists")
    public FeaturedPlaylists getFeaturedPlaylists(@QueryMap Map<String, String> options, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/browse/new-releases")
    public void getNewReleases(Callback<NewReleases> callback);

    @GET("/browse/new-releases")
    public NewReleases getNewReleases();

    @GET("/browse/new-releases")
    public void getNewReleases(@Query("country") String country, Callback<NewReleases> callback);

    @GET("/browse/new-releases")
    public NewReleases getNewReleases(@Query("country") String country);

    @GET("/browse/new-releases")
    public void getNewReleases(@Query("country") String country, @Query("offset") int offset, @Query("limit") int limit, Callback<NewReleases> callback);

    @GET("/browse/new-releases")
    public NewReleases getNewReleases(@Query("country") String country, @Query("offset") int offset, @Query("limit") int limit);


    /**
     * Library / Your Music *
     */

    @GET("/me/tracks")
    public void getMySavedTracks(Callback<Pager<SavedTrack>> callback);

    @GET("/me/tracks")
    public Pager<SavedTrack> getMySavedTracks();

    @GET("/me/tracks")
    public void getMySavedTracks(@Query("offset") int offset, @Query("limit") int limit, Callback<Pager<SavedTrack>> callback);

    @GET("/me/tracks")
    public Pager<SavedTrack> getMySavedTracks(@Query("offset") int offset, @Query("limit") int limit);

    @GET("/me/tracks/contains")
    public void containsMySavedTracks(@Query("ids") String ids, Callback<boolean[]> callback);

    @GET("/me/tracks/contains")
    public Boolean[] containsMySavedTracks(@Query("ids") String ids);

    // todo: process status code and return boolean
    @PUT("/me/tracks")
    public void addToMySavedTracks(@Query("ids") String ids, Callback<Boolean> callback);

    // todo: process status code and return boolean
    @PUT("/me/tracks")
    public boolean addToMySavedTracks(@Query("ids") String ids);

    // todo: process status code and return boolean
    @DELETE("/me/tracks")
    public void removeFromMySavedTracks(@Query("ids") String ids, Callback<Boolean> callback);

    // todo: process status code and return boolean
    @DELETE("/me/tracks")
    public boolean removeFromMySavedTracks(@Query("ids") String ids);

    /**
     * Search *
     */

    @GET("/search?type=track")
    public void searchTracks(@Query("q") String q, Callback<TracksPager> callback);

    @GET("/search?type=track")
    public TracksPager searchTracks(@Query("q") String q);

    @GET("/search?type=track")
    public void searchTracks(@Query("q") String q, @Query("market") String market, Callback<TracksPager> callback);

    @GET("/search?type=track")
    public TracksPager searchTracks(@Query("q") String q, @Query("market") String market);

    @GET("/search?type=track")
    public void searchTracks(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit, Callback<TracksPager> callback);

    @GET("/search?type=track")
    public TracksPager searchTracks(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/search?type=track")
    public void searchTracks(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit, Callback<TracksPager> callback);

    @GET("/search?type=track")
    public TracksPager searchTracks(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit);


    @GET("/search?type=artist")
    public void searchArtists(@Query("q") String q, Callback<ArtistsPager> callback);

    @GET("/search?type=artist")
    public ArtistsPager searchArtists(@Query("q") String q);

    @GET("/search?type=artist")
    public void searchArtists(@Query("q") String q, @Query("market") String market, Callback<ArtistsPager> callback);

    @GET("/search?type=artist")
    public ArtistsPager searchArtists(@Query("q") String q, @Query("market") String market);

    @GET("/search?type=artist")
    public void searchArtists(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit, Callback<ArtistsPager> callback);

    @GET("/search?type=artist")
    public ArtistsPager searchArtists(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/search?type=artist")
    public void searchArtists(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit, Callback<ArtistsPager> callback);

    @GET("/search?type=artist")
    public ArtistsPager searchArtists(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit);


    @GET("/search?type=album")
    public void searchAlbums(@Query("q") String q, Callback<AlbumsPager> callback);

    @GET("/search?type=album")
    public AlbumsPager searchAlbums(@Query("q") String q);

    @GET("/search?type=album")
    public void searchAlbums(@Query("q") String q, @Query("market") String market, Callback<AlbumsPager> callback);

    @GET("/search?type=album")
    public AlbumsPager searchAlbums(@Query("q") String q, @Query("market") String market);

    @GET("/search?type=album")
    public void searchAlbums(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit, Callback<AlbumsPager> callback);

    @GET("/search?type=album")
    public AlbumsPager searchAlbums(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/search?type=album")
    public void searchAlbums(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit, Callback<AlbumsPager> callback);

    @GET("/search?type=album")
    public AlbumsPager searchAlbums(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit);


    @GET("/search?type=playlist")
    public void searchPlaylists(@Query("q") String q, Callback<PlaylistsPager> callback);

    @GET("/search?type=playlist")
    public PlaylistsPager searchPlaylists(@Query("q") String q);

    @GET("/search?type=playlist")
    public void searchPlaylists(@Query("q") String q, @Query("market") String market, Callback<PlaylistsPager> callback);

    @GET("/search?type=playlist")
    public PlaylistsPager searchPlaylists(@Query("q") String q, @Query("market") String market);

    @GET("/search?type=playlist")
    public void searchPlaylists(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit, Callback<PlaylistsPager> callback);

    @GET("/search?type=playlist")
    public PlaylistsPager searchPlaylists(@Query("q") String q, @Query("offset") int offset, @Query("limit") int limit);

    @GET("/search?type=playlist")
    public void searchPlaylists(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit, Callback<PlaylistsPager> callback);

    @GET("/search?type=playlist")
    public PlaylistsPager searchPlaylists(@Query("q") String q, @Query("market") String market, @Query("offset") int offset, @Query("limit") int limit);
}
