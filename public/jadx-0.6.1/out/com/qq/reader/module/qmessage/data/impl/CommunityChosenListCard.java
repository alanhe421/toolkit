package com.qq.reader.module.qmessage.data.impl;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommunityChosenListCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private String mDescription;
    public String mId;
    private boolean mIsShowBottomDivider = true;
    private String mPicUrl;
    private String mQurl;
    private String mReceivedDate;
    private List<a> mSubItemList;
    private String mTitle;
    private SparseArray<View> mViewCache = new SparseArray();

    private static class a {
        public String a;
        public String b;
        public String c;
        public String d;

        public a(String str, String str2, String str3, String str4) {
            this.b = str;
            this.a = str2;
            this.c = str3;
            this.d = str4;
        }
    }

    public CommunityChosenListCard(b bVar, String str) {
        super(bVar, str);
    }

    public void setIsShowBottomDivider(boolean z) {
        this.mIsShowBottomDivider = z;
    }

    public int getResLayoutId() {
        return R.layout.chosen_community_content_list_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mTitle = jSONObject.optString("title");
        this.mReceivedDate = k.c(jSONObject.getLong("publishTime"));
        this.mPicUrl = jSONObject.optString("cover");
        this.mDescription = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mQurl = jSONObject.optString("qurl");
        this.mId = jSONObject.optString("id");
        JSONArray optJSONArray = jSONObject.optJSONArray("itemList");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.mSubItemList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                if (jSONObject2 != null) {
                    this.mSubItemList.add(new a(jSONObject2.getString("title"), jSONObject2.getString(SocialConstants.PARAM_IMG_URL), jSONObject2.getString("qurl"), jSONObject2.getString("id")));
                }
            }
        }
        return true;
    }

    public void attachView() {
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.community_chosen_title);
        TextView textView = (TextView) ap.a(getRootView(), R.id.community_chosen_publish_date);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.community_chosen_description);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.community_chosen_pic);
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.community_chosen_sub_layout);
        if (this.mIsShowBottomDivider) {
            ap.a(getRootView(), R.id.community_chosen_divider).setVisibility(0);
        } else {
            ap.a(getRootView(), R.id.community_chosen_divider).setVisibility(8);
        }
        cardTitle.setCardTitle(37, this.mTitle);
        textView.setText(this.mReceivedDate);
        textView2.setText(this.mDescription);
        c.a(getEvnetListener().getFromActivity()).a(this.mPicUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        ap.a(getRootView(), R.id.community_chosen_main_card_layout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommunityChosenListCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.mQurl + "&" + "from=1");
                    Map hashMap = new HashMap();
                    hashMap.put("issueId", this.a.mId);
                    i.a("event_B217", hashMap, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        linearLayout.removeAllViews();
        LayoutInflater from = LayoutInflater.from(getRootView().getContext());
        if (this.mSubItemList != null && this.mSubItemList.size() > 0) {
            for (int i = 0; i < this.mSubItemList.size(); i++) {
                View view = (View) this.mViewCache.get(i);
                if (view == null) {
                    view = from.inflate(R.layout.chosen_community_content_list_sub_item, linearLayout, false);
                    this.mViewCache.put(i, view);
                }
                View view2 = view;
                final a aVar = (a) this.mSubItemList.get(i);
                ((TextView) ap.a(view2, R.id.community_chosen_sub_item_description)).setText(aVar.b);
                c.a(getEvnetListener().getFromActivity()).a(aVar.a, (ImageView) ap.a(view2, R.id.community_chosen_sub_item_pic), com.qq.reader.common.imageloader.a.a().j());
                view2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CommunityChosenListCard b;

                    public void onClick(View view) {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), aVar.c + "&" + "from=1");
                            Map hashMap = new HashMap();
                            hashMap.put("issueId", this.b.mId);
                            hashMap.put("itemId", aVar.d);
                            i.a("event_B217", hashMap, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(view2);
            }
        }
    }
}
