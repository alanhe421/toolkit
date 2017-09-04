package format.chm.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public final class ChmEntry {
    private ChmFile a;
    private long b;
    private long c;
    private int d;
    private int e;
    private String f;
    private Attribute[] g;

    public enum Attribute {
        ALL(31),
        DIRECTORY(16),
        FILE(8),
        META(2),
        NORMAL(1),
        SPECIAL(4);
        
        private int value;

        private Attribute(int i) {
            this.value = i;
        }

        int getValue() {
            return this.value;
        }
    }

    private native byte[] readContent(String str, String str2);

    public ChmEntry(ChmFile chmFile, long j, long j2, int i, int i2, String str) {
        if (chmFile == null) {
            throw new NullPointerException("chm file is null");
        }
        this.a = chmFile;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
        this.f = str;
        List arrayList = new ArrayList();
        for (Attribute attribute : Attribute.values()) {
            if ((attribute.value & i2) == attribute.value) {
                arrayList.add(attribute);
            }
        }
        this.g = (Attribute[]) arrayList.toArray(new Attribute[0]);
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(readContent(getChmFile().a().getCanonicalPath(), this.f));
    }

    public String guessContentType() throws IOException {
        return URLConnection.guessContentTypeFromStream(getInputStream());
    }

    public ChmFile getChmFile() {
        return this.a;
    }

    public long getLength() {
        return this.c;
    }

    public String getPath() {
        return this.f;
    }

    public boolean hasAttribute(Attribute attribute) {
        return (this.e & attribute.value) == attribute.value;
    }

    public Attribute[] getAttributes() {
        return (Attribute[]) this.g.clone();
    }

    public ChmEntry[] entries(Attribute... attributeArr) throws IOException {
        return this.a.a(this, attributeArr);
    }

    public String toString() {
        return this.f;
    }
}
