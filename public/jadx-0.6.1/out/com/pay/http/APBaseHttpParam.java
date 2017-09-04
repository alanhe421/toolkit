package com.pay.http;

import com.pay.tool.APMidasTools;
import com.tencent.connect.common.Constants;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.http.conn.util.InetAddressUtils;

public class APBaseHttpParam {
    public static final int CONNECT_TIMEOUT = 15000;
    public static final int READ_TIMEOUT = 15000;
    public static final int TRY_TIMES = 2;
    public long begTime;
    public int connectTimeout;
    public String defaultDomain;
    public String domain = APMidasTools.getSysServerDomain();
    public long endTime;
    public String port;
    public int reTryTimes;
    public int readTimeout;
    public HashMap<String, String> reqParam = new HashMap();
    public String reqType;
    public int requestTimes;
    public String sendType;
    public String url;
    public String urlName;
    public String urlParams;

    public APBaseHttpParam() {
        this.reqType = "http://";
        this.sendType = Constants.HTTP_GET;
        this.defaultDomain = "";
        this.domain = "";
        this.port = "";
        this.urlName = "";
        this.urlParams = "";
        this.connectTimeout = 15000;
        this.readTimeout = 15000;
        this.requestTimes = 0;
        this.reTryTimes = 2;
        this.begTime = 0;
        this.endTime = 0;
        this.reqParam = new HashMap();
        this.domain = APMidasTools.getSysServerDomain();
    }

    public void constructParams() {
        StringBuilder stringBuilder = new StringBuilder("");
        if (this.reqParam != null) {
            for (Entry entry : this.reqParam.entrySet()) {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append((String) entry.getValue());
                stringBuilder.append("&");
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                this.urlParams = stringBuilder.toString();
            }
        }
        APLog.i("APBaseHttpReq", "urlParams=" + this.urlParams);
    }

    public void constructReTryUrl() {
        if (this.requestTimes < this.reTryTimes) {
            this.domain = this.defaultDomain;
            String str = "";
            if (isIPAddress(this.domain) && this.port.length() != 0) {
                str = ":" + this.port;
            }
            this.url = this.reqType + this.domain + str + this.urlName;
            this.requestTimes++;
        }
    }

    public void constructUrl() {
        constructParams();
        if (this.sendType.equals(Constants.HTTP_GET)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.url);
            if (!this.url.endsWith("?")) {
                stringBuffer.append("?");
            }
            stringBuffer.append(this.urlParams.toString());
            this.url = stringBuffer.toString();
        }
    }

    public boolean isIPAddress(String str) {
        return str != null && (InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str));
    }

    public void setReqWithHttp() {
        this.reqType = "http://";
    }

    public void setReqWithHttps() {
        this.reqType = "https://";
    }

    public void setSendWithGet() {
        this.sendType = Constants.HTTP_GET;
    }

    public void setSendWithPost() {
        this.sendType = Constants.HTTP_POST;
    }

    public void setUrl(String str, String str2, String str3, String str4) {
        String str5 = APMidasPayAPI.env;
        String str6 = "";
        if (isIPAddress(this.domain) && this.port.length() != 0) {
            str6 = ":" + this.port;
        }
        if (str5.equals(APMidasPayAPI.ENV_DEV)) {
            this.urlName = str2;
            this.defaultDomain = APPluginUrlConf.UNIPAY_DEV_DOMAIN;
            this.url = this.reqType + this.domain + str6 + str2;
        } else if (str5.equals(APMidasPayAPI.ENV_TEST)) {
            this.urlName = str3;
            this.defaultDomain = APPluginUrlConf.UNIPAY_SANDBOX_DOMAIN;
            this.url = this.reqType + this.domain + str6 + str3;
        } else if (str5.equals(APMidasPayAPI.ENV_TESTING)) {
            this.urlName = str3;
            this.defaultDomain = APPluginUrlConf.UNIPAY_SANDBOX_DOMAIN;
            this.url = this.reqType + this.domain + str6 + str3;
        } else if (str5.equals("release")) {
            this.urlName = str4;
            this.defaultDomain = "api.unipay.qq.com";
            this.url = this.reqType + this.domain + str6 + str4;
        }
    }

    public void setUrlNotMidas(String str, String str2, String str3, String str4) {
        String str5 = APMidasPayAPI.env;
        this.defaultDomain = "";
        if (str5.equals(APMidasPayAPI.ENV_TESTING)) {
            this.urlName = str2;
            this.url = str2;
        } else if (str5.equals(APMidasPayAPI.ENV_TEST)) {
            this.urlName = str3;
            this.url = str3;
        } else if (str5.equals("release")) {
            this.urlName = str4;
            this.url = str4;
        }
    }
}
