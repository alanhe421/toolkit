package com.sijla.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sijla.c.c;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.f;
import java.util.ArrayList;
import java.util.List;

public class HBL extends BroadcastReceiver {
    private static List<String> a;

    static {
        a = null;
        if (a == null) {
            a = new ArrayList();
        }
        a.add("android.intent.action.PACKAGE_ADDED");
        a.add("android.intent.action.PACKAGE_REMOVED");
        a.add("android.intent.action.PACKAGE_REPLACED");
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        f.a("onReceive:" + action);
        if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
            List arrayList = new ArrayList();
            arrayList.add(a.i());
            arrayList.add(b.g() + "");
            try {
                b.e().a("PU", arrayList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!"android.intent.action.BOOT_COMPLETED".equals(action) && !"android.net.conn.CONNECTIVITY_CHANGE".equals(action) && a.contains(action)) {
            try {
                c.a(new com.sijla.h.c(context, intent));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
