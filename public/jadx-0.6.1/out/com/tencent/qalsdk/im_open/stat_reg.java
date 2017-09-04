package com.tencent.qalsdk.im_open;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;
import com.tencent.qalsdk.im_open.common.CmdErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import tencent.tls.tools.I18nMsg;

public final class stat_reg {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int BYTES_BIND_QQ_KEY_FIELD_NUMBER = 17;
        public static final int BYTES_GUID_FIELD_NUMBER = 7;
        public static final int BYTES_VENDOR_DATA_FIELD_NUMBER = 15;
        public static final int STR_BUILD_VER_FIELD_NUMBER = 13;
        public static final int STR_DEV_NAME_FIELD_NUMBER = 10;
        public static final int STR_DEV_TYPE_FIELD_NUMBER = 11;
        public static final int STR_FIRMWARE_VER_FIELD_NUMBER = 14;
        public static final int STR_OS_VER_FIELD_NUMBER = 12;
        public static final int UINT32_CONN_TYPE_FIELD_NUMBER = 2;
        public static final int UINT32_INST_TYPE_FIELD_NUMBER = 22;
        public static final int UINT32_KICK_PC_FIELD_NUMBER = 4;
        public static final int UINT32_LOCALEID_FIELD_NUMBER = 8;
        public static final int UINT32_OPEN_PUSH_FIELD_NUMBER = 19;
        public static final int UINT32_PRODUCT_VER_FIELD_NUMBER = 21;
        public static final int UINT32_REGTYPE_FIELD_NUMBER = 6;
        public static final int UINT32_SILENT_PUSH_FIELD_NUMBER = 9;
        public static final int UINT32_STATUS_FIELD_NUMBER = 3;
        public static final int UINT32_TIMESTAMP_FIELD_NUMBER = 5;
        public static final int UINT64_BID_FIELD_NUMBER = 1;
        public static final int UINT64_BIND_QQ_FIELD_NUMBER = 16;
        public static final int UINT64_TINYID_FIELD_NUMBER = 20;
        public static final int VENDER_APPID_FIELD_NUMBER = 18;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48, 58, 64, 72, 82, 90, 98, 106, 114, 122, 128, Opcodes.DOUBLE_TO_INT, Opcodes.ADD_INT, Opcodes.SHL_INT, 160, Opcodes.MUL_FLOAT, Opcodes.ADD_INT_2ADDR}, new String[]{"uint64_bid", "uint32_conn_type", "uint32_status", "uint32_kick_pc", "uint32_timestamp", "uint32_regtype", "bytes_guid", "uint32_localeid", "uint32_silent_push", "str_dev_name", "str_dev_type", "str_os_ver", "str_build_ver", "str_firmware_ver", "bytes_vendor_data", "uint64_bind_qq", "bytes_bind_qq_key", "vender_appid", "uint32_open_push", "uint64_tinyid", "uint32_product_ver", "uint32_inst_type"}, new Object[]{Long.valueOf(1), Integer.valueOf(0), Integer.valueOf(11), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(I18nMsg.ZH_CN), Integer.valueOf(0), "", "", "", "", "", ByteStringMicro.EMPTY, Long.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(1), Long.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, ReqBody.class);
        public final PBBytesField bytes_bind_qq_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_guid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_vendor_data = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBStringField str_build_ver = PBField.initString("");
        public final PBStringField str_dev_name = PBField.initString("");
        public final PBStringField str_dev_type = PBField.initString("");
        public final PBStringField str_firmware_ver = PBField.initString("");
        public final PBStringField str_os_ver = PBField.initString("");
        public final PBUInt32Field uint32_conn_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_inst_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_kick_pc = PBField.initUInt32(0);
        public final PBUInt32Field uint32_localeid = PBField.initUInt32(I18nMsg.ZH_CN);
        public final PBUInt32Field uint32_open_push = PBField.initUInt32(1);
        public final PBUInt32Field uint32_product_ver = PBField.initUInt32(0);
        public final PBUInt32Field uint32_regtype = PBField.initUInt32(0);
        public final PBUInt32Field uint32_silent_push = PBField.initUInt32(0);
        public final PBUInt32Field uint32_status = PBField.initUInt32(11);
        public final PBUInt32Field uint32_timestamp = PBField.initUInt32(0);
        public final PBUInt64Field uint64_bid = PBField.initUInt64(1);
        public final PBUInt64Field uint64_bind_qq = PBField.initUInt64(0);
        public final PBUInt64Field uint64_tinyid = PBField.initUInt64(0);
        public final PBUInt32Field vender_appid = PBField.initUInt32(0);
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int ENUM_CMD_ERROR_CODE_FIELD_NUMBER = 1;
        public static final int STR_CLIENT_IP_FIELD_NUMBER = 6;
        public static final int STR_ERRMSG_FIELD_NUMBER = 2;
        public static final int UINT32_CLIENT_PORT_FIELD_NUMBER = 7;
        public static final int UINT32_HELLO_INTERVAL_FIELD_NUMBER = 8;
        public static final int UINT32_SERVER_TIME_FIELD_NUMBER = 3;
        public static final int UINT32_TIMESTAMP_FIELD_NUMBER = 5;
        public static final int UINT32_UPDATE_FLAG_FIELD_NUMBER = 4;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 24, 32, 40, 50, 56, 64}, new String[]{"enum_cmd_error_code", "str_errmsg", "uint32_server_time", "uint32_update_flag", "uint32_timestamp", "str_client_ip", "uint32_client_port", "uint32_hello_interval"}, new Object[]{null, "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(300)}, RspBody.class);
        public CmdErrorCode enum_cmd_error_code = new CmdErrorCode();
        public final PBStringField str_client_ip = PBField.initString("");
        public final PBStringField str_errmsg = PBField.initString("");
        public final PBUInt32Field uint32_client_port = PBField.initUInt32(0);
        public final PBUInt32Field uint32_hello_interval = PBField.initUInt32(300);
        public final PBUInt32Field uint32_server_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_timestamp = PBField.initUInt32(0);
        public final PBUInt32Field uint32_update_flag = PBField.initUInt32(0);
    }

    private stat_reg() {
    }
}
