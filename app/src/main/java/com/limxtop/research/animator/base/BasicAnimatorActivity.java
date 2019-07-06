package com.limxtop.research.animator.base;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.limxtop.research.R;

@SuppressLint("NewApi") 
public class BasicAnimatorActivity extends Activity implements OnClickListener {

	private View mVanish;
	private View mKeyFrame;
	private View mTransition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_value_animator);
		
		mVanish = findViewById(R.id.vanish);
		mVanish.setOnClickListener(this);
		
		mKeyFrame = findViewById(R.id.key_frame);
		mKeyFrame.setOnClickListener(this);
		
		mTransition = findViewById(R.id.value_animator);
		mTransition.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animator, menu);
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
	public void onClick(final View v) {
		
		int id = v.getId();
		switch (id) {
		case R.id.vanish:
			ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
			animator.setDuration(2000);
			animator.start();
			break;
			/**
             * we need to use dp rather than px value to translate in real projects. The way I know at present is that
			 * we pre multiply the scale value when we pass parameter to ofXXX method, but I think there must be other
			 * more easier way to realize.
			 */
		case R.id.key_frame:
			Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
			Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
			Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
			PropertyValuesHolder translationX = PropertyValuesHolder.ofKeyframe("translationX", kf0, kf1, kf2);
			ObjectAnimator translationAnimator = ObjectAnimator.ofPropertyValuesHolder(v, translationX);
			translationAnimator.setDuration(5000);
			translationAnimator.start();
			break;
			
		case R.id.value_animator:
			ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
			valueAnimator.setDuration(3000);
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					float translationX = ((Float)animation.getAnimatedValue()).floatValue();
					v.setTranslationX(translationX);
				}
			});
			valueAnimator.start();
			break;
		default:
			break;
		}
	}
}
