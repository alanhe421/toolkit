package com.qq.reader.widget.cloudtag;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLTag */
public class a {
    public float a = 0.0f;
    public float b = 0.0f;
    public float c = 0.25f;
    public float d = 0.25f;
    public float e = 0.0f;
    public String f;
    public String g;
    public float[] h;
    public int i;
    int j = 0;
    int k = 0;
    public float[] l = new float[3];
    public float m = 255.0f;
    public float n = 1.0f;
    private IntBuffer o;
    private IntBuffer p;
    private ByteBuffer q;
    private FloatBuffer r;

    public a(float[] fArr, int i, float f, String str, String str2) {
        this.f = str;
        this.g = str2;
        this.h = fArr;
        this.i = i;
        this.e = f;
        this.j = 6;
        int[] iArr = new int[]{-16383, -16383, 0, 16383, 16383, 0, -16383, 16383, 0, -16383, -16383, 0, 16383, -16383, 0, 16383, 16383, 0};
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(iArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.o = allocateDirect.asIntBuffer();
        this.o.put(iArr);
        this.o.position(0);
        iArr = new int[]{0, 0, 65535, 0, 0, 65535, 0, 0, 0, 65535, 65535, 0, 0, 65535, 65535, 0};
        allocateDirect = ByteBuffer.allocateDirect(iArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.p = allocateDirect.asIntBuffer();
        this.p.put(iArr);
        this.p.position(0);
        this.k = 6;
        byte[] bArr = new byte[]{(byte) 0, (byte) 2, (byte) 1, (byte) 0, (byte) 3, (byte) 2};
        this.q = ByteBuffer.allocateDirect(bArr.length);
        this.q.put(bArr);
        this.q.position(0);
        float[] fArr2 = new float[]{0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
        allocateDirect = ByteBuffer.allocateDirect(fArr2.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.r = allocateDirect.asFloatBuffer();
        this.r.put(fArr2);
        this.r.position(0);
    }

    public void a(GL10 gl10) {
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, 5132, 0, this.o);
        gl10.glEnable(3553);
        gl10.glEnableClientState(32888);
        gl10.glTexCoordPointer(2, 5126, 0, this.r);
        gl10.glBindTexture(3553, this.i);
        gl10.glDrawArrays(4, 0, this.j);
        gl10.glDisable(3553);
    }

    public void a(float[] fArr) {
        this.l[0] = (((this.h[0] * fArr[0]) + (this.h[1] * fArr[1])) + (this.h[2] * fArr[2])) + 0.1f;
        this.l[1] = ((this.h[0] * fArr[4]) + (this.h[1] * fArr[5])) + (this.h[2] * fArr[6]);
        this.l[2] = (((this.h[0] * fArr[8]) + (this.h[1] * fArr[9])) + (this.h[2] * fArr[10])) + this.e;
        this.m = 1.0f;
        this.m = (1.0f / (-1.5f - -2.0f)) * (this.l[2] - -2.0f);
        if (this.m < 0.0f) {
            this.m = 0.0f;
        } else if (this.m > 1.0f) {
            this.m = 1.0f;
        }
    }

    public float[] b(float[] fArr) {
        a(fArr);
        return this.l;
    }

    public float[] a() {
        return new float[]{this.c, this.d};
    }
}
