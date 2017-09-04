package oicq.wlogin_sdk.b;

import java.nio.ByteBuffer;

/* compiled from: tlv_t508 */
public class cp extends b {
    public static boolean a = true;
    public static int i = 1000;
    public static byte[] j = new byte[0];

    public cp() {
        this.h = 1288;
    }

    public Boolean f() {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(this.g);
            wrap.position(this.e);
            a = wrap.get() == (byte) 1;
            i = wrap.getInt();
            j = new byte[wrap.getShort()];
            wrap.get(j);
            if (i == 0) {
                i = 1000;
            }
            if (j == null) {
                j = new byte[0];
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            Boolean valueOf = Boolean.valueOf(false);
            if (i == 0) {
                i = 1000;
            }
            if (j != null) {
                return valueOf;
            }
            j = new byte[0];
            return valueOf;
        } catch (Throwable th) {
            if (i == 0) {
                i = 1000;
            }
            if (j == null) {
                j = new byte[0];
            }
        }
    }
}
