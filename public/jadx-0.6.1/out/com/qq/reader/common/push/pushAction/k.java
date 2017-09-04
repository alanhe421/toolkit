package com.qq.reader.common.push.pushAction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.model.RedPacketMessage;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RedPacketAction */
public class k extends i {
    public static long c = 3577552342434L;
    private List<JSONObject> d = null;

    public k(Context context, List<JSONObject> list) {
        super(context);
        this.d = list;
    }

    public void a(JSONObject jSONObject) {
        int i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        if (this.d != null) {
            String str = "";
            str = "";
            str = "";
            str = "";
            int i2 = 0;
            str = "";
            str = "";
            for (JSONObject jSONObject2 : this.d) {
                JSONObject optJSONObject = jSONObject2.optJSONObject("Data");
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("Type", 1);
                    if (optInt == 1) {
                        long optLong = optJSONObject.optLong("HongBaoId");
                        if (optLong != 0) {
                            optInt = optJSONObject.optInt("HongBaoType");
                            String optString = optJSONObject.optString("Message");
                            String optString2 = optJSONObject.optString("UserName");
                            String optString3 = optJSONObject.optString("HeadImg");
                            long optLong2 = optJSONObject.optLong("BookId");
                            int optInt2 = optJSONObject.optInt("Level");
                            int optInt3 = optJSONObject.optInt("IsSetAdmin");
                            int optInt4 = optJSONObject.optInt("PowerType");
                            try {
                                i2 = Integer.parseInt(optJSONObject.optString("FansLevel"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String optString4 = jSONObject2.optString("Title");
                            long optLong3 = jSONObject2.optLong("CreateTime");
                            RedPacket redPacket = new RedPacket();
                            redPacket.b(optLong);
                            redPacket.b(optInt);
                            redPacket.b(optString);
                            redPacket.c(optString2);
                            redPacket.d(optString3);
                            redPacket.d(optInt2);
                            redPacket.e(optInt3);
                            redPacket.c(optInt4);
                            redPacket.h(i2);
                            redPacket.c(optLong2);
                            redPacket.a(optString4);
                            redPacket.d(optLong3);
                            if (optInt4 == 1) {
                                try {
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("bid", optLong2);
                                    jSONObject3.put("rid", optLong);
                                    jSONObject3.put("name", optString2);
                                    jSONObject3.put(MessageKey.MSG_ICON, optString3);
                                    jSONObject3.put("bname", optString4);
                                    jSONObject3.put(SocialConstants.PARAM_SEND_MSG, optString);
                                    jSONObject3.put("nick", optString2);
                                    jSONObject3.put("ct", optLong3);
                                    jSONObject3.put("type", optInt);
                                    jSONObject3.put("yw", optInt3);
                                    jSONArray.put(jSONObject3);
                                } catch (Exception e2) {
                                }
                            }
                            arrayList.add(redPacket);
                            i = i2;
                            i2 = i;
                        }
                    } else if (optInt == 0) {
                        Object optString5 = optJSONObject.optString("Message");
                        String optString6 = optJSONObject.optString("HeadImg");
                        long optLong4 = optJSONObject.optLong("BookId");
                        str = jSONObject2.optString("Title");
                        long optLong5 = optJSONObject.optLong("HongBaoId");
                        int optInt5 = optJSONObject.optInt("IsSetAdmin");
                        if (!TextUtils.isEmpty(optString5)) {
                            RedPacketMessage redPacketMessage = new RedPacketMessage();
                            redPacketMessage.b(optString5);
                            redPacketMessage.a(optString6);
                            redPacketMessage.a(optLong4);
                            redPacketMessage.c(str);
                            redPacketMessage.b(optLong5);
                            redPacketMessage.a(optInt5);
                            arrayList2.add(redPacketMessage);
                        }
                    }
                }
                i = i2;
                i2 = i;
            }
        }
        if (jSONArray.length() > 0) {
            Object bL = d.bL(ReaderApplication.getApplicationImp().getApplicationContext());
            if (!TextUtils.isEmpty(bL)) {
                try {
                    JSONArray jSONArray2 = new JSONArray(bL);
                    for (i = 0; i < jSONArray2.length(); i++) {
                        jSONArray.put(jSONArray2.get(i));
                    }
                } catch (JSONException e3) {
                }
            }
            d.D(ReaderApplication.getApplicationImp().getApplicationContext(), jSONArray.toString());
        }
        if (arrayList.size() > 0) {
            Intent intent = new Intent();
            intent.setAction("com.qq.reader.redpacket_arrived");
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("redpacket", arrayList);
            intent.putExtras(bundle);
            android.support.v4.content.d.a(a()).a(intent);
            c.b("RedPacketAction", "new redpacket send --->");
        }
        if (arrayList2.size() > 0) {
            intent = new Intent();
            intent.setAction("com.qq.reader.redpacket_arrived");
            bundle = new Bundle();
            bundle.putParcelableArrayList("redpacketmsg", arrayList2);
            intent.putExtras(bundle);
            android.support.v4.content.d.a(a()).a(intent);
            c.b("RedPacketAction", "redpacket message send --->");
        }
    }
}
