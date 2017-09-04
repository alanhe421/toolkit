package com.yw.game.sdk.login.util.network;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Http<T> extends BaseHttp {
    private static final String TAG = "Request";
    private Object Object;
    private Context context;
    private ArrayList<HashMap<String, String>> mHeaders = null;
    private ArrayList<HashMap<String, String>> params = null;
    private g<T> requestCallback = null;
    String requestMothod = GET;
    String urlStr = null;

    public static class a {
        private ArrayList<HashMap<String, String>> a = null;
        private ArrayList<HashMap<String, String>> b = null;
        private g c = null;
        private d d = null;
        private String e = "";
        private String f = BaseHttp.GET;
        private BufferedInputStream g = null;
        private String h = null;
        private Object i;
        private Context j;

        public a a(Context context) {
            this.j = context;
            return this;
        }

        public a a() {
            this.f = BaseHttp.POST;
            return this;
        }

        public a a(String str, String str2) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            HashMap hashMap = new HashMap();
            hashMap.put(str, str2);
            this.a.add(hashMap);
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a a(g gVar) {
            this.c = gVar;
            return this;
        }
    }

    private Object getObject() {
        return this.Object;
    }

    public ArrayList<HashMap<String, String>> getParams() {
        return this.params;
    }

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public ArrayList<HashMap<String, String>> getHeaders() {
        return this.mHeaders;
    }

    public Http(a aVar) {
        super(aVar.e, aVar.f);
        this.charset = TextUtils.isEmpty(aVar.h) ? UTF_8 : aVar.h;
        this.handler = aVar.d;
        this.urlStr = aVar.e;
        this.params = aVar.a;
        this.requestCallback = aVar.c;
        this.requestMothod = TextUtils.isEmpty(aVar.f) ? GET : aVar.f;
        this.mHeaders = aVar.b;
        this.Object = aVar.i;
        this.context = aVar.j;
    }

    protected String schedulersIo() {
        return execute(this.context);
    }

    protected void mainThread(String str) {
        if (this.requestCallback != null) {
            if (TextUtils.isEmpty(str) || this.exception != null) {
                if (this.exception != null) {
                    this.exception.printStackTrace();
                }
                this.requestCallback.a(new Exception());
            } else if (this.handler != null) {
                Object a = this.handler.a(str);
                if (this.requestCallback != null && a != null) {
                    this.requestCallback.a(a);
                } else if (a == null || "".equals(a.toString())) {
                    this.exception = new Exception("json converter error");
                    this.requestCallback.a(this.exception);
                }
            } else {
                this.requestCallback.a((Object) str);
            }
        }
    }

    private String execute(Context context) {
        this.exception = null;
        try {
            return a.a(context, this).a().b();
        } catch (Throwable e) {
            this.exception = new Exception("请检查网络地址是否正确", e);
            Log.e(TAG, e.toString());
            return "";
        } catch (Exception e2) {
            this.exception = e2;
            Log.e(TAG, e2.toString());
            return "";
        } catch (Exception e22) {
            this.exception = e22;
            Log.e(TAG, e22.toString());
            return "";
        } catch (Exception e222) {
            this.exception = e222;
            Log.e(TAG, e222.toString());
            return "";
        }
    }

    protected void interup(Http http) {
        i.b();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseHttp)) {
            return false;
        }
        if (this.requestId != ((BaseHttp) obj).requestId) {
            z = false;
        }
        return z;
    }

    public int compareTo(BaseHttp baseHttp) {
        if (this.requestId == baseHttp.getRequestId()) {
            return 0;
        }
        return this.requestId < baseHttp.getRequestId() ? 1 : -1;
    }
}
