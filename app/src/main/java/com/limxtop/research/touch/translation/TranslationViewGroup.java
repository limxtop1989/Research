package com.limxtop.research.touch.translation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class TranslationViewGroup extends RelativeLayout {

    private float mLastX;
    private float mLastY;

    private Translatable mTranslate;

    public TranslationViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(Translatable t) {
        mTranslate = t;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float deltaX = x - mLastX;
                float deltaY = y - mLastY;
                if (null != mTranslate) {
                    mTranslate.translate(deltaX, deltaY);
                }
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return true;

    }

    interface Translatable {
        void translate(float x, float y);
    }
}
