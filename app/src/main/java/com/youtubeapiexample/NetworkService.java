package com.youtubeapiexample;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by tan on 27/03/17.
 **/

public interface NetworkService {

    @GET("playlistItems/")
    Observable<PlaylistItemsModel> getPlaylistVideos(@QueryMap Map<String, Object> queries);

}
