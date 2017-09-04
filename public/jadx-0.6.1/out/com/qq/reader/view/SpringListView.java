package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

public class SpringListView extends ListView {
    private Scroller a;
    private float b = -1.0f;
    private float c = -1.0f;
    private int d = 0;
    private boolean e = false;
    private boolean f = true;

    protected void a(Context context) {
        this.a = new Scroller(context, new AccelerateInterpolator());
        this.d = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
    }

    public SpringListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public SpringListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SpringListView(Context context) {
        super(context);
        a(context);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
        r8 = this;
        r7 = 0;
        r0 = 1;
        r1 = r9.getAction();	 Catch:{ Exception -> 0x0019 }
        switch(r1) {
            case 0: goto L_0x000e;
            case 1: goto L_0x00c8;
            case 2: goto L_0x0024;
            default: goto L_0x0009;
        };
    L_0x0009:
        r0 = super.onTouchEvent(r9);
    L_0x000d:
        return r0;
    L_0x000e:
        r0 = r9.getY();	 Catch:{ Exception -> 0x0019 }
        r8.b = r0;	 Catch:{ Exception -> 0x0019 }
        r0 = r8.b;	 Catch:{ Exception -> 0x0019 }
        r8.c = r0;	 Catch:{ Exception -> 0x0019 }
        goto L_0x0009;
    L_0x0019:
        r0 = move-exception;
        r1 = "SpringListView";
        r2 = "onTouchEvent";
        com.qq.reader.common.monitor.f.a(r1, r2, r0);
        goto L_0x0009;
    L_0x0024:
        r1 = r9.getY();	 Catch:{ Exception -> 0x0019 }
        r2 = r8.f;	 Catch:{ Exception -> 0x0019 }
        if (r2 == 0) goto L_0x003a;
    L_0x002c:
        r2 = r8.e;	 Catch:{ Exception -> 0x0019 }
        if (r2 != 0) goto L_0x003a;
    L_0x0030:
        r8.b = r1;	 Catch:{ Exception -> 0x0019 }
        r1 = r8.b;	 Catch:{ Exception -> 0x0019 }
        r8.c = r1;	 Catch:{ Exception -> 0x0019 }
        r1 = 0;
        r8.f = r1;	 Catch:{ Exception -> 0x0019 }
        goto L_0x000d;
    L_0x003a:
        r2 = 0;
        r2 = r8.getChildAt(r2);	 Catch:{ Exception -> 0x0019 }
        r2 = r2.getTop();	 Catch:{ Exception -> 0x0019 }
        r3 = r8.getChildCount();	 Catch:{ Exception -> 0x0019 }
        r3 = r3 + -1;
        r3 = r8.getChildAt(r3);	 Catch:{ Exception -> 0x0019 }
        r3 = r3.getBottom();	 Catch:{ Exception -> 0x0019 }
        r4 = r8.getHeight();	 Catch:{ Exception -> 0x0019 }
        r5 = r8.getPaddingBottom();	 Catch:{ Exception -> 0x0019 }
        r4 = r4 - r5;
        r5 = r8.b;	 Catch:{ Exception -> 0x0019 }
        r5 = r5 - r1;
        r6 = r8.getFirstVisiblePosition();	 Catch:{ Exception -> 0x0019 }
        if (r6 != 0) goto L_0x008f;
    L_0x0063:
        r6 = r8.getPaddingTop();	 Catch:{ Exception -> 0x0019 }
        if (r2 < r6) goto L_0x008f;
    L_0x0069:
        r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r2 < 0) goto L_0x0073;
    L_0x006d:
        r2 = r8.getScrollY();	 Catch:{ Exception -> 0x0019 }
        if (r2 >= 0) goto L_0x00c4;
    L_0x0073:
        r8.a(r1, r5);	 Catch:{ Exception -> 0x0019 }
        r1 = r8.e;	 Catch:{ Exception -> 0x0019 }
        if (r1 != 0) goto L_0x000d;
    L_0x007a:
        r0 = r8.b;	 Catch:{ Exception -> 0x0019 }
        r1 = r8.c;	 Catch:{ Exception -> 0x0019 }
        r0 = r0 - r1;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Exception -> 0x0019 }
        r1 = r8.d;	 Catch:{ Exception -> 0x0019 }
        r1 = (float) r1;	 Catch:{ Exception -> 0x0019 }
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 <= 0) goto L_0x0009;
    L_0x008a:
        r0 = 1;
        r8.e = r0;	 Catch:{ Exception -> 0x0019 }
        goto L_0x0009;
    L_0x008f:
        r2 = r8.getFirstVisiblePosition();	 Catch:{ Exception -> 0x0019 }
        r6 = r8.getChildCount();	 Catch:{ Exception -> 0x0019 }
        r2 = r2 + r6;
        r6 = r8.getCount();	 Catch:{ Exception -> 0x0019 }
        if (r2 != r6) goto L_0x00c4;
    L_0x009e:
        if (r3 > r4) goto L_0x00c4;
    L_0x00a0:
        r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r2 > 0) goto L_0x00aa;
    L_0x00a4:
        r2 = r8.getScrollY();	 Catch:{ Exception -> 0x0019 }
        if (r2 <= 0) goto L_0x00c4;
    L_0x00aa:
        r8.a(r1, r5);	 Catch:{ Exception -> 0x0019 }
        r2 = r8.e;	 Catch:{ Exception -> 0x0019 }
        if (r2 != 0) goto L_0x000d;
    L_0x00b1:
        r0 = r8.b;	 Catch:{ Exception -> 0x0019 }
        r2 = r8.c;	 Catch:{ Exception -> 0x0019 }
        r0 = r0 - r2;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Exception -> 0x0019 }
        r2 = r8.d;	 Catch:{ Exception -> 0x0019 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x0019 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x00c4;
    L_0x00c1:
        r0 = 1;
        r8.e = r0;	 Catch:{ Exception -> 0x0019 }
    L_0x00c4:
        r8.b = r1;	 Catch:{ Exception -> 0x0019 }
        goto L_0x0009;
    L_0x00c8:
        r2 = r8.getScrollY();	 Catch:{ Exception -> 0x0019 }
        if (r2 == 0) goto L_0x00e1;
    L_0x00ce:
        r0 = r8.getScroller();	 Catch:{ Exception -> 0x0019 }
        r1 = 0;
        r3 = 0;
        r4 = -r2;
        r5 = java.lang.Math.abs(r2);	 Catch:{ Exception -> 0x0019 }
        r5 = r5 << 1;
        r0.startScroll(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0019 }
        r8.invalidate();	 Catch:{ Exception -> 0x0019 }
    L_0x00e1:
        r0 = 0;
        r8.e = r0;	 Catch:{ Exception -> 0x0019 }
        r0 = 1;
        r8.f = r0;	 Catch:{ Exception -> 0x0019 }
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.SpringListView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void a(float f, float f2) {
        scrollBy(0, (int) ((1.0f - (((float) Math.abs(getScrollY())) / 200.0f)) * f2));
        this.b = f;
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollTo(0, this.a.getCurrY());
            invalidate();
        }
    }

    public Scroller getScroller() {
        return this.a;
    }
}
