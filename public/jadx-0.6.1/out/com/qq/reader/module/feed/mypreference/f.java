package com.qq.reader.module.feed.mypreference;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;

/* compiled from: TextDrawable */
public class f extends ShapeDrawable {
    private final Paint a;
    private final Paint b;
    private final String c;
    private final int d;
    private final RectShape e;
    private final int f;
    private final int g;
    private final int h;
    private final float i;
    private final int j;

    /* compiled from: TextDrawable */
    public interface b {
    }

    /* compiled from: TextDrawable */
    public interface c {
        c a();

        c a(int i);

        c b(int i);

        c c(int i);

        d c();

        c d(int i);
    }

    /* compiled from: TextDrawable */
    public interface d {
        f a(String str, int i);

        f a(String str, int i, int i2);

        c b();
    }

    /* compiled from: TextDrawable */
    public static class a implements b, c, d {
        public int a;
        public float b;
        private String c;
        private int d;
        private int e;
        private int f;
        private int g;
        private Typeface h;
        private RectShape i;
        private int j;
        private boolean k;
        private boolean l;

        private a() {
            this.c = "";
            this.d = -7829368;
            this.a = -1;
            this.e = 0;
            this.f = -1;
            this.g = -1;
            this.i = new RectShape();
            this.h = Typeface.create("sans-serif-light", 0);
            this.j = -1;
            this.k = false;
            this.l = false;
        }

        public c a(int i) {
            this.f = i;
            return this;
        }

        public c b(int i) {
            this.g = i;
            return this;
        }

        public c c(int i) {
            this.e = i;
            return this;
        }

        public c d(int i) {
            this.j = i;
            return this;
        }

        public c a() {
            this.k = true;
            return this;
        }

        public c b() {
            return this;
        }

        public d c() {
            return this;
        }

        public b d() {
            this.i = new OvalShape();
            return this;
        }

        public b e(int i) {
            this.b = (float) i;
            this.i = new RoundRectShape(new float[]{(float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i}, null, null);
            return this;
        }

        public f a(String str, int i, int i2) {
            e(i2);
            return b(str, i);
        }

        public f a(String str, int i) {
            d();
            return b(str, i);
        }

        public f b(String str, int i) {
            this.d = i;
            this.c = str;
            return new f();
        }
    }

    private f(a aVar) {
        super(aVar.i);
        this.e = aVar.i;
        this.f = aVar.g;
        this.g = aVar.f;
        this.i = aVar.b;
        this.c = aVar.l ? aVar.c.toUpperCase() : aVar.c;
        this.d = aVar.d;
        this.h = aVar.j;
        this.a = new Paint();
        this.a.setColor(aVar.a);
        this.a.setAntiAlias(true);
        this.a.setFakeBoldText(aVar.k);
        this.a.setStyle(Style.FILL);
        this.a.setTypeface(aVar.h);
        this.a.setTextAlign(Align.CENTER);
        this.a.setStrokeWidth((float) aVar.e);
        this.j = aVar.e;
        this.b = new Paint();
        this.b.setColor(a(this.d));
        this.b.setStyle(Style.STROKE);
        this.b.setStrokeWidth((float) this.j);
        getPaint().setColor(this.d);
    }

    private int a(int i) {
        return Color.rgb((int) (((float) Color.red(i)) * 0.9f), (int) (((float) Color.green(i)) * 0.9f), (int) (((float) Color.blue(i)) * 0.9f));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        if (this.j > 0) {
            a(canvas);
        }
        int save = canvas.save();
        canvas.translate((float) bounds.left, (float) bounds.top);
        int width = this.g < 0 ? bounds.width() : this.g;
        int height = this.f < 0 ? bounds.height() : this.f;
        this.a.setTextSize((float) (this.h < 0 ? (Math.min(width, height) / 2) - 2 : this.h));
        canvas.drawText(this.c, (float) (width / 2), ((float) (height / 2)) - ((this.a.descent() + this.a.ascent()) / 2.0f), this.a);
        canvas.restoreToCount(save);
    }

    private void a(Canvas canvas) {
        RectF rectF = new RectF(getBounds());
        rectF.inset((float) (this.j / 2), (float) (this.j / 2));
        if (this.e instanceof OvalShape) {
            canvas.drawOval(rectF, this.b);
        } else if (this.e instanceof RoundRectShape) {
            canvas.drawRoundRect(rectF, this.i, this.i, this.b);
        } else {
            canvas.drawRect(rectF, this.b);
        }
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.g;
    }

    public int getIntrinsicHeight() {
        return this.f;
    }

    public static d a() {
        return new a();
    }
}
