SpotifyApi
==========

Spotify Web API Wrapper with Retrofit and OkClients just for Android.  
This library includes authentication and account token management feature.

## Set up

Prepare `SpotifyApi` instance on your `Application`.

```java
public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    SpotifyApi.initialize(this, new ClientConfig.Builder()
        .setClientId("clientId")
        .setClientSecret("clientSecret")
        .setRedirectUri("redirectUri")
        .build());
  }
}
```

`SpotifyApi` has a responsibility to handle api calls and callback handler for user auth.

## Start user authentication

You can authenticate user with this.

```java
public class AuthActivity extends Activity {
  public void onAuthButtonClick(View view) {
    SpotifyApi.getInstance().authorize(this, new String[] {"playlist-read-private", "streaming", "user-library-read"}, false);
  }
}
```

You should prepare the activity that receives redirect uri callback.
The callback redirect handling code is below.

```java
public class AuthActivity extends Activity {
  @Override
  public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    SpotifyApi.getInstance().onCallback(intent.getData(), this);
  }
}
```

## Use with Loaders

The library provides abstraction of `AsyncTaskLoader` so you can easily integrate with generic Android framework.

```java
public class MyselfLoader extends SpotifyLoader<User> {
  public MyselfLoader(Context context, SpotifyApi api) {
    super(context, api);
  }

  @Override
  public User call(SpotifyService service) throws Exception {
    return service.getMe();
  }
}
```

SpotifyLoader will automatically refresh the token if it is expired.

## Retrofit callbacks

This library is based on [Retrofit](https://github.com/square/retrofit/) so you can use its callback framework.

```java
public class MyActivity extends Activity {
  public void onStartLoadClick(View view) {
    SpotifyApi.getInstance().getSpotifyApi().getMe(new Callback<User>() {
      @Override
      public void success(User user, Response response) {}

      @Override
      public void failure(RetrofitError error) {}
    });
  }
}
```

Callback will be executed on the main thread.

## Acknowledgement

The original library project for common Java project is [here](https://github.com/kaaes/spotify-web-api-android).
We optimize the library for use in Android project.

## License

```
Copyright 2015 Drivemode, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
