package com.qq.reader.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.qq.reader.TypeContext;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.b.b;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static String a = "";
    private final int b = -1;
    private final int c = 0;
    private final int d = 1;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
    }

    public void onStart() {
        super.onStart();
        try {
            WXApiManager.getInstance(getApplicationContext()).getWXAPIInterface().handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onReq(BaseReq baseReq) {
        Intent intent;
        switch (baseReq.getType()) {
            case 3:
                Req req = (Req) baseReq;
                String str = req.transaction;
                if (a.equals(str)) {
                    intent = new Intent(this, MainActivity.class);
                    intent.setFlags(SigType.WLOGIN_QRPUSH);
                    startActivity(intent);
                    finish();
                } else {
                    a = str;
                    String str2 = req.username;
                    Intent intent2 = new Intent(this, WXShareBookActivity.class);
                    intent2.putExtra("WXTRANSACTION", baseReq.transaction);
                    intent2.putExtra("WXSENDTARGETNAME", str2);
                    startActivity(intent2);
                    finish();
                }
                j.a(73, 0);
                return;
            case 4:
                WXMediaMessage wXMediaMessage = ((ShowMessageFromWX.Req) baseReq).message;
                WXAppExtendObject wXAppExtendObject = (WXAppExtendObject) wXMediaMessage.mediaObject;
                Intent intent3 = new Intent(this, TypeContext.class);
                Bundle bundle = new Bundle();
                String str3 = wXAppExtendObject.extInfo;
                if (str3 != null && str3.length() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(str3);
                        str3 = jSONObject.getString("device");
                        int i = jSONObject.getInt("type");
                        if (str3.equals("android")) {
                            bundle.putString("filepath", wXAppExtendObject.filePath);
                        } else {
                            str3 = a.aR + "/" + jSONObject.getString("name");
                            ao.a(new File(wXAppExtendObject.filePath), new File(str3));
                            if (checkWXShareFromIOS(i) == 1) {
                                bundle.putString("filepath", str3);
                            } else {
                                intent = new Intent("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.addFlags(536870912);
                                intent.setDataAndType(Uri.fromFile(new File(str3)), "text/plain");
                                startActivity(intent);
                                finish();
                                return;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                bundle.putString("filename", wXMediaMessage.title);
                intent3.putExtras(bundle);
                startActivity(intent3);
                j.a(74, 0);
                finish();
                return;
            default:
                return;
        }
    }

    public int checkWXShareFromIOS(int i) {
        if (i > WXShareTypeEnum.EBookWeixin_1.ordinal()) {
            return -1;
        }
        if (i == WXShareTypeEnum.EBookTXT.ordinal() || i == WXShareTypeEnum.EBookTXT.ordinal() || i == WXShareTypeEnum.EBookEPUB.ordinal() || i == WXShareTypeEnum.EBookUMD.ordinal() || i == WXShareTypeEnum.EBookCHM.ordinal() || i == WXShareTypeEnum.EBookZip.ordinal() || i == WXShareTypeEnum.EBookPDF.ordinal() || i == WXShareTypeEnum.EBookDOC.ordinal() || i == WXShareTypeEnum.EBookDOCX.ordinal() || i == WXShareTypeEnum.EBookPPT.ordinal() || i == WXShareTypeEnum.EBookPPTX.ordinal() || i == WXShareTypeEnum.EBookXLS.ordinal() || i == WXShareTypeEnum.EBookXLSX.ordinal()) {
            return 1;
        }
        return 0;
    }

    public void onResp(BaseResp baseResp) {
        f.a("errCode", baseResp.errCode + "");
        Intent intent = new Intent();
        if (baseResp.getType() == 1) {
            switch (baseResp.errCode) {
                case -4:
                    Map hashMap = new HashMap();
                    hashMap.put("get_code", "failed");
                    hashMap.put("do_login", "installed");
                    intent.setAction("com.qq.reader.wxlogin.code");
                    intent.putExtra("type", 2);
                    intent.putExtra("status", "error");
                    sendBroadcast(intent);
                    break;
                case -2:
                    intent.setAction("com.qq.reader.wxlogin.code");
                    intent.putExtra("type", 2);
                    intent.putExtra("status", "cancel");
                    sendBroadcast(intent);
                    break;
                case 0:
                    Resp resp = (Resp) baseResp;
                    intent.setAction("com.qq.reader.wxlogin.code");
                    intent.putExtra("status", "success");
                    intent.putExtra("code", resp.code);
                    intent.putExtra("type", 2);
                    sendBroadcast(intent);
                    finish();
                    break;
            }
        } else if (baseResp.getType() == 2) {
            b.a(this, baseResp.transaction, baseResp.errCode);
        }
        finish();
    }
}
