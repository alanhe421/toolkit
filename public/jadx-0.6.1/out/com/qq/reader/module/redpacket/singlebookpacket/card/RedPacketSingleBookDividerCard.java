package com.qq.reader.module.redpacket.singlebookpacket.card;

import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class RedPacketSingleBookDividerCard extends MessageBaseCard {
    public RedPacketSingleBookDividerCard(b bVar) {
        super(bVar);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public int getResLayoutId() {
        return R.layout.red_packet_single_book_divider_layout;
    }

    public void attachView() {
        getRootView().setClickable(false);
    }
}
