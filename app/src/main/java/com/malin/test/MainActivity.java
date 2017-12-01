package com.malin.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 类描述: 启动制定包名的其他应用
 * 创建人:malin.myemail@163.com
 * 创建时间:2017/11/30 12:59
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:
 * http://blog.csdn.net/abrazen_zz/article/details/53288151
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private HomeWatcher mHomeWatcher;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityStartUtil.startThirdApp(MainActivity.this, "com.tencent.tmgp.sgame");

            }
        });

        findViewById(R.id.btn_main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityDialog.class));
                finish();
                //ActivityStartUtil.startThirdApp2(MainActivity.this, "com.tencent.tmgp.sgame");
            }
        });

        registerHomeClickedBroadCast();
    }

    private void registerHomeClickedBroadCast() {
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                //短按Home键
                //Toast.makeText(getApplicationContext(), "短按Home键 应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "短按Home键 应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, ActivityDialog.class));
                        //TODO:启动dialog样式的Activity,
                        Toast.makeText(getApplicationContext(), "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, "应用是否在前台:" + MyLifecycleHandler.isApplicationInForeground());
                    }
                }, 1000);
            }

            @Override
            public void onRecentAppsPressed() {

            }
        });
        mHomeWatcher.startWatch();
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Toast.makeText(MainActivity.this, "返回键无效", Toast.LENGTH_SHORT).show();
//            return true;//return true;拦截事件传递,从而屏蔽back键。
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHomeWatcher != null) {
            mHomeWatcher.stopWatch();
        }
    }
}
