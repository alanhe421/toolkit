package com.tencent.widget;

import android.annotation.TargetApi;
import android.view.VelocityTracker;
import android.view.View;
import com.tencent.util.VersionUtils;

class AbsListView$FlingRunnable implements Runnable {
    private static final int FLYWHEEL_TIMEOUT = 40;
    private final Runnable mCheckFlywheel = new Runnable() {
        @TargetApi(8)
        public void run() {
            int access$1100 = AbsListView.access$1100(AbsListView$FlingRunnable.this.this$0);
            VelocityTracker access$1200 = AbsListView.access$1200(AbsListView$FlingRunnable.this.this$0);
            OverScroller access$1300 = AbsListView$FlingRunnable.this.mScroller;
            if (access$1200 != null && access$1100 != -1) {
                access$1200.computeCurrentVelocity(1000, (float) AbsListView.access$1400(AbsListView$FlingRunnable.this.this$0));
                float f = VersionUtils.isrFroyo() ? -access$1200.getYVelocity(access$1100) : -access$1200.getYVelocity();
                if (Math.abs(f) < ((float) AbsListView.access$1500(AbsListView$FlingRunnable.this.this$0)) || !access$1300.isScrollingInDirection(0.0f, f)) {
                    AbsListView$FlingRunnable.this.endFling();
                    AbsListView$FlingRunnable.this.this$0.mTouchMode = 3;
                    AbsListView$FlingRunnable.this.this$0.reportScrollStateChange(1);
                    return;
                }
                AbsListView$FlingRunnable.this.this$0.postDelayed(this, 40);
            }
        }
    };
    private int mLastFlingY;
    private final OverScroller mScroller;
    final /* synthetic */ AbsListView this$0;

    AbsListView$FlingRunnable(AbsListView absListView) {
        this.this$0 = absListView;
        this.mScroller = new OverScroller(absListView.getContext());
    }

    void start(int i) {
        int i2 = i < 0 ? Integer.MAX_VALUE : 0;
        this.mLastFlingY = i2;
        this.mScroller.fling(0, i2, 0, i, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        this.this$0.mTouchMode = 4;
        this.this$0.post(this);
        if (AbsListView.access$1600(this.this$0) == null) {
            AbsListView.access$1602(this.this$0, AbsListView.access$1700(this.this$0, "AbsListView-fling"));
        }
    }

    void startSpringback(int i) {
        if (this.mScroller.springBack(0, this.this$0.getScrollY(), i, i, i, i)) {
            this.this$0.mTouchMode = 6;
            this.this$0.invalidate();
            this.this$0.post(this);
            return;
        }
        this.this$0.mTouchMode = -1;
        this.this$0.reportScrollStateChange(0);
    }

    void startOverfling(int i) {
        this.mScroller.fling(0, this.this$0.getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, this.this$0.getHeight());
        this.this$0.mTouchMode = 6;
        this.this$0.invalidate();
        this.this$0.post(this);
    }

    @TargetApi(9)
    void edgeReached(int i) {
        int i2;
        OverScroller overScroller = this.mScroller;
        if (i > 0) {
            i2 = this.this$0.mTopOverflingDistance;
        } else {
            i2 = this.this$0.mBottomOverflingDistance;
        }
        overScroller.notifyVerticalEdgeReached(i, 0, i2);
        i2 = this.this$0.getOverScrollMode();
        if (i2 == 0 || (i2 == 1 && !AbsListView.access$1800(this.this$0))) {
            this.this$0.mTouchMode = 6;
            i2 = (int) this.mScroller.getCurrVelocity();
            if (AbsListView.access$1900(this.this$0) != null) {
                if (i > 0) {
                    AbsListView.access$1900(this.this$0).onAbsorb(i2);
                } else {
                    AbsListView.access$2000(this.this$0).onAbsorb(i2);
                }
            }
        } else {
            this.this$0.mTouchMode = -1;
            if (this.this$0.mPositionScroller != null) {
                this.this$0.mPositionScroller.stop();
            }
            if (AbsListView.access$2100(this.this$0) != null) {
                AbsListView.access$2100(this.this$0).stop();
            }
        }
        this.this$0.invalidate();
        this.this$0.post(this);
    }

    void startScroll(int i, int i2) {
        int i3 = i < 0 ? Integer.MAX_VALUE : 0;
        this.mLastFlingY = i3;
        this.mScroller.startScroll(0, i3, 0, i, i2);
        this.this$0.mTouchMode = 4;
        this.this$0.post(this);
    }

    void endFling() {
        this.this$0.mTouchMode = -1;
        this.this$0.removeCallbacks(this);
        this.this$0.removeCallbacks(this.mCheckFlywheel);
        this.this$0.reportScrollStateChange(0);
        AbsListView.access$2200(this.this$0);
        this.mScroller.abortAnimation();
        if (AbsListView.access$1600(this.this$0) != null) {
            AbsListView.access$1602(this.this$0, AbsListView.access$2300(this.this$0, AbsListView.access$1600(this.this$0)));
        }
    }

    void flywheelTouch() {
        this.this$0.postDelayed(this.mCheckFlywheel, 40);
    }

    @TargetApi(9)
    public void run() {
        int currVelocity;
        Object obj = 1;
        switch (this.this$0.mTouchMode) {
            case 3:
                if (this.mScroller.isFinished()) {
                    return;
                }
                break;
            case 4:
                break;
            case 6:
                OverScroller overScroller = this.mScroller;
                if (overScroller.computeScrollOffset()) {
                    int scrollY = this.this$0.getScrollY();
                    int currY = overScroller.getCurrY();
                    if (this.this$0.overScrollBy(0, currY - scrollY, 0, scrollY, 0, 0, 0, this.this$0.mOverscrollDistance, false)) {
                        Object obj2;
                        Object obj3 = (scrollY > 0 || currY <= 0) ? null : 1;
                        if (scrollY < 0 || currY >= 0) {
                            obj2 = null;
                        } else {
                            obj2 = 1;
                        }
                        if (obj3 == null && obj2 == null) {
                            startSpringback(0);
                            return;
                        }
                        currVelocity = (int) overScroller.getCurrVelocity();
                        if (obj2 != null) {
                            currVelocity = -currVelocity;
                        }
                        overScroller.abortAnimation();
                        start(currVelocity);
                        return;
                    }
                    this.this$0.invalidate();
                    this.this$0.post(this);
                    return;
                }
                endFling();
                return;
            default:
                endFling();
                return;
        }
        AdapterView.traceBegin("AbsListView.FlingRunable.onfling");
        try {
            if (this.this$0.mDataChanged) {
                this.this$0.layoutChildren();
            }
            if (this.this$0.mItemCount == 0 || this.this$0.getChildCount() == 0) {
                endFling();
                return;
            }
            int min;
            OverScroller overScroller2 = this.mScroller;
            boolean computeScrollOffset = overScroller2.computeScrollOffset();
            int currY2 = overScroller2.getCurrY();
            currVelocity = this.mLastFlingY - currY2;
            if (currVelocity > 0) {
                this.this$0.mMotionPosition = this.this$0.mFirstPosition;
                this.this$0.mMotionViewOriginalTop = this.this$0.getChildAt(0).getTop();
                min = Math.min(((this.this$0.getHeight() - AbsListView.access$2400(this.this$0)) - AbsListView.access$2500(this.this$0)) - 1, currVelocity);
            } else {
                min = this.this$0.getChildCount() - 1;
                this.this$0.mMotionPosition = this.this$0.mFirstPosition + min;
                this.this$0.mMotionViewOriginalTop = this.this$0.getChildAt(min).getTop();
                min = Math.max(-(((this.this$0.getHeight() - AbsListView.access$2600(this.this$0)) - AbsListView.access$2700(this.this$0)) - 1), currVelocity);
            }
            View childAt = this.this$0.getChildAt(this.this$0.mMotionPosition - this.this$0.mFirstPosition);
            if (childAt != null) {
                currVelocity = childAt.getTop();
            } else {
                currVelocity = 0;
            }
            if (!this.this$0.trackMotionScroll(min, min) || min == 0) {
                obj = null;
            }
            if (obj != null) {
                if (childAt != null) {
                    currY2 = -(min - (childAt.getTop() - currVelocity));
                    if (computeScrollOffset) {
                        edgeReached(currY2);
                        currY2 = this.mScroller.getCurrY();
                    }
                    this.this$0.overScrollBy(0, currY2, 0, this.this$0.getScrollY(), 0, 0, 0, this.this$0.mOverscrollDistance, false);
                }
                AdapterView.traceEnd();
                return;
            }
            if (computeScrollOffset && obj == null) {
                this.this$0.invalidate();
                this.mLastFlingY = currY2;
                this.this$0.post(this);
            } else {
                endFling();
            }
            AdapterView.traceEnd();
        } finally {
            AdapterView.traceEnd();
        }
    }
}
