package com.qq.reader.module.feed.data.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.AlertDialog;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.sdk.v;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class FeedBaseCard extends a {
    private static final String JSON_KEY_CLICK = "click";
    private static final String JSON_KEY_CMD = "cmd";
    private static final String JSON_KEY_CMDVALUE = "cmdvalue";
    private static final String JSON_KEY_FORCEPUSH = "forcePush";
    private static final String JSON_KEY_ID = "id";
    private static final String JSON_KEY_INFO = "info";
    private static final String JSON_KEY_INFO_EX = "ex";
    private static final String JSON_KEY_MOUDLE = "moudle";
    private static final String JSON_KEY_QURL = "qurl";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STYLE = "style";
    private static final String JSON_KEY_TITLE = "title";
    public static final int MESSAGETYPE_3BOOKS = 13;
    public static final int MESSAGETYPE_3_HOR_BOOKS = 16;
    public static final int MESSAGETYPE_3_HOT_RANK = 17;
    public static final int MESSAGETYPE_3_VER_BOOKS = 15;
    public static final int MESSAGETYPE_BOOKGROUP = 2;
    public static final int MESSAGETYPE_BOOKREVIEW = 3;
    public static final int MESSAGETYPE_BOOKREVIEW_OFFICIAL = 10;
    public static final int MESSAGETYPE_INACTIVETOPIC = 11;
    public static final int MESSAGETYPE_LISTENBOOK = 12;
    public static final int MESSAGETYPE_NEWS = 4;
    public static final int MESSAGETYPE_RECOMMEND_A_BOOKS = 18;
    public static final int MESSAGETYPE_RECOMMEND_B_BOOKS = 19;
    public static final int MESSAGETYPE_SINGLEBOOK = 5;
    public static final int MESSAGETYPE_TODAY_BROWSE = 20;
    public static final int MESSAGETYPE_TOPIC = 6;
    public static final int MESSAGETYPE_USERTAG = 7;
    private int clickedStatus = 0;
    public boolean isClickEnable = false;
    private d mCmd;
    private JSONObject mEx;
    private String mExtInfoId;
    private int mIndex;
    private int mIsForcePush;
    public boolean mIsFromNet = false;
    public boolean mIsNeedStatAlg = false;
    private JSONObject mJsondataObj = null;
    private i mListener;
    private int mMoudle;
    private String mMsgId;
    protected String mQURL;
    private boolean mShowDivider;
    private String mShowTime;
    private int mSliceOrder;
    private String mStatAlg;
    private j mStatItem = new j();
    private int mStyle;
    protected String mTitle;

    public FeedBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    public j getStatItem() {
        return this.mStatItem;
    }

    public FeedBaseCard setShowTime(String str) {
        if (str != null) {
            this.mShowTime = str;
        }
        return this;
    }

    public String getShowTime() {
        return this.mShowTime;
    }

    public int getSliceOrder() {
        return this.mSliceOrder;
    }

    public void setSliceOrder(int i) {
        this.mSliceOrder = i;
    }

    public FeedBaseCard setIndex(int i) {
        if (i >= 0) {
            this.mIndex = i;
        }
        return this;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public boolean fillData(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("data")) {
            try {
                parseData(jSONObject);
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    StringBuilder stringBuilder3 = new StringBuilder();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("item_id");
                            String optString2 = optJSONObject.optString("alg_info");
                            optJSONObject = optJSONObject.optJSONObject("ext_info");
                            Object optString3 = optJSONObject.optString("info_id");
                            if (optJSONArray.length() == 1) {
                                this.mQURL = optJSONObject.optString(JSON_KEY_QURL);
                            }
                            if (i == optJSONArray.length() - 1) {
                                stringBuilder.append(optString);
                                stringBuilder2.append(optString2);
                                if (TextUtils.isEmpty(optString3)) {
                                    stringBuilder3.append(v.n);
                                } else {
                                    stringBuilder3.append(optString3);
                                }
                            } else {
                                stringBuilder.append(optString + ",");
                                stringBuilder2.append(optString2 + ",");
                                if (TextUtils.isEmpty(optString3)) {
                                    stringBuilder3.append("*,");
                                } else {
                                    stringBuilder3.append(optString3 + ",");
                                }
                            }
                        }
                    }
                    this.mMsgId = stringBuilder.toString();
                    setCardId(this.mMsgId);
                    this.mStatAlg = stringBuilder2.toString();
                    this.mExtInfoId = stringBuilder3.toString();
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        this.mJsondataObj = jSONObject;
        this.mMsgId = jSONObject.optString("id");
        setCardId(this.mMsgId);
        setExtInfoId("");
        this.mMoudle = jSONObject.optInt(JSON_KEY_MOUDLE);
        this.mStyle = jSONObject.optInt(JSON_KEY_STYLE);
        this.clickedStatus = jSONObject.optInt(JSON_KEY_CLICK);
        JSONObject optJSONObject2 = jSONObject.optJSONObject(JSON_KEY_CMD);
        if (optJSONObject2 != null) {
            this.mCmd = new d(optJSONObject2.optString(JSON_KEY_CMD), optJSONObject2.optString(JSON_KEY_CMDVALUE));
        }
        this.mQURL = jSONObject.optString(JSON_KEY_QURL);
        try {
            optJSONObject2 = jSONObject.optJSONObject(JSON_KEY_INFO);
            if (com.qq.reader.appconfig.b.a) {
                this.mIsForcePush = optJSONObject2.optInt(JSON_KEY_FORCEPUSH);
            }
            parseData(optJSONObject2);
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("stat_params");
            if (optJSONObject3 != null) {
                this.mStatAlg = optJSONObject3.optString(s.ALG);
            }
            if (!(optJSONObject3 == null || this.mCmd == null)) {
                this.mCmd.a(optJSONObject3);
            }
            this.mEx = optJSONObject2.optJSONObject(JSON_KEY_INFO_EX);
            return true;
        } catch (Exception e2) {
            c.a("FeedTimeUtil", e2.toString());
            return false;
        }
    }

    public void attachView(View view) {
        super.attachView(view);
        if (!this.mShowDivider) {
            View a = ap.a(view, R.id.localstore_adv_divider);
            if (a != null) {
                a.setVisibility(8);
            }
        }
    }

    public boolean isShowDivider() {
        return this.mShowDivider;
    }

    public void setShowDivider(boolean z) {
        this.mShowDivider = z;
    }

    public boolean isFocePush() {
        return this.mIsForcePush == 1;
    }

    public JSONObject getDataSourceObj() {
        return this.mJsondataObj;
    }

    public String getMsgId() {
        return this.mMsgId;
    }

    public int getMoudle() {
        return this.mMoudle;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public d getCmd() {
        return this.mCmd;
    }

    public void setCmd(d dVar) {
        this.mCmd = dVar;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isLongClickEnable() {
        return true;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void setExtInfoId(String str) {
        this.mExtInfoId = str;
    }

    public String getExtInfoId() {
        return this.mExtInfoId;
    }

    public void doOnFeedClicked(boolean z) {
        if (com.qq.reader.qurl.c.a(this.mQURL)) {
            try {
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), this.mQURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (getCmd() != null) {
            com.qq.reader.module.feed.activity.a.a(getCmd(), getEvnetListener());
        }
        Map hashMap = new HashMap();
        hashMap.put("event_feed_click", getStatString());
        StatisticsManager.a().a("event_feed_click", hashMap);
        if (z) {
            doClickedCard();
        }
    }

    public void doRemoveCard() {
        g.a().a(new 1(this));
    }

    public void doClickedCard() {
        if (this.clickedStatus == 0) {
            g.a().a(new 2(this));
        }
    }

    public boolean swipeEnable() {
        return true;
    }

    public void setIRemovedListener(i iVar) {
        this.mListener = iVar;
    }

    protected void setToClickedStatus() {
        if (this.clickedStatus == 0) {
            this.clickedStatus = 1;
            if (this.mJsondataObj != null) {
                try {
                    this.mJsondataObj.put(JSON_KEY_CLICK, 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isClickedStatus() {
        return this.clickedStatus == 1;
    }

    public void ivanDebugPrint() {
        try {
            c.e("FeedPackageDate", "(" + this.mIndex + ")" + " id : " + this.mMsgId + " --- " + getClass().getSimpleName() + " --- " + this.mTitle + " --- " + this.clickedStatus);
        } catch (Exception e) {
            c.e("FeedPackageDate", e.toString());
        }
    }

    public void setDataStatus(int i) {
        this.mDataState = i;
    }

    protected void checkClickEnable() {
        if (this.isClickEnable) {
            getRootView().setOnClickListener(new 3(this));
        }
    }

    public String getStatAlg() {
        return this.mStatAlg;
    }

    public void setStatAlg(String str) {
        this.mStatAlg = str;
    }

    public String getStatString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mMsgId);
        if (!TextUtils.isEmpty(this.mStatAlg)) {
            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuilder.append(this.mStatAlg);
        }
        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("").append(DLConstants.DEPENDENCY_PACKAGE_DIV).append("");
        return stringBuilder.toString();
    }

    public void doFeedLongClicked() {
        if (isLongClickEnable()) {
            doOnFeedLongClicked();
        }
    }

    protected void doOnFeedLongClicked() {
        if (com.qq.reader.common.c.b.i != 0) {
            com.qq.reader.module.bookstore.qnative.c.a evnetListener = getEvnetListener();
            StringBuilder stringBuilder = new StringBuilder();
            if (this.mEx != null) {
                Iterator keys = this.mEx.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    stringBuilder.append(str).append(":").append(this.mEx.optString(str)).append("\n");
                }
            }
            CharSequence stringBuilder2 = stringBuilder.toString();
            if (!TextUtils.isEmpty(stringBuilder2) && evnetListener != null) {
                AlertDialog a = new AlertDialog.a((Activity) evnetListener).c(17301543).a((CharSequence) "信息流附属信息").b(stringBuilder2).a();
                a.a((CharSequence) "确定", new 4(this));
                a.show();
            }
        }
    }
}
