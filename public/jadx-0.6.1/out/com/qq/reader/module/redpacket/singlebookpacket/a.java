package com.qq.reader.module.redpacket.singlebookpacket;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.RedPacketSingleBookTask;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.singlebookpacket.card.RedPacketSingleBookCard;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: RedPacketSingleBookDataLoader */
public class a {
    private WeakReference<Handler> a;
    private final List<RedPacket> b = new LinkedList();
    private long c = -1;
    private boolean d;

    public a(Handler handler) {
        this.a = new WeakReference(handler);
    }

    public void a(List<RedPacket> list, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        if (list != null && list.size() != 0) {
            g.a().a(new RedPacketSingleBookDataLoader$1(this, list, aVar));
        }
    }

    public int a() {
        return this.b.size();
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        if (this.b.size() != 0) {
            g.a().a(new RedPacketSingleBookDataLoader$2(this, aVar));
        }
    }

    private void b(List<RedPacket> list, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        synchronized (this.b) {
            this.b.addAll(list);
        }
    }

    public boolean b() {
        return this.d;
    }

    public void a(c cVar) {
        if (cVar != null) {
            b(this.a, cVar);
        }
    }

    private void a(WeakReference<Handler> weakReference, c cVar) {
        if (cVar.f() == 1) {
            b(cVar);
            this.d = false;
        }
        if ((cVar.f() == 1 || cVar.f() == 3 || cVar.f() == 2) && cVar.h().d().size() > 0) {
            RedPacketSingleBookCard redPacketSingleBookCard = (RedPacketSingleBookCard) cVar.h().d().get(0);
            if (this.c < redPacketSingleBookCard.getItem().d()) {
                this.c = redPacketSingleBookCard.getItem().d();
            }
        }
        c(weakReference, cVar);
    }

    private void b(c cVar) {
        synchronized (this.b) {
            if (this.b.size() == 0) {
                return;
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                if (((RedPacket) it.next()).d() <= this.c) {
                    it.remove();
                }
            }
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : cVar.h().d()) {
                RedPacketSingleBookCard redPacketSingleBookCard = (RedPacketSingleBookCard) aVar;
                Iterator it2 = this.b.iterator();
                while (it2.hasNext()) {
                    if (((RedPacket) it2.next()).d() == redPacketSingleBookCard.getItem().d()) {
                        it2.remove();
                    }
                }
            }
            if (this.b.size() > 0) {
                for (RedPacket a : this.b) {
                    cVar.h().d().add(0, d.a(a, cVar.h().c()));
                }
            }
            this.b.clear();
        }
    }

    private void b(final WeakReference<Handler> weakReference, final c cVar) {
        if (cVar.f() == 1) {
            this.d = true;
        }
        g.a().a(new RedPacketSingleBookTask(cVar.c(), cVar.f(), cVar.d(), cVar.e(), cVar.a(), new c(this) {
            final /* synthetic */ a c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                d h = cVar.h();
                if (h != null) {
                    h.a(str);
                    if (weakReference.get() != null) {
                        ((Handler) weakReference.get()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.c.a(weakReference, cVar);
                            }
                        });
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.c.a(weakReference, cVar, -1);
            }
        }));
    }

    private synchronized void c(WeakReference<Handler> weakReference, c cVar) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000001;
            obtain.obj = cVar;
            obtain.arg1 = cVar.g();
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference, c cVar, int i) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000002;
            obtain.obj = cVar;
            obtain.arg1 = i;
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }
}
