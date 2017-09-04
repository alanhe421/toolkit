package com.tencent.qalsdk.util;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class CodecWarpper {
    public static final int CODE_FAIL = -1;
    public static final int CODE_FAIL_DECRYPT_DATA_LEN_ERROR = -5;
    public static final int CODE_FAIL_DECRYPT_EMPTY = -4;
    public static final int CODE_FAIL_DECRYPT_ONCE = -2;
    public static final int CODE_FAIL_DECRYPT_TWICE = -3;
    public static final int CODE_FAIL_PBUNPACK = -8;
    public static final int CODE_FAIL_ZLIB_DATA_LEN_SHORT = -6;
    public static final int CODE_FAIL_ZLIB_UNCOMPRESS_ERROR = -7;
    public static final int CODE_SUCC_DECRYPT_ONCE = 1;
    public static final int CODE_SUCC_DECRYPT_TWICE = 2;
    public static final String NATIVE_LIB_NAME = "codecwrapperV2";
    private static AtomicBoolean isSoLoaded = new AtomicBoolean(false);
    public static String tag = "MSF.C.CodecWarpper";

    public static native byte[] encodeRequest(int i, String str, String str2, String str3, String str4, String str5, byte[] bArr, int i2, int i3, String str6, byte b, byte b2, byte[] bArr2);

    public static native byte[] getFileStoreKey();

    public static native int getMaxPackageSize();

    public static native synchronized void onConnClose();

    public static native synchronized void removeAccountKey(String str);

    public static native synchronized void setAccountKey(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, String str2);

    public static native synchronized void setKsid(byte[] bArr);

    public static native void setMaxPackageSize(int i);

    public static native void setUseSimpleHead(String str, boolean z);

    public native void init(Context context, boolean z);

    public abstract void onInvalidData(int i, int i2);

    public abstract void onInvalidSign();

    public native void onReceData(byte[] bArr);

    public abstract void onResponse(int i, Object obj, int i2);

    public static synchronized void nateiveRemoveAccountKey(String str) {
        synchronized (CodecWarpper.class) {
            if (QLog.isColorLevel()) {
                QLog.d(tag, 2, "remove " + str + " key ");
            }
            removeAccountKey(str);
        }
    }

    public void onInvalidDataNative(int i) {
        onInvalidData(-5, i);
    }
}
