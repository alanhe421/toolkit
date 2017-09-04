package com.qq.reader.cservice.adv;

import com.qq.reader.cservice.adv.e.a;
import java.util.List;
import java.util.Random;

/* compiled from: AdvertisementTraveller */
public class d {
    private static int a = -1;
    private static int b = 0;
    private static long c = -1;
    private static Random d = new Random();

    public static a a(long j, int i, boolean z) {
        int i2 = 0;
        a a = e.a().a(String.valueOf(j));
        if (a == null) {
            return null;
        }
        int b = a.b();
        if (c == -1 || !((c == -1 || c == j) && a == b)) {
            b = 0;
            a = a.b();
            d = new Random();
        }
        c = j;
        if (i % a == b) {
            if (z) {
                b++;
                if (b % a == 0) {
                    b = 0;
                }
            } else {
                i2 = 1;
            }
        }
        if (i2 == 0) {
            return null;
        }
        List a2 = a.a();
        if (a2 == null) {
            return null;
        }
        int size = a2.size();
        if (size > 0) {
            return (a) a2.get(d.nextInt(size));
        }
        return null;
    }
}
