package qalsdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: IRegisterUinHandler */
public interface bc extends ax {
    void a(int i, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void a(int i, String str, byte[] bArr, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void a(int i, byte[] bArr, int i2, int i3, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void a(int i, byte[] bArr, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void a(int i, byte[] bArr, byte[] bArr2, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void b(int i, byte[] bArr, int i2, int i3, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);
}
