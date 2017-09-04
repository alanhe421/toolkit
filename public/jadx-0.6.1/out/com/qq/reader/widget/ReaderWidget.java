package com.qq.reader.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.qq.reader.TypeContext;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.f;
import com.tencent.feedback.proguard.R;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class ReaderWidget extends AppWidgetProvider {
    public static String a = "com.qq.reader.widget.ReaderWidget.shelf";
    public static String b = "com.qq.reader.widget.ReaderWidget.web";
    public static String c = "com.qq.reader.widget.ReaderWidget.book";
    public static String d = "com.qq.reader.appwidget.action.REFRESH";
    private static boolean v = false;
    private PendingIntent e = null;
    private Intent f = null;
    private PendingIntent g = null;
    private Intent h = null;
    private int[] i = new int[]{R.id.widget_book1, R.id.widget_book2, R.id.widget_book3};
    private int[] j = new int[]{R.id.widget_book1_name, R.id.widget_book2_name, R.id.widget_book3_name};
    private int[] k = new int[]{R.id.widget_book1_cover, R.id.widget_book2_cover, R.id.widget_book3_cover};
    private PendingIntent l = null;
    private PendingIntent m = null;
    private PendingIntent n = null;
    private PendingIntent[] o = new PendingIntent[]{this.l, this.m, this.n};
    private Intent p = null;
    private Intent q = null;
    private Intent r = null;
    private Intent[] s = new Intent[]{this.p, this.q, this.r};
    private RemoteViews t = null;
    private ComponentName u = null;

    public void onEnabled(Context context) {
        super.onEnabled(context);
        v = true;
        d.v(context, true);
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
        v = false;
        d.v(context, false);
    }

    private void a(final Context context, boolean z) {
        if (z) {
            g.a().a(new ReaderShortTask() {
                public void run() {
                    super.run();
                    ReaderWidget.this.a(context, ReaderWidget.this.a(context));
                    ReaderWidget.this.b(context, ReaderWidget.this.a(context));
                    AppWidgetManager instance = AppWidgetManager.getInstance(context);
                    if (instance != null) {
                        instance.updateAppWidget(ReaderWidget.this.b(context), ReaderWidget.this.a(context));
                    }
                }
            });
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equalsIgnoreCase(d)) {
            a(context, v);
        } else if (action.toUpperCase().indexOf("APPWIDGET_RESIZE") != -1) {
            a(context, v);
        }
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            a(context, true);
        }
    }

    private void a(Context context, RemoteViews remoteViews) {
        if (this.h == null) {
            this.h = new Intent(context, MainActivity.class);
            this.h.setAction(b);
            this.h.putExtra("main_tab_tag", 1);
            this.h.putExtra("widget", true);
        }
        if (this.g == null) {
            this.g = PendingIntent.getActivity(context, 0, this.h, SigType.WLOGIN_PT4Token);
            remoteViews.setOnClickPendingIntent(R.id.widget_web, this.g);
        }
        if (this.f == null) {
            this.f = new Intent(context, MainActivity.class);
            this.f.setAction(a);
            this.f.putExtra("main_tab_tag", 0);
            this.f.putExtra("widget", true);
        }
        if (this.e == null) {
            this.e = PendingIntent.getActivity(context, 0, this.f, SigType.WLOGIN_PT4Token);
            remoteViews.setOnClickPendingIntent(R.id.widget_shelf, this.e);
        }
    }

    private void b(Context context, RemoteViews remoteViews) {
        int i;
        for (int viewVisibility : this.i) {
            remoteViews.setViewVisibility(viewVisibility, 4);
        }
        List g = i.c().g();
        Collections.sort(g, f.b);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.i.length && i2 < g.size()) {
            Mark mark = (Mark) g.get(i2);
            if (mark instanceof LocalMark) {
                Bitmap bitmap;
                try {
                    bitmap = (Bitmap) com.bumptech.glide.g.b(context).a(mark.getImageURI()).j().a().d(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    bitmap = null;
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                    bitmap = null;
                }
                if (bitmap == null) {
                    remoteViews.setImageViewResource(this.k[i3], ao.o(mark.getBookName()));
                    remoteViews.setTextViewText(this.j[i3], ((Mark) g.get(i2)).getBookShortName());
                } else {
                    remoteViews.setTextViewText(this.j[i3], null);
                    remoteViews.setImageViewBitmap(this.k[i3], bitmap);
                }
                Bundle bundle = new Bundle();
                bundle.putString("filepath", mark.getId());
                bundle.putString("filename", mark.getBookName());
                bundle.putString("fileauthor", mark.getAuthor());
                bundle.putInt("fileencode", mark.getEncoding());
                bundle.putBoolean("widget", true);
                int type = mark.getType();
                bundle.putInt("marktype", type);
                if (this.s[i3] == null) {
                    this.s[i3] = new Intent(context, TypeContext.class);
                    this.s[i3].setAction("book" + i3);
                }
                this.s[i3].putExtras(bundle);
                if (4 == type) {
                    this.s[i3].putExtra("com.qq.reader.OnlineTag", v.b().a(mark.getId()));
                    this.s[i3].putExtra("com.qq.reader.fromonline", true);
                }
                if (this.o[i3] == null) {
                    this.o[i3] = PendingIntent.getActivity(context, 0, this.s[i3], SigType.WLOGIN_PT4Token);
                    remoteViews.setOnClickPendingIntent(this.i[i3], this.o[i3]);
                }
                remoteViews.setViewVisibility(this.i[i3], 0);
                i = i3 + 1;
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        v = false;
        a(true);
    }

    private void a(boolean z) {
        int i = 0;
        this.h = null;
        this.g = null;
        this.f = null;
        this.e = null;
        if (z) {
            this.t = null;
            for (int i2 = 0; i2 < this.s.length; i2++) {
                this.s[i2] = null;
            }
            while (i < this.o.length) {
                this.o[i] = null;
                i++;
            }
        }
        this.u = null;
    }

    private RemoteViews a(Context context) {
        if (this.t == null) {
            this.t = new RemoteViews(context.getPackageName(), R.layout.reader_widget);
        }
        return this.t;
    }

    private ComponentName b(Context context) {
        if (this.u == null) {
            this.u = new ComponentName(context, ReaderWidget.class);
        }
        return this.u;
    }
}
