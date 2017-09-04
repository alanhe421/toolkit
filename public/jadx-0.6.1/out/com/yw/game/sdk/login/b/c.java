package com.yw.game.sdk.login.b;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.yw.game.sdk.login.BaseGameLoginActivity;
import com.yw.game.sdk.login.util.NetResult;
import com.yw.game.sdk.login.util.network.h;
import com.yw.game.sdk.login.util.network.i;
import com.yw.game.sdk.login.util.task.GetPkgNameTask;
import com.yw.game.sdk.login.util.task.VerifyKeyStoreMd5Task;
import org.json.JSONObject;

/* compiled from: UriVerifier */
class c {

    /* compiled from: UriVerifier */
    private static class a {
        private static final c a = new c();
    }

    c() {
    }

    public static c a() {
        return a.a;
    }

    void a(BaseGameLoginActivity baseGameLoginActivity, int i, String str, com.yw.game.sdk.login.a.c cVar) {
        baseGameLoginActivity.e();
        final BaseGameLoginActivity baseGameLoginActivity2 = baseGameLoginActivity;
        final String str2 = str;
        final int i2 = i;
        final com.yw.game.sdk.login.a.c cVar2 = cVar;
        i.a().submit(new GetPkgNameTask(baseGameLoginActivity, str, com.yw.game.sdk.login.util.c.a(), new h(this) {
            final /* synthetic */ c e;

            public void a(NetResult netResult) {
                JSONObject dateString = netResult.getDateString();
                if (dateString == null) {
                    com.yw.game.sdk.login.util.c.a("verifyKeystoreMD5获取包名信息报错:data中数据为null");
                    d.a().a(baseGameLoginActivity2, null);
                    return;
                }
                com.yw.game.sdk.login.util.c.a("verifyKeystoreMD5获取包名信息成功");
                this.e.a(baseGameLoginActivity2, str2, i2, dateString.optString("packagename"), cVar2);
            }

            public void a(int i, String str) {
                com.yw.game.sdk.login.util.c.a(baseGameLoginActivity2, i + str);
                com.yw.game.sdk.login.util.c.a("verifyKeystoreMD5获取包名信息报错" + i + str);
                d.a().a(baseGameLoginActivity2, null);
            }

            public void a(Exception exception) {
                com.yw.game.sdk.login.util.c.a("verifyKeystoreMD5获取包名信息报错" + exception.toString());
                d.a().a(baseGameLoginActivity2, null);
            }
        }));
    }

    private void a(BaseGameLoginActivity baseGameLoginActivity, String str, int i, String str2, com.yw.game.sdk.login.a.c cVar) {
        if (TextUtils.isEmpty(str2)) {
            Toast.makeText(baseGameLoginActivity, "游戏来源不合法", 0).show();
            d.a().a(baseGameLoginActivity, null);
            return;
        }
        String a = com.yw.game.sdk.login.util.c.a();
        String b = com.yw.game.sdk.login.util.c.b((Context) baseGameLoginActivity, str2);
        if (com.yw.game.sdk.login.util.c.a) {
            String c = com.yw.game.sdk.login.util.c.c(a, b);
            com.yw.game.sdk.login.util.c.a("测试环境：time:" + a + " signatureDigest:" + b + " 签名结果：", c + "\n");
            com.yw.game.sdk.login.util.c.a("测试环境：这个签名结果用于和正在发出请求的参数进行对比" + c);
        }
        final BaseGameLoginActivity baseGameLoginActivity2 = baseGameLoginActivity;
        final String str3 = str;
        final int i2 = i;
        final com.yw.game.sdk.login.a.c cVar2 = cVar;
        i.a().submit(new VerifyKeyStoreMd5Task(baseGameLoginActivity, str, i, str2, b, a, new h(this) {
            final /* synthetic */ c e;

            public void a(NetResult netResult) {
                com.yw.game.sdk.login.util.c.a("VerifyKeyStoreMd5Task校验证书成功");
                baseGameLoginActivity2.f();
                d.a().a(baseGameLoginActivity2, str3, i2, cVar2);
            }

            public void a(int i, String str) {
                com.yw.game.sdk.login.util.c.a(baseGameLoginActivity2, i + str);
                com.yw.game.sdk.login.util.c.a("VerifyKeyStoreMd5Task校验证书失败" + i + str);
                d.a().a(baseGameLoginActivity2, null);
            }

            public void a(Exception exception) {
                com.yw.game.sdk.login.util.c.a(baseGameLoginActivity2, exception.toString());
                com.yw.game.sdk.login.util.c.a("VerifyKeyStoreMd5Task" + exception.toString());
                d.a().a(baseGameLoginActivity2, null);
            }
        }));
    }
}
