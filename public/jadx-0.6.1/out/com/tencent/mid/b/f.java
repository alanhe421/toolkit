package com.tencent.mid.b;

import android.content.Context;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.a;
import com.tencent.mid.util.Util;

public abstract class f {
    protected static com.tencent.mid.util.f b = Util.getLogger();
    protected Context c = null;
    protected int d = 0;

    protected f(Context context, int i) {
        this.c = context;
        this.d = i;
    }

    private void b(String str) {
        if (b()) {
            a(c(str));
        }
    }

    private String k() {
        return b() ? d(c()) : null;
    }

    public abstract int a();

    public void a(MidEntity midEntity) {
        if (midEntity != null) {
            if (a() == 4) {
                a.a(this.c).a(midEntity.getMid());
            }
            b(midEntity.toString());
        }
    }

    protected abstract void a(a aVar);

    protected abstract void a(String str);

    public void b(a aVar) {
        if (aVar != null && b()) {
            a(aVar);
        }
    }

    protected abstract boolean b();

    protected abstract String c();

    protected String c(String str) {
        return Util.encode(str);
    }

    protected abstract a d();

    protected String d(String str) {
        return Util.decode(str);
    }

    public String e() {
        return this.d == 0 ? Util.decode("6X8Y4XdM2Vhvn0I=") : Util.decode("6X8Y4XdM2Vhvn0I=") + this.d;
    }

    public String f() {
        return this.d == 0 ? Util.decode("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=") : Util.decode("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=") + this.d;
    }

    public String g() {
        return this.d == 0 ? Util.decode("4kU71lN96TJUomD1vOU9lgj9U+kKmxDPLVM+zzjst5U=") : Util.decode("4kU71lN96TJUomD1vOU9lgj9U+kKmxDPLVM+zzjst5U=") + this.d;
    }

    protected String h() {
        return this.d == 0 ? Util.decode("4kU71lN96TJUomD1vOU9lgj9Tw==") : Util.decode("4kU71lN96TJUomD1vOU9lgj9Tw==") + this.d;
    }

    public MidEntity i() {
        String k = k();
        return k != null ? MidEntity.parse(k) : null;
    }

    public a j() {
        return b() ? d() : null;
    }
}
