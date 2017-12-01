package com.malin.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

public class HomeWatcher {
    private static final String TAG = "HomeWatcher";
    private Context mContext;
    private IntentFilter mFilter;
    private OnHomePressedListener mListener;
    private InnerHomeBroadcastReceiver mInnerHomeBroadcastReceiver;

    public interface OnHomePressedListener {
        /**
         * 点击Home键
         */
        void onHomePressed();

        /**
         * 点击多任务键
         */
        void onRecentAppsPressed();
    }

    public HomeWatcher(Context context) {
        mContext = context;
        //当按下Home键时，系统会发出action为Intent.ACTION_CLOSE_SYSTEM_DIALOGS的BroadcastReceiver
        mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    /**
     * 设置监听
     *
     * @param listener OnHomePressedListener
     */
    public void setOnHomePressedListener(OnHomePressedListener listener) {
        mListener = listener;
        mInnerHomeBroadcastReceiver = new InnerHomeBroadcastReceiver();
    }

    /**
     * 开始监听，注册广播
     */
    public void startWatch() {
        if (mInnerHomeBroadcastReceiver == null) return;
        mContext.registerReceiver(mInnerHomeBroadcastReceiver, mFilter);
    }

    /**
     * 停止监听，注销广播
     */
    public void stopWatch() {
        if (mInnerHomeBroadcastReceiver == null) return;
        mContext.unregisterReceiver(mInnerHomeBroadcastReceiver);
    }

    /**
     * 广播接收者
     * 当按下Home键时，系统会发出action为Intent.ACTION_CLOSE_SYSTEM_DIALOGS的BroadcastReceive
     * 这种方式能对home键与多任务键实现全盘监听，但你无法去屏蔽系统的行为。
     */
    private class InnerHomeBroadcastReceiver extends BroadcastReceiver {
        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
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
                    if (mListener == null) return;
                    mListener.onHomePressed();
                } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                    //长按Home键 或者activity切换键
                    Log.d(TAG, "多任务键被监听 应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                    if (mListener == null) return;
                    mListener.onRecentAppsPressed();
                }
            }
        }
    }
}
