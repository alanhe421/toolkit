package jadx.core.utils.files;

import com.android.dex.Dex;
import jadx.core.utils.AsmUtils;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputFile {
    private static final Logger LOG = LoggerFactory.getLogger(InputFile.class);
    private final List<DexFile> dexFiles = new ArrayList();
    private final File file;

    private boolean loadFromZip(java.lang.String r11) throws java.io.IOException, jadx.core.utils.exceptions.DecodeException {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        r8 = 1;
        r6 = new java.util.zip.ZipFile;
        r7 = r10.file;
        r6.<init>(r7);
        r3 = 0;
    L_0x0009:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r9 = "classes";
        r9 = r7.append(r9);
        if (r3 != 0) goto L_0x0031;
    L_0x0016:
        r7 = "";
    L_0x0018:
        r7 = r9.append(r7);
        r7 = r7.append(r11);
        r1 = r7.toString();
        r0 = r6.getEntry(r1);
        if (r0 != 0) goto L_0x0036;
    L_0x002a:
        r6.close();
        if (r3 <= 0) goto L_0x0095;
    L_0x002f:
        r7 = r8;
    L_0x0030:
        return r7;
    L_0x0031:
        r7 = java.lang.Integer.valueOf(r3);
        goto L_0x0018;
    L_0x0036:
        r4 = r6.getInputStream(r0);
        r7 = ".dex";	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r7 = r11.equals(r7);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        if (r7 == 0) goto L_0x0053;	 Catch:{ all -> 0x0077, all -> 0x0072 }
    L_0x0042:
        r7 = new com.android.dex.Dex;	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r7.<init>(r4);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r10.addDexFile(r1, r7);	 Catch:{ all -> 0x0077, all -> 0x0072 }
    L_0x004a:
        jadx.core.utils.files.FileUtils.close(r4);
        r3 = r3 + 1;
        if (r3 != r8) goto L_0x0009;
    L_0x0051:
        r3 = 2;
        goto L_0x0009;
    L_0x0053:
        r7 = ".jar";	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r7 = r11.equals(r7);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        if (r7 == 0) goto L_0x007c;	 Catch:{ all -> 0x0077, all -> 0x0072 }
    L_0x005b:
        r5 = jadx.core.utils.files.FileUtils.createTempFile(r1);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r2 = new java.io.FileOutputStream;	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r2.<init>(r5);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        org.apache.commons.io.IOUtils.copy(r4, r2);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        jadx.core.utils.files.FileUtils.close(r2);
        r7 = loadFromJar(r5);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r10.addDexFile(r1, r7);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        goto L_0x004a;
    L_0x0072:
        r7 = move-exception;
        jadx.core.utils.files.FileUtils.close(r4);
        throw r7;
    L_0x0077:
        r7 = move-exception;
        jadx.core.utils.files.FileUtils.close(r2);
        throw r7;	 Catch:{ all -> 0x0077, all -> 0x0072 }
    L_0x007c:
        r7 = new jadx.core.utils.exceptions.JadxRuntimeException;	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r8.<init>();	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r9 = "Unexpected extension in zip: ";	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r8 = r8.append(r11);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r8 = r8.toString();	 Catch:{ all -> 0x0077, all -> 0x0072 }
        r7.<init>(r8);	 Catch:{ all -> 0x0077, all -> 0x0072 }
        throw r7;	 Catch:{ all -> 0x0077, all -> 0x0072 }
    L_0x0095:
        r7 = 0;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: jadx.core.utils.files.InputFile.loadFromZip(java.lang.String):boolean");
    }

    public static void addFilesFrom(File file, List<InputFile> list) throws IOException, DecodeException {
        InputFile inputFile = new InputFile(file);
        inputFile.searchDexFiles();
        list.add(inputFile);
    }

    private InputFile(File file) throws IOException, DecodeException {
        if (file.exists()) {
            this.file = file;
            return;
        }
        throw new IOException("File not found: " + file.getAbsolutePath());
    }

    private void searchDexFiles() throws IOException, DecodeException {
        String fileName = this.file.getName();
        if (fileName.endsWith(".dex")) {
            addDexFile(new Dex(this.file));
        } else if (fileName.endsWith(".class")) {
            addDexFile(loadFromClassFile(this.file));
        } else if (fileName.endsWith(".apk") || fileName.endsWith(".zip")) {
            loadFromZip(".dex");
        } else if (fileName.endsWith(".jar")) {
            if (!loadFromZip(".dex")) {
                addDexFile(loadFromJar(this.file));
            }
        } else if (fileName.endsWith(".aar")) {
            loadFromZip(".jar");
        } else {
            throw new DecodeException("Unsupported input file format: " + this.file);
        }
    }

    private void addDexFile(Dex dexBuf) throws IOException {
        addDexFile("", dexBuf);
    }

    private void addDexFile(String fileName, Dex dexBuf) throws IOException {
        this.dexFiles.add(new DexFile(this, fileName, dexBuf));
    }

    private static Dex loadFromJar(File jarFile) throws DecodeException {
        try {
            LOG.info("converting to dex: {} ...", jarFile.getName());
            JavaToDex j2d = new JavaToDex();
            byte[] ba = j2d.convert(jarFile.getAbsolutePath());
            if (ba.length == 0) {
                throw new JadxException(j2d.isError() ? j2d.getDxErrors() : "Empty dx output");
            }
            if (j2d.isError()) {
                LOG.warn("dx message: {}", j2d.getDxErrors());
            }
            return new Dex(ba);
        } catch (Throwable e) {
            DecodeException decodeException = new DecodeException("java class to dex conversion error:\n " + e.getMessage(), e);
        }
    }

    private static Dex loadFromClassFile(File file) throws IOException, DecodeException {
        Throwable th;
        File outFile = FileUtils.createTempFile("cls.jar");
        FileOutputStream out = null;
        JarOutputStream jo = null;
        try {
            FileOutputStream out2 = new FileOutputStream(outFile);
            try {
                JarOutputStream jo2 = new JarOutputStream(out2);
                try {
                    String clsName = AsmUtils.getNameFromClassFile(file);
                    if (clsName == null) {
                        throw new IOException("Can't read class name from file: " + file);
                    }
                    FileUtils.addFileToJar(jo2, file, clsName + ".class");
                    FileUtils.close(jo2);
                    FileUtils.close(out2);
                    return loadFromJar(outFile);
                } catch (Throwable th2) {
                    th = th2;
                    jo = jo2;
                    out = out2;
                    FileUtils.close(jo);
                    FileUtils.close(out);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                FileUtils.close(jo);
                FileUtils.close(out);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            FileUtils.close(jo);
            FileUtils.close(out);
            throw th;
        }
    }

    public File getFile() {
        return this.file;
    }

    public List<DexFile> getDexFiles() {
        return this.dexFiles;
    }

    public String toString() {
        return this.file.getAbsolutePath();
    }
}
