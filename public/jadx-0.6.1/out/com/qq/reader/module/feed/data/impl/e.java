package com.qq.reader.module.feed.data.impl;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.feed.loader.f;
import com.qq.reader.module.feed.loader.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: FeedDataPackage */
public class e {
    private String a = null;
    private String b = null;
    private String c = "A";
    private String[] d = new String[]{null, null};
    private ArrayList<g> e = new ArrayList();
    private ArrayList<FeedBaseCard> f = new ArrayList();
    private int g = 0;
    private boolean h = false;
    private boolean i;
    private List<String> j = new ArrayList();
    private int k;
    private boolean l;
    private boolean m = true;

    public e(String str, int i) {
        if (i == 0) {
            this.b = str;
        } else {
            this.a = str;
        }
        this.g = i;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(g gVar) {
        this.e.add(gVar);
        this.f.addAll(gVar.a());
        b(gVar);
    }

    public void a(int i, g gVar) {
        this.e.add(i, gVar);
        this.f.addAll(i, gVar.a());
        b(gVar);
    }

    private void b(g gVar) {
        String c = gVar.c();
        if (!TextUtils.isEmpty(c)) {
            if (this.d[0] == null || h.a(this.d[0], c)) {
                this.d[0] = c;
            }
            if (this.d[1] == null || h.a(c, this.d[1])) {
                this.d[1] = c;
            }
        }
    }

    public boolean a() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public boolean b() {
        return this.l;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public int c() {
        return this.k;
    }

    public void a(int i) {
        this.k = i;
    }

    public void c(String str) {
        this.j.add(str);
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : this.j) {
            stringBuilder.append(append);
            stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public String[] e() {
        return this.d;
    }

    public String f() {
        return this.a;
    }

    public String g() {
        return this.b;
    }

    public ArrayList<FeedBaseCard> h() {
        return this.f;
    }

    public f i() {
        f fVar = new f();
        try {
            if (this.e != null && this.e.size() > 0) {
                fVar.a = ((g) this.e.get(0)).c();
                fVar.b = ((g) this.e.get(0)).d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fVar;
    }

    public int j() {
        return this.g;
    }

    public void k() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((g) it.next()).f();
        }
        this.e.clear();
        this.f.clear();
        this.d[0] = null;
        this.d[1] = null;
    }

    public void l() {
        try {
            c.e("FeedPackageDate", "=================package start==================");
            c.e("FeedPackageDate", "opt is  " + this.g);
            c.e("FeedPackageDate", "start time = " + f());
            c.e("FeedPackageDate", "end time = " + g());
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                ((g) it.next()).g();
            }
            c.e("FeedPackageDate", "=================package end==================");
        } catch (Exception e) {
            c.e("FeedPackageDate", e.toString());
        }
    }

    public void d(String str) {
        this.c = str;
    }

    public String m() {
        return this.c;
    }

    public boolean n() {
        return this.m;
    }

    public void c(boolean z) {
        this.m = z;
    }
}
