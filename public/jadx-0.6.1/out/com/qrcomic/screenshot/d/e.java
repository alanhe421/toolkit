package com.qrcomic.screenshot.d;

import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import com.qrcomic.screenshot.a.b;
import com.qrcomic.util.g;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: QRTextLayoutUtil */
public class e {
    public static final float a = b.b(40.0f);
    public static final float b = b.b(10.0f);
    public static final float c = ((a - b) / 6.0f);

    /* compiled from: QRTextLayoutUtil */
    public static class a {
        public String a;
        public float b;
        public float c;
        public float d;
        public int e;
        public float f;
    }

    private static int a(String str, SparseArray<String> sparseArray) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int length = str.length();
        Matcher matcher = Pattern.compile("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]").matcher(str);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            sparseArray.put(start - i, str.substring(start, end));
            i += (end - start) - 1;
        }
        return length - i;
    }

    private static String a(String str, SparseArray<String> sparseArray, int i, int i2) {
        int i3 = i2;
        int i4 = i;
        for (int i5 = 0; i5 < sparseArray.size(); i5++) {
            int keyAt = sparseArray.keyAt(i5);
            int length = ((String) sparseArray.valueAt(i5)).length() - 1;
            if (keyAt < i) {
                i4 += length;
            }
            if (keyAt < i2) {
                i3 += length;
            }
        }
        if (g.a()) {
            g.a("QRTextLayoutUtil", g.d, "dst = [" + i + " , " + i2 + "] , src = [" + i4 + " , " + i3 + "]");
        }
        if (i4 >= str.length()) {
            i4 = str.length() - 1;
        }
        if (i3 > str.length()) {
            i3 = str.length();
        }
        return str.substring(i4, i3);
    }

    public static float a(String str, float f, float f2, List<a> list, float f3, boolean z) {
        if (TextUtils.isEmpty(str) || list == null) {
            return f2;
        }
        float f4;
        float f5;
        SparseArray sparseArray = new SparseArray();
        int a = a(str, sparseArray);
        if (g.a()) {
            g.a("QRTextLayoutUtil", g.d, "srcStringLen = " + str.length() + " , dstStringLen = " + a + " , emojiArray.size = " + sparseArray.size());
        }
        float f6 = a;
        if (a >= 7) {
            f4 = b;
        } else {
            f4 = f6 - (((float) (a - 1)) * c);
        }
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(f4);
        FontMetrics fontMetrics = new FontMetrics();
        textPaint.getFontMetrics(fontMetrics);
        float f7 = fontMetrics.descent - fontMetrics.ascent;
        list.clear();
        String property = System.getProperty("line.separator", "\n");
        float f8 = 0.0f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < a) {
            String a2 = a(str, sparseArray, i2, i2 + 1);
            if (a2.equals(property)) {
                i++;
                a aVar = new a();
                aVar.a = a(str, sparseArray, i2 - i3, i2);
                list.add(aVar);
                i3 = 0;
                f5 = 0.0f;
            } else {
                f5 = textPaint.measureText(a2);
                if (f8 + f5 <= f) {
                    f5 += f8;
                    i3++;
                } else {
                    i++;
                    a aVar2 = new a();
                    aVar2.a = a(str, sparseArray, i2 - i3, i2);
                    list.add(aVar2);
                    i3 = 1;
                }
            }
            i2++;
            f8 = f5;
        }
        if (i3 != 0) {
            i++;
            aVar = new a();
            aVar.a = a(str, sparseArray, a - i3, a);
            list.add(aVar);
        }
        i2 = i;
        f5 = i2 > ((int) (f2 / f7)) ? ((float) i2) * f7 : (!z || i2 >= ((int) (f2 / f7))) ? f2 : Math.max(((float) i2) * f7, f3);
        if (g.a()) {
            g.a("QRTextLayoutUtil", g.d, "lineCount = " + i2 + " , height = " + f2 + " , newHeight = " + f5);
        }
        f8 = ((f5 - ((((float) i2) * 1.0f) * f7)) / 2.0f) + Math.abs(fontMetrics.ascent);
        if (list.size() == 1) {
            a aVar3 = (a) list.get(0);
            aVar3.d = f4;
            aVar3.b = (f - textPaint.measureText(aVar3.a)) / 2.0f;
            aVar3.c = f8;
            aVar3.f = ((float) i2) * f7;
            return f5;
        }
        for (i = 0; i < list.size(); i++) {
            aVar3 = (a) list.get(i);
            aVar3.d = f4;
            aVar3.b = 0.0f;
            aVar3.c = (((float) i) * f7) + f8;
            aVar3.f = ((float) i2) * f7;
        }
        return f5;
    }

    public static float b(String str, float f, float f2, List<a> list, float f3, boolean z) {
        if (TextUtils.isEmpty(str) || list == null) {
            return f;
        }
        float f4;
        int i;
        a aVar;
        SparseArray sparseArray = new SparseArray();
        int a = a(str, sparseArray);
        if (g.a()) {
            g.a("QRTextLayoutUtil", g.d, "srcStringLen = " + str.length() + " , dstStringLen = " + a + " , emojiArray.size = " + sparseArray.size());
        }
        float f5 = a;
        if (a >= 7) {
            f4 = b;
        } else {
            f4 = f5 - (((float) (a - 1)) * c);
        }
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(f4);
        FontMetrics fontMetrics = new FontMetrics();
        textPaint.getFontMetrics(fontMetrics);
        float f6 = fontMetrics.descent - fontMetrics.ascent;
        float measureText = textPaint.measureText("器") + b.b(1.0f);
        list.clear();
        String property = System.getProperty("line.separator", "\n");
        f5 = Math.abs(fontMetrics.ascent) - f6;
        float f7 = 0.0f;
        int i2 = 1;
        for (i = 0; i < a; i++) {
            String a2 = a(str, sparseArray, i, i + 1);
            if (a2.equals(property)) {
                i2++;
                f7 = 0.0f;
                f5 = Math.abs(fontMetrics.ascent) - f6;
            } else if (f7 + f6 <= f2) {
                f7 += f6;
                a aVar2 = new a();
                aVar2.a = a2;
                f5 += f6;
                aVar2.c = f5;
                aVar2.e = i2;
                list.add(aVar2);
            } else {
                int i3 = i2 + 1;
                a aVar3 = new a();
                aVar3.a = a2;
                f5 = Math.abs(fontMetrics.ascent);
                aVar3.c = f5;
                aVar3.e = i3;
                list.add(aVar3);
                i2 = i3;
                f7 = f6;
            }
        }
        f7 = i2 > ((int) (f / measureText)) ? ((float) i2) * measureText : (!z || i2 >= ((int) (f / measureText))) ? f : Math.max(((float) i2) * measureText, f3);
        if (g.a()) {
            g.a("QRTextLayoutUtil", g.d, "listCount = " + i2 + " , width = " + f + " , newWidth = " + f7);
        }
        float f8 = (f7 - ((f7 - ((((float) i2) * 1.0f) * measureText)) / 2.0f)) - measureText;
        if (i2 == 1 && list.size() > 0) {
            float size = ((f2 - (((float) list.size()) * f6)) / 2.0f) + Math.abs(fontMetrics.ascent);
            for (i = 0; i < list.size(); i++) {
                aVar = (a) list.get(i);
                if (aVar.e == 1) {
                    aVar.c = (((float) i) * f6) + size;
                }
            }
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            aVar = (a) list.get(i4);
            aVar.d = f4;
            aVar.b = f8 - (((float) (aVar.e - 1)) * measureText);
            aVar.f = ((float) i2) * measureText;
        }
        return f7;
    }
}
