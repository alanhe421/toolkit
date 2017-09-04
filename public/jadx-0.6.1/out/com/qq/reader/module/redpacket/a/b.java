package com.qq.reader.module.redpacket.a;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.an;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforEmptyRefreshable;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.redpacket.b.a;
import com.qq.reader.module.redpacket.card.RedPacketRankBookItemCard;
import com.qq.reader.module.redpacket.card.RedPacketRankBookTopCard;
import com.qq.reader.module.redpacket.card.RedPacketRankUserItemCard;
import com.qq.reader.module.redpacket.card.RedPacketRankUserTopCard;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerRedPacketRankListPage */
public class b extends af {
    private static d a;
    private int b;

    public b(Bundle bundle) {
        super(bundle);
    }

    public String x() {
        if (this.b == 1) {
            return ReaderApplication.getApplicationImp().getResources().getString(R.string.red_packet_rank_user_empty_tip);
        }
        if (this.b == 2) {
            return ReaderApplication.getApplicationImp().getResources().getString(R.string.red_packet_rank_book_empty_tip);
        }
        return "";
    }

    public String a(Bundle bundle) {
        String str = null;
        c cVar = new c(bundle);
        this.b = bundle.getInt("RANK_TYPE", 0);
        String string = bundle.getString("KEY_ACTIONTAG");
        if (this.b == 1) {
            str = "user?";
        } else if (this.b == 2) {
            str = "book?";
        }
        return cVar.a(a.a, str + "type=" + string);
    }

    public void b(JSONObject jSONObject) {
        int i = 3;
        if (jSONObject != null) {
            JSONArray optJSONArray;
            int length;
            com.qq.reader.module.bookstore.qnative.card.a redPacketRankUserTopCard;
            if (this.b == 1) {
                optJSONArray = jSONObject.optJSONArray("list");
                if (optJSONArray != null) {
                    length = optJSONArray.length();
                    if (length > 0) {
                        redPacketRankUserTopCard = new RedPacketRankUserTopCard(this, "RedPacketRankUserTopCard");
                        redPacketRankUserTopCard.fillData(jSONObject);
                        redPacketRankUserTopCard.setEventListener(l());
                        this.k.add(redPacketRankUserTopCard);
                    }
                    if (length > 3) {
                        while (i < length) {
                            redPacketRankUserTopCard = new RedPacketRankUserItemCard(this, "RedPacketRankUserItemCard", i + 1);
                            redPacketRankUserTopCard.fillData(optJSONArray.optJSONObject(i));
                            redPacketRankUserTopCard.setEventListener(l());
                            this.k.add(redPacketRankUserTopCard);
                            i++;
                        }
                    }
                }
            } else if (this.b == 2) {
                optJSONArray = jSONObject.optJSONArray("list");
                if (optJSONArray != null) {
                    length = optJSONArray.length();
                    if (length > 0) {
                        redPacketRankUserTopCard = new RedPacketRankBookTopCard(this, "RedPacketRankBookTopCard");
                        redPacketRankUserTopCard.fillData(jSONObject);
                        redPacketRankUserTopCard.setEventListener(l());
                        this.k.add(redPacketRankUserTopCard);
                    }
                    if (length > 3) {
                        while (i < length) {
                            redPacketRankUserTopCard = new RedPacketRankBookItemCard(this, "RedPacketRankBookItemCard", i + 1);
                            redPacketRankUserTopCard.fillData(optJSONArray.optJSONObject(i));
                            redPacketRankUserTopCard.setEventListener(l());
                            this.k.add(redPacketRankUserTopCard);
                            i++;
                        }
                    }
                }
            }
        }
        z();
    }

    private void z() {
        try {
            if (a == null) {
                JSONObject jSONObject = new JSONObject(an.a().a(3));
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
                com.qq.reader.module.bookstore.qnative.page.d.b bVar = (com.qq.reader.module.bookstore.qnative.page.d.b) g.get(i);
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
            }
        }
        this.n = a;
    }

    public Class c() {
        return NativePageFragmentforEmptyRefreshable.class;
    }

    public boolean a() {
        return false;
    }

    public Bundle y() {
        return this.f;
    }
}
