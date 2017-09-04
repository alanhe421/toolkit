package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.charge.voucher.b;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.d;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class OnlinePayPageInfoView extends View {
    private static float j;
    private Paint A = new Paint();
    private StringBuffer B = new StringBuffer();
    private int C;
    private int D = -1;
    public String a;
    private h b;
    private Context c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private TextPaint i;
    private Bitmap k;
    private Bitmap l;
    private Bitmap m;
    private Bitmap n;
    private RectF o = new RectF();
    private RectF p = new RectF();
    private RectF q = new RectF();
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private int u;
    private int v;
    private boolean w = true;
    private boolean x = true;
    private Rect y = new Rect();
    private RectF z = new RectF();

    public OnlinePayPageInfoView(Context context) {
        super(context);
        this.c = context;
        this.k = ao.b(this.c, R.drawable.checkbox_on);
        if (this.k == null) {
            this.k = ao.c(this.c, R.drawable.checkbox_on);
        }
        this.u = this.c.getResources().getDimensionPixelSize(R.dimen.paypage_checkbox_size);
        this.v = this.u;
        try {
            this.y.set(0, 0, this.k.getWidth(), this.k.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.l = ao.b(this.c, R.drawable.checkbox_off);
        if (this.l == null) {
            this.l = ao.c(this.c, R.drawable.checkbox_off);
        }
        this.n = ao.b(this.c, b.b());
        this.A.setAntiAlias(true);
        this.A.setFilterBitmap(true);
        this.A.setDither(true);
    }

    public void setBound(int i, int i2, int i3, int i4) {
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
    }

    public void setBatBuyStrPosY(int i) {
        this.h = i;
    }

    public void setTextColor(int i) {
        this.C = i;
    }

    public int getTextColor() {
        return this.C;
    }

    public void setTexPaint(TextPaint textPaint) {
        this.i = textPaint;
    }

    public void setPayInfo(h hVar) {
        this.b = hVar;
    }

    public boolean a() {
        if (this.b == null || this.b.c() == null || ((!this.b.c().a() && !this.b.c().b() && !this.b.c().c()) || (this.b.c().p() != 1003 && this.b.c().p() != 1009))) {
            return false;
        }
        return true;
    }

    public boolean b() {
        if (this.b == null || this.b.c() == null || (this.b.c().p() != 1003 && this.b.c().p() != 1009)) {
            return false;
        }
        return true;
    }

    public int a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (!a() || !this.x) {
            return -1;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.D = a(x, y);
                if (this.D != -1) {
                    return this.D;
                }
                return -1;
            case 1:
            case 3:
                int a = a(x, y);
                if (a != -1 && a == this.D) {
                    if (a == 1000) {
                        this.w = !this.w;
                        d.a = this.w;
                        return a;
                    } else if (a == 1003 || a == 1001 || a == 1002 || a == 1004 || a == 1005) {
                        return a;
                    }
                }
                this.D = -1;
                return -1;
            case 2:
                if (this.D != -1) {
                    return this.D;
                }
                return -1;
            case 4:
                this.D = -1;
                return -1;
            default:
                return -1;
        }
    }

    private int a(float f, float f2) {
        if (this.o.contains(f, f2)) {
            return 1000;
        }
        if (this.p.contains(f, f2)) {
            if (this.s) {
                return 1003;
            }
            if (this.r) {
                return 1002;
            }
            if (this.t) {
                return 1005;
            }
            return 1001;
        } else if (this.q.contains(f, f2)) {
            return 1004;
        } else {
            return -1;
        }
    }

    protected void onDraw(Canvas canvas) {
        if (b()) {
            ReadOnlineResult t = this.b.c().t();
            if (t != null) {
                int i;
                Object obj;
                float dimensionPixelOffset;
                String str;
                float width;
                FontMetrics fontMetrics;
                boolean a = this.b.c().a();
                boolean c = this.b.c().c();
                boolean d = this.b.c().d();
                boolean f = this.b.c().f();
                CharSequence h = this.b.c().h();
                CharSequence e = this.b.c().e();
                CharSequence k = this.b.c().k();
                CharSequence g = this.b.c().g();
                int i2 = this.C;
                int i3 = this.C;
                if (a.d.n) {
                    i = -6141440;
                } else {
                    i = -35072;
                }
                float textSize = this.i.getTextSize();
                int color = this.i.getColor();
                int dimensionPixelOffset2 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
                this.i.setColor(i3);
                this.i.getStrokeWidth();
                float f2 = (float) this.f;
                int dimensionPixelOffset3 = this.e - this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_12);
                canvas.drawLine((float) dimensionPixelOffset2, (float) dimensionPixelOffset3, (float) (this.f - dimensionPixelOffset2), (float) dimensionPixelOffset3, this.i);
                this.i.setColor(color);
                i3 = t.g();
                int j = t.j();
                if (i3 == 0 || j == 0 || i3 == j) {
                    obj = null;
                } else {
                    obj = 1;
                }
                String str2 = i3 + "书币";
                this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_15));
                if (j == 0.0f) {
                    j = this.i.measureText("价格：");
                }
                float f3 = (float) this.d;
                float dimensionPixelOffset4 = (float) (this.e + this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_5));
                FontMetrics fontMetrics2 = this.i.getFontMetrics();
                dimensionPixelOffset3 = (int) Math.ceil((double) (fontMetrics2.descent - fontMetrics2.ascent));
                float f4 = dimensionPixelOffset4 - fontMetrics2.ascent;
                canvas.drawText("价格：", f3, f4, this.i);
                f3 += j;
                int color2 = this.i.getColor();
                if (obj != null) {
                    this.i.setColor(i2);
                }
                canvas.drawText(str2, f3, f4, this.i);
                this.i.setColor(color2);
                float measureText = this.i.measureText(str2);
                if (obj != null) {
                    int color3 = this.i.getColor();
                    this.i.setColor(i2);
                    float strokeWidth = this.i.getStrokeWidth();
                    this.i.setStrokeWidth((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_1));
                    canvas.drawLine(f3, f4 - ((float) (dimensionPixelOffset3 / 3)), f3 + measureText, f4 - ((float) (dimensionPixelOffset3 / 3)), this.i);
                    this.i.setColor(color3);
                    this.i.setStrokeWidth(strokeWidth);
                    dimensionPixelOffset = (f3 + measureText) + ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_2));
                    str = j + "书币";
                    canvas.drawText(str, dimensionPixelOffset, f4, this.i);
                    f3 = this.i.measureText(str) + dimensionPixelOffset;
                }
                if (obj == null) {
                    dimensionPixelOffset4 = f3 + measureText;
                } else {
                    dimensionPixelOffset4 = ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_3)) + f3;
                }
                float f5 = f2 - dimensionPixelOffset4;
                if (obj != null) {
                    dimensionPixelOffset3 = this.i.getColor();
                    this.i.setColor(i);
                    String F = t.F();
                    if (!TextUtils.isEmpty(F)) {
                        str = "(" + F + ")";
                        dimensionPixelOffset2 = 1;
                        while (f5 < this.i.measureText(str) && dimensionPixelOffset2 < F.length()) {
                            str = "(" + F.substring(0, F.length() - dimensionPixelOffset2) + "...)";
                            dimensionPixelOffset2++;
                        }
                        canvas.drawText(str, dimensionPixelOffset4, f4, this.i);
                    }
                    this.i.setColor(dimensionPixelOffset3);
                }
                dimensionPixelOffset = (float) this.d;
                float dimensionPixelOffset5 = f4 + ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_12));
                float f6 = dimensionPixelOffset5 - fontMetrics2.ascent;
                canvas.drawText("余额：", dimensionPixelOffset, f6, this.i);
                f5 = dimensionPixelOffset + j;
                String G = t.G();
                if (TextUtils.isEmpty(G)) {
                    dimensionPixelOffset = f5;
                } else {
                    width = ((((float) this.f) - f5) - ((float) (G.contains("抵扣券") ? this.n.getWidth() : 0))) - ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_16));
                    dimensionPixelOffset2 = 1;
                    str = G;
                    while (width < this.i.measureText(str) && dimensionPixelOffset2 < G.length()) {
                        str = G.substring(0, G.length() - dimensionPixelOffset2) + "...";
                        dimensionPixelOffset2++;
                    }
                    canvas.drawText(str, f5, f6, this.i);
                    dimensionPixelOffset = this.i.measureText(str) + f5;
                }
                if (G.contains("抵扣券")) {
                    i3 = (int) (fontMetrics2.descent - fontMetrics2.ascent);
                    color2 = b.c();
                    this.q.set(((float) color2) + dimensionPixelOffset, dimensionPixelOffset5, ((float) color2) + (((float) i3) + dimensionPixelOffset), ((float) i3) + dimensionPixelOffset5);
                    canvas.drawBitmap(this.n, null, this.q, this.i);
                    dimensionPixelOffset += (float) this.n.getWidth();
                }
                if (this.f > this.g) {
                    boolean h2 = t.h();
                    this.a = t.D();
                    if (!(h2 || ao.s(this.a))) {
                        dimensionPixelOffset += (float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
                        i3 = this.c.getResources().getDimensionPixelSize(R.dimen.common_dp_16);
                        this.z.set(dimensionPixelOffset, dimensionPixelOffset5, ((float) i3) + dimensionPixelOffset, ((float) i3) + dimensionPixelOffset5);
                        this.m = ao.b(this.c, R.drawable.gift_32);
                        canvas.drawBitmap(this.m, null, this.z, this.A);
                        this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
                        dimensionPixelOffset += (float) (this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_4) + i3);
                        FontMetrics fontMetrics3 = this.i.getFontMetrics();
                        f3 = (((float) ((i3 - ((i3 - ((int) Math.ceil((double) (fontMetrics3.descent - fontMetrics3.ascent)))) / 2)) - ((int) fontMetrics3.descent))) + dimensionPixelOffset5) + 1.0f;
                        this.i.setColor(i2);
                        canvas.drawText(this.a, dimensionPixelOffset, f3, this.i);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "2");
                        i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
                if (a) {
                    Bitmap bitmap;
                    f3 = (float) this.d;
                    dimensionPixelOffset4 = f6 + ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_12));
                    dimensionPixelOffset2 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
                    this.o.set(f3 - ((float) dimensionPixelOffset2), dimensionPixelOffset4 - ((float) dimensionPixelOffset2), (((float) this.u) + f3) + ((float) dimensionPixelOffset2), ((float) dimensionPixelOffset2) + (((float) this.v) + dimensionPixelOffset4));
                    if (this.w) {
                        bitmap = this.k;
                    } else {
                        bitmap = this.l;
                    }
                    this.z.set(f3, dimensionPixelOffset4, ((float) this.u) + f3, ((float) this.v) + dimensionPixelOffset4);
                    canvas.drawBitmap(bitmap, this.y, this.z, this.A);
                    this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
                    fontMetrics = this.i.getFontMetrics();
                    dimensionPixelOffset = (((float) ((this.v - ((this.v - ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)))) / 2)) - ((int) fontMetrics.descent))) + dimensionPixelOffset4) + 1.0f;
                    f3 = (f3 + ((float) this.u)) + ((float) this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_4));
                    this.i.setColor(i2);
                    canvas.drawText("以后不再提示我，自动购买下一章", f3, dimensionPixelOffset, this.i);
                    dimensionPixelOffset = this.i.measureText("以后不再提示我，自动购买下一章") + f3;
                }
                if (this.f < this.g) {
                    int i4;
                    if (f && !TextUtils.isEmpty(h)) {
                        this.s = true;
                        this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
                        fontMetrics = this.i.getFontMetrics();
                        i3 = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
                        dimensionPixelOffset4 = this.i.measureText(h);
                        i4 = this.h;
                        dimensionPixelOffset3 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_10);
                        f6 = (((float) this.f) - dimensionPixelOffset4) / 2.0f;
                        this.p.set(f6, (float) (i4 - dimensionPixelOffset3), dimensionPixelOffset4 + f6, (float) ((i3 + i4) + dimensionPixelOffset3));
                        this.i.setColor(i);
                        canvas.drawText(h, f6, ((float) i4) - fontMetrics.ascent, this.i);
                    } else if (d && !TextUtils.isEmpty(e)) {
                        this.r = true;
                        str2 = "开通超值包，在线免费读 >";
                        if (!TextUtils.isEmpty(e)) {
                            str2 = e;
                        }
                        this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
                        r3 = this.i.getFontMetrics();
                        color2 = (int) Math.ceil((double) (r3.descent - r3.ascent));
                        f5 = this.i.measureText(str2);
                        dimensionPixelOffset3 = this.h;
                        r7 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_10);
                        width = (((float) this.f) - f5) / 2.0f;
                        this.p.set(width, (float) (dimensionPixelOffset3 - r7), f5 + width, (float) ((color2 + dimensionPixelOffset3) + r7));
                        this.i.setColor(i);
                        canvas.drawText(str2, width, ((float) dimensionPixelOffset3) - r3.ascent, this.i);
                    } else if (!TextUtils.isEmpty(g)) {
                        this.t = true;
                        this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
                        fontMetrics = this.i.getFontMetrics();
                        i3 = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
                        dimensionPixelOffset4 = this.i.measureText(g);
                        i4 = this.h;
                        dimensionPixelOffset3 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_10);
                        f6 = (((float) this.f) - dimensionPixelOffset4) / 2.0f;
                        this.p.set(f6, (float) (i4 - dimensionPixelOffset3), dimensionPixelOffset4 + f6, (float) ((i3 + i4) + dimensionPixelOffset3));
                        this.i.setColor(i);
                        canvas.drawText(g, f6, ((float) i4) - fontMetrics.ascent, this.i);
                    } else if (c && !TextUtils.isEmpty(k)) {
                        str2 = "开通包月VIP线免费读 >";
                        if (!TextUtils.isEmpty(k)) {
                            str2 = k;
                        }
                        this.i.setTextSize((float) this.c.getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
                        r3 = this.i.getFontMetrics();
                        color2 = (int) Math.ceil((double) (r3.descent - r3.ascent));
                        f5 = this.i.measureText(str2);
                        dimensionPixelOffset3 = this.h;
                        r7 = this.c.getResources().getDimensionPixelOffset(R.dimen.common_dp_10);
                        width = (((float) this.f) - f5) / 2.0f;
                        this.p.set(width, (float) (dimensionPixelOffset3 - r7), f5 + width, (float) ((color2 + dimensionPixelOffset3) + r7));
                        this.i.setColor(i);
                        canvas.drawText(str2, width, ((float) dimensionPixelOffset3) - r3.ascent, this.i);
                    }
                    this.i.setColor(color);
                    this.i.setTextSize(textSize);
                }
            }
        }
    }
}
