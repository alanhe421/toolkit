package com.qq.reader.widget.swipBackView;

import android.view.View;
import com.qq.reader.widget.swipBackView.e.a;

class SwipeBackLayout$b extends a {
    final /* synthetic */ SwipeBackLayout a;
    private boolean b;

    private SwipeBackLayout$b(SwipeBackLayout swipeBackLayout) {
        this.a = swipeBackLayout;
    }

    public boolean a(View view, int i) {
        int i2 = 1;
        boolean c = SwipeBackLayout.b(this.a).c(SwipeBackLayout.a(this.a), i);
        if (c) {
            if (SwipeBackLayout.b(this.a).c(1, i)) {
                SwipeBackLayout.a(this.a, 1);
            } else if (SwipeBackLayout.b(this.a).c(2, i)) {
                SwipeBackLayout.a(this.a, 2);
            } else if (SwipeBackLayout.b(this.a).c(8, i)) {
                SwipeBackLayout.a(this.a, 8);
            }
            if (!(SwipeBackLayout.c(this.a) == null || SwipeBackLayout.c(this.a).isEmpty())) {
                for (SwipeBackLayout$a a : SwipeBackLayout.c(this.a)) {
                    a.a(SwipeBackLayout.d(this.a));
                }
            }
            this.b = true;
        }
        if (SwipeBackLayout.a(this.a) == 1 || SwipeBackLayout.a(this.a) == 2) {
            i2 = !SwipeBackLayout.b(this.a).b(2, i) ? 1 : 0;
        } else if (SwipeBackLayout.a(this.a) == 8) {
            if (SwipeBackLayout.b(this.a).b(1, i)) {
                i2 = 0;
            }
        } else if (SwipeBackLayout.a(this.a) != 11) {
            i2 = 0;
        }
        return c & i2;
    }

    public int a(View view) {
        return SwipeBackLayout.a(this.a) & 3;
    }

    public int b(View view) {
        return SwipeBackLayout.a(this.a) & 8;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        super.a(view, i, i2, i3, i4);
        if ((SwipeBackLayout.d(this.a) & 1) != 0) {
            SwipeBackLayout.a(this.a, Math.abs(((float) i) / ((float) (SwipeBackLayout.e(this.a).getWidth() + SwipeBackLayout.f(this.a).getIntrinsicWidth()))));
        } else if ((SwipeBackLayout.d(this.a) & 2) != 0) {
            SwipeBackLayout.a(this.a, Math.abs(((float) i) / ((float) (SwipeBackLayout.e(this.a).getWidth() + SwipeBackLayout.g(this.a).getIntrinsicWidth()))));
        } else if ((SwipeBackLayout.d(this.a) & 8) != 0) {
            SwipeBackLayout.a(this.a, Math.abs(((float) i2) / ((float) (SwipeBackLayout.e(this.a).getHeight() + SwipeBackLayout.h(this.a).getIntrinsicHeight()))));
        }
        SwipeBackLayout.b(this.a, i);
        SwipeBackLayout.c(this.a, i2);
        this.a.invalidate();
        if (SwipeBackLayout.i(this.a) < SwipeBackLayout.j(this.a) && !this.b) {
            this.b = true;
        }
        if (SwipeBackLayout.c(this.a) != null && !SwipeBackLayout.c(this.a).isEmpty() && SwipeBackLayout.b(this.a).c() == 1 && SwipeBackLayout.i(this.a) >= SwipeBackLayout.j(this.a) && this.b) {
            this.b = false;
            for (SwipeBackLayout$a a : SwipeBackLayout.c(this.a)) {
                a.a();
            }
        }
        if (SwipeBackLayout.i(this.a) >= 1.0f && !SwipeBackLayout.k(this.a).isFinishing()) {
            SwipeBackLayout.a(this.a, true);
            SwipeBackLayout.k(this.a).finish();
            SwipeBackLayout.k(this.a).overridePendingTransition(0, 0);
        }
    }

    public void a(View view, float f, float f2) {
        int i = 0;
        int width = view.getWidth();
        int height = view.getHeight();
        if ((SwipeBackLayout.d(this.a) & 1) != 0) {
            width = (f > 0.0f || (f == 0.0f && SwipeBackLayout.i(this.a) > SwipeBackLayout.j(this.a))) ? (width + SwipeBackLayout.f(this.a).getIntrinsicWidth()) + 10 : 0;
        } else if ((SwipeBackLayout.d(this.a) & 2) != 0) {
            width = (f < 0.0f || (f == 0.0f && SwipeBackLayout.i(this.a) > SwipeBackLayout.j(this.a))) ? -((width + SwipeBackLayout.f(this.a).getIntrinsicWidth()) + 10) : 0;
        } else if ((SwipeBackLayout.d(this.a) & 8) != 0) {
            width = (f2 < 0.0f || (f2 == 0.0f && SwipeBackLayout.i(this.a) > SwipeBackLayout.j(this.a))) ? -((SwipeBackLayout.h(this.a).getIntrinsicHeight() + height) + 10) : 0;
            int i2 = width;
            width = 0;
            i = i2;
        } else {
            width = 0;
        }
        SwipeBackLayout.b(this.a).a(width, i);
        this.a.invalidate();
    }

    public int a(View view, int i, int i2) {
        if ((SwipeBackLayout.d(this.a) & 1) != 0) {
            return Math.min(view.getWidth(), Math.max(i, 0));
        }
        if ((SwipeBackLayout.d(this.a) & 2) != 0) {
            return Math.min(0, Math.max(i, -view.getWidth()));
        }
        return 0;
    }

    public int b(View view, int i, int i2) {
        if ((SwipeBackLayout.d(this.a) & 8) != 0) {
            return Math.min(0, Math.max(i, -view.getHeight()));
        }
        return 0;
    }

    public void a(int i) {
        super.a(i);
        if (SwipeBackLayout.c(this.a) != null && !SwipeBackLayout.c(this.a).isEmpty()) {
            for (SwipeBackLayout$a a : SwipeBackLayout.c(this.a)) {
                a.a(i, SwipeBackLayout.i(this.a));
            }
        }
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        if (SwipeBackLayout.a(this.a) == 1 && SwipeBackLayout.c(this.a) != null && !SwipeBackLayout.c(this.a).isEmpty()) {
            for (SwipeBackLayout$a a : SwipeBackLayout.c(this.a)) {
                a.a(SwipeBackLayout.d(this.a));
            }
        }
    }
}
