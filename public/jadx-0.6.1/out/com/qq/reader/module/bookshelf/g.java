package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.view.ProgressBar;
import com.tencent.feedback.proguard.R;

/* compiled from: BookShelfOnlineBookListItem */
public class g {
    public ImageView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public TextView f;
    public TextView g;
    public ImageView h;
    public FrameLayout i;
    public ProgressBar j;
    public TextView k;
    private TextView l;
    private ImageView m;
    private Context n;

    public g(View view, Context context) {
        this.n = context;
        this.b = (TextView) view.findViewById(R.id.bookshelf_cloud_book_title);
        this.c = (TextView) view.findViewById(R.id.bookshelf_cloud_book_info);
        this.a = (ImageView) view.findViewById(R.id.bookshelf_cloud_cover);
        this.d = (TextView) view.findViewById(R.id.bookshelf_cloud_book_update_status);
        this.e = (TextView) view.findViewById(R.id.bookshelf_cloud_new_add);
        this.f = (TextView) view.findViewById(R.id.bookshelf_cloud_recentread);
        this.g = (TextView) view.findViewById(R.id.bookshelf_cloud_update);
        this.h = (ImageView) view.findViewById(R.id.download_status);
        this.m = (ImageView) view.findViewById(R.id.download_status_bg);
        this.i = (FrameLayout) view.findViewById(R.id.download_layout);
        this.j = (ProgressBar) view.findViewById(R.id.download_progress);
        this.l = (TextView) view.findViewById(R.id.bookshelf_format);
    }

    public void a(String str) {
        String str2 = null;
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf != -1) {
                str2 = str.substring(lastIndexOf + 1, str.length());
            }
        }
        if (str2 == null || str2.length() <= 0) {
            this.k.setVisibility(8);
            this.a.setImageResource(R.color.localstore_img_loading);
            return;
        }
        this.k.setText(str2.toLowerCase());
        this.k.setVisibility(0);
        this.a.setImageResource(R.drawable.book_default);
    }

    public void b(String str) {
        this.b.setText(str);
    }

    public void c(String str) {
        if (str == null || str.length() <= 0) {
            this.c.setText(" ");
        } else {
            this.c.setText(str);
        }
    }

    public void d(String str) {
        if (str == null || str.length() <= 0) {
            this.d.setText(" ");
        } else {
            this.d.setText(str);
        }
    }

    public void a(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void b(boolean z) {
        this.g.setVisibility(z ? 0 : 8);
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = this.n.getResources().getDimensionPixelOffset(R.dimen.common_dp_30);
        } else if (this.e.getVisibility() != 0) {
            layoutParams.leftMargin = 0;
        }
        this.b.setLayoutParams(layoutParams);
    }

    public void c(boolean z) {
        this.e.setVisibility(z ? 8 : 0);
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        if (z) {
            layoutParams.leftMargin = 0;
        } else {
            layoutParams.leftMargin = this.n.getResources().getDimensionPixelOffset(R.dimen.common_dp_30);
        }
        this.b.setLayoutParams(layoutParams);
    }

    public void a(boolean z, int i, boolean z2) {
        if (!z || i < 0 || i > 100 || z2) {
            this.i.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.j.setProgress((double) i);
    }

    public void a(TaskStateEnum taskStateEnum, boolean z, boolean z2) {
        if (taskStateEnum == TaskStateEnum.Paused && !z) {
            this.h.setVisibility(0);
            this.m.setVisibility(0);
            this.h.setBackgroundResource(R.drawable.bookcase_book_pause_nor);
        } else if (taskStateEnum == TaskStateEnum.Failed) {
            this.h.setVisibility(0);
            this.m.setVisibility(0);
            this.h.setBackgroundResource(R.drawable.bookcase_book_fail_nor);
        } else if (taskStateEnum == TaskStateEnum.Started || taskStateEnum == TaskStateEnum.DeactiveStarted) {
            this.h.setVisibility(0);
            this.m.setVisibility(0);
        } else {
            this.h.setVisibility(8);
            this.m.setVisibility(8);
        }
    }
}
