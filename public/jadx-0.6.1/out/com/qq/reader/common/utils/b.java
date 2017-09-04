package com.qq.reader.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.qq.reader.common.c.a;

/* compiled from: AndroidBug5497Workaround */
public class b {
    private static int d = 0;
    private View a;
    private int b;
    private LayoutParams c = ((LayoutParams) this.a.getLayoutParams());

    public static void a(Activity activity) {
        b bVar = new b(activity);
    }

    private b(Activity activity) {
        d = a((Context) activity);
        this.a = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.a.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                this.a.a();
            }
        });
    }

    private void a() {
        int b = b();
        if (b != this.b) {
            int height;
            if (b == this.a.getRootView().getHeight()) {
                height = this.a.getRootView().getHeight();
            } else {
                height = this.a.getRootView().getHeight() - d;
            }
            int i = height - b;
            if (i > height / 4) {
                this.c.height = height - i;
            } else {
                this.c.height = height;
            }
            this.a.requestLayout();
            this.b = b;
        }
    }

    private int b() {
        Rect rect = new Rect();
        this.a.getWindowVisibleDisplayFrame(rect);
        return (rect.bottom - rect.top) + a.ca;
    }

    public static int a(Context context) {
        return b(context) - a.bT;
    }

    public static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
