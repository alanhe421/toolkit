package com.qq.reader.cservice.download.audio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.audiobook.core.e;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class SplitDownloadTask extends ReaderIOTask {
    public static final String KEY_AUTHO_INFO = "KEY_AUTHO_INFO";
    private static final String TAG = "SplitDownloadTask";
    private String mAcid;
    private String mAdid;
    private int mBufStart;
    protected long mDecrytedLength;
    protected long mDownloadedLength;
    private Bundle mExtraBundle;
    private final String mFileNamePath;
    private boolean mIsAuthFirst;
    private boolean mIsEncrypted;
    protected boolean mIsRunning;
    private String mKey;
    private d mListener;
    private RequestMsg mRequestMsg;
    private byte[] mRollCryptedBuf;
    private int mState;
    private int mStoredBufLen;
    protected long mTotalLength;
    private RandomAccessFile rdFile;

    public SplitDownloadTask(String str, RequestMsg requestMsg, d dVar) {
        this.rdFile = null;
        this.mState = -2;
        this.mExtraBundle = new Bundle();
        this.mRollCryptedBuf = new byte[51200];
        this.mBufStart = 0;
        this.mStoredBufLen = 0;
        this.mIsAuthFirst = false;
        this.mRequestMsg = requestMsg;
        this.mFileNamePath = str;
        this.mListener = dVar;
        this.mIsRunning = true;
        this.mIsEncrypted = false;
    }

    public SplitDownloadTask(String str, RequestMsg requestMsg, d dVar, boolean z, String str2, String str3, boolean z2) {
        this.rdFile = null;
        this.mState = -2;
        this.mExtraBundle = new Bundle();
        this.mRollCryptedBuf = new byte[51200];
        this.mBufStart = 0;
        this.mStoredBufLen = 0;
        this.mIsAuthFirst = false;
        this.mRequestMsg = requestMsg;
        this.mFileNamePath = str;
        this.mListener = dVar;
        this.mIsRunning = true;
        this.mIsEncrypted = z;
        this.mAcid = str3;
        this.mAdid = str2;
        this.mIsAuthFirst = z2;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    protected boolean prepareDownload() {
        boolean z = true;
        try {
            File file = new File(this.mFileNamePath.substring(0, this.mFileNamePath.lastIndexOf(47) + 1));
            if (!file.exists()) {
                z = file.mkdirs();
            }
            File file2 = new File(this.mFileNamePath);
            if (!file2.exists()) {
                z = file2.createNewFile();
            }
            if (z) {
                this.mDownloadedLength = file2.length();
                this.rdFile = new RandomAccessFile(file2, "rw");
                this.rdFile.seek(this.mDownloadedLength);
                c.e(TAG, "downloadedLength:" + this.mDownloadedLength);
                return z;
            }
            this.rdFile = null;
            return z;
        } catch (Exception e) {
            this.rdFile = null;
            return false;
        }
    }

    public void terminate() {
        c.e(TAG, "last terminate");
        this.mIsRunning = false;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void run() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        IOException iOException;
        Throwable th;
        Throwable th2;
        super.run();
        Process.setThreadPriority(10);
        c.e(TAG, "START RUN");
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection2 = getConnect(this.mRequestMsg);
            if (this.mState == -8 || this.mState == -9 || this.mState == 16 || this.mState == 15) {
                String str = "";
                for (String str2 : this.mRequestMsg.b().split("&")) {
                    String str22;
                    if (str22.indexOf("adid=") != -1) {
                        str22 = str22.split("=")[1];
                    } else if (str22.indexOf("acid=") != -1) {
                        Long.parseLong(str22.split("=")[1]);
                    }
                }
                handleState(this.mState);
                onFailure(this.mState);
                this.mIsRunning = false;
            }
            httpURLConnection = httpURLConnection2;
        } catch (IOException e) {
            this.mState = -2;
            handleState(this.mState);
            onFailure(this.mState);
            this.mIsRunning = false;
            httpURLConnection = httpURLConnection2;
        } catch (Exception e2) {
            this.mState = -2;
            handleState(this.mState);
            onFailure(this.mState);
            this.mIsRunning = false;
            c.e("Error", e2.getMessage());
            httpURLConnection = httpURLConnection2;
        }
        if (prepareDownload()) {
            InputStream inputStream2 = null;
            while (this.mIsRunning && !isReachMaxAutoFailedTime() && !isDownloadComplete()) {
                if (httpURLConnection != null) {
                    if (getCurrentThread().isInterrupted()) {
                        this.mIsRunning = false;
                        c.e(TAG, "interrupted");
                        break;
                    }
                    String headerField;
                    try {
                        this.mFaiedAutoTryedTime++;
                        c.e(TAG, "auto try:" + this.mFaiedAutoTryedTime);
                        handleRange(this.mDownloadedLength);
                        handleState(12);
                        setConnState(0);
                        headerField = httpURLConnection.getHeaderField("Content-Range");
                        if (headerField != null) {
                            this.mTotalLength = getRangeEnd(headerField);
                        } else {
                            this.mTotalLength = (long) httpURLConnection.getContentLength();
                        }
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            handleState(13);
                            byte[] bArr = new byte[16384];
                            while (this.mIsRunning && this.mDownloadedLength < this.mTotalLength) {
                                int read = inputStream.read(bArr, 0, 16384);
                                if (read > 0) {
                                    this.mDownloadedLength += (long) read;
                                    if (this.mIsEncrypted) {
                                        processCryptedData(bArr, 0, read, this.mAdid, this.mAcid);
                                    } else {
                                        processData(bArr, 0, read);
                                    }
                                }
                            }
                            if (!this.mIsRunning || isDownloadComplete()) {
                                if (this.mIsEncrypted) {
                                    processLastCryptedData(this.mAdid, this.mAcid);
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e3) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e4) {
                                    }
                                }
                            } else {
                                this.mFaiedAutoTryedTime = 0;
                                handleRange(this.mDownloadedLength);
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e6) {
                                        inputStream2 = inputStream;
                                    }
                                }
                                inputStream2 = inputStream;
                            }
                        } catch (IOException e7) {
                            iOException = e7;
                            inputStream2 = inputStream;
                        } catch (Throwable th3) {
                            th2 = th3;
                        }
                    } catch (IOException e8) {
                        iOException = e8;
                        try {
                            iOException.printStackTrace();
                            headerField = iOException.getMessage();
                            if (headerField == null || !headerField.equals("No space left on device")) {
                                setConnState(-2);
                                setConnState(-2);
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e10) {
                                    }
                                }
                            } else {
                                setConnState(-6);
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e11) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e12) {
                                    }
                                }
                                endDownload();
                                if (isDownloadComplete()) {
                                    onComplete(0);
                                    return;
                                } else if (this.mIsRunning) {
                                    onFailure(this.mState);
                                    this.mIsRunning = false;
                                    return;
                                } else {
                                    onFailure(-5);
                                    return;
                                }
                            }
                        } catch (Throwable th4) {
                            inputStream = inputStream2;
                            th2 = th4;
                        }
                    } catch (Throwable th5) {
                        th4 = th5;
                        th4.printStackTrace();
                        setConnState(-2);
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e13) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e14) {
                            }
                        }
                    }
                }
            }
        }
        endDownload();
        if (isDownloadComplete()) {
            onComplete(0);
            return;
        } else if (this.mIsRunning) {
            onFailure(this.mState);
            this.mIsRunning = false;
            return;
        } else {
            onFailure(-5);
            return;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e15) {
            }
        }
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e16) {
            }
        }
        throw th2;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        throw th2;
        throw th2;
    }

    private void setDataDuration(long j, long j2) {
        if (this.mListener != null) {
            this.mListener.a(j, j2);
        }
    }

    protected HttpURLConnection getConnect(RequestMsg requestMsg) throws Exception {
        HttpURLConnection authResult;
        if (this.mIsAuthFirst) {
            authResult = getAuthResult(requestMsg, null);
            if (authResult == null) {
                return authResult;
            }
            int responseCode = authResult.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                this.mState = changeRespCode(responseCode, authResult);
            } else {
                Object a = ao.a(authResult.getInputStream());
                if (!TextUtils.isEmpty(a)) {
                    JSONObject jSONObject = new JSONObject(a);
                    if (jSONObject != null) {
                        int optInt = jSONObject.optInt("result", -1);
                        if (optInt == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("auth");
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString(SocialConstants.PARAM_URL);
                                long optLong = optJSONObject.optLong("duration", 0);
                                long optLong2 = optJSONObject.optLong("bitRate", 0);
                                if (d.aD(ReaderApplication.getApplicationImp())) {
                                    optJSONObject = optJSONObject.optJSONObject(this.mAcid);
                                    if (optJSONObject != null) {
                                        optString = optJSONObject.optString(SocialConstants.PARAM_URL);
                                        optLong = optJSONObject.optLong("duration", 0);
                                        optLong2 = optJSONObject.optLong("bitRate", 0);
                                    }
                                    ReaderApplication.getApplicationImp().sendBroadcast(new Intent(e.s));
                                }
                                HttpURLConnection authResult2 = getAuthResult(requestMsg, optString);
                                int responseCode2 = authResult2.getResponseCode();
                                this.mState = changeRespCode(responseCode2, authResult2);
                                setDataDuration(optLong, optLong2);
                                responseCode = responseCode2;
                                authResult = authResult2;
                            }
                            if (responseCode == 200 || responseCode == 206) {
                                CharSequence contentType = authResult.getContentType();
                                if (contentType != null && ("text/vnd.wap.wml".contains(contentType) || "application/vnd.wap.wmlc".contains(contentType))) {
                                    this.mState = -2;
                                    authResult = null;
                                }
                            } else {
                                authResult = null;
                            }
                        } else if (optInt == -100121 || optInt == -100305) {
                            if (com.qq.reader.common.login.c.b()) {
                                this.mState = -8;
                            } else {
                                this.mState = -9;
                            }
                        } else if (optInt == -100108) {
                            this.mState = -9;
                        } else if (optInt == -100306) {
                            this.mState = -2;
                        } else if (optInt == -100199) {
                            this.mState = 15;
                        } else if (optInt == -100204) {
                            this.mState = 16;
                        }
                    }
                }
            }
            return (this.mState == -8 || this.mState == -9) ? authResult : authResult;
        } else {
            authResult = getAuthResult(requestMsg, null);
            this.mState = changeRespCode(authResult.getResponseCode(), authResult);
            return authResult;
        }
    }

    private HttpURLConnection getAuthResult(RequestMsg requestMsg, String str) throws Exception {
        handleState(10);
        String b = requestMsg.b();
        if (TextUtils.isEmpty(str)) {
            str = b;
        }
        if (!str.contains("http://")) {
            str = "http://" + str;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        CharSequence d = requestMsg.d();
        if (requestMsg.c() == null && TextUtils.isEmpty(d)) {
            requestMsg.a(Constants.HTTP_GET);
        }
        httpURLConnection.setRequestMethod(requestMsg.e());
        httpURLConnection.setRequestProperty("Accept", "*/*");
        HashMap a = requestMsg.a();
        if (a != null) {
            for (Entry entry : a.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (requestMsg.e().equalsIgnoreCase(Constants.HTTP_POST)) {
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            String d2 = requestMsg.d();
            byte[] c;
            DataOutputStream dataOutputStream;
            if (d2 == null || d2.length() <= 0) {
                c = requestMsg.c();
                if (c != null) {
                    dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(c);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
            } else {
                c = d2.getBytes();
                dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(c);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        } else {
            httpURLConnection.setReadTimeout(120000);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    protected void processLastCryptedData(String str, String str2) throws Exception {
        if (this.mDecrytedLength + ((long) this.mBufStart) == this.mTotalLength) {
            byte[] a = com.qq.reader.common.utils.a.e.a(this.mRollCryptedBuf, 0, this.mBufStart % this.mRollCryptedBuf.length, com.qq.reader.common.utils.a.e.a(str, str2));
            processData(a, 0, a.length);
            this.mDecrytedLength = ((long) a.length) + this.mDecrytedLength;
        }
    }

    protected boolean processCryptedData(byte[] bArr, int i, int i2, String str, String str2) throws Exception {
        if (this.mBufStart + i2 >= this.mRollCryptedBuf.length) {
            System.arraycopy(bArr, i, this.mRollCryptedBuf, this.mBufStart, this.mRollCryptedBuf.length - this.mBufStart);
            byte[] a = com.qq.reader.common.utils.a.e.a(this.mRollCryptedBuf, 0, this.mRollCryptedBuf.length, com.qq.reader.common.utils.a.e.a(str, str2));
            processData(a, 0, a.length);
            this.mDecrytedLength = ((long) a.length) + this.mDecrytedLength;
            int length = (this.mBufStart + i2) - this.mRollCryptedBuf.length;
            if (length > 0) {
                System.arraycopy(bArr, this.mRollCryptedBuf.length - this.mBufStart, this.mRollCryptedBuf, 0, length);
            }
            this.mBufStart = length;
        } else {
            System.arraycopy(bArr, i, this.mRollCryptedBuf, this.mBufStart, i2);
            this.mBufStart += i2;
        }
        return true;
    }

    protected boolean processData(byte[] bArr, int i, int i2) throws Exception {
        if (this.mListener != null) {
            this.mListener.a(this.mExtraBundle, this.mDownloadedLength, this.mTotalLength);
        }
        if (!(this.rdFile == null || bArr == null)) {
            if (this.mIsEncrypted) {
                this.rdFile.seek(this.mDecrytedLength);
            }
            this.rdFile.write(bArr, i, i2);
        }
        return true;
    }

    protected void endDownload() {
        if (this.rdFile != null) {
            try {
                c.e(TAG, "end set length:" + this.mDownloadedLength);
                this.rdFile.setLength(this.mDownloadedLength);
                this.rdFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.rdFile = null;
        }
    }

    private long getRangeEnd(String str) {
        long j = -1;
        int lastIndexOf = str.lastIndexOf(47) + 1;
        if (lastIndexOf != -1) {
            try {
                j = Long.parseLong(str.substring(lastIndexOf));
            } catch (Exception e) {
            }
        }
        return j;
    }

    public void handleState(int i) {
        if (this.mListener != null) {
            this.mListener.a(i);
        }
    }

    private void onComplete(int i) {
        if (this.mListener != null) {
            this.mListener.a(i, this.mExtraBundle);
        }
        c.e(TAG, "completed");
    }

    private void onFailure(int i) {
        if (!(this.mListener == null || i == -5)) {
            this.mListener.b(i, this.mExtraBundle);
        }
        c.e(TAG, "failed:" + i);
    }

    public void setConnState(int i) {
        if (this.mState != -5) {
            this.mState = i;
        }
    }

    private boolean isDownloadComplete() {
        return this.mDownloadedLength > 0 && this.mTotalLength > 0 && this.mDownloadedLength >= this.mTotalLength;
    }

    private void handleRange(long j) {
        c.e(TAG, "bytes=" + j + "-" + (((long) 524288) + j));
        this.mRequestMsg.a("Range", "bytes=" + j + "-" + (((long) 524288) + j));
    }

    private int changeRespCode(int i, HttpURLConnection httpURLConnection) {
        if (i == 211) {
            return -8;
        }
        if (i >= 200 && i < 300) {
            return 0;
        }
        if (i >= 400 && i < http.Internal_Server_Error) {
            return -4;
        }
        if (i >= http.Internal_Server_Error) {
            return -3;
        }
        return -2;
    }

    public static byte[] append(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }
}
