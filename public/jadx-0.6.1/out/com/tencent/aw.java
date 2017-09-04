package com.tencent;

import android.os.Process;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

final class aw implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ IMCoreNotify b;

    aw(IMCoreNotify iMCoreNotify, ArrayList arrayList) {
        this.b = iMCoreNotify;
        this.a = arrayList;
    }

    public final void run() {
        Iterator it;
        Set<TIMMessageListener> messageListeners = TIMManager.getInstanceById(IMCoreNotify.a(this.b)).getMessageListeners();
        QLog.d("IMCoreNotify", 1, "listeners size: " + messageListeners.size() + "|msg size: " + this.a.size() + "|pid:" + Process.myPid());
        if (messageListeners.size() == 0) {
            QLog.i("IMCoreNotify", 1, "RecvMsg|4-Callback|Fail|user not set onNewMessage cb");
        } else {
            for (TIMMessageListener tIMMessageListener : messageListeners) {
                if (tIMMessageListener != null && tIMMessageListener.onNewMessages(this.a)) {
                    break;
                }
            }
            it = this.a.iterator();
            while (it.hasNext()) {
                TIMMessage tIMMessage = (TIMMessage) it.next();
                QLog.i("IMCoreNotify", 1, "RecvMsg|4-Callback|Succ|type=" + tIMMessage.getConversation().getType() + ", sid=" + tIMMessage.getConversation().getPeer() + ", msgid=" + tIMMessage.getMsgId() + ", seq=" + tIMMessage.getMsg().seq() + ", rand=" + tIMMessage.getMsg().rand() + ", time=" + tIMMessage.getMsg().time() + ", isRead=" + tIMMessage.getMsg().isRead());
            }
        }
        if (messageListeners.size() == 0 && IMMsfCoreProxy.get().isOfflinePushEnabled(IMMsfCoreProxy.get().getContext(), IMCoreNotify.a(this.b))) {
            TIMOfflinePushListener offlinePushListener = TIMManager.getInstanceById(IMCoreNotify.a(this.b)).getOfflinePushListener();
            if (offlinePushListener != null) {
                it = this.a.iterator();
                while (it.hasNext()) {
                    TIMOfflinePushNotification tIMOfflinePushNotification = new TIMOfflinePushNotification(IMMsfCoreProxy.get().getContext(), (TIMMessage) it.next());
                    if (tIMOfflinePushNotification.isValid()) {
                        tIMOfflinePushNotification.setIdentifier(IMCoreNotify.a(this.b));
                        offlinePushListener.handleNotification(tIMOfflinePushNotification);
                    }
                }
            }
        }
    }
}
