package format.archive;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipException;
import org.apache.a.a.g;

/* compiled from: QQReaderZipFile */
public class b extends g {
    public b(String str, String str2) throws IOException {
        super(str, str2);
    }

    public b(File file, String str) throws IOException {
        super(file, str);
    }

    protected String a(byte[] bArr) throws ZipException {
        String a;
        int a2 = com.qq.reader.common.utils.c.b.a(bArr);
        if (a2 != 0) {
            a = com.qq.reader.common.utils.c.b.a(a2);
        } else {
            a = a();
        }
        if (a == null) {
            return new String(bArr);
        }
        try {
            return new String(bArr, a);
        } catch (UnsupportedEncodingException e) {
            throw new ZipException(e.getMessage());
        }
    }
}
