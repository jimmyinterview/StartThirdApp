package com.malin.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 类描述: 启动制定包名的其他应用
 * 创建人:malin.myemail@163.com
 * 创建时间:2017/11/30 12:59
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityStartUtil.startThirdApp(MainActivity.this, "com.malin.third.app");

            }
        });

        findViewById(R.id.btn_main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityStartUtil.startThirdApp2(MainActivity.this, "com.malin.third.app");
            }
        });
    }
}
