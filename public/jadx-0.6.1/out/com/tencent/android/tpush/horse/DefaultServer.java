package com.tencent.android.tpush.horse;

import android.text.TextUtils;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.ApList;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ProGuard */
public class DefaultServer {
    public static String a;
    public static ArrayList b = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(443), Integer.valueOf(8080), Integer.valueOf(80), Integer.valueOf(14000)}));
    public static String[] c;
    public static String[] d;
    public static String[] e;
    public static final ENV f = ENV.RELEASE;
    public static final ArrayList g = new ArrayList();

    /* compiled from: ProGuard */
    public enum ENV {
        RELEASE
    }

    static {
        a = "tpns.qq.com";
        c = new String[]{"183.232.98.178"};
        d = new String[]{"58.251.139.182"};
        e = new String[]{"183.61.46.193"};
        Collections.shuffle(b);
        a = "tpns.qq.com";
        c = new String[]{"183.232.98.178", "111.30.131.23"};
        d = new String[]{"58.251.139.182", "125.39.240.55"};
        e = new String[]{"183.61.46.193", "123.151.152.50"};
        g.add(new ServerItem("183.61.46.193", 443, 0));
    }

    public static ArrayList a(String str) {
        if (str == null) {
            throw new NullReturnException("createDefaultItems return null,because key is null");
        }
        ArrayList arrayList = new ArrayList();
        Iterator it;
        int intValue;
        if (str.equals(String.valueOf(3))) {
            it = b.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                for (String serverItem : c) {
                    arrayList.add(new ServerItem(serverItem, intValue, 3));
                }
            }
        } else if (str.equals(String.valueOf(1))) {
            it = b.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                for (String serverItem2 : e) {
                    arrayList.add(new ServerItem(serverItem2, intValue, 1));
                }
            }
        } else if (str.equals(String.valueOf(2))) {
            it = b.iterator();
            while (it.hasNext()) {
                intValue = ((Integer) it.next()).intValue();
                for (String serverItem22 : d) {
                    arrayList.add(new ServerItem(serverItem22, intValue, 2));
                }
            }
        } else {
            String hostAddress;
            String domain = CacheManager.getDomain(m.e());
            if (TextUtils.isEmpty(domain)) {
                domain = a;
            }
            try {
                hostAddress = InetAddress.getByName(domain).getHostAddress();
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "", e);
                hostAddress = c[0];
            }
            Iterator it2 = b.iterator();
            while (it2.hasNext()) {
                arrayList.add(new ServerItem(hostAddress, ((Integer) it2.next()).intValue(), 0));
            }
        }
        return arrayList;
    }

    public static void a(ApList apList) {
        Map map = apList.primary;
        Map map2 = apList.secondary;
        ArrayList arrayList = apList.portList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Byte b : map.keySet()) {
            String a = f.a(((Long) map.get(b)).longValue());
            if (!TextUtils.isEmpty(a)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ServerItem serverItem = new ServerItem(a, ((Integer) it.next()).intValue(), b.intValue());
                    if (b.byteValue() == (byte) 3) {
                        arrayList2.add(serverItem);
                    }
                    if (b.byteValue() == (byte) 1) {
                        arrayList3.add(serverItem);
                    }
                    if (b.byteValue() == (byte) 2) {
                        arrayList4.add(serverItem);
                    }
                }
            }
        }
        for (Byte b2 : map2.keySet()) {
            String a2 = f.a(((Long) map2.get(b2)).longValue());
            if (!TextUtils.isEmpty(a2)) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ServerItem serverItem2 = new ServerItem(a2, ((Integer) it2.next()).intValue(), b2.intValue());
                    if (b2.byteValue() == (byte) 3) {
                        arrayList2.add(serverItem2);
                    }
                    if (b2.byteValue() == (byte) 1) {
                        arrayList3.add(serverItem2);
                    }
                    if (b2.byteValue() == (byte) 2) {
                        arrayList4.add(serverItem2);
                    }
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            CacheManager.addServerItems(m.e(), "3", arrayList2);
        }
        if (!arrayList3.isEmpty()) {
            CacheManager.addServerItems(m.e(), "1", arrayList3);
        }
        if (!arrayList4.isEmpty()) {
            CacheManager.addServerItems(m.e(), "2", arrayList4);
        }
        ArrayList arrayList5 = apList.speedTestIpList;
        ArrayList arrayList6 = new ArrayList();
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            Long l = (Long) it3.next();
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                arrayList6.add(new ServerItem(l.longValue(), ((Integer) it4.next()).intValue(), 0));
            }
        }
        CacheManager.saveSpeedTestList(m.e(), arrayList6);
        String str = apList.domain;
        if (!TextUtils.isEmpty(str) && !str.equals(CacheManager.getDomain(m.e()))) {
            CacheManager.clearDomainServerItem(m.e());
            CacheManager.saveDomain(m.e(), str);
        }
    }

    public static ArrayList a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = b.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            for (String serverItem : c) {
                arrayList.add(new ServerItem(serverItem, intValue, 3));
            }
            for (String serverItem2 : e) {
                arrayList.add(new ServerItem(serverItem2, intValue, 1));
            }
            for (String serverItem22 : d) {
                arrayList.add(new ServerItem(serverItem22, intValue, 2));
            }
        }
        String domain = CacheManager.getDomain(m.e());
        if (TextUtils.isEmpty(domain)) {
            domain = a;
        }
        try {
            String hostAddress = InetAddress.getByName(domain).getHostAddress();
            it = b.iterator();
            while (it.hasNext()) {
                arrayList.add(new ServerItem(hostAddress, ((Integer) it.next()).intValue(), 0));
            }
        } catch (Exception e) {
            a.h(Constants.ServiceLogTag, ">> Dns resolve err : " + e.getMessage());
        }
        return arrayList;
    }
}
