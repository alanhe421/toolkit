package com.tencent.upload.network.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.e;
import com.tencent.upload.common.e.a;
import com.tencent.upload.impl.TaskManager.TaskType;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class f implements a {
    private Looper a;
    private HandlerThread b;
    private ServerEnv c;
    private c d;
    private String e;
    private FileType f;
    private a g;
    private Map<TaskType, List<a>> h = new HashMap();
    private volatile boolean i = false;

    public f(FileType fileType) {
        this.f = fileType;
        this.b = new HandlerThread("session_pool");
        this.b.start();
        this.a = this.b.getLooper();
        Handler handler = new Handler(this.a);
    }

    private void a(TaskType taskType, a aVar) {
        if (aVar != null) {
            List list = (List) this.h.get(taskType);
            if (list == null) {
                list = new ArrayList();
                this.h.put(taskType, list);
            }
            list.add(aVar);
        }
    }

    private synchronized void a(TaskType taskType, a aVar, List<a> list) {
        if (!this.i) {
            int b = b(taskType) - (list != null ? list.size() : 0);
            if (b > 0) {
                this.i = true;
                new Thread(new g(this, aVar, b, list)).start();
            }
        }
    }

    private static boolean a(a aVar) {
        return aVar != null && aVar.g() == b.ESTABLISHED;
    }

    private boolean a(List<a> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!a((a) list.get(size))) {
                list.remove(size);
            }
        }
        return list.size() > 0;
    }

    public static int b(TaskType taskType) {
        return TaskType.UPLOAD == taskType ? com.tencent.upload.common.a.a().a("upload_channel", 2) : 1;
    }

    private a b(a aVar) {
        if (aVar == null || aVar.c() == null) {
            return null;
        }
        k c = aVar.c();
        a hVar = new h(this.f, false, this.d.b(), null);
        if (!hVar.a(c)) {
            return null;
        }
        long j = BuglyBroadcastRecevier.UPLOADLIMITED;
        while (j > 0 && hVar.g() != b.NO_CONNECT && hVar.g() != b.ESTABLISHED) {
            j -= 50;
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a(hVar) ? hVar : null;
    }

    private List<a> b(TaskType taskType, long j) {
        Exception exception;
        Throwable th;
        if (this.h.containsKey(taskType)) {
            List list = (List) this.h.get(taskType);
            if (a(list)) {
                return list;
            }
            a(taskType);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2;
        try {
            List<a> list2;
            if (a(this.g)) {
                a b = b(this.g);
                com.tencent.upload.common.a.a.c(d(), "clone main session. main_sid=" + this.g.hashCode() + " new_sid=" + (b != null ? Integer.valueOf(b.hashCode()) : "N/A"));
                if (a(b)) {
                    b.a(this.a);
                    a(taskType, b);
                    list2 = (List) this.h.get(taskType);
                    com.tencent.upload.common.a.a.c(d(), "create Session. taskType=" + taskType + " timeout=" + j + " timecost=" + (System.currentTimeMillis() - currentTimeMillis));
                    return list2;
                }
            }
            if (j < 0) {
                j = 0;
            }
            if (this.d.e() != c.a.DETECTING) {
                this.d.d();
            }
            long j3 = 5;
            j2 = j;
            while (j2 >= 0 && j3 > 0) {
                try {
                    if (this.d.e() != c.a.AVAILABLE) {
                        boolean b2 = e.b(Global.context);
                        long j4 = !b2 ? 5 : j3;
                        long j5 = b2 ? 500 : 5000;
                        try {
                            if (this.d.e() != c.a.DETECTING) {
                                this.d.d();
                                if (b2) {
                                    j4--;
                                }
                                j3 = j4;
                            } else {
                                j3 = j4;
                            }
                            j2 -= j5;
                            Thread.sleep(j5);
                        } catch (Exception e) {
                            Exception exception2 = e;
                            j3 = j4;
                            exception = exception2;
                            try {
                                exception.printStackTrace();
                            } catch (Exception e2) {
                                j = j2;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th22) {
                            th = th22;
                        }
                    }
                } catch (Exception e3) {
                    exception = e3;
                    exception.printStackTrace();
                } catch (Throwable th222) {
                    th = th222;
                }
            }
            a c = this.d.c();
            if (a(c)) {
                c.a(this.a);
                this.g = c;
                com.tencent.upload.common.a.a.c(d(), "get main session. sid=" + this.g.hashCode() + " type=" + taskType);
                a(taskType, c);
                list2 = (List) this.h.get(taskType);
                com.tencent.upload.common.a.a.c(d(), "create Session. taskType=" + taskType + " timeout=" + j2 + " timecost=" + (System.currentTimeMillis() - currentTimeMillis));
                return list2;
            }
            com.tencent.upload.common.a.a.c(d(), "create Session. taskType=" + taskType + " timeout=" + j2 + " timecost=" + (System.currentTimeMillis() - currentTimeMillis));
            return null;
        } catch (Exception e4) {
            com.tencent.upload.common.a.a.c(d(), "create Session. taskType=" + taskType + " timeout=" + j + " timecost=" + (System.currentTimeMillis() - currentTimeMillis));
            return null;
        } catch (Throwable th3) {
            th = th3;
            j2 = j;
            com.tencent.upload.common.a.a.c(d(), "create Session. taskType=" + taskType + " timeout=" + j2 + " timecost=" + (System.currentTimeMillis() - currentTimeMillis));
            throw th;
        }
    }

    private String d() {
        return "SessionPool_" + this.f;
    }

    public final a a() {
        return this.g;
    }

    public final List<a> a(TaskType taskType, long j) {
        List b = b(taskType, BuglyBroadcastRecevier.UPLOADLIMITED);
        if (a(b)) {
            a(taskType, (a) b.get(0), b);
        }
        return b;
    }

    public final void a(ServerEnv serverEnv) {
        this.c = serverEnv;
        this.d = new c(this.f, this.c);
        e.a().a((a) this);
    }

    public final void a(TaskType taskType) {
        List<a> list = (List) this.h.remove(taskType);
        if (list != null) {
            for (a aVar : list) {
                if (a(aVar)) {
                    aVar.b();
                }
            }
            if (this.g != null && list.contains(this.g)) {
                this.g = null;
            }
        }
    }

    public final void a(k kVar) {
        if (this.d != null) {
            this.d.a(kVar);
        }
    }

    public final int b() {
        return this.d.a;
    }

    public final String c() {
        return this.d.b;
    }

    public final void onNetworkConnect(boolean z) {
        String b = e.a().b();
        com.tencent.upload.common.a.a.c(d(), "network changed: " + this.e + " -> " + b + " network:" + e.b(Global.context));
        if (!(b.equals(this.e) || this.e == null)) {
            a(TaskType.COMMON);
            a(TaskType.UPLOAD);
            if (this.d != null && this.d.a()) {
                this.d.d();
            }
        }
        this.e = b;
    }
}
