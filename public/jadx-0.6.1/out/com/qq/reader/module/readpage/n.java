package com.qq.reader.module.readpage;

import java.util.ArrayList;
import java.util.List;

/* compiled from: QRActiveElementList */
public class n {
    private List<m> a = new ArrayList();

    public void a(m mVar) {
        this.a.add(mVar);
    }

    public boolean a(int i, int i2) {
        for (m mVar : this.a) {
            if (mVar != null && mVar.a(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.a.clear();
    }
}
