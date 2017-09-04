package com.qq.reader.module.bookshelf;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.imageloader.core.a.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.view.EmptyView;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: TabBookShelfCloudBooks */
public class k extends a {
    private SwipeRefreshLayout n;
    private Activity o;
    private boolean p = false;
    private i q;
    private OnScrollListener r;
    private TextView s;
    private View t;

    public k(int i, Activity activity, Handler handler, int i2, int i3, View view, i iVar) {
        super(i, activity, handler, i2, i3);
        this.q = iVar;
        this.t = view;
        this.c.addHeaderView(view);
        this.e = new FrameLayout(activity.getApplicationContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, (int) activity.getResources().getDimension(R.dimen.book_shelf_adv_margin_top), 0, 0);
        ((ViewGroup) view).addView(this.e, layoutParams);
        o();
        this.c.setOverScrollMode(2);
        this.c.setAdapter(this.q);
        this.c.setOnScrollListener(new a(this, c.a(getFromActivity()).a(), false, true) {
            final /* synthetic */ k a;

            public void onScrollStateChanged(AbsListView absListView, int i) {
                super.onScrollStateChanged(absListView, i);
                if (this.a.r != null) {
                    this.a.r.onScrollStateChanged(absListView, i);
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                super.onScroll(absListView, i, i2, i3);
                if (this.a.r != null) {
                    this.a.r.onScroll(absListView, i, i2, i3);
                }
            }
        });
        this.o = activity;
        this.n = (SwipeRefreshLayout) this.i.findViewById(R.id.bookshelf_pull_down_view);
        this.n.setOnRefreshListener(new b(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.l();
            }
        });
    }

    public void a(TextView textView) {
        this.s = textView;
    }

    public void j() {
        this.n.a();
    }

    public int k() {
        return this.c.getFirstVisiblePosition();
    }

    public void a(OnScrollListener onScrollListener) {
        this.r = onScrollListener;
    }

    public void l() {
        this.g.sendEmptyMessage(300004);
        i.a("event_A104", null, ReaderApplication.getApplicationImp());
    }

    public void m() {
        if (com.qq.reader.common.login.c.b()) {
            b(true);
            if (this.o instanceof MainActivity) {
                ((MainActivity) this.o).e();
            }
        }
    }

    public void n() {
        this.n.setRefreshing(true);
        l();
    }

    public void a(Message message) {
        switch (message.what) {
            case 8007:
                String string;
                OnlineTag[] onlineTagArr = (OnlineTag[]) message.obj;
                if (onlineTagArr == null || onlineTagArr.length <= 0) {
                    string = ReaderApplication.getApplicationImp().getResources().getString(R.string.bookshelf_no_follow_new_content);
                } else {
                    string = "" + onlineTagArr.length + "本书有更新";
                }
                this.n.a(string, this.s);
                return;
            case 8008:
            case 300009:
                try {
                    this.n.a((String) message.obj, this.s);
                    return;
                } catch (Exception e) {
                    this.n.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed), this.s);
                    return;
                }
            case 10006:
                if (this.n != null) {
                    this.p = true;
                    this.n.setRefreshing(false);
                    b(false);
                    return;
                }
                return;
            case Constants.CODE_SERVICE_DISABLED /*10007*/:
                if (this.n != null) {
                    this.p = false;
                    this.n.setRefreshing(false);
                    b(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void o() {
        if (this.c.getFooterViewsCount() == 0) {
            View emptyView = new EmptyView(this.b);
            emptyView.a(4).b("空空如也").a("赶快用好书塞满书架").c("去精选找好书").b(R.drawable.empty05).a(true).a(new OnClickListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.g.sendEmptyMessage(10016);
                    i.a("event_A143", null, ReaderApplication.getApplicationImp());
                }
            });
            this.f = emptyView;
            this.c.addFooterView(this.f);
        }
    }

    public void a() {
    }

    public void a(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.c.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount <= this.q.getCount()) {
            Message obtainMessage = this.g.obtainMessage(300005);
            obtainMessage.arg1 = headerViewsCount;
            obtainMessage.sendToTarget();
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.c.getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount <= this.q.getCount()) {
            Message obtainMessage = this.g.obtainMessage(300006);
            obtainMessage.arg1 = headerViewsCount;
            obtainMessage.sendToTarget();
        }
        return true;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void b() {
        this.f.setVisibility(8);
    }

    public void c() {
        if (this.f.getVisibility() == 8) {
            this.f.setVisibility(0);
        }
        i.a("event_A142", null, ReaderApplication.getApplicationImp());
    }
}
