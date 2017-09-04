package com.qq.reader.widget;

import android.os.Handler;
import android.os.Looper;
import android.widget.BaseAdapter;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NotifyErrorLoggedAdapter */
public abstract class a extends BaseAdapter {
    private Handler a = new Handler(Looper.getMainLooper());

    public void notifyDataSetChanged() {
        try {
            if (a()) {
                super.notifyDataSetChanged();
            } else {
                this.a.post(new 1(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
            b();
        }
    }

    public void notifyDataSetInvalidated() {
        try {
            if (a()) {
                super.notifyDataSetInvalidated();
            } else {
                this.a.post(new 2(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
            b();
        }
    }

    private boolean a() {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            return true;
        }
        b();
        return false;
    }

    private void b() {
        c.c("ListViewThread", "check thread :" + Thread.currentThread());
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, c.b());
        i.a("LIST_ERROR", hashMap, ReaderApplication.getApplicationImp());
    }
}
