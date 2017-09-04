package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.exception.WeiboException;

/* compiled from: AsyncWeiboRunner */
public class a {
    private Context a;

    /* compiled from: AsyncWeiboRunner */
    private static class a<T> {
        private T a;
        private WeiboException b;

        public T a() {
            return this.a;
        }

        public WeiboException b() {
            return this.b;
        }

        public a(T t) {
            this.a = t;
        }

        public a(WeiboException weiboException) {
            this.b = weiboException;
        }
    }

    /* compiled from: AsyncWeiboRunner */
    public static class b extends AsyncTask<Void, Void, a<String>> {
        private final Context a;
        private final String b;
        private final f c;
        private final String d;
        private final e e;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((a) obj);
        }

        public b(Context context, String str, f fVar, String str2, e eVar) {
            this.a = context;
            this.b = str;
            this.c = fVar;
            this.d = str2;
            this.e = eVar;
        }

        protected a<String> a(Void... voidArr) {
            try {
                return new a(HttpManager.a(this.a, this.b, this.d, this.c));
            } catch (WeiboException e) {
                return new a(e);
            }
        }

        protected void onPreExecute() {
        }

        protected void a(a<String> aVar) {
            WeiboException b = aVar.b();
            if (b != null) {
                this.e.a(b);
            } else {
                this.e.a((String) aVar.a());
            }
        }
    }

    public a(Context context) {
        this.a = context;
    }

    public void a(String str, f fVar, String str2, e eVar) {
        a(this.a, fVar.a());
        new b(this.a, str, fVar, str2, eVar).execute(new Void[]{null});
    }

    private void a(Context context, String str) {
        try {
            Class.forName("com.sina.weibo.sdk.a.g").getMethod("getInstance", new Class[]{Context.class, String.class}).invoke(null, new Object[]{context, str}).getClass().getMethod("activateApp", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
        }
    }
}
