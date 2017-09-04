package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.qq.reader.common.monitor.f;
import com.tencent.smtt.sdk.WebView;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

public class KeywordsFlow extends FrameLayout implements OnGlobalLayoutListener {
    private static Interpolator b;
    private static AlphaAnimation c;
    private static AlphaAnimation d;
    private static ScaleAnimation e;
    private static ScaleAnimation f;
    private static ScaleAnimation g;
    private static ScaleAnimation h;
    private OnClickListener a;
    private Vector<String> i;
    private int j;
    private int k;
    private boolean l;
    private Random m;
    private int n;
    private long o;
    private long p;
    private int q = 4;
    private int r = 4;

    public KeywordsFlow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public KeywordsFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public KeywordsFlow(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.o = 0;
        this.p = 800;
        this.m = new Random();
        this.i = new Vector(10);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        b = AnimationUtils.loadInterpolator(getContext(), 17432582);
        c = new AlphaAnimation(0.0f, 1.0f);
        d = new AlphaAnimation(1.0f, 0.0f);
        e = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f);
        f = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f);
        g = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        h = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f);
    }

    public long getDuration() {
        return this.p;
    }

    public void setDuration(long j) {
        this.p = j;
    }

    private boolean b() {
        if (this.j <= 0 || this.k <= 0 || this.i == null || this.i.size() <= 0 || !this.l) {
            return false;
        }
        this.l = false;
        this.o = System.currentTimeMillis();
        int i = this.j >> 1;
        int i2 = this.k >> 1;
        int size = this.i.size();
        int i3 = this.j / size;
        int i4 = this.k / size;
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        int i5 = this.j / 8;
        for (i5 = 0; i5 < size; i5++) {
            linkedList.add(Integer.valueOf(i5 * i3));
            linkedList2.add(Integer.valueOf((i5 * i4) + (i4 >> 2)));
            f.a("Search", "ListX:" + (i5 * i3) + "#listY:" + ((i5 * i4) + (i4 >> 2)));
        }
        LinkedList linkedList3 = new LinkedList();
        LinkedList linkedList4 = new LinkedList();
        for (int i6 = 0; i6 < size; i6++) {
            String str = (String) this.i.get(i6);
            int nextInt = WebView.NIGHT_MODE_COLOR | this.m.nextInt(7864319);
            Object a = a(this.m, linkedList, linkedList2, i3);
            TextView textView = new TextView(getContext());
            textView.setOnClickListener(this.a);
            textView.setText(str);
            textView.setTextColor(nextInt);
            textView.setTextSize(2, (float) 21);
            textView.setShadowLayer(1.0f, 1.0f, 1.0f, -580294295);
            textView.setGravity(17);
            i5 = (int) Math.ceil((double) textView.getPaint().measureText(str));
            a[2] = i5;
            if (a[0] + i5 > this.j - (i3 >> 1)) {
                a[0] = ((this.j - i5) - i3) + this.m.nextInt(i3 >> 1);
            } else if (a[0] == 0) {
                a[0] = Math.max(this.m.nextInt(i3), i3 / 3);
            }
            a[3] = Math.abs(a[1] - i2);
            textView.setTag(a);
            if (a[1] > i2) {
                linkedList4.add(textView);
            } else {
                linkedList3.add(textView);
            }
        }
        a(linkedList3, i, i2, i4);
        a(linkedList4, i, i2, i4);
        return true;
    }

    private void a(LinkedList<TextView> linkedList, int i, int i2, int i3) {
        int size = linkedList.size();
        a(linkedList, size);
        for (int i4 = 0; i4 < size; i4++) {
            int abs;
            LayoutParams layoutParams;
            TextView textView = (TextView) linkedList.get(i4);
            int[] iArr = (int[]) textView.getTag();
            int i5 = iArr[1] - i2;
            int abs2 = Math.abs(i5);
            int i6 = i4 - 1;
            while (i6 >= 0) {
                int[] iArr2 = (int[]) ((TextView) linkedList.get(i6)).getTag();
                int i7 = iArr2[0];
                int i8 = iArr2[2] + i7;
                if ((iArr2[1] - i2) * i5 <= 0 || !a(i7, i8, iArr[0], iArr[0] + iArr[2])) {
                    i6--;
                } else {
                    abs = Math.abs(iArr[1] - iArr2[1]);
                    if (abs <= i3) {
                        if (abs2 > 0) {
                            abs = 0;
                        }
                        abs = abs2;
                    }
                    if (abs > i3) {
                        abs -= i3;
                        iArr[1] = iArr[1] - ((Math.max(this.m.nextInt(abs), abs >> 1) * i5) / Math.abs(i5));
                        iArr[3] = Math.abs(iArr[1] - i2);
                        a(linkedList, i4 + 1);
                    }
                    layoutParams = new FrameLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 51;
                    layoutParams.leftMargin = iArr[0];
                    layoutParams.topMargin = iArr[1];
                    addView(textView, layoutParams);
                    textView.startAnimation(a(iArr, i, i2, this.n));
                }
            }
            abs = abs2;
            if (abs > i3) {
                abs -= i3;
                iArr[1] = iArr[1] - ((Math.max(this.m.nextInt(abs), abs >> 1) * i5) / Math.abs(i5));
                iArr[3] = Math.abs(iArr[1] - i2);
                a(linkedList, i4 + 1);
            }
            layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 51;
            layoutParams.leftMargin = iArr[0];
            layoutParams.topMargin = iArr[1];
            addView(textView, layoutParams);
            textView.startAnimation(a(iArr, i, i2, this.n));
        }
    }

    public AnimationSet a(int[] iArr, int i, int i2, int i3) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(b);
        if (i3 == 1) {
            animationSet.addAnimation(c);
            animationSet.addAnimation(e);
            animationSet.addAnimation(new TranslateAnimation((float) (((iArr[0] + (iArr[2] >> 1)) - i) << 1), 0.0f, (float) ((iArr[1] - i2) << 1), 0.0f));
        } else if (i3 == 2) {
            animationSet.addAnimation(d);
            animationSet.addAnimation(f);
            animationSet.addAnimation(new TranslateAnimation(0.0f, (float) (((iArr[0] + (iArr[2] >> 1)) - i) << 1), 0.0f, (float) ((iArr[1] - i2) << 1)));
        } else if (i3 == 4) {
            animationSet.addAnimation(d);
            animationSet.addAnimation(h);
            animationSet.addAnimation(new TranslateAnimation(0.0f, (float) ((-iArr[0]) + i), 0.0f, (float) ((-iArr[1]) + i2)));
        } else if (i3 == 3) {
            animationSet.addAnimation(c);
            animationSet.addAnimation(g);
            animationSet.addAnimation(new TranslateAnimation((float) ((-iArr[0]) + i), 0.0f, (float) ((-iArr[1]) + i2), 0.0f));
        }
        animationSet.setDuration(this.p);
        return animationSet;
    }

    private void a(LinkedList<TextView> linkedList, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = i2 + 1; i3 < i; i3++) {
                if (((int[]) ((TextView) linkedList.get(i3)).getTag())[3] < ((int[]) ((TextView) linkedList.get(i2)).getTag())[3]) {
                    TextView textView = (TextView) linkedList.get(i2);
                    linkedList.set(i2, (TextView) linkedList.get(i3));
                    linkedList.set(i3, textView);
                }
            }
        }
    }

    private boolean a(int i, int i2, int i3, int i4) {
        if (i3 >= i && i3 <= i2) {
            return true;
        }
        if (i4 >= i && i4 <= i2) {
            return true;
        }
        if (i >= i3 && i <= i4) {
            return true;
        }
        if (i2 < i3 || i2 > i4) {
            return false;
        }
        return true;
    }

    private int[] a(Random random, LinkedList<Integer> linkedList, LinkedList<Integer> linkedList2, int i) {
        int[] iArr = new int[4];
        iArr[0] = ((Integer) linkedList.remove(random.nextInt(linkedList.size()))).intValue();
        iArr[1] = ((Integer) linkedList2.remove(random.nextInt(linkedList2.size()))).intValue();
        return iArr;
    }

    public void onGlobalLayout() {
        int width = getWidth();
        int height = getHeight();
        if (this.j != width || this.k != height) {
            this.j = width;
            this.k = height;
            b();
        }
    }

    public Vector<String> getKeywords() {
        return this.i;
    }

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.a = onClickListener;
    }
}
