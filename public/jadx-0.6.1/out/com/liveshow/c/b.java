package com.liveshow.c;

import android.os.Handler;
import com.google.gson.GsonBuilder;
import com.liveshow.a.b.a;
import com.liveshow.model.task.HostEndPageTask;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

/* compiled from: HostLiveEndPagePresenter */
public class b implements a {
    private com.liveshow.a.b.b a;
    private Handler b = new Handler();
    private com.liveshow.model.b c;

    public b(com.liveshow.a.b.b bVar) {
        this.a = bVar;
    }

    public void a(com.liveshow.a.b.b bVar) {
        this.a = bVar;
    }

    public void a(int i) {
        b(i);
    }

    private void b(int i) {
        g.a().a(new HostEndPageTask(new c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.c = (com.liveshow.model.b) new GsonBuilder().create().fromJson(str, com.liveshow.model.b.class);
                this.a.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.a != null) {
                            this.a.a.a.a(this.a.a.c);
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

    public void b() {
        if (this.a != null && this.c != null) {
            o.c(this.a.a(), this.c.b, this.c.c, this.c.a, null);
        }
    }

    public void a() {
        this.a = null;
    }
}
