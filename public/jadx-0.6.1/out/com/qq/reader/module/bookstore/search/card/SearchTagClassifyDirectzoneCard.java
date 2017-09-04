package com.qq.reader.module.bookstore.search.card;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchTagClassifyDirectzoneCard extends SearchBaseCard {
    public static final int TYPE_CLASSIFY_2 = 1;
    public static final int TYPE_CLASSIFY_3 = 2;
    public static final int TYPE_RANK = 3;
    public static final int TYPE_TAG = 0;
    private int[] bookContainerIds = new int[]{R.id.book_0, R.id.book_1, R.id.book_2, R.id.book_3};
    private int[] imgTopMarkerResIds = new int[]{R.id.img_top1_marker, R.id.img_top2_marker, R.id.img_top3_marker, R.id.img_top4_marker};
    private List<a> mBooks = new ArrayList();
    private int mCardType;
    private String mDesc;
    private String mRecommendTag;
    private String mTitle;
    private int mTotalbooks;
    private int type = 1;

    private class a {
        String a;
        long b;
        String c;
        String d;
        String e;
        final /* synthetic */ SearchTagClassifyDirectzoneCard f;

        private a(SearchTagClassifyDirectzoneCard searchTagClassifyDirectzoneCard) {
            this.f = searchTagClassifyDirectzoneCard;
        }
    }

    public SearchTagClassifyDirectzoneCard(b bVar, String str, int i) {
        super(bVar, str);
        this.mCardType = i;
    }

    public int getResLayoutId() {
        return R.layout.search_tag_classify_card;
    }

    public void attachView() {
        Drawable drawable;
        super.attachView();
        TextView textView = (TextView) ap.a(getRootView(), R.id.title);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.title_tag);
        if (TextUtils.isEmpty(this.mRecommendTag)) {
            textView.setText(this.mTitle);
        } else {
            textView.setText(this.mRecommendTag + " " + this.mTitle);
        }
        if (this.mCardType == 1 || this.mCardType == 2) {
            drawable = textView.getResources().getDrawable(R.drawable.search_result_icon_classify);
        } else if (this.mCardType == 0) {
            drawable = textView.getResources().getDrawable(R.drawable.search_result_icon_label);
        } else {
            drawable = null;
        }
        if (drawable == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageDrawable(drawable);
        }
        textView = (TextView) ap.a(getRootView(), R.id.content);
        if (TextUtils.isEmpty(this.mDesc)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(this.mDesc);
        }
        textView = (TextView) ap.a(getRootView(), R.id.more);
        if (this.mCardType == 3) {
            textView.setText("查看完整榜单");
        } else {
            textView.setText("查看全部(" + this.mTotalbooks + ")");
        }
        int i = 0;
        while (i < 4) {
            a aVar;
            if (this.mBooks.size() <= 0 || this.mBooks.size() <= i) {
                aVar = null;
            } else {
                aVar = (a) this.mBooks.get(i);
            }
            View a = ap.a(getRootView(), this.bookContainerIds[i]);
            if (aVar != null) {
                ImageView imageView2 = (ImageView) ap.a(a, R.id.book_cover);
                TextView textView2 = (TextView) ap.a(a, R.id.book_name);
                TextView textView3 = (TextView) ap.a(a, R.id.book_des);
                a.setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(aVar.e, imageView2, com.qq.reader.common.imageloader.a.a().j());
                textView2.setText(aVar.a);
                textView3.setText(aVar.c);
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ SearchTagClassifyDirectzoneCard b;

                    public void onClick(View view) {
                        if (com.qq.reader.qurl.c.a(aVar.d)) {
                            Map hashMap = new HashMap(this.b.mLogMap);
                            hashMap.put("bid", String.valueOf(aVar.b));
                            if (this.b.mCardType == 0) {
                                i.a("event_B176", hashMap, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_B176", hashMap);
                            } else if (this.b.mCardType == 1) {
                                i.a("event_B178", hashMap, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_B178", hashMap);
                            } else if (this.b.mCardType == 2) {
                                i.a("event_B164", hashMap, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_B164", hashMap);
                            } else {
                                i.a("event_B161", hashMap, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_B161", hashMap);
                            }
                            try {
                                com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), aVar.d, new JumpActivityParameter().a(hashMap));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                a.setVisibility(8);
            }
            i++;
        }
        ap.a(getRootView(), R.id.more).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchTagClassifyDirectzoneCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.doClickedCard();
            }
        });
        if (this.mCardType == 3) {
            showTopMarker(true);
        } else {
            showTopMarker(false);
        }
    }

    protected void expose() {
        super.expose();
        if (this.mCardType == 0) {
            i.a("event_B175", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B175", this.mLogMap);
        } else if (this.mCardType == 1) {
            i.a("event_B177", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B177", this.mLogMap);
        } else if (this.mCardType == 2) {
            i.a("event_B163", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B163", this.mLogMap);
        } else {
            i.a("event_B156", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B156", this.mLogMap);
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mQURL = jSONObject.optString("qurl");
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mTotalbooks = jSONObject.optInt("totalbooks");
        this.mRecommendTag = jSONObject.optString("recommend");
        this.mBooks.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    a aVar = new a();
                    aVar.c = optJSONObject.optString("author");
                    aVar.d = optJSONObject.optString("qurl");
                    aVar.a = optJSONObject.optString("title");
                    aVar.b = optJSONObject.optLong("bid");
                    aVar.e = ao.g(aVar.b);
                    this.mBooks.add(aVar);
                }
            }
        }
        return true;
    }

    public void doClickedCard() {
        super.doClickedCard();
        if (this.mCardType == 0) {
            i.a("event_B176", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B176", this.mLogMap);
        } else if (this.mCardType == 1) {
            i.a("event_B178", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B178", this.mLogMap);
        } else if (this.mCardType == 2) {
            i.a("event_B164", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B164", this.mLogMap);
        } else {
            i.a("event_B161", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B161", this.mLogMap);
        }
    }

    private void showTopMarker(boolean z) {
        View rootView = getRootView();
        for (int a : this.imgTopMarkerResIds) {
            ImageView imageView = (ImageView) ap.a(rootView, a);
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    private void addQuote(TextView textView, String str) {
        String str2 = "\" " + str.toString() + " \"";
        CharSequence spannableString = new SpannableString(str2);
        Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.search_left_quote);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.qq.reader.common.emotion.b.a aVar = new com.qq.reader.common.emotion.b.a(drawable);
        drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.search_right_quote);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new com.qq.reader.common.emotion.b.a(drawable), str2.length() - 1, str2.length(), 33);
        spannableString.setSpan(aVar, 0, 1, 33);
        textView.setText(spannableString);
    }
}
