package com.qq.reader.module.feed.card;

import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedAdvCard extends FeedBaseCard {
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_IMAGE0 = "img0";
    private static final String JSON_KEY_IMAGE1 = "img1";
    private static final String JSON_KEY_IMAGE2 = "img2";
    private static final String JSON_KEY_PICS = "pics";
    private static final String JSON_KEY_PICS_URL = "url";
    private static final String JSON_KEY_TITLE = "title";
    private String mDesc;
    private String mImg1Url;
    private String mImg2Url;
    private String mImgUrl;
    private int showPicHeight = ((a.bU * 109) / ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT);

    public FeedAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_adv_layout;
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.concept_img);
        LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = this.showPicHeight;
        imageView.setLayoutParams(layoutParams);
        setImage(imageView, this.mImgUrl, null);
        checkClickEnable();
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mDesc = jSONObject.optString("desc");
        this.mTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("pics");
        if (!(optJSONArray == null || optJSONArray.optJSONObject(0) == null)) {
            this.mImgUrl = optJSONArray.optJSONObject(0).optString("url");
        }
        return true;
    }
}
