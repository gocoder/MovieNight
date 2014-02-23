package com.gocoder.movienight.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.models.MovieModel;
import com.squareup.picasso.Picasso;

/**
 * Created by ashishn on 2/21/14.
 */
public class MovieInfoActivity extends Activity {

    MovieModel movie;

    ImageView movieImage;

    TextView description;


    public void onCreate(Bundle savedInstanceState) {
    	Log.d("DEBUG","Movie Info");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_movie);
        Intent i = getIntent();
        this.movie = MovieModel.fromJson(i.getStringExtra("movieID"));
        movieImage = (ImageView) findViewById(R.id.movieImage);
        movieImage.setScaleType(ImageView.ScaleType.FIT_START);
        description = (TextView) findViewById(R.id.description);
        Log.d("DEBUG","Movie Info - process");
        process();
    }

    private void process() {
        Picasso.with(this).load(movie.getPosters().getOriginal()).noFade().into(movieImage);
        description.setText(movie.getSynopsis());

    }


}