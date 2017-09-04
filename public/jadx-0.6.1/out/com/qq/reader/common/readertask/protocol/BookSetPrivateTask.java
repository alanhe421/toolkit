package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.bookstore.qnative.model.BookSercetModel;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class BookSetPrivateTask extends ReaderProtocolJSONTask {
    public BookSetPrivateTask(c cVar, ArrayList<BookSercetModel> arrayList) {
        super(cVar);
        String str = e.R;
        JSONObject jSONObject = new JSONObject();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BookSercetModel bookSercetModel = (BookSercetModel) it.next();
            try {
                jSONObject.put(bookSercetModel.b(), bookSercetModel.a());
            } catch (Exception e) {
            }
        }
        try {
            this.mUrl = str + URLEncoder.encode("uin=" + d.R(ReaderApplication.getApplicationImp()) + "&params=" + "{\"plat\":10, \"books\":" + jSONObject.toString() + "}", "utf-8");
        } catch (Exception e2) {
        }
    }
}
