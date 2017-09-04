package com.tencent.av.sdk;

import android.content.Context;
import android.view.SurfaceHolder;
import com.tencent.av.opengl.GraphicRendererMgr;
import com.tencent.av.sdk.AVRoomMulti.EnterParam;
import com.tencent.av.sdk.AVRoomMulti.EventListener;

public abstract class AVContext {
    public static final String sdkVersion = "1.8.4.8";

    public abstract void destroy();

    public abstract void enterRoom(EventListener eventListener, EnterParam enterParam);

    public abstract int exitRoom();

    public abstract AVAudioCtrl getAudioCtrl();

    public abstract AVCloudSpearEngineCtrl getCloudSpearEngineCtrl();

    public abstract AVCustomSpearEngineCtrl getCustomSpearEngineCtrl();

    public abstract AVRoomMulti getRoom();

    public abstract AVVideoCtrl getVideoCtrl();

    public abstract int setRenderMgrAndHolder(GraphicRendererMgr graphicRendererMgr, SurfaceHolder surfaceHolder);

    public abstract void start(StartParam startParam, AVCallback aVCallback);

    public abstract void start(StartParam startParam, AVSDKLogSetting aVSDKLogSetting, AVCallback aVCallback);

    public abstract int stop();

    public abstract void switchRoom(int i);

    public static String getVersion() {
        return AVContextImpl.getVersion();
    }

    public static AVContext createInstance(Context context) {
        AVContext aVContextImpl = new AVContextImpl();
        return aVContextImpl.create(context, false) ? aVContextImpl : null;
    }

    public static AVContext createInstance(Context context, boolean z) {
        AVContext aVContextImpl = new AVContextImpl();
        return aVContextImpl.create(context, z) ? aVContextImpl : null;
    }

    public static int getSoExtractError() {
        return AVContextImpl.getSoExtractError();
    }
}
