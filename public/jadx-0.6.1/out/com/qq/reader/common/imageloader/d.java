package com.qq.reader.common.imageloader;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: NetworkDisablingLoader */
class d implements com.bumptech.glide.load.b.b.d<String> {
    d() {
    }

    public c<InputStream> a(final String str, int i, int i2) {
        return new c<InputStream>(this) {
            final /* synthetic */ d b;

            public /* synthetic */ Object b(Priority priority) throws Exception {
                return a(priority);
            }

            public InputStream a(Priority priority) throws Exception {
                throw new IOException("Forced Glide network failure");
            }

            public void a() {
            }

            public String b() {
                return str;
            }

            public void c() {
            }
        };
    }
}
