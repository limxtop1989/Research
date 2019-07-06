package com.limxtop.research.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by limxtop on 1/8/17.
 */
public class ParentViewGroup extends LinearLayout {

    private static final String TAG = "ParentViewGroup";

    public ParentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "onInterceptTouchEvent", "return false, " + ev.toString());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "onTouchEvent", "return false, " + event.toString());
        return false;
    }
}
