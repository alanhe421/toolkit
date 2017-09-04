package com.qq.reader.common.readertask.protocol;

import android.util.Log;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReadPageCorrectTask extends ReaderProtocolJSONTask {

    public static class a {
        long a;
        long b;
        long c;
        long d;
        long e;
        int f;
        long g;
        private String h;

        public a a(long j) {
            this.a = j;
            return this;
        }

        public a b(long j) {
            this.b = j;
            return this;
        }

        public a c(long j) {
            this.c = j;
            return this;
        }

        public a d(long j) {
            this.d = j;
            return this;
        }

        public a e(long j) {
            this.e = j;
            return this;
        }

        public a a(int i) {
            this.f = i;
            return this;
        }

        public a a(String str) {
            this.h = str;
            return this;
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                stringBuilder.append("?").append("bid=").append(this.a).append(GetVoteUserIconsTask.CID).append(this.b).append("&pos1=").append(this.d).append("&pos2=").append(this.e).append("&errortype=").append(this.f).append("&uuid=").append(this.g).append("&errordesc=").append(URLEncoder.encode(this.h, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        public a f(long j) {
            this.g = j;
            return this;
        }
    }

    public ReadPageCorrectTask(c cVar, a aVar) {
        super(cVar);
        this.mUrl = e.ci + aVar.a();
        Log.e("ReadPageCorrectTask url", this.mUrl);
    }

    public String getRequestMethod() {
        return super.getRequestMethod();
    }
}
