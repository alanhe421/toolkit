package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.beacon.e.a;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class i {
    private static i c = null;
    private Context a = null;
    private Map<String, FileChannel> b = null;

    private i(Context context) {
        this.a = context;
        this.b = new HashMap(5);
    }

    public static synchronized i a(Context context) {
        i iVar;
        synchronized (i.class) {
            if (c == null) {
                c = new i(context);
            }
            iVar = c;
        }
        return iVar;
    }

    public final synchronized boolean a(String str) {
        boolean z;
        if (str != null) {
            if (str.trim().length() > 0) {
                File b = b(str);
                if (b == null) {
                    z = true;
                } else {
                    try {
                        FileChannel fileChannel = (FileChannel) this.b.get(str);
                        if (fileChannel == null || !fileChannel.isOpen()) {
                            a.b(" create channel %s", str);
                            fileChannel = new FileOutputStream(b).getChannel();
                            this.b.put(str, fileChannel);
                        }
                        FileLock tryLock = fileChannel.tryLock();
                        if (tryLock != null && tryLock.isValid()) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        a.a(th);
                    }
                    z = false;
                }
            }
        }
        z = false;
        return z;
    }

    private synchronized File b(String str) {
        File file;
        try {
            file = new File(this.a.getFilesDir(), "Beacon_" + str + ".lock");
            if (!file.exists()) {
                a.b(" create lock file: %s", file.getAbsolutePath());
                file.createNewFile();
            }
        } catch (Throwable e) {
            a.a(e);
            file = null;
        }
        return file;
    }
}
