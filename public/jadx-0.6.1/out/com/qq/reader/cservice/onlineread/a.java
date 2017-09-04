package com.qq.reader.cservice.onlineread;

import android.content.Context;
import com.qq.reader.common.conn.http.HttpErrorException;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.r;
import com.tencent.android.tpush.common.Constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* compiled from: OnlineFailReason */
public class a {
    public static String a(Context context, Throwable th, int i) {
        String str = "";
        if (i == 1002) {
            return "存储卡已满，建议清理存储卡";
        }
        if (i == com.tencent.qalsdk.base.a.d) {
            return "存储卡不可用，无法阅读";
        }
        if (!ao.d(context)) {
            return "网络不好，请检查网络设置";
        }
        if (th instanceof HttpErrorException) {
            return "服务异常：" + ((HttpErrorException) th).getStateCode();
        }
        return "网络异常，请稍候再试";
    }

    public static int a(Throwable th) {
        int i = com.tencent.qalsdk.base.a.d;
        try {
            if (th instanceof SocketTimeoutException) {
                return 1001;
            }
            if (th instanceof HttpErrorException) {
                return ((HttpErrorException) th).getStateCode();
            }
            if (th instanceof SocketException) {
                if (th instanceof ConnectException) {
                    if (th.getMessage() == null || th.getMessage().indexOf("failed to connect to /10.0.0.172") == -1 || th.getMessage().indexOf("ECONNREFUSED") == -1) {
                        return Constants.ERRORCODE_UNKNOWN;
                    }
                    return 1005;
                } else if (th.getMessage() == null || th.getMessage().indexOf("No route") == -1) {
                    return 1012;
                } else {
                    return 1006;
                }
            } else if (th instanceof IOException) {
                String message = th.getMessage();
                if (message == null) {
                    i = Constants.ERRORCODE_UNKNOWN;
                } else if (r.a()) {
                    if (th.getMessage().indexOf("No space left") != -1) {
                        i = 1002;
                    } else if (message.indexOf("onlineUnZip failed unknown format") != -1) {
                        i = 1008;
                    } else if (message.indexOf("onlineUnZip failed") != -1 && message.indexOf("No such file") != -1) {
                        i = 1009;
                    } else if (th instanceof UnknownHostException) {
                        i = 1010;
                    } else if (!(th instanceof FileNotFoundException)) {
                        i = 1011;
                    }
                }
                return i;
            } else if (th instanceof IllegalArgumentException) {
                return 1007;
            } else {
                if (th instanceof SecurityException) {
                    return com.tencent.qalsdk.base.a.c;
                }
                return Constants.ERRORCODE_UNKNOWN;
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
            return Constants.ERRORCODE_UNKNOWN;
        }
    }
}
