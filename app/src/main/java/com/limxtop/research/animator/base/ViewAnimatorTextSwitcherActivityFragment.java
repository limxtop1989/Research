package com.limxtop.research.animator.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.limxtop.research.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewAnimatorTextSwitcherActivityFragment extends Fragment implements ViewSwitcher.ViewFactory, View.OnClickListener {

    private int mShowIndex;
    private static int[] mTextRes = {
            R.string.first,
            R.string.second,
            R.string.third,
            R.string.fourth
    };

    private Animation mSlideInLeft;
    private Animation mSlideOutRight;

    private Animation mSlideOutLeft;
    private Animation mSlideInRight;

    private TextSwitcher mViewSwitcher;

    public ViewAnimatorTextSwitcherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_view_animator_text_switcher, container, false);

        Button prev = (Button) fragmentView.findViewById(R.id.prev);
        prev.setOnClickListener(this);

        Button next = (Button) fragmentView.findViewById(R.id.next);
        next.setOnClickListener(this);

        mViewSwitcher = (TextSwitcher) fragmentView.findViewById(R.id.text_switcher);
        mViewSwitcher.setFactory(this);
        mViewSwitcher.setText(getText(mTextRes[mShowIndex]));

        mSlideInLeft = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        mSlideOutRight = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);

        mSlideOutLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);
        mSlideInRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);

        return fragmentView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View makeView() {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setBackground(getResources().getDrawable(R.color.light_blue));
        /**
         * Exception: Caused by: java.lang.ClassCastException:
         * android.widget.LinearLayout$LayoutParams cannot be cast to android.widget.FrameLayout$LayoutParams
         * The conclusion is that the LayoutParams of child view is used by parent view to decide
         * where the child view locate in parent view.
         */
        /*LinearLayout.LayoutParams layoutParames = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParames.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParames);*/

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp);
        return textView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.prev:
                mShowIndex--;
                if (mShowIndex < 0) {
                    mShowIndex = mTextRes.length -1;
                }
                mViewSwitcher.setInAnimation(mSlideInLeft);
                mViewSwitcher.setOutAnimation(mSlideOutRight);
                break;
            case R.id.next:
                mShowIndex++;
                if (mShowIndex >= mTextRes.length) {
                    mShowIndex = 0;
                }
                mViewSwitcher.setInAnimation(mSlideInRight);
                mViewSwitcher.setOutAnimation(mSlideOutLeft);
                break;
        }

        mViewSwitcher.setText(getResources().getText(mTextRes[mShowIndex]));
    }
}
