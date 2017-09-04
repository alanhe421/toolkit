package com.qq.reader.module.comic.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qrcomic.a.i;
import com.qrcomic.entity.k;
import com.qrcomic.entity.o;
import java.lang.ref.WeakReference;
import java.util.List;

public class SectionPayTask extends ReaderProtocolJSONTask {
    private WeakReference<a> callbackRef;
    private String cid;
    private List<String> sids;

    public interface a {
        void a(o<k> oVar);
    }

    public String getTaskName() {
        return SectionPayTask.class.getSimpleName();
    }

    public SectionPayTask(String str, List<String> list, int i, int i2, a aVar) {
        this.cid = str;
        this.sids = list;
        this.callbackRef = new WeakReference(aVar);
        this.mListener = new c(this) {
            final /* synthetic */ SectionPayTask a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                o oVar = (o) new Gson().fromJson(str, new TypeToken<o<k>>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }
                }.getType());
                if (this.a.callbackRef != null) {
                    a aVar = (a) this.a.callbackRef.get();
                    if (aVar != null) {
                        aVar.a(oVar);
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.callbackRef != null) {
                    a aVar = (a) this.a.callbackRef.get();
                    if (aVar != null) {
                        aVar.a(null);
                    }
                }
            }
        };
        setUrl(i.a(this.cid, this.sids, 2, i, i2));
    }
}
