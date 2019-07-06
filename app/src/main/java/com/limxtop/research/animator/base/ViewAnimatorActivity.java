package com.limxtop.research.animator.base;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.limxtop.research.R;

public class ViewAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager mFragmentManager;

    private Fragment mLeftFlipper;
    private Fragment mCenterImageSwitcher;
    private Fragment mRightTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator);

        mFragmentManager = getFragmentManager();
        mLeftFlipper = mFragmentManager.findFragmentById(R.id.fragment_flipper);
        mCenterImageSwitcher = mFragmentManager.findFragmentById(R.id.fragment_image_switcher);
        mRightTextSwitcher = mFragmentManager.findFragmentById(R.id.fragment_text_switcher);

        // TODO is there any ways to show flipper fragment when first launch by default?
        showFlipper();
        Button entrance = (Button) findViewById(R.id.button_left);
        entrance.setOnClickListener(this);
        entrance = (Button) findViewById(R.id.button_center);
        entrance.setOnClickListener(this);
        entrance = (Button) findViewById(R.id.button_right);
        entrance.setOnClickListener(this);
    }

    private void showFlipper() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.show(mLeftFlipper);
        ft.hide(mCenterImageSwitcher);
        ft.hide(mRightTextSwitcher);
        ft.commit();
    }

    private void showImageSwitcher() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.show(mCenterImageSwitcher);
        ft.hide(mLeftFlipper);
        ft.hide(mRightTextSwitcher);
        ft.commit();
    }

    private void showTextSwitcher() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.show(mRightTextSwitcher);
        ft.hide(mLeftFlipper);
        ft.hide(mCenterImageSwitcher);
        ft.commit();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.button_left:
                showFlipper();
                break;
            case R.id.button_center:
                showImageSwitcher();
                break;
            case R.id.button_right:
                showTextSwitcher();
                break;
            default:
                break;
        }

//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
