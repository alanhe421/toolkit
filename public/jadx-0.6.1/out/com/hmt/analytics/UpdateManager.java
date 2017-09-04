package com.hmt.analytics;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import com.hmt.analytics.common.CommonUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateManager {
    public static Context b;
    public static String c;
    public static ProgressDialog d;
    public static String e = null;
    public static String g = now();
    private static String h = "Found  new version , update?";
    private static String i = null;
    private static Dialog j;
    private static String k = null;
    private static int l;
    private static Thread m;
    private static boolean n = false;
    private static Handler o = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    UpdateManager.d.setProgress(UpdateManager.l);
                    return;
                case 2:
                    UpdateManager.installApk();
                    return;
                default:
                    return;
            }
        }
    };
    private static Runnable p = new Runnable() {
        public void run() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(UpdateManager.e).openConnection();
                httpURLConnection.connect();
                int contentLength = httpURLConnection.getContentLength();
                InputStream inputStream = httpURLConnection.getInputStream();
                File file = new File("/sdcard/");
                if (!Environment.getExternalStorageState().equals("mounted")) {
                    UpdateManager.showSdDialog(UpdateManager.b);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(UpdateManager.k));
                byte[] bArr = new byte[1024];
                int i = 0;
                do {
                    int read = inputStream.read(bArr);
                    i += read;
                    UpdateManager.l = (int) ((((float) i) / ((float) contentLength)) * 100.0f);
                    UpdateManager.o.sendEmptyMessage(1);
                    if (read <= 0) {
                        UpdateManager.d.dismiss();
                        UpdateManager.o.sendEmptyMessage(2);
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } while (!UpdateManager.n);
                fileOutputStream.close();
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    };
    String a;
    public String f;

    class AnonymousClass3 implements OnClickListener {
        private final /* synthetic */ Context a;

        AnonymousClass3(Context context) {
            this.a = context;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            UpdateManager.showDownloadDialog(this.a);
        }
    }

    public static String now() {
        Time time = new Time("Asia/Beijing");
        time.setToNow();
        return time.format("%Y-%m-%d");
    }

    public UpdateManager(Context context, String str, String str2, String str3, String str4) {
        this.a = CommonUtil.getAppKey(context);
        this.f = str;
        c = str2;
        e = str3;
        b = context;
        i = h + "\n" + str + ":" + str4;
        k = "/sdcard/" + g;
    }

    public static void showNoticeDialog(Context context) {
        CommonUtil.printLog("message", e);
        Builder builder = new Builder(context);
        builder.setTitle("Update software");
        builder.setMessage(i);
        builder.setPositiveButton("OK", new AnonymousClass3(context));
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (UpdateManager.c.equals("true")) {
                    System.exit(0);
                } else {
                    dialogInterface.dismiss();
                }
            }
        });
        j = builder.create();
        j.show();
    }

    public static void showSdDialog(Context context) {
        Builder builder = new Builder(context);
        builder.setTitle("point");
        builder.setMessage("SD card does not exist");
        builder.setNegativeButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        j = builder.create();
        j.show();
    }

    private static void showDownloadDialog(Context context) {
        d = new ProgressDialog(context);
        d.setTitle("Update software");
        d.setProgressStyle(1);
        d.setButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                UpdateManager.n = true;
            }
        });
        d.show();
        downloadApk();
    }

    private static void downloadApk() {
        m = new Thread(p);
        m.start();
    }

    private static void installApk() {
        File file = new File(k);
        if (file.exists()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            b.startActivity(intent);
        }
    }
}
