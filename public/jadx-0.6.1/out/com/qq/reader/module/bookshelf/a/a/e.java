package com.qq.reader.module.bookshelf.a.a;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: CategoryIndexListItem */
public class e {
    public ImageView a;
    public TextView b;
    public TextView c;

    public e(View view) {
        this.a = (ImageView) view.findViewById(R.id.categrory_index_icon);
        this.b = (TextView) view.findViewById(R.id.categrory_index_name);
        this.c = (TextView) view.findViewById(R.id.categrory_index_count);
    }

    public void a(int i) {
        this.a.setImageResource(i);
    }

    public void a(String str) {
        this.b.setText(str);
    }

    public void b(int i) {
        this.c.setText("共" + i + "本图书");
    }
}
