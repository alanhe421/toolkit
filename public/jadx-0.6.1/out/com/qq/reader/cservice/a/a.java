package com.qq.reader.cservice.a;

import android.os.Handler;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.ParaiseTask;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AgreePublisher */
public abstract class a {
    public boolean a = false;
    private Handler b = null;
    private long c = -1;
    private String d = null;
    private int e = -1;
    private ParaiseTask f = null;

    public abstract void a(String str);

    public abstract void b();

    public a(Handler handler, long j, String str, int i) {
        this.b = handler;
        this.c = j;
        this.d = str;
        this.e = i;
        this.a = false;
    }

    private ParaiseTask c() {
        if (this.f == null) {
            this.f = new ParaiseTask(new c(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        this.a.a = false;
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            int optInt = jSONObject.optInt("code");
                            final String optString = jSONObject.optString("message");
                            if (optInt == 0) {
                                this.a.b.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.b();
                                    }
                                });
                            } else {
                                this.a.b.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 b;

                                    public void run() {
                                        this.b.a.a(optString);
                                    }
                                });
                            }
                        }
                    } catch (JSONException e) {
                        this.a.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a("网络异常，请稍后重试");
                            }
                        });
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.a = false;
                    this.a.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("网络异常，请稍后重试");
                        }
                    });
                }
            }, this.c, this.d, this.e);
        }
        return this.f;
    }

    public void a() {
        g.a().a(c());
        this.a = true;
    }
}
