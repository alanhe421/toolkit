package com.qq.reader.framework.a;

import com.qq.reader.readengine.model.b;
import java.util.ArrayList;

/* compiled from: NoteFlag */
public class a extends b {
    int a = 1;
    ArrayList<Long> b = new ArrayList();

    public a(long j, String str) {
        d(str);
        this.b.add(Long.valueOf(j));
    }

    public ArrayList<Long> a() {
        return this.b;
    }

    public void a(long j) {
        this.b.add(Long.valueOf(j));
    }

    public int b() {
        int i = this.a;
        this.a = i + 1;
        return i;
    }
}
