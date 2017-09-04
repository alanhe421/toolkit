package format.epub.view.style;

import format.epub.common.b.b;
import format.epub.common.utils.d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class SimpleCSSReader {
    private State a;
    private State b;
    private Map<Integer, e> c;
    private Map<String, String> d;
    private String e;
    private String f;

    private enum State {
        EXPECT_SELECTOR,
        EXPECT_OPEN_BRACKET,
        EXPECT_NAME,
        EXPECT_VALUE,
        READ_COMMENT
    }

    SimpleCSSReader() {
    }

    Map<Integer, e> a(b bVar) {
        InputStream i;
        Throwable th;
        this.c = new LinkedHashMap();
        this.a = State.EXPECT_SELECTOR;
        InputStream inputStream = null;
        try {
            i = bVar.i();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    for (String readLine2 : d.c(readLine2)) {
                        a(readLine2);
                    }
                }
                if (i != null) {
                    try {
                        i.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                inputStream = i;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return this.c;
            } catch (Throwable th2) {
                th = th2;
                if (i != null) {
                    try {
                        i.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            if (inputStream != null) {
                inputStream.close();
            }
            return this.c;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            i = null;
            th = th4;
            if (i != null) {
                i.close();
            }
            throw th;
        }
        return this.c;
    }

    private void a(String str) {
        if (this.a == State.READ_COMMENT || !str.startsWith("/*")) {
            switch (this.a) {
                case READ_COMMENT:
                    if (str.endsWith("*/")) {
                        this.a = this.b;
                        return;
                    }
                    return;
                case EXPECT_SELECTOR:
                    this.e = str;
                    this.a = State.EXPECT_OPEN_BRACKET;
                    return;
                case EXPECT_OPEN_BRACKET:
                    if ("{".equals(str)) {
                        this.d = new HashMap();
                        this.a = State.EXPECT_NAME;
                        return;
                    }
                    return;
                case EXPECT_NAME:
                    if ("}".equals(str)) {
                        if (this.e != null) {
                            try {
                                this.c.put(Integer.valueOf((String) this.d.get("fbreader-id")), new e(this.e, this.d));
                            } catch (Exception e) {
                            }
                        }
                        this.a = State.EXPECT_SELECTOR;
                        return;
                    }
                    this.f = str;
                    this.a = State.EXPECT_VALUE;
                    return;
                case EXPECT_VALUE:
                    if (!(this.d == null || this.f == null)) {
                        this.d.put(this.f, str);
                    }
                    this.a = State.EXPECT_NAME;
                    return;
                default:
                    return;
            }
        }
        this.b = this.a;
        this.a = State.READ_COMMENT;
    }
}
