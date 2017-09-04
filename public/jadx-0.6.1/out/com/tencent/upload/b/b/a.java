package com.tencent.upload.b.b;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class a extends com.tencent.upload.b.a {
    public int o;

    public static class a extends a {
        public a() {
            this.a = "qcloudaudio";
        }

        public final int a() {
            return 3;
        }
    }

    public static class b extends a {
        public b() {
            this.a = "qcloudfile";
        }

        public final int a() {
            return 1;
        }
    }

    public static class c extends a {
        public c() {
            this.a = "qcloudimage";
        }

        public final int a() {
            return 2;
        }
    }

    public static class d extends a {
        public d() {
            this.a = "qcloudunknown";
        }

        public final int a() {
            return 0;
        }
    }

    public static class e extends a {
        public e() {
            this.a = "qcloudvideo";
        }

        public final int a() {
            return 4;
        }
    }

    public a() {
        this.o = -1;
        this.d = SocialConstants.PARAM_SEND_MSG;
    }

    public int a() {
        return 2;
    }

    public final JSONObject b() {
        JSONObject b = super.b();
        if (b == null) {
            return null;
        }
        try {
            b.put("cmd", this.o);
        } catch (Throwable th) {
            com.tencent.upload.log.b.c("FileOprReportObj", "to json error!", th);
            b = null;
        }
        return b;
    }
}
