package com.tencent.av.utils;

import java.nio.ByteBuffer;

public final class NioUtils {
    public static final String TAG = "NioUtils";

    public static boolean destroyDirectByteBuffer(ByteBuffer byteBuffer) {
        QLog.d(TAG, 0, "destroyDirectByteBuffer start");
        if (!byteBuffer.isDirect()) {
            return false;
        }
        try {
            byteBuffer.getClass().getMethod("free", new Class[0]).invoke(byteBuffer, new Object[0]);
            QLog.d(TAG, 0, "destroyDirectByteBuffer end");
            return true;
        } catch (Exception e) {
            QLog.e(TAG, 0, "destroyDirectByteBuffer", e);
            return false;
        }
    }

    public static ByteBuffer createDirectByteBuffer(int i) {
        QLog.d(TAG, 0, "createDirectByteBuffer len = " + i);
        return ByteBuffer.allocateDirect(i);
    }
}
