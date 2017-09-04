package com.qq.reader.liveshow.avcontrollers;

import android.content.Context;
import android.graphics.Rect;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.av.opengl.GraphicRendererMgr;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import com.tencent.av.opengl.texture.BasicTexture;
import com.tencent.av.opengl.texture.StringTexture;
import com.tencent.av.opengl.texture.YUVTexture;
import com.tencent.av.opengl.texture.YUVTexture.GLRenderListener;
import com.tencent.av.opengl.ui.GLRootView;
import com.tencent.av.opengl.ui.GLView;
import com.tencent.av.opengl.utils.Utils;
import com.tencent.av.utils.QLog;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: GLVideoView */
public class b extends GLView {
    private GraphicRendererMgr A = null;
    private boolean B = true;
    private Runnable C = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            GLRootView gLRootView = this.a.getGLRootView();
            if (gLRootView != null) {
                this.a.invalidate();
                gLRootView.postDelayed(this.a.C, 80);
            }
        }
    };
    int a = -1;
    private YUVTexture b;
    private BasicTexture c;
    private StringTexture d;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private boolean k = false;
    private float l = 1.0f;
    private int m = 0;
    private int n = 0;
    private float o = 0.0f;
    private float p = 0.0f;
    private int q = 0;
    private boolean r = false;
    private int s = 0;
    private boolean t = false;
    private long u = 0;
    private String v = null;
    private int w = 0;
    private boolean x = false;
    private Context y;
    private boolean z = true;

    public b(Context context, GraphicRendererMgr graphicRendererMgr) {
        this.y = context;
        this.b = new YUVTexture(this.y);
        this.b.setGLRenderListener(new GLRenderListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onRenderFrame() {
                this.a.invalidate();
            }

            public void onRenderReset() {
                this.a.i();
                this.a.invalidate();
            }

            public void onRenderFlush() {
                this.a.i();
                this.a.invalidate();
            }

            public void onRenderInfoNotify(int i, int i2, int i3) {
                if (QLog.isColorLevel()) {
                    QLog.d("GLVideoView", 0, "onRenderInfoNotify uin: " + this.a.v + ", mVideoSrcType: " + this.a.w + ", width: " + i + ", height: " + i2 + ", angle: " + i3);
                }
                if (!this.a.x) {
                    SxbLog.b("GLVideoView", "PerformanceTest  end     " + SxbLog.a());
                    this.a.x = true;
                }
                this.a.b.setTextureSize(i, i2);
                this.a.invalidate();
            }
        });
        this.A = graphicRendererMgr;
    }

    protected void onDetachFromRoot() {
        GLRootView gLRootView = getGLRootView();
        if (gLRootView != null) {
            gLRootView.removeCallbacks(this.C);
        }
        super.onDetachFromRoot();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        if (this.b != null) {
            this.b.recycle();
            this.b = null;
        }
        this.C = null;
    }

    protected void renderBackground(GLCanvas gLCanvas) {
        if (!this.z) {
            this.z = false;
        }
        int width = getWidth();
        int height = getHeight();
        Rect paddings = getPaddings();
        int i = paddings.left;
        int i2 = paddings.top;
        int i3 = (width - paddings.left) - paddings.right;
        int i4 = (height - paddings.top) - paddings.bottom;
        if (this.a == -1) {
            this.a = Utils.getGLVersion(this.y);
        }
        if (this.a != 1) {
            gLCanvas.fillRect(0.0f, 0.0f, (float) width, (float) height, this.mBackgroundColor);
            if (this.mBackgroundColor != WebView.NIGHT_MODE_COLOR && this.z) {
                gLCanvas.fillRect((float) i, (float) i2, (float) i3, (float) i4, WebView.NIGHT_MODE_COLOR);
            }
            if ((this.b == null || !a() || !this.z) && this.mBackground != null) {
                this.mBackground.draw(gLCanvas, i, i2, i3, i4);
            }
        } else if ((this.b == null || !a() || !this.z) && this.mBackground != null) {
            this.mBackground.draw(gLCanvas, i, i2, i3, i4);
        }
    }

    protected void render(GLCanvas gLCanvas) {
        int width;
        int i;
        int width2;
        int sourceWidth;
        Rect paddings = getPaddings();
        renderBackground(gLCanvas);
        if (this.v != null && this.b != null && this.b.canRender() && this.z) {
            float f;
            float f2;
            float f3;
            c(false);
            width = (getWidth() - paddings.left) - paddings.right;
            int height = (getHeight() - paddings.top) - paddings.bottom;
            int imgAngle = this.b.getImgAngle();
            if (this.B) {
                i = ((this.i + imgAngle) + 4) % 4;
            } else {
                i = ((this.i + imgAngle) + 4) % 4;
                if (i == 2 || i == 0) {
                    i = 1;
                }
            }
            float f4 = (float) paddings.left;
            float f5 = (float) paddings.top;
            float f6 = (float) width;
            float f7 = (float) height;
            if (i % 2 != 0) {
                width = (int) ((float) width);
            } else {
                f = f7;
                f7 = f6;
                f6 = f;
                f2 = f5;
                f5 = f4;
                f4 = f2;
                int i2 = height;
                height = width;
                width = i2;
            }
            float imgWidth = (float) this.b.getImgWidth();
            float imgHeight = (float) this.b.getImgHeight();
            float f8 = imgWidth / imgHeight;
            float f9 = f7 / f6;
            boolean z = imgWidth < imgHeight && imgAngle == 0;
            if (a(i, z)) {
                if (i % 2 == 0) {
                    f3 = f7 / f8;
                    if (f3 > f6) {
                        f3 = f6 * f8;
                        f5 += (f7 - f3) / 2.0f;
                        f7 = f3;
                    } else {
                        f4 += (f6 - f3) / 2.0f;
                        f6 = f3;
                    }
                } else {
                    f3 = f6 * f8;
                    if (f3 > f7) {
                        f3 = f7 / f8;
                        f4 += (f6 - f3) / 2.0f;
                        f6 = f3;
                    } else {
                        f5 += (f7 - f3) / 2.0f;
                        f7 = f3;
                    }
                }
                f9 = f4;
                f4 = imgWidth;
                f = imgHeight;
                imgHeight = f5;
                f5 = f6;
                f6 = f7 / f6;
                f3 = f7;
                f7 = f;
            } else {
                imgAngle = (int) imgWidth;
                if (imgAngle % 8 != 0) {
                    imgHeight = ((float) (imgAngle * imgAngle)) / ((float) (((imgAngle / 8) + 1) * 8));
                    f3 = imgHeight / f8;
                    f = f9;
                    f9 = f4;
                    f4 = imgHeight;
                    imgHeight = f5;
                    f5 = f6;
                    f6 = f;
                    f2 = f7;
                    f7 = f3;
                    f3 = f2;
                } else {
                    f3 = f7;
                    f7 = imgHeight;
                    imgHeight = f5;
                    f5 = f6;
                    f6 = f9;
                    f9 = f4;
                    f4 = imgWidth;
                }
            }
            imgHeight = (imgHeight * this.l) + (((float) this.m) * (1.0f - this.l));
            f9 = (f9 * this.l) + (((float) this.n) * (1.0f - this.l));
            f3 *= this.l;
            f5 *= this.l;
            if (!(this.r || this.q == 0)) {
                if ((this.q & 3) == 3) {
                    this.o = ((float) (height / 2)) - ((f3 / 2.0f) + imgHeight);
                } else if ((this.q & 1) == 1) {
                    this.o = -imgHeight;
                } else if ((this.q & 2) == 2) {
                    this.o = (((float) height) - f3) - imgHeight;
                }
                if ((this.q & 12) == 12) {
                    this.p = ((float) (width / 2)) - ((f5 / 2.0f) + f9);
                } else if ((this.q & 4) == 4) {
                    this.p = -f9;
                } else if ((this.q & 8) == 8) {
                    this.p = (((float) width) - f5) - f9;
                }
                this.q = 0;
                if (QLog.isColorLevel()) {
                    QLog.d("GLVideoView", 0, "render uin: " + this.v + ", mVideoSrcType: " + this.w + ", width: " + height + ", height: " + width + ", mScale: " + this.l + ", mPivotX: " + this.m + ", mPivotY: " + this.n + ", x: " + imgHeight + ", y: " + f9 + ", w: " + f3 + ", h: " + f5 + ", mOffsetX: " + this.o + ", mOffsetY: " + this.p + ", mWidth: " + this.g + ", mHeight: " + this.h);
                }
            }
            float f10 = this.p + f9;
            this.e = (int) (this.o + imgHeight);
            this.f = (int) f10;
            this.g = (int) f3;
            this.h = (int) f5;
            if (f8 > f6) {
                f6 *= f7;
                if (Utils.getGLVersion(this.y) == 1) {
                    f6 = (f6 * f4) / ((float) Utils.nextPowerOf2((int) f4));
                }
                f5 = (f4 - f6) / 2.0f;
                this.b.setSourceSize((int) f6, (int) f7);
                this.b.setSourceLeft((int) f5);
                this.b.setSourceTop(0);
            } else {
                f6 = f4 / f6;
                if (Utils.getGLVersion(this.y) == 1) {
                    f6 = (f6 * f7) / ((float) Utils.nextPowerOf2((int) f7));
                }
                f5 = (f7 - f6) / 2.0f;
                this.b.setSourceSize((int) f4, (int) f6);
                this.b.setSourceLeft(0);
                this.b.setSourceTop((int) f5);
            }
            if (Utils.getGLVersion(this.y) == 1) {
                this.b.setSourceSize((int) f4, (int) f7);
                this.b.setSourceLeft(0);
                this.b.setSourceTop((int) 0.0f);
            }
            gLCanvas.save(2);
            width2 = getWidth() / 2;
            int height2 = getHeight() / 2;
            gLCanvas.translate((float) width2, (float) height2);
            if (this.k) {
                if (this.i % 2 == 0) {
                    gLCanvas.scale(-1.0f, 1.0f, 1.0f);
                } else {
                    gLCanvas.scale(1.0f, -1.0f, 1.0f);
                }
            }
            gLCanvas.rotate((float) (i * 90), 0.0f, 0.0f, 1.0f);
            if (i % 2 != 0) {
                gLCanvas.translate((float) (-height2), (float) (-width2));
            } else {
                gLCanvas.translate((float) (-width2), (float) (-height2));
            }
            this.b.draw(gLCanvas, this.e, this.f, this.g, this.h);
            gLCanvas.restore();
        }
        if (this.t && this.c != null) {
            this.s %= 360;
            width2 = getWidth();
            i = getHeight();
            sourceWidth = this.c.getSourceWidth();
            width = this.c.getSourceHeight();
            if (sourceWidth > width2) {
                sourceWidth = width2;
            }
            if (width > i) {
                width = i;
            }
            gLCanvas.save(2);
            gLCanvas.translate((float) (width2 / 2), (float) (i / 2));
            gLCanvas.rotate((float) this.s, 0.0f, 0.0f, 1.0f);
            gLCanvas.translate((float) ((-sourceWidth) / 2), (float) ((-width) / 2));
            this.c.draw(gLCanvas, 0, 0, sourceWidth, width);
            gLCanvas.restore();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.u >= 80) {
                this.u = currentTimeMillis;
                this.s += 8;
            }
        }
        if (this.d != null) {
            width2 = getWidth();
            i = getHeight();
            sourceWidth = this.d.getSourceWidth();
            width = this.d.getSourceHeight();
            if (sourceWidth > width2) {
                sourceWidth = width2;
            }
            if (width > i) {
                width = i;
            }
            gLCanvas.save(2);
            gLCanvas.translate((float) ((width2 / 2) - (sourceWidth / 2)), (float) ((i / 2) - (width / 2)));
            this.d.draw(gLCanvas, 0, 0, sourceWidth, width);
            gLCanvas.restore();
        }
    }

    public void setRotation(int i) {
        int i2;
        switch (i % 360) {
            case Opcodes.IPUT_WIDE /*90*/:
                i2 = 3;
                break;
            case 180:
                i2 = 2;
                break;
            case im_common.WPA_QZONE /*270*/:
                i2 = 1;
                break;
            default:
                i2 = 0;
                break;
        }
        if (this.i != i2) {
            this.i = i2;
            this.l = 1.0f;
            this.m = 0;
            this.n = 0;
            this.o = 0.0f;
            this.p = 0.0f;
            if (getVisibility() == 0) {
                invalidate();
            }
        }
    }

    public boolean a() {
        if (this.b != null) {
            return this.b.canRender();
        }
        return false;
    }

    public String b() {
        return this.v;
    }

    public int c() {
        return this.w;
    }

    public void a(String str, int i) {
        if (str == null || i == 0) {
            this.v = null;
            this.w = 0;
            return;
        }
        this.v = str;
        this.w = i;
        String str2 = this.v + "_" + this.w;
        if (this.b == null) {
            SxbLog.e("GLVideoView", "null == mYuvTexture");
        }
        this.A.setGlRender(str2, this.b);
        this.l = 1.0f;
        this.m = 0;
        this.n = 0;
        this.o = 0.0f;
        this.p = 0.0f;
    }

    public void d() {
        if (this.v != null) {
            this.A.setGlRender(this.v + "_" + this.w, null);
        }
        this.v = null;
        this.w = 0;
        this.l = 1.0f;
        this.m = 0;
        this.n = 0;
        this.o = 0.0f;
        this.p = 0.0f;
    }

    public void a(boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d("GLVideoView", 0, "setBlackBorder uin: " + this.v + ", mVideoSrcType: " + this.w + ", mIsPC: " + this.j + ", isPC: " + z);
        }
        if (this.j != z) {
            this.j = z;
            this.l = 1.0f;
            this.m = 0;
            this.n = 0;
            this.o = 0.0f;
            this.p = 0.0f;
            invalidate();
        }
    }

    public boolean e() {
        return this.j;
    }

    public void b(boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d("GLVideoView", 0, "setMirror uin: " + this.v + ", mVideoSrcType: " + this.w + ", mMirror: " + this.k + ", mirror: " + z);
        }
        if (this.k != z) {
            this.k = z;
            invalidate();
        }
    }

    public boolean f() {
        return this.k;
    }

    public float g() {
        return this.l;
    }

    public void a(float f, int i, int i2, boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d("GLVideoView", 0, "setScale uin: " + this.v + ", mVideoSrcType: " + this.w + ", scale: " + f + ", x: " + i + ", y: " + i2 + ", isEnd: " + z + ", mOffsetX: " + this.o + ", mOffsetY: " + this.p + ", mX: " + this.e + ", mY: " + this.f + ", mWidth: " + this.g + ", mHeight: " + this.h);
        }
        if (z) {
            if (f < 1.0f) {
                this.q = 0;
                this.q |= 3;
                this.q |= 12;
                f = 1.0f;
            }
            if (f > 4.0f) {
                this.q = 0;
                this.q |= 1;
                this.q |= 4;
                f = 4.0f;
            }
        } else if (f < 0.75f) {
            f = 0.75f;
        } else if (f > 4.0f) {
            f = 4.0f;
        }
        if (this.i % 2 == 0) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        this.l = f;
        this.m = i2;
        this.n = i;
        invalidate();
    }

    public void a(int i, int i2, boolean z) {
        int width;
        if (QLog.isColorLevel()) {
            QLog.d("GLVideoView", 0, "setOffset uin: " + this.v + ", mVideoSrcType: " + this.w + ", offsetX: " + i + ", offsetY: " + i2 + ", isEnd: " + z);
        }
        this.r = !z;
        if (z) {
            Rect paddings = getPaddings();
            width = (getWidth() - paddings.left) - paddings.right;
            int height = (getHeight() - paddings.top) - paddings.bottom;
            if ((((this.b.getImgAngle() + this.i) + 4) % 4) % 2 == 0) {
                int i3 = height;
                height = width;
                width = i3;
            }
            this.q = 0;
            if (this.e < 0 || this.e + this.g > height) {
                if (this.e >= 0) {
                    if (this.g <= height) {
                        this.q |= 3;
                    } else {
                        this.q |= 1;
                    }
                } else if (this.e + this.g <= height) {
                    if (this.g <= height) {
                        this.q |= 3;
                    } else {
                        this.q |= 2;
                    }
                }
            } else if (this.g <= height) {
                this.q |= 3;
            }
            if (this.f < 0 || this.f + this.h > width) {
                if (this.f >= 0) {
                    if (this.h <= width) {
                        this.q |= 12;
                    } else {
                        this.q |= 4;
                    }
                } else if (this.f + this.h <= width) {
                    if (this.h <= width) {
                        this.q |= 12;
                    } else {
                        this.q |= 8;
                    }
                }
            } else if (this.h <= width) {
                this.q |= 12;
            }
            if (QLog.isColorLevel()) {
                QLog.d("GLVideoView", 0, "setOffset uin: " + this.v + ", mVideoSrcType: " + this.w + ", mPosition: " + this.q + ", width: " + height + ", height: " + width + ", mX: " + this.e + ", mY: " + this.f + ", mWidth: " + this.g + ", mHeight: " + this.h);
            }
        }
        if (this.i % 2 != 0) {
            if (this.i == 1) {
                i = -i;
            } else {
                i2 = -i2;
            }
        } else if (this.i == 2) {
            width = -i;
            i = -i2;
            i2 = width;
        } else {
            i3 = i2;
            i2 = i;
            i = i3;
        }
        this.o += (float) i2;
        this.p += (float) i;
        invalidate();
    }

    public void c(boolean z) {
        if (this.t != z) {
            if (QLog.isColorLevel()) {
                QLog.d("GLVideoView", 0, "enableLoading uin: " + this.v + ", mVideoSrcType: " + this.w + ", loading: " + z + ", mLoading: " + this.t);
            }
            this.t = z;
            GLRootView gLRootView;
            if (z) {
                if (this.c == null) {
                    gLRootView = getGLRootView();
                } else {
                    gLRootView = getGLRootView();
                }
                if (gLRootView != null) {
                    gLRootView.post(this.C);
                    return;
                }
                return;
            }
            gLRootView = getGLRootView();
            if (gLRootView != null) {
                gLRootView.removeCallbacks(this.C);
            }
        }
    }

    public boolean h() {
        return this.t;
    }

    public void i() {
        if (this.b != null) {
            this.b.flush(false);
        }
        if (this.v != null) {
            this.A.flushGlRender(this.v + "_" + this.w);
        }
    }

    public void d(boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d("GLVideoView", 0, "setNeedRenderVideo uin: " + this.v + ", mVideoSrcType: " + this.w + ", bRender: " + z + ", mNeedRenderVideo: " + this.z);
        }
        this.z = z;
        invalidate();
    }

    private boolean a(int i, boolean z) {
        boolean z2 = true;
        if ((this.v != null && this.v.equals("")) || this.mParent == null || getWidth() != this.mParent.getWidth() || getHeight() != this.mParent.getHeight()) {
            return false;
        }
        if (this.j) {
            return true;
        }
        if (i % 2 != 0) {
            return z;
        }
        if (z) {
            z2 = false;
        }
        return z2;
    }
}
