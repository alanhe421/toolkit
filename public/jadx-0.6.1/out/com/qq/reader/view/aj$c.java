package com.qq.reader.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: ShareDialog */
class aj$c {
    public View a;
    public ImageView b;
    public TextView c;
    final /* synthetic */ aj d;

    public aj$c(aj ajVar, View view) {
        this.d = ajVar;
        this.a = view.findViewById(R.id.icon);
        this.b = (ImageView) view.findViewById(R.id.img);
        this.c = (TextView) view.findViewById(R.id.txt);
    }

    public void a(String str) {
        this.c.setText(str);
    }

    public void a(int i) {
        this.b.setBackgroundResource(i);
    }
}
