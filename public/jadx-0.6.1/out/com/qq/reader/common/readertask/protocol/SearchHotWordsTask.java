package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.search.ISearchParamCollection;

public class SearchHotWordsTask extends ReaderProtocolJSONTask {
    public SearchHotWordsTask(c cVar, ISearchParamCollection iSearchParamCollection) {
        super(cVar);
        this.mUrl = ao.a(iSearchParamCollection).getHotWordProtocolURL();
        this.mUrl += "gtype=" + d.aV(ReaderApplication.getApplicationImp());
        com.qq.reader.common.monitor.debug.c.e("SearchHotWordsTask", " mUrl : " + this.mUrl);
    }
}
