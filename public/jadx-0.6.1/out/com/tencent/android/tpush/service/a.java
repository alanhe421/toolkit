package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.b.j;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.c;
import com.tencent.android.tpush.service.d.f;
import com.tencent.mid.api.MidEntity;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    public static final String a = a.class.getSimpleName();
    private static a b = null;
    private static volatile j c = null;
    private static volatile l d = null;

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    public synchronized void a(Context context) {
        if (context != null) {
            IntentFilter intentFilter;
            try {
                if (c == null) {
                    c = new j(this, null);
                    intentFilter = new IntentFilter();
                    intentFilter.addDataScheme("package");
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                    context.registerReceiver(c, intentFilter);
                }
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(a, "registerReceiver", e);
            }
            try {
                if (d == null) {
                    d = new l(this, null);
                    intentFilter = new IntentFilter();
                    intentFilter.addAction("com.tencent.android.tpush.action.REGISTER");
                    intentFilter.addAction("com.tencent.android.tpush.action.UNREGISTER");
                    intentFilter.addAction("com.tencent.android.tpush.action.ENABLE_DEBUG");
                    intentFilter.addAction("com.tencent.android.tpush.action.MSG_ACK");
                    intentFilter.addAction("com.tencent.android.tpush.action.TAG");
                    intentFilter.addAction("com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
                    intentFilter.addAction("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT");
                    intentFilter.addAction("com.tencent.android.tpush.action.CUSTOM_NOTIFICATION");
                    intentFilter.addAction("com.tencent.android.tpush.action.CLEAR_CUSTOM_NOTIFICATION");
                    intentFilter.addAction("com.tencent.android.tpush.action.ack.sdk2srv");
                    intentFilter.addAction("com.tencent.android.tpush.action.reserved.act");
                    intentFilter.addAction("com.tencent.android.tpush.action.UPDATE_OTHER_PUSH_TOKEN");
                    context.registerReceiver(d, intentFilter);
                }
            } catch (Throwable e2) {
                com.tencent.android.tpush.a.a.c(a, "registerReceiver", e2);
            }
            Intent intent = new Intent("com.tencent.android.tpush.action.SERVICE_START");
            intent.putExtra("pkg", m.e().getPackageName());
            intent.putExtra(MidEntity.TAG_VER, Constants.PUSH_SDK_VERSION);
            m.e().sendBroadcast(intent);
        }
    }

    public void b(Context context) {
        if (context != null) {
            if (c != null) {
                p.a(context, c);
                c = null;
            }
            if (d != null) {
                p.a(context, d);
                d = null;
            }
        }
    }

    private void a(Context context, Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && context != null && f.d(context, dataString.substring(8))) {
            m.a().d();
            g.a().a(new b(this, context), 2000);
        }
    }

    private void b(Context context, Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && context != null) {
            g.a().a(new c(this, context, dataString.substring(8)), 30000);
        }
    }

    private void c(Context context) {
        Object obj = 1;
        if (com.tencent.android.tpush.service.d.a.a(context) == (byte) 3) {
            long currentTimeMillis = System.currentTimeMillis();
            long c = f.c(context, "com.tencent.android.tpush.action.next.applist.ts", 0);
            if (c == 0) {
                c = m.a(context, "com.tencent.android.tpush.action.next.applist.ts", 0);
            }
            if (c != 0 && currentTimeMillis <= c && Math.abs(c - currentTimeMillis) <= 172800000) {
                obj = null;
            }
            if (obj != null) {
                g.a().a(new d(this, context, currentTimeMillis), 5000);
            }
        }
    }

    private void c(Context context, Intent intent) {
        if (context != null && intent != null) {
            String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            String decrypt2 = Rijndael.decrypt(intent.getStringExtra("accKey"));
            String decrypt3 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            String decrypt4 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_ACCOUNT));
            String decrypt5 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TICKET));
            int intExtra = intent.getIntExtra(Constants.FLAG_TICKET_TYPE, -1);
            String decrypt6 = Rijndael.decrypt(intent.getStringExtra("qua"));
            String stringExtra = intent.getStringExtra("appVer");
            String decrypt7 = Rijndael.decrypt(intent.getStringExtra("reserved"));
            boolean booleanExtra = intent.getBooleanExtra("aidl", false);
            try {
                if (!f.a(decrypt6)) {
                    CacheManager.setQua(context, Long.parseLong(decrypt), decrypt6);
                }
                String encryptAPKSignature = TpnsSecurity.getEncryptAPKSignature(context.createPackageContext(decrypt3, 0));
                p.a().a(Long.parseLong(decrypt), decrypt2, c.a(), decrypt4, decrypt5, intExtra, encryptAPKSignature, stringExtra, decrypt7, new f(this, decrypt, decrypt3, booleanExtra, context));
                XGWatchdog.getInstance(context).sendAllLocalXGAppList();
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.h(a, ">> register error " + e);
                com.tencent.android.tpush.a.a.h(a, ">> register error-> " + Log.getStackTraceString(e));
            }
        }
    }

    private void d(Context context, Intent intent) {
        if (context != null && intent != null) {
            long longExtra = intent.getLongExtra("accId", -1);
            String decrypt = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            int intExtra = intent.getIntExtra(Constants.FLAG_TAG_TYPE, -1);
            String decrypt2 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TAG_NAME));
            p.a().a(longExtra, decrypt, intExtra, decrypt2, new g(this, longExtra, intExtra, decrypt2, decrypt));
        }
    }

    private void e(Context context, Intent intent) {
        if (context != null && intent != null) {
            String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            String decrypt2 = Rijndael.decrypt(intent.getStringExtra("accKey"));
            String decrypt3 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            String decrypt4 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TOKEN));
            CacheManager.UnregisterInfoByPkgName(decrypt3);
            try {
                p.a().a(decrypt4, c.a(), Long.parseLong(decrypt), decrypt2, decrypt3, new h(this, decrypt, decrypt3));
            } catch (Exception e) {
                com.tencent.android.tpush.a.a.h(a, ">>> unregister error " + e);
            }
        }
    }

    private void f(Context context, Intent intent) {
        if (context != null && intent != null) {
            String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            p.a().a(Long.parseLong(decrypt), Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TOKEN)), Rijndael.decrypt(intent.getStringExtra("other_push_type")), Rijndael.decrypt(intent.getStringExtra("other_push_token")), new i(this, decrypt));
        }
    }

    private void g(Context context, Intent intent) {
        if (intent != null && context != null) {
            boolean booleanExtra = intent.getBooleanExtra("debugMode", false);
            XGPushConfig.enableDebug = booleanExtra;
            if (booleanExtra) {
                com.tencent.android.tpush.a.a.a(2);
            } else {
                com.tencent.android.tpush.a.a.a(3);
            }
        }
    }

    private void a(int i, TpnsRegisterReq tpnsRegisterReq, String str, String str2) {
        Intent a = f.a(i, str2, 1);
        a.putExtra("accId", tpnsRegisterReq.accessId);
        if (!(tpnsRegisterReq.account == null || tpnsRegisterReq.account.length() == 0)) {
            a.putExtra(Constants.FLAG_ACCOUNT, tpnsRegisterReq.account);
        }
        if (!(str == null || str.length() == 0)) {
            a.putExtra(Constants.FLAG_TOKEN, str);
        }
        if (!(tpnsRegisterReq.ticket == null || tpnsRegisterReq.ticket.length() == 0)) {
            a.putExtra(Constants.FLAG_TICKET, tpnsRegisterReq.ticket);
            a.putExtra(Constants.FLAG_TICKET_TYPE, tpnsRegisterReq.ticketType);
        }
        if (!(tpnsRegisterReq.deviceId == null || tpnsRegisterReq.deviceId.length() == 0)) {
            a.putExtra(Constants.FLAG_DEVICE_ID, tpnsRegisterReq.deviceId);
        }
        m.e().sendBroadcast(a);
    }

    private void a(int i, String str) {
        m.e().sendBroadcast(f.a(i, str, 2));
    }

    private void a(int i, String str, int i2, String str2) {
        Intent a = f.a(i, str2, 3);
        a.putExtra(Constants.FLAG_TAG_NAME, Rijndael.encrypt(str));
        a.putExtra(Constants.FLAG_TAG_TYPE, i2);
        m.e().sendBroadcast(a);
    }

    private void a(int i, TpnsRegisterRsp tpnsRegisterRsp, TpnsRegisterReq tpnsRegisterReq, com.tencent.android.tpush.service.channel.a aVar, String str, boolean z) {
        Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT");
        intent.putExtra("data", tpnsRegisterRsp.token);
        intent.putExtra("flag", 0);
        intent.putExtra("code", i);
        intent.putExtra("operation", 0);
        com.tencent.android.tpush.data.a aVar2 = new com.tencent.android.tpush.data.a();
        aVar2.a = tpnsRegisterReq.accessId;
        aVar2.b = tpnsRegisterReq.accessKey;
        aVar2.c = tpnsRegisterRsp.token;
        aVar2.d = str;
        CacheManager.addRegisterInfo(aVar2);
        CacheManager.addRegisterInfos(str);
        j.a().a(m.e(), tpnsRegisterReq.accessId, str);
        CacheManager.setToken(m.e(), tpnsRegisterRsp.token);
        if (!f.a(str)) {
            intent.setPackage(str);
        }
        m.e().sendBroadcast(intent);
        a(i, tpnsRegisterReq, tpnsRegisterRsp.token, str);
        b.a().a(false);
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("aidl", z);
            jSONObject.toString();
        } catch (JSONException e) {
        }
    }

    private void a(int i, String str, TpnsRegisterReq tpnsRegisterReq, com.tencent.android.tpush.service.channel.a aVar, String str2) {
        Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT");
        intent.putExtra("data", "");
        intent.putExtra("code", i);
        intent.putExtra(SocialConstants.PARAM_SEND_MSG, str);
        intent.putExtra("flag", 0);
        intent.putExtra("operation", 1);
        if (!f.a(str2)) {
            intent.setPackage(str2);
        }
        m.e().sendBroadcast(intent);
        a(i, tpnsRegisterReq, tpnsRegisterReq.token, str2);
    }

    private void a(int i, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar, String str) {
        Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT");
        intent.putExtra("flag", 0);
        intent.putExtra("operation", 0);
        CacheManager.UnregisterInfoSuccessByPkgName(str);
        CacheManager.removeRegisterInfos(str);
        j.a().a(m.e(), str);
        if (!p.b(str)) {
            intent.setPackage(str);
        }
        m.e().sendBroadcast(intent);
        a(i, str);
    }

    private void a(int i, String str, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar, String str2) {
        com.tencent.android.tpush.a.a.h(a, "unregisterFailHandler failed with (" + i + "," + str + "," + tpnsUnregisterReq + "," + aVar + "," + str2 + ")");
        Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT");
        intent.putExtra("flag", 0);
        intent.putExtra("code", i);
        intent.putExtra(SocialConstants.PARAM_SEND_MSG, str);
        intent.putExtra("operation", 1);
        if (!p.b(str2)) {
            intent.setPackage(str2);
        }
        m.e().sendBroadcast(intent);
        a(i, str2);
    }
}
