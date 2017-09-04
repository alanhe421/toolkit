package com.qq.reader.common.web.js;

import android.app.Activity;
import com.qq.reader.activity.WebBrowser;
import com.qq.reader.common.web.js.a.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class JSSearch extends b {
    private Activity a;

    public JSSearch(Activity activity) {
        this.a = activity;
    }

    public void doSearch(String str) {
        if (this.a instanceof WebBrowser) {
            String str2 = "";
            try {
                ((WebBrowser) this.a).c(new JSONObject(str).getString("keyword"));
            } catch (JSONException e) {
                e.printStackTrace();
                ((WebBrowser) this.a).c("");
            }
        }
    }
}
