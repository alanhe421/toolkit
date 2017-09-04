package com.qq.reader.qurl.a;

import android.app.Activity;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.qurl.d;
import com.tencent.android.tpush.common.Constants;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfWebPage */
public class u extends d {
    private final String a = "userlike";
    private final String b = "game";
    private final String c = "fullscreen";

    public u(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        String c = c();
        if (!TextUtils.isEmpty(c)) {
            if (d == null) {
                c(c);
            } else if (!c.startsWith("http://")) {
            } else {
                if ("userlike".equalsIgnoreCase(d)) {
                    c(d(c));
                } else if ("game".equalsIgnoreCase(d)) {
                    b(c);
                } else if ("fullscreen".equalsIgnoreCase(d)) {
                    e(c);
                }
            }
        }
    }

    private void e(String str) {
        o.i(b(), str, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH).a((int) Constants.ERRORCODE_UNKNOWN));
    }

    public void b(String str) {
        o.a(b(), str, false, new JumpActivityParameter().a(40000));
    }

    public void c(String str) {
        o.h(b(), str, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH).a((int) Constants.ERRORCODE_UNKNOWN));
    }

    public String d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        if (str.contains("?")) {
            stringBuilder.append("&");
        } else {
            stringBuilder.append("?");
        }
        stringBuilder.append("sex=");
        stringBuilder.append(a.d.aU(ReaderApplication.getApplicationImp()));
        return stringBuilder.toString();
    }
}
