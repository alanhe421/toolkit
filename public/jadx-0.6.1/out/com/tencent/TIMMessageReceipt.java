package com.tencent;

import com.tencent.imcore.MsgReceipt;

public class TIMMessageReceipt {
    private TIMConversation conversation;
    private long timestamp = 0;

    protected static TIMMessageReceipt convertFrom(MsgReceipt msgReceipt) {
        if (msgReceipt == null) {
            return null;
        }
        TIMMessageReceipt tIMMessageReceipt = new TIMMessageReceipt();
        if (msgReceipt.getSess() != null) {
            TIMConversation tIMConversation = new TIMConversation();
            tIMConversation.setType(TIMConversationType.getType(msgReceipt.getSess().getType()));
            tIMConversation.setPeer(msgReceipt.getSess().getSid());
            tIMMessageReceipt.setConversation(tIMConversation);
        }
        tIMMessageReceipt.setTimestamp(msgReceipt.getTimestamp());
        return tIMMessageReceipt;
    }

    public TIMConversation getConversation() {
        return this.conversation;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    void setConversation(TIMConversation tIMConversation) {
        this.conversation = tIMConversation;
    }

    void setTimestamp(long j) {
        this.timestamp = j;
    }
}
