package com.qq.reader.module.redpacket.card;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class RedPacketRankBookItemCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private c clickListener = new c(this) {
        final /* synthetic */ RedPacketRankBookItemCard a;

        {
            this.a = r1;
        }

        public void a(View view) {
            if (this.a.item != null) {
                o.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.item.d), null, null, null);
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.a.getStaticsOrigin());
                i.a("event_D212", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    };
    private int index;
    private a item;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public long d;
        public String e;
        final /* synthetic */ RedPacketRankBookItemCard f;

        private a(RedPacketRankBookItemCard redPacketRankBookItemCard) {
            this.f = redPacketRankBookItemCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optString(MessageKey.MSG_ICON);
            this.b = jSONObject.optString("title");
            this.c = jSONObject.optString("authorName");
            this.d = jSONObject.optLong("bid");
            this.e = jSONObject.optString("totalMoney");
        }
    }

    public RedPacketRankBookItemCard(b bVar, String str, int i) {
        super(bVar, str);
        this.index = i;
    }

    private String getStaticsOrigin() {
        b bindPage = getBindPage();
        if (bindPage != null && (bindPage instanceof com.qq.reader.module.redpacket.a.b)) {
            Bundle y = ((com.qq.reader.module.redpacket.a.b) bindPage).y();
            if (y != null) {
                return y.getString("KEY_ACTIONTAG");
            }
        }
        return null;
    }

    public int getResLayoutId() {
        return R.layout.red_packet_rank_book_item_layout;
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
            TextView textView = (TextView) ap.a(getRootView(), R.id.tv_book_name);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_author_name);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_amount);
            ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.img_icon_mask);
            ((TextView) ap.a(getRootView(), R.id.tv_index)).setText(String.format("%02d", new Object[]{Integer.valueOf(this.index)}));
            textView.setText(this.item.b);
            textView2.setText(this.item.c);
            textView3.setText(this.item.e);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(this.item.d), imageView, com.qq.reader.common.imageloader.a.a().j());
            imageView2.setOnClickListener(this.clickListener);
            getRootView().setOnClickListener(this.clickListener);
        }
    }
}
