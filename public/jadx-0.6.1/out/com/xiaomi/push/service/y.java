package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.p;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.a;

public class y {
    private static String a = (d.a(5) + "-");
    private static AtomicLong b = new AtomicLong(0);

    public static String a() {
        return a + b.incrementAndGet();
    }

    public static ArrayList<af> a(List<e> list, String str, String str2, int i) {
        if (list == null) {
            c.d("requests can not be null in TinyDataHelper.transToThriftObj().");
            return null;
        } else if (list.size() == 0) {
            c.d("requests.length is 0 in TinyDataHelper.transToThriftObj().");
            return null;
        } else {
            ArrayList<af> arrayList = new ArrayList();
            int i2 = 0;
            a dVar = new com.xiaomi.xmpush.thrift.d();
            for (int i3 = 0; i3 < list.size(); i3++) {
                e eVar = (e) list.get(i3);
                if (eVar != null) {
                    int length = ar.a(eVar).length;
                    if (length > i) {
                        c.d("TinyData is too big, ignore upload request." + eVar.toString());
                    } else {
                        if (i2 + length > i) {
                            af afVar = new af(a(), false);
                            afVar.d(str);
                            afVar.b(str2);
                            afVar.c(p.UploadTinyData.T);
                            afVar.a(com.xiaomi.channel.commonutils.a.a.a(ar.a(dVar)));
                            arrayList.add(afVar);
                            dVar = new com.xiaomi.xmpush.thrift.d();
                            i2 = 0;
                        }
                        dVar.a(eVar);
                        i2 += length;
                    }
                }
            }
            if (dVar.a() != 0) {
                af afVar2 = new af(a(), false);
                afVar2.d(str);
                afVar2.b(str2);
                afVar2.c(p.UploadTinyData.T);
                afVar2.a(com.xiaomi.channel.commonutils.a.a.a(ar.a(dVar)));
                arrayList.add(afVar2);
            }
            return arrayList;
        }
    }

    private static void a(Context context, e eVar) {
        c.c("TinyDataHelper.upload(Context, ClientUploadItem); " + eVar);
        Intent intent = new Intent("com.xiaomi.mipush.SEND_TINYDATA");
        intent.putExtra("mipush_payload", ar.a(eVar));
        intent.setComponent(new ComponentName(context, "com.xiaomi.mipush.sdk.PushMessageHandler"));
        try {
            context.startService(intent);
        } catch (Throwable e) {
            c.a(e);
        }
    }

    public static void a(Context context, String str, String str2, long j, String str3) {
        e eVar = new e();
        eVar.d(str);
        eVar.c(str2);
        eVar.a(j);
        eVar.b(str3);
        eVar.a("push_sdk_channel");
        eVar.e(context.getPackageName());
        eVar.c(true);
        eVar.b(System.currentTimeMillis());
        eVar.f(a());
        a(context, eVar);
    }

    public static boolean a(e eVar, boolean z) {
        if (eVar == null) {
            c.a("item is null, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!z && TextUtils.isEmpty(eVar.a)) {
            c.a("item.channel is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(eVar.g)) {
            c.a("item.category is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(eVar.c)) {
            c.a("item.name is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!d.d(eVar.g)) {
            c.a("item.category can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!d.d(eVar.c)) {
            c.a("item.name can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (eVar.b == null || eVar.b.length() <= 10240) {
            return false;
        } else {
            c.a("item.data is too large(" + eVar.b.length() + "), max size for data is " + 10240 + " , verfiy ClientUploadDataItem failed.");
            return true;
        }
    }
}
