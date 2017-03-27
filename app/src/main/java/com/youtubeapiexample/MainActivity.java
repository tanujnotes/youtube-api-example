package com.youtubeapiexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String PLAYLIST_ID = "PLuBXqtS2jaLMu81JnF6AOnRHzG6Csbd6y";

    @Inject
    NetworkService networkService;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplication()).getNetComponent().inject(this);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);
        getPlaylistItems();
    }

    private void getPlaylistItems() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("part", "snippet");
        map.put("maxResults", 5); // max value 50
        map.put("playlistId", PLAYLIST_ID);
        map.put("key", BuildConfig.youtube_key); // Set your key in gradle file

        Observable<PlaylistItemsModel> observable = networkService.getPlaylistVideos(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlaylistItemsModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText(getString(R.string.error));
                        Log.d("ERROR", e.getMessage());
                    }

                    @Override
                    public void onNext(PlaylistItemsModel model) {
                        textView.setText(getString(R.string.success));
                        // You got the response model. Do whatever you want with it.
                    }
                });
    }
}
