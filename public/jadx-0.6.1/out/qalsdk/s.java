package qalsdk;

import com.tencent.qalsdk.util.QLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: SocketEngineFactory */
class s extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    s(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    public void run() {
        a(5, this.a);
    }

    void a(int i, String str) {
        Process start;
        try {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "start pingServer:" + this.a);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("-c ");
            stringBuilder.append(i);
            start = new ProcessBuilder(new String[0]).command(new String[]{"/system/bin/ping", stringBuilder.toString(), str}).redirectErrorStream(true).start();
            start.getInputStream();
            start.getOutputStream();
            String str2 = new String();
            str2 = new String();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            String str3 = new String();
            while (true) {
                str3 = bufferedReader.readLine();
                if (str3 == null) {
                    start.destroy();
                    return;
                } else if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "pingServer:" + this.a + " out:" + str3);
                }
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "pingServer:" + this.a + " readLine Exception" + e);
            }
        } catch (Throwable th) {
            start.destroy();
        }
    }
}
