package tencent.tls.oidb.cmd0x483;

import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;

public final class Oidb0X483 {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int STR_CODE_FIELD_NUMBER = 3;
        public static final int UINT32_ACCOUNT_TYPE_FIELD_NUMBER = 2;
        public static final int UINT32_APPID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26}, new String[]{"uint32_appid", "uint32_account_type", "str_code"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), ""}, ReqBody.class);
        public final PBStringField str_code = PBField.initString("");
        public final PBUInt32Field uint32_account_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_appid = PBField.initUInt32(0);
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int STR_ERRHINT_FIELD_NUMBER = 1;
        public static final int ST_TOKEN_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"str_errhint", "st_token"}, new Object[]{"", null}, RspBody.class);
        public TokenInfo st_token = new TokenInfo();
        public final PBStringField str_errhint = PBField.initString("");
    }

    public static final class TokenInfo extends MessageMicro<TokenInfo> {
        public static final int STR_ACCESS_TOKEN_FIELD_NUMBER = 1;
        public static final int STR_OPENID_FIELD_NUMBER = 4;
        public static final int STR_REFRESH_TOKEN_FIELD_NUMBER = 3;
        public static final int STR_SCOPE_FIELD_NUMBER = 5;
        public static final int STR_UNIONID_FIELD_NUMBER = 6;
        public static final int UINT32_EXPIRES_IN_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26, 34, 42, 50}, new String[]{"str_access_token", "uint32_expires_in", "str_refresh_token", "str_openid", "str_scope", "str_unionid"}, new Object[]{"", Integer.valueOf(0), "", "", "", ""}, TokenInfo.class);
        public final PBStringField str_access_token = PBField.initString("");
        public final PBStringField str_openid = PBField.initString("");
        public final PBStringField str_refresh_token = PBField.initString("");
        public final PBStringField str_scope = PBField.initString("");
        public final PBStringField str_unionid = PBField.initString("");
        public final PBUInt32Field uint32_expires_in = PBField.initUInt32(0);
    }

    private Oidb0X483() {
    }
}
