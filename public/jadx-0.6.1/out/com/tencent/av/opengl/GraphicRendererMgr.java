package com.tencent.av.opengl;

import com.tencent.av.opengl.texture.YUVTexture;
import com.tencent.av.utils.SoUtil;

public class GraphicRendererMgr {
    private static boolean mIsSoLoaded = false;
    private static GraphicRendererMgr sGraphicRenderMgr;

    public native void flushGlRender(String str);

    public native int getRecvDecoderFrameFunctionptr();

    public native void sendFrame2GLRender(String str, byte[] bArr, int i, int i2, int i3);

    public native void setGlRender(String str, YUVTexture yUVTexture);

    public native void setSelfId(String str);

    private void loadSo() {
        if (!mIsSoLoaded) {
            if (!SoUtil.getCopySoInfo()) {
                try {
                    System.loadLibrary("qav_graphics");
                    mIsSoLoaded = true;
                } catch (UnsatisfiedLinkError e) {
                    mIsSoLoaded = false;
                    e.printStackTrace();
                }
            } else if (SoUtil.LoadExtractedSo("qav_graphics")) {
                mIsSoLoaded = true;
            } else {
                mIsSoLoaded = false;
            }
        }
    }

    private GraphicRendererMgr() {
        loadSo();
    }

    public static GraphicRendererMgr getInstance() {
        if (sGraphicRenderMgr == null) {
            synchronized (GraphicRendererMgr.class) {
                if (sGraphicRenderMgr == null) {
                    sGraphicRenderMgr = new GraphicRendererMgr();
                }
            }
        }
        return sGraphicRenderMgr;
    }
}
