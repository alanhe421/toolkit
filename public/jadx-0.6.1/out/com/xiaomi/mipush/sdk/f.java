package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.s;
import java.util.List;

public class f {
    private static int a = 0;

    public static int a(Context context) {
        if (a == 0) {
            if (b(context)) {
                a(1);
            } else {
                a(2);
            }
        }
        return a;
    }

    public static MiPushCommandMessage a(String str, List<String> list, long j, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage a(ak akVar, s sVar, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(akVar.c());
        if (!TextUtils.isEmpty(akVar.j())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(akVar.j());
        } else if (!TextUtils.isEmpty(akVar.h())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(akVar.h());
        } else if (TextUtils.isEmpty(akVar.r())) {
            miPushMessage.setMessageType(0);
        } else {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(akVar.r());
        }
        miPushMessage.setCategory(akVar.p());
        if (akVar.l() != null) {
            miPushMessage.setContent(akVar.l().f());
        }
        if (sVar != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(sVar.b());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(sVar.f());
            }
            miPushMessage.setDescription(sVar.j());
            miPushMessage.setTitle(sVar.h());
            miPushMessage.setNotifyType(sVar.l());
            miPushMessage.setNotifyId(sVar.q());
            miPushMessage.setPassThrough(sVar.o());
            miPushMessage.setExtra(sVar.s());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    private static void a(int i) {
        a = i;
    }

    public static void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 3);
        intent.putExtra("key_command", miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean b(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return a(context, intent);
    }
}
