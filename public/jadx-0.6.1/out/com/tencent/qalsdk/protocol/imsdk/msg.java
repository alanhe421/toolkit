package com.tencent.qalsdk.protocol.imsdk;

import com.etrump.jni.ETConverter;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.mobileqq.pb.MessageMicro.FieldMap;
import com.tencent.mobileqq.pb.PBBoolField;
import com.tencent.mobileqq.pb.PBBytesField;
import com.tencent.mobileqq.pb.PBDoubleField;
import com.tencent.mobileqq.pb.PBEnumField;
import com.tencent.mobileqq.pb.PBField;
import com.tencent.mobileqq.pb.PBInt32Field;
import com.tencent.mobileqq.pb.PBRepeatField;
import com.tencent.mobileqq.pb.PBRepeatMessageField;
import com.tencent.mobileqq.pb.PBSInt32Field;
import com.tencent.mobileqq.pb.PBStringField;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.mobileqq.pb.PBUInt64Field;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public final class msg {

    public static final class AnonymousGroupMsg extends MessageMicro<AnonymousGroupMsg> {
        public static final int STR_ANON_ID_FIELD_NUMBER = 2;
        public static final int STR_ANON_NICK_FIELD_NUMBER = 3;
        public static final int UINT32_BUBBLE_ID_FIELD_NUMBER = 6;
        public static final int UINT32_EXPIRE_TIME_FIELD_NUMBER = 5;
        public static final int UINT32_FLAGS_FIELD_NUMBER = 1;
        public static final int UINT32_HEAD_PORTRAIT_FIELD_NUMBER = 4;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 32, 40, 48}, new String[]{"uint32_flags", "str_anon_id", "str_anon_nick", "uint32_head_portrait", "uint32_expire_time", "uint32_bubble_id"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, AnonymousGroupMsg.class);
        public final PBBytesField str_anon_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField str_anon_nick = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_bubble_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_expire_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_flags = PBField.initUInt32(0);
        public final PBUInt32Field uint32_head_portrait = PBField.initUInt32(0);
    }

    public static final class ArkAppElem extends MessageMicro<ArkAppElem> {
        public static final int APP_NAME_FIELD_NUMBER = 1;
        public static final int MIN_VERSION_FIELD_NUMBER = 2;
        public static final int XML_TEMPLATE_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26}, new String[]{"app_name", "min_version", "xml_template"}, new Object[]{"", "", ""}, ArkAppElem.class);
        public final PBStringField app_name = PBField.initString("");
        public final PBStringField min_version = PBField.initString("");
        public final PBStringField xml_template = PBField.initString("");
    }

    public static final class Attr extends MessageMicro<Attr> {
        public static final int CHAR_SET_FIELD_NUMBER = 7;
        public static final int CODE_PAGE_FIELD_NUMBER = 1;
        public static final int COLOR_FIELD_NUMBER = 4;
        public static final int EFFECT_FIELD_NUMBER = 6;
        public static final int FONT_NAME_FIELD_NUMBER = 9;
        public static final int PITCH_AND_FAMILY_FIELD_NUMBER = 8;
        public static final int RANDOM_FIELD_NUMBER = 3;
        public static final int RESERVE_DATA_FIELD_NUMBER = 10;
        public static final int SIZE_FIELD_NUMBER = 5;
        public static final int TIME_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48, 56, 64, 74, 82}, new String[]{"code_page", "time", "random", "color", "size", "effect", "char_set", "pitch_and_family", "font_name", "reserve_data"}, new Object[]{Integer.valueOf(-1), Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(7), Integer.valueOf(78), Integer.valueOf(90), "Times New Roman", ByteStringMicro.EMPTY}, Attr.class);
        public final PBUInt32Field char_set = PBField.initUInt32(78);
        public final PBSInt32Field code_page = PBField.initSInt32(-1);
        public final PBUInt32Field color = PBField.initUInt32(0);
        public final PBUInt32Field effect = PBField.initUInt32(7);
        public final PBStringField font_name = PBField.initString("Times New Roman");
        public final PBUInt32Field pitch_and_family = PBField.initUInt32(90);
        public final PBUInt32Field random = PBField.initUInt32(0);
        public final PBBytesField reserve_data = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field size = PBField.initUInt32(10);
        public final PBUInt32Field time = PBField.initUInt32(1);
    }

    public static final class ConferenceTipsInfo extends MessageMicro<ConferenceTipsInfo> {
        public static final int ECONFST_BUDDY_AV = 2;
        public static final int ECONFST_DISCUSS_AV = 1;
        public static final int ECONFST_NONE = 0;
        public static final int STR_TEXT_FIELD_NUMBER = 3;
        public static final int UINT32_SESSION_TYPE_FIELD_NUMBER = 1;
        public static final int UINT64_SESSION_UIN_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26}, new String[]{"uint32_session_type", "uint64_session_uin", "str_text"}, new Object[]{Integer.valueOf(0), Long.valueOf(0), ""}, ConferenceTipsInfo.class);
        public final PBStringField str_text = PBField.initString("");
        public final PBUInt32Field uint32_session_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_session_uin = PBField.initUInt64(0);
    }

    public static final class CrmElem extends MessageMicro<CrmElem> {
        public static final int BYTES_MSG_RESID_FIELD_NUMBER = 2;
        public static final int CRM_BUF_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"crm_buf", "bytes_msg_resid"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, CrmElem.class);
        public final PBBytesField bytes_msg_resid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField crm_buf = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class CustomElem extends MessageMicro<CustomElem> {
        public static final int BYTES_DATA_FIELD_NUMBER = 2;
        public static final int BYTES_DESC_FIELD_NUMBER = 1;
        public static final int BYTES_EXT_FIELD_NUMBER = 4;
        public static final int DataType_Bin = 2;
        public static final int DataType_Text = 1;
        public static final int ENUM_TYPE_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 24, 34}, new String[]{"bytes_desc", "bytes_data", "enum_type", "bytes_ext"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(1), ByteStringMicro.EMPTY}, CustomElem.class);
        public final PBBytesField bytes_data = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_desc = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_ext = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBEnumField enum_type = PBField.initEnum(1);
    }

    public static final class CustomFace extends MessageMicro<CustomFace> {
        public static final int BIZ_TYPE_FIELD_NUMBER = 17;
        public static final int BYTES_BUFFER_FIELD_NUMBER = 4;
        public static final int BYTES_FLAG_FIELD_NUMBER = 5;
        public static final int BYTES_GUID_FIELD_NUMBER = 1;
        public static final int BYTES_MD5_FIELD_NUMBER = 13;
        public static final int BYTES_OLD_DATA_FIELD_NUMBER = 6;
        public static final int BYTES_SIGNATURE_FIELD_NUMBER = 11;
        public static final int IMAGE_TYPE_FIELD_NUMBER = 20;
        public static final int INDEX_FIELD_NUMBER = 21;
        public static final int REPEAT_IMAGE_FIELD_NUMBER = 19;
        public static final int REPEAT_INDEX_FIELD_NUMBER = 18;
        public static final int STR_BIG_URL_FIELD_NUMBER = 15;
        public static final int STR_FILE_PATH_FIELD_NUMBER = 2;
        public static final int STR_ORIG_URL_FIELD_NUMBER = 16;
        public static final int STR_SHORTCUT_FIELD_NUMBER = 3;
        public static final int STR_THUMB_URL_FIELD_NUMBER = 14;
        public static final int UINT32_DOWNLOAD_LEN_FIELD_NUMBER = 30;
        public static final int UINT32_FILE_ID_FIELD_NUMBER = 7;
        public static final int UINT32_FILE_TYPE_FIELD_NUMBER = 10;
        public static final int UINT32_HEIGHT_FIELD_NUMBER = 23;
        public static final int UINT32_ORIGIN_FIELD_NUMBER = 26;
        public static final int UINT32_SERVER_IP_FIELD_NUMBER = 8;
        public static final int UINT32_SERVER_PORT_FIELD_NUMBER = 9;
        public static final int UINT32_SHOW_LEN_FIELD_NUMBER = 29;
        public static final int UINT32_SIZE_FIELD_NUMBER = 25;
        public static final int UINT32_SOURCE_FIELD_NUMBER = 24;
        public static final int UINT32_THUMB_HEIGHT_FIELD_NUMBER = 28;
        public static final int UINT32_THUMB_WIDTH_FIELD_NUMBER = 27;
        public static final int UINT32_USEFUL_FIELD_NUMBER = 12;
        public static final int UINT32_WIDTH_FIELD_NUMBER = 22;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 42, 50, 56, 64, 72, 80, 90, 96, 106, 114, 122, Opcodes.INT_TO_FLOAT, Opcodes.FLOAT_TO_LONG, Opcodes.ADD_INT, Opcodes.SHL_INT, 160, Opcodes.MUL_FLOAT, Opcodes.ADD_INT_2ADDR, Opcodes.SHL_INT_2ADDR, Opcodes.AND_LONG_2ADDR, 200, 208, 216, Opcodes.SHL_INT_LIT8, 232, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK}, new String[]{"bytes_guid", "str_file_path", "str_shortcut", "bytes_buffer", "bytes_flag", "bytes_old_data", "uint32_file_id", "uint32_server_ip", "uint32_server_port", "uint32_file_type", "bytes_signature", "uint32_useful", "bytes_md5", "str_thumb_url", "str_big_url", "str_orig_url", "biz_type", "repeat_index", "repeat_image", "image_type", "index", "uint32_width", "uint32_height", "uint32_source", "uint32_size", "uint32_origin", "uint32_thumb_width", "uint32_thumb_height", "uint32_show_len", "uint32_download_len"}, new Object[]{ByteStringMicro.EMPTY, "", "", ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, "", "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, CustomFace.class);
        public final PBUInt32Field biz_type = PBField.initUInt32(0);
        public final PBBytesField bytes_buffer = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_flag = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_guid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_old_data = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_signature = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field image_type = PBField.initUInt32(0);
        public final PBUInt32Field index = PBField.initUInt32(0);
        public final PBUInt32Field repeat_image = PBField.initUInt32(0);
        public final PBUInt32Field repeat_index = PBField.initUInt32(0);
        public final PBStringField str_big_url = PBField.initString("");
        public final PBStringField str_file_path = PBField.initString("");
        public final PBStringField str_orig_url = PBField.initString("");
        public final PBStringField str_shortcut = PBField.initString("");
        public final PBStringField str_thumb_url = PBField.initString("");
        public final PBUInt32Field uint32_download_len = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_height = PBField.initUInt32(0);
        public final PBUInt32Field uint32_origin = PBField.initUInt32(0);
        public final PBUInt32Field uint32_server_ip = PBField.initUInt32(0);
        public final PBUInt32Field uint32_server_port = PBField.initUInt32(0);
        public final PBUInt32Field uint32_show_len = PBField.initUInt32(0);
        public final PBUInt32Field uint32_size = PBField.initUInt32(0);
        public final PBUInt32Field uint32_source = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_height = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_width = PBField.initUInt32(0);
        public final PBUInt32Field uint32_useful = PBField.initUInt32(0);
        public final PBUInt32Field uint32_width = PBField.initUInt32(0);
    }

    public static final class DeliverGiftMsg extends MessageMicro<DeliverGiftMsg> {
        public static final int STRING_ANIMATION_CONTENT_FIELD_NUMBER = 2;
        public static final int STRING_GRAY_TIP_CONTENT_FIELD_NUMBER = 1;
        public static final int STRING_REMIND_BRIEF_FIELD_NUMBER = 3;
        public static final int UINT64_RECEIVER_UIN_FIELD_NUMBER = 5;
        public static final int UINT64_SENDER_UIN_FIELD_NUMBER = 4;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 32, 40}, new String[]{"string_gray_tip_content", "string_animation_content", "string_remind_brief", "uint64_sender_uin", "uint64_receiver_uin"}, new Object[]{"", "", "", Long.valueOf(0), Long.valueOf(0)}, DeliverGiftMsg.class);
        public final PBStringField string_animation_content = PBField.initString("");
        public final PBStringField string_gray_tip_content = PBField.initString("");
        public final PBStringField string_remind_brief = PBField.initString("");
        public final PBUInt64Field uint64_receiver_uin = PBField.initUInt64(0);
        public final PBUInt64Field uint64_sender_uin = PBField.initUInt64(0);
    }

    public static final class Elem extends MessageMicro<Elem> {
        public static final int ANON_GROUP_MSG_FIELD_NUMBER = 21;
        public static final int ARK_APP_FIELD_NUMBER = 36;
        public static final int BANKCODE_CTRL_INFO_FIELD_NUMBER = 29;
        public static final int CONFERENCE_TIPS_INFO_FIELD_NUMBER = 26;
        public static final int CRM_ELEM_FIELD_NUMBER = 25;
        public static final int CUSTOM_ELEM_FIELD_NUMBER = 31;
        public static final int CUSTOM_FACE_FIELD_NUMBER = 8;
        public static final int DELIVER_GIFT_MSG_FIELD_NUMBER = 39;
        public static final int ELEM_FLAGS2_FIELD_NUMBER = 9;
        public static final int ELEM_FLAGS_FIELD_NUMBER = 7;
        public static final int EXTRA_INFO_FIELD_NUMBER = 16;
        public static final int FACE_FIELD_NUMBER = 2;
        public static final int FSJ_MSG_ELEM_FIELD_NUMBER = 35;
        public static final int FUN_FACE_FIELD_NUMBER = 10;
        public static final int GENERAL_FLAGS_FIELD_NUMBER = 37;
        public static final int GROUP_FILE_FIELD_NUMBER = 13;
        public static final int HC_FLASH_PIC_FIELD_NUMBER = 38;
        public static final int LIFE_ONLINE_FIELD_NUMBER = 23;
        public static final int LOCATION_INFO_FIELD_NUMBER = 32;
        public static final int LOW_VERSION_TIPS_FIELD_NUMBER = 28;
        public static final int MARKET_FACE_FIELD_NUMBER = 6;
        public static final int MARKET_TRANS_FIELD_NUMBER = 15;
        public static final int NEAR_BY_MSG_FIELD_NUMBER = 30;
        public static final int NOT_ONLINE_IMAGE_FIELD_NUMBER = 4;
        public static final int ONLINE_IMAGE_FIELD_NUMBER = 3;
        public static final int PUB_ACCOUNT_FIELD_NUMBER = 18;
        public static final int PUB_ACC_INFO_FIELD_NUMBER = 33;
        public static final int PUB_GROUP_FIELD_NUMBER = 14;
        public static final int QQWALLET_MSG_FIELD_NUMBER = 24;
        public static final int QQ_LIVE_OLD_FIELD_NUMBER = 22;
        public static final int REDBAG_INFO_FIELD_NUMBER = 27;
        public static final int RICH_MSG_FIELD_NUMBER = 12;
        public static final int SECRET_FILE_FIELD_NUMBER = 11;
        public static final int SHAKE_WINDOW_FIELD_NUMBER = 17;
        public static final int SMALL_EMOJI_FIELD_NUMBER = 34;
        public static final int TEXT_FIELD_NUMBER = 1;
        public static final int TIPS_INFO_FIELD_NUMBER = 20;
        public static final int TRANS_ELEM_INFO_FIELD_NUMBER = 5;
        public static final int VIDEO_FILE_FIELD_NUMBER = 19;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 42, 50, 58, 66, 74, 82, 90, 98, 106, 114, 122, Opcodes.INT_TO_FLOAT, Opcodes.DOUBLE_TO_INT, Opcodes.MUL_INT, Opcodes.USHR_INT, Opcodes.XOR_LONG, Opcodes.REM_FLOAT, Opcodes.MUL_INT_2ADDR, Opcodes.USHR_INT_2ADDR, Opcodes.XOR_LONG_2ADDR, 202, 210, 218, Opcodes.USHR_INT_LIT8, 234, 242, 250, VoiceWakeuperAidl.RES_SPECIFIED, 266, 274, 282, 290, 298, 306, ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL}, new String[]{"text", "face", "online_image", "not_online_image", "trans_elem_info", "market_face", "elem_flags", "custom_face", "elem_flags2", "fun_face", "secret_file", "rich_msg", "group_file", "pub_group", "market_trans", "extra_info", "shake_window", "pub_account", "video_file", "tips_info", "anon_group_msg", "qq_live_old", "life_online", "qqwallet_msg", "crm_elem", "conference_tips_info", "redbag_info", "low_version_tips", "bankcode_ctrl_info", "near_by_msg", "custom_elem", "location_info", "pub_acc_info", "small_emoji", "fsj_msg_elem", "ark_app", "general_flags", "hc_flash_pic", "deliver_gift_msg"}, new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ByteStringMicro.EMPTY, null, null, null, null, null, null, null, null, null, null}, Elem.class);
        public AnonymousGroupMsg anon_group_msg = new AnonymousGroupMsg();
        public ArkAppElem ark_app = new ArkAppElem();
        public final PBBytesField bankcode_ctrl_info = PBField.initBytes(ByteStringMicro.EMPTY);
        public ConferenceTipsInfo conference_tips_info = new ConferenceTipsInfo();
        public CrmElem crm_elem = new CrmElem();
        public CustomElem custom_elem = new CustomElem();
        public CustomFace custom_face = new CustomFace();
        public DeliverGiftMsg deliver_gift_msg = new DeliverGiftMsg();
        public ElemFlags elem_flags = new ElemFlags();
        public ElemFlags2 elem_flags2 = new ElemFlags2();
        public ExtraInfo extra_info = new ExtraInfo();
        public Face face = new Face();
        public FSJMessageElem fsj_msg_elem = new FSJMessageElem();
        public FunFace fun_face = new FunFace();
        public GeneralFlags general_flags = new GeneralFlags();
        public GroupFile group_file = new GroupFile();
        public CustomFace hc_flash_pic = new CustomFace();
        public LifeOnlineAccount life_online = new LifeOnlineAccount();
        public LocationInfo location_info = new LocationInfo();
        public LowVersionTips low_version_tips = new LowVersionTips();
        public MarketFace market_face = new MarketFace();
        public MarketTrans market_trans = new MarketTrans();
        public NearByMessageType near_by_msg = new NearByMessageType();
        public NotOnlineImage not_online_image = new NotOnlineImage();
        public OnlineImage online_image = new OnlineImage();
        public PubAccInfo pub_acc_info = new PubAccInfo();
        public PubAccount pub_account = new PubAccount();
        public PubGroup pub_group = new PubGroup();
        public QQLiveOld qq_live_old = new QQLiveOld();
        public QQWalletMsg qqwallet_msg = new QQWalletMsg();
        public RedBagInfo redbag_info = new RedBagInfo();
        public RichMsg rich_msg = new RichMsg();
        public SecretFileMsg secret_file = new SecretFileMsg();
        public ShakeWindow shake_window = new ShakeWindow();
        public SmallEmoji small_emoji = new SmallEmoji();
        public Text text = new Text();
        public TipsInfo tips_info = new TipsInfo();
        public TransElem trans_elem_info = new TransElem();
        public VideoFile video_file = new VideoFile();
    }

    public static final class ElemFlags2 extends MessageMicro<ElemFlags2> {
        public static final int PC_SUPPORT_DEF_FIELD_NUMBER = 13;
        public static final int RPT_INSTS_FIELD_NUMBER = 7;
        public static final int SRC_INST_FIELD_NUMBER = 9;
        public static final int UINT32_COLOR_TEXT_ID_FIELD_NUMBER = 1;
        public static final int UINT32_COMPATIBLE_ID_FIELD_NUMBER = 6;
        public static final int UINT32_CRM_FLAGS_FIELD_NUMBER = 14;
        public static final int UINT32_CUSTOM_FONT_FIELD_NUMBER = 12;
        public static final int UINT32_LATITUDE_FIELD_NUMBER = 11;
        public static final int UINT32_LONGTITUDE_FIELD_NUMBER = 10;
        public static final int UINT32_MSG_RPT_CNT_FIELD_NUMBER = 8;
        public static final int UINT32_PTT_CHANGE_BIT_FIELD_NUMBER = 4;
        public static final int UINT32_VIP_STATUS_FIELD_NUMBER = 5;
        public static final int UINT32_WHISPER_SESSION_ID_FIELD_NUMBER = 3;
        public static final int UINT64_MSG_ID_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48, 58, 64, 74, 80, 88, 96, 106, 112}, new String[]{"uint32_color_text_id", "uint64_msg_id", "uint32_whisper_session_id", "uint32_ptt_change_bit", "uint32_vip_status", "uint32_compatible_id", "rpt_insts", "uint32_msg_rpt_cnt", "src_inst", "uint32_longtitude", "uint32_latitude", "uint32_custom_font", "pc_support_def", "uint32_crm_flags"}, new Object[]{Integer.valueOf(0), Long.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0)}, ElemFlags2.class);
        public PcSupportDef pc_support_def = new PcSupportDef();
        public final PBRepeatMessageField<Inst> rpt_insts = PBField.initRepeatMessage(Inst.class);
        public Inst src_inst = new Inst();
        public final PBUInt32Field uint32_color_text_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_compatible_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_crm_flags = PBField.initUInt32(0);
        public final PBUInt32Field uint32_custom_font = PBField.initUInt32(0);
        public final PBUInt32Field uint32_latitude = PBField.initUInt32(0);
        public final PBUInt32Field uint32_longtitude = PBField.initUInt32(0);
        public final PBUInt32Field uint32_msg_rpt_cnt = PBField.initUInt32(0);
        public final PBUInt32Field uint32_ptt_change_bit = PBField.initUInt32(0);
        public final PBUInt32Field uint32_vip_status = PBField.initUInt32(0);
        public final PBUInt32Field uint32_whisper_session_id = PBField.initUInt32(0);
        public final PBUInt64Field uint64_msg_id = PBField.initUInt64(0);

        public static final class Inst extends MessageMicro<Inst> {
            public static final int UINT32_APP_ID_FIELD_NUMBER = 1;
            public static final int UINT32_INST_ID_FIELD_NUMBER = 2;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16}, new String[]{"uint32_app_id", "uint32_inst_id"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0)}, Inst.class);
            public final PBUInt32Field uint32_app_id = PBField.initUInt32(0);
            public final PBUInt32Field uint32_inst_id = PBField.initUInt32(0);
        }
    }

    public static final class ElemFlags extends MessageMicro<ElemFlags> {
        public static final int BYTES_BUSINESS_DATA_FIELD_NUMBER = 2;
        public static final int BYTES_FLAGS1_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"bytes_flags1", "bytes_business_data"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, ElemFlags.class);
        public final PBBytesField bytes_business_data = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_flags1 = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class ExtraInfo extends MessageMicro<ExtraInfo> {
        public static final int BYTES_GROUP_CARD_FIELD_NUMBER = 2;
        public static final int BYTES_NICK_FIELD_NUMBER = 1;
        public static final int BYTES_SENDER_TITLE_FIELD_NUMBER = 7;
        public static final int UINT32_FLAGS_FIELD_NUMBER = 4;
        public static final int UINT32_GROUP_MASK_FIELD_NUMBER = 5;
        public static final int UINT32_LEVEL_FIELD_NUMBER = 3;
        public static final int UINT32_MSG_TAIL_ID_FIELD_NUMBER = 6;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 24, 32, 40, 48, 58}, new String[]{"bytes_nick", "bytes_group_card", "uint32_level", "uint32_flags", "uint32_group_mask", "uint32_msg_tail_id", "bytes_sender_title"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY}, ExtraInfo.class);
        public final PBBytesField bytes_group_card = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_nick = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_sender_title = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_flags = PBField.initUInt32(0);
        public final PBUInt32Field uint32_group_mask = PBField.initUInt32(0);
        public final PBUInt32Field uint32_level = PBField.initUInt32(0);
        public final PBUInt32Field uint32_msg_tail_id = PBField.initUInt32(0);
    }

    public static final class FSJMessageElem extends MessageMicro<FSJMessageElem> {
        public static final int ETypeHotChatFlashPic = 1;
        public static final int ETypeNormal = 0;
        public static final int UINT32_MSG_TYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"uint32_msg_type"}, new Object[]{Integer.valueOf(0)}, FSJMessageElem.class);
        public final PBUInt32Field uint32_msg_type = PBField.initUInt32(0);
    }

    public static final class Face extends MessageMicro<Face> {
        public static final int BUF_FIELD_NUMBER = 11;
        public static final int INDEX_FIELD_NUMBER = 1;
        public static final int OLD_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 90}, new String[]{"index", "old", "buf"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, Face.class);
        public final PBBytesField buf = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field index = PBField.initUInt32(0);
        public final PBBytesField old = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class FunFace extends MessageMicro<FunFace> {
        public static final int MSG_BOMB_FIELD_NUMBER = 2;
        public static final int MSG_TURNTABLE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"msg_turntable", "msg_bomb"}, new Object[]{null, null}, FunFace.class);
        public Bomb msg_bomb = new Bomb();
        public Turntable msg_turntable = new Turntable();

        public static final class Bomb extends MessageMicro<Bomb> {
            public static final int BOOL_BURST_FIELD_NUMBER = 1;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"bool_burst"}, new Object[]{Boolean.valueOf(false)}, Bomb.class);
            public final PBBoolField bool_burst = PBField.initBool(false);
        }

        public static final class Turntable extends MessageMicro<Turntable> {
            public static final int RPT_UINT64_UIN_LIST_FIELD_NUMBER = 1;
            public static final int STR_HIT_UIN_NICK_FIELD_NUMBER = 3;
            public static final int UINT64_HIT_UIN_FIELD_NUMBER = 2;
            static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26}, new String[]{"rpt_uint64_uin_list", "uint64_hit_uin", "str_hit_uin_nick"}, new Object[]{Long.valueOf(0), Long.valueOf(0), ""}, Turntable.class);
            public final PBRepeatField<Long> rpt_uint64_uin_list = PBField.initRepeat(PBUInt64Field.__repeatHelper__);
            public final PBStringField str_hit_uin_nick = PBField.initString("");
            public final PBUInt64Field uint64_hit_uin = PBField.initUInt64(0);
        }
    }

    public static final class GeneralFlags extends MessageMicro<GeneralFlags> {
        public static final int UINT32_BUBBLE_DIY_TEXT_ID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"uint32_bubble_diy_text_id"}, new Object[]{Integer.valueOf(0)}, GeneralFlags.class);
        public final PBUInt32Field uint32_bubble_diy_text_id = PBField.initUInt32(0);
    }

    public static final class GroupFile extends MessageMicro<GroupFile> {
        public static final int BYTES_BATCH_ID_FIELD_NUMBER = 4;
        public static final int BYTES_BATCH_ITEM_ID_FIELD_NUMBER = 8;
        public static final int BYTES_FILENAME_FIELD_NUMBER = 1;
        public static final int BYTES_FILE_ID_FIELD_NUMBER = 3;
        public static final int BYTES_FILE_KEY_FIELD_NUMBER = 5;
        public static final int BYTES_MARK_FIELD_NUMBER = 6;
        public static final int UINT32_FEED_MSG_TIME_FIELD_NUMBER = 9;
        public static final int UINT64_FILE_SIZE_FIELD_NUMBER = 2;
        public static final int UINT64_SEQUENCE_FIELD_NUMBER = 7;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26, 34, 42, 50, 56, 66, 72}, new String[]{"bytes_filename", "uint64_file_size", "bytes_file_id", "bytes_batch_id", "bytes_file_key", "bytes_mark", "uint64_sequence", "bytes_batch_item_id", "uint32_feed_msg_time"}, new Object[]{ByteStringMicro.EMPTY, Long.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Long.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0)}, GroupFile.class);
        public final PBBytesField bytes_batch_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_batch_item_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_filename = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_mark = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_feed_msg_time = PBField.initUInt32(0);
        public final PBUInt64Field uint64_file_size = PBField.initUInt64(0);
        public final PBUInt64Field uint64_sequence = PBField.initUInt64(0);
    }

    public static final class LifeOnlineAccount extends MessageMicro<LifeOnlineAccount> {
        public static final int UINT32_ACK_FIELD_NUMBER = 5;
        public static final int UINT32_OP_FIELD_NUMBER = 2;
        public static final int UINT32_REPORT_FIELD_NUMBER = 4;
        public static final int UINT32_SHOW_TIME_FIELD_NUMBER = 3;
        public static final int UINT64_BITMAP_FIELD_NUMBER = 6;
        public static final int UINT64_UNIQUE_ID_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48}, new String[]{"uint64_unique_id", "uint32_op", "uint32_show_time", "uint32_report", "uint32_ack", "uint64_bitmap"}, new Object[]{Long.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0)}, LifeOnlineAccount.class);
        public final PBUInt32Field uint32_ack = PBField.initUInt32(0);
        public final PBUInt32Field uint32_op = PBField.initUInt32(0);
        public final PBUInt32Field uint32_report = PBField.initUInt32(0);
        public final PBUInt32Field uint32_show_time = PBField.initUInt32(0);
        public final PBUInt64Field uint64_bitmap = PBField.initUInt64(0);
        public final PBUInt64Field uint64_unique_id = PBField.initUInt64(0);
    }

    public static final class LocationInfo extends MessageMicro<LocationInfo> {
        public static final int BYTES_DESC_FIELD_NUMBER = 3;
        public static final int DOUBLE_LATITUDE_FIELD_NUMBER = 2;
        public static final int DOUBLE_LONGITUDE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{9, 17, 26}, new String[]{"double_longitude", "double_latitude", "bytes_desc"}, new Object[]{Double.valueOf(0.0d), Double.valueOf(0.0d), ByteStringMicro.EMPTY}, LocationInfo.class);
        public final PBBytesField bytes_desc = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBDoubleField double_latitude = PBField.initDouble(0.0d);
        public final PBDoubleField double_longitude = PBField.initDouble(0.0d);
    }

    public static final class LowVersionTips extends MessageMicro<LowVersionTips> {
        public static final int CF_BuddyChatType = 0;
        public static final int CF_DiscussChatType = 3;
        public static final int CF_GroupChatType = 1;
        public static final int CF_TempChatType = 2;
        public static final int CF_UnknownChatType = -1;
        public static final int EBusinessID_ConfScheduleMsg = 1;
        public static final int EBusinessID_DingDongMsg = 2;
        public static final int STR_TEXT_FIELD_NUMBER = 5;
        public static final int UINT32_BUSINESS_ID_FIELD_NUMBER = 1;
        public static final int UINT32_SESSION_TYPE_FIELD_NUMBER = 2;
        public static final int UINT64_SENDER_UIN_FIELD_NUMBER = 4;
        public static final int UINT64_SESSION_UIN_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 42}, new String[]{"uint32_business_id", "uint32_session_type", "uint64_session_uin", "uint64_sender_uin", "str_text"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), ""}, LowVersionTips.class);
        public final PBStringField str_text = PBField.initString("");
        public final PBUInt32Field uint32_business_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_session_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_sender_uin = PBField.initUInt64(0);
        public final PBUInt64Field uint64_session_uin = PBField.initUInt64(0);
    }

    public static final class MarketFace extends MessageMicro<MarketFace> {
        public static final int BYTES_FACE_ID_FIELD_NUMBER = 4;
        public static final int BYTES_FACE_NAME_FIELD_NUMBER = 1;
        public static final int BYTES_KEY_FIELD_NUMBER = 7;
        public static final int BYTES_MOBILEPARAM_FIELD_NUMBER = 12;
        public static final int BYTES_PARAM_FIELD_NUMBER = 8;
        public static final int UINT32_FACE_INFO_FIELD_NUMBER = 3;
        public static final int UINT32_IMAGE_HEIGHT_FIELD_NUMBER = 11;
        public static final int UINT32_IMAGE_WIDTH_FIELD_NUMBER = 10;
        public static final int UINT32_ITEM_TYPE_FIELD_NUMBER = 2;
        public static final int UINT32_MEDIA_TYPE_FIELD_NUMBER = 9;
        public static final int UINT32_SUB_TYPE_FIELD_NUMBER = 6;
        public static final int UINT32_TAB_ID_FIELD_NUMBER = 5;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 24, 34, 40, 48, 58, 66, 72, 80, 88, 98}, new String[]{"bytes_face_name", "uint32_item_type", "uint32_face_info", "bytes_face_id", "uint32_tab_id", "uint32_sub_type", "bytes_key", "bytes_param", "uint32_media_type", "uint32_image_width", "uint32_image_height", "bytes_mobileparam"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY}, MarketFace.class);
        public final PBBytesField bytes_face_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_face_name = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_mobileparam = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_param = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_face_info = PBField.initUInt32(0);
        public final PBUInt32Field uint32_image_height = PBField.initUInt32(0);
        public final PBUInt32Field uint32_image_width = PBField.initUInt32(0);
        public final PBUInt32Field uint32_item_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_media_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_sub_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_tab_id = PBField.initUInt32(0);
    }

    public static final class MarketTrans extends MessageMicro<MarketTrans> {
        public static final int BYTES_MSG_RESID_FIELD_NUMBER = 3;
        public static final int BYTES_XML_FIELD_NUMBER = 2;
        public static final int INT32_FLAG_FIELD_NUMBER = 1;
        public static final int UINT32_ABILITY_FIELD_NUMBER = 4;
        public static final int UINT32_MIN_ABILITY_FIELD_NUMBER = 5;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 32, 40}, new String[]{"int32_flag", "bytes_xml", "bytes_msg_resid", "uint32_ability", "uint32_min_ability"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0)}, MarketTrans.class);
        public final PBBytesField bytes_msg_resid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_xml = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBInt32Field int32_flag = PBField.initInt32(0);
        public final PBUInt32Field uint32_ability = PBField.initUInt32(0);
        public final PBUInt32Field uint32_min_ability = PBField.initUInt32(0);
    }

    public static final class MsgBody extends MessageMicro<MsgBody> {
        public static final int MSG_CONTENT_FIELD_NUMBER = 2;
        public static final int RICH_TEXT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18}, new String[]{"rich_text", "msg_content"}, new Object[]{null, ByteStringMicro.EMPTY}, MsgBody.class);
        public final PBBytesField msg_content = PBField.initBytes(ByteStringMicro.EMPTY);
        public RichText rich_text = new RichText();
    }

    public static final class MsgBody_subtype4 extends MessageMicro<MsgBody_subtype4> {
        public static final int MSG_NOT_ONLINE_FILE_FIELD_NUMBER = 1;
        public static final int UINT32_MSG_TIME_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16}, new String[]{"msg_not_online_file", "uint32_msg_time"}, new Object[]{null, Integer.valueOf(0)}, MsgBody_subtype4.class);
        public NotOnlineFile msg_not_online_file = new NotOnlineFile();
        public final PBUInt32Field uint32_msg_time = PBField.initUInt32(0);
    }

    public static final class NearByMessageType extends MessageMicro<NearByMessageType> {
        public static final int ETypeClearingImg = 2;
        public static final int ETypeClearingText = 1;
        public static final int ETypeNormal = 0;
        public static final int UINT32_TYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"uint32_type"}, new Object[]{Integer.valueOf(0)}, NearByMessageType.class);
        public final PBUInt32Field uint32_type = PBField.initUInt32(0);
    }

    public static final class NotOnlineFile extends MessageMicro<NotOnlineFile> {
        public static final int BYTES_FILE_MD5_FIELD_NUMBER = 4;
        public static final int BYTES_FILE_NAME_FIELD_NUMBER = 5;
        public static final int BYTES_FILE_UUID_FIELD_NUMBER = 3;
        public static final int BYTES_NOTE_FIELD_NUMBER = 7;
        public static final int BYTES_SIG_FIELD_NUMBER = 2;
        public static final int UINT32_ABS_FILE_TYPE_FIELD_NUMBER = 53;
        public static final int UINT32_CLIENT_TYPE_FIELD_NUMBER = 54;
        public static final int UINT32_DANGER_EVEL_FIELD_NUMBER = 50;
        public static final int UINT32_EXPIRE_TIME_FIELD_NUMBER = 55;
        public static final int UINT32_FILE_TYPE_FIELD_NUMBER = 1;
        public static final int UINT32_LIFE_TIME_FIELD_NUMBER = 51;
        public static final int UINT32_MICRO_CLOUD_FIELD_NUMBER = 10;
        public static final int UINT32_RESERVED_FIELD_NUMBER = 8;
        public static final int UINT32_SUBCMD_FIELD_NUMBER = 9;
        public static final int UINT32_UPLOAD_TIME_FIELD_NUMBER = 52;
        public static final int UINT64_FILE_SIZE_FIELD_NUMBER = 6;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34, 42, 48, 58, 64, 72, 80, 400, http.Request_Timeout, http.Requested_Range_Not_Satisfiable, 424, 432, 440}, new String[]{"uint32_file_type", "bytes_sig", "bytes_file_uuid", "bytes_file_md5", "bytes_file_name", "uint64_file_size", "bytes_note", "uint32_reserved", "uint32_subcmd", "uint32_micro_cloud", "uint32_danger_evel", "uint32_life_time", "uint32_upload_time", "uint32_abs_file_type", "uint32_client_type", "uint32_expire_time"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Long.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, NotOnlineFile.class);
        public final PBBytesField bytes_file_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_name = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_uuid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_note = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_sig = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_abs_file_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_client_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_danger_evel = PBField.initUInt32(0);
        public final PBUInt32Field uint32_expire_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_life_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_micro_cloud = PBField.initUInt32(0);
        public final PBUInt32Field uint32_reserved = PBField.initUInt32(0);
        public final PBUInt32Field uint32_subcmd = PBField.initUInt32(0);
        public final PBUInt32Field uint32_upload_time = PBField.initUInt32(0);
        public final PBUInt64Field uint64_file_size = PBField.initUInt64(0);
    }

    public static final class NotOnlineImage extends MessageMicro<NotOnlineImage> {
        public static final int BIZ_TYPE_FIELD_NUMBER = 16;
        public static final int DOWNLOAD_PATH_FIELD_NUMBER = 3;
        public static final int FILE_LEN_FIELD_NUMBER = 2;
        public static final int FILE_PATH_FIELD_NUMBER = 1;
        public static final int FLAG_FIELD_NUMBER = 11;
        public static final int IMG_TYPE_FIELD_NUMBER = 5;
        public static final int INDEX_FIELD_NUMBER = 18;
        public static final int OLD_PIC_MD5_FIELD_NUMBER = 20;
        public static final int OLD_VER_SEND_FILE_FIELD_NUMBER = 4;
        public static final int OP_FACE_BUF_FIELD_NUMBER = 19;
        public static final int ORIGINAL_FIELD_NUMBER = 13;
        public static final int PIC_HEIGHT_FIELD_NUMBER = 8;
        public static final int PIC_MD5_FIELD_NUMBER = 7;
        public static final int PIC_WIDTH_FIELD_NUMBER = 9;
        public static final int PREVIEWS_IMAGE_FIELD_NUMBER = 6;
        public static final int RESULT_FIELD_NUMBER = 17;
        public static final int RES_ID_FIELD_NUMBER = 10;
        public static final int STR_BIG_URL_FIELD_NUMBER = 14;
        public static final int STR_ORIG_URL_FIELD_NUMBER = 15;
        public static final int STR_THUMB_URL_FIELD_NUMBER = 12;
        public static final int UINT32_DOWNLOAD_LEN_FIELD_NUMBER = 25;
        public static final int UINT32_FILE_ID_FIELD_NUMBER = 23;
        public static final int UINT32_SHOW_LEN_FIELD_NUMBER = 24;
        public static final int UINT32_THUMB_HEIGHT_FIELD_NUMBER = 22;
        public static final int UINT32_THUMB_WIDTH_FIELD_NUMBER = 21;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26, 34, 40, 50, 58, 64, 72, 82, 90, 98, 104, 114, 122, 128, Opcodes.FLOAT_TO_LONG, Opcodes.ADD_INT, Opcodes.USHR_INT, 160, Opcodes.MUL_FLOAT, Opcodes.ADD_INT_2ADDR, Opcodes.SHL_INT_2ADDR, Opcodes.AND_LONG_2ADDR, 200}, new String[]{"file_path", "file_len", "download_path", "old_ver_send_file", "img_type", "previews_image", "pic_md5", "pic_height", "pic_width", "res_id", "flag", "str_thumb_url", "original", "str_big_url", "str_orig_url", "biz_type", "result", "index", "op_face_buf", "old_pic_md5", "uint32_thumb_width", "uint32_thumb_height", "uint32_file_id", "uint32_show_len", "uint32_download_len"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, "", Integer.valueOf(0), "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, Boolean.valueOf(false), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, NotOnlineImage.class);
        public final PBUInt32Field biz_type = PBField.initUInt32(0);
        public final PBBytesField download_path = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field file_len = PBField.initUInt32(0);
        public final PBBytesField file_path = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField flag = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field img_type = PBField.initUInt32(0);
        public final PBUInt32Field index = PBField.initUInt32(0);
        public final PBBoolField old_pic_md5 = PBField.initBool(false);
        public final PBBytesField old_ver_send_file = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField op_face_buf = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field original = PBField.initUInt32(0);
        public final PBUInt32Field pic_height = PBField.initUInt32(0);
        public final PBBytesField pic_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field pic_width = PBField.initUInt32(0);
        public final PBBytesField previews_image = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField res_id = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field result = PBField.initUInt32(0);
        public final PBStringField str_big_url = PBField.initString("");
        public final PBStringField str_orig_url = PBField.initString("");
        public final PBStringField str_thumb_url = PBField.initString("");
        public final PBUInt32Field uint32_download_len = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_show_len = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_height = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_width = PBField.initUInt32(0);
    }

    public static final class OnlineImage extends MessageMicro<OnlineImage> {
        public static final int FILE_PATH_FIELD_NUMBER = 2;
        public static final int GUID_FIELD_NUMBER = 1;
        public static final int OLD_VER_SEND_FILE_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26}, new String[]{"guid", "file_path", "old_ver_send_file"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, OnlineImage.class);
        public final PBBytesField file_path = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField guid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField old_ver_send_file = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class PcSupportDef extends MessageMicro<PcSupportDef> {
        public static final int RPT_PTLS_NOT_SUPPORT_FIELD_NUMBER = 6;
        public static final int RPT_PTLS_SUPPORT_FIELD_NUMBER = 5;
        public static final int UINT32_MAC_PTL_BEGIN_FIELD_NUMBER = 3;
        public static final int UINT32_MAC_PTL_END_FIELD_NUMBER = 4;
        public static final int UINT32_PC_PTL_BEGIN_FIELD_NUMBER = 1;
        public static final int UINT32_PC_PTL_END_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24, 32, 40, 48}, new String[]{"uint32_pc_ptl_begin", "uint32_pc_ptl_end", "uint32_mac_ptl_begin", "uint32_mac_ptl_end", "rpt_ptls_support", "rpt_ptls_not_support"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, PcSupportDef.class);
        public final PBRepeatField<Integer> rpt_ptls_not_support = PBField.initRepeat(PBUInt32Field.__repeatHelper__);
        public final PBRepeatField<Integer> rpt_ptls_support = PBField.initRepeat(PBUInt32Field.__repeatHelper__);
        public final PBUInt32Field uint32_mac_ptl_begin = PBField.initUInt32(0);
        public final PBUInt32Field uint32_mac_ptl_end = PBField.initUInt32(0);
        public final PBUInt32Field uint32_pc_ptl_begin = PBField.initUInt32(0);
        public final PBUInt32Field uint32_pc_ptl_end = PBField.initUInt32(0);
    }

    public static final class Ptt extends MessageMicro<Ptt> {
        public static final int BOOL_VALID_FIELD_NUMBER = 11;
        public static final int BYTES_DOWN_PARA_FIELD_NUMBER = 20;
        public static final int BYTES_FILE_KEY_FIELD_NUMBER = 14;
        public static final int BYTES_FILE_MD5_FIELD_NUMBER = 4;
        public static final int BYTES_FILE_NAME_FIELD_NUMBER = 5;
        public static final int BYTES_FILE_UUID_FIELD_NUMBER = 3;
        public static final int BYTES_GROUP_FILE_KEY_FIELD_NUMBER = 18;
        public static final int BYTES_PB_RESERVE_FIELD_NUMBER = 30;
        public static final int BYTES_PTT_URL_FIELD_NUMBER = 17;
        public static final int BYTES_RESERVE_FIELD_NUMBER = 7;
        public static final int BYTES_SHORTCUT_FIELD_NUMBER = 13;
        public static final int BYTES_SIGNATURE_FIELD_NUMBER = 12;
        public static final int UINT32_FILE_ID_FIELD_NUMBER = 8;
        public static final int UINT32_FILE_SIZE_FIELD_NUMBER = 6;
        public static final int UINT32_FILE_TYPE_FIELD_NUMBER = 1;
        public static final int UINT32_FORMAT_FIELD_NUMBER = 29;
        public static final int UINT32_MAGIC_PTT_INDEX_FIELD_NUMBER = 15;
        public static final int UINT32_SERVER_IP_FIELD_NUMBER = 9;
        public static final int UINT32_SERVER_PORT_FIELD_NUMBER = 10;
        public static final int UINT32_TIME_FIELD_NUMBER = 19;
        public static final int UINT32_VOICE_SWITCH_FIELD_NUMBER = 16;
        public static final int UINT64_SRC_UIN_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26, 34, 42, 48, 58, 64, 72, 80, 88, 98, 106, 114, 120, 128, Opcodes.DOUBLE_TO_INT, Opcodes.MUL_INT, Opcodes.SHL_INT, Opcodes.XOR_LONG, 232, 242}, new String[]{"uint32_file_type", "uint64_src_uin", "bytes_file_uuid", "bytes_file_md5", "bytes_file_name", "uint32_file_size", "bytes_reserve", "uint32_file_id", "uint32_server_ip", "uint32_server_port", "bool_valid", "bytes_signature", "bytes_shortcut", "bytes_file_key", "uint32_magic_ptt_index", "uint32_voice_switch", "bytes_ptt_url", "bytes_group_file_key", "uint32_time", "bytes_down_para", "uint32_format", "bytes_pb_reserve"}, new Object[]{Integer.valueOf(0), Long.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.valueOf(false), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY}, Ptt.class);
        public final PBBoolField bool_valid = PBField.initBool(false);
        public final PBBytesField bytes_down_para = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_name = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_uuid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_group_file_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_pb_reserve = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_ptt_url = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_reserve = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_shortcut = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_signature = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_file_id = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_size = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_format = PBField.initUInt32(0);
        public final PBUInt32Field uint32_magic_ptt_index = PBField.initUInt32(0);
        public final PBUInt32Field uint32_server_ip = PBField.initUInt32(0);
        public final PBUInt32Field uint32_server_port = PBField.initUInt32(0);
        public final PBUInt32Field uint32_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_voice_switch = PBField.initUInt32(0);
        public final PBUInt64Field uint64_src_uin = PBField.initUInt64(0);
    }

    public static final class PubAccInfo extends MessageMicro<PubAccInfo> {
        public static final int STRING_LONG_MSG_URL_FIELD_NUMBER = 3;
        public static final int STRING_MSG_TEMPLATE_ID_FIELD_NUMBER = 2;
        public static final int UINT32_IS_INTER_NUM_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26}, new String[]{"uint32_is_inter_num", "string_msg_template_id", "string_long_msg_url"}, new Object[]{Integer.valueOf(0), "", ""}, PubAccInfo.class);
        public final PBStringField string_long_msg_url = PBField.initString("");
        public final PBStringField string_msg_template_id = PBField.initString("");
        public final PBUInt32Field uint32_is_inter_num = PBField.initUInt32(0);
    }

    public static final class PubAccount extends MessageMicro<PubAccount> {
        public static final int BYTES_BUF_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10}, new String[]{"bytes_buf"}, new Object[]{ByteStringMicro.EMPTY}, PubAccount.class);
        public final PBBytesField bytes_buf = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class PubGroup extends MessageMicro<PubGroup> {
        public static final int BYTES_NICKNAME_FIELD_NUMBER = 1;
        public static final int UINT32_AGE_FIELD_NUMBER = 3;
        public static final int UINT32_DISTANCE_FIELD_NUMBER = 4;
        public static final int UINT32_GENDER_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 24, 32}, new String[]{"bytes_nickname", "uint32_gender", "uint32_age", "uint32_distance"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, PubGroup.class);
        public final PBBytesField bytes_nickname = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_age = PBField.initUInt32(0);
        public final PBUInt32Field uint32_distance = PBField.initUInt32(0);
        public final PBUInt32Field uint32_gender = PBField.initUInt32(0);
    }

    public static final class QQLiveOld extends MessageMicro<QQLiveOld> {
        public static final int STR_INTRODUCE_FIELD_NUMBER = 4;
        public static final int STR_PARAM_FIELD_NUMBER = 3;
        public static final int STR_SHOW_TEXT_FIELD_NUMBER = 2;
        public static final int UINT32_SUB_CMD_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34}, new String[]{"uint32_sub_cmd", "str_show_text", "str_param", "str_introduce"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, QQLiveOld.class);
        public final PBBytesField str_introduce = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField str_param = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField str_show_text = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_sub_cmd = PBField.initUInt32(0);
    }

    public static final class QQWalletAioBody extends MessageMicro<QQWalletAioBody> {
        public static final int BYTES_AUTHKEY_FIELD_NUMBER = 10;
        public static final int BYTES_BILLNO_FIELD_NUMBER = 9;
        public static final int RECEIVER_FIELD_NUMBER = 3;
        public static final int SENDER_FIELD_NUMBER = 2;
        public static final int SINT32_CHANNELID_FIELD_NUMBER = 4;
        public static final int SINT32_REDTYPE_FIELD_NUMBER = 8;
        public static final int SINT32_SESSIONTYPE_FIELD_NUMBER = 11;
        public static final int SINT32_TEMPLATEID_FIELD_NUMBER = 5;
        public static final int UINT32_MSG_PRIORITY_FIELD_NUMBER = 7;
        public static final int UINT32_RESEND_FIELD_NUMBER = 6;
        public static final int UINT64_SENDUIN_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 32, 40, 48, 56, 64, 74, 82, 88}, new String[]{"uint64_senduin", "sender", SocialConstants.PARAM_RECEIVER, "sint32_channelid", "sint32_templateid", "uint32_resend", "uint32_msg_priority", "sint32_redtype", "bytes_billno", "bytes_authkey", "sint32_sessiontype"}, new Object[]{Long.valueOf(0), null, null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0)}, QQWalletAioBody.class);
        public final PBBytesField bytes_authkey = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_billno = PBField.initBytes(ByteStringMicro.EMPTY);
        public QQWalletAioElem receiver = new QQWalletAioElem();
        public QQWalletAioElem sender = new QQWalletAioElem();
        public final PBSInt32Field sint32_channelid = PBField.initSInt32(0);
        public final PBSInt32Field sint32_redtype = PBField.initSInt32(0);
        public final PBSInt32Field sint32_sessiontype = PBField.initSInt32(0);
        public final PBSInt32Field sint32_templateid = PBField.initSInt32(0);
        public final PBUInt32Field uint32_msg_priority = PBField.initUInt32(0);
        public final PBUInt32Field uint32_resend = PBField.initUInt32(0);
        public final PBUInt64Field uint64_senduin = PBField.initUInt64(0);
    }

    public static final class QQWalletAioElem extends MessageMicro<QQWalletAioElem> {
        public static final int BYTES_ACTIONS_PRIORITY_FIELD_NUMBER = 11;
        public static final int BYTES_BASEMAP_FIELD_NUMBER = 15;
        public static final int BYTES_BLACKSTRIPE_FIELD_NUMBER = 7;
        public static final int BYTES_CONTENT_FIELD_NUMBER = 5;
        public static final int BYTES_JUMP_URL_FIELD_NUMBER = 12;
        public static final int BYTES_LINKURL_FIELD_NUMBER = 6;
        public static final int BYTES_NATIVE_ANDROID_FIELD_NUMBER = 14;
        public static final int BYTES_NATIVE_IOS_FIELD_NUMBER = 13;
        public static final int BYTES_NOTICE_FIELD_NUMBER = 8;
        public static final int BYTES_SUBTITLE_FIELD_NUMBER = 4;
        public static final int BYTES_TITLE_FIELD_NUMBER = 3;
        public static final int UINT32_BACKGROUND_FIELD_NUMBER = 1;
        public static final int UINT32_ICON_FIELD_NUMBER = 2;
        public static final int UINT32_SUBTITLE_COLOR_FIELD_NUMBER = 10;
        public static final int UINT32_TITLE_COLOR_FIELD_NUMBER = 9;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 26, 34, 42, 50, 58, 66, 72, 80, 90, 98, 106, 114, 122}, new String[]{"uint32_background", "uint32_icon", "bytes_title", "bytes_subtitle", "bytes_content", "bytes_linkurl", "bytes_blackstripe", "bytes_notice", "uint32_title_color", "uint32_subtitle_color", "bytes_actions_priority", "bytes_jump_url", "bytes_native_ios", "bytes_native_android", "bytes_basemap"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, QQWalletAioElem.class);
        public final PBBytesField bytes_actions_priority = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_basemap = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_blackstripe = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_content = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_jump_url = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_linkurl = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_native_android = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_native_ios = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_notice = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_subtitle = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_title = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_background = PBField.initUInt32(0);
        public final PBUInt32Field uint32_icon = PBField.initUInt32(0);
        public final PBUInt32Field uint32_subtitle_color = PBField.initUInt32(0);
        public final PBUInt32Field uint32_title_color = PBField.initUInt32(0);
    }

    public static final class QQWalletMsg extends MessageMicro<QQWalletMsg> {
        public static final int AIO_BODY_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10}, new String[]{"aio_body"}, new Object[]{null}, QQWalletMsg.class);
        public QQWalletAioBody aio_body = new QQWalletAioBody();
    }

    public static final class RedBagInfo extends MessageMicro<RedBagInfo> {
        public static final int REDBAG_TYPE_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"redbag_type"}, new Object[]{Integer.valueOf(0)}, RedBagInfo.class);
        public final PBUInt32Field redbag_type = PBField.initUInt32(0);
    }

    public static final class RichMsg extends MessageMicro<RichMsg> {
        public static final int BYTES_MSG_RESID_FIELD_NUMBER = 3;
        public static final int BYTES_TEMPLATE_1_FIELD_NUMBER = 1;
        public static final int UINT32_FLAGS_FIELD_NUMBER = 6;
        public static final int UINT32_RAND_FIELD_NUMBER = 4;
        public static final int UINT32_SEQ_FIELD_NUMBER = 5;
        public static final int UINT32_SERVICE_ID_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 26, 32, 40, 48}, new String[]{"bytes_template_1", "uint32_service_id", "bytes_msg_resid", "uint32_rand", "uint32_seq", "uint32_flags"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, RichMsg.class);
        public final PBBytesField bytes_msg_resid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_template_1 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_flags = PBField.initUInt32(0);
        public final PBUInt32Field uint32_rand = PBField.initUInt32(0);
        public final PBUInt32Field uint32_seq = PBField.initUInt32(0);
        public final PBUInt32Field uint32_service_id = PBField.initUInt32(0);
    }

    public static final class RichText extends MessageMicro<RichText> {
        public static final int ATTR_FIELD_NUMBER = 1;
        public static final int ELEMS_FIELD_NUMBER = 2;
        public static final int NOT_ONLINE_FILE_FIELD_NUMBER = 3;
        public static final int PTT_FIELD_NUMBER = 4;
        public static final int TMP_PTT_FIELD_NUMBER = 5;
        public static final int TRANS_211_TMP_MSG_FIELD_NUMBER = 6;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 42, 50}, new String[]{"attr", "elems", "not_online_file", "ptt", "tmp_ptt", "trans_211_tmp_msg"}, new Object[]{null, null, null, null, null, null}, RichText.class);
        public Attr attr = new Attr();
        public final PBRepeatMessageField<Elem> elems = PBField.initRepeatMessage(Elem.class);
        public NotOnlineFile not_online_file = new NotOnlineFile();
        public Ptt ptt = new Ptt();
        public TmpPtt tmp_ptt = new TmpPtt();
        public Trans211TmpMsg trans_211_tmp_msg = new Trans211TmpMsg();
    }

    public static final class SecretFileMsg extends MessageMicro<SecretFileMsg> {
        public static final int BYTES_ENCRYPT_KEY_FIELD_NUMBER = 9;
        public static final int BYTES_FILE_KEY_FIELD_NUMBER = 1;
        public static final int ELEM_FLAGS2_FIELD_NUMBER = 13;
        public static final int NOT_ONLINE_IMAGE_FIELD_NUMBER = 12;
        public static final int STR_FROMPHONENUM_FIELD_NUMBER = 15;
        public static final int TYPE_C2C = 0;
        public static final int TYPE_DISCU = 4;
        public static final int TYPE_GROUP = 2;
        public static final int TYPE_NEARBA = 3;
        public static final int TYPE_WPA = 5;
        public static final int UINT32_ENCRYPT_PREHEAD_LENGTH_FIELD_NUMBER = 7;
        public static final int UINT32_ENCRYPT_TYPE_FIELD_NUMBER = 8;
        public static final int UINT32_LEFT_TIME_FIELD_NUMBER = 11;
        public static final int UINT32_OPERTYPE_FIELD_NUMBER = 14;
        public static final int UINT32_READ_TIMES_FIELD_NUMBER = 10;
        public static final int UINT32_STATUS_FIELD_NUMBER = 4;
        public static final int UINT32_TTL_FIELD_NUMBER = 5;
        public static final int UINT32_TYPE_FIELD_NUMBER = 6;
        public static final int UINT64_FROM_UIN_FIELD_NUMBER = 2;
        public static final int UINT64_TO_UIN_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16, 24, 32, 40, 48, 56, 64, 74, 80, 88, 98, 106, 112, 122}, new String[]{"bytes_file_key", "uint64_from_uin", "uint64_to_uin", "uint32_status", "uint32_ttl", "uint32_type", "uint32_encrypt_prehead_length", "uint32_encrypt_type", "bytes_encrypt_key", "uint32_read_times", "uint32_left_time", "not_online_image", "elem_flags2", "uint32_opertype", "str_fromphonenum"}, new Object[]{ByteStringMicro.EMPTY, Long.valueOf(0), Long.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), null, null, Integer.valueOf(0), ""}, SecretFileMsg.class);
        public final PBBytesField bytes_encrypt_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_key = PBField.initBytes(ByteStringMicro.EMPTY);
        public ElemFlags2 elem_flags2 = new ElemFlags2();
        public NotOnlineImage not_online_image = new NotOnlineImage();
        public final PBStringField str_fromphonenum = PBField.initString("");
        public final PBUInt32Field uint32_encrypt_prehead_length = PBField.initUInt32(0);
        public final PBUInt32Field uint32_encrypt_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_left_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_opertype = PBField.initUInt32(0);
        public final PBUInt32Field uint32_read_times = PBField.initUInt32(0);
        public final PBUInt32Field uint32_status = PBField.initUInt32(0);
        public final PBUInt32Field uint32_ttl = PBField.initUInt32(0);
        public final PBUInt32Field uint32_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_from_uin = PBField.initUInt64(0);
        public final PBUInt64Field uint64_to_uin = PBField.initUInt64(0);
    }

    public static final class ShakeWindow extends MessageMicro<ShakeWindow> {
        public static final int UINT32_RESERVE_FIELD_NUMBER = 2;
        public static final int UINT32_TYPE_FIELD_NUMBER = 1;
        public static final int UINT64_UIN_FIELD_NUMBER = 3;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 16, 24}, new String[]{"uint32_type", "uint32_reserve", "uint64_uin"}, new Object[]{Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0)}, ShakeWindow.class);
        public final PBUInt32Field uint32_reserve = PBField.initUInt32(0);
        public final PBUInt32Field uint32_type = PBField.initUInt32(0);
        public final PBUInt64Field uint64_uin = PBField.initUInt64(0);
    }

    public static final class SmallEmoji extends MessageMicro<SmallEmoji> {
        public static final int PACKIDSUM_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8}, new String[]{"packIdSum"}, new Object[]{Integer.valueOf(0)}, SmallEmoji.class);
        public final PBUInt32Field packIdSum = PBField.initUInt32(0);
    }

    public static final class Text extends MessageMicro<Text> {
        public static final int ATTR_6_BUF_FIELD_NUMBER = 3;
        public static final int ATTR_7_BUF_FIELD_NUMBER = 4;
        public static final int BUF_FIELD_NUMBER = 11;
        public static final int LINK_FIELD_NUMBER = 2;
        public static final int STR_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 34, 90}, new String[]{"str", "link", "attr_6_buf", "attr_7_buf", "buf"}, new Object[]{ByteStringMicro.EMPTY, "", ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY}, Text.class);
        public final PBBytesField attr_6_buf = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField attr_7_buf = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField buf = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBStringField link = PBField.initString("");
        public final PBBytesField str = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class TipsInfo extends MessageMicro<TipsInfo> {
        public static final int TEXT_FIELD_NUMBER = 1;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10}, new String[]{"text"}, new Object[]{""}, TipsInfo.class);
        public final PBStringField text = PBField.initString("");
    }

    public static final class TmpPtt extends MessageMicro<TmpPtt> {
        public static final int BYTES_FILE_MD5_FIELD_NUMBER = 3;
        public static final int BYTES_FILE_NAME_FIELD_NUMBER = 4;
        public static final int BYTES_FILE_UUID_FIELD_NUMBER = 2;
        public static final int BYTES_PB_RESERVE_FIELD_NUMBER = 30;
        public static final int UINT32_BUSI_TYPE_FIELD_NUMBER = 9;
        public static final int UINT32_FILE_SIZE_FIELD_NUMBER = 5;
        public static final int UINT32_FILE_TYPE_FIELD_NUMBER = 1;
        public static final int UINT32_PTTTRANS_FLAG_FIELD_NUMBER = 8;
        public static final int UINT32_USER_TYPE_FIELD_NUMBER = 7;
        public static final int UINT64_PTT_TIMES_FIELD_NUMBER = 6;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18, 26, 34, 40, 48, 56, 64, 72, 242}, new String[]{"uint32_file_type", "bytes_file_uuid", "bytes_file_md5", "bytes_file_name", "uint32_file_size", "uint64_ptt_times", "uint32_user_type", "uint32_ptttrans_flag", "uint32_busi_type", "bytes_pb_reserve"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY}, TmpPtt.class);
        public final PBBytesField bytes_file_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_name = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_uuid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_pb_reserve = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_busi_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_size = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_ptttrans_flag = PBField.initUInt32(0);
        public final PBUInt32Field uint32_user_type = PBField.initUInt32(0);
        public final PBUInt32Field uint64_ptt_times = PBField.initUInt32(0);
    }

    public static final class Trans211TmpMsg extends MessageMicro<Trans211TmpMsg> {
        public static final int BYTES_MSG_BODY_FIELD_NUMBER = 1;
        public static final int UINT32_C2C_CMD_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 16}, new String[]{"bytes_msg_body", "uint32_c2c_cmd"}, new Object[]{ByteStringMicro.EMPTY, Integer.valueOf(0)}, Trans211TmpMsg.class);
        public final PBBytesField bytes_msg_body = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_c2c_cmd = PBField.initUInt32(0);
    }

    public static final class TransElem extends MessageMicro<TransElem> {
        public static final int ELEM_TYPE_FIELD_NUMBER = 1;
        public static final int ELEM_VALUE_FIELD_NUMBER = 2;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{8, 18}, new String[]{"elem_type", "elem_value"}, new Object[]{Integer.valueOf(0), ByteStringMicro.EMPTY}, TransElem.class);
        public final PBUInt32Field elem_type = PBField.initUInt32(0);
        public final PBBytesField elem_value = PBField.initBytes(ByteStringMicro.EMPTY);
    }

    public static final class VideoFile extends MessageMicro<VideoFile> {
        public static final int BYTES_FILE_MD5_FIELD_NUMBER = 2;
        public static final int BYTES_FILE_NAME_FIELD_NUMBER = 3;
        public static final int BYTES_FILE_UUID_FIELD_NUMBER = 1;
        public static final int BYTES_SOURCE_FIELD_NUMBER = 10;
        public static final int BYTES_THUMB_FILE_MD5_FIELD_NUMBER = 9;
        public static final int UINT32_BUSI_TYPE_FIELD_NUMBER = 12;
        public static final int UINT32_FILE_FORMAT_FIELD_NUMBER = 4;
        public static final int UINT32_FILE_SIZE_FIELD_NUMBER = 6;
        public static final int UINT32_FILE_TIME_FIELD_NUMBER = 5;
        public static final int UINT32_FROM_CHAT_TYPE_FIELD_NUMBER = 13;
        public static final int UINT32_THUMB_FILE_SIZE_FIELD_NUMBER = 11;
        public static final int UINT32_THUMB_HEIGHT_FIELD_NUMBER = 8;
        public static final int UINT32_THUMB_WIDTH_FIELD_NUMBER = 7;
        public static final int UINT32_TO_CHAT_TYPE_FIELD_NUMBER = 14;
        static final FieldMap __fieldMap__ = MessageMicro.initFieldMap(new int[]{10, 18, 26, 32, 40, 48, 56, 64, 74, 82, 88, 96, 104, 112}, new String[]{"bytes_file_uuid", "bytes_file_md5", "bytes_file_name", "uint32_file_format", "uint32_file_time", "uint32_file_size", "uint32_thumb_width", "uint32_thumb_height", "bytes_thumb_file_md5", "bytes_source", "uint32_thumb_file_size", "uint32_busi_type", "uint32_from_chat_type", "uint32_to_chat_type"}, new Object[]{ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ByteStringMicro.EMPTY, ByteStringMicro.EMPTY, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)}, VideoFile.class);
        public final PBBytesField bytes_file_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_name = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_file_uuid = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_source = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBBytesField bytes_thumb_file_md5 = PBField.initBytes(ByteStringMicro.EMPTY);
        public final PBUInt32Field uint32_busi_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_format = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_size = PBField.initUInt32(0);
        public final PBUInt32Field uint32_file_time = PBField.initUInt32(0);
        public final PBUInt32Field uint32_from_chat_type = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_file_size = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_height = PBField.initUInt32(0);
        public final PBUInt32Field uint32_thumb_width = PBField.initUInt32(0);
        public final PBUInt32Field uint32_to_chat_type = PBField.initUInt32(0);
    }

    private msg() {
    }
}
