package com.limxtop.research.touch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.limxtop.research.R;


public class PullToShowActivity extends Activity {
	
	private HeaderLayout mHeaderLayout;
	
	private ListView mListView;
	
	private List<String> mListItems;
	
	private ArrayAdapter<String> mAdapter;

	private static final float FRICTION = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_show);
        
        mListView = (ListView) findViewById(R.id.listView);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				100, Gravity.CENTER_HORIZONTAL);
        FrameLayout frame = new FrameLayout(this);
        mHeaderLayout = new HeaderLayout(this);
        mHeaderLayout.setVisibility(View.GONE);
		frame.addView(mHeaderLayout, lp);
        mListView.addHeaderView(frame, null, false);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListItems());
        mListView.setAdapter(mAdapter);
        mListView.setOnTouchListener(onTouchListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pull_to_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    private List<String> getListItems() {
    	mListItems = new ArrayList<String>();
    	for(int i = 0; i < 10; i++) {
    		mListItems.add("item " + i);
    	}
    	
    	return mListItems;
    }
    
    private OnTouchListener onTouchListener = new OnTouchListener() {

    	private float mInitY;
    	private float mLastY;
    	
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				if(isReadyForPull()) {
					mLastY = mInitY = event.getY();
					return true;
				}
				break;
				
			case MotionEvent.ACTION_MOVE:
				mLastY = event.getY();
				int newScrollValue = Math.round(Math.min(mInitY - mLastY, 0) / FRICTION);
				final int maximumPullScroll = Math.round(mHeaderLayout.getHeight() * FRICTION);
				newScrollValue = Math.min(maximumPullScroll, Math.max(-maximumPullScroll, newScrollValue));
				mHeaderLayout.setVisibility(View.VISIBLE);
				v.scrollTo(0, (int) (mInitY - mLastY));
				break;

			default:
				break;
			}
			return false;
		}
    	
    };
    
    private boolean isReadyForPull() {
    	return isFirstItemVisible();
    }
    
    
    private boolean isFirstItemVisible() {

		if (null == mAdapter || mAdapter.isEmpty()) {
			return true;

		} else {

			/**
			 * This check should really just be:
			 * mRefreshableView.getFirstVisiblePosition() == 0, but PtRListView
			 * internally use a HeaderView which messes the positions up. For
			 * now we'll just add one to account for it and rely on the inner
			 * condition which checks getTop().
			 */
			if (mListView.getFirstVisiblePosition() <= 1) {
				final View firstVisibleChild = mListView.getChildAt(0);
				if (firstVisibleChild != null) {
					return firstVisibleChild.getTop() >= mListView.getTop();
				}
			}
		}

		return false;
	}

    
}
