package com.qq.reader.common.readertask;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ReaderQueueDispatcher */
public class e {
    private static e a;
    private Map<String, d> b = Collections.synchronizedMap(new HashMap());

    private e() {
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    public synchronized d a(ReaderTask readerTask) {
        d dVar;
        dVar = (d) this.b.get(readerTask.getTaskName());
        if (dVar == null) {
            dVar = new d(readerTask.getTaskName());
            this.b.put(readerTask.getTaskName(), dVar);
        }
        return dVar;
    }
}
