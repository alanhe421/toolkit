package com.tencent;

import com.tencent.imcore.FutureFriendMeta;

public class TIMFriendFutureMeta {
    private long currentDecideTimestamp = 0;
    private long currentPendencyTimestamp = 0;
    private long currentRecommendTimestamp = 0;
    private long decideSeq = 0;
    private long decideUnReadCnt = 0;
    private TIMPageDirectionType directionType = TIMPageDirectionType.TIM_PAGE_DIRECTION_UP_TYPE;
    private long pendencySeq = 0;
    private long pendencyUnReadCnt = 0;
    private long recommendSeq = 0;
    private long recommendUnReadCnt = 0;
    private long reqNum = 10;
    private long timestamp = 0;

    TIMFriendFutureMeta(FutureFriendMeta futureFriendMeta) {
        this.currentPendencyTimestamp = futureFriendMeta.getDdwCurrentPendencyTimestamp();
        this.currentRecommendTimestamp = futureFriendMeta.getDdwCurrentRecommendTimestamp();
        this.currentDecideTimestamp = futureFriendMeta.getDdwCurrentDecideTimestamp();
        this.pendencySeq = futureFriendMeta.getDdwPendencySequence();
        this.pendencyUnReadCnt = futureFriendMeta.getDdwPendencyUnReadCnt();
        this.recommendSeq = futureFriendMeta.getDdwRecommendSequence();
        this.recommendUnReadCnt = futureFriendMeta.getDdwRecommendUnReadCnt();
        this.decideSeq = futureFriendMeta.getDdwDecideSequence();
        this.decideUnReadCnt = futureFriendMeta.getDdwDecideUnreadCnt();
        this.reqNum = futureFriendMeta.getDdwReqNum();
        this.timestamp = futureFriendMeta.getDdwTimestamp();
        this.directionType = TIMPageDirectionType.getType(futureFriendMeta.getIDirection());
    }

    public long getCurrentDecideTimestamp() {
        return this.currentDecideTimestamp;
    }

    public long getCurrentPendencyTimestamp() {
        return this.currentPendencyTimestamp;
    }

    public long getCurrentRecommendTimestamp() {
        return this.currentRecommendTimestamp;
    }

    public long getDecideSeq() {
        return this.decideSeq;
    }

    public long getDecideUnReadCnt() {
        return this.decideUnReadCnt;
    }

    public TIMPageDirectionType getDirectionType() {
        return this.directionType;
    }

    FutureFriendMeta getFutureFriendMeta() {
        FutureFriendMeta futureFriendMeta = new FutureFriendMeta();
        futureFriendMeta.setDdwCurrentPendencyTimestamp(this.currentPendencyTimestamp);
        futureFriendMeta.setDdwCurrentRecommendTimestamp(this.currentRecommendTimestamp);
        futureFriendMeta.setDdwCurrentDecideTimestamp(this.currentDecideTimestamp);
        futureFriendMeta.setDdwPendencySequence(this.pendencySeq);
        futureFriendMeta.setDdwPendencyUnReadCnt(this.pendencyUnReadCnt);
        futureFriendMeta.setDdwRecommendSequence(this.recommendSeq);
        futureFriendMeta.setDdwRecommendUnReadCnt(this.recommendUnReadCnt);
        futureFriendMeta.setDdwDecideSequence(this.decideSeq);
        futureFriendMeta.setDdwDecideUnreadCnt(this.decideUnReadCnt);
        futureFriendMeta.setDdwReqNum(this.reqNum);
        futureFriendMeta.setDdwTimestamp(this.timestamp);
        futureFriendMeta.setIDirection(this.directionType.getValue());
        return futureFriendMeta;
    }

    public long getPendencySeq() {
        return this.pendencySeq;
    }

    public long getPendencyUnReadCnt() {
        return this.pendencyUnReadCnt;
    }

    public long getRecommendSeq() {
        return this.recommendSeq;
    }

    public long getRecommendUnReadCnt() {
        return this.recommendUnReadCnt;
    }

    public long getReqNum() {
        return this.reqNum;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    void setCurrentDecideTimestamp(long j) {
        this.currentDecideTimestamp = j;
    }

    void setCurrentPendencyTimestamp(long j) {
        this.currentPendencyTimestamp = j;
    }

    void setCurrentRecommendTimestamp(long j) {
        this.currentRecommendTimestamp = j;
    }

    public void setDecideSeq(long j) {
        this.decideSeq = j;
    }

    public void setDirectionType(TIMPageDirectionType tIMPageDirectionType) {
        this.directionType = tIMPageDirectionType;
    }

    public void setPendencySeq(long j) {
        this.pendencySeq = j;
    }

    public void setRecommendSeq(long j) {
        this.recommendSeq = j;
    }

    public void setReqNum(long j) {
        if (j > 30) {
            this.reqNum = 30;
        } else if (j < 0) {
            this.reqNum = 10;
        } else {
            this.reqNum = j;
        }
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
