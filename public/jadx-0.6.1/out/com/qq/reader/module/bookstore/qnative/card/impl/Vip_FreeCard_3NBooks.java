package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class Vip_FreeCard_3NBooks extends FreeCard_3NBooks {
    private String bottomDesc;
    private String desc;
    private int isVip;
    private String qurl;

    public Vip_FreeCard_3NBooks(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        super.attachView();
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.freecard_title_layout);
        if (cardTitle != null) {
            cardTitle.setCardIntroduction(this.desc);
        }
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
        if (cardMoreView != null) {
            if (this.isVip == 1) {
                cardMoreView.setText("换一换");
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ Vip_FreeCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.refresh();
                    }
                });
            } else {
                i.a("event_F178", null, ReaderApplication.getApplicationImp());
                if (TextUtils.isEmpty(this.bottomDesc)) {
                    this.bottomDesc = "开通包月";
                } else {
                    cardMoreView.setText(this.bottomDesc);
                }
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ Vip_FreeCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        try {
                            if (c.b()) {
                                i.a("event_F179", null, ReaderApplication.getApplicationImp());
                                com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.qurl);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("function_type", 3);
                            bundle.putBoolean("need_reload", true);
                            this.a.getEvnetListener().doFunction(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            cardMoreView.setVisibility(0);
        }
    }

    public void refresh() {
        initRandomItem(false);
        attachView();
    }

    protected void showStatics() {
        i.a("event_F181", null, ReaderApplication.getApplicationImp());
    }

    protected void clickStatics() {
        i.a("event_F182", null, ReaderApplication.getApplicationImp());
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        boolean parseData = super.parseData(jSONObject);
        this.desc = jSONObject.optString("pushName");
        JSONObject optJSONObject = jSONObject.optJSONObject("ext");
        this.qurl = optJSONObject.optString("toUrl");
        this.isVip = optJSONObject.optInt("isVip");
        this.bottomDesc = optJSONObject.optString("txt");
        return parseData;
    }
}
