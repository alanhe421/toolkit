package com.liveshow.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.liveshow.a.b.b;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.liveshow.views.customviews.BaseLiveEndFrame;
import com.tencent.feedback.proguard.R;

/* compiled from: HostLiveEndFrame */
public class a extends BaseLiveEndFrame implements b {
    private TextView d = ((TextView) this.o.findViewById(R.id.live_end_page_host_tv_time));
    private TextView e = ((TextView) this.o.findViewById(R.id.live_end_page_host_tv_admires));
    private TextView f = ((TextView) this.o.findViewById(R.id.live_end_page_host_tv_members));
    private TextView g = ((TextView) this.o.findViewById(R.id.live_end_page_host_name));
    private TextView h = ((TextView) this.o.findViewById(R.id.live_end_page_host_tv_income));
    private ImageView i = ((ImageView) this.o.findViewById(R.id.live_end_page_host_head_icon));
    private View j = this.o.findViewById(R.id.live_end_page_host_head_icon_container);
    private ImageView k = ((ImageView) this.o.findViewById(R.id.live_end_page_host_author_level));
    private View l = this.o.findViewById(R.id.live_end_page_host_info_layout);
    private View m = this.o.findViewById(R.id.live_end_host_content);
    private com.liveshow.a.b.a n;
    private View o;

    public a(final com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.o = LayoutInflater.from(activity).inflate(R.layout.live_end_page_host_dialog, viewGroup, false);
        this.o.findViewById(R.id.live_end_page_host_btn_cancel).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (aVar != null) {
                    aVar.a(7, null);
                }
            }
        });
        this.n = new com.liveshow.c.b(this);
    }

    public void b() {
        super.b();
        this.c.addView(this.o);
        this.n.a((b) this);
        c.a(a()).a(getAvatarUrl(), this.i, com.qq.reader.common.imageloader.a.a().o());
        this.g.setText(getName());
        this.l.setVisibility(8);
        this.n.a(getRoomId());
    }

    public void c() {
        this.n.a();
    }

    public void a(com.liveshow.model.b bVar) {
        if (bVar != null) {
            c.a(a()).a(bVar.a, this.i, com.qq.reader.common.imageloader.a.a().o());
            this.j.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.n.b();
                }
            });
            this.g.setText(bVar.c);
            this.l.setVisibility(0);
            if (bVar.d >= 4) {
                this.k.setImageResource(d.a(bVar.d));
                this.k.setVisibility(0);
            } else {
                this.k.setVisibility(8);
            }
            this.d.setText(bVar.e);
            if (bVar.f != null) {
                this.f.setText(bVar.f.b + bVar.f.a);
            }
            if (bVar.g != null) {
                this.e.setText(bVar.g.b + bVar.g.a);
            }
            this.h.setText(bVar.h + "");
        }
    }

    public Activity a() {
        return this.b;
    }
}
