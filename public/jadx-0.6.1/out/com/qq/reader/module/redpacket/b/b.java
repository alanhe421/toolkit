package com.qq.reader.module.redpacket.b;

import com.qq.reader.module.redpacket.model.RedPacket;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RedPacketParser */
public class b {
    public static ArrayList<RedPacket> a(JSONArray jSONArray, int i) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        ArrayList<RedPacket> arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            RedPacket redPacket = new RedPacket();
            redPacket.a(optJSONObject.optString("bname"));
            redPacket.b(optJSONObject.optLong("rid"));
            redPacket.c(optJSONObject.optString("nick"));
            redPacket.b(optJSONObject.optInt("type"));
            redPacket.b(optJSONObject.optString(SocialConstants.PARAM_SEND_MSG));
            redPacket.c(optJSONObject.optLong("bid"));
            redPacket.e(optJSONObject.optString("name"));
            redPacket.d(optJSONObject.optLong("ct"));
            redPacket.e(optJSONObject.optLong("et"));
            redPacket.e(optJSONObject.optInt("yw"));
            redPacket.d(optJSONObject.optString(MessageKey.MSG_ICON));
            redPacket.f(optJSONObject.optLong("cbid"));
            redPacket.c(i);
            redPacket.i(optJSONObject.optInt("online"));
            arrayList.add(redPacket);
        }
        return arrayList;
    }

    public static RedPacket a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RedPacket redPacket = new RedPacket();
        redPacket.b(Long.MAX_VALUE);
        redPacket.b(jSONObject.optString("title"));
        redPacket.d(jSONObject.optLong("publishTime"));
        redPacket.e(1);
        redPacket.d(jSONObject.optString("imageUrl"));
        redPacket.f(jSONObject.optString(SocialConstants.PARAM_URL));
        redPacket.a(true);
        return redPacket;
    }
}
