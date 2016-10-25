package io.github.yavski.fabspeeddial.samples;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;




import io.github.yavski.fabmenu.samples.R;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;



/**
 * Created by yiyangtsui on 2016-10-24.
 */

public class CirularRevealAcitivity extends BaseSampleActivity {



    ImageView androidImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal

        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        androidImage = (ImageView) findViewById(R.id.androidImage);

        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    hide(androidImage);
                else
                    show(androidImage);
            }
        });


        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_hide:
                        hide(androidImage);
                        break;
                    case R.id.action_show:
                        show(androidImage);
                        break;
                }
                return false;
            }
        });


    }

    // To reveal a previously invisible view using this effect:
    @TargetApi(21)
    private void show(final View view) {
        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                0, finalRadius);
        anim.setDuration(1000);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    // To hide a previously visible view using this effect:
    @TargetApi(21)
    private void hide(final View view) {

        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                initialRadius, 0);
        anim.setDuration(1000);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
    }


}
