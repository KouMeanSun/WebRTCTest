package com.rd.webrtctest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
private static final String TAG = "MainPageActivity";
private Context mContext;

private Button pushWebRTCBtn;
private Button pullWebRTCBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        this.commonInit();
    }

    private void commonInit(){
        this.mContext = this;
        this.pushWebRTCBtn = this.findViewById(R.id.btn_pushWebRtc);
        this.pullWebRTCBtn = this.findViewById(R.id.btn_pullWebRtc);

        this.pushWebRTCBtn.setOnClickListener(this);
        this.pullWebRTCBtn.setOnClickListener(this);

        XXPermissions.with(this)
                .permission(Permission.CAMERA)
                .permission(Permission.RECORD_AUDIO)
                .request((permissions, all) -> {
                });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_pushWebRtc:
            {
                Intent intent = new Intent(mContext,PushWebRTCActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.btn_pullWebRtc:
            {
                Intent intent = new Intent(mContext,PlayWebRTCActivity.class);
                startActivity(intent);
            }
                break;

            default:

                break;
        }
    }
}