package com.gocoder.movienight.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.models.MovieModel;
import com.squareup.picasso.Picasso;

/**
 * Created by ashishn on 2/21/14.
 */
public class MovieIntent extends Activity {

    MovieModel movie;

    ImageView movieImage;

    TextView description;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_movie);
        Intent i = getIntent();
        this.movie = MovieModel.fromJson(i.getStringExtra("movieID"));
        movieImage = (ImageView) findViewById(R.id.movieImage);
        movieImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //movieImage.setScaleType(ImageView.ScaleType.FIT_END);
        description = (TextView) findViewById(R.id.description);
        process();
    }

    private void process() {
        Picasso.with(this).load(movie.getPosters().getOriginal()).into(movieImage);
        //ImageLoader.getInstance().displayImage(movie.getPosters().getOriginal(), movieImage);
        //ImageLoader.getInstance().
        description.setText(movie.getSynopsis());
    }


}