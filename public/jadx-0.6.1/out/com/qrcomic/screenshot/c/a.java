package com.qrcomic.screenshot.c;

import android.graphics.PointF;
import android.text.TextUtils;
import com.qrcomic.screenshot.d.d;

/* compiled from: QRBubbleLayerBasicInfo */
public class a extends e {
    public PointF a = new PointF();
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public String h;
    public com.qrcomic.screenshot.a.a i;
    public int j;

    public a(PointF pointF, float f, float f2, float f3, float f4, float f5, float f6, String str, com.qrcomic.screenshot.a.a aVar, int i, long j) {
        a(pointF, f, f2, f3, f4, f5, f6, str, aVar, i, j);
    }

    public void a(PointF pointF, float f, float f2, float f3, float f4, float f5, float f6, String str, com.qrcomic.screenshot.a.a aVar, int i, long j) {
        if (pointF != null) {
            this.a.set(pointF);
        }
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
        this.g = f6;
        this.h = str;
        this.i = aVar;
        this.j = i;
        this.k = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return super.equals(obj) && d.a(this.a, aVar.a) && d.a(this.b, aVar.b) && d.a(this.c, aVar.c) && d.a(this.d, aVar.d) && d.a(this.e, aVar.e) && d.a(this.f, aVar.f) && d.a(this.g, aVar.g) && TextUtils.equals(this.h, aVar.h) && (((this.i != null && this.i.equals(aVar.h)) || (aVar.h != null && aVar.h.equals(this.i))) && this.j == aVar.j);
    }
}
