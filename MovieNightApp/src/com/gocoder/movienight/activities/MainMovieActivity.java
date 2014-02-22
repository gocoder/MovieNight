package com.gocoder.movienight.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import com.gocoder.movienight.R;
import com.gocoder.movienight.fragments.HomeMovieFeedFragment;
import com.gocoder.movienight.fragments.InTheatreMovieFeedFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainMovieActivity extends FragmentActivity implements TabListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);
        //setupNavigationTabs();
        setConfigurations();
        //showHomeFeedView();
        setupNavigationTabs();


    }

    private void setConfigurations() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void showHomeFeedView() {
        FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
        fts.replace(R.id.frame_feed_container, new HomeMovieFeedFragment());
        fts.commit();

    }

    private void setupNavigationTabs() {
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        Tab tabBoxOffice = actionBar.newTab().setText("Box Office")
                .setTag("BoxOffcieFragment")
                .setIcon(R.drawable.ic_boxoffice).setTabListener(this);

        Tab tabInTheatres = actionBar.newTab().setText("In Theatres")
                .setTag("InheatreFragment")
                .setIcon(R.drawable.ic_in_theatres).setTabListener(this);

        actionBar.addTab(tabBoxOffice);
        actionBar.addTab(tabInTheatres);
        actionBar.selectTab(tabBoxOffice);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_movie, menu);
        return true;
    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.d("DEBUG", tab.getTag() + " TAB is selected...");
        FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
        if (tab.getTag() == "BoxOffcieFragment") {
            fts.replace(R.id.frame_feed_container, new HomeMovieFeedFragment());
        } else {
            fts.replace(R.id.frame_feed_container, new InTheatreMovieFeedFragment());
        }

        fts.commit();

    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

}
