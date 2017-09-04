package com.tencent.midas.api.request;

public class APMidasGameRequest extends APMidasBaseRequest {
    public int gameLogo = 0;

    public int getGameLogo() {
        return this.gameLogo;
    }

    public void setGameLogo(int i) {
        this.gameLogo = i;
    }
}
