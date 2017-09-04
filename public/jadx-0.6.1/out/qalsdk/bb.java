package qalsdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: IPushHandler */
public interface bb extends ax {
    void a();

    void a(FromServiceMsg fromServiceMsg);

    void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void b(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void c(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void d(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);
}
