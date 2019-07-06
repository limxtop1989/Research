package com.limxtop.research.animator.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.limxtop.research.R;

import java.util.ArrayList;
import java.util.List;

public class AnimatorCategoryActivity extends Activity implements View.OnClickListener {


    private RecyclerView mRecyclerView;

    private int RECYCLER_VIEW_COLUMN_COUNT = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, RECYCLER_VIEW_COLUMN_COUNT));
        mRecyclerView.setAdapter(new RecyclerViewSImpleAdapter(getDatas(), this));
        mRecyclerView.setOnClickListener(this);
    }

    private List<ViewEntry> getDatas() {
        List<ViewEntry> datas = new ArrayList<>(10);
        datas.add(new ViewEntry("Base Animator", BasicAnimatorActivity.class));
        datas.add(new ViewEntry("View Animation", ViewAnimationActivity.class));
        datas.add(new ViewEntry("Path Animator", PathAnimatorActivity.class));
        datas.add(new ViewEntry("View Animator", ViewAnimatorActivity.class));
        datas.add(new ViewEntry("Bezier Animator", BezierCurveAnimatorActivity.class));
        // TODO add View Property Animator
        return datas;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, (Class) view.getTag());
        startActivity(intent);
    }
}
