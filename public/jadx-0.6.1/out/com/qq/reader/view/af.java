package com.qq.reader.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

/* compiled from: ReaderToast */
public class af {
    View a;
    ImageView b;
    TextView c;
    private Context d;
    private Resources e;
    private LayoutInflater f;
    private Drawable g = null;
    private CharSequence h = null;
    private int i = 0;
    private Toast j;

    public af(Context context) {
        this.d = context;
        this.e = context.getResources();
        this.f = LayoutInflater.from(context);
        e(0);
    }

    public void a(Drawable drawable) {
        this.g = drawable;
    }

    public void a(int i) {
        if (-1 == i) {
            a(null);
        } else {
            a(this.e.getDrawable(i));
        }
    }

    public void a(CharSequence charSequence) {
        this.h = charSequence;
        if (this.h != null) {
            this.c.setText(this.h);
        }
    }

    public void b(int i) {
        a(this.e.getString(i));
    }

    public void c(int i) {
        this.i = i;
        this.j.setDuration(this.i);
    }

    public static int d(int i) {
        return -1;
    }

    private void e(int i) {
        this.j = new Toast(this.d.getApplicationContext());
        this.a = this.f.inflate(R.layout.reader_toast_layout, null, false);
        if (this.g != null) {
            this.b = (ImageView) this.a.findViewById(R.id.toast_icon);
            this.b.setImageDrawable(this.g);
        } else {
            this.b = (ImageView) this.a.findViewById(R.id.toast_icon);
            this.b.setVisibility(8);
        }
        this.c = (TextView) this.a.findViewById(R.id.toast_msg);
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 21 && Build.MANUFACTURER.toLowerCase().contains("meizu")) {
                this.c.setPadding(ao.a(16.0f), ao.a(14.0f), ao.a(16.0f), ao.a(5.0f));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (i == 0) {
            this.j.setGravity(1, 0, 0);
        } else {
            this.j.setGravity(49, 0, i);
        }
        this.j.setView(this.a);
    }

    public void a() {
        this.j.show();
    }

    public static af a(Context context, int i, CharSequence charSequence, int i2) {
        af afVar = new af(context);
        afVar.a(d(i));
        afVar.a(charSequence);
        afVar.c(i2);
        return afVar;
    }

    public static af a(Context context, int i, int i2, int i3) {
        af afVar = new af(context);
        afVar.a(d(i));
        afVar.b(i2);
        afVar.c(i3);
        return afVar;
    }

    public static af a(Context context, CharSequence charSequence, int i) {
        return a(context, -1, charSequence, i);
    }

    public static af a(Context context, int i, int i2) {
        return a(context, -1, i, i2);
    }

    public void b() {
        this.j.cancel();
    }
}
