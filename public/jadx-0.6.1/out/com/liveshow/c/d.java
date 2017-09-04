package com.liveshow.c;

import android.os.Handler;
import com.google.gson.GsonBuilder;
import com.liveshow.a.d.a;
import com.liveshow.a.d.b;
import com.liveshow.model.e;
import com.liveshow.model.task.MemberEndPageTask;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.FocusAuthorTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

/* compiled from: MemberLiveEndPagePresenter */
public class d implements a {
    private b a;
    private Handler b = new Handler();
    private com.liveshow.model.d c;

    public d(b bVar) {
        this.a = bVar;
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(int i) {
        b(i);
    }

    private void b(int i) {
        g.a().a(new MemberEndPageTask(new c(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                final com.liveshow.model.d dVar = (com.liveshow.model.d) new GsonBuilder().create().fromJson(str, com.liveshow.model.d.class);
                this.a.c = dVar;
                if (!(dVar == null || dVar.h == null)) {
                    for (e eVar : dVar.h) {
                        eVar.e = i.c().b(new StringBuilder().append("").append(eVar.a).toString(), true) != null;
                    }
                }
                this.a.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (this.b.a.a != null) {
                            this.b.a.a.a(dVar);
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
                        af.a(ReaderApplication.getApplicationImp(), (int) R.string.live_show_request_end_page_info_failed, 0).a();
                    }
                });
            }
        }, i));
    }

    private void d() {
        com.qq.reader.common.login.c.a(this.a.a(), 7);
        this.a.a().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void a(long j) {
        if (this.a != null && !ao.d(this.a.a())) {
            af.a(this.a.a(), (int) R.string.network_unavailable, 0).a();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            new JSAddToBookShelf(ReaderApplication.getApplicationImp().getApplicationContext()).addById(String.valueOf(j));
            if (this.a != null) {
                this.a.a(true, j);
            }
        } else {
            d();
        }
    }

    public void a() {
        if (this.a != null && !ao.d(this.a.a())) {
            af.a(this.a.a(), (int) R.string.network_unavailable, 0).a();
        } else if (com.qq.reader.common.login.c.b() || this.a == null) {
            g.a().a(new FocusAuthorTask(new c(this) {
                final /* synthetic */ d a;

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
            }, this.c.c));
        } else {
            d();
        }
    }

    public void b() {
        this.a = null;
    }

    public void c() {
        if (this.a != null && this.c != null) {
            o.c(this.a.a(), this.c.c, this.c.b, this.c.a, null);
        }
    }

    public void b(long j) {
        if (this.a != null) {
            o.a(this.a.a(), j + "", null, null, null);
        }
    }
}
