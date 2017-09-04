package com.qq.reader.view.linearmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.tencent.feedback.proguard.R;

/* compiled from: LinearMenuOfOneBook */
public class d extends a {
    View a = LayoutInflater.from(this.k).inflate(R.layout.bookstand_linear_menu_header_view, null);
    TextView b = ((TextView) this.a.findViewById(R.id.book_detailinfo));
    ImageView c;
    ImageView d;
    TextView e;
    TextView m;
    TextView n;
    TextView o;
    TextView p;
    TextView q;
    TextView r;
    private int s;

    public d(Activity activity) {
        super(activity);
        this.i.addHeaderView(this.a);
        h();
        this.c = (ImageView) this.a.findViewById(R.id.book_cover);
        this.e = (TextView) this.a.findViewById(R.id.book_name);
        this.o = (TextView) this.a.findViewById(R.id.cover_name);
        this.m = (TextView) this.a.findViewById(R.id.book_author);
        this.n = (TextView) this.a.findViewById(R.id.book_progress);
        this.p = (TextView) this.a.findViewById(R.id.book_lastoperator_time);
        this.q = (TextView) this.a.findViewById(R.id.book_type_name);
        this.r = (TextView) this.a.findViewById(R.id.book_cp_info);
        this.d = (ImageView) this.a.findViewById(R.id.bookshelf_mark_type_tag);
    }

    public void a(String str, final String str2) {
        c.a(e()).a(str, this.c, a.a().j(), new e<String, b>(this) {
            final /* synthetic */ d b;

            public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                this.b.f(str2);
                return false;
            }

            public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                return false;
            }
        });
    }

    public void a(boolean z, String str) {
        if (z) {
            this.o.setVisibility(0);
            this.o.setText(str);
            return;
        }
        this.o.setVisibility(8);
    }

    public void c(int i) {
        this.s = i;
        if (i == 9) {
            this.d.setImageResource(R.drawable.comic_book_icon);
            this.d.setVisibility(0);
        } else if (i == 8) {
            this.d.setImageResource(R.drawable.listen_book_icon);
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    private void f(String str) {
        String str2 = null;
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf != -1) {
                str2 = str.substring(lastIndexOf + 1, str.length());
            }
        }
        if (str2 == null || str2.length() <= 0) {
            this.q.setVisibility(0);
            this.q.setText(ReaderApplication.getApplicationImp().getString(R.string.app_name));
            this.q.setTextSize(1, 6.0f);
        } else {
            this.q.setText(str2.toUpperCase());
            this.q.setVisibility(0);
            this.q.setTextSize(1, 11.0f);
        }
        this.c.setImageResource(R.drawable.book_default_cover);
    }

    public void a(String str) {
        this.e.setText(str);
    }

    public void b(String str) {
        String string;
        if (this.s == 8) {
            string = this.k.getResources().getString(R.string.ting_book_last_operator_time);
        } else {
            string = this.k.getResources().getString(R.string.book_last_operator_time);
        }
        this.p.setText(string + str);
    }

    public void c(String str) {
        if (str.length() == 0) {
            str = "匿名";
        }
        this.m.setText("作者：" + str);
    }

    public void d(String str) {
        String str2;
        if (this.s == 8) {
            str2 = "播放进度：";
        } else {
            str2 = "阅读进度：";
        }
        this.n.setText(str2 + str);
    }

    public void e(String str) {
        String str2 = "图书来源：";
        if (this.s == 9) {
            str2 = "漫画来源：";
        }
        if (str == null || str.length() == 0) {
            this.r.setText(str2 + ReaderApplication.getApplicationImp().getString(R.string.app_name));
        } else {
            this.r.setText(str2 + str);
        }
        this.r.setVisibility(0);
    }

    public void a(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener);
    }

    public void a(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }
}
