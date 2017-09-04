package com.qq.reader.common.utils.networkUtil;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TracerouteUtil */
public class h {
    private final int a = 30;
    private int b = 1;
    private String c;
    private float d;
    private String e;
    private d f;

    /* compiled from: TracerouteUtil */
    private class a extends AsyncTask<Void, Void, String> {
        final /* synthetic */ h a;
        private int b;
        private String c;
        private List<b> d = new ArrayList();

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public a(h hVar, int i, String str) {
            this.a = hVar;
            this.b = i;
            this.c = str;
        }

        protected String a(Void... voidArr) {
            String str = "";
            try {
                str = b(this.c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(str)) {
                b bVar;
                if (!str.contains("100%") || str.contains("exceed")) {
                    bVar = new b(this.a, "", d(str), this.a.b == this.b ? Float.parseFloat(e(str)) : this.a.d);
                } else {
                    bVar = new b(this.a, "", d(str), this.a.d);
                }
                try {
                    bVar.a(InetAddress.getByName(bVar.a()).getHostName());
                } catch (UnknownHostException e2) {
                    e2.printStackTrace();
                }
                this.d.add(bVar);
            }
            return str;
        }

        protected void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.a.f.a(0, a(this.d));
                return;
            }
            if (this.a.b == 1) {
                this.a.f.a(this.a.c);
            }
            if (((b) this.d.get(this.d.size() - 1)).a().equals(this.a.c)) {
                this.a.f.a(0, a(this.d));
            } else if (this.a.b < this.b) {
                this.a.b = this.a.b + 1;
                new a(this.a, this.b, this.c).execute(new Void[0]);
            }
        }

        private String b(String str) throws IOException {
            String str2 = "";
            str2 = "";
            String format = String.format("ping -c 1 -t %d ", new Object[]{Integer.valueOf(this.a.b)});
            long nanoTime = System.nanoTime();
            Process exec = Runtime.getRuntime().exec(format + str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str2 = str2 + readLine + "\n";
                if (readLine.contains("From") || readLine.contains("from")) {
                    this.a.d = ((float) (System.nanoTime() - nanoTime)) / 1000000.0f;
                }
            }
            exec.destroy();
            if (this.a.b == 1) {
                this.a.c = c(str2);
            }
            return str2;
        }

        private String c(String str) {
            String str2 = "";
            if (!str.contains("PING")) {
                return str2;
            }
            return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        }

        private String d(String str) {
            String str2 = "";
            if (str.contains("From")) {
                str2 = str.substring(str.indexOf("From") + 5);
                if (str2.contains("(")) {
                    return str2.substring(str2.indexOf("(") + 1, str2.indexOf(")"));
                }
                int indexOf;
                String substring = str2.substring(0, str2.indexOf("\n"));
                if (substring.contains(":")) {
                    indexOf = substring.indexOf(":");
                } else {
                    indexOf = substring.indexOf(" ");
                }
                return substring.substring(0, indexOf);
            }
            return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        }

        private String e(String str) {
            String str2 = "";
            if (!str.contains("time=")) {
                return str2;
            }
            str2 = str.substring(str.indexOf("time=") + 5);
            return str2.substring(0, str2.indexOf(" "));
        }

        private String a(List<b> list) {
            StringBuilder stringBuilder = new StringBuilder();
            if (list == null || list.size() <= 0) {
                stringBuilder.append("Traceroute failed\n\n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    stringBuilder.append((i + 1) + ". " + ((b) list.get(i)).toString() + "\n");
                }
                stringBuilder.append("Traceroute complete\n\n");
            }
            return stringBuilder.toString();
        }
    }

    /* compiled from: TracerouteUtil */
    private class b {
        final /* synthetic */ h a;
        private String b;
        private float c;
        private String d;

        public b(h hVar, String str, String str2, float f) {
            this.a = hVar;
            this.b = str2;
            this.c = f;
            this.d = str;
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.d = str;
        }

        public String toString() {
            return this.b + " time=" + String.format("%.3fms", new Object[]{Float.valueOf(this.c)});
        }
    }

    public h(String str, d dVar) {
        this.e = str;
        this.f = dVar;
    }

    public void a() {
        new a(this, 30, this.e).execute(new Void[0]);
    }
}
