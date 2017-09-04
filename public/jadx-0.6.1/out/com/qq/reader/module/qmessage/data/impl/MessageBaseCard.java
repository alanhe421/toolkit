package com.qq.reader.module.qmessage.data.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import org.json.JSONObject;

public abstract class MessageBaseCard extends a {
    public static final String MESSAGE_TYPE_OF_INTERACTION = "INTERACTION";
    public static final String MESSAGE_TYPE_OF_NOTIFICATION = "NOTIFICATION";
    private boolean mIsNewMessage = false;
    private long mMessageId = 0;
    private int mSubType = 0;
    private long mTime = 0;
    private int mType = 0;
    private String mURL = "";

    public MessageBaseCard(b bVar) {
        super(bVar, "messageCard");
    }

    public void setIsNewMessage(boolean z) {
        this.mIsNewMessage = z;
    }

    public boolean isNewMessage() {
        return this.mIsNewMessage;
    }

    public int getResLayoutId() {
        return 0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            this.mOrginCardJsonOjb = jSONObject;
            this.mMessageId = jSONObject.optLong("mid");
            this.mTime = jSONObject.optLong("ctime");
            this.mURL = jSONObject.optString("target").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void setMessageType(int i) {
        this.mType = i;
    }

    public int getMessageType() {
        return this.mType;
    }

    public void setMessageSubType(int i) {
        this.mSubType = i;
    }

    public int getMessageSubType() {
        return this.mSubType;
    }

    public String getURL() {
        return this.mURL;
    }

    public long getMessageId() {
        return this.mMessageId;
    }

    public long getMessageTime() {
        return this.mTime;
    }
}
