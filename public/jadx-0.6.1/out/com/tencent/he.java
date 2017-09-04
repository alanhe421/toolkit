package com.tencent;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.qalsdk.QALOffLineMsg;
import com.tencent.qalsdk.QALOfflinePushListener;

final class he implements QALOfflinePushListener {
    private /* synthetic */ TIMOfflinePushListener a;

    he(TIMManager tIMManager, TIMOfflinePushListener tIMOfflinePushListener) {
        this.a = tIMOfflinePushListener;
    }

    public final void onPushMsg(QALOffLineMsg qALOffLineMsg) {
        QLog.d("imsdk.TIMManager", 1, "OfflinePush|1-Begin|succ|recv offline push, user: " + qALOffLineMsg.getID());
        if (IMMsfCoreProxy.get().isOfflinePushEnabled(qALOffLineMsg.getContext(), qALOffLineMsg.getID())) {
            try {
                System.loadLibrary("_imcore_jni_gyp");
                if (this.a == null) {
                    QLog.d("imsdk.TIMManager", 1, "OfflinePush|2-callback|failed|no callback ");
                    return;
                }
                TIMOfflinePushNotification offlinePush2PushNotification = ProtobufParser.offlinePush2PushNotification(qALOffLineMsg.getID(), qALOffLineMsg.getContext(), qALOffLineMsg.getBody());
                if (offlinePush2PushNotification == null || !offlinePush2PushNotification.isValid()) {
                    QLog.d("imsdk.TIMManager", 1, "OfflinePush|2-callback|failed|invalid msg");
                    return;
                }
                this.a.handleNotification(offlinePush2PushNotification);
                QLog.d("imsdk.TIMManager", 1, "OfflinePush|2-callback|succ|callback");
                return;
            } catch (UnsatisfiedLinkError e) {
                QLog.e("imsdk.TIMManager", 1, "Couldn't load _imcore_jni_gyp|UnsatisfiedLinkError|set mode 0");
                return;
            }
        }
        QLog.d("imsdk.TIMManager", 1, "OfflinePush|2-callback|failed|offline push disabled");
    }
}
