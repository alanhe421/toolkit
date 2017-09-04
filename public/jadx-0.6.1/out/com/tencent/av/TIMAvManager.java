package com.tencent.av;

import android.text.TextUtils;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.av.Message.AvMsg;
import com.tencent.av.Message.AvMsg.Type;
import com.tencent.av.Message.AvMsg0x32;
import com.tencent.av.Message.TIMAvMessageListener;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.av.MultiVideoTinyId;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.openqq.protocol.imsdk.gv_comm_operate.GVCommOprHead;
import com.tencent.openqq.protocol.imsdk.gv_comm_operate.ReqBody;
import com.tencent.openqq.protocol.imsdk.msg;
import com.tencent.openqq.protocol.imsdk.videoinvitation.MsgBody;
import com.tencent.openqq.protocol.imsdk.videoinvitation.UserInfo;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TIMAvManager {
    static String defaultId = "";
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static IMMsfCoreProxy msfCoreProxy = IMMsfCoreProxy.get();
    static ConcurrentHashMap<String, TIMAvManager> mutiMap = new ConcurrentHashMap();
    private static final String tag = "MSF.C.TIMAvManager";
    private String identifier = "";
    private TIMAvMessageListener messageListener;

    public class LiveUrl {
        int encodeType;
        int rate = 0;
        String url;

        public int getEncode() {
            return this.encodeType;
        }

        public RateType getRateType() {
            for (RateType rateType : RateType.values()) {
                if (rateType.getValue() == this.rate) {
                    return rateType;
                }
            }
            return RateType.RATE_TYPE_ORIGINAL;
        }

        public String getUrl() {
            return this.url == null ? "" : this.url;
        }

        void setEncode(int i) {
            this.encodeType = i;
        }

        void setRateType(int i) {
            this.rate = i;
        }

        void setUrl(String str) {
            this.url = str;
        }
    }

    public enum RateType {
        RATE_TYPE_ORIGINAL(0),
        RATE_TYPE_550(10),
        RATE_TYPE_900(20);
        
        private int value;

        private RateType(int i) {
            this.value = 0;
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    public class RecordParam {
        int classId;
        String filename;
        boolean isScreenShot = false;
        boolean isTransCode = false;
        boolean isWaterMark = false;
        RecordType recordType = RecordType.VIDEO;
        SDKType sdktype = SDKType.Normal;
        SourceType stype = SourceType.CAMERA;
        List<String> tags = new ArrayList();

        private void setSourceType(SourceType sourceType) {
            this.stype = sourceType;
        }

        public void addTag(String str) {
            this.tags.add(str);
        }

        public int classId() {
            return this.classId;
        }

        public String filename() {
            return this.filename;
        }

        public RecordType getRecordType() {
            return this.recordType;
        }

        public SDKType getSdkType() {
            return this.sdktype;
        }

        public boolean isScreenShot() {
            return this.isScreenShot;
        }

        public boolean isTransCode() {
            return this.isTransCode;
        }

        public boolean isWaterMark() {
            return this.isWaterMark;
        }

        public void setClassId(int i) {
            this.classId = i;
        }

        public void setFilename(String str) {
            this.filename = str;
        }

        public void setRecordType(RecordType recordType) {
            if (recordType != null) {
                this.recordType = recordType;
            }
        }

        public void setSdkType(SDKType sDKType) {
            this.sdktype = sDKType;
        }

        public void setSreenShot(boolean z) {
            this.isScreenShot = z;
        }

        public void setTransCode(boolean z) {
            this.isTransCode = z;
        }

        public void setWaterMark(boolean z) {
            this.isWaterMark = z;
        }

        public List<String> tags() {
            return this.tags;
        }
    }

    public enum RecordType {
        VIDEO(0),
        AUDIO(1);
        
        private int value;

        private RecordType(int i) {
            this.value = 0;
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    public class RoomInfo {
        int relationId;
        int roomId;

        public void setRelationId(int i) {
            this.relationId = i;
        }

        public void setRoomId(int i) {
            this.roomId = i;
        }
    }

    public enum SDKType {
        Normal(1),
        IOTCamara(2),
        CoastCamara(3);
        
        private int type;

        private SDKType(int i) {
            this.type = i;
        }

        final int getSdkType() {
            return this.type;
        }
    }

    private enum SourceType {
        CAMERA(0),
        SECONDARY_STREAM(1);
        
        private int c;

        static {
            CAMERA = new SourceType("CAMERA", 0, 0);
            SECONDARY_STREAM = new SourceType("SECONDARY_STREAM", 1, 1);
            SourceType[] sourceTypeArr = new SourceType[]{CAMERA, SECONDARY_STREAM};
        }

        private SourceType(int i) {
            this.c = i;
        }

        final int a() {
            return this.c;
        }
    }

    public enum StreamEncode {
        HLS(1),
        FLV(2),
        RAW(4),
        RTMP(5),
        HLS_AND_RTMP(6);
        
        private int encode;

        private StreamEncode(int i) {
            this.encode = i;
        }

        final int getEncode() {
            return this.encode;
        }
    }

    public class StreamParam {
        String chnldescr = "";
        String chnlname = "";
        String chnlpasswd = "";
        StreamEncode encode;
        boolean isRecord = false;
        boolean isWatermark = false;
        List<RateType> rates = new ArrayList();
        SDKType sdktype = SDKType.Normal;
        SourceType stype = SourceType.CAMERA;
        long watermarkId = 0;

        private void setSourceType(SourceType sourceType) {
            this.stype = sourceType;
        }

        public void addRateType(RateType rateType) {
            this.rates.add(rateType);
        }

        public void enableRecord(boolean z) {
            this.isRecord = z;
        }

        public void enableWatermark(boolean z) {
            this.isWatermark = z;
        }

        public void setChannelDescr(String str) {
            this.chnldescr = str;
        }

        public void setChannelName(String str) {
            this.chnlname = str;
        }

        public void setChannelPasswd(String str) {
            this.chnlpasswd = str;
        }

        public void setEncode(StreamEncode streamEncode) {
            this.encode = streamEncode;
        }

        public void setSdkType(SDKType sDKType) {
            this.sdktype = sDKType;
        }

        public void setWatermarkId(long j) {
            this.watermarkId = j;
        }
    }

    public class StreamRes {
        long chnlId;
        long taskId;
        List<LiveUrl> urls = new ArrayList();

        public long getChnlId() {
            return this.chnlId;
        }

        public long getTaskId() {
            return this.taskId;
        }

        public List<LiveUrl> getUrls() {
            return this.urls;
        }
    }

    private class StreamerRecorderContext {
        int a;
        int b;
        int c;
        long d;
        int e;
        int f;
        String g;
        int h;
        int i;
        StreamParam j;
        RecordParam k;
        List<Long> l;

        private StreamerRecorderContext(TIMAvManager tIMAvManager) {
        }
    }

    private TIMAvManager(String str) {
        this.identifier = str;
    }

    private static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length << 1)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i << 1] = hexArray[i2 >>> 4];
            cArr[(i << 1) + 1] = hexArray[i2 & 15];
        }
        return new String(cArr);
    }

    public static TIMAvManager getInstance() {
        return getInstanceById(TIMManager.getInstance().getIdentification());
    }

    public static TIMAvManager getInstanceById(String str) {
        return new TIMAvManager(str);
    }

    private IMMsfUserInfo getMsfUserInfo() {
        return TextUtils.isEmpty(this.identifier) ? IMMsfCoreProxy.get().getMsfUserInfo(TIMManager.getInstance().getIdentification()) : IMMsfCoreProxy.get().getMsfUserInfo(this.identifier);
    }

    public void MsgNotify(byte[] bArr) {
        if (this.messageListener != null) {
            AvMsg avMsg0x32 = new AvMsg0x32(this.identifier);
            avMsg0x32.receive(bArr);
            this.messageListener.onNewMessages(avMsg0x32);
        }
    }

    public void ResponseToVideoInvitation(Type type, AvMsg avMsg, TIMCallBack tIMCallBack) {
        avMsg.setMsgType(type);
        avMsg.response(tIMCallBack);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public TIMAvMessageListener getMessageListener() {
        return this.messageListener;
    }

    public void requestMultiVideoInvitation(int i, int i2, int i3, int i4, byte[] bArr, List<TIMUser> list, TIMCallBack tIMCallBack) {
        IMMsfUserInfo msfUserInfo = getMsfUserInfo();
        if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
            tIMCallBack.onError(6014, "current user not login. id: " + this.identifier);
            return;
        }
        MsgBody msgBody = new MsgBody();
        if (bArr != null && bArr.length > 128) {
            tIMCallBack.onError(-1, "自定义信息长度不能超过128字节");
        } else if (list.size() <= 0) {
            tIMCallBack.onError(-1, "邀请人数至少为一人");
        } else {
            msgBody.int32_buss_type.set(i);
            msgBody.int32_auth_type.set(i2);
            msgBody.uint32_auth_id.set(i3);
            msgBody.uint32_sdk_appid.set(IMMsfCoreProxy.get().getSdkAppId());
            msgBody.int32_request_type.set(i4);
            if (bArr != null) {
                msgBody.bytes_buff.set(ByteStringMicro.copyFrom(bArr));
            }
            MessageMicro userInfo = new UserInfo();
            userInfo.bytes_appid.set(ByteStringMicro.copyFromUtf8(msfUserInfo.getsUerAppId()));
            userInfo.bytes_openid.set(ByteStringMicro.copyFromUtf8(msfUserInfo.getUserId()));
            userInfo.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(IMMsfCoreProxy.get().getUidType()));
            msgBody.msg_sender.set(userInfo);
            List arrayList = new ArrayList();
            for (TIMUser tIMUser : list) {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.bytes_acounttype.set(ByteStringMicro.copyFromUtf8(tIMUser.getAccountType()));
                userInfo2.bytes_appid.set(ByteStringMicro.copyFromUtf8(tIMUser.getAppIdAt3rd()));
                userInfo2.bytes_openid.set(ByteStringMicro.copyFromUtf8(tIMUser.getIdentifier()));
                arrayList.add(userInfo2);
            }
            msgBody.rpt_msg_receiver_list.set(arrayList);
            msg.MsgBody msgBody2 = new msg.MsgBody();
            msgBody2.msg_content.set(ByteStringMicro.copyFrom(msgBody.toByteArray()));
            IMMsfCoreProxy.get().request(msfUserInfo.getUserId(), "openim.videoinvitaion", msgBody2.toByteArray(), new ad(this, tIMCallBack));
        }
    }

    void requestMultiVideoRecorderRelay(StreamerRecorderContext streamerRecorderContext, TIMValueCallBack<List<String>> tIMValueCallBack) {
        int i = 1;
        GVCommOprHead gVCommOprHead = new GVCommOprHead();
        gVCommOprHead.uint32_buss_type.set(streamerRecorderContext.a);
        gVCommOprHead.uint32_auth_type.set(streamerRecorderContext.b);
        gVCommOprHead.uint32_auth_key.set(streamerRecorderContext.c);
        gVCommOprHead.uint64_uin.set(streamerRecorderContext.d);
        gVCommOprHead.uint32_sdk_appid.set(streamerRecorderContext.e);
        ReqBody reqBody = new ReqBody();
        reqBody.req_0x5.setHasFlag(true);
        reqBody.req_0x5.uint32_oper.set(streamerRecorderContext.f);
        reqBody.req_0x5.uint32_seq.set(IMMsfCoreProxy.get().random.nextInt());
        if (streamerRecorderContext.k != null) {
            if (streamerRecorderContext.k.filename() != null) {
                reqBody.req_0x5.string_file_name.set(streamerRecorderContext.k.filename());
            }
            reqBody.req_0x5.uint32_classid.set(streamerRecorderContext.k.classId());
            reqBody.req_0x5.uint32_IsTransCode.set(streamerRecorderContext.k.isTransCode() ? 1 : 0);
            reqBody.req_0x5.uint32_IsScreenShot.set(streamerRecorderContext.k.isScreenShot() ? 1 : 0);
            PBUInt32Field pBUInt32Field = reqBody.req_0x5.uint32_IsWaterMark;
            if (!streamerRecorderContext.k.isWaterMark()) {
                i = 0;
            }
            pBUInt32Field.set(i);
            for (String add : streamerRecorderContext.k.tags()) {
                reqBody.req_0x5.string_tags.add(add);
            }
            reqBody.req_0x5.uint32_sdk_type.set(streamerRecorderContext.k.sdktype.getSdkType());
            if (streamerRecorderContext.k.stype != SourceType.CAMERA) {
                reqBody.req_0x5.uint32_record_data_type.set(streamerRecorderContext.k.stype.a());
            }
            if (streamerRecorderContext.k.recordType != RecordType.VIDEO) {
                reqBody.req_0x5.uint32_record_type.set(streamerRecorderContext.k.recordType.getValue());
            }
        }
        MultiVideoTinyId.get().requestMultiVideoInfo(NetworkUtil.formReq(this.identifier, streamerRecorderContext.h, streamerRecorderContext.i, null, gVCommOprHead.toByteArray(), reqBody.toByteArray()), new ah(this, tIMValueCallBack));
    }

    public void requestMultiVideoRecorderStart(RoomInfo roomInfo, RecordParam recordParam, TIMCallBack tIMCallBack) {
        IMMsfUserInfo msfUserInfo = getMsfUserInfo();
        if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
            tIMCallBack.onError(6014, "current user not login. id: " + this.identifier);
            return;
        }
        StreamerRecorderContext streamerRecorderContext = new StreamerRecorderContext();
        streamerRecorderContext.a = 7;
        streamerRecorderContext.b = 6;
        streamerRecorderContext.c = roomInfo.relationId;
        streamerRecorderContext.i = roomInfo.roomId;
        streamerRecorderContext.e = IMMsfCoreProxy.get().getSdkAppId();
        streamerRecorderContext.d = msfUserInfo.getTinyid();
        streamerRecorderContext.k = recordParam;
        streamerRecorderContext.f = 1;
        streamerRecorderContext.h = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02;
        requestMultiVideoRecorderRelay(streamerRecorderContext, new af(this, tIMCallBack));
    }

    public void requestMultiVideoRecorderStop(RoomInfo roomInfo, TIMValueCallBack<List<String>> tIMValueCallBack) {
        IMMsfUserInfo msfUserInfo = getMsfUserInfo();
        if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
            tIMValueCallBack.onError(6014, "current user not login. id: " + this.identifier);
            return;
        }
        StreamerRecorderContext streamerRecorderContext = new StreamerRecorderContext();
        streamerRecorderContext.a = 7;
        streamerRecorderContext.b = 6;
        streamerRecorderContext.c = roomInfo.relationId;
        streamerRecorderContext.i = roomInfo.roomId;
        streamerRecorderContext.g = null;
        streamerRecorderContext.e = IMMsfCoreProxy.get().getSdkAppId();
        streamerRecorderContext.d = msfUserInfo.getTinyid();
        streamerRecorderContext.f = 2;
        streamerRecorderContext.h = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02;
        requestMultiVideoRecorderRelay(streamerRecorderContext, tIMValueCallBack);
    }

    void requestMultiVideoStreamerRelay(StreamerRecorderContext streamerRecorderContext, TIMValueCallBack<StreamRes> tIMValueCallBack) {
        GVCommOprHead gVCommOprHead = new GVCommOprHead();
        gVCommOprHead.uint32_buss_type.set(streamerRecorderContext.a);
        gVCommOprHead.uint32_auth_type.set(streamerRecorderContext.b);
        gVCommOprHead.uint32_auth_key.set(streamerRecorderContext.c);
        gVCommOprHead.uint64_uin.set(streamerRecorderContext.d);
        gVCommOprHead.uint32_sdk_appid.set(streamerRecorderContext.e);
        ReqBody reqBody = new ReqBody();
        reqBody.req_0x6.setHasFlag(true);
        reqBody.req_0x6.uint32_oper.set(streamerRecorderContext.f);
        if (streamerRecorderContext.j != null) {
            reqBody.req_0x6.uint32_live_code.set(streamerRecorderContext.j.encode.getEncode());
            reqBody.req_0x6.uint32_sdk_type.set(streamerRecorderContext.j.sdktype.getSdkType());
            if (!TextUtils.isEmpty(streamerRecorderContext.j.chnlname)) {
                reqBody.req_0x6.str_channel_name.set(streamerRecorderContext.j.chnlname);
            }
            if (!TextUtils.isEmpty(streamerRecorderContext.j.chnldescr)) {
                reqBody.req_0x6.str_channel_describe.set(streamerRecorderContext.j.chnldescr);
            }
            if (!TextUtils.isEmpty(streamerRecorderContext.j.chnlpasswd)) {
                reqBody.req_0x6.str_player_pwd.set(streamerRecorderContext.j.chnlpasswd);
            }
            if (streamerRecorderContext.j.stype != SourceType.CAMERA) {
                reqBody.req_0x6.uint32_push_data_type.set(streamerRecorderContext.j.stype.a());
            }
            if (streamerRecorderContext.j.isRecord) {
                reqBody.req_0x6.uint32_tape_flag.set(1);
            }
            if (streamerRecorderContext.j.isWatermark) {
                reqBody.req_0x6.uint32_watermark_flag.set(1);
                reqBody.req_0x6.uint32_watermark_id.set((int) streamerRecorderContext.j.watermarkId);
            }
            if (streamerRecorderContext.j.rates.size() > 0) {
                for (RateType value : streamerRecorderContext.j.rates) {
                    reqBody.req_0x6.rpt_rate_type.add(Integer.valueOf(value.getValue()));
                }
            }
        }
        if (streamerRecorderContext.l != null) {
            reqBody.req_0x6.uint64_channel_id.set(streamerRecorderContext.l);
        }
        MultiVideoTinyId.get().requestMultiVideoInfo(NetworkUtil.formReq(this.identifier, streamerRecorderContext.h, streamerRecorderContext.i, null, gVCommOprHead.toByteArray(), reqBody.toByteArray()), new ag(this, tIMValueCallBack));
    }

    public void requestMultiVideoStreamerStart(RoomInfo roomInfo, StreamParam streamParam, TIMValueCallBack<StreamRes> tIMValueCallBack) {
        IMMsfUserInfo msfUserInfo = getMsfUserInfo();
        if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
            tIMValueCallBack.onError(6014, "current user not login. id: " + this.identifier);
            return;
        }
        StreamerRecorderContext streamerRecorderContext = new StreamerRecorderContext();
        streamerRecorderContext.a = 7;
        streamerRecorderContext.b = 6;
        streamerRecorderContext.c = roomInfo.relationId;
        streamerRecorderContext.i = roomInfo.roomId;
        streamerRecorderContext.j = streamParam;
        streamerRecorderContext.e = IMMsfCoreProxy.get().getSdkAppId();
        streamerRecorderContext.d = msfUserInfo.getTinyid();
        streamerRecorderContext.f = 1;
        streamerRecorderContext.h = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
        requestMultiVideoStreamerRelay(streamerRecorderContext, tIMValueCallBack);
    }

    public void requestMultiVideoStreamerStop(RoomInfo roomInfo, List<Long> list, TIMCallBack tIMCallBack) {
        IMMsfUserInfo msfUserInfo = getMsfUserInfo();
        if (msfUserInfo == null || !msfUserInfo.isLoggedIn()) {
            tIMCallBack.onError(6014, "current user not login. id: " + this.identifier);
            return;
        }
        StreamerRecorderContext streamerRecorderContext = new StreamerRecorderContext();
        streamerRecorderContext.a = 7;
        streamerRecorderContext.b = 6;
        streamerRecorderContext.c = roomInfo.relationId;
        streamerRecorderContext.i = roomInfo.roomId;
        streamerRecorderContext.l = list;
        streamerRecorderContext.e = IMMsfCoreProxy.get().getSdkAppId();
        streamerRecorderContext.d = msfUserInfo.getTinyid();
        streamerRecorderContext.f = 2;
        streamerRecorderContext.h = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
        requestMultiVideoStreamerRelay(streamerRecorderContext, new ae(this, tIMCallBack));
    }

    public void requestSpeedTest(TIMPingCallBack tIMPingCallBack) {
        int sdkAppId = IMMsfCoreProxy.get().getSdkAppId();
        ByteBuffer allocate = ByteBuffer.allocate(19);
        allocate.put((byte) 2).putShort((short) 1).putShort((short) 7).putShort((short) 6).putInt(sdkAppId).putInt(23678484).putShort((short) 0).putShort((short) 0);
        MultiVideoTinyId.get().requestMultiVideoInfo(NetworkUtil.formReq(this.identifier, 208, 0, "", allocate.array()), new ac(this, tIMPingCallBack));
    }

    public void requestSpeedTestStop() {
        PingUtil.getInstance().stop();
    }

    public void setMessageListener(TIMAvMessageListener tIMAvMessageListener) {
        this.messageListener = tIMAvMessageListener;
    }
}
