package com.qq.reader.module.bookshelf.a.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: CategoryHandler */
public class c {
    private Context a;
    private Handler b;

    public c(Context context, Handler handler) {
        this.a = context;
        this.b = handler;
    }

    public int a() {
        List h = i.c().h();
        if (h != null) {
            return h.size();
        }
        return -1;
    }

    public int b() {
        List i = i.c().i();
        if (i != null) {
            return i.size();
        }
        return -1;
    }

    public int a(int i) {
        if (i == i.b) {
            return i.c().a(i) + i.c().a(i.c);
        }
        return i.c().a(i);
    }

    public ArrayList<MetroItem> c() {
        return i.c().l();
    }

    public ArrayList<MetroItem> d() {
        ArrayList l = i.c().l();
        ArrayList<MetroItem> arrayList = new ArrayList();
        Iterator it = l.iterator();
        while (it.hasNext()) {
            MetroItem metroItem = (MetroItem) it.next();
            if (!(metroItem.getId() == i.a || metroItem.getId() == i.c)) {
                arrayList.add(metroItem);
            }
        }
        return arrayList;
    }

    public boolean a(String str) {
        Message obtain;
        if (str == null || str.length() == 0) {
            obtain = Message.obtain();
            obtain.what = 20001;
            obtain.obj = this.a.getResources().getString(R.string.toast_catgory_null_name);
            this.b.sendMessage(obtain);
        } else {
            Object obj;
            Iterator it = i.c().l().iterator();
            while (it.hasNext()) {
                if (((MetroItem) it.next()).getName().equals(str)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                obtain = Message.obtain();
                obtain.what = 20001;
                obtain.obj = this.a.getResources().getString(R.string.toast_catgory_duplicate);
                this.b.sendMessage(obtain);
            } else if (i.c().g(str)) {
                obtain = Message.obtain();
                obtain.what = 20005;
                this.b.sendMessageDelayed(obtain, 300);
                return true;
            } else {
                obtain = Message.obtain();
                obtain.what = 20001;
                obtain.obj = this.a.getResources().getString(R.string.toast_catgory_error);
                this.b.sendMessage(obtain);
            }
        }
        return false;
    }

    public void a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            Message obtain = Message.obtain();
            obtain.what = 20001;
            obtain.obj = this.a.getResources().getString(R.string.toast_catgory_null_name);
            this.b.sendMessage(obtain);
            return;
        }
        Object obj;
        Iterator it = i.c().l().iterator();
        while (it.hasNext()) {
            if (((MetroItem) it.next()).getDisplayName().equals(str2)) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            obtain = Message.obtain();
            obtain.what = 20001;
            obtain.obj = this.a.getResources().getString(R.string.toast_catgory_duplicate);
            this.b.sendMessage(obtain);
        } else if (i.c().a(str, str2)) {
            obtain = Message.obtain();
            obtain.what = 20002;
            obtain.obj = str2;
            this.b.sendMessage(obtain);
        } else {
            obtain = Message.obtain();
            obtain.what = 20001;
            obtain.obj = this.a.getResources().getString(R.string.toast_catgory_error);
            this.b.sendMessage(obtain);
        }
    }

    public void b(String str) {
        if (str != null && str.length() != 0) {
            Message obtain;
            if (i.c().i(str)) {
                obtain = Message.obtain();
                obtain.what = 20006;
                this.b.sendMessage(obtain);
                return;
            }
            obtain = Message.obtain();
            obtain.what = 20001;
            obtain.obj = this.a.getResources().getString(R.string.toast_catgory_error);
            this.b.sendMessage(obtain);
        }
    }
}
