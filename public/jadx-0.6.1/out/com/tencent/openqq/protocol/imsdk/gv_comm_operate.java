package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBEnumField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBInt32Field;
import com.tencent.mobileqq.pb.PBRepeatField;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public final class gv_comm_operate {
    public static final int AUDIO = 1;
    public static final int ENCODE_FLV = 2;
    public static final int ENCODE_HLS = 1;
    public static final int ENCODE_HLS_AND_RTMP = 6;
    public static final int ENCODE_RAW = 4;
    public static final int ENCODE_RTMP = 5;
    public static final int RATE_TYPE_550 = 10;
    public static final int RATE_TYPE_900 = 20;
    public static final int RATE_TYPE_ORIGINAL = 0;
    public static final int VIDEO = 0;

    public static final class CookieInfo extends MessageMicro<CookieInfo> {
        public static final int UINT32_REQ_IP_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"uint32_req_ip"}, new Object[]{Integer.valueOf(0)}, CookieInfo.class);
        public final PBUInt32Field uint32_req_ip = PBField.initUInt32(0);
    }

    public static final class GVCommOprHead extends MessageMicro<GVCommOprHead> {
        public static final int BYTES_COOKIE_BUFF_FIELD_NUMBER = 22;
        public static final int RPT_RELAY_INFO_FIELD_NUMBER = 7;
        public static final int RPT_TO_ACCOUNT_FIELD_NUMBER = 25;
        public static final int RPT_TO_OPENID_FIELD_NUMBER = 21;
        public static final int RPT_TO_UIN_FIELD_NUMBER = 12;
        public static final int STR_AV_TOKEN_FIELD_NUMBER = 11;
        public static final int STR_CLIENT_VERSION_FIELD_NUMBER = 24;
        public static final int STR_ERROR_MSG_FIELD_NUMBER = 9;
        public static final int STR_OPENID_FIELD_NUMBER = 20;
        public static final int UINT32_AUTH_KEY_FIELD_NUMBER = 5;
        public static final int UINT32_AUTH_TYPE_FIELD_NUMBER = 4;
        public static final int UINT32_BUSS_TYPE_FIELD_NUMBER = 3;
        public static final int UINT32_RESULT_FIELD_NUMBER = 8;
        public static final int UINT32_SDK_APPID_FIELD_NUMBER = 10;
        public static final int UINT32_SEQ_FIELD_NUMBER = 2;
        public static final int UINT32_SUB_CMD_FIELD_NUMBER = 1;
        public static final int UINT32_TERMINAL_TYPE_FIELD_NUMBER = 23;
        public static final int UINT64_UIN_FIELD_NUMBER = 6;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48, 58, 64, 74, 80, 90, 96, Opcodes.XOR_LONG, Opcodes.REM_FLOAT, Opcodes.MUL_INT_2ADDR, Opcodes.SHL_INT_2ADDR, Opcodes.XOR_LONG_2ADDR, 200}, new String[]{"uint32_sub_cmd", "uint32_seq", "uint32_buss_type", "uint32_auth_type", "uint32_auth_key", "uint64_uin", "rpt_relay_info", "uint32_result", "str_error_msg", "uint32_sdk_appid", "str_av_token", "rpt_to_uin", "str_openid", "rpt_to_openid", "bytes_cookie_buff", "uint32_terminal_type", "str_client_version", "rpt_to_Account"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), null, Integer.valueOf(0), "", Integer.valueOf(0), "", Long.valueOf(0), "", "", ByteStringMicro.EMPTY, Integer.valueOf(0), "", Long.valueOf(0)}, GVCommOprHead.class);
        public final PBBytesField bytes_cookie_buff = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBRepeatMessageField<RelayInfo> rpt_relay_info = PBField.initRepeatMessage(RelayInfo.class);
        public final PBRepeatField<Long> rpt_to_Account = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
        public final PBRepeatField<String> rpt_to_openid = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBRepeatField<Long> rpt_to_uin = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
        public final PBStringField str_av_token = PBField.initString("");
        public final PBStringField str_client_version = PBField.initString("");
        public final PBStringField str_error_msg = PBField.initString("");
        public final PBStringField str_openid = PBField.initString("");
        public final PBUInt32Field uint32_auth_key = PBField.initUInt32(0);
        public final PBUInt32Field uint32_auth_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_buss_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sdk_appid = PBField.initUInt32(0);
        public final PBUInt32Field uint32_seq = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sub_cmd = PBField.initUInt32(0);
        public final PBUInt32Field uint32_terminal_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_uin = PBField.initUInt64(0);
    }

    public static final class LiveUrl extends MessageMicro<LiveUrl> {
        public static final int RATE_TYPE_FIELD_NUMBER = 3;
        public static final int STRING_PLAY_URL_FIELD_NUMBER = 2;
        public static final int UINT32_TYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 24}, new String[]{"uint32_type", "string_play_url", "rate_type"}, new Object[]{Integer.valueOf(0), "", Integer.valueOf(0)}, LiveUrl.class);
        public final PBEnumField rate_type = PBField.initEnum(0);
        public final PBStringField string_play_url = PBField.initString("");
        public final PBUInt32Field uint32_type = PBField.initUInt32(0);
    }

    public static final class RelayInfo extends MessageMicro<RelayInfo> {
        public static final int BYTES_CONTEXT_FIELD_NUMBER = 3;
        public static final int UINT32_CLIENT_IP_FIELD_NUMBER = 1;
        public static final int UINT32_CLIENT_PORT_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26}, new String[]{"uint32_client_ip", "uint32_client_port", "bytes_context"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY}, RelayInfo.class);
        public final PBBytesField bytes_context = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_client_ip = PBField.initUInt32(0);
        public final PBUInt32Field uint32_client_port = PBField.initUInt32(0);
    }

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int REQ_0X101_FIELD_NUMBER = 101;
        public static final int REQ_0X1_FIELD_NUMBER = 1;
        public static final int REQ_0X2_FIELD_NUMBER = 2;
        public static final int REQ_0X3_FIELD_NUMBER = 3;
        public static final int REQ_0X4_FIELD_NUMBER = 4;
        public static final int REQ_0X5_FIELD_NUMBER = 5;
        public static final int REQ_0X6_FIELD_NUMBER = 6;
        public static final int REQ_0X7_FIELD_NUMBER = 7;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 42, 50, 58, 810}, new String[]{"req_0x1", "req_0x2", "req_0x3", "req_0x4", "req_0x5", "req_0x6", "req_0x7", "req_0x101"}, new Object[]{null, null, null, null, null, null, null, null}, ReqBody.class);
        public SubReq_0x1 req_0x1 = new SubReq_0x1();
        public SubReq_0x101 req_0x101 = new SubReq_0x101();
        public SubReq_0x2 req_0x2 = new SubReq_0x2();
        public SubReq_0x3 req_0x3 = new SubReq_0x3();
        public SubReq_0x4 req_0x4 = new SubReq_0x4();
        public SubReq_0x5 req_0x5 = new SubReq_0x5();
        public SubReq_0x6 req_0x6 = new SubReq_0x6();
        public SubReq_0x7 req_0x7 = new SubReq_0x7();
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int RSP_0X101_FIELD_NUMBER = 101;
        public static final int RSP_0X1_FIELD_NUMBER = 1;
        public static final int RSP_0X2_FIELD_NUMBER = 2;
        public static final int RSP_0X3_FIELD_NUMBER = 3;
        public static final int RSP_0X4_FIELD_NUMBER = 4;
        public static final int RSP_0X5_FIELD_NUMBER = 5;
        public static final int RSP_0X6_FIELD_NUMBER = 6;
        public static final int RSP_0X7_FIELD_NUMBER = 7;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 42, 50, 58, 810}, new String[]{"rsp_0x1", "rsp_0x2", "rsp_0x3", "rsp_0x4", "rsp_0x5", "rsp_0x6", "rsp_0x7", "rsp_0x101"}, new Object[]{null, null, null, null, null, null, null, null}, RspBody.class);
        public SubRsp_0x1 rsp_0x1 = new SubRsp_0x1();
        public SubRsp_0x101 rsp_0x101 = new SubRsp_0x101();
        public SubRsp_0x2 rsp_0x2 = new SubRsp_0x2();
        public SubRsp_0x3 rsp_0x3 = new SubRsp_0x3();
        public SubRsp_0x4 rsp_0x4 = new SubRsp_0x4();
        public SubRsp_0x5 rsp_0x5 = new SubRsp_0x5();
        public SubRsp_0x6 rsp_0x6 = new SubRsp_0x6();
        public SubRsp_0x7 rsp_0x7 = new SubRsp_0x7();
    }

    public static final class SubReq_0x101 extends MessageMicro<SubReq_0x101> {
        public static final int BYTES_BUFF_FIELD_NUMBER = 3;
        public static final int UINT32_CLIENT_IP_FIELD_NUMBER = 1;
        public static final int UINT32_CLIENT_PORT_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26}, new String[]{"uint32_client_ip", "uint32_client_port", "bytes_buff"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY}, SubReq_0x101.class);
        public final PBBytesField bytes_buff = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_client_ip = PBField.initUInt32(0);
        public final PBUInt32Field uint32_client_port = PBField.initUInt32(0);
    }

    public static final class SubReq_0x1 extends MessageMicro<SubReq_0x1> {
        public static final int RPT_UINS_FIELD_NUMBER = 3;
        public static final int UINT32_DELETE_TYPE_FIELD_NUMBER = 2;
        public static final int UINT32_ROOM_NUM_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24}, new String[]{"uint32_room_num", "uint32_delete_type", "rpt_uins"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0)}, SubReq_0x1.class);
        public final PBRepeatField<Long> rpt_uins = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
        public final PBUInt32Field uint32_delete_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_room_num = PBField.initUInt32(0);
    }

    public static final class SubReq_0x2 extends MessageMicro<SubReq_0x2> {
        public static final int RPT_UINS_FIELD_NUMBER = 4;
        public static final int STR_PRIVILEGE_MAP_FIELD_NUMBER = 5;
        public static final int UINT32_ROOM_NUM_FIELD_NUMBER = 1;
        public static final int UINT32_SET_PRIV_TYPE_FIELD_NUMBER = 3;
        public static final int UINT64_PRIVILEGE_MAP_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 42}, new String[]{"uint32_room_num", "uint64_privilege_map", "uint32_set_priv_type", "rpt_uins", "str_privilege_map"}, new Object[]{Integer.valueOf(0), Long.valueOf(0), Integer.valueOf(0), Long.valueOf(0), ""}, SubReq_0x2.class);
        public final PBRepeatField<Long> rpt_uins = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
        public final PBStringField str_privilege_map = PBField.initString("");
        public final PBUInt32Field uint32_room_num = PBField.initUInt32(0);
        public final PBUInt32Field uint32_set_priv_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_privilege_map = PBField.initUInt64(0);
    }

    public static final class SubReq_0x3 extends MessageMicro<SubReq_0x3> {
        public static final int UINT32_OP_TYPE_FIELD_NUMBER = 2;
        public static final int UINT32_RECV_IP_FIELD_NUMBER = 3;
        public static final int UINT32_RECV_PORT_FIELD_NUMBER = 4;
        public static final int UINT32_ROOM_NUM_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32}, new String[]{"uint32_room_num", "uint32_op_type", "uint32_recv_ip", "uint32_recv_port"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, SubReq_0x3.class);
        public final PBUInt32Field uint32_op_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_recv_ip = PBField.initUInt32(0);
        public final PBUInt32Field uint32_recv_port = PBField.initUInt32(0);
        public final PBUInt32Field uint32_room_num = PBField.initUInt32(0);
    }

    public static final class SubReq_0x4 extends MessageMicro<SubReq_0x4> {
        public static final int UINT32_ROOM_NUM_FIELD_NUMBER = 1;
        public static final int UINT64_USER_UIN_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16}, new String[]{"uint32_room_num", "uint64_user_uin"}, new Object[]{Integer.valueOf(0), Long.valueOf(0)}, SubReq_0x4.class);
        public final PBUInt32Field uint32_room_num = PBField.initUInt32(0);
        public final PBUInt64Field uint64_user_uin = PBField.initUInt64(0);
    }

    public static final class SubReq_0x5 extends MessageMicro<SubReq_0x5> {
        public static final int STRING_FILE_NAME_FIELD_NUMBER = 3;
        public static final int STRING_TAGS_FIELD_NUMBER = 4;
        public static final int UINT32_CLASSID_FIELD_NUMBER = 5;
        public static final int UINT32_ISSCREENSHOT_FIELD_NUMBER = 7;
        public static final int UINT32_ISTRANSCODE_FIELD_NUMBER = 6;
        public static final int UINT32_ISWATERMARK_FIELD_NUMBER = 8;
        public static final int UINT32_OPER_FIELD_NUMBER = 1;
        public static final int UINT32_RECORD_APPID_FIELD_NUMBER = 11;
        public static final int UINT32_RECORD_DATA_TYPE_FIELD_NUMBER = 10;
        public static final int UINT32_RECORD_TYPE_FIELD_NUMBER = 12;
        public static final int UINT32_SDK_TYPE_FIELD_NUMBER = 9;
        public static final int UINT32_SEQ_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26, 34, 40, 48, 56, 64, 72, 80, 88, 96}, new String[]{"uint32_oper", "uint32_seq", "string_file_name", "string_tags", "uint32_classid", "uint32_IsTransCode", "uint32_IsScreenShot", "uint32_IsWaterMark", "uint32_sdk_type", "uint32_record_data_type", "uint32_record_appid", "uint32_record_type"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, SubReq_0x5.class);
        public final PBStringField string_file_name = PBField.initString("");
        public final PBRepeatField<String> string_tags = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBUInt32Field uint32_IsScreenShot = PBField.initUInt32(0);
        public final PBUInt32Field uint32_IsTransCode = PBField.initUInt32(0);
        public final PBUInt32Field uint32_IsWaterMark = PBField.initUInt32(0);
        public final PBUInt32Field uint32_classid = PBField.initUInt32(0);
        public final PBUInt32Field uint32_oper = PBField.initUInt32(0);
        public final PBUInt32Field uint32_record_appid = PBField.initUInt32(0);
        public final PBUInt32Field uint32_record_data_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_record_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sdk_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_seq = PBField.initUInt32(0);
    }

    public static final class SubReq_0x6 extends MessageMicro<SubReq_0x6> {
        public static final int RPT_RATE_TYPE_FIELD_NUMBER = 13;
        public static final int STR_CHANNEL_DESCRIBE_FIELD_NUMBER = 5;
        public static final int STR_CHANNEL_NAME_FIELD_NUMBER = 4;
        public static final int STR_PLAYER_PWD_FIELD_NUMBER = 6;
        public static final int UINT32_LIVE_CODE_FIELD_NUMBER = 2;
        public static final int UINT32_OPER_FIELD_NUMBER = 1;
        public static final int UINT32_PUSH_DATA_TYPE_FIELD_NUMBER = 8;
        public static final int UINT32_PUSH_DURATION_FIELD_NUMBER = 10;
        public static final int UINT32_SDK_TYPE_FIELD_NUMBER = 3;
        public static final int UINT32_TAPE_FLAG_FIELD_NUMBER = 9;
        public static final int UINT32_WATERMARK_FLAG_FIELD_NUMBER = 11;
        public static final int UINT32_WATERMARK_ID_FIELD_NUMBER = 12;
        public static final int UINT64_CHANNEL_ID_FIELD_NUMBER = 7;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 34, 42, 50, 56, 64, 72, 80, 88, 96, 104}, new String[]{"uint32_oper", "uint32_live_code", "uint32_sdk_type", "str_channel_name", "str_channel_describe", "str_player_pwd", "uint64_channel_id", "uint32_push_data_type", "uint32_tape_flag", "uint32_push_duration", "uint32_watermark_flag", "uint32_watermark_id", "rpt_rate_type"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", "", "", Long.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, SubReq_0x6.class);
        public final PBRepeatField<Integer> rpt_rate_type = PBField.initRepeat(PBEnumField.__repeatHelper__);
        public final PBStringField str_channel_describe = PBField.initString("");
        public final PBStringField str_channel_name = PBField.initString("");
        public final PBStringField str_player_pwd = PBField.initString("");
        public final PBUInt32Field uint32_live_code = PBField.initUInt32(0);
        public final PBUInt32Field uint32_oper = PBField.initUInt32(0);
        public final PBUInt32Field uint32_push_data_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_push_duration = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sdk_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_tape_flag = PBField.initUInt32(0);
        public final PBUInt32Field uint32_watermark_flag = PBField.initUInt32(0);
        public final PBUInt32Field uint32_watermark_id = PBField.initUInt32(0);
        public final PBRepeatField<Long> uint64_channel_id = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
    }

    public static final class SubReq_0x7 extends MessageMicro<SubReq_0x7> {
        public static final int UINT32_REQUEST_TYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"uint32_request_type"}, new Object[]{Integer.valueOf(0)}, SubReq_0x7.class);
        public final PBUInt32Field uint32_request_type = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x101 extends MessageMicro<SubRsp_0x101> {
        public static final int INT32_RESULT_FIELD_NUMBER = 1;
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT64_PRIVILEGE_MAP_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 24}, new String[]{"int32_result", "str_err_msg", "uint64_privilege_map"}, new Object[]{Integer.valueOf(0), "", Long.valueOf(0)}, SubRsp_0x101.class);
        public final PBInt32Field int32_result = PBField.initInt32(0);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt64Field uint64_privilege_map = PBField.initUInt64(0);
    }

    public static final class SubRsp_0x1 extends MessageMicro<SubRsp_0x1> {
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18}, new String[]{"uint32_result", "str_err_msg"}, new Object[]{Integer.valueOf(0), ""}, SubRsp_0x1.class);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x2 extends MessageMicro<SubRsp_0x2> {
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18}, new String[]{"uint32_result", "str_err_msg"}, new Object[]{Integer.valueOf(0), ""}, SubRsp_0x2.class);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x3 extends MessageMicro<SubRsp_0x3> {
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18}, new String[]{"uint32_result", "str_err_msg"}, new Object[]{Integer.valueOf(0), ""}, SubRsp_0x3.class);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x4 extends MessageMicro<SubRsp_0x4> {
        public static final int RPT_USER_INFO_FIELD_NUMBER = 3;
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_result", "str_err_msg", "rpt_user_info"}, new Object[]{Integer.valueOf(0), "", null}, SubRsp_0x4.class);
        public final PBRepeatMessageField<UserInfo> rpt_user_info = PBField.initRepeatMessage(UserInfo.class);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x5 extends MessageMicro<SubRsp_0x5> {
        public static final int STR_ERRORINFO_FIELD_NUMBER = 2;
        public static final int STR_FILEID_FIELD_NUMBER = 3;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_result", "str_errorinfo", "str_fileID"}, new Object[]{Integer.valueOf(0), "", ""}, SubRsp_0x5.class);
        public final PBStringField str_errorinfo = PBField.initString("");
        public final PBRepeatField<String> str_fileID = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class SubRsp_0x6 extends MessageMicro<SubRsp_0x6> {
        public static final int MSG_LIVE_URL_FIELD_NUMBER = 2;
        public static final int STR_ERRORINFO_FIELD_NUMBER = 3;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        public static final int UINT32_TAPE_TASK_ID_FIELD_NUMBER = 5;
        public static final int UINT64_CHANNEL_ID_FIELD_NUMBER = 4;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 32, 40}, new String[]{"uint32_result", "msg_live_url", "str_errorinfo", "uint64_channel_id", "uint32_tape_task_id"}, new Object[]{Integer.valueOf(0), null, "", Long.valueOf(0), Integer.valueOf(0)}, SubRsp_0x6.class);
        public final PBRepeatMessageField<LiveUrl> msg_live_url = PBField.initRepeatMessage(LiveUrl.class);
        public final PBStringField str_errorinfo = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
        public final PBUInt32Field uint32_tape_task_id = PBField.initUInt32(0);
        public final PBUInt64Field uint64_channel_id = PBField.initUInt64(0);
    }

    public static final class SubRsp_0x7 extends MessageMicro<SubRsp_0x7> {
        public static final int RPT_SHARE_USER_INFO_FIELD_NUMBER = 3;
        public static final int STR_ERR_MSG_FIELD_NUMBER = 2;
        public static final int UINT32_RESULT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_result", "str_err_msg", "rpt_share_user_info"}, new Object[]{Integer.valueOf(0), "", null}, SubRsp_0x7.class);
        public final PBRepeatMessageField<UserInfo> rpt_share_user_info = PBField.initRepeatMessage(UserInfo.class);
        public final PBStringField str_err_msg = PBField.initString("");
        public final PBUInt32Field uint32_result = PBField.initUInt32(0);
    }

    public static final class UserInfo extends MessageMicro<UserInfo> {
        public static final int UINT32_TERMINAL_FLAG_FIELD_NUMBER = 2;
        public static final int UINT64_UIN_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16}, new String[]{"uint64_uin", "uint32_terminal_flag"}, new Object[]{Long.valueOf(0), Integer.valueOf(0)}, UserInfo.class);
        public final PBUInt32Field uint32_terminal_flag = PBField.initUInt32(0);
        public final PBUInt64Field uint64_uin = PBField.initUInt64(0);
    }

    private gv_comm_operate() {
    }
}
