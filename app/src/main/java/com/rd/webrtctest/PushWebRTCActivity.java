package com.rd.webrtctest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.webrtc.EglBase;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RendererCommon;

public class PushWebRTCActivity extends AppCompatActivity {
    private final static String TAG = "PushWebRTCActivity";
    private Context mContext;

    private EditText  mServerUrlET;
    private org.webrtc.SurfaceViewRenderer mSurfaceViewRender;
    private EglBase mRootEglBase;
    private WebRtcUtil webRtcUtil1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_web_rtc);
        this.commonInit();
    }

    private void commonInit(){
        this.mContext = this;
        this.mServerUrlET = this.findViewById(R.id.et_webrtcserver);
        this.mServerUrlET.setText("webrtc://192.168.201.91/live/livestream");
        this.mSurfaceViewRender = this.findViewById(R.id.svr_video);

        mRootEglBase = EglBase.create();

        //初始化SurfaceViewRenderer
        this.mSurfaceViewRender.init(mRootEglBase.getEglBaseContext(), new RendererCommon.RendererEvents() {
            @Override
            public void onFirstFrameRendered() {

            }

            @Override
            public void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {

            }
        });
        this.mSurfaceViewRender.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);
        this.mSurfaceViewRender.setEnableHardwareScaler(true);
        this.mSurfaceViewRender.setZOrderMediaOverlay(true);

        this.doPush();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mSurfaceViewRender.release();
        PeerConnectionFactory.stopInternalTracingCapture();
        PeerConnectionFactory.shutdownInternalTracer();
    }

    public void stopPush(View view) {
        if (webRtcUtil1 != null) {
            webRtcUtil1.destroy();
        }
    }

    private void doPush() {
        String text = this.mServerUrlET.getEditableText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(mContext, "推流地址为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (webRtcUtil1 != null) {
            webRtcUtil1.destroy();
        }
        webRtcUtil1 = new WebRtcUtil(mContext);
        webRtcUtil1.create(mRootEglBase, this.mSurfaceViewRender, true, true, text, new WebRtcUtil.WebRtcCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail() {

            }
        });
    }
}