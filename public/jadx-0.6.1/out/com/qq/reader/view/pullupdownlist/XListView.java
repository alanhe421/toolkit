package com.qq.reader.view.pullupdownlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class XListView extends ListView implements OnScrollListener {
    int a = 0;
    private float b = -1.0f;
    private Scroller c;
    private OnScrollListener d;
    private a e;
    private XListViewHeader f;
    private RelativeLayout g;
    private TextView h;
    private boolean i = true;
    private boolean j = false;
    private XListViewFooter k;
    private View l;
    private boolean m;
    private boolean n;
    private boolean o = false;
    private boolean p = true;
    private boolean q = false;
    private int r;
    private OnClickListener s = new 1(this);

    public XListView(Context context) {
        super(context);
        a(context);
    }

    public XListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public XListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = new Scroller(context);
        super.setOnScrollListener(this);
        this.f = new XListViewHeader(context);
        this.g = (RelativeLayout) this.f.findViewById(R.id.xlistview_header_content);
        this.h = (TextView) this.f.findViewById(R.id.xlistview_header_time);
        this.k = new XListViewFooter(context);
        setOverScrollMode(2);
    }

    public void setXListFooter(XListViewFooter xListViewFooter) {
        this.k = xListViewFooter;
    }

    public XListViewFooter getXListFooter() {
        return this.k;
    }

    public void a() {
        if (getLastVisiblePosition() >= (getAdapter().getCount() - getHeaderViewsCount()) - getFooterViewsCount() && this.k.getState() != 3) {
            h();
        }
    }

    @Deprecated
    public void setBottomFooterView(View view) {
        this.l = view;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.p && this.m && !this.o) {
            this.o = true;
            addFooterView(this.k);
        } else if (!(this.p && this.m)) {
            this.o = false;
            removeFooterView(this.k);
        }
        if (this.q) {
            addFooterView(this.k);
        }
        if (this.l != null) {
            addFooterView(this.l);
        }
        super.setAdapter(listAdapter);
    }

    public void setShowFooter(boolean z) {
        this.p = z;
    }

    public void setPullRefreshEnable(boolean z) {
        this.i = z;
        if (this.i) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(4);
        }
    }

    public void setPullLoadEnable(boolean z) {
        this.m = z;
        if (this.m) {
            this.n = false;
            this.k.setState(0);
            return;
        }
        this.k.setOnClickListener(null);
    }

    public void b() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(1);
        this.k.setOnClickListener(null);
    }

    public void c() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(3);
        this.k.setOnClickListener(null);
    }

    public void d() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(4);
        this.k.setOnClickListener(this.s);
    }

    public void e() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(0);
        this.k.setOnClickListener(null);
    }

    public void f() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(6);
        this.k.setOnClickListener(this.s);
    }

    public void g() {
        if (this.n) {
            this.n = false;
        }
        this.k.setState(5);
        this.k.setOnClickListener(null);
    }

    public void setRefreshTime(String str) {
        this.h.setText(str);
    }

    private void h() {
        if (this.m && !this.n) {
            this.n = true;
            this.k.setState(2);
            if (this.e != null) {
                this.e.a();
            }
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && getLastVisiblePosition() == this.r - 1 && this.k.getState() != 3 && this.k.getState() != 5) {
            h();
        }
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.r = i3;
        this.a = (((i + i2) - 1) - 1) - 1;
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }

    public void setFootViewBgColor(int i) {
        if (this.k != null) {
            this.k.setBackgroundColor(i);
        }
    }

    public void setXListViewListener(a aVar) {
        this.e = aVar;
    }

    public void setFooterProgressBarLoadingDrawable(int i) {
        if (this.k != null) {
            this.k.setProgressBarIndeterminateDrawable(i);
        }
    }

    public void setCrashTag(String str) {
    }
}
