package com.qq.reader.cservice.bookfollow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.m;
import com.qq.reader.cservice.bookfollow.b.a;
import com.qq.reader.cservice.onlineread.OnlineTag;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderBookReceiver extends BroadcastReceiver implements a {
    private Context a;

    public void onReceive(Context context, Intent intent) {
        this.a = context;
        try {
            StatisticsManager.a().d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (!ao.b(Calendar.getInstance().get(11)) || m.a(context)) {
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        g.a().a(new ReaderDBTask() {
            public void run() {
                super.run();
                List n = i.c().n();
                if (n != null && n.size() > 0) {
                    String a = b.a(n, true);
                    if (a != null) {
                        b bVar = new b(OrderBookReceiver.this.a);
                        bVar.a(OrderBookReceiver.this);
                        bVar.a(a);
                    }
                }
            }
        });
    }

    public void onQueryNewResult(int i, Object obj) {
        if (i == 8007) {
            d.f(this.a, System.currentTimeMillis());
            OnlineTag[] onlineTagArr = (OnlineTag[]) obj;
            if (onlineTagArr != null && onlineTagArr.length != 0) {
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : onlineTagArr) {
                    if (obj2 != null) {
                        arrayList.add(obj2);
                    }
                }
                if (arrayList.size() <= 0) {
                    return;
                }
                Intent intent;
                if (BookShelfFragment.isInShelf) {
                    intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.cy);
                    this.a.sendBroadcast(intent);
                } else if (d.aC(this.a)) {
                    intent = new Intent();
                    intent.setAction("com.qq.reader.notification");
                    intent.putParcelableArrayListExtra("onlinetag", arrayList);
                    this.a.sendBroadcast(intent);
                }
            }
        }
    }
}
