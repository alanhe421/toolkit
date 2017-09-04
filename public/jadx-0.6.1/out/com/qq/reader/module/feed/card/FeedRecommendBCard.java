package com.qq.reader.module.feed.card;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.loader.d;
import com.qq.reader.module.feed.loader.d$b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class FeedRecommendBCard extends FeedBaseCard {
    private int mCurIndex0;
    private int mCurIndex1;
    private int mCurIndex2;
    private int mIndex0;
    private int mIndex1;
    private int mIndex2;
    private ImageView mLeftColumnIcon;
    private TextView mLeftColumnIntro;
    private TextView mLeftColumnTitle;
    private ArrayList<ArrayList<d$b>> mList = d.b().e();
    private ImageView mRightBottomColumnIcon;
    private TextView mRightBottomColumnIntro;
    private TextView mRightBottomColumnTitle;
    private ImageView mRightTopColumnIcon;
    private TextView mRightTopColumnIntro;
    private TextView mRightTopColumnTitle;

    public FeedRecommendBCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_recommend_b_layout;
    }

    public void attachView() {
        if (this.mList != null && this.mList.size() == 3) {
            this.mLeftColumnIcon = (ImageView) ap.a(getRootView(), R.id.left_icon);
            this.mLeftColumnTitle = (TextView) ap.a(getRootView(), R.id.left_title);
            this.mLeftColumnIntro = (TextView) ap.a(getRootView(), R.id.left_intro);
            this.mRightTopColumnIcon = (ImageView) ap.a(getRootView(), R.id.right_top_icon);
            this.mRightTopColumnTitle = (TextView) ap.a(getRootView(), R.id.right_top_title);
            this.mRightTopColumnIntro = (TextView) ap.a(getRootView(), R.id.right_top_intro);
            this.mRightBottomColumnIcon = (ImageView) ap.a(getRootView(), R.id.right_bottom_icon);
            this.mRightBottomColumnTitle = (TextView) ap.a(getRootView(), R.id.right_bottom_title);
            this.mRightBottomColumnIntro = (TextView) ap.a(getRootView(), R.id.right_bottom_intro);
            RelativeLayout relativeLayout = (RelativeLayout) ap.a(getRootView(), R.id.right_top_layout);
            RelativeLayout relativeLayout2 = (RelativeLayout) ap.a(getRootView(), R.id.right_bottom_layout);
            ((LinearLayout) ap.a(getRootView(), R.id.left_layout)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedRecommendBCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    a.d.u(ReaderApplication.getApplicationImp(), "");
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTION", "column");
                    bundle.putString("KEY_ACTIONID", (String) this.a.getColumnList().get(0));
                    bundle.putString("KEY_JUMP_PAGENAME", "feed_column_list_b");
                    bundle.putString("LOCAL_STORE_IN_TITLE", ((d$b) ((ArrayList) this.a.mList.get(0)).get(0)).c);
                    bundle.putInt("function_type", 0);
                    bundle.putString("algInfo", "55.1.3");
                    bundle.putString("extInfoId", "200000003");
                    new c(bundle).a(this.a.getEvnetListener());
                    Map hashMap = new HashMap();
                    hashMap.put("event_feed_click", this.a.getStatString(0, this.a.mCurIndex0));
                    StatisticsManager.a().a("event_feed_click", hashMap);
                }
            });
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedRecommendBCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    a.d.u(ReaderApplication.getApplicationImp(), "");
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTION", "column");
                    bundle.putString("KEY_ACTIONID", (String) this.a.getColumnList().get(1));
                    bundle.putString("KEY_JUMP_PAGENAME", "feed_column_list_b");
                    bundle.putString("LOCAL_STORE_IN_TITLE", ((d$b) ((ArrayList) this.a.mList.get(1)).get(0)).c);
                    bundle.putInt("function_type", 0);
                    bundle.putString("algInfo", "55.1.3");
                    bundle.putString("extInfoId", "200000003");
                    new c(bundle).a(this.a.getEvnetListener());
                    Map hashMap = new HashMap();
                    hashMap.put("event_feed_click", this.a.getStatString(1, this.a.mCurIndex1));
                    StatisticsManager.a().a("event_feed_click", hashMap);
                }
            });
            relativeLayout2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedRecommendBCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    a.d.u(ReaderApplication.getApplicationImp(), "");
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTION", "column");
                    bundle.putString("KEY_ACTIONID", (String) this.a.getColumnList().get(2));
                    bundle.putString("KEY_JUMP_PAGENAME", "feed_column_list_b");
                    bundle.putString("LOCAL_STORE_IN_TITLE", ((d$b) ((ArrayList) this.a.mList.get(2)).get(0)).c);
                    bundle.putString("algInfo", "55.1.3");
                    bundle.putString("extInfoId", "200000003");
                    bundle.putInt("function_type", 0);
                    new c(bundle).a(this.a.getEvnetListener());
                    Map hashMap = new HashMap();
                    hashMap.put("event_feed_click", this.a.getStatString(2, this.a.mCurIndex2));
                    StatisticsManager.a().a("event_feed_click", hashMap);
                }
            });
            refleshColumn();
        }
    }

    private String getStatString(int i, int i2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(((d$b) ((ArrayList) this.mList.get(i)).get(i2)).a)).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("55.1.3").append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("200000003");
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean parseData(JSONObject jSONObject) {
        return jSONObject == null ? true : true;
    }

    private ArrayList<String> getColumnList() {
        ArrayList<String> arrayList = new ArrayList();
        int aS = a.d.aS(ReaderApplication.getApplicationImp());
        if (aS == 1) {
            arrayList.add("2398");
            arrayList.add("2397");
            arrayList.add("2399");
        } else if (aS == 2) {
            arrayList.add("2401");
            arrayList.add("2400");
            arrayList.add("2402");
        } else if (aS == 3) {
            arrayList.add("2394");
            arrayList.add("2395");
            arrayList.add("2396");
        }
        return arrayList;
    }

    public void refleshColumn() {
        this.mIndex0 = d.b().f();
        this.mIndex1 = d.b().g();
        this.mIndex2 = d.b().h();
        this.mCurIndex0 = this.mIndex0;
        this.mCurIndex1 = this.mIndex1;
        this.mCurIndex2 = this.mIndex2;
        if (this.mList == null) {
            this.mList = d.b().e();
            if (this.mList == null) {
                return;
            }
        }
        if (this.mList.size() == 3 && this.mLeftColumnTitle != null) {
            this.mLeftColumnTitle.setText(((d$b) ((ArrayList) this.mList.get(0)).get(this.mIndex0)).c);
            this.mLeftColumnIntro.setText(((d$b) ((ArrayList) this.mList.get(0)).get(this.mIndex0)).b);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(((d$b) ((ArrayList) this.mList.get(0)).get(this.mIndex0)).a), this.mLeftColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            this.mRightTopColumnTitle.setText(((d$b) ((ArrayList) this.mList.get(1)).get(this.mIndex1)).c);
            this.mRightTopColumnIntro.setText(((d$b) ((ArrayList) this.mList.get(1)).get(this.mIndex1)).b);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(((d$b) ((ArrayList) this.mList.get(1)).get(this.mIndex1)).a), this.mRightTopColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            this.mRightBottomColumnTitle.setText(((d$b) ((ArrayList) this.mList.get(2)).get(this.mIndex2)).c);
            this.mRightBottomColumnIntro.setText(((d$b) ((ArrayList) this.mList.get(2)).get(this.mIndex2)).b);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(((d$b) ((ArrayList) this.mList.get(2)).get(this.mIndex2)).a), this.mRightBottomColumnIcon, com.qq.reader.common.imageloader.a.a().j());
            Map hashMap = new HashMap();
            if (com.qq.reader.common.a.b.a() == 1) {
                hashMap.put("case", "B");
            } else {
                hashMap.put("case", "A");
            }
            hashMap.put("itemid", ((d$b) ((ArrayList) this.mList.get(0)).get(this.mCurIndex0)).a + "," + ((d$b) ((ArrayList) this.mList.get(1)).get(this.mCurIndex1)).a + "," + ((d$b) ((ArrayList) this.mList.get(2)).get(this.mCurIndex2)).a);
            hashMap.put(s.ALG, "55.1.3,55.1.3,55.1.3");
            hashMap.put("ext_info_id", "200000003");
        }
    }

    public void updateColumnIndex() {
        try {
            int size = ((ArrayList) this.mList.get(0)).size();
            int size2 = ((ArrayList) this.mList.get(1)).size();
            int size3 = ((ArrayList) this.mList.get(2)).size();
            this.mIndex0++;
            this.mIndex1++;
            this.mIndex2++;
            if (this.mIndex0 == size) {
                this.mIndex0 = 0;
            }
            if (this.mIndex1 == size2) {
                this.mIndex1 = 0;
            }
            if (this.mIndex2 == size3) {
                this.mIndex2 = 0;
            }
            d.b().a(this.mIndex0);
            d.b().b(this.mIndex1);
            d.b().c(this.mIndex2);
        } catch (Exception e) {
        }
    }

    public boolean swipeEnable() {
        return false;
    }
}
