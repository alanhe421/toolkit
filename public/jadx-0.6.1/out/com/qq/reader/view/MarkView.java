package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.tencent.feedback.proguard.R;
import format.epub.view.ZLTextElementAreaArrayList;
import format.epub.view.h;
import java.util.ArrayList;
import java.util.List;

public class MarkView extends View {
    private float A = 3.0f;
    private boolean B = false;
    private boolean C = false;
    public List<a> a = new ArrayList();
    Paint b;
    Paint c;
    Paint d;
    boolean e;
    boolean f;
    Drawable g;
    Drawable h;
    float i;
    float j;
    float k;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    float r;
    float s;
    float t;
    float u;
    float v;
    private final float w = 4.0f;
    private int x;
    private final int y = 1275954943;
    private final int z = 3;

    private class a {
        float a;
        float b;
        float c;
        float d;
        final /* synthetic */ MarkView e;

        public a(MarkView markView, float f, float f2, float f3, float f4) {
            this.e = markView;
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
        }
    }

    public MarkView(Context context, boolean z, boolean z2, int i) {
        super(context);
        this.x = i;
        this.b = new Paint();
        this.d = new Paint();
        this.c = new Paint();
        if (this.x == 1) {
            this.b.setColor(context.getResources().getColor(R.color.note_paint_color));
        } else {
            this.b.setColor(1275954943);
        }
        this.d.setColor(context.getResources().getColor(R.color.note_line_color));
        this.d.setStyle(Style.FILL);
        this.d.setStrokeWidth(4.0f);
        this.c.setStyle(Style.STROKE);
        this.c.setColor(context.getResources().getColor(R.color.note_remark_color));
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(4.0f);
        this.e = z;
        this.f = z2;
        this.g = getResources().getDrawable(R.drawable.note_tag);
        this.h = getResources().getDrawable(R.drawable.select_arrow_line);
    }

    public boolean a() {
        return this.f;
    }

    public void b() {
        this.a.clear();
    }

    public float getLastStartUpdateY() {
        return this.l;
    }

    public float getLastEndUpdateY() {
        return this.n;
    }

    public void a(float f, float f2, float f3, float f4, ZLTextElementAreaArrayList zLTextElementAreaArrayList) {
        float f5 = -1.0f;
        this.a.clear();
        if (zLTextElementAreaArrayList != null) {
            this.k = f;
            this.l = f2;
            this.m = f3;
            this.n = f4;
            h hVar = null;
            int i = 0;
            Object obj = null;
            float f6 = -1.0f;
            while (i < zLTextElementAreaArrayList.size()) {
                Object obj2;
                h hVar2 = (h) zLTextElementAreaArrayList.get(i);
                if (obj != null || hVar2.c < this.l || hVar2.a < this.k) {
                    if (obj == null || hVar2.c == f5) {
                        if (obj != null && hVar2.d == this.n && hVar2.b > this.m) {
                            if (hVar != null) {
                                this.a.add(new a(this, f6, f5, hVar.b, hVar.d));
                                return;
                            }
                            return;
                        }
                    } else if (hVar != null) {
                        this.a.add(new a(this, f6, f5, hVar.b, hVar.d));
                        f5 = hVar2.c;
                        f6 = hVar2.a;
                        this.s = f6;
                        this.t = f5;
                        this.u = hVar2.b;
                        this.v = f5;
                        obj2 = obj;
                    }
                    obj2 = obj;
                } else {
                    f5 = hVar2.c;
                    f6 = hVar2.a;
                    this.o = f6;
                    this.p = f5;
                    this.q = hVar2.b;
                    this.r = f5;
                    this.s = f6;
                    this.t = f5;
                    this.u = hVar2.b;
                    this.v = f5;
                    obj2 = 1;
                }
                if (obj2 != null && hVar2.d == this.n && hVar2.b == this.m) {
                    this.a.add(new a(this, f6, f5, hVar2.b, hVar2.d));
                    return;
                } else if (obj2 != null && i == zLTextElementAreaArrayList.size() - 1) {
                    this.a.add(new a(this, f6, f5, hVar2.b, hVar2.d));
                    return;
                } else if (hVar2.c <= this.n) {
                    h hVar3 = (h) zLTextElementAreaArrayList.get(i);
                    if (this.p == hVar3.c && this.q < hVar3.b) {
                        this.r = hVar3.c;
                        this.q = hVar3.b;
                    }
                    if (this.t == hVar3.c && this.u < hVar3.b) {
                        this.v = hVar3.c;
                        this.u = hVar3.b;
                    }
                    i++;
                    obj = obj2;
                    hVar = hVar3;
                } else {
                    return;
                }
            }
        }
    }

    public void setIsOutOfScreen(int i, boolean z) {
        if (i == 0) {
            this.B = z;
        } else {
            this.C = z;
        }
    }

    public void a(Canvas canvas, float f, float f2, float f3, float f4, float f5) {
        if (this.a.size() > 0) {
            int i = 0;
            while (i < this.a.size()) {
                a aVar = (a) this.a.get(i);
                if (i == 0) {
                    this.i = aVar.b;
                }
                if (i == this.a.size() - 1) {
                    this.j = aVar.d;
                }
                if (this.e) {
                    Canvas canvas2 = canvas;
                    canvas2.drawRect(aVar.a, Math.max(aVar.b, f), aVar.c, Math.min(f2, (aVar.d - f5) - this.A), this.b);
                } else if (aVar.d - f5 < f2) {
                    float f6 = aVar.d - f5;
                    canvas.drawRect(aVar.a, f6 - this.A, aVar.c, f6, this.d);
                }
                if (this.f && i == this.a.size() - 1) {
                    float f7 = aVar.c;
                    float intrinsicHeight = aVar.d - ((float) (this.g.getIntrinsicHeight() / 2));
                    int intrinsicHeight2 = (int) (((float) this.g.getIntrinsicHeight()) + intrinsicHeight);
                    float intrinsicWidth = ((float) this.g.getIntrinsicWidth()) + f7;
                    if (f4 > 0.0f && intrinsicWidth > f4) {
                        f7 = f4 - ((float) this.g.getIntrinsicWidth());
                        intrinsicWidth = f4;
                    }
                    this.g.setBounds((int) f7, (int) intrinsicHeight, (int) intrinsicWidth, intrinsicHeight2);
                    this.g.draw(canvas);
                }
                i++;
            }
        }
    }

    public Rect getNoteTagBound() {
        if (this.g != null) {
            return this.g.getBounds();
        }
        return null;
    }

    public PointF getFirstLineStartPoint() {
        return new PointF(this.o, this.p);
    }

    public PointF getFirstLineEndPoint() {
        return new PointF(this.q, this.r);
    }

    public PointF getLastLineStartPoint() {
        return new PointF(this.s, this.t);
    }

    public PointF getLastLineEndPoint() {
        return new PointF(this.u, this.v);
    }

    public boolean a(float f, float f2) {
        if (this.a == null) {
            return false;
        }
        for (a aVar : this.a) {
            if (f >= aVar.a && f <= aVar.c && f2 >= aVar.b && f2 <= aVar.d) {
                return true;
            }
        }
        return false;
    }

    public float getStartY() {
        return this.i;
    }

    public float getEndY() {
        return this.j;
    }
}
