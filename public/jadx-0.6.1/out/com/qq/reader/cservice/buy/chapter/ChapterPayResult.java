package com.qq.reader.cservice.buy.chapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChapterPayResult implements Serializable {
    private static final long serialVersionUID = -7263488863270295228L;
    private String chargeUrl = "";
    private int code = -1;
    private String mBookId;
    private List<Integer> mNeedBuyChapterId = new ArrayList();
    private List<Integer> mPayedChapterId = new ArrayList();
    private String mResultStr = "";
    private int realCost;

    public ChapterPayResult(String str) {
        this.mBookId = str;
    }

    public void addPayedChapters(int i) {
        this.mPayedChapterId.add(Integer.valueOf(i));
    }

    public List<Integer> getPayedChapters() {
        return this.mPayedChapterId;
    }

    public void addNeedBuyChapters(int i) {
        this.mNeedBuyChapterId.add(Integer.valueOf(i));
    }

    public List<Integer> getNeedBuyChapters() {
        return this.mNeedBuyChapterId;
    }

    public String getBookId() {
        return this.mBookId;
    }

    public void setBookId(String str) {
        this.mBookId = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getResultStr() {
        return this.mResultStr;
    }

    public void setResultStr(String str) {
        this.mResultStr = str;
    }

    public int getRealCost() {
        return this.realCost;
    }

    public void setRealCost(int i) {
        this.realCost = i;
    }

    public String getChargeUrl() {
        return this.chargeUrl;
    }

    public void setChargeUrl(String str) {
        this.chargeUrl = str;
    }
}
