package com.limxtop.research.animator.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.limxtop.research.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewAnimatorFlipperActivityFragment extends Fragment implements View.OnClickListener {

    private ViewFlipper mFlipper;

    private Button mButton;

    private Animation mFadeIn;
    private Animation mFadeOut;

    public ViewAnimatorFlipperActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animator_flipper, container, false);
        mFlipper = (ViewFlipper) view.findViewById(R.id.view_flipper);
        mButton = (Button) view.findViewById(R.id.controller_button);
        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFlipper.setFlipInterval(2000);
        mFlipper.setInAnimation(getActivity(), android.R.anim.fade_in);
        mFlipper.setOutAnimation(getActivity(), android.R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        boolean isFlipping = mFlipper.isFlipping();
        if (isFlipping) {
            mFlipper.stopFlipping();
            mButton.setText(getResources().getString(R.string.start));
        } else {
            /**
             * TODO: why there is a glint at first frame when start button clicked after add animation to flipper.
             */
            mFlipper.startFlipping();
            mButton.setText(getResources().getString(R.string.stop));
        }
    }
}
