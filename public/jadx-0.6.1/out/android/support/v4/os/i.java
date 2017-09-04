package android.support.v4.os;

import android.os.Trace;

/* compiled from: TraceJellybeanMR2 */
class i {
    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void a() {
        Trace.endSection();
    }
}
