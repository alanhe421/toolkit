package com.qq.reader.common.emotion;

import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* compiled from: EmoticonPanelViewPool */
public class g {
    private SparseArray<List<View>> a = new SparseArray();

    public boolean a(int i, View view) {
        if (view == null) {
            return false;
        }
        List list = (List) this.a.get(i);
        if (list == null) {
            list = new ArrayList(3);
            this.a.put(i, list);
        }
        if (list.size() >= 3) {
            return false;
        }
        list.add(view);
        return true;
    }

    public View a(int i) {
        List list = (List) this.a.get(i);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (View) list.remove(0);
    }
}
