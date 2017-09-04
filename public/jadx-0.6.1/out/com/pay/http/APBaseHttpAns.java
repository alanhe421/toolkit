package com.pay.http;

import android.os.Message;
import java.util.HashMap;

public class APBaseHttpAns implements IAPHttpAns {
    private final int a = 1;
    private APHttpHandle b;
    private HashMap<String, APBaseHttpReq> c;
    private IAPHttpAnsObserver d;
    private APBaseHttpReq e;
    public String errorMsg = "";
    private int f = 0;
    public String httpReqKey = "";
    public int resultCode = -1;
    public String resultMsg = "";

    public APBaseHttpAns(APHttpHandle aPHttpHandle, IAPHttpAnsObserver iAPHttpAnsObserver, HashMap<String, APBaseHttpReq> hashMap, String str) {
        this.b = aPHttpHandle;
        this.c = hashMap;
        this.httpReqKey = str;
        this.d = iAPHttpAnsObserver;
        this.b.register(this.httpReqKey, iAPHttpAnsObserver);
    }

    private void a() {
        Message message = new Message();
        message.what = 4;
        message.obj = this;
        this.b.sendMessage(message);
    }

    private void a(APBaseHttpReq aPBaseHttpReq) {
        this.c.put(this.httpReqKey, aPBaseHttpReq);
    }

    private void a(byte[] bArr) {
        Message message = new Message();
        message.what = 3;
        message.obj = this;
        this.b.sendMessage(message);
    }

    private void b() {
        Message message = new Message();
        message.what = 5;
        message.obj = this;
        this.b.sendMessage(message);
    }

    private void c() {
        this.c.remove(this.httpReqKey);
    }

    public String getErrorMessage() {
        return this.errorMsg;
    }

    public String getHttpReqKey() {
        return this.httpReqKey;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getResultMessage() {
        return this.resultMsg;
    }

    public void onError(APBaseHttpReq aPBaseHttpReq, int i, String str) {
        this.errorMsg = str;
        this.resultMsg = str;
        this.resultCode = i;
        c();
        onErrorAns(aPBaseHttpReq);
        a();
    }

    public void onErrorAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void onFinish(APBaseHttpReq aPBaseHttpReq) {
        c();
        if (aPBaseHttpReq.getContent() == null) {
            this.resultCode = -1;
            this.resultMsg = "";
            a();
            return;
        }
        this.e = aPBaseHttpReq;
        onFinishAns(aPBaseHttpReq.getContent(), aPBaseHttpReq);
        a(aPBaseHttpReq.getContent());
    }

    public void onFinishAns(byte[] bArr, APBaseHttpReq aPBaseHttpReq) {
    }

    public void onReceive(byte[] bArr, int i, long j, APBaseHttpReq aPBaseHttpReq) {
        onReceiveAns(bArr, i, j, aPBaseHttpReq);
    }

    public void onReceiveAns(byte[] bArr, int i, long j, APBaseHttpReq aPBaseHttpReq) {
    }

    public void onStart(APBaseHttpReq aPBaseHttpReq) {
        a(aPBaseHttpReq);
        onStartAns(aPBaseHttpReq);
    }

    public void onStartAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void onStop(APBaseHttpReq aPBaseHttpReq) {
        c();
        onStopAns(aPBaseHttpReq);
        b();
    }

    public void onStopAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void reRegister() {
        this.b.register(this.httpReqKey, this.d);
    }

    public void requestAgain() {
        if (this.e == null || this.f > 1) {
            reRegister();
            onError(this.e, -1, "");
            return;
        }
        this.f++;
        reRegister();
        new Thread(new Runnable(this) {
            final /* synthetic */ APBaseHttpAns a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e.requestAgain();
            }
        }).start();
    }
}
