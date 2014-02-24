package com.gocoder.movienight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.helpers.OnSwipeTouchListener;
import com.gocoder.movienight.helpers.SwipeInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by arunsund on 2/22/14.
 */
public class MovieNightSplash extends FragmentActivity  implements SwipeInterface
{
    ImageView ivSplashView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConfigurations();
        getActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        ivSplashView = (ImageView) findViewById(R.id.ivSplashView);
        ivSplashView.setOnTouchListener(new OnSwipeTouchListener(this,this));
        ivSplashView.setVisibility(0);
        changeBg();
    }
    private void setConfigurations() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }
    private void changeBg(){
        Picasso.with(this).load("http://www.hollywoodreporter.com/sites/default/files/imagecache/blog_post_349_width/2013/09/robocop_poster_p_2013.jpg")
                .noFade().into(ivSplashView, new Callback() {
            @Override
            public void onSuccess() {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_zoomout);
                ivSplashView.startAnimation(animFadein);
            }

            @Override
            public void onError() {

            }
        });


    }
    private void startApp(){
        Intent i = new Intent(MovieNightSplash.this, MainMovieActivity.class);
        startActivity(i);
    }

    @Override
    public void onSwipeRight() {
        startApp();
    }

    @Override
    public void onSwipeLeft() {
        startApp();
    }

    @Override
    public void onSwipeUp() {
    }

    @Override
    public void onSwipeDown() {
    }
}