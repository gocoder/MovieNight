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
import android.view.MenuItem;
import android.widget.SearchView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.fragments.HomeMovieFeedFragment;
import com.gocoder.movienight.fragments.InTheatreMovieFeedFragment;
import com.gocoder.movienight.fragments.SearchMoviesFragment;

public class MainMovieActivity extends FragmentActivity implements TabListener, SearchView.OnQueryTextListener{

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);
        setupNavigationTabs();

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
                .setTag("BoxOfficeFragment")
                .setIcon(R.drawable.ic_boxoffice).setTabListener(this);

        Tab tabInTheatres = actionBar.newTab().setText("In Theatres")
                .setTag("InTheatreFragment")
                .setIcon(R.drawable.ic_in_theatres).setTabListener(this);

        actionBar.addTab(tabBoxOffice);
        actionBar.addTab(tabInTheatres);
        actionBar.selectTab(tabInTheatres);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_movie, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        renderSelectedTabView(tab);

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.d("DEBUG", tab.getTag() + " TAB is selected...");
        renderSelectedTabView(tab);

    }
    protected void renderSelectedTabView(Tab tab){
        if (tab.getTag() == "BoxOfficeFragment") {
            fragmentRender("BoxOfficeFragment",null);
        } else if(tab.getTag() == "InTheatreFragment"){
            fragmentRender("InTheatreFragment",null);
        }
    }

    protected void fragmentRender(String model, String query){
        if(!model.isEmpty()){
            FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
             if(model.equals("BoxOfficeFragment")){
                fts.replace(R.id.frame_feed_container, new HomeMovieFeedFragment());
             }
             else if(model.equals("InTheatreFragment")){
                fts.replace(R.id.frame_feed_container, new InTheatreMovieFeedFragment());
             }
             else if(model.equals("SearchFragment")){
                fts.replace(R.id.frame_feed_container, new SearchMoviesFragment(query));
             }
            fts.commit();
        }
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }
    private void setupSearchView(MenuItem searchItem) {

        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }

        mSearchView.setOnQueryTextListener(this);
    }

    public boolean onQueryTextChange(String newText) {
       // mStatusView.setText("Query = " + newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        if(query.isEmpty()){
            return false;
        }
        fragmentRender("SearchFragment", query);
        return true;
    }


    protected boolean isAlwaysExpanded() {
        return false;
    }


}
