package com.tencent.qalsdk;

import android.content.Context;

public class QALOffLineMsg {
    private String cmd = null;
    private Context context = null;
    private String identifer = null;
    private byte[] msgBody = null;

    public void setID(String str) {
        this.identifer = str;
    }

    public String getID() {
        return this.identifer;
    }

    public void setCmd(String str) {
        this.cmd = str;
    }

    public String getCmd() {
        return this.cmd;
    }

    public void setBody(byte[] bArr) {
        this.msgBody = bArr;
    }

    public byte[] getBody() {
        return this.msgBody;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
