package com.qqcomic.bitmaphelper;

import java.io.Closeable;

/* compiled from: ClosableBitmap */
public abstract class a implements Closeable {
    public abstract boolean a();

    public abstract void close();

    protected void finalize() throws Throwable {
        if (!a()) {
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
