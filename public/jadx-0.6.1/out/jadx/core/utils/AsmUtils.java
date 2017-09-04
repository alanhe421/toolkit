package jadx.core.utils;

import jadx.core.utils.files.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.objectweb.asm.ClassReader;

public class AsmUtils {
    private AsmUtils() {
    }

    public static String getNameFromClassFile(File file) throws IOException {
        Throwable th;
        FileInputStream in = null;
        try {
            FileInputStream in2 = new FileInputStream(file);
            try {
                String className = new ClassReader(in2).getClassName();
                FileUtils.close(in2);
                return className;
            } catch (Throwable th2) {
                th = th2;
                in = in2;
                FileUtils.close(in);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close(in);
            throw th;
        }
    }
}
