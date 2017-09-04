package com.qq.reader.common.monitor;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.StatisticsManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: EndpageLog */
public class d {
    private static Set<Long> a = new HashSet();

    public static void a(long j, boolean z) {
        if (a.add(Long.valueOf(j))) {
            Map hashMap = new HashMap();
            if (z) {
                hashMap.put("finish", "1");
            } else {
                hashMap.put("finish", "0");
            }
            i.a("event_B65", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
            StatisticsManager.a().a("event_B65", null);
            j.a(64, 1);
        }
    }

    public static void a() {
        a.clear();
    }
}
