package com.tencent.mobileqq.openpay.api;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.mobileqq.openpay.data.base.BaseApi;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;
import com.tencent.mobileqq.openpay.data.pay.PayResponse;
import com.tencent.open.utils.SystemUtils;
import oicq.wlogin_sdk.request.WtloginHelper;
import tencent.tls.platform.SigType;

final class a implements IOpenApi {
    private final String[] a = new String[]{OpenConstants.API_NAME_PAY};
    private Context mContext;

    a(Context context) {
        this.mContext = context;
    }

    private static int a(String str, String str2) {
        if (str == null) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length && i < split2.length) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return str.compareTo(str2);
            }
        }
        return split.length > i ? 1 : split2.length <= i ? 0 : -1;
    }

    private String a() {
        String str = null;
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
            if (!(packageInfo == null || TextUtils.isEmpty(packageInfo.versionName))) {
                str = packageInfo.versionName;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private boolean a(BaseApi baseApi) {
        Bundle bundle = new Bundle();
        baseApi.toBundle(bundle);
        try {
            Object packageName = this.mContext.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            bundle.putString("_mqqpay_payapi_packageName", packageName);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("mqqwallet://open_pay/"));
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtras(bundle);
            intent.addFlags(SigType.TLS).addFlags(WtloginHelper.SigType.WLOGIN_PT4Token);
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean b(BaseApi baseApi) {
        try {
            Object packageName = this.mContext.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            Bundle bundle = new Bundle();
            baseApi.toBundle(bundle);
            bundle.putString("_mqqpay_baseapi_pkgname", packageName);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("mqqwallet://open_pay/"));
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtras(bundle);
            intent.addFlags(SigType.TLS).addFlags(WtloginHelper.SigType.WLOGIN_PT4Token);
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean execApi(BaseApi baseApi) {
        if (baseApi == null || !baseApi.checkParams() || OpenConstants.API_NAME_PAY.compareTo(baseApi.getApiName()) != 0) {
            return false;
        }
        String a = a();
        return a != null ? a(a, SystemUtils.QQ_VERSION_NAME_5_3_0) >= 0 ? a(baseApi) : a(a, "4.7.2") >= 0 ? b(baseApi) : false : false;
    }

    public final boolean handleIntent(Intent intent, IOpenApiListener iOpenApiListener) {
        if (intent == null || iOpenApiListener == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra("com_tencent_mobileqq_open_pay");
        if (TextUtils.isEmpty(stringExtra) || stringExtra.compareTo("com.tencent.mobileqq.open.pay") != 0) {
            return false;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return false;
        }
        BaseResponse baseResponse = null;
        switch (extras.getInt("_mqqpay_baseapi_apimark", -1)) {
            case 1:
                baseResponse = new PayResponse();
                baseResponse.fromBundle(extras);
                break;
        }
        if (baseResponse == null || !baseResponse.checkParams()) {
            return false;
        }
        iOpenApiListener.onOpenResponse(baseResponse);
        return true;
    }

    public final boolean isMobileQQInstalled() {
        return a() != null;
    }

    public final boolean isMobileQQSupportApi(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr;
        int i = 0;
        while (true) {
            strArr = this.a;
            if (i <= 0 && str.compareTo(this.a[0]) != 0) {
                i++;
            }
        }
        strArr = this.a;
        if (i > 0) {
            return false;
        }
        String a = a();
        return a != null && a(a, "4.7.2") >= 0;
    }
}
