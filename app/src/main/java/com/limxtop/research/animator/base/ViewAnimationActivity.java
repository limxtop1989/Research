package com.limxtop.research.animator.base;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.limxtop.research.R;


public class ViewAnimationActivity extends Activity implements View.OnClickListener {

    private CheckBox mFillBefore;
    private CheckBox mFillAfter;

    private View mAnimatorView;

    private Button mScaleButton;
    private Button mRotateButton;
    private Button mAlphaButton;
    private Button mTransitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mAnimatorView = findViewById(R.id.animation_view);
        mAnimatorView.setOnClickListener(this);

        mFillBefore = (CheckBox) findViewById(R.id.fill_before);
        mFillAfter = (CheckBox) findViewById(R.id.fill_after);

        mScaleButton = (Button) findViewById(R.id.scale_button);
        mScaleButton.setOnClickListener(this);
        mRotateButton = (Button) findViewById(R.id.rotate_button);
        mRotateButton.setOnClickListener(this);
        mAlphaButton = (Button) findViewById(R.id.alpha_button);
        mAlphaButton.setOnClickListener(this);
        mTransitionButton = (Button) findViewById(R.id.transition_button);
        mTransitionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (R.id.animation_view == view.getId()) {
            // The clickable area is not change after translation animation
            Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
            return;
        }
        Animation animation = initAnimation(view);
        if (null != animation) {
            doAnimation(animation);
        }
    }

    @Nullable
    private Animation initAnimation(View view) {
        int id = view.getId();
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        Animation animation;
        switch (id) {
            case R.id.scale_button:
                // scale to double size repeatedly
                animation = new ScaleAnimation(1, 2, 1, 2,
                        Animation.RELATIVE_TO_SELF, (float) 0.5,
                        Animation.RELATIVE_TO_SELF, (float) 0.5);
                break;
            case R.id.rotate_button:
                // rotate  from zero degree to 360 degree
                animation = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF, (float) 0.5,
                        Animation.RELATIVE_TO_SELF, (float) 0.5);
                break;

            case R.id.alpha_button:
                // animate from opacity to transparency
                animation = new AlphaAnimation(1, 0);
                break;

            case R.id.transition_button:
                // vibrate horizontally and make the amplitude to be half of the view;
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, (float) -0.5,
                        Animation.RELATIVE_TO_SELF, (float) 0.5,
                        Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0);
//                animation = new TranslateAnimation(
//                        Animation.RELATIVE_TO_SELF, 0,
//                        Animation.RELATIVE_TO_SELF, 2,
//                        Animation.RELATIVE_TO_SELF, 0,
//                        Animation.RELATIVE_TO_SELF, 0
//                );

                break;

            default:
                animation = null;
                break;
        }
        if (mFillBefore.isChecked()) {
            animation.setFillEnabled(true);
            // TODO: what happen when fill before is true
            animation.setFillBefore(true);
        } else {
            animation.setFillEnabled(false);
            animation.setFillBefore(false);
        }

        if (mFillAfter.isChecked()) {
            animation.setFillAfter(true);
        } else {
            animation.setFillAfter(false);
        }
        return animation;
    }

    private void doAnimation(Animation animation) {
        animation.setRepeatMode(Animation.REVERSE);
        if (animation instanceof TranslateAnimation) {
            animation.setDuration(1000);
//            animation.setRepeatCount(1);
        } else {
            animation.setDuration(2000);
            animation.setRepeatCount(Animation.INFINITE);
        }
        mAnimatorView.startAnimation(animation);
    }
}
