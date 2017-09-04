package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.d;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookchapter.online.h;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class OnlineChapterFragment extends BaseFragment {
    private EmptyView emptyLayout;
    private boolean isInitedChapter = false;
    private h mAdapter;
    private int mCurReadChapterID = 1;
    private OnItemClickListener mItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ OnlineChapterFragment a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (view instanceof ChapterAdapterItem) {
                OnlineChapter onlineChapter = (OnlineChapter) this.a.mAdapter.getItem(i);
                this.a.mOnlineTag.c(onlineChapter.getChapterId()).b(onlineChapter.getChapterName()).a(0);
                Intent intent = new Intent();
                intent.setClass(this.a.getBaseActivity(), ReaderPageActivity.class);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                intent.putExtra("com.qq.reader.OnlineTag", this.a.mOnlineTag);
                intent.putExtra("com.qq.reader.OnlineTag.web.chapter", true);
                intent.putExtra("com.qq.reader.fromonline", true);
                this.a.getBaseActivity().startActivity(intent);
            }
        }
    };
    private ListView mListView;
    private View mLoading;
    private g mOnlineHandle;
    private f mOnlineOperator;
    private OnlineTag mOnlineTag;
    private Button mRetryButton;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mOnlineTag = new OnlineTag(String.valueOf(getHashArguments().get("bid")), "", 0);
        registerChapterHandler();
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 21000:
                this.mOnlineOperator = (f) message.obj;
                d y = this.mOnlineOperator.y();
                long parseLong = Long.parseLong(this.mOnlineTag.k());
                this.mOnlineTag.a(y.e()).e(y.G()).f(y.l()).c(-1).d(y.j()).h(ao.g(parseLong)).k(y.D()).i(y.E()).h(y.C());
                e.a().a(String.valueOf(parseLong), y.F());
                this.mLoading.setVisibility(8);
                List e = this.mOnlineOperator.e();
                if (e == null || e.size() == 0) {
                    this.mListView.setVisibility(8);
                    this.emptyLayout.setVisibility(0);
                } else {
                    this.mListView.setVisibility(0);
                    this.emptyLayout.setVisibility(8);
                    this.mAdapter.a(this.mOnlineOperator.e());
                    this.mListView.setSelection(this.mAdapter.b(this.mCurReadChapterID));
                    this.mAdapter.notifyDataSetChanged();
                    this.isInitedChapter = true;
                }
                return true;
            case 21001:
                this.mLoading.setVisibility(8);
                if (!this.isInitedChapter) {
                    this.mListView.setVisibility(8);
                    this.emptyLayout.setVisibility(0);
                }
                return true;
            default:
                unregisterChapterHandler();
                return super.handleMessageImp(message);
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
        this.mOnlineHandle.a(false);
    }

    public void onLoadFinished() {
    }

    public void initUI(View view) {
        this.emptyLayout = (EmptyView) view.findViewById(R.id.online_chapter_empyt_layout);
        this.emptyLayout.setVisibility(8);
        this.emptyLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OnlineChapterFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.emptyLayout.setVisibility(8);
                this.a.mLoading.setVisibility(0);
                this.a.registerChapterHandler();
            }
        });
        this.mListView = (ListView) view.findViewById(R.id.online_chapter_list);
        this.mAdapter = new h();
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setVisibility(8);
        this.mListView.setOnItemClickListener(this.mItemClickListener);
        this.mLoading = view.findViewById(R.id.chapter_loading);
        this.mLoading.setVisibility(0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.online_directory, null);
        initUI(inflate);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return inflate;
    }

    private void registerChapterHandler() {
        this.mOnlineHandle = new g(getApplicationContext(), this.mOnlineTag);
        this.mOnlineHandle.a(getHandler());
    }

    public void onDestroy() {
        unregisterChapterHandler();
        super.onDestroy();
    }

    private void unregisterChapterHandler() {
        if (this.mOnlineHandle != null) {
            this.mOnlineHandle.d();
            this.mOnlineHandle = null;
        }
    }
}
