package com.qq.reader.common.offline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: ReaderInputStream */
public class f extends FileInputStream {
    public f(File file) throws FileNotFoundException {
        super(file);
    }

    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
