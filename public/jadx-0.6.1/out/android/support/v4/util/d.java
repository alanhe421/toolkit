package android.support.v4.util;

import android.util.Log;
import java.io.Writer;

/* compiled from: LogWriter */
public class d extends Writer {
    private final String a;
    private StringBuilder b = new StringBuilder(128);

    public d(String str) {
        this.a = str;
    }

    public void close() {
        a();
    }

    public void flush() {
        a();
    }

    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                a();
            } else {
                this.b.append(c);
            }
        }
    }

    private void a() {
        if (this.b.length() > 0) {
            Log.d(this.a, this.b.toString());
            this.b.delete(0, this.b.length());
        }
    }
}
