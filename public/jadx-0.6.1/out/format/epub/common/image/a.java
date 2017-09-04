package format.epub.common.image;

import com.tencent.tinker.android.dex.DexFormat;
import format.epub.common.b.b;
import format.epub.common.utils.e;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ZLFileImage */
public class a extends g {
    private final b a;
    private final int b;
    private final int c;

    public a(String str, b bVar, int i, int i2) {
        super(str);
        this.a = bVar;
        this.b = i;
        this.c = i2;
    }

    public a(String str, b bVar) {
        this(str, bVar, 0, (int) bVar.h());
    }

    public String j_() {
        return "imagefile://" + this.a.c() + DexFormat.MAGIC_SUFFIX + this.b + DexFormat.MAGIC_SUFFIX + this.c;
    }

    public String b() {
        return this.a.c();
    }

    public InputStream c() {
        try {
            return new e(this.a.i(), this.b, this.c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
