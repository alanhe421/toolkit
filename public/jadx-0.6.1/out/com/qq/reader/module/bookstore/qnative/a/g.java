package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.widget.SectionIndexer;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import java.util.HashMap;

/* compiled from: NativeBookStroeAdapterIndexer */
public class g extends c implements SectionIndexer {
    public HashMap<Integer, Integer> c;

    public g(Context context, ListCardCommon listCardCommon, boolean z, HashMap<Integer, Integer> hashMap) {
        super(context, listCardCommon, z);
        this.c = hashMap;
    }

    public Object[] getSections() {
        return null;
    }

    public int getPositionForSection(int i) {
        if (this.c == null || !this.c.containsKey(Integer.valueOf(i))) {
            return -1;
        }
        return ((Integer) this.c.get(Integer.valueOf(i))).intValue();
    }

    public int getSectionForPosition(int i) {
        return 0;
    }
}
