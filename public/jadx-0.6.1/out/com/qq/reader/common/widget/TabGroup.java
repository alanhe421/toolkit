package com.qq.reader.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TabGroup extends LinearLayout {
    private a a;
    private int b = -1;

    public interface a {
        void a(int i, int i2);
    }

    private class b implements OnClickListener {
        final /* synthetic */ TabGroup a;
        private final int b;

        private b(TabGroup tabGroup, int i) {
            this.a = tabGroup;
            this.b = i;
        }

        public void onClick(View view) {
            if (this.a.a != null) {
                if (this.a.b != this.b) {
                    this.a.a(this.a.b).setSelected(false);
                }
                this.a.a(this.b).setSelected(true);
                this.a.a.a(this.a.b, this.b);
                this.a.b = this.b;
            }
        }
    }

    public TabGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addView(View view, LayoutParams layoutParams) {
        a(view);
        super.addView(view, layoutParams);
        view.setClickable(true);
        view.setOnClickListener(new b(getTabCount() - 1));
    }

    public void addView(View view) {
        a(view);
        super.addView(view);
        view.setClickable(true);
        view.setOnClickListener(new b(getTabCount() - 1));
    }

    private void a(View view) {
        if (view.getLayoutParams() == null) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
            layoutParams.setMargins(0, 0, 0, 0);
            view.setLayoutParams(layoutParams);
        }
        view.requestFocus();
        view.setFocusable(true);
    }

    public int getTabCount() {
        return getChildCount();
    }

    public View a(int i) {
        return getChildAt(i);
    }

    public void setCurrentTab(int i) {
        if (i >= 0 && i < getTabCount() && i != this.b) {
            if (this.a != null) {
                if (this.b != -1) {
                    a(this.b).setSelected(false);
                }
                a(i).setSelected(true);
                this.a.a(this.b, i);
                this.b = i;
            }
            if (isShown()) {
                sendAccessibilityEvent(4);
            }
        }
    }

    public void setOnTabChangedListener(a aVar) {
        this.a = aVar;
    }
}
