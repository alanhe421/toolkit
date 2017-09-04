package com.qrcomic.screenshot.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import b.a.a.a.a.a.d;
import com.qrcomic.util.a;
import com.qrcomic.util.g;
import com.tencent.smtt.sdk.WebView;

public class QRComicScreenView extends View {
    public static int d = 75;
    public static String e = "comic_original.jpg";
    public static String f = "screen_shot_key_name";
    public static String g = "VipComicScreenView";
    private RectF A = new RectF();
    private RectF B = new RectF();
    private RectF C = new RectF();
    private boolean D = false;
    public Bitmap a;
    public Drawable b = getResources().getDrawable(d.vip_comic_screenshot_select);
    Paint c = new Paint(1);
    public int h = 0;
    public int i = 0;
    public int j = 0;
    int k = 100001;
    int l = 100002;
    int m;
    float n;
    float o;
    float p;
    float q;
    boolean r = true;
    int s = 30;
    Rect t = new Rect();
    Rect u = new Rect();
    private Matrix v = new Matrix();
    private Matrix w = new Matrix();
    private float x = 1.0f;
    private int y;
    private RectF z = new RectF();

    public QRComicScreenView(Context context) {
        super(context);
    }

    public QRComicScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QRComicScreenView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.equals(this.a)) {
            this.a = bitmap;
            invalidate();
        }
    }

    public RectF getBitmapSelectRect() {
        if (!(this.w == null || this.A == null)) {
            this.w.mapRect(this.A, this.z);
        }
        return this.A;
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public void draw(Canvas canvas) {
        if (this.a != null && this.A != null) {
            if (!this.D) {
                this.h = 0;
                this.i = 0;
                this.j = 0;
                Point point = new Point();
                point.x = getWidth();
                point.y = getHeight();
                Point point2 = new Point();
                point2.x = this.a.getWidth();
                point2.y = this.a.getHeight();
                if (!(point2.x == 0 || point2.y == 0)) {
                    if (((float) point.x) / ((float) point2.x) < ((float) point.y) / ((float) point2.y)) {
                        point2.y = (point2.y * point.x) / point2.x;
                        point2.x = point.x;
                    } else {
                        point2.x = (point2.x * point.y) / point2.y;
                        point2.y = point.y;
                    }
                }
                point2.x = (int) (((double) point2.x) * 0.9d);
                point2.y = (int) (((double) point2.y) * 0.9d);
                this.x = ((float) point2.x) / ((float) this.a.getWidth());
                this.y = (int) (a.a(d, getResources()) * this.x);
                this.v.reset();
                this.w.reset();
                this.v.postScale(this.x, this.x);
                this.v.postTranslate((float) ((point.x - point2.x) / 2), (float) ((point.y - point2.y) / 2));
                this.v.invert(this.w);
                this.v.mapRect(this.z, new RectF(this.A));
                this.v.mapRect(this.B, new RectF(0.0f, 0.0f, (float) this.a.getWidth(), (float) this.a.getHeight()));
                this.D = true;
            }
            canvas.drawColor(WebView.NIGHT_MODE_COLOR, Mode.CLEAR);
            this.c.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
            canvas.drawBitmap(this.a, this.v, this.c);
            this.c.setColor(Integer.MIN_VALUE);
            this.c.setStyle(Style.FILL);
            canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) ((int) this.z.top), this.c);
            canvas.drawRect(0.0f, (float) ((int) this.z.bottom), (float) getWidth(), (float) getHeight(), this.c);
            canvas.drawRect(0.0f, (float) ((int) this.z.top), (float) ((int) this.z.left), (float) ((int) this.z.bottom), this.c);
            canvas.drawRect((float) ((int) this.z.right), (float) ((int) this.z.top), (float) getWidth(), (float) ((int) this.z.bottom), this.c);
            this.c.setStyle(Style.STROKE);
            this.c.setColor(-19456);
            canvas.drawRect(this.z, this.c);
            if (this.z.width() >= ((float) this.y)) {
                this.C.left = this.z.left + ((this.z.width() - ((float) this.y)) / 2.0f);
                this.C.right = this.z.right - ((this.z.width() - ((float) this.y)) / 2.0f);
            }
            if (this.z.height() >= ((float) this.y)) {
                this.C.top = this.z.top + ((this.z.height() - ((float) this.y)) / 2.0f);
                this.C.bottom = this.z.bottom - ((this.z.height() - ((float) this.y)) / 2.0f);
            }
            int intrinsicWidth = (int) (this.z.left - ((float) (this.b.getIntrinsicWidth() / 2)));
            int intrinsicHeight = (int) (this.z.top - ((float) (this.b.getIntrinsicHeight() / 2)));
            int intrinsicHeight2 = this.b.getIntrinsicHeight() + intrinsicHeight;
            this.b.setBounds(intrinsicWidth, intrinsicHeight, this.b.getIntrinsicWidth() + intrinsicWidth, intrinsicHeight2);
            this.b.draw(canvas);
            intrinsicWidth = (int) (this.z.right - ((float) (this.b.getIntrinsicWidth() / 2)));
            int intrinsicWidth2 = this.b.getIntrinsicWidth() + intrinsicWidth;
            this.b.setBounds(intrinsicWidth, intrinsicHeight, intrinsicWidth2, intrinsicHeight2);
            this.b.draw(canvas);
            intrinsicHeight = (int) (this.z.bottom - ((float) (this.b.getIntrinsicHeight() / 2)));
            intrinsicHeight2 = this.b.getIntrinsicHeight() + intrinsicHeight;
            this.b.setBounds(intrinsicWidth, intrinsicHeight, intrinsicWidth2, intrinsicHeight2);
            this.b.draw(canvas);
            intrinsicWidth = (int) (this.z.left - ((float) (this.b.getIntrinsicWidth() / 2)));
            this.b.setBounds(intrinsicWidth, intrinsicHeight, this.b.getIntrinsicWidth() + intrinsicWidth, intrinsicHeight2);
            this.b.draw(canvas);
        } else if (g.a()) {
            g.a(g, g.d, "mBitmap or mBitmapSelectRect is null");
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.n = motionEvent.getX();
                this.o = motionEvent.getY();
                if (!this.z.isEmpty()) {
                    if (new RectF(this.z.left + ((float) this.s), this.z.top + ((float) this.s), this.z.right - ((float) this.s), this.z.bottom - ((float) this.s)).contains(this.n, this.o)) {
                        this.m = this.k;
                        this.j++;
                    } else if (new RectF(this.z.left - ((float) this.s), this.z.top - ((float) this.s), this.z.right + ((float) this.s), this.z.bottom + ((float) this.s)).contains(this.n, this.o)) {
                        this.m = this.l;
                        this.r = true;
                        if (new RectF(this.z.left - ((float) this.s), this.z.top - ((float) this.s), this.z.left + ((float) this.s), this.z.top + ((float) this.s)).contains(this.n, this.o) || new RectF(this.z.right - ((float) this.s), this.z.top - ((float) this.s), this.z.right + ((float) this.s), this.z.top + ((float) this.s)).contains(this.n, this.o) || new RectF(this.z.left - ((float) this.s), this.z.bottom - ((float) this.s), this.z.left + ((float) this.s), this.z.bottom + ((float) this.s)).contains(this.n, this.o) || new RectF(this.z.right - ((float) this.s), this.z.bottom - ((float) this.s), this.z.right + ((float) this.s), this.z.bottom + ((float) this.s)).contains(this.n, this.o)) {
                            this.h++;
                        } else {
                            this.i++;
                        }
                    }
                }
                invalidate();
                break;
            case 1:
                this.m = 0;
                invalidate();
                break;
            case 2:
                this.p = motionEvent.getX();
                this.q = motionEvent.getY();
                float f;
                float f2;
                RectF rectF;
                RectF rectF2;
                if (this.m == this.k) {
                    f = this.p - this.n;
                    f2 = this.q - this.o;
                    this.n = this.p;
                    this.o = this.q;
                    if (this.z.left + f >= this.B.left && this.z.right + f <= this.B.right) {
                        rectF = this.z;
                        rectF.left += f;
                        rectF = this.z;
                        rectF.right = f + rectF.right;
                    }
                    if (this.z.top + f2 >= this.B.top && this.z.bottom + f2 <= this.B.bottom) {
                        rectF2 = this.z;
                        rectF2.top += f2;
                        rectF2 = this.z;
                        rectF2.bottom = f2 + rectF2.bottom;
                    }
                } else if (this.m == this.l) {
                    f = this.p - this.n;
                    f2 = this.q - this.o;
                    this.n = this.p;
                    this.o = this.q;
                    if (true == this.r) {
                        if (this.n < this.z.left + (this.z.width() / 2.0f)) {
                            rectF = this.z;
                            rectF.left = f + rectF.left;
                            this.z.left = Math.max(this.z.left, this.B.left);
                            this.z.left = Math.min(this.z.left, this.C.left);
                        } else {
                            rectF = this.z;
                            rectF.right = f + rectF.right;
                            this.z.right = Math.min(this.z.right, this.B.right);
                            this.z.right = Math.max(this.z.right, this.C.right);
                        }
                        if (this.o < this.z.top + (this.z.height() / 2.0f)) {
                            rectF2 = this.z;
                            rectF2.top = f2 + rectF2.top;
                            this.z.top = Math.max(this.z.top, this.B.top);
                            this.z.top = Math.min(this.z.top, this.C.top);
                        } else {
                            rectF2 = this.z;
                            rectF2.bottom = f2 + rectF2.bottom;
                            this.z.bottom = Math.min(this.z.bottom, this.B.bottom);
                            this.z.bottom = Math.max(this.z.bottom, this.C.bottom);
                        }
                    }
                    this.t.set(((int) this.z.left) + this.s, ((int) this.z.top) + this.s, ((int) this.z.right) - this.s, ((int) this.z.bottom) - this.s);
                    this.u.set((int) this.z.left, (int) this.z.top, (int) this.z.right, (int) this.z.bottom);
                    if (this.u.contains((int) this.p, (int) this.q) && this.t.contains((int) this.p, (int) this.q)) {
                        this.r = false;
                    } else {
                        this.r = true;
                    }
                }
                invalidate();
                break;
        }
        return true;
    }
}
