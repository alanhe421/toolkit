package qalsdk;

import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.util.QLog;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/* compiled from: SocketEngineFactory */
class p extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ o c;

    p(o oVar, String str, int i) {
        this.c = oVar;
        this.a = str;
        this.b = i;
    }

    public void run() {
        try {
            this.c.A = false;
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "start send checkNetConnectBySocket server:" + this.a + " port:" + this.b);
            }
            Socket socket = new Socket();
            SocketAddress inetSocketAddress = new InetSocketAddress(this.a, this.b);
            socket.setSoTimeout(Constants.ERRORCODE_UNKNOWN);
            socket.setTcpNoDelay(true);
            socket.setKeepAlive(true);
            socket.connect(inetSocketAddress, Constants.ERRORCODE_UNKNOWN);
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "checkNetConnectBySocket connect server:" + this.a + " port:" + this.b + " success");
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("GET http://3gimg.qq.com/qq_product_operations/nettest/index.html?mType=connCheck HTTP/1.1\r\n");
            stringBuffer.append("Host: 3gimg.qq.com \r\n");
            stringBuffer.append("Connection: close\r\n");
            stringBuffer.append("\r\n");
            outputStreamWriter.write(stringBuffer.toString());
            outputStreamWriter.flush();
            InputStream inputStream = socket.getInputStream();
            String str = null;
            byte[] bArr = new byte[64];
            if (inputStream.read(bArr) != -1) {
                str = new String(bArr, o.E);
            }
            if (str.indexOf("302") != -1) {
                this.c.A = true;
            }
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "checkNetConnectBySocket get header:" + str);
            } else {
                QLog.d("MSF.C.NetConnTag", 1, "checkNetConnectBySocket " + str.hashCode() + " len: " + str.length());
            }
            inputStream.close();
        } catch (Throwable e) {
            QLog.d("MSF.C.NetConnTag", 1, "checkNetConnectBySocket connect server UnknownHostException:" + this.a + " port:" + this.b + " failed" + e, e);
        } catch (Throwable e2) {
            QLog.d("MSF.C.NetConnTag", 1, "checkNetConnectBySocket connect server IOException:" + this.a + " port:" + this.b + " failed" + e2, e2);
        } catch (Throwable e22) {
            QLog.d("MSF.C.NetConnTag", 1, "checkNetConnectBySocket connect server:" + this.a + " port:" + this.b + " failed" + e22, e22);
        }
    }
}
