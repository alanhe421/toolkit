package com.tencent.openqq.protocol.imsdk;

import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBUInt32Field;

public final class stat_get_pushsound$Config extends MessageMicro<stat_get_pushsound$Config> {
    public static final int BYTES_C2C_SOUND_FIELD_NUMBER = 2;
    public static final int BYTES_GRP_SOUND_FIELD_NUMBER = 3;
    public static final int BYTES_VIDEO_SOUND_FIELD_NUMBER = 4;
    public static final int UINT32_OPENPUSH_FIELD_NUMBER = 1;
    static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34}, new String[]{"uint32_openpush", "bytes_c2c_sound", "bytes_grp_sound", "bytes_video_sound"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, stat_get_pushsound$Config.class);
    public final PBBytesField bytes_c2c_sound = PBField.initBytes(ByteStringMicro.EMPTY);
    public final PBBytesField bytes_grp_sound = PBField.initBytes(ByteStringMicro.EMPTY);
    public final PBBytesField bytes_video_sound = PBField.initBytes(ByteStringMicro.EMPTY);
    public final PBUInt32Field uint32_openpush = PBField.initUInt32(0);
}
