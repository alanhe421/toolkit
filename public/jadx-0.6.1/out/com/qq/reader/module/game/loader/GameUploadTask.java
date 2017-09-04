package com.qq.reader.module.game.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import org.json.JSONObject;

public class GameUploadTask extends ReaderProtocolJSONTask implements c {
    public static final String DOWNLOAD_LOCAL_GAME = "2";
    public static final String OPEN_H5_GAME = "1";
    public static final String OPEN_LOCAL_GAME = "3";
    String gameId;
    String type;

    public GameUploadTask(String str, String str2) {
        this.mListener = this;
        this.gameId = str;
        this.type = str2;
        setUrl(e.cL + "?gameId=" + str + FeedDataTask.MS_TYPE + str2);
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            int optInt = new JSONObject(str).optInt("code");
            com.qq.reader.common.monitor.debug.c.e("GAME_LOG", "upload success type = " + this.type + " gameId = " + this.gameId + " code is " + optInt);
            switch (optInt) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        com.qq.reader.common.monitor.debug.c.e("GAME_LOG", "upload fail by connection error type = " + this.type + "gameId = " + this.gameId);
    }
}
