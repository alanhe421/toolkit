package com.qq.reader.cservice.cloud.a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CloudBatDelBookAction */
public class c extends g {
    private List<a> a;

    public c(List<a> list) {
        super(-1, 1, 0, 0);
        this.l = "batdel";
        this.a = list;
        this.m = 1;
    }

    public List<a> a() {
        return this.a;
    }

    public List<Long> g_() {
        List<Long> arrayList = new ArrayList();
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                arrayList.add(Long.valueOf(((a) this.a.get(i)).a()));
            }
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        boolean z = this.l.equals(((g) obj).n()) && a((c) obj) && this.e == ((g) obj).h() && this.f == ((g) obj).i();
        return z;
    }

    private boolean a(c cVar) {
        List<Long> g_ = g_();
        List g_2 = cVar.g_();
        if (this.a.size() != g_2.size()) {
            return false;
        }
        for (Long contains : g_) {
            if (!g_2.contains(contains)) {
                return false;
            }
        }
        return true;
    }
}
