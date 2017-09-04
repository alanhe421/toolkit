package com.qq.reader.common.utils.b;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CSVParser */
public class a {
    private final char a;
    private final char b;
    private final char c;
    private final boolean d;
    private String e;
    private boolean f;
    private final boolean g;

    public a() {
        this(',', '\"', '\\');
    }

    public a(char c, char c2, char c3) {
        this(c, c2, c3, false);
    }

    public a(char c, char c2, char c3, boolean z) {
        this(c, c2, c3, z, true);
    }

    public a(char c, char c2, char c3, boolean z, boolean z2) {
        this.f = false;
        if (a(c, c2, c3)) {
            throw new UnsupportedOperationException("The separator, quote, and escape characters must be different!");
        } else if (c == '\u0000') {
            throw new UnsupportedOperationException("The separator character must be defined!");
        } else {
            this.a = c;
            this.b = c2;
            this.c = c3;
            this.d = z;
            this.g = z2;
        }
    }

    private boolean a(char c, char c2, char c3) {
        return a(c, c2) || a(c, c3) || a(c2, c3);
    }

    private boolean a(char c, char c2) {
        return c != '\u0000' && c == c2;
    }

    public boolean a() {
        return this.e != null;
    }

    public String[] a(String str) throws IOException {
        return a(str, true);
    }

    private String[] a(String str, boolean z) throws IOException {
        StringBuilder stringBuilder = null;
        if (!(z || this.e == null)) {
            this.e = null;
        }
        if (str != null) {
            boolean z2;
            List arrayList = new ArrayList();
            CharSequence stringBuilder2 = new StringBuilder(128);
            if (this.e != null) {
                stringBuilder2.append(this.e);
                this.e = null;
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z3 = z2;
            int i = 0;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                boolean z4;
                if (charAt == this.c) {
                    if (z3 || this.f) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (a(str, z4, i)) {
                        stringBuilder2.append(str.charAt(i + 1));
                        i++;
                    }
                } else if (charAt == this.b) {
                    z4 = z3 || this.f;
                    if (b(str, z4, i)) {
                        stringBuilder2.append(str.charAt(i + 1));
                        i++;
                    } else {
                        if (!(this.d || i <= 2 || str.charAt(i - 1) == this.a || str.length() <= i + 1 || str.charAt(i + 1) == this.a)) {
                            if (this.g && stringBuilder2.length() > 0 && a(stringBuilder2)) {
                                stringBuilder2.setLength(0);
                            } else {
                                stringBuilder2.append(charAt);
                            }
                        }
                        z3 = !z3;
                    }
                    if (this.f) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    this.f = z4;
                } else if (charAt == this.a && !z3) {
                    arrayList.add(stringBuilder2.toString());
                    stringBuilder2.setLength(0);
                    this.f = false;
                } else if (!this.d || z3) {
                    stringBuilder2.append(charAt);
                    this.f = true;
                    if (z3 && charAt == '”') {
                        z3 = false;
                    }
                }
                i++;
            }
            if (!z3) {
                CharSequence charSequence = stringBuilder2;
            } else if (z) {
                stringBuilder2.append("\n");
                this.e = stringBuilder2.toString();
            } else {
                throw new IOException("Un-terminated quoted field at end of CSV line");
            }
            if (stringBuilder != null) {
                arrayList.add(stringBuilder.toString());
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } else if (this.e == null) {
            return null;
        } else {
            String str2 = this.e;
            this.e = null;
            return new String[]{str2};
        }
    }

    private boolean b(String str, boolean z, int i) {
        return z && str.length() > i + 1 && str.charAt(i + 1) == this.b;
    }

    protected boolean a(String str, boolean z, int i) {
        return z && str.length() > i + 1 && (str.charAt(i + 1) == this.b || str.charAt(i + 1) == this.c);
    }

    protected boolean a(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
