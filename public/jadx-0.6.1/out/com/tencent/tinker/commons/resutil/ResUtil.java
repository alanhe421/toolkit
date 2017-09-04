package com.tencent.tinker.commons.resutil;

import com.tencent.tinker.commons.ziputil.TinkerZipEntry;
import com.tencent.tinker.commons.ziputil.TinkerZipFile;
import com.tencent.tinker.commons.ziputil.TinkerZipOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResUtil {
    private static final int BUFFER_SIZE = 16384;

    public static void extractTinkerEntry(TinkerZipFile tinkerZipFile, TinkerZipEntry tinkerZipEntry, TinkerZipOutputStream tinkerZipOutputStream) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = tinkerZipFile.getInputStream(tinkerZipEntry);
            tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry));
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                tinkerZipOutputStream.write(bArr, 0, read);
            }
            tinkerZipOutputStream.closeEntry();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void extractLargeModifyFile(TinkerZipEntry tinkerZipEntry, File file, long j, TinkerZipOutputStream tinkerZipOutputStream) throws IOException {
        Throwable th;
        TinkerZipEntry tinkerZipEntry2 = new TinkerZipEntry(tinkerZipEntry);
        tinkerZipEntry2.setMethod(0);
        tinkerZipEntry2.setSize(file.length());
        tinkerZipEntry2.setCompressedSize(file.length());
        tinkerZipEntry2.setCrc(j);
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry2));
                byte[] bArr = new byte[16384];
                for (int read = fileInputStream.read(bArr); read != -1; read = fileInputStream.read(bArr)) {
                    tinkerZipOutputStream.write(bArr, 0, read);
                }
                tinkerZipOutputStream.closeEntry();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }
}
