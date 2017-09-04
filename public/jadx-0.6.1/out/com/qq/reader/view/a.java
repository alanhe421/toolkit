package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

/* compiled from: AutoScrollControlDlg */
public class a extends BaseDialog {
    Context a;
    private a b;
    private WeakReference<Handler> c;
    private boolean d = true;
    private View e;
    private View i;
    private View j;
    private View k;
    private TextView l;
    private TextView m;
    private TextView n;
    private View o;
    private View p;
    private View q;
    private View r;
    private TextView s;
    private int t = 0;

    /* compiled from: AutoScrollControlDlg */
    public interface a {
        void b(boolean z);

        void e(int i);

        float g(boolean z);

        void z();
    }

    public a(Activity activity, boolean z) {
        this.a = activity;
        if (this.f == null) {
            a(activity, null, R.layout.autoscrolldialog, true, false, true);
            d(false);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.d && this.a.c != null) {
                        Handler handler = (Handler) this.a.c.get();
                        if (handler != null) {
                            handler.removeMessages(1244);
                            handler.sendEmptyMessageDelayed(1244, 1800000);
                            c.e("AUTO", "send msg");
                        }
                    }
                    this.a.g();
                    if (this.a.b != null) {
                        this.a.b.z();
                    }
                    this.a.d = true;
                }
            });
            this.e = this.f.findViewById(R.id.top_shadow);
            this.i = this.f.findViewById(R.id.divider_1);
            this.j = this.f.findViewById(R.id.divider_2);
            this.k = this.f.findViewById(R.id.divider_3);
            this.l = (TextView) this.f.findViewById(R.id.autoscroll_reduce_speed);
            this.m = (TextView) this.f.findViewById(R.id.autoscroll_add_speed);
            this.r = this.f.findViewById(R.id.autoscroll_btn_mode);
            if (z) {
                this.r.setVisibility(8);
            } else {
                this.r.setVisibility(0);
            }
            this.n = (TextView) this.f.findViewById(R.id.autoscroll_btn_mode);
            this.o = this.f.findViewById(R.id.autoscroll_btn_stop);
            this.l.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.b != null) {
                        this.a.a(this.a.b.g(false));
                        i.a("event_B46", null, this.a.a);
                    }
                }
            });
            this.m.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.b != null) {
                        this.a.a(this.a.b.g(true));
                        i.a("event_B46", null, this.a.a);
                    }
                }
            });
            this.n.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.b != null) {
                        Handler handler;
                        if (this.a.t == 2) {
                            this.a.t = 1;
                            this.a.n.setText("切换至覆盖模式");
                            this.a.g();
                            if (this.a.c != null) {
                                handler = (Handler) this.a.c.get();
                                if (handler != null) {
                                    handler.removeMessages(1244);
                                    handler.sendEmptyMessageDelayed(1244, 1800000);
                                    c.e("AUTO", "send msg");
                                }
                            }
                            this.a.b.e(this.a.t);
                        } else if (this.a.t == 1) {
                            this.a.t = 2;
                            this.a.n.setText("切换至滚动模式");
                            this.a.g();
                            if (this.a.c != null) {
                                handler = (Handler) this.a.c.get();
                                if (handler != null) {
                                    handler.removeMessages(1244);
                                    handler.sendEmptyMessageDelayed(1244, 1800000);
                                    c.e("AUTO", "send msg");
                                }
                            }
                            this.a.b.e(this.a.t);
                        }
                        i.a("event_B54", null, this.a.a);
                    }
                }
            });
            this.o.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.g();
                    if (this.a.b != null) {
                        this.a.b.b(true);
                    }
                    i.a("event_B47", null, this.a.a);
                }
            });
        }
        this.s = (TextView) this.f.findViewById(R.id.autoscroll_speed_num_tv);
        this.q = this.f.findViewById(R.id.autoscroll_speed_num);
        this.q.setClickable(true);
        this.p = this.f.findViewById(R.id.autoscroll_just_speed_buttons);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
            }
        });
        a(d.n);
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(boolean z) {
        if (z) {
            this.p.setBackgroundResource(R.color.commonsetting_bg_color_night);
            this.e.setVisibility(8);
            this.q.setBackgroundResource(R.drawable.popup_box_night);
            this.s.setTextColor(e().getResources().getColor(R.color.text_color_c304));
            this.l.setTextColor(e().getResources().getColorStateList(R.color.autoscrol_speedbtn_textcolor_night));
            this.l.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector_night);
            this.m.setTextColor(e().getResources().getColorStateList(R.color.autoscrol_speedbtn_textcolor_night));
            this.m.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector_night);
            this.o.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector_night);
            this.r.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector_night);
            this.n.setTextColor(e().getResources().getColor(R.color.text_color_c304));
            this.i.setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.j.setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.k.setBackgroundResource(R.color.commonset_dlg_divider_night);
            return;
        }
        this.p.setBackgroundResource(R.color.commonsetting_bg_color);
        this.e.setVisibility(0);
        this.q.setBackgroundResource(R.drawable.popup_box);
        this.s.setTextColor(e().getResources().getColor(R.color.text_color_c301));
        this.l.setTextColor(e().getResources().getColorStateList(R.color.autoscrol_speedbtn_textcolor));
        this.l.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector);
        this.m.setTextColor(e().getResources().getColorStateList(R.color.autoscrol_speedbtn_textcolor));
        this.m.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector);
        this.o.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector);
        this.r.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector);
        this.n.setTextColor(e().getResources().getColor(R.color.text_color_c301));
        this.i.setBackgroundResource(R.color.commonset_dlg_divider);
        this.j.setBackgroundResource(R.color.commonset_dlg_divider);
        this.k.setBackgroundResource(R.color.commonset_dlg_divider);
    }

    public void a(float f, int i) {
        a(f);
        this.t = i;
        h();
        this.f.show();
    }

    private void a(float f) {
        synchronized (this) {
            this.l.setEnabled(true);
            this.m.setEnabled(true);
            if (f >= 17.0f) {
                this.m.setEnabled(false);
            } else if (f <= 0.0f) {
                this.l.setEnabled(false);
            }
            this.s.setText(String.valueOf(Math.round(1.0f + f)));
        }
    }

    private boolean g() {
        if (this.f.isShowing()) {
            this.f.cancel();
        }
        this.d = false;
        return true;
    }

    private void h() {
        this.o.setClickable(true);
        switch (this.t) {
            case 1:
                this.n.setText("切换至滚动模式");
                return;
            case 2:
                this.n.setText("切换至覆盖模式");
                return;
            default:
                return;
        }
    }

    public void a(Handler handler) {
        this.c = new WeakReference(handler);
    }
}
