package com.tencent.mm.sdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.mm.sdk.a.a.a;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.mm.sdk.modelbiz.CreateChatroom;
import com.tencent.mm.sdk.modelbiz.JoinChatroom;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Req;
import com.tencent.mm.sdk.modelmsg.LaunchFromWX;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.sdk.modelpay.PayResp;

final class WXApiImplV10 implements IWXAPI {
    private static final String TAG = "MicroMsg.SDK.WXApiImplV10";
    private static String wxappPayEntryClassname = null;
    private String appId;
    private boolean checkSignature = false;
    private Context context;
    private boolean detached = false;

    WXApiImplV10(Context context, String str, boolean z) {
        b.c(TAG, "<init>, appId = " + str + ", checkSignature = " + z);
        this.context = context;
        this.appId = str;
        this.checkSignature = z;
    }

    private boolean checkSumConsistent(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            b.a(TAG, "checkSumConsistent fail, invalid arguments");
            return false;
        } else if (bArr.length != bArr2.length) {
            b.a(TAG, "checkSumConsistent fail, length is different");
            return false;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean createChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_create_chatroom_group_id"), bundle.getString("_wxapi_create_chatroom_chatroom_name"), bundle.getString("_wxapi_create_chatroom_chatroom_nickname"), bundle.getString("_wxapi_create_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean joinChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_join_chatroom_group_id"), bundle.getString("_wxapi_join_chatroom_chatroom_nickname"), bundle.getString("_wxapi_join_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendAddCardToWX(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[]{this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizProfileReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), bundle.getInt("_wxapi_jump_to_biz_profile_req_scene"), bundle.getInt("_wxapi_jump_to_biz_profile_req_profile_type")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizTempSessionReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizTempSession"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_session_from"), bundle.getInt("_wxapi_jump_to_biz_webview_req_show_type")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizWebviewReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), bundle.getInt("_wxapi_jump_to_biz_webview_req_scene")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), null, null, new String[]{this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), null, null, new String[0], null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenWebview(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_webview_url")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayReq(Context context, Bundle bundle) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context).getString("_wxapp_pay_entry_classname_", null);
            b.c(TAG, "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                b.a(TAG, "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        a aVar = new a();
        aVar.e = bundle;
        aVar.b = "com.tencent.mm";
        aVar.c = wxappPayEntryClassname;
        return com.tencent.mm.sdk.a.a.a(context, aVar);
    }

    public final void detach() {
        b.c(TAG, "detach");
        this.detached = true;
        this.context = null;
    }

    public final int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        } else if (isWXAppInstalled()) {
            return new MMSharedPreferences(this.context).getInt("_build_info_sdk_int_", 0);
        } else {
            b.a(TAG, "open wx app failed, not installed or signature check failed");
            return 0;
        }
    }

    public final boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        if (!WXApiImplComm.isIntentFromWx(intent, Token.WX_TOKEN_VALUE_MSG)) {
            b.b(TAG, "handleIntent fail, intent not from weixin msg");
            return false;
        } else if (this.detached) {
            throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
        } else {
            String stringExtra = intent.getStringExtra(ConstantsAPI.CONTENT);
            int intExtra = intent.getIntExtra(ConstantsAPI.SDK_VERSION, 0);
            String stringExtra2 = intent.getStringExtra(ConstantsAPI.APP_PACKAGE);
            if (stringExtra2 == null || stringExtra2.length() == 0) {
                b.a(TAG, "invalid argument");
                return false;
            } else if (checkSumConsistent(intent.getByteArrayExtra(ConstantsAPI.CHECK_SUM), com.tencent.mm.sdk.a.a.b.a(stringExtra, intExtra, stringExtra2))) {
                int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
                switch (intExtra2) {
                    case 1:
                        iWXAPIEventHandler.onResp(new Resp(intent.getExtras()));
                        return true;
                    case 2:
                        iWXAPIEventHandler.onResp(new SendMessageToWX.Resp(intent.getExtras()));
                        return true;
                    case 3:
                        iWXAPIEventHandler.onReq(new Req(intent.getExtras()));
                        return true;
                    case 4:
                        iWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(intent.getExtras()));
                        return true;
                    case 5:
                        iWXAPIEventHandler.onResp(new PayResp(intent.getExtras()));
                        return true;
                    case 6:
                        iWXAPIEventHandler.onReq(new LaunchFromWX.Req(intent.getExtras()));
                        return true;
                    case 9:
                        iWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(intent.getExtras()));
                        return true;
                    case 14:
                        iWXAPIEventHandler.onResp(new CreateChatroom.Resp(intent.getExtras()));
                        return true;
                    case 15:
                        iWXAPIEventHandler.onResp(new JoinChatroom.Resp(intent.getExtras()));
                        return true;
                    default:
                        b.a(TAG, "unknown cmd = " + intExtra2);
                        return false;
                }
            } else {
                b.a(TAG, "checksum fail");
                return false;
            }
        }
    }

    public final boolean isWXAppInstalled() {
        boolean z = false;
        if (this.detached) {
            throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
            if (packageInfo != null) {
                z = WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
            }
        } catch (NameNotFoundException e) {
        }
        return z;
    }

    public final boolean isWXAppSupportAPI() {
        if (!this.detached) {
            return getWXAppSupportAPI() >= 570490883;
        } else {
            throw new IllegalStateException("isWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
    }

    public final boolean openWXApp() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        } else if (isWXAppInstalled()) {
            try {
                this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
                return true;
            } catch (Exception e) {
                b.a(TAG, "startActivity fail, exception = " + e.getMessage());
                return false;
            }
        } else {
            b.a(TAG, "open wx app failed, not installed or signature check failed");
            return false;
        }
    }

    public final boolean registerApp(String str) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        } else if (WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            b.c(TAG, "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            b.c(TAG, "register app " + this.context.getPackageName());
            com.tencent.mm.sdk.a.a.a.a aVar = new com.tencent.mm.sdk.a.a.a.a();
            aVar.f = "com.tencent.mm";
            aVar.g = ConstantsAPI.ACTION_HANDLE_APP_REGISTER;
            aVar.d = "weixin://registerapp?appid=" + this.appId;
            return com.tencent.mm.sdk.a.a.a.a(this.context, aVar);
        } else {
            b.a(TAG, "register app failed for wechat app signature check failed");
            return false;
        }
    }

    public final boolean sendReq(BaseReq baseReq) {
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            b.a(TAG, "sendReq failed for wechat app signature check failed");
            return false;
        } else if (baseReq.checkArgs()) {
            b.c(TAG, "sendReq, req type = " + baseReq.getType());
            Bundle bundle = new Bundle();
            baseReq.toBundle(bundle);
            if (baseReq.getType() == 5) {
                return sendPayReq(this.context, bundle);
            }
            if (baseReq.getType() == 7) {
                return sendJumpToBizProfileReq(this.context, bundle);
            }
            if (baseReq.getType() == 8) {
                return sendJumpToBizWebviewReq(this.context, bundle);
            }
            if (baseReq.getType() == 10) {
                return sendJumpToBizTempSessionReq(this.context, bundle);
            }
            if (baseReq.getType() == 9) {
                return sendAddCardToWX(this.context, bundle);
            }
            if (baseReq.getType() == 11) {
                return sendOpenRankListReq(this.context, bundle);
            }
            if (baseReq.getType() == 12) {
                return sendOpenWebview(this.context, bundle);
            }
            if (baseReq.getType() == 13) {
                return sendOpenBusiLuckyMoney(this.context, bundle);
            }
            if (baseReq.getType() == 14) {
                return createChatroom(this.context, bundle);
            }
            if (baseReq.getType() == 15) {
                return joinChatroom(this.context, bundle);
            }
            a aVar = new a();
            aVar.e = bundle;
            aVar.d = "weixin://sendreq?appid=" + this.appId;
            aVar.b = "com.tencent.mm";
            aVar.c = WXApp.WXAPP_MSG_ENTRY_CLASSNAME;
            return com.tencent.mm.sdk.a.a.a(this.context, aVar);
        } else {
            b.a(TAG, "sendReq checkArgs fail");
            return false;
        }
    }

    public final boolean sendResp(BaseResp baseResp) {
        if (this.detached) {
            throw new IllegalStateException("sendResp fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            b.a(TAG, "sendResp failed for wechat app signature check failed");
            return false;
        } else if (baseResp.checkArgs()) {
            Bundle bundle = new Bundle();
            baseResp.toBundle(bundle);
            a aVar = new a();
            aVar.e = bundle;
            aVar.d = "weixin://sendresp?appid=" + this.appId;
            aVar.b = "com.tencent.mm";
            aVar.c = WXApp.WXAPP_MSG_ENTRY_CLASSNAME;
            return com.tencent.mm.sdk.a.a.a(this.context, aVar);
        } else {
            b.a(TAG, "sendResp checkArgs fail");
            return false;
        }
    }

    public final void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        } else if (WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            b.c(TAG, "unregisterApp, appId = " + this.appId);
            if (this.appId == null || this.appId.length() == 0) {
                b.a(TAG, "unregisterApp fail, appId is empty");
                return;
            }
            b.c(TAG, "unregister app " + this.context.getPackageName());
            com.tencent.mm.sdk.a.a.a.a aVar = new com.tencent.mm.sdk.a.a.a.a();
            aVar.f = "com.tencent.mm";
            aVar.g = ConstantsAPI.ACTION_HANDLE_APP_UNREGISTER;
            aVar.d = "weixin://unregisterapp?appid=" + this.appId;
            com.tencent.mm.sdk.a.a.a.a(this.context, aVar);
        } else {
            b.a(TAG, "unregister app failed for wechat app signature check failed");
        }
    }
}
