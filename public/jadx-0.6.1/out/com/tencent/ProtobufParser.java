package com.tencent;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.head.AndroidOfflineInfo;
import com.tencent.openqq.protocol.imsdk.head.C2CInfo;
import com.tencent.openqq.protocol.imsdk.head.ContentHead;
import com.tencent.openqq.protocol.imsdk.head.GroupInfo;
import com.tencent.openqq.protocol.imsdk.head.MsgExtraInfo;
import com.tencent.openqq.protocol.imsdk.head.OfflinePushInfo;
import com.tencent.openqq.protocol.imsdk.head.RoutineHead;
import com.tencent.openqq.protocol.imsdk.head.UinExtaInfo;
import com.tencent.openqq.protocol.imsdk.msg.Elem;
import com.tencent.openqq.protocol.imsdk.msg.MsgBody;
import com.tencent.openqq.protocol.imsdk.msg_push.ReqBody;

public class ProtobufParser {
    private static final String tag = "ProtobufParser";

    public static TIMOfflinePushNotification offlinePush2PushNotification(String str, Context context, byte[] bArr) {
        TIMOfflinePushNotification tIMOfflinePushNotification = new TIMOfflinePushNotification();
        tIMOfflinePushNotification.setIdentifier(str);
        ReqBody reqBody = new ReqBody();
        try {
            reqBody.mergeFrom(bArr);
            ContentHead contentHead = reqBody.msg_msg.msg_msg_head.msg_content_head;
            RoutineHead routineHead = reqBody.msg_msg.msg_msg_head.msg_routine_head;
            int i = contentHead.uint32_type.get();
            int i2 = contentHead.uint32_subtype.get();
            QLog.d(tag, 1, "msgtype: " + Integer.toHexString(i) + "|subtype: " + Integer.toHexString(i2));
            if (i2 != 6) {
                return tIMOfflinePushNotification;
            }
            String str2;
            String toStringUtf8;
            CharSequence charSequence;
            CharSequence charSequence2;
            CharSequence charSequence3;
            QLog.d(tag, 1, "pushbuf: " + IMFunc.byte2hex(bArr));
            String str3 = "";
            String str4 = "";
            String str5 = "";
            String str6 = "";
            String str7 = "";
            String str8 = "";
            String str9 = "";
            String str10 = "";
            MsgExtraInfo msgExtraInfo = reqBody.msg_msg.msg_msg_head.msg_extra_info;
            String str11;
            Object obj;
            if (!msgExtraInfo.has()) {
                str2 = str3;
                str3 = str5;
                str11 = str6;
                Object obj2 = str9;
                obj = str11;
            } else if (msgExtraInfo.uint32_msg_lifetime.has() && msgExtraInfo.uint32_msg_lifetime.get() == 0) {
                return tIMOfflinePushNotification;
            } else {
                if (msgExtraInfo.msg_offline_push_info.has()) {
                    OfflinePushInfo offlinePushInfo = (OfflinePushInfo) msgExtraInfo.msg_offline_push_info.get();
                    if (offlinePushInfo.uint32_push_flag.get() == 1) {
                        return tIMOfflinePushNotification;
                    }
                    str2 = offlinePushInfo.bytes_desc.has() ? offlinePushInfo.bytes_desc.get().toStringUtf8() : str9;
                    if (offlinePushInfo.bytes_ext.has()) {
                        tIMOfflinePushNotification.setExt(offlinePushInfo.bytes_ext.get().toByteArray());
                    }
                    if (offlinePushInfo.msg_android_info.has()) {
                        AndroidOfflineInfo androidOfflineInfo = (AndroidOfflineInfo) offlinePushInfo.msg_android_info.get();
                        if (androidOfflineInfo.bytes_sound.has()) {
                            obj = androidOfflineInfo.bytes_sound.get().toStringUtf8();
                            if (!TextUtils.isEmpty(obj)) {
                                tIMOfflinePushNotification.setSound(Uri.parse(obj));
                            }
                        }
                    }
                    toStringUtf8 = offlinePushInfo.bytes_title.has() ? offlinePushInfo.bytes_title.get().toStringUtf8() : str3;
                } else {
                    str2 = str9;
                    toStringUtf8 = str3;
                }
                str9 = msgExtraInfo.bytes_identifier.has() ? msgExtraInfo.bytes_identifier.get().toStringUtf8() : str5;
                str3 = msgExtraInfo.bytes_from_uin_nick.has() ? msgExtraInfo.bytes_from_uin_nick.get().toStringUtf8() : str6;
                if (msgExtraInfo.msg_uin_extra_info.has()) {
                    UinExtaInfo uinExtaInfo = msgExtraInfo.msg_uin_extra_info;
                    if (uinExtaInfo.uint32_msg_flag.has()) {
                        tIMOfflinePushNotification.setGroupReceiveMsgOpt((long) uinExtaInfo.uint32_msg_flag.get());
                    }
                }
                charSequence = str2;
                str2 = toStringUtf8;
                str11 = str9;
                charSequence2 = str3;
                str3 = str11;
            }
            if (i == 561) {
                tIMOfflinePushNotification.setConversationType(TIMConversationType.C2C);
                tIMOfflinePushNotification.setConversationId(str3);
                C2CInfo c2CInfo = routineHead.msg_c2c_info;
                if (c2CInfo.has()) {
                    if (c2CInfo.bytes_from_uin_nick.has()) {
                        toStringUtf8 = c2CInfo.bytes_from_uin_nick.get().toStringUtf8();
                    } else {
                        charSequence3 = charSequence2;
                    }
                    str2 = TextUtils.isEmpty(str2) ? toStringUtf8 : str2;
                    charSequence2 = toStringUtf8;
                    toStringUtf8 = String.valueOf(c2CInfo.uint64_from_uin.get());
                } else {
                    toStringUtf8 = str10;
                }
                str10 = toStringUtf8;
            } else if (i == 564) {
                GroupInfo groupInfo = routineHead.msg_group_info;
                if (groupInfo.has()) {
                    UinExtaInfo uinExtaInfo2 = (UinExtaInfo) groupInfo.msg_from_uin_extra_info.get();
                    if (uinExtaInfo2.has()) {
                        if (uinExtaInfo2.bytes_name_card.has()) {
                            charSequence2 = uinExtaInfo2.bytes_name_card.get().toStringUtf8();
                        }
                        if (uinExtaInfo2.bytes_user_id.has()) {
                            toStringUtf8 = uinExtaInfo2.bytes_user_id.get().toStringUtf8();
                            if (TextUtils.isEmpty(charSequence2) && groupInfo.bytes_from_uin_nick.has()) {
                                charSequence2 = groupInfo.bytes_from_uin_nick.get().toStringUtf8();
                            }
                            str3 = groupInfo.bytes_group_name.has() ? groupInfo.bytes_group_name.get().toStringUtf8() : str7;
                            if (groupInfo.bytes_group_id.has()) {
                                str5 = toStringUtf8;
                                toStringUtf8 = str8;
                            } else {
                                str10 = groupInfo.bytes_group_id.get().toStringUtf8();
                                str5 = toStringUtf8;
                                toStringUtf8 = str10;
                            }
                        }
                    }
                    toStringUtf8 = str3;
                    charSequence2 = groupInfo.bytes_from_uin_nick.get().toStringUtf8();
                    if (groupInfo.bytes_group_name.has()) {
                    }
                    if (groupInfo.bytes_group_id.has()) {
                        str5 = toStringUtf8;
                        toStringUtf8 = str8;
                    } else {
                        str10 = groupInfo.bytes_group_id.get().toStringUtf8();
                        str5 = toStringUtf8;
                        toStringUtf8 = str10;
                    }
                } else {
                    toStringUtf8 = str8;
                    str5 = str3;
                    str3 = str7;
                }
                if (!TextUtils.isEmpty(str2)) {
                    str3 = str2;
                }
                tIMOfflinePushNotification.setConversationType(TIMConversationType.Group);
                tIMOfflinePushNotification.setConversationId(toStringUtf8);
                str2 = str3;
                str3 = str5;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = tIMOfflinePushNotification.getConversationId();
            }
            MsgBody msgBody = reqBody.msg_msg.msg_msg_body;
            if (TextUtils.isEmpty(charSequence)) {
                if (i == 564) {
                    if (TextUtils.isEmpty(charSequence2)) {
                        toStringUtf8 = str3;
                    } else {
                        charSequence3 = charSequence2;
                    }
                    toStringUtf8 = str4 + toStringUtf8 + ": ";
                } else {
                    toStringUtf8 = str4;
                }
                if (msgBody.rich_text.ptt.has()) {
                    toStringUtf8 = toStringUtf8 + "[语音]";
                }
                if (msgBody.rich_text.not_online_file.has()) {
                    toStringUtf8 = toStringUtf8 + "[文件]";
                }
                str5 = toStringUtf8;
                for (Elem elem : msgBody.rich_text.elems.get()) {
                    if (elem.text.has()) {
                        str5 = str5 + elem.text.str.get().toStringUtf8();
                    } else if (elem.not_online_image.has()) {
                        str5 = str5 + "[图片]";
                    } else if (elem.face.has()) {
                        str5 = str5 + "[表情]";
                    } else {
                        if (elem.custom_elem.has()) {
                            if (!TextUtils.isEmpty(elem.custom_elem.bytes_desc.get().toStringUtf8())) {
                                str5 = str5 + "[" + elem.custom_elem.bytes_desc.get().toStringUtf8() + "]";
                            }
                            if (tIMOfflinePushNotification.getExt() == null || tIMOfflinePushNotification.getExt().length == 0) {
                                tIMOfflinePushNotification.setExt(elem.custom_elem.bytes_ext.get().toByteArray());
                            }
                            toStringUtf8 = str5;
                        } else if (elem.location_info.has()) {
                            str5 = str5 + "[位置信息]" + elem.location_info.bytes_desc.get().toStringUtf8();
                        } else {
                            if (elem.video_file.has()) {
                                toStringUtf8 = str5 + "[视频]";
                            }
                            toStringUtf8 = str5;
                        }
                        str5 = toStringUtf8;
                    }
                }
            } else {
                str5 = charSequence;
            }
            tIMOfflinePushNotification.setTitle(str2);
            tIMOfflinePushNotification.setContent(str5);
            tIMOfflinePushNotification.setSenderIdentifier(str3);
            tIMOfflinePushNotification.setSenderNickName(charSequence2);
            if (tIMOfflinePushNotification.getSound() == null) {
                TIMOfflinePushSettings tIMOfflinePushSettings = new TIMOfflinePushSettings();
                if (IMMsfCoreProxy.get().getOfflinePushSettingsFromLocal(context, str, tIMOfflinePushSettings)) {
                    if (tIMOfflinePushNotification.getConversationType() == TIMConversationType.C2C && tIMOfflinePushSettings.getC2cMsgRemindSound() != null) {
                        tIMOfflinePushNotification.setSound(tIMOfflinePushSettings.getC2cMsgRemindSound());
                    } else if (tIMOfflinePushNotification.getConversationType() == TIMConversationType.Group && tIMOfflinePushSettings.getGroupMsgRemindSound() != null) {
                        tIMOfflinePushNotification.setSound(tIMOfflinePushSettings.getGroupMsgRemindSound());
                    }
                }
            }
            tIMOfflinePushNotification.setTag(str10);
            tIMOfflinePushNotification.setIsValid(true);
            return tIMOfflinePushNotification;
        } catch (Throwable th) {
            QLog.e(tag, 1, IMFunc.getExceptionInfo(th));
            return tIMOfflinePushNotification;
        }
    }
}
