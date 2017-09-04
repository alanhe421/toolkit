package com.qq.reader.common.emotion;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class EmoticonLinearLayout extends LinearLayout {
    private static final String b = EmoticonLinearLayout.class.getSimpleName();
    private static Rect n = new Rect();
    Runnable a = new Runnable(this) {
        final /* synthetic */ EmoticonLinearLayout a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.e != null) {
                this.a.e.b();
                this.a.postDelayed(this, 100);
            }
        }
    };
    private int c = 6;
    private List<RelativeLayout> d = new ArrayList();
    private c e;
    private c f;
    private Context g;
    private float h;
    private a i;
    private boolean j;
    private View k;
    private View l;
    private b m = new b(this) {
        final /* synthetic */ EmoticonLinearLayout a;
        private boolean b;
        private int c;
        private int d;

        {
            this.a = r1;
        }

        public void a(int i, int i2) {
            if (this.c != i || this.d != i2 || !this.b) {
                this.c = i;
                this.d = i2;
                this.a.removeAllViews();
                int i3 = 0;
                int i4 = 0;
                while (i4 < this.c) {
                    View linearLayout = new LinearLayout(this.a.g);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    layoutParams.weight = 1.0f;
                    linearLayout.setOrientation(0);
                    int i5 = i3;
                    for (int i6 = 0; i6 < this.d; i6++) {
                        View relativeLayout;
                        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
                        layoutParams2.weight = 1.0f;
                        View view = null;
                        if (i5 < this.a.d.size()) {
                            view = (RelativeLayout) this.a.d.get(i5);
                        }
                        if (view == null) {
                            relativeLayout = new RelativeLayout(this.a.g);
                            view = new ImageView(this.a.g);
                            view.setId(978670);
                            switch (this.a.c) {
                                case 2007:
                                    LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
                                    layoutParams3.addRule(13, -1);
                                    view.setScaleType(ScaleType.FIT_XY);
                                    view.setAdjustViewBounds(false);
                                    relativeLayout.addView(view, layoutParams3);
                                    break;
                            }
                            this.a.d.add(relativeLayout);
                        } else {
                            ViewGroup viewGroup = (ViewGroup) view.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeAllViews();
                            }
                            relativeLayout = view;
                        }
                        relativeLayout.setBackgroundResource(R.drawable.emoicon_background);
                        linearLayout.addView(relativeLayout, layoutParams2);
                        i5++;
                    }
                    this.a.addView(linearLayout, layoutParams);
                    i4++;
                    i3 = i5;
                }
                this.b = true;
            }
        }

        public void a() {
            int b = this.a.f.b();
            int c = this.a.f.c();
            int i = 0;
            int i2 = 0;
            while (i < c) {
                int i3 = 0;
                int i4 = i2;
                while (i3 < b) {
                    if (this.a.d.size() > i4) {
                        this.a.f.a(i4, (RelativeLayout) this.a.d.get(i4), this.a);
                        i4++;
                        i3++;
                    } else {
                        return;
                    }
                }
                i++;
                i2 = i4;
            }
            this.a.requestLayout();
        }
    };
    private FrameLayout o;
    private FrameLayout p;
    private boolean q = false;
    private d r;

    public interface b {
        void a();

        void a(int i, int i2);
    }

    class a implements Runnable {
        final /* synthetic */ EmoticonLinearLayout a;
        private int b;

        a(EmoticonLinearLayout emoticonLinearLayout) {
            this.a = emoticonLinearLayout;
        }

        public void run() {
            if (this.a.getParent() != null && this.b == this.a.getWindowAttachCount() && this.a.k != null) {
                d dVar = (d) this.a.k.getTag();
                if (dVar == null) {
                    return;
                }
                if (this.a.e == null || !this.a.e.b(dVar)) {
                    this.a.j = true;
                    this.a.getParent().requestDisallowInterceptTouchEvent(true);
                    this.a.sendAccessibilityEvent(2);
                    if ("delete".equals(dVar.b)) {
                        this.a.a.run();
                        return;
                    } else if (!"setting".equals(dVar.b) && !"add".equals(dVar.b)) {
                        this.a.a(this.a.k, dVar);
                        return;
                    } else {
                        return;
                    }
                }
                this.a.k = null;
            }
        }

        public void a() {
            this.b = this.a.getWindowAttachCount();
        }
    }

    public static abstract class c {
        int a;
        int b;
        int c;
        int d;
        d e;
        d f;
        d g;
        boolean h;
        boolean i;
        boolean j;
        List<d> k;
        b l;

        public abstract void a(int i, RelativeLayout relativeLayout, ViewGroup viewGroup);

        public void a(d dVar) {
            this.e = dVar;
        }

        public void a(boolean z) {
            this.h = z;
        }

        public void b(boolean z) {
            this.i = z;
        }

        public void c(boolean z) {
            this.j = z;
        }

        public void a(int i) {
            this.c = i;
        }

        void a(b bVar) {
            this.l = bVar;
        }

        public void a() {
            if (this.l != null) {
                this.l.a();
            }
        }

        public void a(List<d> list) {
            this.k = list;
        }

        public int b() {
            return this.a;
        }

        public int c() {
            return this.b;
        }

        public int a(int i, int i2) {
            if (this.h) {
                if ((i + 1) % this.d == 0) {
                    if ((i + 1) + (this.c * (this.d - 1)) < i2) {
                        return -1;
                    }
                    return (this.c * (this.d - 1)) + i;
                } else if ((this.c * (this.d - 1)) + i != i2) {
                    return (this.c * (this.d - 1)) + i;
                } else {
                    return -1;
                }
            } else if (this.j && ((this.c * this.d) + i) - this.k.size() == 0) {
                return -2;
            } else {
                if (this.j && this.i && 1 == ((this.c * this.d) + i) - this.k.size()) {
                    return -3;
                }
                if (!this.j && this.i && ((this.c * this.d) + i) - this.k.size() == 0) {
                    return -3;
                }
                return (this.c * this.d) + i;
            }
        }

        public void b(int i, int i2) {
            this.b = i;
            this.a = i2;
            this.d = i2 * i;
            this.l.a(i, i2);
        }

        public d b(int i) {
            if (this.k == null) {
                return null;
            }
            int a = a(i, this.k.size());
            if (a == -1) {
                return this.e;
            }
            if (a == -2) {
                return this.g;
            }
            if (a == -3) {
                return this.f;
            }
            if (this.k == null || a >= this.k.size()) {
                return null;
            }
            return (d) this.k.get(a);
        }
    }

    public EmoticonLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = context;
        setOrientation(1);
        this.h = context.getResources().getDisplayMetrics().density;
        super.setClickable(true);
        super.setLongClickable(true);
    }

    public void setPanelViewType(int i) {
        this.c = i;
    }

    public void setCallBack(c cVar) {
        this.e = cVar;
    }

    public void setAdapter(c cVar) {
        this.f = cVar;
        this.f.a(this.m);
    }

    public c getAdapter() {
        return this.f;
    }

    public EmoticonLinearLayout(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        d dVar;
        switch (motionEvent.getAction()) {
            case 0:
                this.j = false;
                this.k = a(motionEvent.getX(), motionEvent.getY());
                if (this.k != null) {
                    this.k.setPressed(true);
                    this.l = this.k;
                    if (this.i == null) {
                        this.i = new a(this);
                    }
                    this.i.a();
                    postDelayed(this.i, (long) ViewConfiguration.getLongPressTimeout());
                    dVar = (d) this.k.getTag();
                    if (!(dVar == null || this.e == null || !"delete".equals(dVar.b))) {
                        this.e.b();
                        break;
                    }
                }
                break;
            case 1:
                b();
                if (!(this.j || this.i == null)) {
                    removeCallbacks(this.i);
                }
                if (!(this.k == null || this.j)) {
                    a(this.k);
                }
                a();
                this.k = null;
                super.removeCallbacks(this.a);
                break;
            case 2:
                this.k = a(motionEvent.getX(), motionEvent.getY());
                if (this.k != this.l) {
                    b();
                }
                if (!this.j || (a(this.k, n) && n.contains((int) motionEvent.getX(), (int) motionEvent.getY()))) {
                    if (!(this.j || this.k == null || (a(this.k, n) && n.contains((int) motionEvent.getX(), (int) motionEvent.getY())))) {
                        this.k = null;
                        break;
                    }
                }
                this.k = a(motionEvent.getX(), motionEvent.getY());
                if (this.k != null && this.k.getTag() != null) {
                    dVar = (d) this.k.getTag();
                    if (!(dVar == null || "delete".equals(dVar.b) || "add".equals(dVar.b) || "setting".equals(dVar.b))) {
                        a(this.k, (d) this.k.getTag());
                        break;
                    }
                }
                a();
                break;
                break;
            case 3:
                b();
                setPressed(false);
                if (this.i != null) {
                    removeCallbacks(this.i);
                }
                removeCallbacks(this.a);
                a();
                this.k = null;
                break;
        }
        return true;
    }

    private void a(View view) {
        d dVar = (d) view.getTag();
        if (dVar != null) {
            super.sendAccessibilityEvent(1);
            super.playSoundEffect(0);
            if (!"delete".equals(dVar.b) && this.e != null) {
                this.e.a(dVar);
            }
        }
    }

    public void a() {
        if (this.o != null && this.q) {
            ((WindowManager) getContext().getSystemService("window")).removeViewImmediate(this.p);
            if (this.e != null) {
                this.e.c(this.r);
            }
            this.q = false;
        }
    }

    void a(View view, d dVar) {
        Drawable b = dVar.b(this.g, this.h);
        d dVar2 = this.r;
        this.r = dVar;
        if (this.e != null) {
            this.e.a(dVar2, dVar, b);
        }
    }

    private View a(float f, float f2) {
        for (int childCount = super.getChildCount() - 1; childCount >= 0; childCount--) {
            LinearLayout linearLayout = (LinearLayout) super.getChildAt(childCount);
            float scrollX = (((float) super.getScrollX()) + f) - ((float) linearLayout.getLeft());
            float scrollY = (((float) super.getScrollY()) + f2) - ((float) linearLayout.getTop());
            if (scrollX >= 0.0f && scrollX <= ((float) linearLayout.getWidth()) && scrollY >= 0.0f && scrollY < ((float) linearLayout.getHeight())) {
                for (int childCount2 = linearLayout.getChildCount() - 1; childCount2 >= 0; childCount2--) {
                    View childAt = linearLayout.getChildAt(childCount2);
                    float scrollX2 = (((float) linearLayout.getScrollX()) + scrollX) - ((float) childAt.getLeft());
                    float scrollY2 = (((float) linearLayout.getScrollY()) + scrollY) - ((float) childAt.getTop());
                    if (scrollX2 >= 0.0f && scrollX2 <= ((float) childAt.getWidth()) && scrollY2 >= 0.0f && scrollY2 < ((float) childAt.getHeight())) {
                        return childAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private boolean a(View view, Rect rect) {
        if (view == null) {
            return false;
        }
        view.getDrawingRect(rect);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        rect.offset(view.getLeft() - viewGroup.getScrollX(), view.getTop() - viewGroup.getScrollY());
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
        rect.offset(viewGroup.getLeft() - viewGroup2.getScrollX(), viewGroup.getTop() - viewGroup2.getScrollY());
        return true;
    }

    private void b() {
        for (int childCount = super.getChildCount() - 1; childCount >= 0; childCount--) {
            LinearLayout linearLayout = (LinearLayout) super.getChildAt(childCount);
            for (int childCount2 = linearLayout.getChildCount() - 1; childCount2 >= 0; childCount2--) {
                linearLayout.getChildAt(childCount2).setPressed(false);
            }
        }
    }
}
