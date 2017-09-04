package com.qq.reader.module.bookstore.qnative.card.impl;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedVirtualRecommendCard extends FeedBaseCard implements b {
    private int[] bodyLayoutResId = new int[]{R.id.body_layout_0, R.id.body_layout_1, R.id.body_layout_2, R.id.body_layout_3};
    private int[] fourBookResIds = new int[]{R.id.section_0, R.id.section_1, R.id.section_2, R.id.section_3};
    int index = 0;
    private a mRecommendItem;
    private List<a> recommendItems = new ArrayList();

    private class a extends s {
        String a;
        String b;
        String c;
        public String d;
        String e;
        public int f;
        List<g> g;
        final /* synthetic */ FeedVirtualRecommendCard h;

        private a(FeedVirtualRecommendCard feedVirtualRecommendCard) {
            this.h = feedVirtualRecommendCard;
            this.g = new ArrayList();
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optString("title");
            this.b = jSONObject.optString("pushName");
            this.c = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
            this.e = jSONObject.optString("image");
            this.d = jSONObject.optString("qurl");
            this.f = jSONObject.optInt("preference");
            JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    g gVar = new g();
                    gVar.b(this.h.mFromBid);
                    gVar.parseData(optJSONObject);
                    this.g.add(gVar);
                }
            }
        }
    }

    public FeedVirtualRecommendCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_feedcard_recommend;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.recommendItems.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.parseData(optJSONObject);
                this.recommendItems.add(aVar);
            }
        }
        if (this.recommendItems.size() > 0) {
            return true;
        }
        return false;
    }

    public void attachView() {
        int i = 0;
        this.mRecommendItem = (a) this.recommendItems.get(this.index);
        if (this.mRecommendItem != null) {
            showStatics();
            List list = this.mRecommendItem.g;
            if (list.size() >= 1) {
                setCardTitle();
                setMoreView();
                if (this.mRecommendItem.f == 3) {
                    showBodyLayout(-1);
                    while (i < 3) {
                        if (list.size() > i) {
                            setSingleBookInfo(i);
                        }
                        i++;
                    }
                } else if (list.size() == 1) {
                    showBodyLayout(0);
                    setSingleBookInfo(0);
                } else if (list.size() >= 2) {
                    showBodyLayout(3);
                    while (i < this.fourBookResIds.length) {
                        View a = ap.a(getRootView(), this.fourBookResIds[i]);
                        if (i >= list.size()) {
                            a.setVisibility(4);
                        } else {
                            fillSingleBookInfo(a, (s) list.get(i));
                        }
                        i++;
                    }
                }
                statExposure();
            }
        }
    }

    private void setSingleBookInfo(int i) {
        SingleBookInfo singleBookInfo = (SingleBookInfo) ap.a(getRootView(), this.bodyLayoutResId[i]);
        singleBookInfo.setVisibility(0);
        if (i != 0) {
            singleBookInfo.a(true);
        }
        final g gVar = (g) this.mRecommendItem.g.get(i);
        singleBookInfo.setBookInfoByRecommendPage(gVar);
        singleBookInfo.setOnClickListener(new c(this) {
            final /* synthetic */ FeedVirtualRecommendCard b;

            public void a(View view) {
                this.b.clickStatics();
                this.b.goToDetail(gVar);
            }
        });
    }

    private void statExposure() {
        if (this.mIsNeedStatAlg) {
            CharSequence feedStatString = getFeedStatString();
            if (!TextUtils.isEmpty(feedStatString)) {
                Map hashMap = new HashMap();
                hashMap.put("event_feed_exposure", feedStatString);
                StatisticsManager.a().a("event_feed_exposure", hashMap);
            }
            this.mIsNeedStatAlg = false;
        }
    }

    private String getFeedStatString() {
        if (this.mRecommendItem == null || this.mRecommendItem.g == null || this.mRecommendItem.g.size() < 1) {
            return "";
        }
        List list = this.mRecommendItem.g;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            if (gVar != null) {
                long m = gVar.m();
                String alg = gVar.getAlg();
                if (i == 0) {
                    stringBuilder.append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                } else {
                    stringBuilder.append(",").append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                }
            }
        }
        return stringBuilder.toString();
    }

    private void showBodyLayout(int i) {
        for (int i2 = 0; i2 < this.bodyLayoutResId.length; i2++) {
            if (i == i2) {
                ap.a(getRootView(), this.bodyLayoutResId[i2]).setVisibility(0);
            } else {
                ap.a(getRootView(), this.bodyLayoutResId[i2]).setVisibility(8);
            }
        }
    }

    private void fillSingleBookInfo(View view, s sVar) {
        view.setVisibility(0);
        final g gVar = (g) sVar;
        view.setOnClickListener(new c(this) {
            final /* synthetic */ FeedVirtualRecommendCard b;

            public void a(View view) {
                this.b.goToDetail(gVar);
                this.b.clickStatics();
            }
        });
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(gVar.m()), (ImageView) ap.a(view, R.id.img_book_cover), com.qq.reader.common.imageloader.a.a().j());
        ((TextView) ap.a(view, R.id.tv_title)).setText(gVar.n());
        TextView textView = (TextView) ap.a(view, R.id.tv_author);
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    protected void setCardTitle() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_editor_des);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.iv_editor_avatar);
        RelativeLayout relativeLayout = (RelativeLayout) ap.a(getRootView(), R.id.rl_title_bk);
        ((TextView) ap.a(getRootView(), R.id.tv_editor_name)).setText(this.mRecommendItem.a);
        textView.setText(this.mRecommendItem.b);
        setFormatText(this.mRecommendItem.c);
        if (!(this.mRecommendItem.e == null || "".equals(this.mRecommendItem.e))) {
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mRecommendItem.e, imageView);
        }
        if (this.mRecommendItem.f == 1) {
            relativeLayout.setBackgroundResource(R.color.editor_recommend_title_boy);
        } else if (this.mRecommendItem.f == 2) {
            relativeLayout.setBackgroundResource(R.color.editor_recommend_title_girl);
        } else if (this.mRecommendItem.f == 3) {
            relativeLayout.setBackgroundResource(R.color.editor_recommend_title_publish);
        }
    }

    private void setMoreView() {
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
        cardMoreView.setVisibility(0);
        cardMoreView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedVirtualRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("editor", this.a.mRecommendItem.a);
                i.a("event_F139", hashMap, ReaderApplication.getApplicationImp());
                try {
                    if (!TextUtils.isEmpty(this.a.mRecommendItem.d)) {
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.mRecommendItem.d);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setFormatText(String str) {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_recommend_text);
        int textSize = (int) textView.getTextSize();
        int paddingLeft = (((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - textView.getPaddingLeft()) - textView.getPaddingRight()) * 3) - (textSize * 6);
        if (((int) textView.getPaint().measureText(str)) > paddingLeft) {
            str = str.substring(0, paddingLeft / textSize) + "..";
        }
        String str2 = "\" " + str + " \"";
        CharSequence spannableString = new SpannableString(str2);
        Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_start);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.qq.reader.common.emotion.b.b bVar = new com.qq.reader.common.emotion.b.b(drawable);
        drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_end);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new com.qq.reader.common.emotion.b.b(drawable), str2.length() - 1, str2.length(), 33);
        spannableString.setSpan(bVar, 0, 1, 33);
        textView.setText(spannableString);
    }

    public boolean swipeEnable() {
        return false;
    }

    public void change() {
        this.mIsNeedStatAlg = true;
        this.index++;
        if (this.index == this.recommendItems.size()) {
            this.index = 0;
        }
    }

    protected void showStatics() {
        Map hashMap = new HashMap();
        hashMap.put("editor", this.mRecommendItem.a);
        int aS = d.aS(ReaderApplication.getApplicationImp());
        if (aS == 1) {
            i.a("event_F133", hashMap, ReaderApplication.getApplicationImp());
        } else if (aS == 2) {
            i.a("event_F134", hashMap, ReaderApplication.getApplicationImp());
        } else if (aS == 3) {
            i.a("event_F135", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    protected void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put("editor", this.mRecommendItem.a);
        int aS = d.aS(ReaderApplication.getApplicationImp());
        if (aS == 1) {
            i.a("event_F136", hashMap, ReaderApplication.getApplicationImp());
        } else if (aS == 2) {
            i.a("event_F137", hashMap, ReaderApplication.getApplicationImp());
        } else if (aS == 3) {
            i.a("event_F138", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    private void goToDetail(g gVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(gVar.m()).append("&alg=").append(gVar.getAlg()).append(H5GameChargeTask.ITEMID).append(gVar.m());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ext_info_id", gVar.m());
            jSONObject.put("itemid", gVar.m());
            jSONObject.put(s.ALG, gVar.getAlg());
            stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
        } catch (Exception e) {
        }
    }
}
