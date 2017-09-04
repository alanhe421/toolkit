package com.nhaarman.listviewanimations.itemmanipulation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nhaarman.listviewanimations.b.d;
import com.nhaarman.listviewanimations.itemmanipulation.b.e;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.b;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.f;
import java.util.Collection;
import java.util.HashSet;

public class DynamicListView extends ListView {
    private final a a;
    private b b;
    private e c;
    private a d;
    private com.nhaarman.listviewanimations.itemmanipulation.a.a<Object> e;
    private com.nhaarman.listviewanimations.itemmanipulation.b.a.a f;

    private class a implements OnScrollListener {
        final /* synthetic */ DynamicListView a;
        private final Collection<OnScrollListener> b;

        private a(DynamicListView dynamicListView) {
            this.a = dynamicListView;
            this.b = new HashSet();
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            for (OnScrollListener onScrollStateChanged : this.b) {
                onScrollStateChanged.onScrollStateChanged(absListView, i);
            }
            if (i == 1 && (this.a.c instanceof com.nhaarman.listviewanimations.itemmanipulation.b.a.b)) {
                ((com.nhaarman.listviewanimations.itemmanipulation.b.a.b) this.a.c).i();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            for (OnScrollListener onScroll : this.b) {
                onScroll.onScroll(absListView, i, i2, i3);
            }
        }

        public void a(OnScrollListener onScrollListener) {
            this.b.add(onScrollListener);
        }
    }

    public DynamicListView(Context context) {
        this(context, null);
    }

    public DynamicListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, Resources.getSystem().getIdentifier("listViewStyle", "attr", "android"));
    }

    public DynamicListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a();
        super.setOnScrollListener(this.a);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (!(onTouchListener instanceof e)) {
            super.setOnTouchListener(onTouchListener);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.a.a(onScrollListener);
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2;
        this.f = null;
        if (listAdapter instanceof BaseAdapter) {
            BaseAdapter baseAdapter = (BaseAdapter) listAdapter;
            while (baseAdapter instanceof com.nhaarman.listviewanimations.b) {
                if (baseAdapter instanceof com.nhaarman.listviewanimations.itemmanipulation.b.a.a) {
                    this.f = (com.nhaarman.listviewanimations.itemmanipulation.b.a.a) baseAdapter;
                }
                baseAdapter = ((com.nhaarman.listviewanimations.b) baseAdapter).a();
            }
            if (baseAdapter instanceof d) {
                this.e = new com.nhaarman.listviewanimations.itemmanipulation.a.a((BaseAdapter) listAdapter);
                this.e.a((ListView) this);
                listAdapter2 = this.e;
                super.setAdapter(listAdapter2);
                if (this.b != null) {
                    this.b.a(listAdapter);
                }
            }
        }
        listAdapter2 = listAdapter;
        super.setAdapter(listAdapter2);
        if (this.b != null) {
            this.b.a(listAdapter);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            return onTouchEvent(motionEvent);
        }
        boolean z;
        if (((this.c instanceof com.nhaarman.listviewanimations.itemmanipulation.b.a.b) && ((com.nhaarman.listviewanimations.itemmanipulation.b.a.b) this.c).h()) || this.b == null) {
            z = false;
        } else {
            this.b.a(motionEvent);
            z = this.b.a();
            if (z) {
                this.d = this.b;
                a(this.c, motionEvent);
            }
        }
        if (this.d == null && this.c != null) {
            this.c.a(motionEvent);
            z = this.c.f();
            if (z) {
                this.d = this.c;
                a(this.b, motionEvent);
            }
        }
        if (z) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            super.onTouchEvent(obtain);
        }
        if (z || super.dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d != null) {
            this.d.a(motionEvent);
        }
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.d = null;
        }
        if (this.d != null || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    private void a(a aVar, MotionEvent motionEvent) {
        if (aVar != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            aVar.a(obtain);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.b != null) {
            this.b.a(canvas);
        }
    }

    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setDraggableManager(com.nhaarman.listviewanimations.itemmanipulation.dragdrop.d dVar) {
        if (this.b != null) {
            this.b.a(dVar);
        }
    }

    public void setOnItemMovedListener(f fVar) {
        if (this.b != null) {
            this.b.a(fVar);
        }
    }

    public void setScrollSpeed(float f) {
        if (this.b != null) {
            this.b.a(f);
        }
    }

    public void setDismissableManager(com.nhaarman.listviewanimations.itemmanipulation.b.a aVar) {
        if (this.c != null) {
            this.c.a(aVar);
        }
    }

    public void fling(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void setSwipeTouchChild(int i) {
        if (this.c != null) {
            this.c.c(i);
        }
    }

    public void setMinimumAlpha(float f) {
        if (this.c != null) {
            this.c.a(f);
        }
    }
}
