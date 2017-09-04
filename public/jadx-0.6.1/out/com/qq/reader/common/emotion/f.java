package com.qq.reader.common.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.qq.reader.common.emotion.EmoticonLinearLayout.c;
import com.tencent.feedback.proguard.R;

/* compiled from: EmoticonPanelViewBinder */
public abstract class f extends h {
    private static g e;
    protected SparseArray<View> a;
    public int b;
    protected Context c;

    /* compiled from: EmoticonPanelViewBinder */
    public class a extends c {
        final /* synthetic */ f m;
        private int n;
        private int o;
        private int p;
        private int q;

        public a(f fVar, int i) {
            this.m = fVar;
            float f = fVar.c.getResources().getDisplayMetrics().density;
            this.o = (int) ((28.0f * f) + 0.5f);
            this.n = (int) (63.0f * f);
            this.p = (int) (f * 48.0f);
            this.q = i;
        }

        public void a(int i, RelativeLayout relativeLayout, ViewGroup viewGroup) {
            ImageView imageView = (ImageView) relativeLayout.findViewById(978670);
            imageView.setVisibility(0);
            imageView.setTag(Boolean.valueOf(false));
            d b = b(i);
            relativeLayout.setTag(b);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            float f = this.m.c.getResources().getDisplayMetrics().density;
            if (b != null) {
                if (b.a == 1) {
                    layoutParams.width = this.o;
                    layoutParams.height = this.o;
                }
                if (b.a != -1) {
                    Drawable a = b.a(this.m.c, f);
                    if (a != null) {
                        imageView.setImageDrawable(a);
                        return;
                    } else {
                        imageView.setVisibility(4);
                        return;
                    }
                } else if ("delete".equals(b.b)) {
                    layoutParams.width = this.o;
                    layoutParams.height = this.o;
                    imageView.setImageResource(R.drawable.delete_emo_icon);
                    return;
                } else if (!"setting".equals(b.b) && !"add".equals(b.b)) {
                    imageView.setImageDrawable(null);
                    relativeLayout.setBackgroundResource(R.drawable.emoicon_normal_background);
                    return;
                } else {
                    return;
                }
            }
            relativeLayout.setBackgroundResource(R.drawable.emoicon_normal_background);
            imageView.setImageDrawable(null);
            imageView.setVisibility(4);
        }
    }

    protected abstract void a(View view, int i);

    protected abstract int d(int i);

    public f(Context context, int i, int i2) {
        super(i);
        if (context == null) {
            throw new IllegalArgumentException("Context MUST NOT be null!!!");
        }
        this.c = context;
        this.b = i2;
        this.a = new SparseArray();
    }

    protected View a(int i) {
        int d = d(i);
        View view = null;
        if (e != null) {
            view = e.a(d);
        }
        if (view == null) {
            view = b(d);
        }
        if (view != null) {
            if (this.a == null) {
                this.a = new SparseArray();
            }
            this.a.put(i, view);
            a(view, i);
        }
        return view;
    }

    protected View b(int i) {
        switch (i) {
            case 2007:
                View emoticonLinearLayout = new EmoticonLinearLayout(this.c, null);
                ((EmoticonLinearLayout) emoticonLinearLayout).setPanelViewType(i);
                return emoticonLinearLayout;
            default:
                return null;
        }
    }

    public void c(int i) {
        if (this.a != null) {
            View view = (View) this.a.get(i);
            if (view != null) {
                this.a.remove(i);
                int d = d(i);
                if (e(d)) {
                    if (e == null) {
                        e = new g();
                    }
                    if (!e.a(d, view)) {
                    }
                }
            }
        }
    }

    private boolean e(int i) {
        switch (i) {
            case 2007:
                return true;
            default:
                return false;
        }
    }
}
