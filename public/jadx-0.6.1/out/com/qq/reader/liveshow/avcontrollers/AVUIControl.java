package com.qq.reader.liveshow.avcontrollers;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.av.opengl.GraphicRendererMgr;
import com.tencent.av.opengl.gesturedetectors.MoveGestureDetector;
import com.tencent.av.opengl.gesturedetectors.MoveGestureDetector.OnMoveGestureListener;
import com.tencent.av.opengl.ui.GLRootView;
import com.tencent.av.opengl.ui.GLView;
import com.tencent.av.opengl.ui.GLView.OnTouchListener;
import com.tencent.av.opengl.ui.GLViewGroup;
import com.tencent.av.opengl.utils.Utils;
import com.tencent.av.utils.QLog;
import com.tencent.smtt.sdk.WebView;
import java.util.HashMap;

public class AVUIControl extends GLViewGroup {
    private com.qq.reader.liveshow.avcontrollers.c.a A;
    private Callback B = new Callback(this) {
        final /* synthetic */ AVUIControl a;

        {
            this.a = r1;
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.a.q = true;
            if (this.a.y.i() != null) {
                this.a.y.h().setRenderMgrAndHolder(this.a.c, surfaceHolder);
            }
            this.a.b.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_SURFACE_CREATED"));
            SxbLog.e("VideoLayerUI", " surfaceCreated");
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (surfaceHolder.getSurface() != null) {
                surfaceHolder.setFixedSize(i2, i3);
                SxbLog.e("VideoLayerUI", "memoryLeak surfaceChanged");
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            SxbLog.e("VideoLayerUI", "memoryLeak surfaceDestroyed");
        }
    };
    boolean a = false;
    Context b = null;
    GraphicRendererMgr c = null;
    View d = null;
    int e = 0;
    int f = 0;
    GLRootView g = null;
    b[] h = null;
    int i = 0;
    int j = -1;
    OnTouchListener k = null;
    GestureDetector l = null;
    MoveGestureDetector m = null;
    ScaleGestureDetector n = null;
    int o = 0;
    int p = 0;
    boolean q = false;
    int r = 1;
    boolean s = false;
    private int t = -1;
    private int u = -1;
    private String v = "";
    private boolean w = false;
    private SurfaceView x = null;
    private c y;
    private HashMap<Integer, String> z = new HashMap();

    enum MoveDistanceLevel {
        e_MoveDistance_Min,
        e_MoveDistance_Positive,
        e_MoveDistance_Negative
    }

    class a extends SimpleOnGestureListener {
        final /* synthetic */ AVUIControl a;

        a(AVUIControl aVUIControl) {
            this.a = aVUIControl;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (QLog.isColorLevel()) {
                QLog.d("VideoLayerUI", 0, "GestureListener-->mTargetIndex=" + this.a.j);
            }
            if (this.a.j > 0) {
                this.a.b.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_SWITCH_VIDEO").putExtra("identifier", (String) this.a.z.get(Integer.valueOf(this.a.j))));
                this.a.a(0, this.a.j);
            }
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent.getY() - motionEvent2.getY() <= 20.0f || Math.abs(f2) <= 10.0f) {
                if (motionEvent2.getY() - motionEvent.getY() <= 20.0f || Math.abs(f2) <= 10.0f) {
                    if (motionEvent2.getX() - motionEvent.getX() <= 20.0f || Math.abs(f) <= 10.0f) {
                        if (motionEvent.getX() - motionEvent2.getX() > 20.0f && Math.abs(f) > 10.0f && this.a.A != null) {
                            this.a.A.c();
                        }
                    } else if (this.a.A != null) {
                        this.a.A.d();
                    }
                } else if (this.a.A != null) {
                    this.a.A.b();
                }
            } else if (this.a.A != null) {
                this.a.A.a();
            }
            return false;
        }
    }

    class b implements OnMoveGestureListener {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        final /* synthetic */ AVUIControl f;

        b(AVUIControl aVUIControl) {
            this.f = aVUIControl;
        }

        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            if (this.f.j != 0 && this.f.j == 1) {
                this.a = (int) moveGestureDetector.getFocusX();
                this.b = (int) moveGestureDetector.getFocusY();
                this.e = this.f.h();
            }
            return true;
        }

        public boolean onMove(MoveGestureDetector moveGestureDetector) {
            PointF focusDelta = moveGestureDetector.getFocusDelta();
            int i = (int) focusDelta.x;
            int i2 = (int) focusDelta.y;
            if (this.f.j == 0) {
                this.f.h[0].a(i, i2, false);
            } else if (this.f.j == 1) {
                if (Math.abs(i) > 4 || Math.abs(i2) > 4) {
                    this.f.s = true;
                }
                this.f.b(i, i2);
            }
            return true;
        }

        public void onMoveEnd(MoveGestureDetector moveGestureDetector) {
            PointF focusDelta = moveGestureDetector.getFocusDelta();
            int i = (int) focusDelta.x;
            int i2 = (int) focusDelta.y;
            if (this.f.j == 0) {
                this.f.h[0].a(i, i2, true);
            } else if (this.f.j == 1) {
                this.f.b(i, i2);
                this.c = (int) moveGestureDetector.getFocusX();
                this.d = (int) moveGestureDetector.getFocusY();
                this.f.r = this.f.a(this.e, this.a, this.b, this.c, this.d);
                this.f.c(this.f.r);
            }
        }
    }

    class c implements OnScaleGestureListener {
        final /* synthetic */ AVUIControl a;

        c(AVUIControl aVUIControl) {
            this.a = aVUIControl;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            this.a.h[0].a(scaleGestureDetector.getScaleFactor() * this.a.h[0].g(), (int) focusX, (int) focusY, false);
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            this.a.h[0].a(scaleGestureDetector.getScaleFactor() * this.a.h[0].g(), (int) focusX, (int) focusY, true);
        }
    }

    class d implements OnTouchListener {
        final /* synthetic */ AVUIControl a;

        d(AVUIControl aVUIControl) {
            this.a = aVUIControl;
        }

        public boolean onTouch(GLView gLView, MotionEvent motionEvent) {
            if (gLView == this.a.h[0]) {
                this.a.j = 0;
            } else if (gLView == this.a.h[1]) {
                this.a.j = 1;
            } else if (gLView == this.a.h[2]) {
                this.a.j = 2;
            } else if (gLView == this.a.h[3]) {
                this.a.j = 3;
            } else {
                this.a.j = -1;
            }
            if (this.a.l != null) {
                this.a.l.onTouchEvent(motionEvent);
            }
            return true;
        }
    }

    public AVUIControl(Context context, View view) {
        this.b = context;
        this.d = view;
        this.c = GraphicRendererMgr.getInstance();
        this.y = c.a();
        e();
        f();
        j();
        this.z.clear();
    }

    private void j() {
        if (this.y != null) {
            this.w = true;
        }
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "isSupportMultiVideo: " + this.w);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "onLayout|left: " + i + ", top: " + i2 + ", right: " + i3 + ", bottom: " + i4);
        }
        a(false);
    }

    public void a() {
        if (this.g != null) {
            this.g.onResume();
        }
        setRotation(this.p);
    }

    public void b() {
        if (this.g != null) {
            this.g.onPause();
        }
    }

    public void c() {
        SxbLog.d("VideoLayerUI", " AVUIControl onDestroy");
        g();
        this.b = null;
        this.d = null;
        removeAllView();
        for (int i = 0; i < this.h.length; i++) {
            if (this.h[i] != null) {
                this.h[i].i();
                this.h[i].d();
                this.h[i] = null;
            }
        }
        this.g.setOnTouchListener(null);
        this.g.setContentPane(null);
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.c = null;
        this.g = null;
        this.h = null;
    }

    public void a(boolean z, String str) {
        int a = a(str, 1);
        if (a >= 0) {
            this.h[a].b(z);
        } else {
            SxbLog.e("VideoLayerUI", "setMirror->fail index: " + a);
        }
    }

    public boolean a(boolean z, boolean z2, String str) {
        if (this.b == null || Utils.getGLVersion(this.b) == 1) {
            return false;
        }
        if (z) {
            b bVar = null;
            int a = a(str, 1);
            if (a < 0) {
                a = a(0);
                if (a >= 0) {
                    bVar = this.h[a];
                    this.z.put(Integer.valueOf(a), com.qq.reader.liveshow.model.c.a().b());
                    bVar.a(str, 1);
                    this.t = a;
                }
            } else {
                bVar = this.h[a];
            }
            if (bVar != null) {
                bVar.a(false);
                bVar.c(false);
                bVar.setVisibility(0);
            }
            if (z2 && a > 0) {
                a(0, a);
            }
        } else if (!z) {
            int a2 = a(str, 1);
            if (a2 >= 0) {
                b(a2);
                this.t = -1;
            }
        }
        this.a = z;
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRotation(int r4) {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.b;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r4 % 90;
        r2 = r3.o;
        r2 = r2 % 90;
        if (r0 == r2) goto L_0x0010;
    L_0x000e:
        r3.i = r1;
    L_0x0010:
        r3.o = r4;
        r3.p = r4;
        r0 = r3.y;
        if (r0 == 0) goto L_0x002d;
    L_0x0018:
        r0 = r3.y;
        r0 = r0.h();
        if (r0 == 0) goto L_0x002d;
    L_0x0020:
        r0 = r3.y;
        r0 = r0.h();
        r0 = r0.getVideoCtrl();
        r0.setRotation(r4);
    L_0x002d:
        switch(r4) {
            case 0: goto L_0x0031;
            case 90: goto L_0x0044;
            case 180: goto L_0x0058;
            case 270: goto L_0x006c;
            default: goto L_0x0030;
        };
    L_0x0030:
        goto L_0x0005;
    L_0x0031:
        r0 = r1;
    L_0x0032:
        r2 = r3.getChildCount();
        if (r0 >= r2) goto L_0x0005;
    L_0x0038:
        r2 = r3.getChild(r0);
        if (r2 == 0) goto L_0x0041;
    L_0x003e:
        r2.setRotation(r1);
    L_0x0041:
        r0 = r0 + 1;
        goto L_0x0032;
    L_0x0044:
        r0 = r3.getChildCount();
        if (r1 >= r0) goto L_0x0005;
    L_0x004a:
        r0 = r3.getChild(r1);
        if (r0 == 0) goto L_0x0055;
    L_0x0050:
        r2 = 90;
        r0.setRotation(r2);
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x0044;
    L_0x0058:
        r0 = r3.getChildCount();
        if (r1 >= r0) goto L_0x0005;
    L_0x005e:
        r0 = r3.getChild(r1);
        if (r0 == 0) goto L_0x0069;
    L_0x0064:
        r2 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        r0.setRotation(r2);
    L_0x0069:
        r1 = r1 + 1;
        goto L_0x0058;
    L_0x006c:
        r0 = r3.getChildCount();
        if (r1 >= r0) goto L_0x0005;
    L_0x0072:
        r0 = r3.getChild(r1);
        if (r0 == 0) goto L_0x007d;
    L_0x0078:
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r0.setRotation(r2);
    L_0x007d:
        r1 = r1 + 1;
        goto L_0x006c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.liveshow.avcontrollers.AVUIControl.setRotation(int):void");
    }

    int d() {
        int i = 0;
        int i2 = 0;
        while (i < this.h.length) {
            b bVar = this.h[i];
            if (bVar.getVisibility() == 0 && bVar.b() != null) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    public int a(int i) {
        while (i < this.h.length) {
            b bVar = this.h[i];
            if (bVar.b() == null || bVar.getVisibility() == 1) {
                return i;
            }
            i++;
        }
        return -1;
    }

    int a(String str, int i) {
        if (str == null) {
            SxbLog.e("VideoLayerUI", "getViewIndexById->id is empty!");
            return -1;
        }
        int i2 = 0;
        while (i2 < this.h.length) {
            b bVar = this.h[i2];
            if (str.equals(bVar.b()) && bVar.c() == i && bVar.getVisibility() == 0) {
                break;
            }
            i2++;
        }
        i2 = -1;
        return i2;
    }

    void a(boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "layoutVideoView virtical: " + z);
        }
        if (this.b != null) {
            int width = getWidth();
            int height = getHeight();
            SxbLog.c("VideoLayerUI", "width: " + getWidth() + "height: " + getHeight());
            this.h[0].layout(0, 0, width, height);
            this.h[0].setBackgroundColor(WebView.NIGHT_MODE_COLOR);
            this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetX);
            int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetY);
            int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_margin_top);
            this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_margin_bottom);
            int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_width);
            int dimensionPixelSize4 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_height);
            int dimensionPixelSize5 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_marginright);
            int dimensionPixelSize6 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.small_area_marginbetween);
            height = (height - dimensionPixelSize) - this.f;
            if (this.w) {
                if (QLog.isColorLevel()) {
                    QLog.d("VideoLayerUI", 0, "SupportMultiVideo");
                }
                dimensionPixelSize3 = (width - dimensionPixelSize5) - dimensionPixelSize3;
                width -= dimensionPixelSize5;
                if (z) {
                    width = this.h[1].getBounds().left;
                    dimensionPixelSize2 = this.h[1].getBounds().right;
                } else {
                    height = dimensionPixelSize2 + dimensionPixelSize4;
                    dimensionPixelSize = dimensionPixelSize2;
                    dimensionPixelSize2 = width;
                    width = dimensionPixelSize3;
                }
                this.h[1].layout(width, dimensionPixelSize, dimensionPixelSize2, height);
                if (z) {
                    width = this.h[2].getBounds().left;
                    dimensionPixelSize2 = this.h[2].getBounds().right;
                } else {
                    dimensionPixelSize = height + dimensionPixelSize6;
                    height = dimensionPixelSize + dimensionPixelSize4;
                }
                this.h[2].layout(width, dimensionPixelSize, dimensionPixelSize2, height);
                if (z) {
                    width = this.h[3].getBounds().left;
                    dimensionPixelSize2 = this.h[3].getBounds().right;
                } else {
                    dimensionPixelSize = height + dimensionPixelSize6;
                    height = dimensionPixelSize + dimensionPixelSize4;
                }
                this.h[3].layout(width, dimensionPixelSize, dimensionPixelSize2, height);
                this.h[1].setBackgroundColor(-1);
                this.h[2].setBackgroundColor(-1);
                this.h[3].setBackgroundColor(-1);
                this.h[1].setPaddings(2, 3, 3, 3);
                this.h[2].setPaddings(2, 3, 2, 3);
                this.h[3].setPaddings(2, 3, 2, 3);
            } else {
                height = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_width);
                dimensionPixelSize = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_height);
                dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetX);
                width = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetY) + this.e;
                this.h[1].layout(dimensionPixelSize2, width, height + dimensionPixelSize2, dimensionPixelSize + width);
                this.h[1].setBackgroundColor(-1);
            }
            invalidate();
        }
    }

    void b(int i) {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "closeVideoView index: " + i);
        }
        b bVar = this.h[d(i)];
        bVar.setVisibility(1);
        bVar.d(true);
        bVar.c(false);
        bVar.a(false);
        bVar.d();
        com.qq.reader.liveshow.model.a.a(com.qq.reader.liveshow.model.a.a() - 1);
        a(false);
    }

    private int d(int i) {
        if (i == 1 && d() == 4) {
            a(1, 2);
            a(2, 3);
            return 3;
        } else if (i == 2 && d() == 4) {
            a(2, 3);
            return 3;
        } else if (i != 1 || d() != 3) {
            return i;
        } else {
            a(1, 2);
            return 2;
        }
    }

    void e() {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "initQQGlView");
        }
        this.g = (GLRootView) this.d.findViewById(e.av_video_glview);
        this.h = new b[4];
        this.h[0] = new b(this.b.getApplicationContext(), this.c);
        this.h[0].setVisibility(1);
        addView(this.h[0]);
        for (int i = 3; i >= 1; i--) {
            this.h[i] = new b(this.b.getApplicationContext(), this.c);
            this.h[i].setVisibility(1);
            addView(this.h[i]);
        }
        this.g.setContentPane(this);
        this.n = new ScaleGestureDetector(this.b, new c(this));
        this.l = new GestureDetector(this.b, new a(this));
        this.m = new MoveGestureDetector(this.b, new b(this));
        this.k = new d(this);
        setOnTouchListener(this.k);
    }

    void f() {
        WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
        LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.flags = 776;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        layoutParams.type = 2005;
        layoutParams.gravity = 51;
        try {
            this.x = new SurfaceView(this.b);
            SurfaceHolder holder = this.x.getHolder();
            holder.addCallback(this.B);
            holder.setType(3);
            this.x.setZOrderMediaOverlay(true);
            windowManager.addView(this.x, layoutParams);
        } catch (IllegalStateException e) {
            windowManager.updateViewLayout(this.x, layoutParams);
            if (QLog.isColorLevel()) {
                QLog.d("VideoLayerUI", 0, "add camera surface view fail: IllegalStateException." + e);
            }
        } catch (Exception e2) {
            if (QLog.isColorLevel()) {
                QLog.d("VideoLayerUI", 0, "add camera surface view fail." + e2);
            }
        }
        SxbLog.b("VideoLayerUI", "initCameraPreview");
    }

    void g() {
        WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
        try {
            this.x.getHolder().removeCallback(this.B);
            windowManager.removeView(this.x);
            this.x = null;
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.e("VideoLayerUI", 0, "remove camera view fail.", e);
            }
        }
    }

    void a(int i, int i2) {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "switchVideo index1: " + i + ", index2: " + i2);
        }
        if (i != i2 && i >= 0 && i < this.h.length && i2 >= 0 && i2 < this.h.length) {
            if (1 == this.h[i].getVisibility() || 1 == this.h[i2].getVisibility()) {
                SxbLog.c("switchVideo", "can not switchVideo");
                return;
            }
            String b = this.h[i].b();
            int c = this.h[i].c();
            boolean e = this.h[i].e();
            boolean f = this.h[i].f();
            boolean h = this.h[i].h();
            String b2 = this.h[i2].b();
            int c2 = this.h[i2].c();
            boolean e2 = this.h[i2].e();
            boolean f2 = this.h[i2].f();
            boolean h2 = this.h[i2].h();
            this.h[i].a(b2, c2);
            this.h[i].a(e2);
            this.h[i].b(f2);
            this.h[i].c(h2);
            this.h[i2].a(b, c);
            this.h[i2].a(e);
            this.h[i2].b(f);
            this.h[i2].c(h);
            int i3 = this.t;
            this.t = this.u;
            this.u = i3;
            c(i, i2);
        }
    }

    public void a(boolean z, String str, int i) {
        if (QLog.isColorLevel()) {
            QLog.d("VideoLayerUI", 0, "setSmallVideoViewLayout position: " + this.r);
        }
        if (this.b != null) {
            int width = getWidth();
            int height = getHeight();
            int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_width);
            int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_height);
            int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetX);
            int dimensionPixelSize4 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetY);
            if (this.f == 0) {
                dimensionPixelSize4 = dimensionPixelSize3;
            }
            switch (this.r) {
                case 1:
                    dimensionPixelSize3 += dimensionPixelSize;
                    dimensionPixelSize4 = (dimensionPixelSize4 + this.e) + dimensionPixelSize2;
                    break;
                case 2:
                    dimensionPixelSize3 = ((width - dimensionPixelSize) - dimensionPixelSize3) + dimensionPixelSize;
                    dimensionPixelSize4 = (dimensionPixelSize4 + this.e) + dimensionPixelSize2;
                    break;
                case 3:
                    dimensionPixelSize3 = ((width - dimensionPixelSize) - dimensionPixelSize3) + dimensionPixelSize;
                    dimensionPixelSize4 = (((height - dimensionPixelSize2) - dimensionPixelSize4) - this.f) + dimensionPixelSize2;
                    break;
                case 4:
                    dimensionPixelSize3 += dimensionPixelSize;
                    dimensionPixelSize4 = (((height - dimensionPixelSize2) - dimensionPixelSize4) - this.f) + dimensionPixelSize2;
                    break;
            }
            if (z) {
                b bVar = null;
                this.v = str;
                dimensionPixelSize3 = a(str, i);
                if (!(this.w || this.u == -1)) {
                    b(this.u);
                }
                if (dimensionPixelSize3 < 0) {
                    if (this.v.equals(com.qq.reader.liveshow.model.a.c())) {
                        dimensionPixelSize3 = 0;
                    } else {
                        dimensionPixelSize3 = a(1);
                    }
                    if (dimensionPixelSize3 >= 0) {
                        bVar = this.h[dimensionPixelSize3];
                        bVar.a(str, i);
                        this.z.put(Integer.valueOf(dimensionPixelSize3), this.v);
                        this.u = dimensionPixelSize3;
                    }
                } else {
                    bVar = this.h[dimensionPixelSize3];
                }
                if (bVar != null) {
                    bVar.a(false);
                    bVar.b(false);
                    bVar.c(false);
                    bVar.setVisibility(0);
                    return;
                }
                return;
            }
            dimensionPixelSize4 = a(str, i);
            if (dimensionPixelSize4 >= 0) {
                b(dimensionPixelSize4);
                this.u = -1;
            }
        }
    }

    void b(int i, int i2) {
        int i3 = 0;
        if (this.b != null) {
            int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_width);
            int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_height);
            Rect bounds = getBounds();
            int width = bounds.width() - dimensionPixelSize;
            int height = bounds.height() - dimensionPixelSize2;
            int i4 = this.h[1].getBounds().left + i;
            int i5 = this.h[1].getBounds().top + i2;
            if (i4 < 0) {
                width = 0;
            } else if (i4 <= width) {
                width = i4;
            }
            if (i5 >= 0) {
                if (i5 > height) {
                    i3 = height;
                } else {
                    i3 = i5;
                }
            }
            this.h[1].layout(width, i3, width + dimensionPixelSize, i3 + dimensionPixelSize2);
        }
    }

    int h() {
        Rect bounds = getBounds();
        int centerX = bounds.centerX();
        int centerY = bounds.centerY();
        int centerX2 = this.h[1].getBounds().centerX();
        int centerY2 = this.h[1].getBounds().centerY();
        if (centerX2 < centerX && centerY2 < centerY) {
            return 1;
        }
        if (centerX2 < centerX && centerY2 > centerY) {
            return 4;
        }
        if (centerX2 <= centerX || centerY2 >= centerY) {
            return (centerX2 <= centerX || centerY2 <= centerY) ? 0 : 3;
        } else {
            return 2;
        }
    }

    private void c(int i, int i2) {
        String str = (String) this.z.get(Integer.valueOf(i));
        this.z.put(Integer.valueOf(i), (String) this.z.get(Integer.valueOf(i2)));
        this.z.put(Integer.valueOf(i2), str);
    }

    int a(int i, int i2, int i3, int i4, int i5) {
        MoveDistanceLevel moveDistanceLevel;
        int dimensionPixelSize = this.b.getApplicationContext().getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_smallview_move_thresholdX);
        int dimensionPixelSize2 = this.b.getApplicationContext().getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_smallview_move_thresholdY);
        MoveDistanceLevel moveDistanceLevel2 = MoveDistanceLevel.e_MoveDistance_Min;
        moveDistanceLevel2 = MoveDistanceLevel.e_MoveDistance_Min;
        if (i4 - i2 > dimensionPixelSize) {
            moveDistanceLevel2 = MoveDistanceLevel.e_MoveDistance_Positive;
        } else if (i4 - i2 < (-dimensionPixelSize)) {
            moveDistanceLevel2 = MoveDistanceLevel.e_MoveDistance_Negative;
        } else {
            moveDistanceLevel2 = MoveDistanceLevel.e_MoveDistance_Min;
        }
        if (i5 - i3 > dimensionPixelSize2) {
            moveDistanceLevel = MoveDistanceLevel.e_MoveDistance_Positive;
        } else if (i5 - i3 < (-dimensionPixelSize2)) {
            moveDistanceLevel = MoveDistanceLevel.e_MoveDistance_Negative;
        } else {
            moveDistanceLevel = MoveDistanceLevel.e_MoveDistance_Min;
        }
        dimensionPixelSize2 = h();
        if (dimensionPixelSize2 == 3) {
            if (i == 1) {
                return 3;
            }
            if (i == 2) {
                return 3;
            }
            if (i == 4) {
                return 3;
            }
            if (i == 3) {
                if (moveDistanceLevel2 == MoveDistanceLevel.e_MoveDistance_Negative) {
                    if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Negative) {
                        return 1;
                    }
                    return 4;
                } else if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Negative) {
                    return 2;
                } else {
                    return 3;
                }
            }
        } else if (dimensionPixelSize2 == 2) {
            if (i == 1) {
                return 2;
            }
            if (i == 3) {
                return 2;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 2) {
                if (moveDistanceLevel2 == MoveDistanceLevel.e_MoveDistance_Negative) {
                    if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Positive) {
                        return 4;
                    }
                    return 1;
                } else if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Positive) {
                    return 3;
                } else {
                    return 2;
                }
            }
        } else if (dimensionPixelSize2 == 1) {
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 1;
            }
            if (i == 1) {
                if (moveDistanceLevel2 == MoveDistanceLevel.e_MoveDistance_Positive) {
                    if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Positive) {
                        return 3;
                    }
                    return 2;
                } else if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Positive) {
                    return 4;
                } else {
                    return 1;
                }
            }
        } else if (dimensionPixelSize2 == 4) {
            if (i == 1) {
                return 4;
            }
            if (i == 2) {
                return 4;
            }
            if (i == 3) {
                return 4;
            }
            if (i == 4) {
                if (moveDistanceLevel2 == MoveDistanceLevel.e_MoveDistance_Positive) {
                    if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Negative) {
                        return 2;
                    }
                    return 3;
                } else if (moveDistanceLevel == MoveDistanceLevel.e_MoveDistance_Negative) {
                    return 1;
                } else {
                    return 4;
                }
            }
        }
        return 1;
    }

    void c(int i) {
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_width);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_height);
        int dimensionPixelSize3 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetX);
        int dimensionPixelSize4 = this.b.getResources().getDimensionPixelSize(com.qq.reader.liveshow.a.c.video_small_view_offsetY);
        if (this.f == 0) {
            dimensionPixelSize4 = dimensionPixelSize3;
        }
        Rect bounds = getBounds();
        int i2 = this.h[1].getBounds().left;
        i2 = this.h[1].getBounds().top;
        switch (i) {
            case 2:
                dimensionPixelSize4 = (bounds.width() - dimensionPixelSize3) - dimensionPixelSize;
                return;
            case 3:
                dimensionPixelSize3 = (bounds.width() - dimensionPixelSize3) - dimensionPixelSize;
                dimensionPixelSize4 = (bounds.height() - dimensionPixelSize4) - dimensionPixelSize2;
                return;
            case 4:
                dimensionPixelSize4 = (bounds.height() - dimensionPixelSize4) - dimensionPixelSize2;
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        if (this.c != null) {
            this.c.setSelfId(str + "_" + 1);
        }
    }

    public void a(com.qq.reader.liveshow.avcontrollers.c.a aVar) {
        this.A = aVar;
    }

    public void i() {
        for (int i = 0; i < this.h.length; i++) {
            if (this.h[i] != null) {
                this.h[i].setVisibility(1);
            }
        }
    }
}
