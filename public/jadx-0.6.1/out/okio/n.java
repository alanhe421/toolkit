package okio;

/* compiled from: SegmentPool */
final class n {
    static m a;
    static long b;

    private n() {
    }

    static m a() {
        synchronized (n.class) {
            if (a != null) {
                m mVar = a;
                a = mVar.f;
                mVar.f = null;
                b -= 8192;
                return mVar;
            }
            return new m();
        }
    }

    static void a(m mVar) {
        if (mVar.f != null || mVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!mVar.d) {
            synchronized (n.class) {
                if (b + 8192 > 65536) {
                    return;
                }
                b += 8192;
                mVar.f = a;
                mVar.c = 0;
                mVar.b = 0;
                a = mVar;
            }
        }
    }
}
