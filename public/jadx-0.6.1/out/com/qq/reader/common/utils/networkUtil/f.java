package com.qq.reader.common.utils.networkUtil;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: PingUtil */
public class f {
    private String a;
    private d b;
    private StringBuffer c = new StringBuffer();

    /* compiled from: PingUtil */
    private class a extends AsyncTask<String, Void, Integer> {
        final /* synthetic */ f a;

        private a(f fVar) {
            this.a = fVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        protected Integer a(String... strArr) {
            int waitFor;
            Exception e;
            try {
                Process exec = Runtime.getRuntime().exec("ping -c 3 " + strArr[0]);
                waitFor = exec.waitFor();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    String str = "";
                    while (true) {
                        str = bufferedReader.readLine();
                        if (str == null) {
                            break;
                        }
                        this.a.c.append(str + "\n");
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
            if (num.intValue() != 0 || TextUtils.isEmpty(this.a.c.toString())) {
                this.a.b.a(num.intValue(), "失败\n");
            } else {
                this.a.b.a(num.intValue(), this.a.c.toString());
            }
        }
    }

    public f(String str, d dVar) {
        this.a = str;
        this.b = dVar;
    }

    public void a() {
        this.b.a("PING " + this.a + "\n");
        new a().execute(new String[]{this.a});
    }
}
