package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: tlv_t145 */
public class ap extends b {
    public int a;

    public ap() {
        this.a = 0;
        this.h = ErrorCode.THROWABLE_INITX5CORE;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.a = bArr.length;
        Object obj = new byte[this.a];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        int length = bArr.length + 0;
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
