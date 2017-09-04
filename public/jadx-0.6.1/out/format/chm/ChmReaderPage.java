package format.chm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.zxing.common.StringUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.h;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.ProgressBar;
import com.qq.reader.view.ab;
import com.qq.reader.view.ab.b;
import com.qq.reader.view.af;
import com.qq.reader.view.g;
import com.qq.reader.view.p;
import com.qq.reader.view.x;
import com.qq.reader.view.z;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.midas.data.APMidasPluginInfo;
import com.tencent.tinker.android.dex.DexFormat;
import format.chm.core.ChmEntry;
import format.chm.core.ChmEntry.Attribute;
import format.chm.core.ChmFile;
import format.epub.common.utils.f;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;

public class ChmReaderPage extends Activity {
    static int g = 0;
    private final String[] A = new String[]{"/index.html", "/index.htm", "/default.html", "/default.htm"};
    private boolean B = false;
    private boolean C = false;
    private int D = -1;
    private volatile String E = "";
    private z F;
    private x G;
    private g H;
    private boolean I = false;
    String a = a.bb;
    String b;
    String c;
    String d;
    String e;
    WebView f;
    ChmEntry[] h;
    Attribute i;
    ChmFile j;
    a k;
    String l;
    String m;
    String n;
    String o = "";
    volatile ArrayList<String> p = new ArrayList();
    private ProgressBar q;
    private ab r;
    private final int s = 0;
    private final int t = 1;
    private final int u = 2;
    private final int v = 3;
    private final int w = 4;
    private final int x = 5;
    private p y;
    private Handler z = null;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.qq.reader.appconfig.a.a.a().b();
        n();
        b();
        i.a("event_readbook", null, getApplicationContext());
        StatisticsManager.a().a("event_readbook", null);
        d.n = d.P(getApplicationContext());
        requestWindowFeature(5);
        setContentView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.chm_main, null, false));
        setProgressBarIndeterminateVisibility(true);
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            this.d = intent.getData().getPath();
            if (!(this.d == null || this.d.equals(""))) {
                this.e = this.d.substring(this.d.lastIndexOf("/") + 1, this.d.length());
            }
        } else {
            Bundle extras = getIntent().getExtras();
            this.d = extras.getString("filepath");
            this.e = extras.getString("filename");
            if (!(this.d == null || this.d.equals(""))) {
                this.e = this.d.substring(this.d.lastIndexOf("/") + 1, this.d.length());
            }
        }
        if (this.d == null || this.d.length() == 0) {
            l();
        } else {
            this.a = a.bb + this.e + "/";
            this.b = a.bb + this.e;
            this.c = "file://" + this.b;
            com.qq.reader.common.utils.ab.a(new File(this.a));
        }
        this.q = (ProgressBar) findViewById(R.id.webloadprogress);
        this.f = (WebView) findViewById(R.id.webview);
        this.f.getSettings().setBuiltInZoomControls(true);
        int aj = d.aj(getApplicationContext());
        if (aj > 0) {
            this.f.setInitialScale(aj);
        }
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setDefaultTextEncodingName(StringUtils.GB2312);
        this.f.removeJavascriptInterface("searchBoxJavaBridge_");
        this.f.removeJavascriptInterface("accessibility");
        this.f.removeJavascriptInterface("accessibilityTraversal");
        this.f.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
                this.a.setTitle("Loading..." + i + "%");
                this.a.setProgressBarIndeterminateVisibility(true);
                this.a.setProgress(i * 100);
                this.a.q.setProgress((double) i);
                if (i == 100) {
                    this.a.setProgressBarIndeterminateVisibility(false);
                    if (this.a.f != null) {
                        this.a.f.invalidate();
                    }
                }
            }
        });
        this.f.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public void onLoadResource(WebView webView, String str) {
                if (!this.a.C) {
                    String a = this.a.c(str);
                    if (a.startsWith("file://") && !new File(a.substring("file://".length())).exists() && a.startsWith(this.a.c)) {
                        this.a.B = true;
                        try {
                            String substring = a.substring(this.a.c.length());
                            for (ChmEntry chmEntry : this.a.h) {
                                if (substring.toLowerCase().startsWith(chmEntry.getPath().toLowerCase())) {
                                    this.a.a(substring, chmEntry);
                                }
                            }
                        } catch (Exception e) {
                            Log.i("CHM", "encodeUrl =" + a);
                        }
                    }
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                int scale = (int) (100.0f * webView.getScale());
                if (scale > 0) {
                    webView.setInitialScale(scale);
                }
                this.a.b(this.a.c(str));
                if (this.a.q.getVisibility() != 0) {
                    this.a.q.setVisibility(0);
                }
            }

            public void onPageFinished(WebView webView, String str) {
                this.a.E = this.a.c(str);
                if (this.a.q.getVisibility() != 4) {
                    this.a.q.setVisibility(4);
                }
                if (this.a.B) {
                    this.a.B = false;
                    this.a.C = true;
                    if (this.a.f != null) {
                        this.a.f.reload();
                    }
                } else {
                    this.a.C = false;
                }
                webView.getSettings().getDefaultTextEncodingName();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String a = this.a.c(str);
                if (a.startsWith("file://")) {
                    String str2;
                    if (!a.startsWith(this.a.c)) {
                        String str3 = "file://" + am.b() + "/";
                        str2 = "file://" + a.bb;
                        if (a.startsWith(str2)) {
                            a = this.a.c + "/" + a.substring(str2.length());
                        } else if (a.startsWith(str3)) {
                            a = this.a.c + "/" + a.substring(str3.length());
                        }
                    }
                    if (!new File(a.substring("file://".length())).exists() && a.startsWith(this.a.c)) {
                        str2 = a.substring(this.a.c.length());
                        for (ChmEntry chmEntry : this.a.h) {
                            if (str2.toLowerCase().startsWith(chmEntry.getPath().toLowerCase())) {
                                this.a.a(str2, chmEntry);
                            }
                        }
                    }
                    webView.loadUrl(a);
                }
                return true;
            }
        });
        this.z = new Handler(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1114:
                        if (message.arg1 == 0 || message.arg1 == 1) {
                            this.a.b(message.arg1);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        h.a().a(5);
        h.a().g();
    }

    public String a() {
        DataInputStream dataInputStream;
        EOFException e;
        DataInputStream dataInputStream2;
        IOException e2;
        Throwable th;
        String str = null;
        String str2 = this.b + "/#WINDOWS";
        String str3 = this.b + "/#STRINGS";
        File file = new File(str2);
        File file2 = new File(str3);
        if (file.exists() && file2.exists()) {
            FileInputStream fileInputStream;
            FileInputStream fileInputStream2;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    int read;
                    dataInputStream = new DataInputStream(fileInputStream);
                    try {
                        dataInputStream.skipBytes(28);
                        dataInputStream.read();
                        dataInputStream.skipBytes(75);
                        dataInputStream.skipBytes(8);
                        read = dataInputStream.read();
                        fileInputStream.close();
                        dataInputStream.close();
                        fileInputStream2 = new FileInputStream(str3);
                    } catch (EOFException e3) {
                        e = e3;
                        dataInputStream2 = null;
                        fileInputStream2 = null;
                        try {
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e222222) {
                                    e222222.printStackTrace();
                                }
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (IOException e2222222) {
                                    e2222222.printStackTrace();
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e22222222) {
                                    e22222222.printStackTrace();
                                }
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e222222222) {
                                    e222222222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e4) {
                        e222222222 = e4;
                        dataInputStream2 = null;
                        fileInputStream2 = null;
                        e222222222.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2222222222) {
                                e2222222222.printStackTrace();
                            }
                        }
                        if (dataInputStream != null) {
                            try {
                                dataInputStream.close();
                            } catch (IOException e22222222222) {
                                e22222222222.printStackTrace();
                            }
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e222222222222) {
                                e222222222222.printStackTrace();
                            }
                        }
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (IOException e2222222222222) {
                                e2222222222222.printStackTrace();
                            }
                        }
                        return str;
                    } catch (Throwable th3) {
                        dataInputStream2 = null;
                        fileInputStream2 = null;
                        th = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        throw th;
                    }
                    try {
                        dataInputStream2 = new DataInputStream(fileInputStream2);
                        try {
                            dataInputStream2.skip((long) read);
                            byte[] bArr = new byte[1024];
                            do {
                            } while (dataInputStream2.read(bArr) > 0);
                            String str4 = new String(bArr, "GBK");
                            str = str4.substring(0, str4.indexOf(DexFormat.MAGIC_SUFFIX));
                            fileInputStream2.close();
                            dataInputStream2.close();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e22222222222222) {
                                    e22222222222222.printStackTrace();
                                }
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (IOException e222222222222222) {
                                    e222222222222222.printStackTrace();
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2222222222222222) {
                                    e2222222222222222.printStackTrace();
                                }
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e22222222222222222) {
                                    e22222222222222222.printStackTrace();
                                }
                            }
                        } catch (EOFException e5) {
                            e = e5;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (dataInputStream != null) {
                                dataInputStream.close();
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (dataInputStream2 != null) {
                                dataInputStream2.close();
                            }
                            return str;
                        } catch (IOException e6) {
                            e22222222222222222 = e6;
                            e22222222222222222.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (dataInputStream != null) {
                                dataInputStream.close();
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (dataInputStream2 != null) {
                                dataInputStream2.close();
                            }
                            return str;
                        }
                    } catch (EOFException e7) {
                        e = e7;
                        dataInputStream2 = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        return str;
                    } catch (IOException e8) {
                        e22222222222222222 = e8;
                        dataInputStream2 = null;
                        e22222222222222222.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        return str;
                    } catch (Throwable th32) {
                        dataInputStream2 = null;
                        th = th32;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        throw th;
                    }
                } catch (EOFException e9) {
                    e = e9;
                    dataInputStream2 = null;
                    dataInputStream = null;
                    fileInputStream2 = null;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    return str;
                } catch (IOException e10) {
                    e22222222222222222 = e10;
                    dataInputStream2 = null;
                    dataInputStream = null;
                    fileInputStream2 = null;
                    e22222222222222222.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    return str;
                } catch (Throwable th322) {
                    dataInputStream2 = null;
                    dataInputStream = null;
                    fileInputStream2 = null;
                    th = th322;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    throw th;
                }
            } catch (EOFException e11) {
                e = e11;
                dataInputStream2 = null;
                dataInputStream = null;
                fileInputStream2 = null;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                return str;
            } catch (IOException e12) {
                e22222222222222222 = e12;
                dataInputStream2 = null;
                dataInputStream = null;
                fileInputStream2 = null;
                fileInputStream = null;
                e22222222222222222.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                return str;
            } catch (Throwable th3222) {
                dataInputStream2 = null;
                dataInputStream = null;
                fileInputStream2 = null;
                fileInputStream = null;
                th = th3222;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th;
            }
        }
        return str;
    }

    private void h() {
        String str;
        String str2 = null;
        try {
            this.j = new ChmFile(this.d);
            this.i = Attribute.ALL;
            this.h = this.j.a(null, this.i);
            str = "/#";
            g = 0;
            while (g < this.h.length) {
                if (this.h[g].toString().substring(0, 2).equals(str)) {
                    if (this.h[g].toString().equals("/#SYSTEM")) {
                        a(this.a, this.h[g], this.i);
                        this.k = new a(this.a + "#SYSTEM");
                        this.k.a();
                        this.l = "/" + this.k.e;
                    }
                    if (this.h[g].toString().equals("/#WINDOWS")) {
                        a(this.a, this.h[g], this.i);
                    }
                    if (this.h[g].toString().equals("/#STRINGS")) {
                        a(this.a, this.h[g], this.i);
                    }
                }
                if (this.h[g].toString().toLowerCase().endsWith("hhc")) {
                    a(this.a, this.h[g], this.i);
                    this.n = this.h[g].toString();
                }
                g++;
            }
            if (this.k != null && this.k.e != null && this.k.e.length() == 0) {
                str = a();
                if (str == null) {
                    str = "";
                    for (ChmEntry path : this.h) {
                        String path2 = path.getPath();
                        if (path2.equals(this.A[0]) || path2.equals(this.A[1]) || path2.equals(this.A[2]) || path2.equals(this.A[3])) {
                            this.l = path2;
                            break;
                        }
                    }
                } else {
                    this.l = "/" + str;
                }
            }
        } catch (IOException e) {
            Log.e("CHM", APMidasPluginInfo.LAUNCH_INTERFACE_INIT + e.toString());
            l();
        }
        Mark b = com.qq.reader.common.db.handle.i.c().b(this.d, false);
        if (b != null) {
            str2 = b.getStarPointStr();
        }
        this.m = "file://" + this.b + this.l;
        if (str2 == null || !str2.startsWith("file://")) {
            str2 = c(this.m);
        } else {
            str2 = c(str2);
        }
        if (!new File(str2.substring("file://".length())).exists() && str2.startsWith(this.c)) {
            String substring = str2.substring(this.c.length());
            str = "";
            for (ChmEntry chmEntry : this.h) {
                if (substring.startsWith(chmEntry.getPath())) {
                    a(substring, chmEntry);
                    break;
                }
            }
        }
        a(this.b + this.n);
        this.E = c(str2);
        this.f.post(new Runnable(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f.loadUrl(this.a.E);
            }
        });
    }

    public void a(String str) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), "GBK"));
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    if (readLine.contains("<param name=\"Local\"")) {
                        readLine = readLine.substring(readLine.lastIndexOf("value=\""));
                        readLine = readLine.substring(7, readLine.length() - 2).replaceAll("%20", " ");
                        if (readLine.toLowerCase().contains(".html") || readLine.toLowerCase().contains(".htm")) {
                            this.p.add(readLine);
                        }
                    }
                }
                if (this.p.size() <= 2) {
                    this.p.clear();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    private void b(String str) {
        if (this.p != null && this.p.size() > 0 && str != null) {
            String[] a = ao.a(str.toLowerCase(), ".chm/");
            if (a.length == 2) {
                for (int i = 0; i < this.p.size(); i++) {
                    if (a[1].equalsIgnoreCase((String) this.p.get(i))) {
                        this.D = i;
                        return;
                    }
                }
            }
            this.D = -1;
        }
    }

    private String c(String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private void a(String str, ChmEntry chmEntry) {
        a(this.b + str, str, chmEntry);
    }

    protected void onResume() {
        super.onResume();
        ao.a(this);
        ao.b(this);
        setRequestedOrientation(d.x);
    }

    public void b() {
        c();
        getWindow().setFlags(1024, d.ao(getApplicationContext()) ? 1024 : 0);
    }

    public void c() {
        requestWindowFeature(1);
    }

    private static void a(String str, ChmEntry chmEntry, Attribute attribute) throws IOException {
        InputStream inputStream;
        OutputStream fileOutputStream;
        Throwable e;
        InputStream inputStream2 = null;
        File file = new File(str, chmEntry.getPath());
        if (!chmEntry.hasAttribute(Attribute.DIRECTORY)) {
            try {
                inputStream = chmEntry.getInputStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                    inputStream2 = inputStream;
                    try {
                        Log.e("test chm", "error", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                            }
                        }
                        if (inputStream2 == null) {
                            inputStream2.close();
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        inputStream = inputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw e;
                    }
                } catch (Throwable th4) {
                    e = th4;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw e;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e3) {
                    e = e3;
                    inputStream2 = inputStream;
                    Log.e("test chm", "error", e);
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream2 == null) {
                        inputStream2.close();
                    }
                } catch (Throwable th6) {
                    e = th6;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw e;
                }
            } catch (IOException e4) {
                e = e4;
                fileOutputStream = null;
                Log.e("test chm", "error", e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream2 == null) {
                    inputStream2.close();
                }
            } catch (Throwable th7) {
                e = th7;
                fileOutputStream = null;
                inputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw e;
            }
        } else if (!file.isDirectory() && !com.qq.reader.common.utils.ab.b(file)) {
            throw new IOException("failed to create directory : " + file);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (d.ao(getApplicationContext())) {
            getWindow().addFlags(2048);
        }
        d();
        j.a(0, 1);
        return super.onPrepareOptionsMenu(menu);
    }

    public void d() {
        f().f_();
        g().f_();
        e().f_();
    }

    private x i() {
        if (this.G == null) {
            this.G = new x(this, R.layout.readpage_topbar_popup_menu, R.id.readpage_topbar_popup, R.id.menulist, 7);
            this.G.a(getResources().getString(R.string.readpage_topbar_share), R.drawable.readpage_topbar_share, 1001);
            this.G.a(new com.qq.reader.view.a.a(this) {
                final /* synthetic */ ChmReaderPage a;

                {
                    this.a = r1;
                }

                public boolean a(int i) {
                    this.a.r.cancel();
                    switch (i) {
                        case 1001:
                            Log.i("craft", "分享");
                            com.qq.reader.cservice.b.a.a(this.a, ao.l(this.a.d));
                            break;
                    }
                    return false;
                }
            });
        }
        return this.G;
    }

    private void j() {
        this.r.cancel();
        a(5);
    }

    public z e() {
        if (this.F == null) {
            this.F = new z(this, Constants.ERRORCODE_UNKNOWN, k(), null);
            this.F.a(new z.a(this) {
                final /* synthetic */ ChmReaderPage a;

                {
                    this.a = r1;
                }

                public void a(int i, View view) {
                    switch (i) {
                        case 1000:
                            this.a.j();
                            return;
                        case 1003:
                            if (this.a.i().f()) {
                                this.a.i().cancel();
                                return;
                            } else {
                                this.a.i().f_();
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
        }
        return this.F;
    }

    private boolean k() {
        return true;
    }

    public ab f() {
        this.r = new ab(this, 5);
        if (k()) {
            int i;
            this.r.a(0, "主页", R.drawable.chm_icon_home);
            if (this.f == null || !this.f.canGoBack()) {
                i = R.drawable.chm_icon_back_press;
            } else {
                i = R.drawable.chm_icon_back;
            }
            this.r.a(1, "后退", i);
            if (this.p.size() <= 0 || this.D >= this.p.size() - 1) {
                i = R.drawable.chm_icon_forward_press;
            } else {
                i = R.drawable.chm_icon_forward;
            }
            this.r.a(2, "下一章", i);
            this.r.a(3, "亮度", R.drawable.chm_icon_brightness_selector);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.r.a(4, "竖屏", R.drawable.menu_icon_landscape);
            } else {
                this.r.a(4, "横屏", R.drawable.menu_icon_landscape);
            }
        } else {
            this.r.a(5, "返回", R.drawable.readpage_topbar_back);
            this.r.i();
        }
        this.r.a(new b(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public boolean a(int i) {
                return this.a.a(i);
            }
        });
        this.r.a(new OnCancelListener(this) {
            final /* synthetic */ ChmReaderPage a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.i().f()) {
                    this.a.i().cancel();
                }
                if (this.a.e().f()) {
                    this.a.e().cancel();
                }
                if (this.a.g().f()) {
                    this.a.g().cancel();
                }
                if (d.ao(this.a.getApplicationContext())) {
                    this.a.getWindow().clearFlags(2048);
                }
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.r;
    }

    protected boolean a(int i) {
        int i2 = 0;
        String c;
        String substring;
        String str;
        ChmEntry[] chmEntryArr;
        int length;
        ChmEntry chmEntry;
        switch (i) {
            case 0:
                c = c(this.m);
                if (!new File(c.substring("file://".length())).exists() && c.startsWith(this.c)) {
                    substring = c.substring(this.c.length());
                    str = "";
                    chmEntryArr = this.h;
                    length = chmEntryArr.length;
                    while (i2 < length) {
                        chmEntry = chmEntryArr[i2];
                        if (substring.startsWith(chmEntry.getPath())) {
                            a(substring, chmEntry);
                        }
                        i2++;
                    }
                }
                if (this.f == null) {
                    return true;
                }
                this.f.loadUrl(c);
                return true;
            case 1:
                if (this.f == null || !this.f.canGoBack()) {
                    af.a(getApplicationContext(), "不能再后退了", 0).a();
                    return true;
                }
                this.f.goBack();
                return true;
            case 2:
                if (this.p.size() <= 0 || this.D >= this.p.size() - 1) {
                    af.a(getApplicationContext(), "已经是最后一页", 0).a();
                    return true;
                }
                this.D++;
                c = c("file://" + this.b + "/" + ((String) this.p.get(this.D)));
                substring = c.substring(this.c.length());
                str = "";
                chmEntryArr = this.h;
                length = chmEntryArr.length;
                while (i2 < length) {
                    chmEntry = chmEntryArr[i2];
                    if (substring.toLowerCase().startsWith(chmEntry.getPath().toLowerCase())) {
                        a(substring, chmEntry);
                    }
                    i2++;
                }
                if (this.f == null) {
                    return true;
                }
                this.f.loadUrl(c);
                return true;
            case 3:
                m().f_();
                i.a("event_B11", null, getApplicationContext());
                return true;
            case 4:
                int i3;
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    i3 = 0;
                } else {
                    boolean z = true;
                }
                Message obtain = Message.obtain();
                obtain.what = 1114;
                if (i3 == 0) {
                    obtain.arg1 = 1;
                } else {
                    obtain.arg1 = 0;
                }
                this.z.sendMessage(obtain);
                i.a("event_B10", null, this);
                return true;
            case 5:
                l();
                return true;
            default:
                return false;
        }
    }

    private void a(String str, String str2, ChmEntry chmEntry) {
        InputStream inputStream;
        OutputStream fileOutputStream;
        Throwable e;
        InputStream inputStream2;
        InputStream inputStream3;
        OutputStream outputStream = null;
        try {
            if (!new File(str).exists()) {
                byte[] bArr;
                int read;
                if (chmEntry.hasAttribute(Attribute.DIRECTORY) && chmEntry.hasAttribute(Attribute.NORMAL)) {
                    com.qq.reader.common.utils.ab.a(new File(str.substring(0, str.lastIndexOf("/") + 1)));
                    Attribute attribute = Attribute.ALL;
                    String str3 = "";
                    for (ChmEntry chmEntry2 : this.j.a(chmEntry, attribute)) {
                        String path = chmEntry2.getPath();
                        if (chmEntry2.hasAttribute(Attribute.DIRECTORY) && chmEntry2.hasAttribute(Attribute.NORMAL) && str2.startsWith(path)) {
                            a(str, str2, chmEntry2);
                            return;
                        }
                        if (str2.equalsIgnoreCase(chmEntry2.getPath())) {
                            File file = new File(this.b, chmEntry2.getPath());
                            if (chmEntry2.hasAttribute(Attribute.FILE) && chmEntry2.hasAttribute(Attribute.NORMAL)) {
                                try {
                                    inputStream = chmEntry2.getInputStream();
                                    try {
                                        fileOutputStream = new FileOutputStream(file);
                                    } catch (IOException e2) {
                                        e = e2;
                                        fileOutputStream = null;
                                        inputStream2 = inputStream;
                                        try {
                                            Log.e("CHM", "error", e);
                                            if (fileOutputStream != null) {
                                                fileOutputStream.close();
                                            }
                                            if (inputStream2 == null) {
                                                inputStream2.close();
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th) {
                                            e = th;
                                            inputStream = inputStream2;
                                            if (fileOutputStream != null) {
                                                fileOutputStream.close();
                                            }
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            throw e;
                                        }
                                    } catch (Throwable th2) {
                                        e = th2;
                                        fileOutputStream = null;
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        throw e;
                                    }
                                    try {
                                        bArr = new byte[1024];
                                        while (true) {
                                            read = inputStream.read(bArr);
                                            if (read <= 0) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, read);
                                            fileOutputStream.flush();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (inputStream != null) {
                                            inputStream.close();
                                            return;
                                        }
                                        return;
                                    } catch (IOException e3) {
                                        e = e3;
                                        inputStream2 = inputStream;
                                        Log.e("CHM", "error", e);
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (inputStream2 == null) {
                                            inputStream2.close();
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th3) {
                                        e = th3;
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        throw e;
                                    }
                                } catch (IOException e4) {
                                    e = e4;
                                    fileOutputStream = null;
                                    Log.e("CHM", "error", e);
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (inputStream2 == null) {
                                        inputStream2.close();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th4) {
                                    e = th4;
                                    fileOutputStream = null;
                                    inputStream = null;
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw e;
                                }
                            }
                        }
                    }
                } else if (chmEntry.hasAttribute(Attribute.FILE) && chmEntry.hasAttribute(Attribute.NORMAL)) {
                    com.qq.reader.common.utils.ab.a(new File(str.substring(0, str.lastIndexOf("/") + 1)));
                    try {
                        File file2 = new File(this.b, chmEntry.getPath());
                        inputStream = chmEntry.getInputStream();
                        try {
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                bArr = new byte[1024];
                                while (true) {
                                    read = inputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                    fileOutputStream.flush();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (IOException e5) {
                                e = e5;
                                outputStream = fileOutputStream;
                                inputStream3 = inputStream;
                                try {
                                    Log.e("CHM", "error", e);
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    if (inputStream3 == null) {
                                        inputStream3.close();
                                    }
                                } catch (Throwable th5) {
                                    e = th5;
                                    inputStream = inputStream3;
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw e;
                                }
                            } catch (Throwable th6) {
                                e = th6;
                                outputStream = fileOutputStream;
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                throw e;
                            }
                        } catch (IOException e6) {
                            e = e6;
                            inputStream3 = inputStream;
                            Log.e("CHM", "error", e);
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream3 == null) {
                                inputStream3.close();
                            }
                        } catch (Throwable th7) {
                            e = th7;
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw e;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        inputStream3 = null;
                        Log.e("CHM", "error", e);
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream3 == null) {
                            inputStream3.close();
                        }
                    } catch (Throwable th8) {
                        e = th8;
                        inputStream = null;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw e;
                    }
                }
            }
        } catch (Exception e8) {
            Log.e("CHM", "paserNode(pathStr) : " + str + "   " + e8.toString());
        } catch (Throwable th9) {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    protected void onPause() {
        super.onPause();
        Serializable localMark = new LocalMark(this.e, this.d, 1, 1, true);
        localMark.setStarPointStr(this.E);
        Intent intent = new Intent();
        intent.setAction(a.cD);
        intent.putExtra("mark", localMark);
        sendBroadcast(intent);
        d.n(getApplicationContext(), d.s);
        d.o(getApplicationContext(), d.q);
        h.a().i();
        if (getIntent().getBooleanExtra("widget", false)) {
            finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        d.l(getApplicationContext(), d.x);
        h.a().j();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                l();
                return true;
            default:
                return false;
        }
    }

    private void l() {
        this.h = null;
        this.p = null;
        if (this.f != null) {
            d.s(getApplicationContext(), (int) (100.0f * this.f.getScale()));
            this.f.stopLoading();
            this.f.clearCache(false);
            this.f.clearHistory();
            this.f = null;
        }
        finish();
    }

    public p g() {
        if (this.y == null) {
            this.y = new p(this);
            this.y.a(new p.a(this) {
                final /* synthetic */ ChmReaderPage a;

                {
                    this.a = r1;
                }

                public void a() {
                    ao.b(this.a);
                    i.a("event_B4", null, this.a);
                }
            });
        }
        return this.y;
    }

    private g m() {
        if (this.H == null) {
            this.H = new g(this, 2);
            this.H.a(new g.b(this) {
                final /* synthetic */ ChmReaderPage a;

                {
                    this.a = r1;
                }

                public void a() {
                    ao.b(this.a);
                }

                public void b() {
                }
            });
        }
        return this.H;
    }

    private void b(int i) {
        setRequestedOrientation(i);
        d.x = i;
    }

    private void n() {
        b(d.Y(getApplicationContext()));
    }

    public void onWindowFocusChanged(boolean z) {
        if (!this.I) {
            h.a().h();
            f.a(new Runnable(this) {
                final /* synthetic */ ChmReaderPage a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.h();
                }
            }, this);
            this.I = true;
        }
    }

    public void finish() {
        super.finish();
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } catch (Exception e) {
        }
    }
}
