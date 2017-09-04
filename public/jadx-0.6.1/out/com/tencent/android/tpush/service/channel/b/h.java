package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.channel.c.e;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/* compiled from: ProGuard */
public class h extends i implements e {
    protected HashMap a;
    protected int b;
    protected int c;

    public h(int i) {
        this.a = new HashMap(4);
        this.b = 0;
        this.c = -1;
        this.d = (short) 80;
        this.e = i;
    }

    public synchronized void d() {
        super.d();
        this.a.clear();
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
                        case -5:
                            i += f(outputStream);
                            break;
                        case -4:
                            i += e(outputStream);
                            break;
                        case -3:
                            i += d(outputStream);
                            break;
                        case -2:
                            i += c(outputStream);
                            break;
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
            a.c("Channel.SendPacket", "write >>> IORefusedException thrown", e);
            return i;
        }
        return i;
    }

    void a(int i) {
        if (this.c != i) {
            this.b = 0;
        }
        this.c = i;
    }

    protected int b(OutputStream outputStream) {
        e.a(outputStream, this.d);
        a(-2);
        return 1;
    }

    protected int c(OutputStream outputStream) {
        e.a(outputStream, this.k);
        switch (this.k) {
            case (short) 1:
            case (short) 10:
                a(-3);
                break;
            case (short) 20:
                a(0);
                break;
            default:
                throw new UnexpectedDataException("protocol: " + this.k);
        }
        return 1;
    }

    protected int d(OutputStream outputStream) {
        e.b(outputStream, this.e);
        a(-5);
        return 4;
    }

    protected int e(OutputStream outputStream) {
        e.a(outputStream, this.f);
        a(-5);
        return 4;
    }

    protected int f(OutputStream outputStream) {
        byte[] bArr = (byte[]) this.a.get("packetData");
        if (bArr == null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                if (this.k == (short) 10) {
                    h(byteArrayOutputStream);
                } else {
                    g(byteArrayOutputStream);
                }
                Object toByteArray = byteArrayOutputStream.toByteArray();
                this.f = (long) (toByteArray.length + 10);
                this.a.put("packetData", toByteArray);
                this.a.put("packetDataLeftLength", Integer.valueOf(toByteArray.length));
                a(-4);
                return 0;
            } catch (Throwable e) {
                throw new UnexpectedDataException("packetData can not be write correctly!", e);
            }
        }
        int intValue = ((Integer) this.a.get("packetDataLeftLength")).intValue();
        if (intValue == 0) {
            a(0);
            return 0;
        }
        int a = e.a(outputStream, bArr);
        this.a.put("packetDataLeftLength", Integer.valueOf(intValue - a));
        return a;
    }

    private void g(OutputStream outputStream) {
        this.i = (short) 0;
        if (this.j.needsUpdate()) {
            this.i = (short) 1;
            this.j.update();
        }
        e.a(outputStream, this.i);
        this.g = this.j.getRandom();
        e.a(outputStream, this.g);
        if (this.i != (short) 0) {
            e.a(outputStream, this.j.getEncKey());
        }
        h(outputStream);
    }

    private void h(OutputStream outputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        e.a(byteArrayOutputStream, this.k != (short) 1 ? 0 : this.j.getInc());
        e.a(byteArrayOutputStream, this.l);
        e.a(byteArrayOutputStream, this.h);
        e.a(byteArrayOutputStream, this.m);
        byteArrayOutputStream.write(this.n);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        if (this.k == (short) 1) {
            toByteArray = this.j.encryptData(toByteArray);
        }
        e.a(outputStream, toByteArray);
    }
}
