package com.tencent.upload.log.trace;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Comparator;

final class d implements Comparator<File> {
    private /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    private int a(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        try {
            SimpleDateFormat b = this.a.b();
            return b.parse(name).compareTo(b.parse(name2));
        } catch (Exception e) {
            return -1;
        }
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }
}
