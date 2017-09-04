package com.tencent.upload.network.a;

public final class h implements f {
    private int a = -1;

    public final boolean a(k kVar) {
        return true;
    }

    public final void c() {
        this.a = -1;
    }

    public final k[] d() {
        this.a++;
        if (this.a != 0) {
            return null;
        }
        return new k[]{new k("upload.imagetest.myqcloud.com", 80, 1, 3)};
    }
}
