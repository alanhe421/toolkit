package com.yuewen.ywlogin.a;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import okhttp3.u;

public class h implements b {
    public static okhttp3.u.a a = new okhttp3.u.a();
    public static u b = a.a();
    public static Handler c = new Handler(Looper.getMainLooper());
    private static int d = 20000;
    private static ArrayList<a> e = new ArrayList();

    public interface b {
        void a();
    }

    private class a {
        b a;
    }

    static {
        a.a(new i());
        a.a((long) d, TimeUnit.MILLISECONDS);
        a.b((long) d, TimeUnit.MILLISECONDS);
    }

    public j a(String str, ContentValues contentValues) {
        return new d().a(str, contentValues);
    }

    public static void a(okhttp3.w.a aVar) {
        if (aVar != null) {
            aVar.b("referer", "http://android.qidian.com");
        }
    }

    public static void a(b bVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(e);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a == bVar) {
                aVar.a.a();
                e.remove(aVar);
            }
        }
        arrayList.clear();
    }
}
