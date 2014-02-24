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

import java.util.ArrayList;

/**
 * Created by arunsund on 2/22/14.
 */
public class MovieNightSplash extends FragmentActivity  implements SwipeInterface
{
    ImageView ivSplashView;
    ArrayList<String> movieImages;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConfigurations();
        setMovieImages();
        getActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        ivSplashView = (ImageView) findViewById(R.id.ivSplashView);
        ivSplashView.setOnTouchListener(new OnSwipeTouchListener(this,this));
        ivSplashView.setVisibility(0);
        changeBg();
    }

    private void setMovieImages() {
        movieImages = new ArrayList<String>();
        movieImages.add("http://www.hollywoodreporter.com/sites/default/files/imagecache/blog_post_349_width/2013/09/robocop_poster_p_2013.jpg");
        movieImages.add("http://blogs-images.forbes.com/scottmendelson/files/2014/02/lego_a.jpg");
        movieImages.add("http://www.thestranger.com/binary/b062/american-hustle-posters-sony.jpg");
        movieImages.add("http://www.nerdist.com/wp-content/uploads/2014/02/monuments2.jpg");
        movieImages.add("http://www.hdwallpapersin.com/files/submissions/jack_ryan_shadow_recruit_movie_wallpaper_2144723299.jpg");
        movieImages.add("http://lastyearsgirl.pixlet.net/wp-content/uploads/2014/01/endless-love.jpg");
        movieImages.add("http://7films.dendelionblu.me/images/2014/2/11//a-winters-tale.jpg");
        movieImages.add("http://i2.cdn.turner.com/cnn/dam/assets/140214102432-about-last-night-kevin-hart-story-top.jpg");
        movieImages.add("http://pop-verse.com/wp-content/uploads/2014/01/american-hustle.jpg");
        movieImages.add("http://www.awn.com/sites/default/files/image/featured/1015650-imagineer-mocha-tracks-wolf-wall-street.jpg");
        movieImages.add("http://img2.wikia.nocookie.net/__cb20131103205459/disney/images/6/66/Frozen_castposter.jpg");

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