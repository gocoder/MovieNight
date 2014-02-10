package com.gocoder.movienight.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.gocoder.movienight.R;

public class MainMovieActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_movie);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_movie, menu);
		return true;
	}

}
