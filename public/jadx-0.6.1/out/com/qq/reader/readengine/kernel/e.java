package com.qq.reader.readengine.kernel;

import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.VoteViewGroup;

/* compiled from: QTextLine */
public class e {
    private String a;
    private int b;
    private boolean c;
    private float d;
    private float e;
    private float f;

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
        switch (a()) {
            case 100:
            case 101:
            case 103:
                a((float) VoteViewGroup.b);
                return;
            case 105:
                a((float) ao.a(50.0f));
                return;
            case 1000:
                a((float) ao.a(77.0f));
                return;
            default:
                return;
        }
    }

    public boolean b() {
        return this.b == 0 || this.b == 1 || this.b == 10;
    }

    public boolean c() {
        return this.b == 103 || this.b == 100 || this.b == 101;
    }

    public e(String str) {
        this.a = str;
    }

    public String d() {
        return this.a;
    }

    public float e() {
        return this.d;
    }

    public void a(float f) {
        this.d = f;
    }

    public float f() {
        return this.e;
    }

    public void b(float f) {
        this.e = f;
    }

    public boolean g() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean h() {
        return this.b == 10;
    }

    public boolean i() {
        return this.b == 1;
    }

    public float j() {
        return this.f;
    }

    public void c(float f) {
        this.f = f;
    }
}
