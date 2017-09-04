package com.liveshow.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.liveshow.a.c.a;
import com.liveshow.c.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.liveshow.views.customviews.BaseMemberInfoDialog;
import com.tencent.feedback.proguard.R;

public class MemberDetailDialog extends BaseMemberInfoDialog implements a, c {
    private View a;
    private TextView b;
    private View c;
    private TextView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private View q;
    private View r;
    private View s;
    private View t;
    private boolean u;
    private c v;
    private Activity w;
    private BroadcastReceiver x = new BroadcastReceiver(this) {
        final /* synthetic */ MemberDetailDialog a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.reader.live.login.success")) {
                this.a.v.c();
            }
        }
    };

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.reader.live.login.success");
        this.w.registerReceiver(this.x, intentFilter);
    }

    public MemberDetailDialog(Activity activity, int i) {
        super(activity, i);
        setContentView(R.layout.live_show_member_detail_dialog);
        j();
        i();
        this.w = activity;
        this.v = new c(this);
    }

    private void h() {
        this.d.setText(getName());
        com.qq.reader.common.imageloader.c.a(getContext()).a(getAvatarUrl(), this.e, com.qq.reader.common.imageloader.a.a().p());
        this.q.setVisibility(8);
        this.f.setVisibility(8);
        this.t.setVisibility(0);
        this.r.setVisibility(4);
        this.s.setVisibility(4);
    }

    protected void onStart() {
        super.onStart();
        this.v.a((a) this);
        h();
        d();
        this.v.a(getUserId(), getRoomId());
    }

    protected void onStop() {
        super.onStop();
        this.v.b();
        this.v.a();
        this.w.unregisterReceiver(this.x);
    }

    private void i() {
        this.a = findViewById(R.id.live_show_member_info_report);
        this.b = (TextView) findViewById(R.id.live_show_member_info_shut_up);
        this.c = findViewById(R.id.live_show_member_info_kick_out);
        this.d = (TextView) findViewById(R.id.live_show_member_info_name);
        this.e = (ImageView) findViewById(R.id.live_show_member_user_icon);
        this.f = (ImageView) findViewById(R.id.live_show_member_gender_icon);
        this.g = (ImageView) findViewById(R.id.live_show_member_month_img);
        this.h = (TextView) findViewById(R.id.live_show_member_vip_level_tv);
        this.i = (TextView) findViewById(R.id.live_show_member_grow_level_tv);
        this.j = (TextView) findViewById(R.id.live_show_member_sign);
        this.k = (TextView) findViewById(R.id.live_show_member_read_time_hour_count_tv);
        this.l = (TextView) findViewById(R.id.live_show_member_read_time_hour_tv);
        this.m = (TextView) findViewById(R.id.live_show_member_read_time_minute_count_tv);
        this.n = (TextView) findViewById(R.id.live_show_member_read_time_minute_tv);
        this.o = (TextView) findViewById(R.id.live_show_member_praise_num_tv);
        this.p = (TextView) findViewById(R.id.live_show_member_info_contribution);
        this.q = findViewById(R.id.live_show_member_info_contribution_layout);
        this.r = findViewById(R.id.live_show_member_user_layout);
        this.s = findViewById(R.id.live_show_member_info_action_container);
        this.t = findViewById(R.id.live_show_member_detail_loading);
    }

    public boolean c() {
        return this.u;
    }

    private void j() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        window.setGravity(80);
        attributes.width = defaultDisplay.getWidth();
        getWindow().setAttributes(attributes);
    }

    public void b(boolean z) {
        this.u = z;
        if (z) {
            this.b.setText(R.string.free_shut_up_state);
        } else {
            this.b.setText(R.string.shut_up);
        }
    }

    public void a(com.liveshow.model.c cVar) {
        this.t.setVisibility(8);
        this.r.setVisibility(0);
        this.s.setVisibility(0);
        d.a(this, cVar.g, getUserId(), getMyId(), cVar.h);
        if (cVar.g < 5) {
            b(false);
        } else {
            b(true);
        }
        if (cVar.a == 0) {
            this.f.setImageResource(R.drawable.user_center_male_icon);
        } else if (cVar.a == 1) {
            this.f.setImageResource(R.drawable.user_center_female_icon);
        } else {
            this.f.setVisibility(8);
        }
        ao.a(cVar.i, this.g, true);
        this.h.setText("VIP" + cVar.c);
        this.h.setVisibility(0);
        this.i.setVisibility(0);
        this.i.setText("LV" + cVar.d);
        long j = (cVar.e / 1000) / 60;
        long j2 = j / 60;
        long j3 = j2 / 24;
        if (j2 == 0) {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setText(j + "");
            this.n.setVisibility(0);
        } else if (j2 <= 9999) {
            this.k.setText(j2 + "");
            this.l.setVisibility(0);
            this.m.setText((j % 60) + "");
            this.n.setVisibility(0);
        } else {
            this.k.setText(j3 + "");
            this.l.setVisibility(0);
            this.l.setText("天");
            this.m.setText((j2 % 24) + "");
            this.n.setText("小时");
            this.n.setVisibility(0);
        }
        this.o.setText(String.valueOf(cVar.f));
        if (cVar.b > 0) {
            this.q.setVisibility(0);
            this.p.setText(String.valueOf(cVar.b));
        } else {
            this.q.setVisibility(8);
        }
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MemberDetailDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v.a(this.a.getContext(), this.a.getUserId());
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MemberDetailDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c()) {
                    this.a.v.a(this.a.w, this.a.getUserId(), 0);
                } else {
                    this.a.v.a(this.a.getContext(), this.a.getUserId(), -1);
                }
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MemberDetailDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v.b(this.a.getContext(), this.a.getUserId());
            }
        });
    }

    public void a() {
        this.t.setVisibility(8);
    }

    public void a(boolean z) {
        b(z);
    }

    public Activity b() {
        return this.w;
    }

    public void show() {
        if (!this.w.isFinishing()) {
            try {
                super.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public View e() {
        return this.a;
    }

    public View f() {
        return this.b;
    }

    public View g() {
        return this.c;
    }
}
