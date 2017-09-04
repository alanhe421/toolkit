package com.qq.reader.module.findhome.base;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import java.util.HashMap;
import java.util.Map;

class FindHomeExpandBaseVH$2 extends ReaderShortTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ boolean val$isAd;
    final /* synthetic */ int val$position;
    final /* synthetic */ int val$type;

    FindHomeExpandBaseVH$2(b bVar, int i, boolean z, int i2) {
        this.this$0 = bVar;
        this.val$type = i;
        this.val$isAd = z;
        this.val$position = i2;
    }

    public String getTaskName() {
        return "handleExposedStatistics";
    }

    public void run() {
        super.run();
        Map hashMap;
        switch (this.val$type) {
            case 0:
                if (this.val$isAd) {
                    i.a("event_B350", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(this.val$position));
                i.a("event_B389", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 1:
                if (this.val$isAd) {
                    i.a("event_B347", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(this.val$position));
                i.a("event_B357", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 2:
                if (this.val$isAd) {
                    i.a("event_B348", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(this.val$position));
                i.a("event_B358", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 3:
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(this.val$position));
                i.a("event_B349", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 4:
                if (this.val$isAd) {
                    i.a("event_B346", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(this.val$position));
                i.a("event_B356", hashMap, ReaderApplication.getApplicationImp());
                return;
            default:
                return;
        }
    }
}
