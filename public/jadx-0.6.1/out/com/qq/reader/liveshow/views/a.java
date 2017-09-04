package com.qq.reader.liveshow.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.c;
import com.qq.reader.liveshow.b.l;
import com.qq.reader.liveshow.c.d;
import com.qq.reader.liveshow.c.i;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.im.message.SenderProfile;
import com.qq.reader.liveshow.model.im.viewdata.b;
import com.qq.reader.liveshow.views.customviews.DanmakuLayout;
import com.qq.reader.liveshow.views.customviews.SuperVipEnterLayout;
import java.util.ArrayList;

/* compiled from: LiveCommonChatProxy */
public class a implements com.qq.reader.liveshow.c.a.a.a {
    LiveActivity a;
    d b;
    private ArrayList<b> c;
    private com.qq.reader.liveshow.a.a d;
    private ArrayList<b> e;
    private ListView f;
    private SuperVipEnterLayout g;
    private DanmakuLayout h;
    private View i;
    private long j;
    private boolean k = false;

    public a(LiveActivity liveActivity, j jVar) {
        this.a = liveActivity;
        this.b = (d) m();
        this.b.a(jVar);
        e();
    }

    public void e() {
        this.h = (DanmakuLayout) this.a.findViewById(e.danmaku_layout);
        this.h.setPositionCallback(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
                this.a.b.b(bVar);
            }
        });
        this.g = (SuperVipEnterLayout) this.a.findViewById(e.superVip_layout);
        this.g.setPositionCallback(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
                this.a.b.a(bVar);
            }
        });
        this.i = this.a.findViewById(e.new_message_btn);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k();
                l.a("event_Z5", null, this.a.a.getApplicationContext());
            }
        });
        this.f = (ListView) this.a.findViewById(e.im_msg_listview);
        this.c = new ArrayList();
        this.d = new com.qq.reader.liveshow.a.a(this.a, this.f, this.c, new com.qq.reader.liveshow.b.j(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b.b();
                this.a.k = true;
            }

            public void b() {
                this.a.k();
            }
        });
        this.f.setAdapter(this.d);
        if (this.e == null) {
            this.e = new ArrayList();
        }
        n();
    }

    public void j() {
        this.d.notifyDataSetChanged();
    }

    public void k() {
        if (this.i.getVisibility() == 0) {
            this.i.setVisibility(8);
        }
        ArrayList c = this.b.c();
        if (c == null || c.size() == 0) {
            this.k = false;
            return;
        }
        for (int i = 0; i < c.size(); i++) {
            b aVar = new com.qq.reader.liveshow.model.im.viewdata.a(this.a);
            aVar.a((com.qq.reader.liveshow.model.im.message.a.b) c.get(i));
            this.c.add(aVar);
        }
        j();
        this.k = false;
    }

    public void l() {
        this.c.clear();
        this.d.notifyDataSetChanged();
    }

    private void n() {
        com.qq.reader.liveshow.model.im.message.a.b bVar = new com.qq.reader.liveshow.model.im.message.a.b(new SenderProfile().setNickName("管理员"));
        bVar.a(this.a.getString(h.list_msg_notice));
        bVar.a(6);
        a(bVar);
    }

    public i m() {
        return new d(this);
    }

    public void h() {
        this.b.a();
    }

    public void i() {
    }

    public void a(com.qq.reader.liveshow.model.im.message.a.b bVar) {
        b aVar = new com.qq.reader.liveshow.model.im.viewdata.a(this.a);
        aVar.a(bVar);
        this.e.add(aVar);
        if (System.currentTimeMillis() - this.j >= 500 || bVar.d()) {
            this.j = System.currentTimeMillis();
            this.c.addAll(this.e);
            this.e.clear();
            if (!this.k) {
                j();
            }
        }
    }

    public void b(com.qq.reader.liveshow.model.im.message.a.b bVar) {
        this.g.a(bVar);
    }

    public void c(com.qq.reader.liveshow.model.im.message.a.b bVar) {
        this.h.a(bVar);
    }

    public void a() {
        if (this.i.getVisibility() != 0) {
            this.i.setVisibility(0);
        }
    }

    public int b() {
        return 1;
    }

    public int c() {
        return 2;
    }

    public void d() {
        if (this.k) {
            k();
        }
    }

    public void f() {
    }

    public void g() {
    }
}
