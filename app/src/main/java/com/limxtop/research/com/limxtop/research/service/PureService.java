package com.limxtop.research.com.limxtop.research.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.limxtop.research.R;

public class PureService extends Service {

    private static final String TAG = PureServiceActivity.TAG;

    private PureBinder mBinder = new PureBinder();

    public PureService() {
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate:");
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("notification coming");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("title");
        builder.setContentText("text");
        builder.setContentInfo("info");
        Intent intent = new Intent(this, PureServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        // TODO the notification does not make effect.
        startForeground(0, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:" + " flags=" + flags + ", startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind:");
        return mBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    class PureBinder extends Binder {

        public void download() {
            Log.d(PureService.TAG, "download:");
        }
    }
}
