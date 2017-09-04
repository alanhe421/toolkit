package com.qq.reader.module.bookstore.charge.card;

import android.widget.ImageView;
import com.qq.reader.common.c.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ChargeAdvCard extends ChargeBaseCard {
    private final String TAG = "ChargeAdvCard";
    private String mImgURL;
    private int mPicHeight = 0;

    public ChargeAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.charge_adv_img;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mImgURL = jSONObject.optString("imgad");
        return true;
    }

    public void attachView() {
        this.mPicHeight = (a.bU * 93) / 360;
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.charge_adv_img);
        imageView.getLayoutParams().height = this.mPicHeight;
        c.a(getEvnetListener().getFromActivity()).a(this.mImgURL, imageView, com.qq.reader.common.imageloader.a.a().a(R.color.localstore_img_loading, R.drawable.chargebanner_default));
    }
}
