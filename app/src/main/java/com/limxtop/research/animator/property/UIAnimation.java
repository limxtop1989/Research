/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.limxtop.research.animator.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * A helper class to provide a simple animation when user selects any of the three icons on the
 * main UI.
 */
public class UIAnimation {

    private AnimatorSet mCurrentAnimator;
    private ImageView mSourceImageView;
    private ImageView mDestinationImageView;
    private final View mContainerView;
    private final int mAnimationDurationTime;

    private final Rect startBounds;
    private final Rect finalBounds;
    private final Point globalOffset;
    private float mStartScale;


    private UIStateListener mListener;
    private UIState mState = UIState.HOME;

    public UIAnimation(View containerView, ImageView sourceImageView, ImageView expandedView,
                       int animationDuration, UIStateListener listener) {
        mContainerView = containerView;
        mSourceImageView = sourceImageView;
        mDestinationImageView = expandedView;
        mAnimationDurationTime = animationDuration;
        mListener = listener;
        startBounds = new Rect();
        finalBounds = new Rect();
        globalOffset = new Point();
        calculateBound();
        mSourceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb();
            }
        });
    }

    private void calculateBound() {
        // TODO: top of when start is not correct.

        mSourceImageView.getGlobalVisibleRect(startBounds);
        mContainerView.getGlobalVisibleRect(finalBounds, globalOffset);
        mDestinationImageView.getGlobalVisibleRect(finalBounds);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
//        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            mStartScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = mStartScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            mStartScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = mStartScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }
    }

    private void zoomImageFromThumb() {
        if (mCurrentAnimator != null) {
            return;
        }

//        mSourceImageView.setAlpha(0f);
        mDestinationImageView.setVisibility(View.GONE);

        mDestinationImageView.setPivotX(0f);
        mDestinationImageView.setPivotY(0f);

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mDestinationImageView, View.X, startBounds.left, finalBounds.left);
        xAnimator.addUpdateListener((ValueAnimator animation) -> {
            Log.d("Maxim X = ", String.valueOf(animation.getAnimatedValue()));
        });
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mDestinationImageView, View.Y, startBounds.top, finalBounds
                .top);
        yAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("Maxim Y = ", String.valueOf(animation.getAnimatedValue()));
            }
        });
        AnimatorSet zommInAnimator = new AnimatorSet();
        zommInAnimator.play(xAnimator).with(
                yAnimator).with(
                ObjectAnimator.ofFloat(mDestinationImageView, View.SCALE_X, mStartScale, 1f))
                .with(ObjectAnimator.ofFloat(mDestinationImageView, View.SCALE_Y, mStartScale, 1f))
                .with(ObjectAnimator.ofFloat(mDestinationImageView, View.ALPHA, 0, 1))
                .after(ObjectAnimator.ofFloat(mSourceImageView, View.ALPHA, 1, 0));
        zommInAnimator.setDuration(mAnimationDurationTime);
        zommInAnimator.setInterpolator(new DecelerateInterpolator());
        zommInAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                mDestinationImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
                if (mListener != null) {
//                    mState = UIState.getUIState(index);
                    mListener.onUIStateChanged(mState);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        zommInAnimator.start();
        mCurrentAnimator = zommInAnimator;

        final float startScaleFinal = mStartScale;
        mDestinationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    return;
                }
                AnimatorSet zoomOutAnimator = new AnimatorSet();
                zoomOutAnimator.play(ObjectAnimator
                        .ofFloat(mDestinationImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(mDestinationImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(mDestinationImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(mDestinationImageView,
                                        View.SCALE_Y, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(mDestinationImageView,
                                        View.ALPHA, 1, 0))
                        .before(ObjectAnimator
                                .ofFloat(mSourceImageView,
                                        View.ALPHA, 0, 1));

                zoomOutAnimator.setDuration(mAnimationDurationTime);
                zoomOutAnimator.setInterpolator(new DecelerateInterpolator());
                zoomOutAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mSourceImageView.setAlpha(1f);
                        mDestinationImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                        if (mListener != null) {
                            mState = UIState.HOME;
                            mListener.onUIStateChanged(mState);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mSourceImageView.setAlpha(1f);
                        mDestinationImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                zoomOutAnimator.start();
                mCurrentAnimator = zoomOutAnimator;
            }
        });
    }

    public enum UIState {
        MIC_UP(0), SOUND_UP(1), MUSIC_UP(2), HOME(3);
        private int mState;

        UIState(int state) {
            mState = state;
        }

        static UIState getUIState(int state) {
            for (UIState uiState : values()) {
                if (uiState.mState == state) {
                    return uiState;
                }
            }
            return null;
        }
    }

    public interface UIStateListener {
        void onUIStateChanged(UIState state);
    }

    public void transitionToHome() {
        if (mState == UIState.HOME) {
            return;
        }
        mDestinationImageView.callOnClick();

    }
}
