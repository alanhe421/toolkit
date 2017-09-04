package com.qq.reader.common.monitor.debug;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

/* compiled from: LocalStatisicManager */
public class a {
    private static a c = new a();
    private b a = new b(this, "SHandlerThread");
    private DiskHashMap b = new LocalStatisicManager$1(this, "LocalStatisicManager");
    private Handler d;

    /* compiled from: LocalStatisicManager */
    class a {
        String a;
        Object b;
    }

    /* compiled from: LocalStatisicManager */
    class b extends HandlerThread implements Callback {
        final /* synthetic */ a a;

        public b(a aVar, String str) {
            this.a = aVar;
            super(str);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    a aVar = (a) message.obj;
                    if (aVar != null) {
                        this.a.b.put(aVar.a, aVar.b);
                    }
                    return true;
                case 1002:
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.a.b.remove(str);
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    public a() {
        this.a.start();
        this.d = new Handler(this.a.getLooper(), this.a);
    }
}
