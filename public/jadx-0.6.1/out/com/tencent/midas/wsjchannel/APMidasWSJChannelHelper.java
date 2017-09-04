package com.tencent.midas.wsjchannel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.plugin.APPluginActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class APMidasWSJChannelHelper {
    private static volatile APMidasWSJChannelHelper a = null;
    private ArrayList<Handler> b = new ArrayList(1);
    private Handler c = new Handler(this) {
        final /* synthetic */ APMidasWSJChannelHelper a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    APLog.i("APMidasWSJChannelHelper", "Result:" + ((String) message.obj));
                    synchronized (this.a.b) {
                        Iterator it = this.a.b.iterator();
                        while (it.hasNext()) {
                            Handler handler = (Handler) it.next();
                            Message message2 = new Message();
                            message2.what = 10;
                            Bundle bundle = new Bundle();
                            bundle.putString("resultInfo", (String) message.obj);
                            message2.setData(bundle);
                            handler.sendMessage(message2);
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    };

    private APMidasWSJChannelHelper(Context context) {
    }

    public static APMidasWSJChannelHelper getInstance(Context context) {
        if (a == null) {
            synchronized (APMidasWSJChannelHelper.class) {
                if (a == null) {
                    a = new APMidasWSJChannelHelper(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    public void addObserver(Handler handler) {
        APLog.i("APMidasWSJChannelHelper", "addObserver observer:" + handler);
        synchronized (this.b) {
            if (!this.b.contains(handler)) {
                this.b.add(handler);
            }
        }
    }

    public String getSDKVersion(Activity activity) {
        String version = new PayTask(activity).getVersion();
        APLog.i("APMidasWSJChannelHelper", "getSDKVersion:" + version);
        return version;
    }

    public void handleIntent(Intent intent) {
        APLog.i("APMidasWSJChannelHelper", "handleIntent intent:" + intent);
    }

    public void removeObserver(Handler handler) {
        APLog.i("APMidasWSJChannelHelper", "removeObserver observer:" + handler);
        synchronized (this.b) {
            if (this.b.contains(handler)) {
                this.b.remove(handler);
            }
        }
    }

    public void toPay(final Activity activity, final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ APMidasWSJChannelHelper c;

            public void run() {
                Activity activity = ((APPluginActivity) activity).mProxyActivity;
                APLog.i("APMidasWSJChannelHelper", "proxyActivity:" + activity);
                if (activity != null) {
                    String pay = new PayTask(activity).pay(str);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = pay;
                    this.c.c.sendMessage(message);
                }
            }
        }).start();
    }
}
