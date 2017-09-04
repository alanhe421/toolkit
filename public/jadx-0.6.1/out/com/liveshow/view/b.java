package com.liveshow.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.liveshow.a.d.a;
import com.liveshow.c.d;
import com.liveshow.model.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.views.customviews.BaseLiveEndFrame;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MemberLiveEndFrame */
public class b extends BaseLiveEndFrame implements com.liveshow.a.d.b {
    private ImageView d;
    private View e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private LinearLayout k;
    private View l;
    private View m;
    private View n;
    private a o;
    private View p;
    private List<View> q = new ArrayList();
    private BroadcastReceiver r = new BroadcastReceiver(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(n.a().e().d())) {
                this.a.o.a(this.a.getRoomId());
            }
        }
    };

    public b(final LiveEnterRoomDialog.a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.p = LayoutInflater.from(activity).inflate(R.layout.live_end_page_member_dialog, viewGroup, false);
        this.d = (ImageView) this.p.findViewById(R.id.live_end_page_member_head_icon);
        this.e = this.p.findViewById(R.id.live_end_page_member_head_icon_container);
        this.j = (TextView) this.p.findViewById(R.id.live_end_page_member_name);
        this.i = (TextView) this.p.findViewById(R.id.live_end_page_member_follow);
        this.f = (TextView) this.p.findViewById(R.id.live_end_page_member_tv_time);
        this.g = (TextView) this.p.findViewById(R.id.live_end_page_member_tv_members);
        this.h = (TextView) this.p.findViewById(R.id.live_end_page_member_tv_admires);
        this.k = (LinearLayout) this.p.findViewById(R.id.live_end_page_member_books_recommendation);
        this.l = this.p.findViewById(R.id.live_end_page_member_tv_master_piece_tag_layout);
        this.m = this.p.findViewById(R.id.live_end_member_content);
        this.n = this.p.findViewById(R.id.live_end_page_member_info_layout);
        this.o = new d(this);
        this.p.findViewById(R.id.live_end_page_member_btn_cancel).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                if (aVar != null) {
                    aVar.a(7, null);
                }
            }
        });
    }

    private void f() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(n.a().e().d());
        this.b.registerReceiver(this.r, intentFilter);
    }

    public void b() {
        super.b();
        this.c.addView(this.p);
        this.o.a((com.liveshow.a.d.b) this);
        c.a(this.b).a(getAvatarUrl(), this.d, com.qq.reader.common.imageloader.a.a().o());
        this.j.setText(getName());
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        this.o.a(getRoomId());
        f();
    }

    public void c() {
        this.o.b();
        this.b.unregisterReceiver(this.r);
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.bookinfo_add2bookshelf_already);
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.text_color_c103));
            textView.setBackgroundResource(R.drawable.shap_hollow_disable_white_button);
            textView.setEnabled(false);
            return;
        }
        textView.setTextColor(textView.getContext().getResources().getColorStateList(R.color.button_b0_text_color_selector));
        textView.setText(R.string.bookinfo_add2bookshelf_ok);
        textView.setBackgroundResource(R.drawable.selector_round_hollow_white_button);
        textView.setEnabled(true);
    }

    private void a(final e eVar) {
        if (eVar != null) {
            this.k.removeAllViews();
            this.q.clear();
            View inflate = this.b.getLayoutInflater().inflate(R.layout.live_show_member_end_page_book_single_item, this.k, false);
            TextView textView = (TextView) inflate.findViewById(R.id.member_live_end_books_recommendation_name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.member_live_end_books_recommendation_des);
            TextView textView3 = (TextView) inflate.findViewById(R.id.member_live_end_books_recommendation_add_book_shelf);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.member_live_end_books_recommendation_cover);
            View findViewById = inflate.findViewById(R.id.member_live_end_books_recommendation_cover_container);
            textView.setText(eVar.c);
            c.a(this.b).a(eVar.b, imageView, com.qq.reader.common.imageloader.a.a().j());
            textView2.setText(eVar.d);
            textView3.setVisibility(0);
            a(textView3, eVar.e);
            textView3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.o.a(eVar.a);
                    i.a("event_Z38", null, view.getContext());
                }
            });
            OnClickListener anonymousClass4 = new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.o.b(eVar.a);
                    i.a("event_Z42", null, view.getContext());
                }
            };
            findViewById.setOnClickListener(anonymousClass4);
            textView.setOnClickListener(anonymousClass4);
            textView2.setOnClickListener(anonymousClass4);
            inflate.setTag(Long.valueOf(eVar.a));
            this.k.addView(inflate);
            this.q.add(inflate);
        }
    }

    private void a(List<e> list) {
        if (list != null && list.size() != 0) {
            this.k.removeAllViews();
            this.q.clear();
            for (int i = 0; i < list.size(); i++) {
                final e eVar = (e) list.get(i);
                View inflate = this.b.getLayoutInflater().inflate(R.layout.live_show_member_end_page_book_item, this.k, false);
                ((LayoutParams) inflate.getLayoutParams()).weight = 1.0f;
                TextView textView = (TextView) inflate.findViewById(R.id.member_live_end_books_recommendation_name);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.member_live_end_books_recommendation_cover);
                View findViewById = inflate.findViewById(R.id.member_live_end_books_recommendation_cover_container);
                TextView textView2 = (TextView) inflate.findViewById(R.id.member_live_end_books_recommendation_add_book_shelf);
                textView.setText(eVar.c);
                c.a(this.b).a(eVar.b, imageView, com.qq.reader.common.imageloader.a.a().j());
                textView2.setVisibility(0);
                a(textView2, eVar.e);
                textView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        this.b.o.a(eVar.a);
                        i.a("event_Z38", null, view.getContext());
                    }
                });
                findViewById.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        this.b.o.b(eVar.a);
                        i.a("event_Z42", null, view.getContext());
                    }
                });
                inflate.setTag(Long.valueOf(eVar.a));
                this.k.addView(inflate);
                this.q.add(inflate);
            }
            int size = 3 - list.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    View view = new View(this.k.getContext());
                    ViewGroup.LayoutParams layoutParams = new LayoutParams(0, 0);
                    layoutParams.weight = 1.0f;
                    view.setLayoutParams(layoutParams);
                    this.k.addView(view);
                }
            }
            this.k.post(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.g();
                }
            });
        }
    }

    private void g() {
        int i = 0;
        for (View findViewById : this.q) {
            int i2;
            TextView textView = (TextView) findViewById.findViewById(R.id.member_live_end_books_recommendation_name);
            if (textView == null || textView.getLineCount() <= i) {
                i2 = i;
            } else {
                i2 = textView.getLineCount();
            }
            i = i2;
        }
        for (View findViewById2 : this.q) {
            textView = (TextView) findViewById2.findViewById(R.id.member_live_end_books_recommendation_name);
            if (textView != null) {
                textView.setLines(i);
            }
        }
    }

    public void a(com.liveshow.model.d dVar) {
        if (dVar != null) {
            c.a(this.b).a(dVar.a, this.d, com.qq.reader.common.imageloader.a.a().o());
            this.j.setText(dVar.b);
            this.n.setVisibility(0);
            b(dVar.g);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.o.c();
                    i.a("event_Z41", null, view.getContext());
                }
            });
            if (!dVar.g) {
                this.i.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.o.a();
                        i.a("event_Z37", null, view.getContext());
                    }
                });
            }
            this.f.setText(dVar.d);
            if (dVar.e != null) {
                this.g.setText(dVar.e.b + dVar.e.a);
            }
            if (dVar.f != null) {
                this.h.setText(dVar.f.b + dVar.f.a);
            }
            if (dVar.h == null || dVar.h.size() == 0) {
                this.l.setVisibility(8);
                this.k.setVisibility(8);
            } else if (dVar.h.size() == 1) {
                this.l.setVisibility(0);
                this.k.setVisibility(0);
                a((e) dVar.h.get(0));
            } else {
                this.l.setVisibility(0);
                this.k.setVisibility(0);
                a(dVar.h);
            }
        }
    }

    public void a(boolean z, long j) {
        if (j > 0 && z) {
            for (View view : this.q) {
                if (view.getTag() != null && Long.valueOf(j).equals(view.getTag())) {
                    a((TextView) view.findViewById(R.id.member_live_end_books_recommendation_add_book_shelf), true);
                }
            }
        }
    }

    public void a(boolean z) {
        b(z);
    }

    public Activity a() {
        return this.b;
    }

    private void b(boolean z) {
        if (z) {
            this.i.setEnabled(false);
            this.i.setText(R.string.followed);
            this.i.setBackgroundResource(R.drawable.shap_disable_round_white_button);
            return;
        }
        this.i.setText(R.string.follow);
        this.i.setBackgroundResource(R.drawable.selector_round_blue_button);
        this.i.setEnabled(true);
    }
}
