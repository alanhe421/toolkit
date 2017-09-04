package com.tencent.upload.network.b;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.c.c;
import com.tencent.upload.common.j;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.a.b;
import com.tencent.upload.network.base.d;
import com.tencent.upload.network.base.f;
import com.tencent.upload.task.impl.HandshakeTask;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class h implements a, d {
    private HandshakeTask a;
    private com.tencent.upload.network.base.a b;
    private k c;
    private k d;
    private final WeakReference<b> e;
    private boolean f;
    private ByteBuffer g;
    private volatile b h = b.UNKONWN;
    private String i;
    private String j;
    private Handler k;
    private ConcurrentLinkedQueue<com.tencent.upload.c.a> l;
    private SparseArray<com.tencent.upload.c.a> m;
    private SparseArray<a> n;
    private final int o;
    private int p = 0;
    private final FileType q;

    static final class a {
        public final com.tencent.upload.c.a a;
        public Runnable b;
        public long c = System.currentTimeMillis();

        public a(com.tencent.upload.c.a aVar) {
            this.a = aVar;
        }
    }

    public h(FileType fileType, boolean z, Looper looper, b bVar) {
        this.q = fileType;
        this.o = hashCode();
        this.f = z;
        this.k = new Handler(looper);
        this.g = ByteBuffer.allocate(128);
        this.l = new ConcurrentLinkedQueue();
        this.m = new SparseArray();
        this.n = new SparseArray();
        this.e = new WeakReference(bVar);
        a(b.UNKONWN);
    }

    private void a(c cVar) {
        if (this.a == null || cVar == null || !"CMD_HANDSHAKE".equals(cVar.b())) {
            b bVar = (b) this.e.get();
            if (bVar != null) {
                bVar.a(this, RetCode.HANDSHAKE_FAILED.getCode(), RetCode.HANDSHAKE_FAILED.getDesc());
            }
        } else {
            this.a.onResponse(null, cVar);
        }
        this.a = null;
    }

    private void a(b bVar) {
        if (this.h != bVar) {
            com.tencent.upload.common.a.a.c(j(), "Sesseion State Change sid=" + this.o + " old_state=" + this.h.toString() + " new_state=" + bVar.toString());
            this.h = bVar;
            if (bVar == b.NO_CONNECT) {
                l();
            }
        }
    }

    static /* synthetic */ void a(h hVar, int i) {
        com.tencent.upload.c.a aVar = (com.tencent.upload.c.a) hVar.m.get(i);
        if (aVar != null) {
            int c = aVar.c();
            if (((a) hVar.n.get(c)) != null) {
                com.tencent.upload.common.a.a.c(hVar.j(), "timeout runnable has been started. reqId=" + c + " sid=" + hVar.o);
                return;
            }
            a aVar2 = new a(aVar);
            Runnable jVar = new j(hVar, c, aVar);
            hVar.k.removeCallbacks(aVar2.b);
            aVar2.b = jVar;
            hVar.n.put(c, aVar2);
            hVar.k.postDelayed(jVar, (long) j.i());
        }
    }

    static /* synthetic */ void a(h hVar, d dVar) {
        if (dVar != hVar.b) {
            ((com.tencent.upload.network.base.a) dVar).b();
            return;
        }
        com.tencent.upload.common.a.a.a(hVar.j(), "Session DisConnected. sid=" + hVar.o + " currState=" + hVar.h.toString());
        b bVar = (b) hVar.e.get();
        if (bVar != null) {
            if (hVar.h == b.ESTABLISHED) {
                bVar.b(hVar);
            } else if (hVar.h != b.HANDSHAKE) {
                bVar.a(hVar, RetCode.SESSION_DISCONNECT.getCode(), RetCode.SESSION_DISCONNECT.getDesc());
            }
        }
        hVar.a(b.NO_CONNECT);
    }

    static /* synthetic */ void a(h hVar, d dVar, int i, int i2) {
        if (dVar != hVar.b) {
            ((com.tencent.upload.network.base.a) dVar).b();
            return;
        }
        com.tencent.upload.common.a.a.c(hVar.j(), "Send Timeout! sid=" + hVar.o + " seq:" + i + " reason:" + i2 + " currState=" + hVar.h.toString());
        if (hVar.h == b.ESTABLISHED) {
            com.tencent.upload.c.a aVar = (com.tencent.upload.c.a) hVar.m.get(i);
            if (aVar != null && aVar.g() != null) {
                aVar.g().onRequestTimeout(aVar);
                hVar.a(false);
            }
        } else if (hVar.h == b.HANDSHAKE) {
            b bVar = (b) hVar.e.get();
            if (bVar != null) {
                bVar.a(hVar, RetCode.HANDSHAKE_TIMEOUT.getCode(), RetCode.HANDSHAKE_TIMEOUT.getDesc());
            }
        }
    }

    static /* synthetic */ void a(h hVar, d dVar, boolean z, int i, String str) {
        if (dVar != hVar.b) {
            com.tencent.upload.network.base.a aVar = (com.tencent.upload.network.base.a) dVar;
            com.tencent.upload.common.a.a.a(hVar.j(), "Session Connected. sid=" + hVar.o + " succeed=" + z + " errorCode=" + i + " ip=" + str + " The connection is invalid, close it!" + aVar.hashCode());
            aVar.b();
            return;
        }
        com.tencent.upload.common.a.a.b(hVar.j(), "Session Connected. sid=" + hVar.o + " succeed=" + z + " errorCode=" + i + " ip=" + str + " currState=" + hVar.h.toString());
        if (hVar.h != b.CONNECTING) {
            return;
        }
        b bVar;
        if (z) {
            hVar.i = str;
            hVar.c.a = hVar.i;
            HandshakeTask handshakeTask = new HandshakeTask(hVar.q, hVar.f, com.tencent.upload.common.a.a().b(), new r(hVar));
            hVar.a(b.HANDSHAKE);
            hVar.a = handshakeTask;
            if (!handshakeTask.onSend(hVar)) {
                com.tencent.upload.common.a.a.d(hVar.j(), "Send Handshake Failed! sid=" + hVar.o);
                bVar = (b) hVar.e.get();
                if (bVar != null) {
                    bVar.a(hVar, RetCode.HANDSHAKE_FAILED.getCode(), RetCode.HANDSHAKE_FAILED.getDesc());
                    return;
                }
                return;
            }
            return;
        }
        bVar = (b) hVar.e.get();
        if (bVar != null) {
            bVar.a(hVar, i, "连接失败");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.tencent.upload.network.b.h r12, com.tencent.upload.network.base.d r13, byte[] r14) {
        /*
        r9 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r5 = 1;
        r4 = 0;
        r6 = 0;
        if (r14 == 0) goto L_0x002c;
    L_0x0007:
        r0 = r12.g;
        r0 = r0.position();
        r1 = r14.length;
        r0 = r0 + r1;
        r1 = r12.g;
        r1 = r1.capacity();
        if (r1 >= r0) goto L_0x0027;
    L_0x0017:
        r0 = java.nio.ByteBuffer.allocate(r0);
        r1 = r12.g;
        r1.flip();
        r1 = r12.g;
        r0.put(r1);
        r12.g = r0;
    L_0x0027:
        r0 = r12.g;
        r0.put(r14);
    L_0x002c:
        r0 = r12.g;
        r0 = r0.position();
        if (r0 != 0) goto L_0x0097;
    L_0x0034:
        r0 = r4;
    L_0x0035:
        if (r0 == 0) goto L_0x0220;
    L_0x0037:
        r1 = r0.length;
        if (r1 != 0) goto L_0x012c;
    L_0x003a:
        r0 = r5;
    L_0x003b:
        if (r0 == 0) goto L_0x0096;
    L_0x003d:
        r0 = r12.j();
        r1 = new java.lang.StringBuilder;
        r2 = "recv divide buf exception. currState=";
        r1.<init>(r2);
        r2 = r12.h;
        r2 = r2.toString();
        r1 = r1.append(r2);
        r2 = " sid=";
        r1 = r1.append(r2);
        r2 = r12.o;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.tencent.upload.common.a.a.d(r0, r1);
        r0 = r12.h;
        r1 = com.tencent.upload.network.b.a.b.ESTABLISHED;
        if (r0 != r1) goto L_0x0215;
    L_0x006d:
        r0 = new byte[r9];
        r1 = r12.g;
        r1 = r1.array();
        r2 = r1.length;
        r2 = java.lang.Math.min(r2, r9);
        java.lang.System.arraycopy(r1, r6, r0, r6, r2);
        r0 = r12.e;
        r0 = r0.get();
        r0 = (com.tencent.upload.network.b.b) r0;
        if (r0 == 0) goto L_0x0096;
    L_0x0087:
        r1 = com.tencent.upload.Const.RetCode.SESSION_DIVIDE_PACKET_ERROR;
        r1 = r1.getCode();
        r2 = com.tencent.upload.Const.RetCode.SESSION_DIVIDE_PACKET_ERROR;
        r2 = r2.getDesc();
        r0.b(r12, r1, r2);
    L_0x0096:
        return;
    L_0x0097:
        r0 = r12.g;
        r0 = r0.position();
        r1 = 4;
        if (r0 >= r1) goto L_0x00bd;
    L_0x00a0:
        r0 = r12.j();
        r1 = new java.lang.StringBuilder;
        r2 = "doDivideReceivedBuffer: size < 4 sid=";
        r1.<init>(r2);
        r2 = r12.o;
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.tencent.upload.common.a.a.c(r0, r1);
        r0 = new byte[r6];
        goto L_0x0035;
    L_0x00bd:
        r0 = r12.g;
        r0 = r0.array();
        r1 = 3;
        r1 = r0[r1];
        r1 = r1 & 255;
        r2 = 2;
        r2 = r0[r2];
        r2 = r2 & 255;
        r2 = r2 << 8;
        r1 = r1 | r2;
        r2 = r0[r5];
        r2 = r2 & 255;
        r2 = r2 << 16;
        r1 = r1 | r2;
        r0 = r0[r6];
        r0 = r0 & 255;
        r0 = r0 << 24;
        r0 = r0 | r1;
        r1 = com.tencent.upload.common.j.g();
        if (r0 > r1) goto L_0x00e6;
    L_0x00e4:
        if (r0 > 0) goto L_0x010e;
    L_0x00e6:
        r1 = r12.j();
        r2 = new java.lang.StringBuilder;
        r3 = " doDivideReceivedBuffer size > max, size:";
        r2.<init>(r3);
        r0 = r2.append(r0);
        r2 = " sid=";
        r0 = r0.append(r2);
        r2 = r12.o;
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.tencent.upload.common.a.a.c(r1, r0);
        r0 = new byte[r6];
        goto L_0x0035;
    L_0x010e:
        r1 = r12.g;
        r1 = r1.position();
        if (r0 <= r1) goto L_0x0119;
    L_0x0116:
        r0 = r4;
        goto L_0x0035;
    L_0x0119:
        r0 = new byte[r0];
        r1 = r12.g;
        r1.flip();
        r1 = r12.g;
        r1.get(r0);
        r1 = r12.g;
        r1.compact();
        goto L_0x0035;
    L_0x012c:
        r7 = new com.tencent.upload.c.c;
        r7.<init>();
        r7.a(r0);
        r0 = r7.d();
        r1 = r12.n;
        r0 = r1.get(r0);
        r1 = r0;
        r1 = (com.tencent.upload.network.b.h.a) r1;
        if (r1 == 0) goto L_0x0210;
    L_0x0143:
        r0 = r1.a;
        r0 = r0.b();
        r7.a(r0);
        if (r7 == 0) goto L_0x0166;
    L_0x014e:
        r2 = r7.d();
        r3 = r12.n;
        r0 = r3.get(r2);
        r0 = (com.tencent.upload.network.b.h.a) r0;
        if (r0 == 0) goto L_0x0166;
    L_0x015c:
        r8 = r12.k;
        r0 = r0.b;
        r8.removeCallbacks(r0);
        r3.delete(r2);
    L_0x0166:
        r0 = r12.j();
        r2 = new java.lang.StringBuilder;
        r3 = "Session Recv Response. sid=";
        r2.<init>(r3);
        r3 = r12.o;
        r2 = r2.append(r3);
        r3 = " taskId=";
        r2 = r2.append(r3);
        r3 = r7.c();
        r2 = r2.append(r3);
        r3 = " reqId=";
        r2 = r2.append(r3);
        r3 = r7.d();
        r2 = r2.append(r3);
        r3 = " cmd=";
        r2 = r2.append(r3);
        r3 = r7.b();
        r2 = r2.append(r3);
        r3 = " timecost=";
        r8 = r2.append(r3);
        if (r1 == 0) goto L_0x0200;
    L_0x01ae:
        r2 = java.lang.System.currentTimeMillis();
        r10 = r1.c;
        r2 = r2 - r10;
    L_0x01b5:
        r2 = r8.append(r2);
        r3 = " recvBuf_Pos=";
        r2 = r2.append(r3);
        r3 = r12.g;
        r3 = r3.position();
        r2 = r2.append(r3);
        r3 = " currState=";
        r2 = r2.append(r3);
        r3 = r12.h;
        r3 = r3.toString();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.upload.common.a.a.b(r0, r2);
        if (r1 == 0) goto L_0x0203;
    L_0x01e4:
        r0 = r1.a;
    L_0x01e6:
        r1 = r12.h;
        r2 = com.tencent.upload.network.b.a.b.ESTABLISHED;
        if (r1 != r2) goto L_0x0205;
    L_0x01ec:
        if (r0 == 0) goto L_0x002c;
    L_0x01ee:
        r1 = r0.g();
        if (r1 == 0) goto L_0x002c;
    L_0x01f4:
        r1 = r0.g();
        r1.onResponse(r0, r7);
        r12.a(r5);
        goto L_0x002c;
    L_0x0200:
        r2 = -999; // 0xfffffffffffffc19 float:NaN double:NaN;
        goto L_0x01b5;
    L_0x0203:
        r0 = r4;
        goto L_0x01e6;
    L_0x0205:
        r0 = r12.h;
        r1 = com.tencent.upload.network.b.a.b.HANDSHAKE;
        if (r0 != r1) goto L_0x002c;
    L_0x020b:
        r12.a(r7);
        goto L_0x002c;
    L_0x0210:
        com.tencent.upload.impl.TaskCenter.handleResponse(r7);
        goto L_0x002c;
    L_0x0215:
        r0 = r12.h;
        r1 = com.tencent.upload.network.b.a.b.HANDSHAKE;
        if (r0 != r1) goto L_0x0096;
    L_0x021b:
        r12.a(r4);
        goto L_0x0096;
    L_0x0220:
        r0 = r6;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.network.b.h.a(com.tencent.upload.network.b.h, com.tencent.upload.network.base.d, byte[]):void");
    }

    private void a(d dVar, int i, String str) {
        if (dVar == this.b) {
            com.tencent.upload.common.a.a.c(j(), "Session Error. sid=" + this.o + " errorCode=" + i + " msg=" + str + " currState=" + this.h.toString());
            b bVar;
            if (this.h == b.ESTABLISHED) {
                bVar = (b) this.e.get();
                if (bVar != null) {
                    bVar.b(this, i, str);
                }
            } else if (this.h != b.HANDSHAKE) {
            } else {
                if (this.a != null) {
                    this.a.onError(i, str, i == RetCode.NETWORK_NOT_AVAILABLE.getCode());
                    this.a = null;
                    return;
                }
                bVar = (b) this.e.get();
                if (bVar != null) {
                    bVar.a(this, i, str);
                }
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            this.p = 0;
            return;
        }
        this.p++;
        if (this.p >= 3) {
            com.tencent.upload.common.a.a.d(j(), "request failed statistics " + this.p + ", session auto close. sid=" + this.o);
            b();
        }
    }

    static /* synthetic */ void b(h hVar, int i) {
        com.tencent.upload.c.a aVar = (com.tencent.upload.c.a) hVar.m.get(i);
        if (aVar == null) {
            com.tencent.upload.common.a.a.c(hVar.j(), "doSendEnd request== null. reqId=" + i + " sid=" + hVar.o);
            return;
        }
        com.tencent.upload.common.a.a.b(hVar.j(), "Send Request End. sid=" + hVar.o + " reqId=" + aVar.c());
        hVar.m.delete(i);
        if (hVar.a != null) {
            hVar.a.onRequestSended(aVar);
        } else if (aVar.g() != null) {
            aVar.g().onRequestSended(aVar);
        }
        a aVar2 = (a) hVar.n.get(i);
        if (!(aVar2 == null || aVar2.a == null || aVar2.a.f())) {
            if (aVar2.b != null) {
                hVar.k.removeCallbacks(aVar2.b);
            }
            aVar2.b = null;
        }
        hVar.m();
    }

    private void k() {
        for (int i = 0; i < this.n.size(); i++) {
            a aVar = (a) this.n.get(this.n.keyAt(i));
            if (aVar != null) {
                this.k.removeCallbacks(aVar.b);
                aVar.b = null;
            }
        }
        this.n.clear();
    }

    private void l() {
        int i = 0;
        com.tencent.upload.common.a.a.a(j(), "do Cleanup Session. sid=" + this.o);
        this.g.clear();
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            com.tencent.upload.c.a aVar = (com.tencent.upload.c.a) it.next();
            if (!(aVar == null || aVar.g() == null)) {
                aVar.g().onRequestError(aVar, RetCode.SESSION_DISCONNECT.getCode(), RetCode.SESSION_DISCONNECT.getDesc());
            }
        }
        this.l.clear();
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            int keyAt = this.m.keyAt(i2);
            a aVar2 = (a) this.n.get(keyAt);
            if (aVar2 == null || aVar2.a == null) {
                aVar = (com.tencent.upload.c.a) this.m.get(keyAt);
                if (!(aVar == null || aVar.g() == null)) {
                    aVar.g().onRequestError(aVar, RetCode.SESSION_DISCONNECT.getCode(), RetCode.SESSION_DISCONNECT.getDesc());
                }
            }
        }
        this.m.clear();
        while (i < this.n.size()) {
            aVar2 = (a) this.n.get(this.n.keyAt(i));
            aVar = aVar2 != null ? aVar2.a : null;
            if (!(aVar == null || aVar.g() == null)) {
                aVar.g().onRequestError(aVar, RetCode.SESSION_DISCONNECT.getCode(), RetCode.SESSION_DISCONNECT.getDesc());
            }
            i++;
        }
        k();
    }

    private void m() {
        com.tencent.upload.c.a aVar;
        if (!this.l.isEmpty()) {
            try {
                aVar = (com.tencent.upload.c.a) this.l.remove();
            } catch (Throwable e) {
                com.tencent.upload.log.b.c(j(), "get send request exception.", e);
                aVar = null;
            }
            if (aVar != null) {
                com.tencent.upload.common.a.a.b(j(), "Send Request Begin. sid=" + this.o + " " + aVar.toString() + " Sending_Count:" + this.m.size() + " Left_Count:" + this.l.size());
                this.m.put(aVar.c(), aVar);
                d dVar = this.b;
                if (dVar == null) {
                    com.tencent.upload.common.a.a.d(j(), "Session has no connection! actionId=" + aVar.b() + " reqId=" + aVar.c() + " sid=" + this.o);
                    a(dVar, RetCode.SESSION_WITHOUT_CONN.getCode(), RetCode.SESSION_WITHOUT_CONN.getDesc());
                    return;
                }
                try {
                    byte[] a = aVar.a();
                    if (a == null) {
                        com.tencent.upload.common.a.a.d(j(), "decode request failed. actionId=" + aVar.b() + " reqId=" + aVar.c() + " cmd=" + aVar.d() + " sid=" + this.o);
                        this.m.delete(aVar.c());
                        if (aVar.g() != null) {
                            aVar.g().onRequestError(aVar, RetCode.SESSION_REQUEST_ENCODE_ERROR.getCode(), RetCode.SESSION_REQUEST_ENCODE_ERROR.getDesc());
                            return;
                        }
                        return;
                    }
                    int i = j.i();
                    if (dVar.a(a, aVar.c(), i, i)) {
                        dVar.c();
                        return;
                    }
                    com.tencent.upload.common.a.a.d(j(), "Connection SendAsync failed. sid=" + this.o);
                    this.m.delete(aVar.c());
                    a(dVar, RetCode.SESSION_CONN_SEND_FAILED.getCode(), RetCode.SESSION_CONN_SEND_FAILED.getDesc());
                } catch (Throwable e2) {
                    if (aVar.g() != null) {
                        aVar.g().onRequestError(aVar, RetCode.IO_EXCEPTION.getCode(), RetCode.IO_EXCEPTION.getDesc());
                    }
                    com.tencent.upload.log.b.c(j(), "", e2);
                } catch (Throwable e22) {
                    if (aVar.g() != null) {
                        aVar.g().onRequestError(aVar, RetCode.OOM.getCode(), RetCode.OOM.getDesc());
                    }
                    com.tencent.upload.log.b.c(j(), "", e22);
                }
            }
        }
    }

    public final Looper a() {
        return this.k.getLooper();
    }

    public final void a(Looper looper) {
        this.k = new Handler(looper);
    }

    public final void a(d dVar) {
        if (dVar == this.b) {
            com.tencent.upload.common.a.a.a(j(), "Session onStart. sid=" + this.o);
        }
    }

    public final void a(d dVar, int i) {
        this.k.post(new m(this, i, dVar));
    }

    public final void a(d dVar, int i, int i2) {
        this.k.post(new n(this, dVar, i, i2));
    }

    public final void a(d dVar, boolean z, int i, String str) {
        this.k.post(new k(this, dVar, z, i, str));
    }

    public final void a(d dVar, byte[] bArr) {
        this.k.post(new o(this, dVar, bArr));
    }

    public final boolean a(com.tencent.upload.c.a aVar, com.tencent.upload.network.b.a.a aVar2) {
        if (this.h != b.ESTABLISHED && this.h != b.HANDSHAKE) {
            com.tencent.upload.common.a.a.d(j(), "Can't send request, state is illegel. CurrState=" + this.h.toString() + " sid=" + this.o);
            return false;
        } else if (aVar == null) {
            com.tencent.upload.common.a.a.d(j(), "Can't send request, request is illegel. sid=" + this.o);
            return false;
        } else {
            aVar.a(aVar2);
            this.l.add(aVar);
            this.k.post(new i(this));
            return true;
        }
    }

    public final boolean a(k kVar) {
        boolean z = false;
        int h = j.h();
        if (kVar == null) {
            com.tencent.upload.common.a.a.c(j(), "Can't open Session with a null route! sid=" + this.o);
        } else if (this.h == b.UNKONWN || this.h == b.NO_CONNECT) {
            if (h <= 0) {
                h = j.h();
            }
            com.tencent.upload.common.a.a.b(j(), "Begin Open Session. sid=" + this.o + " state=" + this.h.toString() + " route:" + kVar.toString() + " timeout=" + h);
            int f = kVar.f();
            if (this.b != null) {
                this.b.b();
                this.b = null;
            }
            if (this.c != null) {
                this.c = null;
            }
            if (f == 1) {
                this.b = new f(this, this.q);
            } else if (f == 2) {
                this.b = new com.tencent.upload.network.base.b(this, this.q);
            }
            if (this.b == null) {
                com.tencent.upload.common.a.a.c(j(), "Open Connection Failed! sid=" + this.o + " Protocol=" + f + " state:" + this.h.toString());
            } else if (this.b.a()) {
                z = this.b.a(kVar.b(), kVar.c(), kVar.d(), kVar.e(), h);
                if (z) {
                    this.c = kVar;
                    a(b.CONNECTING);
                }
            } else {
                com.tencent.upload.common.a.a.c(j(), "Connection Start Failed! sid=" + this.o + " Protocol=" + f + " state:" + this.h.toString());
            }
        } else {
            com.tencent.upload.common.a.a.c(j(), "Can't open Session because state is illegel! sid=" + this.o + " state=" + this.h.toString());
        }
        return z;
    }

    public final void b() {
        if (this.b != null) {
            com.tencent.upload.common.a.a.c(j(), "Close Session. sid=" + this.o);
            if (this.b != null) {
                this.b.b();
                this.b = null;
            }
            b bVar = (b) this.e.get();
            if (bVar != null) {
                bVar.b(this);
            }
            a(b.NO_CONNECT);
        }
    }

    public final void b(d dVar) {
        this.k.post(new l(this, dVar));
    }

    public final void b(d dVar, int i) {
        this.k.post(new q(this, i));
    }

    public final k c() {
        return this.c;
    }

    public final void c(d dVar, int i) {
        this.k.post(new p(this, i));
    }

    public final k d() {
        return this.d;
    }

    public final String e() {
        return this.i;
    }

    public final String f() {
        return this.j;
    }

    public final b g() {
        return this.h;
    }

    public final boolean h() {
        return this.c != null && this.c.a() == 2;
    }

    public final boolean i() {
        return this.l.size() == 0 && this.m.size() == 0;
    }

    public final String j() {
        return "UploadSession_" + this.q;
    }
}
