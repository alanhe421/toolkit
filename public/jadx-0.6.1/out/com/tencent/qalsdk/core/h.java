package com.tencent.qalsdk.core;

import java.io.File;
import java.util.Comparator;

/* compiled from: LogManager */
class h implements Comparator<File> {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        if (file.lastModified() > file2.lastModified()) {
            return 1;
        }
        return 0;
    }
}
