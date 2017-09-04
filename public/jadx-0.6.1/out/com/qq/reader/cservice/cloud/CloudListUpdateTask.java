package com.qq.reader.cservice.cloud;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;

public class CloudListUpdateTask extends ReaderProtocolJSONTask {
    public CloudListUpdateTask(c cVar, long j) {
        super(cVar);
        long a = h.a();
        int d = l.b().d();
        if (((long) i.c().e()) < ((long) d)) {
            d = 0;
        }
        this.mUrl = e.aY + a + "&tid=" + j + H5GameGrantTicketTask.COMMON_COUNT + d;
        setTid(j);
    }

    protected boolean interuptNoConn() {
        return true;
    }

    public boolean isResponseGzip() {
        return true;
    }
}
