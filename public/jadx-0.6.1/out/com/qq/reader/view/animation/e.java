package com.qq.reader.view.animation;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.qq.reader.common.utils.y;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QuickReturnListViewOnScrollListener */
public class e implements OnScrollListener {
    private final int a;
    private View b;
    private int c;
    private View d;
    private int e;
    private final boolean f;
    private int g;
    private int h;
    private int i;
    private List<OnScrollListener> j;

    /* compiled from: QuickReturnListViewOnScrollListener */
    public static class a {
        private final int a;
        private View b = null;
        private int c = 0;
        private View d = null;
        private int e = 0;
        private boolean f = false;

        public a(int i) {
            this.a = i;
        }

        public a a(View view) {
            this.d = view;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public e a() {
            return new e();
        }
    }

    private e(a aVar) {
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = new ArrayList();
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        for (OnScrollListener onScrollStateChanged : this.j) {
            onScrollStateChanged.onScrollStateChanged(absListView, i);
        }
        if (i == 0 && this.f) {
            int i2 = (-this.c) / 2;
            int i3 = this.e / 2;
            ObjectAnimator ofFloat;
            switch (this.a) {
                case 0:
                    if (this.b == null) {
                        return;
                    }
                    if ((-this.h) > 0 && (-this.h) < i2) {
                        ofFloat = ObjectAnimator.ofFloat(this.b, "translationY", new float[]{ViewHelper.getTranslationY(this.b)});
                        ofFloat.setDuration(100);
                        ofFloat.start();
                        this.h = 0;
                        return;
                    } else if ((-this.h) < (-this.c) && (-this.h) >= i2) {
                        ofFloat = ObjectAnimator.ofFloat(this.b, "translationY", new float[]{ViewHelper.getTranslationY(this.b), (float) this.c});
                        ofFloat.setDuration(100);
                        ofFloat.start();
                        this.h = this.c;
                        return;
                    } else {
                        return;
                    }
                case 1:
                    if (this.d == null) {
                        return;
                    }
                    if ((-this.i) > 0 && (-this.i) < i3) {
                        ofFloat = ObjectAnimator.ofFloat(this.d, "translationY", new float[]{ViewHelper.getTranslationY(this.d)});
                        ofFloat.setDuration(100);
                        ofFloat.start();
                        this.i = 0;
                        return;
                    } else if ((-this.i) < this.e && (-this.i) >= i3) {
                        ofFloat = ObjectAnimator.ofFloat(this.d, "translationY", new float[]{ViewHelper.getTranslationY(this.d), (float) this.e});
                        ofFloat.setDuration(100);
                        ofFloat.start();
                        this.i = -this.e;
                        return;
                    } else {
                        return;
                    }
                case 2:
                    if (this.b != null && this.d != null) {
                        if ((-this.h) > 0 && (-this.h) < i2) {
                            ofFloat = ObjectAnimator.ofFloat(this.b, "translationY", new float[]{ViewHelper.getTranslationY(this.b)});
                            ofFloat.setDuration(100);
                            ofFloat.start();
                            this.h = 0;
                        } else if ((-this.h) < (-this.c) && (-this.h) >= i2) {
                            ofFloat = ObjectAnimator.ofFloat(this.b, "translationY", new float[]{ViewHelper.getTranslationY(this.b), (float) this.c});
                            ofFloat.setDuration(100);
                            ofFloat.start();
                            this.h = this.c;
                        }
                        if ((-this.i) > 0 && (-this.i) < i3) {
                            ofFloat = ObjectAnimator.ofFloat(this.d, "translationY", new float[]{ViewHelper.getTranslationY(this.d)});
                            ofFloat.setDuration(100);
                            ofFloat.start();
                            this.i = 0;
                            return;
                        } else if ((-this.i) < this.e && (-this.i) >= i3) {
                            ofFloat = ObjectAnimator.ofFloat(this.d, "translationY", new float[]{ViewHelper.getTranslationY(this.d), (float) this.e});
                            ofFloat.setDuration(100);
                            ofFloat.start();
                            this.i = -this.e;
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        for (OnScrollListener onScroll : this.j) {
            onScroll.onScroll(absListView, i, i2, i3);
        }
        int a = y.a(absListView);
        int i4 = this.g - a;
        if (i4 != 0) {
            switch (this.a) {
                case 0:
                    if (this.b != null) {
                        if (i4 < 0) {
                            this.h = Math.max(i4 + this.h, this.c);
                        } else {
                            this.h = Math.min(Math.max(i4 + this.h, this.c), 0);
                        }
                        ViewHelper.setTranslationY(this.b, (float) this.h);
                        break;
                    }
                    return;
                case 1:
                    if (this.d != null) {
                        if (i4 < 0) {
                            this.i = Math.max(i4 + this.i, -this.e);
                        } else {
                            this.i = Math.min(Math.max(i4 + this.i, -this.e), 0);
                        }
                        ViewHelper.setTranslationY(this.d, (float) (-this.i));
                        break;
                    }
                    return;
                case 2:
                    if (this.b != null && this.d != null) {
                        if (i4 < 0) {
                            this.h = Math.max(this.h + i4, this.c);
                            this.i = Math.max(i4 + this.i, -this.e);
                        } else {
                            this.h = Math.min(Math.max(this.h + i4, this.c), 0);
                            this.i = Math.min(Math.max(i4 + this.i, -this.e), 0);
                        }
                        ViewHelper.setTranslationY(this.b, (float) this.h);
                        ViewHelper.setTranslationY(this.d, (float) (-this.i));
                        break;
                    }
                    return;
            }
        }
        this.g = a;
    }

    public void a(OnScrollListener onScrollListener) {
        this.j.add(onScrollListener);
    }

    public void a(int i) {
        this.e = i;
    }

    public void a() {
        if (this.d != null) {
            ViewHelper.setTranslationY(this.d, 0.0f);
        }
        if (this.b != null) {
            ViewHelper.setTranslationY(this.b, 0.0f);
        }
        y.a();
    }
}
