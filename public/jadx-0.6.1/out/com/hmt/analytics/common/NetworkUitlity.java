package com.hmt.analytics.common;

import com.hmt.analytics.objects.MyMessage;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkUitlity {
    public static MyMessage get(String str) {
        HttpsURLConnection ignoreSSL;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        MyMessage myMessage = new MyMessage();
        String str2 = "";
        CommonUtil.printLog("HMTAGENT==>url", str);
        try {
            int responseCode;
            String str3;
            BufferedReader bufferedReader;
            String str4;
            if (str.startsWith("https")) {
                ignoreSSL = ignoreSSL(str);
                ignoreSSL.setReadTimeout(15000);
                ignoreSSL.setConnectTimeout(15000);
                ignoreSSL.connect();
                responseCode = ignoreSSL.getResponseCode();
                bufferedReader = new BufferedReader(new InputStreamReader(ignoreSSL.getInputStream()));
                str4 = "";
                while (true) {
                    str4 = bufferedReader.readLine();
                    if (str4 == null) {
                        break;
                    }
                    str2 = new StringBuilder(String.valueOf(str2)).append(str4).toString();
                }
                if (ignoreSSL != null) {
                    ignoreSSL.disconnect();
                    str3 = str2;
                } else {
                    str3 = str2;
                }
            } else {
                try {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                    try {
                        httpURLConnection2.setReadTimeout(15000);
                        httpURLConnection2.setConnectTimeout(15000);
                        httpURLConnection2.connect();
                        int responseCode2 = httpURLConnection2.getResponseCode();
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                        str4 = "";
                        while (true) {
                            str4 = bufferedReader.readLine();
                            if (str4 == null) {
                                break;
                            }
                            str2 = new StringBuilder(String.valueOf(str2)).append(str4).toString();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        responseCode = responseCode2;
                        str3 = str2;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        httpURLConnection = httpURLConnection2;
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            }
            CommonUtil.printLog("HMTAGENT", new StringBuilder(String.valueOf(responseCode)).append("returnContent=>").append(str3).toString());
            switch (responseCode) {
                case 200:
                    myMessage.setFlag(true);
                    myMessage.setMsg(str3);
                    break;
                default:
                    myMessage.setFlag(false);
                    myMessage.setMsg(str3);
                    break;
            }
            myMessage.setMsg(str3);
        } catch (Exception e) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("err", e.toString());
                String jSONObject2 = jSONObject.toString();
                myMessage.setFlag(false);
                myMessage.setMsg(jSONObject2);
                CommonUtil.printLog("HMTAGENT", jSONObject2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th5) {
            if (ignoreSSL != null) {
                ignoreSSL.disconnect();
            }
        }
        return myMessage;
    }

    public static MyMessage post(String str, String str2) {
        Throwable th;
        DataOutputStream dataOutputStream;
        Exception e;
        Object obj;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        CommonUtil.printLog("hmt", str);
        String str3 = "";
        BufferedReader bufferedReader = null;
        MyMessage myMessage = new MyMessage();
        DataOutputStream dataOutputStream2;
        if (str.startsWith("https")) {
            HttpsURLConnection ignoreSSL;
            try {
                ignoreSSL = ignoreSSL(str);
                try {
                    ignoreSSL.setRequestMethod(Constants.HTTP_POST);
                    ignoreSSL.setDoOutput(true);
                    ignoreSSL.setReadTimeout(15000);
                    ignoreSSL.setConnectTimeout(15000);
                    dataOutputStream2 = new DataOutputStream(ignoreSSL.getOutputStream());
                    if (str2 != null) {
                        try {
                            dataOutputStream2.writeBytes(getEncodeData(str2));
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream = dataOutputStream2;
                            if (ignoreSSL != null) {
                                ignoreSSL.disconnect();
                            }
                            throw th;
                        }
                    }
                    dataOutputStream2.flush();
                    int responseCode = ignoreSSL.getResponseCode();
                    if (ignoreSSL != null) {
                        try {
                            ignoreSSL.disconnect();
                            dataOutputStream = dataOutputStream2;
                        } catch (Exception e2) {
                            e = e2;
                            dataOutputStream = dataOutputStream2;
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("err", e.toString());
                                String jSONObject2 = jSONObject.toString();
                                myMessage.setFlag(false);
                                myMessage.setMsg(jSONObject2);
                                CommonUtil.printLog("HMTAGENT", jSONObject2);
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            } catch (Throwable th3) {
                                th = th3;
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (IOException e4) {
                                        CommonUtil.printLog("hmt", e4.getMessage());
                                    }
                                }
                                throw th;
                            }
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException e5) {
                                    CommonUtil.printLog("hmt", e5.getMessage());
                                }
                            }
                            return myMessage;
                        } catch (Throwable th4) {
                            th = th4;
                            dataOutputStream = dataOutputStream2;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    dataOutputStream = dataOutputStream2;
                } catch (Throwable th5) {
                    th = th5;
                    if (ignoreSSL != null) {
                        ignoreSSL.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                ignoreSSL = null;
                if (ignoreSSL != null) {
                    ignoreSSL.disconnect();
                }
                throw th;
            }
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection3.setReadTimeout(15000);
                httpURLConnection3.setConnectTimeout(15000);
                httpURLConnection3.setDoOutput(true);
                dataOutputStream2 = new DataOutputStream(httpURLConnection3.getOutputStream());
                if (str2 != null) {
                    try {
                        dataOutputStream2.writeBytes(getEncodeData(str2));
                    } catch (Throwable th7) {
                        httpURLConnection2 = httpURLConnection3;
                        th = th7;
                        Object obj2 = dataOutputStream2;
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e6) {
                                e = e6;
                                obj = httpURLConnection;
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("err", e.toString());
                                String jSONObject22 = jSONObject3.toString();
                                myMessage.setFlag(false);
                                myMessage.setMsg(jSONObject22);
                                CommonUtil.printLog("HMTAGENT", jSONObject22);
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                return myMessage;
                            } catch (Throwable th8) {
                                th = th8;
                                obj = httpURLConnection;
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                throw th;
                            }
                        }
                        throw th;
                    }
                }
                dataOutputStream2.flush();
                int responseCode2 = httpURLConnection3.getResponseCode();
                if (httpURLConnection3 != null) {
                    try {
                        httpURLConnection3.disconnect();
                    } catch (Exception e7) {
                        e = e7;
                        dataOutputStream = dataOutputStream2;
                        JSONObject jSONObject32 = new JSONObject();
                        jSONObject32.put("err", e.toString());
                        String jSONObject222 = jSONObject32.toString();
                        myMessage.setFlag(false);
                        myMessage.setMsg(jSONObject222);
                        CommonUtil.printLog("HMTAGENT", jSONObject222);
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        return myMessage;
                    } catch (Throwable th9) {
                        th = th9;
                        dataOutputStream = dataOutputStream2;
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        throw th;
                    }
                }
                dataOutputStream = dataOutputStream2;
                responseCode = responseCode2;
            } catch (Throwable th72) {
                Throwable th10 = th72;
                httpURLConnection = null;
                httpURLConnection2 = httpURLConnection3;
                th = th10;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Throwable th11) {
            th = th11;
            httpURLConnection = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
        try {
            CommonUtil.printLog("hmt", responseCode);
            if (200 == responseCode) {
                String str4 = str3;
                for (jSONObject222 = bufferedReader.readLine(); jSONObject222 != null; jSONObject222 = bufferedReader.readLine()) {
                    if (str4 != null) {
                        str4 = new StringBuilder(String.valueOf(str4)).append(jSONObject222).toString();
                    } else {
                        str4 = jSONObject222;
                    }
                }
                jSONObject222 = URLDecoder.decode(str4);
                myMessage.setFlag(true);
                myMessage.setMsg(jSONObject222);
            } else {
                myMessage.setFlag(false);
                myMessage.setMsg(str3);
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e52) {
                    CommonUtil.printLog("hmt", e52.getMessage());
                }
            }
        } catch (Exception e8) {
            e = e8;
        }
        return myMessage;
    }

    public static boolean Post(String str, String str2, String str3) {
        Throwable th;
        HttpsURLConnection httpsURLConnection;
        Exception e;
        Error e2;
        HttpURLConnection httpURLConnection;
        Object obj;
        HttpURLConnection httpURLConnection2;
        DataOutputStream dataOutputStream = null;
        CommonUtil.printLog("hmt", str);
        HMTTool.preSendCallback(str3);
        try {
            int responseCode;
            if (str.startsWith("https")) {
                try {
                    HttpsURLConnection ignoreSSL = ignoreSSL(str);
                    try {
                        ignoreSSL.setRequestMethod(Constants.HTTP_POST);
                        ignoreSSL.setDoOutput(true);
                        ignoreSSL.setReadTimeout(15000);
                        ignoreSSL.setConnectTimeout(15000);
                        DataOutputStream dataOutputStream2 = new DataOutputStream(ignoreSSL.getOutputStream());
                        if (str2 != null) {
                            try {
                                dataOutputStream2.writeBytes(getEncodeData(str2));
                            } catch (Throwable th2) {
                                th = th2;
                                httpsURLConnection = ignoreSSL;
                                dataOutputStream = dataOutputStream2;
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                }
                                throw th;
                            }
                        }
                        dataOutputStream2.flush();
                        responseCode = ignoreSSL.getResponseCode();
                        if (ignoreSSL != null) {
                            try {
                                ignoreSSL.disconnect();
                                dataOutputStream = dataOutputStream2;
                            } catch (Exception e3) {
                                e = e3;
                                dataOutputStream = dataOutputStream2;
                                try {
                                    CommonUtil.printLog("HMTAGENT=>ex", e.toString());
                                    HMTTool.sendFailCallback(str3, 0);
                                    if (dataOutputStream != null) {
                                        try {
                                            dataOutputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    return false;
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (dataOutputStream != null) {
                                        try {
                                            dataOutputStream.close();
                                        } catch (IOException e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Error e6) {
                                e2 = e6;
                                dataOutputStream = dataOutputStream2;
                                CommonUtil.printLog("HMTAGENT=>err", e2.toString());
                                HMTTool.sendFailCallback(str3, 0);
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (IOException e42) {
                                        e42.printStackTrace();
                                    }
                                }
                                return false;
                            } catch (Throwable th4) {
                                th = th4;
                                dataOutputStream = dataOutputStream2;
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                throw th;
                            }
                        }
                        dataOutputStream = dataOutputStream2;
                    } catch (Throwable th5) {
                        th = th5;
                        httpsURLConnection = ignoreSSL;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    httpsURLConnection = null;
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
            }
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection3.setRequestMethod(Constants.HTTP_POST);
                    httpURLConnection3.setDoOutput(true);
                    httpURLConnection3.setReadTimeout(15000);
                    httpURLConnection3.setConnectTimeout(15000);
                    DataOutputStream dataOutputStream3 = new DataOutputStream(httpURLConnection3.getOutputStream());
                    if (str2 != null) {
                        try {
                            dataOutputStream3.writeBytes(getEncodeData(str2));
                        } catch (Throwable th7) {
                            httpURLConnection = httpURLConnection3;
                            th = th7;
                            Object obj2 = dataOutputStream3;
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (Exception e7) {
                                    e = e7;
                                    obj = httpURLConnection2;
                                    CommonUtil.printLog("HMTAGENT=>ex", e.toString());
                                    HMTTool.sendFailCallback(str3, 0);
                                    if (dataOutputStream != null) {
                                        dataOutputStream.close();
                                    }
                                    return false;
                                } catch (Error e8) {
                                    e2 = e8;
                                    obj = httpURLConnection2;
                                    CommonUtil.printLog("HMTAGENT=>err", e2.toString());
                                    HMTTool.sendFailCallback(str3, 0);
                                    if (dataOutputStream != null) {
                                        dataOutputStream.close();
                                    }
                                    return false;
                                } catch (Throwable th8) {
                                    th = th8;
                                    obj = httpURLConnection2;
                                    if (dataOutputStream != null) {
                                        dataOutputStream.close();
                                    }
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    }
                    dataOutputStream3.flush();
                    int responseCode2 = httpURLConnection3.getResponseCode();
                    if (httpURLConnection3 != null) {
                        try {
                            httpURLConnection3.disconnect();
                        } catch (Exception e9) {
                            e = e9;
                            dataOutputStream = dataOutputStream3;
                            CommonUtil.printLog("HMTAGENT=>ex", e.toString());
                            HMTTool.sendFailCallback(str3, 0);
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            return false;
                        } catch (Error e10) {
                            e2 = e10;
                            dataOutputStream = dataOutputStream3;
                            CommonUtil.printLog("HMTAGENT=>err", e2.toString());
                            HMTTool.sendFailCallback(str3, 0);
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            return false;
                        } catch (Throwable th9) {
                            th = th9;
                            dataOutputStream = dataOutputStream3;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    responseCode = responseCode2;
                    dataOutputStream = dataOutputStream3;
                } catch (Throwable th72) {
                    Throwable th10 = th72;
                    httpURLConnection2 = null;
                    httpURLConnection = httpURLConnection3;
                    th = th10;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th11) {
                th = th11;
                httpURLConnection2 = null;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            CommonUtil.printLog("hmt", responseCode);
            if (200 == responseCode) {
                HMTTool.sendSuccessCallback(str3);
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e422) {
                        e422.printStackTrace();
                    }
                }
                return true;
            }
            HMTTool.sendFailCallback(str3, responseCode);
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e4222) {
                    e4222.printStackTrace();
                }
            }
            return false;
        } catch (Exception e11) {
            e = e11;
            CommonUtil.printLog("HMTAGENT=>ex", e.toString());
            HMTTool.sendFailCallback(str3, 0);
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            return false;
        } catch (Error e12) {
            e2 = e12;
            CommonUtil.printLog("HMTAGENT=>err", e2.toString());
            HMTTool.sendFailCallback(str3, 0);
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            return false;
        }
    }

    private static String getEncodeData(String str) throws IOException {
        CommonUtil.printLog("HMTAGENT", str);
        String encoded = UrlBase64Coder.encoded(UrlBase64Coder.compress(str));
        return new StringBuilder(String.valueOf("contents=" + encoded)).append("&sign=").append(MD5Utility.md5Appkey(new StringBuilder(String.valueOf(encoded)).append("88f702a9ef191b4a22e84ffe4a24e1add60a35b9f2394c560ff").toString())).toString();
    }

    public static HttpsURLConnection ignoreSSL(String str) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        TrustManager[] trustManagerArr = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }
        }};
        HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, trustManagerArr, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        return (HttpsURLConnection) new URL(str).openConnection();
    }
}
