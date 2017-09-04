package jadx.core.utils.files;

import com.android.dx.command.DxConsole;
import com.android.dx.command.dexer.Main;
import com.android.dx.command.dexer.Main.Arguments;
import jadx.core.utils.exceptions.JadxException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class JavaToDex {
    private static final String CHARSET_NAME = "UTF-8";
    private String dxErrors;

    public static class DxArgs extends Arguments {
        public DxArgs(String dexFile, String[] input) {
            this.outName = dexFile;
            this.fileNames = input;
            this.jarOutput = false;
            this.optimize = true;
            this.localInfo = true;
            this.coreLibrary = true;
            this.debug = true;
        }
    }

    public byte[] convert(String javaFile) throws JadxException {
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();
        try {
            DxConsole.err = new PrintStream(errOut, true, CHARSET_NAME);
            PrintStream oldOut = System.out;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                System.setOut(new PrintStream(baos, true, CHARSET_NAME));
                DxArgs args = new DxArgs("-", new String[]{javaFile});
                resetOutDexVar();
                Main.run(args);
                FileUtils.close(baos);
                System.setOut(oldOut);
                try {
                    this.dxErrors = errOut.toString(CHARSET_NAME);
                    return baos.toByteArray();
                } catch (UnsupportedEncodingException e) {
                    throw new JadxException("Can't save error output", e);
                }
            } catch (Throwable th) {
                FileUtils.close(baos);
                System.setOut(oldOut);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new JadxException(e2.getMessage(), e2);
        }
    }

    private void resetOutDexVar() throws JadxException {
        try {
            Field outputDex = Main.class.getDeclaredField("outputDex");
            outputDex.setAccessible(true);
            outputDex.set(null, null);
        } catch (Exception e) {
            throw new JadxException("Failed to reset outputDex field", e);
        }
    }

    public String getDxErrors() {
        return this.dxErrors;
    }

    public boolean isError() {
        return (this.dxErrors == null || this.dxErrors.isEmpty()) ? false : true;
    }
}
