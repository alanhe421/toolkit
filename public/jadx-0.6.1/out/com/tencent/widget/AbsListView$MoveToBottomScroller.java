package com.tencent.widget;

import android.view.animation.AnimationUtils;

class AbsListView$MoveToBottomScroller implements Runnable {
    private static final int SCROLL_DURATION = 400;
    private static final int STATUS_ACCERLERING = 0;
    private static final int STATUS_DECERLERING = 1;
    private static final int STATUS_UNIFORM = 2;
    private static final int STATUS_VISCOUS_FLUID = 3;
    private int lastMotionY = 0;
    private float mAccerleration;
    private float mCurrVelocity;
    private int mDistance;
    private int mDuration;
    private float mMaxVeloctiy;
    private long mStartTime;
    private int mStatus;
    private int mTargetPosition;
    private boolean mUseViscousFluid = false;
    final /* synthetic */ AbsListView this$0;

    AbsListView$MoveToBottomScroller(AbsListView absListView) {
        this.this$0 = absListView;
    }

    void start() {
        boolean z = true;
        int childCount = (this.this$0.mItemCount - ((this.this$0.mFirstPosition + this.this$0.getChildCount()) - 1)) - 1;
        if (childCount == 0) {
            this.mDistance = this.this$0.getChildAt(this.this$0.getChildCount() - 1).getBottom() - ((AbsListView.access$2800(this.this$0) - AbsListView.access$2900(this.this$0)) - this.this$0.mListPadding.bottom);
            if (this.mDistance == 0) {
                stop();
                return;
            }
            this.mDuration = 400;
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.lastMotionY = 0;
            this.mStatus = 3;
            this.this$0.post(this);
            return;
        }
        this.mMaxVeloctiy = ((float) ((this.this$0.getHeight() * childCount) / this.this$0.getChildCount())) / 300.0f;
        this.mAccerleration = this.mMaxVeloctiy / 100.0f;
        this.mCurrVelocity = 0.0f;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStatus = 0;
        this.lastMotionY = 0;
        this.mTargetPosition = this.this$0.mItemCount - 1;
        if (childCount != 1) {
            z = false;
        }
        this.mUseViscousFluid = z;
        this.this$0.post(this);
    }

    void stop() {
        this.this$0.removeCallbacks(this);
        this.this$0.mScrollToBottom = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r0 = 0;
        r9 = 1137180672; // 0x43c80000 float:400.0 double:5.61841903E-315;
        r8 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r7 = 100;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = "AbsListView.MoveToBottomScroller.run";
        com.tencent.widget.AdapterView.traceBegin(r1);
        r2 = android.view.animation.AnimationUtils.currentAnimationTimeMillis();	 Catch:{ all -> 0x0089 }
        r4 = r10.mStartTime;	 Catch:{ all -> 0x0089 }
        r2 = r2 - r4;
        r1 = (int) r2;	 Catch:{ all -> 0x0089 }
        r2 = r10.mStatus;	 Catch:{ all -> 0x0089 }
        switch(r2) {
            case 0: goto L_0x0072;
            case 1: goto L_0x00a7;
            case 2: goto L_0x009b;
            case 3: goto L_0x00d3;
            default: goto L_0x001c;
        };	 Catch:{ all -> 0x0089 }
    L_0x001c:
        r2 = r10.lastMotionY;	 Catch:{ all -> 0x0089 }
        r0 = r0 - r2;
        r2 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r3 = -r0;
        r0 = -r0;
        r0 = r2.trackMotionScroll(r3, r0);	 Catch:{ all -> 0x0089 }
        if (r0 != 0) goto L_0x0143;
    L_0x0029:
        r0 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r0 = r0.getChildCount();	 Catch:{ all -> 0x0089 }
        r2 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r2 = r2.mFirstPosition;	 Catch:{ all -> 0x0089 }
        r3 = r10.mStatus;	 Catch:{ all -> 0x0089 }
        r4 = 3;
        if (r3 == r4) goto L_0x0135;
    L_0x0038:
        r3 = r10.mStatus;	 Catch:{ all -> 0x0089 }
        r4 = 1;
        if (r3 == r4) goto L_0x0135;
    L_0x003d:
        r2 = r2 + r0;
        r2 = r2 + -1;
        r3 = r10.mTargetPosition;	 Catch:{ all -> 0x0089 }
        if (r2 < r3) goto L_0x0135;
    L_0x0044:
        r2 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r2 = com.tencent.widget.AbsListView.access$3000(r2);	 Catch:{ all -> 0x0089 }
        r3 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r3 = com.tencent.widget.AbsListView.access$3100(r3);	 Catch:{ all -> 0x0089 }
        r2 = r2 - r3;
        r3 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r3 = r3.mListPadding;	 Catch:{ all -> 0x0089 }
        r3 = r3.bottom;	 Catch:{ all -> 0x0089 }
        r2 = r2 - r3;
        r3 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r0 = r0 + -1;
        r0 = r3.getChildAt(r0);	 Catch:{ all -> 0x0089 }
        r0 = r0.getBottom();	 Catch:{ all -> 0x0089 }
        r0 = r0 - r2;
        r10.mDistance = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        if (r0 != 0) goto L_0x00f7;
    L_0x006b:
        r10.stop();	 Catch:{ all -> 0x0089 }
        com.tencent.widget.AdapterView.traceEnd();
    L_0x0071:
        return;
    L_0x0072:
        if (r1 <= r7) goto L_0x008e;
    L_0x0074:
        r0 = r10.mMaxVeloctiy;	 Catch:{ all -> 0x0089 }
        r10.mCurrVelocity = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mMaxVeloctiy;	 Catch:{ all -> 0x0089 }
        r2 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r2;
        r2 = r10.mMaxVeloctiy;	 Catch:{ all -> 0x0089 }
        r2 = r2 * r9;
        r2 = r2 / r8;
        r0 = r0 - r2;
        r0 = (int) r0;	 Catch:{ all -> 0x0089 }
        r2 = 0;
        r10.mAccerleration = r2;	 Catch:{ all -> 0x0089 }
        r2 = 2;
        r10.mStatus = r2;	 Catch:{ all -> 0x0089 }
        goto L_0x001c;
    L_0x0089:
        r0 = move-exception;
        com.tencent.widget.AdapterView.traceEnd();
        throw r0;
    L_0x008e:
        r0 = r10.mAccerleration;	 Catch:{ all -> 0x0089 }
        r2 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r2;
        r10.mCurrVelocity = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mCurrVelocity;	 Catch:{ all -> 0x0089 }
        r2 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r2;
        r0 = r0 / r6;
        r0 = (int) r0;	 Catch:{ all -> 0x0089 }
        goto L_0x001c;
    L_0x009b:
        r0 = r10.mMaxVeloctiy;	 Catch:{ all -> 0x0089 }
        r2 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r2;
        r2 = r10.mMaxVeloctiy;	 Catch:{ all -> 0x0089 }
        r2 = r2 * r9;
        r2 = r2 / r8;
        r0 = r0 - r2;
        r0 = (int) r0;	 Catch:{ all -> 0x0089 }
        goto L_0x001c;
    L_0x00a7:
        r0 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        if (r1 <= r0) goto L_0x00bb;
    L_0x00ab:
        r0 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        r1 = r10.lastMotionY;	 Catch:{ all -> 0x0089 }
        r0 = r0 - r1;
        r1 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r2 = -r0;
        r0 = -r0;
        r1.trackMotionScroll(r2, r0);	 Catch:{ all -> 0x0089 }
        com.tencent.widget.AdapterView.traceEnd();
        goto L_0x0071;
    L_0x00bb:
        r0 = r10.mCurrVelocity;	 Catch:{ all -> 0x0089 }
        r2 = r10.mAccerleration;	 Catch:{ all -> 0x0089 }
        r3 = (float) r1;	 Catch:{ all -> 0x0089 }
        r2 = r2 * r3;
        r0 = r0 - r2;
        r10.mCurrVelocity = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        r0 = (float) r0;	 Catch:{ all -> 0x0089 }
        r2 = r10.mCurrVelocity;	 Catch:{ all -> 0x0089 }
        r3 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        r3 = r3 - r1;
        r3 = (float) r3;	 Catch:{ all -> 0x0089 }
        r2 = r2 * r3;
        r2 = r2 / r6;
        r0 = r0 - r2;
        r0 = (int) r0;	 Catch:{ all -> 0x0089 }
        goto L_0x001c;
    L_0x00d3:
        r0 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        if (r1 <= r0) goto L_0x00e7;
    L_0x00d7:
        r0 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        r1 = r10.lastMotionY;	 Catch:{ all -> 0x0089 }
        r0 = r0 - r1;
        r1 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r2 = -r0;
        r0 = -r0;
        r1.trackMotionScroll(r2, r0);	 Catch:{ all -> 0x0089 }
        com.tencent.widget.AdapterView.traceEnd();
        goto L_0x0071;
    L_0x00e7:
        r0 = (float) r1;
        r2 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        r2 = (float) r2;	 Catch:{ all -> 0x0089 }
        r0 = r0 / r2;
        r0 = com.tencent.util.AnimateUtils.viscousFluid(r0);	 Catch:{ all -> 0x0089 }
        r2 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        r2 = (float) r2;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r2;
        r0 = (int) r0;	 Catch:{ all -> 0x0089 }
        goto L_0x001c;
    L_0x00f7:
        r0 = 400 - r1;
        r10.mDuration = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        if (r0 >= r7) goto L_0x0103;
    L_0x00ff:
        r0 = 100;
        r10.mDuration = r0;	 Catch:{ all -> 0x0089 }
    L_0x0103:
        r0 = android.view.animation.AnimationUtils.currentAnimationTimeMillis();	 Catch:{ all -> 0x0089 }
        r10.mStartTime = r0;	 Catch:{ all -> 0x0089 }
        r0 = 0;
        r10.lastMotionY = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mCurrVelocity;	 Catch:{ all -> 0x0089 }
        r1 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;
        r0 = r0 * r1;
        r1 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r1 = com.tencent.widget.AbsListView.access$1500(r1);	 Catch:{ all -> 0x0089 }
        r1 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 <= 0) goto L_0x013f;
    L_0x011c:
        r0 = r10.mUseViscousFluid;	 Catch:{ all -> 0x0089 }
        if (r0 != 0) goto L_0x013f;
    L_0x0120:
        r0 = 1;
        r10.mStatus = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mDistance;	 Catch:{ all -> 0x0089 }
        r0 = (float) r0;	 Catch:{ all -> 0x0089 }
        r0 = r0 * r6;
        r1 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        r1 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 / r1;
        r10.mCurrVelocity = r0;	 Catch:{ all -> 0x0089 }
        r0 = r10.mCurrVelocity;	 Catch:{ all -> 0x0089 }
        r1 = r10.mDuration;	 Catch:{ all -> 0x0089 }
        r1 = (float) r1;	 Catch:{ all -> 0x0089 }
        r0 = r0 / r1;
        r10.mAccerleration = r0;	 Catch:{ all -> 0x0089 }
    L_0x0135:
        r0 = r10.this$0;	 Catch:{ all -> 0x0089 }
        r0.post(r10);	 Catch:{ all -> 0x0089 }
    L_0x013a:
        com.tencent.widget.AdapterView.traceEnd();
        goto L_0x0071;
    L_0x013f:
        r0 = 3;
        r10.mStatus = r0;	 Catch:{ all -> 0x0089 }
        goto L_0x0135;
    L_0x0143:
        r10.stop();	 Catch:{ all -> 0x0089 }
        goto L_0x013a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.widget.AbsListView$MoveToBottomScroller.run():void");
    }
}
