package qalsdk;

import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.util.MsfSocketInputBuffer;

/* compiled from: SsoProtocolDataWrapper */
public class u implements i {
    j a;

    public u(j jVar) {
        this.a = jVar;
    }

    public void a(MsfSocketInputBuffer msfSocketInputBuffer) throws Exception {
        byte[] bArr = new byte[msfSocketInputBuffer.getBufferlen()];
        System.arraycopy(msfSocketInputBuffer.getBuffer(), 0, bArr, 0, bArr.length);
        this.a.d.c(bArr);
    }

    public byte[] a(c cVar, String str, String str2, byte[] bArr) {
        return bArr;
    }
}
