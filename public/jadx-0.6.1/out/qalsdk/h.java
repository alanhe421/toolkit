package qalsdk;

import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.util.MsfSocketInputBuffer;
import com.tencent.qalsdk.util.QLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.ProtocolException;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.IdentityInputStream;

/* compiled from: HttpProtocolDataWrapper */
public class h implements i {
    j a;

    public h(j jVar) {
        this.a = jVar;
    }

    public void a(MsfSocketInputBuffer msfSocketInputBuffer) throws Exception {
        do {
            j a = new k(msfSocketInputBuffer).a();
            if (k.a(a)) {
                if (a.f() != -1) {
                    a.a(new ContentLengthInputStream(msfSocketInputBuffer, (long) a.f()));
                } else if (a.h().equalsIgnoreCase("chunked")) {
                    a.a(new ChunkedInputStream(msfSocketInputBuffer));
                } else {
                    a.a(new IdentityInputStream(msfSocketInputBuffer));
                }
            }
            int statusCode = a.b().getStatusCode();
            if (statusCode >= 200) {
                Object obj = new byte[20480];
                ArrayList arrayList = new ArrayList();
                statusCode = 0;
                while (true) {
                    int read = a.k().read(obj);
                    if (read <= 0) {
                        break;
                    }
                    statusCode += read;
                    Object obj2 = new byte[read];
                    System.arraycopy(obj, 0, obj2, 0, obj2.length);
                    arrayList.add(obj2);
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, " read " + obj2.length);
                    }
                }
                if (arrayList.size() == 1) {
                    this.a.d.c((byte[]) arrayList.get(0));
                } else {
                    byte[] bArr = new byte[statusCode];
                    Iterator it = arrayList.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        byte[] bArr2 = (byte[]) it.next();
                        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
                        i = bArr2.length + i;
                    }
                    this.a.d.c(bArr);
                }
            } else if (statusCode != 100) {
                throw new ProtocolException("Unexpected response: " + a.b());
            }
        } while (msfSocketInputBuffer.hasBufferedData());
    }

    public byte[] a(c cVar, String str, String str2, byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        int a = a(bArr2);
        if (bArr.length <= a) {
            return b(cVar, str, str2, bArr);
        }
        ArrayList arrayList = new ArrayList();
        int i = a;
        int i2 = 0;
        a = 0;
        while (bArr.length >= i) {
            Object obj = new byte[i];
            System.arraycopy(bArr, i2, obj, 0, obj.length);
            Object b = b(cVar, str, str2, obj);
            a += b.length;
            arrayList.add(b);
            i2 += obj.length;
            if (i2 >= bArr.length) {
                break;
            }
            System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
            i = a(bArr2);
        }
        Object obj2 = new byte[a];
        Iterator it = arrayList.iterator();
        i = 0;
        while (it.hasNext()) {
            byte[] bArr3 = (byte[]) it.next();
            System.arraycopy(bArr3, 0, obj2, i, bArr3.length);
            i = bArr3.length + i;
        }
        return obj2;
    }

    protected int a(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put(bArr).flip();
        return allocate.getInt();
    }

    protected byte[] b(c cVar, String str, String str2, byte[] bArr) {
        Object bytes = ("POST / HTTP/1.1\r\nConnection: Keep-Alive\r\nHost: " + cVar.c() + ":" + cVar.d() + "\r\n" + "Accept: */*\r\n" + "User-Agent: javaMsfClient\r\n" + "Content-Type: application/x-www-form-urlencoded\r\n" + "Content-Length: " + bArr.length + "\r\n\r\n").getBytes();
        Object obj = new byte[(bytes.length + bArr.length)];
        System.arraycopy(bytes, 0, obj, 0, bytes.length);
        System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
        return obj;
    }
}
