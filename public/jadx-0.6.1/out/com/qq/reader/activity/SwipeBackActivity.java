package com.qq.reader.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.widget.swipBackView.SwipeBackLayout;
import com.qq.reader.widget.swipBackView.a;
import com.qq.reader.widget.swipBackView.d;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

public class SwipeBackActivity extends FragmentActivity {
    protected static Stack<WeakReference<Activity>> mActivityStack = new Stack();
    private final String TAG = SwipeBackActivity.class.getSimpleName();
    private a mHelper;
    private boolean mIsaddSwipView = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WeakReference weakReference = null;
        if (!mActivityStack.isEmpty()) {
            weakReference = (WeakReference) mActivityStack.peek();
            c.d(this.TAG, "onCreate   parent =" + weakReference.get());
        }
        this.mHelper = new a(weakReference, this);
        this.mHelper.a();
        mActivityStack.add(new WeakReference(this));
        if (!isLayoutFillWindow()) {
            setSwipeBackEnable(false);
        }
    }

    protected boolean isLayoutFillWindow() {
        return true;
    }

    protected void onStart() {
        super.onStart();
        if (!this.mIsaddSwipView) {
            this.mHelper.c();
            if (isLayoutFillWindow()) {
                getSwipeBackLayout().post(new Runnable(this) {
                    final /* synthetic */ SwipeBackActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        d.a(this.a);
                    }
                });
            }
            this.mIsaddSwipView = true;
        }
    }

    public View findViewById(int i) {
        View findViewById = super.findViewById(i);
        if (findViewById != null || this.mHelper == null) {
            return findViewById;
        }
        return this.mHelper.a(i);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return this.mHelper.d();
    }

    public void setSwipeBackEnable(boolean z) {
        getSwipeBackLayout().setEnableGesture(z);
    }

    public void scrollToFinishActivity() {
        d.a(this, null);
        getSwipeBackLayout().a();
    }

    public void finish() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        c.e(this.TAG, "finish->View state : " + swipeBackLayout.getViewDragState());
        c.e(this.TAG, "finish->activity isWaitingForFinish : " + swipeBackLayout.b());
        if (swipeBackLayout.getViewDragState() != 2 || swipeBackLayout.b()) {
            if (!mActivityStack.isEmpty()) {
                Iterator it = mActivityStack.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (this == weakReference.get()) {
                        mActivityStack.remove(weakReference);
                        break;
                    }
                }
            }
            Activity b = this.mHelper.b();
            if (b != null) {
                ViewGroup viewGroup = (ViewGroup) b.getWindow().getDecorView();
                c.d(this.TAG, "view.getTranslationX() = " + viewGroup.getTranslationX());
                if (viewGroup.getTranslationX() != 0.0f) {
                    if (swipeBackLayout.getViewDragState() == 2) {
                        c.e(this.TAG, "mlastView is in animating");
                    } else {
                        viewGroup.setTranslationX(0.0f);
                    }
                }
            }
            super.finish();
        }
    }
}
