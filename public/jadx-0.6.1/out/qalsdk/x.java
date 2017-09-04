package qalsdk;

import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.util.QLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import qalsdk.w.a;

/* compiled from: EchoTaskHttp */
public class x extends w {
    private static final String g = "HttpEchoTask";
    private String h;
    private URL i;
    private String j;
    private int k = Constants.ERRORCODE_UNKNOWN;
    private HttpURLConnection l;
    private int m;
    private String n;

    public x(int i, String str, String str2, int i2, a aVar) {
        super(i, aVar);
        this.h = str + b(str);
        this.h = MsfSdkUtils.insertMtype("netdetect", this.h);
        this.j = str2;
        this.k = i2;
    }

    protected boolean a() {
        try {
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " try connect " + this.h);
            }
            this.i = new URL(this.h);
            this.l = (HttpURLConnection) this.i.openConnection();
            this.l.setRequestMethod(com.tencent.connect.common.Constants.HTTP_GET);
            this.l.setDoInput(true);
            this.l.setUseCaches(false);
            this.l.setConnectTimeout(this.k);
            this.l.setReadTimeout(Constants.ERRORCODE_UNKNOWN);
            this.l.connect();
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " connect " + this.h + " succ.");
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " connect " + this.h + " failed.");
            }
            if (this.l != null) {
                this.l.disconnect();
            }
            return false;
        }
    }

    protected void b() {
        if (QLog.isColorLevel()) {
            QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " disconnect " + this.h);
        }
        if (this.l != null) {
            this.l.disconnect();
        }
    }

    protected String c() {
        try {
            this.m = this.l.getResponseCode();
            this.n = this.l.getURL().getHost();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.l.getInputStream()));
            String str = "";
            String str2 = "";
            while (true) {
                str2 = bufferedReader.readLine();
                if (str2 == null) {
                    break;
                }
                str = str + str2;
            }
            bufferedReader.close();
            if (!QLog.isColorLevel()) {
                return str;
            }
            QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " echo content: " + (str.length() > 10 ? str.substring(0, 10) : str));
            return str;
        } catch (Throwable th) {
            th.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " echo failed");
            }
            return null;
        }
    }

    protected int a(String str) {
        if (this.m != 200 || !this.i.getHost().equals(this.n) || TextUtils.isEmpty(str)) {
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " valid failed.");
            }
            if (this.m == 302 || this.m == 301) {
                return -3;
            }
            QLog.i(g, "wifi detect response:" + this.m);
            return -2;
        } else if (!str.equals(this.j)) {
            return -3;
        } else {
            if (QLog.isColorLevel()) {
                QLog.d(g, 2, "WIFI detect, HttpEchoTask " + this.e + " valid succ");
            }
            return 0;
        }
    }

    protected Object d() {
        return this.h;
    }

    private String b(String str) {
        String str2 = "r=" + String.valueOf(new Random(System.currentTimeMillis()).nextInt(100000));
        if (!str.contains("?")) {
            return "?" + str2;
        }
        if (str.endsWith("?")) {
            return str + str2;
        }
        return str2 + "&" + str2;
    }
}
