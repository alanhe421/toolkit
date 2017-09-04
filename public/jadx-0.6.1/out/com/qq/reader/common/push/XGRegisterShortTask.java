package com.qq.reader.common.push;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.f;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPro;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.HashMap;
import java.util.Map;

public class XGRegisterShortTask extends ReaderShortTask {
    public static boolean hasStartXG = false;

    public XGRegisterShortTask() {
        setFailedType(1);
        setPriority(3);
    }

    public void run() {
        super.run();
        if (!hasStartXG) {
            final Context applicationImp = ReaderApplication.getApplicationImp();
            XGPushConfig.enableDebug(applicationImp, false);
            final Map hashMap = new HashMap();
            try {
                String str;
                String str2;
                XGPro.enableXGPro(applicationImp, true);
                String R = d.R(applicationImp);
                if (R == null || R.length() <= 0) {
                    R = d.h(applicationImp);
                    str = "qimei";
                    str2 = "event_XgRegistFaildByQIMEI";
                } else {
                    str = "qq";
                    str2 = "event_XgRegistFaildByQQ";
                }
                c.c(Constants.LogTag, "registerPush", false);
                XGPushManager.registerPush(ReaderApplication.getApplicationImp(), R, new XGIOperateCallback(this) {
                    final /* synthetic */ XGRegisterShortTask f;

                    public void onSuccess(Object obj, int i) {
                        c.c(Constants.LogTag, "start XG onSuccess code = " + i, false);
                        XGRegisterShortTask.hasStartXG = true;
                    }

                    public void onFail(Object obj, int i, String str) {
                        if (this.f.isReachMaxAutoFailedTime()) {
                            hashMap.put(str, R);
                            hashMap.put("code", String.valueOf(i));
                            i.a(str2, hashMap, applicationImp);
                            c.c(Constants.LogTag, "start XG onFailure code = " + i, true);
                            return;
                        }
                        this.f.isFailedTask = true;
                        c.c(Constants.LogTag, "start XG onFailure code = " + i, true);
                        this.f.setDelayTime(TracerConfig.LOG_FLUSH_DURATION);
                        f.b().a(this.f);
                        c.e(Constants.LogTag, "start XG do retry");
                    }
                });
            } catch (Exception e) {
                hashMap.put("error", e.getMessage());
                i.a("event_registxg_error", hashMap, applicationImp);
                c.c(Constants.LogTag, "start XG : " + e.toString(), true);
            }
        }
    }
}
