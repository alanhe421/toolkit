package com.qq.reader.module.redpacket.card;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class RedPacketRankUserItemCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private Drawable bookvipMonth;
    private Drawable bookvipYear;
    private c clickListener = new c(this) {
        final /* synthetic */ RedPacketRankUserItemCard a;

        {
            this.a = r1;
        }

        public void a(View view) {
            if (this.a.item != null) {
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.item.e, this.a.item.d, this.a.item.a, this.a.item.b);
            }
        }
    };
    private int index;
    private a item;
    private int vipDrawablePadding = 0;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public String d;
        public boolean e;
        public int f;
        final /* synthetic */ RedPacketRankUserItemCard g;

        private a(RedPacketRankUserItemCard redPacketRankUserItemCard) {
            this.g = redPacketRankUserItemCard;
        }

        public void parseData(JSONObject jSONObject) {
            boolean z = true;
            this.a = jSONObject.optString(MessageKey.MSG_ICON);
            this.b = jSONObject.optString("name");
            this.c = jSONObject.optString("totalMoney");
            this.d = jSONObject.optString("id");
            if (jSONObject.optInt("type") != 1) {
                z = false;
            }
            this.e = z;
            this.f = jSONObject.optInt("vipStatus", 0);
        }
    }

    public RedPacketRankUserItemCard(b bVar, String str, int i) {
        super(bVar, str);
        this.index = i;
        this.bookvipMonth = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.bookvip_month);
        this.bookvipMonth = this.bookvipMonth.mutate().getConstantState().newDrawable();
        this.bookvipMonth.setBounds(0, 0, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_height));
        this.bookvipYear = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.bookvip_year);
        this.bookvipYear = this.bookvipYear.mutate().getConstantState().newDrawable();
        this.bookvipYear.setBounds(0, 0, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_height));
        this.vipDrawablePadding = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_margin_left);
    }

    public int getResLayoutId() {
        return R.layout.red_packet_rank_user_item_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.item = new a();
        this.item.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        if (this.item != null) {
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.img_icon);
            TextView textView = (TextView) ap.a(getRootView(), R.id.tv_name);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_amount);
            ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.img_icon_mask);
            ((TextView) ap.a(getRootView(), R.id.tv_index)).setText(String.format("%02d", new Object[]{Integer.valueOf(this.index)}));
            textView.setText(this.item.b);
            if (TextUtils.isEmpty(this.item.c)) {
                textView2.setText("0");
            } else {
                textView2.setText(this.item.c);
            }
            if (this.item.f == 1 && !this.item.e) {
                textView.setCompoundDrawables(null, null, this.bookvipMonth, null);
                textView.setCompoundDrawablePadding(this.vipDrawablePadding);
            } else if (this.item.f != 2 || this.item.e) {
                textView.setCompoundDrawables(null, null, null, null);
                textView.setCompoundDrawablePadding(0);
            } else {
                textView.setCompoundDrawables(null, null, this.bookvipYear, null);
                textView.setCompoundDrawablePadding(this.vipDrawablePadding);
            }
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.item.a, imageView, com.qq.reader.common.imageloader.a.a().e());
            imageView2.setOnClickListener(this.clickListener);
            getRootView().setOnClickListener(this.clickListener);
        }
    }
}
