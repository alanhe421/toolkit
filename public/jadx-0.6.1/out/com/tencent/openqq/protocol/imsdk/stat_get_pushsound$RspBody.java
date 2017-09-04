package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;

public final class stat_get_pushsound$RspBody extends MessageMicro<stat_get_pushsound$RspBody> {
    public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
    public static final int MSG_CONFIG_FIELD_NUMBER = 3;
    public static final int UINT32_PLATFORM_FIELD_NUMBER = 2;
    static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26}, new String[]{"enum_cmd_error_code", "uint32_platform", "msg_config"}, new Object[]{null, Integer.valueOf(0), null}, stat_get_pushsound$RspBody.class);
    public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
    public stat_get_pushsound$Config msg_config = new stat_get_pushsound$Config();
    public final PBUInt32Field uint32_platform = PBField.initUInt32(0);
}
