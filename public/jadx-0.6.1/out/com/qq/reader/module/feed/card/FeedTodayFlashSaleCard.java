package com.qq.reader.module.feed.card;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.b;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedTodayFlashSaleCard extends FeedBaseCard implements b {
    private Handler handler;
    private TextView mCountDownHour;
    private TextView mCountDownMin;
    private TextView mCountDownSec;
    private View mCountDownll;
    private String mDesc;
    private long mEndTime;
    private ArrayList<a> mFeedTodayFlashSaleModels = new ArrayList();
    private String mHourString;
    private String mMinString;
    private String mPushName;
    private String mQurl;
    private String mSecString;
    private String mTitle;
    private int[] sectionResIds = new int[]{R.id.section_1, R.id.section_2, R.id.section_3, R.id.section_4};

    public static class a {
        public long a;
        public String b;
        public String c;
        public String d;
        public String e;
    }

    public FeedTodayFlashSaleCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        i.a("event_F118", null, ReaderApplication.getApplicationImp().getApplicationContext());
        this.mCountDownHour = (TextView) ap.a(getRootView(), R.id.feed_countdown_hour);
        this.mCountDownMin = (TextView) ap.a(getRootView(), R.id.feed_countdown_minute);
        this.mCountDownSec = (TextView) ap.a(getRootView(), R.id.feed_countdown_second);
        this.mCountDownll = ap.a(getRootView(), R.id.feed_countdown_ll);
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(0, this.mTitle, "", null);
        ((TextView) ap.a(getRootView(), R.id.feed_today_flash_sale_time)).setText(this.mPushName);
        ((TextView) ap.a(getRootView(), R.id.feed_today_flash_sale_num)).setText(this.mDesc);
        fillGridBookInfo();
        ((CardMoreView) ap.a(getRootView(), R.id.more_view)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedTodayFlashSaleCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.buildQurl(0), null);
                    i.a("event_F120", null, ReaderApplication.getApplicationImp().getApplicationContext());
                } catch (Exception e) {
                }
            }
        });
        startCountDown();
        statExposure();
    }

    public void startCountDown() {
        if (this.mCountDownHour != null && this.mCountDownMin != null && this.mCountDownSec != null) {
            if (this.handler == null) {
                this.handler = new Handler(this, Looper.getMainLooper()) {
                    final /* synthetic */ FeedTodayFlashSaleCard a;

                    public void handleMessage(Message message) {
                        super.handleMessage(message);
                        int i = message.what;
                        if (i != 0) {
                            this.a.formatLongToTimeStr(i);
                            this.a.mCountDownHour.setText(this.a.mHourString);
                            this.a.mCountDownMin.setText(this.a.mMinString);
                            this.a.mCountDownSec.setText(this.a.mSecString);
                            i--;
                            Message obtain = Message.obtain();
                            obtain.what = i;
                            this.a.handler.sendMessageDelayed(obtain, 1000);
                        } else if (i == 0) {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("feedNeedRefresh", true);
                            bundle.putBoolean("fromFeedAction", true);
                            this.a.getEvnetListener().doFunction(bundle);
                        }
                    }
                };
            }
            if (this.handler != null) {
                this.handler.removeCallbacksAndMessages(null);
            }
            Message obtain = Message.obtain();
            long currentTimeMillis = this.mEndTime - System.currentTimeMillis();
            if (currentTimeMillis >= 1000) {
                if (this.mCountDownll != null) {
                    this.mCountDownll.setVisibility(0);
                }
                obtain.what = ((int) currentTimeMillis) / 1000;
                this.handler.sendMessageDelayed(obtain, 1000);
            } else if (this.mCountDownll != null) {
                this.mCountDownll.setVisibility(8);
            }
        }
    }

    public int getResLayoutId() {
        return R.layout.feed_column_today_flash_sale_card_layout;
    }

    private void fillGridBookInfo() {
        if (this.mFeedTodayFlashSaleModels != null) {
            View a;
            int size = this.mFeedTodayFlashSaleModels.size();
            if (size > 0) {
                a = ap.a(getRootView(), this.sectionResIds[0]);
                fillSingleGridBookInfo(a, (a) this.mFeedTodayFlashSaleModels.get(0));
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedTodayFlashSaleCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.clickStat(0);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "1");
                        i.a("event_F119", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), this.a.buildQurl(0), null);
                        } catch (Exception e) {
                        }
                    }
                });
            }
            if (size > 1) {
                a = ap.a(getRootView(), this.sectionResIds[1]);
                fillSingleGridBookInfo(a, (a) this.mFeedTodayFlashSaleModels.get(1));
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedTodayFlashSaleCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.clickStat(1);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "2");
                        i.a("event_F119", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), this.a.buildQurl(1), null);
                        } catch (Exception e) {
                        }
                    }
                });
            }
            if (size > 2) {
                a = ap.a(getRootView(), this.sectionResIds[2]);
                fillSingleGridBookInfo(a, (a) this.mFeedTodayFlashSaleModels.get(2));
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedTodayFlashSaleCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.clickStat(2);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "3");
                        i.a("event_F119", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), this.a.buildQurl(2), null);
                        } catch (Exception e) {
                        }
                    }
                });
            }
            if (size > 3) {
                View a2 = ap.a(getRootView(), this.sectionResIds[3]);
                fillSingleGridBookInfo(a2, (a) this.mFeedTodayFlashSaleModels.get(3));
                a2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedTodayFlashSaleCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.clickStat(3);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "4");
                        i.a("event_F119", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), this.a.buildQurl(3), null);
                        } catch (Exception e) {
                        }
                    }
                });
            }
        }
    }

    private void clickStat(int i) {
        CharSequence clickStatString = getClickStatString(i);
        if (!TextUtils.isEmpty(clickStatString)) {
            Map hashMap = new HashMap();
            hashMap.put("event_feed_click", clickStatString);
            StatisticsManager.a().a("event_feed_click", hashMap);
        }
    }

    private String getClickStatString(int i) {
        if (this.mFeedTodayFlashSaleModels == null || i >= this.mFeedTodayFlashSaleModels.size()) {
            return "";
        }
        a aVar = (a) this.mFeedTodayFlashSaleModels.get(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aVar.a).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(aVar.e).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("");
        return stringBuilder.toString();
    }

    private void fillSingleGridBookInfo(View view, a aVar) {
        if (aVar != null) {
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(aVar.a), (ImageView) ap.a(view, R.id.img_book_cover), com.qq.reader.common.imageloader.a.a().j());
            ((TextView) ap.a(view, R.id.tv_title)).setText(aVar.b);
            TextView textView = (TextView) ap.a(view, R.id.tv_origin_price);
            textView.setText(aVar.c);
            textView.getPaint().setFlags(17);
            ((TextView) ap.a(view, R.id.tv_discount_price)).setText(aVar.d);
            view.setVisibility(0);
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (jSONObject == null || jSONObject.optInt("style") != 13) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return false;
        }
        this.mTitle = optJSONObject.optString("title");
        this.mEndTime = optJSONObject.optLong("endTime");
        this.mPushName = optJSONObject.optString("pushName");
        this.mDesc = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mQurl = optJSONObject.optString("qurl");
        JSONArray optJSONArray = optJSONObject.optJSONArray("bookList");
        if (optJSONArray == null || optJSONArray.length() < 1) {
            return false;
        }
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                if (this.mFeedTodayFlashSaleModels.size() > 0) {
                    this.mFeedTodayFlashSaleModels.clear();
                }
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    a aVar = new a();
                    aVar.a = jSONObject2.optLong("bid");
                    aVar.b = jSONObject2.optString("title");
                    aVar.c = jSONObject2.optString("originalPrice");
                    aVar.d = jSONObject2.optString("discountPrice");
                    jSONObject2 = jSONObject2.optJSONObject(s.STATPARAM_KEY);
                    if (jSONObject2 != null) {
                        aVar.e = jSONObject2.optString(s.ALG);
                    }
                    this.mFeedTodayFlashSaleModels.add(aVar);
                    i++;
                }
                return true;
            }
        }
        return true;
    }

    public boolean swipeEnable() {
        return false;
    }

    public void change() {
    }

    private void formatLongToTimeStr(int i) {
        int i2;
        int i3 = 0;
        if (i >= 60) {
            i2 = i / 60;
            i %= 60;
        } else {
            i2 = 0;
        }
        if (i2 >= 60) {
            i3 = i2 / 60;
            i2 %= 60;
        }
        if (i3 < 10) {
            this.mHourString = "0" + i3;
        } else {
            this.mHourString = "" + i3;
        }
        if (i2 < 10) {
            this.mMinString = "0" + i2;
        } else {
            this.mMinString = "" + i2;
        }
        if (i < 10) {
            this.mSecString = "0" + i;
        } else {
            this.mSecString = "" + i;
        }
    }

    public void removeHandler() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    private String buildQurl(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mQurl);
        if (i < this.mFeedTodayFlashSaleModels.size()) {
            stringBuilder.append("?").append("bids=").append(((a) this.mFeedTodayFlashSaleModels.get(i)).a);
            int i2 = 0;
            while (i2 < this.mFeedTodayFlashSaleModels.size() && i2 != i) {
                stringBuilder.append(",").append(((a) this.mFeedTodayFlashSaleModels.get(i2)).a);
                i2++;
            }
        }
        return stringBuilder.toString();
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
        if (this.mFeedTodayFlashSaleModels == null || this.mFeedTodayFlashSaleModels.size() < 4) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            a aVar = (a) this.mFeedTodayFlashSaleModels.get(i);
            if (aVar != null) {
                long j = aVar.a;
                String str = aVar.e;
                if (i == 0) {
                    stringBuilder.append(j).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(str).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                } else {
                    stringBuilder.append(",").append(j).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(str).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                }
            }
        }
        return stringBuilder.toString();
    }
}
