package com.limxtop.research;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.limxtop.research.animator.base.AnimatorCategoryActivity;
import com.limxtop.research.animator.base.RecyclerViewSImpleAdapter;
import com.limxtop.research.animator.base.ViewEntry;
import com.limxtop.research.animator.property.ZoomActivity;
import com.limxtop.research.camera.TakePhotoActivity;
import com.limxtop.research.camera.VideoActivity;
import com.limxtop.research.com.limxtop.research.service.PureServiceActivity;
import com.limxtop.research.com.limxtop.research.service.ServiceLifeCycleActivity;
import com.limxtop.research.draw.CanvasActivity;
import com.limxtop.research.draw.MatrixTransformActivity;
import com.limxtop.research.draw.StatisticsActivity;
import com.limxtop.research.touch.TouchEventActivity;
import com.limxtop.research.touch.translation.TranslateActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRecyclerView;

    private int RECYCLER_VIEW_COLUMN_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, RECYCLER_VIEW_COLUMN_COUNT));
        mRecyclerView.setAdapter(new RecyclerViewSImpleAdapter(getDatas(), this));
        mRecyclerView.setOnClickListener(this);
    }

    private List<ViewEntry> getDatas() {
        List<ViewEntry> datas = new ArrayList<>(10);
        datas.add(new ViewEntry("Animate", AnimatorCategoryActivity.class));
        datas.add(new ViewEntry("Draw", MatrixTransformActivity.class));
        datas.add(new ViewEntry("Statistics", StatisticsActivity.class));
        datas.add(new ViewEntry("Service", ServiceLifeCycleActivity.class));
        datas.add(new ViewEntry("PureService", PureServiceActivity.class));
        datas.add(new ViewEntry("TouchEvent", TouchEventActivity.class));
        datas.add(new ViewEntry("Take Photo", TakePhotoActivity.class));
        datas.add(new ViewEntry("Take Video", VideoActivity.class));
        datas.add(new ViewEntry("Translate", TranslateActivity.class));
        datas.add(new ViewEntry("Canvas", CanvasActivity.class));
        return datas;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, (Class) view.getTag());

        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(new Intent(this, TouchEventActivity.class),
                PackageManager.MATCH_DEFAULT_ONLY);


        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}
