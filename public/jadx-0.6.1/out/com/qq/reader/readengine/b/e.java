package com.qq.reader.readengine.b;

import com.qq.reader.readengine.fileparse.d;
import com.qq.taf.jce.JceStruct;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: TxtByteReader */
public class e extends a {
    private RandomAccessFile h = null;
    private long i;
    private byte[] j = new byte[153600];

    public e(d dVar, String str) {
        super(dVar, str);
        try {
            this.h = new RandomAccessFile(dVar.t().getBookPath(), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (this.h != null) {
                try {
                    this.h.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } finally {
                    this.h = null;
                }
            }
        }
    }

    public byte a() throws IOException {
        if (this.h == null) {
            return (byte) -1;
        }
        int min;
        if (this.a >= this.d) {
            min = (int) Math.min((long) this.j.length, this.h.length() - this.i);
            if (min == 0) {
                return (byte) -1;
            }
            this.h.read(this.j, 0, min);
            this.i += (long) min;
            this.d = min;
            this.a = 0;
        }
        byte[] bArr = this.j;
        min = this.a;
        this.a = min + 1;
        byte b = bArr[min];
        this.e++;
        return b;
    }

    public long c() {
        return this.e;
    }

    public void d() {
        if (this.h != null) {
            try {
                this.h.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.h = null;
            }
        }
    }

    public long a(long j) {
        long max;
        Exception e;
        Throwable th;
        long filePointer;
        if (this.f.equals("UTF-16BE") || this.f.equals("UTF-16LE")) {
            max = Math.max(j - 40, 0);
        } else if (this.f.equals("UTF-8")) {
            max = Math.max(j - 60, 0);
            if (max > 0) {
                try {
                    r2 = this.h.getFilePointer();
                    try {
                        max = b(max, 60);
                        try {
                            this.h.seek(r2);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            try {
                                this.h.seek(r2);
                                max = j;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                max = j;
                            }
                            if (max < 0) {
                                max = 0;
                            }
                            return Math.min(max, j);
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                this.h.seek(r2);
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                            throw th;
                        }
                    }
                } catch (Exception e6) {
                    e = e6;
                    r2 = 0;
                    e.printStackTrace();
                    this.h.seek(r2);
                    max = j;
                    if (max < 0) {
                        max = 0;
                    }
                    return Math.min(max, j);
                } catch (Throwable th3) {
                    th = th3;
                    r2 = 0;
                    this.h.seek(r2);
                    throw th;
                }
            }
        } else {
            long max2 = Math.max(j - 40, 0);
            if (max2 > 0) {
                try {
                    filePointer = this.h.getFilePointer();
                    try {
                        r2 = a(max2, 20);
                        this.h.seek(r2);
                        max2 = a(r2, max2, true);
                        try {
                            this.h.seek(filePointer);
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    } catch (Exception e7) {
                        e = e7;
                        r2 = filePointer;
                        try {
                            e.printStackTrace();
                            try {
                                this.h.seek(r2);
                                max2 = j;
                            } catch (IOException e422) {
                                e422.printStackTrace();
                                max2 = j;
                            }
                            max = max2;
                            if (max < 0) {
                                max = 0;
                            }
                            return Math.min(max, j);
                        } catch (Throwable th4) {
                            th = th4;
                            filePointer = r2;
                            try {
                                this.h.seek(filePointer);
                            } catch (IOException e52) {
                                e52.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        this.h.seek(filePointer);
                        throw th;
                    }
                } catch (Exception e8) {
                    e = e8;
                    r2 = 0;
                    e.printStackTrace();
                    this.h.seek(r2);
                    max2 = j;
                    max = max2;
                    if (max < 0) {
                        max = 0;
                    }
                    return Math.min(max, j);
                } catch (Throwable th6) {
                    th = th6;
                    filePointer = 0;
                    this.h.seek(filePointer);
                    throw th;
                }
            }
            max = max2;
        }
        if (max < 0) {
            max = 0;
        }
        return Math.min(max, j);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long b(long r14) {
        /*
        r13 = this;
        r4 = 40;
        r2 = 0;
        r10 = 1;
        r0 = r13.f;
        r1 = "UTF-16BE";
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x001c;
    L_0x0011:
        r0 = r13.f;
        r1 = "UTF-16LE";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x003b;
    L_0x001c:
        r0 = r14 + r4;
        r0 = r0 - r10;
        r2 = r13.b();
        r2 = r2 - r10;
        r0 = java.lang.Math.min(r0, r2);
    L_0x0028:
        r2 = r13.b();
        r2 = r2 - r10;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0036;
    L_0x0031:
        r0 = r13.b();
        r0 = r0 - r10;
    L_0x0036:
        r0 = java.lang.Math.max(r14, r0);
        return r0;
    L_0x003b:
        r0 = r13.f;
        r1 = "UTF-8";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0090;
    L_0x0046:
        r0 = 60;
        r0 = r0 + r14;
        r4 = r13.b();
        r4 = r4 - r10;
        r0 = java.lang.Math.min(r0, r4);
        r4 = r13.b();
        r4 = r4 - r10;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x0028;
    L_0x005b:
        r4 = r13.h;	 Catch:{ Exception -> 0x0073 }
        r2 = r4.getFilePointer();	 Catch:{ Exception -> 0x0073 }
        r4 = 20;
        r0 = r13.b(r0, r4);	 Catch:{ Exception -> 0x0073 }
        r0 = r0 - r10;
        r4 = r13.h;	 Catch:{ IOException -> 0x006e }
        r4.seek(r2);	 Catch:{ IOException -> 0x006e }
        goto L_0x0028;
    L_0x006e:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0028;
    L_0x0073:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0084 }
        r0 = r13.h;	 Catch:{ IOException -> 0x007e }
        r0.seek(r2);	 Catch:{ IOException -> 0x007e }
        r0 = r14;
        goto L_0x0028;
    L_0x007e:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r14;
        goto L_0x0028;
    L_0x0084:
        r0 = move-exception;
        r1 = r13.h;	 Catch:{ IOException -> 0x008b }
        r1.seek(r2);	 Catch:{ IOException -> 0x008b }
    L_0x008a:
        throw r0;
    L_0x008b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x008a;
    L_0x0090:
        r0 = r14 + r4;
        r4 = r13.b();
        r4 = r4 - r10;
        r4 = java.lang.Math.min(r0, r4);
        r0 = r13.b();
        r0 = r0 - r10;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x00bb;
    L_0x00a4:
        r0 = r13.h;	 Catch:{ Exception -> 0x00c3 }
        r8 = r0.getFilePointer();	 Catch:{ Exception -> 0x00c3 }
        r0 = r13.h;	 Catch:{ Exception -> 0x00e3, all -> 0x00e1 }
        r0.seek(r14);	 Catch:{ Exception -> 0x00e3, all -> 0x00e1 }
        r6 = 0;
        r1 = r13;
        r2 = r14;
        r4 = r1.a(r2, r4, r6);	 Catch:{ Exception -> 0x00e3, all -> 0x00e1 }
        r0 = r13.h;	 Catch:{ IOException -> 0x00be }
        r0.seek(r8);	 Catch:{ IOException -> 0x00be }
    L_0x00bb:
        r0 = r4;
        goto L_0x0028;
    L_0x00be:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00bb;
    L_0x00c3:
        r0 = move-exception;
    L_0x00c4:
        r0.printStackTrace();	 Catch:{ all -> 0x00d4 }
        r0 = r13.h;	 Catch:{ IOException -> 0x00ce }
        r0.seek(r2);	 Catch:{ IOException -> 0x00ce }
        r4 = r14;
        goto L_0x00bb;
    L_0x00ce:
        r0 = move-exception;
        r0.printStackTrace();
        r4 = r14;
        goto L_0x00bb;
    L_0x00d4:
        r0 = move-exception;
        r8 = r2;
    L_0x00d6:
        r1 = r13.h;	 Catch:{ IOException -> 0x00dc }
        r1.seek(r8);	 Catch:{ IOException -> 0x00dc }
    L_0x00db:
        throw r0;
    L_0x00dc:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00db;
    L_0x00e1:
        r0 = move-exception;
        goto L_0x00d6;
    L_0x00e3:
        r0 = move-exception;
        r2 = r8;
        goto L_0x00c4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.readengine.b.e.b(long):long");
    }

    private long a(long j, long j2, boolean z) throws IOException {
        long j3 = j;
        while (j3 < j2) {
            int read = this.h.read();
            j3++;
            if (read > Opcodes.INT_TO_LONG) {
                this.h.skipBytes(1);
                j3++;
            } else if (read < 0) {
                return j3 - 1;
            }
        }
        if (z) {
            return j3;
        }
        return j3 - 1;
    }

    private long a(long j, int i) throws IOException {
        byte[] bArr = new byte[i];
        while (j > 0) {
            long j2 = j - ((long) i);
            long j3 = j2 > 0 ? j2 : 0;
            if (j3 <= 0) {
                this.h.seek(0);
                return 0;
            }
            this.h.seek(j3);
            this.h.read(bArr);
            int length = bArr.length - 1;
            while (length > 0) {
                if (bArr[length] >= (byte) 0 && bArr[length] < (byte) 64) {
                    return (((long) length) + j3) + 1;
                }
                length--;
            }
            j = j3;
        }
        return 0;
    }

    private long b(long j, int i) throws IOException {
        this.h.seek(j);
        byte[] bArr = new byte[i];
        this.h.read(bArr);
        int i2 = 0;
        while (true) {
            byte b = bArr[i2];
            i2++;
            if (!(b == JceStruct.SIMPLE_LIST || b == (byte) 10)) {
                if ((b & Opcodes.AND_LONG_2ADDR) != 128 || i2 >= 6) {
                }
            }
        }
        return ((long) (i2 - 1)) + j;
    }

    public String a(long j, long j2, long j3) {
        String replaceAll;
        Exception e;
        byte[] bArr = new byte[(((int) (j2 - j)) + 1)];
        long j4 = 0;
        this.g = -1;
        try {
            j4 = this.h.getFilePointer();
            this.h.seek(j);
            this.h.read(bArr);
            replaceAll = new String(bArr, this.f).replaceAll("\r|\n| ", " ");
            try {
                this.g = new String(bArr, 0, Math.max((int) (j3 - j), 0), this.f).length();
                try {
                    this.h.seek(j4);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    return replaceAll;
                } finally {
                    try {
                        this.h.seek(j4);
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            }
        } catch (Exception e4) {
            Exception exception = e4;
            replaceAll = null;
            e = exception;
            e.printStackTrace();
            return replaceAll;
        }
        return replaceAll;
    }
}
