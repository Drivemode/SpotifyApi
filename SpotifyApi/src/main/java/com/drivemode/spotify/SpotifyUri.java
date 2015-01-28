package com.drivemode.spotify;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author KeishinYokomaku
 */
public class SpotifyUri {
	public static final String TAG = SpotifyUri.class.getSimpleName();
	public static final String SCHEME = "spotify";
	private static final Pattern PATTERN_DEFAULT_URI = Pattern.compile(SCHEME + ":(album|artist|track):([a-zA-Z0-9_-]+)");
	private static final Pattern PATTERN_PLAYLIST_MATCHER = Pattern.compile(SCHEME + ":user:([a-zA-Z0-9_-]+):(playlist):([a-zA-Z0-9_-]+)");
	private final String mScheme;
	private final String mUserId;
	private final ResourceType mResourceType;
	private final String mId;

	/* package */ SpotifyUri(ResourceType resourceType, String id) {
		this(null, resourceType, id);
	}

	/* package */ SpotifyUri(String userId, ResourceType resourceType, String id) {
		this(SCHEME, userId, resourceType, id);
	}

	/* package */ SpotifyUri(String scheme, String userId, ResourceType resourceType, String id) {
		mScheme = scheme;
		mUserId = userId;
		mResourceType = resourceType;
		mId = id;
	}

	@Override
	public String toString() {
		if (mUserId == null) {
			return SCHEME + ":" + mResourceType.toString() + ":" + mId;
		}
		return SCHEME + ":user:" + mUserId + ":" + mResourceType.toString() + ":" + mId;
	}

	/**
	 * Uri scheme.
	 * @return always "spotify"
	 */
	public @NonNull String getScheme() {
		return mScheme;
	}

	/**
	 * @return Spotify user id. Can be null.
	 */
	public @Nullable String getUserId() {
		return mUserId;
	}

	/**
	 * Resource type.
	 * @return one of {@link com.drivemode.spotify.SpotifyUri.ResourceType}
	 */
	public @NonNull ResourceType getResourceType() {
		return mResourceType;
	}

	/**
	 * Id for the resource.
	 * @return id
	 */
	public @NonNull String getId() {
		return mId;
	}

	public static SpotifyUri parse(String uri) {
		Matcher playlistMatcher = PATTERN_PLAYLIST_MATCHER.matcher(uri);
		if (playlistMatcher.matches()) {
			String user = playlistMatcher.group(1);
			ResourceType type = ResourceType.from(playlistMatcher.group(2));
			String id = playlistMatcher.group(3);
			return new SpotifyUri(user, type, id);
		}

		Matcher publicMatcher = PATTERN_DEFAULT_URI.matcher(uri);
		if (publicMatcher.matches()) {
			ResourceType type = ResourceType.from(publicMatcher.group(1));
			String id = publicMatcher.group(2);
			return new SpotifyUri(type, id);
		}
		return null;
	}

	public static enum ResourceType {
		ALBUM("album"),
		ARTIST("artist"),
		TRACK("track"),
		PLAYLIST("playlist");

		private final String mRaw;

		private ResourceType(String raw) {
			mRaw = raw;
		}

		public static ResourceType from(String raw) {
			for (ResourceType type : values()) {
				if (type.mRaw.equals(raw)) {
					return type;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return mRaw;
		}
	}
}