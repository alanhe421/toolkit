package com.qq.reader.module.feed.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.login.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.tencent.feedback.proguard.R;

public class NativePageFragmentForAuthorTimeLine extends NativePageFragmentforOther {
    private BroadcastReceiver mLoginOkReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ NativePageFragmentForAuthorTimeLine a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.mPullDownView.setEnabled(true);
            this.a.mHandler.sendEmptyMessage(500002);
            this.a.emptyView.setVisibility(4);
        }
    };

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null) {
            getActivity().registerReceiver(this.mLoginOkReceiver, new IntentFilter("com.qq.reader.loginok"));
        }
    }

    public void onLoading() {
        if (c.b()) {
            this.mHandler.sendEmptyMessage(500002);
        } else {
            this.mHandler.post(new Runnable(this) {
                final /* synthetic */ NativePageFragmentForAuthorTimeLine a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.emptyView.a(3);
                    this.a.emptyView.b((int) R.drawable.empty02);
                    this.a.emptyView.setVisibility(0);
                    this.a.emptyView.c("立即登录");
                    this.a.emptyView.a((CharSequence) "登录后，查看已关注作者的全部动态");
                    this.a.emptyView.a(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            c.a(this.a.a.getActivity(), 7);
                            this.a.a.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                    this.a.mPullDownView.setEnabled(false);
                }
            });
        }
    }

    protected void configEmptyView() {
        super.configEmptyView();
        if (this.emptyView != null) {
            this.emptyView.a(4);
            this.emptyView.b((int) R.drawable.empty02);
            this.emptyView.b((CharSequence) "空空如也");
            this.emptyView.a((CharSequence) "在此查看，已关注作者的全部动态");
            this.emptyView.c("去名人堂逛逛");
            this.emptyView.a(new OnClickListener(this) {
                final /* synthetic */ NativePageFragmentForAuthorTimeLine a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    ((FamousAuthorActivity) this.a.getActivity()).b(1);
                }
            });
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.mLoginOkReceiver);
        }
    }
}
