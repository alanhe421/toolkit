package org.apache.commons.compress.archivers;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.InputStream;
import org.apache.commons.compress.archivers.a.b;
import org.apache.commons.compress.archivers.c.a;
import org.apache.commons.compress.archivers.zip.o;

/* compiled from: ArchiveStreamFactory */
public class c {
    private String a = null;

    public b a(String str, InputStream inputStream) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        } else if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null.");
        } else if ("ar".equalsIgnoreCase(str)) {
            return new b(inputStream);
        } else {
            if ("zip".equalsIgnoreCase(str)) {
                if (this.a != null) {
                    return new o(inputStream, this.a);
                }
                return new o(inputStream);
            } else if ("tar".equalsIgnoreCase(str)) {
                if (this.a != null) {
                    return new org.apache.commons.compress.archivers.d.b(inputStream, this.a);
                }
                return new org.apache.commons.compress.archivers.d.b(inputStream);
            } else if (ShareConstants.DEXMODE_JAR.equalsIgnoreCase(str)) {
                return new a(inputStream);
            } else {
                if ("cpio".equalsIgnoreCase(str)) {
                    return new org.apache.commons.compress.archivers.b.b(inputStream);
                }
                if ("dump".equalsIgnoreCase(str)) {
                    return new org.apache.commons.compress.archivers.dump.b(inputStream);
                }
                throw new ArchiveException("Archiver: " + str + " not found.");
            }
        }
    }
}
