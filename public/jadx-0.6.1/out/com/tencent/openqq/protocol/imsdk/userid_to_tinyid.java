package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;
import com.tencent.openqq.protocol.imsdk.im_open_common.IMUserId;
import com.tencent.openqq.protocol.imsdk.im_open_common.SdkData;

public final class userid_to_tinyid {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int SDKDATA_FIELD_NUMBER = 1;
        public static final int USERID_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"sdkdata", "userid"}, new Object[]{null, null}, ReqBody.class);
        public SdkData sdkdata = new SdkData();
        public final PBRepeatMessageField<IMUserId> userid = PBField.initRepeatMessage(IMUserId.class);
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
        public static final int USERID_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"enum_cmd_error_code", "userid"}, new Object[]{null, null}, RspBody.class);
        public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
        public final PBRepeatMessageField<IMUserId> userid = PBField.initRepeatMessage(IMUserId.class);
    }

    private userid_to_tinyid() {
    }
}
