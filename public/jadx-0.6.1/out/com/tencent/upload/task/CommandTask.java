package com.tencent.upload.task;

import FileCloud.stAuth;
import android.text.TextUtils;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.c.c;
import com.tencent.upload.common.g;
import com.tencent.upload.common.j;
import com.tencent.upload.impl.SessionManager;
import com.tencent.upload.impl.TaskManager.TaskType;
import com.tencent.upload.log.b;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.a.a;
import com.tencent.upload.network.b.f;
import com.tencent.upload.task.ITask.TaskState;
import com.tencent.upload.task.ITask.TaskStateListener;
import com.tencent.upload.utils.AESUtil;
import java.util.UUID;

public abstract class CommandTask implements a, ITask {
    private static final String TAG = "CommandTask";
    private String mBucket;
    protected long mEndTime;
    private ICmdListener mListener;
    protected com.tencent.upload.c.a mRequest;
    private int mRequestRetryCount = 0;
    protected int mRetCode = RetCode.SUCCEED.getCode();
    protected String mRetMsg = RetCode.SUCCEED.getDesc();
    protected SessionManager mSessionManager;
    private stAuth mStAuth = new stAuth();
    protected long mStartTime;
    protected int mTaskId;
    private TaskState mTaskState;
    protected k mUploadRoute;
    protected String mUrl;

    public CommandTask(ICmdListener iCmdListener) {
        this.mListener = iCmdListener;
        this.mTaskState = TaskState.WAITING;
        this.mTaskId = UUID.randomUUID().hashCode();
    }

    private String getSecurityKey() {
        return new String(AESUtil.getDeviceSecurity());
    }

    private void onFailed(int i, String str) {
        if (this.mListener != null) {
            this.mListener.onFailure(i, str);
        }
        setTaskStatus(TaskState.FAILED);
        onTaskFinished(i, str);
    }

    private void report(int i, String str) {
        g gVar = new g();
        gVar.b = this.mRequest != null ? this.mRequest.e() : 0;
        gVar.a = i;
        gVar.c = str;
        gVar.e = this.mUrl;
        String b = this.mUploadRoute != null ? !TextUtils.isEmpty(this.mUploadRoute.a) ? this.mUploadRoute.a : this.mUploadRoute.b() : "";
        gVar.g = b;
        gVar.h = this.mUploadRoute != null ? this.mUploadRoute.c() : 80;
        gVar.i = this.mEndTime - this.mStartTime;
        gVar.j = 0;
        gVar.l = getFileType();
        gVar.d = getReportBiz();
        int a = this.mUploadRoute != null ? this.mUploadRoute.a() : 4;
        if (1 == a || 5 == a) {
            gVar.k = 2;
        } else if (2 == a) {
            gVar.k = 3;
        } else if (3 == a) {
            gVar.k = 4;
        } else {
            gVar.k = 1;
        }
        com.tencent.upload.common.a.a.a(gVar);
    }

    public boolean authIsEmpty() {
        return getAuth() == null || TextUtils.isEmpty(this.mStAuth.appid) || TextUtils.isEmpty(this.mStAuth.sign);
    }

    public boolean checkTaskValidity(FileType fileType) {
        if (authIsEmpty()) {
            com.tencent.upload.common.a.a.d(getTag(), "check task error, auth is empty. taskId=" + getTaskId());
            return false;
        } else if (getFileType() == fileType) {
            return true;
        } else {
            com.tencent.upload.common.a.a.d(getTag(), "check task error, fileType match failed. taskId=" + getTaskId() + " taskFileType=" + getFileType() + " fileType=" + fileType);
            return false;
        }
    }

    protected String decrypt(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return AESUtil.decrypt(getSecurityKey(), str);
        } catch (Throwable e) {
            Throwable th = e;
            str2 = "";
            b.c(TAG, "decrypt error", th);
            return str2;
        } catch (Throwable th2) {
            return str2;
        }
    }

    protected String encrypt(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return AESUtil.encrypt(getSecurityKey(), str);
        } catch (Throwable e) {
            Throwable th = e;
            str2 = "";
            b.c(TAG, "encrypt error", th);
            return str2;
        } catch (Throwable th2) {
            return str2;
        }
    }

    protected String getAbsolutePath(String str) {
        return "/" + getAuth().appid + "/" + getBucket() + str;
    }

    protected stAuth getAuth() {
        return this.mStAuth;
    }

    public String getBucket() {
        return this.mBucket;
    }

    protected abstract com.tencent.upload.c.a getNetworkRequest();

    protected com.tencent.upload.c.a getNetworkRequestEx() {
        com.tencent.upload.c.a networkRequest = getNetworkRequest();
        if (networkRequest == null) {
            return null;
        }
        networkRequest.a(getAuth());
        return networkRequest.a(getFileType());
    }

    protected String getReportBiz() {
        return !TextUtils.isEmpty(getBucket()) ? getBucket() + "-" + getAuth().appid : getAuth().appid;
    }

    public int getRetCode() {
        return this.mRetCode;
    }

    public String getRetMsg() {
        return this.mRetMsg;
    }

    public String getTag() {
        return TAG;
    }

    public int getTaskId() {
        return this.mTaskId;
    }

    public TaskState getTaskState() {
        return this.mTaskState;
    }

    public void onCancel(int i) {
        com.tencent.upload.common.a.a.a(getTag(), "Canceled taskId=" + getTaskId() + " state=" + i);
    }

    public void onConnecting() {
        setTaskStatus(TaskState.CONNECTING);
    }

    public void onError(int i, String str, boolean z) {
        com.tencent.upload.common.a.a.a(getTag(), "onError taskId=" + getTaskId() + " ret=" + i + " msg=" + str + " network=" + z);
        onFailed(i, str);
    }

    public void onRequestError(com.tencent.upload.c.a aVar, int i, String str) {
        com.tencent.upload.common.a.a.a(getTag(), "RequestError taskId=" + getTaskId() + " reqId=" + (aVar == null ? "N/A" : Integer.valueOf(aVar.c())) + " cmd=" + (aVar == null ? "N/A" : aVar.d()) + " ret=" + i + " msg=:" + str);
        if (aVar == null || this.mSessionManager == null) {
            onFailed(i, str);
        } else if (i != RetCode.SESSION_DISCONNECT.getCode()) {
            onFailed(i, str);
        } else if (this.mRequestRetryCount >= f.b(TaskType.UPLOAD)) {
            onFailed(i, str);
        } else {
            this.mRequestRetryCount++;
            this.mSessionManager.sendRequest(aVar, this);
        }
    }

    public void onRequestSended(com.tencent.upload.c.a aVar) {
    }

    public void onRequestTimeout(com.tencent.upload.c.a aVar) {
        com.tencent.upload.common.a.a.a(getTag(), "Timeout taskId=" + getTaskId() + " reqId=" + aVar.c() + " cmd=" + aVar.d());
        if (this.mListener != null) {
            this.mListener.onFailure(RetCode.REQUEST_TIMEOUT.getCode(), RetCode.REQUEST_TIMEOUT.getDesc());
        }
        if (this.mUploadRoute != null) {
            j.b(this.mUploadRoute.b());
        }
        setTaskStatus(TaskState.FAILED);
        onTaskFinished(RetCode.REQUEST_TIMEOUT.getCode(), RetCode.REQUEST_TIMEOUT.getDesc());
    }

    public void onResponse(com.tencent.upload.c.a aVar, c cVar) {
        this.mEndTime = System.currentTimeMillis();
        com.tencent.upload.common.a.a.a(getTag(), "Response taskId=" + getTaskId() + " reqId=" + cVar.d() + " cmd=" + cVar.b());
        this.mRequestRetryCount = 0;
        if (cVar.a() == null) {
            cVar.a = RetCode.RESPONSE_IS_NULL.getCode();
            cVar.b = RetCode.RESPONSE_IS_NULL.getDesc();
            if (this.mListener != null) {
                this.mListener.onFailure(cVar.a, cVar.b);
            }
        }
        setTaskStatus(cVar.a == 0 ? TaskState.SUCCEED : TaskState.FAILED);
        onTaskFinished(cVar.a, cVar.b);
    }

    public boolean onSend(com.tencent.upload.network.b.a aVar) {
        if (this.mTaskState == TaskState.SUCCEED || this.mTaskState == TaskState.FAILED || this.mTaskState == TaskState.PAUSE) {
            return false;
        }
        this.mStartTime = System.currentTimeMillis();
        this.mUploadRoute = aVar.c();
        com.tencent.upload.c.a networkRequestEx = getNetworkRequestEx();
        this.mRequest = networkRequestEx;
        if (networkRequestEx != null) {
            networkRequestEx.a(getTaskId());
        } else {
            com.tencent.upload.common.a.a.d(getTag(), "Send Request must't be null!");
        }
        setTaskStatus(TaskState.SENDING);
        if (aVar.a(networkRequestEx, this)) {
            return true;
        }
        onTaskFinished(RetCode.SESSION_STATE_INVALID.getCode(), RetCode.SESSION_STATE_INVALID.getDesc());
        return false;
    }

    protected void onTaskFinished(int i, String str) {
        com.tencent.upload.common.a.a.b(getTag(), "Finished taskId=" + getTaskId() + " ret=" + i + " desc=" + str);
        this.mRetCode = i;
        this.mRetMsg = str;
        if (this.mSessionManager != null) {
            this.mSessionManager.onTaskStateChanged(this, getTaskState());
        }
        if (!(this instanceof UploadTask)) {
            report(i, str);
        }
    }

    public void run(SessionManager sessionManager, TaskStateListener taskStateListener) {
        this.mStartTime = System.currentTimeMillis();
        this.mSessionManager = sessionManager;
        com.tencent.upload.c.a networkRequestEx = getNetworkRequestEx();
        this.mRequest = networkRequestEx;
        if (networkRequestEx != null) {
            networkRequestEx.a(getTaskId());
        } else {
            onTaskFinished(0, "成功");
        }
        setTaskStatus(TaskState.SENDING);
        this.mSessionManager.sendRequest(networkRequestEx, this);
        com.tencent.upload.network.b.a usefulSession = sessionManager.getUsefulSession();
        if (usefulSession != null) {
            this.mUploadRoute = usefulSession.c();
        }
    }

    public void setAppid(String str) {
        this.mStAuth.appid = str;
    }

    public void setAuth(String str) {
        this.mStAuth.sign = str;
    }

    public void setBucket(String str) {
        this.mBucket = str;
    }

    public void setSessionManager(SessionManager sessionManager, TaskStateListener taskStateListener) {
        this.mSessionManager = sessionManager;
    }

    protected boolean setTaskStatus(TaskState taskState) {
        if (this.mTaskState == taskState) {
            return false;
        }
        com.tencent.upload.common.a.a.b(getTag(), "State Change taskId=" + getTaskId() + " old_state=" + this.mTaskState + " new_state=" + taskState);
        this.mTaskState = taskState;
        return true;
    }
}
