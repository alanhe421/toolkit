package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;

public final class stat_set_pushsound$RspBody extends MessageMicro<stat_set_pushsound$RspBody> {
    public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
    static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10}, new String[]{"enum_cmd_error_code"}, new Object[]{null}, stat_set_pushsound$RspBody.class);
    public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
}
