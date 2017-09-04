package com.liveshow.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.liveshow.a.a.a;
import com.liveshow.a.a.b;
import com.liveshow.model.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.views.customviews.BaseAuthorDetailDialog;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class AuthorDetailDialog extends BaseAuthorDetailDialog implements b, c {
    private BroadcastReceiver A = new BroadcastReceiver(this) {
        final /* synthetic */ AuthorDetailDialog a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(n.a().e().d())) {
                this.a.x.a(this.a.getRoomId(), this.a.getAuthorId(), this.a.getUserId());
                this.a.x.c();
            }
        }
    };
    private View b;
    private TextView c;
    private View d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private View q;
    private LinearLayout r;
    private View s;
    private View t;
    private View u;
    private View v;
    private List<View> w = new ArrayList();
    private a x;
    private Activity y;
    private boolean z;

    private void j() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(n.a().e().d());
        this.y.registerReceiver(this.A, intentFilter);
    }

    public AuthorDetailDialog(Activity activity, int i) {
        super(activity, i);
        setContentView(R.layout.live_show_host_detail_dialog);
        this.b = findViewById(R.id.host_detail_dialog_report_tv);
        this.c = (TextView) findViewById(R.id.host_detail_dialog_shut_up);
        this.d = findViewById(R.id.host_detail_dialog_kick_out);
        this.e = (TextView) findViewById(R.id.host_detail_dialog_follow_tv);
        this.f = (ImageView) findViewById(R.id.host_detail_dialog_author_avatar);
        this.g = (ImageView) findViewById(R.id.host_detail_dialog_author_level);
        this.h = (TextView) findViewById(R.id.host_detail_dialog_name);
        this.i = (TextView) findViewById(R.id.host_detail_dialog_sign);
        this.j = (TextView) findViewById(R.id.host_detail_dialog_words_count);
        this.k = (TextView) findViewById(R.id.host_detail_dialog_book_category);
        this.l = (TextView) findViewById(R.id.host_detail_dialog_fans_count);
        this.r = (LinearLayout) findViewById(R.id.host_detail_dialog_books_recommendation);
        this.q = findViewById(R.id.host_detail_dialog_books_recommendation_divider);
        this.m = findViewById(R.id.host_detail_dialog_fans_divider);
        this.p = findViewById(R.id.host_detail_dialog_fans_layout);
        this.o = findViewById(R.id.host_detail_dialog_category_divider);
        this.n = findViewById(R.id.host_detail_dialog_category_layout);
        this.s = findViewById(R.id.host_info_creation_info_layout);
        this.t = findViewById(R.id.host_detail_dialog_content_container);
        this.u = findViewById(R.id.live_show_host_detail_action_container);
        this.v = findViewById(R.id.live_show_host_detail_loading);
        this.x = new com.liveshow.c.a(this);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AuthorDetailDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.x.a(this.a.getAuthorId());
                i.a("event_Z35", null, view.getContext());
            }
        });
        l();
        this.y = activity;
    }

    private void k() {
        this.h.setText(getName());
        c.a(getContext()).a(getAvatarUrl(), this.f, com.qq.reader.common.imageloader.a.a().o());
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.v.setVisibility(0);
        this.t.setVisibility(4);
        this.u.setVisibility(4);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
    }

    protected void onStart() {
        super.onStart();
        this.x.a((b) this);
        k();
        j();
        this.x.a(getRoomId(), getAuthorId(), getUserId());
    }

    protected void onStop() {
        super.onStop();
        this.x.b();
        this.x.a();
        this.y.unregisterReceiver(this.A);
    }

    public void show() {
        if (!this.y.isFinishing()) {
            try {
                super.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean d() {
        return this.z;
    }

    private void e(boolean z) {
        this.e.setVisibility(0);
        if (z) {
            this.e.setText(R.string.followed);
            this.e.setBackgroundResource(R.drawable.shap_disable_round_white_button);
            this.e.setEnabled(false);
            return;
        }
        this.e.setBackgroundResource(R.drawable.selector_round_blue_button);
        this.e.setText(R.string.follow);
        this.e.setEnabled(true);
    }

    public void c(boolean z) {
        if (z) {
            this.b.setVisibility(0);
            this.e.setVisibility(0);
            return;
        }
        this.b.setVisibility(8);
        this.e.setVisibility(8);
    }

    private void l() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        window.setGravity(80);
        attributes.width = defaultDisplay.getWidth();
        getWindow().setAttributes(attributes);
    }

    private void a(final e eVar) {
        if (eVar != null) {
            this.r.removeAllViews();
            this.w.clear();
            View inflate = getLayoutInflater().inflate(R.layout.live_show_host_detail_book_single_item, this.r, false);
            TextView textView = (TextView) inflate.findViewById(R.id.host_info_books_recommendation_des);
            TextView textView2 = (TextView) inflate.findViewById(R.id.host_info_books_recommendation_add_book_shelf);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.host_info_books_recommendation_cover);
            ((TextView) inflate.findViewById(R.id.host_info_books_recommendation_name)).setText(eVar.c);
            c.a(getContext()).a(eVar.b, imageView, com.qq.reader.common.imageloader.a.a().j());
            textView.setText(eVar.d);
            if (getUserId().equals(com.qq.reader.liveshow.model.c.a().b())) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                a(textView2, eVar.e);
                textView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AuthorDetailDialog b;

                    public void onClick(View view) {
                        this.b.x.b(eVar.a);
                        i.a("event_Z36", null, view.getContext());
                    }
                });
            }
            inflate.setTag(Long.valueOf(eVar.a));
            this.r.addView(inflate);
            this.w.add(inflate);
        }
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.bookinfo_add2bookshelf_already);
            textView.setBackgroundResource(R.drawable.shap_hollow_disable_white_button);
            textView.setTextColor(textView.getResources().getColor(R.color.text_color_c103));
            textView.setEnabled(false);
            return;
        }
        textView.setBackgroundResource(R.drawable.selector_round_hollow_blue_button);
        textView.setTextColor(textView.getResources().getColorStateList(R.drawable.selector_hollow_blue_text));
        textView.setEnabled(true);
        textView.setText(R.string.bookinfo_add2bookshelf_ok);
    }

    private void a(List<e> list) {
        if (list != null || list.size() > 0) {
            this.r.removeAllViews();
            this.w.clear();
            for (int i = 0; i < list.size(); i++) {
                final e eVar = (e) list.get(i);
                View inflate = getLayoutInflater().inflate(R.layout.live_show_host_detail_book_item, this.r, false);
                ((LinearLayout.LayoutParams) inflate.getLayoutParams()).weight = 1.0f;
                ImageView imageView = (ImageView) inflate.findViewById(R.id.host_info_books_recommendation_cover);
                TextView textView = (TextView) inflate.findViewById(R.id.host_info_books_recommendation_add_book_shelf);
                ((TextView) inflate.findViewById(R.id.host_info_books_recommendation_name)).setText(eVar.c);
                c.a(getContext()).a(eVar.b, imageView, com.qq.reader.common.imageloader.a.a().j());
                if (getUserId().equals(com.qq.reader.liveshow.model.c.a().b())) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    a(textView, eVar.e);
                    textView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AuthorDetailDialog b;

                        public void onClick(View view) {
                            this.b.x.b(eVar.a);
                            i.a("event_Z36", null, view.getContext());
                        }
                    });
                }
                inflate.setTag(Long.valueOf(eVar.a));
                this.r.addView(inflate);
                this.w.add(inflate);
            }
            int size = 3 - list.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    View view = new View(this.r.getContext());
                    ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
                    layoutParams.weight = 1.0f;
                    view.setLayoutParams(layoutParams);
                    this.r.addView(view);
                }
            }
            this.r.post(new Runnable(this) {
                final /* synthetic */ AuthorDetailDialog a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.m();
                }
            });
        }
    }

    private void m() {
        int i = 0;
        for (View findViewById : this.w) {
            int i2;
            TextView textView = (TextView) findViewById.findViewById(R.id.host_info_books_recommendation_name);
            if (textView == null || textView.getLineCount() <= i) {
                i2 = i;
            } else {
                i2 = textView.getLineCount();
            }
            i = i2;
        }
        for (View findViewById2 : this.w) {
            textView = (TextView) findViewById2.findViewById(R.id.host_info_books_recommendation_name);
            if (textView != null) {
                textView.setLines(i);
            }
        }
    }

    public void d(boolean z) {
        this.z = z;
        if (z) {
            this.c.setText(R.string.free_shut_up_state);
        } else {
            this.c.setText(R.string.shut_up);
        }
    }

    public void a(com.liveshow.model.a aVar) {
        this.v.setVisibility(8);
        this.t.setVisibility(0);
        this.u.setVisibility(0);
        d.a(this, aVar.g, getUserId(), getMyId(), aVar.h);
        c(!getUserId().equals(getMyId()));
        if (aVar.g < 5) {
            d(false);
        } else {
            d(true);
        }
        if (aVar.a > 0) {
            this.g.setVisibility(0);
            this.g.setImageResource(d.a(aVar.a));
        } else {
            this.g.setVisibility(8);
        }
        if (TextUtils.isEmpty(aVar.d)) {
            this.i.setVisibility(8);
        } else {
            this.i.setText(aVar.d);
            this.i.setVisibility(0);
        }
        if (aVar.e == null) {
            this.j.setText("");
        } else {
            this.j.setText(aVar.e.b + aVar.e.a);
        }
        if (TextUtils.isEmpty(aVar.b)) {
            this.o.setVisibility(8);
            this.n.setVisibility(8);
        } else {
            this.k.setText(aVar.b);
            this.o.setVisibility(0);
            this.n.setVisibility(0);
        }
        try {
            MarginLayoutParams marginLayoutParams;
            if (aVar.f == null || Float.valueOf(aVar.f.b).floatValue() == 0.0f) {
                this.m.setVisibility(8);
                this.p.setVisibility(8);
                if (aVar.i != 1) {
                    this.s.setVisibility(8);
                } else {
                    this.s.setVisibility(0);
                }
                marginLayoutParams = (MarginLayoutParams) this.s.getLayoutParams();
                if (aVar.j != null || aVar.j.size() == 0) {
                    this.q.setVisibility(8);
                    this.r.setVisibility(8);
                    marginLayoutParams.bottomMargin = ao.a(19.0f);
                } else if (aVar.j.size() == 1) {
                    a((e) aVar.j.get(0));
                    this.q.setVisibility(0);
                    this.r.setVisibility(0);
                    marginLayoutParams.bottomMargin = ao.a(13.0f);
                } else {
                    a(aVar.j);
                    this.q.setVisibility(0);
                    this.r.setVisibility(0);
                    marginLayoutParams.bottomMargin = ao.a(13.0f);
                }
                if (!getUserId().equals(getMyId())) {
                    e(aVar.c);
                }
                this.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AuthorDetailDialog a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.x.a(this.a.y, this.a.getUserId());
                        i.a("event_Z34", null, view.getContext());
                    }
                });
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AuthorDetailDialog a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.d()) {
                            this.a.x.a(this.a.y, this.a.getUserId(), 0);
                        } else {
                            this.a.x.a(this.a.y, this.a.getUserId(), -1);
                        }
                    }
                });
                this.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AuthorDetailDialog a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.x.b(this.a.y, this.a.getUserId());
                    }
                });
            }
            this.l.setText(aVar.f.b + aVar.f.a);
            this.m.setVisibility(0);
            this.p.setVisibility(0);
            if (aVar.i != 1) {
                this.s.setVisibility(0);
            } else {
                this.s.setVisibility(8);
            }
            marginLayoutParams = (MarginLayoutParams) this.s.getLayoutParams();
            if (aVar.j != null) {
            }
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            marginLayoutParams.bottomMargin = ao.a(19.0f);
            if (getUserId().equals(getMyId())) {
                e(aVar.c);
            }
            this.b.setOnClickListener(/* anonymous class already generated */);
            this.c.setOnClickListener(/* anonymous class already generated */);
            this.d.setOnClickListener(/* anonymous class already generated */);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.m.setVisibility(8);
            this.p.setVisibility(8);
        }
    }

    public void a(boolean z) {
        e(true);
    }

    public void a(long j, boolean z) {
        if (j > 0 && z) {
            for (View view : this.w) {
                if (view.getTag() != null && Long.valueOf(j).equals(view.getTag())) {
                    a((TextView) view.findViewById(R.id.host_info_books_recommendation_add_book_shelf), true);
                }
            }
        }
    }

    public void a() {
        this.v.setVisibility(8);
    }

    public void b(boolean z) {
        d(z);
    }

    public void b() {
        if (h()) {
            i();
        }
    }

    public Activity c() {
        return this.y;
    }

    public View e() {
        return this.b;
    }

    public View f() {
        return this.c;
    }

    public View g() {
        return this.d;
    }
}
