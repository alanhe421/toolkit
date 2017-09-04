package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.readengine.fileparse.a;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.c.c;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.s;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import format.epub.view.ZLRectNoteArrayList;
import format.epub.view.ZLTextElementAreaArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PagePaintContextForText */
public class k extends j {
    private OnlinePayPageInfoView A;
    private Rect B = new Rect();
    private String C = "大家来讨论";
    private String D;
    private s E;
    private Drawable F;
    private Drawable G;
    private Paint H = new TextPaint();
    private int I = -10134443;
    Mark[] q = null;
    protected c r;
    protected Paint s;
    private e t;
    private TextPaint u;
    private TextPaint v;
    private TextPaint w;
    private Bitmap x;
    private Drawable y;
    private Drawable z;

    public k(Context context, b bVar) {
        super(context, bVar);
        this.r = (c) bVar.b();
        this.t = new e(context, this.c);
        this.u = new TextPaint();
        this.u.setAntiAlias(true);
        this.u.setDither(false);
        File k = ao.k();
        if (k == null || !k.exists()) {
            d.c(ReaderApplication.getApplicationImp(), d.B);
            d.d(ReaderApplication.getApplicationImp(), d.C);
            this.t.a(Typeface.SANS_SERIF);
            this.h.setTypeface(Typeface.SANS_SERIF);
            this.u.setTypeface(Typeface.SANS_SERIF);
        } else {
            try {
                Typeface createFromFile = Typeface.createFromFile(k);
                this.t.a(createFromFile);
                this.h.setTypeface(createFromFile);
                this.u.setTypeface(createFromFile);
            } catch (Exception e) {
                e.printStackTrace();
                this.t.a(Typeface.SANS_SERIF);
                this.h.setTypeface(Typeface.SANS_SERIF);
                this.u.setTypeface(Typeface.SANS_SERIF);
            }
        }
        this.u.setTextSize((float) context.getResources().getDimensionPixelOffset(R.dimen.text_size_class_2));
        this.u.setColor(WebView.NIGHT_MODE_COLOR);
        this.u.setTextAlign(Align.LEFT);
        this.y = context.getResources().getDrawable(R.drawable.new_btn_normal);
        this.z = context.getResources().getDrawable(R.drawable.readerpage_loading_blue);
        this.v = new TextPaint();
        this.v.setAntiAlias(true);
        this.v.setColor(WebView.NIGHT_MODE_COLOR);
        this.w = new TextPaint();
        this.w.setAntiAlias(true);
        this.w.setTextSize((float) context.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
        this.w.setColor(-1);
        this.w.setTextAlign(Align.LEFT);
        this.s = new Paint();
        this.s.setColor(-65536);
        this.A = new OnlinePayPageInfoView(this.i);
        this.A.setPayInfo(this.r.B());
        this.A.setTexPaint(this.v);
        this.r.B().a(this.A);
        this.E = new s(this.i);
        this.D = this.i.getResources().getString(R.string.chapter_comment_tip);
        this.C = String.format(this.D, new Object[]{Integer.valueOf(0)});
        this.F = this.i.getResources().getDrawable(R.drawable.author_head_default);
        this.G = this.i.getResources().getDrawable(R.drawable.author_flag_icon);
        this.H.setAntiAlias(true);
        this.H.setDither(false);
    }

    public void d(int i) {
        this.E.a(i);
        this.t.c(i);
    }

    public void q() {
        File k = ao.k();
        if (k == null || !k.exists()) {
            this.t.a(Typeface.SANS_SERIF);
            this.v.setTypeface(Typeface.SANS_SERIF);
            this.h.setTypeface(Typeface.SANS_SERIF);
            this.u.setTypeface(Typeface.SANS_SERIF);
        } else {
            Typeface createFromFile = Typeface.createFromFile(k);
            this.t.a(createFromFile);
            this.v.setTypeface(createFromFile);
            this.h.setTypeface(createFromFile);
            this.u.setTypeface(createFromFile);
        }
        this.n = this.h.getTypeface();
        ao.i();
    }

    public TextPaint d() {
        return this.h;
    }

    public void a(float f) {
        super.a(f);
        ao.i();
    }

    public int p() {
        return b();
    }

    public void a(int i, int i2) {
        Object obj = null;
        if (!(this.b == 0 || this.b == i2)) {
            obj = 1;
        }
        if (!(this.a == 0 || this.a == i)) {
            obj = 1;
        }
        super.a(i, i2);
        if (obj != null) {
            this.r.j();
        }
    }

    public void b(float f) {
        super.b(f);
        ao.i();
    }

    public void b(int i) {
        this.h.setColor(i);
        this.t.a(i);
        this.v.setColor(i);
        this.u.setColor(i);
    }

    public void c(int i) {
        this.I = i;
        this.t.b(i);
    }

    protected void a(Canvas canvas, PageIndex pageIndex, ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList, n nVar, d dVar, int i) {
        com.qq.reader.readengine.kernel.c.b q;
        if (i != 2) {
            if (this.r.A()) {
                this.p.a(Boolean.valueOf(true));
                zLTextElementAreaArrayList.clear();
                if (this.r.B().f() == 1008) {
                    this.t.a(this.b, this.a, canvas, this.d.d().t());
                    return;
                } else {
                    a(canvas);
                    return;
                }
            }
            this.p.a(Boolean.valueOf(false));
        }
        switch (pageIndex) {
            case current:
                if (i != 1) {
                    q = this.r.q();
                    break;
                }
                q = this.r.s();
                if (q == null || !q.b()) {
                    q = this.r.q();
                    break;
                }
            default:
                q = this.r.q();
                break;
        }
        a(canvas, q, zLTextElementAreaArrayList, nVar);
        this.l.j();
        this.l.a(zLTextElementAreaArrayList, zLRectNoteArrayList);
        this.l.b(canvas);
    }

    private void a(Canvas canvas) {
        int i;
        int i2;
        int n = n();
        int o = o();
        int dimensionPixelSize = this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_margin_horizonal);
        int i3 = n - (dimensionPixelSize * 2);
        int dimensionPixelSize2 = this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_height);
        int dimensionPixelSize3 = this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_margin_top);
        int color = this.h.getColor();
        this.v.setTextAlign(Align.LEFT);
        int dimensionPixelOffset = this.i.getResources().getDimensionPixelOffset(R.dimen.title_font_size_3);
        this.v.setTextSize((float) dimensionPixelOffset);
        FontMetrics fontMetrics = this.v.getFontMetrics();
        int ceil = (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
        float f = fontMetrics.ascent;
        int dimensionPixelOffset2 = this.i.getResources().getDimensionPixelOffset(R.dimen.screen_default_font_size);
        this.v.setTextSize((float) dimensionPixelOffset2);
        float descent = (this.v.descent() - this.v.ascent()) * 4.0f;
        float descent2 = (this.v.descent() - this.v.ascent()) * 1.4f;
        float f2 = this.v.getFontMetrics().ascent;
        float k = (float) j.k();
        int j = j.j();
        int i4 = o / 2;
        h.b c = this.r.B().c();
        String r = c.r();
        String str = "";
        ReadOnlineResult t = c.t();
        if (t != null) {
            str = t.v();
        }
        int j2 = j.j() * 2;
        if (this.r != null && c.p() == 1004) {
            j2 = 0;
        }
        com.qq.reader.readengine.fileparse.c aVar = new a();
        aVar.f = true;
        aVar.g = str;
        ao.i();
        com.qq.reader.readengine.a.c.a(aVar, c(), 0, this.v);
        ao.i();
        this.v.setTextSize((float) dimensionPixelOffset);
        List a = ao.a(r, this.v, (float) (this.a - j2));
        int k2 = aVar.k();
        if (n <= o) {
            if (o > 800) {
                i = 5;
            } else if (o == 800) {
                i = 4;
            } else {
                i = 3;
            }
        } else if (o < 800) {
            i = 1;
        } else {
            i = 1;
        }
        float f3 = ((float) i) * descent2;
        float f4 = (((float) i4) - f3) - (2.5f * ((float) ceil));
        this.E.a(y());
        this.E.a(canvas);
        if (a.size() > 0) {
            this.v.setTextSize((float) dimensionPixelOffset);
            this.v.setColor(this.I);
            f4 = k - f;
            char[] cArr = (char[]) a.get(0);
            if (a.size() > 1) {
                cArr[cArr.length - 1] = '…';
            }
            canvas.drawText(cArr, 0, ((char[]) a.get(0)).length, (float) j, f4, this.v);
            this.v.setColor(color);
        }
        this.v.setColor(color);
        float f5 = k + descent;
        if (k2 > 0) {
            this.v.setTextSize((float) dimensionPixelOffset2);
            j = Math.min(k2, i);
            f4 = (float) j.j();
            dimensionPixelOffset = 0;
            f = f5;
            while (dimensionPixelOffset < j) {
                String d = aVar.d(dimensionPixelOffset);
                float[] e = aVar.e(dimensionPixelOffset);
                float[] fArr = new float[e.length];
                for (i2 = 0; i2 < e.length; i2 += 2) {
                    fArr[i2] = e[i2] + f4;
                    fArr[i2 + 1] = f - f2;
                }
                if (dimensionPixelOffset == j - 1) {
                    try {
                        if (fArr[e.length - 2] + this.v.measureText("…") <= ((float) (n() - j.i()))) {
                            StringBuffer stringBuffer = new StringBuffer(d);
                            int length = stringBuffer.length();
                            stringBuffer.replace(length - 1, length, "…");
                            str = stringBuffer.toString();
                        } else {
                            str = d;
                        }
                        d = str;
                    } catch (Exception e2) {
                    }
                }
                canvas.drawPosText(d, fArr, this.v);
                dimensionPixelOffset++;
                f += descent2;
            }
        } else if (c.s().length() > 0) {
            this.v.setTextAlign(Align.CENTER);
            this.v.setTextSize((float) dimensionPixelOffset2);
            canvas.drawText(c.s(), (float) (n / 2), (((float) (ceil * 2)) + f4) + (f3 / 2.0f), this.v);
            this.v.setTextAlign(Align.LEFT);
        }
        j2 = (o / 2) + dimensionPixelSize3;
        i2 = this.i.getResources().getDimensionPixelSize(R.dimen.paypage_batbuy_posY_relative_btn);
        this.A.setBound(dimensionPixelSize, o / 2, n(), o());
        this.A.setBatBuyStrPosY(i2 + (j2 + dimensionPixelSize2));
        this.A.onDraw(canvas);
        this.v.setTextAlign(Align.CENTER);
        this.v.setTextSize((float) this.i.getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
        fontMetrics = this.v.getFontMetrics();
        i2 = (((dimensionPixelSize2 - ((dimensionPixelSize2 - ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)))) / 2)) - ((int) fontMetrics.descent)) + j2) + 1;
        dimensionPixelOffset = this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.y.setBounds((n - i3) / 2, j2, (n + i3) / 2, j2 + dimensionPixelSize2);
        switch (c.p()) {
            case 1000:
                if (!x()) {
                    fontMetrics = this.w.getFontMetrics();
                    i2 = (((dimensionPixelSize2 - ((dimensionPixelSize2 - ((int) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top)))) / 2)) - ((int) Math.abs(fontMetrics.bottom))) + j2) - 1;
                    int measureText = ((n - ((int) this.w.measureText(c.n()))) - dimensionPixelOffset) / 2;
                    j = measureText + dimensionPixelOffset;
                    j2 += (dimensionPixelSize2 - dimensionPixelOffset) / 2;
                    this.z.setBounds(measureText, j2, measureText + dimensionPixelOffset, dimensionPixelOffset + j2);
                    this.z.draw(canvas);
                    this.w.setColor(this.h.getColor());
                    canvas.drawText(c.n(), (float) j, (float) i2, this.w);
                    return;
                }
                return;
            case 1001:
                this.y.draw(canvas);
                j2 = this.v.getColor();
                this.v.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) i2, this.v);
                this.v.setColor(j2);
                return;
            case 1003:
            case 1009:
                this.y.draw(canvas);
                dimensionPixelOffset = this.v.getColor();
                this.v.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) i2, this.v);
                f = this.v.getTextSize();
                this.v.setColor(this.A.getTextColor());
                boolean z = true;
                if (t != null) {
                    z = t.h();
                }
                String D = c.t().D();
                if (z || ao.s(D) || n >= o) {
                    this.v.setTextAlign(Align.CENTER);
                    this.v.setTextSize((float) this.i.getResources().getDimensionPixelOffset(R.dimen.text_size_class_2));
                    float f6 = (float) (n / 2);
                    Canvas canvas2 = canvas;
                    canvas2.drawText("感谢支持作者，支持正版阅读", f6, (float) (((j2 + dimensionPixelSize2) + this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_8)) - ((int) this.w.getFontMetrics().ascent)), this.v);
                } else {
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setFilterBitmap(true);
                    paint.setDither(true);
                    RectF rectF = new RectF();
                    f5 = (float) ((j2 + dimensionPixelSize2) + this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_8));
                    i = this.i.getResources().getDimensionPixelSize(R.dimen.common_dp_16);
                    this.v.setTextAlign(Align.LEFT);
                    this.v.setTextSize((float) this.i.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
                    float dimensionPixelOffset3 = 0.0f + ((((float) n) - (((float) (this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_4) + i)) + this.v.measureText(D))) / 2.0f);
                    rectF.set(dimensionPixelOffset3, f5, ((float) i) + dimensionPixelOffset3, ((float) i) + f5);
                    this.x = ao.b(this.i, R.drawable.gift_32);
                    canvas.drawBitmap(this.x, dimensionPixelOffset3, f5, paint);
                    fontMetrics = this.w.getFontMetrics();
                    float ceil2 = (((float) ((i - ((i - ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)))) / 2)) - ((int) fontMetrics.descent))) + f5) + 1.0f;
                    canvas.drawText(D, ((float) (this.i.getResources().getDimensionPixelOffset(R.dimen.common_dp_4) + i)) + dimensionPixelOffset3, ceil2, this.v);
                    Map hashMap = new HashMap();
                    hashMap.put(com.qq.reader.module.bookstore.qnative.item.s.ORIGIN, "2");
                    i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                }
                this.v.setTextAlign(Align.CENTER);
                this.v.setColor(dimensionPixelOffset);
                this.v.setTextSize(f);
                return;
            case 1004:
                this.y.draw(canvas);
                j2 = this.v.getColor();
                this.v.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) i2, this.v);
                this.v.setColor(j2);
                return;
            case 1005:
                this.y.draw(canvas);
                j2 = this.v.getColor();
                this.v.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) i2, this.v);
                this.v.setColor(j2);
                return;
            case 1006:
                this.y.draw(canvas);
                j2 = this.v.getColor();
                this.v.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) i2, this.v);
                this.v.setColor(j2);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.graphics.Canvas r39, com.qq.reader.readengine.kernel.c.b r40, format.epub.view.ZLTextElementAreaArrayList r41, com.qq.reader.module.readpage.n r42) {
        /*
        r38 = this;
        r4 = com.qq.reader.module.readpage.j.j();
        r0 = (float) r4;
        r23 = r0;
        r4 = com.qq.reader.module.readpage.j.k();
        r4 = (float) r4;
        r5 = com.qq.reader.module.readpage.j.k();
        r0 = (float) r5;
        r24 = r0;
        r5 = r38.c();
        r5 = (float) r5;
        r21 = r23 + r5;
        r25 = com.qq.reader.readengine.a.a.a();
        r0 = r38;
        r5 = r0.i;
        r26 = com.qq.reader.appconfig.a.d.F(r5);
        r0 = r38;
        r5 = r0.i;
        r27 = com.qq.reader.appconfig.a.d.I(r5);
        r0 = r38;
        r5 = r0.n;
        if (r40 == 0) goto L_0x04ba;
    L_0x0034:
        r5 = r40.b();
        if (r5 == 0) goto L_0x04ba;
    L_0x003a:
        r0 = r40;
        r5 = r0.c;
        r20 = r4 + r5;
        r4 = r38.a();
        r0 = r40;
        r5 = r0.b;
        r28 = r4 + r5;
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
        r0 = r38;
        r4 = r0.o;
        if (r4 == 0) goto L_0x0064;
    L_0x0059:
        r0 = r38;
        r4 = r0.h;
        r0 = r38;
        r5 = r0.o;
        r4.setTypeface(r5);
    L_0x0064:
        r0 = r38;
        r4 = r0.h;
        r18 = r4.descent();
        r0 = r38;
        r4 = r0.h;
        r15 = r4.ascent();
        r0 = r38;
        r4 = r0.h;
        r0 = r38;
        r5 = r0.n;
        r4.setTypeface(r5);
        r0 = r38;
        r4 = r0.h;
        r19 = r4.descent();
        r0 = r38;
        r4 = r0.h;
        r16 = r4.ascent();
        r0 = r38;
        r4 = r0.h;
        r0 = r26;
        r4.setTextSize(r0);
        r0 = r38;
        r4 = r0.h;
        r17 = r4.descent();
        r0 = r38;
        r4 = r0.h;
        r14 = r4.ascent();
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
        r40.g();
        r38.m();
        r6 = 0;
        r5 = 0;
        r4 = "";
        r0 = r38;
        r7 = r0.d;
        if (r7 == 0) goto L_0x04c0;
    L_0x00c2:
        r0 = r38;
        r6 = r0.d;
        r6 = r6.d();
        r6 = r6.t();
        if (r6 == 0) goto L_0x04bb;
    L_0x00d0:
        r5 = r6.getReadType();
        r4 = r6.getBookShortName();
        r10 = r4;
        r11 = r5;
        r12 = r6;
    L_0x00db:
        r29 = r40.f();
        r0 = r38;
        r4 = r0.h;
        r30 = r4.getColor();
        r13 = 0;
        r4 = 0;
        r22 = r4;
    L_0x00eb:
        r0 = r22;
        r1 = r29;
        if (r0 >= r1) goto L_0x0487;
    L_0x00f1:
        r0 = r40;
        r1 = r22;
        r31 = r0.a(r1);
        r0 = r31;
        r4 = r0.length;
        r0 = new float[r4];
        r32 = r0;
        r0 = r40;
        r1 = r22;
        r4 = r0.b(r1);
        r33 = r4.c();
        r34 = r4.g();
        r35 = r4.h();
        r4 = r35.i();
        r36 = r35.h();
        if (r36 == 0) goto L_0x0140;
    L_0x011e:
        r5 = r22 + 1;
        r0 = r29;
        if (r5 >= r0) goto L_0x0140;
    L_0x0124:
        r5 = r22 + 1;
        r0 = r40;
        r5 = r0.b(r5);
        r5 = r5.h();
        r5 = r5.a();
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r5 == r6) goto L_0x0140;
    L_0x0138:
        r6 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r5 == r6) goto L_0x0140;
    L_0x013c:
        r6 = 100;
        if (r5 != r6) goto L_0x0140;
    L_0x0140:
        r5 = "TAG";
        r6 = "curTextLine.isTextLine()";
        com.qq.reader.common.monitor.f.b(r5, r6);
        r5 = r35.b();
        if (r5 != 0) goto L_0x0342;
    L_0x014f:
        r4 = "TAG";
        r5 = "作者头像";
        com.qq.reader.common.monitor.f.b(r4, r5);
        r4 = r35.a();
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r4 != r5) goto L_0x032d;
    L_0x0160:
        r4 = r35.f();
        r4 = r4 + r24;
        r0 = r38;
        r5 = r0.H;
        r6 = android.graphics.Paint.Style.FILL;
        r5.setStyle(r6);
        r5 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r5 = com.qq.reader.common.utils.ao.a(r5);
        r5 = (float) r5;
        r6 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        r6 = com.qq.reader.common.utils.ao.a(r6);
        r6 = (float) r6;
        r0 = r38;
        r6 = r0.H;
        r7 = 1096810496; // 0x41600000 float:14.0 double:5.41896386E-315;
        r7 = com.qq.reader.common.utils.ao.a(r7);
        r7 = (float) r7;
        r6.setTextSize(r7);
        r6 = "作者有话说";
        r0 = r38;
        r7 = r0.H;
        r7.measureText(r6);
        r0 = r38;
        r6 = r0.H;
        r7 = -3355444; // 0xffffffffffcccccc float:NaN double:NaN;
        r6.setColor(r7);
        r6 = r4 + r5;
        r0 = r38;
        r4 = r0.H;
        r4 = r4.descent();
        r0 = r38;
        r5 = r0.H;
        r5 = r5.ascent();
        r4 = r4 - r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r5;
        r4 = r4 + r6;
        r4 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r4 = com.qq.reader.common.utils.ao.a(r4);
        r4 = (float) r4;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = com.qq.reader.common.utils.ao.a(r4);
        r4 = (float) r4;
        r7 = r23 + r4;
        r0 = r38;
        r5 = r0.F;
        r4 = 0;
        if (r12 == 0) goto L_0x01d7;
    L_0x01cd:
        r8 = r12.getAuthorIconDrawable();
        if (r8 == 0) goto L_0x01d7;
    L_0x01d3:
        r5 = r12.getAuthorIconDrawable();
    L_0x01d7:
        if (r12 == 0) goto L_0x01e7;
    L_0x01d9:
        r8 = r12.getAuthorId();
        r8 = android.text.TextUtils.isEmpty(r8);
        if (r8 != 0) goto L_0x01e7;
    L_0x01e3:
        r4 = r12.getAuthorId();
    L_0x01e7:
        r8 = 1105199104; // 0x41e00000 float:28.0 double:5.46040909E-315;
        r8 = com.qq.reader.common.utils.ao.a(r8);
        r9 = 1105199104; // 0x41e00000 float:28.0 double:5.46040909E-315;
        r9 = com.qq.reader.common.utils.ao.a(r9);
        r0 = (int) r7;
        r31 = r0;
        r0 = (int) r6;
        r32 = r0;
        r0 = (int) r7;
        r33 = r0;
        r33 = r33 + r8;
        r0 = (int) r6;
        r34 = r0;
        r34 = r34 + r9;
        r0 = r31;
        r1 = r32;
        r2 = r33;
        r3 = r34;
        r5.setBounds(r0, r1, r2, r3);
        r0 = r39;
        r5.draw(r0);
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x0242;
    L_0x0219:
        r0 = r38;
        r4 = r0.B;
        r5 = (int) r7;
        r0 = (int) r6;
        r31 = r0;
        r0 = (int) r7;
        r32 = r0;
        r32 = r32 + r8;
        r0 = (int) r6;
        r33 = r0;
        r33 = r33 + r9;
        r0 = r31;
        r1 = r32;
        r2 = r33;
        r4.set(r5, r0, r1, r2);
        r4 = new com.qq.reader.module.readpage.m;
        r0 = r38;
        r5 = r0.B;
        r4.<init>(r5);
        r0 = r42;
        r0.a(r4);
    L_0x0242:
        r0 = r38;
        r4 = r0.H;
        r5 = android.graphics.Paint.Style.FILL;
        r4.setStyle(r5);
        r0 = r38;
        r4 = r0.H;
        r5 = 1098907648; // 0x41800000 float:16.0 double:5.42932517E-315;
        r5 = com.qq.reader.common.utils.ao.a(r5);
        r5 = (float) r5;
        r4.setTextSize(r5);
        r0 = r38;
        r4 = r0.H;
        r0 = r30;
        r4.setColor(r0);
        r0 = r38;
        r4 = r0.H;
        r4 = r4.descent();
        r0 = r38;
        r5 = r0.H;
        r5 = r5.ascent();
        r4 = r4 - r5;
        r5 = (float) r8;
        r5 = r5 + r7;
        r7 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r7 = com.qq.reader.common.utils.ao.a(r7);
        r7 = (float) r7;
        r5 = r5 + r7;
        r7 = (float) r9;
        r4 = r7 - r4;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r7;
        r7 = r6 + r4;
        if (r12 == 0) goto L_0x0328;
    L_0x0287:
        r4 = r12.getAuthor();
    L_0x028b:
        r0 = r38;
        r8 = r0.H;
        r8 = r8.measureText(r4);
        r0 = r38;
        r0 = r0.H;
        r31 = r0;
        r31 = r31.ascent();
        r7 = r7 - r31;
        r0 = r38;
        r0 = r0.H;
        r31 = r0;
        r0 = r39;
        r1 = r31;
        r0.drawText(r4, r5, r7, r1);
        r0 = r38;
        r4 = r0.G;
        r7 = 1108344832; // 0x42100000 float:36.0 double:5.47595105E-315;
        r7 = com.qq.reader.common.utils.ao.a(r7);
        r31 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        r31 = com.qq.reader.common.utils.ao.a(r31);
        r5 = r5 + r8;
        r8 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r8 = com.qq.reader.common.utils.ao.a(r8);
        r8 = (float) r8;
        r5 = r5 + r8;
        r8 = r9 - r31;
        r8 = r8 / 2;
        r8 = (float) r8;
        r8 = r8 + r6;
        r0 = (int) r5;
        r32 = r0;
        r0 = (int) r8;
        r33 = r0;
        r0 = (int) r5;
        r34 = r0;
        r34 = r34 + r7;
        r8 = (int) r8;
        r8 = r8 + r31;
        r0 = r32;
        r1 = r33;
        r2 = r34;
        r4.setBounds(r0, r1, r2, r8);
        r0 = r39;
        r4.draw(r0);
        r0 = r38;
        r4 = r0.H;
        r8 = android.graphics.Paint.Style.STROKE;
        r4.setStyle(r8);
        r0 = r38;
        r4 = r0.H;
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4.setStrokeWidth(r8);
        r0 = r38;
        r4 = r0.H;
        r8 = -862348903; // 0xffffffffcc999999 float:-8.0530632E7 double:NaN;
        r4.setColor(r8);
        r4 = (float) r7;
        r4 = r4 + r5;
        r5 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r5 = com.qq.reader.common.utils.ao.a(r5);
        r5 = (float) r5;
        r5 = r5 + r4;
        r4 = r38.c();
        r4 = (float) r4;
        r7 = r23 + r4;
        r4 = r9 / 2;
        r4 = (float) r4;
        r6 = r6 + r4;
        r0 = r38;
        r9 = r0.H;
        r4 = r39;
        r8 = r6;
        r4.drawLine(r5, r6, r7, r8, r9);
    L_0x0322:
        r4 = r22 + 1;
        r22 = r4;
        goto L_0x00eb;
    L_0x0328:
        r4 = "匿名";
        goto L_0x028b;
    L_0x032d:
        r4 = "TAG";
        r5 = "控件层";
        com.qq.reader.common.monitor.f.b(r4, r5);
        r0 = r38;
        r4 = r0.p;
        r0 = r39;
        r1 = r35;
        r4.a(r0, r1);
        goto L_0x0322;
    L_0x0342:
        if (r25 == 0) goto L_0x03c4;
    L_0x0344:
        if (r4 == 0) goto L_0x03c4;
    L_0x0346:
        r4 = 1;
        r0 = r38;
        r5 = r0.h;
        r0 = r26;
        r5.setTextSize(r0);
        r0 = r38;
        r5 = r0.h;
        r0 = r38;
        r6 = r0.I;
        r5.setColor(r6);
        r0 = r38;
        r5 = r0.h;
        r0 = r38;
        r6 = r0.n;
        r5.setTypeface(r6);
        r6 = r4;
        r7 = r14;
        r8 = r17;
    L_0x036a:
        com.qq.reader.common.utils.ao.j();
        r4 = 0;
        r9 = r4;
    L_0x036f:
        r0 = r31;
        r4 = r0.length;
        if (r9 >= r4) goto L_0x0448;
    L_0x0374:
        r4 = r9 / 2;
        r0 = r33;
        r4 = r0.get(r4);
        r4 = (format.epub.view.h) r4;
        r5 = r31[r9];
        r5 = r5 + r23;
        r32[r9] = r5;
        if (r25 == 0) goto L_0x040c;
    L_0x0386:
        r5 = r35.f();
        r5 = r5 + r24;
        r13 = r5;
    L_0x038d:
        r5 = r9 + 1;
        r37 = r13 - r7;
        r32[r5] = r37;
        r5 = r9 + 2;
        r0 = r32;
        r0 = r0.length;
        r37 = r0;
        r0 = r37;
        if (r5 >= r0) goto L_0x0410;
    L_0x039e:
        r5 = r9 + 2;
        r5 = r31[r5];
        r5 = r5 + r23;
    L_0x03a4:
        r37 = r32[r9];
        r0 = r37;
        r4.a = r0;
        r4.b = r5;
        r4.c = r13;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 * r8;
        r5 = r5 - r7;
        r5 = r5 + r13;
        r4.d = r5;
        r0 = r41;
        r0.add(r4);
        r4 = r4.b;
        r4 = r9 + 1;
        r4 = r32[r4];
        r4 = r9 + 2;
        r9 = r4;
        goto L_0x036f;
    L_0x03c4:
        if (r36 == 0) goto L_0x03e8;
    L_0x03c6:
        r0 = r38;
        r4 = r0.h;
        r0 = r30;
        r4.setColor(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r38;
        r5 = r0.o;
        r4.setTypeface(r5);
        r6 = r13;
        r7 = r15;
        r8 = r18;
        goto L_0x036a;
    L_0x03e8:
        r0 = r38;
        r4 = r0.h;
        r0 = r30;
        r4.setColor(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r38;
        r5 = r0.n;
        r4.setTypeface(r5);
        r6 = r13;
        r7 = r16;
        r8 = r19;
        goto L_0x036a;
    L_0x040c:
        r13 = r20;
        goto L_0x038d;
    L_0x0410:
        r5 = r4.o;
        r5 = r5 instanceof format.epub.view.y;
        if (r5 == 0) goto L_0x043e;
    L_0x0416:
        r5 = r4.o;
        r5 = (format.epub.view.y) r5;
        r5 = r5.toString();
    L_0x041e:
        r37 = 0;
        r0 = r37;
        r5 = r5.charAt(r0);
        r0 = r38;
        r0 = r0.h;
        r37 = r0;
        r0 = r37;
        r5 = com.qq.reader.common.utils.ao.a(r5, r0);
        r37 = r32[r9];
        r37 = r37 + r5;
        r37 = (r37 > r21 ? 1 : (r37 == r21 ? 0 : -1));
        if (r37 < 0) goto L_0x0442;
    L_0x043a:
        r5 = r21;
        goto L_0x03a4;
    L_0x043e:
        r5 = " ";
        goto L_0x041e;
    L_0x0442:
        r37 = r32[r9];
        r5 = r5 + r37;
        goto L_0x03a4;
    L_0x0448:
        if (r36 == 0) goto L_0x0479;
    L_0x044a:
        r0 = r38;
        r4 = r0.h;
        r0 = r39;
        r1 = r34;
        r2 = r32;
        r0.drawPosText(r1, r2, r4);
        r0 = r38;
        r4 = r0.h;
        r0 = r30;
        r4.setColor(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r38;
        r5 = r0.n;
        r4.setTypeface(r5);
    L_0x0474:
        r20 = r20 + r28;
        r13 = r6;
        goto L_0x0322;
    L_0x0479:
        r0 = r38;
        r4 = r0.h;
        r0 = r39;
        r1 = r34;
        r2 = r32;
        r0.drawPosText(r1, r2, r4);
        goto L_0x0474;
    L_0x0487:
        r4 = 1;
        if (r11 != r4) goto L_0x04a8;
    L_0x048a:
        if (r25 == 0) goto L_0x04a8;
    L_0x048c:
        if (r13 != 0) goto L_0x0498;
    L_0x048e:
        r4 = r40.h();
        r0 = r38;
        r10 = r0.a(r12, r4);
    L_0x0498:
        r0 = r38;
        r4 = r0.E;
        r4.a(r10);
        r0 = r38;
        r4 = r0.E;
        r0 = r39;
        r4.a(r0);
    L_0x04a8:
        r0 = r38;
        r4 = r0.h;
        r0 = r30;
        r4.setColor(r0);
        r0 = r38;
        r4 = r0.h;
        r0 = r27;
        r4.setTextSize(r0);
    L_0x04ba:
        return;
    L_0x04bb:
        r10 = r4;
        r11 = r5;
        r12 = r6;
        goto L_0x00db;
    L_0x04c0:
        r10 = r4;
        r11 = r5;
        r12 = r6;
        goto L_0x00db;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.readpage.k.a(android.graphics.Canvas, com.qq.reader.readengine.kernel.c.b, format.epub.view.ZLTextElementAreaArrayList, com.qq.reader.module.readpage.n):void");
    }

    public String y() {
        String str = "";
        if (this.d == null) {
            return str;
        }
        IBook t = this.d.d().t();
        if (t != null) {
            return t.getBookShortName();
        }
        return str;
    }

    private String a(IBook iBook, int i) {
        if (iBook == null) {
            return "第" + i + "章";
        }
        OnlineChapter onlineChapter = (OnlineChapter) iBook.getMulitFile().getChapterInfo(i);
        if (onlineChapter != null) {
            return onlineChapter.getChapterName();
        }
        return "第" + i + "章";
    }

    public boolean b(int i, int i2) {
        n d = this.k.d();
        if (d == null || !d.a(i, i2) || this.m == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (!(this.d == null || this.d.d() == null || this.d.d().t() == null)) {
            bundle.putString("AUTHORPAGE_KEY_AUTHORID", this.d.d().t().getAuthorId());
            bundle.putString("AUTHORPAGE_KEY_AUTHOR_NAME", this.d.d().t().getAuthor());
            bundle.putString("AUTHORPAGE_KEY_AVATAR_URL", this.d.d().t().getAuthorIcon());
            bundle.putString("PARA_READER_PAGE_CONTEXT_ONCLICK_EVENT_TYPE", "READER_PAGE_AUTHOR_WORDS_ICON_ONCLICK");
        }
        this.m.a(1002, bundle);
        return true;
    }

    public boolean s() {
        return false;
    }

    public void r() {
        this.r.r();
    }

    public String a(g gVar, g gVar2) {
        return this.r.b(gVar, gVar2);
    }
}
