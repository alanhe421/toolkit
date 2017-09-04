package com.tencent.android.tpush.service.channel.a;

import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.b.d;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.qalsdk.core.c;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class a extends Thread {
    protected b a;
    public SocketChannel b = null;
    protected Selector c = null;
    protected TpnsSecurity d = new TpnsSecurity();
    protected d e = null;
    protected e f = null;
    protected String g = "";
    protected int h = 0;
    protected int i = 0;
    protected long j = Long.MAX_VALUE;
    protected com.tencent.android.tpush.service.channel.a k = null;
    private volatile boolean l = false;

    public a(SocketChannel socketChannel, b bVar) {
        super("TpnsClient");
        if (socketChannel.socket().isConnected()) {
            this.g = socketChannel.socket().getInetAddress() == null ? "" : socketChannel.socket().getInetAddress().getHostAddress();
            this.h = socketChannel.socket().getPort();
            this.i = 0;
            com.tencent.android.tpush.a.a.e("TpnsClient", "Connect to Xinge Server succeed!");
        } else {
            com.tencent.android.tpush.a.a.h("TpnsClient", "TpnsClient -> the socketChannel is not connected");
        }
        this.b = socketChannel;
        this.a = bVar;
    }

    protected boolean a() {
        if (this.e == null) {
            this.e = new g();
            ((g) this.e).a(this.d);
        }
        return true;
    }

    protected boolean b() {
        if (this.f == null) {
            ArrayList a = this.a.a(this, 1);
            if (!a.isEmpty()) {
                this.f = (e) a.get(0);
            }
            if (this.f != null) {
                ((h) this.f).a(this.d);
            }
        }
        return this.f != null;
    }

    public void a(a aVar, d dVar) {
        this.a.b(aVar, (i) dVar);
    }

    public void a(a aVar, e eVar) {
        if ((((h) eVar).h() & Opcodes.NEG_FLOAT) != 7) {
            this.a.a(aVar, (i) eVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14 = this;
        r0 = com.tencent.android.tpush.XGPushConfig.enableDebug;
        if (r0 == 0) goto L_0x000d;
    L_0x0004:
        r0 = "TpnsClient";
        r1 = "TpnsClient is running and ready for send and recevie msg.";
        com.tencent.android.tpush.a.a.d(r0, r1);
    L_0x000d:
        r4 = 0;
        r0 = java.nio.channels.Selector.open();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r14.c = r0;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r14.b;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = 0;
        r0.configureBlocking(r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r5 = java.nio.ByteBuffer.allocateDirect(r0);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r6 = new com.tencent.android.tpush.service.channel.c.a;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r1 = 0;
        r6.<init>(r0, r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r7 = new byte[r0];	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r8 = java.nio.ByteBuffer.allocateDirect(r0);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r9 = new com.tencent.android.tpush.service.channel.c.a;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = -1;
        r1 = 0;
        r9.<init>(r0, r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r10 = new byte[r0];	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r8.flip();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r2 = 0;
    L_0x0042:
        r0 = r14.l;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 != 0) goto L_0x007f;
    L_0x0046:
        r0 = r14.b;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = r14.c;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = 1;
        r0.register(r1, r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r14.b();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 != 0) goto L_0x0060;
    L_0x0054:
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 > 0) goto L_0x0060;
    L_0x005a:
        r0 = r9.c();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 <= 0) goto L_0x0068;
    L_0x0060:
        r0 = r14.b;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = r14.c;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = 4;
        r0.register(r1, r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x0068:
        r0 = r14.g();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 == 0) goto L_0x00ad;
    L_0x006e:
        r0 = r14.e;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 != 0) goto L_0x00ad;
    L_0x0072:
        r0 = r14.f;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 != 0) goto L_0x00ad;
    L_0x0076:
        r0 = "TpnsClient";
        r1 = ">> retired!!!";
        com.tencent.android.tpush.a.a.h(r0, r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x007f:
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x0292 }
        r0.close();	 Catch:{ Exception -> 0x0292 }
    L_0x0085:
        r0 = r14.b;	 Catch:{ Exception -> 0x02b2 }
        r0.close();	 Catch:{ Exception -> 0x02b2 }
    L_0x008a:
        monitor-exit(r14);	 Catch:{ all -> 0x02af }
        if (r4 == 0) goto L_0x02cf;
    L_0x008d:
        r0 = "TpnsClient";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "<<< Run <<< exit!!! cause: ";
        r1 = r1.append(r2);
        r1 = r1.append(r4);
        r1 = r1.toString();
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14, r4);
    L_0x00ac:
        return;
    L_0x00ad:
        r0 = r14.c;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0.select(r2);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r2 = 0;
        r0 = r14.f;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 == 0) goto L_0x0114;
    L_0x00b8:
        r0 = r14.f;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r0.a();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = 0;
        r11 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1));
        if (r11 > 0) goto L_0x010f;
    L_0x00c4:
        r0 = new java.util.concurrent.TimeoutException;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = "发送超时";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        throw r0;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x00cd:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel IOException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0519 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x0519 }
        r2 = 10103; // 0x2777 float:1.4157E-41 double:4.9915E-320;
        r3 = "TpnsClient发生IO异常，链路可能被关闭";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x0519 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x02f3 }
        r0.close();	 Catch:{ Exception -> 0x02f3 }
    L_0x00e7:
        r0 = r14.b;	 Catch:{ Exception -> 0x0313 }
        r0.close();	 Catch:{ Exception -> 0x0313 }
    L_0x00ec:
        monitor-exit(r14);	 Catch:{ all -> 0x0310 }
        if (r1 == 0) goto L_0x0330;
    L_0x00ef:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00ac;
    L_0x010f:
        r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r11 >= 0) goto L_0x0170;
    L_0x0113:
        r2 = r0;
    L_0x0114:
        r0 = r14.e;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 == 0) goto L_0x0177;
    L_0x0118:
        r0 = r14.e;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r0.a();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = 0;
        r11 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1));
        if (r11 > 0) goto L_0x0172;
    L_0x0124:
        r0 = new java.util.concurrent.TimeoutException;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = "接收超时";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        throw r0;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x012d:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel InnerException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0519 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x0519 }
        r2 = 10104; // 0x2778 float:1.4159E-41 double:4.992E-320;
        r3 = "TpnsClient发生内部异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x0519 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x0354 }
        r0.close();	 Catch:{ Exception -> 0x0354 }
    L_0x0147:
        r0 = r14.b;	 Catch:{ Exception -> 0x0374 }
        r0.close();	 Catch:{ Exception -> 0x0374 }
    L_0x014c:
        monitor-exit(r14);	 Catch:{ all -> 0x0371 }
        if (r1 == 0) goto L_0x0391;
    L_0x014f:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00ac;
    L_0x0170:
        r0 = r2;
        goto L_0x0113;
    L_0x0172:
        r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r11 >= 0) goto L_0x01f6;
    L_0x0176:
        r2 = r0;
    L_0x0177:
        r0 = r14.c;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r0.selectedKeys();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = r0.iterator();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x0181:
        r0 = r1.hasNext();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 == 0) goto L_0x0042;
    L_0x0187:
        r0 = r1.next();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = (java.nio.channels.SelectionKey) r0;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r0.isReadable();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r11 == 0) goto L_0x020c;
    L_0x0193:
        r5.clear();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r6.d();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r5.limit(r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r14.b;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = r5.slice();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r11.read(r12);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = -1;
        if (r11 != r12) goto L_0x01f9;
    L_0x01aa:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r1 = "socket channel read return -1";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        throw r0;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x01b3:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel UnexpectedDataException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0519 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x0519 }
        r2 = 10108; // 0x277c float:1.4164E-41 double:4.994E-320;
        r3 = "TpnsClient发生非预期数据异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x0519 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x03b5 }
        r0.close();	 Catch:{ Exception -> 0x03b5 }
    L_0x01cd:
        r0 = r14.b;	 Catch:{ Exception -> 0x03d5 }
        r0.close();	 Catch:{ Exception -> 0x03d5 }
    L_0x01d2:
        monitor-exit(r14);	 Catch:{ all -> 0x03d2 }
        if (r1 == 0) goto L_0x03f2;
    L_0x01d5:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00ac;
    L_0x01f6:
        r0 = r2;
        goto L_0x0176;
    L_0x01f9:
        r12 = 0;
        r5.get(r7, r12, r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = r6.a();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r13 = 0;
        r12.write(r7, r13, r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r6.b();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r14.a(r11);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x020c:
        r0 = r0.isWritable();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 == 0) goto L_0x0245;
    L_0x0212:
        r0 = r9.a();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r14.a(r0);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r9.c();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 <= 0) goto L_0x0245;
    L_0x021f:
        r8.compact();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = r9.c();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        if (r0 >= r11) goto L_0x028d;
    L_0x022c:
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x0230:
        r11 = r9.b();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r12 = 0;
        r0 = r11.read(r10, r12, r0);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r11 = 0;
        r8.put(r10, r11, r0);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r8.flip();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0 = r14.b;	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        r0.write(r8);	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
    L_0x0245:
        r1.remove();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        goto L_0x0181;
    L_0x024a:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel TimeoutException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0519 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x0519 }
        r2 = 10105; // 0x2779 float:1.416E-41 double:4.9925E-320;
        r3 = "TpnsClient发生超时异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x0519 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x0416 }
        r0.close();	 Catch:{ Exception -> 0x0416 }
    L_0x0264:
        r0 = r14.b;	 Catch:{ Exception -> 0x0436 }
        r0.close();	 Catch:{ Exception -> 0x0436 }
    L_0x0269:
        monitor-exit(r14);	 Catch:{ all -> 0x0433 }
        if (r1 == 0) goto L_0x0453;
    L_0x026c:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00ac;
    L_0x028d:
        r0 = r9.c();	 Catch:{ IOException -> 0x00cd, InnerException -> 0x012d, UnexpectedDataException -> 0x01b3, TimeoutException -> 0x024a, Exception -> 0x0477 }
        goto L_0x0230;
    L_0x0292:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02af }
        r2.<init>();	 Catch:{ all -> 0x02af }
        r3 = ">>> Run >>> selector.close() ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x02af }
        r0 = r2.append(r0);	 Catch:{ all -> 0x02af }
        r0 = r0.toString();	 Catch:{ all -> 0x02af }
        com.tencent.android.tpush.a.a.h(r1, r0);	 Catch:{ all -> 0x02af }
        goto L_0x0085;
    L_0x02af:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x02af }
        throw r0;
    L_0x02b2:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02af }
        r2.<init>();	 Catch:{ all -> 0x02af }
        r3 = ">>> Run >>> socketChannel.close(): ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x02af }
        r0 = r2.append(r0);	 Catch:{ all -> 0x02af }
        r0 = r0.toString();	 Catch:{ all -> 0x02af }
        com.tencent.android.tpush.a.a.h(r1, r0);	 Catch:{ all -> 0x02af }
        goto L_0x008a;
    L_0x02cf:
        r0 = r14.l;
        if (r0 == 0) goto L_0x02e3;
    L_0x02d3:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x02e3:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x02f3:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0310 }
        r3.<init>();	 Catch:{ all -> 0x0310 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0310 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0310 }
        r0 = r0.toString();	 Catch:{ all -> 0x0310 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0310 }
        goto L_0x00e7;
    L_0x0310:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0310 }
        throw r0;
    L_0x0313:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0310 }
        r3.<init>();	 Catch:{ all -> 0x0310 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0310 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0310 }
        r0 = r0.toString();	 Catch:{ all -> 0x0310 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0310 }
        goto L_0x00ec;
    L_0x0330:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0344;
    L_0x0334:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x0344:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x0354:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0371 }
        r3.<init>();	 Catch:{ all -> 0x0371 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0371 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0371 }
        r0 = r0.toString();	 Catch:{ all -> 0x0371 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0371 }
        goto L_0x0147;
    L_0x0371:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0371 }
        throw r0;
    L_0x0374:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0371 }
        r3.<init>();	 Catch:{ all -> 0x0371 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0371 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0371 }
        r0 = r0.toString();	 Catch:{ all -> 0x0371 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0371 }
        goto L_0x014c;
    L_0x0391:
        r0 = r14.l;
        if (r0 == 0) goto L_0x03a5;
    L_0x0395:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x03a5:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x03b5:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03d2 }
        r3.<init>();	 Catch:{ all -> 0x03d2 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x03d2 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x03d2 }
        r0 = r0.toString();	 Catch:{ all -> 0x03d2 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x03d2 }
        goto L_0x01cd;
    L_0x03d2:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x03d2 }
        throw r0;
    L_0x03d5:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03d2 }
        r3.<init>();	 Catch:{ all -> 0x03d2 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x03d2 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x03d2 }
        r0 = r0.toString();	 Catch:{ all -> 0x03d2 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x03d2 }
        goto L_0x01d2;
    L_0x03f2:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0406;
    L_0x03f6:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x0406:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x0416:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0433 }
        r3.<init>();	 Catch:{ all -> 0x0433 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0433 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0433 }
        r0 = r0.toString();	 Catch:{ all -> 0x0433 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0433 }
        goto L_0x0264;
    L_0x0433:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0433 }
        throw r0;
    L_0x0436:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0433 }
        r3.<init>();	 Catch:{ all -> 0x0433 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0433 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0433 }
        r0 = r0.toString();	 Catch:{ all -> 0x0433 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0433 }
        goto L_0x0269;
    L_0x0453:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0467;
    L_0x0457:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x0467:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x0477:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel Exception";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x0519 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x0519 }
        r2 = 10109; // 0x277d float:1.4166E-41 double:4.9945E-320;
        r3 = "TpnsClient发生未知异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x0519 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x04ba }
        r0.close();	 Catch:{ Exception -> 0x04ba }
    L_0x0491:
        r0 = r14.b;	 Catch:{ Exception -> 0x04d9 }
        r0.close();	 Catch:{ Exception -> 0x04d9 }
    L_0x0496:
        monitor-exit(r14);	 Catch:{ all -> 0x04d6 }
        if (r1 == 0) goto L_0x04f5;
    L_0x0499:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00ac;
    L_0x04ba:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x04d6 }
        r3.<init>();	 Catch:{ all -> 0x04d6 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x04d6 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x04d6 }
        r0 = r0.toString();	 Catch:{ all -> 0x04d6 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x04d6 }
        goto L_0x0491;
    L_0x04d6:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x04d6 }
        throw r0;
    L_0x04d9:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x04d6 }
        r3.<init>();	 Catch:{ all -> 0x04d6 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x04d6 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x04d6 }
        r0 = r0.toString();	 Catch:{ all -> 0x04d6 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x04d6 }
        goto L_0x0496;
    L_0x04f5:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0509;
    L_0x04f9:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00ac;
    L_0x0509:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00ac;
    L_0x0519:
        r0 = move-exception;
        monitor-enter(r14);
        r1 = r14.c;	 Catch:{ Exception -> 0x0548 }
        r1.close();	 Catch:{ Exception -> 0x0548 }
    L_0x0520:
        r1 = r14.b;	 Catch:{ Exception -> 0x0567 }
        r1.close();	 Catch:{ Exception -> 0x0567 }
    L_0x0525:
        monitor-exit(r14);	 Catch:{ all -> 0x0564 }
        if (r4 == 0) goto L_0x0583;
    L_0x0528:
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r4);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.a(r14, r4);
    L_0x0547:
        throw r0;
    L_0x0548:
        r1 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0564 }
        r3.<init>();	 Catch:{ all -> 0x0564 }
        r5 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0564 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0564 }
        r1 = r1.toString();	 Catch:{ all -> 0x0564 }
        com.tencent.android.tpush.a.a.h(r2, r1);	 Catch:{ all -> 0x0564 }
        goto L_0x0520;
    L_0x0564:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0564 }
        throw r0;
    L_0x0567:
        r1 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0564 }
        r3.<init>();	 Catch:{ all -> 0x0564 }
        r5 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0564 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0564 }
        r1 = r1.toString();	 Catch:{ all -> 0x0564 }
        com.tencent.android.tpush.a.a.h(r2, r1);	 Catch:{ all -> 0x0564 }
        goto L_0x0525;
    L_0x0583:
        r1 = r14.l;
        if (r1 == 0) goto L_0x0596;
    L_0x0587:
        r1 = "TpnsClient";
        r2 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.a(r14);
        goto L_0x0547;
    L_0x0596:
        r1 = "TpnsClient";
        r2 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.b(r14);
        goto L_0x0547;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.a.a.run():void");
    }

    protected int a(InputStream inputStream) {
        int i = 0;
        while (inputStream.available() > 0) {
            a();
            if (this.e != null) {
                i += this.e.a(inputStream);
                if (!this.e.b()) {
                    com.tencent.android.tpush.a.a.h(Constants.TcpRecvPackLogTag, ">> recvHandle not success");
                    break;
                }
                a(this, this.e);
                this.e = null;
            }
        }
        return i;
    }

    protected int a(OutputStream outputStream) {
        int i = 0;
        if (!g()) {
            b();
        }
        if (this.f != null) {
            i = this.f.a(outputStream);
            if (this.f.b()) {
                a(this, this.f);
                this.f = null;
            }
            if (b()) {
                h();
            }
        }
        return i;
    }

    public synchronized void start() {
        super.start();
    }

    public synchronized void c() {
        this.l = true;
        h();
    }

    public synchronized boolean d() {
        boolean isConnected;
        if (this.b != null) {
            isConnected = this.b.isConnected();
        } else {
            isConnected = false;
        }
        return isConnected;
    }

    public boolean e() {
        return this.i == 1;
    }

    public com.tencent.android.tpush.service.channel.a f() {
        boolean z = true;
        if (this.k == null) {
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(0);
            objArr[1] = this.g;
            objArr[2] = Integer.valueOf(1);
            objArr[3] = Integer.valueOf(this.h);
            objArr[4] = Integer.valueOf(2);
            if (this.i != 1) {
                z = false;
            }
            objArr[5] = Boolean.valueOf(z);
            this.k = new com.tencent.android.tpush.service.channel.a(objArr);
        }
        return this.k;
    }

    protected boolean g() {
        return System.currentTimeMillis() > this.j;
    }

    public void h() {
        try {
            if (this.c != null && this.c.isOpen()) {
                this.c.wakeup();
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("TpnsClient", ">>selector wakeup err", th);
        }
    }

    public String toString() {
        return new StringBuffer(getClass().getSimpleName()).append("(ip:").append(this.g).append(",port:").append(this.h).append(",protocol:").append(this.i == 1 ? c.d : "tcp").append(")").toString();
    }
}
