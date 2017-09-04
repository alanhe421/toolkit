package com.qq.reader.module.bookshelf.view;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.b;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.QRImageView;
import com.qq.reader.view.RoundProgressBar;
import com.tencent.feedback.proguard.R;

/* compiled from: BookShelfListItem */
public class a {
    public ImageView a;
    public QRImageView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public TextView f;
    public TextView g;
    public TextView h;
    public ImageView i;
    public ImageView j;
    public View k;
    public RoundProgressBar l;
    public TextView m;
    private Context n;
    private ImageView o;
    private int p = -1;
    private ViewStub q;
    private View r;
    private ImageView s;
    private boolean t = false;
    private int u = 1;

    public a(View view, Context context) {
        this.n = context;
        this.a = (ImageView) view.findViewById(R.id.bookshelf_layout_bg);
        this.b = (QRImageView) view.findViewById(R.id.bookshelf_bookcover);
        this.c = (TextView) view.findViewById(R.id.bookshelf_bookname);
        this.d = (TextView) view.findViewById(R.id.bookshelf_chaptername);
        this.e = (TextView) view.findViewById(R.id.bookshelf_update);
        this.f = (TextView) view.findViewById(R.id.bookshelf_operate);
        this.g = (TextView) view.findViewById(R.id.bookshelf_operate_time);
        this.h = (TextView) view.findViewById(R.id.bookshelf_book_type);
        this.q = (ViewStub) view.findViewById(R.id.download_status_bg_stub);
        this.r = view.findViewById(R.id.bookshelf_booktrail_tag);
        this.j = (ImageView) view.findViewById(R.id.bookshelf_update_new);
        this.s = (ImageView) view.findViewById(R.id.bookshelf_mark_type_tag);
        this.o = (ImageView) view.findViewById(R.id.bookshelf_private_tag);
    }

    public void a(boolean z) {
        this.t = z;
    }

    public boolean a() {
        return this.t;
    }

    public void b() {
        this.h.setVisibility(8);
    }

    public void a(String str) {
        this.p = str.hashCode();
    }

    public void b(String str) {
        if (str == null || !com.qq.reader.readengine.model.a.k(str)) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
        }
    }

    public void a(int i) {
        if (i == 9) {
            this.s.setImageResource(R.drawable.comic_book_icon);
            this.s.setVisibility(0);
        } else if (i == 8) {
            this.s.setImageResource(R.drawable.listen_book_icon);
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
        }
    }

    public void b(int i) {
        if (i == 0) {
            this.o.setVisibility(0);
        } else {
            this.o.setVisibility(8);
        }
    }

    public void b(boolean z) {
        if (z) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.g.setVisibility(8);
        }
    }

    public void c(String str) {
        String str2 = null;
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf != -1) {
                str2 = str.substring(lastIndexOf + 1, str.length());
            }
        }
        if (str2 == null || str2.length() <= 0) {
            this.h.setVisibility(0);
            this.h.setText(ReaderApplication.getApplicationImp().getString(R.string.app_name));
            this.h.setTextSize(1, 6.0f);
            this.b.setImageResource(R.drawable.book_default_cover);
            return;
        }
        this.h.setText(str2.toUpperCase());
        this.h.setVisibility(0);
        this.h.setTextSize(1, 11.0f);
        this.b.setImageResource(R.drawable.book_default_cover);
    }

    public void d(String str) {
        try {
            this.c.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void e(String str) {
        try {
            this.d.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(int i) {
        this.d.setTextColor(this.n.getResources().getColor(i));
    }

    public void c(boolean z) {
        int i = 8;
        int i2 = 0;
        if (b.b == 1) {
            ImageView imageView = this.j;
            if (!z) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
            return;
        }
        int i3;
        TextView textView = this.e;
        if (z) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        textView.setVisibility(i3);
        TextView textView2 = this.f;
        if (!z) {
            i = 0;
        }
        textView2.setVisibility(i);
    }

    public void d(boolean z) {
        CharSequence charSequence = "读过";
        if (this.u == 8) {
            charSequence = "听过";
        }
        TextView textView = this.f;
        if (!z) {
            charSequence = "加入";
        }
        textView.setText(charSequence);
    }

    public void a(long j, boolean z) {
        if (b.b != 1) {
            this.g.setVisibility(0);
            this.g.setText(ao.j(j / 1000));
        } else if (z) {
            this.d.setText(ao.j(j / 1000) + (":" + this.d.getText()));
        }
    }

    public void a(boolean z, int i, boolean z2) {
        if (!z || i < 0 || i > 100 || z2) {
            this.l.setVisibility(8);
            return;
        }
        this.l.setProgress(i);
        this.l.setVisibility(0);
        this.m.setText(i + "%");
    }

    public void a(TaskStateEnum taskStateEnum, boolean z, boolean z2) {
        if (z2) {
            this.i.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
        } else if (taskStateEnum == TaskStateEnum.Paused && !z) {
            this.i.setVisibility(0);
            this.k.setVisibility(0);
            this.i.setBackgroundResource(R.drawable.bookcase_book_pause_nor);
            this.l.setVisibility(0);
            this.l.setProgress(0);
            this.m.setText("已暂停");
        } else if (taskStateEnum == TaskStateEnum.Failed) {
            this.i.setVisibility(0);
            this.k.setVisibility(0);
            this.i.setBackgroundResource(R.drawable.bookcase_book_fail_nor);
            this.l.setProgress(0);
            this.m.setText("下载失败");
        } else if (taskStateEnum == TaskStateEnum.Started || taskStateEnum == TaskStateEnum.DeactiveStarted) {
            this.i.setVisibility(8);
            this.k.setVisibility(0);
        } else {
            this.i.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
        }
    }

    public View c() {
        if (this.k == null) {
            this.k = this.q.inflate();
            this.i = (ImageView) this.k.findViewById(R.id.download_status);
            this.k = this.k.findViewById(R.id.download_status_bg);
            this.l = (RoundProgressBar) this.k.findViewById(R.id.download_progressbar);
            this.m = (TextView) this.k.findViewById(R.id.download_progress_text);
        }
        return this.k;
    }

    public void d(int i) {
        if (i == 0) {
            c();
        } else if (this.k != null) {
            this.k.setVisibility(8);
        }
    }

    public void e(int i) {
        this.u = i;
    }
}
