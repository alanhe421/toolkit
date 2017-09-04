package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

/* compiled from: MotionEventCompat */
public class n {
    static final e a;

    /* compiled from: MotionEventCompat */
    interface e {
        int a(MotionEvent motionEvent);

        int a(MotionEvent motionEvent, int i);

        int b(MotionEvent motionEvent);

        int b(MotionEvent motionEvent, int i);

        float c(MotionEvent motionEvent, int i);

        float d(MotionEvent motionEvent, int i);

        float e(MotionEvent motionEvent, int i);
    }

    /* compiled from: MotionEventCompat */
    static class a implements e {
        a() {
        }

        public int a(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            return -1;
        }

        public int b(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float c(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float d(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public int a(MotionEvent motionEvent) {
            return 1;
        }

        public int b(MotionEvent motionEvent) {
            return 0;
        }

        public float e(MotionEvent motionEvent, int i) {
            return 0.0f;
        }
    }

    /* compiled from: MotionEventCompat */
    static class b extends a {
        b() {
        }

        public int a(MotionEvent motionEvent, int i) {
            return o.a(motionEvent, i);
        }

        public int b(MotionEvent motionEvent, int i) {
            return o.b(motionEvent, i);
        }

        public float c(MotionEvent motionEvent, int i) {
            return o.c(motionEvent, i);
        }

        public float d(MotionEvent motionEvent, int i) {
            return o.d(motionEvent, i);
        }

        public int a(MotionEvent motionEvent) {
            return o.a(motionEvent);
        }
    }

    /* compiled from: MotionEventCompat */
    static class c extends b {
        c() {
        }

        public int b(MotionEvent motionEvent) {
            return p.a(motionEvent);
        }
    }

    /* compiled from: MotionEventCompat */
    static class d extends c {
        d() {
        }

        public float e(MotionEvent motionEvent, int i) {
            return q.a(motionEvent, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 12) {
            a = new d();
        } else if (VERSION.SDK_INT >= 9) {
            a = new c();
        } else if (VERSION.SDK_INT >= 5) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static int a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int a(MotionEvent motionEvent, int i) {
        return a.a(motionEvent, i);
    }

    public static int b(MotionEvent motionEvent, int i) {
        return a.b(motionEvent, i);
    }

    public static float c(MotionEvent motionEvent, int i) {
        return a.c(motionEvent, i);
    }

    public static float d(MotionEvent motionEvent, int i) {
        return a.d(motionEvent, i);
    }

    public static int c(MotionEvent motionEvent) {
        return a.a(motionEvent);
    }

    public static int d(MotionEvent motionEvent) {
        return a.b(motionEvent);
    }

    public static float e(MotionEvent motionEvent, int i) {
        return a.e(motionEvent, i);
    }
}
