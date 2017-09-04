package com.qq.reader.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class WavingLinesView extends View {
    protected List<a> a = new ArrayList();
    protected Paint b;
    List<b> c;
    List<b> d;
    b e;
    b f;
    a g;
    private boolean h = false;
    private Path i;
    private boolean j;

    public static class a {
        Path a = new Path();
        int b;
        List<b> c = new ArrayList();
        List<b> d = new ArrayList();
        List<b> e = new ArrayList();
        List<b> f = new ArrayList();
        b g;
        b h;
        int i;

        public void a() {
            int i = 0;
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                this.g = (b) this.e.get(i2);
                this.h = (b) this.c.get(i2);
                a(this.g, this.h, 1.0d);
            }
            while (i < this.f.size()) {
                a((b) this.f.get(i), (b) this.d.get(i), 1.0d);
                i++;
            }
        }

        private void a(b bVar, b bVar2, double d) {
            this.i = (int) Math.round(((double) bVar.a.x) + (((double) bVar.b) * d));
            if (this.i >= bVar2.a.x + bVar.c || this.i <= bVar2.a.x - bVar.c) {
                this.i = bVar2.a.x;
            }
            bVar.a.x = this.i;
        }

        public void a(List<b> list, List<b> list2) {
            for (b bVar : list) {
                this.c.add(bVar.a());
                this.e.add(bVar.a());
            }
            for (b bVar2 : list2) {
                this.d.add(bVar2.a());
                this.f.add(bVar2.a());
            }
        }
    }

    public static class b {
        Point a;
        float b;
        int c;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public b a() {
            b bVar = new b();
            bVar.a = new Point(this.a.x, this.a.y);
            bVar.b = this.b;
            bVar.c = this.c;
            return bVar;
        }
    }

    public WavingLinesView(Context context) {
        super(context);
        a();
    }

    protected void a() {
        this.b = new Paint();
        this.b.setStyle(Style.FILL);
        this.b.setStrokeWidth(2.0f);
    }

    public WavingLinesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        c();
        b();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    protected void a(Canvas canvas) {
        for (int i = 0; i < this.a.size(); i++) {
            this.g = (a) this.a.get(i);
            this.c = this.g.f;
            this.d = this.g.e;
            this.i = this.g.a;
            this.i.reset();
            this.j = false;
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                this.e = (b) this.c.get(i2);
                this.f = null;
                if (i2 == 0) {
                    this.i.moveTo((float) this.e.a.x, (float) this.e.a.y);
                } else {
                    if (i2 - 1 < this.d.size()) {
                        this.f = (b) this.d.get(i2 - 1);
                    }
                    if (this.f != null) {
                        if (!this.j) {
                            this.i.quadTo((float) this.f.a.x, (float) this.f.a.y, (float) this.e.a.x, (float) this.e.a.y);
                        }
                        if (this.e.a.x > getWidth()) {
                            this.j = true;
                        }
                    } else {
                        try {
                            this.i.lineTo((float) this.e.a.x, (float) this.e.a.y);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
            this.i.lineTo((float) getWidth(), (float) getHeight());
            this.i.lineTo(0.0f, (float) getHeight());
            this.i.close();
            this.b.setColor(this.g.b);
            canvas.drawPath(this.i, this.b);
            this.g.a();
        }
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        if (this.h) {
            postInvalidateDelayed(20);
        }
    }

    private void c() {
        this.a.clear();
    }

    protected void b() {
        this.a.add(a(0.75f, -0.4f, 0.003f, getResources().getColor(R.color.skin_set_bookshelf_wave_color_top), 0.3f, 0.7f));
        this.a.add(a(0.75f, 0.0f, 0.0025f, getResources().getColor(R.color.skin_set_bookshelf_wave_color_bottom), 0.15f, 0.7f));
    }

    protected a a(float f, float f2, float f3, int i, float f4, float f5) {
        float height = (float) getHeight();
        float min = Math.min(2048.0f * f, ((float) getWidth()) / f);
        float f6 = (0.0f - min) + (f2 * min);
        int i2 = (int) (f5 * height);
        int round = ((Math.round(f) * 2) + 1) + 3;
        int i3 = round - 1;
        float f7 = f4 * height;
        List arrayList = new ArrayList();
        for (int i4 = 0; i4 < round; i4++) {
            b bVar = new b();
            bVar.b = f3 * min;
            bVar.c = (int) min;
            bVar.a = new Point((int) ((((float) i4) * ((1.0f * min) / 2.0f)) + f6), i2);
            arrayList.add(bVar);
        }
        List arrayList2 = new ArrayList();
        for (round = 0; round < i3; round++) {
            b bVar2 = new b();
            bVar2.b = f3 * min;
            bVar2.c = (int) min;
            bVar2.a = new Point((int) ((((float) ((round * 2) + 1)) * ((1.0f * min) / 4.0f)) + f6), (int) ((round % 2 == 0 ? f7 : -f7) + ((float) i2)));
            arrayList2.add(bVar2);
        }
        a aVar = new a();
        aVar.a(arrayList2, arrayList);
        aVar.b = i;
        return aVar;
    }
}
