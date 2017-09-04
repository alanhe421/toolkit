package format.epub.common.utils;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: SliceInputStream */
public class e extends l {
    private final int a;
    private final int b;

    public e(InputStream inputStream, int i, int i2) throws IOException {
        super(inputStream);
        super.skip((long) i);
        this.a = i;
        this.b = i2;
    }

    public int available() throws IOException {
        return Math.min(super.available(), Math.max((this.a + this.b) - super.a(), 0));
    }

    public void reset() throws IOException {
        super.reset();
        super.skip((long) this.a);
    }
}
