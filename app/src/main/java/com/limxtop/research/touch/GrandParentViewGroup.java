package com.limxtop.research.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by limxtop on 1/8/17.
 */
public class GrandParentViewGroup extends LinearLayout implements ChildView.MoveInterceptInterface {

    private static final String TAG = "GrandParentViewGroup";

    private boolean mIsIntercept = false;
    public GrandParentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "onInterceptTouchEvent", "return " + mIsIntercept + ", " +ev.toString());
        return mIsIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "onTouchEvent", "return true, " + event.toString());
        return true;
    }

    @Override
    public void interceptMove() {
        mIsIntercept = true;
    }
}
