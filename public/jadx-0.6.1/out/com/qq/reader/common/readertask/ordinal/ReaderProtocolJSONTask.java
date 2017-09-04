package com.qq.reader.common.readertask.ordinal;

import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.monitor.f;
import com.tencent.android.tpush.common.MessageKey;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

public class ReaderProtocolJSONTask extends ReaderProtocolTask {
    protected transient c mListener;
    private int mRefreshLoginTicketTimes = 0;

    public ReaderProtocolJSONTask() {
        super(null);
    }

    public ReaderProtocolJSONTask(c cVar) {
        super(null);
        this.mListener = cVar;
    }

    public void registerNetTaskListener(c cVar) {
        this.mListener = cVar;
    }

    protected void onFinish(HttpResponse httpResponse) throws Exception {
        if (Thread.interrupted()) {
            f.a("thread interrupted", "on finish");
            return;
        }
        InputStream inputStream = null;
        HttpEntity entity = httpResponse.getEntity();
        try {
            if (isResponseGzip()) {
                inputStream = new GZIPInputStream(entity.getContent());
            } else {
                inputStream = entity.getContent();
            }
            long contentLength = entity.getContentLength();
            String a = b.a(inputStream);
            if (this.mListener != null) {
                this.mListener.onConnectionRecieveData(this, a, contentLength);
            }
            onFailedTaskSuccess();
            doConnectionSuccessToRDM(this.isFailedTask);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    protected void onError(Exception exception) {
        if (Thread.interrupted()) {
            f.a("thread interrupted", "on error");
        } else if (!doReConnectFailedTask()) {
            if (this.mListener != null) {
                this.mListener.onConnectionError(this, exception);
            }
            doConnectionErrorToRDM(this.isFailedTask, exception);
        }
    }

    protected String wrapJsonData(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessageKey.MSG_CONTENT, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
