package com.tencent.qalsdk.im_open;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBEnumField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBInt32Field;
import com.tencent.mobileqq.pb.PBInt64Field;
import com.tencent.mobileqq.pb.PBRepeatField;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import qalsdk.b.a;

public final class http {
    public static final int Bad_Gateway = 502;
    public static final int Bad_Request = 400;
    public static final int Bytes = 2;
    public static final int Continue = 100;
    public static final int DELETE = 5;
    public static final int Forbidden = 403;
    public static final int Found = 302;
    public static final int GET = 1;
    public static final int GZip = 2;
    public static final int Gateway_Timeout = 504;
    public static final int HEAD = 2;
    public static final int Internal_Server_Error = 500;
    public static final int Moved_Permanently = 301;
    public static final int No_Content = 204;
    public static final int None = 1;
    public static final int Not_Found = 404;
    public static final int Not_Implemented = 501;
    public static final int Not_Modified = 304;
    public static final int Nothing = 1;
    public static final int OK = 200;
    public static final int OPTIONS = 7;
    public static final int POST = 3;
    public static final int PUT = 4;
    public static final int Partial_Content = 206;
    public static final int Request_Entity_Too_Large = 413;
    public static final int Request_Timeout = 408;
    public static final int Request_URI_Too_Long = 414;
    public static final int Requested_Range_Not_Satisfiable = 416;
    public static final int Service_Unavailable = 503;
    public static final int TRACE = 8;
    public static final int Unauthorized = 401;

    public static final class Pair extends MessageMicro<Pair> {
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int VALUE_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"key", "value"}, new Object[]{"", ""}, Pair.class);
        public final PBStringField key = PBField.initString("");
        public final PBStringField value = PBField.initString("");
    }

    public static final class PairBytes extends MessageMicro<PairBytes> {
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int VALUE_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"key", "value"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, PairBytes.class);
        public final PBBytesField key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField value = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class Request extends MessageMicro<Request> {
        public static final int ACCEPT_CHARSET_FIELD_NUMBER = 13;
        public static final int ACCEPT_FIELD_NUMBER = 11;
        public static final int ACCEPT_LANGUAGE_FIELD_NUMBER = 12;
        public static final int BODY_FIELD_NUMBER = 47;
        public static final int CACHE_CONTROL_FIELD_NUMBER = 35;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 3;
        public static final int COOKIE_FIELD_NUMBER = 21;
        public static final int IF_MATCH_FIELD_NUMBER = 34;
        public static final int IF_MODIFIED_SINCE_FIELD_NUMBER = 31;
        public static final int IF_NONE_MATCH_FIELD_NUMBER = 33;
        public static final int IF_RANGE_FIELD_NUMBER = 37;
        public static final int IF_UNMODIFIED_SINCE_FIELD_NUMBER = 32;
        public static final int METHOD_FIELD_NUMBER = 2;
        public static final int ORIGIN_FIELD_NUMBER = 23;
        public static final int OTHER_HEADERS_FIELD_NUMBER = 45;
        public static final int PRAGMA_FIELD_NUMBER = 36;
        public static final int PRIVATE_REQUST_FIELD_NUMBER = 48;
        public static final int REFERER_FIELD_NUMBER = 22;
        public static final int URI_FIELD_NUMBER = 1;
        public static final int USER_AGENT_FIELD_NUMBER = 20;
        public static final int X_REQUESTED_WITH_FIELD_NUMBER = 24;
        public static final int X_WWW_FORM_FIELD_NUMBER = 46;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26, 90, 98, 106, Opcodes.XOR_LONG, Opcodes.REM_FLOAT, Opcodes.MUL_INT_2ADDR, Opcodes.USHR_INT_2ADDR, Opcodes.XOR_LONG_2ADDR, 250, VoiceWakeuperAidl.RES_SPECIFIED, 266, 274, 282, 290, 298, 362, 370, 378, 386}, new String[]{"uri", "method", "content_type", "accept", "accept_language", "accept_charset", "user_agent", "cookie", "referer", s.ORIGIN, "x_requested_with", "if_modified_since", "if_unmodified_since", "if_none_match", "if_match", "cache_control", "pragma", "if_range", "other_headers", "x_www_form", "body", "private_requst"}, new Object[]{"", Integer.valueOf(1), "", "*/*", "", "", "", "", "", "", "", "", "", "", "", "", "", "", null, null, ByteStringMicro.EMPTY, null}, Request.class);
        public final PBStringField accept = PBField.initString("*/*");
        public final PBStringField accept_charset = PBField.initString("");
        public final PBStringField accept_language = PBField.initString("");
        public final PBBytesField body = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBRepeatField<String> cache_control = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBStringField content_type = PBField.initString("");
        public final PBStringField cookie = PBField.initString("");
        public final PBStringField if_match = PBField.initString("");
        public final PBStringField if_modified_since = PBField.initString("");
        public final PBStringField if_none_match = PBField.initString("");
        public final PBStringField if_range = PBField.initString("");
        public final PBStringField if_unmodified_since = PBField.initString("");
        public final PBEnumField method = PBField.initEnum(1);
        public final PBStringField origin = PBField.initString("");
        public final PBRepeatMessageField<Pair> other_headers = PBField.initRepeatMessage(Pair.class);
        public final PBStringField pragma = PBField.initString("");
        public RequestPrivate private_requst = new RequestPrivate();
        public final PBStringField referer = PBField.initString("");
        public final PBStringField uri = PBField.initString("");
        public final PBStringField user_agent = PBField.initString("");
        public final PBStringField x_requested_with = PBField.initString("");
        public final PBRepeatMessageField<PairBytes> x_www_form = PBField.initRepeatMessage(PairBytes.class);
    }

    public static final class RequestPrivate extends MessageMicro<RequestPrivate> {
        public static final int CHUNK_END_FIELD_NUMBER = 2;
        public static final int CHUNK_START_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16}, new String[]{"chunk_start", "chunk_end"}, new Object[]{Long.valueOf(-1), Long.valueOf(-1)}, RequestPrivate.class);
        public final PBInt64Field chunk_end = PBField.initInt64(-1);
        public final PBInt64Field chunk_start = PBField.initInt64(-1);
    }

    public static final class Response extends MessageMicro<Response> {
        public static final int AGE_FIELD_NUMBER = 21;
        public static final int BODY_FIELD_NUMBER = 42;
        public static final int CACHE_CONTROL_FIELD_NUMBER = 24;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
        public static final int DATE_FIELD_NUMBER = 10;
        public static final int ETAG_FIELD_NUMBER = 23;
        public static final int EXPIRES_FIELD_NUMBER = 25;
        public static final int LAST_MODIFIED_FIELD_NUMBER = 22;
        public static final int LOCATION_FIELD_NUMBER = 3;
        public static final int OTHER_HEADERS_FIELD_NUMBER = 41;
        public static final int PRAGMA_FIELD_NUMBER = 26;
        public static final int PRIVATE_RESPONSE_FIELD_NUMBER = 43;
        public static final int SERVER_FIELD_NUMBER = 11;
        public static final int SET_COOKIE_FIELD_NUMBER = 31;
        public static final int STATUS_CODE_FIELD_NUMBER = 1;
        public static final int VIA_FIELD_NUMBER = 12;
        public static final int X_CACHE_FIELD_NUMBER = 13;
        public static final int X_CACHE_LOOKUP_FIELD_NUMBER = 14;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 82, 90, 98, 106, 114, Opcodes.MUL_FLOAT, Opcodes.MUL_INT_2ADDR, Opcodes.USHR_INT_2ADDR, Opcodes.XOR_LONG_2ADDR, 202, 210, 250, ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, 338, 346}, new String[]{"status_code", "content_type", "location", MessageKey.MSG_DATE, "server", "via", "x_cache", "x_cache_lookup", "age", "last_modified", a.h, "cache_control", "expires", "pragma", "set_cookie", "other_headers", "body", "private_response"}, new Object[]{Integer.valueOf(200), "", "", "", "", "", "", "", Integer.valueOf(0), "", "", "", "", "", "", null, ByteStringMicro.EMPTY, null}, Response.class);
        public final PBUInt32Field age = PBField.initUInt32(0);
        public final PBBytesField body = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBRepeatField<String> cache_control = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBStringField content_type = PBField.initString("");
        public final PBStringField date = PBField.initString("");
        public final PBStringField etag = PBField.initString("");
        public final PBStringField expires = PBField.initString("");
        public final PBStringField last_modified = PBField.initString("");
        public final PBStringField location = PBField.initString("");
        public final PBRepeatMessageField<Pair> other_headers = PBField.initRepeatMessage(Pair.class);
        public final PBStringField pragma = PBField.initString("");
        public ResponsePrivate private_response = new ResponsePrivate();
        public final PBStringField server = PBField.initString("");
        public final PBRepeatField<String> set_cookie = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBInt32Field status_code = PBField.initInt32(200);
        public final PBStringField via = PBField.initString("");
        public final PBRepeatField<String> x_cache = PBField.initRepeat(PBStringField.__repeatHelper__);
        public final PBRepeatField<String> x_cache_lookup = PBField.initRepeat(PBStringField.__repeatHelper__);
    }

    public static final class ResponsePrivate extends MessageMicro<ResponsePrivate> {
        public static final int BODY_ENCODING_FIELD_NUMBER = 3;
        public static final int CACHE_MAX_AGE_FIELD_NUMBER = 1;
        public static final int CACHE_MAX_STALE_AGE_FIELD_NUMBER = 2;
        public static final int CHUNK_START_FIELD_NUMBER = 9;
        public static final int CONTENT_CHARSET_FIELD_NUMBER = 5;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 4;
        public static final int RAW_TOTAL_LENGTH_FIELD_NUMBER = 8;
        public static final int REQUEST_CHUNK_FIELD_NUMBER = 6;
        public static final int TOTAL_LENGTH_FIELD_NUMBER = 7;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 34, 42, 48, 56, 64, 72}, new String[]{"cache_max_age", "cache_max_stale_age", "body_encoding", "content_type", "content_charset", "request_chunk", "total_length", "raw_total_length", "chunk_start"}, new Object[]{Long.valueOf(-1), Long.valueOf(-1), Integer.valueOf(1), "", "utf-8", Integer.valueOf(2), Long.valueOf(-1), Long.valueOf(-1), Long.valueOf(-1)}, ResponsePrivate.class);
        public final PBEnumField body_encoding = PBField.initEnum(1);
        public final PBInt64Field cache_max_age = PBField.initInt64(-1);
        public final PBInt64Field cache_max_stale_age = PBField.initInt64(-1);
        public final PBInt64Field chunk_start = PBField.initInt64(-1);
        public final PBStringField content_charset = PBField.initString("utf-8");
        public final PBStringField content_type = PBField.initString("");
        public final PBInt64Field raw_total_length = PBField.initInt64(-1);
        public final PBEnumField request_chunk = PBField.initEnum(2);
        public final PBInt64Field total_length = PBField.initInt64(-1);
    }

    private http() {
    }
}
