package format.epub.common.utils;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import format.epub.options.c;
import format.epub.options.g;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ColorProfile */
public class b {
    private static final ArrayList<String> i = new ArrayList();
    private static final HashMap<String, b> j = new HashMap();
    public final g a;
    public final c b;
    public final c c;
    public final c d;
    public final c e;
    public final c f;
    public final c g;
    public final c h;

    public static b a(String str) {
        b bVar = (b) j.get(str);
        if (bVar != null) {
            return bVar;
        }
        bVar = new b(str);
        j.put(str, bVar);
        return bVar;
    }

    private static c a(String str, String str2, int i, int i2, int i3) {
        return new c("Colors", str + ':' + str2, new k(i, i2, i3));
    }

    private b(String str) {
        if ("defaultDark".equals(str)) {
            this.a = new g("Colors", str + ":Wallpaper", "");
            this.b = a(str, "Background", 0, 0, 0);
            this.c = a(str, "SelectionBackground", 82, Opcodes.INT_TO_DOUBLE, Opcodes.XOR_LONG_2ADDR);
            this.d = a(str, "Highlighting", 96, 96, 128);
            this.e = a(str, "Text", Opcodes.AND_LONG_2ADDR, Opcodes.AND_LONG_2ADDR, Opcodes.AND_LONG_2ADDR);
            this.f = a(str, "Hyperlink", 60, Opcodes.INT_TO_CHAR, Opcodes.SHL_INT_LIT8);
            this.g = a(str, "VisitedHyperlink", 200, Opcodes.DOUBLE_TO_LONG, 255);
            this.h = a(str, "FooterFillOption", 85, 85, 85);
            return;
        }
        this.a = new g("Colors", str + ":Wallpaper", "wallpapers/sepia.jpg");
        this.b = a(str, "Background", 255, 255, 255);
        this.c = a(str, "SelectionBackground", 82, Opcodes.INT_TO_DOUBLE, Opcodes.XOR_LONG_2ADDR);
        this.d = a(str, "Highlighting", 255, Opcodes.AND_LONG_2ADDR, 128);
        this.e = a(str, "Text", 0, 0, 0);
        this.f = a(str, "Hyperlink", 60, Opcodes.DOUBLE_TO_LONG, 255);
        this.g = a(str, "VisitedHyperlink", 200, Opcodes.DOUBLE_TO_LONG, 255);
        this.h = a(str, "FooterFillOption", Opcodes.REM_FLOAT, Opcodes.REM_FLOAT, Opcodes.REM_FLOAT);
    }
}
