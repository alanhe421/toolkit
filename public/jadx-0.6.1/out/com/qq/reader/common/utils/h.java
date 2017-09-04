package com.qq.reader.common.utils;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.HashMap;
import java.util.Map;

/* compiled from: CardReportUtility */
public class h {
    public static void a(String str, int i, long j, int i2, long j2) {
        if (str.equals("AdvCard_Circle")) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(j2));
            if (i == (j + "DetailPage").hashCode()) {
                if (String.valueOf(i2).equals("102181")) {
                    i.a("event_C172", hashMap, ReaderApplication.getApplicationImp());
                } else if (String.valueOf(i2).equals("102385")) {
                    i.a("event_C174", hashMap, ReaderApplication.getApplicationImp());
                }
            }
            i.a("event_C163", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    public static void b(String str, int i, long j, int i2, long j2) {
        if (str.equals("AdvCard_Circle")) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(j2));
            if (i != (j + "DetailPage").hashCode()) {
                return;
            }
            if (String.valueOf(i2).equals("102181")) {
                i.a("event_C171", hashMap, ReaderApplication.getApplicationImp());
            } else if (String.valueOf(i2).equals("102385")) {
                i.a("event_C173", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    }
}
