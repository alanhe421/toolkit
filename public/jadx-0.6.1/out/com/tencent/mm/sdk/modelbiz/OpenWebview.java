package com.tencent.mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import java.net.URLEncoder;

public class OpenWebview {

    public static class Req extends BaseReq {
        private static final int MAX_URL_LENGHT = 10240;
        public String url;

        public boolean checkArgs() {
            return this.url != null && this.url.length() >= 0 && this.url.length() <= MAX_URL_LENGHT;
        }

        public int getType() {
            return 12;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_webview_url", URLEncoder.encode(this.url));
        }
    }
}
