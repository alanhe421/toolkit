package com.qrcomic.screenshot.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import b.a.a.a.a.a.d;
import com.qrcomic.screenshot.c.c;
import com.qrcomic.screenshot.c.f;
import com.qrcomic.screenshot.d.b;
import com.qrcomic.util.g;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.sdk.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QRComicDoodleView extends View implements com.qrcomic.screenshot.b.a.a {
    Bitmap a;
    Bitmap b;
    Canvas c;
    RectF d = new RectF();
    Rect e = new Rect();
    RectF f = new RectF();
    RectF g = new RectF();
    List<com.qrcomic.screenshot.b.a> h = new ArrayList();
    Stack<c> i = new Stack();
    Stack<c> j = new Stack();
    Stack<c> k = new Stack();
    public Matrix l = new Matrix();
    public Matrix m = new Matrix();
    public Matrix n = new Matrix();
    public Matrix o = new Matrix();
    a p;
    Paint q = null;
    public DrawFilter r = new PaintFlagsDrawFilter(0, 7);
    Bitmap s;
    Bitmap t;
    Bitmap u;
    private com.qrcomic.screenshot.b.a v = null;
    private PointF w = new PointF();

    public interface a {
        void a();

        void a(String str);

        void a(boolean z, boolean z2);
    }

    public QRComicDoodleView(Context context) {
        super(context);
        d();
    }

    public QRComicDoodleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    private void d() {
        this.q = new Paint(1);
        this.q.setStrokeJoin(Join.ROUND);
        this.q.setStyle(Style.STROKE);
        this.q.setStrokeCap(Cap.ROUND);
    }

    public void setOnDoodleViewListener(a aVar) {
        this.p = aVar;
    }

    public Bitmap getCtrlBitmap() {
        if (this.s == null || this.s.isRecycled()) {
            this.s = b.a(getContext(), d.bubble_ctrl_icon, com.qrcomic.screenshot.a.b.b, com.qrcomic.screenshot.a.b.b);
        }
        return this.s;
    }

    public Bitmap getCancelBitmap() {
        if (this.t == null || this.t.isRecycled()) {
            this.t = b.a(getContext(), d.bubble_delete_icon, com.qrcomic.screenshot.a.b.b, com.qrcomic.screenshot.a.b.b);
        }
        return this.t;
    }

    public Bitmap getSwitchBitmap() {
        if (this.u == null || this.u.isRecycled()) {
            this.u = b.a(getContext(), d.bubble_switch_icon, com.qrcomic.screenshot.a.b.b, com.qrcomic.screenshot.a.b.b);
        }
        return this.u;
    }

    public void setOriginBitmapAndRect(Bitmap bitmap, RectF rectF) {
        setOriginBitmap(bitmap);
        setGoalRect(rectF);
    }

    public void setOriginBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            if (g.a()) {
                g.a("VipComicDoodleView", g.d, "mOriginBitmap.width = " + bitmap.getWidth() + " , mOriginBitmap.height = " + bitmap.getHeight());
            }
            if (!bitmap.equals(this.a)) {
                if (!(this.a == null || this.a.isRecycled())) {
                    this.a.recycle();
                }
                this.a = bitmap;
            }
        }
    }

    public void setGoalRect(RectF rectF) {
        if (rectF != null && !com.qrcomic.screenshot.d.d.a(this.d, rectF)) {
            this.d.set(rectF);
            this.e.set(Math.round(this.d.left), Math.round(this.d.top), Math.round(this.d.right), Math.round(this.d.bottom));
            e();
        }
    }

    public RectF getGoalRect() {
        return this.d;
    }

    public RectF getScaleGoalRect() {
        return this.f;
    }

    public RectF getRealScaleGoalRect() {
        return this.g;
    }

    public Bitmap getCacheBitmap() {
        if (this.a == null || this.a.isRecycled()) {
            return null;
        }
        if (this.c == null) {
            this.c = new Canvas();
        }
        if (this.b == null || this.b.isRecycled() || this.b.getWidth() != this.a.getWidth() || this.b.getHeight() != this.a.getHeight()) {
            if (!(this.b == null || this.b.isRecycled())) {
                this.b.recycle();
            }
            this.b = b.a(this.a.getWidth(), this.a.getHeight(), Config.RGB_565);
            this.c.setBitmap(this.b);
            this.c.setDrawFilter(this.r);
        }
        this.c.drawBitmap(this.a, 0.0f, 0.0f, this.q);
        if (this.h.size() > 0) {
            for (com.qrcomic.screenshot.b.a b : this.h) {
                b.b(this.c);
            }
        }
        return this.b;
    }

    public boolean a() {
        if (this.i.isEmpty()) {
            return false;
        }
        for (int size = this.i.size() - 1; size >= 0; size--) {
            if (!(this.i.get(size) instanceof com.qrcomic.screenshot.c.g)) {
                return true;
            }
        }
        return false;
    }

    public boolean b() {
        if (this.j.isEmpty()) {
            return false;
        }
        return true;
    }

    public synchronized void a(c cVar) {
        if (cVar != null) {
            while (!this.i.isEmpty() && (this.i.peek() instanceof com.qrcomic.screenshot.c.g)) {
                this.i.pop();
            }
            this.i.push(cVar);
            if (!(cVar instanceof com.qrcomic.screenshot.c.g)) {
                this.j.clear();
            }
            if (this.p != null) {
                this.p.a(a(), b());
            }
        }
    }

    public List<String> getBubbleContentList() {
        if (this.h.size() <= 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (com.qrcomic.screenshot.b.a aVar : this.h) {
            if (aVar instanceof com.qrcomic.screenshot.b.b) {
                CharSequence e = ((com.qrcomic.screenshot.b.b) aVar).e();
                if (!TextUtils.isEmpty(e)) {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    public int getBubbleNum() {
        return this.h.size();
    }

    public String getSaveBitmapResolution() {
        return this.f.width() + v.n + this.f.height();
    }

    private com.qrcomic.screenshot.b.a getCurActiveLayer() {
        com.qrcomic.screenshot.d.d.a(this.h);
        for (int size = this.h.size(); size >= 0; size--) {
            com.qrcomic.screenshot.b.a aVar = (com.qrcomic.screenshot.b.a) this.h.get(size);
            if (aVar.a()) {
                return aVar;
            }
        }
        return null;
    }

    public com.qrcomic.screenshot.b.b getCurActiveBubbleLayer() {
        com.qrcomic.screenshot.d.d.a(this.h);
        for (int size = this.h.size() - 1; size >= 0; size--) {
            com.qrcomic.screenshot.b.a aVar = (com.qrcomic.screenshot.b.a) this.h.get(size);
            if ((aVar instanceof com.qrcomic.screenshot.b.b) && aVar.a()) {
                return (com.qrcomic.screenshot.b.b) aVar;
            }
        }
        return null;
    }

    public void setAllBubbleActiveStatus(boolean z) {
        for (com.qrcomic.screenshot.b.a a : this.h) {
            a.a(z);
        }
        invalidate();
    }

    public boolean a(com.qrcomic.screenshot.b.a aVar) {
        if (aVar != null) {
            return this.h.contains(aVar);
        }
        return false;
    }

    private void e() {
        int width = getWidth();
        int height = getHeight();
        if (width != 0 && height != 0 && !this.d.isEmpty()) {
            float f;
            if (((float) width) / this.d.width() < ((float) height) / this.d.height()) {
                f = (float) width;
                if (f / this.d.width() >= 2.0f) {
                    f = this.d.width() * 2.0f;
                }
                this.f.set(0.0f, 0.0f, f, (this.d.height() * f) / this.d.width());
            } else {
                f = (float) height;
                if (f / this.d.height() >= 2.0f) {
                    f = this.d.height() * 2.0f;
                }
                this.f.set(0.0f, 0.0f, (this.d.width() * f) / this.d.height(), f);
            }
            this.l.setTranslate((((float) width) - this.f.width()) / 2.0f, (((float) height) - this.f.height()) / 2.0f);
            this.l.invert(this.m);
            this.l.mapRect(this.g, this.f);
            this.n.setScale(this.d.width() / this.f.width(), this.d.height() / this.f.height());
            this.n.postTranslate(this.d.left, this.d.top);
            this.n.invert(this.o);
            c();
        }
    }

    public void c() {
        com.qrcomic.screenshot.d.d.a(this.h);
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(-15329770);
        if (this.a != null && !this.e.isEmpty() && !this.g.isEmpty()) {
            canvas.drawBitmap(this.a, this.e, this.g, this.q);
            if (this.h.size() > 0) {
                for (com.qrcomic.screenshot.b.a a : this.h) {
                    a.a(canvas);
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (g.a()) {
            g.a("VipComicDoodleView", g.d, "VipComicDoodleView size change newWidth = " + i + " , newHeight = " + i2 + " , oldWidth = " + i3 + " , oldHeight = " + i4);
        }
        e();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.h != null) {
            boolean z;
            if ((motionEvent.getAction() & 255) == 0) {
                this.w.set(motionEvent.getX(), motionEvent.getY());
                this.v = null;
                for (int size = this.h.size() - 1; size >= 0; size--) {
                    if (((com.qrcomic.screenshot.b.a) this.h.get(size)).a(motionEvent)) {
                        this.v = (com.qrcomic.screenshot.b.a) this.h.get(size);
                        z = true;
                        break;
                    }
                }
                z = false;
            } else {
                if (this.v != null) {
                    z = this.v.a(motionEvent);
                }
                z = false;
            }
            if (!z && (motionEvent.getAction() & 255) == 1 && Math.sqrt(Math.pow((double) (this.w.x - motionEvent.getX()), 2.0d) + Math.pow((double) (this.w.y - motionEvent.getY()), 2.0d)) < ((double) com.qrcomic.screenshot.a.b.a) && this.p != null) {
                this.p.a(Constants.VIA_SHARE_TYPE_INFO);
                this.p.a();
            }
            invalidate();
        }
        return true;
    }

    public void a(com.qrcomic.screenshot.b.a aVar, boolean z) {
        if (z && aVar != null && this.h.size() > 0) {
            if (this.p != null) {
                this.p.a("7");
            }
            if (this.h.remove(aVar)) {
                if (!this.k.isEmpty()) {
                    c cVar = (c) this.k.peek();
                    if (cVar instanceof f) {
                        f fVar = (f) cVar;
                        if (aVar.equals(fVar.b())) {
                            if (fVar.a() != null) {
                                aVar.a(false);
                            } else {
                                this.k.pop();
                            }
                        } else if (g.a()) {
                            g.a("VipComicDoodleView", g.d, "删除临时命令时,图层对象没有对应上,重大bug!!!");
                        }
                    }
                }
                if (this.p != null) {
                    this.p.a();
                }
                aVar.c();
            } else if (g.a()) {
                g.a("VipComicDoodleView", g.d, "deleteLayer  baseLayer " + aVar + " not in mLayerList");
            }
            c();
        }
    }

    public void b(com.qrcomic.screenshot.b.a aVar, boolean z) {
        if (z && aVar != null) {
            if (aVar instanceof com.qrcomic.screenshot.b.b) {
                ((com.qrcomic.screenshot.b.b) aVar).f();
            }
            c();
        }
    }

    public void d(com.qrcomic.screenshot.b.a aVar, boolean z) {
        if (aVar != null) {
            synchronized (this) {
                if (z) {
                    this.k.push(com.qrcomic.screenshot.c.d.a().a(aVar, true));
                } else if (!this.k.isEmpty()) {
                    c cVar = (c) this.k.pop();
                    if (cVar != null && (cVar instanceof f)) {
                        cVar = (f) cVar;
                        if (aVar.equals(cVar.b())) {
                            cVar.a(cVar.b().d());
                            a(cVar);
                        } else if (g.a()) {
                            g.a("VipComicDoodleView", g.d, "把临时命令转换为正式命令时,图层对象没有对应上,重大bug");
                        }
                    }
                }
            }
        }
    }

    public void c(com.qrcomic.screenshot.b.a aVar, boolean z) {
    }

    public void e(com.qrcomic.screenshot.b.a aVar, boolean z) {
    }

    public void f(com.qrcomic.screenshot.b.a aVar, boolean z) {
    }

    public void g(com.qrcomic.screenshot.b.a aVar, boolean z) {
    }
}
