package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBRepeatField;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;
import com.tencent.openqq.protocol.imsdk.im_open_common.UserId;

public final class uin_to_userid {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int OPENAPPID_FIELD_NUMBER = 2;
        public static final int UIDTYPE_FIELD_NUMBER = 1;
        public static final int UIN_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 24}, new String[]{"uidtype", "openappid", "uin"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0), Long.valueOf(0)}, ReqBody.class);
        public final PBUInt32Field openappid = PBField.initUInt32(0);
        public final PBBytesField uidtype = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBRepeatField<Long> uin = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
        public static final int USERID_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"enum_cmd_error_code", "userid"}, new Object[]{null, null}, RspBody.class);
        public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
        public final PBRepeatMessageField<UserId> userid = PBField.initRepeatMessage(UserId.class);
    }

    private uin_to_userid() {
    }
}
