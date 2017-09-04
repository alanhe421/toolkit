package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class SurroundItemCard extends a {
    private String mDesc;
    private String mPicUrl;
    private String mQurl;
    private String mTime;
    private String mTitle;
    private int mType;
    private String mTypeIcon;

    public SurroundItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.surround_item_card_layout;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.surrounding_item_time);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.surrounding_item_pic);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.surrounding_item_title);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.surrounding_item_desc);
        setImage((ImageView) ap.a(getRootView(), R.id.surrounding_item_type_icon), this.mTypeIcon, null);
        textView.setText(this.mTime);
        setImage(imageView, this.mPicUrl, null);
        textView2.setText(this.mTitle);
        textView3.setText(this.mDesc);
        View rootView = getRootView();
        if (rootView != null) {
            rootView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SurroundItemCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    try {
                        c.a(this.a.getEvnetListener().getFromActivity(), this.a.mQurl, null);
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mTitle = jSONObject.optString("title");
        this.mTime = jSONObject.optString("time");
        this.mPicUrl = jSONObject.optString("picUrl");
        this.mDesc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mTypeIcon = jSONObject.optString("typeIcon");
        this.mType = jSONObject.optInt("type");
        this.mQurl = jSONObject.optString("qurl");
        return true;
    }

    protected void setImage(ImageView imageView, String str, OnClickListener onClickListener) {
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, imageView);
        if (imageView != null && onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }
}
