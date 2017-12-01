package com.malin.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityDialog extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOk;
    private Button mBtnCancel;
    private TextView mTvTitle;
    private TextView mTvContent;
    private RelativeLayout mRlRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_layout);
        initView();
    }

    private void initView() {
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);
        mTvContent = findViewById(R.id.tv_content);
        mTvTitle = findViewById(R.id.tv_title);
        mRlRoot = findViewById(R.id.rl_root);
        mRlRoot.setOnClickListener(this);
        mTvTitle.setOnClickListener(this);
        mTvContent.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    private void initWindow() {
        //Remove title bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

//        getWindow().addFlags(
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_ok: {
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_cancel: {
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_title: {
                Toast.makeText(this, "title", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_content: {
                Toast.makeText(this, "content", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.rl_root: {
                Toast.makeText(this, "root clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            default:
        }

    }
}
