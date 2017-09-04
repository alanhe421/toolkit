package com.tencent.feedback.eup.jni;

import java.io.File;
import java.io.FilenameFilter;

/* compiled from: RQDSRC */
class a$1 implements FilenameFilter {
    a$1(a aVar) {
    }

    public final boolean accept(File file, String str) {
        if (str.endsWith("so")) {
            return true;
        }
        return false;
    }
}
