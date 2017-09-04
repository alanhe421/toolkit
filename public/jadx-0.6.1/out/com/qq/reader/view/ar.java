package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.plugin.tts.model.VoiceMetroItem;
import com.qq.reader.plugin.tts.n;
import com.qq.reader.view.VoiceTabs.a;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TtsSettingDlg */
public class ar extends BaseDialog {
    private View a;
    private SeekBar b;
    private TextView c;
    private View d;
    private View e;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private TextView n;
    private TextView o;
    private TextView p;
    private VoiceTabs q;
    private LinearLayout r;
    private boolean s = true;
    private int t = -1;
    private long u;
    private CountDownTimer v;
    private a w;

    public ar(Activity activity) {
        int i = 1;
        if (this.f == null) {
            a(activity, null, R.layout.ttssettingdlg, true, false, true);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ ar a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.m();
                }
            });
        }
        d(false);
        e(true);
        this.e = this.f.findViewById(R.id.ttssettingdialog);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.cancel();
            }
        });
        this.j = this.f.findViewById(R.id.top_shadow);
        this.i = this.f.findViewById(R.id.tts_panel);
        this.o = (TextView) this.f.findViewById(R.id.left_button);
        this.n = (TextView) this.f.findViewById(R.id.right_button);
        this.d = this.f.findViewById(R.id.speedinfo_area);
        this.p = (TextView) this.f.findViewById(R.id.clock_text);
        this.k = this.f.findViewById(R.id.divider_1);
        this.l = this.f.findViewById(R.id.divider_2);
        this.m = this.f.findViewById(R.id.divider_3);
        this.b = (SeekBar) this.f.findViewById(R.id.tts_adjust_progress);
        this.b.setMax(100);
        this.b.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() != d.ax(this.a.e())) {
                    d.v(this.a.e(), seekBar.getProgress());
                    n.f().c(seekBar.getProgress());
                    n.f().m();
                }
                j.a(74, 1);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.a.c.setText(i + "");
            }
        });
        this.a = this.f.findViewById(R.id.bottom_btn_area);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.w != null) {
                    this.a.w.O();
                }
                this.a.l();
                this.a.k();
                j.a(73, 1);
            }
        });
        this.c = (TextView) this.f.findViewById(R.id.tts_speed_info_tv);
        this.q = (VoiceTabs) this.f.findViewById(R.id.tts_voice_list);
        this.q.setOnTabsChangedListener(new a(this) {
            final /* synthetic */ ar a;

            {
                this.a = r1;
            }

            public boolean a(int i) {
                MetroItem a = this.a.q.a(i);
                if (a != null) {
                    if (a.getId() == -100) {
                        n.f().o();
                        if (this.a.w != null) {
                            this.a.w.P();
                        }
                        j.a(76, 1);
                    } else {
                        if (!d.ay(this.a.e()).equalsIgnoreCase(a.getName())) {
                            VoiceMetroItem voiceMetroItem = (VoiceMetroItem) a;
                            if (n.f().l() == 1) {
                                if (voiceMetroItem.mType == 1) {
                                    d.o(this.a.e(), a.getName());
                                    this.a.w.h(1);
                                } else {
                                    d.o(this.a.e(), a.getName());
                                    n.f().a(a.getName());
                                    n.f().m();
                                    this.a.q.setSelectedIndex(i);
                                }
                            } else if (voiceMetroItem.mType == 0) {
                                d.o(this.a.e(), a.getName());
                                this.a.w.h(0);
                            } else {
                                d.o(this.a.e(), a.getName());
                                n.f().a(a.getName());
                                n.f().m();
                                this.a.q.setSelectedIndex(i);
                            }
                        }
                        j.a(75, 1);
                    }
                }
                return false;
            }
        });
        this.r = (LinearLayout) a(R.id.timing_ctrl);
        this.r.setVisibility(8);
        while (i < this.r.getChildCount()) {
            ((TextView) this.r.getChildAt(i)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ar a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a(view);
                }
            });
            i++;
        }
        a(d.n);
    }

    private void k() {
        for (int i = 1; i < this.r.getChildCount(); i++) {
            TextView textView = (TextView) this.r.getChildAt(i);
            textView.setText(Integer.valueOf((String) textView.getTag()).intValue() + "分钟");
            textView.setTextColor(e().getResources().getColor(R.color.text_color_c103));
            textView.setBackgroundResource(R.drawable.tts_round_corner_bg_unfocused);
        }
    }

    private void a(View view) {
        k();
        for (int i = 1; i < this.r.getChildCount(); i++) {
            if (view.equals(this.r.getChildAt(i))) {
                l();
                if (this.t != i) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(e().getResources().getColor(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
                    textView.setBackgroundResource(R.drawable.tts_round_corner_bg);
                    this.t = i;
                    a(60000 * Integer.valueOf((String) view.getTag()).intValue(), (TextView) view);
                } else {
                    this.t = -1;
                }
            }
        }
    }

    public void g() {
        k();
        l();
    }

    private void a(int i, TextView textView) {
        final TextView textView2 = textView;
        this.v = new CountDownTimer(this, (long) i, 1000) {
            final /* synthetic */ ar b;

            public void onTick(long j) {
                this.b.u = j;
                long g = this.b.u / BuglyBroadcastRecevier.UPLOADLIMITED;
                long g2 = (this.b.u % BuglyBroadcastRecevier.UPLOADLIMITED) / 1000;
                textView2.setText((g < 10 ? "0" + g : String.valueOf(g)) + ":" + (g2 < 10 ? "0" + g2 : String.valueOf(g2)));
            }

            public void onFinish() {
                if (this.b.w != null) {
                    this.b.w.O();
                }
                this.b.k();
            }
        };
        this.v.start();
    }

    private void l() {
        f.a("SETTINGDLG", "stou count down");
        if (this.v != null) {
            this.v.cancel();
        }
    }

    private void m() {
        n.f().p();
    }

    public void h() {
        int ax = d.ax(e());
        j();
        this.b.setProgress(ax);
        this.c.setText(ax + "");
        if (this.a != null) {
            if (this.s) {
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
        }
        this.f.show();
    }

    public void i() {
        h();
        n.f().o();
    }

    public void j() {
        int i = 0;
        List<com.qq.reader.plugin.tts.model.f> k = n.f().k();
        List arrayList = new ArrayList();
        String ay = d.ay(e());
        if (k == null || k.size() <= 0) {
            this.q.setVisibility(8);
            return;
        }
        this.q.setVisibility(0);
        int i2 = -1;
        int i3 = 0;
        for (com.qq.reader.plugin.tts.model.f fVar : k) {
            if (ay.equalsIgnoreCase(fVar.a)) {
                i2 = i3;
            }
            int i4 = i3 + 1;
            arrayList.add(new VoiceMetroItem(i3, fVar.a, fVar.b, fVar.c));
            i3 = i4;
        }
        if (i2 == -1) {
            d.o(e(), ((com.qq.reader.plugin.tts.model.f) k.get(0)).a);
        } else {
            i = i2;
        }
        this.q.setDataset(arrayList);
        this.q.a();
        this.q.setSelectedIndex(i);
        this.q.b();
    }

    public void a(a aVar) {
        this.w = aVar;
    }

    public void a(boolean z) {
        int i;
        TextView textView;
        if (z) {
            this.j.setVisibility(8);
            this.d.setBackgroundResource(R.drawable.popup_box_night);
            this.c.setTextColor(e().getResources().getColor(R.color.text_color_c304));
            this.i.setBackgroundResource(R.color.commonsetting_bg_color_night);
            this.o.setTextColor(e().getResources().getColor(R.color.text_color_c102));
            this.n.setTextColor(e().getResources().getColor(R.color.text_color_c102));
            f(true);
            this.p.setTextColor(e().getResources().getColor(R.color.text_color_c102));
            for (i = 1; i < this.r.getChildCount(); i++) {
                textView = (TextView) this.r.getChildAt(i);
                if (i == this.t) {
                    textView.setTextColor(e().getResources().getColorStateList(R.color.text_color_c304));
                    textView.setBackgroundResource(R.drawable.tts_round_corner_bg_night);
                } else {
                    textView.setTextColor(e().getResources().getColorStateList(R.color.text_color_c103));
                    textView.setBackgroundResource(R.drawable.tts_round_corner_bg_unfocused_night);
                }
            }
            j();
            this.a.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector_night);
            this.k.setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.l.setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.m.setBackgroundResource(R.color.commonset_dlg_divider_night);
            return;
        }
        this.j.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.popup_box);
        this.c.setTextColor(e().getResources().getColor(R.color.text_color_c301));
        this.i.setBackgroundResource(R.color.commonsetting_bg_color);
        this.o.setTextColor(e().getResources().getColor(R.color.text_color_c801));
        this.n.setTextColor(e().getResources().getColor(R.color.text_color_c801));
        f(false);
        this.p.setTextColor(e().getResources().getColor(R.color.text_color_c801));
        for (i = 1; i < this.r.getChildCount(); i++) {
            textView = (TextView) this.r.getChildAt(i);
            if (i == this.t) {
                textView.setTextColor(e().getResources().getColorStateList(R.color.text_color_c301));
                textView.setBackgroundResource(R.drawable.tts_round_corner_bg);
            } else {
                textView.setTextColor(e().getResources().getColorStateList(R.color.text_color_c103));
                textView.setBackgroundResource(R.drawable.tts_round_corner_bg_unfocused);
            }
        }
        j();
        this.a.setBackgroundResource(R.drawable.commonsetting_btn_bg_selector);
        this.k.setBackgroundResource(R.color.commonset_dlg_divider);
        this.l.setBackgroundResource(R.color.commonset_dlg_divider);
        this.m.setBackgroundResource(R.color.commonset_dlg_divider);
    }

    private void f(boolean z) {
        Drawable drawable = e().getResources().getDrawable(R.drawable.seekbar_style);
        Drawable drawable2 = e().getResources().getDrawable(R.drawable.seekbar_thumb);
        if (z) {
            drawable = e().getResources().getDrawable(R.drawable.seekbar_style_night);
            drawable2 = e().getResources().getDrawable(R.drawable.seekbar_thumb_night);
        }
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        if (VERSION.SDK_INT < 14) {
            drawable.setBounds(this.b.getProgressDrawable().getBounds());
            this.b.setProgressDrawable(drawable);
            if (this.b.getProgress() == 0) {
                this.b.setProgress(1);
                this.b.setProgress(0);
                return;
            }
            this.b.setProgress(this.b.getProgress() - 1);
            this.b.setProgress(this.b.getProgress() + 1);
            return;
        }
        this.b.setProgressDrawable(drawable);
        this.b.setThumb(drawable2);
    }
}
