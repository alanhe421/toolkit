package format.epub.zip;

import java.io.IOException;

public class ZipException extends IOException {
    ZipException(String str) {
        super(str);
    }
}
