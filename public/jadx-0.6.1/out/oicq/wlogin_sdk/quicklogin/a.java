package oicq.wlogin_sdk.quicklogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.connect.common.Constants;
import oicq.wlogin_sdk.request.WtloginHelper.QuickLoginParam;
import oicq.wlogin_sdk.tools.RSACrypt;
import oicq.wlogin_sdk.tools.util;

/* compiled from: QuickLogin */
public class a {
    private static int a(Context context, Activity activity, String str, long j, long j2, String str2) {
        byte[] bArr = util.get_rsa_pubkey(context);
        if (bArr == null || bArr.length == 0) {
            bArr = util.string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
        }
        Intent intent = new Intent();
        intent.setClassName(str, "com.tencent.open.agent.AgentActivity");
        Bundle bundle = new Bundle();
        bundle.putLong("dstSsoVer", 1);
        bundle.putLong("dstAppid", j);
        bundle.putLong("subDstAppid", j2);
        bundle.putByteArray("dstAppVer", str2.getBytes());
        bundle.putByteArray("publickey", bArr);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        intent.putExtra(Constants.KEY_ACTION, "action_quick_login");
        activity.startActivityForResult(intent, 1201);
        return 0;
    }

    private static int a(Activity activity, long j, long j2, QuickLoginParam quickLoginParam) {
        Intent intent;
        if (quickLoginParam == null || quickLoginParam.webViewActivityClassName == null || quickLoginParam.webViewActivityClassName.length() == 0) {
            intent = new Intent(activity, QuickLoginWebViewActivity.class);
        } else {
            intent = new Intent();
            intent.setClassName(activity, quickLoginParam.webViewActivityClassName);
        }
        intent.putExtra("appid", j);
        intent.putExtra("subappid", j2);
        if (!(quickLoginParam == null || quickLoginParam.userAccount == null || quickLoginParam.userAccount.length() == 0)) {
            intent.putExtra(com.tencent.android.tpush.common.Constants.FLAG_ACCOUNT, quickLoginParam.userAccount);
            intent.putExtra("isUserAccountLocked", quickLoginParam.isUserAccountLocked);
        }
        if (quickLoginParam != null) {
            intent.putExtra("forceWebLogin", quickLoginParam.forceWebLogin);
        }
        activity.startActivityForResult(intent, 1202);
        return 0;
    }

    public static int a(Context context, Activity activity, long j, long j2, String str, QuickLoginParam quickLoginParam) {
        if (quickLoginParam != null && true == quickLoginParam.forceWebLogin) {
            return a(activity, j, j2, quickLoginParam);
        }
        try {
            String str2 = "com.tencent.mobileqq";
            if (true == util.CheckMayFastLogin(context)) {
                return a(context, activity, str2, j, j2, str);
            }
            str2 = Constants.PACKAGE_QQ_PAD;
            if (true == util.CheckQQMiniHD(context)) {
                return a(context, activity, str2, j, j2, str);
            }
            return a(activity, j, j2, quickLoginParam);
        } catch (Exception e) {
            return a(activity, j, j2, quickLoginParam);
        }
    }
}
