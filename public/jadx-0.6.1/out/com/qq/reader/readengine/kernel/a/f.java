package com.qq.reader.readengine.kernel.a;

import format.epub.view.y;

/* compiled from: TextBuildTraverser */
class f extends g {
    protected final StringBuilder a = new StringBuilder();

    f() {
    }

    protected void a(y yVar) {
        this.a.append(yVar.a, yVar.b, yVar.h);
    }

    protected void a() {
        this.a.append(" ");
    }

    protected void b() {
        this.a.append("\n");
    }

    public String c() {
        return this.a.toString();
    }
}
