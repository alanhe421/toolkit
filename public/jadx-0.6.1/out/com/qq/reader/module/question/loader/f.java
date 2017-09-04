package com.qq.reader.module.question.loader;

import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.loader.d.a;
import com.tencent.util.WeakReferenceHandler;
import java.io.File;
import java.io.FileInputStream;

/* compiled from: RecordHandleManager */
public class f extends b {
    private static volatile f a;
    private c b = new c(this.d);
    private e c = new e(this.d);
    private b d = new b();

    private f() {
    }

    public static f b() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    public void a() {
        synchronized (f.class) {
            if (this.c != null) {
                this.c.a();
            }
            a = null;
        }
    }

    public boolean a(AudioData audioData, WeakReferenceHandler weakReferenceHandler) {
        if (this.b == null) {
            return false;
        }
        File file = new File(a(audioData.a().g()));
        a(file, audioData);
        return this.b.a(file, audioData, weakReferenceHandler);
    }

    public void a(File file, AudioData audioData, a aVar) {
        if (this.c != null) {
            this.c.a(file, audioData, aVar);
        }
    }

    private void a(File file, AudioData audioData) {
        if (audioData != null) {
            switch (audioData.b().e()) {
                case 1:
                    if (com.qq.reader.module.question.b.d(audioData.b().m())) {
                        b(file, audioData);
                        return;
                    }
                    return;
                case 2:
                    if (audioData.b().n() == 0) {
                        b(file, audioData);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void b(File file, AudioData audioData) {
        audioData.b().a(null);
        if (file != null && file.exists()) {
            c.e("DELETE", "delete out of time temp record " + file.delete());
        }
        b().a(audioData);
    }

    public void a(String str, FileInputStream fileInputStream, com.qq.reader.common.imageloader.d.a.a aVar) {
        if (this.d != null) {
            this.d.a(str, fileInputStream, aVar);
        }
    }

    private boolean a(AudioData audioData) {
        boolean z = false;
        if (this.d != null) {
            try {
                z = this.d.b(audioData.b().h());
            } catch (Exception e) {
            }
        }
        return z;
    }

    public String a(String str) {
        if (this.d != null) {
            return this.d.c(str);
        }
        return null;
    }
}
