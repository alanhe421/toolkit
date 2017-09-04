package format.chm.core;

import format.chm.core.ChmEntry.Attribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class ChmFile {
    private File a;

    private native ChmEntry[] entries(String str, String str2, int i) throws IOException;

    static {
        try {
            System.loadLibrary("chm");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("WARNING: Could not load library!");
        }
    }

    public ChmFile(String str) throws FileNotFoundException {
        this(new File(str));
    }

    public ChmFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("file not found : " + file.getAbsolutePath());
        } else if (file.isDirectory()) {
            throw new IllegalArgumentException("the file is a directory : " + file.getAbsolutePath());
        } else {
            this.a = file;
        }
    }

    public ChmEntry[] a(ChmEntry chmEntry, Attribute... attributeArr) throws IllegalArgumentException, IOException {
        int i = 0;
        if (chmEntry == null || equals(chmEntry.getChmFile())) {
            int i2 = 0;
            while (i2 < attributeArr.length) {
                int value = attributeArr[i2].getValue() + i;
                i2++;
                i = value;
            }
            if (i == 0) {
                i = Attribute.ALL.getValue();
            }
            return entries(this.a.getCanonicalPath(), chmEntry == null ? "/" : chmEntry.getPath(), i);
        }
        throw new IllegalArgumentException("the entry does not belong to this file");
    }

    public File a() {
        return this.a;
    }

    public String toString() {
        return this.a.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof ChmFile) {
            return this.a.equals(((ChmFile) obj).a());
        }
        return false;
    }

    public int hashCode() {
        return (this.a != null ? this.a.hashCode() : 0) + 679;
    }
}
