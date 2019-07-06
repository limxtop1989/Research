package com.limxtop.research.com.limxtop.research.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 *
 send request after first request finish
 05-08 16:44:32.045 14565-14565/com.limxtop.research D/DownloadIntentService: onCreate:
 05-08 16:44:32.046 14565-14565/com.limxtop.research D/DownloadIntentService: onStartCommand:
 05-08 16:44:32.047 14565-14565/com.limxtop.research D/DownloadIntentService: onStart:
 05-08 16:44:32.047 14565-15157/com.limxtop.research D/DownloadIntentService: handleActionFoo
 05-08 16:44:35.051 14565-14565/com.limxtop.research D/DownloadIntentService: onDestroy:
 05-08 16:44:38.508 14565-14565/com.limxtop.research D/DownloadIntentService: onCreate:
 05-08 16:44:38.509 14565-14565/com.limxtop.research D/DownloadIntentService: onStartCommand:
 05-08 16:44:38.509 14565-14565/com.limxtop.research D/DownloadIntentService: onStart:
 05-08 16:44:38.509 14565-15255/com.limxtop.research D/DownloadIntentService: handleActionBaz
 05-08 16:44:41.513 14565-14565/com.limxtop.research D/DownloadIntentService: onDestroy:
 send request before first request finish
 05-08 16:45:10.681 14565-14565/com.limxtop.research D/DownloadIntentService: onCreate:
 05-08 16:45:10.682 14565-14565/com.limxtop.research D/DownloadIntentService: onStartCommand:
 05-08 16:45:10.683 14565-14565/com.limxtop.research D/DownloadIntentService: onStart:
 05-08 16:45:10.683 14565-15516/com.limxtop.research D/DownloadIntentService: handleActionFoo
 05-08 16:45:11.266 14565-14565/com.limxtop.research D/DownloadIntentService: onStartCommand:
 05-08 16:45:11.266 14565-14565/com.limxtop.research D/DownloadIntentService: onStart:
 05-08 16:45:13.686 14565-15516/com.limxtop.research D/DownloadIntentService: handleActionBaz
 05-08 16:45:16.690 14565-14565/com.limxtop.research D/DownloadIntentService: onDestroy:

 conclusion: the service process the request sequentially, and stop itself when finish the request,
 but it is not when there are messages in the queue still.
 */
public class DownloadIntentService extends IntentService {
    private static final String TAG = DownloadIntentService.class.getSimpleName();
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.limxtop.research.com.limxtop.research.service.action.FOO";
    private static final String ACTION_BAZ = "com.limxtop.research.com.limxtop.research.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.limxtop.research.com.limxtop.research.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.limxtop.research.com.limxtop.research.service.extra.PARAM2";

    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        Log.d(TAG, "handleActionFoo");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        Log.d(TAG, "handleActionBaz");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart:");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, "stopService:");
        return super.stopService(name);
    }
}
