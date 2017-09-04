package format.epub.common.b;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ZLPhysicalFile */
public final class d extends b {
    private final File a;

    d(String str) {
        this(new File(str));
    }

    public d(File file) {
        this.a = file;
        g();
    }

    public boolean a() {
        return this.a.exists();
    }

    public long h() {
        return this.a.length();
    }

    public boolean b() {
        return this.a.isDirectory();
    }

    public String c() {
        return this.a.getPath();
    }

    public String d() {
        return b() ? c() : this.a.getName();
    }

    public b e() {
        return b() ? null : new d(this.a.getParent());
    }

    public d f() {
        return this;
    }

    public InputStream i() throws IOException {
        return new FileInputStream(this.a);
    }

    protected List<b> m() {
        File[] listFiles = this.a.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return Collections.emptyList();
        }
        List<b> arrayList = new ArrayList(listFiles.length);
        for (File file : listFiles) {
            if (!file.getName().startsWith(".")) {
                arrayList.add(new d(file));
            }
        }
        return arrayList;
    }
}
