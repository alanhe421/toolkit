package com.qq.reader.module.bookchapter.online;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.QueryBookIntroTask;
import com.qq.reader.common.readertask.protocol.QueryMediaBookIndexTask;
import com.qq.reader.common.readertask.protocol.QueryMediaBookInfoTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveException;
import org.json.JSONObject;

/* compiled from: OnlineChapterHandle */
public class g {
    private static Set<String> e = Collections.synchronizedSet(new HashSet());
    private static Map<String, Handler> f = Collections.synchronizedMap(new HashMap());
    private OnlineTag a = null;
    private f b = null;
    private Handler c = null;
    private Context d = null;
    private e g;
    private boolean h = false;

    /* compiled from: OnlineChapterHandle */
    public class a extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ g a;

        public a(g gVar) {
            this.a = gVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected Void a(Object... objArr) {
            Boolean bool = (Boolean) objArr[0];
            if (this.a.a != null) {
                this.a.b.b();
                long i = this.a.b.i();
                int size = this.a.b.e() != null ? this.a.b.e().size() : 0;
                Handler handler = (Handler) g.f.get(this.a.a.k());
                if (!(i == -1 || handler == null)) {
                    Message obtain = Message.obtain();
                    obtain.what = 21000;
                    obtain.obj = this.a.b;
                    if (size < this.a.a.n()) {
                        obtain.arg1 = 1;
                    }
                    handler.sendMessage(obtain);
                }
                if (this.a.a(i, size, this.a.a)) {
                    bool = Boolean.valueOf(true);
                }
                if (bool.booleanValue()) {
                    ReaderTask a;
                    if (this.a.a.F() == 2) {
                        a = this.a.a(this.a.a.k());
                    } else {
                        a = this.a.a(this.a.a.k(), this.a.b.m(), (long) size, this.a.b.i());
                    }
                    com.qq.reader.common.readertask.g.a().a(a);
                } else {
                    g.f.remove(this.a.a.k());
                    g.e.remove(this.a.a.k());
                }
            }
            return null;
        }
    }

    public g(Context context, OnlineTag onlineTag) {
        this.d = context;
        this.a = onlineTag;
        this.b = new f(this.a);
        this.g = new e(context, onlineTag);
    }

    public void a() throws IOException, ArchiveException {
        this.g.a();
    }

    public int b() {
        if (this.a == null || this.b == null) {
            return -1;
        }
        this.b.a();
        if (this.b.i() != -1) {
            return this.b.j();
        }
        return -2;
    }

    public int c() {
        int i = -1;
        if (this.a != null) {
            this.b.a();
            long i2 = this.b.i();
            if (i2 != -1) {
                i = this.b.j();
            } else {
                f.put(this.a.k(), this.c);
                i = -2;
            }
            this.c = null;
            if (i2 == -1 && e.add(this.a.k())) {
                ReaderTask a = a(this.a.k(), 0, 0, -1);
                a.setTid(-100);
                com.qq.reader.common.readertask.g.a().a(a);
            }
        }
        return i;
    }

    public void a(final c cVar) {
        if (this.a != null) {
            com.qq.reader.common.readertask.g.a().a(new QueryBookIntroTask(new c(this) {
                final /* synthetic */ g b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    Handler a = this.b.c;
                    try {
                        int c = this.b.b.c(str);
                        if (c < 0) {
                            cVar.onConnectionError(readerProtocolTask, new Exception("code:" + c));
                        } else {
                            cVar.onConnectionRecieveData(readerProtocolTask, str, j);
                        }
                    } catch (Exception e) {
                        if (a != null) {
                            a.sendEmptyMessage(21013);
                        }
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    cVar.onConnectionError(readerProtocolTask, exception);
                }
            }, this.a.k(), 0, 0, -1));
        }
    }

    private ReaderProtocolTask a(String str) {
        return new QueryMediaBookInfoTask(new c(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.e("Error", exception.getMessage());
                Handler handler = (Handler) g.f.remove(this.a.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21001);
                }
                g.e.remove(this.a.a.k());
            }
        }, str);
    }

    private ReaderProtocolTask a(String str, long j, long j2, long j3) {
        return new QueryBookIntroTask(new c(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.b(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Handler handler = (Handler) g.f.remove(this.a.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21001);
                }
                g.e.remove(this.a.a.k());
            }
        }, str, j, j2, j3);
    }

    public void a(boolean z) {
        if (this.a != null) {
            f.put(this.a.k(), this.c);
            this.c = null;
            new a(this).execute(new Object[]{Boolean.valueOf(z)});
        }
    }

    public void b(boolean z) {
        if (this.a != null) {
            this.h = true;
            f.put(this.a.k(), this.c);
            this.c = null;
            new a(this).execute(new Object[]{Boolean.valueOf(z)});
        }
    }

    public void a(Handler handler) {
        this.c = handler;
    }

    public void d() {
        this.c = null;
        f.remove(this.a.k());
    }

    private void a(ReaderProtocolTask readerProtocolTask, String str) {
        Handler handler;
        try {
            int c = this.b.c(str);
            String optString = new JSONObject(str).optString("id");
            if (c < 0) {
                handler = (Handler) f.remove(this.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21001);
                }
            }
            if (c == 0) {
                com.qq.reader.common.readertask.g.a().a(new QueryMediaBookIndexTask(new c(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        Handler handler;
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.optInt("result", -1) == 0) {
                                ao.j(this.a.a.d(), str);
                                if (this.a.b != null) {
                                    this.a.b.c();
                                    if (this.a.b.y() != null) {
                                        this.a.b.y().r(jSONObject.optInt("isInFree", 0));
                                    }
                                }
                                handler = (Handler) g.f.remove(this.a.a.k());
                                if (handler != null) {
                                    Message obtain = Message.obtain();
                                    obtain.what = 21000;
                                    obtain.obj = this.a.b;
                                    handler.sendMessage(obtain);
                                }
                            }
                        } catch (Exception e) {
                            Exception exception = e;
                            exception.printStackTrace();
                            handler = (Handler) g.f.remove(this.a.a.k());
                            if (handler != null) {
                                handler.sendEmptyMessage(21001);
                            }
                            exception.printStackTrace();
                        } catch (Throwable th) {
                            th.printStackTrace();
                            handler = (Handler) g.f.remove(this.a.a.k());
                            if (handler != null) {
                                handler.sendEmptyMessage(21001);
                            }
                        }
                        g.e.remove(this.a.a.k());
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        Handler handler = (Handler) g.f.remove(this.a.a.k());
                        if (handler != null) {
                            handler.sendEmptyMessage(21001);
                        }
                        com.qq.reader.common.monitor.debug.c.e("Error", exception.getMessage());
                    }
                }, optString));
            }
        } catch (Exception e) {
            Exception exception = e;
            handler = (Handler) f.remove(this.a.k());
            if (handler != null) {
                handler.sendEmptyMessage(21001);
            }
            com.qq.reader.common.monitor.debug.c.e("Error", exception.getMessage());
        }
    }

    private void b(ReaderProtocolTask readerProtocolTask, String str) {
        Handler handler;
        Object obj = null;
        try {
            int c = this.b.c(str);
            if (this.a.F() == 2) {
                c = 0;
            }
            if (c < 0) {
                handler = (Handler) f.remove(this.a.k());
                if (handler != null) {
                    handler.sendEmptyMessage(21001);
                }
            } else {
                int i;
                if (this.h) {
                    i = 1;
                } else {
                    i = c;
                }
                List arrayList = new ArrayList();
                if (i == 0) {
                    if (this.a.F() == 1) {
                        Collection e = this.b.e();
                        if (e != null) {
                            arrayList.addAll(e);
                        }
                        if (arrayList.size() > 0) {
                            obj = 1;
                        }
                    }
                    this.g.a();
                }
                Object obj2 = obj;
                if (i != 1) {
                    this.b.d();
                }
                handler = (Handler) f.remove(this.a.k());
                if (handler != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 21000;
                    obtain.obj = this.b;
                    if (readerProtocolTask.getTid() == -100) {
                        obtain.arg1 = 3;
                    }
                    if (i != 1) {
                        obtain.arg2 = 2;
                    }
                    handler.sendMessage(obtain);
                }
                if (obj2 != null) {
                    List arrayList2 = new ArrayList();
                    Collection e2 = this.b.e();
                    if (e2 != null) {
                        arrayList2.addAll(e2);
                    }
                    a(arrayList, arrayList2, handler);
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            handler = (Handler) f.remove(this.a.k());
            if (handler != null) {
                handler.sendEmptyMessage(21001);
            }
            th2.printStackTrace();
        }
        e.remove(this.a.k());
    }

    private boolean a(long j, int i, OnlineTag onlineTag) {
        return j == -1 || i < onlineTag.n() || i < onlineTag.g();
    }

    public f e() {
        return this.b;
    }

    private void a(List<OnlineChapter> list, List<OnlineChapter> list2, Handler handler) {
        com.qq.reader.common.readertask.g.a().a(new OnlineChapterHandle$5(this, list, list2, handler));
    }
}
