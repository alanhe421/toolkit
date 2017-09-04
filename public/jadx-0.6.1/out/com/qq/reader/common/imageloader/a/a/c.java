package com.qq.reader.common.imageloader.a.a;

import android.content.Context;
import com.bumptech.glide.load.engine.a.d;
import com.bumptech.glide.load.engine.a.d.a;
import com.qq.reader.common.imageloader.d.b;
import java.io.File;

/* compiled from: ReaderDiskCacheFactory */
public class c extends d {

    /* compiled from: ReaderDiskCacheFactory */
    class AnonymousClass1 implements a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        AnonymousClass1(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public File a() {
            File a = b.a(this.a, this.b);
            if (a == null) {
                return null;
            }
            return a;
        }
    }

    public c(Context context, int i) {
        this(context, a.d, i);
    }

    public c(Context context, String str, int i) {
        super(new AnonymousClass1(context, str), i);
    }
}
