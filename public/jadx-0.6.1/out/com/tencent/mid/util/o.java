package com.tencent.mid.util;

import android.net.wifi.ScanResult;
import java.util.Comparator;

final class o implements Comparator<ScanResult> {
    o() {
    }

    public int a(ScanResult scanResult, ScanResult scanResult2) {
        int abs = Math.abs(scanResult.level);
        int abs2 = Math.abs(scanResult2.level);
        return abs > abs2 ? 1 : abs == abs2 ? 0 : -1;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((ScanResult) obj, (ScanResult) obj2);
    }
}
