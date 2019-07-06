package com.limxtop.research.animator.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.limxtop.research.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewAnimatorImageSwitcherActivityFragment extends Fragment implements ImageSwitcher.ViewFactory, View.OnClickListener {

    private ImageSwitcher mImageSwitcher;
    private Button mPrev;
    private Button mNext;

    private Animation mInLeft;
    private Animation mOutLeft;
    private Animation mInRight;
    private Animation mOutRight;

    private int mIndex;
    private int[] mImageRes = new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };


    public ViewAnimatorImageSwitcherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_view_animator_image_switcher, container, false);
        mImageSwitcher = (ImageSwitcher) contentView.findViewById(R.id.image_switcher);
        mImageSwitcher.setFactory(this);
        // show first image by default
        mImageSwitcher.setImageResource(mImageRes[mIndex]);

        mInLeft = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        mOutRight = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);

        mInRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        mOutLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);

        mImageSwitcher.setAnimateFirstView(true);
        mPrev = (Button) contentView.findViewById(R.id.prev);
        mPrev.setOnClickListener(this);

        mNext = (Button) contentView.findViewById(R.id.next);
        mNext.setOnClickListener(this);
        return contentView;
    }

    @Override
    public View makeView() {
        return new ImageView(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev:
                if (mIndex == 0) {
                    mIndex = mImageRes.length - 1;
                } else {
                    mIndex--;
                }
                /**
                 * go out to right and come from left when click prev button;
                 */
                mImageSwitcher.setOutAnimation(mOutRight);
                mImageSwitcher.setInAnimation(mInLeft);
                break;
            case R.id.next:
                if (++mIndex == mImageRes.length) {
                    mIndex = 0;
                }
                /**
                 * go out to left and come from right when click next button;
                 */
                mImageSwitcher.setOutAnimation(mOutLeft);
                mImageSwitcher.setInAnimation(mInRight);
                break;
            default:
                break;
        }
        mImageSwitcher.setImageResource(mImageRes[mIndex]);
    }
}
