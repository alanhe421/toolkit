package com.tencent.upload.task.impl;

import FileCloud.HandShakeRsp;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.c.a.h;
import com.tencent.upload.c.c;
import com.tencent.upload.common.a;
import com.tencent.upload.network.a.k;
import com.tencent.upload.task.CommandTask;
import com.tencent.upload.task.ICmdListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandshakeTask extends CommandTask {
    private final FileType mFileType;
    private String mLastUpdate;
    private IListener mListener = null;
    private boolean mNeedRedirect = false;

    public interface IListener extends ICmdListener<HandShakeResult> {
    }

    public static class HandShakeResult {
        public String clientIp;
        public Map<String, String> configs = new HashMap();
        public String last_update = "";
        public List<String> upload_svr_list = new ArrayList();

        public k getRedirectRoute() {
            return (this.upload_svr_list == null || this.upload_svr_list.size() <= 0) ? null : new k((String) this.upload_svr_list.get(0), 80, 1, 2);
        }
    }

    public HandshakeTask(FileType fileType, boolean z, String str, IListener iListener) {
        super(iListener);
        setAuth("");
        setAppid("");
        this.mFileType = fileType;
        this.mListener = iListener;
        this.mLastUpdate = str;
        this.mNeedRedirect = z;
    }

    private void updateFileCloudConfig(Map<String, String> map, String str) {
        if (map != null && map.size() > 0) {
            a.a().a((Map) map, str);
        }
    }

    public FileType getFileType() {
        return this.mFileType;
    }

    public com.tencent.upload.c.a getNetworkRequest() {
        return new h(this.mFileType, this.mNeedRedirect, this.mLastUpdate);
    }

    public String getTag() {
        return "HandshakeTask_" + this.mFileType;
    }

    public void onResponse(com.tencent.upload.c.a aVar, c cVar) {
        HandShakeRsp handShakeRsp = (HandShakeRsp) cVar.a();
        if (handShakeRsp != null) {
            cVar.a = handShakeRsp.result.ret;
            cVar.b = handShakeRsp.result.msg;
            HandShakeResult handShakeResult = new HandShakeResult();
            if (this.mListener != null) {
                if (handShakeRsp.result.ret == 0) {
                    handShakeResult.last_update = handShakeRsp.last_update;
                    handShakeResult.clientIp = handShakeRsp.clientip;
                    if (handShakeRsp.upload_svr != null) {
                        handShakeResult.upload_svr_list.addAll(handShakeRsp.upload_svr);
                    }
                    if (handShakeRsp.config != null) {
                        handShakeResult.configs.putAll(handShakeRsp.config);
                    }
                    updateFileCloudConfig(handShakeResult.configs, handShakeResult.last_update);
                    this.mListener.onSuccess(handShakeResult);
                } else {
                    this.mListener.onFailure(handShakeRsp.result.ret, handShakeRsp.result.msg);
                }
            }
        }
        super.onResponse(aVar, cVar);
    }
}
