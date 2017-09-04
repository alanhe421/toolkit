package com.qq.reader.module.redpacket.a;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.an;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforEmptyRefreshable;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.redpacket.card.MyRedPacketReceivedItemCard;
import com.qq.reader.module.redpacket.card.MyRedPacketReceivedTopCard;
import com.qq.reader.module.redpacket.card.MyRedPacketSendItemCard;
import com.qq.reader.module.redpacket.card.MyRedPacketSendTopCard;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerMyRedPacketListPage */
public class a extends af {
    private static d a;

    public a(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(com.qq.reader.module.redpacket.b.a.b, bundle.getString("KEY_ACTIONTAG") + "?");
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        y();
        if (jSONObject != null) {
            this.o = (long) jSONObject.optInt("pagestamp");
            if (jSONObject.optInt("totalAmount") > 0) {
                int i2;
                com.qq.reader.module.bookstore.qnative.card.a myRedPacketSendTopCard;
                String string = this.f.getString("KEY_ACTIONTAG");
                long j = this.f.getLong("KEY_PAGEINDEX", 1);
                if ("sent".equals(string)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (j == 1) {
                    if (i2 != 0) {
                        myRedPacketSendTopCard = new MyRedPacketSendTopCard(this, "MyRedPacketSendTopCard");
                    } else {
                        myRedPacketSendTopCard = new MyRedPacketReceivedTopCard(this, "MyRedPacketReceivedTopCard");
                    }
                    if (myRedPacketSendTopCard != null) {
                        myRedPacketSendTopCard.fillData(jSONObject);
                        myRedPacketSendTopCard.setEventListener(l());
                        this.k.add(myRedPacketSendTopCard);
                    }
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("redPackageList");
                while (optJSONArray != null && i < optJSONArray.length()) {
                    if (i2 != 0) {
                        myRedPacketSendTopCard = new MyRedPacketSendItemCard(this, "MyRedPacketSendItemCard");
                    } else {
                        myRedPacketSendTopCard = new MyRedPacketReceivedItemCard(this, "MyRedPacketReceivedItemCard");
                    }
                    if (myRedPacketSendTopCard != null) {
                        myRedPacketSendTopCard.fillData(optJSONArray.optJSONObject(i));
                        myRedPacketSendTopCard.setEventListener(l());
                        this.k.add(myRedPacketSendTopCard);
                    }
                    i++;
                }
            }
        }
    }

    private void y() {
        try {
            if (a == null) {
                JSONObject jSONObject = new JSONObject(an.a().a(2));
                a = new d();
                a.a(jSONObject);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = a.g();
            for (int i = 0; i < g.size(); i++) {
                b bVar = (b) g.get(i);
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
            }
        }
        this.n = a;
    }

    public String x() {
        String string = this.f.getString("KEY_ACTIONTAG");
        if ("received".equals(string)) {
            return ReaderApplication.getApplicationImp().getResources().getString(R.string.my_red_packet_no_received_tip);
        }
        if ("sent".equals(string)) {
            return ReaderApplication.getApplicationImp().getResources().getString(R.string.my_red_packet_no_sent_tip);
        }
        return "";
    }

    public Class c() {
        return NativePageFragmentforEmptyRefreshable.class;
    }

    public boolean a() {
        return false;
    }
}
