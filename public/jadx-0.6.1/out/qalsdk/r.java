package qalsdk;

import android.os.Environment;
import com.tencent.qalsdk.util.QLog;
import com.tencent.tesla.soload.SoLoadCore;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

/* compiled from: SocketEngineFactory */
class r extends Thread {
    final /* synthetic */ o a;

    r(o oVar) {
        this.a = oVar;
    }

    public void run() {
        try {
            a();
        } catch (Exception e) {
        }
    }

    void a() {
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "start tcpdump now");
        }
        try {
            String str = null;
            try {
                String[] list = this.a.f.u.getAssets().list("");
                int length = list.length;
                int i = 0;
                while (i < length) {
                    String str2 = list[i];
                    if (str2.indexOf("tcpdump") == -1) {
                        str2 = str;
                    }
                    i++;
                    str = str2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (str != null) {
                this.a.f.u.getDir("assets", 0).toString();
                StringBuilder stringBuilder = new StringBuilder();
                o.b(this.a.f.u);
                new ProcessBuilder(new String[0]).command(new String[]{"chmod", "777", this.a.f.u.getFilesDir().getParent() + SoLoadCore.PATH_TX_LIB + "tcpdump"}).redirectErrorStream(true).start();
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "start tcpdump");
                }
                stringBuilder.setLength(0);
                str = Environment.getExternalStorageDirectory().getPath() + "/tencent/" + this.a.f.u.getPackageName().replace(".", "/") + "/";
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis()));
                stringBuilder.append(str);
                stringBuilder.append(format);
                stringBuilder.append(".pcap");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"su", "-c", this.a.f.u.getFilesDir().getParent() + SoLoadCore.PATH_TX_LIB + "tcpdump  -p -vv -s 0 -w " + stringBuilder}).getErrorStream()));
                    if (bufferedReader.readLine().toLowerCase().contains("syntax error")) {
                        bufferedReader.close();
                        return;
                    }
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "tcpdump started Sleep for 2 minutes");
                    }
                    this.a.w = System.currentTimeMillis();
                    Thread.sleep(120000);
                    bufferedReader.close();
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "tcpdump end Sleep for 2 minutes");
                    }
                    o.l();
                    this.a.v = true;
                } catch (IOException e2) {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "Error running tcpdump, msg=" + e2.getMessage());
                    }
                }
            }
        } catch (Exception e3) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "tcpdump: readLine Exception" + e3);
            }
        }
    }
}
