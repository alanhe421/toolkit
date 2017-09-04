package com.qq.reader.cservice.cloud;

import com.pay.http.APPluginErrorCode;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.framework.a.b;
import java.util.List;

/* compiled from: CloudSynNoteManager */
public class e {
    private static e a = null;
    private c b = null;

    private e() {
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(List<b> list) {
        g.a().a(new CloudNoteSynSaveTask(new c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, list));
    }

    private void a(ReaderProtocolTask readerProtocolTask, String str) {
        Object obj = null;
        d parseNoteSaveReceiveData = CloudNoteSynSaveTask.parseNoteSaveReceiveData(str);
        if (parseNoteSaveReceiveData != null) {
            if (parseNoteSaveReceiveData.b >= 0) {
                Object obj2 = null;
                for (b bVar : parseNoteSaveReceiveData.c) {
                    if (bVar.i() == 0) {
                        s.a().a(bVar.h(), bVar.a(), (long) bVar.d(), bVar.g(), true);
                        obj = obj2;
                    } else if (bVar.i() == 1000) {
                        if (s.a().b(bVar.h(), bVar.a()).b() > bVar.b()) {
                            obj2 = 1;
                        } else if (bVar.a(s.a())) {
                            s.a().a(bVar.h(), bVar.a(), (long) bVar.d(), 0, true);
                        }
                        obj = obj2;
                    } else {
                        if (bVar.i() == APPluginErrorCode.ERROR_APP_SYSTEM) {
                            s.a().a(bVar.h(), bVar.a(), 0, System.currentTimeMillis(), true);
                        }
                        obj = obj2;
                    }
                    obj2 = obj;
                }
                obj = obj2;
            } else if (parseNoteSaveReceiveData.b == -1000) {
                obj = 1;
            }
        }
        if (this.b != null && r0 != null) {
            this.b.saveDone(parseNoteSaveReceiveData);
        }
    }
}
