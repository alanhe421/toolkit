package com.tencent.imsdk;

import android.os.Handler;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.util.QualityReportHelper;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

final class an implements Runnable {
    final /* synthetic */ TIMValueCallBack a;
    final /* synthetic */ QualityReportHelper b;
    private /* synthetic */ List c;
    private /* synthetic */ Handler d;

    an(IMMsfCoreProxy iMMsfCoreProxy, List list, Handler handler, TIMValueCallBack tIMValueCallBack, QualityReportHelper qualityReportHelper) {
        this.c = list;
        this.d = handler;
        this.a = tIMValueCallBack;
        this.b = qualityReportHelper;
    }

    public final void run() {
        HttpURLConnection httpURLConnection;
        Throwable th;
        int i = 0;
        for (String str : this.c) {
            i++;
            try {
                String str2;
                QLog.d("imsdk.IMMsfCoreProxy", 1, "downloading from url: " + str2);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str2).openConnection();
                try {
                    QLog.d("imsdk.IMMsfCoreProxy", 1, "http resp code: " + httpURLConnection2.getResponseCode() + "|descr: " + httpURLConnection2.getResponseMessage());
                    if (httpURLConnection2.getResponseCode() == 200) {
                        try {
                            InputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[10240];
                            while (true) {
                                int read = bufferedInputStream.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            this.d.post(new aq(this, byteArrayOutputStream.toByteArray()));
                            this.b.init(0, "");
                            this.b.report();
                            return;
                        } catch (Throwable th2) {
                            String stackTraceString = Log.getStackTraceString(th2);
                            QLog.e("imsdk.IMMsfCoreProxy", 1, stackTraceString);
                            this.d.post(new ar(this, stackTraceString));
                        } finally {
                            httpURLConnection2.disconnect();
                        }
                    } else if (i >= this.c.size()) {
                        String str3 = httpURLConnection2.getResponseCode() + DLConstants.DEPENDENCY_PACKAGE_DIV + httpURLConnection2.getResponseMessage();
                        this.d.post(new ao(this, str3));
                        this.b.init(6010, str3);
                        this.b.report();
                        return;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection = httpURLConnection2;
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = null;
            }
        }
        return;
        str2 = Log.getStackTraceString(th);
        QLog.e("imsdk.IMMsfCoreProxy", 1, str2);
        this.d.post(new ap(this, str2));
        this.b.init(6010, str2);
        this.b.report();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
