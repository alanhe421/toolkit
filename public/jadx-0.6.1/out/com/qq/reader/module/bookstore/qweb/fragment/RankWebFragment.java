package com.qq.reader.module.bookstore.qweb.fragment;

import com.qq.reader.common.c.a;
import com.qq.reader.common.offline.c;
import java.util.HashMap;

public class RankWebFragment extends WebBrowserFragment {
    public void onPreLoad() {
        HashMap hashArguments = getHashArguments();
        if (hashArguments != null) {
            String str = (String) hashArguments.get(a.cO);
            this.mUrl = str;
            this.NAME = str + System.currentTimeMillis();
        }
        bindJavaScript(this.mWebPage);
        bindWebViewClient();
        bindWebChromeClient();
        com.qq.reader.common.offline.a.a(this.mContext).a((com.qq.reader.common.offline.a.a) this);
        c.a(this.mContext).a(this.mHandler, this.NAME);
    }
}
