package com.gocoder.movienight.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gocoder.movienight.R;
import com.gocoder.movienight.models.MovieModel;
import com.squareup.picasso.Picasso;

/**
 * Created by ashishn on 2/23/14.
 */
public class MovieFragment extends Fragment {

    MovieModel movie;

    ImageView movieImage;

    TextView description;

    ScrollView scrollView;

    public MovieFragment(MovieModel movieModel) {
        this.movie = movieModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.individual_movie, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        movieImage = (ImageView) getView().findViewById(R.id.movieImage);


        movieImage.setScaleType(ImageView.ScaleType.FIT_START);

        description = (TextView) getView().findViewById(R.id.description);
        scrollView = (ScrollView) getView().findViewById(R.id.ScrollView01);
        process();

    }


    private void process() {

        Picasso.with(getActivity()).load(movie.getPosters().getOriginal()).into(movieImage);
        description.setText(movie.getSynopsis());

    }

}
