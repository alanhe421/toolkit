package com.qq.reader.liveshow.views;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.a.i;
import com.qq.reader.liveshow.b.l;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.e;
import com.qq.reader.liveshow.c.g;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.views.customviews.GiftSelectDialog;
import com.qq.reader.liveshow.views.customviews.HeartLayout;
import com.qq.reader.liveshow.views.customviews.InputTextMsgDialog;
import com.qq.reader.liveshow.views.customviews.SlideView;
import com.qq.reader.liveshow.views.customviews.SlideView.OnClickChildViewListener;
import com.qq.reader.liveshow.views.customviews.SlideView.OnShowChildViewListener;
import java.util.ArrayList;

/* compiled from: LiveCustomLayerProxy */
public class b implements com.qq.reader.liveshow.c.a.b.a {
    private static boolean E = false;
    private static boolean F = true;
    private int A;
    private SlideView B;
    private b C;
    private InputTextMsgDialog D = null;
    private boolean G = false;
    private boolean H;
    private boolean I = false;
    private boolean J;
    LiveActivity a;
    e b;
    private int c = 0;
    private HeartLayout d;
    private TextView e;
    private a f = new a(this);
    private LinearLayout g;
    private RelativeLayout h;
    private RelativeLayout i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private View w;
    private View x;
    private SeekBar y;
    private int z;

    /* compiled from: LiveCustomLayerProxy */
    public interface b {
        void a(boolean z);
    }

    /* compiled from: LiveCustomLayerProxy */
    class a implements OnClickListener {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == com.qq.reader.liveshow.a.e.message_input) {
                if (c.a().k()) {
                    com.qq.reader.liveshow.b.b b = n.a().b();
                    if (b != null) {
                        b.a(this.a.a);
                        return;
                    }
                    return;
                }
                this.a.n();
            } else if (id == com.qq.reader.liveshow.a.e.send_gift) {
                this.a.o();
                l.a("event_Z13", null, this.a.a.getApplicationContext());
            } else if (id == com.qq.reader.liveshow.a.e.send_good) {
                this.a.p();
                l.a("event_Z15", null, this.a.a.getApplicationContext());
            } else if (id == com.qq.reader.liveshow.a.e.switch_cam) {
                if (this.a.a != null) {
                    g q = this.a.a.q();
                    if (q != null) {
                        q.i();
                    }
                    l.a("event_Z20", null, this.a.a.getApplicationContext());
                }
            } else if (id != com.qq.reader.liveshow.a.e.camera_controll && id != com.qq.reader.liveshow.a.e.mic_controll) {
                if (id == com.qq.reader.liveshow.a.e.beauty_btn) {
                    SxbLog.b("LiveCustomLayerProxy", "onClick " + this.a.z);
                    this.a.H = b.E;
                    if (this.a.w == null) {
                        SxbLog.b("LiveCustomLayerProxy", "beauty_btn mTopBar  is null ");
                    } else if (this.a.w.getVisibility() != 0) {
                        this.a.w.setVisibility(0);
                        this.a.B.setVisibility(4);
                        this.a.y.setProgress(this.a.z);
                    } else {
                        this.a.w.setVisibility(8);
                        this.a.B.setVisibility(0);
                    }
                    l.a("event_Z21", null, this.a.a.getApplicationContext());
                } else if (id == com.qq.reader.liveshow.a.e.white_btn) {
                    SxbLog.b("LiveCustomLayerProxy", "onClick " + this.a.A);
                    this.a.H = b.F;
                    if (this.a.w == null) {
                        SxbLog.b("LiveCustomLayerProxy", "beauty_btn mTopBar  is null ");
                    } else if (this.a.w.getVisibility() != 0) {
                        this.a.w.setVisibility(0);
                        this.a.B.setVisibility(4);
                        this.a.y.setProgress(this.a.A);
                    } else {
                        this.a.w.setVisibility(8);
                        this.a.B.setVisibility(0);
                    }
                    l.a("event_Z22", null, this.a.a.getApplicationContext());
                } else if (id == com.qq.reader.liveshow.a.e.qav_beauty_setting_finish) {
                    this.a.w.setVisibility(8);
                    this.a.B.setVisibility(0);
                } else if (id == com.qq.reader.liveshow.a.e.host_share_btn) {
                    r0 = n.a().d();
                    if (r0 != null && this.a.a != null) {
                        r0.a(this.a.a, com.qq.reader.liveshow.utils.l.a.a(com.qq.reader.liveshow.utils.l.d(com.qq.reader.liveshow.model.a.i()), com.qq.reader.liveshow.model.a.e(), n.a().e().a(this.a.a, com.qq.reader.liveshow.model.a.h()), n.a().e().b(this.a.a, com.qq.reader.liveshow.model.a.d())));
                        l.a("event_Z25", null, this.a.a.getApplicationContext());
                    }
                } else if (id == com.qq.reader.liveshow.a.e.member_share_btn) {
                    r0 = n.a().d();
                    if (r0 != null && this.a.a != null) {
                        r0.a(this.a.a, com.qq.reader.liveshow.utils.l.a.a(com.qq.reader.liveshow.utils.l.d(com.qq.reader.liveshow.model.a.i()), com.qq.reader.liveshow.model.a.e(), n.a().e().a(this.a.a, com.qq.reader.liveshow.model.a.h()), n.a().e().b(this.a.a, com.qq.reader.liveshow.model.a.d())));
                        l.a("event_Z10", null, this.a.a.getApplicationContext());
                    }
                } else {
                    this.a.p();
                    l.a("event_Z15", null, this.a.a.getApplicationContext());
                }
            }
        }
    }

    public b(LiveActivity liveActivity, j jVar) {
        this.a = liveActivity;
        this.b = (e) e();
        this.b.a(jVar);
        d();
    }

    public void d() {
        this.d = (HeartLayout) this.a.findViewById(com.qq.reader.liveshow.a.e.heart_layout);
        this.h = (RelativeLayout) this.a.findViewById(com.qq.reader.liveshow.a.e.host_bottom_layout);
        this.i = (RelativeLayout) this.a.findViewById(com.qq.reader.liveshow.a.e.member_bottom_layout);
        this.g = (LinearLayout) this.a.findViewById(com.qq.reader.liveshow.a.e.video_member_bottom_layout);
        this.g.setVisibility(4);
        this.p = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.video_interact);
        this.j = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.message_input);
        this.j.setOnClickListener(this.f);
        this.q = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.camera_controll);
        this.r = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.mic_controll);
        this.s = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.close_member_video);
        this.q.setOnClickListener(this.f);
        this.r.setOnClickListener(this.f);
        this.s.setOnClickListener(this.f);
        this.u = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.host_share_btn);
        this.u.setEnabled(false);
        this.v = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.member_share_btn);
        this.u.setOnClickListener(this.f);
        this.v.setOnClickListener(this.f);
        this.B = (SlideView) this.a.findViewById(com.qq.reader.liveshow.a.e.control_ui);
    }

    public void b(String str) {
        if (c.a().h() == 1) {
            if (str.equals(c.a().b())) {
                this.h.setVisibility(0);
                this.g.setVisibility(4);
                return;
            }
            this.h.setVisibility(4);
            this.g.setVisibility(0);
        } else if (str.equals(c.a().b())) {
            this.g.setVisibility(0);
            this.i.setVisibility(4);
        } else if (str.equals(com.qq.reader.liveshow.model.a.c())) {
            this.g.setVisibility(4);
            this.i.setVisibility(0);
        } else {
            this.g.setVisibility(4);
            this.i.setVisibility(4);
        }
    }

    private void m() {
        this.D = new InputTextMsgDialog(this.a, i.inputdialog, this.a.q(), this.b);
        Display defaultDisplay = this.a.getWindowManager().getDefaultDisplay();
        LayoutParams attributes = this.D.getWindow().getAttributes();
        attributes.width = defaultDisplay.getWidth();
        this.D.getWindow().setAttributes(attributes);
        this.D.setCancelable(true);
        this.D.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.I = false;
                Message obtain = Message.obtain();
                obtain.what = -3;
                this.a.b.b(obtain);
                if (this.a.C != null) {
                    this.a.C.a(false);
                }
            }
        });
    }

    private void n() {
        m();
        this.D.show();
        this.I = true;
        Message obtain = Message.obtain();
        obtain.what = -2;
        this.b.b(obtain);
        if (this.C != null) {
            this.C.a(true);
        }
    }

    private void o() {
        GiftSelectDialog giftSelectDialog = new GiftSelectDialog(this.a, i.gift_send_dialog, this.b, com.qq.reader.liveshow.model.a.m());
        Display defaultDisplay = this.a.getWindowManager().getDefaultDisplay();
        LayoutParams attributes = giftSelectDialog.getWindow().getAttributes();
        attributes.width = defaultDisplay.getWidth();
        giftSelectDialog.getWindow().setAttributes(attributes);
        giftSelectDialog.setCancelable(true);
        giftSelectDialog.show();
    }

    private float d(int i) {
        SxbLog.c("shixu", "progress: " + i);
        return (9.0f * ((float) i)) / 100.0f;
    }

    public void c(int i) {
        this.o.setBackgroundResource(i);
    }

    private void p() {
        if (com.qq.reader.liveshow.model.a.t()) {
            this.d.a();
            this.b.b();
        }
    }

    public com.qq.reader.liveshow.c.i e() {
        return new e(this);
    }

    public void h() {
        this.c = 0;
        this.b.a();
    }

    public void i() {
        if (c.a().h() == 1) {
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            this.k = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.flash_btn);
            this.l = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.switch_cam);
            this.m = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.beauty_btn);
            this.n = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.white_btn);
            this.o = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.mic_btn);
            this.k.setOnClickListener(this.f);
            this.l.setOnClickListener(this.f);
            this.m.setOnClickListener(this.f);
            this.n.setOnClickListener(this.f);
            this.o.setOnClickListener(this.f);
            this.w = this.a.findViewById(com.qq.reader.liveshow.a.e.qav_beauty_setting);
            this.x = this.a.findViewById(com.qq.reader.liveshow.a.e.qav_beauty_setting_finish);
            this.x.setOnClickListener(this.f);
            this.y = (SeekBar) this.a.findViewById(com.qq.reader.liveshow.a.e.qav_beauty_progress);
            this.y.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    SxbLog.c("SeekBar", "onStopTrackingTouch");
                    if (this.a.H == b.E) {
                        m.a(this.a.a, "美颜 " + this.a.z + "%", 0);
                    } else {
                        m.a(this.a.a, "美白 " + this.a.A + "%", 0);
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    SxbLog.c("SeekBar", "onStartTrackingTouch");
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    SxbLog.b("LiveCustomLayerProxy", "onProgressChanged " + i);
                    if (this.a.H == b.E) {
                        this.a.z = i;
                        com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl().inputBeautyParam(this.a.d(i));
                        return;
                    }
                    this.a.A = i;
                    com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl().inputWhiteningParam(this.a.d(i));
                }
            });
            return;
        }
        this.i.setVisibility(0);
        this.h.setVisibility(8);
        this.t = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.send_gift);
        this.t.setOnClickListener(this.f);
        this.e = (TextView) this.a.findViewById(com.qq.reader.liveshow.a.e.send_good);
        this.e.setOnClickListener(this.f);
        this.p.setVisibility(8);
        new ArrayList().add(com.qq.reader.liveshow.model.a.c());
        this.B.setOnClickChildViewListener(new OnClickChildViewListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.p();
                l.a("event_Z15", null, this.a.a.getApplicationContext());
            }
        });
        this.B.setOnShowChildViewListener(new OnShowChildViewListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                l.a("event_Z19", null, this.a.a.getApplicationContext());
            }

            public void b() {
            }
        });
    }

    public void a() {
        if (!this.G && !this.J) {
            this.d.a();
        }
    }

    public void b() {
        com.qq.reader.liveshow.b.b b = n.a().b();
        if (b != null) {
            b.a(this.a);
        }
    }

    public void a(int i) {
        SxbLog.e("LiveCustomLayerProxy", this.a.getString(h.toast_sendgiftsuccess, new Object[]{String.valueOf(i)}));
    }

    public void a(GiftItem giftItem, int i) {
        String str = "";
        if (giftItem != null) {
            int i2 = giftItem.mPrice * i;
            str = this.a.getString(h.live_show_charge_danmaku_msg_count, new Object[]{String.valueOf(i2)}) + this.a.getString(h.bookcoin_unit);
        } else {
            str = this.a.getString(h.live_show_charge_danmaku_msg_default);
        }
        n.a().e().a(this.a, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    n.a().d().a(this.a.a, com.qq.reader.liveshow.utils.l.a.a(0, "danmaku"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, str).show();
    }

    public void b(GiftItem giftItem, int i) {
        String str = "";
        if (giftItem != null) {
            int i2 = giftItem.mPrice * i;
            str = this.a.getString(h.live_show_gift_cost_count, new Object[]{String.valueOf(i2)}) + this.a.getString(h.bookcoin_unit);
        } else {
            str = this.a.getString(h.live_show_charge_danmaku_msg_default);
        }
        n.a().e().a(this.a, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    n.a().d().a(this.a.a, com.qq.reader.liveshow.utils.l.a.a(0, "gift"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, str).show();
    }

    public void a(String str) {
        m.a(this.a, str, 0);
    }

    public void c() {
        if (this.u != null) {
            this.u.setEnabled(true);
        }
    }

    public void b(int i) {
        m.a(this.a, i, 0);
    }

    public boolean j() {
        if (this.w == null || this.w.getVisibility() != 0) {
            return false;
        }
        this.w.setVisibility(8);
        this.B.setVisibility(0);
        return true;
    }

    public void f() {
        this.J = false;
        if (this.I) {
            n();
        }
    }

    public void g() {
        this.J = true;
    }

    public void a(b bVar) {
        this.C = bVar;
    }
}
