package com.gocoder.movienight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.gocoder.movienight.R;
import com.gocoder.movienight.fragments.MovieFragment;
import com.gocoder.movienight.models.MovieModel;

import java.util.ArrayList;

/**
 * Created by ashishn on 2/21/14.
 */
public class MovieIntent extends FragmentActivity {


    private ViewPager mPager;

    ArrayList<MovieModel> movies;


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

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviepager);
        Intent i = getIntent();
        this.movies = MovieModel.fromJsonList(i.getStringExtra("movieID"));
        int positionSelected = Integer.parseInt(i.getStringExtra("position"));

        mPager = (ViewPager) findViewById(R.id.moviePager);

        mPager.setPageTransformer(true, new DepthPageTransformer());

        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);

        mPager.setCurrentItem(positionSelected);

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MovieFragment(movies.get(position));
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


