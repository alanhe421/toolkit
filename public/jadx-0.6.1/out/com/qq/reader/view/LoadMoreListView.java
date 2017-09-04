package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ListView;

public class LoadMoreListView extends ListView {
    private OnScrollListener a = null;
    private View b;
    private View c;
    private ViewGroup d;
    private ViewGroup e;
    private LoadStatus f = LoadStatus.IDLE;
    private int g;
    private int h;
    private a i;
    private b j;

    public enum LoadStatus {
        LOADING,
        IDLE,
        LOADEND
    }

    public interface a {
        void a(View view);
    }

    public interface b {
        void a(MotionEvent motionEvent);
    }

    public LoadMoreListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public LoadMoreListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public LoadMoreListView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new OnScrollListener(this) {
            final /* synthetic */ LoadMoreListView a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.a.getLastVisiblePosition() == this.a.getAdapter().getCount() - 1 && this.a.f == LoadStatus.IDLE) {
                    this.a.b();
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        };
        super.setOnScrollListener(this.a);
    }

    private void b() {
        this.f = LoadStatus.LOADING;
        if (this.b != null) {
            this.b.setVisibility(0);
        }
        if (this.i != null) {
            this.i.a(this.b);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        super.setOnScrollListener(onScrollListener);
    }

    public void setLoadMoreView(View view) {
        if (view == null) {
            throw new IllegalAccessError("can't add an null loadView");
        }
        if (this.d != null) {
            removeFooterView(this.d);
        }
        this.b = view;
        this.d = new FrameLayout(getContext());
        this.d.addView(this.b);
        addFooterView(this.d);
    }

    public void setLoadEmtyView(View view) {
        if (view == null) {
            throw new IllegalAccessError("can't add an null loadView");
        }
        if (this.e != null) {
            removeFooterView(this.e);
        }
        this.c = view;
        this.e = new FrameLayout(getContext());
        this.e.addView(this.c);
        addFooterView(this.e);
    }

    public void setLoadEmtyViewVisibility(int i) {
        this.c.setVisibility(i);
    }

    public View getLoadMoreView() {
        return this.b;
    }

    public OnScrollListener getScrollListener() {
        return this.a;
    }

    public void setOnloadMoreListenser(a aVar) {
        this.i = aVar;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.j != null) {
            this.j.a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setDispatchTouchListener(b bVar) {
        this.j = bVar;
    }

    public int getLastFirstPos() {
        return this.g;
    }

    public int getLastScrollPosY() {
        return this.h;
    }
}
