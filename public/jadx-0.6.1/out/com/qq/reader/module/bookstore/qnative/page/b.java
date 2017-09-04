package com.qq.reader.module.bookstore.qnative.page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.bookstore.qnative.a;
import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.storage.task.BaseNativeDataTask;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;

/* compiled from: NativeBasePage */
public abstract class b implements a, com.qq.reader.module.bookstore.qnative.b {
    private WeakReference<com.qq.reader.module.bookstore.qnative.c.a> a = null;
    private long b = 0;
    protected Bundle f = null;
    protected String g = null;
    protected int h = 1000;
    public String i = null;
    protected String j;
    protected List<com.qq.reader.module.bookstore.qnative.card.a> k = new ArrayList();
    protected HashMap<String, com.qq.reader.module.bookstore.qnative.card.a> l = new HashMap();
    protected String m;
    protected d n;
    protected long o = 1;
    protected String p = "";
    protected JSONObject q = null;

    public abstract String a(List<com.qq.reader.module.bookstore.qnative.card.a> list);

    public abstract void a(String str);

    public abstract boolean a();

    public abstract void b(JSONObject jSONObject);

    public abstract boolean b();

    public abstract BaseNativeDataTask f();

    public String g() {
        return this.g;
    }

    public void a(long j) {
        long currentTimeMillis = System.currentTimeMillis() + 172800000;
        if (this.b > currentTimeMillis) {
            this.b = currentTimeMillis;
            return;
        }
        if (j <= 0) {
            j = System.currentTimeMillis() + BuglyBroadcastRecevier.UPLOADLIMITED;
        }
        this.b = j;
    }

    public long h() {
        return this.b;
    }

    public b i() {
        return e.a().a(this.f, l());
    }

    public Bundle j() {
        return this.f;
    }

    public boolean b(b bVar) {
        if (bVar.n != null) {
            return bVar.n.a(this.n);
        }
        return false;
    }

    public void a(b bVar) {
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        try {
            if (bVar.k != null) {
                int i = 0;
                while (i < this.k.size()) {
                    try {
                        ((com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i)).onCardShouldDestroy();
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.k.clear();
                for (com.qq.reader.module.bookstore.qnative.card.a add : bVar.k) {
                    this.k.add(add);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (bVar.l != null) {
                try {
                    for (Entry value : this.l.entrySet()) {
                        ((com.qq.reader.module.bookstore.qnative.card.a) value.getValue()).onCardShouldDestroy();
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
                this.l.clear();
                for (Entry value2 : bVar.l.entrySet()) {
                    this.l.put((String) value2.getKey(), (com.qq.reader.module.bookstore.qnative.card.a) value2.getValue());
                }
            }
        } catch (Exception e222) {
            e222.printStackTrace();
        }
        if (bVar.n != null) {
            if (this.n == null) {
                this.n = new d();
            }
            this.n.a(bVar.n);
        }
        this.b = bVar.b;
        this.o = bVar.o;
        this.p = bVar.p;
    }

    public d k() {
        return this.n;
    }

    public void b(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.a = new WeakReference(aVar);
        for (com.qq.reader.module.bookstore.qnative.card.a aVar2 : this.k) {
            if (aVar2 != null) {
                aVar2.setEventListener(aVar);
            }
        }
    }

    public com.qq.reader.module.bookstore.qnative.c.a l() {
        return (com.qq.reader.module.bookstore.qnative.c.a) this.a.get();
    }

    public void b(String str) {
        this.m = str;
    }

    public List<com.qq.reader.module.bookstore.qnative.card.a> m() {
        return this.k;
    }

    public int i_() {
        return this.i.hashCode();
    }

    public int n() {
        return i_();
    }

    public boolean c(JSONObject jSONObject) {
        try {
            a(jSONObject.optLong("expireTime") * 1000);
            return b();
        } catch (Exception e) {
            return true;
        }
    }

    public String o() {
        if (this.f != null) {
            return this.f.getString("URL_DATA_QURL");
        }
        return null;
    }

    public int p() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public void q() {
        if (this.k != null) {
            for (com.qq.reader.module.bookstore.qnative.card.a refresh : this.k) {
                refresh.refresh();
            }
        }
    }

    public void b(Bundle bundle) {
    }

    public long r() {
        return this.o;
    }

    public boolean s() {
        return this.o > 1;
    }

    public boolean addMore(a aVar) {
        return false;
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
    }

    public void t() {
    }

    public Class c() {
        return NativePageFragmentforOther.class;
    }

    public boolean u() {
        if (this.k == null || this.k.size() == 0) {
            return true;
        }
        if (this.k.size() == 1 && (this.k.get(0) instanceof BaseEmptyCard)) {
            return true;
        }
        return false;
    }

    public void v() {
        g.a().a(new NativeBasePage$1(this));
    }

    public void w() {
        for (com.qq.reader.module.bookstore.qnative.card.a onCardShouldDestroy : this.k) {
            onCardShouldDestroy.onCardShouldDestroy();
        }
    }
}
