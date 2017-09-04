package com.qq.reader.common.a;

import com.qq.reader.common.monitor.e;
import java.util.HashSet;

/* compiled from: ABRdmEventNameFilter */
public class a implements e {
    private static String[] a = new String[]{"event_C55", "event_A189", "event_C67", "event_C112", "event_C72", "event_C4", "event_C65", "event_C63", "event_A188", "event_A183", "feed_divider", "event_feed_exposure"};
    private static HashSet<String> b;

    static {
        int i = 0;
        b = null;
        b = new HashSet();
        String[] strArr = a;
        int length = strArr.length;
        while (i < length) {
            b.add(strArr[i]);
            i++;
        }
    }

    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        return b.contains(str);
    }

    public int a() {
        return b.a();
    }
}
