package com.limxtop.research.animator.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.limxtop.research.R;

@SuppressLint("NewApi") public class PathAnimatorActivity extends Activity implements OnClickListener {

	private View mLineView;
	
	private Path mPath;

	private ObjectAnimator mObjectAnimator;

	private Button mStartButton;
	private Button mEndButton;
	private Button mResumeButton;
	private Button mPauseButton;
	private Button mReverseButton;

	private ButtonState mCurrentButtonState;
	private ButtonState mStartButtonState = new StartButtonState();
	private ButtonState mEndButtonState = new EndButtonState();
	private ButtonState mResumeButtonState = new ResumeButtonState();
	private ButtonState mPauseButtonState = new PauseButtonState();
	private ButtonState mReverseButtonState =  new ReverseButtonState();

	private int[] mEnables;
	private int[] mDisables;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_animator);
		
		mPath = new Path();
		
		mLineView = findViewById(R.id.line);
		mLineView.setOnClickListener(this);
		
		mStartButton = (Button) findViewById(R.id.start);
		mStartButton.setOnClickListener(this);

		mEndButton = (Button) findViewById(R.id.end);
		mEndButton.setOnClickListener(this);

		mResumeButton = (Button) findViewById(R.id.resume);
		mResumeButton.setOnClickListener(this);

		mPauseButton = (Button) findViewById(R.id.pause);
		mPauseButton.setOnClickListener(this);

		mReverseButton = (Button) findViewById(R.id.reverse);
		mReverseButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bezier_animation, menu);
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

	@Override
	public void onClick(final View view) {
		int id = view.getId();

		View parent = (View) mLineView.getParent();
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();

		int destinationX;
		int destinationY;

		switch (id) {
			case R.id.start:
				destinationX = parentWidth - mLineView.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
				destinationY = parentHeight - mLineView.getHeight();
				/**
				 * As I understand it, the left top corner of mLineView is the pivot point of coordinate system of path.
				 */
				mPath.reset();
				mPath.moveTo(0, 0);
				mPath.lineTo(destinationX, 0);
				mObjectAnimator = ObjectAnimator.ofFloat(mLineView, "translationX", "translationY", mPath);
				mObjectAnimator.setDuration(1000);
				mObjectAnimator.addListener(new AnimatorListenerAdapter() {

					@Override
					public void onAnimationStart(Animator animation) {
						super.onAnimationStart(animation);
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						/**
                         * the code commented cause infinite loop, hence need special process here
						 */
//						mEndButton.performClick();
						mCurrentButtonState = mEndButtonState;
						mEnables = new int[] { R.id.start, R.id.reverse };
						mDisables = new int[] { R.id.end, R.id.resume, R.id.pause };
						mCurrentButtonState.action((ViewGroup) view.getParent(), mEnables, mDisables);
					}

					@Override
					public void onAnimationPause(Animator animation) {
						super.onAnimationPause(animation);
					}

					@Override
					public void onAnimationResume(Animator animation) {
						super.onAnimationResume(animation);
					}
				});
				mObjectAnimator.start();
				mEnables = new int[] { R.id.end, R.id.pause, R.id.reverse };
				mDisables = new int[] { R.id.start, R.id.resume };
				mCurrentButtonState = mStartButtonState;
				break;
			case R.id.end:
				mObjectAnimator.end();
				mEnables = new int[] { R.id.start, R.id.reverse };
				mDisables = new int[] { R.id.end, R.id.resume, R.id.pause };
				mCurrentButtonState = mEndButtonState;
				break;
			case R.id.resume:
				mObjectAnimator.resume();
				mEnables = new int[] { R.id.end, R.id.pause, R.id.reverse };
				mDisables = new int[] { R.id.start, R.id.resume };
				mCurrentButtonState = mResumeButtonState;
				break;
			case R.id.pause:
				mObjectAnimator.pause();
				mEnables = new int[] { R.id.resume };
				mDisables = new int[] {R.id.start, R.id.end, R.id.pause, R.id.reverse };
				mCurrentButtonState = mPauseButtonState;
				break;
			case R.id.reverse:
				mObjectAnimator.reverse();
				mEnables = new int[] { R.id.end, R.id.pause, R.id.reverse };
				mDisables = new int[] { R.id.start, R.id.resume };
				mCurrentButtonState = mReverseButtonState;
				break;
			default:
				break;
		}
		mCurrentButtonState.action((ViewGroup) view.getParent(), mEnables, mDisables);
	}
}
