package com.tencent.qalsdk.im_open;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.PBBoolField;
import com.tencent.mobileqq.pb.PBEnumField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBInt32Field;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.qalsdk.core.c;

public final class QalMonitor {

    public static final class Request extends MessageMicro<Request> {
        public static final int Android = 1;
        public static final int Background = 2;
        public static final int CONN_FIELD_NUMBER = 2;
        public static final int DROP_FIELD_NUMBER = 4;
        public static final int Foreground = 1;
        public static final int HTTP_FIELD_NUMBER = 3;
        public static final int HttpDecodeErr = 2;
        public static final int HttpTimeout = 1;
        public static final int MacOS = 4;
        public static final int NoHttpRequest = 3;
        public static final int SEQNO_FIELD_NUMBER = 1;
        public static final int Unknow = 3;
        public static final int WindowsPhone = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 32}, new String[]{"seqno", "conn", c.d, "drop"}, new Object[]{Integer.valueOf(0), null, null, Integer.valueOf(0)}, Request.class);
        public static final int fail = 2;
        public static final int iOS = 2;
        public static final int logic_fail = 3;
        public static final int success = 1;
        public final PBRepeatMessageField<Conn> conn = PBField.initRepeatMessage(Conn.class);
        public final PBUInt32Field drop = PBField.initUInt32(0);
        public final PBRepeatMessageField<Http> http = PBField.initRepeatMessage(Http.class);
        public final PBUInt32Field seqno = PBField.initUInt32(0);

        public static final class Conn extends MessageMicro<Conn> {
            public static final int APN_FIELD_NUMBER = 8;
            public static final int COST_TIME_FIELD_NUMBER = 2;
            public static final int ENV_FIELD_NUMBER = 15;
            public static final int ERRCODE_FIELD_NUMBER = 13;
            public static final int ERRMSG_FIELD_NUMBER = 14;
            public static final int GATEWAY_IP_FIELD_NUMBER = 11;
            public static final int OS_FIELD_NUMBER = 4;
            public static final int OS_VERSION_FIELD_NUMBER = 5;
            public static final int PROCESS_STATUS_FIELD_NUMBER = 7;
            public static final int RADIO_ACCESS_FIELD_NUMBER = 12;
            public static final int RESULT_FIELD_NUMBER = 3;
            public static final int SDK_VERSION_FIELD_NUMBER = 6;
            public static final int SERVER_IP_PORT_FIELD_NUMBER = 10;
            public static final int SSID_FIELD_NUMBER = 9;
            public static final int TIMESTAMP_FIELD_NUMBER = 1;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 42, 50, 56, 66, 74, 82, 90, 96, 104, 114, 122}, new String[]{"timestamp", "cost_time", "result", "os", "os_version", "sdk_version", "process_status", "apn", "ssid", "server_ip_port", "gateway_ip", "radio_access", "errcode", "errmsg", "env"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), "", "", Integer.valueOf(1), "", "", "", "", Integer.valueOf(0), Integer.valueOf(0), "", null}, Conn.class);
            public final PBStringField apn = PBField.initString("");
            public final PBInt32Field cost_time = PBField.initInt32(0);
            public final PBRepeatMessageField<Environment> env = PBField.initRepeatMessage(Environment.class);
            public final PBInt32Field errcode = PBField.initInt32(0);
            public final PBStringField errmsg = PBField.initString("");
            public final PBStringField gateway_ip = PBField.initString("");
            public final PBEnumField os = PBField.initEnum(1);
            public final PBStringField os_version = PBField.initString("");
            public final PBEnumField process_status = PBField.initEnum(1);
            public final PBInt32Field radio_access = PBField.initInt32(0);
            public final PBEnumField result = PBField.initEnum(1);
            public final PBStringField sdk_version = PBField.initString("");
            public final PBStringField server_ip_port = PBField.initString("");
            public final PBStringField ssid = PBField.initString("");
            public final PBUInt32Field timestamp = PBField.initUInt32(0);
        }

        public static final class Environment extends MessageMicro<Environment> {
            public static final int APN_FIELD_NUMBER = 1;
            public static final int RAT_FIELD_NUMBER = 6;
            public static final int RAT_SS_FIELD_NUMBER = 7;
            public static final int WIFI_BSSID_FIELD_NUMBER = 4;
            public static final int WIFI_RSSI_FIELD_NUMBER = 5;
            public static final int WIFI_SSID_FIELD_NUMBER = 3;
            public static final int WIFI_SUPPLICANT_STATE_FIELD_NUMBER = 2;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 40, 48, 56}, new String[]{"apn", "wifi_supplicant_state", "wifi_ssid", "wifi_bssid", "wifi_rssi", "rat", "rat_ss"}, new Object[]{"", "", "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, Environment.class);
            public final PBStringField apn = PBField.initString("");
            public final PBInt32Field rat = PBField.initInt32(0);
            public final PBInt32Field rat_ss = PBField.initInt32(0);
            public final PBStringField wifi_bssid = PBField.initString("");
            public final PBInt32Field wifi_rssi = PBField.initInt32(0);
            public final PBStringField wifi_ssid = PBField.initString("");
            public final PBStringField wifi_supplicant_state = PBField.initString("");
        }

        public static final class Http extends MessageMicro<Http> {
            public static final int APN_FIELD_NUMBER = 7;
            public static final int CACHE_COST_FIELD_NUMBER = 3;
            public static final int CODE_FIELD_NUMBER = 5;
            public static final int ERRMSG_FIELD_NUMBER = 11;
            public static final int GATEWAY_IP_FIELD_NUMBER = 10;
            public static final int HIT_CACHE_FIELD_NUMBER = 6;
            public static final int RADIO_ACCESS_FIELD_NUMBER = 8;
            public static final int SERVER_IP_FIELD_NUMBER = 9;
            public static final int TIMESTAMP_FIELD_NUMBER = 1;
            public static final int TOTAL_COST_FIELD_NUMBER = 4;
            public static final int URI_FIELD_NUMBER = 2;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 24, 32, 40, 48, 58, 64, 74, 82, 90}, new String[]{"timestamp", "uri", "cache_cost", "total_cost", "code", "hit_cache", "apn", "radio_access", "server_ip", "gateway_ip", "errmsg"}, new Object[]{Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.valueOf(false), "", Integer.valueOf(0), "", "", ""}, Http.class);
            public final PBStringField apn = PBField.initString("");
            public final PBInt32Field cache_cost = PBField.initInt32(0);
            public final PBInt32Field code = PBField.initInt32(0);
            public final PBStringField errmsg = PBField.initString("");
            public final PBStringField gateway_ip = PBField.initString("");
            public final PBBoolField hit_cache = PBField.initBool(false);
            public final PBInt32Field radio_access = PBField.initInt32(0);
            public final PBStringField server_ip = PBField.initString("");
            public final PBUInt32Field timestamp = PBField.initUInt32(0);
            public final PBInt32Field total_cost = PBField.initInt32(0);
            public final PBStringField uri = PBField.initString("");
        }
    }

    public static final class Response extends MessageMicro<Response> {
        public static final int SEQNO_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"seqno"}, new Object[]{Integer.valueOf(0)}, Response.class);
        public final PBUInt32Field seqno = PBField.initUInt32(0);
    }

    private QalMonitor() {
    }
}
