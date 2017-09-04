package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookchapter.online.d;
import com.qq.reader.readengine.model.IBook;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import java.util.List;

/* compiled from: HeaderPagePainter */
public class e {
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private String G;
    private RectF H;
    private TextPaint a = new TextPaint();
    private int b;
    private int c;
    private String d;
    private String e;
    private Context f;
    private float g;
    private float h;
    private IBook i;
    private d j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;

    public e(Context context, float f) {
        this.f = context;
        this.a.setAntiAlias(true);
        this.a.setDither(false);
        this.a.setTypeface(Typeface.SANS_SERIF);
        this.a.setTextSize((float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_17));
        this.a.setColor(WebView.NIGHT_MODE_COLOR);
        this.a.setTextAlign(Align.LEFT);
        this.g = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_bookname_size);
        this.k = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_69);
        this.l = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
        this.A = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_marginbottom);
        this.C = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_landscape_marginleft);
        this.D = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_landscape_marginleft);
        this.y = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_19);
        this.z = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_14);
        this.o = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_35);
        this.B = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_bookname_marginleft);
        this.n = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_marginleft);
        this.o = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_margintop);
        this.r = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_24);
        this.s = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_15);
        this.t = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_16);
        this.u = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.v = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_55);
        this.w = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_80);
        this.x = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_110);
        this.p = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_second_marginleft);
        this.q = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_second_margintop);
        this.E = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_resouce_size);
        this.F = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.headerpage_right_size);
        this.G = this.f.getResources().getString(R.string.rights_info);
        this.m = (float) this.f.getResources().getDimensionPixelOffset(R.dimen.common_dp_1);
        this.h = (float) ((int) this.f.getResources().getDimension(R.dimen.common_dp_18));
    }

    public void a(int i) {
        this.a.setColor(i);
    }

    public void b(int i) {
        this.b = i;
    }

    public void a(Typeface typeface) {
        this.a.setTypeface(typeface);
    }

    public void a(int i, int i2, Canvas canvas, IBook iBook) {
        a(iBook);
        if (iBook.getReadType() != 0 || this.i.getBookNetId() > 0) {
            float f;
            float f2;
            float f3;
            float f4;
            int i3;
            int color = this.a.getColor();
            float f5;
            if (i2 > i) {
                f = this.o;
                f2 = this.n;
                f5 = this.q;
                f3 = this.p;
                f4 = f5;
                i3 = 0;
            } else {
                f = this.n;
                f2 = this.o;
                f5 = this.p;
                f3 = this.q;
                f4 = f5;
                i3 = 1;
            }
            canvas.drawColor(0);
            this.a.setStyle(Style.STROKE);
            this.a.setColor(this.f.getResources().getColor(R.color.headerpage_firstcircle_color));
            this.a.setStrokeWidth(this.m);
            a(i, i2, canvas, f, f2);
            this.a.setColor(this.f.getResources().getColor(R.color.headerpage_secondcircle_color));
            a(i, i2, canvas, f4, f3);
            this.a.setColor(color);
            this.a.setStyle(Style.FILL);
            if (i3 != 0) {
                b(i, i2, canvas);
            } else {
                a(i, i2, canvas);
            }
            this.a.setColor(color);
            return;
        }
        b(i, i2, canvas, iBook);
    }

    private void b(int i, int i2, Canvas canvas, IBook iBook) {
        float f;
        float f2;
        float f3;
        Object obj = 1;
        if (i2 > i) {
            obj = null;
        }
        if (obj != null) {
            f = this.u;
            f2 = this.x;
            f3 = f;
        } else {
            f = this.w;
            f2 = this.v;
            f3 = f;
        }
        this.a.setTextSize(this.g);
        float ascent = this.a.ascent();
        List<char[]> a = ao.a(this.d, this.a, ((float) i2) - (2.0f * f3));
        float descent = (this.a.descent() - ascent) * 1.4f;
        int color = this.a.getColor();
        this.a.setColor(this.b);
        int i3 = 0;
        float f4 = f2;
        for (char[] cArr : a) {
            char[] cArr2;
            Canvas canvas2 = canvas;
            canvas2.drawText(cArr2, 0, cArr2.length, (((float) i2) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f, f4 - ascent, this.a);
            if (i3 == a.size() - 1) {
                f = f4 + (this.a.descent() - ascent);
            } else {
                f = f4 + descent;
            }
            i3++;
            f4 = f;
        }
        this.a.setColor(color);
        f = f4 + this.t;
        this.a.setTextSize(this.s);
        f4 = this.a.ascent();
        List a2 = ao.a(this.e + "  著", this.a, ((float) i2) - (2.0f * f3));
        float descent2 = (this.a.descent() - f4) * 1.4f;
        int i4 = 0;
        float f5 = f;
        while (i4 < a2.size()) {
            cArr2 = (char[]) a2.get(i4);
            canvas2 = canvas;
            canvas2.drawText(cArr2, 0, ((char[]) a2.get(i4)).length, (((float) i2) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f, f5 - f4, this.a);
            i4++;
            f5 += descent2;
        }
    }

    private void a(int i, int i2, Canvas canvas, float f, float f2) {
        a(1, f, f2, this.y, canvas);
        canvas.drawLine((this.y + f) - (this.m / 2.0f), f2, ((((float) i2) - f) - this.y) + (this.m / 2.0f), f2, this.a);
        a(2, ((float) i2) - f, f2, this.y, canvas);
        canvas.drawLine(f, (this.y + f2) - (this.m / 2.0f), f, (((((float) i) - f2) - this.m) - this.y) + (this.m / 2.0f), this.a);
        a(3, f, (((float) i) - f2) - this.m, this.y, canvas);
        Canvas canvas2 = canvas;
        canvas2.drawLine(((float) i2) - f, (this.y + f2) - (this.m / 2.0f), ((float) i2) - f, (this.m / 2.0f) + (((((float) i) - f2) - this.m) - this.y), this.a);
        a(4, ((float) i2) - f, (((float) i) - f2) - this.m, this.y, canvas);
        canvas2 = canvas;
        canvas2.drawLine((this.y + f) - (this.m / 2.0f), (((float) i) - f2) - this.m, (this.m / 2.0f) + ((((float) i2) - f) - this.y), (((float) i) - f2) - this.m, this.a);
    }

    private void a(int i, float f, float f2, float f3, Canvas canvas) {
        float f4;
        float f5;
        float f6;
        float f7 = 0.0f;
        switch (i) {
            case 1:
                f4 = this.m + (f + f3);
                f7 = (f2 + f3) + this.m;
                f5 = f2;
                f6 = f;
                break;
            case 2:
                f5 = (f - f3) - this.m;
                f4 = this.m + (f5 + f3);
                f7 = (f2 + f3) + this.m;
                f6 = f5;
                f5 = f2;
                break;
            case 3:
                f5 = (f2 - f3) - this.m;
                f6 = f;
                f4 = (f + f3) + this.m;
                f7 = f2;
                break;
            case 4:
                f5 = (f2 - f3) - this.m;
                f6 = (f - f3) - this.m;
                f7 = f2;
                f4 = f;
                break;
            default:
                f4 = 0.0f;
                f5 = 0.0f;
                f6 = 0.0f;
                break;
        }
        this.H = new RectF(f6, f5, f4, f7);
        canvas.save();
        canvas.clipRect(this.H);
        canvas.drawCircle(f, f2, f3, this.a);
        canvas.restore();
    }

    private void a(int i, int i2, Canvas canvas) {
        char[] cArr;
        float f = this.C;
        a(canvas, (int) f, (int) ((((float) i) - this.f.getResources().getDimension(R.dimen.headerpage_conver_height)) / 2.0f));
        f += (float) ((int) this.f.getResources().getDimension(R.dimen.headerpage_conver_width));
        float a = (((float) i) - a(f, i2)) / 2.0f;
        float f2 = f + this.r;
        this.a.setTextSize(this.g);
        float ascent = this.a.ascent();
        List<char[]> a2 = ao.a(this.d, this.a, (((float) i2) - f2) - this.A);
        float descent = (this.a.descent() - ascent) * 1.4f;
        int i3 = 0;
        float f3 = a;
        for (char[] cArr2 : a2) {
            Canvas canvas2 = canvas;
            canvas2.drawText(cArr2, 0, cArr2.length, f2 + ((((((float) i2) - f2) - this.A) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f), f3 - ascent, this.a);
            if (i3 == a2.size() - 1) {
                a = f3 + (this.a.descent() - ascent);
            } else {
                a = f3 + descent;
            }
            i3++;
            f3 = a;
        }
        a = f3 + this.z;
        this.a.setTextSize(this.E);
        ascent = this.a.ascent();
        List a3 = ao.a(this.e + " / 作品", this.a, (((float) i2) - f2) - this.D);
        descent = (this.a.descent() - ascent) * 1.4f;
        i3 = 0;
        f3 = a;
        while (i3 < a3.size()) {
            cArr2 = (char[]) a3.get(i3);
            float measureText = f2 + ((((((float) i2) - f2) - this.A) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f);
            canvas.drawText(cArr2, 0, ((char[]) a3.get(i3)).length, measureText, f3 - ascent, this.a);
            i3++;
            f3 += descent;
        }
        if (this.i.getReadType() != 0 || this.i.getBookNetId() > 0) {
            a = f3 + this.k;
            String str = "";
            str = ReaderApplication.getApplicationImp().getString(R.string.default_publish_copyright_info, new Object[]{ReaderApplication.getApplicationImp().getString(R.string.app_name)});
            this.a.setTextSize(this.E);
            ascent = this.a.ascent();
            float descent2 = this.a.descent() - ascent;
            a3 = ao.a(str, this.a, (((float) i2) - f2) - this.A);
            descent = descent2 + this.l;
            i3 = 0;
            f3 = a;
            while (i3 < a3.size()) {
                cArr2 = (char[]) a3.get(i3);
                measureText = f2 + ((((((float) i2) - f2) - this.A) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f);
                canvas.drawText(cArr2, 0, cArr2.length, measureText, f3 + ascent, this.a);
                i3++;
                f3 += descent;
            }
            f = this.z + f3;
            this.a.setColor(this.c);
            this.a.setTextSize(this.F);
            a = this.a.ascent();
            String str2 = this.G;
            canvas.drawText(str2, ((((((float) i2) - f2) - this.A) - this.a.measureText(str2, 0, str2.length())) / 2.0f) + f2, f + a, this.a);
        }
    }

    private float a(float f, int i) {
        int i2;
        int i3 = 0;
        float f2 = f + this.r;
        this.a.setTextSize(this.g);
        float ascent = this.a.ascent();
        List a = ao.a(this.d, this.a, (((float) i) - f2) - this.A);
        float descent = (this.a.descent() - ascent) * 1.4f;
        int i4 = 0;
        int i5 = 0;
        for (i2 = 0; i2 < a.size(); i2++) {
            if (i4 == a.size() - 1) {
                i5 = (int) (((float) i5) + (this.a.descent() - ascent));
            } else {
                i5 = (int) (((float) i5) + descent);
            }
            i4++;
        }
        i2 = (int) (((float) i5) + this.z);
        this.a.setTextSize(this.E);
        float ascent2 = this.a.ascent();
        ascent = (this.a.descent() - ascent2) * 1.4f;
        i4 = i2;
        for (i2 = 0; i2 < ao.a(this.e + " / 作品", this.a, (((float) i) - f2) - this.D).size(); i2++) {
            i4 = (int) (((float) i4) + ascent);
        }
        i2 = (int) (((float) i4) + this.k);
        String str = "";
        str = ReaderApplication.getApplicationImp().getString(R.string.default_publish_copyright_info, new Object[]{ReaderApplication.getApplicationImp().getString(R.string.app_name)});
        this.a.setTextSize(this.E);
        float descent2 = this.a.descent() - this.a.ascent();
        descent2 += this.l;
        while (i3 < ao.a(str, this.a, (((float) i) - f2) - this.A).size()) {
            i2 = (int) (((float) i2) + descent2);
            i3++;
        }
        i2 = (int) (((float) i2) + this.z);
        this.a.setTextSize(this.F);
        return ((float) i2) + this.a.ascent();
    }

    private void b(int i, int i2, Canvas canvas) {
        int dimension = (int) this.f.getResources().getDimension(R.dimen.headerpage_conver_margintop);
        a(canvas, (i2 - ((int) this.f.getResources().getDimension(R.dimen.headerpage_conver_width))) / 2, dimension);
        dimension += (int) this.f.getResources().getDimension(R.dimen.headerpage_conver_height);
        float f = this.B;
        this.a.setTextSize(this.g);
        float ascent = this.a.ascent();
        List<char[]> a = ao.a(this.d, this.a, ((float) i2) - (f * 2.0f));
        f = this.h;
        int i3 = (int) (((float) dimension) + this.h);
        float descent = (this.a.descent() - ascent) * 1.4f;
        int color = this.a.getColor();
        this.a.setColor(color);
        int i4 = 0;
        int i5 = i3;
        for (char[] cArr : a) {
            char[] cArr2;
            Canvas canvas2 = canvas;
            canvas2.drawText(cArr2, 0, cArr2.length, (((float) i2) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f, ((float) i5) - ascent, this.a);
            if (i4 == a.size() - 1) {
                i3 = (int) (((float) i5) + (this.a.descent() - ascent));
            } else {
                i3 = (int) (((float) i5) + descent);
            }
            i4++;
            i5 = i3;
        }
        this.a.setColor(color);
        i3 = (int) (((float) i5) + this.z);
        float f2 = this.B;
        this.a.setTextSize(this.E);
        ascent = this.a.ascent();
        List a2 = ao.a(this.e + " / 作品", this.a, ((float) i2) - (f2 * 2.0f));
        descent = (this.a.descent() - ascent) * 1.4f;
        i4 = 0;
        i5 = i3;
        while (i4 < a2.size()) {
            cArr2 = (char[]) a2.get(i4);
            canvas2 = canvas;
            canvas2.drawText(cArr2, 0, ((char[]) a2.get(i4)).length, (((float) i2) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f, ((float) i5) - ascent, this.a);
            i4++;
            i5 = (int) (((float) i5) + descent);
        }
        if (this.i.getReadType() != 0 || this.i.getBookNetId() > 0) {
            dimension = (i - ((int) this.A)) - ((int) (this.a.descent() - this.a.ascent()));
            this.a.setColor(this.c);
            this.a.setTextSize(this.F);
            String str = this.G;
            canvas.drawText(str, (((float) i2) - this.a.measureText(str, 0, str.length())) / 2.0f, (float) dimension, this.a);
            float f3 = this.B;
            String string = ReaderApplication.getApplicationImp().getString(R.string.default_publish_copyright_info, new Object[]{ReaderApplication.getApplicationImp().getString(R.string.app_name)});
            this.a.setColor(color);
            this.a.setTextSize(this.E);
            float descent2 = this.a.descent() - this.a.ascent();
            i3 = (int) (((float) dimension) - (this.z + descent2));
            List a3 = ao.a(string, this.a, ((float) i2) - (f3 * 2.0f));
            float f4 = descent2 + this.l;
            i4 = a3.size() - 1;
            i5 = i3;
            while (i4 >= 0) {
                cArr2 = (char[]) a3.get(i4);
                canvas2 = canvas;
                canvas2.drawText(cArr2, 0, cArr2.length, (((float) i2) - this.a.measureText(cArr2, 0, cArr2.length)) / 2.0f, (float) i5, this.a);
                i4--;
                i5 = (int) (((float) i5) - f4);
            }
        }
    }

    private void a(Canvas canvas, int i, int i2) {
        Bitmap headPageBitmap = this.i.getHeadPageBitmap();
        if (headPageBitmap != null && !headPageBitmap.isRecycled()) {
            canvas.drawBitmap(headPageBitmap, (float) i, (float) i2, this.a);
        }
    }

    private void a(IBook iBook) {
        this.i = iBook;
        this.j = iBook.getBookTailInfo();
        this.d = iBook.getBookShortName();
        if (this.d == null) {
            this.d = "";
        }
        this.e = iBook.getAuthor();
        if (this.e == null || this.e.trim().length() == 0) {
            this.e = "匿名";
        }
    }

    public void c(int i) {
        this.c = i;
    }
}
