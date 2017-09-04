package com.qq.reader.liveshow.model.im.a.a;

import com.qq.reader.liveshow.model.im.message.SenderProfile;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Command */
public class a extends com.qq.reader.liveshow.model.im.a.a {
    private String a;
    private SenderProfile b = new SenderProfile();

    public String b() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public SenderProfile c() {
        return this.b;
    }

    public void a(SenderProfile senderProfile) {
        this.b = senderProfile;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("action = ").append(a()).append("   ").append("params = ").append(b());
        return stringBuilder.toString();
    }

    public String d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("userAction", a());
        jSONObject.put("actionParam", b());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(SenderProfile.KEY_UID, c().getId());
        jSONObject2.put(SenderProfile.KEY_NICKNAME, c().getNickName());
        jSONObject2.put(SenderProfile.KEY_HEADICION, c().getAvatar());
        jSONObject2.put(SenderProfile.KEY_AUTHORID, c().getAuthorId());
        jSONObject2.put(SenderProfile.KEY_USERLEVEL, c().getPermissionsLevel());
        jSONObject2.put(SenderProfile.KEY_VIPLEVEL, c().getVipLevel());
        jSONObject.put("userProfile", jSONObject2.toString());
        return jSONObject.toString();
    }
}
