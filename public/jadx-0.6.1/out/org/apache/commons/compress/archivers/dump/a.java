package org.apache.commons.compress.archivers.dump;

/* compiled from: Dirent */
class a {
    private final int a;
    private final int b;
    private final int c;
    private final String d;

    a(int i, int i2, int i3, String str) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = str;
    }

    public String toString() {
        return String.format("[%d]: %s", new Object[]{Integer.valueOf(this.a), this.d});
    }
}
