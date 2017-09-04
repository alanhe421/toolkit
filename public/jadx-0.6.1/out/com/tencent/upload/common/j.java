package com.tencent.upload.common;

import com.pay.http.APPluginErrorCode;
import com.qq.reader.module.question.fragment.FamousAuthorSayFragment;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.a.a;
import com.tencent.upload.log.b;
import com.tencent.upload.network.a.d;
import com.tencent.upload.network.a.e;
import com.tencent.upload.network.a.f;
import com.tencent.upload.network.a.g;
import com.tencent.upload.network.a.h;
import com.tencent.upload.network.a.i;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.a.l;
import com.tencent.upload.network.a.m;
import com.tencent.util.TimeFormatterUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public final class j {
    private static String a = "upload.file.myqcloud.com";
    private static String b = "101.226.68.120";
    private static String c = "upload.image.myqcloud.com";
    private static String d = "59.37.116.13";
    private static String e = "upload.video.myqcloud.com";
    private static String f = "101.226.68.120";
    private static final int[] g = new int[]{1440, 1200, 700};
    private static HashMap<String, Integer> h = new HashMap();

    public static final int a(String str) {
        String[] strArr = null;
        if (!d.d()) {
            return -1;
        }
        Integer num;
        synchronized (h) {
            num = (Integer) h.get(str);
        }
        if (num == null) {
            num = Integer.valueOf(0);
        }
        a.a("Configuration", "get timeout ip: " + str + "," + num);
        try {
            String a = a.a().a("upload_socket_max_seglist", null);
            if (a != null && a.length() > 0) {
                strArr = a.split(",");
            }
        } catch (PatternSyntaxException e) {
            a.c("Configuration", "xxx" + e.toString());
        }
        if (strArr == null || strArr.length == 0) {
            return g[num.intValue() % g.length];
        }
        try {
            int parseInt = Integer.parseInt(strArr[num.intValue() % strArr.length]);
            return parseInt < 64 ? 64 : parseInt;
        } catch (NumberFormatException e2) {
            a.c("Configuration", e2.toString());
            return g[num.intValue() % g.length];
        }
    }

    public static final f a(FileType fileType, ServerEnv serverEnv) {
        switch (fileType) {
            case File:
                switch (serverEnv) {
                    case NORMAL:
                        return new d();
                    case DEV:
                        return new e();
                    default:
                        return new d();
                }
            case Photo:
            case Audio:
                return a(serverEnv);
            case Video:
                switch (serverEnv) {
                    case NORMAL:
                        return new l();
                    case DEV:
                        return new m();
                    default:
                        return new l();
                }
            default:
                return a(serverEnv);
        }
    }

    private static f a(ServerEnv serverEnv) {
        switch (serverEnv) {
            case NORMAL:
                return new g();
            case DEV:
                return new h();
            default:
                return new g();
        }
    }

    public static final i a(String str, k kVar) {
        com.tencent.upload.network.a.j jVar = new com.tencent.upload.network.a.j();
        i a = jVar.a(str);
        if (a == null) {
            a = new i();
            a.a(System.currentTimeMillis());
        }
        k g = kVar.g();
        g.a(3);
        a.a(g);
        jVar.a(str, a);
        return a;
    }

    public static final List<Integer> a() {
        String a = a.a().a("upload_port", "80,8080,2000");
        List<Integer> arrayList = new ArrayList();
        try {
            String[] split = a.split(",");
            if (split == null) {
                return arrayList;
            }
            for (String parseInt : split) {
                arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
            }
            return arrayList;
        } catch (Throwable th) {
            return Arrays.asList(new Integer[]{Integer.valueOf(80), Integer.valueOf(8080), Integer.valueOf(APPluginErrorCode.ERROR_APP_SYSTEM)});
        }
    }

    public static final List<k> b() {
        List arrayList = new ArrayList();
        arrayList.add(new k(a, 80, 1, 4));
        arrayList.add(new k(b, 80, 1, 5));
        return arrayList;
    }

    public static final void b(String str) {
        if (str != null && str.length() != 0 && d.d()) {
            Object valueOf;
            synchronized (h) {
                Integer num = (Integer) h.get(str);
                if (num == null) {
                    num = Integer.valueOf(0);
                }
                valueOf = Integer.valueOf(num.intValue() + 1);
                if (valueOf.intValue() < 0) {
                    valueOf = Integer.valueOf(0);
                }
                h.put(str, valueOf);
            }
            a.a("Configuration", "put timeout ip: " + str + "," + valueOf);
        }
    }

    public static final List<k> c() {
        List arrayList = new ArrayList();
        arrayList.add(new k(c, 80, 1, 4));
        arrayList.add(new k(d, 80, 1, 5));
        return arrayList;
    }

    public static final List<k> d() {
        List arrayList = new ArrayList();
        arrayList.add(new k(e, 80, 1, 4));
        arrayList.add(new k(f, 80, 1, 5));
        return arrayList;
    }

    public static final String e() {
        return d.a();
    }

    public static final String f() {
        return d.c() ? d.a() : d.d() ? d.b() : null;
    }

    public static final int g() {
        return 2097152;
    }

    public static final int h() {
        return a.a().a("upload_connect_timeout_secs", 20) * 1000;
    }

    public static final int i() {
        return a.a().a("upload_data_timeout_secs", 30) * 1000;
    }

    public static final int j() {
        return 20000;
    }

    public static final int k() {
        return b.c() == null ? 1 : 0;
    }

    public static final long l() {
        return b.c() == null ? FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK : TimeFormatterUtils.ONE_DAY;
    }
}
