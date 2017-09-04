package com.qq.reader.activity.a;

import android.app.Activity;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: ActivityBackStackManager */
public class a {
    private static final String b = a.class.getSimpleName();
    private static a c;
    public Map<Class, List<Integer>> a = new HashMap();

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private a() {
    }

    public synchronized void a(Activity activity) {
        try {
            Class cls = activity.getClass();
            List list = (List) this.a.get(cls);
            if (list == null) {
                list = new ArrayList();
                this.a.put(cls, list);
            }
            list.add(Integer.valueOf(activity.hashCode()));
            Map hashMap = new HashMap();
            hashMap.put("size", String.valueOf(list.size()));
            hashMap.put("clazz", cls.getSimpleName());
            i.a("activity_size", hashMap, ReaderApplication.getApplicationContext());
            c.d(b, "onActivityCreate  size=" + String.valueOf(list.size()) + " HASH=" + activity.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void b(Activity activity) {
        try {
            c.d(b, "onActivityDestroy " + activity);
            Class cls = activity.getClass();
            List list = (List) this.a.get(cls);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) it.next();
                    if (num == null || num.intValue() == activity.hashCode()) {
                        it.remove();
                    }
                }
                if (list.size() == 0) {
                    this.a.remove(cls);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
