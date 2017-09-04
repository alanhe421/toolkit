package com.tencent.upload.network.base;

import com.tencent.upload.a.a.a;
import java.lang.ref.WeakReference;

public class ConnectionImpl {
    private static final int MSG_ID_ON_CONNECT = 1;
    private static final int MSG_ID_ON_DISCONNECT = 2;
    private static final int MSG_ID_ON_ERROR = 3;
    private static final int MSG_ID_ON_MSGPROC = 8;
    private static final int MSG_ID_ON_RECV = 5;
    private static final int MSG_ID_ON_SENDBEGIN = 6;
    private static final int MSG_ID_ON_SENDEND = 7;
    private static final int MSG_ID_ON_START = 0;
    private static final int MSG_ID_ON_TIMEOUT = 4;
    private static final String TAG = "NavtieConn";
    private static volatile boolean sIsLibraryPrepared;
    private d mCallback = null;
    private final int mHashCode = hashCode();
    private e mMsgCallback = null;
    private long mNativeContext;
    private final int mType;

    static {
        boolean z = false;
        sIsLibraryPrepared = false;
        try {
            boolean a = a.a("networkbase");
            boolean a2 = a.a("uploadnetwork");
            if (!a && a2) {
                a = a.a("networkbase");
            }
            native_init();
            if (a && a2) {
                z = true;
            }
            sIsLibraryPrepared = z;
        } catch (UnsatisfiedLinkError e) {
            com.tencent.upload.common.a.a.d(TAG, e.toString());
        } catch (NullPointerException e2) {
            com.tencent.upload.common.a.a.d(TAG, e2.toString());
        } catch (Exception e3) {
            com.tencent.upload.common.a.a.d(TAG, e3.toString());
        }
    }

    public ConnectionImpl(int i, int i2) {
        this.mType = i;
        native_setup(new WeakReference(this), this.mType, i2);
        com.tencent.upload.common.a.a.c(TAG, this.mHashCode + " ConnectionImpl");
    }

    public static final boolean isLibraryPrepared() {
        return sIsLibraryPrepared;
    }

    private final native void native_finalize();

    private static final native void native_init();

    private final native void native_setup(Object obj, int i, int i2);

    private void onConnect(boolean z, int i, String str) {
        if (this.mCallback != null) {
            this.mCallback.a(this.mCallback, z, i, str);
        }
    }

    private void onDisconnect() {
        if (this.mCallback != null) {
            this.mCallback.b(this.mCallback);
        }
    }

    private void onError(int i) {
        if (this.mCallback != null) {
            this.mCallback.a(this.mCallback, i);
        }
    }

    private void onMsgProc(int i, Object obj, int i2) {
        if (this.mMsgCallback != null) {
            e eVar = this.mMsgCallback;
            e eVar2 = this.mMsgCallback;
            eVar.a(i, i2);
        }
    }

    private void onRecv(byte[] bArr) {
        if (this.mCallback != null) {
            this.mCallback.a(this.mCallback, bArr);
        }
    }

    private void onSendBegin(int i) {
        if (this.mCallback != null) {
            this.mCallback.c(this.mCallback, i);
        }
    }

    private void onSendEnd(int i) {
        if (this.mCallback != null) {
            this.mCallback.b(this.mCallback, i);
        }
    }

    private void onStart() {
        if (this.mCallback != null) {
            this.mCallback.a(this.mCallback);
        }
    }

    private void onTimeOut(int i, int i2) {
        if (this.mCallback != null) {
            this.mCallback.a(this.mCallback, i, i2);
        }
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        if (obj instanceof WeakReference) {
            Object obj3 = ((WeakReference) obj).get();
            if (obj3 instanceof ConnectionImpl) {
                ConnectionImpl connectionImpl = (ConnectionImpl) obj3;
                int hashCode = connectionImpl.getHashCode();
                com.tencent.upload.common.a.a.a(TAG, hashCode + " fromNative:" + print(i) + " arg1:" + i2 + " arg2:" + i3 + " obj:" + obj2);
                switch (i) {
                    case 0:
                        connectionImpl.onStart();
                        return;
                    case 1:
                        connectionImpl.onConnect(i2 != 0, i3, (String) obj2);
                        return;
                    case 2:
                        connectionImpl.onDisconnect();
                        return;
                    case 3:
                        connectionImpl.onError(i2);
                        return;
                    case 4:
                        connectionImpl.onTimeOut(i2, i3);
                        return;
                    case 5:
                        connectionImpl.onRecv((byte[]) obj2);
                        return;
                    case 6:
                        connectionImpl.onSendBegin(i2);
                        return;
                    case 7:
                        connectionImpl.onSendEnd(i2);
                        return;
                    case 8:
                        connectionImpl.onMsgProc(i2, obj2, i3);
                        return;
                    default:
                        com.tencent.upload.common.a.a.d(TAG, hashCode + " Unknown message type " + i);
                        return;
                }
            }
            com.tencent.upload.common.a.a.c(TAG, "fromNative: !(ref instanceof ConnectionImpl)");
            return;
        }
        com.tencent.upload.common.a.a.c(TAG, "fromNative: !(ConnectionImpl_ref instanceof WeakReference<?>)");
    }

    private static final String print(int i) {
        switch (i) {
            case 0:
                return "onStart";
            case 1:
                return "onConnect";
            case 2:
                return "onDisconnect";
            case 3:
                return "onError";
            case 4:
                return "onTimeout";
            case 5:
                return "onRecv";
            case 6:
                return "sendBegin";
            case 7:
                return "sendEnd";
            case 8:
                return "msgProc";
            default:
                return "unknown msg";
        }
    }

    public static void printLog(int i, String str) {
        com.tencent.upload.common.a.a.a("jni", str);
    }

    public native boolean PostMessage(int i, Object obj, int i2);

    public native boolean SendData(byte[] bArr, int i, int i2, int i3);

    public native boolean connect(String str, int i, String str2, int i2, int i3, int i4);

    public native boolean disconnect();

    protected void finalize() {
        com.tencent.upload.common.a.a.c(TAG, this.mHashCode + " finalize");
        try {
            stop();
            native_finalize();
            super.finalize();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getHashCode() {
        return this.mHashCode;
    }

    public native boolean isRunning();

    public native boolean isSendDone(int i);

    public native void removeAllSendData();

    public native void removeSendData(int i);

    public void setCallback(d dVar) {
        this.mCallback = dVar;
    }

    public void setMsgCallback(e eVar) {
        this.mMsgCallback = eVar;
    }

    public native boolean start();

    public native boolean stop();

    public native void wakeUp();
}
