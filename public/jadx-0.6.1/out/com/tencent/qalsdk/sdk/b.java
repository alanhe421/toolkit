package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.base.remote.ToServiceMsg;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: BaseServiceProxyFactory */
public class b {
    static ConcurrentHashMap<String, aj> a = new ConcurrentHashMap();

    public static int a(ToServiceMsg toServiceMsg) {
        if (toServiceMsg == null) {
            return -1;
        }
        if (toServiceMsg.getServiceName().equals(ac.a().b())) {
            return ac.a().b(toServiceMsg);
        }
        if (!a.containsKey(toServiceMsg.getServiceName())) {
            a.putIfAbsent(toServiceMsg.getServiceName(), new aj(toServiceMsg.getServiceName()));
        }
        return ((aj) a.get(toServiceMsg.getServiceName())).c(toServiceMsg);
    }

    public static void a(String str) {
        if (str != null) {
            if (str.equals(ac.a().b())) {
                ac.a().c();
                return;
            }
            if (!a.containsKey(str)) {
                a.putIfAbsent(str, new aj(str));
            }
            ((aj) a.get(str)).i();
        }
    }

    public static void b(String str) {
        if (str != null) {
            if (str.equals(ac.a().b())) {
                ac.a().d();
                return;
            }
            if (!a.containsKey(str)) {
                a.putIfAbsent(str, new aj(str));
            }
            ((aj) a.get(str)).a();
        }
    }

    public static void c(String str) {
        if (str != null) {
            if (str.equals(ac.a().b())) {
                ac.a().e();
                return;
            }
            if (!a.containsKey(str)) {
                a.putIfAbsent(str, new aj(str));
            }
            ((aj) a.get(str)).b();
        }
    }

    public static boolean d(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(ac.a().b())) {
            ac.a().h();
            return true;
        } else if (!a.containsKey(str)) {
            return false;
        } else {
            ((aj) a.get(str)).g();
            return true;
        }
    }

    public static boolean e(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(ac.a().b())) {
            ac.a().i();
            return true;
        } else if (!a.containsKey(str)) {
            return false;
        } else {
            ((aj) a.get(str)).c();
            return true;
        }
    }
}
