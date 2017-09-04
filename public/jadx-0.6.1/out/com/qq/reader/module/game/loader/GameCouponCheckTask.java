package com.qq.reader.module.game.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class GameCouponCheckTask extends ReaderProtocolJSONTask {
    private final String TAG = "GameCoupon";

    public GameCouponCheckTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.b + "nativepage/game/checkTickets?no=" + str;
        com.qq.reader.common.monitor.debug.c.c("GameCoupon", this.mUrl, true);
    }
}
