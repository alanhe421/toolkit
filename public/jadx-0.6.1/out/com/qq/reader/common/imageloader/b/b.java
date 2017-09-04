package com.qq.reader.common.imageloader.b;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.b.b.d;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LocalStreamFetcher */
public class b implements d<String> {
    InputStream a;

    public b(InputStream inputStream) {
        this.a = inputStream;
    }

    public c<InputStream> a(final String str, int i, int i2) {
        return new c<InputStream>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ Object b(Priority priority) throws Exception {
                return a(priority);
            }

            public InputStream a(Priority priority) throws Exception {
                return this.b.a;
            }

            public void a() {
                try {
                    this.b.a.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public String b() {
                return str;
            }

            public void c() {
            }
        };
    }
}
