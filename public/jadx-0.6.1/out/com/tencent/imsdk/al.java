package com.tencent.imsdk;

final class al implements Runnable {
    private /* synthetic */ ai a;

    al(ai aiVar) {
        this.a = aiVar;
    }

    public final void run() {
        this.a.a.onSuccess();
    }
}
