package com.qq.reader.liveshow.c;

import android.os.Message;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.liveshow.c.a.c.a;
import com.qq.reader.liveshow.model.filter.a.b;
import com.qq.reader.liveshow.model.filter.queue.impl.MessageBlockingQueue;
import com.qq.reader.liveshow.model.im.message.a.c;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LiveGiftHelper */
public class f extends i {
    private static String b = "LiveGiftHelper";
    private a c;
    private com.qq.reader.liveshow.model.filter.a d;
    private com.qq.reader.liveshow.model.filter.a e;

    public f(a aVar) {
        this.c = aVar;
        c();
        b();
    }

    private void b() {
        this.e = new com.qq.reader.liveshow.model.filter.a("BigGiftPool").a(new MessageBlockingQueue()).a(new com.qq.reader.liveshow.model.filter.a.a.a(0)).a(this.c.c());
        this.e.a(new com.qq.reader.liveshow.model.filter.a.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                final c cVar = (c) bVar;
                this.a.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (this.b.a.c != null) {
                            this.b.a.c.b(cVar);
                        }
                    }
                });
            }
        });
    }

    private void c() {
        this.d = new com.qq.reader.liveshow.model.filter.a("MultiGiftPool").a(new MessageBlockingQueue()).a(new com.qq.reader.liveshow.model.filter.a.a.a(200)).a(this.c.b());
        this.d.a(new com.qq.reader.liveshow.model.filter.a.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                final c cVar = (c) bVar;
                this.a.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        if (this.b.a.c != null) {
                            this.b.a.c.a(cVar);
                        }
                    }
                });
            }
        });
    }

    protected boolean a(Message message) {
        int optInt;
        JSONException e;
        b cVar;
        if (message.what == 1002) {
            GiftItem f;
            com.qq.reader.liveshow.model.im.a.a.a aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
            int i = 0;
            try {
                JSONObject jSONObject = new JSONObject(aVar.b());
                optInt = jSONObject.optInt("giftid", -1);
                try {
                    i = jSONObject.optInt("num");
                } catch (JSONException e2) {
                    e = e2;
                    SxbLog.e(b, e.getMessage());
                    f = com.qq.reader.liveshow.model.a.f(optInt);
                    if (f != null) {
                        cVar = new c(aVar.c());
                        cVar.a(optInt);
                        cVar.b(i);
                        if (f.mType == 2) {
                            if (message.arg1 == 1) {
                                this.e.c(cVar);
                            } else {
                                this.e.b(cVar);
                            }
                        } else if (f.mType == 1) {
                            if (message.arg1 == 1) {
                                this.d.c(cVar);
                            } else {
                                this.d.b(cVar);
                            }
                        }
                    }
                    return true;
                }
            } catch (JSONException e3) {
                JSONException jSONException = e3;
                optInt = -1;
                e = jSONException;
                SxbLog.e(b, e.getMessage());
                f = com.qq.reader.liveshow.model.a.f(optInt);
                if (f != null) {
                    cVar = new c(aVar.c());
                    cVar.a(optInt);
                    cVar.b(i);
                    if (f.mType == 2) {
                        if (f.mType == 1) {
                            if (message.arg1 == 1) {
                                this.d.b(cVar);
                            } else {
                                this.d.c(cVar);
                            }
                        }
                    } else if (message.arg1 == 1) {
                        this.e.b(cVar);
                    } else {
                        this.e.c(cVar);
                    }
                }
                return true;
            }
            f = com.qq.reader.liveshow.model.a.f(optInt);
            if (f != null) {
                cVar = new c(aVar.c());
                cVar.a(optInt);
                cVar.b(i);
                if (f.mType == 2) {
                    if (message.arg1 == 1) {
                        this.e.c(cVar);
                    } else {
                        this.e.b(cVar);
                    }
                } else if (f.mType == 1) {
                    if (message.arg1 == 1) {
                        this.d.c(cVar);
                    } else {
                        this.d.b(cVar);
                    }
                }
            }
        } else if (message.what == APPluginErrorCode.ERROR_APP_SYSTEM) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        this.a.c.a();
                    }
                }
            });
        }
        return true;
    }

    public void a() {
        this.d.a();
        this.e.a();
        this.c = null;
    }

    public void a(b bVar) {
        this.d.a(bVar);
    }

    public void b(b bVar) {
        this.e.a(bVar);
    }
}
