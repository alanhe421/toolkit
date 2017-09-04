package com.qq.reader.module.bookchapter.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.QueryBookIntroTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.f;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: LocalChapterHandle */
public class a {
    private static Set<String> e = Collections.synchronizedSet(new HashSet());
    private static Map<String, Handler> f = Collections.synchronizedMap(new HashMap());
    private OnlineTag a = null;
    private f b = null;
    private Handler c = null;
    private Context d = null;

    public a(Context context, OnlineTag onlineTag) {
        this.d = context;
        this.a = onlineTag;
        this.b = new f(this.a);
    }

    public int a() {
        if (this.a == null) {
            return -1;
        }
        f.put(this.a.k(), this.c);
        this.c = null;
        if (-1 == -1 && e.add(this.a.k())) {
            ReaderTask a = a(this.a.k(), 0, 0, -1);
            a.setTid(-100);
            g.a().a(a);
        }
        return -2;
    }

    public void a(final c cVar) {
        if (this.a != null) {
            this.c = null;
            g.a().a(new QueryBookIntroTask(new c(this) {
                final /* synthetic */ a b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    Handler a = this.b.c;
                    try {
                        if (this.b.b.c(str) >= 0) {
                            cVar.onConnectionRecieveData(readerProtocolTask, str, j);
                        } else if (a != null) {
                            a.sendEmptyMessage(21013);
                        }
                    } catch (Exception e) {
                        if (a != null) {
                            a.sendEmptyMessage(21013);
                        }
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if (this.b.c != null) {
                        this.b.c.sendEmptyMessage(21013);
                    }
                    cVar.onConnectionError(readerProtocolTask, exception);
                }
            }, this.a.k(), 0, 0, -1));
        }
    }

    private ReaderProtocolTask a(String str, long j, long j2, long j3) {
        return new QueryBookIntroTask(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Handler handler = (Handler) a.f.remove(this.a.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21013);
                }
                a.e.remove(this.a.a.k());
            }
        }, str, j, j2, j3);
    }

    public void a(Handler handler) {
        this.c = handler;
    }

    public void b() {
        this.c = null;
        f.remove(this.a.k());
    }

    private void a(ReaderProtocolTask readerProtocolTask, String str) {
        Handler handler;
        try {
            if (this.b.c(str) < 0) {
                handler = (Handler) f.remove(this.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21013);
                }
            } else {
                handler = (Handler) f.remove(this.a.k());
                if (handler != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 21012;
                    obtain.obj = this.b;
                    handler.sendMessage(obtain);
                }
            }
        } catch (Exception e) {
            Exception exception = e;
            handler = (Handler) f.remove(this.a.k());
            if (handler != null) {
                handler.sendEmptyMessage(21013);
            }
            exception.printStackTrace();
        }
        e.remove(this.a.k());
    }

    public f c() {
        return this.b;
    }
}
