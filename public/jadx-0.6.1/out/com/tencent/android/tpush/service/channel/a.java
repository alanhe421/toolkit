package com.tencent.android.tpush.service.channel;

import android.util.SparseArray;

/* compiled from: ProGuard */
public class a {
    private static a b = new a();
    private SparseArray a = new SparseArray();

    public static a a() {
        return b;
    }

    public a(Object... objArr) {
        for (int i = 0; i < objArr.length; i += 2) {
            this.a.put(((Integer) objArr[i]).intValue(), objArr[i + 1]);
        }
    }

    public void a(int i, Object obj) {
        this.a.put(i, obj);
    }

    public boolean b() {
        return ((Boolean) this.a.get(2, Boolean.valueOf(false))).booleanValue();
    }

    public long c() {
        return ((Long) this.a.get(3, Long.valueOf(0))).longValue();
    }

    public String d() {
        return (String) this.a.get(0, "");
    }

    public int e() {
        return ((Integer) this.a.get(1, Integer.valueOf(0))).intValue();
    }
}
