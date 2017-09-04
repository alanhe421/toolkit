package com.qq.reader.module.readpage;

import android.content.Context;
import com.qq.reader.readengine.kernel.a.c;
import com.qq.reader.readengine.kernel.b;
import format.epub.view.a;

/* compiled from: ReaderPaintFactory */
public class v {
    public static j a(Context context, b bVar) {
        if (bVar instanceof c) {
            return new a(context, bVar);
        }
        return new k(context, bVar);
    }
}
