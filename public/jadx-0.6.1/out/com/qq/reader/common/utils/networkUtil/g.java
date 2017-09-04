package com.qq.reader.common.utils.networkUtil;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: PublicIPUtil */
public class g {
    private d a;
    private StringBuffer b = new StringBuffer();
    private String c = "http://members.3322.org/dyndns/getip";

    /* compiled from: PublicIPUtil */
    private class a extends AsyncTask<Void, Void, Integer> {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        protected Integer a(Void... voidArr) {
            int waitFor;
            Exception e;
            try {
                Process exec = Runtime.getRuntime().exec(String.format("curl %1$s", new Object[]{this.a.c}));
                waitFor = exec.waitFor();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    String str = "";
                    while (true) {
                        str = bufferedReader.readLine();
                        if (str == null) {
                            break;
                        }
                        this.a.b.append(str);
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return Integer.valueOf(waitFor);
                }
            } catch (Exception e3) {
                Exception exception = e3;
                waitFor = -1;
                e = exception;
                e.printStackTrace();
                return Integer.valueOf(waitFor);
            }
            return Integer.valueOf(waitFor);
        }

        protected void a(Integer num) {
            if (num.intValue() != 0 || TextUtils.isEmpty(this.a.b.toString())) {
                this.a.a.a(num.intValue(), "0.0.0.0\n");
            } else {
                this.a.a.a(num.intValue(), this.a.b.toString() + "\n");
            }
        }
    }

    public g(String str, d dVar) {
        this.a = dVar;
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
        }
    }

    public void a() {
        new a().execute(new Void[0]);
    }
}
