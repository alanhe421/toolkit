package com.tencent.midas.api.request;

public class APMidasGoodsRequest extends APMidasBaseRequest {
    public static final int GETTOKENTYPE_CLIENT = 3;
    public static final int GETTOKENTYPE_SDK = 2;
    public static final int GETTOKENTYPE_SERVER = 1;
    public String developerPayload = "";
    public int gameLogo = 0;
    public String goodsTokenUrl = "";
    public boolean mIsReceiptMode = false;
    public String prodcutId = "";
    public int tokenType = -1;

    public String getDeveloperPayload() {
        return this.developerPayload;
    }

    public int getGameLogo() {
        return this.gameLogo;
    }

    public String getGoodsTokenUrl() {
        return this.goodsTokenUrl;
    }

    public boolean getIsReceiptMode() {
        return this.mIsReceiptMode;
    }

    public String getProdcutId() {
        return this.prodcutId;
    }

    public int getTokenType() {
        return this.tokenType;
    }

    public void setDeveloperPayload(String str) {
        this.developerPayload = str;
    }

    public void setGameLogo(int i) {
        this.gameLogo = i;
    }

    public void setGoodsTokenUrl(String str) {
        this.goodsTokenUrl = str;
    }

    public void setIsReceiptMode(boolean z) {
        this.mIsReceiptMode = z;
    }

    public void setProdcutId(String str) {
        this.prodcutId = str;
    }

    public void setTokenType(int i) {
        this.tokenType = i;
    }
}
