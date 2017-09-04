package com.qq.reader.plugin;

import android.graphics.Rect;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: NinePatchChunk */
public class j {
    public Rect a = new Rect();
    public int[] b;
    public int[] c;
    public int[] d;

    private static void a(int[] iArr, ByteBuffer byteBuffer) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = byteBuffer.getInt();
        }
    }

    private static void a(int i) {
        if (i == 0 || (i & 1) != 0) {
            throw new RuntimeException("invalid nine-patch: " + i);
        }
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return bArr;
        }
        byte[] bArr2 = (byte[]) bArr.clone();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        if (order == null || order.get() == (byte) 0) {
            return bArr;
        }
        int i;
        j jVar = new j();
        jVar.b = new int[order.get()];
        jVar.c = new int[order.get()];
        jVar.d = new int[order.get()];
        a(jVar.b.length);
        a(jVar.c.length);
        order.getInt();
        order.getInt();
        jVar.a.left = order.getInt();
        jVar.a.right = order.getInt();
        jVar.a.top = order.getInt();
        jVar.a.bottom = order.getInt();
        order.getInt();
        a(jVar.b, order);
        a(jVar.c, order);
        int position = order.position();
        if (jVar == null || jVar.d == null) {
            i = 0;
        } else {
            i = jVar.d.length * 4;
        }
        int i2;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            i2 = i;
            i = 0;
            while (i2 > 0 && bArr2 != null && position < bArr2.length) {
                i++;
                if (i % 4 == 0) {
                    bArr2[position] = (byte) 1;
                } else {
                    bArr2[position] = (byte) 0;
                }
                i2--;
                position++;
            }
        } else if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            i2 = i;
            i = position;
            position = 0;
            while (i2 > 0 && bArr2 != null && i < bArr2.length) {
                if (position % 4 == 0) {
                    bArr2[i] = (byte) 1;
                } else {
                    bArr2[i] = (byte) 0;
                }
                position++;
                i2--;
                i++;
            }
        }
        return bArr2;
    }

    public static Rect b(byte[] bArr) {
        Rect rect = new Rect();
        if (bArr == null || bArr.length <= 0) {
            return rect;
        }
        byte[] bArr2 = (byte[]) bArr.clone();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        if (order == null) {
            return rect;
        }
        if (order.get() == (byte) 0) {
            return rect;
        }
        j jVar = new j();
        jVar.b = new int[order.get()];
        jVar.c = new int[order.get()];
        jVar.d = new int[order.get()];
        a(jVar.b.length);
        a(jVar.c.length);
        order.getInt();
        order.getInt();
        rect.left = order.getInt();
        rect.right = order.getInt();
        rect.top = order.getInt();
        rect.bottom = order.getInt();
        return rect;
    }
}
