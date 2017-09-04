package com.tencent.av;

import com.tencent.IMFunc;
import com.tencent.TIMValueCallBack;
import com.tencent.av.TIMAvManager.StreamRes;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.gv_comm_operate.LiveUrl;
import com.tencent.openqq.protocol.imsdk.gv_comm_operate.RspBody;
import java.util.ArrayList;

final class ag implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMValueCallBack a;
    private /* synthetic */ TIMAvManager b;

    ag(TIMAvManager tIMAvManager, TIMValueCallBack tIMValueCallBack) {
        this.b = tIMAvManager;
        this.a = tIMValueCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        byte[] parseRsp = NetworkUtil.parseRsp(bArr);
        if (parseRsp == null) {
            this.a.onError(6001, "parse streamer rsp failed");
            return;
        }
        try {
            rspBody.mergeFrom(parseRsp);
            if (rspBody.rsp_0x6.uint32_result.get() != 0) {
                QLog.d("MSF.C.TIMAvManager", 1, "streamer svr ret: " + rspBody.rsp_0x6.uint32_result.get() + " err: " + rspBody.rsp_0x6.str_errorinfo.get());
                this.a.onError(rspBody.rsp_0x6.uint32_result.get(), rspBody.rsp_0x6.str_errorinfo.get());
                return;
            }
            StreamRes streamRes = new StreamRes();
            streamRes.urls = new ArrayList();
            QLog.d("MSF.C.TIMAvManager", 1, "live url list size: " + rspBody.rsp_0x6.msg_live_url.size());
            for (LiveUrl liveUrl : rspBody.rsp_0x6.msg_live_url.get()) {
                TIMAvManager.LiveUrl liveUrl2 = new TIMAvManager.LiveUrl();
                liveUrl2.setEncode(liveUrl.uint32_type.get());
                liveUrl2.setUrl(liveUrl.string_play_url.get());
                liveUrl2.setRateType(liveUrl.rate_type.get());
                streamRes.urls.add(liveUrl2);
            }
            streamRes.chnlId = rspBody.rsp_0x6.uint64_channel_id.get();
            streamRes.taskId = (long) rspBody.rsp_0x6.uint32_tape_task_id.get();
            this.a.onSuccess(streamRes);
        } catch (Throwable th) {
            QLog.e("MSF.C.TIMAvManager", 1, IMFunc.getExceptionInfo(th));
            QLog.e("MSF.C.TIMAvManager", 1, "parse streamer svr rsp failed");
            this.a.onError(6001, "parse streamer rsp failed");
        }
    }
}
