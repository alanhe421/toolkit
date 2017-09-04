package com.pay.http;

import android.text.TextUtils;
import com.pay.network.model.APDataReportAns;
import com.pay.network.model.APDataReportReq;
import com.tencent.midas.data.APPluginDataInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class APNetworkManager {
    public static final String HTTP_KEY_DATAREPORT = "datareport";
    public static final String HTTP_KEY_INITREPORT = "initreport";
    private static APNetworkManager a = null;
    private HashMap<String, APBaseHttpReq> b = new HashMap();

    public static void cancelRequest(String str) {
        APBaseHttpReq aPBaseHttpReq = (APBaseHttpReq) a.b.get(str);
        if (aPBaseHttpReq != null) {
            aPBaseHttpReq.stopRequest();
            a.b.remove(str);
        }
    }

    public static APNetworkManager getInstance() {
        if (a == null) {
            synchronized (APNetworkManager.class) {
                if (a == null) {
                    a = new APNetworkManager();
                }
            }
        }
        return a;
    }

    public static void release() {
        a = null;
    }

    public void cancelPreRequest() {
        if (a.b != null) {
            List arrayList = new ArrayList();
            for (Entry entry : a.b.entrySet()) {
                if (entry != null) {
                    arrayList.add((APBaseHttpReq) entry.getValue());
                }
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                APBaseHttpReq aPBaseHttpReq = (APBaseHttpReq) arrayList.get(i);
                if (aPBaseHttpReq != null) {
                    aPBaseHttpReq.stopRequest();
                }
            }
            a.b.clear();
        }
    }

    public void dataReport(String str, IAPHttpAnsObserver iAPHttpAnsObserver) {
        if (!TextUtils.isEmpty(APPluginDataInterface.singleton().getOfferId())) {
            APDataReportReq aPDataReportReq = new APDataReportReq();
            aPDataReportReq.setHttpAns(new APDataReportAns(APHttpHandle.getIntanceHandel(), iAPHttpAnsObserver, this.b, HTTP_KEY_DATAREPORT));
            aPDataReportReq.startService(str);
        }
    }
}
