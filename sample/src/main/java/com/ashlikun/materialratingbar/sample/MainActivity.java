/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.ashlikun.materialratingbar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    RatingBar[] mDecimalRatingBars = new RatingBar[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        mDecimalRatingBars[0] = findViewById(R.id.library_decimal_ratingbar);
        mDecimalRatingBars[0].startAnimation(new RatingAnimation());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class RatingAnimation extends Animation {

        public RatingAnimation() {
            setDuration(mDecimalRatingBars[0].getNumStars()
                    * 4 * getResources().getInteger(android.R.integer.config_longAnimTime));
            setInterpolator(new LinearInterpolator());
            setRepeatCount(Animation.INFINITE);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int progress = Math.round(interpolatedTime * mDecimalRatingBars[0].getMax());
            for (RatingBar ratingBar : mDecimalRatingBars) {
                ratingBar.setProgress(progress);
            }
        }

        @Override
        public boolean willChangeTransformationMatrix() {
            return false;
        }

        @Override
        public boolean willChangeBounds() {
            return false;
        }
    }
}
