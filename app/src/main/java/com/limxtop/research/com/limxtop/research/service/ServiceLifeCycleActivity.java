package com.limxtop.research.com.limxtop.research.service;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.limxtop.research.R;

public class ServiceLifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;

    private DownloadIntentService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_life_cycle);
        mButton1 = (Button) findViewById(R.id.first_request);
        mButton2 = (Button) findViewById(R.id.second_request);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        mService = new DownloadIntentService();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.first_request:
                mService.startActionFoo(this, "first", "request");
                break;
            case R.id.second_request:
                mService.startActionBaz(this, "second", "request");
                break;
        }
    }
}
