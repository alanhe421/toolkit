package com.qq.reader.module.feed.loader;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class CardNavigationBarLoader {
    private Reference<Handler> a;
    private int b = 0;

    class RequestReaderProtocolJSONTask extends ReaderProtocolJSONTask {
        public RequestReaderProtocolJSONTask(String str, c cVar) {
            super(cVar);
            setUrl(str);
        }
    }

    public void a(Handler handler) {
        this.a = new WeakReference(handler);
    }

    public <T> void a(String str, int i, Handler handler, final a<T> aVar) {
        g.a().a(new RequestReaderProtocolJSONTask(str, new c(this) {
            final /* synthetic */ CardNavigationBarLoader b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (aVar != null) {
                    Object a = aVar.a(str);
                    if (this.b.a != null) {
                        Handler handler = (Handler) this.b.a.get();
                        if (handler != null) {
                            Message obtainMessage = handler.obtainMessage();
                            obtainMessage.what = this.b.b;
                            obtainMessage.obj = a;
                            obtainMessage.arg1 = 1;
                            handler.sendMessage(obtainMessage);
                        }
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.b.a != null) {
                    Handler handler = (Handler) this.b.a.get();
                    if (handler != null) {
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.what = this.b.b;
                        obtainMessage.arg1 = 0;
                        handler.sendMessage(obtainMessage);
                    }
                }
            }
        }));
        if (handler != null) {
            a(handler);
        }
        this.b = i;
    }
}
