package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class DiscoveryCommentDetailCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private int[] bgResId = new int[]{R.drawable.discovery_comment_header_item_1_bg, R.drawable.discovery_comment_header_item_2_bg, R.drawable.discovery_comment_header_item_3_bg};

    public class a extends s {
        public long a;
        public String b;
        public int c;
        public int d;
        public String e;
        final /* synthetic */ DiscoveryCommentDetailCard f;

        public a(DiscoveryCommentDetailCard discoveryCommentDetailCard) {
            this.f = discoveryCommentDetailCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.b = jSONObject.optString("title");
            this.c = jSONObject.optInt("commentcount");
            this.d = jSONObject.optInt("score");
            this.e = jSONObject.optString("topuser");
            if (!TextUtils.isEmpty(this.e)) {
                this.e = this.e.replace("[", "").replace("]", "").replace("\"", "").replace(",", "、");
            }
            this.a = jSONObject.optLong("bid");
        }
    }

    public DiscoveryCommentDetailCard(b bVar, String str) {
        super(bVar, str);
        setCardId(str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_discovery_comment_detail_info;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        s aVar = new a(this);
        aVar.parseData(jSONObject);
        getItemList().clear();
        addItem(aVar);
        return true;
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            a aVar = (a) getItemList().get(0);
            View a = ap.a(getRootView(), R.id.discovery_comment_detail_content);
            if (aVar.a == 1) {
                a.setBackgroundResource(this.bgResId[0]);
            } else if (aVar.a == 2) {
                a.setBackgroundResource(this.bgResId[1]);
            } else if (aVar.a == 3) {
                a.setBackgroundResource(this.bgResId[2]);
            }
            setIcon((ImageView) ap.a(getRootView(), R.id.discovery_comment_detail_icon), aVar);
            ((TextView) ap.a(getRootView(), R.id.discovery_comment_detail_name)).setText(aVar.b);
            ((TextView) ap.a(getRootView(), R.id.discovery_comment_detail_comments_amount)).setText("" + aVar.c);
            ((TextView) ap.a(getRootView(), R.id.discovery_comment_detail_hotweek_amount)).setText("" + aVar.d);
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_bookclub_admin);
            if (TextUtils.isEmpty(aVar.e)) {
                linearLayout.setVisibility(8);
                return;
            }
            linearLayout.setVisibility(0);
            ((TextView) ap.a(getRootView(), R.id.discovery_comment_detail_admin)).setText("" + aVar.e);
        }
    }

    private void setIcon(ImageView imageView, a aVar) {
        if (aVar.a == 1) {
            imageView.setBackgroundResource(R.drawable.discovery_comment_header_item_1_image);
        } else if (aVar.a == 2) {
            imageView.setBackgroundResource(R.drawable.discovery_comment_header_item_2_image);
        } else if (aVar.a == 3) {
            imageView.setBackgroundResource(R.drawable.discovery_comment_header_item_3_image);
        }
    }
}
