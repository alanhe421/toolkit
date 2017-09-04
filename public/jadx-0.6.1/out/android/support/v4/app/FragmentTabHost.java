package android.support.v4.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<a> a = new ArrayList();
    private FrameLayout b;
    private Context c;
    private k d;
    private int e;
    private OnTabChangeListener f;
    private a g;
    private boolean h;

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
        String a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.a + "}";
        }
    }

    static final class a {
        private final String a;
        private final Class<?> b;
        private final Bundle c;
        private Fragment d;
    }

    public FragmentTabHost(Context context) {
        super(context, null);
        a(context, null);
    }

    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void a(Context context) {
        if (findViewById(16908307) == null) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new LayoutParams(-1, -1));
            View tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            tabWidget = new FrameLayout(context);
            tabWidget.setId(16908305);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(0, 0, 0.0f));
            tabWidget = new FrameLayout(context);
            this.b = tabWidget;
            this.b.setId(this.e);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, k kVar) {
        a(context);
        super.setup();
        this.c = context;
        this.d = kVar;
        a();
    }

    public void setup(Context context, k kVar, int i) {
        a(context);
        super.setup();
        this.c = context;
        this.d = kVar;
        this.e = i;
        a();
        this.b.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.e);
            }
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        m mVar = null;
        for (int i = 0; i < this.a.size(); i++) {
            a aVar = (a) this.a.get(i);
            aVar.d = this.d.a(aVar.a);
            if (!(aVar.d == null || aVar.d.isDetached())) {
                if (aVar.a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (mVar == null) {
                        mVar = this.d.a();
                    }
                    mVar.d(aVar.d);
                }
            }
        }
        this.h = true;
        m a = a(currentTabTag, mVar);
        if (a != null) {
            a.a();
            this.d.b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTabTag();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.a);
    }

    public void onTabChanged(String str) {
        if (this.h) {
            m a = a(str, null);
            if (a != null) {
                a.a();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(str);
        }
    }

    private m a(String str, m mVar) {
        a aVar = null;
        int i = 0;
        while (i < this.a.size()) {
            a aVar2 = (a) this.a.get(i);
            if (!aVar2.a.equals(str)) {
                aVar2 = aVar;
            }
            i++;
            aVar = aVar2;
        }
        if (aVar == null) {
            throw new IllegalStateException("No tab known for tag " + str);
        }
        if (this.g != aVar) {
            if (mVar == null) {
                mVar = this.d.a();
            }
            if (!(this.g == null || this.g.d == null)) {
                mVar.d(this.g.d);
            }
            if (aVar != null) {
                if (aVar.d == null) {
                    aVar.d = Fragment.instantiate(this.c, aVar.b.getName(), aVar.c);
                    mVar.a(this.e, aVar.d, aVar.a);
                } else {
                    mVar.e(aVar.d);
                }
            }
            this.g = aVar;
        }
        return mVar;
    }
}
