package com.qq.reader.liveshow.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.qq.reader.liveshow.utils.SxbLog;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.e;
import okhttp3.f;
import okhttp3.y;

/* compiled from: RequestListener */
public abstract class m<T> implements f {
    private Class<T> a = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    private Gson b = new GsonBuilder().registerTypeAdapter(this.a, new JsonDeserializer<T>(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            T fromJson;
            try {
                fromJson = new Gson().fromJson(jsonElement, type);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                fromJson = null;
            }
            if (fromJson == null) {
                try {
                    fromJson = this.a.a.newInstance();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
            return fromJson;
        }
    }).create();
    private Handler c = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ m a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    this.a.a((Exception) message.obj);
                    return;
                case 1:
                    this.a.a(message.arg1, (String) message.obj);
                    return;
                case 2:
                    this.a.a(message.arg1, message.obj);
                    return;
                default:
                    return;
            }
        }
    };

    public abstract void a(int i, T t);

    public abstract void a(int i, String str);

    public abstract void a(Exception exception);

    public final void a(e eVar, IOException iOException) {
        this.c.obtainMessage(0, iOException).sendToTarget();
    }

    public final void a(e eVar, y yVar) throws IOException {
        try {
            String e = yVar.g().e();
            SxbLog.a("OKHTTP", yVar.toString() + "   ---->   result = " + e);
        } catch (Exception e2) {
            this.c.obtainMessage(0, e2).sendToTarget();
            return;
        } finally {
            yVar.g().close();
        }
        try {
            if (yVar.c()) {
                this.c.obtainMessage(2, yVar.b(), 1, this.b.fromJson(e, this.a)).sendToTarget();
                return;
            }
            this.c.obtainMessage(1, yVar.b(), 0, e).sendToTarget();
        } catch (JsonSyntaxException e3) {
            SxbLog.e("OKHTTP", e3.getMessage());
            this.c.obtainMessage(0, e3).sendToTarget();
        }
    }
}
