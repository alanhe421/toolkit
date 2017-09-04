package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* compiled from: GPUImageFilter */
public class a {
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    private final LinkedList<Runnable> g;
    private final String h;
    private final String i;
    private boolean j;

    public a() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
    }

    public a(String str, String str2) {
        this.g = new LinkedList();
        this.h = str;
        this.i = str2;
    }

    public final void a() {
        b();
        this.j = true;
        c();
    }

    public void b() {
        this.a = c.a(this.h, this.i);
        this.b = GLES20.glGetAttribLocation(this.a, ComicStoreExclusiveItemCard.NET_AD_ATTR_POSITION);
        this.c = GLES20.glGetUniformLocation(this.a, "inputImageTexture");
        this.d = GLES20.glGetAttribLocation(this.a, "inputTextureCoordinate");
        this.j = true;
    }

    public void c() {
    }

    public final void d() {
        this.j = false;
        GLES20.glDeleteProgram(this.a);
        e();
    }

    public void e() {
    }

    public void a(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.a);
        g();
        if (this.j) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.b, 2, 5126, false, 0, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.b);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.d, 2, 5126, false, 0, floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.d);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.c, 0);
            }
            f();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.b);
            GLES20.glDisableVertexAttribArray(this.d);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected void f() {
    }

    protected void g() {
        while (!this.g.isEmpty()) {
            ((Runnable) this.g.removeFirst()).run();
        }
    }

    public int h() {
        return this.a;
    }
}
