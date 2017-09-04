package qalsdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.k;
import com.tencent.qalsdk.im_open.mobroute.MobRouteSSOListReq;
import com.tencent.qalsdk.util.Cryptor;
import com.tencent.qalsdk.util.QLog;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: ConfigManager */
class e extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ d d;

    e(d dVar, String str, boolean z, String str2) {
        this.d = dVar;
        this.a = str;
        this.b = z;
        this.c = str2;
    }

    public void run() {
        String str;
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable th2;
        MobRouteSSOListReq mobRouteSSOListReq = new MobRouteSSOListReq();
        if (k.a() != null) {
            mobRouteSSOListReq.string_imei.set(k.a());
        }
        if (k.b() != null) {
            mobRouteSSOListReq.string_imsi.set(k.b());
        }
        if (this.a != null) {
            mobRouteSSOListReq.string_uin.set(this.a);
        }
        mobRouteSSOListReq.uint32_nettype.set(this.b ? 1 : 2);
        mobRouteSSOListReq.uint32_uintype.set(20);
        QLog.d("MSF.C.ConfigManager", "get http:" + k.a() + ":" + k.b() + ":" + this.a);
        mobRouteSSOListReq.uint32_appid.set(this.d.j.i());
        int serializedSize = mobRouteSSOListReq.getSerializedSize() + 14;
        ByteBuffer allocate = ByteBuffer.allocate(serializedSize);
        allocate.put((byte) 2);
        allocate.putInt(serializedSize);
        allocate.putShort((short) 1);
        allocate.putShort((short) 2);
        allocate.putInt(j.f());
        allocate.put(mobRouteSSOListReq.toByteArray());
        allocate.put((byte) 3);
        allocate.flip();
        byte[] bArr = new byte[allocate.limit()];
        allocate.get(bArr);
        byte[] encrypt = new Cryptor().encrypt(bArr, d.s);
        HttpURLConnection httpURLConnection2 = null;
        String str2 = "succ";
        try {
            URL url = new URL("http://configsvr.openmsf.3g.qq.com/configsvr/openlist.jsp");
            long currentTimeMillis = System.currentTimeMillis();
            QLog.i("MSF.C.ConfigManager", 1, "start send http sso svrlist  msg");
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) url.openConnection();
            try {
                String str3;
                httpURLConnection3.setDoOutput(true);
                httpURLConnection3.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.getOutputStream().write(encrypt);
                httpURLConnection3.getOutputStream().flush();
                httpURLConnection3.getOutputStream().close();
                int responseCode = httpURLConnection3.getResponseCode();
                if (responseCode == 200) {
                    byte[] bArr2;
                    Object obj = new byte[128];
                    int i = 0;
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        int read = httpURLConnection3.getInputStream().read(obj);
                        if (read == -1) {
                            break;
                        }
                        Object obj2 = new byte[read];
                        System.arraycopy(obj, 0, obj2, 0, read);
                        arrayList.add(obj2);
                        i += read;
                    }
                    if (arrayList.size() == 1) {
                        bArr2 = (byte[]) arrayList.get(0);
                    } else {
                        obj = new byte[i];
                        Iterator it = arrayList.iterator();
                        int i2 = 0;
                        while (it.hasNext()) {
                            bArr2 = (byte[]) it.next();
                            System.arraycopy(bArr2, 0, obj, i2, bArr2.length);
                            i2 = bArr2.length + i2;
                        }
                        Object obj3 = obj;
                    }
                    if (QLog.isDevelopLevel()) {
                        QLog.d("MSF.C.ConfigManager", 4, "send checkSso msg , costTime " + (System.currentTimeMillis() - currentTimeMillis) + " allData len is " + bArr2.length);
                    }
                    if (d.a(new Cryptor().decrypt(bArr2, d.s))) {
                        d.q = System.currentTimeMillis();
                        this.d.j.d.a.q = 1;
                    } else {
                        QLog.d("MSF.C.ConfigManager", 1, "received sso list is null.");
                        this.d.j.d.a.q = 3;
                    }
                    if (!this.b) {
                        d.n = System.currentTimeMillis() + ((long) 3600000);
                        this.d.v();
                    } else if (this.b) {
                        d.o = System.currentTimeMillis() + ((long) 3600000);
                        this.d.w();
                    }
                    str3 = str2;
                } else {
                    str3 = "respCode is " + responseCode;
                    if (QLog.isDevelopLevel()) {
                        QLog.d("MSF.C.ConfigManager", 4, "send checkSso msg , resp code is " + responseCode);
                    }
                    QLog.e("MSF.C.ConfigManager", 1, "send checkSso msg , resp code is " + responseCode);
                    this.d.j.d.a.q = 3;
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                    str = str3;
                } else {
                    str = str3;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection2 = httpURLConnection3;
                th2 = th4;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th2;
        }
        if (this.c != null && this.c.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            str = this.d.j.n.format(Long.valueOf(System.currentTimeMillis())) + DLConstants.DEPENDENCY_PACKAGE_DIV + 0 + DLConstants.DEPENDENCY_PACKAGE_DIV + 0 + DLConstants.DEPENDENCY_PACKAGE_DIV + this.c + DLConstants.DEPENDENCY_PACKAGE_DIV + str;
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.ConfigManager", 2, "add waitReportData " + str);
            }
            try {
                arrayList2.add(str.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HashMap hashMap = new HashMap();
            hashMap.put("CHECKSSOLISTBYHTTP", arrayList2);
            this.d.j.d.a(hashMap);
        }
    }
}
