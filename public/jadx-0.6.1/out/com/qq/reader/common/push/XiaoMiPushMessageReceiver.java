package com.qq.reader.common.push;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.f;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;

public class XiaoMiPushMessageReceiver extends PushMessageReceiver {
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        c.a("XiaoMiPush", "onReceivePassThroughMessage is called. " + miPushMessage.toString());
        if (!TextUtils.isEmpty(miPushMessage.getTopic())) {
            b.b = miPushMessage.getTopic();
        } else if (!TextUtils.isEmpty(miPushMessage.getAlias())) {
            b.c = miPushMessage.getAlias();
        }
        a.a(context, miPushMessage.getContent(), a.a);
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        c.a("XiaoMiPush", "onNotificationMessageClicked is called. " + miPushMessage.toString());
        a.a(context, miPushMessage.getContent(), a.c);
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        c.a("XiaoMiPush", "onNotificationMessageArrived is called. " + miPushMessage.toString());
        a.a(context, miPushMessage.getContent(), a.b);
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        c.a("XiaoMiPush", "onCommandResult is called. " + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if ("register".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                b.a = str;
                c.e("XiaomiPush", "regId = " + String.valueOf(str));
                b.b();
                b.c();
                b.e();
                b.d();
                return;
            }
            f.b().a(b.e);
        } else if ("set-alias".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                b.c = str;
            } else {
                f.b().a(b.f);
            }
        } else if ("unset-alias".equals(command)) {
            if (miPushCommandMessage.getResultCode() != 0) {
            }
        } else if ("set-account".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                b.d = str;
            } else {
                f.b().a(b.g);
            }
        } else if ("unset-account".equals(command)) {
            if (miPushCommandMessage.getResultCode() != 0) {
                f.b().a(b.h);
            }
        } else if ("subscribe-topic".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                b.b = str;
            } else {
                f.b().a(b.i);
            }
        } else if (!"unsubscibe-topic".equals(command) || miPushCommandMessage.getResultCode() != 0) {
        }
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        c.a("XiaoMiPush", "onReceiveRegisterResult is called. " + miPushCommandMessage.toString());
    }
}
