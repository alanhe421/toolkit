package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.d.h;
import com.xiaomi.network.e;
import com.xiaomi.network.f;
import com.xiaomi.network.f.b;
import com.xiaomi.push.thrift.a;
import java.io.IOException;
import java.util.ArrayList;

class p$b extends f {
    protected p$b(Context context, e eVar, b bVar, String str) {
        super(context, eVar, bVar, str);
    }

    protected String a(ArrayList<String> arrayList, String str, String str2, boolean z) {
        try {
            if (com.xiaomi.d.f.a().c()) {
                str2 = v.e();
            }
            return super.a(arrayList, str, str2, z);
        } catch (IOException e) {
            IOException iOException = e;
            h.a(0, a.r.a(), 1, null, d.d(c) ? 1 : 0);
            throw iOException;
        }
    }
}
