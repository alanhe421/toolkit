package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;

public class PopupLayerView extends RelativeLayout {
    public boolean a = false;
    private int b = 0;
    private ArrayList<Animator> c = new ArrayList();
    private final long d = 3000;
    private final long e = 1000;
    private final long f = 500;
    private final float g = 0.33f;
    private final float h = 0.2f;
    private final float i = 0.2f;
    private float j = -76.0f;
    private ArrayList<View> k = new ArrayList();
    private boolean l = false;
    private b m;
    private Runnable n = new Runnable(this) {
        final /* synthetic */ PopupLayerView a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.k != null && this.a.k.size() > 0) {
                this.a.removeAllViews();
                ((View) this.a.k.get(0)).setVisibility(0);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(8);
                this.a.addView((View) this.a.k.get(0), layoutParams);
                if (!this.a.l) {
                    ((View) this.a.k.get(0)).postDelayed(this.a.o, 3000);
                }
            }
        }
    };
    private Runnable o = new Runnable(this) {
        final /* synthetic */ PopupLayerView a;

        {
            this.a = r1;
        }

        public void run() {
            View view;
            int i = 0;
            int childCount = this.a.getChildCount();
            if (childCount < this.a.k.size()) {
                View view2 = (View) this.a.k.get(childCount);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(8);
                this.a.addView(view2, layoutParams);
                view2.setVisibility(0);
                view = view2;
            } else {
                view = null;
            }
            int childCount2 = this.a.getChildCount();
            Collection a = this.a.a(childCount2);
            while (a != null && i < a.size()) {
                Object obj = (Animator) a.get(i);
                if (obj instanceof ObjectAnimator) {
                    ObjectAnimator objectAnimator = (ObjectAnimator) obj;
                    if (i == 0) {
                        obj = this.a.a(objectAnimator, view);
                    } else {
                        obj = this.a.a(objectAnimator, this.a.getChildAt((childCount2 - 1) - i));
                    }
                }
                a.set(i, obj);
                ((Animator) a.get(i)).setTarget(this.a.getChildAt((childCount2 - 1) - i));
                i++;
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(a);
            animatorSet.setDuration(500);
            animatorSet.start();
            if (childCount2 < this.a.k.size()) {
                if (!this.a.l) {
                    this.a.postDelayed(this.a.o, 3000);
                }
            } else if (!this.a.l) {
                this.a.postDelayed(this.a.p, 3000);
            }
        }
    };
    private Runnable p = new Runnable(this) {
        final /* synthetic */ PopupLayerView a;

        {
            this.a = r1;
        }

        public void run() {
            View e = this.a.f();
            if (e != null) {
                int childCount = this.a.getChildCount();
                this.a.c = this.a.a(childCount);
                int i = 0;
                while (this.a.c != null && i < this.a.c.size()) {
                    Object obj = (Animator) this.a.c.get(i);
                    if (obj instanceof ObjectAnimator) {
                        ObjectAnimator objectAnimator = (ObjectAnimator) obj;
                        if (i == 0) {
                            obj = this.a.a(objectAnimator, e);
                        } else {
                            obj = this.a.a(objectAnimator, this.a.getChildAt((childCount - 1) - i));
                        }
                    }
                    this.a.c.set(i, obj);
                    ((Animator) this.a.c.get(i)).setTarget(this.a.getChildAt((childCount - 1) - i));
                    i++;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(this.a.c);
                animatorSet.setDuration(500);
                animatorSet.start();
                this.a.b = this.a.b + 1;
                if (!this.a.l) {
                    this.a.postDelayed(this.a.p, 3000);
                }
            }
        }
    };

    public interface a {
        int a();

        Object a(int i);

        void a(View view, int i, Object obj);

        int b();

        int c();
    }

    public class b {
        final /* synthetic */ PopupLayerView a;
        private a b;
        private int c;
        private ArrayList<View> d = new ArrayList();

        public b(PopupLayerView popupLayerView) {
            this.a = popupLayerView;
        }

        public ArrayList<View> a() {
            return this.d;
        }

        public void a(a aVar) {
            if (this.b != null) {
                this.c = this.b.a();
            }
            this.b = aVar;
            c();
        }

        public void b() {
            if (this.d != null) {
                this.d.clear();
            }
        }

        public void c() {
            if (this.b != null) {
                this.a.c();
                if (d()) {
                    this.a.removeAllViews();
                    if (this.d != null) {
                        this.d.clear();
                    } else {
                        this.d = new ArrayList();
                    }
                }
                a(this.b.b());
                int i = 0;
                while (this.d != null && i < this.d.size()) {
                    if (this.b != null) {
                        this.b.a((View) this.d.get(i), i, this.b.a(i));
                    }
                    i++;
                }
                this.a.d();
            }
        }

        public boolean d() {
            return this.c != this.b.a();
        }

        private boolean a(int i) {
            int i2 = 0;
            if (this.d != null) {
                int size = this.d.size();
                if (i > size) {
                    size = i - size;
                    while (i2 < size) {
                        this.d.add(View.inflate(ReaderApplication.getApplicationImp(), this.b.c(), null));
                        i2++;
                    }
                    return true;
                } else if (size > i) {
                    size -= i;
                    while (i2 < size && this.d != null && this.d.size() > 0) {
                        this.d.remove(this.d.size() - 1);
                        i2++;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public PopupLayerView(Context context) {
        super(context);
        e();
    }

    public PopupLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public PopupLayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    private void e() {
        this.m = new b(this);
        this.j = -0.2f * ((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.read_endpage_comment_popup_up_trans_y));
    }

    public void setAdapter(a aVar) {
        if (this.m != null) {
            this.m.a(aVar);
        }
    }

    public void a() {
        if (this.m != null) {
            this.m.c();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        if (!this.a) {
            try {
                super.dispatchDraw(canvas);
            } catch (Exception e) {
                c.e(getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    private View f() {
        if (this.k == null || this.k.size() == 0) {
            return null;
        }
        this.k.size();
        int childCount = this.b % getChildCount();
        if (childCount >= this.k.size()) {
            return null;
        }
        View view = (View) this.k.get(childCount);
        removeView(view);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(8);
        addView(view, layoutParams);
        view.setVisibility(0);
        return view;
    }

    private ObjectAnimator a(ObjectAnimator objectAnimator, View view) {
        if (objectAnimator == null || view == null) {
            return objectAnimator;
        }
        PropertyValuesHolder[] values = objectAnimator.getValues();
        if (values == null) {
            return objectAnimator;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[(values.length + 2)];
        int i = 0;
        while (values != null && i < values.length) {
            propertyValuesHolderArr[i] = values[i];
            i++;
        }
        propertyValuesHolderArr[values.length] = PropertyValuesHolder.ofFloat("pivotX", new float[]{((float) view.getWidth()) / 2.0f, ((float) view.getWidth()) / 2.0f});
        propertyValuesHolderArr[values.length + 1] = PropertyValuesHolder.ofFloat("pivotY", new float[]{(float) view.getHeight(), (float) view.getHeight()});
        return ObjectAnimator.ofPropertyValuesHolder(new Object(), propertyValuesHolderArr);
    }

    public void b() {
        this.l = true;
        removeCallbacks(this.n);
        removeCallbacks(this.o);
        removeCallbacks(this.p);
    }

    public void c() {
        b();
    }

    public void d() {
        if (this.m != null) {
            this.k = this.m.a();
        }
        if (this.k == null) {
            this.k = new ArrayList();
        }
        int childCount = getChildCount();
        this.l = false;
        if (childCount == 0) {
            g();
        } else if (childCount < this.k.size()) {
            postDelayed(this.o, 3000);
        } else {
            postDelayed(this.p, 3000);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
        b();
        if (this.k != null) {
            this.k.clear();
        }
        if (this.m != null) {
            this.m.b();
            this.m = null;
        }
    }

    private ArrayList<Animator> a(int i) {
        ArrayList<Animator> arrayList = new ArrayList();
        if (i > 0 && i >= 2) {
            if (i < 3) {
                arrayList.add(getFirstAnimator());
                arrayList.add(c(i - 1));
            } else {
                arrayList.add(getFirstAnimator());
                for (int i2 = 1; i2 < i - 1; i2++) {
                    arrayList.add(b(i2));
                }
                arrayList.add(c(i - 1));
            }
        }
        return arrayList;
    }

    private void g() {
        int i = 0;
        while (this.k != null && i < this.k.size()) {
            ((View) this.k.get(i)).setVisibility(4);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(8);
            addView((View) this.k.get(i), layoutParams);
            i++;
        }
        int childCount = getChildCount();
        if (childCount > 0) {
            getChildAt(childCount - 1).post(this.n);
        }
    }

    private ObjectAnimator getFirstAnimator() {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("scaleX", new float[]{0.0f, 1.0f});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("scaleY", new float[]{0.0f, 1.0f});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("translationY", new float[]{0.0f, 0.0f});
        return ObjectAnimator.ofPropertyValuesHolder(new Object(), new PropertyValuesHolder[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
    }

    private ObjectAnimator b(int i) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{1.33f - (((float) i) * 0.33f), 1.0f - (((float) i) * 0.33f)});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("scaleX", new float[]{1.2f - (((float) i) * 0.2f), 1.0f - (((float) i) * 0.2f)});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("scaleY", new float[]{1.2f - (((float) i) * 0.2f), 1.0f - (((float) i) * 0.2f)});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("translationY", new float[]{this.j * ((float) (i - 1)), this.j * ((float) i)});
        return ObjectAnimator.ofPropertyValuesHolder(new Object(), new PropertyValuesHolder[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
    }

    private ObjectAnimator c(int i) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{1.33f - (((float) i) * 0.33f), 1.0f - (((float) i) * 0.33f)});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("scaleX", new float[]{1.2f - (((float) i) * 0.2f), 1.0f - (((float) i) * 0.2f)});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("scaleY", new float[]{1.2f - (((float) i) * 0.2f), 1.0f - (((float) i) * 0.2f)});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("translationY", new float[]{this.j * ((float) (i - 1)), this.j * ((float) i)});
        return ObjectAnimator.ofPropertyValuesHolder(new Object(), new PropertyValuesHolder[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
    }
}
