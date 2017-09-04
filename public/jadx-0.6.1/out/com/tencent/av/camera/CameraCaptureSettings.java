package com.tencent.av.camera;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public class CameraCaptureSettings {
    private static int initial_height = 480;
    private static int initial_width = ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR;
    public int format;
    public int height;
    public int width;

    public CameraCaptureSettings() {
        initSettings();
    }

    public void initSettings() {
        this.width = initial_width;
        this.height = initial_height;
        this.format = 0;
    }
}
