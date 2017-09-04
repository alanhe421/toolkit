package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: tlv_t143 */
public class an extends b {
    public int a;

    public an() {
        this.a = 0;
        this.h = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03;
    }

    public byte[] a(byte[] bArr) {
        this.a = bArr.length;
        Object obj = new byte[this.a];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
