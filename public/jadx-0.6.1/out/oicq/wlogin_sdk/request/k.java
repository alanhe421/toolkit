package oicq.wlogin_sdk.request;

import com.tencent.connect.common.Constants;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: oicq_report_error */
public class k {
    protected u a;

    protected byte[] a(byte[] bArr) {
        Object obj = util.get_rand_16byte(u.a);
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, obj);
        Object obj2 = new byte[(encrypt.length + obj.length)];
        System.arraycopy(obj, 0, obj2, 0, obj.length);
        System.arraycopy(encrypt, 0, obj2, obj.length, encrypt.length);
        return obj2;
    }

    public String a(int i) {
        String[] strArr = new String[]{"log.wtlogin.qq.com", "log1.wtlogin.qq.com"};
        return strArr[Math.abs(i % strArr.length)];
    }

    public int b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        int responseCode;
        util.LOGI(getClass().getName() + ":snd_rcv_req_error ...", "" + this.a.f);
        int nextInt = new Random().nextInt();
        int i = 0;
        while (i < 2) {
            util.LOGI("try http connect " + i + " ...", "" + this.a.f);
            try {
                URL url = new URL("http://" + a(nextInt) + "/cgi-bin/wlogin_proxy_log");
                util.LOGI("url=" + url, "" + this.a.f);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
                httpURLConnection.setRequestProperty("Content-Disposition", "attachment; filename=micromsgresp.dat");
                httpURLConnection.setRequestProperty("Content-Length", new Integer(bArr.length).toString());
                httpURLConnection.setConnectTimeout(this.a.l);
                httpURLConnection.setReadTimeout(this.a.l);
                httpURLConnection.setDoOutput(true);
                util.LOGI("http request connect ...", "" + this.a.f);
                if (j.a(httpURLConnection, (long) this.a.l)) {
                    util.LOGI("http request write ...", "" + this.a.f);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(bArr, 0, bArr.length);
                    outputStream.flush();
                    responseCode = httpURLConnection.getResponseCode();
                    util.LOGI("http request response code=" + responseCode, "" + this.a.f);
                    if (200 == responseCode) {
                        break;
                    }
                    i++;
                    nextInt++;
                } else {
                    util.LOGI("http request connect failed", "" + this.a.f);
                    i++;
                    nextInt++;
                }
            } catch (Exception e) {
                util.printException(e, "" + this.a.f);
                i++;
                nextInt++;
            }
        }
        if (i >= 1) {
            responseCode = -1000;
        } else {
            responseCode = 0;
        }
        util.LOGI(getClass().getName() + ":snd_rcv_req_error ret=" + responseCode, "" + this.a.f);
        return responseCode;
    }
}
