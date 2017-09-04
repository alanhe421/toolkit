package com.qq.reader.module.bookstore.charge.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class MonthlyChargeProfileCard extends ChargeBaseCard {
    private String mAvatar;
    @Deprecated
    private int mGfrom = 1;
    private String mHelpCenter;
    private String mMonthlyVipEndTime;
    private String mName;
    private int mQQOpenType;
    private int mStatus;
    private String mVipIntro;
    private String mYearVipEndTime;

    public MonthlyChargeProfileCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.charge_month_profile_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mStatus = jSONObject.optInt("status", -1);
        this.mQQOpenType = jSONObject.optInt("type");
        this.mAvatar = jSONObject.optString(MessageKey.MSG_ICON);
        this.mName = jSONObject.optString("name");
        this.mGfrom = jSONObject.optInt("gfrom", 1);
        if ((TextUtils.isEmpty(this.mAvatar) || TextUtils.isEmpty(this.mName)) && c.b()) {
            a c = c.c();
            this.mAvatar = c.b();
            this.mName = c.a();
        }
        long optLong = jSONObject.optLong("endtime");
        if (optLong != 0) {
            Date date = new Date();
            date.setTime(optLong);
            this.mMonthlyVipEndTime = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
        }
        optLong = jSONObject.optLong("endtimeYearVip");
        if (optLong != 0) {
            date = new Date();
            date.setTime(optLong);
            this.mYearVipEndTime = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
        }
        switch (this.mStatus) {
            case 1:
                if (this.mQQOpenType != 1) {
                    this.mVipIntro = this.mMonthlyVipEndTime + "包月到期";
                    break;
                }
                this.mVipIntro = "已开通";
                break;
            case 2:
                this.mVipIntro = this.mYearVipEndTime + "年费到期";
                break;
            case 3:
                this.mVipIntro = this.mYearVipEndTime + "年费到期，" + this.mMonthlyVipEndTime + "包月到期";
                break;
            default:
                this.mVipIntro = "你还不是包月VIP";
                break;
        }
        return true;
    }

    public void attachView() {
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mAvatar, (ImageView) ap.a(getRootView(), R.id.charge_avatar), com.qq.reader.common.imageloader.a.a().i());
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.charge_avatar_viptag);
        if (this.mStatus == 1) {
            imageView.setImageResource(R.drawable.bookvip_month);
        } else if (this.mStatus == 2 || this.mStatus == 3) {
            imageView.setImageResource(R.drawable.bookvip_year);
        } else {
            imageView.setImageResource(R.drawable.bookvip_none);
        }
        ((TextView) ap.a(getRootView(), R.id.charge_name)).setText(this.mName);
        ((TextView) ap.a(getRootView(), R.id.charge_info)).setText(this.mVipIntro);
        ((TextView) ap.a(getRootView(), R.id.charge_help)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MonthlyChargeProfileCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.h(this.a.getEvnetListener().getFromActivity(), this.a.mHelpCenter, null);
            }
        });
    }

    public int getStatus() {
        return this.mStatus;
    }

    public int getQQOpenType() {
        return this.mQQOpenType;
    }

    public int getGfrom() {
        return this.mGfrom;
    }

    public void setHelpCenter(String str) {
        this.mHelpCenter = str;
    }
}
