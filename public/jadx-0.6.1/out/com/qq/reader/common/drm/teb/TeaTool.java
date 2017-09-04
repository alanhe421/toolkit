package com.qq.reader.common.drm.teb;

import android.annotation.TargetApi;
import com.qq.reader.common.utils.a.d;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TeaTool {
    private static int[] a;
    private static String b;

    private static native String getTeaKey();

    static {
        System.loadLibrary("epub");
    }

    public static int[] a(String str) {
        if (b == null) {
            b = getTeaKey();
        }
        byte[] bArr = null;
        try {
            bArr = d.a((str + b).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return a(bArr);
    }

    public static int[] a() {
        if (b == null) {
            b = getTeaKey();
        }
        if (a == null) {
            byte[] bArr = null;
            try {
                bArr = b.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            a = a(bArr);
        }
        return a;
    }

    public static byte[] a(byte[] bArr, int[] iArr) {
        return a(bArr, 16, iArr);
    }

    private static byte[] a(byte[] bArr, int i, int[] iArr) {
        int[] b = b(bArr);
        int i2 = b[0];
        int i3 = b[1];
        int i4 = 0;
        int i5 = iArr[0];
        int i6 = iArr[1];
        int i7 = iArr[2];
        int i8 = iArr[3];
        for (int i9 = 0; i9 < i; i9++) {
            i4 -= 1640531527;
            i2 += (((i3 << 4) + i5) ^ (i3 + i4)) ^ ((i3 >> 5) + i6);
            i3 += (((i2 << 4) + i7) ^ (i2 + i4)) ^ ((i2 >> 5) + i8);
        }
        b[0] = i2;
        b[1] = i3;
        return a(b);
    }

    public static byte[] b(byte[] bArr, int[] iArr) {
        return b(bArr, 16, iArr);
    }

    private static byte[] b(byte[] bArr, int i, int[] iArr) {
        int i2;
        int[] b = b(bArr);
        int i3 = b[0];
        int i4 = b[1];
        int i5 = iArr[0];
        int i6 = iArr[1];
        int i7 = iArr[2];
        int i8 = iArr[3];
        if (i == 32) {
            i2 = -957401312;
        } else if (i == 16) {
            i2 = -478700656;
        } else {
            i2 = -1640531527 * i;
        }
        int i9 = i2;
        for (i2 = 0; i2 < i; i2++) {
            i4 -= (((i3 << 4) + i7) ^ (i3 + i9)) ^ ((i3 >> 5) + i8);
            i3 -= (((i4 << 4) + i5) ^ (i4 + i9)) ^ ((i4 >> 5) + i6);
            i9 -= -1640531527;
        }
        b[0] = i3;
        b[1] = i4;
        return a(b);
    }

    public static int[] a(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int[] iArr = new int[4];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = wrap.getInt();
        }
        return iArr;
    }

    private static int[] b(byte[] bArr) {
        ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        return new int[]{r0.getInt(), r0.getInt()};
    }

    @TargetApi(11)
    private static byte[] a(int[] iArr) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(iArr[0]);
        allocate.putInt(iArr[1]);
        return allocate.array();
    }
}
