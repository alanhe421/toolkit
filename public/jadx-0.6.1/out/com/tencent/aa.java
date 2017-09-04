package com.tencent;

import android.os.Handler;
import android.os.Looper;
import com.tencent.imcore.EnvRequestClosure;
import com.tencent.imcore.HttpMethod;
import com.tencent.imcore.IEnv;
import com.tencent.imcore.RunClosure;
import com.tencent.imcore.ThreadEntry;
import com.tencent.imcore.UploadLogFileOpt;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import java.net.HttpURLConnection;
import java.net.URL;

final class aa extends IEnv {
    private static String a = "imcore_jni";
    private static aa c = new aa();
    private Handler b = new Handler(Looper.getMainLooper());

    private aa() {
    }

    public static aa a() {
        return c;
    }

    public final boolean a(Runnable runnable) {
        return this.b.post(runnable);
    }

    public final boolean createThread(ThreadEntry threadEntry) {
        new Thread(new ad(this, threadEntry)).start();
        return true;
    }

    public final boolean httpRequest(String str, HttpMethod httpMethod, String str2, byte[] bArr, EnvRequestClosure envRequestClosure) {
        try {
            new Thread(new ab(this, httpMethod, (HttpURLConnection) new URL(str2).openConnection(), bArr, envRequestClosure)).start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean runOnMainThread(RunClosure runClosure) {
        return this.b.post(new ae(this, runClosure));
    }

    public final boolean sSORequest(String str, String str2, byte[] bArr, EnvRequestClosure envRequestClosure) {
        return sSORequest(str, str2, bArr, envRequestClosure, IMMsfCoreProxy.get().getReqTimeout());
    }

    public final boolean sSORequest(String str, String str2, byte[] bArr, EnvRequestClosure envRequestClosure, long j) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(str);
        if (msfUserInfo == null) {
            QLog.e(a, 1, "sSORequest no user found: " + str);
            msfUserInfo = new IMMsfUserInfo();
        }
        QLog.d(a, 1, "request user: " + str + "|cmd:" + str2);
        if (str2.equals("IMBDH.GetKeyAndIp") || r0.isLoggedIn()) {
            TIMValueCallBack tIMValueCallBack = null;
            if (envRequestClosure != null) {
                tIMValueCallBack = new ac(this, envRequestClosure, str);
            }
            IMMsfCoreProxy.get().request(str, str2, bArr, tIMValueCallBack, j);
            return envRequestClosure != null;
        } else if (envRequestClosure == null) {
            return false;
        } else {
            try {
                envRequestClosure.fail(6014, "current user not login. id: " + str);
            } catch (Throwable th) {
                String exceptionInfo = IMFunc.getExceptionInfo(th);
                QLog.e(a, 1, exceptionInfo);
                TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
                if (exceptionListener != null) {
                    exceptionListener.onException(exceptionInfo);
                }
            }
            return true;
        }
    }

    public final boolean uploadLogFile(String str, UploadLogFileOpt uploadLogFileOpt) {
        return IMMsfCoreProxy.get().uploadLogFile(str, IMCoreUploadLogFileOpt.convertFrom(uploadLogFileOpt));
    }
}
