package oicq.wlogin_sdk.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

/* compiled from: FileTracer */
public class b implements Callback {
    private static b a = null;
    private static final Object c = new Object();
    private static StringBuilder d = new StringBuilder();
    private static StringBuilder e = new StringBuilder();
    private volatile boolean b = false;
    private Context f;
    private HandlerThread g;
    private Handler h;

    private b(Context context) {
        this.f = context;
        this.g = new HandlerThread("FileTracer");
        this.g.start();
        if (this.g.isAlive()) {
            this.h = new Handler(this.g.getLooper(), this);
        }
        this.h.sendEmptyMessage(1024);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1024:
                b();
                a();
                break;
        }
        return true;
    }

    private void a() {
        try {
            this.h.sendEmptyMessageDelayed(1024, 2000);
        } catch (Throwable th) {
        }
    }

    private void b() {
        if (Thread.currentThread() == this.g && !this.b) {
            this.b = true;
            c();
            this.b = false;
        }
    }

    private void c() {
        if (e != null && e.length() != 0) {
            d = e;
            e = new StringBuilder();
            Object compress = util.compress(d.toString().getBytes());
            if (compress != null && compress.length != 0) {
                Object obj = new byte[(compress.length + 4)];
                util.int32_to_buf(obj, 0, compress.length);
                System.arraycopy(compress, 0, obj, 4, compress.length);
                util.writeFile(util.getLogFileName(this.f, util.getCurrentDay()), obj);
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        if (context != null && str2 != null) {
            if (a == null) {
                a = new b(context);
            }
            synchronized (c) {
                if (e.length() > util.MAX_CONTENT_SIZE) {
                    e.delete(0, e.length() / 2);
                    e.append("log has been cut len ").append(e.length() / 2).append("\n");
                }
                try {
                    e.append(util.getDate()).append(util.getThreadId()).append(util.getLineInfo(3)).append(util.getSdkVersion()).append(util.getUser(str)).append(str2).append("\n");
                } catch (Exception e) {
                    e = new StringBuilder();
                }
            }
        }
    }
}
