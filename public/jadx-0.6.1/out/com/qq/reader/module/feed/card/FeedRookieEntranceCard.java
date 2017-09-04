package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.d;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedRookieEntranceCard extends FeedBaseCard {
    public static final int BOOK_LIST = 5;
    public static final int DEFAULT_LOGIN = 6;
    public static final int GIFT_AREA = 2;
    public static final String JSON_KEY_CLICK_STATE = "CStatus";
    public static final String JSON_KEY_NAVIGATION_BAR = "navigationBar";
    private static final String JSON_KEY_QURL = "qurl";
    private static final String JSON_KEY_ROOKIE_AUTHOR_ICON = "authorIcon";
    private static final String JSON_KEY_ROOKIE_DESC = "desc";
    private static final String JSON_KEY_ROOKIE_SHOW_RED = "red";
    private static final String JSON_KEY_ROOKIE_TITLE = "title";
    public static final String JSON_KEY_ROOKIE_TYPE = "type";
    public static final int LOGIN_NO_RECEIVED = 1;
    public static final int LOGIN_RECEIVED = 0;
    public static final int NATURE = 4;
    public static final int TASK = 3;
    private String content;
    public JSONObject jObj;
    private int mClickStates = 0;
    private int mStates;
    private String rightUrl;
    private boolean showRedIcon;
    private String title;

    public boolean isLoginCard() {
        return this.mStates == 1 || this.mStates == 0 || this.mStates == 6;
    }

    public int getStates() {
        return this.mStates;
    }

    public String getContent() {
        return this.content + "";
    }

    public FeedRookieEntranceCard(b bVar, String str) {
        super(bVar, str);
    }

    public void initDefaultLogin(a aVar) {
        this.mStates = 6;
        this.title = ReaderApplication.getApplicationImp().getString(R.string.rookie_login_item);
        setEventListener(aVar);
        bindCmd();
    }

    public int getResLayoutId() {
        return R.layout.concept_rookie_entrance_card;
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.iv_right_icon);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.red_icon);
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_content);
        setLeftIconBg((ImageView) ap.a(getRootView(), R.id.iv_left_icon));
        setLoginStyle();
        if (this.showRedIcon) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        alignLayout(imageView, textView2);
        if (this.mStates == 1 || this.mStates == 0) {
            textView.setText(this.content);
        } else {
            textView.setText(this.title);
            textView2.setText(this.content);
        }
        c.a(getEvnetListener().getFromActivity()).a(this.rightUrl, imageView, com.qq.reader.common.imageloader.a.a().b());
        UploadLog();
        changeClickStates(imageView2, textView2);
        getRootView().setOnClickListener(new 1(this));
    }

    private void setLoginStyle() {
        int i;
        int i2;
        int i3 = R.drawable.list_item_enter_icon;
        switch (this.mStates) {
            case 0:
            case 1:
            case 6:
                i3 = R.drawable.list_item_enter_icon_white;
                i = R.drawable.feed_adv_background_no_login_selector;
                i2 = 17170443;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                i = R.drawable.book_shelf_adv_background_selector;
                i2 = R.color.feed_rookie_title_color;
                break;
            default:
                i = R.drawable.concept_card_bg_selector;
                i2 = R.color.feed_rookie_title_color;
                break;
        }
        ap.a(getRootView(), R.id.rl_content_bg).setBackgroundResource(i);
        ((ImageView) ap.a(getRootView(), R.id.right_arrow)).setImageResource(i3);
        ((TextView) ap.a(getRootView(), R.id.tv_title)).setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(i2));
    }

    private void changeClickStates(View view, View view2) {
        switch (this.mStates) {
            case 3:
            case 4:
            case 5:
                if (this.mClickStates == 1) {
                    view.setVisibility(8);
                    view2.setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void UploadLog() {
        switch (this.mStates) {
            case 2:
                i.a("event_A194", null, getEvnetListener().getFromActivity());
                break;
            case 3:
                i.a("event_A195", null, getEvnetListener().getFromActivity());
                i.a("event_A194", null, getEvnetListener().getFromActivity());
                break;
            case 4:
                i.a("event_A194", null, getEvnetListener().getFromActivity());
                break;
            case 5:
                i.a("event_A194", null, getEvnetListener().getFromActivity());
                break;
        }
        i.a("event_A196", null, getEvnetListener().getFromActivity());
    }

    private void setLeftIconBg(ImageView imageView) {
        int i = R.drawable.rookie_icon;
        switch (this.mStates) {
            case 0:
            case 1:
            case 6:
                i = R.drawable.card_login_icon;
                break;
        }
        imageView.setBackgroundResource(i);
    }

    private void alignLayout(View view, TextView textView) {
        switch (this.mStates) {
            case 0:
            case 1:
            case 6:
                textView.setVisibility(8);
                view.setVisibility(8);
                return;
            case 2:
                textView.setVisibility(0);
                view.setVisibility(0);
                return;
            case 3:
            case 4:
            case 5:
                textView.setVisibility(0);
                view.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mClickStates = jSONObject.optInt(JSON_KEY_CLICK_STATE, 0);
        JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_NAVIGATION_BAR);
        this.mStates = jSONObject2.optInt("type", -1);
        this.content = jSONObject2.optString("desc");
        this.rightUrl = jSONObject2.optString(JSON_KEY_ROOKIE_AUTHOR_ICON);
        this.showRedIcon = jSONObject2.optBoolean(JSON_KEY_ROOKIE_SHOW_RED);
        this.title = jSONObject2.optString("title");
        if (TextUtils.isEmpty(this.title)) {
            this.title = ReaderApplication.getApplicationImp().getString(R.string.rookie_area);
        }
        if (TextUtils.isEmpty(this.mQURL)) {
            this.mQURL = jSONObject2.optString(JSON_KEY_QURL);
        }
        bindCmd();
        return true;
    }

    public void bindCmd() {
        if (this.mStates == 1 || this.mStates == 0 || this.mStates == 6) {
            setCmd(new d("goRookieLogin", this.mQURL));
        }
    }

    public boolean swipeEnable() {
        return false;
    }

    public void doClickedCard() {
        super.doClickedCard();
        if (this.mStates != 2) {
            ap.a(getRootView(), R.id.red_icon).setVisibility(8);
            ap.a(getRootView(), R.id.tv_content).setVisibility(8);
        }
        this.mClickStates = 1;
        try {
            FeedRookieEntranceCard bV;
            if (this.mStates == 1 || this.mStates == 0 || this.mStates == 6) {
                bV = com.qq.reader.appconfig.a.d.bV(ReaderApplication.getApplicationImp());
                if (bV != null) {
                    com.qq.reader.appconfig.a.d.L(ReaderApplication.getApplicationImp(), bV.jObj.put(JSON_KEY_CLICK_STATE, 1).toString());
                    return;
                }
                return;
            }
            bV = com.qq.reader.appconfig.a.d.bW(ReaderApplication.getApplicationImp());
            if (bV != null) {
                com.qq.reader.appconfig.a.d.M(ReaderApplication.getApplicationImp(), bV.jObj.put(JSON_KEY_CLICK_STATE, 1).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOnFeedClicked(boolean z) {
        if (this.mStates == 1 || this.mStates == 0 || this.mStates == 6) {
            if (getCmd() != null) {
                com.qq.reader.module.feed.activity.a.a(getCmd(), getEvnetListener());
            }
        } else if (com.qq.reader.qurl.c.a(this.mQURL)) {
            try {
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), this.mQURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (z) {
            doClickedCard();
        }
        switch (this.mStates) {
            case 2:
                i.a("event_A197", null, getEvnetListener().getFromActivity());
                break;
            case 3:
                i.a("event_A198", null, getEvnetListener().getFromActivity());
                i.a("event_A197", null, getEvnetListener().getFromActivity());
                break;
            case 4:
                i.a("event_A197", null, getEvnetListener().getFromActivity());
                break;
            case 5:
                i.a("event_A197", null, getEvnetListener().getFromActivity());
                break;
        }
        i.a("event_A199", null, ReaderApplication.getApplicationImp());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FeedRookieEntranceCard)) {
            return false;
        }
        if (getStates() == ((FeedRookieEntranceCard) obj).getStates() && getContent().equals(((FeedRookieEntranceCard) obj).getContent()) && this.showRedIcon == ((FeedRookieEntranceCard) obj).showRedIcon) {
            return true;
        }
        return false;
    }
}
