package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.v;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ListCard4AudioBook extends ListCardCommon {
    private Bundle entranBundle;

    public class a extends v {
        final /* synthetic */ ListCard4AudioBook a;
        private long b;

        public a(ListCard4AudioBook listCard4AudioBook) {
            this.a = listCard4AudioBook;
        }

        private void u() {
            Map hashMap = new HashMap();
            if (this.a.entranBundle != null) {
                hashMap.put(s.ORIGIN, this.a.entranBundle.getString("KEY_ACTIONID"));
            }
            i.a("event_B245", hashMap, ReaderApplication.getApplicationImp());
        }

        public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
            u();
            super.a(aVar);
        }

        public void a(View view, int i, boolean z) {
            super.a(view, i, z);
            TextView textView = (TextView) ap.a(view, R.id.concept_order);
            if (this.b > 0) {
                try {
                    textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_order_color));
                    textView.setBackgroundResource(R.drawable.concept_bookitem_order);
                    textView.setText(j.a(this.b) + "播放");
                    textView.setVisibility(0);
                } catch (Exception e) {
                    c.e("Err", e.getMessage());
                }
            }
        }

        public void parseData(JSONObject jSONObject) {
            super.parseData(jSONObject);
            if (jSONObject != null) {
                this.b = jSONObject.optLong("num", 0);
            }
        }
    }

    public ListCard4AudioBook(b bVar, String str, Bundle bundle) {
        super(bVar, str);
        this.entranBundle = bundle;
    }

    public s createListItem() {
        return new a(this);
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_listcard_item;
    }
}
