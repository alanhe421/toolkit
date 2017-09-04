package com.tencent.upload.b.b;

import org.json.JSONObject;

public abstract class b extends com.tencent.upload.b.a {
    public int o;
    public int p;
    public int q;

    public static class a extends b {
        public a() {
            this.a = "qcloudaudio";
        }

        public final int a() {
            return 3;
        }
    }

    public static class b extends b {
        public b() {
            this.a = "qcloudfile";
        }

        public final int a() {
            return 1;
        }
    }

    public static class c extends b {
        public c() {
            this.a = "qcloudimage";
        }

        public final int a() {
            return 2;
        }
    }

    public static class d extends b {
        public d() {
            this.a = "qcloudunknown";
        }

        public final int a() {
            return 0;
        }
    }

    public static class e extends b {
        public e() {
            this.a = "qcloudvideo";
        }

        public final int a() {
            return 4;
        }
    }

    public b() {
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.d = "upload";
    }

    public final JSONObject b() {
        JSONObject b = super.b();
        if (b == null) {
            return null;
        }
        try {
            b.put("fastupload", this.o);
            b.put("controlcost", this.p);
            b.put("uploadcost", this.q);
        } catch (Throwable th) {
            com.tencent.upload.log.b.c("UpReportObj", "to json error!", th);
            b = null;
        }
        return b;
    }
}
