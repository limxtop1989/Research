package com.limxtop.research.touch;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.limxtop.research.R;

/**
 * Created by limxtop on 1/8/17.
 */
public class TouchEventActivity extends AppCompatActivity {

    private static final String TAG = "TouchEventActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_layout);

        GrandParentViewGroup grand = (GrandParentViewGroup) findViewById(R.id.grand_parent);
        ChildView child = (ChildView) findViewById(R.id.child);

        child.registerMoveInterceptLisener(grand);
    }
}
