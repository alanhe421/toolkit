package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.y;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.e;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PushMessageHandler extends IntentService {
    private static List<com.xiaomi.mipush.sdk.c.a> a = new ArrayList();

    interface a extends Serializable {
    }

    public PushMessageHandler() {
        super("mipush message handler");
    }

    protected static void a() {
        synchronized (a) {
            a.clear();
        }
    }

    public static void a(long j, String str, String str2) {
        synchronized (a) {
            for (com.xiaomi.mipush.sdk.c.a a : a) {
                a.a(j, str, str2);
            }
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        synchronized (a) {
            for (com.xiaomi.mipush.sdk.c.a aVar : a) {
                if (a(miPushMessage.getCategory(), aVar.a())) {
                    aVar.a(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    aVar.a(miPushMessage);
                }
            }
        }
    }

    public static void a(Context context, a aVar) {
        String str = null;
        if (aVar instanceof MiPushMessage) {
            a(context, (MiPushMessage) aVar);
        } else if (aVar instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) aVar;
            String command = miPushCommandMessage.getCommand();
            List commandArguments;
            if ("register".equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                if (!(commandArguments == null || commandArguments.isEmpty())) {
                    str = (String) commandArguments.get(0);
                }
                a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if ("set-alias".equals(command) || "unset-alias".equals(command) || "accept-time".equals(command)) {
                a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if ("subscribe-topic".equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            } else if ("unsubscibe-topic".equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            }
        }
    }

    protected static void a(Context context, String str, long j, String str2, String str3) {
        synchronized (a) {
            for (com.xiaomi.mipush.sdk.c.a aVar : a) {
                if (a(str, aVar.a())) {
                    aVar.b(j, str2, str3);
                }
            }
        }
    }

    protected static void a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (a) {
            for (com.xiaomi.mipush.sdk.c.a aVar : a) {
                if (a(str, aVar.a())) {
                    aVar.a(str2, j, str3, (List) list);
                }
            }
        }
    }

    private void a(Intent intent, ResolveInfo resolveInfo) {
        try {
            MessageHandleService.a(new com.xiaomi.mipush.sdk.MessageHandleService.a(intent, (PushMessageReceiver) Class.forName(resolveInfo.activityInfo.name).newInstance()));
            MessageHandleService.a(this, new Intent(getApplicationContext(), MessageHandleService.class));
        } catch (Throwable th) {
            c.a(th);
        }
    }

    protected static void a(com.xiaomi.mipush.sdk.c.a aVar) {
        synchronized (a) {
            if (!a.contains(aVar)) {
                a.add(aVar);
            }
        }
    }

    protected static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    protected static void b(Context context, String str, long j, String str2, String str3) {
        synchronized (a) {
            for (com.xiaomi.mipush.sdk.c.a aVar : a) {
                if (a(str, aVar.a())) {
                    aVar.c(j, str2, str3);
                }
            }
        }
    }

    public static boolean b() {
        return a.isEmpty();
    }

    protected void onHandleIntent(Intent intent) {
        try {
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                if (q.a((Context) this).i()) {
                    al.a((Context) this).a();
                    CharSequence stringExtra = intent.getStringExtra("waker_pkgname");
                    JSONObject jSONObject = new JSONObject();
                    if (TextUtils.isEmpty(stringExtra)) {
                        jSONObject.put("wakerPkgName", stringExtra);
                    }
                    jSONObject.put("selfPkgName", getPackageName());
                    y.a(this, "system_running_log", "wake_up_app", 1, jSONObject.toString());
                }
            } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                org.apache.thrift.a eVar = new e();
                ar.a(eVar, intent.getByteArrayExtra("mipush_payload"));
                c.c("PushMessageHandler.onHandleIntent " + eVar.toString());
                e.a(this, eVar);
            } else if ("com.xiaomi.mipush.sdk.HYBRID_NOTIFICATION_CLICK".equals(intent.getAction())) {
                MiPushMessage miPushMessage = (MiPushMessage) intent.getSerializableExtra("mipush_payload");
                String stringExtra2 = intent.getStringExtra("mipush_hybrid_app_pkg");
                c.a(getApplicationContext(), miPushMessage);
                ab.a(getApplicationContext(), stringExtra2, miPushMessage);
            } else if (1 != f.a((Context) this)) {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(getPackageName());
                intent2.putExtras(intent);
                List<ResolveInfo> queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent2, 32);
                if (queryBroadcastReceivers != null) {
                    for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                        if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(getPackageName())) {
                            break;
                        }
                    }
                }
                ResolveInfo resolveInfo2 = null;
                if (resolveInfo2 != null) {
                    a(intent2, resolveInfo2);
                } else {
                    c.d("cannot find the receiver to handler this message, check your manifest");
                }
            } else if (b()) {
                c.d("receive a message before application calling initialize");
            } else {
                a a = aj.a((Context) this).a(intent);
                if (a != null) {
                    a((Context) this, a);
                }
            }
        } catch (Throwable e) {
            c.a(e);
        } catch (Throwable e2) {
            c.a(e2);
        }
    }
}
