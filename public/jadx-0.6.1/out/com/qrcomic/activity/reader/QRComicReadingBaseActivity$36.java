package com.qrcomic.activity.reader;

import com.qrcomic.a.d;
import com.qrcomic.util.e;

class QRComicReadingBaseActivity$36 extends d {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ int g;
    final /* synthetic */ int h;
    final /* synthetic */ boolean i;
    final /* synthetic */ QRComicReadingBaseActivity j;

    QRComicReadingBaseActivity$36(QRComicReadingBaseActivity qRComicReadingBaseActivity, String str, String str2, String str3, int i, String str4, String str5, int i2, int i3, boolean z) {
        this.j = qRComicReadingBaseActivity;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = str4;
        this.f = str5;
        this.g = i2;
        this.h = i3;
        this.i = z;
    }

    public void run() {
        try {
            if (this.j.v != null && this.j.a != null && this.j.Z != null && this.j.Z.i != null) {
                this.j.v.a(this.j.a.a(), this.a, this.b, this.c, this.d, this.e, this.f, this.g, e.a(), this.h, this.j.Z.i.m, this.i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
