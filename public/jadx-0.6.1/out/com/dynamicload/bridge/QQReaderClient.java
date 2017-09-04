package com.dynamicload.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NewLoginActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.b;
import com.qq.reader.common.login.c;
import com.qq.reader.common.web.js.JSSns;
import com.qq.reader.cservice.b.a;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.view.aj;
import java.io.Serializable;
import tencent.tls.platform.SigType;

public class QQReaderClient {
    private static QQReaderClient mInstance;

    public static final class HostSetting {
        public static boolean isNightTheme(Context context) {
            return d.P(context.getApplicationContext());
        }

        public static void changeNightTheme(Context context) {
            d.i(context.getApplicationContext(), !d.P(context.getApplicationContext()));
        }

        public static int getOritationType(Context context) {
            return d.Z(context.getApplicationContext());
        }

        public static void setOritationType(Context context, int i) {
            d.m(context.getApplicationContext(), i);
        }

        public static boolean getVolumeKeyTurnPage(Context context) {
            return d.am(context.getApplicationContext());
        }

        public static void setVolumeKeyTurnPage(Context context, boolean z) {
            d.o(context.getApplicationContext(), z);
        }

        public static boolean getPressLeftTurnPage(Context context) {
            return d.an(context.getApplicationContext());
        }

        public static void setPressLeftTurnPage(Context context, boolean z) {
            d.p(context.getApplicationContext(), z);
        }

        public static boolean getReadFullScreen(Context context) {
            return d.ao(context.getApplicationContext());
        }

        public static void setReadFullScreen(Context context, boolean z) {
            d.q(context.getApplicationContext(), z);
        }

        public static int getNightModeBrightness(Context context) {
            return d.ab(context.getApplicationContext());
        }

        public static void setNightModeBrightness(Context context, int i) {
            d.n(context.getApplicationContext(), i);
        }

        public static int getDayModeBrightness(Context context) {
            return d.ac(context.getApplicationContext());
        }

        public static void setDayModeBrightness(Context context, int i) {
            d.o(context.getApplicationContext(), i);
        }

        public static boolean getReadShowNavigation(Context context) {
            return d.aq(context.getApplicationContext());
        }

        public static void setReadShowNavigation(Context context, boolean z) {
            d.s(context.getApplicationContext(), z);
        }

        public static int getScreenProtectTime(Context context) {
            return d.ai(context.getApplicationContext());
        }

        public static void setScreenProtectTime(Context context, int i) {
            d.r(context.getApplicationContext(), i);
        }

        public static boolean iSFollowSysBrightness(Context context) {
            return d.aa(context.getApplicationContext());
        }

        public static void setFollowSysBrightness(Context context, boolean z) {
            d.k(context.getApplicationContext(), z);
        }
    }

    public static synchronized QQReaderClient getInstance() {
        QQReaderClient qQReaderClient;
        synchronized (QQReaderClient.class) {
            if (mInstance == null) {
                mInstance = new QQReaderClient();
            }
            qQReaderClient = mInstance;
        }
        return qQReaderClient;
    }

    public String getLoginkey(Context context) {
        return c.c().a(context);
    }

    public String getUin(Context context) {
        return c.c().c();
    }

    public void triggerLogin(final QLoginCallback qLoginCallback) {
        Intent intent = new Intent();
        intent.setClass(ReaderApplication.getApplicationImp(), NewLoginActivity.class);
        intent.addFlags(SigType.TLS);
        NewLoginActivity.a(new b(this) {
            final /* synthetic */ QQReaderClient b;

            public void a(int i, String str) {
                if (i == 1) {
                    if (qLoginCallback != null) {
                        qLoginCallback.loginSuccess(str);
                    }
                } else if (i == 2 && qLoginCallback != null) {
                    qLoginCallback.loginFailed();
                }
            }
        });
        ReaderApplication.getApplicationImp().startActivity(intent);
    }

    public void shareBook(Activity activity, String str, String str2, String str3) {
        Object obj = 1;
        if (!TextUtils.isEmpty(str3)) {
            obj = null;
        }
        if (obj != null) {
            a.a(activity, str);
        } else {
            new aj(activity).a(str3, str);
        }
    }

    public void shareWebPage(Activity activity, String str, String str2, String str3, String str4) {
        new JSSns(activity).sharePage(str4, str3, str, str2, "");
    }

    public String getRootPath() {
        return com.qq.reader.common.c.a.l;
    }

    public void addToBookShelf(String str, String str2, long j, long j2) {
        Serializable localMark = new LocalMark(str, str2, j, 1, true);
        localMark.setStartPoint(j2);
        if (localMark != null) {
            Intent intent = new Intent();
            intent.setAction(com.qq.reader.common.c.a.cD);
            intent.putExtra("mark", localMark);
            ReaderApplication.getApplicationImp().sendBroadcast(intent);
        }
    }
}
