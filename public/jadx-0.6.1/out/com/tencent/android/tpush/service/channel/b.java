package com.tencent.android.tpush.service.channel;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.g;
import com.tencent.android.tpush.horse.k;
import com.tencent.android.tpush.horse.l;
import com.tencent.android.tpush.service.XGWatchdog;
import com.tencent.android.tpush.service.b.j;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a.a;
import com.tencent.android.tpush.service.channel.a.c;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import com.tencent.android.tpush.service.u;
import com.tencent.android.tpush.service.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import qalsdk.o;

/* compiled from: ProGuard */
public final class b implements l, com.tencent.android.tpush.service.channel.a.b {
    public static int a = 0;
    public static int b = 0;
    public static int c = 290000;
    public static int d = 180000;
    public static int e = o.c;
    public static int f = c;
    protected static int g = 0;
    protected static Boolean h = null;
    private static volatile long r = 0;
    private static volatile long s = 0;
    private static String t = "";
    private Handler i;
    private ArrayList j;
    private Map k;
    private Map l;
    private a m;
    private volatile boolean n;
    private PendingIntent o;
    private o p;
    private volatile boolean q;
    private k u;
    private Handler v;
    private p w;

    private void a(int r5, com.tencent.android.tpush.service.channel.o r6) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r0 = r6.a();
        if (r0 == 0) goto L_0x000d;
    L_0x0006:
        r0 = com.tencent.android.tpush.service.m.e();
        com.tencent.android.tpush.common.p.k(r0);
    L_0x000d:
        monitor-enter(r4);	 Catch:{ Throwable -> 0x0061 }
        r0 = r4.j;	 Catch:{ Throwable -> 0x0061 }
        r0 = r0.size();	 Catch:{ Throwable -> 0x0061 }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ Throwable -> 0x0061 }
        if (r0 >= r1) goto L_0x0075;	 Catch:{ Throwable -> 0x0061 }
    L_0x0018:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0061 }
        r6.a = r0;	 Catch:{ Throwable -> 0x0061 }
        r0 = -1;	 Catch:{ Throwable -> 0x0061 }
        if (r5 != r0) goto L_0x0058;	 Catch:{ Throwable -> 0x0061 }
    L_0x0021:
        r0 = r4.j;	 Catch:{ Throwable -> 0x0061 }
        r0.add(r6);	 Catch:{ Throwable -> 0x0061 }
    L_0x0026:
        r0 = r4.m;	 Catch:{ Throwable -> 0x0061 }
        if (r0 == 0) goto L_0x002f;	 Catch:{ Throwable -> 0x0061 }
    L_0x002a:
        r0 = r4.m;	 Catch:{ Throwable -> 0x0061 }
        r0.h();	 Catch:{ Throwable -> 0x0061 }
    L_0x002f:
        r4.e();	 Catch:{ Throwable -> 0x0061 }
        monitor-exit(r4);	 Catch:{ Throwable -> 0x0061 }
        r0 = new com.tencent.android.tpush.service.channel.m;	 Catch:{ Throwable -> 0x0061 }
        r1 = 0;	 Catch:{ Throwable -> 0x0061 }
        r0.<init>(r4, r1);	 Catch:{ Throwable -> 0x0061 }
        r1 = r4.l;	 Catch:{ Throwable -> 0x0061 }
        r1.put(r6, r0);	 Catch:{ Throwable -> 0x0061 }
        r1 = r4.i;	 Catch:{ Throwable -> 0x0061 }
        r2 = com.tencent.android.tpush.service.m.e();	 Catch:{ Throwable -> 0x0061 }
        r2 = com.tencent.android.tpush.service.a.a.a(r2);	 Catch:{ Throwable -> 0x0061 }
        r2 = r2.f;	 Catch:{ Throwable -> 0x0061 }
        r2 = (long) r2;	 Catch:{ Throwable -> 0x0061 }
        r1.postDelayed(r0, r2);	 Catch:{ Throwable -> 0x0061 }
        r0 = r6.a();
        if (r0 == 0) goto L_0x0057;
    L_0x0054:
        com.tencent.android.tpush.common.p.a();
    L_0x0057:
        return;
    L_0x0058:
        r0 = r4.j;	 Catch:{ Throwable -> 0x0061 }
        r0.add(r5, r6);	 Catch:{ Throwable -> 0x0061 }
        goto L_0x0026;	 Catch:{ Throwable -> 0x0061 }
    L_0x005e:
        r0 = move-exception;	 Catch:{ Throwable -> 0x0061 }
        monitor-exit(r4);	 Catch:{ Throwable -> 0x0061 }
        throw r0;	 Catch:{ Throwable -> 0x0061 }
    L_0x0061:
        r0 = move-exception;
        r1 = "XGService";	 Catch:{ all -> 0x0096 }
        r2 = "messageInQueue";	 Catch:{ all -> 0x0096 }
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0096 }
        r0 = r6.a();
        if (r0 == 0) goto L_0x0057;
    L_0x0071:
        com.tencent.android.tpush.common.p.a();
        goto L_0x0057;
    L_0x0075:
        r0 = "XGService";	 Catch:{ Throwable -> 0x0061 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0061 }
        r1.<init>();	 Catch:{ Throwable -> 0x0061 }
        r2 = ">>FG messageInQueue is full,size:";	 Catch:{ Throwable -> 0x0061 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0061 }
        r2 = r4.j;	 Catch:{ Throwable -> 0x0061 }
        r2 = r2.size();	 Catch:{ Throwable -> 0x0061 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x0061 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0061 }
        com.tencent.android.tpush.a.a.h(r0, r1);	 Catch:{ Throwable -> 0x0061 }
        goto L_0x0026;
    L_0x0096:
        r0 = move-exception;
        r1 = r6.a();
        if (r1 == 0) goto L_0x00a0;
    L_0x009d:
        com.tencent.android.tpush.common.p.a();
    L_0x00a0:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.b.a(int, com.tencent.android.tpush.service.channel.o):void");
    }

    public static b a() {
        return n.a;
    }

    private b() {
        this.i = null;
        this.j = new ArrayList();
        this.k = new ConcurrentHashMap();
        this.l = new ConcurrentHashMap();
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = true;
        this.u = new c(this);
        this.v = new d(this);
        this.w = new e(this);
        g.a().a(this);
        this.i = com.tencent.android.tpush.common.g.a().b();
    }

    public void b() {
        e();
    }

    public void c() {
        this.n = false;
        if (this.m != null) {
            this.m.c();
            this.m = null;
        }
    }

    public void d() {
        c();
        e();
    }

    public void e() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.a("TpnsChannel", "Action -> checkAndSetupClient( tpnsClient = " + this.m + ", isClientCreating = " + this.n + ")");
        }
        synchronized (this) {
            if (this.m == null && !this.n) {
                this.n = true;
                try {
                    g.a().a(this.u);
                } catch (Throwable e) {
                    com.tencent.android.tpush.a.a.c("TpnsChannel", "createOptimalSocketChannel error", e);
                }
            } else if (!(this.n || this.m == null || this.m.d())) {
                com.tencent.android.tpush.a.a.h("TpnsChannel", "The socket Channel is unconnected");
                try {
                    this.m.c();
                    g.a().a(this.u);
                } catch (Throwable e2) {
                    com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "createOptimalSocketChannel error", e2);
                }
            }
        }
    }

    protected synchronized boolean f() {
        boolean z = false;
        synchronized (this) {
            if (com.tencent.android.tpush.service.d.a.d(m.e())) {
                int j = p.j(m.e());
                if (p.i(m.e()) || j > 0) {
                    int i = ((g + 1) * 2) * 1000;
                    g++;
                    if (g <= 3) {
                        if (i > d) {
                            i = d;
                        }
                        if (g <= 3 || j == 1) {
                            if (!this.v.hasMessages(1000)) {
                                if (XGPushConfig.enableDebug) {
                                    com.tencent.android.tpush.a.a.c("TpnsChannel", "onDisconnected and retry HANDLER_CHECKANDSETUP " + i + " retry times = " + g);
                                }
                                this.v.sendEmptyMessageDelayed(1000, (long) i);
                            }
                            z = true;
                        }
                    }
                }
            }
        }
        return z;
    }

    public void a(JceStruct jceStruct, p pVar) {
        if (jceStruct != null) {
            try {
                a(-1, new o(jceStruct, pVar));
                return;
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "sendMessage error ", e);
                return;
            }
        }
        com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "sendMessage null jceMessage");
    }

    public void a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - s > 120000 || z) {
            s = currentTimeMillis;
            o b = com.tencent.android.tpush.service.p.a().b();
            if (b != null) {
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> sendReconnMessage with token - " + CacheManager.getToken(m.e()));
                }
                if (m.e() != null && !"0".equals(CacheManager.getToken(m.e()))) {
                    a(0, b);
                    XGWatchdog.getInstance(m.e()).sendAllLocalXGAppList();
                }
            }
        }
    }

    private synchronized void h() {
        if (this.j.isEmpty()) {
            if (this.p == null) {
                this.p = new o((short) 7, null, this.w);
            }
            if (this.p.d == null) {
                this.p = new o((short) 7, null, this.w);
            }
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> send heartbeat ");
            }
            a(-1, this.p);
            if ((a > 0 && a % 3 == 0) || a == 2) {
                f.b(m.e());
            }
            if (a % 4 == 0) {
                XGWatchdog.getInstance(m.e()).sendAllLocalXGAppList();
            } else if (b >= 5) {
                com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "heartbeat to watchdog failed too many time , start watchdog again");
                b = 0;
                XGWatchdog.getInstance(m.e()).startWatchdog();
            } else {
                XGWatchdog.getInstance(m.e()).sendHeartbeat2Watchdog("heartbeat:", new f(this));
            }
        }
        i();
        w.a(m.e()).a();
    }

    public int b(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - r > 120000 || z) {
            r = currentTimeMillis;
            Context e = m.e();
            if (!(e == null || f.a(e.getPackageName()))) {
                ArrayList d = com.tencent.android.tpush.service.b.a.a().d(e);
                if (d != null && d.size() > 0) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "Action -> trySendCachedMsgIntent with CachedMsgList size = " + d.size());
                    }
                    List arrayList = new ArrayList();
                    for (int i = 0; i < d.size(); i++) {
                        CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) d.get(i);
                        try {
                            String decrypt = Rijndael.decrypt(cachedMessageIntent.intent);
                            if (f.a(decrypt)) {
                                arrayList.add(cachedMessageIntent);
                            } else {
                                Intent parseUri = Intent.parseUri(decrypt, 1);
                                String str = parseUri.getPackage();
                                parseUri.getLongExtra(MessageKey.MSG_CREATE_MULTIPKG, 0);
                                if (f.d(m.e(), str, parseUri.getLongExtra("accId", 0))) {
                                    com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                                    if (registerInfoByPkgName == null || registerInfoByPkgName.e <= 0) {
                                        long longExtra = parseUri.getLongExtra(MessageKey.MSG_ID, 0);
                                        parseUri.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
                                        long longExtra2 = parseUri.getLongExtra(MessageKey.MSG_EXPIRE_TIME, 0);
                                        com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "Action -> trySendCachedMsgIntent msgId = " + longExtra + ",appPkgName=" + str);
                                        if (longExtra2 <= 0) {
                                            int intExtra = parseUri.getIntExtra("ttl", 0);
                                            if (intExtra < 0 || intExtra > 259200000) {
                                                intExtra = 259200000;
                                            }
                                            parseUri.putExtra(MessageKey.MSG_EXPIRE_TIME, ((long) intExtra) + currentTimeMillis);
                                        } else if (currentTimeMillis > longExtra2) {
                                            arrayList.add(cachedMessageIntent);
                                            com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "currentTime:" + currentTimeMillis + " > expire_time:" + longExtra2 + ", remove msg:" + cachedMessageIntent);
                                        }
                                        com.tencent.android.tpush.service.b.a.a().a(parseUri.getStringExtra(MessageKey.MSG_DATE), parseUri, str);
                                    }
                                } else {
                                    arrayList.add(cachedMessageIntent);
                                    com.tencent.android.tpush.a.a.h("TpnsChannel", str + " is uninstalled , discard the msg and report to the server");
                                    com.tencent.android.tpush.service.p.a().a(str);
                                    j.a().a(m.e(), str);
                                    com.tencent.android.tpush.service.b.a.a().d(m.e(), str, new ArrayList());
                                }
                            }
                        } catch (Throwable e2) {
                            com.tencent.android.tpush.a.a.c("TpnsChannel", "", e2);
                        }
                    }
                    if (arrayList.size() > 0) {
                        com.tencent.android.tpush.service.b.a.a().a(e, arrayList, d);
                    }
                    return d.size();
                }
            }
        }
        return 0;
    }

    private void i() {
        try {
            if (this.o == null) {
                m.e().registerReceiver(new g(this), new IntentFilter("com.tencent.android.tpush.service.channel.heartbeatIntent"));
                this.o = PendingIntent.getBroadcast(m.e(), 0, new Intent("com.tencent.android.tpush.service.channel.heartbeatIntent"), SigType.WLOGIN_PT4Token);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (f > e) {
                f = e;
            }
            f = com.tencent.android.tpush.common.m.a(m.e(), "com.tencent.android.xg.wx.HeartbeatIntervalMs", f);
            u.a().a(0, currentTimeMillis + ((long) f), this.o);
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("TpnsChannel", "scheduleHeartbeat error", th);
        }
    }

    public synchronized ArrayList a(a aVar, int i) {
        ArrayList arrayList;
        if (i < 1) {
            i = 1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.k.get(aVar);
        arrayList = new ArrayList(i);
        if (!this.j.isEmpty()) {
            Iterator it = this.j.iterator();
            o oVar = (o) it.next();
            h hVar = new h(oVar.b());
            oVar.a(hVar);
            arrayList.add(hVar);
            oVar.b = currentTimeMillis;
            if (!oVar.a()) {
                concurrentHashMap.put(Integer.valueOf(oVar.c()), oVar);
            }
            it.remove();
            int i2 = i - 1;
            boolean z = oVar.c instanceof TpnsReconnectReq;
            while (it.hasNext()) {
                oVar = (o) it.next();
                if (z && ((oVar.c instanceof TpnsReconnectReq) || (oVar.c instanceof TpnsPushVerifyReq))) {
                    if (oVar.d != null) {
                        this.i.post(new h(this, oVar));
                    }
                    it.remove();
                } else {
                    int i3 = i2 - 1;
                    if (i2 > 0) {
                        hVar = new h(oVar.b());
                        oVar.a(hVar);
                        arrayList.add(hVar);
                        oVar.b = currentTimeMillis;
                        if (!oVar.a()) {
                            concurrentHashMap.put(Integer.valueOf(oVar.c()), oVar);
                        }
                        it.remove();
                    }
                    i2 = i3;
                }
            }
        }
        return arrayList;
    }

    public void a(a aVar, ChannelException channelException) {
        com.tencent.android.tpush.a.a.h("TpnsChannel", "clientExceptionOccurs(isHttpClient : " + (aVar instanceof c) + "," + channelException + ")");
        this.i.post(new k(this, aVar, channelException, true));
    }

    public void a(a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.h("TpnsChannel", "Action -> clientDidCancelled " + aVar);
        }
        this.i.post(new k(this, aVar, new ChannelException(10102, "TpnsClient is cancelled!"), false));
    }

    public void b(a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.h("TpnsChannel", "Action -> clientDidRetired " + aVar);
        }
        this.i.post(new k(this, aVar, new ChannelException(10105, "TpnsMessage timeout!"), false));
    }

    public void a(a aVar, i iVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> clientDidSendPacket packet : " + iVar.m());
        }
        o oVar = (o) ((ConcurrentHashMap) this.k.get(aVar)).get(Integer.valueOf(iVar.i()));
        if (oVar != null) {
            oVar.b = System.currentTimeMillis();
        } else {
            com.tencent.android.tpush.a.a.h("TpnsChannel", ">> message(" + iVar.i() + ") not in the sentQueue!");
        }
    }

    public synchronized void b(a aVar, i iVar) {
        a().b(false);
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> clientDidReceivePacket packet : " + iVar.m());
        }
        switch (iVar.j()) {
            case (short) 1:
            case (short) 10:
                if (iVar.e()) {
                    com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> clientDidReceivePacket RequestSuccRunnable NEV1 : " + iVar.m());
                    this.i.post(new l(this, aVar, iVar));
                } else {
                    com.tencent.android.tpush.a.a.c("TpnsChannel", "Action -> clientDidReceivePacket PushMessageRunnable NEV1 : " + iVar.m());
                    this.i.post(new j(this, aVar, iVar));
                }
                i();
                break;
            case (short) 20:
                this.i.post(new i(this, aVar, iVar));
                break;
            default:
                com.tencent.android.tpush.a.a.h("TpnsChannel", "Action -> clientDidReceivePacket unkonwn protocol : " + iVar.m());
                i();
                break;
        }
    }
}
