package com.qq.reader.module.redpacket.singlebookpacket;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookInValidCard;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookValidCard;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RedPacketSingleBookPage */
public class d {
    private List<a> a = new ArrayList();
    private boolean b = false;
    private boolean c = false;
    private com.qq.reader.module.bookstore.qnative.c.a d = null;
    private long e = -1;

    public void a(long j) {
        this.e = j;
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.d = aVar;
    }

    public com.qq.reader.module.bookstore.qnative.c.a c() {
        return this.d;
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        if ((this.a == null || this.a.size() == 0) && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("hasnext") > 0) {
                    this.b = false;
                } else {
                    this.b = true;
                }
                if (jSONObject.getInt("code") == -100) {
                    this.c = true;
                } else {
                    this.c = false;
                }
                this.a = a(jSONObject.getJSONArray("list"), c());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private List<a> a(JSONArray jSONArray, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RedPacket redPacket = new RedPacket();
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                redPacket.f(jSONObject.getLong("cbid"));
                redPacket.b(jSONObject.getLong("rid"));
                redPacket.b(jSONObject.getString(SocialConstants.PARAM_SEND_MSG));
                redPacket.c(jSONObject.getString("nick"));
                redPacket.d(jSONObject.getString(MessageKey.MSG_ICON));
                redPacket.d(jSONObject.getLong("ct"));
                redPacket.e(jSONObject.getLong("et"));
                redPacket.b(jSONObject.getInt("type"));
                redPacket.a(jSONObject.getInt("status"));
                redPacket.d(jSONObject.optInt("fansLevel", -1));
                redPacket.h(jSONObject.optInt("isAuthor", -1));
                redPacket.a(jSONObject.optLong("costTime", -1));
                redPacket.c(this.e);
            } catch (Exception e) {
                e.printStackTrace();
            }
            arrayList.add(a(redPacket, aVar));
        }
        return arrayList;
    }

    public static a a(RedPacket redPacket, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        if (redPacket == null) {
            return null;
        }
        a singleBookValidCard;
        if (redPacket.c() == 2) {
            singleBookValidCard = new SingleBookValidCard(null, SingleBookValidCard.class.getSimpleName());
        } else {
            singleBookValidCard = new SingleBookInValidCard(null, SingleBookValidCard.class.getSimpleName());
        }
        singleBookValidCard.setItem(redPacket);
        singleBookValidCard.setEventListener(aVar);
        return singleBookValidCard;
    }

    public void a(List<a> list) {
        a((List) list, false);
    }

    public void a(List<a> list, boolean z) {
        if (z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.a.add(0, list.get(size));
            }
            return;
        }
        this.a.addAll(list);
    }

    public List<a> d() {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        return this.a;
    }
}
