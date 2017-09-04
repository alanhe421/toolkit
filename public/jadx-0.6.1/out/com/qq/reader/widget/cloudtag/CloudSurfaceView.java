package com.qq.reader.widget.cloudtag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NativeBookStoreSearchActivity;
import com.qq.reader.common.monitor.i;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import tencent.tls.platform.SigType;

public class CloudSurfaceView extends GLSurfaceView {
    private final float A;
    private ArrayList<Float> B;
    private ArrayList<String> C;
    private int D;
    private Scroller E;
    private boolean F;
    private int G;
    private float H;
    private long I;
    private float J;
    private float K;
    private a L;
    public boolean a;
    public float b;
    final float c;
    final float d;
    final float e;
    final float f;
    int g;
    float h;
    final float i;
    final float j;
    final float k;
    final float l;
    VelocityTracker m;
    float n;
    float o;
    float[] p;
    float[] q;
    private boolean r;
    private final float s;
    private b t;
    private float u;
    private float v;
    private Context w;
    private ArrayList<a> x;
    private ArrayList<a> y;
    private final float z;

    private class a {
        public float a;
        public float b;
        public float c;
        final /* synthetic */ CloudSurfaceView d;

        public a(CloudSurfaceView cloudSurfaceView, double d, double d2, double d3) {
            this.d = cloudSurfaceView;
            this.a = (float) d;
            this.b = (float) d2;
            this.c = (float) d3;
        }

        public float[] a() {
            return new float[]{this.a, this.b, this.c};
        }
    }

    private class b implements Renderer {
        final int[] a = new int[]{-16776961, WebView.NIGHT_MODE_COLOR, -16711936, -65536, -256, -7829368, -16711681};
        final /* synthetic */ CloudSurfaceView b;

        public b(CloudSurfaceView cloudSurfaceView) {
            this.b = cloudSurfaceView;
        }

        public void onDrawFrame(GL10 gl10) {
            gl10.glMatrixMode(5889);
            gl10.glLoadIdentity();
            gl10.glFrustumf(-1056964608, 0.5f, -1.0f, 1.0f, 1.0f, 10.0f);
            gl10.glShadeModel(7425);
            gl10.glClear(16384);
            gl10.glMatrixMode(5888);
            if (!this.b.r) {
                CloudSurfaceView cloudSurfaceView;
                if (Math.abs(this.b.n) > 30.0f) {
                    if (this.b.n < 30.0f) {
                        cloudSurfaceView = this.b;
                        cloudSurfaceView.n += 130.0f;
                    } else {
                        cloudSurfaceView = this.b;
                        cloudSurfaceView.n -= 80.0f;
                    }
                }
                cloudSurfaceView = this.b;
                cloudSurfaceView.b -= this.b.n / 500.0f;
            }
            this.b.b += this.b.h;
            Matrix.setIdentityM(this.b.p, 0);
            Matrix.rotateM(this.b.p, 0, this.b.b, 0.0f, 1.0f, 0.0f);
            Matrix.invertM(this.b.q, 0, this.b.p, 0);
            gl10.glLoadIdentity();
            Iterator it = this.b.x.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                aVar.b(this.b.p);
                gl10.glEnable(3042);
                gl10.glBlendFunc(770, 771);
                gl10.glColor4f(1.0f, 1.0f, 1.0f, aVar.m);
                gl10.glLoadIdentity();
                gl10.glTranslatef(aVar.l[0], aVar.l[1], aVar.l[2]);
                aVar.a(gl10);
                gl10.glDisable(3042);
            }
        }

        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
            gl10.glViewport(0, 0, i, i2);
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            gl10.glDisable(3024);
            gl10.glHint(3152, 4353);
            gl10.glEnable(2912);
            gl10.glFogfv(2918, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0);
            gl10.glFogx(2917, 2049);
            gl10.glFogf(2914, 0.6f);
            gl10.glFogf(2915, 0.9f);
            gl10.glFogf(2916, 100.0f);
            gl10.glEnable(3042);
            gl10.glBlendFunc(770, 771);
            this.b.x = new ArrayList();
            int i = 0;
            while (this.b.C != null && i < this.b.C.size() && this.b.y != null && i < this.b.y.size() && i < this.b.y.size()) {
                a aVar = (a) this.b.y.get(i);
                if (i < this.b.C.size()) {
                    String str = (String) this.b.C.get(i);
                    if (aVar != null && str != null) {
                        double d = (double) aVar.a()[2];
                        this.b.x.add(new a(aVar.a(), a(gl10, str, a(), d), -2.0f, str, null));
                        i++;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        private int a() {
            return this.a[new Random().nextInt(7)];
        }

        public int a(GL10 gl10, String str, int i, double d) {
            int[] iArr = new int[1];
            gl10.glGenTextures(1, iArr, 0);
            int i2 = iArr[0];
            gl10.glBindTexture(3553, i2);
            gl10.glTexParameterf(3553, 10241, 9728.0f);
            gl10.glTexParameterf(3553, 10240, 9729.0f);
            gl10.glTexParameterf(3553, 10242, 10497.0f);
            gl10.glTexParameterf(3553, 10243, 10497.0f);
            Bitmap createBitmap = Bitmap.createBitmap(256, 128, Config.ARGB_4444);
            createBitmap.eraseColor(0);
            Canvas canvas = new Canvas(createBitmap);
            double abs = 36.0d - Math.abs(4.0d * d);
            Paint paint = new Paint();
            paint.setColor(-1);
            if (str.length() > 5) {
                str = str.substring(0, 5);
            }
            paint.setTextSize((float) ((int) abs));
            Rect rect = new Rect();
            paint.getTextBounds(str, 0, str.length(), rect);
            int applyDimension = (int) TypedValue.applyDimension(1, 2.0f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics());
            applyDimension = (int) TypedValue.applyDimension(1, 8.0f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics());
            paint.setStyle(Style.STROKE);
            paint.setColor(-1);
            paint.setStrokeWidth(20.0f);
            paint.setStyle(Style.FILL);
            if (com.qq.reader.common.c.a.bZ < 1.5f) {
                paint.setTextSize((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.search_tool_cloud_tag_textsiez));
            } else {
                paint.setTextSize(36.0f);
            }
            paint.setAntiAlias(true);
            canvas.drawText(str, 0.0f, (float) ((rect.height() / 2) + 64), paint);
            GLUtils.texImage2D(3553, 0, createBitmap, 0);
            createBitmap.recycle();
            return i2;
        }
    }

    public CloudSurfaceView(Context context) {
        super(context);
        this.r = false;
        this.s = 0.8f;
        this.a = true;
        this.b = 0.0f;
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = -2.0f;
        this.A = 0.7f;
        this.B = new ArrayList();
        this.C = new ArrayList();
        this.c = 0.5f;
        this.d = 1.0f;
        this.e = 1.0f;
        this.f = 10.0f;
        this.g = 0;
        this.h = 0.3f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.D = 0;
        this.E = null;
        this.F = false;
        this.G = 0;
        this.H = 0.0f;
        this.m = VelocityTracker.obtain();
        this.p = new float[16];
        this.q = new float[16];
        this.w = context;
    }

    public CloudSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = false;
        this.s = 0.8f;
        this.a = true;
        this.b = 0.0f;
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = -2.0f;
        this.A = 0.7f;
        this.B = new ArrayList();
        this.C = new ArrayList();
        this.c = 0.5f;
        this.d = 1.0f;
        this.e = 1.0f;
        this.f = 10.0f;
        this.g = 0;
        this.h = 0.3f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.D = 0;
        this.E = null;
        this.F = false;
        this.G = 0;
        this.H = 0.0f;
        this.m = VelocityTracker.obtain();
        this.p = new float[16];
        this.q = new float[16];
        this.w = context;
        a();
    }

    public void setTopMargin(float f) {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            ((a) it.next()).n = 1.0f - f;
        }
    }

    public void setBookNames(ArrayList<String> arrayList) {
        this.C = arrayList;
        this.g = this.C.size();
        a();
    }

    private void a() {
        b();
        setZOrderOnTop(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-2);
        this.t = new b(this);
        setRenderer(this.t);
        setRenderMode(1);
        this.D = ViewConfiguration.get(this.w.getApplicationContext()).getScaledMinimumFlingVelocity();
        this.E = new Scroller(getContext());
    }

    private void b() {
        this.y = new ArrayList();
        if (this.g > 0) {
            double toRadians;
            int i = 19833 / (this.g * 34);
            int i2 = 40;
            int i3 = 34;
            while (i2 < 390) {
                int i4 = i3 + 3;
                for (int i5 = -80; i5 < 90; i5 += i4) {
                    toRadians = Math.toRadians((double) i2);
                    double toRadians2 = Math.toRadians((double) i5);
                    toRadians2 = (toRadians2 + ((Math.sqrt(Math.log(Math.random()) * -3.0d) * Math.cos(Math.random() * 6.283185307179586d)) * toRadians2)) / 2.4d;
                    this.y.add(new a(this, (Math.cos(toRadians2) * 0.699999988079071d) * Math.cos(toRadians), (0.699999988079071d * Math.sin(toRadians2)) * 1.4d, (0.699999988079071d * Math.cos(toRadians2)) * Math.sin(toRadians)));
                }
                i2 += i;
                i3 = i4;
            }
            Collections.shuffle(this.y);
            for (int i6 = 1; i6 < this.y.size() - 1; i6++) {
                int i7 = i6 + 1;
                while (i7 < this.y.size()) {
                    a aVar = (a) this.y.get(i6);
                    a aVar2 = (a) this.y.get(i7);
                    double abs = (double) Math.abs(aVar.a - aVar2.a);
                    toRadians = (double) Math.abs(aVar.b - aVar2.b);
                    if (abs < 0.025d && toRadians < 0.12d) {
                        this.y.remove(i7);
                        i7--;
                    }
                    i7++;
                }
            }
            Collections.shuffle(this.y);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!com.qq.reader.common.c.b.k) {
            return true;
        }
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        float floatValue;
        int indexOf;
        switch (motionEvent.getAction()) {
            case 0:
                this.J = x;
                this.K = y;
                this.I = System.currentTimeMillis();
                this.r = true;
                if (this.B != null) {
                    this.B.clear();
                } else {
                    this.B = new ArrayList();
                }
                if (this.L != null) {
                    this.L = null;
                }
                int i = 0;
                while (this.x != null && i < this.x.size()) {
                    float[] a = ((a) this.x.get(i)).a();
                    float[] b = ((a) this.x.get(i)).b(this.p);
                    float[] a2 = c.a(x, y, (float) getWidth(), (float) getHeight(), 0.5f, 1.0f, 1.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f);
                    this.B.add(Float.valueOf(b.a(b, a, a2[0], a2[1], a2[2], a2[3], a2[4], a2[5])));
                    i++;
                }
                ArrayList arrayList = (ArrayList) this.B.clone();
                Collections.sort(arrayList);
                if (arrayList != null && arrayList.size() > 0) {
                    floatValue = ((Float) arrayList.get(0)).floatValue();
                    if (floatValue < 0.7f) {
                        indexOf = this.B.indexOf(Float.valueOf(floatValue));
                        if (indexOf >= 0 && this.x != null && indexOf < this.x.size()) {
                            this.L = (a) this.x.get(indexOf);
                            break;
                        }
                    }
                }
                break;
            case 1:
            case 3:
                this.r = false;
                indexOf = (int) this.n;
                this.m.clear();
                if (com.qq.reader.common.c.b.l && Math.abs(x - this.J) < 10.0f && Math.abs(y - this.K) < 10.0f && System.currentTimeMillis() - this.I < 1000 && this.L != null) {
                    a(this.L.f);
                    break;
                }
            case 2:
                if (com.qq.reader.common.c.b.m) {
                    this.m.addMovement(motionEvent);
                    this.m.computeCurrentVelocity(1000);
                    this.n = this.m.getXVelocity();
                    this.o = this.m.getYVelocity();
                    floatValue = y - this.u;
                    this.b -= (x - this.v) * 0.8f;
                    requestRender();
                    break;
                }
                break;
        }
        this.u = y;
        this.v = x;
        return true;
    }

    private void a(String str) {
        i.a("event_C150", null, ReaderApplication.getApplicationImp());
        Bundle bundle = new Bundle();
        bundle.putString("searchkey", str);
        Intent intent = new Intent();
        intent.setClass(getContext(), NativeBookStoreSearchActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("form", "5");
        intent.setFlags(SigType.TLS);
        ReaderApplication.getApplicationImp().startActivity(intent);
    }
}
