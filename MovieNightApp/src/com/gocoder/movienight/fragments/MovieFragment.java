package com.gocoder.movienight.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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

    //Target target;

    public MovieFragment() {

    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

//    public MovieFragment(MovieModel movieModel) {
//        this.movie = movieModel;
//    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(getActivity(), "orientationchanged", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getActivity(), YouTubeActivity.class);
//            startActivity(i);
//        }
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.individual_movie, container, false);


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        movieImage = (ImageView) getView().findViewById(R.id.movieImage);


        movieImage.setScaleType(ImageView.ScaleType.FIT_XY);

        description = (TextView) getView().findViewById(R.id.description);
        scrollView = (ScrollView) getView().findViewById(R.id.ScrollView01);

        scrollView.setSmoothScrollingEnabled(true);


        process();

    }


    private void process() {

//        target = new Target() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
//                final RenderScript rs = RenderScript.create(getActivity());
//                final Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
//                final Allocation output = Allocation.createTyped(rs, input.getType());
//                final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//                script.setRadius(50f /* e.g. 3.f */);
//                script.setInput(input);
//                script.forEach(output);
//                output.copyTo(bitmap);
//                //mo
//                movieImage.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable drawable) {
//
//                Toast.makeText(getActivity(), "error=", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable drawable) {
//
//            }
//        };


        Picasso.with(getActivity()).load(movie.getPosters().getOriginal()).into(movieImage);

        description.setText(movie.getSynopsis());

        description.setTypeface(Typeface.SANS_SERIF);

        description.append(Html.fromHtml("<br/><br/><strong><em>Cast</em></strong>"));

    }


}
