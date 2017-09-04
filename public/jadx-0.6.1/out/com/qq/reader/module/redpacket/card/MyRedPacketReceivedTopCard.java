package com.qq.reader.module.redpacket.card;

import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class MyRedPacketReceivedTopCard extends a {
    private int amount;
    private int red_packet_count;

    public MyRedPacketReceivedTopCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.my_red_packet_top_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.amount = jSONObject.optInt("totalAmount");
        this.red_packet_count = jSONObject.optInt("total");
        if (this.amount > 0) {
            return true;
        }
        return false;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_red_packet_count_desc);
        ((TextView) ap.a(getRootView(), R.id.tv_total_amount)).setText(String.valueOf(this.amount));
        textView.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.my_red_packet_count_received_desc), new Object[]{String.valueOf(this.red_packet_count)}));
    }
}
