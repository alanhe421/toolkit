package com.tencent.av.opengl.texture;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.av.opengl.GlStringParser;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import com.tencent.av.opengl.utils.Utils;
import com.tencent.av.utils.QLog;
import java.lang.ref.WeakReference;

public final class YUVTexture extends BasicTexture {
    public static final int MSG_FLUSH = 1;
    public static final int MSG_RENDER = 0;
    public static final int MSG_RESET = 2;
    public static final int MSG_SHOW = 3;
    private static String TAG = "YUVTexture";
    static boolean soloaded = false;
    public float mBrightness = 1.2f;
    public float mContrast = 1.93f;
    private EventHandler mEventHandler;
    private GLRenderListener mListener;
    private int mNativeContext = 0;
    public float mSaturation = 1.05f;
    private GlStringParser mStringParser = null;
    private int m_drawcount = 0;
    private long m_endtime = 0;
    private long m_starttime = 0;

    class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (YUVTexture.this.mListener != null) {
                        YUVTexture.this.mListener.onRenderFrame();
                        return;
                    }
                    return;
                case 1:
                    if (YUVTexture.this.mListener != null) {
                        YUVTexture.this.mListener.onRenderFlush();
                        return;
                    }
                    return;
                case 2:
                    if (YUVTexture.this.mListener != null) {
                        YUVTexture.this.mListener.onRenderReset();
                        return;
                    }
                    return;
                case 3:
                    if (YUVTexture.this.mListener != null) {
                        if (YUVTexture.this.mStringParser == null) {
                            YUVTexture.this.mStringParser = new GlStringParser('=', ';');
                        }
                        YUVTexture.this.mStringParser.unflatten((String) message.obj);
                        YUVTexture.this.mListener.onRenderInfoNotify(YUVTexture.this.mStringParser.getInt("width"), YUVTexture.this.mStringParser.getInt("height"), YUVTexture.this.mStringParser.getInt("angle"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface GLRenderListener {
        void onRenderFlush();

        void onRenderFrame();

        void onRenderInfoNotify(int i, int i2, int i3);

        void onRenderReset();
    }

    public native boolean canRender();

    public native void flush(boolean z);

    native int getFrameCount();

    native int getFrameIndex();

    public native int getImgAngle();

    public native int getImgHeight();

    public native int getImgWidth();

    native void init(int i, Object obj);

    public native void onPause();

    public native void onResume();

    native void uninit();

    native void uploadContent(int[] iArr);

    public void setGLRenderListener(GLRenderListener gLRenderListener) {
        this.mListener = gLRenderListener;
    }

    public YUVTexture(Context context) {
        super(null, 0, 0);
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new EventHandler(myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        init(Utils.getGLVersion(context), new WeakReference(this));
    }

    static void onNativeNotify(Object obj, int i, Object obj2) {
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference.get() == null) {
            return;
        }
        if (i == 0) {
            ((YUVTexture) weakReference.get()).notifyupdateui(0, 0, obj2);
        } else if (i == 2) {
            ((YUVTexture) weakReference.get()).notifyupdateui(2, 0, obj2);
        } else if (i == 1) {
            ((YUVTexture) weakReference.get()).notifyupdateui(1, 0, obj2);
        } else if (i == 3) {
            ((YUVTexture) weakReference.get()).notifyupdateui(3, 0, obj2);
        }
    }

    public void notifyupdateui(int i, int i2, Object obj) {
        if (this.mEventHandler != null) {
            Message obtainMessage = this.mEventHandler.obtainMessage(i, 0, 0, obj);
            if (i2 == 0) {
                this.mEventHandler.sendMessage(obtainMessage);
            } else {
                this.mEventHandler.sendMessageDelayed(obtainMessage, (long) i2);
            }
        } else if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "notifyupdateui|mEventHandler == null");
        }
    }

    public void setColorAjust(float f, float f2, float f3) {
        this.mBrightness = f;
        this.mContrast = f2;
        this.mSaturation = f3;
    }

    public float getBrightness() {
        return this.mBrightness;
    }

    public float getContrast() {
        return this.mContrast;
    }

    public float getSaturation() {
        return this.mSaturation;
    }

    public boolean isOpaque() {
        return true;
    }

    public boolean onBind(GLCanvas gLCanvas) {
        if (this.mId == null) {
            this.mId = new int[3];
            for (int i = 0; i < this.mId.length; i++) {
                this.mId[i] = gLCanvas.getGLId().generateTexture();
            }
        }
        uploadContent(this.mId);
        this.mState = 1;
        return isLoaded();
    }

    public int getTarget() {
        return 3553;
    }

    public int getFormatType() {
        return 1;
    }
}
