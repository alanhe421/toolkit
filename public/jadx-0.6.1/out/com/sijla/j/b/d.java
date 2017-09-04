package com.sijla.j.b;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.sijla.j.a.c;
import com.sijla.j.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private JSONObject a;
    private Context b;

    private class a implements X509TrustManager {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }
    }

    public d(Context context, JSONObject jSONObject) {
        this.b = context;
        this.a = jSONObject;
    }

    private JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject optJSONObject = this.a.optJSONObject("xpc").optJSONObject("val");
            Object optString = optJSONObject.optString("atg");
            Object optString2 = optJSONObject.optString("adg");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return null;
            }
            JSONObject b = b(this.b);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(b);
            JSONArray optJSONArray = optJSONObject.optJSONArray("bev");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return null;
            }
            JSONArray jSONArray2 = new JSONArray();
            for (String[] a : a(optJSONArray)) {
                jSONArray2.put(a(this.b, a));
            }
            jSONObject.put(optString, jSONArray2);
            jSONObject.put(optString2, jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.a.optJSONObject("xpc").optJSONObject("val").optString("sgv"));
    }

    private String e() {
        return this.a.optString("urlc");
    }

    private String f() {
        return this.a.optString(SocialConstants.PARAM_URL);
    }

    private JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject optJSONObject = this.a.optJSONObject("xpc");
            String optString = optJSONObject.optString("au");
            String optString2 = optJSONObject.optString("an");
            String optString3 = optJSONObject.optString("ag");
            String optString4 = optJSONObject.optString("ao");
            String optString5 = optJSONObject.optString("az");
            String optString6 = optJSONObject.optString("ac");
            String optString7 = optJSONObject.optString("ax");
            String optString8 = optJSONObject.optString("ap");
            String optString9 = optJSONObject.optString("al");
            String optString10 = optJSONObject.optString("ay");
            String optString11 = optJSONObject.optString("at");
            String optString12 = optJSONObject.optString("aw");
            String optString13 = optJSONObject.optString("am");
            String optString14 = optJSONObject.optString("ai");
            String optString15 = optJSONObject.optString("as");
            String optString16 = optJSONObject.optString("av");
            String optString17 = optJSONObject.optString("af");
            String optString18 = optJSONObject.optString("aa");
            String optString19 = optJSONObject.optString("aq");
            String optString20 = optJSONObject.optString("bc");
            String optString21 = optJSONObject.optString("ae");
            String optString22 = optJSONObject.optString("ba");
            String optString23 = optJSONObject.optString("bd");
            String optString24 = optJSONObject.optString("aj");
            String optString25 = optJSONObject.optString("ah");
            optJSONObject = optJSONObject.optJSONObject("val");
            String optString26 = optJSONObject.optString("auv");
            String optString27 = optJSONObject.optString("anv");
            String optString28 = optJSONObject.optString("aty");
            String optString29 = optJSONObject.optString("azv");
            String optString30 = optJSONObject.optString("axv");
            String optString31 = optJSONObject.optString("ayv");
            jSONObject.put(optString, optString26);
            jSONObject.put(optString2, optString27);
            jSONObject.put(optString3, optString28);
            jSONObject.put(optString4, f(context));
            jSONObject.put(optString5, optString29);
            jSONObject.put(optString6, String.valueOf(i()));
            jSONObject.put(optString7, optString30);
            jSONObject.put(optString8, "");
            jSONObject.put(optString9, com.sijla.j.a.a.j(context));
            jSONObject.put(optString10, optString31);
            jSONObject.put(optString11, "");
            jSONObject.put(optString12, "");
            jSONObject.put(optString13, h(context));
            optString = com.sijla.j.a.a.k(context);
            jSONObject.put(optString14, optString.equals("") ? "" : c.a(optString));
            jSONObject.put(optString15, optString);
            jSONObject.put(optString16, "");
            jSONObject.put(optString17, i(context));
            jSONObject.put(optString18, j(context));
            jSONObject.put(optString19, com.sijla.j.a.a.d());
            optString31 = this.a.optString("app", "");
            jSONObject.put(optString20, optString31);
            optString = this.a.optString("appver", "");
            jSONObject.put(optString21, optString);
            jSONObject.put(optString22, this.a.optString("apd", ""));
            jSONObject.put(optString23, optString31 + "_" + optString);
            jSONObject.put(optString24, h());
            jSONObject.put(optString25, Locale.getDefault().getLanguage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject a(Context context, String[] strArr) {
        JSONObject a = a(context);
        try {
            JSONObject optJSONObject = this.a.optJSONObject("xpc");
            String optString = this.a.optString("appid");
            String optString2 = optJSONObject.optString("ag");
            String optString3 = optJSONObject.optString("be");
            String optString4 = optJSONObject.optString("bb");
            String optString5 = optJSONObject.optString("ad");
            String optString6 = optJSONObject.optString("ac");
            String optString7 = optJSONObject.optString("ak");
            String optString8 = optJSONObject.optString("ar");
            String optString9 = optJSONObject.optString("ab");
            a.put(optString2, optJSONObject.optJSONObject("val").optString("aty"));
            a.put(optString3, strArr[0]);
            a.put(optString4, optString + strArr[0]);
            a.put(optString7, strArr[1]);
            a.put(optString5, strArr[2]);
            a.put(optString8, strArr[3]);
            a.put(optString6, strArr[4]);
            a.put(optString9, g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }

    private JSONObject b(Context context) {
        JSONObject a = a(context);
        try {
            JSONObject optJSONObject = this.a.optJSONObject("xpc");
            String optString = this.a.optString("appid");
            String optString2 = optJSONObject.optString("by");
            String optString3 = optJSONObject.optString("bx");
            String optString4 = optJSONObject.optString("bg");
            String optString5 = optJSONObject.optString("bw");
            String optString6 = optJSONObject.optString("bs");
            String optString7 = optJSONObject.optString("br");
            String optString8 = optJSONObject.optString("bt");
            String optString9 = optJSONObject.optString("cb");
            String optString10 = optJSONObject.optString("bz");
            String optString11 = optJSONObject.optString("bv");
            String optString12 = optJSONObject.optString("bn");
            String optString13 = optJSONObject.optString("bo");
            String optString14 = optJSONObject.optString("bq");
            String optString15 = optJSONObject.optString("ca");
            String optString16 = optJSONObject.optString("bm");
            String optString17 = optJSONObject.optString("bh");
            String optString18 = optJSONObject.optString("bu");
            String optString19 = optJSONObject.optString("bj");
            String optString20 = optJSONObject.optString("bk");
            String optString21 = optJSONObject.optString("bl");
            String optString22 = optJSONObject.optString("bi");
            String optString23 = optJSONObject.optString("bp");
            a.put(optJSONObject.optString("ag"), optJSONObject.optJSONObject("val").optString("ady"));
            a.put(optString7, g(context));
            a.put(optString4, Build.MODEL);
            a.put(optString2, Build.PRODUCT);
            a.put(optString9, k(context));
            a.put(optString5, optString);
            a.put(optString6, e(context));
            a.put(optString8, com.sijla.j.a.a.k(context));
            a.put(optString3, Build.MANUFACTURER);
            a.put(optString11, com.sijla.j.a.a.z(context));
            a.put(optString12, BluetoothAdapter.getDefaultAdapter() != null);
            a.put(optString15, com.sijla.j.a.a.m(context));
            a.put(optString16, true);
            a.put(optString17, com.sijla.j.a.a.b());
            String[] d = d(context);
            a.put(optString10, com.sijla.j.a.a.y(context));
            a.put(optString14, true);
            a.put(optString18, d[1]);
            a.put(optString13, c(context));
            a.put(optString22, com.sijla.j.a.a.A(context));
            a.put(optString23, com.sijla.j.a.a.d(context));
            String[] l = l(context);
            a.put(optString20, l[0]);
            a.put(optString21, l[1]);
            a.put(optString19, d[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }

    private List<String[]> a(JSONArray jSONArray) {
        long currentTimeMillis = System.currentTimeMillis() - ((long) (60000 * b.a(10, 50)));
        List<String[]> b = b(jSONArray);
        long j = 0;
        for (String[] strArr : b) {
            j = Long.parseLong(strArr[1]) + j;
        }
        long j2 = currentTimeMillis - j;
        List<String[]> arrayList = new ArrayList();
        j = j2;
        for (String[] strArr2 : b) {
            r6 = new String[5];
            j2 = Long.parseLong(strArr2[1]) - b.a(10, 100);
            r6[2] = String.valueOf(j + j2);
            r6[3] = String.valueOf(j2);
            j2 = (j2 + j) + ((long) b.a(2, 8));
            r6[4] = String.valueOf(j2);
            arrayList.add(r6);
            j = j2;
        }
        return arrayList;
    }

    private List<String[]> b(JSONArray jSONArray) {
        int length = jSONArray.length();
        List<String[]> arrayList = new ArrayList();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("pgv");
                int optInt = optJSONObject.optInt("default");
                int optInt2 = optJSONObject.optInt("max");
                int optInt3 = optJSONObject.optInt(MessageKey.MSG_ACCEPT_TIME_MIN);
                if (optInt == 0) {
                    optInt = b.a(optInt3, optInt2);
                }
                arrayList.add(new String[]{optString, String.valueOf(optInt * 1000)});
            }
        }
        return arrayList;
    }

    private boolean c(Context context) {
        return ((LocationManager) context.getSystemService("location")) != null;
    }

    private String[] d(Context context) {
        String[] strArr = new String[2];
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                if (gsmCellLocation != null) {
                    strArr[0] = String.valueOf(gsmCellLocation.getLac());
                    strArr[1] = String.valueOf(gsmCellLocation.getCid());
                }
            }
        } catch (Exception e) {
            strArr[0] = "";
            strArr[1] = "";
        }
        return strArr;
    }

    private String e(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string == null || string.contains("2e549") || string.length() < 15) {
            return new BigInteger(64, new SecureRandom()).toString(16);
        }
        return string;
    }

    private String g() {
        return c.a(a() + i());
    }

    private String f(Context context) {
        String str = "";
        str = g(context);
        String k = k(context);
        if ("".equals(str) || str.contains("000000")) {
            str = "";
        }
        if ("".equals(k) || k.contains("00:00:00") || k.contains("ff:ff")) {
            k = "";
        }
        if (!TextUtils.isEmpty(str + k)) {
            return c.a(str + k);
        }
        str = com.sijla.j.a.a.k(context);
        if (TextUtils.isEmpty(str) || str.contains("82e54") || str.length() < 15) {
            return new BigInteger(64, new SecureRandom()).toString(16);
        }
        return str;
    }

    private String g(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private String h(Context context) {
        String g = g(context);
        if ("".equals(g)) {
            return "";
        }
        return c.a(g);
    }

    private String i(Context context) {
        String k = k(context);
        if (k == null || "".equals(k)) {
            return "";
        }
        return c.a(k.replace(":", "").toUpperCase());
    }

    private String j(Context context) {
        String k = k(context);
        if (k == null || "".equals(k)) {
            return "";
        }
        return c.a(k.toUpperCase());
    }

    private String k(Context context) {
        String str = "";
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getMacAddress();
                }
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    public String a() {
        try {
            return this.a.optJSONObject("xpc").getJSONObject("val").getString("anv");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String h() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return a(str2);
        }
        return a(str) + " " + str2;
    }

    private String a(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    private long i() {
        return System.currentTimeMillis();
    }

    private String[] l(Context context) {
        String[] strArr = new String[]{"", ""};
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                strArr[0] = String.valueOf(lastKnownLocation.getLatitude());
                strArr[1] = String.valueOf(lastKnownLocation.getLongitude());
            }
        } catch (Exception e) {
        }
        return strArr;
    }

    public boolean b() {
        boolean z = false;
        try {
            JSONObject optJSONObject = this.a.optJSONObject("xpc").optJSONObject("val");
            String optString = optJSONObject.optString("cnv");
            String optString2 = optJSONObject.optString("anv");
            JSONObject c = c();
            if (c != null) {
                String b = b.b(e.a(c.toString()));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(optString, b);
                if (d()) {
                    jSONObject.put(optJSONObject.optString("sgk"), c.a(b + optJSONObject.optString("sgv")));
                }
                optString = a(jSONObject).toString();
                a(e() + optString2 + optJSONObject.optString("sfv"), "", Constants.HTTP_GET);
                z = a(f(), optString, Constants.HTTP_POST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    private boolean a(String str, String str2, String str3) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            HttpURLConnection a = a(str, str3);
            if (str3.equals(Constants.HTTP_POST)) {
                a(a, str2.getBytes());
                b(a, str2.getBytes());
            }
            if (200 == a.getResponseCode()) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(new KeyManager[0], new TrustManager[]{new a()}, new SecureRandom());
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                httpURLConnection.setHostnameVerifier(new HostnameVerifier(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                });
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        }
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN);
        httpURLConnection.setReadTimeout(com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setUseCaches(false);
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
    }

    private void b(HttpURLConnection httpURLConnection, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            httpURLConnection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bArr);
            dataOutputStream.close();
        }
    }

    private StringBuffer a(JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        if (jSONObject == null) {
            return stringBuffer;
        }
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                stringBuffer.append(str).append("=").append(URLEncoder.encode(jSONObject.get(str) + "", "UTF-8")).append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
