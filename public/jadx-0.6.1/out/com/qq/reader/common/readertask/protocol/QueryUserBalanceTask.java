package com.qq.reader.common.readertask.protocol;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.voucher.a.b;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import org.json.JSONObject;

public class QueryUserBalanceTask extends ReaderProtocolJSONTask {
    public static final int AUDIO_BOOK = 2;
    public static final int COMIC_BOOK = 1;
    public static final int NORMAL_BOOK = 0;
    private static final String TAG = QueryUserBalanceTask.class.getSimpleName();
    private a mResultListner;

    public interface a {
        void a();

        void a(com.qq.reader.common.charge.voucher.a.a aVar);
    }

    public QueryUserBalanceTask(a aVar, String str, int i) {
        this.mResultListner = aVar;
        this.mListener = new c(this) {
            final /* synthetic */ QueryUserBalanceTask a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("balanceinfo");
                    com.qq.reader.common.charge.voucher.a.a aVar = new com.qq.reader.common.charge.voucher.a.a();
                    if (optJSONObject != null) {
                        b bVar;
                        int optInt = optJSONObject.optInt("balance", 0);
                        int optInt2 = optJSONObject.optInt("bookTicket", 0);
                        int optInt3 = optJSONObject.optInt("isfirstsave", 0);
                        String optString = optJSONObject.optString("firstsavemsg");
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("voucher");
                        if (optJSONObject2 != null) {
                            bVar = (b) new Gson().fromJson(optJSONObject2.toString(), b.class);
                        } else {
                            bVar = null;
                        }
                        aVar.b = optInt;
                        aVar.c = optInt2;
                        aVar.f = optInt3;
                        aVar.e = optString;
                        aVar.d = bVar == null ? 0 : bVar.b;
                        aVar.g = bVar;
                        if (this.a.mResultListner != null) {
                            this.a.mResultListner.a(aVar);
                        }
                    } else if (this.a.mResultListner != null) {
                        this.a.mResultListner.a();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.e(QueryUserBalanceTask.TAG, exception.getMessage());
                if (this.a.mResultListner != null) {
                    this.a.mResultListner.a();
                }
            }
        };
        this.mUrl = e.i + "common/cmds?c=balance&usid=" + d.c() + (TextUtils.isEmpty(str) ? "" : GetVoteUserIconsTask.BID + str) + "&bookType=" + i;
        setFailedType(1);
    }
}
