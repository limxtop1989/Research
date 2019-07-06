package com.limxtop.research.touch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.limxtop.research.R;

public class HeaderLayout extends ViewGroup {
	private static final String TAG = HeaderLayout.class.getSimpleName();
	
	private TextView mCallType;
	
	private ImageView mArrow;

	public HeaderLayout(Context context) {
		super(context);
		initViews(context);
	}

	private void initViews(Context context) {
		mCallType = new TextView(context);
		mCallType.setText("hello kitty");
		this.addView(mCallType);
		
		mArrow = new ImageView(context);
		mArrow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.default_ptr_rotate));
		this.addView(mArrow);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		LogUtils.d("limxtop", TAG, "onLayout", "l=" + l + ", t=" + t + ", r=" + r + ", b=" + b);
		
		int parentHeight = b - t;
		
		int viewWidth = 0;
		int viewHeight = 0;
		
		if(isVisible(mCallType)) {
			viewWidth = mCallType.getMeasuredWidth();
			viewHeight = mCallType.getMeasuredHeight();
			mCallType.layout(l, (parentHeight - viewHeight) / 2, l + viewWidth, (parentHeight + viewHeight) / 2);
		}
		
		if(isVisible(mArrow)) {
			viewWidth = mArrow.getMeasuredWidth();
			viewHeight = mArrow.getMeasuredHeight();
			mArrow.layout(r - viewWidth, (parentHeight - viewHeight) / 2, r, (parentHeight + viewHeight) / 2);
		}
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(isVisible(mCallType)) {
			mCallType.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		}
		if(isVisible(mArrow)) {
			mArrow.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	private boolean isVisible(View view) {
		if(view != null && view.getVisibility() == View.VISIBLE) {
			return true;
		}
		return false;
	}
	

}
