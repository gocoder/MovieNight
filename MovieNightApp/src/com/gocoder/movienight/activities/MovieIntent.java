package com.gocoder.movienight.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;
import com.gocoder.movienight.R;
import com.gocoder.movienight.fragments.MovieFragment;
import com.gocoder.movienight.fragments.YouTubeActivity;
import com.gocoder.movienight.models.MovieModel;

import java.util.ArrayList;

/**
 * Created by ashishn on 2/21/14.
 */
public class MovieIntent extends FragmentActivity {


    private ViewPager mPager;

    ArrayList<MovieModel> movies;

    PagerAdapter mPagerAdapter;

    @Override
    public void onBackPressed() {
        finish();
//        if (mPager.getCurrentItem() == 0) {
//
//            super.onBackPressed();
//        } else {
//
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent i = new Intent(this, YouTubeActivity.class);
            Toast.makeText(getBaseContext(), Long.toString(movies.get(mPager.getCurrentItem()).getId()), Toast.LENGTH_SHORT).show();
            i.putExtra("movieid", Long.toString(movies.get(mPager.getCurrentItem()).getId()));
            startActivity(i);
        }


    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.moviepager);
        Intent i = getIntent();
        this.movies = MovieModel.fromJsonList(i.getStringExtra("movieID"));
        int positionSelected = Integer.parseInt(i.getStringExtra("position"));

        mPager = (ViewPager) findViewById(R.id.moviePager);

        mPager.setPageTransformer(true, new DepthPageTransformer());

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);

        mPager.setCurrentItem(positionSelected);

        Toast.makeText(getBaseContext(), " Swipe  <---> to view more", Toast.LENGTH_LONG).show();

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MovieFragment movie = new MovieFragment();
            movie.setMovie(movies.get(position));
            return movie;
        }

        @Override
        public int getCount() {
            return movies.size();
        }
    }


    class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            view.setTranslationX(-1 * view.getWidth() * position);

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                view.setTranslationX(pageWidth * -position);

                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else {
                view.setAlpha(0);
            }
        }
    }

}


