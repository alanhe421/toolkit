package com.qq.reader.common.conn.socket;

import android.app.IntentService;
import android.content.Intent;
import com.tencent.open.SocialConstants;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PushMessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue<a> a = new ConcurrentLinkedQueue();

    public static class a {
        private PushMessageReceiver a;
        private Intent b;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.b = intent;
            this.a = pushMessageReceiver;
        }

        public PushMessageReceiver a() {
            return this.a;
        }

        public Intent b() {
            return this.b;
        }
    }

    public static void a(a aVar) {
        if (aVar != null) {
            a.add(aVar);
        }
    }

    public PushMessageHandleService() {
        super("PushMessageHandleService");
    }

    public PushMessageHandleService(String str) {
        super(str);
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            a aVar = (a) a.poll();
            if (aVar != null) {
                Intent b = aVar.b();
                if (b != null) {
                    PushMessageReceiver a = aVar.a();
                    if (a != null) {
                        QRPushMessage qRPushMessage = (QRPushMessage) b.getSerializableExtra(SocialConstants.PARAM_SEND_MSG);
                        if (qRPushMessage != null) {
                            a.a(this, qRPushMessage);
                        }
                    }
                }
            }
        }
    }
}
