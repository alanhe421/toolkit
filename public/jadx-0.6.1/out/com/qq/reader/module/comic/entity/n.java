package com.qq.reader.module.comic.entity;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ComicPackageInfo */
public class n {
    private List<o> a = new ArrayList();

    public void a(o oVar) {
        this.a.add(oVar);
    }

    public int a() {
        return this.a.size();
    }

    public String b() {
        if (this.a == null || this.a.size() == 0) {
            return "";
        }
        if (this.a.size() == 1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("第");
            stringBuffer.append(((o) this.a.get(0)).b() + 1);
            stringBuffer.append(((o) this.a.get(0)).h());
            return stringBuffer.toString();
        }
        stringBuffer = new StringBuffer();
        stringBuffer.append("第");
        stringBuffer.append(((o) this.a.get(0)).b() + 1);
        stringBuffer.append(((o) this.a.get(0)).h());
        stringBuffer.append("-第");
        stringBuffer.append(((o) this.a.get(this.a.size() - 1)).b() + 1);
        stringBuffer.append(((o) this.a.get(0)).h());
        return stringBuffer.toString();
    }

    public o a(int i) {
        if (this.a != null && this.a.size() > 0 && i < this.a.size()) {
            return (o) this.a.get(i);
        }
        return null;
    }
}
