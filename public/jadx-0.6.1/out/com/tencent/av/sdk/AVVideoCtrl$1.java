package com.tencent.av.sdk;

class AVVideoCtrl$1 implements Runnable {
    final /* synthetic */ AVVideoCtrl this$0;
    final /* synthetic */ AVVideoCtrl$LocalVideoPreProcessCallback val$localVideoPreProcessCallback;

    AVVideoCtrl$1(AVVideoCtrl aVVideoCtrl, AVVideoCtrl$LocalVideoPreProcessCallback aVVideoCtrl$LocalVideoPreProcessCallback) {
        this.this$0 = aVVideoCtrl;
        this.val$localVideoPreProcessCallback = aVVideoCtrl$LocalVideoPreProcessCallback;
    }

    public void run() {
        AVVideoCtrl.access$000(this.this$0, this.val$localVideoPreProcessCallback);
    }
}
