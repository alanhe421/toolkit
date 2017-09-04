package com.pay.tool;

import android.text.TextUtils;
import com.pay.http.APPluginUrlConf;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;

public class APMidasTools {
    static long a = 0;

    public static String getCurrentThreadName(Thread thread) {
        return thread.getName();
    }

    public static int getErrorCodeFromException(IOException iOException) {
        return iOException instanceof CharConversionException ? -20 : iOException instanceof MalformedInputException ? -21 : iOException instanceof UnmappableCharacterException ? -22 : iOException instanceof HttpResponseException ? -23 : iOException instanceof ClosedChannelException ? -24 : iOException instanceof ConnectionClosedException ? -25 : iOException instanceof EOFException ? -26 : iOException instanceof FileLockInterruptionException ? -27 : iOException instanceof FileNotFoundException ? -28 : iOException instanceof HttpRetryException ? -29 : iOException instanceof ConnectTimeoutException ? -7 : iOException instanceof SocketTimeoutException ? -8 : iOException instanceof InvalidPropertiesFormatException ? -30 : iOException instanceof MalformedChunkCodingException ? -31 : iOException instanceof MalformedURLException ? -3 : iOException instanceof NoHttpResponseException ? -32 : iOException instanceof InvalidClassException ? -33 : iOException instanceof InvalidObjectException ? -34 : iOException instanceof NotActiveException ? -35 : iOException instanceof NotSerializableException ? -36 : iOException instanceof OptionalDataException ? -37 : iOException instanceof StreamCorruptedException ? -38 : iOException instanceof WriteAbortedException ? -39 : iOException instanceof ProtocolException ? -40 : iOException instanceof SSLHandshakeException ? -41 : iOException instanceof SSLKeyException ? -42 : iOException instanceof SSLPeerUnverifiedException ? -43 : iOException instanceof SSLProtocolException ? -44 : iOException instanceof BindException ? -45 : iOException instanceof ConnectException ? -46 : iOException instanceof NoRouteToHostException ? -47 : iOException instanceof PortUnreachableException ? -48 : iOException instanceof SyncFailedException ? -49 : iOException instanceof UTFDataFormatException ? -50 : iOException instanceof UnknownHostException ? -51 : iOException instanceof UnknownServiceException ? -52 : iOException instanceof UnsupportedEncodingException ? -53 : iOException instanceof ZipException ? -54 : -2;
    }

    public static String getSysServerDomain() {
        String str = APMidasPayAPI.env;
        return str.equals(APMidasPayAPI.ENV_DEV) ? APPluginUrlConf.UNIPAY_DEV_DOMAIN : str.equals(APMidasPayAPI.ENV_TEST) ? APPluginUrlConf.UNIPAY_SANDBOX_DOMAIN : "api.unipay.qq.com";
    }

    public static long getTimeInterval(long j, long j2) {
        return j2 - j;
    }

    public static String getUUID() {
        String str = "";
        try {
            str = UUID.randomUUID().toString();
        } catch (Exception e) {
        }
        return str;
    }

    public static String getUrlParamsValue(String str, String str2) {
        String[] split = str.split("[?]");
        if (split.length <= 1 || split[1] == null) {
            return null;
        }
        for (String split2 : split[1].split("[&]")) {
            String[] split3 = split2.split("[=]");
            String split22 = (split3.length <= 1 || split3[0] == null) ? null : split3[0];
            String str3 = (split3.length <= 1 || split3[1] == null) ? null : split3[1];
            if (split22 != null && split22.compareToIgnoreCase(str2) == 0) {
                return str3;
            }
        }
        return null;
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a < 3000) {
            return true;
        }
        a = currentTimeMillis;
        return false;
    }

    public static String map2UrlParams(HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Entry entry : hashMap.entrySet()) {
                stringBuffer.append((String) entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append((String) entry.getValue());
                stringBuffer.append("&");
            }
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    public static String urlDecode(String str, int i) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            APLog.w("", "解码内容为空");
        } else if (i <= 0) {
            return str;
        } else {
            int i2 = 0;
            while (i2 < i) {
                try {
                    str2 = URLDecoder.decode(str, "utf-8");
                } catch (Exception e) {
                    APLog.i("urlEncode", e.toString());
                }
                i2++;
                str = str2;
            }
        }
        return str2;
    }

    public static String urlEncode(String str, int i) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            APLog.i("urlEncode", "编码内容为空");
        } else if (i <= 0) {
            return str;
        } else {
            int i2 = 0;
            while (i2 < i) {
                try {
                    str2 = URLEncoder.encode(str, "utf-8");
                } catch (Exception e) {
                    APLog.i("urlEncode", e.toString());
                }
                i2++;
                str = str2;
            }
        }
        return str2;
    }
}
