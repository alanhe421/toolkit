package com.tencent.qalhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.dynamicload.Lib.DLConstants;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.InvalidProtocolBufferMicroException;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.qalsdk.QALInitHelper;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.IBaseActionListener;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.im_open.QalMonitor;
import com.tencent.qalsdk.im_open.QalMonitor.Request.Http;
import com.tencent.qalsdk.im_open.http.Pair;
import com.tencent.qalsdk.im_open.http.PairBytes;
import com.tencent.qalsdk.im_open.http.Request;
import com.tencent.qalsdk.im_open.http.Response;
import com.tencent.qalsdk.im_open.http.ResponsePrivate;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.ac;
import com.tencent.qalsdk.sdk.e;
import com.tencent.qalsdk.util.QLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class QALHttpHelper {
    static a cacheHelper = new a();
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final String tag = "QALHttpHelper";
    private String accept = null;
    private String acceptCharset = null;
    private String acceptLanguage = null;
    private long beginTime = 0;
    private byte[] body = null;
    private List<String> cache_control = null;
    private String contentType = null;
    private String cookie = null;
    private Map<String, String> formData;
    private String formDataCharSet;
    LinkedList<QALHttpResponse> frags = new LinkedList();
    QALHttpResponse head = new QALHttpResponse();
    private String if_match = null;
    private String if_modified_since = null;
    private String if_none_match = null;
    private String if_unmodified_since = null;
    int local_total_length = 0;
    private int method = -1;
    private String origin = null;
    private Map<String, String> otherHeaders;
    private String pragma;
    private String referer = null;
    int total_length = -1;
    private String uri = null;
    private String user_agent = null;
    private String x_requested_with = null;

    public QALHttpHelper(String str) {
        this.uri = str;
        this.otherHeaders = new HashMap();
    }

    public boolean setRequestMethod(int i) {
        this.method = i;
        return true;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setAccept(String str) {
        this.accept = str;
    }

    public void setAcceptLanguage(String str) {
        this.acceptLanguage = str;
    }

    public void setAcceptCharset(String str) {
        this.acceptCharset = str;
    }

    public void setUserAgent(String str) {
        this.user_agent = str;
    }

    public void setCookie(String str) {
        this.cookie = str;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setXRequestedWith(String str) {
        this.x_requested_with = str;
    }

    public void setIfModifiedSince(String str) {
        this.if_modified_since = str;
    }

    public void setIfUnmodifiedSince(String str) {
        this.if_unmodified_since = str;
    }

    public void setIfNoneMatch(String str) {
        this.if_none_match = str;
    }

    public void setIfMatch(String str) {
        this.if_match = str;
    }

    public void setCacheControl(List<String> list) {
        this.cache_control = list;
    }

    public void setPragma(String str) {
        this.pragma = str;
    }

    public void setRequestOtherHeader(String str, String str2) {
        this.otherHeaders.put(str, str2);
    }

    public void setFormData(String str, Map<String, String> map) {
        this.formData = map;
        this.contentType = "application/x-www-form-urlencoded;" + str;
    }

    public void setBody(byte[] bArr) {
        this.body = bArr;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Content-Type:");
        stringBuilder.append(this.contentType);
        stringBuilder.append("\n");
        stringBuilder.append("Accept:");
        stringBuilder.append(this.accept);
        stringBuilder.append("\n");
        stringBuilder.append("Accept-Language:");
        stringBuilder.append(this.acceptLanguage);
        stringBuilder.append("\n");
        stringBuilder.append("Accept-Charset:");
        stringBuilder.append(this.acceptCharset);
        stringBuilder.append("\n");
        stringBuilder.append("User-Agent:");
        stringBuilder.append(this.user_agent);
        stringBuilder.append("\n");
        stringBuilder.append("Cookie:");
        stringBuilder.append(this.cookie);
        stringBuilder.append("\n");
        stringBuilder.append("Referer:");
        stringBuilder.append(this.referer);
        stringBuilder.append("\n");
        stringBuilder.append("Origin:");
        stringBuilder.append(this.origin);
        stringBuilder.append("\n");
        stringBuilder.append("X-Requested-With:");
        stringBuilder.append(this.x_requested_with);
        stringBuilder.append("\n");
        stringBuilder.append("If-Modified-Since:");
        stringBuilder.append(this.if_modified_since);
        stringBuilder.append("\n");
        stringBuilder.append("If-Unmodified-Since:");
        stringBuilder.append(this.if_unmodified_since);
        stringBuilder.append("\n");
        stringBuilder.append("If-None-Match:");
        stringBuilder.append(this.if_none_match);
        stringBuilder.append("\n");
        stringBuilder.append("If-Match:");
        stringBuilder.append(this.if_match);
        stringBuilder.append("\n");
        stringBuilder.append("Pragma:");
        stringBuilder.append(this.pragma);
        return stringBuilder.toString();
    }

    public void request(QALHttpValueCallBack qALHttpValueCallBack) {
        QLog.i(tag, 1, "http request:" + this.method + ":" + this.uri);
        this.beginTime = SystemClock.elapsedRealtime();
        QALHttpResponse a = cacheHelper.a(this.method, this.uri);
        if (a != null) {
            QLog.d(tag, 1, "cache hit|qal http sdk resp ok:" + a.getStatus() + DLConstants.DEPENDENCY_PACKAGE_DIV + a.getBody().length + "|sdk costTime|" + cacheHelper.d);
            mainCallSucc(qALHttpValueCallBack, a);
            reportHttp(cacheHelper.d, 0, a.getStatus(), "", true);
            if (cacheHelper.a) {
                QLog.d(tag, 4, " cache hit| but need fresh");
            } else {
                return;
            }
        }
        if (cacheHelper.c != null) {
            this.if_modified_since = cacheHelper.c;
        }
        if (cacheHelper.b != null) {
            this.if_none_match = cacheHelper.b;
        }
        byte[] EncodeRequest = EncodeRequest();
        if (EncodeRequest == null) {
            QLog.e(tag, this.uri + " http encode err");
            return;
        }
        IBaseActionListener bVar = new b(this, qALHttpValueCallBack);
        if (e.b().f() == null) {
            QALInitHelper.instance().addToQueue("qal.http", EncodeRequest, bVar);
        } else {
            e.b().a(e.b().f(), "qal.http", EncodeRequest, bVar, e.b().c(EncodeRequest.length), true);
        }
    }

    private void removeSendMsg(ToServiceMsg toServiceMsg) {
        ac.a().a(toServiceMsg);
        toServiceMsg.setMsfCommand(MsfCommand.httpreq_remove);
        toServiceMsg.setNeedCallback(false);
        ac.a().b(toServiceMsg);
    }

    private void mainCallFail(QALHttpValueCallBack qALHttpValueCallBack, int i, String str) {
        mainHandler.post(new c(this, qALHttpValueCallBack, i, str));
    }

    private void mainCallSucc(QALHttpValueCallBack qALHttpValueCallBack, QALHttpResponse qALHttpResponse) {
        mainHandler.post(new d(this, qALHttpValueCallBack, qALHttpResponse));
    }

    public void onHttpResp(byte[] bArr, ToServiceMsg toServiceMsg, QALHttpValueCallBack qALHttpValueCallBack) {
        Response response = new Response();
        long elapsedRealtime;
        String str;
        try {
            response.mergeFrom(bArr);
            put_frag(DecodeResponse(response));
            if (is_full()) {
                merge_frag();
                removeSendMsg(toServiceMsg);
                if (is_complete()) {
                    if (this.head.responsePrivate.body_encoding.get() == 2 && this.head.getBody().length != 0) {
                        byte[] unzipBody = unzipBody(this.head.getBody());
                        if (unzipBody != null) {
                            this.head.setBody(unzipBody);
                        } else {
                            this.head.setStatus(a.bH);
                        }
                    }
                    elapsedRealtime = SystemClock.elapsedRealtime() - this.beginTime;
                    if (cacheHelper.a) {
                        QLog.d(tag, 4, "cache already return ,server response need no return");
                    } else if (this.head.getStatus() == 304) {
                        QLog.i(tag, 1, this.uri + " 304,return from cache");
                        mainCallSucc(qALHttpValueCallBack, cacheHelper.a(this.uri));
                        reportHttp(cacheHelper.d, elapsedRealtime, 304, "", false);
                    } else if (this.head.getStatus() == a.bH) {
                        str = "body unzip error";
                        QLog.e(tag, 1, "http resp error:" + str);
                        mainCallFail(qALHttpValueCallBack, a.bH, str);
                        reportHttp(cacheHelper.d, elapsedRealtime, a.bH, str, false);
                    } else {
                        QLog.i(tag, 1, this.uri + DLConstants.DEPENDENCY_PACKAGE_DIV + "http resp :" + this.head.getStatus() + DLConstants.DEPENDENCY_PACKAGE_DIV + this.head.getBody().length);
                        mainCallSucc(qALHttpValueCallBack, this.head);
                        reportHttp(cacheHelper.d, elapsedRealtime - cacheHelper.d, this.head.getStatus(), "", false);
                    }
                    cacheHelper.a(this.method, this.uri, this.head);
                    return;
                }
                str = "收包错误,不完整";
                elapsedRealtime = SystemClock.elapsedRealtime() - this.beginTime;
                mainCallFail(qALHttpValueCallBack, a.bI, str);
                QLog.e(tag, this.uri + " http res error:" + a.bI);
                reportHttp(cacheHelper.d, elapsedRealtime, a.bI, str, false);
            }
        } catch (InvalidProtocolBufferMicroException e) {
            e.printStackTrace();
            if (qALHttpValueCallBack != null) {
                str = "http parse rspbody failed";
                elapsedRealtime = SystemClock.elapsedRealtime() - this.beginTime;
                mainCallFail(qALHttpValueCallBack, a.bJ, str);
                reportHttp(cacheHelper.d, elapsedRealtime, 6001, str, false);
                QLog.e(tag, 1, this.uri + "|http resp error:http parse rspbody failed");
            }
        }
    }

    private byte[] EncodeRequest() {
        Request request = new Request();
        if (this.uri == null) {
            QLog.e(tag, 1, "param error: uri null");
            return null;
        }
        request.uri.set(this.uri);
        if (this.method < 1 || this.method > 8) {
            QLog.e(tag, 1, "param error: method wrong");
            return null;
        }
        request.method.set(this.method);
        if (this.contentType != null) {
            request.content_type.set(this.contentType);
        }
        if (this.accept != null) {
            request.accept.set(this.accept);
        }
        if (this.acceptLanguage != null) {
            request.accept_language.set(this.acceptLanguage);
        }
        if (this.acceptCharset != null) {
            request.accept_charset.set(this.acceptCharset);
        }
        if (this.user_agent != null) {
            request.user_agent.set(this.user_agent);
        }
        if (this.cookie != null) {
            request.cookie.set(this.cookie);
        }
        if (this.referer != null) {
            request.referer.set(this.referer);
        }
        if (this.origin != null) {
            request.origin.set(this.origin);
        }
        if (this.x_requested_with != null) {
            request.x_requested_with.set(this.x_requested_with);
        }
        if (this.if_modified_since != null) {
            request.if_modified_since.set(this.if_modified_since);
        }
        if (this.if_unmodified_since != null) {
            request.if_unmodified_since.set(this.if_unmodified_since);
        }
        if (this.if_none_match != null) {
            request.if_none_match.set(this.if_none_match);
        }
        if (this.if_match != null) {
            request.if_match.set(this.if_match);
        }
        if (this.cache_control != null) {
            request.cache_control.set(this.cache_control);
        }
        if (this.pragma != null) {
            request.pragma.set(this.pragma);
        }
        for (String str : this.otherHeaders.keySet()) {
            if (!(str.equals("Accept-Encoding") || str.equals("Connection") || str.equals("Content-Length"))) {
                MessageMicro pair = new Pair();
                pair.key.set(str);
                pair.value.set((String) this.otherHeaders.get(str));
                request.other_headers.add(pair);
            }
        }
        if (this.formData != null) {
            for (String str2 : this.formData.keySet()) {
                pair = new PairBytes();
                pair.key.set(ByteStringMicro.copyFrom(str2.getBytes()));
                pair.value.set(ByteStringMicro.copyFrom(((String) this.formData.get(str2)).getBytes()));
                request.x_www_form.add(pair);
            }
        }
        if (this.body != null) {
            request.body.set(ByteStringMicro.copyFrom(this.body));
            QLog.d(tag, 4, "http reqeust body len:" + this.body.length);
        }
        return request.toByteArray();
    }

    private QALHttpResponse DecodeResponse(Response response) {
        QALHttpResponse qALHttpResponse = new QALHttpResponse();
        qALHttpResponse.setStatus(response.status_code.get());
        qALHttpResponse.setContentType(response.content_type.get());
        qALHttpResponse.setLocation(response.location.get());
        qALHttpResponse.setDate(response.date.get());
        qALHttpResponse.setServer(response.server.get());
        qALHttpResponse.setVia(response.via.get());
        qALHttpResponse.setXCache(response.x_cache.get());
        qALHttpResponse.setXCacheLookup(response.x_cache_lookup.get());
        qALHttpResponse.setAge((long) response.age.get());
        qALHttpResponse.setLastModified(response.last_modified.get());
        qALHttpResponse.setEtag(response.etag.get());
        qALHttpResponse.setCacheControl(response.cache_control.get());
        qALHttpResponse.setExpires(response.expires.get());
        qALHttpResponse.setPragma(response.pragma.get());
        qALHttpResponse.setSetCookie(response.set_cookie.get());
        qALHttpResponse.responsePrivate = (ResponsePrivate) response.private_response.get();
        Map hashMap = new HashMap();
        for (int i = 0; i < response.other_headers.size(); i++) {
            hashMap.put(((Pair) response.other_headers.get(i)).key.get(), ((Pair) response.other_headers.get(i)).value.get());
        }
        qALHttpResponse.setOtherHeaders(hashMap);
        qALHttpResponse.setBody(response.body.get().toByteArray());
        return qALHttpResponse;
    }

    public byte[] unzipBody(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
            byte[] bArr2 = new byte[4096];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    boolean is_complete() {
        long length = this.head == null ? -1 : (long) this.head.getBody().length;
        if (this.total_length < 0 || length != ((long) this.total_length)) {
            return false;
        }
        return true;
    }

    boolean is_full() {
        return this.total_length >= 0 && this.local_total_length == this.total_length;
    }

    void merge_frag() {
        if (!this.frags.isEmpty() && ((QALHttpResponse) this.frags.get(0)).responsePrivate.chunk_start.get() == 0) {
            this.head = (QALHttpResponse) this.frags.get(0);
            this.frags.remove(0);
            for (int i = 0; i < this.frags.size(); i++) {
                QALHttpResponse qALHttpResponse = (QALHttpResponse) this.frags.get(i);
                if (((long) this.head.getBody().length) != qALHttpResponse.responsePrivate.chunk_start.get()) {
                    QLog.e(tag, "respfrag order wrong!");
                    return;
                }
                Object obj = new byte[(this.head.getBody().length + qALHttpResponse.getBody().length)];
                System.arraycopy(this.head.getBody(), 0, obj, 0, this.head.getBody().length);
                System.arraycopy(qALHttpResponse.getBody(), 0, obj, this.head.getBody().length, qALHttpResponse.getBody().length);
                this.head.setBody(obj);
                if (!qALHttpResponse.getSetCookie().isEmpty()) {
                    this.head.setSetCookie(qALHttpResponse.getSetCookie());
                }
                if (qALHttpResponse.responsePrivate.cache_max_age.get() >= 0) {
                    this.head.responsePrivate.cache_max_age.set(qALHttpResponse.responsePrivate.cache_max_age.get());
                }
                if (qALHttpResponse.responsePrivate.cache_max_stale_age.get() >= 0) {
                    this.head.responsePrivate.cache_max_stale_age.set(qALHttpResponse.responsePrivate.cache_max_stale_age.get());
                }
            }
        }
    }

    void put_frag(QALHttpResponse qALHttpResponse) {
        if (this.frags.size() > 0) {
            int size = this.frags.size() - 1;
            while (size >= 0 && ((QALHttpResponse) this.frags.get(size)).responsePrivate.chunk_start.get() >= qALHttpResponse.responsePrivate.chunk_start.get()) {
                size--;
            }
            if (size == this.frags.size() - 1) {
                this.frags.add(qALHttpResponse);
            } else {
                this.frags.add(size + 1, qALHttpResponse);
            }
        } else {
            this.frags.add(qALHttpResponse);
        }
        this.local_total_length += qALHttpResponse.getBody().length;
        QLog.d(tag, "recv frag|" + qALHttpResponse.responsePrivate.chunk_start.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + qALHttpResponse.getBody().length + "|local_len:" + this.local_total_length);
        if (qALHttpResponse.responsePrivate.total_length.get() >= 0) {
            this.total_length = (int) qALHttpResponse.responsePrivate.total_length.get();
        }
    }

    private void reportHttp(long j, long j2, int i, String str, boolean z) {
        if (Math.random() <= 0.1d) {
            try {
                QalMonitor.Request request = new QalMonitor.Request();
                MessageMicro http = new Http();
                http.timestamp.set((int) (System.currentTimeMillis() / 1000));
                http.uri.set(this.uri);
                http.cache_cost.set((int) j);
                http.total_cost.set((int) j2);
                http.code.set(i);
                http.hit_cache.set(z);
                if (e.b().i() != null) {
                    http.apn.set(e.b().i());
                }
                if (e.b().j() != null) {
                    http.gateway_ip.set(e.b().j());
                }
                if (e.b().k() != null) {
                    http.server_ip.set(e.b().k());
                }
                http.radio_access.set(e.b().l());
                http.errmsg.set(str);
                request.http.add(http);
                byte[] toByteArray = request.toByteArray();
                if (toByteArray == null) {
                    QLog.e(tag, "http report pb error");
                    return;
                }
                ToServiceMsg toServiceMsg = new ToServiceMsg("", e.b().f(), a.cs);
                toServiceMsg.setRequestSsoSeq(j.f());
                toServiceMsg.putWupBuffer(toByteArray);
                toServiceMsg.setUinType(20);
                toServiceMsg.setNeedCallback(false);
                toServiceMsg.setAppId(a.bm);
                toServiceMsg.setTimeout(a.ap);
                ac.a().b(toServiceMsg);
                QLog.d(tag, "report http:" + (http.timestamp.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.uri.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.cache_cost.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.total_cost.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.code.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.hit_cache.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.apn.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.radio_access.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.server_ip.get() + DLConstants.DEPENDENCY_PACKAGE_DIV + http.gateway_ip.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
