package com.tencent.upload.task;

import FileCloud.FileControlRsp;
import FileCloud.FileUploadRsp;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.c.a.g;
import com.tencent.upload.c.c;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.a;
import com.tencent.upload.common.e;
import com.tencent.upload.common.h;
import com.tencent.upload.common.i;
import com.tencent.upload.impl.SessionManager;
import com.tencent.upload.impl.TaskCenter;
import com.tencent.upload.impl.TaskManager.TaskType;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.f;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.ITask.TaskStateListener;
import com.tencent.upload.task.data.UploadDataSource;
import com.tencent.upload.task.data.UploadDataSource.ByteDataSource;
import com.tencent.upload.task.data.UploadDataSource.FileDataSource;

public abstract class UploadTask extends CommandTask implements Parcelable {
    protected static final int DEFAULT_MAX_RETRY_TIMES = 3;
    protected static final int DEFAULT_MD5_FILE_LIMIT = 5;
    protected static final int DEFAULT_PHOTO_SLICE_SIZE = 32;
    protected static final int DEFAULT_VIDEO_SLICE_SIZE = 64;
    protected static final String TAG = "UploadTask";
    private String mBusiMsg;
    protected String mConnectedIp = "";
    protected int mConnectedPort = 0;
    long mContolPkgEndTime;
    long mContolPkgStartTime;
    private int mCurrControlReqId = 0;
    private long mDataLength;
    long mDataPkgEndTime;
    long mDataPkgStartTime;
    private UploadDataSource mDataSource;
    long mEndTime;
    private FileCheckInfo mFileCheckInfo = null;
    private long mFileSendOffset;
    boolean mIsFastUpload = false;
    protected IUploadTaskListener mListener;
    private int mRequestRetryCount = 0;
    private int mRetryCount = 0;
    private long mSendedSize = 0;
    private int mSliceSize;
    long mStartTime;
    protected k mUploadRoute;
    private String mUploadSession;
    protected String mUrl;

    public class FileCheckInfo {
        public String check_content;
        public int check_type;
    }

    public UploadTask(Parcel parcel) {
        super(null);
        this.mTaskId = parcel.readInt();
        this.mDataSource = (UploadDataSource) parcel.readParcelable(UploadDataSource.class.getClassLoader());
        this.mUploadSession = parcel.readString();
        int readInt = parcel.readInt();
        setBucket(parcel.readString());
        setAppid(parcel.readString());
        setAuth(decrypt(parcel.readString()));
        TaskState stateFromCode = TaskState.getStateFromCode(readInt);
        if (stateFromCode == TaskState.CONNECTING || stateFromCode == TaskState.SENDING) {
            stateFromCode = TaskState.WAITING;
        }
        setTaskStatus(stateFromCode);
    }

    public UploadTask(String str) {
        super(null);
        this.mDataSource = new FileDataSource(str);
    }

    public UploadTask(byte[] bArr) {
        super(null);
        this.mDataSource = new ByteDataSource(bArr);
    }

    private void calculateFileCheckInfo() {
        if (this.mFileCheckInfo == null) {
            this.mFileCheckInfo = new FileCheckInfo();
            this.mFileCheckInfo.check_type = 1;
            this.mFileCheckInfo.check_content = this.mDataSource.calcSha1();
        }
    }

    private int getMaxRetryTimes() {
        return a.a().a("upload_data_timeout_retry_times", 3);
    }

    private void onFileControlResponse(FileControlRsp fileControlRsp, c cVar) {
        a.a.b(getTag(), "Recv Response taskId=" + getTaskId() + " reqId=" + cVar.d() + " cmd=" + cVar.b() + " ret=" + fileControlRsp.result.ret + " msg=" + fileControlRsp.result.msg + " offset=" + fileControlRsp.offset + " slice_size=" + fileControlRsp.slice_size + " session=" + fileControlRsp.session);
        this.mContolPkgEndTime = System.currentTimeMillis();
        if (fileControlRsp.result.ret == 1) {
            onUploadProgress(this.mDataLength, this.mDataLength);
            setTaskStatus(TaskState.SUCCEED);
            onUploadSucceed(fileControlRsp.biz_rsp);
            onTaskFinished(RetCode.FAST_SUCCEED.getCode(), RetCode.FAST_SUCCEED.getDesc());
        } else if (fileControlRsp.result.ret == 0) {
            this.mUploadSession = fileControlRsp.session;
            this.mFileSendOffset = fileControlRsp.offset < 0 ? 0 : fileControlRsp.offset;
            if (this.mFileSendOffset > 0 && fileControlRsp.slice_size > 0) {
                this.mSliceSize = (int) fileControlRsp.slice_size;
            }
            this.mSendedSize = this.mFileSendOffset;
            this.mDataPkgStartTime = System.currentTimeMillis();
            TaskCenter.registTask(this.mUploadSession, this);
            sendFilePkg(false);
            this.mStartTime = System.currentTimeMillis();
        } else if (fileControlRsp.result.ret == -290) {
            this.mUploadSession = "";
            this.mRetryCount--;
            onSend();
        } else {
            onServerError(fileControlRsp.result.ret, fileControlRsp.result.msg);
        }
    }

    private void onFileUploadResponse(FileUploadRsp fileUploadRsp, c cVar) {
        a.a.b(getTag(), "Recv Response taskId=" + getTaskId() + " reqId=" + cVar.d() + " cmd=" + cVar.b() + " ret=" + fileUploadRsp.result.ret + " msg=" + fileUploadRsp.result.msg + " offset=" + fileUploadRsp.offset + " size=" + fileUploadRsp.size + " totalSize=" + this.mDataLength + " sendOffset=" + this.mFileSendOffset + " finish=" + fileUploadRsp.finish + (i.a(this.mUploadSession, fileUploadRsp.session) ? "" : " Session invalid! ") + " session=" + fileUploadRsp.session);
        this.mRetryCount = 0;
        this.mRequestRetryCount = 0;
        if (fileUploadRsp.result.ret != 0) {
            onServerError(fileUploadRsp.result.ret, fileUploadRsp.result.msg);
            return;
        }
        if ((fileUploadRsp.finish == 1 ? 1 : 0) != 0) {
            onUploadProgress(this.mDataLength, this.mDataLength);
            setTaskStatus(TaskState.SUCCEED);
            onUploadSucceed(fileUploadRsp.biz_rsp);
            onTaskFinished(RetCode.SUCCEED.getCode(), RetCode.SUCCEED.getDesc());
        }
    }

    private boolean onSend() {
        if (getTaskState() == TaskState.PAUSE || getTaskState() == TaskState.CANCEL || getTaskState() == TaskState.SUCCEED) {
            return false;
        }
        this.mStartTime = System.currentTimeMillis();
        if (getTaskState() != TaskState.PAUSE) {
            this.mRetryCount++;
        }
        if (this.mSessionManager == null) {
            onError(RetCode.NO_SESSION.getCode(), RetCode.NO_SESSION.getDesc(), false);
            return false;
        }
        a.a.b(getTag(), "SendBegin actionId=" + getTaskId() + " retry=" + this.mRetryCount + " route=" + this.mUploadRoute + " currState=" + getTaskState());
        if (!this.mDataSource.exists()) {
            onError(RetCode.FILE_NOT_EXIST.getCode(), RetCode.FILE_NOT_EXIST.getDesc(), false);
            return false;
        } else if (this.mDataSource.getDataLength() > 0) {
            return sendControlPkg();
        } else {
            onError(RetCode.FILE_LENGTH_INVAID.getCode(), RetCode.FILE_LENGTH_INVAID.getDesc(), false);
            return false;
        }
    }

    private void onTaskInfoChanged() {
        if (this.mSessionManager != null) {
            this.mSessionManager.saveTaskInfo(this);
        }
    }

    private void report(int i, String str) {
        a.a.a(TAG, "Report taskId=" + getTaskId() + " errorCode=" + i + " errorMsg=" + str + " url=" + this.mUrl + " retry=" + this.mRetryCount);
        h hVar = new h();
        hVar.b = i;
        hVar.c = str;
        hVar.f = this.mUrl;
        hVar.g = getTaskId();
        hVar.h = this.mConnectedIp;
        hVar.i = this.mConnectedPort;
        hVar.j = this.mIsFastUpload;
        hVar.k = this.mEndTime - this.mStartTime;
        hVar.l = this.mContolPkgEndTime - this.mContolPkgStartTime;
        hVar.m = this.mDataPkgEndTime - this.mDataPkgStartTime;
        hVar.n = (long) (((float) this.mDataLength) / 1024.0f);
        hVar.o = this.mRetryCount;
        hVar.a = getFileType();
        hVar.d = getReportBiz();
        int a = this.mUploadRoute != null ? this.mUploadRoute.a() : 4;
        if (1 == a || 5 == a) {
            hVar.p = 2;
        } else if (2 == a) {
            hVar.p = 3;
        } else if (3 == a) {
            hVar.p = 4;
        } else {
            hVar.p = 1;
        }
        a.a.a(hVar);
    }

    private boolean sendControlPkg() {
        this.mContolPkgStartTime = System.currentTimeMillis();
        setTaskStatus(TaskState.SENDING);
        com.tencent.upload.c.a controlRequest = getControlRequest();
        this.mCurrControlReqId = controlRequest.c();
        this.mSessionManager.sendRequest(controlRequest, this);
        return true;
    }

    private boolean sendFilePkg(boolean z) {
        int b = z ? 1 : f.b(TaskType.UPLOAD);
        while (this.mSessionManager.isIdle() && b > 0 && getTaskState() != TaskState.PAUSE && getTaskState() != TaskState.CANCEL) {
            b--;
            synchronized (this) {
                com.tencent.upload.c.a fileUploadRequest = getFileUploadRequest();
                if (fileUploadRequest != null) {
                    this.mFileSendOffset = fileUploadRequest.a + fileUploadRequest.k();
                }
            }
            if (fileUploadRequest == null) {
                setTaskStatus(TaskState.SENDING);
                break;
            }
            this.mSessionManager.sendRequest(fileUploadRequest, this);
        }
        return true;
    }

    public boolean cancel() {
        if (getTaskState() == TaskState.SUCCEED) {
            return false;
        }
        setTaskStatus(TaskState.CANCEL);
        this.mRetryCount = 0;
        onTaskFinished(RetCode.CANCELED.getCode(), RetCode.CANCELED.getDesc());
        return true;
    }

    protected final void cancelForError(int i, String str) {
        a.a.c(getTag(), "Cancel Action For Error taskId=" + getTaskId() + " errorCode=" + i + " errorMsg=" + str);
        onUploadError(i, str);
        setTaskStatus(TaskState.FAILED);
        onTaskFinished(i, str);
    }

    public int describeContents() {
        return 0;
    }

    protected abstract int getBucketSize();

    protected com.tencent.upload.c.a.c getControlRequest() {
        this.mDataLength = this.mDataSource.getDataLength();
        calculateFileCheckInfo();
        if (this.mSliceSize <= 0) {
            this.mSliceSize = getBucketSize();
        }
        String str = "";
        if (this.mDataSource instanceof FileDataSource) {
            str = ((FileDataSource) this.mDataSource).getFileName();
        }
        com.tencent.upload.c.a.c cVar = new com.tencent.upload.c.a.c(this.mUploadSession, this.mFileCheckInfo.check_type, this.mFileCheckInfo.check_content, str, this.mDataLength, (long) this.mSliceSize);
        cVar.a(getTaskId());
        cVar.a(this.mBusiMsg);
        if (TextUtils.isEmpty(this.mUploadSession) && this.mDataLength <= ((long) this.mSliceSize)) {
            cVar.a(this.mDataSource, (long) this.mSliceSize);
        }
        cVar.a(getAuth());
        cVar.a(getFileType());
        return cVar;
    }

    public UploadDataSource getDataSource() {
        return this.mDataSource;
    }

    protected g getFileUploadRequest() {
        if (this.mFileSendOffset >= this.mDataLength) {
            return null;
        }
        g gVar = new g(this.mDataSource, this.mUploadSession, this.mFileSendOffset, this.mSliceSize > 0 ? (long) this.mSliceSize : (long) getBucketSize(), getFileType() != FileType.Photo);
        gVar.a(getAuth());
        gVar.a(getTaskId());
        gVar.a(getFileType());
        return gVar;
    }

    public com.tencent.upload.c.a getNetworkRequest() {
        return null;
    }

    public k getRoute() {
        return this.mUploadRoute;
    }

    public String getTag() {
        return TAG;
    }

    public float getUpoadProgress() {
        if (this.mDataLength <= 0) {
            this.mDataLength = this.mDataSource.getDataLength();
        }
        return (this.mFileSendOffset >= this.mDataLength || this.mDataLength <= 0 || getTaskState() == TaskState.SUCCEED) ? 100.0f : (100.0f * ((float) this.mFileSendOffset)) / ((float) this.mDataLength);
    }

    public boolean isDataSourceValid() {
        return this.mDataSource != null ? this.mDataSource.isValid() : false;
    }

    public void onError(int i, String str, boolean z) {
        a.a.c(getTag(), "Task Error! taskId=" + getTaskId() + " ret=" + i + " desc=" + str + " networkAvailable=" + e.b(Global.context) + " retry=" + this.mRetryCount);
        cancelForError(i, str);
    }

    public void onRequestError(com.tencent.upload.c.a aVar, int i, String str) {
        if (aVar != null && aVar.c() >= this.mCurrControlReqId && getTaskState() != TaskState.FAILED && getTaskState() != TaskState.CANCEL && getTaskState() != TaskState.SUCCEED && getTaskState() != TaskState.PAUSE) {
            a.a.c(getTag(), "Send Request Error. taskId=" + getTaskId() + " reqId=" + aVar.c() + " CMD=" + aVar.d() + " state=" + getTaskState().toString() + " retry=" + this.mRetryCount + " ret=" + i + " msg=" + str);
            if (i == RetCode.SESSION_DISCONNECT.getCode() && this.mRequestRetryCount < f.b(TaskType.UPLOAD) * getMaxRetryTimes()) {
                this.mRequestRetryCount++;
                this.mSessionManager.sendRequest(aVar, this);
            } else if (this.mRetryCount >= getMaxRetryTimes() || i == RetCode.NETWORK_NOT_AVAILABLE.getCode()) {
                cancelForError(i, str);
            } else {
                onSend();
            }
        }
    }

    public void onRequestSended(com.tencent.upload.c.a aVar) {
        if (aVar.c() >= this.mCurrControlReqId) {
            switch (getTaskState()) {
                case SENDING:
                    if (aVar instanceof g) {
                        this.mSendedSize += ((g) aVar).k();
                        onUploadProgress(this.mDataLength, this.mSendedSize <= this.mFileSendOffset ? this.mSendedSize : this.mFileSendOffset);
                        this.mDataPkgEndTime = System.currentTimeMillis();
                        sendFilePkg(true);
                        return;
                    } else if (aVar instanceof com.tencent.upload.c.a.c) {
                        com.tencent.upload.c.a.c cVar = (com.tencent.upload.c.a.c) aVar;
                        if (cVar.k() > 0) {
                            onUploadProgress(this.mDataLength, cVar.k());
                            this.mFileSendOffset = cVar.k();
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public void onRequestTimeout(com.tencent.upload.c.a aVar) {
        if (aVar.c() >= this.mCurrControlReqId) {
            a.a.b(getTag(), "Send Timeout taskId=" + getTaskId() + " reqId=" + aVar.c() + " CMD=" + aVar.d() + " state=" + getTaskState().toString() + " retry=" + this.mRetryCount);
            if (this.mRetryCount <= getMaxRetryTimes()) {
                onSend();
            } else {
                cancelForError(RetCode.REQUEST_TIMEOUT.getCode(), RetCode.REQUEST_TIMEOUT.getDesc());
            }
        }
    }

    public void onResponse(com.tencent.upload.c.a aVar, c cVar) {
        if (getTaskState() != TaskState.SUCCEED && getTaskState() != TaskState.FAILED && getTaskState() != TaskState.CANCEL && cVar.d() >= this.mCurrControlReqId) {
            JceStruct a = cVar.a();
            if (a == null) {
                return;
            }
            if (a instanceof FileControlRsp) {
                onFileControlResponse((FileControlRsp) a, cVar);
            } else if (a instanceof FileUploadRsp) {
                onFileUploadResponse((FileUploadRsp) a, cVar);
            }
        }
    }

    public void onServerError(int i, String str) {
        cancelForError(i, str);
    }

    protected void onTaskFinished(int i, String str) {
        this.mEndTime = System.currentTimeMillis();
        TaskCenter.unregistTask(this.mUploadSession);
        this.mIsFastUpload = i == RetCode.FAST_SUCCEED.getCode();
        if (i == RetCode.FAST_SUCCEED.getCode()) {
            i = RetCode.SUCCEED.getCode();
        }
        if (this.mSessionManager != null) {
            this.mUploadRoute = this.mSessionManager.getUploadRoute();
            this.mConnectedIp = this.mSessionManager.getConnectedIp();
            if (this.mUploadRoute != null) {
                this.mConnectedPort = this.mUploadRoute.c();
            }
        }
        if (!(getTaskState() == TaskState.CANCEL || getTaskState() == TaskState.PAUSE)) {
            report(i, str);
        }
        super.onTaskFinished(i, str);
    }

    protected abstract void onUploadError(int i, String str);

    protected abstract void onUploadProgress(long j, long j2);

    protected abstract void onUploadStateChange(TaskState taskState);

    protected abstract void onUploadSucceed(Object obj);

    public boolean pause() {
        if (getTaskState() == TaskState.SUCCEED || getTaskState() == TaskState.FAILED || getTaskState() == TaskState.PAUSE || getTaskState() == TaskState.CANCEL) {
            return false;
        }
        if (getTaskState() == TaskState.SENDING && this.mFileSendOffset >= this.mDataLength) {
            return false;
        }
        setTaskStatus(TaskState.PAUSE);
        onTaskFinished(RetCode.PAUSED.getCode(), RetCode.PAUSED.getDesc());
        return true;
    }

    public boolean resume() {
        if (getTaskState() == TaskState.SUCCEED) {
            return false;
        }
        setTaskStatus(TaskState.WAITING);
        this.mRetryCount = 0;
        return true;
    }

    public void run(SessionManager sessionManager, TaskStateListener taskStateListener) {
        this.mSessionManager = sessionManager;
        onSend();
    }

    public void setBusiMsg(String str) {
        this.mBusiMsg = str;
    }

    protected boolean setTaskStatus(TaskState taskState) {
        boolean taskStatus = super.setTaskStatus(taskState);
        onTaskInfoChanged();
        if (taskStatus) {
            onUploadStateChange(taskState);
        }
        return taskStatus;
    }

    public void setUploadListener(IUploadTaskListener iUploadTaskListener) {
        this.mListener = iUploadTaskListener;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mTaskId);
        parcel.writeParcelable(this.mDataSource, i);
        parcel.writeString(this.mUploadSession);
        parcel.writeInt(getTaskState().getCode());
        parcel.writeString(getBucket());
        parcel.writeString(getAuth().appid);
        parcel.writeString(encrypt(getAuth().sign));
    }
}
