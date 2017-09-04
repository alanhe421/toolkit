package com.liveshow.c;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import com.liveshow.a.a.b;
import com.liveshow.model.e;
import com.liveshow.model.task.AuthorDetailTask;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.FocusAuthorTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

/* compiled from: AuthorDetailPresenter */
public class a implements com.liveshow.a.a.a {
    private b a;
    private Handler b = new Handler();
    private ReaderTask c;
    private ReaderTask d;

    public a(b bVar) {
        this.a = bVar;
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(int i, long j, String str) {
        b(i, j, str);
    }

    private void b(int i, long j, String str) {
        this.c = new AuthorDetailTask(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                final com.liveshow.model.a aVar = (com.liveshow.model.a) new GsonBuilder().create().fromJson(str, com.liveshow.model.a.class);
                if (!(aVar == null || aVar.j == null)) {
                    for (e eVar : aVar.j) {
                        eVar.e = i.c().b(new StringBuilder().append(eVar.a).append("").toString(), true) != null;
                    }
                }
                this.a.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (this.b.a.a != null) {
                            this.b.a.a.a(aVar);
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
                        if (this.a.a.a != null) {
                            this.a.a.a.a();
                        }
                    }
                });
            }
        }, i, j, str);
        g.a().a(this.c);
    }

    private void d() {
        com.qq.reader.common.login.c.a(this.a.c(), 7);
        this.a.c().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void a(long j) {
        if (this.a != null && !ao.d(this.a.c())) {
            Toast.makeText(this.a.c(), R.string.network_unavailable, 0).show();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            this.d = new FocusAuthorTask(new c(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        final int optInt = new JSONObject(str).optInt("code");
                        this.a.b.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void run() {
                                if (optInt == 0) {
                                    if (this.b.a.a != null) {
                                        this.b.a.a.a(true);
                                        this.b.a.a.b();
                                    }
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.followed, 0).a();
                                } else if (optInt == -2) {
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.followed, 0).a();
                                } else {
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.author_edit_fenda_server_error, 0).a();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(ReaderApplication.getApplicationImp(), (int) R.string.author_edit_fenda_server_error, 0).a();
                        }
                    });
                }
            }, j + "");
            g.a().a(this.d);
        } else {
            d();
        }
    }

    public void b(long j) {
        if (this.a != null && !ao.d(this.a.c())) {
            af.a(this.a.c(), (int) R.string.network_unavailable, 0).a();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            new JSAddToBookShelf(ReaderApplication.getApplicationImp().getApplicationContext()).addById(String.valueOf(j));
            if (this.a != null) {
                this.a.a(j, true);
            }
        } else {
            d();
        }
    }

    public void a() {
        this.a = null;
    }

    public void b() {
        g.a().b(this.c);
        g.a().b(this.d);
    }

    public void c() {
    }

    public void a(Context context, String str) {
        if (this.a != null && !ao.d(this.a.c())) {
            af.a(this.a.c(), (int) R.string.network_unavailable, 0).a();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            com.qq.reader.liveshow.utils.i.b(context, str, null);
        } else {
            d();
        }
    }

    public void a(Context context, String str, int i) {
        if (this.a == null || ao.d(this.a.c())) {
            com.qq.reader.liveshow.utils.i.a(context, str, i, new com.qq.reader.liveshow.utils.i.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (this.a.a != null) {
                        this.a.a.b(z);
                    }
                }

                public void a() {
                }
            });
        } else {
            af.a(this.a.c(), (int) R.string.network_unavailable, 0).a();
        }
    }

    public void b(Context context, String str) {
        if (this.a == null || ao.d(this.a.c())) {
            com.qq.reader.liveshow.utils.i.a(context, str, null);
        } else {
            af.a(this.a.c(), (int) R.string.network_unavailable, 0).a();
        }
    }
}
