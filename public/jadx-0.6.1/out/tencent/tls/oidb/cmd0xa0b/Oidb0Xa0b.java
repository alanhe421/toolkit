package tencent.tls.oidb.cmd0xa0b;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;

public final class Oidb0Xa0b {

    public static final class ReqBody extends MessageMicro<ReqBody> {
        public static final int BYTES_USERSIG_FIELD_NUMBER = 4;
        public static final int STR_APPIDAT3RD_FIELD_NUMBER = 2;
        public static final int STR_IDENTIFIER_FIELD_NUMBER = 3;
        public static final int UINT32_ACCOUNTTYPE_FIELD_NUMBER = 5;
        public static final int UINT32_SDKAPPID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34, 40}, new String[]{"uint32_sdkappid", "str_appidat3rd", "str_identifier", "bytes_usersig", "uint32_accounttype"}, new Object[]{Integer.valueOf(0), "", "", ByteStringMicro.EMPTY, Integer.valueOf(0)}, ReqBody.class);
        public final PBBytesField bytes_usersig = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBStringField str_appidat3rd = PBField.initString("");
        public final PBStringField str_identifier = PBField.initString("");
        public final PBUInt32Field uint32_accounttype = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sdkappid = PBField.initUInt32(0);
    }

    public static final class RspBody extends MessageMicro<RspBody> {
        public static final int BYTES_A2_FIELD_NUMBER = 2;
        public static final int BYTES_D2KEY_FIELD_NUMBER = 4;
        public static final int BYTES_D2_FIELD_NUMBER = 3;
        public static final int STR_ERRHINT_FIELD_NUMBER = 5;
        public static final int STR_IDENTIFIER_FIELD_NUMBER = 7;
        public static final int STR_TLSSIG_FIELD_NUMBER = 8;
        public static final int UINT32_ADMINFLAG_FIELD_NUMBER = 6;
        public static final int UINT64_TINYID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34, 42, 48, 58, 66}, new String[]{"uint64_tinyid", "bytes_a2", "bytes_d2", "bytes_d2key", "str_errhint", "uint32_adminflag", "str_identifier", "str_tlssig"}, new Object[]{Long.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, "", Integer.valueOf(0), "", ""}, RspBody.class);
        public final PBBytesField bytes_a2 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_d2 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_d2key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBStringField str_errhint = PBField.initString("");
        public final PBStringField str_identifier = PBField.initString("");
        public final PBStringField str_tlssig = PBField.initString("");
        public final PBUInt32Field uint32_adminflag = PBField.initUInt32(0);
        public final PBUInt64Field uint64_tinyid = PBField.initUInt64(0);
    }

    private Oidb0Xa0b() {
    }
}
