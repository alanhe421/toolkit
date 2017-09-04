package com.qq.reader.module.qmessage;

class MessageActivity$2 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ MessageActivity b;

    MessageActivity$2(MessageActivity messageActivity, int i) {
        this.b = messageActivity;
        this.a = i;
    }

    public void run() {
        try {
            MessageActivity.d(this.b).b(this.a);
        } catch (Exception e) {
        }
    }
}
