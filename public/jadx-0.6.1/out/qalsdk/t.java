package qalsdk;

import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.util.QLog;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: SocketEngineFactory */
class t extends Thread {
    final /* synthetic */ o a;

    t(o oVar) {
        this.a = oVar;
    }

    public void run() {
        Throwable th;
        Throwable th2;
        String th3;
        this.a.y = 0;
        this.a.z = false;
        "/".getBytes();
        byte[] bArr = new byte[128];
        HttpURLConnection httpURLConnection = null;
        String str = "succ";
        try {
            URL url = new URL("http://3gimg.qq.com/qq_product_operations/nettest/index.html?mType=connCheck");
            long currentTimeMillis = System.currentTimeMillis();
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "start send checkNetConnectByHttp msg");
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setRequestMethod(Constants.HTTP_GET);
                httpURLConnection2.setConnectTimeout(com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN);
                httpURLConnection2.setReadTimeout(com.tencent.android.tpush.common.Constants.ERRORCODE_UNKNOWN);
                httpURLConnection2.setDoInput(true);
                int read = httpURLConnection2.getInputStream().read(bArr);
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode == 200 && url.getHost().equals(httpURLConnection2.getURL().getHost())) {
                    QLog.d("MSF.C.NetConnTag", 1, "send checkNetConnectByHttp resp code:" + responseCode + " , costTime " + (System.currentTimeMillis() - currentTimeMillis) + ", resp len: " + read);
                    this.a.y = responseCode;
                    this.a.z = true;
                } else {
                    "respCode is " + responseCode;
                    QLog.d("MSF.C.NetConnTag", 1, "send checkNetConnectByHttp msg , resp code is " + responseCode + ", resp len: " + read);
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                this.a.B = System.currentTimeMillis();
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = httpURLConnection2;
                th2 = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                this.a.B = System.currentTimeMillis();
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            th3 = th2.toString();
            this.a.y = 0;
            this.a.z = false;
            QLog.d("MSF.C.NetConnTag", 1, "send checkNetConnectByHttp msg exception" + th3, th2);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.a.B = System.currentTimeMillis();
        }
    }
}
