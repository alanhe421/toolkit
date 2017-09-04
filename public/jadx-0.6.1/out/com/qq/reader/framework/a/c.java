package com.qq.reader.framework.a;

import com.qq.reader.readengine.model.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: NoteList */
public class c {
    private List<a> a = new ArrayList();
    private Map<a, List<b>> b = new HashMap();

    public c(List<a> list, Map<a, List<b>> map) {
        this.a = list;
        this.b = map;
    }

    public List<a> a() {
        return this.a;
    }

    public Map<a, List<b>> b() {
        return this.b;
    }

    public int a(b bVar) {
        int i = 0;
        a l = bVar.l();
        if (l != null) {
            ((List) this.b.get(l)).remove(bVar);
            i = ((List) this.b.get(l)).size();
            if (i == 0) {
                this.a.remove(l);
                this.b.remove(l);
            }
        }
        return i;
    }
}
