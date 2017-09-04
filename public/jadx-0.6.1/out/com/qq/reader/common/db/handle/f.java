package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import java.util.HashMap;

/* compiled from: BookOnlineTagCacheHandle */
public class f {
    private static f a;
    private HashMap<String, OnlineTag> b = new HashMap();

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    public void a(OnlineTag onlineTag) {
        onlineTag.b(true);
        if (this.b.containsKey(onlineTag.k())) {
            this.b.put(onlineTag.k(), onlineTag);
            g.a().a(new BookOnlineTagCacheHandle$1(this, onlineTag));
            return;
        }
        v.b().e(onlineTag);
    }

    public void b(OnlineTag onlineTag) {
        if (onlineTag != null && this.b.containsKey(onlineTag.k())) {
            this.b.remove(onlineTag.k());
        }
    }

    public void b() {
        this.b.clear();
    }

    public OnlineTag a(String str) {
        OnlineTag onlineTag = (OnlineTag) this.b.get(str);
        if (onlineTag == null) {
            onlineTag = v.b().f(str);
            if (onlineTag != null) {
                this.b.put(str, onlineTag);
            }
        }
        return onlineTag;
    }
}
