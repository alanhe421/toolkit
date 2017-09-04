package com.qq.reader.widget.swipBackView;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

/* compiled from: SwipeBackActivityHelper */
public class a {
    private Activity a;
    private WeakReference<Activity> b;
    private SwipeBackLayout c;
    private boolean d;

    public a(WeakReference<Activity> weakReference, Activity activity) {
        this.a = activity;
        this.b = weakReference;
    }

    public void a() {
        this.a.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.a.getWindow().getDecorView().setBackgroundDrawable(null);
        this.c = (SwipeBackLayout) LayoutInflater.from(this.a).inflate(R.layout.swipeback_layout, null);
        this.c.a(new 1(this));
        this.c.setparentActivity(this.b);
    }

    public Activity b() {
        if (this.b != null) {
            return (Activity) this.b.get();
        }
        return null;
    }

    public void c() {
        this.c.a(this.a);
    }

    public View a(int i) {
        if (this.c != null) {
            return this.c.findViewById(i);
        }
        return null;
    }

    public SwipeBackLayout d() {
        return this.c;
    }
}
