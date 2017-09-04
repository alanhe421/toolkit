package com.qq.reader.view;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.framework.mark.Mark;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: ReadPageTopDialog */
public class z extends BaseDialog implements OnClickListener, l {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private TextView i;
    private View j;
    private View k;
    private ImageView l;
    private ImageView m;
    private a n;
    private boolean o = false;

    /* compiled from: ReadPageTopDialog */
    public interface a {
        void a(int i, View view);
    }

    public z(Activity activity, int i, boolean z, Mark mark) {
        a(activity, null, R.layout.readpage_topbar, 6, true);
        this.b = (ImageView) this.f.findViewById(R.id.readpage_back);
        this.b.setOnClickListener(this);
        this.a = (ImageView) this.f.findViewById(R.id.readpage_more);
        this.a.setOnClickListener(this);
        this.c = (ImageView) this.f.findViewById(R.id.readpage_download);
        this.c.setOnClickListener(this);
        this.d = (ImageView) this.f.findViewById(R.id.readpage_vote);
        this.d.setOnClickListener(this);
        this.e = (ImageView) this.f.findViewById(R.id.readpage_goPlayer);
        this.e.setOnClickListener(this);
        this.e.setVisibility(0);
        i.a("event_B264", null, ReaderApplication.getApplicationImp());
        this.l = (ImageView) this.f.findViewById(R.id.readpage_bookmark);
        this.m = (ImageView) this.f.findViewById(R.id.readpage_shared);
        this.j = this.f.findViewById(R.id.shadow);
        this.k = this.f.findViewById(R.id.audio_book_red_dot);
        if (d.cm(e())) {
            this.k.setVisibility(4);
        } else {
            this.k.setVisibility(0);
        }
        this.i = (TextView) this.f.findViewById(R.id.tv_download_voucher_tips);
        if (!z) {
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        } else if (i == 0) {
            String str = "";
            if (mark != null) {
                str = mark.getId().toLowerCase();
            }
            if (str.endsWith(".trial")) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            if (mark == null || mark.getBookId() <= 0) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
            }
        } else if (i == 1) {
            this.c.setVisibility(0);
            this.d.setVisibility(0);
        } else if (i == Constants.ERRORCODE_UNKNOWN) {
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        } else if (i == 10001) {
            this.l.setVisibility(0);
            this.m.setVisibility(0);
            this.l.setOnClickListener(this);
            this.m.setOnClickListener(this);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            this.a.setVisibility(8);
        }
        if (VERSION.SDK_INT >= 21 && com.qq.reader.common.c.a.C) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f.findViewById(R.id.top_menu);
            if (relativeLayout != null) {
                relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                relativeLayout.setLayoutParams(new LayoutParams(-1, com.qq.reader.common.c.a.ca + ((int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.bookstore_titlerbar_height))));
            }
            this.f.getWindow().addFlags(512);
            this.f.getWindow().getDecorView().setSystemUiVisibility(1284);
        }
        f(d.n);
    }

    public void a(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.readpage_back:
                dismiss();
                if (this.n != null) {
                    this.n.a(1000, view);
                    return;
                }
                return;
            case R.id.readpage_more:
                if (this.n != null) {
                    this.n.a(1003, view);
                    return;
                }
                return;
            case R.id.readpage_vote:
                if (this.n != null) {
                    this.n.a(1008, view);
                    return;
                }
                return;
            case R.id.readpage_download:
                if (this.n != null) {
                    this.n.a(1004, view);
                    return;
                }
                return;
            case R.id.readpage_goPlayer:
                if (this.n != null) {
                    i.a("event_B265", null, ReaderApplication.getApplicationImp());
                    this.n.a(1009, view);
                }
                if (this.k.getVisibility() == 0) {
                    this.k.setVisibility(4);
                    d.cn(e());
                    return;
                }
                return;
            case R.id.readpage_shared:
                if (this.n != null) {
                    this.n.a(1007, view);
                    return;
                }
                return;
            case R.id.readpage_bookmark:
                if (this.n != null) {
                    this.n.a(1006, view);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void f_() {
        if (this.o || d.n) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
        }
        this.f.show();
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void f(boolean z) {
        if (z) {
            this.f.findViewById(R.id.shadow).setVisibility(8);
            this.f.findViewById(R.id.topbar_bg).setBackgroundResource(R.color.commonsetting_bg_color_night);
            this.b.setImageResource(R.drawable.readpage_topbar_back_night);
            this.a.setImageResource(R.drawable.readpage_topbar_more_night);
            this.d.setImageResource(R.drawable.menu_icon_vote_night);
            this.c.setImageResource(R.drawable.readerpage_download_icon_selector_night);
            this.e.setImageResource(R.drawable.menu_icon_goplayer_night);
            this.l.setImageResource(R.drawable.readerpage_bookmark_icon_selector_night);
            return;
        }
        this.f.findViewById(R.id.shadow).setVisibility(0);
        this.f.findViewById(R.id.topbar_bg).setBackgroundResource(R.color.commonsetting_bg_color);
        this.b.setImageResource(R.drawable.readpage_topbar_back);
        this.a.setImageResource(R.drawable.readpage_topbar_more);
        this.d.setImageResource(R.drawable.menu_icon_vote);
        this.c.setImageResource(R.drawable.readerpage_download_icon_selector);
        this.e.setImageResource(R.drawable.menu_icon_goplayer);
        this.l.setImageResource(R.drawable.readerpage_bookmark_icon_selector);
    }

    public void dismiss(int i) {
        dismiss();
    }

    public k getHighLightArea(int i) {
        return null;
    }

    public void g(boolean z) {
        this.o = z;
    }

    public void b(int i) {
        this.i.setVisibility(i);
    }
}
