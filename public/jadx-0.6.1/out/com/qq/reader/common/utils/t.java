package com.qq.reader.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.qq.reader.appconfig.a.d;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: NightModeUtil */
public class t {
    private Activity a;
    private Dialog b;
    private ImageView c = null;
    private boolean d;
    private int e = -1;
    private int f = -1;

    public t(Activity activity, boolean z) {
        this.a = activity;
        this.d = z;
        this.e = 10001;
    }

    public t(Dialog dialog, boolean z) {
        this.b = dialog;
        this.d = z;
        this.e = Constants.CODE_LOGIC_REGISTER_IN_PROCESS;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void a() {
        b(false);
    }

    public void b(boolean z) {
        if (this.c != null) {
            Window window;
            if (this.e == 10001) {
                window = this.a.getWindow();
            } else if (this.e == Constants.CODE_LOGIC_REGISTER_IN_PROCESS) {
                window = this.b.getWindow();
            } else {
                return;
            }
            if (this.c != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                if (this.f != -1) {
                    ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(this.f);
                    if (viewGroup2 != null) {
                        viewGroup = viewGroup2;
                    }
                }
                if (z) {
                    this.c.startAnimation(AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.alpha_out));
                }
                viewGroup.removeView(this.c);
                this.c = null;
            }
        }
    }

    public void c(boolean z) {
        if (this.c == null) {
            Context context;
            Window window;
            if (this.e == 10001) {
                context = this.a;
                window = this.a.getWindow();
            } else if (this.e == Constants.CODE_LOGIC_REGISTER_IN_PROCESS) {
                context = this.b.getContext();
                window = this.b.getWindow();
            } else {
                return;
            }
            try {
                if (this.c == null) {
                    this.c = new ImageView(context);
                    this.c.setId(R.id.nightmodemask);
                    this.c.setImageDrawable(new ColorDrawable(Color.parseColor("#77000000")));
                    this.c.setPadding(0, 0, 0, 0);
                    this.c.setScaleType(ScaleType.FIT_XY);
                    this.c.setLayoutParams(new LayoutParams(-1, -1));
                    ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                    if (this.f != -1) {
                        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(this.f);
                        if (viewGroup2 != null) {
                            viewGroup = viewGroup2;
                        }
                    }
                    if (z) {
                        this.c.startAnimation(AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.alpha_in));
                    }
                    viewGroup.addView(this.c, this.c.getLayoutParams());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void b() {
        d(false);
    }

    public void d(boolean z) {
        if (this.c != null) {
            if (!d.n) {
                b(z);
            }
        } else if (d.n && this.d) {
            c(z);
        }
    }

    public static void a(ImageView imageView) {
        if (imageView != null) {
            try {
                if (d.n) {
                    imageView.setColorFilter(new PorterDuffColorFilter(1996488704, Mode.SRC_ATOP));
                } else {
                    imageView.setColorFilter(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
