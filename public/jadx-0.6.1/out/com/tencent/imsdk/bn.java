package com.tencent.imsdk;

import java.io.File;
import java.util.Comparator;

final class bn implements Comparator<File> {
    bn(bm bmVar) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((File) obj).lastModified() > ((File) obj2).lastModified() ? 1 : 0;
    }
}
