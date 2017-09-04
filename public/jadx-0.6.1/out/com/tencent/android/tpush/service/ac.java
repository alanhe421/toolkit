package com.tencent.android.tpush.service;

/* compiled from: ProGuard */
class ac implements Comparable {
    public String a = "";
    public float b = 1.0f;
    public long c = 0;

    ac() {
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((ac) obj);
    }

    public int a(ac acVar) {
        if (this.b > acVar.b) {
            return -1;
        }
        if (this.b < acVar.b) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pkgName:").append(this.a).append(",accid:").append(this.c).append(",ver:").append(this.b);
        return stringBuilder.toString();
    }
}
