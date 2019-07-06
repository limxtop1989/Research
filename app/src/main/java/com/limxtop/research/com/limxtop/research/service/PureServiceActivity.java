package com.limxtop.research.com.limxtop.research.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.limxtop.research.R;

public class PureServiceActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "PureServiceActivity";

    private Button mStartServiceButton;
    private Button mStopServiceButton;

    private Button mBindServiceButton;
    private Button mUnbindServiceButton;

    private PureService.PureBinder mBinder;

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected:");
            mBinder = (PureService.PureBinder) service;
            mBinder.download();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected:");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_service);

        mStartServiceButton = (Button) findViewById(R.id.start);
        mStartServiceButton.setOnClickListener(this);

        mStopServiceButton = (Button) findViewById(R.id.stop);
        mStopServiceButton.setOnClickListener(this);

        mBindServiceButton = (Button) findViewById(R.id.bind);
        mBindServiceButton.setOnClickListener(this);

        mUnbindServiceButton = (Button) findViewById(R.id.unbind);
        mUnbindServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        switch (id) {
            case R.id.start:
                intent = new Intent(this, PureService.class);
                startService(intent);
                break;
            case R.id.stop:
                intent = new Intent(this, PureService.class);
                stopService(intent);
                break;
            case R.id.bind:
                intent = new Intent(this, PureService.class);
                bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(mServiceConnection);
                break;

        }
    }
}
