package qalsdk;

import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.qalsdk.util.QLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import qalsdk.w.a;

/* compiled from: EchoTaskTcp */
public class y extends w {
    public static ArrayList<String> g = new ArrayList();
    private static final String h = "EchoTaskTcp";
    private String i;
    private int j;
    private String k;
    private String l;
    private int m = Constants.ERRORCODE_UNKNOWN;
    private Socket n;
    private String o;

    public y(int i, String str, int i2, String str2, int i3, a aVar) {
        super(i, aVar);
        this.i = str;
        this.j = i2;
        this.k = this.i + ":" + this.j;
        this.l = str2;
        this.m = i3;
        this.o = APMidasPayAPI.ENV_TEST;
    }

    protected boolean a() {
        try {
            if (QLog.isColorLevel()) {
                QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " try connect " + this.k);
            }
            SocketAddress inetSocketAddress = new InetSocketAddress(this.i, this.j);
            this.n = new Socket();
            this.n.setSoTimeout(Constants.ERRORCODE_UNKNOWN);
            this.n.setTcpNoDelay(true);
            this.n.setKeepAlive(true);
            this.n.connect(inetSocketAddress, this.m);
            if (!QLog.isColorLevel()) {
                return true;
            }
            QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " connect " + this.k + " succ.");
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " connect " + this.k + " failed.");
            }
            if (this.n != null) {
                try {
                    this.n.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    protected void b() {
        if (QLog.isColorLevel()) {
            QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " disconnect " + this.k);
        }
        if (this.n != null) {
            try {
                g.clear();
                this.n.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected String c() {
        try {
            OutputStream outputStream = this.n.getOutputStream();
            g.add(outputStream.toString());
            InputStream inputStream = this.n.getInputStream();
            g.add(inputStream.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream.write(this.o.getBytes());
            outputStream.flush();
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
            QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " echo content: " + (str.length() > 10 ? str.substring(0, 10) : str));
            return str;
        } catch (Throwable th) {
            g.clear();
            th.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " echo failed");
            }
            return null;
        }
    }

    protected int a(String str) {
        if (TextUtils.isEmpty(str) || !str.equals(this.l)) {
            if (QLog.isColorLevel()) {
                QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " valid failed.");
            }
            return -3;
        }
        if (QLog.isColorLevel()) {
            QLog.d(h, 2, "WIFI detect, EchoTaskTcp " + this.e + " valid succ");
        }
        return 0;
    }

    protected Object d() {
        return this.k;
    }
}
