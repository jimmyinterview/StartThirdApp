package com.malin.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


/**
 * http://www.jianshu.com/p/9aa529bd109e
 */
public class HomeClickBroadcastReceiver extends BroadcastReceiver {

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String TAG = HomeClickBroadcastReceiver.class.getSimpleName();
    private Handler handler = new Handler();

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) return;
        Log.d(TAG, "onReceive: action: " + action);
        if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            Log.d(TAG, "reason:" + reason);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                //短按Home键
                Log.d(TAG, "短按Home键 应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO:启动dialog样式的Activity,
                        Toast.makeText(context, "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                    }
                }, 1000);
            } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                //长按Home键 或者activity切换键
                Log.d(TAG, "多任务键被监听 应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //TODO:
                        Toast.makeText(context, "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                    }
                }, 1000);
            }
        }
    }
}