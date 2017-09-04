package oicq.wlogin_sdk.tools;

import java.util.Map;
import oicq.wlogin_sdk.b.b;

/* compiled from: RegTLVParser */
public class c {
    public static int a(int i, byte[] bArr, int i2, int i3, Map<Integer, b> map) {
        if (bArr.length != i2 + i3) {
            return -1;
        }
        for (int i4 = 0; i4 < i && i3 > 0; i4++) {
            int buf_to_int8 = util.buf_to_int8(bArr, i2);
            int i5 = i2 + 1;
            int i6 = i3 - 1;
            int buf_to_int82 = util.buf_to_int8(bArr, i5);
            i5++;
            i6--;
            if (i6 < buf_to_int82) {
                return -2;
            }
            b bVar = new b(buf_to_int8);
            bVar.a(bArr, i5, buf_to_int82);
            map.put(new Integer(buf_to_int8), bVar);
            i2 = i5 + buf_to_int82;
            i3 = i6 - buf_to_int82;
        }
        return 0;
    }
}
