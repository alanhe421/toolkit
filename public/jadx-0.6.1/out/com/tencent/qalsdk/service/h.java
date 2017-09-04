package com.tencent.qalsdk.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.IInterface;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.util.QLog;
import com.tencent.util.TimeFormatterUtils;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/* compiled from: ProcessGuard */
public class h implements DeathRecipient {
    private static final int A = 0;
    private static int B = 0;
    private static int C = 3;
    public static final int a = 2;
    public static final int b = 1;
    public static final int c = 0;
    public static final int d = 3;
    public static final boolean e = true;
    private static final int h = 500;
    private static final String i = "GuardManager";
    private static final String j = "gm_history";
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 3;
    private static final int n = 4;
    private static final int o = 5;
    private static long p = 720000;
    private static long r = 0;
    private static final int y = 2;
    private static final int z = 1;
    public boolean f = false;
    public volatile boolean g = false;
    private IBinder q;
    private int s = 1;
    private long t = 0;
    private a u;
    private a v;
    private a w;
    private j x;

    /* compiled from: ProcessGuard */
    private static class a {
        public long[] a = null;
        public long[] b = null;

        public a(long[] jArr, long[] jArr2) {
            this.a = jArr;
            this.b = jArr2;
        }

        public boolean a(long j) {
            if (this.a != null) {
                int binarySearch = Arrays.binarySearch(this.a, j);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                    QLog.d(h.i, 2, j + " is the " + binarySearch + "th of " + Arrays.toString(this.a) + ", range reaches [" + (j - h.p) + ", " + (h.p + j) + "]");
                    if (binarySearch > 0 && j - this.a[binarySearch - 1] <= h.p) {
                        binarySearch--;
                    } else if (binarySearch >= this.a.length || this.a[binarySearch] - j > h.p) {
                        binarySearch = -1;
                    }
                }
                if (binarySearch >= 0) {
                    int length = this.a.length;
                    long j2 = this.b[binarySearch];
                    binarySearch = 0;
                    for (int i = 0; i < length; i++) {
                        if (this.b[i] > j2) {
                            binarySearch++;
                        }
                    }
                    QLog.d(h.i, 2, "order: " + binarySearch + ", limit: " + h.C + ", in " + Arrays.toString(this.b));
                    if (binarySearch < h.C) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void a(ObjectOutputStream objectOutputStream, boolean z) throws Throwable {
            int i = 0;
            int length = this.a != null ? this.a.length : 0;
            if (z) {
                objectOutputStream.writeLong((long) length);
            } else {
                objectOutputStream.writeByte(length);
            }
            while (i < length) {
                objectOutputStream.writeLong(this.a[i]);
                objectOutputStream.writeLong(this.b[i]);
                i++;
            }
        }

        public void a(long j, boolean z) {
            int i;
            if (this.a == null) {
                this.a = new long[1];
                this.a[0] = j;
                this.b = new long[1];
            }
            long j2 = z ? 2 : 1;
            int binarySearch = Arrays.binarySearch(this.a, j);
            String str = h.i;
            StringBuilder append = new StringBuilder().append(j).append(" is the ");
            if (binarySearch < 0) {
                i = (-binarySearch) - 1;
            } else {
                i = binarySearch;
            }
            QLog.d(str, 2, append.append(i).append("th of ").append(Arrays.toString(this.a)).toString());
            long[] jArr;
            if (binarySearch < 0) {
                long j3;
                long j4;
                int i2 = (-binarySearch) - 1;
                if (i2 > 0) {
                    j3 = j - this.a[i2 - 1];
                } else {
                    j3 = Long.MAX_VALUE;
                }
                if (i2 < this.a.length) {
                    j4 = this.a[i2] - j;
                } else {
                    j4 = Long.MAX_VALUE;
                }
                if (j3 <= h.p * 2 || j4 <= h.p * 2) {
                    if (j3 < j4) {
                        i = i2 - 1;
                    } else {
                        i = i2;
                    }
                    this.a[i] = (this.a[i] * this.b[i]) + (j * j2);
                    long[] jArr2 = this.b;
                    jArr2[i] = j2 + jArr2[i];
                    jArr = this.a;
                    jArr[i] = jArr[i] / this.b[i];
                } else {
                    binarySearch = this.a.length;
                    ArrayList arrayList = new ArrayList(binarySearch * 2);
                    for (i = 0; i < i2; i++) {
                        arrayList.add(Long.valueOf(this.a[i]));
                        arrayList.add(Long.valueOf(this.b[i]));
                    }
                    arrayList.add(Long.valueOf(j));
                    arrayList.add(Long.valueOf(j2));
                    while (i2 < binarySearch) {
                        arrayList.add(Long.valueOf(this.a[i2]));
                        arrayList.add(Long.valueOf(this.b[i2]));
                        i2++;
                    }
                    int i3;
                    if (arrayList.size() > 56) {
                        j4 = ((Long) arrayList.get(0)).longValue();
                        int size = arrayList.size() / 2;
                        i3 = 0;
                        int i4 = 1;
                        long longValue = ((Long) arrayList.get(1)).longValue();
                        long j5 = j4;
                        while (i4 < size) {
                            int i5;
                            j4 = ((Long) arrayList.get(i4 * 2)).longValue();
                            j3 = ((Long) arrayList.get((i4 * 2) + 1)).longValue();
                            if (j4 - j5 < h.p * 3) {
                                j4 *= j3;
                                j3 += longValue;
                                j4 = (j4 + (j5 * longValue)) / j3;
                                i5 = i3;
                            } else {
                                arrayList.set(i3 * 2, Long.valueOf(j5));
                                arrayList.set((i3 * 2) + 1, Long.valueOf(longValue));
                                i5 = i3 + 1;
                            }
                            i4++;
                            longValue = j3;
                            j5 = j4;
                            i3 = i5;
                        }
                        arrayList.set(i3 * 2, Long.valueOf(j5));
                        arrayList.set((i3 * 2) + 1, Long.valueOf(longValue));
                        i = i3 + 1;
                        this.a = new long[i];
                        this.b = new long[i];
                        for (i3 = 0; i3 < i; i3++) {
                            this.a[i3] = ((Long) arrayList.get(i3 * 2)).longValue();
                            this.b[i3] = ((Long) arrayList.get((i3 * 2) + 1)).longValue();
                        }
                    } else {
                        i = arrayList.size() / 2;
                        this.a = new long[i];
                        this.b = new long[i];
                        for (i3 = 0; i3 < i; i3++) {
                            this.a[i3] = ((Long) arrayList.get(i3 * 2)).longValue();
                            this.b[i3] = ((Long) arrayList.get((i3 * 2) + 1)).longValue();
                        }
                    }
                }
            } else {
                this.a[binarySearch] = (this.a[binarySearch] * this.b[binarySearch]) + (j * j2);
                long[] jArr3 = this.b;
                jArr3[binarySearch] = j2 + jArr3[binarySearch];
                jArr = this.a;
                jArr[binarySearch] = jArr[binarySearch] / this.b[binarySearch];
            }
            QLog.d(h.i, 2, Arrays.toString(this.a));
        }
    }

    public h(j jVar) {
        this.x = jVar;
    }

    public void binderDied() {
        if (QLog.isColorLevel()) {
            QLog.d(i, 2, "binderDied");
        }
        IBinder iBinder = this.q;
        if (iBinder != null) {
            iBinder.unlinkToDeath(this, 0);
            this.q = null;
            r = System.currentTimeMillis();
            this.g = false;
            long j = r - this.t;
            if (j > 0 && j < TimeFormatterUtils.ONE_DAY) {
                a("GM_AliveTime" + this.s, j, null);
            }
        }
    }

    public void a(IInterface iInterface) {
        IBinder asBinder = iInterface != null ? iInterface.asBinder() : null;
        if (QLog.isColorLevel()) {
            QLog.d(i, 2, "onAppBind with " + iInterface);
        }
        IBinder iBinder = this.q;
        if (iBinder != asBinder) {
            long currentTimeMillis = System.currentTimeMillis();
            if (iBinder != null) {
                iBinder.unlinkToDeath(this, 0);
                this.q = null;
                r = currentTimeMillis;
                this.t = currentTimeMillis;
            }
            if (asBinder != null && asBinder.isBinderAlive()) {
                try {
                    asBinder.linkToDeath(this, 0);
                    this.q = asBinder;
                    r = currentTimeMillis;
                } catch (Throwable e) {
                    if (QLog.isColorLevel()) {
                        QLog.d(i, 2, "onAppBind ", e);
                    }
                }
            }
        }
    }

    public void a(int i, long j, long j2) {
        if (QLog.isColorLevel()) {
            QLog.d(i, 2, "onEvent:" + i + ", " + j + ", " + j2);
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.t = currentTimeMillis;
        this.g = true;
        switch (i) {
            case 1:
                this.s = 2;
                return;
            case 2:
                this.s = 3;
                p = j;
                return;
            case 3:
                this.s = 4;
                return;
            case 4:
                this.s = 5;
                p = j;
                return;
            case 5:
                a(currentTimeMillis, (int) (j >> 8), (int) (255 & j), (255 & j2) == 1, (int) (j2 >> 8));
                return;
            case 7:
                a(currentTimeMillis, 0, 0);
                return;
            case 100:
                r = currentTimeMillis;
                this.s = 1;
                this.g = false;
                return;
            default:
                return;
        }
    }

    private void a(long j, int i, int i2, boolean z, int i3) {
        int i4 = 1;
        if (i3 != 0) {
            if (i3 == 2) {
                i4 = 2;
            }
            B = i4;
        } else if (B == 0) {
            if (new Random().nextInt(2) == 0) {
                i4 = 2;
            }
            B = i4;
        }
        Calendar instance = Calendar.getInstance(Locale.getDefault());
        instance.setTimeInMillis(j - com.tencent.qalsdk.base.a.ap);
        int i5 = instance.get(7);
        long j2 = (j % 3600000) + ((long) (((instance.get(11) * 60) * 60) * 1000));
        String str = "GM_StartTime";
        String str2 = B == 2 ? z ? Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE : Constants.VIA_REPORT_TYPE_SHARE_TO_QQ : z ? "01" : "00";
        a(str, j2, str2);
        C = i2;
        a(0, j2, i5);
    }

    private void a(long j, long j2, int i) {
        if (j != 0) {
            Calendar instance = Calendar.getInstance(Locale.getDefault());
            instance.setTimeInMillis(j - com.tencent.qalsdk.base.a.ap);
            i = instance.get(7);
            j2 = ((long) (((instance.get(11) * 60) * 60) * 1000)) + (j % 3600000);
        }
        if (this.w == null) {
            d();
        }
        this.w.a(j2, j == 0);
        if (i == 1 || i == 7) {
            this.v.a(j2, j == 0);
        } else {
            this.u.a(j2, j == 0);
        }
        e();
    }

    private synchronized void d() {
        Throwable e;
        long[] jArr;
        long[] jArr2;
        int i = 1;
        synchronized (this) {
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(QalService.context.openFileInput(j));
                try {
                    long[] jArr3;
                    byte b;
                    int i2;
                    int i3;
                    byte readByte = objectInputStream.readByte();
                    if (readByte != (byte) 0 && 24 % readByte == 0) {
                        jArr3 = new long[readByte];
                        long[] jArr4 = new long[readByte];
                        for (b = (byte) 0; b < readByte; b++) {
                            jArr4[b] = objectInputStream.readLong();
                            jArr3[b] = objectInputStream.readLong();
                        }
                        this.u = new a(jArr4, jArr3);
                    }
                    C = objectInputStream.readInt();
                    B = objectInputStream.readInt();
                    long readLong = objectInputStream.readLong();
                    if (readLong <= 24) {
                        i2 = (int) readLong;
                    } else {
                        b = readByte;
                    }
                    if (i2 != 0 && 24 % i2 == 0) {
                        jArr3 = new long[i2];
                        long[] jArr5 = new long[i2];
                        jArr5[0] = readLong;
                        jArr3[0] = objectInputStream.readLong();
                        for (i3 = 1; i3 < i2; i3++) {
                            jArr5[i3] = objectInputStream.readLong();
                            jArr3[i3] = objectInputStream.readLong();
                        }
                        this.v = new a(jArr5, jArr3);
                    }
                    i3 = objectInputStream.readInt();
                    if (i3 != 0 && 24 % i3 == 0) {
                        long[] jArr6 = new long[i3];
                        jArr3 = new long[i3];
                        jArr3[0] = readLong;
                        jArr6[0] = objectInputStream.readLong();
                        while (i < i3) {
                            jArr3[i] = objectInputStream.readLong();
                            jArr6[i] = objectInputStream.readLong();
                            i++;
                        }
                        this.w = new a(jArr3, jArr6);
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (Throwable e2) {
                            if (QLog.isColorLevel()) {
                                QLog.d(i, 2, "restoreStartHistory", e2);
                            }
                        }
                    }
                } catch (Throwable th) {
                    e2 = th;
                    try {
                        if (QLog.isColorLevel()) {
                            QLog.d(i, 2, "storeStartHistory", e2);
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e22) {
                                if (QLog.isColorLevel()) {
                                    QLog.d(i, 2, "restoreStartHistory", e22);
                                }
                            }
                        }
                        if (this.w == null) {
                            if (this.u != null) {
                                jArr = null;
                                jArr2 = null;
                            } else {
                                jArr2 = (long[]) this.u.a.clone();
                                jArr = (long[]) this.u.b.clone();
                            }
                            this.w = new a(jArr2, jArr);
                        }
                        if (this.u == null) {
                            this.u = new a(null, null);
                        }
                        if (this.v == null) {
                            this.v = new a(null, null);
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable e3) {
                                if (QLog.isColorLevel()) {
                                    QLog.d(i, 2, "restoreStartHistory", e3);
                                }
                            }
                        }
                        throw e22;
                    }
                }
            } catch (Throwable th3) {
                e22 = th3;
                objectInputStream = null;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw e22;
            }
            if (this.w == null) {
                if (this.u != null) {
                    jArr2 = (long[]) this.u.a.clone();
                    jArr = (long[]) this.u.b.clone();
                } else {
                    jArr = null;
                    jArr2 = null;
                }
                this.w = new a(jArr2, jArr);
            }
            if (this.u == null) {
                this.u = new a(null, null);
            }
            if (this.v == null) {
                this.v = new a(null, null);
            }
        }
    }

    private synchronized void e() {
        ObjectOutputStream objectOutputStream;
        Throwable e;
        try {
            objectOutputStream = new ObjectOutputStream(QalService.context.openFileOutput(j, 0));
            try {
                this.u.a(objectOutputStream, false);
                objectOutputStream.writeInt(C);
                objectOutputStream.writeInt(B);
                this.v.a(objectOutputStream, true);
                this.w.a(objectOutputStream, false);
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (Throwable e2) {
                        if (QLog.isColorLevel()) {
                            QLog.d(i, 2, "restoreStartHistory", e2);
                        }
                    }
                }
            } catch (Throwable th) {
                e2 = th;
                try {
                    if (QLog.isColorLevel()) {
                        QLog.d(i, 2, "restoreStartHistory", e2);
                    }
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable e22) {
                            if (QLog.isColorLevel()) {
                                QLog.d(i, 2, "restoreStartHistory", e22);
                            }
                        }
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable e3) {
                            if (QLog.isColorLevel()) {
                                QLog.d(i, 2, "restoreStartHistory", e3);
                            }
                        }
                    }
                    throw e22;
                }
            }
        } catch (Throwable th3) {
            e22 = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw e22;
        }
    }

    public boolean a(int i) {
        if (!this.f) {
            this.f = true;
            long currentTimeMillis = System.currentTimeMillis();
            long abs = Math.abs(currentTimeMillis - r);
            boolean z = this.s == 2 || this.s == 4;
            Intent intent;
            if ((i == 3 && abs > 500 && !this.g && f()) || ((i == 1 && abs > p && !f() && !z) || (i == 0 && abs > 500 && !f()))) {
                if (QLog.isColorLevel()) {
                    QLog.d(i, 2, "prestart " + i + ", " + currentTimeMillis + ", " + r + ", " + p);
                }
                intent = new Intent("com.tencent.mobileqq.broadcast.qq");
                intent.putExtra("k_start_mode", i);
                QalService.context.sendBroadcast(intent);
                r = currentTimeMillis;
                a("GM_LiteStart", 0, "" + i);
            } else if (i == 2 && abs > p && !f() && !z) {
                if (this.w == null) {
                    d();
                }
                Calendar instance = Calendar.getInstance(Locale.getDefault());
                instance.setTimeInMillis(currentTimeMillis);
                abs = ((long) (((instance.get(11) * 60) * 60) * 1000)) + (currentTimeMillis % 3600000);
                if (B == 2) {
                    int i2 = instance.get(7);
                    if (i2 == 1 || i2 == 7) {
                        z = this.v.a(abs);
                    } else {
                        z = this.u.a(abs);
                    }
                } else {
                    z = this.w.a(abs);
                }
                if (z) {
                    intent = new Intent("com.tencent.mobileqq.broadcast.qq");
                    intent.putExtra("k_start_mode", 2);
                    QalService.context.sendBroadcast(intent);
                    a("GM_PreStart", abs, B == 2 ? "1" : "0");
                    r = currentTimeMillis;
                }
            }
        }
        return true;
    }

    private boolean f() {
        IBinder iBinder = this.q;
        return iBinder != null && iBinder.isBinderAlive();
    }

    public boolean a() {
        return this.g && f();
    }

    private void a(String str, long j, String str2) {
        if (str2 != null) {
            new HashMap().put("Tag", str2);
        }
    }
}
