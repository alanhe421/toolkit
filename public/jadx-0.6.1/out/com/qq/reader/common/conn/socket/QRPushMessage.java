package com.qq.reader.common.conn.socket;

import java.io.Serializable;

public final class QRPushMessage extends f implements Serializable {
    public static final int TYPE_MSG_QD = 1001;
    public static final int TYPE_MSG_SYNC = 1000;
    private String mContent;
    private int mMsgType = 1001;

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public String toString() {
        if (this.mContent != null) {
            return this.mContent.toString();
        }
        return super.toString();
    }

    public int getMsgType() {
        return this.mMsgType;
    }

    public void setMsgType(int i) {
        this.mMsgType = i;
    }
}
