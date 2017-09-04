package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.j;

public class LineControllerView extends LinearLayout {
    private String a;
    private boolean b;
    private String c;
    private boolean d;
    private boolean e;

    public LineControllerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(g.view_line_controller, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.LineControllerView, 0, 0);
        try {
            this.a = obtainStyledAttributes.getString(j.LineControllerView_name);
            this.c = obtainStyledAttributes.getString(j.LineControllerView_lcontent);
            this.b = obtainStyledAttributes.getBoolean(j.LineControllerView_isBottom, false);
            this.d = obtainStyledAttributes.getBoolean(j.LineControllerView_canNav, false);
            this.e = obtainStyledAttributes.getBoolean(j.LineControllerView_isSwitch, false);
            a();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void a() {
        int i;
        int i2 = 0;
        ((TextView) findViewById(e.name)).setText(this.a);
        ((TextView) findViewById(e.content)).setText(a(this.c));
        findViewById(e.bottomLine).setVisibility(this.b ? 0 : 8);
        ImageView imageView = (ImageView) findViewById(e.rightArrow);
        if (this.d) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        LinearLayout linearLayout = (LinearLayout) findViewById(e.contentText);
        if (this.e) {
            i = 8;
        } else {
            i = 0;
        }
        linearLayout.setVisibility(i);
        Switch switchR = (Switch) findViewById(e.btnSwitch);
        if (!this.e) {
            i2 = 8;
        }
        switchR.setVisibility(i2);
    }

    public void setContent(String str) {
        this.c = str;
        ((TextView) findViewById(e.content)).setText(a(str));
    }

    public String getContent() {
        return ((TextView) findViewById(e.content)).getText().toString();
    }

    public void setCanNav(boolean z) {
        this.d = z;
        ((ImageView) findViewById(e.rightArrow)).setVisibility(z ? 0 : 8);
    }

    private String a(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() > 23) {
            return str.substring(0, 23) + "...";
        }
        return str;
    }
}
