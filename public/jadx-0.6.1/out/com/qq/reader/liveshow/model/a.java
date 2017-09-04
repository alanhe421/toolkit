package com.qq.reader.liveshow.model;

import android.util.SparseArray;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CurLiveInfo */
public class a {
    private static int A;
    private static int a;
    private static int b;
    private static String c;
    private static double d;
    private static double e;
    private static String f = "";
    private static String g = "";
    private static int h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static int m;
    private static int n = Integer.MAX_VALUE;
    private static long o;
    private static long p;
    private static int q = 0;
    private static String r = "";
    private static String s = "";
    private static boolean t = false;
    private static boolean u = false;
    private static int v = 0;
    private static SparseArray<GiftItem> w = new SparseArray();
    private static List<GiftItem> x = new ArrayList();
    private static GiftItem y;
    private static List<RankItem> z = new ArrayList();

    public static int a() {
        return q;
    }

    public static void a(int i) {
        q = i;
    }

    public static String b() {
        return j;
    }

    public static void a(String str) {
        j = str;
    }

    public static String c() {
        return i;
    }

    public static void b(String str) {
        i = str;
    }

    public static String d() {
        return k;
    }

    public static void c(String str) {
        k = str;
    }

    public static String e() {
        return l;
    }

    public static void d(String str) {
        l = str;
    }

    public static int f() {
        return a;
    }

    public static synchronized void b(int i) {
        synchronized (a.class) {
            a = i;
        }
    }

    public static int g() {
        return b;
    }

    public static void c(int i) {
        b = i;
    }

    public static synchronized void d(int i) {
        synchronized (a.class) {
            b += i;
        }
    }

    public static String h() {
        return c;
    }

    public static void e(String str) {
        c = str;
    }

    public static int i() {
        return h;
    }

    public static void e(int i) {
        h = i;
        SxbLog.e("MINFO", "set currliveinfo room num " + i);
    }

    public static void f(String str) {
        g = str;
    }

    public static String j() {
        return "" + h;
    }

    public static long k() {
        return o;
    }

    public static void a(long j) {
        o = j;
    }

    public static long l() {
        return p;
    }

    public static void b(long j) {
        p = j;
    }

    public static GiftItem f(int i) {
        return (GiftItem) w.get(i);
    }

    public static void a(List<GiftItem> list) {
        if (list != null) {
            x.clear();
            w.clear();
            for (GiftItem giftItem : list) {
                w.put(giftItem.mId, giftItem);
                x.add(giftItem);
            }
        }
    }

    public static List<GiftItem> m() {
        return x;
    }

    public static List<RankItem> n() {
        return z;
    }

    public static void b(List<RankItem> list) {
        z.clear();
        z.addAll(list);
    }

    public static void g(int i) {
        A = i;
    }

    public static int o() {
        return A;
    }

    public static int p() {
        return m;
    }

    public static void h(int i) {
        m = i;
    }

    public static int q() {
        return n;
    }

    public static void i(int i) {
        n = i;
    }

    public static void g(String str) {
        r = str;
    }

    public static String r() {
        return r;
    }

    public static void h(String str) {
        s = str;
    }

    public static String s() {
        return s;
    }

    public static void a(boolean z) {
        t = z;
    }

    public static boolean t() {
        return t;
    }

    public static void b(boolean z) {
        u = z;
    }

    public static boolean u() {
        return u;
    }

    public static GiftItem v() {
        return y;
    }

    public static void a(GiftItem giftItem) {
        y = giftItem;
    }

    public static void w() {
        a = 0;
        b = 0;
        c = "";
        d = 0.0d;
        e = 0.0d;
        f = "";
        g = "";
        h = 0;
        i = "";
        k = "";
        l = "";
        o = 0;
        p = 0;
        q = 0;
        v = 0;
        A = 0;
        j = "";
        x.clear();
        w.clear();
        s = "";
        r = "";
        t = false;
        y = null;
        n = Integer.MAX_VALUE;
    }

    public static void x() {
        x.clear();
        w.clear();
    }
}
