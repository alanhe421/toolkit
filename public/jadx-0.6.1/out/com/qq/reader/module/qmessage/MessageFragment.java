package com.qq.reader.module.qmessage;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.qq.reader.module.qmessage.data.c;
import com.qq.reader.module.qmessage.data.d;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import com.qq.reader.module.qmessage.data.impl.MessageDividerCard;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageFragment extends BaseFragment implements a {
    private static final int DEL_MESSAGE = 1;
    public static final String DEL_MESSAGE_ID = "MessageID";
    private static final String TAG = "MsgFragment";
    private a mAdapter = null;
    private int mCurrentOptType = 2;
    private MessageDividerCard mDivider = new MessageDividerCard(null);
    protected View mFailedLayout = null;
    protected View mLoadingProgress = null;
    private long mMaxTime = 0;
    private c mMessagePackage;
    private long mMinTime = Long.MAX_VALUE;
    private d mPage = null;
    protected SwipeRefreshLayout mPullDownView;
    private int mType = 1;
    protected XListView mXListView = null;
    protected RelativeLayout rl_parentLayout;
    protected View root;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mType = getArguments().getInt("TYPE", 1);
        getActivity().setTheme(R.style.BookCoinChargeTheme);
        this.root = layoutInflater.inflate(getLayoutResourceId(), null);
        init(this.root);
        this.mHandler = new WeakReferenceHandler(this);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        loadData(2, 0);
        return this.root;
    }

    public int getLayoutResourceId() {
        return R.layout.messagelist_layout;
    }

    private void loadData(int i, long j) {
        if (i == 2) {
            showLoadingPage();
        }
        f.a("opt time", j + ":" + this.mMinTime + ":" + this.mMaxTime);
        this.mCurrentOptType = i;
        this.mMessagePackage = new c(j, i, this);
        this.mMessagePackage.a(getArguments().getInt("TYPE", 1));
        com.qq.reader.module.qmessage.loader.a.b().a(this.mMessagePackage, this.mHandler);
    }

    public void onResume() {
        super.onResume();
        this.mType = getArguments().getInt("TYPE", 1);
        if (this.mType == 1) {
            i.a("event_C152", null, getActivity());
        } else {
            i.a("event_C153", null, getActivity());
        }
    }

    protected void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mPullDownView = (SwipeRefreshLayout) view.findViewById(R.id.booklist_pull_down_list);
        this.mPullDownView.setOnRefreshListener(new b(this) {
            final /* synthetic */ MessageFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.reRefresh();
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MessageFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        initCardListView(view);
    }

    protected boolean handleMessageImp(Message message) {
        try {
            switch (message.what) {
                case 1:
                    Long l = (Long) message.obj;
                    List e = this.mPage.e();
                    for (int i = 0; i < e.size(); i++) {
                        if (((MessageBaseCard) e.get(i)).getMessageId() == l.longValue()) {
                            e.remove(i);
                            construct(this.mPage, true, 1);
                            break;
                        }
                    }
                    construct(this.mPage, true, 1);
                case 500005:
                    loadMore();
                    break;
                case 8000001:
                    this.mPullDownView.setRefreshing(false);
                    hideLoadingPage();
                    c cVar = (c) message.obj;
                    long d = (cVar.f().d() >= this.mMinTime || cVar.f().d() == 0) ? this.mMinTime : cVar.f().d();
                    this.mMinTime = d;
                    this.mMaxTime = cVar.f().c() > this.mMaxTime ? cVar.f().c() : this.mMaxTime;
                    if (cVar.c() != 0) {
                        if (cVar.c() != 1) {
                            this.mPage = cVar.f();
                            construct(this.mPage, cVar.a(), cVar.c());
                            if (this.mPage.e().size() == 0 && !ao.d(ReaderApplication.getApplicationImp())) {
                                if (this.mXListView != null) {
                                    this.mXListView.setVisibility(4);
                                }
                                showFailedPage();
                                break;
                            }
                        }
                        this.mPage.e().remove(this.mDivider);
                        List e2 = cVar.f().e();
                        if (this.mPage.e().size() > 0 && e2.size() > 0) {
                            e2.add(this.mDivider);
                        }
                        this.mPage.a(e2, true);
                        construct(this.mPage, true, cVar.c());
                        break;
                    }
                    this.mPage.a(cVar.f().e());
                    construct(this.mPage, cVar.a(), cVar.c());
                    break;
                    break;
                case 8000002:
                    com.qq.reader.common.monitor.debug.c.e(TAG, "message obtain failed");
                    this.mPullDownView.setRefreshing(false);
                    hideLoadingPage();
                    showFailedPage();
                    break;
                case 8000003:
                    f.a("msg_del", "ok");
                    break;
                case 8000004:
                    f.a("msg_del", "failed");
                    break;
                case 8000005:
                    if (this.mPage == null || this.mPage.e().size() == 0) {
                        showLoadingPage();
                        break;
                    }
            }
            return super.handleMessageImp(message);
        } catch (Exception e3) {
            return super.handleMessageImp(message);
        }
    }

    public void construct(d dVar, boolean z, int i) {
        if (!isDetached() && dVar != null) {
            eliminateOldChosenCommunityContentCard(dVar.e());
            if (this.mAdapter == null) {
                this.mAdapter = new a(getApplicationContext());
            }
            this.mAdapter.a(dVar);
            if (this.mAdapter.b()) {
                this.mXListView.setAdapter(this.mAdapter);
            }
            if (i == 0) {
                if (z) {
                    this.mXListView.c();
                } else {
                    this.mXListView.e();
                }
            } else if (dVar.e().size() == 0) {
                this.mXListView.g();
            } else {
                this.mXListView.b();
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    private void eliminateOldChosenCommunityContentCard(List<MessageBaseCard> list) {
        final List arrayList = new ArrayList();
        Iterator it = list.iterator();
        Object obj = null;
        while (it.hasNext()) {
            Object obj2;
            MessageBaseCard messageBaseCard = (MessageBaseCard) it.next();
            if (messageBaseCard.getMessageSubType() != 19) {
                obj2 = obj;
            } else if (obj != null) {
                arrayList.add(Long.valueOf(messageBaseCard.getMessageId()));
                it.remove();
                obj2 = obj;
            } else {
                int i = 1;
            }
            obj = obj2;
        }
        if (arrayList.size() > 0) {
            g.a().a(new ReaderDBTask() {
                public void run() {
                    for (Long longValue : arrayList) {
                        com.qq.reader.module.qmessage.data.b.b().a(longValue.longValue());
                    }
                }
            });
        }
    }

    public void initCardListView(View view) {
        if (this.mXListView == null) {
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
            this.mXListView.setPullRefreshEnable(true);
            this.mXListView.setPullLoadEnable(true);
        }
        this.mXListView.setVisibility(0);
        this.mXListView.setXListFooter(new MessageXListFooter(getActivity(), this.mType));
        this.mXListView.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ MessageFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.mXListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a(this).a(), true, true));
        this.mXListView.b();
    }

    public void loadMore() {
        this.mCurrentOptType = 0;
        loadData(0, this.mMinTime);
    }

    public void reRefresh() {
        this.mCurrentOptType = 1;
        loadData(1, this.mMaxTime);
    }

    public void reRefreshFristTime() {
        showLoadingPage();
        this.mCurrentOptType = 1;
        loadData(1, this.mMaxTime);
    }

    private void reLoadData() {
        loadData(2, 0);
    }

    protected void showLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(8);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(0);
        }
    }

    protected void hideLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(0);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(8);
        }
    }

    protected void showFailedPage() {
        if (this.mXListView == null || this.mXListView.getVisibility() != 0) {
            if (this.mLoadingProgress != null) {
                this.mLoadingProgress.setVisibility(8);
            }
            this.mFailedLayout.setVisibility(0);
        }
    }

    protected void hideFailedPage() {
        this.mFailedLayout.setVisibility(8);
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public void doFunction(final Bundle bundle) {
        new AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a("删除").b("删除本条消息吗").a("确定", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MessageFragment b;

            public void onClick(DialogInterface dialogInterface, int i) {
                long j = bundle.getLong("MessageID", -1);
                if (j > 0) {
                    Message obtain = Message.obtain(this.b.mHandler, 1);
                    obtain.obj = Long.valueOf(j);
                    obtain.sendToTarget();
                    if (this.b.mType == 1) {
                        i.a("event_C155", null, this.b.getActivity());
                    } else {
                        i.a("event_C156", null, this.b.getActivity());
                    }
                    com.qq.reader.module.qmessage.loader.a.b().a(j, this.b.mHandler);
                }
            }
        }).b("取消", null).a().show();
    }

    public Activity getFromActivity() {
        return getActivity();
    }
}
