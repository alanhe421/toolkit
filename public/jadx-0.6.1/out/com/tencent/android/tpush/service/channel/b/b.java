package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.channel.c.e;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class b extends f implements e {
    protected HashMap a = new HashMap(4);
    protected int b = 0;
    protected int c = -1;
    public ArrayList d = new ArrayList(1);
    protected String e = null;
    protected String f = null;
    protected final HashMap g = new HashMap(8);

    public b(String str, String str2) {
        this.e = str;
        this.f = str2;
    }

    void a(int i) {
        if (this.c != i) {
            this.b = 0;
        }
        this.c = i;
    }

    public int a(OutputStream outputStream) {
        int i;
        Throwable e;
        c();
        try {
            this.b = 0;
            i = 0;
            while (!b()) {
                try {
                    int i2 = this.b;
                    this.b = i2 + 1;
                    if (i2 > 2) {
                        throw new InnerException("the duration of the current step is too long!");
                    }
                    switch (this.c) {
                        case -1:
                            i += b(outputStream);
                            break;
                        case 0:
                            d();
                            break;
                        default:
                            throw new InnerException("illegal step value!");
                    }
                } catch (IORefusedException e2) {
                    e = e2;
                }
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            i = 0;
            e = th;
            a.c("Channel.HttpSendPacket", "write >>> IORefusedException thrown", e);
            return i;
        }
        return i;
    }

    protected int b(OutputStream outputStream) {
        byte[] bArr = (byte[]) this.a.get("httpData");
        if (bArr == null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                c(byteArrayOutputStream);
                Object toByteArray = byteArrayOutputStream.toByteArray();
                a("Content-Length", String.valueOf(toByteArray.length));
                String str = "POST " + this.f + " HTTP/1.1" + "\r\n";
                String str2 = str;
                for (Entry entry : this.g.entrySet()) {
                    str2 = str2 + ((String) entry.getKey()) + ": " + ((String) entry.getValue()) + "\r\n";
                }
                Object bytes = (str2 + "\r\n").getBytes("UTF-8");
                bArr = new byte[(bytes.length + toByteArray.length)];
                System.arraycopy(bytes, 0, bArr, 0, bytes.length);
                System.arraycopy(toByteArray, 0, bArr, bytes.length, toByteArray.length);
                this.a.put("httpData", bArr);
                this.a.put("httpDataLeftLength", Integer.valueOf(bArr.length));
            } catch (Throwable e) {
                throw new UnexpectedDataException("http content can not be write correctly!", e);
            }
        }
        byte[] bArr2 = bArr;
        int intValue = ((Integer) this.a.get("httpDataLeftLength")).intValue();
        if (intValue == 0) {
            a(0);
            return 0;
        }
        int a = e.a(outputStream, bArr2);
        this.a.put("httpDataLeftLength", Integer.valueOf(intValue - a));
        return a;
    }

    protected void c(OutputStream outputStream) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            eVar.a(this.j);
            eVar.a(outputStream);
        }
    }

    public void a(String str, String str2) {
        this.g.put(str, str2);
    }

    public void a(e eVar) {
        this.d.add(eVar);
    }
}
