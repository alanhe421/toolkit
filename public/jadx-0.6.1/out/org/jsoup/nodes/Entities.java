package org.jsoup.nodes;

import com.iflytek.cloud.SpeechError;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import org.jsoup.helper.b;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.parser.a;

public class Entities {
    private static final HashMap<String, String> a = new HashMap();
    private static final char[] b = new char[]{',', ';'};

    private enum CoreCharset {
        ascii,
        utf,
        fallback;

        private static CoreCharset byName(String str) {
            if (str.equals("US-ASCII")) {
                return ascii;
            }
            if (str.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    public enum EscapeMode {
        xhtml("entities-xhtml.properties", 4),
        base("entities-base.properties", 106),
        extended("entities-full.properties", 2125);
        
        private int[] codeKeys;
        private int[] codeVals;
        private String[] nameKeys;
        private String[] nameVals;

        private EscapeMode(String str, int i) {
            Entities.b(this, str, i);
        }

        int codepointForName(String str) {
            int binarySearch = Arrays.binarySearch(this.nameKeys, str);
            return binarySearch >= 0 ? this.codeVals[binarySearch] : -1;
        }

        String nameForCodepoint(int i) {
            int binarySearch = Arrays.binarySearch(this.codeKeys, i);
            if (binarySearch >= 0) {
                return (binarySearch >= this.nameVals.length + -1 || this.codeKeys[binarySearch + 1] != i) ? this.nameVals[binarySearch] : this.nameVals[binarySearch + 1];
            } else {
                return "";
            }
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean a(String str) {
        return EscapeMode.extended.codepointForName(str) != -1;
    }

    public static boolean b(String str) {
        return EscapeMode.base.codepointForName(str) != -1;
    }

    public static int a(String str, int[] iArr) {
        String str2 = (String) a.get(str);
        if (str2 != null) {
            iArr[0] = str2.codePointAt(0);
            iArr[1] = str2.codePointAt(1);
            return 2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName == -1) {
            return 0;
        }
        iArr[0] = codepointForName;
        return 1;
    }

    static void a(Appendable appendable, String str, OutputSettings outputSettings, boolean z, boolean z2, boolean z3) throws IOException {
        EscapeMode a = outputSettings.a();
        CharsetEncoder b = outputSettings.b();
        CoreCharset access$100 = CoreCharset.byName(b.charset().name());
        int length = str.length();
        Object obj = null;
        Object obj2 = null;
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (z2) {
                if (b.b(codePointAt)) {
                    if (!(z3 && r0 == null) && r1 == null) {
                        appendable.append(' ');
                        obj2 = 1;
                    }
                    i += Character.charCount(codePointAt);
                } else {
                    obj2 = null;
                    obj = 1;
                }
            }
            if (codePointAt < 65536) {
                char c = (char) codePointAt;
                switch (c) {
                    case '\"':
                        if (!z) {
                            appendable.append(c);
                            break;
                        } else {
                            appendable.append("&quot;");
                            break;
                        }
                    case '&':
                        appendable.append("&amp;");
                        break;
                    case '<':
                        if (z && a != EscapeMode.xhtml) {
                            appendable.append(c);
                            break;
                        } else {
                            appendable.append("&lt;");
                            break;
                        }
                    case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                        if (!z) {
                            appendable.append("&gt;");
                            break;
                        } else {
                            appendable.append(c);
                            break;
                        }
                    case ' ':
                        if (a == EscapeMode.xhtml) {
                            appendable.append("&#xa0;");
                            break;
                        } else {
                            appendable.append("&nbsp;");
                            break;
                        }
                    default:
                        if (!a(access$100, c, b)) {
                            a(appendable, a, codePointAt);
                            break;
                        } else {
                            appendable.append(c);
                            break;
                        }
                }
            }
            CharSequence str2 = new String(Character.toChars(codePointAt));
            if (b.canEncode(str2)) {
                appendable.append(str2);
            } else {
                a(appendable, a, codePointAt);
            }
            i += Character.charCount(codePointAt);
        }
    }

    private static void a(Appendable appendable, EscapeMode escapeMode, int i) throws IOException {
        CharSequence nameForCodepoint = escapeMode.nameForCodepoint(i);
        if (nameForCodepoint != "") {
            appendable.append('&').append(nameForCodepoint).append(';');
        } else {
            appendable.append("&#x").append(Integer.toHexString(i)).append(';');
        }
    }

    private static boolean a(CoreCharset coreCharset, char c, CharsetEncoder charsetEncoder) {
        switch (coreCharset) {
            case ascii:
                if (c >= '') {
                    return false;
                }
                return true;
            case utf:
                return true;
            default:
                return charsetEncoder.canEncode(c);
        }
    }

    private static void b(EscapeMode escapeMode, String str, int i) {
        escapeMode.nameKeys = new String[i];
        escapeMode.codeVals = new int[i];
        escapeMode.codeKeys = new int[i];
        escapeMode.nameVals = new String[i];
        InputStream resourceAsStream = Entities.class.getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IllegalStateException("Could not read resource " + str + ". Make sure you copy resources for " + Entities.class.getCanonicalName());
        }
        try {
            a aVar = new a(Charset.forName("ascii").decode(org.jsoup.helper.a.a(resourceAsStream, 0)).toString());
            int i2 = 0;
            while (!aVar.b()) {
                int parseInt;
                int i3;
                String b = aVar.b('=');
                aVar.f();
                int parseInt2 = Integer.parseInt(aVar.a(b), 36);
                char c = aVar.c();
                aVar.f();
                if (c == ',') {
                    parseInt = Integer.parseInt(aVar.b(';'), 36);
                    aVar.f();
                    i3 = parseInt;
                } else {
                    i3 = -1;
                }
                String b2 = aVar.b('\n');
                if (b2.charAt(b2.length() - 1) == '\r') {
                    b2 = b2.substring(0, b2.length() - 1);
                }
                parseInt = Integer.parseInt(b2, 36);
                aVar.f();
                escapeMode.nameKeys[i2] = b;
                escapeMode.codeVals[i2] = parseInt2;
                escapeMode.codeKeys[parseInt] = parseInt2;
                escapeMode.nameVals[parseInt] = b;
                if (i3 != -1) {
                    a.put(b, new String(new int[]{parseInt2, i3}, 0, 2));
                }
                i2++;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error reading resource " + str);
        }
    }
}
