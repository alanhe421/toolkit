package com.qq.reader.common.charge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.b.e;
import com.qq.reader.common.login.b.f;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.g;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.api.APMidasResponse;
import com.tencent.midas.api.IAPMidasPayCallBack;
import com.tencent.midas.api.request.APMidasBaseRequest;
import com.tencent.midas.api.request.APMidasGameRequest;
import com.tencent.midas.api.request.APMidasSubscribeRequest;

public class PayBridgeActivity extends Activity {
    public static String a;
    public static boolean b = false;
    private int c = -1;

    static {
        a = "release";
        if (b.a) {
            a = APMidasPayAPI.ENV_TEST;
        } else {
            a = "release";
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        if (getIntent().getExtras() == null) {
            finish();
            return;
        }
        this.c = getIntent().getExtras().getInt("pay_requestcode", -1);
        if (this.c == 2) {
            a(getIntent().getExtras());
        } else if (this.c == 3) {
            b(getIntent().getExtras());
        } else {
            finish();
            return;
        }
        b = true;
    }

    protected void onStart() {
        b = true;
        super.onStart();
    }

    public static boolean a(Activity activity, Bundle bundle, int i) {
        if (activity == null || bundle == null) {
            return false;
        }
        Intent intent = new Intent(activity, PayBridgeActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
        return true;
    }

    private void a(Bundle bundle) {
        String a;
        String c;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String string = bundle.getString("offerid");
        String h = ao.h((Context) this);
        a c2 = c.c();
        if (c.b()) {
            a = c2.a(getApplicationContext());
            Object obj = 1;
            if (a == null || a.trim().length() == 0) {
                a = c2.a(getApplicationContext());
                obj = null;
            }
            c = c2.c();
            String str6;
            switch (c2.d()) {
                case 1:
                    str = "uin";
                    str2 = obj != null ? "skey" : "sid";
                    str3 = c;
                    str4 = string;
                    str6 = str;
                    str = a;
                    a = str6;
                    String str7 = "desktop_m_qq-" + h + "-android-areader";
                    str5 = str2;
                    str2 = str7;
                    break;
                case 2:
                    str3 = ((f) c2).b(getApplicationContext());
                    str5 = "wc_actoken";
                    str2 = "wechat-" + h + "-android-areader";
                    str4 = string;
                    str6 = a;
                    a = "hy_gameid";
                    str = str6;
                    break;
                case 10:
                    str4 = "";
                    str5 = "";
                    str = a;
                    a = "";
                    str3 = c2.b(getApplicationContext());
                    str2 = "";
                    break;
                case 50:
                    str3 = ((e) c2).b(getApplicationContext());
                    str5 = "st_dummy";
                    str2 = "desktop_m_qq-" + h + "-android-areader";
                    str4 = string;
                    str6 = a;
                    a = "hy_gameid";
                    str = str6;
                    break;
                default:
                    str2 = null;
                    str5 = null;
                    str = a;
                    str3 = c;
                    a = null;
                    str4 = string;
                    break;
            }
        }
        str2 = null;
        str = null;
        a = null;
        str4 = string;
        str5 = null;
        str3 = null;
        c = "1";
        string = "pfKey";
        h = "common";
        String string2 = bundle.getString("saveValue");
        boolean z = bundle.getBoolean("isCanChange", true);
        String string3 = bundle.getString("paychannel");
        Object obj2 = null;
        if (!TextUtils.isEmpty(string3)) {
            if ("wechat".equals(string3.toLowerCase())) {
                obj2 = "wechat";
            } else if ("bank".equals(string3.toLowerCase())) {
                obj2 = "bank";
            }
        }
        APMidasPayAPI.setEnv(a);
        APMidasPayAPI.setLogEnable(true);
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        aPMidasGameRequest.offerId = str4;
        aPMidasGameRequest.openId = str3;
        aPMidasGameRequest.openKey = str;
        aPMidasGameRequest.sessionId = a;
        aPMidasGameRequest.sessionType = str5;
        aPMidasGameRequest.zoneId = "1";
        aPMidasGameRequest.pf = str2;
        aPMidasGameRequest.pfKey = string;
        aPMidasGameRequest.acctType = h;
        aPMidasGameRequest.saveValue = string2;
        aPMidasGameRequest.isCanChange = z;
        aPMidasGameRequest.resId = R.drawable.qr_icon_gold;
        if (!TextUtils.isEmpty(obj2)) {
            aPMidasGameRequest.setPayChannel(obj2);
        }
        APMidasPayAPI.init(this, aPMidasGameRequest);
        APMidasPayAPI.launchPay(this, aPMidasGameRequest, new IAPMidasPayCallBack(this) {
            final /* synthetic */ PayBridgeActivity a;

            {
                this.a = r1;
            }

            public void MidasPayCallBack(APMidasResponse aPMidasResponse) {
                int i;
                int i2;
                int i3;
                String str;
                int i4 = -1;
                int i5 = 0;
                String str2 = "";
                if (aPMidasResponse != null) {
                    i5 = aPMidasResponse.realSaveNum;
                    i = aPMidasResponse.payChannel;
                    i2 = aPMidasResponse.payState;
                    i4 = aPMidasResponse.provideState;
                    i3 = aPMidasResponse.resultCode;
                    str = aPMidasResponse.resultMsg;
                    if (i3 == 0 && i2 == 0) {
                        g.a(this.a.getApplicationContext(), i5);
                    }
                } else {
                    str = "payResp null";
                    i2 = -1;
                    i = -1;
                    i3 = -1;
                }
                this.a.a(i3, i5, i, i2, i4, str);
            }

            public void MidasPayNeedLogin() {
                this.a.a(5, 0, -1, -1, -1, "pay need login");
            }
        });
    }

    public static String a(Intent intent) {
        String str = "充值失败";
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                str = extras.getString("message");
                if (str != null && str.trim().length() > 0) {
                    str = "充值失败[" + str + "]";
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("midas charge or month result=" + extras.getInt("resultCode"));
                stringBuilder.append(",realSaveNum=" + extras.getInt("realSaveNum"));
                stringBuilder.append(",payChannel=" + extras.getInt("payChannel"));
                stringBuilder.append(",payState=" + extras.getInt("payState"));
                stringBuilder.append(",provideState=" + extras.getInt("provideState"));
                stringBuilder.append(",message=" + extras.getString("message"));
                j.b(stringBuilder.toString());
            }
        }
        return str;
    }

    private void a(int i, int i2, int i3, int i4, int i5, String str) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("result", i);
        bundle.putInt("realSaveNum", i2);
        bundle.putInt("payChannel", i3);
        bundle.putInt("payState", i4);
        bundle.putInt("provideState", i5);
        bundle.putString("message", str);
        intent.putExtras(bundle);
        setResult(i, intent);
        finish();
    }

    public static boolean b(Activity activity, Bundle bundle, int i) {
        if (activity == null || bundle == null) {
            return false;
        }
        Intent intent = new Intent(activity, PayBridgeActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
        return true;
    }

    private void b(Bundle bundle) {
        String a;
        String str;
        String str2;
        String str3;
        String str4;
        String string = bundle.getString("offerid");
        String h = ao.h((Context) this);
        a c = c.c();
        if (c.b()) {
            a = c.a(getApplicationContext());
            Object obj = 1;
            if (a == null || a.trim().length() == 0) {
                a = c.a(getApplicationContext());
                obj = null;
            }
            String c2 = c.c();
            String str5;
            switch (c.d()) {
                case 1:
                    str = "uin";
                    str2 = c2;
                    str5 = obj != null ? "skey" : "sid";
                    str3 = "desktop_m_qq-" + h + "-android-areader";
                    str4 = str;
                    str = a;
                    a = str5;
                    break;
                case 2:
                    str2 = c.b(getApplicationContext());
                    str3 = "wechat-" + h + "-android-areader";
                    str4 = "hy_gameid";
                    str = a;
                    a = "wc_actoken";
                    break;
                case 50:
                    str2 = c.b(getApplicationContext());
                    str3 = "desktop_m_qq-" + h + "-android-areader";
                    str4 = "hy_gameid";
                    str = a;
                    a = "st_dummy";
                    break;
                default:
                    str3 = null;
                    str4 = null;
                    str2 = c2;
                    str5 = a;
                    a = null;
                    str = str5;
                    break;
            }
        }
        str4 = null;
        str2 = null;
        str3 = null;
        a = null;
        str = null;
        h = bundle.getString("saveValue");
        boolean z = bundle.getBoolean("isCanChange", true);
        boolean z2 = bundle.getBoolean("autoPay", true);
        String string2 = bundle.getString("servicecode");
        String string3 = bundle.getString("productid");
        APMidasPayAPI.setEnv(a);
        APMidasPayAPI.setLogEnable(true);
        APMidasBaseRequest aPMidasSubscribeRequest = new APMidasSubscribeRequest();
        aPMidasSubscribeRequest.offerId = string;
        aPMidasSubscribeRequest.openId = str2;
        aPMidasSubscribeRequest.openKey = str;
        aPMidasSubscribeRequest.sessionId = str4;
        aPMidasSubscribeRequest.sessionType = a;
        aPMidasSubscribeRequest.zoneId = "1";
        aPMidasSubscribeRequest.autoPay = z2;
        aPMidasSubscribeRequest.pf = str3;
        aPMidasSubscribeRequest.pfKey = "pfKey";
        aPMidasSubscribeRequest.acctType = "common";
        aPMidasSubscribeRequest.resId = R.drawable.qr_icon_openmonth_;
        aPMidasSubscribeRequest.serviceCode = string2;
        aPMidasSubscribeRequest.productId = string3;
        aPMidasSubscribeRequest.serviceName = getString(R.string.literature_brand) + getString(R.string.openmonth);
        aPMidasSubscribeRequest.saveValue = h;
        aPMidasSubscribeRequest.isCanChange = z;
        APMidasPayAPI.init(this, aPMidasSubscribeRequest);
        APMidasPayAPI.launchPay(this, aPMidasSubscribeRequest, new IAPMidasPayCallBack(this) {
            final /* synthetic */ PayBridgeActivity a;

            {
                this.a = r1;
            }

            public void MidasPayCallBack(APMidasResponse aPMidasResponse) {
                int i;
                int i2;
                int i3;
                int i4 = -1;
                int i5 = 0;
                String str = "";
                if (aPMidasResponse != null) {
                    i5 = aPMidasResponse.realSaveNum;
                    i = aPMidasResponse.payChannel;
                    i2 = aPMidasResponse.payState;
                    i4 = aPMidasResponse.provideState;
                    i3 = aPMidasResponse.resultCode;
                } else {
                    str = "payResp null";
                    i2 = -1;
                    i = -1;
                    i3 = -1;
                }
                if (i3 == 0) {
                    c.c().a(this.a, true);
                }
                this.a.a(i3, i5, i, i2, i4, str);
            }

            public void MidasPayNeedLogin() {
                this.a.a(5, 0, -1, -1, -1, "pay need login");
            }
        });
    }

    protected void onStop() {
        b = false;
        super.onStop();
    }

    protected void onDestroy() {
        b = false;
        super.onDestroy();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return true;
    }
}
