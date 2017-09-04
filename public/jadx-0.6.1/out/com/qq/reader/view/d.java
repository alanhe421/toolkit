package com.qq.reader.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.linearmenu.a;
import com.tencent.feedback.proguard.R;

/* compiled from: CloudListBottomMenu */
public class d extends a {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    ImageView e;

    public d(Activity activity) {
        super(activity);
        View inflate = LayoutInflater.from(this.k).inflate(R.layout.cloudlist_bottommenu_headerview, null);
        this.i.addHeaderView(inflate);
        h();
        this.a = (TextView) inflate.findViewById(R.id.book_name);
        this.b = (TextView) inflate.findViewById(R.id.book_author);
        this.c = (TextView) inflate.findViewById(R.id.book_process);
        this.d = (TextView) inflate.findViewById(R.id.book_status);
        this.e = (ImageView) inflate.findViewById(R.id.cloudlist_bottommenu_view_cover);
    }

    public void a(String str) {
        CharSequence k = ao.k(str);
        if (this.a != null) {
            this.a.setText(k);
        }
    }

    public void b(String str) {
        if (str == null || str.trim().length() <= 0) {
            this.b.setVisibility(8);
            return;
        }
        this.b.setVisibility(0);
        this.b.setText("作    者：" + str);
    }

    public void c(String str) {
        this.c.setText("阅读进度：" + str);
    }

    public void d(String str) {
        this.d.setText("状    态：" + str);
    }

    public ImageView g() {
        return this.e;
    }
}
