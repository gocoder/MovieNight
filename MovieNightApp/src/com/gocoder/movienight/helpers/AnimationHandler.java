package com.gocoder.movienight.helpers;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.gocoder.movienight.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by arunsund on 2/23/14.
 */
public class AnimationHandler {
    private static boolean stopAnimation;
    private static ArrayList<Integer> animTypes = new ArrayList<Integer>(){{
        add(R.anim.anim_zoomout);
        add(R.anim.anim_zoomout_2);
        add(R.anim.anim_move_left);
        add(R.anim.anim_move_right);
        add(R.anim.anim_zoomin);
        add(R.anim.anim_move_scale_up);
        add(R.anim.anim_move_left);
        add(R.anim.anim_move_right);
        add(R.anim.anim_move_scale_down);
    }};
    private AnimationHandler(){}
    private static  int getRandomAnimation(){
        Random randomNumber = new Random();
        return animTypes.get(randomNumber.nextInt(animTypes.size())).intValue();
    }
    public static void animate(final Context context,final ImageView ivSplashView, final ArrayList<String> movieImages,final int imgCtr){
        Picasso.with(context).load(movieImages.get(imgCtr))
                .noFade().into(ivSplashView, new Callback() {
            @Override
            public void onSuccess() {
                Animation animation = AnimationUtils.loadAnimation(context, getRandomAnimation());
                ivSplashView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if(!stopAnimation){
                            if(imgCtr < movieImages.size()-1){
                                animate(context, ivSplashView, movieImages, imgCtr + 1);
                            }
                            else{
                                animate(context, ivSplashView, movieImages, 0);
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onError() {

            }
        });
    }
    public static void stopAnimation(){
        stopAnimation = true;
    }
}
