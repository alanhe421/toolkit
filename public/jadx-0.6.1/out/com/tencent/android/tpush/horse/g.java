package com.tencent.android.tpush.horse;

import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Handler;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.d.a;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/* compiled from: ProGuard */
public class g {
    static long a = 1;
    static long b = 0;
    public static int c = -1;
    private static long j = 0;
    private static long k = 0;
    private static int m;
    private final Object d;
    private volatile int e;
    private volatile boolean f;
    private long g;
    private k h;
    private l i;
    private Timer l;
    private Handler n;
    private b o;
    private b p;

    public void a(l lVar) {
        this.i = lVar;
    }

    public static g a() {
        return m.a;
    }

    private g() {
        this.d = new Object();
        this.e = 0;
        this.f = false;
        this.l = new Timer();
        this.n = null;
        this.o = new i(this);
        this.p = new j(this);
        this.n = com.tencent.android.tpush.common.g.a().b();
    }

    public synchronized void a(k kVar) {
        this.e = 0;
        this.h = kVar;
        this.n.post(new h(this));
    }

    private void a(String str) {
        NullReturnException nullReturnException;
        Exception exception;
        if (!a.d(m.e())) {
            com.tencent.android.tpush.a.a.h("OptimalLinkSelector", "Network can't reachable");
            a(10100, "network can't reachable!");
        } else if (q.i().b() || f.i().b()) {
            com.tencent.android.tpush.a.a.a("OptimalLinkSelector", "Horse task running");
        } else {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "Action -> startHorseTask with key = " + str);
            }
            CacheManager.removeOptStrategyList(m.e(), str);
            List serverItems;
            try {
                int channelType;
                List<StrategyItem> b;
                List arrayList;
                List list;
                if (str.equals("3") || str.equals("1") || str.equals("2")) {
                    serverItems = CacheManager.getServerItems(m.e(), str);
                    if (serverItems == null) {
                        serverItems = new ArrayList();
                    }
                    serverItems.addAll(DefaultServer.a());
                    channelType = Tools.getChannelType(m.e());
                    com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "Tools.getChannelType = " + channelType);
                    switch (channelType) {
                        case 1:
                            try {
                                serverItems = p.a(serverItems, str);
                                q.i().a(this.p);
                                q.i().a(serverItems);
                                q.i().g();
                                return;
                            } catch (NullReturnException e) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get strategyItems(create tcp channel fail!) >> " + e.getMessage());
                                a(10101, "create tcp channel fail!");
                                return;
                            } catch (Exception e2) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> (create tcp channel fail!) >> " + e2.getMessage());
                                a(10101, "create tcp channel fail!");
                                return;
                            }
                        case 2:
                            try {
                                serverItems = p.b(serverItems, str);
                                f.i().a(this.o);
                                f.i().a(serverItems);
                                f.i().g();
                                return;
                            } catch (NullReturnException e3) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get strategyItems(create http channel fail!)>>" + e3.getMessage());
                                a(10101, "create http channel fail!");
                                return;
                            } catch (Exception e22) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> (create http channel fail!) >> " + e22.getMessage());
                                a(10101, "create http channel fail!");
                                return;
                            }
                        case 3:
                            try {
                                b = p.b(serverItems, str);
                                arrayList = new ArrayList();
                                for (StrategyItem strategyItem : b) {
                                    if (strategyItem.h()) {
                                        arrayList.add(strategyItem);
                                    }
                                }
                                f.i().a(this.o);
                                f.i().a(arrayList);
                                f.i().g();
                                return;
                            } catch (NullReturnException e32) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get strategyItems(create wap channel fail!)>>" + e32.getMessage());
                                a(10101, "create wap channel fail!");
                                return;
                            } catch (Exception e222) {
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> (create wap channel fail!) >> " + e222.getMessage());
                                a(10101, "create wap channel fail!");
                                return;
                            }
                        default:
                            try {
                                arrayList = p.a(serverItems, str);
                                try {
                                    list = arrayList;
                                    arrayList = p.b(serverItems, str);
                                    serverItems = list;
                                } catch (NullReturnException e322) {
                                    NullReturnException nullReturnException2 = e322;
                                    serverItems = arrayList;
                                    nullReturnException = nullReturnException2;
                                    com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get strategyItems(create default channel fail!)>>" + nullReturnException.getMessage());
                                    a(10101, "create default channel fail!");
                                    arrayList = null;
                                    q.i().a(this.p);
                                    q.i().a(serverItems);
                                    q.i().g();
                                    f.i().a(this.o);
                                    f.i().a(arrayList);
                                    f.i().g();
                                    return;
                                } catch (Exception e2222) {
                                    Exception exception2 = e2222;
                                    serverItems = arrayList;
                                    exception = exception2;
                                    com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> (create default channel fail!) >> " + exception.getMessage());
                                    a(10101, "create default channel fail!");
                                    arrayList = null;
                                    q.i().a(this.p);
                                    q.i().a(serverItems);
                                    q.i().g();
                                    f.i().a(this.o);
                                    f.i().a(arrayList);
                                    f.i().g();
                                    return;
                                }
                            } catch (NullReturnException e3222) {
                                nullReturnException = e3222;
                                serverItems = null;
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get strategyItems(create default channel fail!)>>" + nullReturnException.getMessage());
                                a(10101, "create default channel fail!");
                                arrayList = null;
                                q.i().a(this.p);
                                q.i().a(serverItems);
                                q.i().g();
                                f.i().a(this.o);
                                f.i().a(arrayList);
                                f.i().g();
                                return;
                            } catch (Exception e22222) {
                                exception = e22222;
                                serverItems = null;
                                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> (create default channel fail!) >> " + exception.getMessage());
                                a(10101, "create default channel fail!");
                                arrayList = null;
                                q.i().a(this.p);
                                q.i().a(serverItems);
                                q.i().g();
                                f.i().a(this.o);
                                f.i().a(arrayList);
                                f.i().g();
                                return;
                            }
                            q.i().a(this.p);
                            q.i().a(serverItems);
                            q.i().g();
                            f.i().a(this.o);
                            f.i().a(arrayList);
                            f.i().g();
                            return;
                    }
                }
                serverItems = DefaultServer.a(str);
                if (serverItems == null) {
                    serverItems = new ArrayList();
                }
                serverItems.addAll(DefaultServer.a());
                channelType = Tools.getChannelType(m.e());
                com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "Tools.getChannelType = " + channelType);
                switch (channelType) {
                    case 1:
                        serverItems = p.a(serverItems, str);
                        q.i().a(this.p);
                        q.i().a(serverItems);
                        q.i().g();
                        return;
                    case 2:
                        serverItems = p.b(serverItems, str);
                        f.i().a(this.o);
                        f.i().a(serverItems);
                        f.i().g();
                        return;
                    case 3:
                        b = p.b(serverItems, str);
                        arrayList = new ArrayList();
                        for (StrategyItem strategyItem2 : b) {
                            if (strategyItem2.h()) {
                                arrayList.add(strategyItem2);
                            }
                        }
                        f.i().a(this.o);
                        f.i().a(arrayList);
                        f.i().g();
                        return;
                    default:
                        arrayList = p.a(serverItems, str);
                        list = arrayList;
                        arrayList = p.b(serverItems, str);
                        serverItems = list;
                        q.i().a(this.p);
                        q.i().a(serverItems);
                        q.i().g();
                        f.i().a(this.o);
                        f.i().a(arrayList);
                        f.i().g();
                        return;
                }
            } catch (Exception e222222) {
                com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get local serverItems : " + e222222.getMessage());
                try {
                    serverItems = DefaultServer.a(str);
                } catch (Exception e2222222) {
                    com.tencent.android.tpush.a.a.h(Constants.HorseLogTag, ">> Can not get default serverItems : " + e2222222.toString());
                    serverItems = null;
                }
            }
        }
    }

    private void a(int i, String str) {
        if (this.h != null) {
            this.h.a(i, str);
        }
    }

    public synchronized void a(Intent intent) {
        try {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo != null) {
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "Connection state changed to - " + networkInfo.toString());
                }
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                int type = networkInfo.getType();
                if (booleanExtra) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "DisConnected with network type " + networkInfo.getTypeName());
                    }
                    m.b(m.e());
                } else if (State.CONNECTED == networkInfo.getState()) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "Connected with network type " + networkInfo.getTypeName());
                    }
                    c = type;
                    m.a(m.e(), 2000);
                } else if (State.DISCONNECTED == networkInfo.getState()) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "NetworkInfo.State.DISCONNECTED with network type = " + networkInfo.getTypeName());
                    }
                    if (c == -1 || c == type) {
                        m.b(m.e());
                    }
                } else if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.a.a.c("OptimalLinkSelector", "other network state - " + networkInfo.getState() + ". Do nothing.");
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.a("OptimalLinkSelector", "onNetworkChanged", th);
        }
    }

    public void b() {
        m++;
        if (m < com.tencent.android.tpush.service.a.a.a(m.e()).t) {
            a().a(f.h(m.e()));
        } else {
            a(10101, "create socket err");
        }
    }
}
