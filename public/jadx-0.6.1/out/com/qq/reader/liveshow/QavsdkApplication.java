package com.qq.reader.liveshow;

import android.app.Application;
import android.content.Context;
import com.qq.reader.liveshow.c.c;
import com.qq.reader.liveshow.utils.o;

public class QavsdkApplication extends Application {
    private static QavsdkApplication app;
    private static Context context;

    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();
        o.a(getApplicationContext());
        c.a(context);
    }

    public static Context getContext() {
        return context;
    }

    public static QavsdkApplication getInstance() {
        return app;
    }
}
