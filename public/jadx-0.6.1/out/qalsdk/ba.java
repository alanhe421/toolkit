package qalsdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: INotifyHandler */
public interface ba extends ax {
    void a(long j, FromServiceMsg fromServiceMsg);

    void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);

    void b(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg);
}
