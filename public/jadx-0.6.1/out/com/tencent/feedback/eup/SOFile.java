package com.tencent.feedback.eup;

/* compiled from: RQDSRC */
public class SOFile {
    public final String arch;
    public final String fileName;
    public final String sha1;

    public SOFile(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            throw new RuntimeException("SOFile args should not be null!");
        }
        this.fileName = str;
        this.arch = str2;
        this.sha1 = str3;
    }
}
