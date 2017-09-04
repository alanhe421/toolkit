package com.yw.game.sdk.login.b;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.android.tpush.common.Constants;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.a.b;
import com.yw.game.sdk.login.a.c;
import com.yw.game.sdk.login.util.NetResult;
import com.yw.game.sdk.login.util.network.h;
import com.yw.game.sdk.login.util.network.i;
import com.yw.game.sdk.login.util.task.GetTokenTask;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: YWGameLoginWraper */
public class d {

    /* compiled from: YWGameLoginWraper */
    private static class a {
        private static final d a = new d();
    }

    public static d a() {
        return a.a;
    }

    public void a(final BaseGameLoginActivity baseGameLoginActivity, final String str, final int i, c cVar) {
        com.yw.game.sdk.login.util.c.a("login:gameId" + str + " platformid:" + i);
        baseGameLoginActivity.a("提示", "正在授权...");
        cVar.a(new b(this) {
            final /* synthetic */ d d;

            public void a(int i, JSONObject jSONObject) {
                com.yw.game.sdk.login.util.c.a("loginFinish:resultCode" + i + " loginResult:" + jSONObject);
                if (i != 0) {
                    com.yw.game.sdk.login.util.c.a("loginFinish:resultCode:" + i);
                    this.d.a(baseGameLoginActivity, null);
                } else if (jSONObject == null) {
                    com.yw.game.sdk.login.util.c.a("loginFinish:" + i + "loginResult==null:");
                    this.d.a(baseGameLoginActivity, null);
                } else {
                    try {
                        String string = jSONObject.getString("nickName");
                        int i2 = jSONObject.getInt("loginType");
                        Object string2;
                        Object string3;
                        switch (i2) {
                            case 1:
                                int i3 = jSONObject.getInt("keyType");
                                Object string4 = jSONObject.getString("uin");
                                string2 = jSONObject.getString("skey");
                                if (com.yw.game.sdk.login.util.c.a) {
                                    com.yw.game.sdk.login.util.c.a("QQ_LOGIN_TYPE keyType:" + i3 + "uid:" + string4 + "skey:" + string2);
                                }
                                if (TextUtils.isEmpty(string4) || TextUtils.isEmpty(string2)) {
                                    this.d.a(baseGameLoginActivity, null);
                                    return;
                                } else {
                                    e.a().a(baseGameLoginActivity, str, string4, string2, i3, i, string);
                                    return;
                                }
                            case 2:
                                string3 = jSONObject.getString("openId");
                                string2 = jSONObject.getString("accessToken");
                                if (com.yw.game.sdk.login.util.c.a) {
                                    com.yw.game.sdk.login.util.c.a("WEIXIN_LOGIN_TYPEopenId:" + string3 + "accessToken:" + string2);
                                }
                                if (TextUtils.isEmpty(string3) || TextUtils.isEmpty(string2)) {
                                    this.d.a(baseGameLoginActivity, null);
                                    return;
                                } else {
                                    e.a().a(baseGameLoginActivity, str, string2, string3, i, string);
                                    return;
                                }
                            case 3:
                                string2 = jSONObject.getString("ywGuid");
                                string3 = jSONObject.getString("ywKey");
                                if (com.yw.game.sdk.login.util.c.a) {
                                    com.yw.game.sdk.login.util.c.a("QD_LOGIN_TYPE3ywGuid:" + string2 + "ywKey:" + string3);
                                }
                                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                                    this.d.a(baseGameLoginActivity, null);
                                    return;
                                } else {
                                    d.a().a(baseGameLoginActivity, str, i2, string2, string3, i, string);
                                    return;
                                }
                            default:
                                if (com.yw.game.sdk.login.util.c.a) {
                                    com.yw.game.sdk.login.util.c.a("loginResult中未设置loginType,或loginType不能识别");
                                }
                                this.d.a(baseGameLoginActivity, null);
                                return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        com.yw.game.sdk.login.util.c.a("loginResult json解析异常" + e.toString());
                        this.d.a(baseGameLoginActivity, null);
                    }
                    e.printStackTrace();
                    com.yw.game.sdk.login.util.c.a("loginResult json解析异常" + e.toString());
                    this.d.a(baseGameLoginActivity, null);
                }
            }
        });
    }

    void a(BaseGameLoginActivity baseGameLoginActivity, String str, int i, String str2, String str3, int i2, String str4) {
        if (com.yw.game.sdk.login.util.c.a) {
            com.yw.game.sdk.login.util.c.a("统一登录SDK结果：ywguid:" + str2 + " ywkey：" + str3);
        }
        com.yw.game.sdk.login.util.c.a("尝试对登录结果编码签名");
        final BaseGameLoginActivity baseGameLoginActivity2 = baseGameLoginActivity;
        final String str5 = str3;
        final int i3 = i;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str4;
        i.a().submit(new GetTokenTask(baseGameLoginActivity, str2, str3, i2, i, str, com.yw.game.sdk.login.util.c.a(), new h(this) {
            final /* synthetic */ d g;

            public void a(NetResult netResult) {
                JSONObject dateString = netResult.getDateString();
                if (dateString == null) {
                    this.g.a(baseGameLoginActivity2, null);
                    return;
                }
                String optString = dateString.optString("accessToken");
                Object optString2 = dateString.optString("visitorNickName");
                String encodeToString = Base64.encodeToString(str5.getBytes(), 0);
                if (com.yw.game.sdk.login.util.c.a) {
                    com.yw.game.sdk.login.util.c.a("encode:" + encodeToString);
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("loginType", i3);
                    jSONObject.put("gameId", str6);
                    jSONObject.put(Constants.FLAG_TOKEN, str7);
                    jSONObject.put("keySign", encodeToString);
                    jSONObject.put(Constants.FLAG_TOKEN, optString);
                    optString = "nickName";
                    if (!TextUtils.isEmpty(str8)) {
                        optString2 = str8;
                    }
                    jSONObject.put(optString, optString2);
                    if (com.yw.game.sdk.login.util.c.a) {
                        com.yw.game.sdk.login.util.c.a("签名成功" + jSONObject.toString());
                    }
                    this.g.a(baseGameLoginActivity2, jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.g.a(baseGameLoginActivity2, null);
                }
            }

            public void a(int i, String str) {
                this.g.a(baseGameLoginActivity2, null);
            }

            public void a(Exception exception) {
                this.g.a(baseGameLoginActivity2, null);
            }
        }));
    }

    public void a(BaseGameLoginActivity baseGameLoginActivity, JSONObject jSONObject) {
        if (baseGameLoginActivity != null) {
            baseGameLoginActivity.f();
            Intent intent = new Intent();
            if (jSONObject == null) {
                com.yw.game.sdk.login.util.c.a("登录失败，取消动作");
                baseGameLoginActivity.setResult(0, intent);
            } else {
                if (com.yw.game.sdk.login.util.c.a) {
                    com.yw.game.sdk.login.util.c.a("尝试将结果回调到原APP" + jSONObject.toString());
                }
                intent.putExtra("data", jSONObject.toString());
                baseGameLoginActivity.setResult(-1, intent);
            }
            a.a = false;
            baseGameLoginActivity.finish();
        }
    }
}
