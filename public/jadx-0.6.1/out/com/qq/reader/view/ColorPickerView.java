package com.qq.reader.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.List;

public class ColorPickerView extends View {
    private static float F = 23.0f;
    public static int a = 19;
    private static int j = 0;
    private static int k = 0;
    private static int l = 0;
    private static float m = 14.0f;
    private int[] A;
    private b B;
    private c C;
    private int D = -1;
    private int E = -1;
    private Bitmap G;
    private Paint H;
    private Paint I;
    private Bitmap J;
    private int K;
    private int L;
    private int M;
    private int N = 0;
    private float O = 0.0f;
    private float P = 0.0f;
    boolean b = false;
    boolean c = false;
    Context d;
    private Paint e;
    private int f = 300;
    private int g = Opcodes.LONG_TO_INT;
    private int h = -1;
    private int i = -1;
    private float n = ((float) (j + 244));
    private final int[] o = new int[]{-65536, -256, -16711936, -16711681, -16776961, -65281, -65536};
    private List<Paint> p = new ArrayList();
    private List<int[]> q = new ArrayList();
    private volatile Paint r;
    private Paint s;
    private a t;
    private a u;
    private a v;
    private a w;
    private a x;
    private a y;
    private int z;

    public interface b {
        void a(int i, boolean z);
    }

    public interface c {
        void b(int i, int i2);
    }

    private class a {
        final /* synthetic */ ColorPickerView a;
        private float b;
        private float c;
        private int d;
        private int e;
        private int f;
        private ArrayList<Bitmap> g = new ArrayList(3);
        private final float h = 10.0f;

        public a(ColorPickerView colorPickerView, int i, float f, float f2, int i2) {
            this.a = colorPickerView;
            Resources resources = colorPickerView.getResources();
            this.d = i;
            this.b = f;
            this.c = f2;
            switch (this.d) {
                case 0:
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_unselected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_selected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_pressed));
                    return;
                case 1:
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_unselected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_selected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_pressed));
                    return;
                case 2:
                    if (i2 == 0) {
                        this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_unselected));
                        this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_selected));
                        this.g.add(BitmapFactory.decodeResource(resources, R.drawable.text_setting_pressed));
                        return;
                    }
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_unselected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_selected));
                    this.g.add(BitmapFactory.decodeResource(resources, R.drawable.bg_setting_pressed));
                    return;
                default:
                    return;
            }
        }

        public int a() {
            return this.d;
        }

        public float b() {
            return this.b;
        }

        public void a(float f) {
            this.b = f;
        }

        public float c() {
            return this.c;
        }

        public void b(float f) {
            if (this.d != 2) {
                this.c = f;
            }
        }

        public void a(int i) {
            this.e = i;
        }

        public int d() {
            return this.e;
        }

        public int e() {
            return this.f;
        }

        public void b(int i) {
            this.f = i;
        }

        private Bitmap h() {
            return (Bitmap) this.g.get(this.e);
        }

        public void a(Canvas canvas) {
            Bitmap h = h();
            canvas.drawBitmap(h, this.b - ((float) (h.getWidth() / 2)), this.c - ((float) (h.getHeight() / 2)), null);
        }

        public void a(Canvas canvas, int i) {
            Bitmap h = h();
            canvas.drawBitmap(h, this.b - ((float) (h.getWidth() / 2)), (this.c + ((float) i)) - ((float) (h.getHeight() / 2)), null);
        }

        public boolean a(float f, float f2) {
            Bitmap h = h();
            if (f >= (this.b - ((float) (h.getWidth() / 2))) - 10.0f && f < (this.b + ((float) (h.getWidth() / 2))) + 10.0f && f2 >= (this.c - ((float) (h.getHeight() / 2))) - 10.0f) {
                if (f2 < (((float) (h.getHeight() / 2)) + this.c) + 10.0f) {
                    return true;
                }
            }
            return false;
        }

        public float f() {
            return (float) h().getWidth();
        }

        public float g() {
            return (float) h().getHeight();
        }
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.text_setting_unselected);
        a = this.d.getResources().getDimensionPixelOffset(R.dimen.common_dp_16);
        m = ((float) decodeResource.getWidth()) * 0.25f;
        F = (float) Math.round(((float) decodeResource.getWidth()) * 0.4f);
        this.I = new Paint();
        this.I.setColor(-1644826);
        this.K = this.d.getResources().getDimensionPixelSize(R.dimen.common_dp_3);
        this.L = this.d.getResources().getDimensionPixelSize(R.dimen.common_dp_1);
        this.I.setStrokeWidth((float) this.K);
        this.M = this.d.getResources().getDimensionPixelSize(R.dimen.common_dp_50);
        j = ((int) (((float) this.M) - m)) / 2;
        k = a << 1;
        this.H = new TextPaint(1);
        this.H.setDither(false);
        this.H.setTextSize((float) this.d.getResources().getDimensionPixelOffset(R.dimen.common_dp_14));
        this.H.setColor(this.d.getResources().getColor(R.color.text_color_c301));
        this.H.setTextAlign(Align.CENTER);
    }

    private void c() {
        for (int i = this.g / 1; i > 0; i--) {
            this.e = new Paint(1);
            this.e.setStyle(Style.STROKE);
            this.e.setStrokeWidth(1.5f);
            Object a = a(this.o, i, this.g / 1);
            this.q.add(a);
            this.e.setShader(new LinearGradient((float) a, (float) a, (float) (a + this.f), (float) a, a, null, TileMode.REPEAT));
            this.p.add(this.e);
        }
        this.r = new Paint(1);
        this.r.setStyle(Style.STROKE);
        this.r.setColor(-1);
        this.r.setStrokeWidth(m);
        this.s = new Paint(1);
        this.s.setStyle(Style.FILL);
    }

    public void a(int i, int i2, boolean z) {
        this.u.b(i);
        this.v.b(i2);
        float[] a = a(i);
        this.u.a(a[0]);
        this.u.b(a[1]);
        this.x.a(a[2]);
        a = a(i2);
        this.v.a(a[0]);
        this.v.b(a[1]);
        this.y.a(a[2]);
        if (z) {
            this.t = this.u;
            this.w = this.x;
        } else {
            this.t = this.v;
            this.w = this.y;
        }
        this.t.a(1);
        this.w.b(this.t.e());
        this.w.a(this.t.d());
        d();
    }

    private synchronized void d() {
        float[] fArr = new float[3];
        ao.a(this.t.e(), fArr);
        fArr[1] = 1.0f;
        int a = ao.a(fArr);
        fArr[1] = 0.0f;
        int a2 = ao.a(fArr);
        this.A = new int[]{a, a2};
        this.r.setShader(new LinearGradient((float) a, (float) a, (float) (((a + this.f) + this.K) + 2), (float) a, this.A, null, TileMode.REPEAT));
    }

    private final int[] a(int[] iArr, int i, int i2) {
        int[] iArr2 = new int[iArr.length];
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            r3 = new float[3];
            ao.a(iArr[i3], r3);
            r3[2] = ((float) i) / ((float) i2);
            iArr2[i3] = ao.a(r3);
        }
        return iArr2;
    }

    private int a(float f, float f2, a aVar) {
        if (aVar.a() != 2) {
            return a((int[]) this.q.get(Math.min(Math.max(0, ((int) b(f2, false)) / 1), this.q.size() - 1)), a(f, false) / ((float) this.f));
        }
        return a(this.A, a(f, false) / ((float) this.f));
    }

    private float[] a(int i) {
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        ao.a(i, fArr2);
        float f = fArr2[0];
        float f2 = fArr2[1];
        float f3 = fArr2[2];
        fArr[0] = a((f / 360.0f) * ((float) this.f), true);
        fArr[1] = b(((float) this.g) - (f3 * ((float) this.g)), true);
        fArr[2] = a(((float) this.f) - (((float) this.f) * f2), true);
        return fArr;
    }

    public void draw(Canvas canvas) {
        onDraw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        this.I.setColor(-1644826);
        this.I.setStyle(Style.FILL);
        canvas.drawRect((float) a, 0.0f, (float) (this.i - a), (float) ((this.K * 2) + this.g), this.I);
        if (this.G != null) {
            canvas.drawBitmap(this.G, a(0.0f, true) + ((float) this.K), (float) this.K, null);
        } else {
            a(canvas, a(0.0f, true) + ((float) this.K), (float) this.K);
        }
        this.u.a(canvas);
        this.v.a(canvas);
        this.I.setStrokeWidth(1.0f);
        this.I.setStyle(Style.STROKE);
        int dimensionPixelOffset = this.d.getResources().getDimensionPixelOffset(R.dimen.common_dp_12);
        int i = ((int) (((float) dimensionPixelOffset) - m)) / 2;
        int b = (b(dimensionPixelOffset) + (this.K * 2)) + this.g;
        canvas.drawRect(a(0.0f, true), (float) b, (float) (((a + this.f) + (this.K * 2)) + 2), (float) (b + dimensionPixelOffset), this.I);
        this.I.setStyle(Style.FILL);
        this.I.setColor(-1);
        canvas.drawRect(a(0.0f, true) + 1.0f, (float) (b + 1), (float) (((a + this.f) + (this.K * 2)) + 1), (float) ((b + dimensionPixelOffset) - 1), this.I);
        canvas.drawLine((a(0.0f, true) + ((float) this.K)) + 1.0f, ((((float) b) + (m / 2.0f)) + ((float) i)) + 1.0f, (float) (((a + this.f) + this.K) + 1), ((((float) b) + (m / 2.0f)) + ((float) i)) + 1.0f, this.r);
        this.w.a(canvas, (b(dimensionPixelOffset) + i) / 2);
    }

    private void a(Canvas canvas, float f, float f2) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            float f3 = f2 + ((float) (size * 1));
            float f4 = f + ((float) this.f);
            float f5 = f2 + ((float) (size * 1));
            canvas.drawLine(f, f3, f4, f5, (Paint) this.p.get(size));
        }
    }

    private int b(int i) {
        return (((getHeight() - this.g) - (this.K * 2)) - i) / 2;
    }

    public void a() {
        if (this.G != null) {
            this.G.recycle();
            this.G = null;
        }
        if (this.J != null) {
            this.J.recycle();
            this.J = null;
        }
    }

    protected void onMeasure(int i, int i2) {
        if (this.i == -1 || this.h == -1) {
            this.f = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i2);
            if (mode == Integer.MIN_VALUE) {
                this.g = (size >> 1) + l;
                size = this.g + this.M;
            } else if (mode == 1073741824) {
                this.g = size - this.M;
            }
            this.i = this.f;
            this.h = size;
            setMeasuredDimension(this.f, size);
            this.f -= (a + this.K) * 2;
            this.g -= this.K * 2;
            this.n = (float) (this.g + j);
            if (this.u == null) {
                this.u = new a(this, 0, 0.0f, 0.0f, 0);
                this.v = new a(this, 1, 0.0f, 0.0f, 1);
                this.x = new a(this, 2, 0.0f, this.n, 0);
                this.y = new a(this, 2, 0.0f, this.n, 1);
                c();
                this.D = d.k;
                this.E = d.l;
                a(this.D, this.E, false);
            }
            if (this.G == null) {
                this.G = Bitmap.createBitmap(this.f, this.g, Config.ARGB_8888);
                Canvas canvas = new Canvas();
                canvas.setBitmap(this.G);
                a(canvas, 0.0f, 0.0f);
                return;
            }
            return;
        }
        setMeasuredDimension(this.i, this.h);
    }

    private int a(int i, int i2, float f) {
        return Math.round(((float) (i2 - i)) * f) + i;
    }

    private final int a(int[] iArr, float f) {
        if (f <= 0.0f) {
            return iArr[0];
        }
        if (f >= 1.0f) {
            return iArr[iArr.length - 1];
        }
        float length = ((float) (iArr.length - 1)) * f;
        int i = (int) length;
        length -= (float) i;
        int i2 = iArr[i];
        i = iArr[i + 1];
        return Color.argb(a(Color.alpha(i2), Color.alpha(i), length), a(Color.red(i2), Color.red(i), length), a(Color.green(i2), Color.green(i), length), a(Color.blue(i2), Color.blue(i), length));
    }

    private boolean a(float f, float f2) {
        if (this.u.a(f, f2)) {
            this.t = this.u;
            this.v.a(0);
            this.w = this.x;
        } else if (this.v.a(f, f2)) {
            this.t = this.v;
            this.u.a(0);
            this.w = this.y;
        } else if (!this.w.a(f, f2)) {
            return false;
        } else {
            this.t = this.w;
        }
        this.t.a(1);
        return true;
    }

    private boolean b(float f, float f2) {
        if (this.z == -1) {
            return false;
        }
        if (this.u.a(f, f2) && this.z == 0) {
            return true;
        }
        if (this.v.a(f, f2) && this.z == 1) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float b;
        int i;
        switch (motionEvent.getAction()) {
            case 0:
                this.N = 0;
                this.z = -1;
                this.b = a(x, y);
                this.O = motionEvent.getX();
                this.P = motionEvent.getY();
                if (!this.b) {
                    if (e(x, y)) {
                        this.t = this.w;
                    } else if (f(x, y)) {
                        if (this.u.e == 1) {
                            this.t = this.u;
                            this.w = this.x;
                        } else if (this.v.e == 1) {
                            this.t = this.v;
                            this.w = this.y;
                        }
                    }
                    b = this.t.b();
                    i = (int) (b - this.O);
                    int c = (int) (this.t.c() - this.P);
                    int scaledTouchSlop = ViewConfiguration.get(this.d.getApplicationContext()).getScaledTouchSlop();
                    if (Math.abs(i) >= scaledTouchSlop || Math.abs(c) >= scaledTouchSlop) {
                        c(x, y);
                        invalidate();
                        this.c = true;
                        break;
                    }
                }
                e();
                invalidate();
                break;
            case 1:
            case 3:
                this.N = 0;
                this.z = -1;
                if (this.b) {
                    this.t.a(1);
                    invalidate();
                    this.b = false;
                } else if (this.t.d() == 2) {
                    this.t.a(1);
                    invalidate();
                }
                this.c = false;
                break;
            case 2:
                if (!this.b && !this.c) {
                    this.b = b(x, y);
                    break;
                }
                b = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (this.N == 1) {
                    if (d(x, y) && g(x, y)) {
                        c(x, y);
                        this.t.a(2);
                        invalidate();
                        break;
                    }
                }
                int i2 = (int) (b - this.O);
                int i3 = (int) (y2 - this.P);
                i = ViewConfiguration.get(this.d.getApplicationContext()).getScaledTouchSlop();
                if (Math.abs(i2) >= i || Math.abs(i3) >= i) {
                    this.N = 1;
                    break;
                }
                break;
        }
        return true;
    }

    private void c(float f, float f2) {
        this.t.b(a(a(f), b(f2), this.t));
        e();
        this.t.a(a(f));
        this.t.b(b(f2));
        f();
    }

    private void e() {
        if (this.t.a() != 2) {
            d();
            this.w.b(a(this.w.b(), this.w.c(), this.w));
        }
    }

    private synchronized void f() {
        if (this.u.d() == 1 || this.u.d() == 2) {
            this.D = this.w.e();
            this.B.a(this.D, true);
        } else {
            this.E = this.w.e();
            this.B.a(this.E, false);
        }
    }

    private boolean d(float f, float f2) {
        boolean z = true;
        if (this.t.a() == 2) {
            if (f < 0.0f || f > ((float) getMeasuredWidth())) {
                z = false;
            }
        } else if (f <= 0.0f || f >= ((float) getMeasuredWidth()) || f2 <= 0.0f || f2 >= ((float) ((this.g + a) + l))) {
            z = false;
        }
        return !z ? z : z;
    }

    private boolean e(float f, float f2) {
        return f2 > this.n - ((float) j) && f2 <= (this.n + m) + ((float) k);
    }

    private boolean f(float f, float f2) {
        return f2 > 0.0f && f2 < ((float) ((this.g + a) + l));
    }

    private float a(float f) {
        if (f > a((float) getMeasuredWidth(), false)) {
            return a((float) getMeasuredWidth(), false);
        }
        if (f < ((float) a)) {
            return (float) a;
        }
        return f;
    }

    private float b(float f) {
        if (f > ((float) (this.g + a))) {
            return (float) (this.g + a);
        }
        if (f < ((float) l)) {
            return (float) l;
        }
        return f;
    }

    private float a(float f, boolean z) {
        if (z) {
            return ((float) a) + f;
        }
        return f - ((float) a);
    }

    private float b(float f, boolean z) {
        if (z) {
            return ((float) l) + f;
        }
        return f - ((float) l);
    }

    private boolean g(float f, float f2) {
        float a = a(f);
        float b = b(f2);
        if (this.t.a() == 2) {
            return true;
        }
        float b2;
        float c;
        if (this.t.a() == 0) {
            b2 = this.v.b();
            c = this.v.c();
        } else {
            b2 = this.u.b();
            c = this.u.c();
        }
        float f3 = ((this.u.f() / 2.0f) + (this.v.f() / 2.0f)) - F;
        float g = ((this.u.g() / 2.0f) + (this.v.g() / 2.0f)) - F;
        b2 = Math.abs(a - b2);
        c = Math.abs(b - c);
        if (b2 >= Math.abs(f3) || c >= Math.abs(g)) {
            return true;
        }
        this.b = false;
        this.c = false;
        this.z = this.t.a();
        return false;
    }

    public void setOnColorChangeListener(b bVar) {
        this.B = bVar;
    }

    public void a(c cVar) {
        this.C = cVar;
    }

    public void b() {
        this.C.b(this.D, this.E);
    }
}
