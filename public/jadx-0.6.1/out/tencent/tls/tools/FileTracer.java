package tencent.tls.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class FileTracer implements Callback {
    private static final int MSG_FLUSH = 1024;
    private static String bufferA = "";
    private static String bufferB = "";
    private static FileTracer instance = null;
    private Context context;
    private Handler handler;
    private volatile boolean isFlushing = false;
    private HandlerThread thread;

    private FileTracer(Context context) {
        this.context = context;
        this.thread = new HandlerThread("FileTracer");
        if (this.thread != null) {
            this.thread.start();
        }
        if (this.thread.isAlive()) {
            this.handler = new Handler(this.thread.getLooper(), this);
        }
        this.handler.sendEmptyMessage(1024);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1024:
                flushBuffer();
                prepareNextFlush();
                break;
        }
        return true;
    }

    private void prepareNextFlush() {
        try {
            this.handler.sendEmptyMessageDelayed(1024, 1000);
        } catch (Throwable th) {
        }
    }

    public void flush() {
        if (this.handler.hasMessages(1024)) {
            this.handler.removeMessages(1024);
        }
        this.handler.sendEmptyMessage(1024);
    }

    private void flushBuffer() {
        if (Thread.currentThread() == this.thread && !this.isFlushing) {
            this.isFlushing = true;
            writeFile();
            this.isFlushing = false;
        }
    }

    private void writeFile() {
        if (bufferB != null && bufferB.length() > 0) {
            bufferA = bufferB;
            bufferB = "";
            Object compress = util.compress(bufferA.getBytes());
            if (compress != null && compress.length != 0) {
                Object obj = new byte[(compress.length + 4)];
                util.int32_to_buf(obj, 0, compress.length);
                System.arraycopy(compress, 0, obj, 4, compress.length);
                util.writeFile(util.getLogFileName(this.context, util.getCurrentDay()), obj);
            }
        }
    }

    public static void writeLog(Context context, String str, String str2) {
        if (context != null && str2 != null) {
            if (instance == null) {
                instance = new FileTracer(context);
            }
            synchronized (bufferB) {
                if (bufferB.length() > 4096) {
                    bufferB = "";
                }
                bufferB += util.getDate() + util.getThreadId() + util.getLineInfo(3) + util.getSdkVersion() + util.getUser(str) + str2 + "\n";
            }
        }
    }
}
