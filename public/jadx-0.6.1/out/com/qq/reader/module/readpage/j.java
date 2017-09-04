package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Process;
import android.text.TextPaint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import format.epub.view.ZLRectNoteArrayList;
import format.epub.view.ZLTextElementAreaArrayList;
import format.epub.view.u;
import java.io.File;

/* compiled from: PagePaintContext */
public abstract class j implements f {
    protected int a;
    protected int b;
    protected float c = 3.0f;
    protected b d;
    protected Bitmap e;
    protected Bitmap f;
    protected Paint g;
    protected TextPaint h;
    protected Context i;
    public u j;
    public i k;
    protected aa l;
    protected o m;
    protected Typeface n;
    protected Typeface o;
    protected u p;
    private int q;
    private int r = 0;
    private boolean s = false;

    public abstract String a(g gVar, g gVar2);

    protected abstract void a(Canvas canvas, PageIndex pageIndex, ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList, n nVar, d dVar, int i);

    public abstract void b(int i);

    public abstract boolean b(int i, int i2);

    public abstract int p();

    public abstract void q();

    public abstract void r();

    public abstract boolean s();

    public void a(u uVar) {
        this.p = uVar;
    }

    public u e() {
        return this.p;
    }

    public j(Context context, b bVar) {
        this.i = context;
        this.g = new Paint(1);
        this.g.setColor(d.l);
        this.h = new TextPaint();
        this.h.setAntiAlias(true);
        this.h.setDither(false);
        File k = ao.k();
        if (k == null || !k.exists()) {
            d.c(ReaderApplication.getApplicationImp(), d.B);
            d.d(ReaderApplication.getApplicationImp(), d.C);
            this.h.setTypeface(Typeface.SANS_SERIF);
        } else {
            try {
                this.h.setTypeface(Typeface.createFromFile(k));
            } catch (Exception e) {
                this.h.setTypeface(Typeface.SANS_SERIF);
            }
        }
        this.n = this.h.getTypeface();
        k = ao.n("36");
        if (k != null && k.exists()) {
            try {
                this.o = Typeface.createFromFile(k);
            } catch (Exception e2) {
            }
        }
        this.h.setColor(WebView.NIGHT_MODE_COLOR);
        this.h.setTextAlign(Align.LEFT);
        this.j = new u(this);
        this.d = bVar;
        this.d.a(this);
        a.i = d.q(this.i);
    }

    public void a(o oVar) {
        this.m = oVar;
    }

    public void a(aa aaVar) {
        this.l = aaVar;
    }

    public void a(i iVar) {
        this.k = iVar;
    }

    public u f() {
        return this.j;
    }

    public void a(int i, int i2) {
        if (!((this.a == i && this.b == i2) || this.f == null)) {
            this.f.recycle();
            this.f = null;
            System.gc();
        }
        this.a = i;
        this.b = i2;
    }

    public void a(int i) {
        h();
        this.g.setColor(i);
    }

    public int g() {
        return this.g == null ? -1 : this.g.getColor();
    }

    public void a(Bitmap bitmap) {
        try {
            h();
            this.e = bitmap.copy(Config.RGB_565, false);
        } catch (Throwable th) {
            af.a(this.i, "好像出问题了，请再试试", 0).a();
            i.a("out_of_memory", false, 0, 0, null, this.i);
            Process.killProcess(Process.myPid());
        }
    }

    public void h() {
        Object obj = 1;
        Object obj2 = null;
        if (this.e != null) {
            this.e.recycle();
            this.e = null;
            obj2 = 1;
        }
        if (this.f != null) {
            this.f.recycle();
            this.f = null;
        } else {
            obj = obj2;
        }
        if (obj != null) {
            System.gc();
        }
    }

    public void a(Canvas canvas, int i) {
        switch (i) {
            case 0:
                try {
                    if (this.e != null) {
                        if (this.f == null) {
                            this.f = Bitmap.createScaledBitmap(this.e, this.a, this.b, false);
                        }
                        canvas.drawBitmap(this.f, 0.0f, 0.0f, this.g);
                        return;
                    } else if (this.g != null) {
                        canvas.drawRect(0.0f, 0.0f, (float) n(), (float) o(), this.g);
                        return;
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    public void a(Canvas canvas, PageIndex pageIndex, ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList) {
        a(canvas, pageIndex, zLTextElementAreaArrayList, zLRectNoteArrayList, null, null, 2);
    }

    public void a(Bitmap bitmap, PageIndex pageIndex, ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList, n nVar, d dVar, int i) {
        Canvas canvas = new Canvas(bitmap);
        a(canvas, u());
        a(canvas, pageIndex, zLTextElementAreaArrayList, zLRectNoteArrayList, nVar, dVar, i);
    }

    public void a(Bitmap bitmap, PageIndex pageIndex) {
        a(new Canvas(bitmap), u());
    }

    public static int i() {
        return ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
    }

    public static int j() {
        return ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
    }

    public static int k() {
        return ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
    }

    public int l() {
        return this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_30);
    }

    public int b() {
        return (this.b - k()) - l();
    }

    public int c() {
        if (this.a == 0) {
            return 0;
        }
        return (this.a - j()) - i();
    }

    public float a() {
        return com.qq.reader.readengine.a.a.a(this.h);
    }

    public float m() {
        return this.h.getTextSize() * g.a(this.i, this.h);
    }

    public int n() {
        return this.a;
    }

    public int o() {
        return this.b;
    }

    public void a(float f) {
        this.h.setTextSize(f);
    }

    public void b(float f) {
        this.h.setTextSize(f);
    }

    public void c(int i) {
    }

    public void d(int i) {
    }

    public boolean c(int i, int i2) {
        return this.j.a(i, i2);
    }

    public boolean t() {
        return false;
    }

    public int u() {
        return this.q;
    }

    public void e(int i) {
        this.q = i;
    }

    public void v() {
        a.i = d.q(this.i);
    }

    public void w() {
        e().a().sendEmptyMessage(10000510);
    }

    public boolean x() {
        return this.s;
    }

    public boolean a(boolean z) {
        this.s = z;
        return z;
    }
}
