package com.qq.reader.module.c;

import android.util.SparseArray;
import com.qq.reader.common.utils.q;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;

/* compiled from: TimerCounterHandler */
public class a {
    private static SparseArray<q> a = new SparseArray();

    public static void a(int i) {
        q b = b(i);
        if (b != null) {
            b.cancel();
            b.a(false);
            a.remove(i);
        }
    }

    public static q b(int i) {
        return (q) a.get(i);
    }

    public static void a(int i, int i2, com.qq.reader.common.utils.q.a aVar, int i3) {
        if (i2 > 0) {
            if (b(i) != null) {
                a(i);
            }
            q qVar = new q((long) i2, 1000, i3);
            qVar.a(aVar);
            qVar.start();
            qVar.a(true);
            a(i, qVar);
            return;
        }
        a(i);
    }

    private static void a(int i, q qVar) {
        a.put(i, qVar);
    }

    public static String a(long j) {
        long j2 = j / BuglyBroadcastRecevier.UPLOADLIMITED;
        long j3 = (j % BuglyBroadcastRecevier.UPLOADLIMITED) / 1000;
        return (j2 < 10 ? "0" + j2 : String.valueOf(j2)) + ":" + (j3 < 10 ? "0" + j3 : String.valueOf(j3));
    }
}
