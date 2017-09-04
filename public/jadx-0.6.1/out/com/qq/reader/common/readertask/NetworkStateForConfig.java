package com.qq.reader.common.readertask;

import android.content.Context;
import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.receiver.BaseBroadcastReceiver;
import java.util.ArrayList;
import java.util.List;

public class NetworkStateForConfig extends BaseBroadcastReceiver {
    private static NetworkStateForConfig b = null;
    private static Object c = new Object();
    private static List<a> d = new ArrayList();
    private Context a = ReaderApplication.getApplicationImp();

    public interface a {
        void a(boolean z);
    }

    public NetworkStateForConfig() {
        c.a("NETWORK_STATUS", "new NetworkStateForConfig : " + d.toString());
    }

    public static NetworkStateForConfig a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new NetworkStateForConfig();
                    c.a("NETWORK_STATUS", "new NetworkStateForConfig ");
                }
            }
        }
        return b;
    }

    public void a(Context context, Intent intent) {
        if (intent.getAction().compareTo("android.net.conn.CONNECTIVITY_CHANGE") == 0) {
            final boolean a = com.qq.reader.common.e.a.a();
            g.a().a(new ReaderShortTask() {
                public void run() {
                    super.run();
                    NetworkStateForConfig.this.a(a);
                }
            });
        }
    }

    public void a(a aVar) {
        try {
            c.a("NETWORK_STATUS", "addListener : " + d.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (aVar != null) {
            synchronized (d) {
                if (!d.contains(aVar)) {
                    d.add(aVar);
                }
            }
        }
    }

    public void b(a aVar) {
        c.a("NETWORK_STATUS", "removeListener : " + d.toString());
        synchronized (d) {
            d.remove(aVar);
        }
    }

    private void a(boolean z) {
        c.a("NETWORK_STATUS", "notifyObservers : " + d.toString());
        synchronized (d) {
            a[] aVarArr = new a[d.size()];
            d.toArray(aVarArr);
        }
        if (aVarArr != null) {
            for (a a : aVarArr) {
                a.a(z);
            }
        }
    }
}
