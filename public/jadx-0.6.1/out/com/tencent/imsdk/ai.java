package com.tencent.imsdk;

import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.TIMCallBack;
import com.tencent.imsdk.util.QualityReportHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

final class ai implements Runnable {
    final /* synthetic */ TIMCallBack a;
    private /* synthetic */ List b;
    private /* synthetic */ QualityReportHelper c;
    private /* synthetic */ String d;

    ai(IMMsfCoreProxy iMMsfCoreProxy, List list, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper, String str) {
        this.b = list;
        this.a = tIMCallBack;
        this.c = qualityReportHelper;
        this.d = str;
    }

    public final void run() {
        String stackTraceString;
        HttpURLConnection httpURLConnection;
        Throwable th;
        int i = 0;
        for (String stackTraceString2 : this.b) {
            i++;
            try {
                QLog.d("imsdk.IMMsfCoreProxy", 1, "downloading from url: " + stackTraceString2);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(stackTraceString2).openConnection();
                try {
                    QLog.d("imsdk.IMMsfCoreProxy", 1, "http resp code: " + httpURLConnection2.getResponseCode() + "|descr: " + httpURLConnection2.getResponseMessage());
                    if (httpURLConnection2.getResponseCode() == 200) {
                        try {
                            InputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                            FileOutputStream fileOutputStream = new FileOutputStream(this.d);
                            byte[] bArr = new byte[10240];
                            while (true) {
                                int read = bufferedInputStream.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.close();
                            this.c.init(0, "");
                            this.c.report();
                            IMMsfCoreProxy.mainHandler.post(new al(this));
                            return;
                        } catch (Throwable th2) {
                            String stackTraceString3 = Log.getStackTraceString(th2);
                            QLog.e("imsdk.IMMsfCoreProxy", 1, stackTraceString3);
                            File file = new File(this.d);
                            if (file.exists()) {
                                file.delete();
                            }
                            IMMsfCoreProxy.mainHandler.post(new am(this, stackTraceString3));
                            this.c.init(BaseConstants.ERR_IO_OPERATION_FAILED, stackTraceString3);
                            this.c.report();
                        } finally {
                            httpURLConnection2.disconnect();
                        }
                    } else if (i >= this.b.size()) {
                        String str = httpURLConnection2.getResponseCode() + DLConstants.DEPENDENCY_PACKAGE_DIV + httpURLConnection2.getResponseMessage();
                        IMMsfCoreProxy.mainHandler.post(new aj(this, str));
                        this.c.init(6010, str);
                        this.c.report();
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
        stackTraceString2 = Log.getStackTraceString(th);
        QLog.e("imsdk.IMMsfCoreProxy", 1, stackTraceString2);
        IMMsfCoreProxy.mainHandler.post(new ak(this, stackTraceString2));
        this.c.init(6010, stackTraceString2);
        this.c.report();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
