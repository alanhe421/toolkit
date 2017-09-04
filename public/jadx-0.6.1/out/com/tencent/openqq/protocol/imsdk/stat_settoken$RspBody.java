package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;

public final class stat_settoken$RspBody extends MessageMicro<stat_settoken$RspBody> {
    public static final int BYTES_TOKEN_ID_FIELD_NUMBER = 3;
    public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
    public static final int STR_ERRMSG_FIELD_NUMBER = 2;
    static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26}, new String[]{"enum_cmd_error_code", "str_errmsg", "bytes_token_id"}, new Object[]{null, "", ByteStringMicro.EMPTY}, stat_settoken$RspBody.class);
    public final PBBytesField bytes_token_id = PBField.initBytes(ByteStringMicro.EMPTY);
    public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
    public final PBStringField str_errmsg = PBField.initString("");
}
