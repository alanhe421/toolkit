package com.tencent.upload.log.trace;

import java.io.File;
import java.util.Comparator;

final class e implements Comparator<File> {
    private /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return this.a.d((File) obj) - this.a.d((File) obj2);
    }
}
