package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.emotion.ReplyView;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.AddReplyTask;
import com.qq.reader.common.readertask.protocol.AddTopicReplyTask;
import com.qq.reader.common.readertask.protocol.BestReplyTask;
import com.qq.reader.common.readertask.protocol.CommentDetailSetBanCommentTask;
import com.qq.reader.common.readertask.protocol.DelReplyTask;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.impl.ah;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.qq.reader.module.bookstore.qnative.page.impl.v;
import com.qq.reader.module.bookstore.qnative.page.impl.x;
import com.qq.reader.view.af;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class NativePageFragmentOfClub extends NativePageFragment implements Callback {
    private static final String COLOMN_FAKE_REPLYID = "signal=";
    public static final int DIALOG_DELETE_REPLY = 609;
    protected static final int MENU_CANCEL_BAN_COMMENT = 5;
    protected static final int MENU_CANCEL_TOPREPLY = 2;
    public static final int MENU_DELETE = 0;
    protected static final int MENU_REPLY = 1;
    protected static final int MENU_REPORT = 6;
    protected static final int MENU_SET_BAN_COMMENT = 4;
    protected static final int MENU_SET_TOPREPLY = 3;
    protected static final int STATUS_BUSY = 1;
    protected static final int STATUS_FREE = 0;
    protected final String TITLE_SET_BAN_COMMENT = "禁言7天";
    protected Bundle enterBundle = null;
    private a failedFakeCommentInfo = null;
    protected boolean isFromCharts = false;
    BroadcastReceiver loginOkReciver = new BroadcastReceiver(this) {
        final /* synthetic */ NativePageFragmentOfClub a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("loginSuccess", false)) {
                this.a.onUpdate();
            }
        }
    };
    protected f mAdapter;
    private com.qq.reader.cservice.a.a mAgreePublisher = null;
    protected com.qq.reader.view.linearmenu.b mBottomContextMenu;
    protected View mComment_Detail_Bottom_Btns;
    protected View mComment_Detail_Bottom_Btns_Agree_View;
    protected int mCurPageStatus = 0;
    private View mFailedLayout = null;
    protected WeakReferenceHandler mHandler;
    protected boolean mIsNetSucess = false;
    private boolean mIsTopicComment;
    private LinearLayout mLayoutMask;
    protected View mLoadingProgress = null;
    protected Bundle mNextBundle = null;
    public com.qq.reader.module.bookstore.qnative.page.b mNextPage = null;
    @Deprecated
    private int mPageType;
    protected SwipeRefreshLayout mPullDownView;
    protected ReplyView mReplyLayout;
    private b mReplyListener;
    protected XListView mXListView = null;
    private RelativeLayout rl_parentLayout;
    protected View root;

    private class a {
        CharSequence a;
        String b;
        String c;
        final /* synthetic */ NativePageFragmentOfClub d;

        private a(NativePageFragmentOfClub nativePageFragmentOfClub) {
            this.d = nativePageFragmentOfClub;
        }
    }

    private class b implements com.qq.reader.common.emotion.ReplyView.a {
        final /* synthetic */ NativePageFragmentOfClub a;
        private Bundle b;
        private Bundle c;
        private int d;

        public b(NativePageFragmentOfClub nativePageFragmentOfClub) {
            this.a = nativePageFragmentOfClub;
        }

        public void a(Bundle bundle) {
            this.b = bundle;
        }

        public void c() {
            this.c = this.b;
            this.d = 0;
        }

        public void a(Bundle bundle, int i) {
            this.c = bundle;
            this.d = i;
        }

        public boolean a() {
            ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.a.getActivity();
            if (c.b()) {
                return true;
            }
            readerBaseActivity.startLogin();
            return false;
        }

        public void a(CharSequence charSequence) {
        }

        public void b(CharSequence charSequence) {
            this.a.foldReplyAndunfoldPanel();
            String charSequence2 = charSequence.toString();
            Bundle bundle = this.c;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            this.a.sendAddReply(charSequence2, bundle, this.d, timeInMillis);
            bundle.putInt("REPLY_TYPE", this.d);
            if (this.a.mReplyLayout.getFrom() == 1001) {
                this.a.clearReplyLayout();
            } else if (this.a.mReplyLayout.getFrom() == 1000) {
                this.a.hideReplyLayout();
            }
            if (!(this.a.getActivity() == null || this.a.getActivity().getCurrentFocus() == null)) {
                IBinder windowToken = this.a.getActivity().getCurrentFocus().getWindowToken();
                if (windowToken != null) {
                    ((InputMethodManager) this.a.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(windowToken, 1);
                } else {
                    com.qq.reader.common.monitor.debug.c.e("NativePageFragmentClub", "windowToken -> null");
                }
            }
            if (this.a.mHoldPage instanceof ah) {
                bundle.putString("CONTENT", charSequence2);
                bundle.putString("PARA_TYPE_COMMENT_UID", this.a.enterBundle.getString("PARA_TYPE_COMMENT_UID"));
                bundle.putLong("fakereplyid", timeInMillis);
                ah ahVar = (ah) this.a.mHoldPage;
                ahVar.c(bundle);
                ahVar.C();
                this.a.refreshReply();
                this.a.refresh();
            }
            if (this.a.mPageType != 1) {
                try {
                    this.a.mComment_Detail_Bottom_Btns.setVisibility(0);
                    this.a.mReplyLayout.setVisibility(8);
                } catch (Exception e) {
                }
            }
            this.c = this.b;
            this.d = 0;
        }

        public void b() {
            if (this.a.mComment_Detail_Bottom_Btns != null) {
                this.a.mComment_Detail_Bottom_Btns.setVisibility(8);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(getLayoutResId(), null);
        init(this.root);
        this.mHandler = new WeakReferenceHandler(this);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getActivity() != null) {
            getActivity().registerReceiver(this.loginOkReciver, new IntentFilter("com.qq.reader.loginok"));
        }
        return this.root;
    }

    protected int getLayoutResId() {
        return R.layout.localbookclub_layout;
    }

    protected void init(View view) {
        this.mLoadingProgress = view.findViewById(R.id.loading_layout);
        this.mPullDownView = (SwipeRefreshLayout) view.findViewById(R.id.booklist_pull_down_list);
        this.mPullDownView.setOnRefreshListener(new com.qq.reader.common.widget.SwipeRefreshLayout.b(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.onUpdate();
            }
        });
        this.mFailedLayout = view.findViewById(R.id.loading_failed_layout);
        this.mReplyLayout = (ReplyView) view.findViewById(R.id.reply_layout);
        this.rl_parentLayout = (RelativeLayout) view.findViewById(R.id.rl_parent);
        this.mLayoutMask = (LinearLayout) view.findViewById(R.id.ll_mask);
        this.mLayoutMask.setVisibility(4);
        this.mLayoutMask.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.a.getActivity() != null) {
                    ((InputMethodManager) this.a.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.a.mReplyLayout.getWindowToken(), 2);
                }
                this.a.foldReplyAndunfoldPanel();
                if (this.a.mPageType == 1 && this.a.mReplyListener != null) {
                    this.a.mReplyListener.c();
                }
                return true;
            }
        });
        if (VERSION.SDK_INT < 21) {
            this.mReplyLayout.setParentLayout(this.rl_parentLayout);
        }
        this.mReplyLayout.setMask(this.mLayoutMask);
        this.mFailedLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reLoadData();
            }
        });
        HashMap hashArguments = getHashArguments();
        if (hashArguments != null) {
            this.enterBundle = (Bundle) hashArguments.get("key_data");
            this.isFromCharts = this.enterBundle.getBoolean("PARA_TYPE_BOOLEAN");
            String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
            if ("bookclubmain".equals(string) || "discovery_comment_detail".equals(string) || "bookclubreward".equals(string) || "bookclubhot".equals(string)) {
                if (com.qq.reader.appconfig.b.l && this.rl_parentLayout != null) {
                    this.rl_parentLayout.setBackgroundResource(R.color.localstore_card_divider_fill_color);
                }
            } else if ("bookclubreplylist".equals(string) || "bookclubdiscusslist".equals(string)) {
                this.mPageType = 1;
                this.mIsTopicComment = true;
            }
        }
    }

    private void foldReplyAndunfoldPanel() {
        if (this.mReplyLayout != null) {
            if (this.mPageType != 1) {
                this.mReplyLayout.setHasSendState(true);
            }
            this.mReplyLayout.setHint("");
        }
        if (!(this.mPageType == 1 || this.mComment_Detail_Bottom_Btns == null)) {
            hideReplyLayout();
            this.mComment_Detail_Bottom_Btns.setVisibility(0);
        }
        if (this.mLayoutMask != null) {
            this.mLayoutMask.setVisibility(4);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroyView() {
        if (this.mReplyLayout != null) {
            this.mReplyLayout.a();
        }
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.loginOkReciver);
        }
        super.onDestroyView();
    }

    protected void initCommentDetailBottomBtns() {
        String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
        LayoutParams layoutParams;
        View findViewById;
        if ("bookclubreply".equals(string) || "selected_comment".equals(string)) {
            this.mComment_Detail_Bottom_Btns = this.root.findViewById(R.id.comment_detail_bottom_btns);
            this.mComment_Detail_Bottom_Btns.setVisibility(0);
            final long j = this.enterBundle.getLong("URL_BUILD_PERE_BOOK_ID");
            final String string2 = this.enterBundle.getString("COMMENT_ID");
            final int i = this.enterBundle.getInt("CTYPE");
            this.mComment_Detail_Bottom_Btns_Agree_View = this.mComment_Detail_Bottom_Btns.findViewById(R.id.comment_detail_bottom_btns_agree);
            layoutParams = (LayoutParams) ((ImageView) this.mComment_Detail_Bottom_Btns.findViewById(R.id.comment_detail_bottom_btns_image_agree)).getLayoutParams();
            this.mComment_Detail_Bottom_Btns_Agree_View.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativePageFragmentOfClub d;

                public void onClick(View view) {
                    ah ahVar;
                    if (this.d.mHoldPage instanceof ah) {
                        ahVar = (ah) this.d.mHoldPage;
                    } else {
                        ahVar = null;
                    }
                    if (ahVar.b == 0) {
                        af.a(ReaderApplication.getApplicationImp().getApplicationContext(), ReaderApplication.getApplicationImp().getResources().getString(R.string.ready_agree), 0).a();
                    } else if (this.d.mAgreePublisher != null && this.d.mAgreePublisher.a) {
                    } else {
                        if (c.b()) {
                            this.d.sendAgree(j, string2, i);
                            i.a("event_B139", null, ReaderApplication.getApplicationImp());
                            return;
                        }
                        ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.d.getActivity();
                        readerBaseActivity.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass15 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.d.mHandler.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.a.d.sendAgree(j, string2, i);
                                            }
                                        });
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                        readerBaseActivity.startLogin();
                    }
                }
            });
            ImageView imageView = (ImageView) this.mComment_Detail_Bottom_Btns.findViewById(R.id.comment_detail_bottom_btns_image_reply);
            this.mComment_Detail_Bottom_Btns.findViewById(R.id.comment_detail_bottom_btns_reply).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativePageFragmentOfClub a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.initShowReplyLayout();
                    this.a.showReplyView();
                    this.a.mReplyLayout.setHint("");
                    i.a("event_B138", null, ReaderApplication.getApplicationImp());
                }
            });
            findViewById = this.root.findViewById(R.id.fl_main);
            layoutParams = (LayoutParams) findViewById.getLayoutParams();
            layoutParams.bottomMargin = (int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.common_list_item_height);
            findViewById.setLayoutParams(layoutParams);
            refreshAgree();
            refreshReply();
        } else if ("bookclubreplylist".equals(string) || "bookclubchapter".equals(string) || "bookclubdiscusslist".equals(string)) {
            findViewById = this.root.findViewById(R.id.fl_main);
            layoutParams = (LayoutParams) findViewById.getLayoutParams();
            layoutParams.bottomMargin = (int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.comment_reply_ui_height);
            findViewById.setLayoutParams(layoutParams);
            initShowReplyLayout();
        }
        List m = this.mHoldPage.m();
        if (m.size() == 1 && (m.get(0) instanceof MyFavorEmptyCard)) {
            this.mComment_Detail_Bottom_Btns.setVisibility(8);
        }
    }

    protected void refreshBottomPannel() {
        String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
        if ("bookclubreply".equals(string) || "selected_comment".equals(string)) {
            List m = this.mHoldPage.m();
            if (m.size() == 1 && (m.get(0) instanceof MyFavorEmptyCard)) {
                this.mComment_Detail_Bottom_Btns.setVisibility(8);
            } else {
                this.mComment_Detail_Bottom_Btns.setVisibility(0);
            }
        }
    }

    protected void showReplyView() {
        if (this.mComment_Detail_Bottom_Btns != null) {
            this.mComment_Detail_Bottom_Btns.setVisibility(8);
        }
        if (VERSION.SDK_INT >= 21) {
            this.mReplyLayout.setParentLayout(this.rl_parentLayout);
        }
        this.mReplyLayout.getInputFocus();
        this.mReplyLayout.c();
    }

    private void sendAgree(long j, String str, int i) {
        setAgreeState(true, true);
        if (this.mAgreePublisher == null) {
            this.mAgreePublisher = new com.qq.reader.cservice.a.a(this, this.mHandler, j, str, i) {
                final /* synthetic */ NativePageFragmentOfClub b;

                public void b() {
                    if (this.b.mHoldPage instanceof ah) {
                        ah ahVar = (ah) this.b.mHoldPage;
                        ahVar.a++;
                        ahVar.b = 0;
                        this.b.refreshAgree();
                    }
                }

                public void a(String str) {
                    af.a(ReaderApplication.getApplicationImp().getApplicationContext(), (CharSequence) str, 0).a();
                    this.b.setAgreeState(false, true);
                }
            };
        }
        this.mAgreePublisher.a();
    }

    protected void refreshReply() {
        if (this.mHoldPage instanceof ah) {
            int D = ((ah) this.mHoldPage).D();
            TextView textView = (TextView) this.root.findViewById(R.id.comment_detail_bottom_btns_text_reply);
            if (D > 0) {
                textView.setText("" + D);
            } else {
                textView.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.reply));
            }
        }
    }

    protected void refreshAgree() {
        if (this.mHoldPage instanceof ah) {
            ah ahVar = (ah) this.mHoldPage;
            setAgreeState(ahVar.b == 0, false);
            TextView textView = (TextView) this.mComment_Detail_Bottom_Btns_Agree_View.findViewById(R.id.comment_detail_bottom_btns_text_agree);
            if (ahVar.a > 0) {
                textView.setText("" + ahVar.a);
            }
        }
    }

    protected void setAgreeState(boolean z, boolean z2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.agreescale_out);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.hasagree_shake);
        ImageView imageView = (ImageView) this.mComment_Detail_Bottom_Btns_Agree_View.findViewById(R.id.comment_detail_bottom_btns_image_agree);
        TextView textView = (TextView) this.mComment_Detail_Bottom_Btns_Agree_View.findViewById(R.id.comment_detail_bottom_btns_text_agree);
        if (z) {
            if (imageView != null) {
                imageView.setBackgroundResource(R.drawable.comment_agree_press);
                if (z2) {
                    imageView.startAnimation(loadAnimation);
                }
            }
            if (textView != null) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textorange));
                return;
            }
            return;
        }
        if (imageView != null) {
            imageView.setBackgroundResource(R.drawable.comment_agree_normal);
            if (z2) {
                imageView.startAnimation(loadAnimation2);
            }
        }
        if (textView != null) {
            textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_common_textcolor));
        }
    }

    protected void initShowReplyLayout() {
        String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
        if ("bookclubreply".equals(string) || "selected_comment".equals(string) || "bookclubchapter".equals(string) || "bookclubreplylist".equals(string) || "bookclubdiscusslist".equals(string)) {
            Bundle bundle = new Bundle();
            bundle.putString(BookClubReplyCard.BID, String.valueOf(this.enterBundle.getLong("URL_BUILD_PERE_BOOK_ID")));
            bundle.putInt("REPLY_TYPE", 0);
            bundle.putString("COMMENT_ID", this.enterBundle.getString("COMMENT_ID"));
            bundle.putInt("CTYPE", this.enterBundle.getInt("CTYPE"));
            bundle.putInt("REPLY_FROM", 1001);
            bundle.putString("PARA_TYPE_COMMENT_UID", this.enterBundle.getString("PARA_TYPE_COMMENT_UID"));
            if ("bookclubchapter".equals(string)) {
                bundle.putInt("SEND_STATE", 1);
                bundle.putInt("URL_BUILD_PERE_CHAPTER_ID", this.enterBundle.getInt("URL_BUILD_PERE_CHAPTER_ID", -1));
            }
            bundle.putBoolean(s.ORIGIN, true);
            showReplyLayout(bundle, 0);
        }
    }

    protected void initCardListView(View view) {
        if (this.mXListView == null) {
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentOfClub);
            this.mXListView.setPullRefreshEnable(false);
            this.mXListView.setFootViewBgColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.div_divider));
            String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
            if ("bookclubreply".equals(string) || "selected_comment".equals(string)) {
                this.mXListView.setFootViewBgColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.localstore_div_bg));
            }
            if ("bookclubchapter".equals(string) || "bookclubreplylist".equals(string) || "bookclubdiscusslist".equals(string)) {
                this.mXListView.setFootViewBgColor(-1);
            }
        }
        if (this.mHoldPage != null) {
            this.mXListView.setVisibility(0);
            this.mXListView.setPullLoadEnable(true);
            this.mXListView.setXListViewListener(new com.qq.reader.view.pullupdownlist.XListView.a(this) {
                final /* synthetic */ NativePageFragmentOfClub a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.mHandler.sendEmptyMessage(500005);
                }
            });
            this.mXListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a((Fragment) this).a(), true, true));
            if (this.mHoldPage.s()) {
                this.mXListView.setPullLoadEnable(true);
                this.mXListView.e();
                return;
            }
            this.mXListView.c();
        }
    }

    protected void initListBookCardUI(View view, BaseListCard baseListCard) {
        initCardListView(view);
        baseListCard.attachView(this.mXListView);
        baseListCard.notifyDataSetChanged();
    }

    protected void initConfigBookCardUI(View view, List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        initCardListView(view);
        if (this.mAdapter == null) {
            this.mAdapter = new f(getApplicationContext());
        }
        this.mHoldPage.t();
        this.mAdapter.a(this.mHoldPage);
        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
            if (this.mHoldPage.u()) {
                this.mXListView.setShowFooter(false);
            } else {
                this.mXListView.setShowFooter(true);
            }
            this.mXListView.setAdapter(this.mAdapter);
            return;
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public void refresh() {
        if (this.mCurPageStatus != 1) {
            try {
                if (this.mHoldPage != null) {
                    this.mHoldPage.q();
                    List m = this.mHoldPage.m();
                    if (m != null && m.size() > 0) {
                        BaseListCard listBookCard = getListBookCard(m);
                        if (listBookCard != null) {
                            listBookCard.notifyDataSetChanged();
                        } else if (this.mAdapter != null) {
                            this.mAdapter.a(this.mHoldPage);
                            boolean b = this.mAdapter.b();
                            if (this.mHoldPage.u()) {
                                this.mXListView.setShowFooter(false);
                            } else {
                                this.mXListView.setShowFooter(true);
                            }
                            if (b || this.mXListView.getAdapter() == null) {
                                this.mXListView.setAdapter(this.mAdapter);
                            } else {
                                this.mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    refreshBottomPannel();
                    refreshAgree();
                    refreshReply();
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
            }
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(500002);
        }
    }

    private void loadPage() {
        if (this.mHoldPage == null && this.enterBundle != null) {
            try {
                Object obj = getHashArguments().get("LOCAL_STORE_HOLD_PAGE");
                if (obj != null) {
                    this.mHoldPage = (com.qq.reader.module.bookstore.qnative.page.b) obj;
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a("LBPageFragment", "LBPageFragment  loadPage exception : " + e.toString());
            }
            if (this.mHoldPage == null) {
                this.mHoldPage = e.a().a(this.enterBundle, (com.qq.reader.module.bookstore.qnative.c.a) getActivity());
                tryObtainDataWithNet(true, false);
                return;
            }
            notifyData();
            hideLoadingPage();
        }
    }

    private void bindFailedFakeComment() {
        try {
            if (this.failedFakeCommentInfo != null && this.mHoldPage != null) {
                if (this.mHoldPage instanceof v) {
                    ((v) this.mHoldPage).a(this.failedFakeCommentInfo.b, this.failedFakeCommentInfo.a.toString(), this.failedFakeCommentInfo.c);
                }
                this.failedFakeCommentInfo = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.failedFakeCommentInfo = null;
        }
    }

    protected void loadNextPage() {
        if (this.mCurPageStatus != 0) {
            return;
        }
        if (this.mHoldPage.s()) {
            if (this.mNextBundle == null) {
                this.mNextBundle = new Bundle(this.enterBundle);
                this.mNextBundle.putString("URL_DATA_QURL", "");
            }
            long r = this.mHoldPage.r();
            if (r != 0) {
                this.mNextBundle.putLong("KEY_PAGEINDEX", r);
                this.mNextBundle.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            }
            this.mNextPage = e.a().a(this.mNextBundle, (com.qq.reader.module.bookstore.qnative.c.a) getActivity());
            this.mCurPageStatus = 1;
            this.mNextPage.a(1001);
            d.b().a(getApplicationContext(), this.mNextPage, this.mHandler, false);
        } else if (this.mXListView != null) {
            this.mXListView.c();
        }
    }

    public void onLoadFinished() {
    }

    public void doFunction(Bundle bundle) {
        if (bundle.getInt("function_type") == 4) {
            switch (bundle.getInt(BookClubReplyCard.REPLY_STATUS)) {
                case 1:
                    if (!(this.mHoldPage instanceof ao) || ((ao) this.mHoldPage).S == 0) {
                        showReplyLayout(bundle, 1);
                        return;
                    } else {
                        getAuthorMenu(bundle).f_();
                        return;
                    }
                case 2:
                case 3:
                case 7:
                    getAuthorMenu(bundle).f_();
                    return;
                case 4:
                    showReplyLayout(bundle, 0);
                    return;
                case 5:
                    agreeForReply(bundle, true);
                    return;
                case 6:
                    agreeForReply(bundle, false);
                    return;
                default:
                    return;
            }
        }
    }

    private void agreeForReply(Bundle bundle, boolean z) {
        if (bundle != null) {
            this.mReplyLayout.setFrom(bundle.getInt("REPLY_FROM"));
        }
    }

    public com.qq.reader.view.linearmenu.b getMoreMenu(Bundle bundle) {
        this.mBottomContextMenu = new com.qq.reader.view.linearmenu.b(getActivity());
        this.mBottomContextMenu.a(0, "删除", bundle);
        this.mBottomContextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getActivity().getWindow().closeAllPanels();
            }
        });
        return this.mBottomContextMenu;
    }

    protected com.qq.reader.view.linearmenu.b getAuthorMenu(Bundle bundle) {
        int i;
        this.mBottomContextMenu = new com.qq.reader.view.linearmenu.b(getActivity());
        this.mBottomContextMenu.a(1, "回复", bundle);
        int i2 = bundle.getInt(BookClubReplyCard.REPLY_STATUS);
        String string = this.enterBundle.getString("KEY_JUMP_PAGENAME");
        if ("bookclubreply".equals(string)) {
            i = 1;
        } else {
            i = 0;
        }
        switch (i2) {
            case 1:
                if ("bookclubreply".equals(string) && (this.mHoldPage instanceof ao) && ((ao) this.mHoldPage).S != 0) {
                    this.mBottomContextMenu.a(0, "删除", bundle);
                    if (bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                        i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                        if (i == 1) {
                            this.mBottomContextMenu.a(5, "解禁", bundle);
                        } else if (i == 0) {
                            this.mBottomContextMenu.a(4, "禁言7天", bundle);
                        }
                    }
                }
                if ("bookclubchapter".equals(string) && (this.mHoldPage instanceof ao) && ((ao) this.mHoldPage).S != 0) {
                    this.mBottomContextMenu.a(0, "删除", bundle);
                    if (bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                        i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                        if (i != 1) {
                            if (i == 0) {
                                this.mBottomContextMenu.a(4, "禁言7天", bundle);
                                break;
                            }
                        }
                        this.mBottomContextMenu.a(5, "解禁", bundle);
                        break;
                    }
                }
                break;
            case 2:
                this.mBottomContextMenu.a(0, "删除", bundle);
                if (("bookclubreply".equals(string) || "bookclubchapter".equals(string)) && (this.mHoldPage instanceof ao) && ((ao) this.mHoldPage).S != 0 && bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                    i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                    if (i != 1) {
                        if (i == 0) {
                            this.mBottomContextMenu.a(4, "禁言7天", bundle);
                            break;
                        }
                    }
                    this.mBottomContextMenu.a(5, "解禁", bundle);
                    break;
                }
                break;
            case 3:
                if (i != 0) {
                    if (bundle.getBoolean(BookClubReplyCard.IS_TOPREPLY)) {
                        this.mBottomContextMenu.a(2, "取消神回复", bundle);
                    } else {
                        this.mBottomContextMenu.a(3, "设为神回复", bundle);
                    }
                }
                this.mBottomContextMenu.a(0, "删除", bundle);
                if ("bookclubreply".equals(string) && (this.mHoldPage instanceof ao) && ((ao) this.mHoldPage).S != 0 && bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                    i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                    if (i != 1) {
                        if (i == 0) {
                            this.mBottomContextMenu.a(4, "禁言7天", bundle);
                            break;
                        }
                    }
                    this.mBottomContextMenu.a(5, "解禁", bundle);
                    break;
                }
                break;
            case 7:
                if (i != 0) {
                    if (bundle.getBoolean(BookClubReplyCard.IS_TOPREPLY)) {
                        this.mBottomContextMenu.a(2, "取消神回复", bundle);
                    } else {
                        this.mBottomContextMenu.a(3, "设为神回复", bundle);
                    }
                }
                if ("bookclubreply".equals(string) && (this.mHoldPage instanceof ao) && ((ao) this.mHoldPage).S != 0) {
                    this.mBottomContextMenu.a(0, "删除", bundle);
                    if (bundle.containsKey(BookClubReplyCard.REPLY_USER_BLACK)) {
                        i = bundle.getInt(BookClubReplyCard.REPLY_USER_BLACK);
                        if (i != 1) {
                            if (i == 0) {
                                this.mBottomContextMenu.a(4, "禁言7天", bundle);
                                break;
                            }
                        }
                        this.mBottomContextMenu.a(5, "解禁", bundle);
                        break;
                    }
                }
                break;
        }
        this.mBottomContextMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mBottomContextMenu.cancel();
                switch (i) {
                    case 0:
                        this.a.showDialog(609, bundle);
                        return true;
                    case 1:
                        bundle.putBoolean("SHOWKEYBOARD", true);
                        this.a.showReplyLayout(bundle, 1);
                        return true;
                    case 2:
                        this.a.sendBestReply(0, bundle);
                        this.a.mHoldPage.b(bundle);
                        this.a.refresh();
                        break;
                    case 3:
                        this.a.sendBestReply(1, bundle);
                        this.a.mHoldPage.b(bundle);
                        this.a.refresh();
                        return true;
                    case 4:
                        this.a.setBanComment(true, bundle);
                        return true;
                    case 5:
                        this.a.setBanComment(false, bundle);
                        return true;
                }
                return false;
            }
        });
        this.mBottomContextMenu.a(new OnCancelListener(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getActivity().getWindow().closeAllPanels();
            }
        });
        return this.mBottomContextMenu;
    }

    protected void showDialog(int i, Bundle bundle) {
        createDialog(i, bundle).show();
    }

    private Dialog createDialog(int i, final Bundle bundle) {
        switch (i) {
            case 609:
                return new com.qq.reader.view.AlertDialog.a(getActivity()).c(R.drawable.alert_dialog_icon).a((int) R.string.bookstand_menu_del).b((int) R.string.bookclub_reply_delete_msg).a((int) R.string.bookclub_reply_delete, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativePageFragmentOfClub b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.sendDelReply(bundle);
                        if (this.b.mHoldPage instanceof ah) {
                            ah ahVar = (ah) this.b.mHoldPage;
                            ahVar.f(bundle.getString(BookClubReplyCard.REPLY_ID));
                            bundle.getString("COMMENT_ID");
                            ahVar.C();
                            this.b.refresh();
                        }
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativePageFragmentOfClub a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
            default:
                return null;
        }
    }

    private void sendBestReply(final int i, Bundle bundle) {
        String string = bundle.getString(BookClubReplyCard.BID);
        String string2 = bundle.getString(BookClubReplyCard.REPLY_ID);
        bundle.putInt("TOP", i);
        ReaderTask bestReplyTask = new BestReplyTask(string, string2, i, bundle.getInt("CTYPE"));
        bestReplyTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativePageFragmentOfClub b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtainMessage = this.b.mHandler.obtainMessage();
                try {
                    int optInt = new JSONObject(str).optInt("code");
                    if (optInt == 0) {
                        obtainMessage.what = 6000009;
                        obtainMessage.arg1 = i;
                    } else if (optInt == -100) {
                        this.b.mHandler.sendEmptyMessage(111);
                    } else {
                        obtainMessage.what = 60000010;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    obtainMessage.what = 60000010;
                }
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.b.mHandler.sendEmptyMessage(60000010);
            }
        });
        g.a().a(bestReplyTask);
    }

    private void sendDelReply(Bundle bundle) {
        ReaderTask delReplyTask = new DelReplyTask(bundle.getString(BookClubReplyCard.BID), bundle.getString(BookClubReplyCard.REPLY_ID), bundle.getInt("CTYPE"));
        delReplyTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativePageFragmentOfClub a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    switch (new JSONObject(str).optInt("code")) {
                        case -100:
                            this.a.mHandler.sendEmptyMessage(111);
                            return;
                        case 0:
                            this.a.mHandler.sendEmptyMessage(6000007);
                            return;
                        default:
                            return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                e.printStackTrace();
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        g.a().a(delReplyTask);
    }

    protected void addFakeReply(String str, String str2, String str3, String str4, String str5, int i) {
        if (!TextUtils.isEmpty(str2)) {
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.mHoldPage.m()) {
                if (aVar != null && (aVar instanceof BookClubTopicCard) && str2.equals(((BookClubTopicCard) aVar).getCommentId())) {
                    ((BookClubTopicCard) aVar).addFakeReply(str, str2, str3, str4, str5, i);
                }
            }
            notifyData();
        }
    }

    private void sendAddReply(String str, Bundle bundle, int i, long j) {
        if (bundle != null) {
            String str2;
            ReaderTask addTopicReplyTask;
            String string = bundle.getString(BookClubReplyCard.BID);
            String string2 = bundle.getString(BookClubReplyCard.REPLY_UID);
            String string3 = bundle.getString(BookClubReplyCard.REPLY_ID);
            String string4 = bundle.getString("COMMENT_ID");
            bundle.getInt("REPLY_TYPE");
            int i2 = bundle.getInt("URL_BUILD_PERE_CHAPTER_ID", -1);
            int i3 = this.enterBundle.getInt("CTYPE");
            addFakeReply(str, string4, string2, string, string3, i);
            if (string4 == null) {
                str2 = "";
            } else {
                str2 = string4;
            }
            if (this.mIsTopicComment) {
                addTopicReplyTask = new AddTopicReplyTask(this.enterBundle.getString("topiccomments_tid"), i, str2, string3, string2, str, j, i3);
            } else {
                addTopicReplyTask = new AddReplyTask(string, i, str2, string3, string2, str, j, i2, i3);
            }
            addTopicReplyTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativePageFragmentOfClub a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    i.a("event_C62", true, 0, 0, null, false, false, ReaderApplication.getApplicationImp().getApplicationContext());
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        Message obtainMessage = this.a.mHandler.obtainMessage();
                        obtainMessage.obj = jSONObject;
                        switch (optInt) {
                            case -100:
                                this.a.mHandler.sendEmptyMessage(111);
                                return;
                            case 0:
                                obtainMessage.what = 6000004;
                                this.a.mHandler.sendMessage(obtainMessage);
                                return;
                            default:
                                obtainMessage.what = 6000005;
                                this.a.mHandler.sendMessage(obtainMessage);
                                return;
                        }
                    } catch (JSONException e) {
                        this.a.onAddReplyError(readerProtocolTask);
                    }
                    this.a.onAddReplyError(readerProtocolTask);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    i.a("event_C62", false, 0, 0, null, true, false, ReaderApplication.getApplicationImp().getApplicationContext());
                    this.a.onAddReplyError(readerProtocolTask);
                }
            });
            g.a().a(addTopicReplyTask);
        }
    }

    private void onAddReplyError(ReaderProtocolTask readerProtocolTask) {
        Object jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("message", "回复失败");
                jSONObject.put("signal", getFakeReplyidFromUrl(readerProtocolTask.getUrl()));
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            jSONObject = null;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 6000005;
        if (jSONObject != null) {
            obtainMessage.obj = jSONObject;
        }
        this.mHandler.sendMessage(obtainMessage);
    }

    private String getFakeReplyidFromUrl(String str) {
        Matcher matcher = Pattern.compile("\\bsignal=\\b\\d*").matcher(str);
        if (matcher.find()) {
            return str.substring(COLOMN_FAKE_REPLYID.length() + matcher.start(), matcher.end());
        }
        return null;
    }

    protected void showReplyLayout(final Bundle bundle, int i) {
        int i2 = bundle.getInt("REPLY_FROM");
        boolean z = bundle.getBoolean("SHOWKEYBOARD", false);
        int i3 = this.mPageType;
        this.mReplyLayout.setFrom(i2);
        if (this.mReplyLayout != null && this.mReplyLayout.getVisibility() == 8) {
            this.mReplyLayout.setVisibility(0);
        }
        String string = bundle.getString(BookClubReplyCard.REPLY_USER_NAME);
        if (string != null) {
            if (string.trim().length() > 0) {
                this.mReplyLayout.setText("");
                this.mReplyLayout.setHint("回复" + string + "：");
            } else {
                this.mReplyLayout.setHint("回复楼主");
            }
        }
        if (bundle.containsKey("PARA_TYPE_REPLY_CARD_POSITION")) {
            this.mReplyLayout.d();
            ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(getActivity().getCurrentFocus(), 0);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ NativePageFragmentOfClub b;

                public void run() {
                    int[] iArr = new int[2];
                    this.b.mReplyLayout.getLocationInWindow(iArr);
                    this.b.mXListView.smoothScrollBy(bundle.getInt("PARA_TYPE_REPLY_CARD_POSITION") - iArr[1], 300);
                }
            }, 500);
        }
        if (z) {
            this.mHandler.postDelayed(new Runnable(this) {
                final /* synthetic */ NativePageFragmentOfClub a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mReplyLayout.d();
                    ((InputMethodManager) this.a.getActivityAfterDettash().getSystemService("input_method")).showSoftInput(this.a.getActivityAfterDettash().getCurrentFocus(), 0);
                }
            }, 200);
        }
        if (this.mReplyListener == null) {
            this.mReplyListener = new b(this);
            this.mReplyLayout.setReplyActionListener(this.mReplyListener);
        }
        boolean z2 = bundle.getBoolean(s.ORIGIN, false);
        this.mReplyListener.a(bundle, i);
        if (z2) {
            this.mReplyListener.a(bundle);
        }
        if (!z2 || i3 != 1) {
            showReplyView();
        } else if (string == null) {
            if (VERSION.SDK_INT >= 21) {
                this.mReplyLayout.setParentLayout(this.rl_parentLayout);
            }
            this.mReplyLayout.setHint("写点什么吧");
        }
    }

    private void hideReplyLayout() {
        if (this.mReplyLayout != null && this.mReplyLayout.getVisibility() == 0) {
            this.mReplyLayout.setVisibility(8);
            this.mReplyLayout.b();
        }
    }

    private void clearReplyLayout() {
        if (this.mReplyLayout != null && this.mReplyLayout.getVisibility() == 0) {
            this.mReplyLayout.b();
        }
    }

    public boolean onBackPress() {
        if (this.mReplyLayout != null) {
            if (this.mReplyLayout.getFrom() == 1001) {
                clearReplyLayout();
            } else if (this.mReplyLayout.getFrom() == 1000) {
                hideReplyLayout();
            }
        }
        return false;
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void notifyData() {
        if (!isDetached()) {
            bindFailedFakeComment();
            if (this.mNextPage != null && this.mCurPageStatus == 1) {
                if (this.mNextPage.m().size() <= 0) {
                    this.mXListView.c();
                } else {
                    this.mHoldPage.addMore(this.mNextPage);
                    this.mXListView.e();
                    if (this.mHoldPage instanceof ah) {
                        ((ah) this.mHoldPage).B();
                    }
                    if (this.mAdapter != null) {
                        this.mAdapter.a(this.mHoldPage);
                        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                            this.mXListView.setAdapter(this.mAdapter);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                    }
                    if (!this.mHoldPage.s()) {
                        this.mXListView.c();
                    }
                }
                this.mNextPage = null;
                this.mCurPageStatus = 0;
            } else if (this.mHoldPage != null) {
                List m = this.mHoldPage.m();
                if (m != null && m.size() > 0) {
                    BaseListCard listBookCard = getListBookCard(m);
                    if (listBookCard != null) {
                        listBookCard.setIsFromCharis(this.isFromCharts);
                        initListBookCardUI(this.root, listBookCard);
                        return;
                    }
                    initConfigBookCardUI(this.root, m);
                }
            }
        }
    }

    protected BaseListCard getListBookCard(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        if (list != null && list.size() == 1) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) list.get(0);
            if (aVar != null && (aVar instanceof BaseListCard)) {
                return (BaseListCard) aVar;
            }
        }
        return null;
    }

    protected boolean handleMessageImp(Message message) {
        JSONObject jSONObject;
        switch (message.what) {
            case 111:
                af.a(getApplicationContext(), (CharSequence) "登录态失效，请重新登录", 0).a();
                Bundle bundle = new Bundle();
                bundle.putInt("function_type", 3);
                doFunction(bundle);
                return true;
            case 500000:
            case 500001:
                this.mIsNetSucess = true;
                this.mPullDownView.setRefreshing(false);
                j.a(53, 2);
                try {
                    if (message.obj != null) {
                        Object obj = message.obj;
                        if (obj instanceof com.qq.reader.module.bookstore.qnative.page.c) {
                            com.qq.reader.module.bookstore.qnative.page.b bVar = (com.qq.reader.module.bookstore.qnative.page.c) obj;
                            if (bVar.g().indexOf("nextpage") == -1) {
                                this.mHoldPage.a(bVar);
                            } else if (this.mNextPage == null || this.mCurPageStatus != 1) {
                                return true;
                            } else {
                                this.mNextPage.a(bVar);
                            }
                        } else if (obj instanceof com.qq.reader.module.bookstore.qnative.page.b) {
                            this.mHoldPage.a((com.qq.reader.module.bookstore.qnative.page.b) obj);
                        }
                        hideLoadingPage();
                        notifyData();
                    } else {
                        com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", "msg.obj == null");
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
                }
                return true;
            case 500002:
                loadPage();
                initCommentDetailBottomBtns();
                return true;
            case 500004:
                this.mIsNetSucess = false;
                this.mPullDownView.setRefreshing(false);
                if (this.mCurPageStatus == 1) {
                    this.mNextPage = null;
                    this.mCurPageStatus = 0;
                    this.mXListView.d();
                } else {
                    showFailedPage();
                }
                return true;
            case 500005:
                loadNextPage();
                return true;
            case 6000004:
                af.a(getApplicationContext(), (CharSequence) "回复成功", 0).a();
                jSONObject = (JSONObject) message.obj;
                JSONObject optJSONObject = jSONObject.optJSONObject("reply");
                if (this.mHoldPage instanceof ah) {
                    try {
                        ah ahVar = (ah) this.mHoldPage;
                        ahVar.f(ah.b(Long.valueOf(jSONObject.optString("signal")).longValue()));
                        optJSONObject.put("PARA_TYPE_COMMENT_UID", this.enterBundle.getString("PARA_TYPE_COMMENT_UID"));
                        if (ahVar.d(optJSONObject)) {
                            refresh();
                        }
                    } catch (Exception e2) {
                    }
                }
                return true;
            case 6000005:
                if (message.obj != null) {
                    try {
                        jSONObject = (JSONObject) message.obj;
                        CharSequence string = jSONObject.getString("message");
                        String b = ah.b(Long.valueOf(jSONObject.getString("signal")).longValue());
                        if (this.mHoldPage instanceof ah) {
                            ah ahVar2 = (ah) this.mHoldPage;
                            ahVar2.f(b);
                            ahVar2.C();
                            refreshReply();
                            refresh();
                        }
                        af.a(getApplicationContext(), string, 0).a();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                return true;
            case 6000006:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "回复太快了，休息会重试", 0).a();
                refresh();
                return true;
            case 6000007:
                af.a(getApplicationContext(), (CharSequence) "已删除", 0).a();
                return true;
            case 6000008:
            case 60000010:
                af.a(getApplicationContext(), (CharSequence) "操作失败，请稍后重试", 0).a();
                return true;
            case 6000009:
                if (message.arg1 == 0) {
                    af.a(getApplicationContext(), (CharSequence) "已取消神回复", 0).a();
                } else {
                    af.a(getApplicationContext(), (CharSequence) "成功设为神回复", 0).a();
                }
                return true;
            case 7000002:
                if (this.mHoldPage instanceof x) {
                    onUpdate();
                } else {
                    refresh();
                }
                return true;
            case 12345008:
                toast("已禁言");
                onUpdate();
                return true;
            case 12345009:
                toast("已解禁");
                onUpdate();
                break;
            case 12345010:
                toast("网络异常，请稍后重试");
                return true;
            case 12345011:
                toast("出错啦，请稍后重试");
                return true;
        }
        return super.handleMessageImp(message);
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    private void showLoadingPage() {
        hideFailedPage();
        if (this.mXListView != null) {
            this.mXListView.setVisibility(8);
        }
        if (this.mLoadingProgress != null) {
            this.mLoadingProgress.setVisibility(0);
        }
    }

    private void hideLoadingPage() {
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

    public void reLoadData() {
        this.mHoldPage.a(1000);
        tryObtainDataWithNet(true, false);
    }

    private void tryObtainDataWithNet(boolean z, boolean z2) {
        boolean a = d.b().a(getApplicationContext(), this.mHoldPage, this.mHandler, z);
        if (!z2) {
            if (a) {
                notifyData();
                hideLoadingPage();
                return;
            }
            showLoadingPage();
        }
    }

    public void onUpdate() {
        if (this.mPullDownView != null) {
            if (this.mHoldPage != null) {
                this.mHoldPage.a(1001);
            }
            tryObtainDataWithNet(false, true);
            this.mPullDownView.setRefreshing(false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mHoldPage != null) {
            this.mHoldPage.a(i, i2, intent, this.mHandler);
        }
    }

    public void setFailedFakeComment(CharSequence charSequence, String str, String str2) {
        this.failedFakeCommentInfo = new a();
        this.failedFakeCommentInfo.a = charSequence;
        this.failedFakeCommentInfo.b = str;
        this.failedFakeCommentInfo.c = str2;
    }

    protected void setBanComment(final boolean z, Bundle bundle) {
        g.a().a(new CommentDetailSetBanCommentTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativePageFragmentOfClub b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Message obtain = Message.obtain();
                if (!this.b.isSuccess(str)) {
                    obtain.what = 12345011;
                } else if (z) {
                    obtain.what = 12345008;
                } else {
                    obtain.what = 12345009;
                }
                this.b.mHandler.sendMessage(obtain);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtain = Message.obtain();
                obtain.what = 12345010;
                this.b.mHandler.sendMessage(obtain);
            }
        }, bundle.getString(BookClubReplyCard.REPLY_UID), this.enterBundle.getLong("URL_BUILD_PERE_BOOK_ID"), z));
    }

    private void toast(String str) {
        af.a(getApplicationContext(), (CharSequence) str, 0).a();
    }

    private boolean isSuccess(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
