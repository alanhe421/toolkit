package jadx.core.utils.files;

import jadx.core.utils.exceptions.JadxRuntimeException;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
    private static final int MAX_FILENAME_LENGTH = 128;
    public static final int READ_BUFFER_SIZE = 8192;

    private FileUtils() {
    }

    public static void addFileToJar(JarOutputStream jar, File source, String entryName) throws IOException {
        Throwable th;
        BufferedInputStream in = null;
        try {
            JarEntry entry = new JarEntry(entryName);
            entry.setTime(source.lastModified());
            jar.putNextEntry(entry);
            BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(source));
            try {
                copyStream(in2, jar);
                jar.closeEntry();
                close(in2);
            } catch (Throwable th2) {
                th = th2;
                in = in2;
                close(in);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            close(in);
            throw th;
        }
    }

    public static void makeDirsForFile(File file) {
        File dir = file.getParentFile();
        if (dir != null && !dir.exists() && !dir.mkdirs() && !dir.exists()) {
            throw new JadxRuntimeException("Can't create directory " + dir);
        }
    }

    public static File createTempFile(String suffix) {
        try {
            File temp = File.createTempFile("jadx-tmp-", System.nanoTime() + "-" + suffix);
            temp.deleteOnExit();
            return temp;
        } catch (IOException e) {
            throw new JadxRuntimeException("Failed to create temp file with suffix: " + suffix);
        }
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[READ_BUFFER_SIZE];
        while (true) {
            int count = input.read(buffer);
            if (count != -1) {
                output.write(buffer, 0, count);
            } else {
                return;
            }
        }
    }

    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                LOG.error("Close exception for {}", c, e);
            }
        }
    }

    @NotNull
    public static File prepareFile(File file) {
        String name = file.getName();
        if (name.length() > MAX_FILENAME_LENGTH) {
            int dotIndex = name.indexOf(46);
            int cutAt = ((128 - name.length()) + dotIndex) - 1;
            if (cutAt <= 0) {
                name = name.substring(0, 127);
            } else {
                name = name.substring(0, cutAt) + name.substring(dotIndex);
            }
            file = new File(file.getParentFile(), name);
        }
        makeDirsForFile(file);
        return file;
    }
}
