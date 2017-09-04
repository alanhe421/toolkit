package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: ProGuard */
public class a extends f implements d {
    private static final Pattern k = Pattern.compile("\\A(\\S+) +(\\d+) +(.*)\r\n");
    private static final Pattern l = Pattern.compile("(.*) *: *(.*)\r\n");
    protected StringBuffer a = new StringBuffer();
    protected String b;
    public int c;
    protected String d;
    protected final HashMap e = new HashMap();
    protected int f = -1;
    protected int g = 0;
    protected int h = -1;
    public final ArrayList i = new ArrayList();
    private int m = 0;
    private g n = null;

    public int a(InputStream inputStream) {
        int i = 0;
        c();
        if (inputStream.available() != 0) {
            try {
                this.g = 0;
                while (!b()) {
                    int i2 = this.g;
                    this.g = i2 + 1;
                    if (i2 > 2) {
                        throw new InnerException("the duration of the current step is too long!");
                    }
                    switch (this.h) {
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
                    if (this.h == 0 || inputStream.available() != 0) {
                    }
                }
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c("Channel.HttpRecvPacket", "read >>> IORefusedException thrown", e);
            }
        }
        return i;
    }

    void a(int i) {
        if (this.h != i) {
            this.g = 0;
        }
        this.h = i;
    }

    protected int b(InputStream inputStream) {
        int available = inputStream.available();
        int i = 0;
        while (true) {
            int i2 = available - 1;
            if (available <= 0) {
                return i;
            }
            available = i + 1;
            i = inputStream.read();
            switch (i) {
                case -1:
                    throw new IOException("the end of stream has been reached!");
                case 10:
                    this.a.append((char) i);
                    i = this.a.length();
                    if (i >= 4 && "\r\n\r\n".contentEquals(this.a.subSequence(i - 4, i))) {
                        Matcher matcher = k.matcher(this.a.subSequence(0, this.a.length()));
                        if (matcher.find() && matcher.groupCount() == 3) {
                            this.b = matcher.group(1);
                            try {
                                this.c = Integer.parseInt(matcher.group(2).trim());
                                this.d = matcher.group(3);
                                matcher = l.matcher(this.a.subSequence(0, this.a.length()));
                                while (matcher.find() && matcher.groupCount() == 2) {
                                    this.e.put(matcher.group(1).toLowerCase(Locale.US), matcher.group(2));
                                }
                                if (this.e.containsKey("Transfer-Encoding".toLowerCase(Locale.US)) && ((String) this.e.get("Transfer-Encoding".toLowerCase(Locale.US))).equalsIgnoreCase("chunked")) {
                                    this.f = -1;
                                    a(-3);
                                    return available;
                                } else if (this.e.get("Content-Length".toLowerCase(Locale.US)) != null) {
                                    try {
                                        this.f = Integer.parseInt(((String) this.e.get("Content-Length".toLowerCase(Locale.US))).trim());
                                        a(-2);
                                        return available;
                                    } catch (Throwable e) {
                                        com.tencent.android.tpush.a.a.c(Constants.LogTag, "", e);
                                        throw new UnexpectedDataException("http Content-Length can not parsed!");
                                    }
                                } else {
                                    throw new UnexpectedDataException("http Content-Length == null && Transfer-Encoding not equal to 'chunked'!");
                                }
                            } catch (Throwable e2) {
                                com.tencent.android.tpush.a.a.c(Constants.LogTag, "", e2);
                                throw new UnexpectedDataException("http statusLine can not parsed!");
                            }
                        }
                        throw new UnexpectedDataException("http statusLine can not parsed!");
                    }
                    break;
                default:
                    this.a.append((char) i);
                    break;
            }
            i = available;
            available = i2;
        }
    }

    protected int c(InputStream inputStream) {
        int i = 0;
        while (inputStream.available() >= 0) {
            if (this.m > this.f) {
                throw new UnexpectedDataException("readBodyLength > contentLength ?!!");
            } else if (this.m != this.f) {
                if (this.n == null) {
                    this.n = new g();
                    this.n.a(this.j);
                }
                int a = this.n.a(inputStream);
                int i2 = i + a;
                this.m = a + this.m;
                if (this.n.b()) {
                    this.i.add(this.n);
                    this.n = null;
                }
                if (i == i2) {
                    return i2;
                }
                i = i2;
            } else if (this.n != null) {
                throw new InnerException("currentRecvPacket != null ?!!");
            } else {
                a(0);
                return i;
            }
        }
        return i;
    }

    protected int d(InputStream inputStream) {
        throw new InnerException("not support chunked transfer encoding!");
    }
}
