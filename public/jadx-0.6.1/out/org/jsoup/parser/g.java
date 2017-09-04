package org.jsoup.parser;

import com.dynamicload.Lib.DLConstants;
import org.jsoup.helper.b;
import org.jsoup.helper.c;

/* compiled from: TokenQueue */
public class g {
    private String a;
    private int b = 0;

    public g(String str) {
        c.a((Object) str);
        this.a = str;
    }

    public boolean a() {
        return i() == 0;
    }

    private int i() {
        return this.a.length() - this.b;
    }

    public boolean a(String str) {
        return this.a.regionMatches(true, this.b, str, 0, str.length());
    }

    public boolean a(String... strArr) {
        for (String a : strArr) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(char... cArr) {
        if (a()) {
            return false;
        }
        for (char c : cArr) {
            if (this.a.charAt(this.b) == c) {
                return true;
            }
        }
        return false;
    }

    public boolean b(String str) {
        if (!a(str)) {
            return false;
        }
        this.b += str.length();
        return true;
    }

    public boolean b() {
        return !a() && b.b(this.a.charAt(this.b));
    }

    public boolean c() {
        return !a() && Character.isLetterOrDigit(this.a.charAt(this.b));
    }

    public char d() {
        String str = this.a;
        int i = this.b;
        this.b = i + 1;
        return str.charAt(i);
    }

    public void c(String str) {
        if (a(str)) {
            int length = str.length();
            if (length > i()) {
                throw new IllegalStateException("Queue not long enough to consume sequence");
            }
            this.b = length + this.b;
            return;
        }
        throw new IllegalStateException("Queue did not match expected sequence");
    }

    public String d(String str) {
        int indexOf = this.a.indexOf(str, this.b);
        if (indexOf == -1) {
            return h();
        }
        String substring = this.a.substring(this.b, indexOf);
        this.b += substring.length();
        return substring;
    }

    public String b(String... strArr) {
        int i = this.b;
        while (!a() && !a(strArr)) {
            this.b++;
        }
        return this.a.substring(i, this.b);
    }

    public String e(String str) {
        String d = d(str);
        b(str);
        return d;
    }

    public String a(char c, char c2) {
        Object obj = null;
        char c3 = '\u0000';
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        while (!a()) {
            Character valueOf = Character.valueOf(d());
            if (c3 == '\u0000' || c3 != '\\') {
                if ((valueOf.equals(Character.valueOf('\'')) || valueOf.equals(Character.valueOf('\"'))) && valueOf.charValue() != c) {
                    obj = obj == null ? 1 : null;
                }
                if (obj != null) {
                    continue;
                    if (i <= 0) {
                        break;
                    }
                } else if (valueOf.equals(Character.valueOf(c))) {
                    i++;
                    if (i3 == -1) {
                        i3 = this.b;
                    }
                } else if (valueOf.equals(Character.valueOf(c2))) {
                    i--;
                }
            }
            if (i > 0 && c3 != '\u0000') {
                i2 = this.b;
            }
            c3 = valueOf.charValue();
            continue;
            if (i <= 0) {
                break;
            }
        }
        String substring = i2 >= 0 ? this.a.substring(i3, i2) : "";
        if (i > 0) {
            c.b("Did not find balanced maker at " + substring);
        }
        return substring;
    }

    public static String f(String str) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int i2 = 0;
        while (i < length) {
            char c = toCharArray[i];
            if (c != '\\') {
                stringBuilder.append(c);
            } else if (i2 != 0 && i2 == 92) {
                stringBuilder.append(c);
            }
            i++;
            char c2 = c;
        }
        return stringBuilder.toString();
    }

    public boolean e() {
        boolean z = false;
        while (b()) {
            this.b++;
            z = true;
        }
        return z;
    }

    public String f() {
        int i = this.b;
        while (!a()) {
            if (!c()) {
                if (!a("*|", DLConstants.DEPENDENCY_PACKAGE_DIV, "_", "-")) {
                    break;
                }
            }
            this.b++;
        }
        return this.a.substring(i, this.b);
    }

    public String g() {
        int i = this.b;
        while (!a() && (c() || a('-', '_'))) {
            this.b++;
        }
        return this.a.substring(i, this.b);
    }

    public String h() {
        String substring = this.a.substring(this.b, this.a.length());
        this.b = this.a.length();
        return substring;
    }

    public String toString() {
        return this.a.substring(this.b);
    }
}
