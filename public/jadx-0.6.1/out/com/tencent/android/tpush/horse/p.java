package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.m;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class p {
    private static StrategyItem a(String str, int i, int i2) {
        if (str == null || i == 0) {
            return null;
        }
        return new StrategyItem(str, i, "", 80, i2, 0);
    }

    private static List a(List list, short s, String str) {
        StrategyItem e;
        Throwable e2;
        StrategyItem strategyItem;
        int i = 0;
        if (list == null) {
            throw new NullReturnException("getStrategyItems return null, because [items] is null");
        }
        List arrayList = new ArrayList();
        try {
            e = CacheManager.getOptStrategyList(m.e(), str).e();
            try {
                e.a(0);
                if (e.d() == s) {
                    arrayList.add(e);
                }
                Object obj = e;
            } catch (Exception e3) {
                e2 = e3;
                a.b(Constants.ServiceLogTag, "getStrategyItems", e2);
                strategyItem = e;
                while (i < list.size()) {
                    e = a(((ServerItem) list.get(i)).a(), ((ServerItem) list.get(i)).b(), (int) s);
                    arrayList.add(e);
                    i++;
                }
                return arrayList;
            }
        } catch (Throwable e4) {
            Throwable th = e4;
            e = null;
            e2 = th;
            a.b(Constants.ServiceLogTag, "getStrategyItems", e2);
            strategyItem = e;
            while (i < list.size()) {
                e = a(((ServerItem) list.get(i)).a(), ((ServerItem) list.get(i)).b(), (int) s);
                arrayList.add(e);
                i++;
            }
            return arrayList;
        }
        while (i < list.size()) {
            e = a(((ServerItem) list.get(i)).a(), ((ServerItem) list.get(i)).b(), (int) s);
            if (!(e == null || e.equals(r1))) {
                arrayList.add(e);
            }
            i++;
        }
        return arrayList;
    }

    public static List a(List list, String str) {
        return a(list, (short) 0, str);
    }

    public static List b(List list, String str) {
        return a(list, (short) 1, str);
    }
}
