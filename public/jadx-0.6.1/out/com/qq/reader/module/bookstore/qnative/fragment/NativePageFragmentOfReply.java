package com.qq.reader.module.bookstore.qnative.fragment;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.a.b;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.page.impl.ah;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONObject;

public class NativePageFragmentOfReply extends NativePageFragmentOfClub implements Callback {
    private boolean isLocate = false;
    private int mLoadPreCount;
    private int mLoadPreIndex = -1;

    protected void init(View view) {
        super.init(view);
        this.isLocate = this.enterBundle.getBoolean("lcoate");
    }

    protected void initConfigBookCardUI(View view, List<a> list) {
        initCardListView(view);
        if (this.mAdapter == null) {
            this.mAdapter = new b(getApplicationContext());
        }
        this.mHoldPage.t();
        this.mAdapter.a(this.mHoldPage);
        boolean b = this.mAdapter.b();
        this.mXListView.setFootViewBgColor(getApplicationContext().getResources().getColor(R.color.common_backgraound_white));
        if (b || this.mXListView.getAdapter() == null) {
            if (this.mHoldPage.u()) {
                this.mXListView.setShowFooter(false);
            } else {
                this.mXListView.setShowFooter(true);
            }
            this.mXListView.setAdapter(this.mAdapter);
        } else {
            this.mAdapter.notifyDataSetChanged();
        }
        if (this.isLocate) {
            this.mXListView.setSelection(1);
            this.isLocate = false;
        }
    }

    public void refresh() {
        if (this.mCurPageStatus != 1) {
            try {
                if (this.mHoldPage != null) {
                    ((ao) this.mHoldPage).G();
                    ((ao) this.mHoldPage).H();
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
                c.a("LOGGER_NATIVE", e.toString());
            }
        }
    }

    protected void refreshAgree() {
        if (this.mHoldPage instanceof ao) {
            ao aoVar = (ao) this.mHoldPage;
            setAgreeState(aoVar.b == 0, false);
            TextView textView = (TextView) this.mComment_Detail_Bottom_Btns_Agree_View.findViewById(R.id.comment_detail_bottom_btns_text_agree);
            if (textView != null && aoVar.a > 0) {
                textView.setText("" + aoVar.a);
            }
        }
    }

    protected void refreshReply() {
        if (this.mHoldPage instanceof ao) {
            int D = ((ao) this.mHoldPage).D();
            TextView textView = (TextView) this.root.findViewById(R.id.comment_detail_bottom_btns_text_reply);
            if (textView == null) {
                return;
            }
            if (D > 0) {
                textView.setText("" + D);
            } else {
                textView.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.reply));
            }
        }
    }

    protected void loadNextPage() {
        if (this.mCurPageStatus == 0) {
            if (this.mNextBundle == null) {
                this.mNextBundle = new Bundle(this.enterBundle);
            }
            this.mNextBundle.putLong("KEY_PAGEINDEX", -1);
            this.mNextBundle.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            if (this.mNextBundle.containsKey("URL_DATA_QURL")) {
                this.mNextBundle.putString("URL_DATA_QURL", "");
            }
            setFloorIndex(this.mNextBundle);
            this.mNextPage = e.a().a(this.mNextBundle, (com.qq.reader.module.bookstore.qnative.c.a) getActivity());
            this.mCurPageStatus = 1;
            this.mNextPage.a(1001);
            d.b().a(getApplicationContext(), this.mNextPage, this.mHandler, false);
        }
    }

    private void setFloorIndex(Bundle bundle) {
        if (this.mLoadPreIndex >= 2) {
            bundle.putInt("floor_index", this.mLoadPreIndex);
            bundle.putInt("floor_next", this.mLoadPreCount);
            bundle.putBoolean("page_replyloadpre", true);
            this.mLoadPreIndex = -1;
            return;
        }
        bundle.putInt("floor_index", ((ao) this.mHoldPage).F() + 1);
        bundle.putInt("floor_next", 20);
        bundle.putBoolean("page_replyloadpre", false);
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if (bundle.getInt("function_type") == 6) {
            this.mLoadPreIndex = bundle.getInt("floor_index", -1);
            this.mLoadPreCount = bundle.getInt("floor_next", -1);
            this.mHandler.sendEmptyMessage(500005);
        }
    }

    protected void addFakeReply(String str, String str2, String str3, String str4, String str5, int i) {
        if (!TextUtils.isEmpty(str2)) {
            Object obj = null;
            for (a aVar : this.mHoldPage.m()) {
                Object obj2;
                if (aVar != null && (aVar instanceof BookClubTopicCard) && str2.equals(((BookClubTopicCard) aVar).getCommentId())) {
                    ((BookClubTopicCard) aVar).addFakeReply(str, str2, str3, str4, str5, i);
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                notifyData();
            }
        }
    }

    public void notifyData() {
        if (!isDetached()) {
            if (this.mNextPage != null && this.mCurPageStatus == 1) {
                if (((ao) this.mNextPage).M >= ((ao) this.mHoldPage).Q) {
                    if (this.mNextPage.s()) {
                        this.mXListView.e();
                    } else {
                        this.mXListView.c();
                    }
                } else if (((ao) this.mNextPage).N < 0 && !this.mNextPage.s()) {
                    ((ao) this.mHoldPage).P = 2;
                }
                this.mHoldPage.addMore(this.mNextPage);
                this.enterBundle.putInt("floor_index", ((ao) this.mHoldPage).P);
                this.enterBundle.putInt("floor_next", ((ao) this.mHoldPage).N);
                ((ao) this.mHoldPage).B();
                ((ao) this.mHoldPage).G();
                ((ao) this.mHoldPage).H();
                if (this.mAdapter != null) {
                    if (((ao) this.mNextPage).M >= ((ao) this.mHoldPage).Q || ((ao) this.mNextPage).N >= 0) {
                        this.mAdapter.a(this.mHoldPage);
                        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                            this.mXListView.setAdapter(this.mAdapter);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        View childAt = this.mXListView.getChildAt(((b) this.mAdapter).a(((ao) this.mNextPage).M));
                        int top = childAt == null ? 0 : childAt.getTop();
                        this.mAdapter.a(this.mHoldPage);
                        if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                            this.mXListView.setAdapter(this.mAdapter);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                        this.mXListView.setSelectionFromTop(((b) this.mAdapter).a(((ao) this.mNextPage).M), top);
                    }
                }
                this.mNextPage = null;
                this.mCurPageStatus = 0;
            } else if (this.mHoldPage != null) {
                ((ao) this.mHoldPage).G();
                ((ao) this.mHoldPage).H();
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

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500004:
                this.mIsNetSucess = false;
                this.mPullDownView.setRefreshing(false);
                if (this.mCurPageStatus == 1) {
                    this.mCurPageStatus = 0;
                    if (!((ao) this.mNextPage).R) {
                        this.mXListView.d();
                    } else if (getFromActivity() != null) {
                        af.a(getFromActivity(), (CharSequence) "网络异常，请稍后重试", 0).a();
                        refresh();
                    }
                } else {
                    showFailedPage();
                }
                return true;
            case 6000004:
                af.a(getApplicationContext(), (CharSequence) "回复成功", 0).a();
                JSONObject jSONObject = (JSONObject) message.obj;
                JSONObject optJSONObject = jSONObject.optJSONObject("reply");
                try {
                    ao aoVar = (ao) this.mHoldPage;
                    aoVar.f(ah.b(Long.valueOf(jSONObject.optString("signal")).longValue()));
                    optJSONObject.put("PARA_TYPE_COMMENT_UID", this.enterBundle.getString("PARA_TYPE_COMMENT_UID"));
                    if (aoVar.d(optJSONObject)) {
                        ((ao) this.mHoldPage).Q = optJSONObject.optInt("index");
                        refresh();
                    }
                } catch (Exception e) {
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }
}
