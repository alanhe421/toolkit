package format.epub.common.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: UIUtil */
public abstract class f {
    private static final Object a = new Object();
    private static ProgressDialog b;
    private static final Queue<a> c = new LinkedList();
    private static final Handler d = new Handler() {
        public void handleMessage(Message message) {
            try {
                synchronized (f.a) {
                    if (message.what != 1) {
                        if (f.c.isEmpty()) {
                            f.b.dismiss();
                            f.b = null;
                        } else {
                            f.b.setMessage(((a) f.c.peek()).b);
                        }
                        f.a.notify();
                    } else if (f.b != null) {
                        f.b.setProgress(message.arg1);
                    }
                }
            } catch (Exception e) {
            }
        }
    };

    /* compiled from: UIUtil */
    private static class a {
        final Runnable a;
        final String b;

        a(Runnable runnable, String str) {
            this.a = runnable;
            this.b = str;
        }
    }

    public static void a(int i) {
        Message message = new Message();
        message.what = 1;
        message.arg1 = i;
        d.sendMessage(message);
    }

    public static void a(Runnable runnable, Context context, String str) {
        synchronized (a) {
            c.offer(new a(runnable, str));
            if (b == null || !b.isIndeterminate()) {
                b = ProgressDialog.show(context, null, str, true, false);
                final ProgressDialog progressDialog = b;
                new Thread(new Runnable() {
                    public void run() {
                        while (f.b == progressDialog && !f.c.isEmpty()) {
                            ((a) f.c.poll()).a.run();
                            synchronized (f.a) {
                                f.d.sendEmptyMessage(0);
                                try {
                                    f.a.wait();
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    }
                }).start();
                return;
            }
        }
    }

    public static void a(Runnable runnable, Activity activity) {
        String str = "图书加载中，请稍候...";
        a(runnable, activity, "图书加载中，请稍候...");
    }
}
