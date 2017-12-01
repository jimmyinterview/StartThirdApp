package com.malin.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                ActivityStartUtil.startThirdApp2(MainActivity.this, "com.tencent.tmgp.sgame");
            }
        });

        registerHomeClickedBroadCast();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(MainActivity.this, "返回键无效", Toast.LENGTH_SHORT).show();
            return true;//return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }


    private HomeClickBroadcastReceiver homeClickBroadcastReceiver;

    /**
     * 这种方式能对home键与多任务键实现全盘监听，但你无法去屏蔽系统的行为。
     */
    private void registerHomeClickedBroadCast() {
        //创建广播
        homeClickBroadcastReceiver = new HomeClickBroadcastReceiver();
        //动态注册广播
        //当按下Home键时，系统会发出action为Intent.ACTION_CLOSE_SYSTEM_DIALOGS的BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //启动广播
        registerReceiver(homeClickBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homeClickBroadcastReceiver != null) {
            unregisterReceiver(homeClickBroadcastReceiver);
        }
    }
}
