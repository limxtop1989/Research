package com.limxtop.research.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by limxtop on 1/8/17.
 */
public class ChildView extends View {

    private static final String TAG = "ChildView";

    private int mIntialCorodinate = 0;

    private MoveInterceptInterface mMoveIntercept;

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "onTouchEvent", "return true, " + event.toString());
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIntialCorodinate = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distance = (int) (event.getY() - mIntialCorodinate);
                LogUtils.d(TAG, "onTouchEvent", "distance = " + distance);
                if (distance > 100) {
                    mMoveIntercept.interceptMove();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return true;
    }

    void registerMoveInterceptLisener(MoveInterceptInterface moveIntercept) {
        mMoveIntercept = moveIntercept;
    }

    interface MoveInterceptInterface {
        void interceptMove();
    }
}
