package com.gocoder.movienight.fragments;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import com.gocoder.movienight.R;
import com.gocoder.movienight.helpers.DeveloperKey;
import com.gocoder.movienight.helpers.YouTubeFailureRecoveryActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.HashMap;

/**
 * Created by ashishn on 2/24/14.
 */
public class YouTubeActivity extends YouTubeFailureRecoveryActivity {

    HashMap<Integer, String> videos = null;

    private int movieID;


    YouTubePlayerFragment youTubePlayerFragment;

    public void init() {
        if (videos != null) {
            return;
        }
        videos = new HashMap<Integer, String>();
        // robocop
        videos.put(770803774, "INmtQXUXez8");

        //lego
        videos.put(771305753, "fZ_JOBCLF-I");

        //300
        videos.put(771249085, "2zqy21Z29ps");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        youTubePlayerFragment.onDestroy();
        //this.registerReceiver()
        //unregisterReceiver();
        finish();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Intent i = getIntent();
        this.movieID = Integer.parseInt(i.getStringExtra("movieid"));
        setContentView(R.layout.fragments_demo);
        youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        assert youTubePlayerFragment != null;
        youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        youTubePlayerFragment.onDestroy();

        //youTubePlayerFragment.unregisterForCo;
        finish();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {

        if (!wasRestored) {
            //Toast.makeText(getBaseContext(),"playing"+videos.get(movieID),Toast.LENGTH_SHORT).show();
            player.loadVideo(videos.get(movieID));

            if (!player.isPlaying()) {
                player.play();
            }


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        super.unregisterReceiver(receiver);
    }
}

