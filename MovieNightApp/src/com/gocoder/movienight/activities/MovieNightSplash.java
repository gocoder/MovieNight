package com.gocoder.movienight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.helpers.AnimationHandler;
import com.gocoder.movienight.helpers.OnSwipeTouchListener;
import com.gocoder.movienight.helpers.SwipeInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by arunsund on 2/22/14.
 */
public class MovieNightSplash extends FragmentActivity  implements SwipeInterface
{
    ImageView ivSplashView;
    final ArrayList<String> movieImages = new ArrayList<String>();;
    private int imageCtr;
    private final int REQUEST_CODE = 20;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConfigurations();
        setMovieImages();
        getActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        ivSplashView = (ImageView) findViewById(R.id.ivSplashView);
        ivSplashView.setOnTouchListener(new OnSwipeTouchListener(this,this));
        ivSplashView.setVisibility(0);
        startAnimation();
    }

    private void setMovieImages() {
        movieImages.add("http://www.hollywoodreporter.com/sites/default/files/imagecache/blog_post_349_width/2013/09/robocop_poster_p_2013.jpg");
        movieImages.add("http://cinefilles.files.wordpress.com/2013/12/pompeii_xlg.jpg");
        movieImages.add("http://cinemasalem.com/wordpress/wp-content/uploads/2014/01/LEGO-Movie-Poster-2014-HIgh-Resolution.jpg");
        movieImages.add("http://www.thestranger.com/binary/b062/american-hustle-posters-sony.jpg");
        movieImages.add("http://www.nerdist.com/wp-content/uploads/2014/02/monuments2.jpg");
        movieImages.add("http://www.hdwallpapersin.com/files/submissions/jack_ryan_shadow_recruit_movie_wallpaper_2144723299.jpg");
        movieImages.add("http://lastyearsgirl.pixlet.net/wp-content/uploads/2014/01/endless-love.jpg");
        movieImages.add("http://7films.dendelionblu.me/images/2014/2/11//a-winters-tale.jpg");
        movieImages.add("http://images.alphacoders.com/482/482507.jpg");
        movieImages.add("http://i2.cdn.turner.com/cnn/dam/assets/140214102432-about-last-night-kevin-hart-story-top.jpg");
        movieImages.add("http://pop-verse.com/wp-content/uploads/2014/01/american-hustle.jpg");
        movieImages.add("http://www.awn.com/sites/default/files/image/featured/1015650-imagineer-mocha-tracks-wolf-wall-street.jpg");
        movieImages.add("http://img2.wikia.nocookie.net/__cb20131103205459/disney/images/6/66/Frozen_castposter.jpg");

    }
    private void startAnimation(){
        AnimationHandler.resetAnimation();
        AnimationHandler.animate(getApplicationContext(),ivSplashView,movieImages, 0);
    }

    private void setConfigurations() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void startApp(){
        AnimationHandler.stopAnimation();
        Intent i = new Intent(MovieNightSplash.this, MainMovieActivity.class);
        startActivityForResult(i, REQUEST_CODE);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            startAnimation();
        }
    }

}