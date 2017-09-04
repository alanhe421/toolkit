package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.channel.c.e;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

/* compiled from: ProGuard */
public class g extends i implements d {
    protected HashMap a = new HashMap(4);
    protected int b = 0;
    protected int c = -1;

    public synchronized void d() {
        super.d();
        this.a.clear();
    }

    public int a(InputStream inputStream) {
        int i = 0;
        c();
        if (inputStream.available() != 0) {
            try {
                this.b = 0;
                while (!b()) {
                    int i2 = this.b;
                    this.b = i2 + 1;
                    if (i2 > 2) {
                        throw new InnerException("the duration of the current step is too long!");
                    }
                    switch (this.c) {
                        case -7:
                            i += h(inputStream);
                            break;
                        case -6:
                            i += g(inputStream);
                            break;
                        case -5:
                            i += f(inputStream);
                            break;
                        case -4:
                            i += e(inputStream);
                            break;
                        case -3:
                            i += d(inputStream);
                            break;
                        case -2:
                            i += c(inputStream);
                            break;
                        case -1:
                            i += b(inputStream);
                            break;
                        case 0:
                            d();
                            break;
                        default:
                            throw new InnerException("illegal step value!");
                    }
                    if (this.c == 0 || inputStream.available() != 0) {
                    }
                }
            } catch (Throwable e) {
                a.c("Channel.RecvPacket", "read >>> IORefusedException thrown", e);
            }
        }
        return i;
    }

    void a(int i) {
        if (this.c != i) {
            this.b = 0;
        }
        this.c = i;
    }

    protected int b(InputStream inputStream) {
        this.d = e.a(inputStream);
        if (this.d != (short) 80) {
            throw new UnexpectedDataException("soh: " + this.d + " != TPNS_SOH");
        }
        a(-2);
        return 1;
    }

    protected int c(InputStream inputStream) {
        this.k = e.a(inputStream);
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

    protected int d(InputStream inputStream) {
        this.e = e.c(inputStream);
        a(-4);
        return 4;
    }

    protected int e(InputStream inputStream) {
        this.f = e.b(inputStream);
        this.f -= 10;
        if (this.f > 10485760 || this.f < 0) {
            throw new UnexpectedDataException("packetLength: " + this.f);
        }
        if (this.k == (short) 1) {
            a(-5);
        } else {
            a(-7);
        }
        return 4;
    }

    protected int f(InputStream inputStream) {
        this.f--;
        this.i = e.a(inputStream);
        if (this.i != (short) 0) {
            throw new UnexpectedDataException("negotiateSecurity: " + this.i + " != 0");
        }
        a(-6);
        return 1;
    }

    protected int g(InputStream inputStream) {
        this.f -= 4;
        this.g = e.b(inputStream);
        if (this.g != this.j.getRandom()) {
            throw new UnexpectedDataException("unexpected random: " + this.g);
        }
        a(-7);
        return 4;
    }

    protected int h(InputStream inputStream) {
        byte[] bArr = (byte[]) this.a.get("contentData");
        if (bArr == null) {
            if (this.f < 0) {
                throw new UnexpectedDataException("unexpected packetLength: " + this.f + " < 0");
            }
            bArr = new byte[((int) this.f)];
            this.a.put("contentData", bArr);
            this.a.put("contentDataLeftLength", Integer.valueOf(bArr.length));
        }
        byte[] bArr2 = bArr;
        int intValue = ((Integer) this.a.get("contentDataLeftLength")).intValue();
        int a = e.a(inputStream, bArr2, bArr2.length - intValue);
        intValue -= a;
        this.a.put("contentDataLeftLength", Integer.valueOf(intValue));
        if (intValue == 0) {
            if (this.k == (short) 1) {
                bArr2 = this.j.decryptData(bArr2);
            }
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
            try {
                long b = e.b(byteArrayInputStream);
                if (this.k == (short) 1) {
                    this.j.checkRemoteInc(b);
                }
                this.l = e.a(byteArrayInputStream);
                this.h = e.a(byteArrayInputStream);
                this.m = e.a(byteArrayInputStream);
                if (byteArrayInputStream.available() > 0) {
                    this.n = new byte[byteArrayInputStream.available()];
                    e.a(byteArrayInputStream, this.n, 0);
                }
                a(0);
            } catch (Throwable e) {
                throw new UnexpectedDataException("contentData can not be read correctly!", e);
            }
        }
        return a;
    }
}
