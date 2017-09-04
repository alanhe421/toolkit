package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue<a> a = new ConcurrentLinkedQueue();

    public static class a {
        private PushMessageReceiver a;
        private Intent b;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.a = pushMessageReceiver;
            this.b = intent;
        }

        public PushMessageReceiver a() {
            return this.a;
        }

        public Intent b() {
            return this.b;
        }
    }

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    protected static void a(Service service, Intent intent) {
        if (intent != null) {
            try {
                a aVar = (a) a.poll();
                if (aVar != null) {
                    PushMessageReceiver a = aVar.a();
                    Intent b = aVar.b();
                    MiPushCommandMessage miPushCommandMessage;
                    switch (b.getIntExtra("message_type", 1)) {
                        case 1:
                            a a2 = aj.a((Context) service).a(b);
                            if (a2 == null) {
                                return;
                            }
                            if (a2 instanceof MiPushMessage) {
                                MiPushMessage miPushMessage = (MiPushMessage) a2;
                                if (!miPushMessage.isArrivedMessage()) {
                                    a.onReceiveMessage(service, miPushMessage);
                                }
                                if (miPushMessage.getPassThrough() == 1) {
                                    a.onReceivePassThroughMessage(service, miPushMessage);
                                    return;
                                } else if (miPushMessage.isNotified()) {
                                    a.onNotificationMessageClicked(service, miPushMessage);
                                    return;
                                } else {
                                    a.onNotificationMessageArrived(service, miPushMessage);
                                    return;
                                }
                            } else if (a2 instanceof MiPushCommandMessage) {
                                miPushCommandMessage = (MiPushCommandMessage) a2;
                                a.onCommandResult(service, miPushCommandMessage);
                                if (TextUtils.equals(miPushCommandMessage.getCommand(), "register")) {
                                    a.onReceiveRegisterResult(service, miPushCommandMessage);
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        case 3:
                            miPushCommandMessage = (MiPushCommandMessage) b.getSerializableExtra("key_command");
                            a.onCommandResult(service, miPushCommandMessage);
                            if (TextUtils.equals(miPushCommandMessage.getCommand(), "register")) {
                                a.onReceiveRegisterResult(service, miPushCommandMessage);
                                return;
                            }
                            return;
                        case 4:
                            return;
                        default:
                            return;
                    }
                    c.a(e);
                }
            } catch (Throwable e) {
                c.a(e);
            }
        }
    }

    public static void a(a aVar) {
        if (aVar != null) {
            a.add(aVar);
        }
    }

    protected void onHandleIntent(Intent intent) {
        a(this, intent);
    }
}
