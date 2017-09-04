package com.xiaomi.xmpush.thrift;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.f;
import com.xiaomi.push.service.z;
import org.apache.thrift.a;
import org.apache.thrift.e;
import org.apache.thrift.g;
import org.apache.thrift.protocol.k;

public class ar {
    public static short a(Context context, ac acVar) {
        int i = 0;
        int a = (f.a(context) ? 8 : 0) + ((0 + b.d(context, acVar.f).a()) + (f.b(context) ? 4 : 0));
        if (z.a(context, acVar)) {
            i = 16;
        }
        return (short) (a + i);
    }

    public static short a(boolean z, boolean z2, boolean z3) {
        int i = 0;
        int i2 = (z2 ? 2 : 0) + (0 + (z ? 4 : 0));
        if (z3) {
            i = 1;
        }
        return (short) (i + i2);
    }

    public static <T extends a<T, ?>> void a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new org.apache.thrift.f("the message byte is empty.");
        }
        new e(new k.a(true, true, bArr.length)).a(t, bArr);
    }

    public static <T extends a<T, ?>> byte[] a(T t) {
        byte[] bArr = null;
        if (t != null) {
            try {
                bArr = new g(new org.apache.thrift.protocol.a.a()).a(t);
            } catch (Throwable e) {
                c.a("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }
}
