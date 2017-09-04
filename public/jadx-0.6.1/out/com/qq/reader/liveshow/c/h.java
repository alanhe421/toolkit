package com.qq.reader.liveshow.c;

import android.os.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.liveshow.c.a.d.a;
import com.qq.reader.liveshow.model.RankItem;
import com.qq.reader.liveshow.utils.SxbLog;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LiveHostHelper */
public class h extends i {
    private static final String b = h.class.getSimpleName();
    private a c;
    private Gson d;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.c != null) {
                this.a.c.b();
            }
        }
    };
    private long f;
    private long g;

    public h(a aVar) {
        this.c = aVar;
        this.d = new Gson();
    }

    protected boolean a(Message message) {
        com.qq.reader.liveshow.model.im.a.a.a aVar;
        switch (message.what) {
            case -3:
                this.a.post(new Runnable(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.c != null) {
                            this.a.c.a(true);
                        }
                    }
                });
                break;
            case -2:
                this.a.post(new Runnable(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.c != null) {
                            this.a.c.a(false);
                        }
                    }
                });
                break;
            case -1:
                if (this.c != null && this.c.e()) {
                    a(1);
                    break;
                }
            case 1003:
                aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
                switch (aVar.a()) {
                    case 1012:
                        int intValue;
                        try {
                            intValue = Integer.valueOf(aVar.b()).intValue();
                        } catch (Exception e) {
                            SxbLog.e(b, e.getMessage());
                            intValue = 1;
                        }
                        a(intValue);
                        break;
                    default:
                        break;
                }
            case 1004:
                aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
                switch (aVar.a()) {
                    case 2001:
                        String b = aVar.b();
                        SxbLog.b(b, b);
                        try {
                            JsonObject jsonObject = (JsonObject) new JsonParser().parse(b);
                            com.qq.reader.liveshow.model.a.b((List) this.d.fromJson(jsonObject.getAsJsonArray("rank"), new TypeToken<ArrayList<RankItem>>(this) {
                                final /* synthetic */ h a;

                                {
                                    this.a = r1;
                                }
                            }.getType()));
                            this.a.post(new Runnable(this) {
                                final /* synthetic */ h a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    if (this.a.c != null) {
                                        this.a.c.d();
                                    }
                                }
                            });
                            com.qq.reader.liveshow.model.a.b(jsonObject.get("audienceCount").getAsInt());
                            b();
                            break;
                        } catch (Exception e2) {
                            SxbLog.e(b, e2.getMessage());
                            break;
                        }
                    default:
                        break;
                }
            case APPluginErrorCode.ERROR_APP_SYSTEM /*2000*/:
                this.a.post(new Runnable(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.c != null) {
                            this.a.c.a();
                            this.a.c.c();
                            this.a.c.b();
                            this.a.c.d();
                        }
                    }
                });
                break;
        }
        return false;
    }

    private void a(int i) {
        com.qq.reader.liveshow.model.a.d(i);
        if (System.currentTimeMillis() - this.f > 100) {
            this.f = System.currentTimeMillis();
            this.a.post(this.e);
        }
    }

    private void b() {
        if (System.currentTimeMillis() - this.g > 100) {
            this.g = System.currentTimeMillis();
            this.a.post(new Runnable(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        this.a.c.c();
                    }
                }
            });
        }
    }

    public void a() {
        this.c = null;
    }
}
