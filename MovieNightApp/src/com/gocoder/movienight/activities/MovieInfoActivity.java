package com.gocoder.movienight.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
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

    ScrollView scrollView;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_movie);
        Intent i = getIntent();

        this.movie = MovieModel.fromJson(i.getStringExtra("movieID"));
        Toast.makeText(getBaseContext(), Long.toString(movie.getId()), Toast.LENGTH_SHORT).show();
        movieImage = (ImageView) findViewById(R.id.movieImage);
        movieImage.setScaleType(ImageView.ScaleType.FIT_XY);

        movieImage.setScaleType(ImageView.ScaleType.FIT_START);

        description = (TextView) findViewById(R.id.description);
        scrollView = (ScrollView) findViewById(R.id.ScrollView01);
//        movieImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    System.out.println("clips=" + movie.getLinks().getClips());
//                    new RottenTomatoesClient().getTrailer(movie.getLinks().getClips());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        VideoView mVideoView = (VideoView) findViewById(R.id.trailer);

        mVideoView.setVideoURI(Uri.parse("http://www.videodetective.net/flash/players/?customerid=300120&playerid=632&publishedid=72288&playlistid=0&sub=Facebook&pversion=5.6"));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.postInvalidateDelayed(100);
        mVideoView.start();


        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.videodetective.net/flash/players/?customerid=300120&playerid=632&publishedid=72288&playlistid=0&sub=Facebook&pversion=5.6")));

        process();
    }

    private void process() {


        Picasso.with(this).load(movie.getPosters().getOriginal()).into(movieImage);
        description.setText(movie.getSynopsis());


    }


    private Bitmap getBlurBitmap(Bitmap src) {

        final int widthKernal = 5;
        final int heightKernal = 5;

        int w = src.getWidth();
        int h = src.getHeight();

        Bitmap blurBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                int r = 0;
                int g = 0;
                int b = 0;
                int a = 0;

                for (int xk = 0; xk < widthKernal; xk++) {
                    for (int yk = 0; yk < heightKernal; yk++) {
                        int px = x + xk - 2;
                        int py = y + yk - 2;

                        if (px < 0) {
                            px = 0;
                        } else if (px >= w) {
                            px = w - 1;
                        }

                        if (py < 0) {
                            py = 0;
                        } else if (py >= h) {
                            py = h - 1;
                        }

                        int intColor = src.getPixel(px, py);
                        r += Color.red(intColor);
                        g += Color.green(intColor);
                        b += Color.blue(intColor);
                        a += Color.alpha(intColor);

                    }
                }

                blurBitmap.setPixel(x, y, Color.argb(a / 25, r / 25, g / 25, b / 25));

            }
        }

        return blurBitmap;
    }


}


