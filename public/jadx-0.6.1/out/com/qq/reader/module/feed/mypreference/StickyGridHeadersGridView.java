package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.n;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StickyGridHeadersGridView extends GridView implements OnScrollListener, OnItemClickListener, OnItemLongClickListener, OnItemSelectedListener {
    static final String a = StickyGridHeadersGridView.class.getSimpleName();
    private static final String i = ("Error supporting platform " + VERSION.SDK_INT + ".");
    private OnItemClickListener A;
    private OnItemLongClickListener B;
    private OnItemSelectedListener C;
    private e D;
    private OnScrollListener E;
    private int F;
    private View G;
    private Runnable H;
    private int I;
    private int J;
    public a b;
    public b c;
    protected StickyGridHeadersBaseAdapterWrapper d;
    protected boolean e;
    protected int f;
    protected int g;
    boolean h;
    private boolean j;
    private final Rect k;
    private boolean l;
    private boolean m;
    private int n;
    private long o;
    private DataSetObserver p;
    private int q;
    private boolean r;
    private int s;
    private boolean t;
    private float u;
    private int v;
    private boolean w;
    private int x;
    private c y;
    private d z;

    class RuntimePlatformSupportException extends RuntimeException {
        private static final long serialVersionUID = -6512098808936536538L;

        public RuntimePlatformSupportException(Exception exception) {
            super(StickyGridHeadersGridView.i, exception);
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        };
        boolean a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readByte() != (byte) 0;
        }

        public String toString() {
            return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte((byte) (this.a ? 1 : 0));
        }
    }

    private class f {
        private int a;
        final /* synthetic */ StickyGridHeadersGridView c;

        private f(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.c = stickyGridHeadersGridView;
        }

        public void a() {
            this.a = this.c.getWindowAttachCount();
        }

        public boolean b() {
            return this.c.hasWindowFocus() && this.c.getWindowAttachCount() == this.a;
        }
    }

    private class a extends f implements Runnable {
        final /* synthetic */ StickyGridHeadersGridView a;

        private a(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.a = stickyGridHeadersGridView;
            super();
        }

        public void run() {
            View a = this.a.a(this.a.f);
            if (a != null) {
                boolean z;
                long a2 = this.a.b(this.a.f);
                if (!b() || this.a.e) {
                    z = false;
                } else {
                    z = this.a.b(a, a2);
                }
                if (z) {
                    this.a.g = -2;
                    this.a.setPressed(false);
                    a.setPressed(false);
                    return;
                }
                this.a.g = 2;
            }
        }
    }

    final class b implements Runnable {
        final /* synthetic */ StickyGridHeadersGridView a;

        b(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.a = stickyGridHeadersGridView;
        }

        public void run() {
            if (this.a.g == 0) {
                this.a.g = 1;
                View a = this.a.a(this.a.f);
                if (a != null && !this.a.h) {
                    if (this.a.e) {
                        this.a.g = 2;
                        return;
                    }
                    a.setPressed(true);
                    this.a.setPressed(true);
                    this.a.refreshDrawableState();
                    int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    if (this.a.isLongClickable()) {
                        if (this.a.b == null) {
                            this.a.b = new a();
                        }
                        this.a.b.a();
                        this.a.postDelayed(this.a.b, (long) longPressTimeout);
                        return;
                    }
                    this.a.g = 2;
                }
            }
        }
    }

    public interface c {
        void a(AdapterView<?> adapterView, View view, long j);
    }

    public interface d {
        boolean a(AdapterView<?> adapterView, View view, long j);
    }

    private class e extends f implements Runnable {
        int a;
        final /* synthetic */ StickyGridHeadersGridView b;

        private e(StickyGridHeadersGridView stickyGridHeadersGridView) {
            this.b = stickyGridHeadersGridView;
            super();
        }

        public void run() {
            if (!this.b.e && this.b.d != null && this.b.d.getCount() > 0 && this.a != -1 && this.a < this.b.d.getCount() && b()) {
                View a = this.b.a(this.a);
                if (a != null) {
                    this.b.a(a, this.b.b(this.a));
                }
            }
        }
    }

    private static PointerCoords[] a(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        PointerCoords[] pointerCoordsArr = new PointerCoords[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerCoordsArr[i] = new PointerCoords();
            motionEvent.getPointerCoords(i, pointerCoordsArr[i]);
        }
        return pointerCoordsArr;
    }

    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    private static PointerProperties[] b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        PointerProperties[] pointerPropertiesArr = new PointerProperties[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerPropertiesArr[i] = new PointerProperties();
            motionEvent.getPointerProperties(i, pointerPropertiesArr[i]);
        }
        return pointerPropertiesArr;
    }

    private static int[] c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
        }
        return iArr;
    }

    public StickyGridHeadersGridView(Context context) {
        this(context, null);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842865);
    }

    public StickyGridHeadersGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = true;
        this.k = new Rect();
        this.o = -1;
        this.p = new DataSetObserver(this) {
            final /* synthetic */ StickyGridHeadersGridView a;

            {
                this.a = r1;
            }

            public void onChanged() {
                this.a.c();
            }

            public void onInvalidated() {
                this.a.c();
            }
        };
        this.t = true;
        this.x = 1;
        this.F = 0;
        this.h = false;
        super.setOnScrollListener(this);
        setVerticalFadingEdgeEnabled(false);
        if (!this.w) {
            this.v = -1;
        }
        this.I = ViewConfiguration.get(context.getApplicationContext()).getScaledTouchSlop();
    }

    public View a(int i) {
        if (i == -2) {
            return this.G;
        }
        try {
            return (View) getChildAt(i).getTag();
        } catch (Exception e) {
            return null;
        }
    }

    public View getStickiedHeader() {
        return this.G;
    }

    public boolean getStickyHeaderIsTranscluent() {
        return !this.t;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A.onItemClick(adapterView, view, this.d.c(i).b, j);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return this.B.onItemLongClick(adapterView, view, this.d.c(i).b, j);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.C.onItemSelected(adapterView, view, this.d.c(i).b, j);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        this.C.onNothingSelected(adapterView);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.j = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.j;
        return savedState;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.E != null) {
            this.E.onScroll(absListView, i, i2, i3);
        }
        c(i);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.E != null) {
            this.E.onScrollStateChanged(absListView, i);
        }
        this.F = i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View a;
        View view;
        int action = motionEvent.getAction();
        boolean z = this.h;
        if (this.h) {
            a = a(this.f);
            if (this.f == -2) {
                view = a;
            } else {
                view = getChildAt(this.f);
            }
            if (action == 1 || action == 3) {
                this.h = false;
            }
            if (a != null) {
                a.dispatchTouchEvent(a(motionEvent, this.f));
                a.invalidate();
                a.postDelayed(new Runnable(this) {
                    final /* synthetic */ StickyGridHeadersGridView b;

                    public void run() {
                        this.b.invalidate(0, view.getTop(), this.b.getWidth(), view.getTop() + view.getHeight());
                    }
                }, (long) ViewConfiguration.getPressedStateDuration());
                invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
            }
        }
        switch (action & 255) {
            case 0:
                if (this.c == null) {
                    this.c = new b(this);
                }
                postDelayed(this.c, (long) ViewConfiguration.getTapTimeout());
                int y = (int) motionEvent.getY();
                this.u = (float) y;
                this.f = a((float) y);
                if (!(this.f == -1 || this.F == 2)) {
                    view = a(this.f);
                    if (view != null) {
                        if (view.dispatchTouchEvent(a(motionEvent, this.f))) {
                            this.h = true;
                            view.setPressed(true);
                        }
                        view.invalidate();
                        if (this.f != -2) {
                            view = getChildAt(this.f);
                        }
                        invalidate(0, view.getTop(), getWidth(), view.getHeight() + view.getTop());
                    }
                    this.g = 0;
                    return true;
                }
            case 1:
                if (this.g == -2) {
                    this.g = -1;
                    return true;
                } else if (!(this.g == -1 || this.f == -1)) {
                    a = a(this.f);
                    if (!(z || a == null)) {
                        if (this.g != 0) {
                            a.setPressed(false);
                        }
                        if (this.D == null) {
                            this.D = new e();
                        }
                        final e eVar = this.D;
                        eVar.a = this.f;
                        eVar.a();
                        if (this.g == 0 || this.g == 1) {
                            Handler handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.g == 0 ? this.c : this.b);
                            }
                            if (this.e) {
                                this.g = -1;
                            } else {
                                this.g = 1;
                                a.setPressed(true);
                                setPressed(true);
                                if (this.H != null) {
                                    removeCallbacks(this.H);
                                }
                                this.H = new Runnable(this) {
                                    final /* synthetic */ StickyGridHeadersGridView c;

                                    public void run() {
                                        this.c.f = -1;
                                        this.c.H = null;
                                        this.c.g = -1;
                                        a.setPressed(false);
                                        this.c.setPressed(false);
                                        a.invalidate();
                                        this.c.invalidate(0, a.getTop(), this.c.getWidth(), a.getHeight());
                                        if (!this.c.e) {
                                            eVar.run();
                                        }
                                    }
                                };
                                postDelayed(this.H, (long) ViewConfiguration.getPressedStateDuration());
                            }
                        } else if (!this.e) {
                            eVar.run();
                        }
                    }
                    this.g = -1;
                    return true;
                }
                break;
            case 2:
                if (this.f != -1 && Math.abs(motionEvent.getY() - this.u) > ((float) this.I)) {
                    this.g = -1;
                    view = a(this.f);
                    if (view != null) {
                        view.setPressed(false);
                        view.invalidate();
                    }
                    Handler handler2 = getHandler();
                    if (handler2 != null) {
                        handler2.removeCallbacks(this.b);
                    }
                    this.f = -1;
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean a(View view, long j) {
        if (this.y == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.y.a(this, view, j);
        return true;
    }

    public boolean b(View view, long j) {
        boolean a;
        if (this.z != null) {
            a = this.z.a(this, view, j);
        } else {
            a = false;
        }
        if (a) {
            if (view != null) {
                view.sendAccessibilityEvent(2);
            }
            performHapticFeedback(0);
        }
        return a;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.d == null || this.p == null)) {
            this.d.unregisterDataSetObserver(this.p);
        }
        if (!this.m) {
            this.l = true;
        }
        e eVar = (e) listAdapter;
        if (listAdapter instanceof e) {
            listAdapter = (e) listAdapter;
        } else {
            Object obj = eVar;
        }
        this.d = new StickyGridHeadersBaseAdapterWrapper(getContext(), this, listAdapter);
        this.d.registerDataSetObserver(this.p);
        c();
        super.setAdapter(this.d);
    }

    public void setAreHeadersSticky(boolean z) {
        if (z != this.j) {
            this.j = z;
            requestLayout();
        }
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.l = z;
        this.m = true;
    }

    public void setColumnWidth(int i) {
        super.setColumnWidth(i);
        this.n = i;
    }

    public void setHeadersIgnorePadding(boolean z) {
        this.r = z;
    }

    public void setHorizontalSpacing(int i) {
        super.setHorizontalSpacing(i);
        this.s = i;
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.w = true;
        this.v = i;
        if (i != -1 && this.d != null) {
            this.d.a(i);
        }
    }

    public void setOnHeaderClickListener(c cVar) {
        this.y = cVar;
    }

    public void setOnHeaderLongClickListener(d dVar) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.z = dVar;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.A = onItemClickListener;
        super.setOnItemClickListener(this);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.B = onItemLongClickListener;
        super.setOnItemLongClickListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.C = onItemSelectedListener;
        super.setOnItemSelectedListener(this);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.E = onScrollListener;
    }

    public void setStickyHeaderIsTranscluent(boolean z) {
        this.t = !z;
    }

    public void setVerticalSpacing(int i) {
        super.setVerticalSpacing(i);
        this.J = i;
    }

    private int a(float f) {
        if (this.G != null && f <= ((float) this.q)) {
            return -2;
        }
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                View childAt = getChildAt(i);
                int bottom = childAt.getBottom();
                int top = childAt.getTop();
                if (f <= ((float) bottom) && f >= ((float) top)) {
                    return i;
                }
            }
            firstVisiblePosition += this.x;
            i += this.x;
        }
        return -1;
    }

    private int getHeaderHeight() {
        if (this.G != null) {
            return this.G.getMeasuredHeight();
        }
        return 0;
    }

    private long b(int i) {
        if (i == -2) {
            return this.o;
        }
        return this.d.b(getFirstVisiblePosition() + i);
    }

    private void b() {
        if (this.G != null) {
            int makeMeasureSpec;
            int makeMeasureSpec2;
            if (this.r) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            }
            LayoutParams layoutParams = this.G.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            } else {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            }
            this.G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.G.measure(makeMeasureSpec, makeMeasureSpec2);
            if (this.r) {
                this.G.layout(getLeft(), 0, getRight(), this.G.getMeasuredHeight());
            } else {
                this.G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.G.getMeasuredHeight());
            }
        }
    }

    private void c() {
        this.q = 0;
        c(null);
        this.o = Long.MIN_VALUE;
    }

    private void c(int i) {
        if (this.d != null && this.d.getCount() != 0 && this.j && getChildAt(0) != null) {
            long b;
            int i2 = i - this.x;
            if (i2 < 0) {
                i2 = i;
            }
            int i3 = this.x + i;
            if (i3 >= this.d.getCount()) {
                i3 = i;
            }
            if (this.J == 0) {
                b = this.d.b(i);
                i3 = i;
            } else if (this.J < 0) {
                this.d.b(i);
                if (getChildAt(this.x).getTop() <= 0) {
                    b = this.d.b(i3);
                } else {
                    b = this.d.b(i);
                    i3 = i;
                }
            } else {
                i3 = getChildAt(0).getTop();
                if (i3 <= 0 || i3 >= this.J) {
                    b = this.d.b(i);
                    i3 = i;
                } else {
                    b = this.d.b(i2);
                    i3 = i2;
                }
            }
            if (this.o != b) {
                c(this.d.a(i3, this.G, (ViewGroup) this));
                b();
                this.o = b;
            }
            int childCount = getChildCount();
            if (childCount != 0) {
                View view = null;
                i2 = 99999;
                int i4 = 0;
                while (i4 < childCount) {
                    View view2;
                    View childAt = super.getChildAt(i4);
                    if (this.l) {
                        i3 = childAt.getTop() - getPaddingTop();
                    } else {
                        i3 = childAt.getTop();
                    }
                    if (i3 < 0) {
                        view2 = view;
                    } else if (this.d.getItemId(getPositionForView(childAt)) != -1 || i3 >= r0) {
                        view2 = view;
                    } else {
                        i2 = i3;
                        view2 = childAt;
                    }
                    i4 = this.x + i4;
                    view = view2;
                }
                i2 = getHeaderHeight();
                if (view == null) {
                    this.q = i2;
                    if (this.l) {
                        this.q += getPaddingTop();
                    }
                } else if (i == 0 && super.getChildAt(0).getTop() > 0 && !this.l) {
                    this.q = 0;
                } else if (this.l) {
                    this.q = Math.min(view.getTop(), getPaddingTop() + i2);
                    this.q = this.q < getPaddingTop() ? i2 + getPaddingTop() : this.q;
                } else {
                    this.q = Math.min(view.getTop(), i2);
                    if (this.q >= 0) {
                        i2 = this.q;
                    }
                    this.q = i2;
                }
            }
        }
    }

    private void c(View view) {
        b(this.G);
        a(view);
        this.G = view;
    }

    private MotionEvent a(MotionEvent motionEvent, int i) {
        if (i == -2) {
            return motionEvent;
        }
        MotionEvent obtain;
        long downTime = motionEvent.getDownTime();
        long eventTime = motionEvent.getEventTime();
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        c(motionEvent);
        int metaState = motionEvent.getMetaState();
        float xPrecision = motionEvent.getXPrecision();
        float yPrecision = motionEvent.getYPrecision();
        int deviceId = motionEvent.getDeviceId();
        int edgeFlags = motionEvent.getEdgeFlags();
        int d = n.d(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float size = motionEvent.getSize();
        motionEvent.getPressure();
        View childAt = getChildAt(i);
        if (VERSION.SDK_INT >= 9) {
            int flags = motionEvent.getFlags();
            PointerCoords[] a = a(motionEvent);
            for (int i2 = 0; i2 < pointerCount; i2++) {
                PointerCoords pointerCoords = a[i2];
                pointerCoords.y -= (float) childAt.getTop();
            }
            obtain = MotionEvent.obtain(downTime, eventTime, action, pointerCount, b(motionEvent), a, metaState, motionEvent.getButtonState(), xPrecision, yPrecision, deviceId, edgeFlags, d, flags);
        } else if (VERSION.SDK_INT >= 14) {
            obtain = MotionEvent.obtain(downTime, eventTime, action, (float) pointerCount, x, y, size, metaState, xPrecision, yPrecision, deviceId, edgeFlags);
        } else {
            obtain = null;
        }
        return obtain;
    }

    protected void dispatchDraw(Canvas canvas) {
        Object obj;
        if (this.G != null && this.j && this.G.getVisibility() == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        int headerHeight = getHeaderHeight();
        int i = this.q - headerHeight;
        if (obj != null && this.t) {
            if (this.r) {
                this.k.left = 0;
                this.k.right = getWidth();
            } else {
                this.k.left = getPaddingLeft();
                this.k.right = getWidth() - getPaddingRight();
            }
            this.k.top = this.q;
            this.k.bottom = getHeight();
            canvas.save();
            canvas.clipRect(this.k);
        }
        super.dispatchDraw(canvas);
        List arrayList = new ArrayList();
        int i2 = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        while (firstVisiblePosition <= getLastVisiblePosition()) {
            if (getItemIdAtPosition(firstVisiblePosition) == -1) {
                arrayList.add(Integer.valueOf(i2));
            }
            firstVisiblePosition += this.x;
            i2 += this.x;
        }
        int i3 = 0;
        while (i3 < arrayList.size()) {
            View childAt = getChildAt(((Integer) arrayList.get(i3)).intValue());
            try {
                View view = (View) childAt.getTag();
                Object obj2 = (((long) ((HeaderFillerView) childAt).getHeaderId()) == this.o && childAt.getTop() < 0 && this.j) ? 1 : null;
                if (view.getVisibility() == 0 && obj2 == null) {
                    if (this.r) {
                        i2 = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
                    } else {
                        i2 = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
                    }
                    int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                    view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    view.measure(i2, makeMeasureSpec);
                    if (this.r) {
                        view.layout(getLeft(), 0, getRight(), childAt.getHeight());
                    } else {
                        view.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), childAt.getHeight());
                    }
                    if (this.r) {
                        this.k.left = 0;
                        this.k.right = getWidth();
                    } else {
                        this.k.left = getPaddingLeft();
                        this.k.right = getWidth() - getPaddingRight();
                    }
                    this.k.bottom = childAt.getBottom();
                    this.k.top = childAt.getTop();
                    canvas.save();
                    canvas.clipRect(this.k);
                    if (this.r) {
                        canvas.translate(0.0f, (float) childAt.getTop());
                    } else {
                        canvas.translate((float) getPaddingLeft(), (float) childAt.getTop());
                    }
                    view.draw(canvas);
                    canvas.restore();
                }
                i3++;
            } catch (Exception e) {
                return;
            }
        }
        if (obj != null && this.t) {
            canvas.restore();
        } else if (obj == null) {
            return;
        }
        if (this.r) {
            firstVisiblePosition = getWidth();
        } else {
            firstVisiblePosition = (getWidth() - getPaddingLeft()) - getPaddingRight();
        }
        if (this.G.getWidth() != firstVisiblePosition) {
            if (this.r) {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec(getWidth(), 1073741824);
            } else {
                firstVisiblePosition = MeasureSpec.makeMeasureSpec((getWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
            }
            i2 = MeasureSpec.makeMeasureSpec(0, 0);
            this.G.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.G.measure(firstVisiblePosition, i2);
            if (this.r) {
                this.G.layout(getLeft(), 0, getRight(), this.G.getHeight());
            } else {
                this.G.layout(getLeft() + getPaddingLeft(), 0, getRight() - getPaddingRight(), this.G.getHeight());
            }
        }
        if (this.r) {
            this.k.left = 0;
            this.k.right = getWidth();
        } else {
            this.k.left = getPaddingLeft();
            this.k.right = getWidth() - getPaddingRight();
        }
        this.k.bottom = i + headerHeight;
        if (this.l) {
            this.k.top = getPaddingTop();
        } else {
            this.k.top = 0;
        }
        canvas.save();
        canvas.clipRect(this.k);
        if (this.r) {
            canvas.translate(0.0f, (float) i);
        } else {
            canvas.translate((float) getPaddingLeft(), (float) i);
        }
        if (this.q != headerHeight) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (this.q * 255) / headerHeight, 31);
        }
        this.G.draw(canvas);
        if (this.q != headerHeight) {
            canvas.restore();
        }
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1;
        if (this.v == -1) {
            if (this.n > 0) {
                int max = Math.max((MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight(), 0);
                int i4 = max / this.n;
                if (i4 > 0) {
                    while (i4 != 1 && (this.n * i4) + ((i4 - 1) * this.s) > max) {
                        i4--;
                    }
                    i3 = i4;
                }
            } else {
                i3 = 2;
            }
            this.x = i3;
        } else {
            this.x = this.v;
        }
        if (this.d != null) {
            this.d.a(this.x);
        }
        b();
        super.onMeasure(i, i2);
    }

    void a(View view) {
        if (view != null) {
            try {
                View.class.getDeclaredField("mAttachInfo").setAccessible(true);
                Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", new Class[]{Class.forName("android.view.View$AttachInfo"), Integer.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[]{r0.get(this), Integer.valueOf(8)});
            } catch (Exception e) {
                throw new RuntimePlatformSupportException(e);
            } catch (Exception e2) {
                throw new RuntimePlatformSupportException(e2);
            } catch (Exception e22) {
                throw new RuntimePlatformSupportException(e22);
            } catch (Exception e222) {
                throw new RuntimePlatformSupportException(e222);
            } catch (Exception e2222) {
                throw new RuntimePlatformSupportException(e2222);
            } catch (Exception e22222) {
                throw new RuntimePlatformSupportException(e22222);
            }
        }
    }

    void b(View view) {
        if (view != null) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, new Object[0]);
            } catch (Exception e) {
                throw new RuntimePlatformSupportException(e);
            } catch (Exception e2) {
                throw new RuntimePlatformSupportException(e2);
            } catch (Exception e22) {
                throw new RuntimePlatformSupportException(e22);
            } catch (Exception e222) {
                throw new RuntimePlatformSupportException(e222);
            }
        }
    }
}
