package com.liveshow.c;

import android.content.Context;
import android.os.Handler;
import com.google.gson.GsonBuilder;
import com.liveshow.a.c.a;
import com.liveshow.model.task.MemberDetailTask;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.liveshow.utils.i;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

/* compiled from: MemberDetailPresenter */
public class c {
    private a a;
    private Handler b = new Handler();
    private MemberDetailTask c;

    public c(a aVar) {
        this.a = aVar;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(String str, int i) {
        a(i, str);
    }

    private void a(int i, String str) {
        this.c = new MemberDetailTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                final com.liveshow.model.c cVar = (com.liveshow.model.c) new GsonBuilder().create().fromJson(str, com.liveshow.model.c.class);
                this.a.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (this.b.a.a != null) {
                            this.b.a.a.a(cVar);
                        }
                    }
                });
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        af.a(ReaderApplication.getApplicationImp(), (int) R.string.live_show_request_user_info_failed, 0).a();
                        this.a.a.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.a.a != null) {
                                    this.a.a.a.a.a();
                                }
                            }
                        });
                    }
                });
            }
        }, i, str);
        g.a().a(this.c);
    }

    private void d() {
        com.qq.reader.common.login.c.a(this.a.b(), 7);
        this.a.b().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void a() {
        this.a = null;
    }

    public void b() {
        g.a().b(this.c);
    }

    public void a(Context context, String str) {
        if (this.a != null && !ao.d(this.a.b())) {
            af.a(this.a.b(), (int) R.string.network_unavailable, 0).a();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            i.b(context, str, null);
        } else {
            d();
        }
    }

    public void a(Context context, String str, int i) {
        if (this.a == null || ao.d(this.a.b())) {
            i.a(context, str, i, new i.a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (this.a.a != null) {
                        this.a.a.a(z);
                    }
                }

                public void a() {
                }
            });
        } else {
            af.a(this.a.b(), (int) R.string.network_unavailable, 0).a();
        }
    }

    public void b(Context context, String str) {
        if (this.a == null || ao.d(this.a.b())) {
            i.a(context, str, null);
        } else {
            af.a(this.a.b(), (int) R.string.network_unavailable, 0).a();
        }
    }

    public void c() {
    }
}
