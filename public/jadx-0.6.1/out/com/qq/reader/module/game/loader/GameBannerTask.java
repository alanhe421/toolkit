package com.qq.reader.module.game.loader;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.game.data.GameTopBannerData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameBannerTask extends ReaderProtocolJSONTask implements c {
    a callback;

    public interface a {
        void a(List<GameTopBannerData> list);
    }

    public GameBannerTask() {
        registerNetTaskListener(this);
        setUrl(e.cI);
    }

    public void setCallback(a aVar) {
        this.callback = aVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("advs");
            List arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new GameTopBannerData(optJSONArray.optJSONObject(i)));
            }
            d.a(ReaderApplication.getApplicationImp(), arrayList);
            if (this.callback != null) {
                this.callback.a(arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
    }
}
