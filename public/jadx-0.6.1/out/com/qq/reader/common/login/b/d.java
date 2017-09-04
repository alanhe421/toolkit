package com.qq.reader.common.login.b;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.a.c;
import oicq.wlogin_sdk.request.Ticket;
import oicq.wlogin_sdk.request.WUserSigInfo;
import oicq.wlogin_sdk.request.WtloginHelper;

/* compiled from: QQUserInfo */
public class d extends a {
    private String q = "";

    public String b(Context context) {
        return c.a(ReaderApplication.getApplicationImp()).j();
    }

    public String a(Context context) {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        WUserSigInfo GetLocalSig = c.a(context).g().GetLocalSig(c(), 683031601);
        if (GetLocalSig != null) {
            Ticket GetUserSigInfoTicket = WtloginHelper.GetUserSigInfoTicket(GetLocalSig, 4096);
            if (GetUserSigInfoTicket != null) {
                this.q = new String(GetUserSigInfoTicket._sig);
                return this.q;
            }
        }
        return "";
    }
}
