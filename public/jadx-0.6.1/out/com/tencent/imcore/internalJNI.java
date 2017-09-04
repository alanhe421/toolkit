package com.tencent.imcore;

import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo;
import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo.ErrInfo;

public class internalJNI {
    static {
        swig_module_init();
    }

    public static final native long APNsInfo_badgeMode_get(long j, APNsInfo aPNsInfo);

    public static final native void APNsInfo_badgeMode_set(long j, APNsInfo aPNsInfo, long j2);

    public static final native byte[] APNsInfo_sound_get(long j, APNsInfo aPNsInfo);

    public static final native void APNsInfo_sound_set(long j, APNsInfo aPNsInfo, byte[] bArr);

    public static final native long AddFriendReqVec_capacity(long j, AddFriendReqVec addFriendReqVec);

    public static final native void AddFriendReqVec_clear(long j, AddFriendReqVec addFriendReqVec);

    public static final native boolean AddFriendReqVec_empty(long j, AddFriendReqVec addFriendReqVec);

    public static final native long AddFriendReqVec_get(long j, AddFriendReqVec addFriendReqVec, int i);

    public static final native void AddFriendReqVec_pushBack(long j, AddFriendReqVec addFriendReqVec, long j2, AddFriendReq addFriendReq);

    public static final native void AddFriendReqVec_reserve(long j, AddFriendReqVec addFriendReqVec, long j2);

    public static final native void AddFriendReqVec_set(long j, AddFriendReqVec addFriendReqVec, int i, long j2, AddFriendReq addFriendReq);

    public static final native long AddFriendReqVec_size(long j, AddFriendReqVec addFriendReqVec);

    public static final native String AddFriendReq_identifier_get(long j, AddFriendReq addFriendReq);

    public static final native void AddFriendReq_identifier_set(long j, AddFriendReq addFriendReq, String str);

    public static final native byte[] AddFriendReq_nickname_get(long j, AddFriendReq addFriendReq);

    public static final native void AddFriendReq_nickname_set(long j, AddFriendReq addFriendReq, byte[] bArr);

    public static final native byte[] AddFriendReq_source_get(long j, AddFriendReq addFriendReq);

    public static final native void AddFriendReq_source_set(long j, AddFriendReq addFriendReq, byte[] bArr);

    public static final native byte[] AddFriendReq_wording_get(long j, AddFriendReq addFriendReq);

    public static final native void AddFriendReq_wording_set(long j, AddFriendReq addFriendReq, byte[] bArr);

    public static final native long AndroidOfflinePushInfo_notifyMode_get(long j, AndroidOfflinePushInfo androidOfflinePushInfo);

    public static final native void AndroidOfflinePushInfo_notifyMode_set(long j, AndroidOfflinePushInfo androidOfflinePushInfo, long j2);

    public static final native byte[] AndroidOfflinePushInfo_sound_get(long j, AndroidOfflinePushInfo androidOfflinePushInfo);

    public static final native void AndroidOfflinePushInfo_sound_set(long j, AndroidOfflinePushInfo androidOfflinePushInfo, byte[] bArr);

    public static final native byte[] AndroidOfflinePushInfo_title_get(long j, AndroidOfflinePushInfo androidOfflinePushInfo);

    public static final native void AndroidOfflinePushInfo_title_set(long j, AndroidOfflinePushInfo androidOfflinePushInfo, byte[] bArr);

    public static final native long ApplyDownloadFileReq_busiId_get(long j, ApplyDownloadFileReq applyDownloadFileReq);

    public static final native void ApplyDownloadFileReq_busiId_set(long j, ApplyDownloadFileReq applyDownloadFileReq, long j2);

    public static final native long ApplyDownloadFileReq_downloadFlag_get(long j, ApplyDownloadFileReq applyDownloadFileReq);

    public static final native void ApplyDownloadFileReq_downloadFlag_set(long j, ApplyDownloadFileReq applyDownloadFileReq, long j2);

    public static final native long ApplyDownloadFileReq_type_get(long j, ApplyDownloadFileReq applyDownloadFileReq);

    public static final native void ApplyDownloadFileReq_type_set(long j, ApplyDownloadFileReq applyDownloadFileReq, long j2);

    public static final native String ApplyDownloadFileReq_uuid_get(long j, ApplyDownloadFileReq applyDownloadFileReq);

    public static final native void ApplyDownloadFileReq_uuid_set(long j, ApplyDownloadFileReq applyDownloadFileReq, String str);

    public static final native void BytesMap_clear(long j, BytesMap bytesMap);

    public static final native void BytesMap_del(long j, BytesMap bytesMap, byte[] bArr);

    public static final native boolean BytesMap_empty(long j, BytesMap bytesMap);

    public static final native byte[] BytesMap_get(long j, BytesMap bytesMap, byte[] bArr);

    public static final native boolean BytesMap_hasKey(long j, BytesMap bytesMap, byte[] bArr);

    public static final native void BytesMap_set(long j, BytesMap bytesMap, byte[] bArr, byte[] bArr2);

    public static final native long BytesMap_size(long j, BytesMap bytesMap);

    public static final native void BytesMemberInfoMap_clear(long j, BytesMemberInfoMap bytesMemberInfoMap);

    public static final native void BytesMemberInfoMap_del(long j, BytesMemberInfoMap bytesMemberInfoMap, byte[] bArr);

    public static final native boolean BytesMemberInfoMap_empty(long j, BytesMemberInfoMap bytesMemberInfoMap);

    public static final native long BytesMemberInfoMap_get(long j, BytesMemberInfoMap bytesMemberInfoMap, byte[] bArr);

    public static final native boolean BytesMemberInfoMap_hasKey(long j, BytesMemberInfoMap bytesMemberInfoMap, byte[] bArr);

    public static final native void BytesMemberInfoMap_set(long j, BytesMemberInfoMap bytesMemberInfoMap, byte[] bArr, long j2, NewGroupMemberInfo newGroupMemberInfo);

    public static final native long BytesMemberInfoMap_size(long j, BytesMemberInfoMap bytesMemberInfoMap);

    public static final native void BytesMemberInfoParser_fetchMapKeys(long j, BytesMemberInfoParser bytesMemberInfoParser, long j2);

    public static final native long BytesMemberInfoParser_getKey(long j, BytesMemberInfoParser bytesMemberInfoParser, int i);

    public static final native long BytesMemberInfoParser_getValue(long j, BytesMemberInfoParser bytesMemberInfoParser, long j2, int i);

    public static final native long BytesMemberInfoParser_keys_get(long j, BytesMemberInfoParser bytesMemberInfoParser);

    public static final native void BytesMemberInfoParser_keys_set(long j, BytesMemberInfoParser bytesMemberInfoParser, long j2, StrVec strVec);

    public static final native void BytesProfileMapParser_fetchMapKeys(long j, BytesProfileMapParser bytesProfileMapParser, long j2);

    public static final native long BytesProfileMapParser_getKey(long j, BytesProfileMapParser bytesProfileMapParser, int i);

    public static final native long BytesProfileMapParser_getValue(long j, BytesProfileMapParser bytesProfileMapParser, long j2, int i);

    public static final native long BytesProfileMapParser_keys_get(long j, BytesProfileMapParser bytesProfileMapParser);

    public static final native void BytesProfileMapParser_keys_set(long j, BytesProfileMapParser bytesProfileMapParser, long j2, StrVec strVec);

    public static final native void BytesProfileMap_clear(long j, BytesProfileMap bytesProfileMap);

    public static final native void BytesProfileMap_del(long j, BytesProfileMap bytesProfileMap, byte[] bArr);

    public static final native boolean BytesProfileMap_empty(long j, BytesProfileMap bytesProfileMap);

    public static final native long BytesProfileMap_get(long j, BytesProfileMap bytesProfileMap, byte[] bArr);

    public static final native boolean BytesProfileMap_hasKey(long j, BytesProfileMap bytesProfileMap, byte[] bArr);

    public static final native void BytesProfileMap_set(long j, BytesProfileMap bytesProfileMap, byte[] bArr, long j2, FriendProfile friendProfile);

    public static final native long BytesProfileMap_size(long j, BytesProfileMap bytesProfileMap);

    public static final native byte[] BytesVecFetcher_getBytesByIndex(long j, BytesVecFetcher bytesVecFetcher, int i);

    public static final native long BytesVecFetcher_values_get(long j, BytesVecFetcher bytesVecFetcher);

    public static final native void BytesVecFetcher_values_set(long j, BytesVecFetcher bytesVecFetcher, long j2, BytesVec bytesVec);

    public static final native long BytesVec_capacity(long j, BytesVec bytesVec);

    public static final native void BytesVec_clear(long j, BytesVec bytesVec);

    public static final native boolean BytesVec_empty(long j, BytesVec bytesVec);

    public static final native byte[] BytesVec_get(long j, BytesVec bytesVec, int i);

    public static final native void BytesVec_pushBack(long j, BytesVec bytesVec, byte[] bArr);

    public static final native void BytesVec_reserve(long j, BytesVec bytesVec, long j2);

    public static final native void BytesVec_set(long j, BytesVec bytesVec, int i, byte[] bArr);

    public static final native long BytesVec_size(long j, BytesVec bytesVec);

    public static final native long Context_bid_get(long j, Context context);

    public static final native void Context_bid_set(long j, Context context, long j2);

    public static final native boolean Context_isLogPrintEnabled_get(long j, Context context);

    public static final native void Context_isLogPrintEnabled_set(long j, Context context, boolean z);

    public static final native String Context_logPath_get(long j, Context context);

    public static final native void Context_logPath_set(long j, Context context, String str);

    public static final native String Context_picCachePath_get(long j, Context context);

    public static final native void Context_picCachePath_set(long j, Context context, String str);

    public static final native long Context_platform_get(long j, Context context);

    public static final native void Context_platform_set(long j, Context context, long j2);

    public static final native long Context_svr_time_diff_get(long j, Context context);

    public static final native void Context_svr_time_diff_set(long j, Context context, long j2);

    public static final native byte[] CustomElem_data_get(long j, CustomElem customElem);

    public static final native void CustomElem_data_set(long j, CustomElem customElem, byte[] bArr);

    public static final native byte[] CustomElem_desc_get(long j, CustomElem customElem);

    public static final native void CustomElem_desc_set(long j, CustomElem customElem, byte[] bArr);

    public static final native byte[] CustomElem_ext_get(long j, CustomElem customElem);

    public static final native void CustomElem_ext_set(long j, CustomElem customElem, byte[] bArr);

    public static final native byte[] CustomElem_sound_get(long j, CustomElem customElem);

    public static final native void CustomElem_sound_set(long j, CustomElem customElem, byte[] bArr);

    public static final native long Draft_elems_get(long j, Draft draft);

    public static final native void Draft_elems_set(long j, Draft draft, long j2, ElemVec elemVec);

    public static final native long Draft_uint64_edit_time_get(long j, Draft draft);

    public static final native void Draft_uint64_edit_time_set(long j, Draft draft, long j2);

    public static final native byte[] Draft_user_define_get(long j, Draft draft);

    public static final native void Draft_user_define_set(long j, Draft draft, byte[] bArr);

    public static final native long ElemVec_capacity(long j, ElemVec elemVec);

    public static final native void ElemVec_clear(long j, ElemVec elemVec);

    public static final native boolean ElemVec_empty(long j, ElemVec elemVec);

    public static final native long ElemVec_get(long j, ElemVec elemVec, int i);

    public static final native void ElemVec_pushBack(long j, ElemVec elemVec, long j2, Elem elem);

    public static final native void ElemVec_reserve(long j, ElemVec elemVec, long j2);

    public static final native void ElemVec_set(long j, ElemVec elemVec, int i, long j2, Elem elem);

    public static final native long ElemVec_size(long j, ElemVec elemVec);

    public static final native long Elem_custom_get(long j, Elem elem);

    public static final native void Elem_custom_set(long j, Elem elem, long j2, CustomElem customElem);

    public static final native long Elem_face_get(long j, Elem elem);

    public static final native void Elem_face_set(long j, Elem elem, long j2, FaceElem faceElem);

    public static final native long Elem_file_get(long j, Elem elem);

    public static final native void Elem_file_set(long j, Elem elem, long j2, FileElem fileElem);

    public static final native long Elem_friend_change_get(long j, Elem elem);

    public static final native void Elem_friend_change_set(long j, Elem elem, long j2, FriendChangeElem friendChangeElem);

    public static final native long Elem_group_report_get(long j, Elem elem);

    public static final native void Elem_group_report_set(long j, Elem elem, long j2, GroupReportElem groupReportElem);

    public static final native long Elem_group_tips_get(long j, Elem elem);

    public static final native void Elem_group_tips_set(long j, Elem elem, long j2, GroupTipsElem groupTipsElem);

    public static final native long Elem_image_get(long j, Elem elem);

    public static final native void Elem_image_set(long j, Elem elem, long j2, ImageElem imageElem);

    public static final native long Elem_location_get(long j, Elem elem);

    public static final native void Elem_location_set(long j, Elem elem, long j2, LocationElem locationElem);

    public static final native long Elem_profile_change_get(long j, Elem elem);

    public static final native void Elem_profile_change_set(long j, Elem elem, long j2, ProfileChangeElem profileChangeElem);

    public static final native byte[] Elem_resource_get(long j, Elem elem);

    public static final native void Elem_resource_set(long j, Elem elem, byte[] bArr);

    public static final native byte[] Elem_self_identifier_get(long j, Elem elem);

    public static final native void Elem_self_identifier_set(long j, Elem elem, byte[] bArr);

    public static final native long Elem_sound_get(long j, Elem elem);

    public static final native void Elem_sound_set(long j, Elem elem, long j2, SoundElem soundElem);

    public static final native long Elem_text_get(long j, Elem elem);

    public static final native void Elem_text_set(long j, Elem elem, long j2, TextElem textElem);

    public static final native int Elem_type_get(long j, Elem elem);

    public static final native void Elem_type_set(long j, Elem elem, int i);

    public static final native long Elem_video_get(long j, Elem elem);

    public static final native void Elem_video_set(long j, Elem elem, long j2, VideoElem videoElem);

    public static final native void EnvRequestClosure_done(long j, EnvRequestClosure envRequestClosure, byte[] bArr);

    public static final native void EnvRequestClosure_fail(long j, EnvRequestClosure envRequestClosure, int i, String str);

    public static final native void EnvRequestClosure_release(long j, EnvRequestClosure envRequestClosure);

    public static final native long ErrCodeManager_get();

    public static final native boolean ErrCodeManager_transErrCode(long j, ErrCodeManager errCodeManager, String str, long j2, long j3);

    public static final native long ErrInfoVec_capacity(long j, ErrInfoVec errInfoVec);

    public static final native void ErrInfoVec_clear(long j, ErrInfoVec errInfoVec);

    public static final native boolean ErrInfoVec_empty(long j, ErrInfoVec errInfoVec);

    public static final native long ErrInfoVec_get(long j, ErrInfoVec errInfoVec, int i);

    public static final native void ErrInfoVec_pushBack(long j, ErrInfoVec errInfoVec, long j2, ErrInfo errInfo);

    public static final native void ErrInfoVec_reserve(long j, ErrInfoVec errInfoVec, long j2);

    public static final native void ErrInfoVec_set(long j, ErrInfoVec errInfoVec, int i, long j2, ErrInfo errInfo);

    public static final native long ErrInfoVec_size(long j, ErrInfoVec errInfoVec);

    public static final native byte[] FaceElem_buf_get(long j, FaceElem faceElem);

    public static final native void FaceElem_buf_set(long j, FaceElem faceElem, byte[] bArr);

    public static final native int FaceElem_index_get(long j, FaceElem faceElem);

    public static final native void FaceElem_index_set(long j, FaceElem faceElem, int i);

    public static final native int FileElem_business_id_get(long j, FileElem fileElem);

    public static final native void FileElem_business_id_set(long j, FileElem fileElem, int i);

    public static final native int FileElem_download_flag_get(long j, FileElem fileElem);

    public static final native void FileElem_download_flag_set(long j, FileElem fileElem, int i);

    public static final native byte[] FileElem_file_name_get(long j, FileElem fileElem);

    public static final native void FileElem_file_name_set(long j, FileElem fileElem, byte[] bArr);

    public static final native byte[] FileElem_file_path_get(long j, FileElem fileElem);

    public static final native void FileElem_file_path_set(long j, FileElem fileElem, byte[] bArr);

    public static final native long FileElem_file_size_get(long j, FileElem fileElem);

    public static final native void FileElem_file_size_set(long j, FileElem fileElem, long j2);

    public static final native String FileElem_fileid_get(long j, FileElem fileElem);

    public static final native void FileElem_fileid_set(long j, FileElem fileElem, String str);

    public static final native int FileElem_taskid_get(long j, FileElem fileElem);

    public static final native void FileElem_taskid_set(long j, FileElem fileElem, int i);

    public static final native long FileElem_urls_get(long j, FileElem fileElem);

    public static final native void FileElem_urls_set(long j, FileElem fileElem, long j2, StrVec strVec);

    public static final native byte[] FileTransSuccParam_data_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_data_set(long j, FileTransSuccParam fileTransSuccParam, byte[] bArr);

    public static final native long FileTransSuccParam_dlinfo_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_dlinfo_set(long j, FileTransSuccParam fileTransSuccParam, long j2);

    public static final native String FileTransSuccParam_fileid_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_fileid_set(long j, FileTransSuccParam fileTransSuccParam, String str);

    public static final native int FileTransSuccParam_height_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_height_set(long j, FileTransSuccParam fileTransSuccParam, int i);

    public static final native long FileTransSuccParam_size_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_size_set(long j, FileTransSuccParam fileTransSuccParam, long j2);

    public static final native int FileTransSuccParam_width_get(long j, FileTransSuccParam fileTransSuccParam);

    public static final native void FileTransSuccParam_width_set(long j, FileTransSuccParam fileTransSuccParam, int i);

    public static final native long FileTranser_getImpl(long j, FileTranser fileTranser);

    public static final native void FileTranser_setCachePath(long j, FileTranser fileTranser, String str);

    public static final native long FriendChangeElem_ddwDecideReportTimestamp_get(long j, FriendChangeElem friendChangeElem);

    public static final native void FriendChangeElem_ddwDecideReportTimestamp_set(long j, FriendChangeElem friendChangeElem, long j2);

    public static final native long FriendChangeElem_ddwPendencyReportTimestamp_get(long j, FriendChangeElem friendChangeElem);

    public static final native void FriendChangeElem_ddwPendencyReportTimestamp_set(long j, FriendChangeElem friendChangeElem, long j2);

    public static final native long FriendChangeElem_ddwRecommendReportTimestamp_get(long j, FriendChangeElem friendChangeElem);

    public static final native void FriendChangeElem_ddwRecommendReportTimestamp_set(long j, FriendChangeElem friendChangeElem, long j2);

    public static final native long FriendChangeElem_type_get(long j, FriendChangeElem friendChangeElem);

    public static final native void FriendChangeElem_type_set(long j, FriendChangeElem friendChangeElem, long j2);

    public static final native long FriendChangeElem_users_get(long j, FriendChangeElem friendChangeElem);

    public static final native void FriendChangeElem_users_set(long j, FriendChangeElem friendChangeElem, long j2, FriendChangeInfoVec friendChangeInfoVec);

    public static final native long FriendChangeInfoVec_capacity(long j, FriendChangeInfoVec friendChangeInfoVec);

    public static final native void FriendChangeInfoVec_clear(long j, FriendChangeInfoVec friendChangeInfoVec);

    public static final native boolean FriendChangeInfoVec_empty(long j, FriendChangeInfoVec friendChangeInfoVec);

    public static final native long FriendChangeInfoVec_get(long j, FriendChangeInfoVec friendChangeInfoVec, int i);

    public static final native void FriendChangeInfoVec_pushBack(long j, FriendChangeInfoVec friendChangeInfoVec, long j2, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeInfoVec_reserve(long j, FriendChangeInfoVec friendChangeInfoVec, long j2);

    public static final native void FriendChangeInfoVec_set(long j, FriendChangeInfoVec friendChangeInfoVec, int i, long j2, FriendChangeUserInfo friendChangeUserInfo);

    public static final native long FriendChangeInfoVec_size(long j, FriendChangeInfoVec friendChangeInfoVec);

    public static final native byte[] FriendChangeUserInfo_add_source_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_add_source_set(long j, FriendChangeUserInfo friendChangeUserInfo, byte[] bArr);

    public static final native byte[] FriendChangeUserInfo_add_wording_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_add_wording_set(long j, FriendChangeUserInfo friendChangeUserInfo, byte[] bArr);

    public static final native byte[] FriendChangeUserInfo_group_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_group_set(long j, FriendChangeUserInfo friendChangeUserInfo, byte[] bArr);

    public static final native String FriendChangeUserInfo_identifier_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_identifier_set(long j, FriendChangeUserInfo friendChangeUserInfo, String str);

    public static final native byte[] FriendChangeUserInfo_nick_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_nick_set(long j, FriendChangeUserInfo friendChangeUserInfo, byte[] bArr);

    public static final native byte[] FriendChangeUserInfo_remark_get(long j, FriendChangeUserInfo friendChangeUserInfo);

    public static final native void FriendChangeUserInfo_remark_set(long j, FriendChangeUserInfo friendChangeUserInfo, byte[] bArr);

    public static final native long FriendFutureItemVec_capacity(long j, FriendFutureItemVec friendFutureItemVec);

    public static final native void FriendFutureItemVec_clear(long j, FriendFutureItemVec friendFutureItemVec);

    public static final native boolean FriendFutureItemVec_empty(long j, FriendFutureItemVec friendFutureItemVec);

    public static final native long FriendFutureItemVec_get(long j, FriendFutureItemVec friendFutureItemVec, int i);

    public static final native void FriendFutureItemVec_pushBack(long j, FriendFutureItemVec friendFutureItemVec, long j2, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItemVec_reserve(long j, FriendFutureItemVec friendFutureItemVec, long j2);

    public static final native void FriendFutureItemVec_set(long j, FriendFutureItemVec friendFutureItemVec, int i, long j2, FriendFutureItem friendFutureItem);

    public static final native long FriendFutureItemVec_size(long j, FriendFutureItemVec friendFutureItemVec);

    public static final native long FriendFutureItem_ddwAddTime_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_ddwAddTime_set(long j, FriendFutureItem friendFutureItem, long j2);

    public static final native int FriendFutureItem_eType_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_eType_set(long j, FriendFutureItem friendFutureItem, int i);

    public static final native long FriendFutureItem_mpRecommendTags_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_mpRecommendTags_set(long j, FriendFutureItem friendFutureItem, long j2, BytesMap bytesMap);

    public static final native byte[] FriendFutureItem_sAddSource_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_sAddSource_set(long j, FriendFutureItem friendFutureItem, byte[] bArr);

    public static final native byte[] FriendFutureItem_sAddWording_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_sAddWording_set(long j, FriendFutureItem friendFutureItem, byte[] bArr);

    public static final native String FriendFutureItem_sIdentifier_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_sIdentifier_set(long j, FriendFutureItem friendFutureItem, String str);

    public static final native long FriendFutureItem_stProfile_get(long j, FriendFutureItem friendFutureItem);

    public static final native void FriendFutureItem_stProfile_set(long j, FriendFutureItem friendFutureItem, long j2, FriendProfile friendProfile);

    public static final native long FriendGroupItem_identifiers_get(long j, FriendGroupItem friendGroupItem);

    public static final native void FriendGroupItem_identifiers_set(long j, FriendGroupItem friendGroupItem, long j2);

    public static final native byte[] FriendGroupItem_name_get(long j, FriendGroupItem friendGroupItem);

    public static final native void FriendGroupItem_name_set(long j, FriendGroupItem friendGroupItem, byte[] bArr);

    public static final native long FriendGroupVec_capacity(long j, FriendGroupVec friendGroupVec);

    public static final native void FriendGroupVec_clear(long j, FriendGroupVec friendGroupVec);

    public static final native boolean FriendGroupVec_empty(long j, FriendGroupVec friendGroupVec);

    public static final native long FriendGroupVec_get(long j, FriendGroupVec friendGroupVec, int i);

    public static final native void FriendGroupVec_pushBack(long j, FriendGroupVec friendGroupVec, long j2, FriendGroup friendGroup);

    public static final native void FriendGroupVec_reserve(long j, FriendGroupVec friendGroupVec, long j2);

    public static final native void FriendGroupVec_set(long j, FriendGroupVec friendGroupVec, int i, long j2, FriendGroup friendGroup);

    public static final native long FriendGroupVec_size(long j, FriendGroupVec friendGroupVec);

    public static final native long FriendGroup_count_get(long j, FriendGroup friendGroup);

    public static final native void FriendGroup_count_set(long j, FriendGroup friendGroup, long j2);

    public static final native long FriendGroup_identifiers_get(long j, FriendGroup friendGroup);

    public static final native void FriendGroup_identifiers_set(long j, FriendGroup friendGroup, long j2, StrVec strVec);

    public static final native byte[] FriendGroup_name_get(long j, FriendGroup friendGroup);

    public static final native void FriendGroup_name_set(long j, FriendGroup friendGroup, byte[] bArr);

    public static final native long FriendGroup_profiles_get(long j, FriendGroup friendGroup);

    public static final native void FriendGroup_profiles_set(long j, FriendGroup friendGroup, long j2, FriendProfileVec friendProfileVec);

    public static final native long FriendGroup_tinyids_get(long j, FriendGroup friendGroup);

    public static final native void FriendGroup_tinyids_set(long j, FriendGroup friendGroup, long j2);

    public static final native long FriendMetaInfo_ddwInfoSeq_get(long j, FriendMetaInfo friendMetaInfo);

    public static final native void FriendMetaInfo_ddwInfoSeq_set(long j, FriendMetaInfo friendMetaInfo, long j2);

    public static final native long FriendMetaInfo_ddwNextSeq_get(long j, FriendMetaInfo friendMetaInfo);

    public static final native void FriendMetaInfo_ddwNextSeq_set(long j, FriendMetaInfo friendMetaInfo, long j2);

    public static final native long FriendMetaInfo_ddwTimestamp_get(long j, FriendMetaInfo friendMetaInfo);

    public static final native void FriendMetaInfo_ddwTimestamp_set(long j, FriendMetaInfo friendMetaInfo, long j2);

    public static final native boolean FriendMetaInfo_recover_get(long j, FriendMetaInfo friendMetaInfo);

    public static final native void FriendMetaInfo_recover_set(long j, FriendMetaInfo friendMetaInfo, boolean z);

    public static final native long FriendPendencyItemVec_capacity(long j, FriendPendencyItemVec friendPendencyItemVec);

    public static final native void FriendPendencyItemVec_clear(long j, FriendPendencyItemVec friendPendencyItemVec);

    public static final native boolean FriendPendencyItemVec_empty(long j, FriendPendencyItemVec friendPendencyItemVec);

    public static final native long FriendPendencyItemVec_get(long j, FriendPendencyItemVec friendPendencyItemVec, int i);

    public static final native void FriendPendencyItemVec_pushBack(long j, FriendPendencyItemVec friendPendencyItemVec, long j2, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItemVec_reserve(long j, FriendPendencyItemVec friendPendencyItemVec, long j2);

    public static final native void FriendPendencyItemVec_set(long j, FriendPendencyItemVec friendPendencyItemVec, int i, long j2, FriendPendencyItem friendPendencyItem);

    public static final native long FriendPendencyItemVec_size(long j, FriendPendencyItemVec friendPendencyItemVec);

    public static final native long FriendPendencyItem_ddwAddTime_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_ddwAddTime_set(long j, FriendPendencyItem friendPendencyItem, long j2);

    public static final native int FriendPendencyItem_iType_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_iType_set(long j, FriendPendencyItem friendPendencyItem, int i);

    public static final native byte[] FriendPendencyItem_sAddSource_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_sAddSource_set(long j, FriendPendencyItem friendPendencyItem, byte[] bArr);

    public static final native byte[] FriendPendencyItem_sAddWording_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_sAddWording_set(long j, FriendPendencyItem friendPendencyItem, byte[] bArr);

    public static final native String FriendPendencyItem_sIdentifier_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_sIdentifier_set(long j, FriendPendencyItem friendPendencyItem, String str);

    public static final native byte[] FriendPendencyItem_sNickname_get(long j, FriendPendencyItem friendPendencyItem);

    public static final native void FriendPendencyItem_sNickname_set(long j, FriendPendencyItem friendPendencyItem, byte[] bArr);

    public static final native long FriendPendencyMeta_ddwNumPerPage_get(long j, FriendPendencyMeta friendPendencyMeta);

    public static final native void FriendPendencyMeta_ddwNumPerPage_set(long j, FriendPendencyMeta friendPendencyMeta, long j2);

    public static final native long FriendPendencyMeta_ddwSeq_get(long j, FriendPendencyMeta friendPendencyMeta);

    public static final native void FriendPendencyMeta_ddwSeq_set(long j, FriendPendencyMeta friendPendencyMeta, long j2);

    public static final native long FriendPendencyMeta_ddwTimestamp_get(long j, FriendPendencyMeta friendPendencyMeta);

    public static final native void FriendPendencyMeta_ddwTimestamp_set(long j, FriendPendencyMeta friendPendencyMeta, long j2);

    public static final native long FriendPendencyMeta_ddwUnReadCnt_get(long j, FriendPendencyMeta friendPendencyMeta);

    public static final native void FriendPendencyMeta_ddwUnReadCnt_set(long j, FriendPendencyMeta friendPendencyMeta, long j2);

    public static final native String FriendProfileUpdateItem_bytes_value_get(long j, FriendProfileUpdateItem friendProfileUpdateItem);

    public static final native void FriendProfileUpdateItem_bytes_value_set(long j, FriendProfileUpdateItem friendProfileUpdateItem, String str);

    public static final native String FriendProfileUpdateItem_tag_get(long j, FriendProfileUpdateItem friendProfileUpdateItem);

    public static final native void FriendProfileUpdateItem_tag_set(long j, FriendProfileUpdateItem friendProfileUpdateItem, String str);

    public static final native long FriendProfileUpdateItem_uint64_value_get(long j, FriendProfileUpdateItem friendProfileUpdateItem);

    public static final native void FriendProfileUpdateItem_uint64_value_set(long j, FriendProfileUpdateItem friendProfileUpdateItem, long j2);

    public static final native long FriendProfileUpdateItem_vec_bytes_value_get(long j, FriendProfileUpdateItem friendProfileUpdateItem);

    public static final native void FriendProfileUpdateItem_vec_bytes_value_set(long j, FriendProfileUpdateItem friendProfileUpdateItem, long j2, StrVec strVec);

    public static final native long FriendProfileVec_capacity(long j, FriendProfileVec friendProfileVec);

    public static final native void FriendProfileVec_clear(long j, FriendProfileVec friendProfileVec);

    public static final native boolean FriendProfileVec_empty(long j, FriendProfileVec friendProfileVec);

    public static final native long FriendProfileVec_get(long j, FriendProfileVec friendProfileVec, int i);

    public static final native void FriendProfileVec_pushBack(long j, FriendProfileVec friendProfileVec, long j2, FriendProfile friendProfile);

    public static final native void FriendProfileVec_reserve(long j, FriendProfileVec friendProfileVec, long j2);

    public static final native void FriendProfileVec_set(long j, FriendProfileVec friendProfileVec, int i, long j2, FriendProfile friendProfile);

    public static final native long FriendProfileVec_size(long j, FriendProfileVec friendProfileVec);

    public static final native long FriendProfile_custom_info_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_custom_info_set(long j, FriendProfile friendProfile, long j2, BytesMap bytesMap);

    public static final native long FriendProfile_ddwTinyId_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_ddwTinyId_set(long j, FriendProfile friendProfile, long j2);

    public static final native int FriendProfile_eGender_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_eGender_set(long j, FriendProfile friendProfile, int i);

    public static final native long FriendProfile_result_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_result_set(long j, FriendProfile friendProfile, long j2);

    public static final native byte[] FriendProfile_sAddSource_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sAddSource_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native byte[] FriendProfile_sAddWording_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sAddWording_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native String FriendProfile_sAllowType_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sAllowType_set(long j, FriendProfile friendProfile, String str);

    public static final native byte[] FriendProfile_sFaceURL_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sFaceURL_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native long FriendProfile_sGroupNames_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sGroupNames_set(long j, FriendProfile friendProfile, long j2, BytesVec bytesVec);

    public static final native String FriendProfile_sIdentifier_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sIdentifier_set(long j, FriendProfile friendProfile, String str);

    public static final native byte[] FriendProfile_sLocation_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sLocation_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native byte[] FriendProfile_sNickname_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sNickname_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native byte[] FriendProfile_sRemark_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sRemark_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native String FriendProfile_sResponseAction_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sResponseAction_set(long j, FriendProfile friendProfile, String str);

    public static final native byte[] FriendProfile_sSelfSignature_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_sSelfSignature_set(long j, FriendProfile friendProfile, byte[] bArr);

    public static final native long FriendProfile_set_flags_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_set_flags_set(long j, FriendProfile friendProfile, long j2);

    public static final native long FriendProfile_uBirthDay_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_uBirthDay_set(long j, FriendProfile friendProfile, long j2);

    public static final native long FriendProfile_uLanguage_get(long j, FriendProfile friendProfile);

    public static final native void FriendProfile_uLanguage_set(long j, FriendProfile friendProfile, long j2);

    public static final native boolean FriendshipManager_addBlackList(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_addFriend(long j, FriendshipManager friendshipManager, long j2, FriendProfileVec friendProfileVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_addFriendToMap(long j, FriendshipManager friendshipManager, long j2, FriendProfile friendProfile);

    public static final native boolean FriendshipManager_addFriends2Group(long j, FriendshipManager friendshipManager, byte[] bArr, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_checkFriend(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, String str, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native void FriendshipManager_clearAllData(long j, FriendshipManager friendshipManager);

    public static final native boolean FriendshipManager_createFriendGroup(long j, FriendshipManager friendshipManager, long j2, BytesVec bytesVec, long j3, StrVec strVec, long j4, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_delBlackList(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_delFriend(long j, FriendshipManager friendshipManager, int i, long j2, FriendProfileVec friendProfileVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_delFriendToMap(long j, FriendshipManager friendshipManager, long j2, FriendProfile friendProfile);

    public static final native boolean FriendshipManager_delFriendsFromGroup(long j, FriendshipManager friendshipManager, byte[] bArr, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_deleteDecide(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_deleteFriendGroup(long j, FriendshipManager friendshipManager, long j2, BytesVec bytesVec, long j3, IFriendshipCallback iFriendshipCallback);

    public static final native boolean FriendshipManager_deletePendency(long j, FriendshipManager friendshipManager, int i, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_deleteRecommend(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_doResponse(long j, FriendshipManager friendshipManager, long j2, FriendProfileVec friendProfileVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getBlackList(long j, FriendshipManager friendshipManager, long j2, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getFriendGroup(long j, FriendshipManager friendshipManager, long j2, BytesVec bytesVec, boolean z, long j3, IFriendGroupCallback iFriendGroupCallback);

    public static final native boolean FriendshipManager_getFriendListV2(long j, FriendshipManager friendshipManager, long j2, long j3, StrVec strVec, long j4, FriendMetaInfo friendMetaInfo, long j5, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback);

    public static final native boolean FriendshipManager_getFriendList__SWIG_0(long j, FriendshipManager friendshipManager, long j2, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getFriendList__SWIG_1(long j, FriendshipManager friendshipManager, long j2, GetProfileOption getProfileOption, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getFriendProfile(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, GetProfileOption getProfileOption, long j4, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getFutureFriends(long j, FriendshipManager friendshipManager, long j2, long j3, long j4, StrVec strVec, long j5, FutureFriendMeta futureFriendMeta, long j6, IFriendshipGetFutureCallback iFriendshipGetFutureCallback);

    public static final native long FriendshipManager_getLocalFrdList(long j, FriendshipManager friendshipManager);

    public static final native boolean FriendshipManager_getPendencyFromServer(long j, FriendshipManager friendshipManager, long j2, FriendPendencyMeta friendPendencyMeta, int i, long j3, IFriendshipPendencyCallback iFriendshipPendencyCallback);

    public static final native boolean FriendshipManager_getProfile__SWIG_0(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_getProfile__SWIG_1(long j, FriendshipManager friendshipManager, long j2, StrVec strVec, long j3, GetProfileOption getProfileOption, long j4, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_modifyFriendGroupName(long j, FriendshipManager friendshipManager, byte[] bArr, byte[] bArr2, long j2, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native boolean FriendshipManager_pendencyReport(long j, FriendshipManager friendshipManager, long j2, long j3, IFriendshipCallback iFriendshipCallback);

    public static final native boolean FriendshipManager_recommendReport(long j, FriendshipManager friendshipManager, long j2, long j3, IFriendshipCallback iFriendshipCallback);

    public static final native boolean FriendshipManager_searchFriendsUseNickName(long j, FriendshipManager friendshipManager, String str, long j2, long j3, long j4, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2);

    public static final native boolean FriendshipManager_searchUserUseIdentifier(long j, FriendshipManager friendshipManager, String str, long j2, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native void FriendshipManager_setExpire(long j, FriendshipManager friendshipManager);

    public static final native boolean FriendshipManager_setFrdListTimestamp(long j, FriendshipManager friendshipManager, long j2);

    public static final native boolean FriendshipManager_setFrdSeq(long j, FriendshipManager friendshipManager, long j2);

    public static final native boolean FriendshipManager_setProfile(long j, FriendshipManager friendshipManager, long j2, SetProfileOption setProfileOption, long j3, IFriendshipCallback iFriendshipCallback);

    public static final native boolean FriendshipManager_setSnsProfile(long j, FriendshipManager friendshipManager, long j2, SNSProfileItemVec sNSProfileItemVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native void FriendshipManager_updateFriendGroupSeq(long j, FriendshipManager friendshipManager, long j2);

    public static final native long FriendshipProxyConfig_custom_get(long j, FriendshipProxyConfig friendshipProxyConfig);

    public static final native void FriendshipProxyConfig_custom_set(long j, FriendshipProxyConfig friendshipProxyConfig, long j2, StrVec strVec);

    public static final native boolean FriendshipProxyConfig_enable_get(long j, FriendshipProxyConfig friendshipProxyConfig);

    public static final native void FriendshipProxyConfig_enable_set(long j, FriendshipProxyConfig friendshipProxyConfig, boolean z);

    public static final native long FriendshipProxyConfig_flags_get(long j, FriendshipProxyConfig friendshipProxyConfig);

    public static final native void FriendshipProxyConfig_flags_set(long j, FriendshipProxyConfig friendshipProxyConfig, long j2);

    public static final native long FriendshipProxyConfig_listener_get(long j, FriendshipProxyConfig friendshipProxyConfig);

    public static final native void FriendshipProxyConfig_listener_set(long j, FriendshipProxyConfig friendshipProxyConfig, long j2, IFriendshipProxyListener iFriendshipProxyListener);

    public static final native void FriendshipProxy_changeStatusWithoutNotify(long j, FriendshipProxy friendshipProxy, int i);

    public static final native long FriendshipProxy_convertToFriendGroupVec(long j, FriendshipProxy friendshipProxy, long j2);

    public static final native long FriendshipProxy_getAddBlackList(long j, FriendshipProxy friendshipProxy, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getAddFriend2GroupCB(long j, FriendshipProxy friendshipProxy, byte[] bArr, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getCreateFriendGroupCB(long j, FriendshipProxy friendshipProxy, long j2, BytesVec bytesVec, long j3, StrVec strVec, long j4, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getCustom(long j, FriendshipProxy friendshipProxy);

    public static final native long FriendshipProxy_getDelFriendCB(long j, FriendshipProxy friendshipProxy, int i, long j2, FriendProfileVec friendProfileVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getDelFriendsFromGroupCB(long j, FriendshipProxy friendshipProxy, byte[] bArr, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getDeleteFriendGroup(long j, FriendshipProxy friendshipProxy, long j2, BytesVec bytesVec, long j3, IFriendshipCallback iFriendshipCallback);

    public static final native long FriendshipProxy_getDoResponseCB(long j, FriendshipProxy friendshipProxy, long j2, FriendProfileVec friendProfileVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getFrdGrpSeq(long j, FriendshipProxy friendshipProxy);

    public static final native long FriendshipProxy_getFrdSeq(long j, FriendshipProxy friendshipProxy);

    public static final native long FriendshipProxy_getFrdTime(long j, FriendshipProxy friendshipProxy);

    public static final native long FriendshipProxy_getFriendFlags(long j, FriendshipProxy friendshipProxy);

    public static final native int FriendshipProxy_getFriendGroup(long j, FriendshipProxy friendshipProxy, long j2, BytesVec bytesVec, long j3, FriendGroupVec friendGroupVec);

    public static final native int FriendshipProxy_getFriendList(long j, FriendshipProxy friendshipProxy, long j2, FriendProfileVec friendProfileVec);

    public static final native int FriendshipProxy_getFriendProfile(long j, FriendshipProxy friendshipProxy, long j2, StrVec strVec, long j3, FriendProfileVec friendProfileVec);

    public static final native long FriendshipProxy_getModifyFriendGroupName(long j, FriendshipProxy friendshipProxy, byte[] bArr, byte[] bArr2, long j2, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getMove2FriendGroup(long j, FriendshipProxy friendshipProxy, String str, String str2, long j2, StrVec strVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native long FriendshipProxy_getSetSnsProfileCB(long j, FriendshipProxy friendshipProxy, long j2, SNSProfileItemVec sNSProfileItemVec, long j3, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native int FriendshipProxy_getStatus(long j, FriendshipProxy friendshipProxy);

    public static final native void FriendshipProxy_init(long j, FriendshipProxy friendshipProxy, String str, long j2, IFriendshipCallback iFriendshipCallback);

    public static final native boolean FriendshipProxy_isEnable(long j, FriendshipProxy friendshipProxy);

    public static final native void FriendshipProxy_onFriendGroupSync(long j, FriendshipProxy friendshipProxy, long j2, FriendGroupVec friendGroupVec, long j3, boolean z);

    public static final native void FriendshipProxy_onFriendshipEvent(long j, FriendshipProxy friendshipProxy, long j2);

    public static final native void FriendshipProxy_onProfileSync(long j, FriendshipProxy friendshipProxy, long j2, FriendProfileVec friendProfileVec, long j3, long j4, boolean z);

    public static final native void FriendshipProxy_onSyncFail(long j, FriendshipProxy friendshipProxy, int i, String str);

    public static final native void FriendshipProxy_onSyncSucc(long j, FriendshipProxy friendshipProxy);

    public static final native long FriendshipProxy_proxyProfileOption(long j, FriendshipProxy friendshipProxy);

    public static final native void FriendshipProxy_setFriendProxyConfig(long j, FriendshipProxy friendshipProxy, long j2, FriendshipProxyConfig friendshipProxyConfig);

    public static final native void FriendshipProxy_syncProxy(long j, FriendshipProxy friendshipProxy);

    public static final native long FutureFriendMeta_ddwCurrentDecideTimestamp_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwCurrentDecideTimestamp_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwCurrentPendencyTimestamp_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwCurrentPendencyTimestamp_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwCurrentRecommendTimestamp_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwCurrentRecommendTimestamp_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwDecideSequence_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwDecideSequence_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwDecideUnreadCnt_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwDecideUnreadCnt_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwPendencySequence_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwPendencySequence_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwPendencyUnReadCnt_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwPendencyUnReadCnt_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwRecommendSequence_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwRecommendSequence_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwRecommendUnReadCnt_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwRecommendUnReadCnt_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwReqNum_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwReqNum_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native long FutureFriendMeta_ddwTimestamp_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_ddwTimestamp_set(long j, FutureFriendMeta futureFriendMeta, long j2);

    public static final native int FutureFriendMeta_iDirection_get(long j, FutureFriendMeta futureFriendMeta);

    public static final native void FutureFriendMeta_iDirection_set(long j, FutureFriendMeta futureFriendMeta, int i);

    public static final native long GetGroupBaseInfoOption_custom_info_get(long j, GetGroupBaseInfoOption getGroupBaseInfoOption);

    public static final native void GetGroupBaseInfoOption_custom_info_set(long j, GetGroupBaseInfoOption getGroupBaseInfoOption, long j2, BytesMap bytesMap);

    public static final native long GetGroupBaseInfoOption_flag_get(long j, GetGroupBaseInfoOption getGroupBaseInfoOption);

    public static final native void GetGroupBaseInfoOption_flag_set(long j, GetGroupBaseInfoOption getGroupBaseInfoOption, long j2);

    public static final native long GetGroupBaseInfoOption_groups_get(long j, GetGroupBaseInfoOption getGroupBaseInfoOption);

    public static final native void GetGroupBaseInfoOption_groups_set(long j, GetGroupBaseInfoOption getGroupBaseInfoOption, long j2, StrVec strVec);

    public static final native long GetGroupMemInfoOption_custom_info_get(long j, GetGroupMemInfoOption getGroupMemInfoOption);

    public static final native void GetGroupMemInfoOption_custom_info_set(long j, GetGroupMemInfoOption getGroupMemInfoOption, long j2, BytesMap bytesMap);

    public static final native int GetGroupMemInfoOption_filter_get(long j, GetGroupMemInfoOption getGroupMemInfoOption);

    public static final native void GetGroupMemInfoOption_filter_set(long j, GetGroupMemInfoOption getGroupMemInfoOption, int i);

    public static final native long GetGroupMemInfoOption_flag_get(long j, GetGroupMemInfoOption getGroupMemInfoOption);

    public static final native void GetGroupMemInfoOption_flag_set(long j, GetGroupMemInfoOption getGroupMemInfoOption, long j2);

    public static final native String GetGroupMemInfoOption_group_id_get(long j, GetGroupMemInfoOption getGroupMemInfoOption);

    public static final native void GetGroupMemInfoOption_group_id_set(long j, GetGroupMemInfoOption getGroupMemInfoOption, String str);

    public static final native long GetGroupMemInfoOption_members_get(long j, GetGroupMemInfoOption getGroupMemInfoOption);

    public static final native void GetGroupMemInfoOption_members_set(long j, GetGroupMemInfoOption getGroupMemInfoOption, long j2, StrVec strVec);

    public static final native long GetGroupPendencyOption_max_limited_get(long j, GetGroupPendencyOption getGroupPendencyOption);

    public static final native void GetGroupPendencyOption_max_limited_set(long j, GetGroupPendencyOption getGroupPendencyOption, long j2);

    public static final native long GetGroupPendencyOption_start_time_get(long j, GetGroupPendencyOption getGroupPendencyOption);

    public static final native void GetGroupPendencyOption_start_time_set(long j, GetGroupPendencyOption getGroupPendencyOption, long j2);

    public static final native long GetProfileOption_custom_info_get(long j, GetProfileOption getProfileOption);

    public static final native void GetProfileOption_custom_info_set(long j, GetProfileOption getProfileOption, long j2, BytesMap bytesMap);

    public static final native long GetProfileOption_flag_get(long j, GetProfileOption getProfileOption);

    public static final native void GetProfileOption_flag_set(long j, GetProfileOption getProfileOption, long j2);

    public static final native long GroupAssistantConfig_callback_get(long j, GroupAssistantConfig groupAssistantConfig);

    public static final native void GroupAssistantConfig_callback_set(long j, GroupAssistantConfig groupAssistantConfig, long j2, IGroupAssistantCallback iGroupAssistantCallback);

    public static final native long GroupAssistantConfig_settings_get(long j, GroupAssistantConfig groupAssistantConfig);

    public static final native void GroupAssistantConfig_settings_set(long j, GroupAssistantConfig groupAssistantConfig, long j2, GroupSettings groupSettings);

    public static final native boolean GroupAssistant_checkGroupModifyOption(long j, GroupAssistant groupAssistant, long j2, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native boolean GroupAssistant_checkMemberModifyOption(long j, GroupAssistant groupAssistant, long j2, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native long GroupAssistant_getGroupInfoCache(long j, GroupAssistant groupAssistant);

    public static final native long GroupAssistant_getGroupSettings(long j, GroupAssistant groupAssistant);

    public static final native int GroupAssistant_getGroups(long j, GroupAssistant groupAssistant, long j2, StrVec strVec, long j3, GroupCacheInfoVec groupCacheInfoVec);

    public static final native void GroupAssistant_init(long j, GroupAssistant groupAssistant, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native void GroupAssistant_initGroupSettings(long j, GroupAssistant groupAssistant, long j2, GroupSettings groupSettings);

    public static final native boolean GroupAssistant_isGroupExist(long j, GroupAssistant groupAssistant, String str);

    public static final native void GroupAssistant_onGroupAdd(long j, GroupAssistant groupAssistant, String str);

    public static final native void GroupAssistant_onGroupDelete(long j, GroupAssistant groupAssistant, String str);

    public static final native void GroupAssistant_onGroupSystemEvent(long j, GroupAssistant groupAssistant, String str, long j2);

    public static final native void GroupAssistant_onGroupTipsEvent(long j, GroupAssistant groupAssistant, String str, long j2, GroupTipsElem groupTipsElem);

    public static final native void GroupAssistant_onGroupUpdateOwner(long j, GroupAssistant groupAssistant, String str, String str2);

    public static final native void GroupAssistant_onGroupUpdateSelfInfo(long j, GroupAssistant groupAssistant, String str, long j2, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void GroupAssistant_onGroupUpdate__SWIG_0(long j, GroupAssistant groupAssistant, long j2, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void GroupAssistant_onGroupUpdate__SWIG_1(long j, GroupAssistant groupAssistant, String str, long j2, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native void GroupAssistant_onMemberJoin(long j, GroupAssistant groupAssistant, String str, long j2);

    public static final native void GroupAssistant_onMemberQuit(long j, GroupAssistant groupAssistant, String str, long j2);

    public static final native void GroupAssistant_onMemberUpdate(long j, GroupAssistant groupAssistant, String str, long j2);

    public static final native long GroupAssistant_prepareDefaultGetGroupOption(long j, GroupAssistant groupAssistant, long j2, StrVec strVec);

    public static final native long GroupAssistant_prepareDefaultGetMemOption(long j, GroupAssistant groupAssistant, String str, long j2, StrVec strVec);

    public static final native void GroupAssistant_setCallback(long j, GroupAssistant groupAssistant, long j2, IGroupAssistantCallback iGroupAssistantCallback);

    public static final native void GroupAssistant_sync(long j, GroupAssistant groupAssistant, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native long GroupBaseInfoVec_capacity(long j, GroupBaseInfoVec groupBaseInfoVec);

    public static final native void GroupBaseInfoVec_clear(long j, GroupBaseInfoVec groupBaseInfoVec);

    public static final native boolean GroupBaseInfoVec_empty(long j, GroupBaseInfoVec groupBaseInfoVec);

    public static final native long GroupBaseInfoVec_get(long j, GroupBaseInfoVec groupBaseInfoVec, int i);

    public static final native void GroupBaseInfoVec_pushBack(long j, GroupBaseInfoVec groupBaseInfoVec, long j2, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfoVec_reserve(long j, GroupBaseInfoVec groupBaseInfoVec, long j2);

    public static final native void GroupBaseInfoVec_set(long j, GroupBaseInfoVec groupBaseInfoVec, int i, long j2, GroupBaseInfo groupBaseInfo);

    public static final native long GroupBaseInfoVec_size(long j, GroupBaseInfoVec groupBaseInfoVec);

    public static final native long GroupBaseInfo_dwInfoSeq_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_dwInfoSeq_set(long j, GroupBaseInfo groupBaseInfo, long j2);

    public static final native long GroupBaseInfo_dwMsgFalg_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_dwMsgFalg_set(long j, GroupBaseInfo groupBaseInfo, long j2);

    public static final native long GroupBaseInfo_dwMsgSeq_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_dwMsgSeq_set(long j, GroupBaseInfo groupBaseInfo, long j2);

    public static final native long GroupBaseInfo_dwReadSeq_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_dwReadSeq_set(long j, GroupBaseInfo groupBaseInfo, long j2);

    public static final native byte[] GroupBaseInfo_sFaceUrl_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_sFaceUrl_set(long j, GroupBaseInfo groupBaseInfo, byte[] bArr);

    public static final native String GroupBaseInfo_sGroupId_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_sGroupId_set(long j, GroupBaseInfo groupBaseInfo, String str);

    public static final native byte[] GroupBaseInfo_sGroupName_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_sGroupName_set(long j, GroupBaseInfo groupBaseInfo, byte[] bArr);

    public static final native String GroupBaseInfo_sGroupType_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_sGroupType_set(long j, GroupBaseInfo groupBaseInfo, String str);

    public static final native long GroupBaseInfo_stSelfInfo_get(long j, GroupBaseInfo groupBaseInfo);

    public static final native void GroupBaseInfo_stSelfInfo_set(long j, GroupBaseInfo groupBaseInfo, long j2, GroupSelfInfo groupSelfInfo);

    public static final native long GroupCacheInfoVec_capacity(long j, GroupCacheInfoVec groupCacheInfoVec);

    public static final native void GroupCacheInfoVec_clear(long j, GroupCacheInfoVec groupCacheInfoVec);

    public static final native boolean GroupCacheInfoVec_empty(long j, GroupCacheInfoVec groupCacheInfoVec);

    public static final native long GroupCacheInfoVec_get(long j, GroupCacheInfoVec groupCacheInfoVec, int i);

    public static final native void GroupCacheInfoVec_pushBack(long j, GroupCacheInfoVec groupCacheInfoVec, long j2, GroupCacheInfo groupCacheInfo);

    public static final native void GroupCacheInfoVec_reserve(long j, GroupCacheInfoVec groupCacheInfoVec, long j2);

    public static final native void GroupCacheInfoVec_set(long j, GroupCacheInfoVec groupCacheInfoVec, int i, long j2, GroupCacheInfo groupCacheInfo);

    public static final native long GroupCacheInfoVec_size(long j, GroupCacheInfoVec groupCacheInfoVec);

    public static final native long GroupCacheInfo_groupInfo_get(long j, GroupCacheInfo groupCacheInfo);

    public static final native void GroupCacheInfo_groupInfo_set(long j, GroupCacheInfo groupCacheInfo, long j2, GroupDetailInfo groupDetailInfo);

    public static final native long GroupCacheInfo_selfInfo_get(long j, GroupCacheInfo groupCacheInfo);

    public static final native void GroupCacheInfo_selfInfo_set(long j, GroupCacheInfo groupCacheInfo, long j2, GroupSelfInfo groupSelfInfo);

    public static final native long GroupDetailInfoVec_capacity(long j, GroupDetailInfoVec groupDetailInfoVec);

    public static final native void GroupDetailInfoVec_clear(long j, GroupDetailInfoVec groupDetailInfoVec);

    public static final native boolean GroupDetailInfoVec_empty(long j, GroupDetailInfoVec groupDetailInfoVec);

    public static final native long GroupDetailInfoVec_get(long j, GroupDetailInfoVec groupDetailInfoVec, int i);

    public static final native void GroupDetailInfoVec_pushBack(long j, GroupDetailInfoVec groupDetailInfoVec, long j2, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfoVec_reserve(long j, GroupDetailInfoVec groupDetailInfoVec, long j2);

    public static final native void GroupDetailInfoVec_set(long j, GroupDetailInfoVec groupDetailInfoVec, int i, long j2, GroupDetailInfo groupDetailInfo);

    public static final native long GroupDetailInfoVec_size(long j, GroupDetailInfoVec groupDetailInfoVec);

    public static final native long GroupDetailInfo_custom_info_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_custom_info_set(long j, GroupDetailInfo groupDetailInfo, long j2, BytesMap bytesMap);

    public static final native long GroupDetailInfo_ddwOwnerTinyId_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_ddwOwnerTinyId_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwAddOption_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwAddOption_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwAppid_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwAppid_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwCreateTime_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwCreateTime_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwInfoSeq_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwInfoSeq_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwLastInfoTime_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwLastInfoTime_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwLastMsgTime_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwLastMsgTime_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwMaxMemberNum_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwMaxMemberNum_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwMemberNum_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwMemberNum_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwNextMsgSeq_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwNextMsgSeq_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native long GroupDetailInfo_dwOnlineMemberNum_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_dwOnlineMemberNum_set(long j, GroupDetailInfo groupDetailInfo, long j2);

    public static final native int GroupDetailInfo_eSearchable_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_eSearchable_set(long j, GroupDetailInfo groupDetailInfo, int i);

    public static final native int GroupDetailInfo_eVisible_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_eVisible_set(long j, GroupDetailInfo groupDetailInfo, int i);

    public static final native long GroupDetailInfo_lastMsg_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_lastMsg_set(long j, GroupDetailInfo groupDetailInfo, long j2, Msg msg);

    public static final native String GroupDetailInfo_sFaceUrl_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sFaceUrl_set(long j, GroupDetailInfo groupDetailInfo, String str);

    public static final native String GroupDetailInfo_sGroupId_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sGroupId_set(long j, GroupDetailInfo groupDetailInfo, String str);

    public static final native byte[] GroupDetailInfo_sGroupName_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sGroupName_set(long j, GroupDetailInfo groupDetailInfo, byte[] bArr);

    public static final native String GroupDetailInfo_sGroupType_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sGroupType_set(long j, GroupDetailInfo groupDetailInfo, String str);

    public static final native byte[] GroupDetailInfo_sIntroduction_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sIntroduction_set(long j, GroupDetailInfo groupDetailInfo, byte[] bArr);

    public static final native byte[] GroupDetailInfo_sNotification_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sNotification_set(long j, GroupDetailInfo groupDetailInfo, byte[] bArr);

    public static final native String GroupDetailInfo_sOwner_get(long j, GroupDetailInfo groupDetailInfo);

    public static final native void GroupDetailInfo_sOwner_set(long j, GroupDetailInfo groupDetailInfo, String str);

    public static final native boolean GroupManager_applyJoinGroup(long j, GroupManager groupManager, String str, String str2, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_createGroup__SWIG_0(long j, GroupManager groupManager, String str, long j2, StrVec strVec, byte[] bArr, long j3, ICreateGroupCallback iCreateGroupCallback);

    public static final native boolean GroupManager_createGroup__SWIG_1(long j, GroupManager groupManager, long j2, NewGroupInfo newGroupInfo, long j3, ICreateGroupCallback iCreateGroupCallback);

    public static final native boolean GroupManager_deleteGroup(long j, GroupManager groupManager, String str, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_deleteGroupMember__SWIG_0(long j, GroupManager groupManager, String str, long j2, StrVec strVec, long j3, IGroupMemberResultCallback iGroupMemberResultCallback, byte[] bArr);

    public static final native boolean GroupManager_deleteGroupMember__SWIG_1(long j, GroupManager groupManager, String str, long j2, StrVec strVec, long j3, IGroupMemberResultCallback iGroupMemberResultCallback);

    public static final native boolean GroupManager_getGroupBaseInfo__SWIG_0(long j, GroupManager groupManager, long j2, StrVec strVec, long j3, IGroupInfoListCallback iGroupInfoListCallback);

    public static final native boolean GroupManager_getGroupBaseInfo__SWIG_1(long j, GroupManager groupManager, long j2, GetGroupBaseInfoOption getGroupBaseInfoOption, long j3, IGroupInfoListCallback iGroupInfoListCallback);

    public static final native boolean GroupManager_getGroupList(long j, GroupManager groupManager, boolean z, long j2, IGroupListCallback iGroupListCallback);

    public static final native boolean GroupManager_getGroupMembers(long j, GroupManager groupManager, String str, long j2, IGroupMemberCallback iGroupMemberCallback);

    public static final native boolean GroupManager_getGroupMembersByFilter(long j, GroupManager groupManager, String str, long j2, int i, long j3, BytesVec bytesVec, long j4, long j5, IGroupMemberCallbackV2 iGroupMemberCallbackV2);

    public static final native boolean GroupManager_getGroupMembersInfo__SWIG_0(long j, GroupManager groupManager, String str, long j2, StrVec strVec, long j3, IGroupMemberCallback iGroupMemberCallback);

    public static final native boolean GroupManager_getGroupMembersInfo__SWIG_1(long j, GroupManager groupManager, long j2, GetGroupMemInfoOption getGroupMemInfoOption, long j3, IGroupMemberCallback iGroupMemberCallback);

    public static final native boolean GroupManager_getGroupMembersV2(long j, GroupManager groupManager, String str, long j2, long j3, BytesVec bytesVec, long j4, long j5, IGroupMemberCallbackV2 iGroupMemberCallbackV2);

    public static final native boolean GroupManager_getGroupPublicInfo(long j, GroupManager groupManager, long j2, StrVec strVec, long j3, IGroupInfoListCallback iGroupInfoListCallback);

    public static final native boolean GroupManager_getGroupPublicInfoV2(long j, GroupManager groupManager, long j2, StrVec strVec, long j3, long j4, BytesVec bytesVec, long j5, IGroupInfoListCallback iGroupInfoListCallback);

    public static final native boolean GroupManager_getMsgSeq(long j, GroupManager groupManager, String str, long j2, long j3);

    public static final native boolean GroupManager_getPendency(long j, GroupManager groupManager, long j2, GetGroupPendencyOption getGroupPendencyOption, long j3, IGroupGetPendencyCallback iGroupGetPendencyCallback);

    public static final native boolean GroupManager_getSelfInfo(long j, GroupManager groupManager, String str, long j2, IGroupMemberCallback iGroupMemberCallback);

    public static final native boolean GroupManager_handleInviteRequest(long j, GroupManager groupManager, String str, String str2, byte[] bArr, byte[] bArr2, long j2, long j3, byte[] bArr3, long j4, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_handleJoinRequest(long j, GroupManager groupManager, String str, String str2, byte[] bArr, byte[] bArr2, long j2, long j3, byte[] bArr3, long j4, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_inviteGroupMember(long j, GroupManager groupManager, String str, long j2, StrVec strVec, long j3, IGroupMemberResultCallback iGroupMemberResultCallback);

    public static final native boolean GroupManager_modifyGroupBaseInfo__SWIG_0(long j, GroupManager groupManager, String str, long j2, byte[] bArr, long j3, long j4, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_modifyGroupBaseInfo__SWIG_1(long j, GroupManager groupManager, long j2, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, long j3, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_modifyGroupMemberInfo__SWIG_0(long j, GroupManager groupManager, String str, String str2, long j2, long j3, long j4, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_modifyGroupMemberInfo__SWIG_1(long j, GroupManager groupManager, long j2, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j3, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_modifyGroupOwner(long j, GroupManager groupManager, String str, String str2, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_pendencyReport(long j, GroupManager groupManager, long j2, long j3, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_quitGroup(long j, GroupManager groupManager, String str, long j2, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native boolean GroupManager_searchGroupByName(long j, GroupManager groupManager, String str, long j2, long j3, BytesVec bytesVec, long j4, long j5, long j6, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2);

    public static final native long GroupMemberInfoVec_capacity(long j, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void GroupMemberInfoVec_clear(long j, GroupMemberInfoVec groupMemberInfoVec);

    public static final native boolean GroupMemberInfoVec_empty(long j, GroupMemberInfoVec groupMemberInfoVec);

    public static final native long GroupMemberInfoVec_get(long j, GroupMemberInfoVec groupMemberInfoVec, int i);

    public static final native void GroupMemberInfoVec_pushBack(long j, GroupMemberInfoVec groupMemberInfoVec, long j2, MemberInfo memberInfo);

    public static final native void GroupMemberInfoVec_reserve(long j, GroupMemberInfoVec groupMemberInfoVec, long j2);

    public static final native void GroupMemberInfoVec_set(long j, GroupMemberInfoVec groupMemberInfoVec, int i, long j2, MemberInfo memberInfo);

    public static final native long GroupMemberInfoVec_size(long j, GroupMemberInfoVec groupMemberInfoVec);

    public static final native long GroupMemberResultVec_capacity(long j, GroupMemberResultVec groupMemberResultVec);

    public static final native void GroupMemberResultVec_clear(long j, GroupMemberResultVec groupMemberResultVec);

    public static final native boolean GroupMemberResultVec_empty(long j, GroupMemberResultVec groupMemberResultVec);

    public static final native long GroupMemberResultVec_get(long j, GroupMemberResultVec groupMemberResultVec, int i);

    public static final native void GroupMemberResultVec_pushBack(long j, GroupMemberResultVec groupMemberResultVec, long j2, MemberResult memberResult);

    public static final native void GroupMemberResultVec_reserve(long j, GroupMemberResultVec groupMemberResultVec, long j2);

    public static final native void GroupMemberResultVec_set(long j, GroupMemberResultVec groupMemberResultVec, int i, long j2, MemberResult memberResult);

    public static final native long GroupMemberResultVec_size(long j, GroupMemberResultVec groupMemberResultVec);

    public static final native long GroupMsgSeqInfo_readSeq_get(long j, GroupMsgSeqInfo groupMsgSeqInfo);

    public static final native void GroupMsgSeqInfo_readSeq_set(long j, GroupMsgSeqInfo groupMsgSeqInfo, long j2);

    public static final native long GroupMsgSeqInfo_svrLatestSeq_get(long j, GroupMsgSeqInfo groupMsgSeqInfo);

    public static final native void GroupMsgSeqInfo_svrLatestSeq_set(long j, GroupMsgSeqInfo groupMsgSeqInfo, long j2);

    public static final native long GroupPendencyItemVec_capacity(long j, GroupPendencyItemVec groupPendencyItemVec);

    public static final native void GroupPendencyItemVec_clear(long j, GroupPendencyItemVec groupPendencyItemVec);

    public static final native boolean GroupPendencyItemVec_empty(long j, GroupPendencyItemVec groupPendencyItemVec);

    public static final native long GroupPendencyItemVec_get(long j, GroupPendencyItemVec groupPendencyItemVec, int i);

    public static final native void GroupPendencyItemVec_pushBack(long j, GroupPendencyItemVec groupPendencyItemVec, long j2, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItemVec_reserve(long j, GroupPendencyItemVec groupPendencyItemVec, long j2);

    public static final native void GroupPendencyItemVec_set(long j, GroupPendencyItemVec groupPendencyItemVec, int i, long j2, GroupPendencyItem groupPendencyItem);

    public static final native long GroupPendencyItemVec_size(long j, GroupPendencyItemVec groupPendencyItemVec);

    public static final native long GroupPendencyItem_add_time_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_add_time_set(long j, GroupPendencyItem groupPendencyItem, long j2);

    public static final native byte[] GroupPendencyItem_apply_invite_msg_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_apply_invite_msg_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native byte[] GroupPendencyItem_approval_msg_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_approval_msg_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native byte[] GroupPendencyItem_authentication_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_authentication_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native String GroupPendencyItem_from_id_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_from_id_set(long j, GroupPendencyItem groupPendencyItem, String str);

    public static final native byte[] GroupPendencyItem_from_user_defined_data_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_from_user_defined_data_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native String GroupPendencyItem_group_id_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_group_id_set(long j, GroupPendencyItem groupPendencyItem, String str);

    public static final native long GroupPendencyItem_handle_result_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_handle_result_set(long j, GroupPendencyItem groupPendencyItem, long j2);

    public static final native long GroupPendencyItem_handled_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_handled_set(long j, GroupPendencyItem groupPendencyItem, long j2);

    public static final native byte[] GroupPendencyItem_key_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_key_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native long GroupPendencyItem_pendency_type_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_pendency_type_set(long j, GroupPendencyItem groupPendencyItem, long j2);

    public static final native byte[] GroupPendencyItem_selfIdentifier_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_selfIdentifier_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native String GroupPendencyItem_to_id_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_to_id_set(long j, GroupPendencyItem groupPendencyItem, String str);

    public static final native byte[] GroupPendencyItem_to_user_defined_data_get(long j, GroupPendencyItem groupPendencyItem);

    public static final native void GroupPendencyItem_to_user_defined_data_set(long j, GroupPendencyItem groupPendencyItem, byte[] bArr);

    public static final native long GroupPendencyMeta_next_start_time_get(long j, GroupPendencyMeta groupPendencyMeta);

    public static final native void GroupPendencyMeta_next_start_time_set(long j, GroupPendencyMeta groupPendencyMeta, long j2);

    public static final native long GroupPendencyMeta_read_time_seq_get(long j, GroupPendencyMeta groupPendencyMeta);

    public static final native void GroupPendencyMeta_read_time_seq_set(long j, GroupPendencyMeta groupPendencyMeta, long j2);

    public static final native long GroupPendencyMeta_unread_num_get(long j, GroupPendencyMeta groupPendencyMeta);

    public static final native void GroupPendencyMeta_unread_num_set(long j, GroupPendencyMeta groupPendencyMeta, long j2);

    public static final native byte[] GroupReportElem_authentication_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_authentication_set(long j, GroupReportElem groupReportElem, byte[] bArr);

    public static final native String GroupReportElem_group_get(long j, GroupReportElem groupReportElem);

    public static final native String GroupReportElem_group_name_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_group_name_set(long j, GroupReportElem groupReportElem, String str);

    public static final native void GroupReportElem_group_set(long j, GroupReportElem groupReportElem, String str);

    public static final native byte[] GroupReportElem_msg_get(long j, GroupReportElem groupReportElem);

    public static final native long GroupReportElem_msg_key_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_msg_key_set(long j, GroupReportElem groupReportElem, long j2);

    public static final native void GroupReportElem_msg_set(long j, GroupReportElem groupReportElem, byte[] bArr);

    public static final native long GroupReportElem_operator_group_member_info_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_operator_group_member_info_set(long j, GroupReportElem groupReportElem, long j2, NewGroupMemberInfo newGroupMemberInfo);

    public static final native String GroupReportElem_operator_user_get(long j, GroupReportElem groupReportElem);

    public static final native long GroupReportElem_operator_user_info_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_operator_user_info_set(long j, GroupReportElem groupReportElem, long j2, FriendProfile friendProfile);

    public static final native void GroupReportElem_operator_user_set(long j, GroupReportElem groupReportElem, String str);

    public static final native byte[] GroupReportElem_platform_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_platform_set(long j, GroupReportElem groupReportElem, byte[] bArr);

    public static final native long GroupReportElem_type_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_type_set(long j, GroupReportElem groupReportElem, long j2);

    public static final native byte[] GroupReportElem_user_data_get(long j, GroupReportElem groupReportElem);

    public static final native void GroupReportElem_user_data_set(long j, GroupReportElem groupReportElem, byte[] bArr);

    public static final native long GroupSelfInfo_dwMsgFalg_get(long j, GroupSelfInfo groupSelfInfo);

    public static final native void GroupSelfInfo_dwMsgFalg_set(long j, GroupSelfInfo groupSelfInfo, long j2);

    public static final native long GroupSelfInfo_joinTime_get(long j, GroupSelfInfo groupSelfInfo);

    public static final native void GroupSelfInfo_joinTime_set(long j, GroupSelfInfo groupSelfInfo, long j2);

    public static final native long GroupSelfInfo_role_get(long j, GroupSelfInfo groupSelfInfo);

    public static final native void GroupSelfInfo_role_set(long j, GroupSelfInfo groupSelfInfo, long j2);

    public static final native long GroupSelfInfo_unreadNum_get(long j, GroupSelfInfo groupSelfInfo);

    public static final native void GroupSelfInfo_unreadNum_set(long j, GroupSelfInfo groupSelfInfo, long j2);

    public static final native long GroupSettings_groupInfoOpt_get(long j, GroupSettings groupSettings);

    public static final native void GroupSettings_groupInfoOpt_set(long j, GroupSettings groupSettings, long j2, UpdateInfoOpt updateInfoOpt);

    public static final native long GroupSettings_memberInfoOpt_get(long j, GroupSettings groupSettings);

    public static final native void GroupSettings_memberInfoOpt_set(long j, GroupSettings groupSettings, long j2, UpdateInfoOpt updateInfoOpt);

    public static final native boolean GroupSettings_storageEnabled_get(long j, GroupSettings groupSettings);

    public static final native void GroupSettings_storageEnabled_set(long j, GroupSettings groupSettings, boolean z);

    public static final native long GroupTipsElem_GroupInfoVec_capacity(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native void GroupTipsElem_GroupInfoVec_clear(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native boolean GroupTipsElem_GroupInfoVec_empty(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native long GroupTipsElem_GroupInfoVec_get(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec, int i);

    public static final native void GroupTipsElem_GroupInfoVec_pushBack(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec, long j2, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo);

    public static final native void GroupTipsElem_GroupInfoVec_reserve(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec, long j2);

    public static final native void GroupTipsElem_GroupInfoVec_set(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec, int i, long j2, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo);

    public static final native long GroupTipsElem_GroupInfoVec_size(long j, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native int GroupTipsElem_GroupInfo_type_get(long j, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo);

    public static final native void GroupTipsElem_GroupInfo_type_set(long j, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo, int i);

    public static final native byte[] GroupTipsElem_GroupInfo_value_get(long j, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo);

    public static final native void GroupTipsElem_GroupInfo_value_set(long j, GroupTipsElem_GroupInfo groupTipsElem_GroupInfo, byte[] bArr);

    public static final native long GroupTipsElem_MemberInfoVec_capacity(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec);

    public static final native void GroupTipsElem_MemberInfoVec_clear(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec);

    public static final native boolean GroupTipsElem_MemberInfoVec_empty(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec);

    public static final native long GroupTipsElem_MemberInfoVec_get(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec, int i);

    public static final native void GroupTipsElem_MemberInfoVec_pushBack(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec, long j2, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo);

    public static final native void GroupTipsElem_MemberInfoVec_reserve(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec, long j2);

    public static final native void GroupTipsElem_MemberInfoVec_set(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec, int i, long j2, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo);

    public static final native long GroupTipsElem_MemberInfoVec_size(long j, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec);

    public static final native String GroupTipsElem_MemberInfo_identifier_get(long j, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo);

    public static final native void GroupTipsElem_MemberInfo_identifier_set(long j, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo, String str);

    public static final native long GroupTipsElem_MemberInfo_shutup_time_get(long j, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo);

    public static final native void GroupTipsElem_MemberInfo_shutup_time_set(long j, GroupTipsElem_MemberInfo groupTipsElem_MemberInfo, long j2);

    public static final native long GroupTipsElem_changed_group_member_info_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_changed_group_member_info_set(long j, GroupTipsElem groupTipsElem, long j2);

    public static final native long GroupTipsElem_changed_user_info_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_changed_user_info_set(long j, GroupTipsElem groupTipsElem, long j2);

    public static final native long GroupTipsElem_group_change_list_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_group_change_list_set(long j, GroupTipsElem groupTipsElem, long j2, GroupTipsElem_GroupInfoVec groupTipsElem_GroupInfoVec);

    public static final native byte[] GroupTipsElem_group_id_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_group_id_set(long j, GroupTipsElem groupTipsElem, byte[] bArr);

    public static final native byte[] GroupTipsElem_group_name_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_group_name_set(long j, GroupTipsElem groupTipsElem, byte[] bArr);

    public static final native long GroupTipsElem_member_change_list_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_member_change_list_set(long j, GroupTipsElem groupTipsElem, long j2, GroupTipsElem_MemberInfoVec groupTipsElem_MemberInfoVec);

    public static final native long GroupTipsElem_member_num_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_member_num_set(long j, GroupTipsElem groupTipsElem, long j2);

    public static final native long GroupTipsElem_op_group_member_info_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_op_group_member_info_set(long j, GroupTipsElem groupTipsElem, long j2, NewGroupMemberInfo newGroupMemberInfo);

    public static final native String GroupTipsElem_op_user_get(long j, GroupTipsElem groupTipsElem);

    public static final native long GroupTipsElem_op_user_info_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_op_user_info_set(long j, GroupTipsElem groupTipsElem, long j2, FriendProfile friendProfile);

    public static final native void GroupTipsElem_op_user_set(long j, GroupTipsElem groupTipsElem, String str);

    public static final native byte[] GroupTipsElem_platform_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_platform_set(long j, GroupTipsElem groupTipsElem, byte[] bArr);

    public static final native long GroupTipsElem_time_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_time_set(long j, GroupTipsElem groupTipsElem, long j2);

    public static final native int GroupTipsElem_type_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_type_set(long j, GroupTipsElem groupTipsElem, int i);

    public static final native long GroupTipsElem_user_list_get(long j, GroupTipsElem groupTipsElem);

    public static final native void GroupTipsElem_user_list_set(long j, GroupTipsElem groupTipsElem, long j2, StrVec strVec);

    public static final native void IApplyDownloadFileCallback_change_ownership(IApplyDownloadFileCallback iApplyDownloadFileCallback, long j, boolean z);

    public static final native void IApplyDownloadFileCallback_director_connect(IApplyDownloadFileCallback iApplyDownloadFileCallback, long j, boolean z, boolean z2);

    public static final native void IApplyDownloadFileCallback_done(long j, IApplyDownloadFileCallback iApplyDownloadFileCallback, long j2, StrVec strVec);

    public static final native void IApplyDownloadFileCallback_doneSwigExplicitIApplyDownloadFileCallback(long j, IApplyDownloadFileCallback iApplyDownloadFileCallback, long j2, StrVec strVec);

    public static final native void IApplyDownloadFileCallback_fail(long j, IApplyDownloadFileCallback iApplyDownloadFileCallback, int i, String str);

    public static final native void IApplyDownloadFileCallback_failSwigExplicitIApplyDownloadFileCallback(long j, IApplyDownloadFileCallback iApplyDownloadFileCallback, int i, String str);

    public static final native void IAvInviteCallBack_change_ownership(IAvInviteCallBack iAvInviteCallBack, long j, boolean z);

    public static final native void IAvInviteCallBack_director_connect(IAvInviteCallBack iAvInviteCallBack, long j, boolean z, boolean z2);

    public static final native void IAvInviteCallBack_onAvInviteBuf(long j, IAvInviteCallBack iAvInviteCallBack, byte[] bArr);

    public static final native void IAvInviteCallBack_onAvInviteBufSwigExplicitIAvInviteCallBack(long j, IAvInviteCallBack iAvInviteCallBack, byte[] bArr);

    public static final native int IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_code_get(long j, ErrInfo errInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_code_set(long j, ErrInfo errInfo, int i);

    public static final native String IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_msg_get(long j, ErrInfo errInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_ErrInfo_err_msg_set(long j, ErrInfo errInfo, String str);

    public static final native String IBatchOprCallback_BatchOprDetailInfo_ErrInfo_id_get(long j, ErrInfo errInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_ErrInfo_id_set(long j, ErrInfo errInfo, String str);

    public static final native long IBatchOprCallback_BatchOprDetailInfo_errs_get(long j, BatchOprDetailInfo batchOprDetailInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_errs_set(long j, BatchOprDetailInfo batchOprDetailInfo, long j2, ErrInfoVec errInfoVec);

    public static final native long IBatchOprCallback_BatchOprDetailInfo_fail_num_get(long j, BatchOprDetailInfo batchOprDetailInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_fail_num_set(long j, BatchOprDetailInfo batchOprDetailInfo, long j2);

    public static final native long IBatchOprCallback_BatchOprDetailInfo_succ_num_get(long j, BatchOprDetailInfo batchOprDetailInfo);

    public static final native void IBatchOprCallback_BatchOprDetailInfo_succ_num_set(long j, BatchOprDetailInfo batchOprDetailInfo, long j2);

    public static final native void IBatchOprCallback_change_ownership(IBatchOprCallback iBatchOprCallback, long j, boolean z);

    public static final native void IBatchOprCallback_director_connect(IBatchOprCallback iBatchOprCallback, long j, boolean z, boolean z2);

    public static final native void IBatchOprCallback_done(long j, IBatchOprCallback iBatchOprCallback);

    public static final native void IBatchOprCallback_doneSwigExplicitIBatchOprCallback(long j, IBatchOprCallback iBatchOprCallback);

    public static final native void IBatchOprCallback_fail(long j, IBatchOprCallback iBatchOprCallback, int i, String str, long j2, BatchOprDetailInfo batchOprDetailInfo);

    public static final native void IBatchOprCallback_failSwigExplicitIBatchOprCallback(long j, IBatchOprCallback iBatchOprCallback, int i, String str, long j2, BatchOprDetailInfo batchOprDetailInfo);

    public static final native void IBatchOprCallback_setLogin(long j, IBatchOprCallback iBatchOprCallback, String str, boolean z);

    public static final native void IBatchOprCallback_setLoginSwigExplicitIBatchOprCallback(long j, IBatchOprCallback iBatchOprCallback, String str, boolean z);

    public static final native void ICreateGroupCallback_change_ownership(ICreateGroupCallback iCreateGroupCallback, long j, boolean z);

    public static final native void ICreateGroupCallback_director_connect(ICreateGroupCallback iCreateGroupCallback, long j, boolean z, boolean z2);

    public static final native void ICreateGroupCallback_done(long j, ICreateGroupCallback iCreateGroupCallback, String str);

    public static final native void ICreateGroupCallback_doneSwigExplicitICreateGroupCallback(long j, ICreateGroupCallback iCreateGroupCallback, String str);

    public static final native void ICreateGroupCallback_fail(long j, ICreateGroupCallback iCreateGroupCallback, int i, String str);

    public static final native void ICreateGroupCallback_failSwigExplicitICreateGroupCallback(long j, ICreateGroupCallback iCreateGroupCallback, int i, String str);

    public static final native void IDeleteLocalMsg_change_ownership(IDeleteLocalMsg iDeleteLocalMsg, long j, boolean z);

    public static final native void IDeleteLocalMsg_director_connect(IDeleteLocalMsg iDeleteLocalMsg, long j, boolean z, boolean z2);

    public static final native void IDeleteLocalMsg_done(long j, IDeleteLocalMsg iDeleteLocalMsg);

    public static final native void IDeleteLocalMsg_doneSwigExplicitIDeleteLocalMsg(long j, IDeleteLocalMsg iDeleteLocalMsg);

    public static final native void IDeleteLocalMsg_fail(long j, IDeleteLocalMsg iDeleteLocalMsg, int i, String str);

    public static final native void IDeleteLocalMsg_failSwigExplicitIDeleteLocalMsg(long j, IDeleteLocalMsg iDeleteLocalMsg, int i, String str);

    public static final native void IEnv_change_ownership(IEnv iEnv, long j, boolean z);

    public static final native boolean IEnv_createThread(long j, IEnv iEnv, long j2, ThreadEntry threadEntry);

    public static final native boolean IEnv_createThreadSwigExplicitIEnv(long j, IEnv iEnv, long j2, ThreadEntry threadEntry);

    public static final native void IEnv_director_connect(IEnv iEnv, long j, boolean z, boolean z2);

    public static final native boolean IEnv_httpRequest(long j, IEnv iEnv, String str, int i, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native boolean IEnv_httpRequestSwigExplicitIEnv(long j, IEnv iEnv, String str, int i, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native boolean IEnv_runOnMainThread(long j, IEnv iEnv, long j2, RunClosure runClosure);

    public static final native boolean IEnv_runOnMainThreadSwigExplicitIEnv(long j, IEnv iEnv, long j2, RunClosure runClosure);

    public static final native boolean IEnv_sSORequestSwigExplicitIEnv__SWIG_0(long j, IEnv iEnv, String str, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure, long j3);

    public static final native boolean IEnv_sSORequestSwigExplicitIEnv__SWIG_1(long j, IEnv iEnv, String str, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native boolean IEnv_sSORequest__SWIG_0(long j, IEnv iEnv, String str, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure, long j3);

    public static final native boolean IEnv_sSORequest__SWIG_1(long j, IEnv iEnv, String str, String str2, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native boolean IEnv_uploadLogFile(long j, IEnv iEnv, String str, long j2, UploadLogFileOpt uploadLogFileOpt);

    public static final native boolean IEnv_uploadLogFileSwigExplicitIEnv(long j, IEnv iEnv, String str, long j2, UploadLogFileOpt uploadLogFileOpt);

    public static final native void IFileTrans_change_ownership(IFileTrans iFileTrans, long j, boolean z);

    public static final native void IFileTrans_director_connect(IFileTrans iFileTrans, long j, boolean z, boolean z2);

    public static final native void IFileTrans_done(long j, IFileTrans iFileTrans, long j2, FileTransSuccParam fileTransSuccParam);

    public static final native void IFileTrans_doneSwigExplicitIFileTrans(long j, IFileTrans iFileTrans, long j2, FileTransSuccParam fileTransSuccParam);

    public static final native void IFileTrans_fail(long j, IFileTrans iFileTrans, int i, String str);

    public static final native void IFileTrans_failSwigExplicitIFileTrans(long j, IFileTrans iFileTrans, int i, String str);

    public static final native void IFriendGroupCallback_change_ownership(IFriendGroupCallback iFriendGroupCallback, long j, boolean z);

    public static final native void IFriendGroupCallback_director_connect(IFriendGroupCallback iFriendGroupCallback, long j, boolean z, boolean z2);

    public static final native void IFriendGroupCallback_done(long j, IFriendGroupCallback iFriendGroupCallback, long j2, FriendGroupVec friendGroupVec);

    public static final native void IFriendGroupCallback_doneSwigExplicitIFriendGroupCallback(long j, IFriendGroupCallback iFriendGroupCallback, long j2, FriendGroupVec friendGroupVec);

    public static final native void IFriendGroupCallback_fail(long j, IFriendGroupCallback iFriendGroupCallback, int i, String str);

    public static final native void IFriendGroupCallback_failSwigExplicitIFriendGroupCallback(long j, IFriendGroupCallback iFriendGroupCallback, int i, String str);

    public static final native void IFriendshipActionCallbackV2_change_ownership(IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j, boolean z);

    public static final native void IFriendshipActionCallbackV2_director_connect(IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j, boolean z, boolean z2);

    public static final native void IFriendshipActionCallbackV2_done(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j2, long j3, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipActionCallbackV2_doneSwigExplicitIFriendshipActionCallbackV2(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j2, long j3, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipActionCallbackV2_fail(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, int i, String str);

    public static final native void IFriendshipActionCallbackV2_failSwigExplicitIFriendshipActionCallbackV2(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, int i, String str);

    public static final native long IFriendshipActionCallbackV2_vecFriends_get(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2);

    public static final native void IFriendshipActionCallbackV2_vecFriends_set(long j, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipActionCallback_change_ownership(IFriendshipActionCallback iFriendshipActionCallback, long j, boolean z);

    public static final native void IFriendshipActionCallback_director_connect(IFriendshipActionCallback iFriendshipActionCallback, long j, boolean z, boolean z2);

    public static final native void IFriendshipActionCallback_done(long j, IFriendshipActionCallback iFriendshipActionCallback, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipActionCallback_doneSwigExplicitIFriendshipActionCallback(long j, IFriendshipActionCallback iFriendshipActionCallback, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipActionCallback_fail(long j, IFriendshipActionCallback iFriendshipActionCallback, int i, String str);

    public static final native void IFriendshipActionCallback_failSwigExplicitIFriendshipActionCallback(long j, IFriendshipActionCallback iFriendshipActionCallback, int i, String str);

    public static final native long IFriendshipActionCallback_vecFriends_get(long j, IFriendshipActionCallback iFriendshipActionCallback);

    public static final native void IFriendshipActionCallback_vecFriends_set(long j, IFriendshipActionCallback iFriendshipActionCallback, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipCallback_change_ownership(IFriendshipCallback iFriendshipCallback, long j, boolean z);

    public static final native void IFriendshipCallback_director_connect(IFriendshipCallback iFriendshipCallback, long j, boolean z, boolean z2);

    public static final native void IFriendshipCallback_done(long j, IFriendshipCallback iFriendshipCallback);

    public static final native void IFriendshipCallback_doneSwigExplicitIFriendshipCallback(long j, IFriendshipCallback iFriendshipCallback);

    public static final native void IFriendshipCallback_fail(long j, IFriendshipCallback iFriendshipCallback, int i, String str);

    public static final native void IFriendshipCallback_failSwigExplicitIFriendshipCallback(long j, IFriendshipCallback iFriendshipCallback, int i, String str);

    public static final native void IFriendshipGetFriendV2Callback_change_ownership(IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, long j, boolean z);

    public static final native void IFriendshipGetFriendV2Callback_director_connect(IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, long j, boolean z, boolean z2);

    public static final native void IFriendshipGetFriendV2Callback_done(long j, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, long j2, FriendMetaInfo friendMetaInfo, long j3, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipGetFriendV2Callback_doneSwigExplicitIFriendshipGetFriendV2Callback(long j, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, long j2, FriendMetaInfo friendMetaInfo, long j3, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipGetFriendV2Callback_fail(long j, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, int i, String str);

    public static final native void IFriendshipGetFriendV2Callback_failSwigExplicitIFriendshipGetFriendV2Callback(long j, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, int i, String str);

    public static final native void IFriendshipGetFutureCallback_change_ownership(IFriendshipGetFutureCallback iFriendshipGetFutureCallback, long j, boolean z);

    public static final native void IFriendshipGetFutureCallback_director_connect(IFriendshipGetFutureCallback iFriendshipGetFutureCallback, long j, boolean z, boolean z2);

    public static final native void IFriendshipGetFutureCallback_done(long j, IFriendshipGetFutureCallback iFriendshipGetFutureCallback, long j2, FutureFriendMeta futureFriendMeta, long j3, FriendFutureItemVec friendFutureItemVec);

    public static final native void IFriendshipGetFutureCallback_doneSwigExplicitIFriendshipGetFutureCallback(long j, IFriendshipGetFutureCallback iFriendshipGetFutureCallback, long j2, FutureFriendMeta futureFriendMeta, long j3, FriendFutureItemVec friendFutureItemVec);

    public static final native void IFriendshipGetFutureCallback_fail(long j, IFriendshipGetFutureCallback iFriendshipGetFutureCallback, int i, String str);

    public static final native void IFriendshipGetFutureCallback_failSwigExplicitIFriendshipGetFutureCallback(long j, IFriendshipGetFutureCallback iFriendshipGetFutureCallback, int i, String str);

    public static final native void IFriendshipPendencyCallback_change_ownership(IFriendshipPendencyCallback iFriendshipPendencyCallback, long j, boolean z);

    public static final native void IFriendshipPendencyCallback_director_connect(IFriendshipPendencyCallback iFriendshipPendencyCallback, long j, boolean z, boolean z2);

    public static final native void IFriendshipPendencyCallback_done(long j, IFriendshipPendencyCallback iFriendshipPendencyCallback, long j2, FriendPendencyMeta friendPendencyMeta, long j3, FriendPendencyItemVec friendPendencyItemVec);

    public static final native void IFriendshipPendencyCallback_doneSwigExplicitIFriendshipPendencyCallback(long j, IFriendshipPendencyCallback iFriendshipPendencyCallback, long j2, FriendPendencyMeta friendPendencyMeta, long j3, FriendPendencyItemVec friendPendencyItemVec);

    public static final native void IFriendshipPendencyCallback_fail(long j, IFriendshipPendencyCallback iFriendshipPendencyCallback, int i, String str);

    public static final native void IFriendshipPendencyCallback_failSwigExplicitIFriendshipPendencyCallback(long j, IFriendshipPendencyCallback iFriendshipPendencyCallback, int i, String str);

    public static final native void IFriendshipProxyActionCallback_done(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback, long j2, FriendProfileVec friendProfileVec, long j3, long j4);

    public static final native void IFriendshipProxyActionCallback_fail(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback, int i, String str);

    public static final native boolean IFriendshipProxyActionCallback_getAllFlag(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback);

    public static final native void IFriendshipProxyActionCallback_setAllFlag(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback, boolean z);

    public static final native long IFriendshipProxyActionCallback_vecFriends_get(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback);

    public static final native void IFriendshipProxyActionCallback_vecFriends_set(long j, IFriendshipProxyActionCallback iFriendshipProxyActionCallback, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipProxyListener_change_ownership(IFriendshipProxyListener iFriendshipProxyListener, long j, boolean z);

    public static final native void IFriendshipProxyListener_director_connect(IFriendshipProxyListener iFriendshipProxyListener, long j, boolean z, boolean z2);

    public static final native void IFriendshipProxyListener_onAddFriendNotify(long j, IFriendshipProxyListener iFriendshipProxyListener, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipProxyListener_onAddFriendReq(long j, IFriendshipProxyListener iFriendshipProxyListener, long j2, AddFriendReqVec addFriendReqVec);

    public static final native void IFriendshipProxyListener_onDeleteFriendNotify(long j, IFriendshipProxyListener iFriendshipProxyListener, long j2, StrVec strVec);

    public static final native void IFriendshipProxyListener_onFriendProfileUpdate(long j, IFriendshipProxyListener iFriendshipProxyListener, long j2, FriendProfileVec friendProfileVec);

    public static final native void IFriendshipProxyListener_onProxyStatusChange(long j, IFriendshipProxyListener iFriendshipProxyListener, int i);

    public static final native void IGetMsgs_change_ownership(IGetMsgs iGetMsgs, long j, boolean z);

    public static final native void IGetMsgs_director_connect(IGetMsgs iGetMsgs, long j, boolean z, boolean z2);

    public static final native void IGetMsgs_done(long j, IGetMsgs iGetMsgs, long j2, MsgVec msgVec);

    public static final native void IGetMsgs_doneSwigExplicitIGetMsgs(long j, IGetMsgs iGetMsgs, long j2, MsgVec msgVec);

    public static final native void IGetMsgs_fail(long j, IGetMsgs iGetMsgs, int i, String str);

    public static final native void IGetMsgs_failSwigExplicitIGetMsgs(long j, IGetMsgs iGetMsgs, int i, String str);

    public static final native void IGroupAssistantCallback_change_ownership(IGroupAssistantCallback iGroupAssistantCallback, long j, boolean z);

    public static final native void IGroupAssistantCallback_director_connect(IGroupAssistantCallback iGroupAssistantCallback, long j, boolean z, boolean z2);

    public static final native void IGroupAssistantCallback_onGroupAdd(long j, IGroupAssistantCallback iGroupAssistantCallback, long j2, GroupCacheInfo groupCacheInfo);

    public static final native void IGroupAssistantCallback_onGroupAddSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, long j2, GroupCacheInfo groupCacheInfo);

    public static final native void IGroupAssistantCallback_onGroupDelete(long j, IGroupAssistantCallback iGroupAssistantCallback, String str);

    public static final native void IGroupAssistantCallback_onGroupDeleteSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, String str);

    public static final native void IGroupAssistantCallback_onGroupUpdate(long j, IGroupAssistantCallback iGroupAssistantCallback, long j2, GroupCacheInfo groupCacheInfo);

    public static final native void IGroupAssistantCallback_onGroupUpdateSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, long j2, GroupCacheInfo groupCacheInfo);

    public static final native void IGroupAssistantCallback_onMemberJoin(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupAssistantCallback_onMemberJoinSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupAssistantCallback_onMemberQuit(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, StrVec strVec);

    public static final native void IGroupAssistantCallback_onMemberQuitSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, StrVec strVec);

    public static final native void IGroupAssistantCallback_onMemberUpdate(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupAssistantCallback_onMemberUpdateSwigExplicitIGroupAssistantCallback(long j, IGroupAssistantCallback iGroupAssistantCallback, String str, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupGetPendencyCallback_change_ownership(IGroupGetPendencyCallback iGroupGetPendencyCallback, long j, boolean z);

    public static final native void IGroupGetPendencyCallback_director_connect(IGroupGetPendencyCallback iGroupGetPendencyCallback, long j, boolean z, boolean z2);

    public static final native void IGroupGetPendencyCallback_done(long j, IGroupGetPendencyCallback iGroupGetPendencyCallback, long j2, GroupPendencyMeta groupPendencyMeta, long j3, GroupPendencyItemVec groupPendencyItemVec);

    public static final native void IGroupGetPendencyCallback_doneSwigExplicitIGroupGetPendencyCallback(long j, IGroupGetPendencyCallback iGroupGetPendencyCallback, long j2, GroupPendencyMeta groupPendencyMeta, long j3, GroupPendencyItemVec groupPendencyItemVec);

    public static final native void IGroupGetPendencyCallback_fail(long j, IGroupGetPendencyCallback iGroupGetPendencyCallback, int i, String str);

    public static final native void IGroupGetPendencyCallback_failSwigExplicitIGroupGetPendencyCallback(long j, IGroupGetPendencyCallback iGroupGetPendencyCallback, int i, String str);

    public static final native void IGroupInfoListCallbackV2_change_ownership(IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, long j, boolean z);

    public static final native void IGroupInfoListCallbackV2_director_connect(IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, long j, boolean z, boolean z2);

    public static final native void IGroupInfoListCallbackV2_done(long j, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, long j2, long j3, GroupDetailInfoVec groupDetailInfoVec);

    public static final native void IGroupInfoListCallbackV2_doneSwigExplicitIGroupInfoListCallbackV2(long j, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, long j2, long j3, GroupDetailInfoVec groupDetailInfoVec);

    public static final native void IGroupInfoListCallbackV2_fail(long j, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, int i, String str);

    public static final native void IGroupInfoListCallbackV2_failSwigExplicitIGroupInfoListCallbackV2(long j, IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, int i, String str);

    public static final native void IGroupInfoListCallback_change_ownership(IGroupInfoListCallback iGroupInfoListCallback, long j, boolean z);

    public static final native void IGroupInfoListCallback_director_connect(IGroupInfoListCallback iGroupInfoListCallback, long j, boolean z, boolean z2);

    public static final native void IGroupInfoListCallback_done(long j, IGroupInfoListCallback iGroupInfoListCallback, long j2, GroupDetailInfoVec groupDetailInfoVec);

    public static final native void IGroupInfoListCallback_doneSwigExplicitIGroupInfoListCallback(long j, IGroupInfoListCallback iGroupInfoListCallback, long j2, GroupDetailInfoVec groupDetailInfoVec);

    public static final native void IGroupInfoListCallback_fail(long j, IGroupInfoListCallback iGroupInfoListCallback, int i, String str);

    public static final native void IGroupInfoListCallback_failSwigExplicitIGroupInfoListCallback(long j, IGroupInfoListCallback iGroupInfoListCallback, int i, String str);

    public static final native void IGroupListCallback_change_ownership(IGroupListCallback iGroupListCallback, long j, boolean z);

    public static final native void IGroupListCallback_director_connect(IGroupListCallback iGroupListCallback, long j, boolean z, boolean z2);

    public static final native void IGroupListCallback_done(long j, IGroupListCallback iGroupListCallback, long j2, GroupBaseInfoVec groupBaseInfoVec);

    public static final native void IGroupListCallback_doneSwigExplicitIGroupListCallback(long j, IGroupListCallback iGroupListCallback, long j2, GroupBaseInfoVec groupBaseInfoVec);

    public static final native void IGroupListCallback_fail(long j, IGroupListCallback iGroupListCallback, int i, String str);

    public static final native void IGroupListCallback_failSwigExplicitIGroupListCallback(long j, IGroupListCallback iGroupListCallback, int i, String str);

    public static final native void IGroupMemberCallbackV2_change_ownership(IGroupMemberCallbackV2 iGroupMemberCallbackV2, long j, boolean z);

    public static final native void IGroupMemberCallbackV2_director_connect(IGroupMemberCallbackV2 iGroupMemberCallbackV2, long j, boolean z, boolean z2);

    public static final native void IGroupMemberCallbackV2_done(long j, IGroupMemberCallbackV2 iGroupMemberCallbackV2, long j2, long j3, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupMemberCallbackV2_doneSwigExplicitIGroupMemberCallbackV2(long j, IGroupMemberCallbackV2 iGroupMemberCallbackV2, long j2, long j3, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupMemberCallbackV2_fail(long j, IGroupMemberCallbackV2 iGroupMemberCallbackV2, int i, String str);

    public static final native void IGroupMemberCallbackV2_failSwigExplicitIGroupMemberCallbackV2(long j, IGroupMemberCallbackV2 iGroupMemberCallbackV2, int i, String str);

    public static final native void IGroupMemberCallback_change_ownership(IGroupMemberCallback iGroupMemberCallback, long j, boolean z);

    public static final native void IGroupMemberCallback_director_connect(IGroupMemberCallback iGroupMemberCallback, long j, boolean z, boolean z2);

    public static final native void IGroupMemberCallback_done(long j, IGroupMemberCallback iGroupMemberCallback, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupMemberCallback_doneSwigExplicitIGroupMemberCallback(long j, IGroupMemberCallback iGroupMemberCallback, long j2, GroupMemberInfoVec groupMemberInfoVec);

    public static final native void IGroupMemberCallback_fail(long j, IGroupMemberCallback iGroupMemberCallback, int i, String str);

    public static final native void IGroupMemberCallback_failSwigExplicitIGroupMemberCallback(long j, IGroupMemberCallback iGroupMemberCallback, int i, String str);

    public static final native void IGroupMemberResultCallback_change_ownership(IGroupMemberResultCallback iGroupMemberResultCallback, long j, boolean z);

    public static final native void IGroupMemberResultCallback_director_connect(IGroupMemberResultCallback iGroupMemberResultCallback, long j, boolean z, boolean z2);

    public static final native void IGroupMemberResultCallback_done(long j, IGroupMemberResultCallback iGroupMemberResultCallback, long j2, GroupMemberResultVec groupMemberResultVec);

    public static final native void IGroupMemberResultCallback_doneSwigExplicitIGroupMemberResultCallback(long j, IGroupMemberResultCallback iGroupMemberResultCallback, long j2, GroupMemberResultVec groupMemberResultVec);

    public static final native void IGroupMemberResultCallback_fail(long j, IGroupMemberResultCallback iGroupMemberResultCallback, int i, String str);

    public static final native void IGroupMemberResultCallback_failSwigExplicitIGroupMemberResultCallback(long j, IGroupMemberResultCallback iGroupMemberResultCallback, int i, String str);

    public static final native void IGroupNotifyCallback_change_ownership(IGroupNotifyCallback iGroupNotifyCallback, long j, boolean z);

    public static final native void IGroupNotifyCallback_director_connect(IGroupNotifyCallback iGroupNotifyCallback, long j, boolean z, boolean z2);

    public static final native void IGroupNotifyCallback_done(long j, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native void IGroupNotifyCallback_doneSwigExplicitIGroupNotifyCallback(long j, IGroupNotifyCallback iGroupNotifyCallback);

    public static final native void IGroupNotifyCallback_fail(long j, IGroupNotifyCallback iGroupNotifyCallback, int i, String str);

    public static final native void IGroupNotifyCallback_failSwigExplicitIGroupNotifyCallback(long j, IGroupNotifyCallback iGroupNotifyCallback, int i, String str);

    public static final native void IGroupTipsEventCallback_change_ownership(IGroupTipsEventCallback iGroupTipsEventCallback, long j, boolean z);

    public static final native void IGroupTipsEventCallback_director_connect(IGroupTipsEventCallback iGroupTipsEventCallback, long j, boolean z, boolean z2);

    public static final native void IGroupTipsEventCallback_onGroupTipsEvent(long j, IGroupTipsEventCallback iGroupTipsEventCallback, long j2, GroupTipsElem groupTipsElem);

    public static final native void IGroupTipsEventCallback_onGroupTipsEventSwigExplicitIGroupTipsEventCallback(long j, IGroupTipsEventCallback iGroupTipsEventCallback, long j2, GroupTipsElem groupTipsElem);

    public static final native void IGroupUpdateCallback_change_ownership(IGroupUpdateCallback iGroupUpdateCallback, long j, boolean z);

    public static final native void IGroupUpdateCallback_director_connect(IGroupUpdateCallback iGroupUpdateCallback, long j, boolean z, boolean z2);

    public static final native void IGroupUpdateCallback_onMembersUpdate(long j, IGroupUpdateCallback iGroupUpdateCallback, long j2, GroupTipsElem groupTipsElem);

    public static final native void IGroupUpdateCallback_onMembersUpdateSwigExplicitIGroupUpdateCallback(long j, IGroupUpdateCallback iGroupUpdateCallback, long j2, GroupTipsElem groupTipsElem);

    public static final native void IImageUploadCallback_change_ownership(IImageUploadCallback iImageUploadCallback, long j, boolean z);

    public static final native void IImageUploadCallback_director_connect(IImageUploadCallback iImageUploadCallback, long j, boolean z, boolean z2);

    public static final native void IImageUploadCallback_done(long j, IImageUploadCallback iImageUploadCallback, long j2, ImageElem imageElem);

    public static final native void IImageUploadCallback_doneSwigExplicitIImageUploadCallback(long j, IImageUploadCallback iImageUploadCallback, long j2, ImageElem imageElem);

    public static final native void IImageUploadCallback_fail(long j, IImageUploadCallback iImageUploadCallback, int i, String str);

    public static final native void IImageUploadCallback_failSwigExplicitIImageUploadCallback(long j, IImageUploadCallback iImageUploadCallback, int i, String str);

    public static final native void IInit_change_ownership(IInit iInit, long j, boolean z);

    public static final native void IInit_director_connect(IInit iInit, long j, boolean z, boolean z2);

    public static final native void IInit_done(long j, IInit iInit);

    public static final native void IInit_doneSwigExplicitIInit(long j, IInit iInit);

    public static final native void IInit_fail(long j, IInit iInit, int i, String str);

    public static final native void IInit_failSwigExplicitIInit(long j, IInit iInit, int i, String str);

    public static final native void ILogMsgCallback_change_ownership(ILogMsgCallback iLogMsgCallback, long j, boolean z);

    public static final native void ILogMsgCallback_director_connect(ILogMsgCallback iLogMsgCallback, long j, boolean z, boolean z2);

    public static final native void ILogMsgCallback_onLogMsg(long j, ILogMsgCallback iLogMsgCallback, String str, int i, String str2);

    public static final native void ILogMsgCallback_onLogMsgSwigExplicitILogMsgCallback(long j, ILogMsgCallback iLogMsgCallback, String str, int i, String str2);

    public static final native String IMCoreUser_a2(long j, IMCoreUser iMCoreUser);

    public static final native int IMCoreUser_applyDownloadFile(long j, IMCoreUser iMCoreUser, long j2, ApplyDownloadFileReq applyDownloadFileReq, long j3, IApplyDownloadFileCallback iApplyDownloadFileCallback);

    public static final native int IMCoreUser_cancelAllPicupTask(long j, IMCoreUser iMCoreUser);

    public static final native void IMCoreUser_cancelTask(long j, IMCoreUser iMCoreUser, long j2);

    public static final native void IMCoreUser_clearCookie(long j, IMCoreUser iMCoreUser);

    public static final native int IMCoreUser_compressPic(long j, IMCoreUser iMCoreUser, String str, String str2, int i);

    public static final native boolean IMCoreUser_deleteGroupMsgs(long j, IMCoreUser iMCoreUser, String str, long j2, long j3);

    public static final native boolean IMCoreUser_deleteSession(long j, IMCoreUser iMCoreUser, int i, String str);

    public static final native boolean IMCoreUser_deleteSessionAndMsgs(long j, IMCoreUser iMCoreUser, int i, String str);

    public static final native boolean IMCoreUser_fake(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getAvInviteCallBack(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getFileTranser(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getFriendShipMgr(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getFriendShipPrxy(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getGroupAssistant(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getGroupMgr(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getGroupTipsEventCallback(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getGroupUpdateCallback(long j, IMCoreUser iMCoreUser);

    public static final native int IMCoreUser_getImageUploadProgrss(long j, IMCoreUser iMCoreUser, long j2);

    public static final native long IMCoreUser_getSelfProfile(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getSessionList(long j, IMCoreUser iMCoreUser);

    public static final native long IMCoreUser_getSession__SWIG_0(long j, IMCoreUser iMCoreUser, long j2);

    public static final native long IMCoreUser_getSession__SWIG_1(long j, IMCoreUser iMCoreUser, int i, String str);

    public static final native long IMCoreUser_getStatusMgr(long j, IMCoreUser iMCoreUser);

    public static final native boolean IMCoreUser_httpRequest(long j, IMCoreUser iMCoreUser, int i, String str, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native String IMCoreUser_ip(long j, IMCoreUser iMCoreUser);

    public static final native void IMCoreUser_loginSyncCache(long j, IMCoreUser iMCoreUser);

    public static final native void IMCoreUser_loginSyncMsg(long j, IMCoreUser iMCoreUser);

    public static final native int IMCoreUser_manualPush(long j, IMCoreUser iMCoreUser, byte[] bArr);

    public static final native long IMCoreUser_newSession(long j, IMCoreUser iMCoreUser, int i, String str);

    public static final native boolean IMCoreUser_runOnIOThread(long j, IMCoreUser iMCoreUser, long j2);

    public static final native boolean IMCoreUser_runOnMainThread(long j, IMCoreUser iMCoreUser, long j2);

    public static final native boolean IMCoreUser_runOnTaskThread(long j, IMCoreUser iMCoreUser, long j2);

    public static final native boolean IMCoreUser_sSORequest__SWIG_0(long j, IMCoreUser iMCoreUser, String str, byte[] bArr, long j2, EnvRequestClosure envRequestClosure, long j3);

    public static final native boolean IMCoreUser_sSORequest__SWIG_1(long j, IMCoreUser iMCoreUser, String str, byte[] bArr, long j2, EnvRequestClosure envRequestClosure);

    public static final native void IMCoreUser_saveSelfProfile(long j, IMCoreUser iMCoreUser, long j2, FriendProfile friendProfile);

    public static final native long IMCoreUser_sdkAppId(long j, IMCoreUser iMCoreUser);

    public static final native void IMCoreUser_sendMsgToMultiUsers(long j, IMCoreUser iMCoreUser, long j2, StrVec strVec, long j3, Msg msg, long j4, IBatchOprCallback iBatchOprCallback);

    public static final native long IMCoreUser_sessionCount(long j, IMCoreUser iMCoreUser);

    public static final native void IMCoreUser_setA2(long j, IMCoreUser iMCoreUser, String str);

    public static final native void IMCoreUser_setAvInviteCallBack(long j, IMCoreUser iMCoreUser, long j2, IAvInviteCallBack iAvInviteCallBack);

    public static final native void IMCoreUser_setGroupTipsEventCallback(long j, IMCoreUser iMCoreUser, long j2, IGroupTipsEventCallback iGroupTipsEventCallback);

    public static final native void IMCoreUser_setGroupUpdateCallback(long j, IMCoreUser iMCoreUser, long j2, IGroupUpdateCallback iGroupUpdateCallback);

    public static final native void IMCoreUser_setIp(long j, IMCoreUser iMCoreUser, String str);

    public static final native void IMCoreUser_setSdkAppId(long j, IMCoreUser iMCoreUser, long j2);

    public static final native long IMCoreUser_submitUploadTask__SWIG_0(long j, IMCoreUser iMCoreUser, String str, long j2, IImageUploadCallback iImageUploadCallback, int i);

    public static final native long IMCoreUser_submitUploadTask__SWIG_1(long j, IMCoreUser iMCoreUser, String str, long j2, IImageUploadCallback iImageUploadCallback);

    public static final native void IMCore_addIgnoreStoreSession(long j, IMCore iMCore, int i, String str);

    public static final native void IMCore_clearLog__SWIG_0(long j, IMCore iMCore, String str, long j2, String str2, long j3, int i);

    public static final native void IMCore_clearLog__SWIG_1(long j, IMCore iMCore, String str, long j2, String str2, long j3);

    public static final native void IMCore_clearLog__SWIG_2(long j, IMCore iMCore, String str, long j2, String str2);

    public static final native void IMCore_clearLog__SWIG_3(long j, IMCore iMCore, String str, long j2);

    public static final native long IMCore_get();

    public static final native long IMCore_getContext(long j, IMCore iMCore);

    public static final native long IMCore_getTime(long j, IMCore iMCore);

    public static final native long IMCore_getUser(long j, IMCore iMCore, String str);

    public static final native String IMCore_getVersion(long j, IMCore iMCore, int i);

    public static final native boolean IMCore_initOpenIM(long j, IMCore iMCore, String str, String str2, long j2, IEnv iEnv, String str3, String str4);

    public static final native int IMCore_initUser(long j, IMCore iMCore, int i, String str, String str2, String str3, String str4, byte[] bArr, String str5, String str6, long j2, UserConfig userConfig, long j3, IInit iInit);

    public static final native void IMCore_lOGGERINIT(long j, IMCore iMCore, String str, String str2, boolean z, long j2, ILogMsgCallback iLogMsgCallback);

    public static final native void IMCore_lOGGERLOG(long j, IMCore iMCore, int i, String str, int i2, String str2, String str3, String str4);

    public static final native void IMCore_lOGGERSETLOGCBLEVEL(long j, IMCore iMCore, String str);

    public static final native void IMCore_qrReportEvent(long j, IMCore iMCore, long j2, SdkReportItem sdkReportItem);

    public static final native void IMCore_setContext(long j, IMCore iMCore, long j2, Context context);

    public static final native int IMCore_unInitUser(long j, IMCore iMCore, String str);

    public static final native boolean IMCore_uploadLogFile(long j, IMCore iMCore, String str, long j2, UploadLogFileOpt uploadLogFileOpt);

    public static final native void INotify_change_ownership(INotify iNotify, long j, boolean z);

    public static final native void INotify_director_connect(INotify iNotify, long j, boolean z, boolean z2);

    public static final native void INotify_onMsgEvent(long j, INotify iNotify, long j2, MsgVec msgVec);

    public static final native void INotify_onMsgEventSwigExplicitINotify(long j, INotify iNotify, long j2, MsgVec msgVec);

    public static final native void INotify_onMsgUpdate(long j, INotify iNotify, long j2, MsgVec msgVec);

    public static final native void INotify_onMsgUpdateSwigExplicitINotify(long j, INotify iNotify, long j2, MsgVec msgVec);

    public static final native void INotify_onRecvMsgReceipt(long j, INotify iNotify, long j2, MsgReceiptVec msgReceiptVec);

    public static final native void INotify_onRecvMsgReceiptSwigExplicitINotify(long j, INotify iNotify, long j2, MsgReceiptVec msgReceiptVec);

    public static final native void INotify_onRefresh(long j, INotify iNotify);

    public static final native void INotify_onRefreshConversation(long j, INotify iNotify, long j2, SessionUUIDVec sessionUUIDVec);

    public static final native void INotify_onRefreshConversationSwigExplicitINotify(long j, INotify iNotify, long j2, SessionUUIDVec sessionUUIDVec);

    public static final native void INotify_onRefreshSwigExplicitINotify(long j, INotify iNotify);

    public static final native void INotify_onUploadProgress(long j, INotify iNotify, long j2, Msg msg, int i, int i2, int i3);

    public static final native void INotify_onUploadProgressSwigExplicitINotify(long j, INotify iNotify, long j2, Msg msg, int i, int i2, int i3);

    public static final native void INotify_onUserStatusChanged(long j, INotify iNotify, long j2, UserStatus userStatus);

    public static final native void INotify_onUserStatusChangedSwigExplicitINotify(long j, INotify iNotify, long j2, UserStatus userStatus);

    public static final native void ISendMsg_change_ownership(ISendMsg iSendMsg, long j, boolean z);

    public static final native void ISendMsg_director_connect(ISendMsg iSendMsg, long j, boolean z, boolean z2);

    public static final native void ISendMsg_done(long j, ISendMsg iSendMsg);

    public static final native void ISendMsg_doneSwigExplicitISendMsg(long j, ISendMsg iSendMsg);

    public static final native void ISendMsg_fail(long j, ISendMsg iSendMsg, int i, String str);

    public static final native void ISendMsg_failSwigExplicitISendMsg(long j, ISendMsg iSendMsg, int i, String str);

    public static final native void ISetReadMsg_change_ownership(ISetReadMsg iSetReadMsg, long j, boolean z);

    public static final native void ISetReadMsg_director_connect(ISetReadMsg iSetReadMsg, long j, boolean z, boolean z2);

    public static final native void ISetReadMsg_done(long j, ISetReadMsg iSetReadMsg);

    public static final native void ISetReadMsg_doneSwigExplicitISetReadMsg(long j, ISetReadMsg iSetReadMsg);

    public static final native void ISetReadMsg_fail(long j, ISetReadMsg iSetReadMsg, int i, String str);

    public static final native void ISetReadMsg_failSwigExplicitISetReadMsg(long j, ISetReadMsg iSetReadMsg, int i, String str);

    public static final native void IStatusCallback_change_ownership(IStatusCallback iStatusCallback, long j, boolean z);

    public static final native void IStatusCallback_director_connect(IStatusCallback iStatusCallback, long j, boolean z, boolean z2);

    public static final native void IStatusCallback_done(long j, IStatusCallback iStatusCallback, long j2, UserStatusVec userStatusVec);

    public static final native void IStatusCallback_doneSwigExplicitIStatusCallback(long j, IStatusCallback iStatusCallback, long j2, UserStatusVec userStatusVec);

    public static final native void IStatusCallback_fail(long j, IStatusCallback iStatusCallback, int i, String str);

    public static final native void IStatusCallback_failSwigExplicitIStatusCallback(long j, IStatusCallback iStatusCallback, int i, String str);

    public static final native long IStatusCallback_vecUserStatus_get(long j, IStatusCallback iStatusCallback);

    public static final native void IStatusCallback_vecUserStatus_set(long j, IStatusCallback iStatusCallback, long j2, UserStatusVec userStatusVec);

    public static final native void IStatusSetUserDefCallback_change_ownership(IStatusSetUserDefCallback iStatusSetUserDefCallback, long j, boolean z);

    public static final native void IStatusSetUserDefCallback_director_connect(IStatusSetUserDefCallback iStatusSetUserDefCallback, long j, boolean z, boolean z2);

    public static final native void IStatusSetUserDefCallback_done(long j, IStatusSetUserDefCallback iStatusSetUserDefCallback);

    public static final native void IStatusSetUserDefCallback_doneSwigExplicitIStatusSetUserDefCallback(long j, IStatusSetUserDefCallback iStatusSetUserDefCallback);

    public static final native void IStatusSetUserDefCallback_fail(long j, IStatusSetUserDefCallback iStatusSetUserDefCallback, int i, String str);

    public static final native void IStatusSetUserDefCallback_failSwigExplicitIStatusSetUserDefCallback(long j, IStatusSetUserDefCallback iStatusSetUserDefCallback, int i, String str);

    public static final native String ImageElem_fileid_get(long j, ImageElem imageElem);

    public static final native void ImageElem_fileid_set(long j, ImageElem imageElem, String str);

    public static final native int ImageElem_format_get(long j, ImageElem imageElem);

    public static final native void ImageElem_format_set(long j, ImageElem imageElem, int i);

    public static final native String ImageElem_large_url_get(long j, ImageElem imageElem);

    public static final native void ImageElem_large_url_set(long j, ImageElem imageElem, String str);

    public static final native int ImageElem_level_get(long j, ImageElem imageElem);

    public static final native void ImageElem_level_set(long j, ImageElem imageElem, int i);

    public static final native String ImageElem_orig_url_get(long j, ImageElem imageElem);

    public static final native void ImageElem_orig_url_set(long j, ImageElem imageElem, String str);

    public static final native int ImageElem_pic_height_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_height_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_large_height_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_large_height_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_large_size_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_large_size_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_large_width_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_large_width_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_size_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_size_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_thumb_height_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_thumb_height_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_thumb_size_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_thumb_size_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_thumb_width_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_thumb_width_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_pic_width_get(long j, ImageElem imageElem);

    public static final native void ImageElem_pic_width_set(long j, ImageElem imageElem, int i);

    public static final native int ImageElem_taskid_get(long j, ImageElem imageElem);

    public static final native void ImageElem_taskid_set(long j, ImageElem imageElem, int i);

    public static final native String ImageElem_thumb_url_get(long j, ImageElem imageElem);

    public static final native void ImageElem_thumb_url_set(long j, ImageElem imageElem, String str);

    public static final native long InstStatusVec_capacity(long j, InstStatusVec instStatusVec);

    public static final native void InstStatusVec_clear(long j, InstStatusVec instStatusVec);

    public static final native boolean InstStatusVec_empty(long j, InstStatusVec instStatusVec);

    public static final native long InstStatusVec_get(long j, InstStatusVec instStatusVec, int i);

    public static final native void InstStatusVec_pushBack(long j, InstStatusVec instStatusVec, long j2, InstStatus instStatus);

    public static final native void InstStatusVec_reserve(long j, InstStatusVec instStatusVec, long j2);

    public static final native void InstStatusVec_set(long j, InstStatusVec instStatusVec, int i, long j2, InstStatus instStatus);

    public static final native long InstStatusVec_size(long j, InstStatusVec instStatusVec);

    public static final native long InstStatus_ddwClientType_get(long j, InstStatus instStatus);

    public static final native void InstStatus_ddwClientType_set(long j, InstStatus instStatus, long j2);

    public static final native byte[] InstStatus_sUserDefine_get(long j, InstStatus instStatus);

    public static final native void InstStatus_sUserDefine_set(long j, InstStatus instStatus, byte[] bArr);

    public static final native byte[] LocationElem_desc_get(long j, LocationElem locationElem);

    public static final native void LocationElem_desc_set(long j, LocationElem locationElem, byte[] bArr);

    public static final native double LocationElem_latitude_get(long j, LocationElem locationElem);

    public static final native void LocationElem_latitude_set(long j, LocationElem locationElem, double d);

    public static final native double LocationElem_longitude_get(long j, LocationElem locationElem);

    public static final native void LocationElem_longitude_set(long j, LocationElem locationElem, double d);

    public static final native void MapKeyFetcher_fetchMapKeys(long j, MapKeyFetcher mapKeyFetcher, long j2, BytesMap bytesMap);

    public static final native byte[] MapKeyFetcher_getKey(long j, MapKeyFetcher mapKeyFetcher, int i);

    public static final native byte[] MapKeyFetcher_getValue(long j, MapKeyFetcher mapKeyFetcher, long j2, BytesMap bytesMap, int i);

    public static final native long MapKeyFetcher_keys_get(long j, MapKeyFetcher mapKeyFetcher);

    public static final native void MapKeyFetcher_keys_set(long j, MapKeyFetcher mapKeyFetcher, long j2, BytesVec bytesVec);

    public static final native long MemberInfo_custom_info_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_custom_info_set(long j, MemberInfo memberInfo, long j2, BytesMap bytesMap);

    public static final native long MemberInfo_join_time_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_join_time_set(long j, MemberInfo memberInfo, long j2);

    public static final native String MemberInfo_member_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_member_set(long j, MemberInfo memberInfo, String str);

    public static final native long MemberInfo_msg_flag_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_msg_flag_set(long j, MemberInfo memberInfo, long j2);

    public static final native long MemberInfo_msg_seq_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_msg_seq_set(long j, MemberInfo memberInfo, long j2);

    public static final native byte[] MemberInfo_name_card_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_name_card_set(long j, MemberInfo memberInfo, byte[] bArr);

    public static final native long MemberInfo_role_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_role_set(long j, MemberInfo memberInfo, long j2);

    public static final native long MemberInfo_shutup_time_get(long j, MemberInfo memberInfo);

    public static final native void MemberInfo_shutup_time_set(long j, MemberInfo memberInfo, long j2);

    public static final native long MemberResult_status_get(long j, MemberResult memberResult);

    public static final native void MemberResult_status_set(long j, MemberResult memberResult, long j2);

    public static final native String MemberResult_user_get(long j, MemberResult memberResult);

    public static final native void MemberResult_user_set(long j, MemberResult memberResult, String str);

    public static final native long ModifyGroupBaseInfoOption_add_option_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_add_option_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, long j2);

    public static final native long ModifyGroupBaseInfoOption_custom_info_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_custom_info_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, long j2, BytesMap bytesMap);

    public static final native byte[] ModifyGroupBaseInfoOption_face_url_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_face_url_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, byte[] bArr);

    public static final native long ModifyGroupBaseInfoOption_flag_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_flag_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, long j2);

    public static final native String ModifyGroupBaseInfoOption_group_id_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_group_id_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, String str);

    public static final native byte[] ModifyGroupBaseInfoOption_group_name_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_group_name_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, byte[] bArr);

    public static final native byte[] ModifyGroupBaseInfoOption_introduction_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_introduction_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, byte[] bArr);

    public static final native long ModifyGroupBaseInfoOption_max_member_num_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_max_member_num_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, long j2);

    public static final native byte[] ModifyGroupBaseInfoOption_notification_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_notification_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, byte[] bArr);

    public static final native int ModifyGroupBaseInfoOption_searchable_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_searchable_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, int i);

    public static final native int ModifyGroupBaseInfoOption_visible_get(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption);

    public static final native void ModifyGroupBaseInfoOption_visible_set(long j, ModifyGroupBaseInfoOption modifyGroupBaseInfoOption, int i);

    public static final native long ModifyGroupMemberInfoOption_custom_info_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_custom_info_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j2, BytesMap bytesMap);

    public static final native long ModifyGroupMemberInfoOption_flag_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_flag_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j2);

    public static final native String ModifyGroupMemberInfoOption_group_id_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_group_id_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, String str);

    public static final native String ModifyGroupMemberInfoOption_member_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_member_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, String str);

    public static final native long ModifyGroupMemberInfoOption_msg_flag_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_msg_flag_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j2);

    public static final native byte[] ModifyGroupMemberInfoOption_name_card_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_name_card_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, byte[] bArr);

    public static final native long ModifyGroupMemberInfoOption_role_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_role_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j2);

    public static final native long ModifyGroupMemberInfoOption_shutup_time_get(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption);

    public static final native void ModifyGroupMemberInfoOption_shutup_time_set(long j, ModifyGroupMemberInfoOption modifyGroupMemberInfoOption, long j2);

    public static final native long MsgReceiptVec_capacity(long j, MsgReceiptVec msgReceiptVec);

    public static final native void MsgReceiptVec_clear(long j, MsgReceiptVec msgReceiptVec);

    public static final native boolean MsgReceiptVec_empty(long j, MsgReceiptVec msgReceiptVec);

    public static final native long MsgReceiptVec_get(long j, MsgReceiptVec msgReceiptVec, int i);

    public static final native void MsgReceiptVec_pushBack(long j, MsgReceiptVec msgReceiptVec, long j2, MsgReceipt msgReceipt);

    public static final native void MsgReceiptVec_reserve(long j, MsgReceiptVec msgReceiptVec, long j2);

    public static final native void MsgReceiptVec_set(long j, MsgReceiptVec msgReceiptVec, int i, long j2, MsgReceipt msgReceipt);

    public static final native long MsgReceiptVec_size(long j, MsgReceiptVec msgReceiptVec);

    public static final native long MsgReceipt_sess_get(long j, MsgReceipt msgReceipt);

    public static final native void MsgReceipt_sess_set(long j, MsgReceipt msgReceipt, long j2, SessionUUID sessionUUID);

    public static final native long MsgReceipt_timestamp_get(long j, MsgReceipt msgReceipt);

    public static final native void MsgReceipt_timestamp_set(long j, MsgReceipt msgReceipt, long j2);

    public static final native long MsgVec_capacity(long j, MsgVec msgVec);

    public static final native void MsgVec_clear(long j, MsgVec msgVec);

    public static final native boolean MsgVec_empty(long j, MsgVec msgVec);

    public static final native long MsgVec_get(long j, MsgVec msgVec, int i);

    public static final native void MsgVec_pushBack(long j, MsgVec msgVec, long j2, Msg msg);

    public static final native void MsgVec_reserve(long j, MsgVec msgVec, long j2);

    public static final native void MsgVec_set(long j, MsgVec msgVec, int i, long j2, Msg msg);

    public static final native long MsgVec_size(long j, MsgVec msgVec);

    public static final native void Msg_addElem(long j, Msg msg, long j2, Elem elem);

    public static final native long Msg_cloneShared(long j, Msg msg);

    public static final native boolean Msg_convertToImportedmsg(long j, Msg msg);

    public static final native boolean Msg_copyFrom(long j, Msg msg, long j2, Msg msg2);

    public static final native int Msg_customInt(long j, Msg msg);

    public static final native String Msg_customStr(long j, Msg msg);

    public static final native boolean Msg_deleteFromStorage(long j, Msg msg);

    public static final native long Msg_elemSize(long j, Msg msg);

    public static final native int Msg_elemType(long j, Msg msg, long j2);

    public static final native long Msg_flag(long j, Msg msg);

    public static final native long Msg_getElem(long j, Msg msg, long j2);

    public static final native byte[] Msg_getGroupName(long j, Msg msg);

    public static final native long Msg_getOfflinePushInfo(long j, Msg msg);

    public static final native int Msg_getRecvFlag(long j, Msg msg);

    public static final native String Msg_getSender(long j, Msg msg);

    public static final native long Msg_getSenderGroupMemberProfile(long j, Msg msg);

    public static final native long Msg_getSenderProfile(long j, Msg msg);

    public static final native boolean Msg_hasGap(long j, Msg msg);

    public static final native boolean Msg_isPeerRead(long j, Msg msg);

    public static final native boolean Msg_isRead(long j, Msg msg);

    public static final native boolean Msg_isSelf(long j, Msg msg);

    public static final native boolean Msg_isValid(long j, Msg msg);

    public static final native long Msg_lifetime(long j, Msg msg);

    public static final native byte[] Msg_msgid(long j, Msg msg);

    public static final native long Msg_newMsg__SWIG_0();

    public static final native long Msg_newMsg__SWIG_1(long j, Msg msg);

    public static final native boolean Msg_parse(long j, Msg msg, byte[] bArr);

    public static final native int Msg_priority(long j, Msg msg);

    public static final native long Msg_rand(long j, Msg msg);

    public static final native boolean Msg_remove(long j, Msg msg);

    public static final native long Msg_seq(long j, Msg msg);

    public static final native byte[] Msg_serialize(long j, Msg msg);

    public static final native long Msg_session(long j, Msg msg);

    public static final native boolean Msg_setCustomInt(long j, Msg msg, int i);

    public static final native boolean Msg_setCustomStr(long j, Msg msg, String str);

    public static final native boolean Msg_setOfflinePushInfo(long j, Msg msg, long j2, OfflinePushInfo offlinePushInfo);

    public static final native boolean Msg_setPriority(long j, Msg msg, int i);

    public static final native boolean Msg_setSender(long j, Msg msg, String str);

    public static final native boolean Msg_setTime(long j, Msg msg, long j2);

    public static final native int Msg_status(long j, Msg msg);

    public static final native long Msg_time(long j, Msg msg);

    public static final native long Msg_uniqueid(long j, Msg msg);

    public static final native long NewGroupInfo_add_option_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_add_option_set(long j, NewGroupInfo newGroupInfo, long j2);

    public static final native long NewGroupInfo_base_custom_info_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_base_custom_info_set(long j, NewGroupInfo newGroupInfo, long j2, BytesMap bytesMap);

    public static final native String NewGroupInfo_face_url_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_face_url_set(long j, NewGroupInfo newGroupInfo, String str);

    public static final native byte[] NewGroupInfo_group_id_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_group_id_set(long j, NewGroupInfo newGroupInfo, byte[] bArr);

    public static final native long NewGroupInfo_group_members_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_group_members_set(long j, NewGroupInfo newGroupInfo, long j2, NewGroupMemVec newGroupMemVec);

    public static final native byte[] NewGroupInfo_group_name_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_group_name_set(long j, NewGroupInfo newGroupInfo, byte[] bArr);

    public static final native String NewGroupInfo_group_type_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_group_type_set(long j, NewGroupInfo newGroupInfo, String str);

    public static final native byte[] NewGroupInfo_introduction_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_introduction_set(long j, NewGroupInfo newGroupInfo, byte[] bArr);

    public static final native long NewGroupInfo_max_member_num_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_max_member_num_set(long j, NewGroupInfo newGroupInfo, long j2);

    public static final native byte[] NewGroupInfo_notification_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_notification_set(long j, NewGroupInfo newGroupInfo, byte[] bArr);

    public static final native long NewGroupInfo_owner_tiny_id_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_owner_tiny_id_set(long j, NewGroupInfo newGroupInfo, long j2);

    public static final native boolean NewGroupInfo_set_add_option_get(long j, NewGroupInfo newGroupInfo);

    public static final native void NewGroupInfo_set_add_option_set(long j, NewGroupInfo newGroupInfo, boolean z);

    public static final native long NewGroupMemVec_capacity(long j, NewGroupMemVec newGroupMemVec);

    public static final native void NewGroupMemVec_clear(long j, NewGroupMemVec newGroupMemVec);

    public static final native boolean NewGroupMemVec_empty(long j, NewGroupMemVec newGroupMemVec);

    public static final native long NewGroupMemVec_get(long j, NewGroupMemVec newGroupMemVec, int i);

    public static final native void NewGroupMemVec_pushBack(long j, NewGroupMemVec newGroupMemVec, long j2, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemVec_reserve(long j, NewGroupMemVec newGroupMemVec, long j2);

    public static final native void NewGroupMemVec_set(long j, NewGroupMemVec newGroupMemVec, int i, long j2, NewGroupMemberInfo newGroupMemberInfo);

    public static final native long NewGroupMemVec_size(long j, NewGroupMemVec newGroupMemVec);

    public static final native long NewGroupMemberInfo_custom_info_get(long j, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemberInfo_custom_info_set(long j, NewGroupMemberInfo newGroupMemberInfo, long j2, BytesMap bytesMap);

    public static final native String NewGroupMemberInfo_identifier_get(long j, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemberInfo_identifier_set(long j, NewGroupMemberInfo newGroupMemberInfo, String str);

    public static final native int NewGroupMemberInfo_member_role_get(long j, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemberInfo_member_role_set(long j, NewGroupMemberInfo newGroupMemberInfo, int i);

    public static final native int NewGroupMemberInfo_msg_flag_get(long j, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemberInfo_msg_flag_set(long j, NewGroupMemberInfo newGroupMemberInfo, int i);

    public static final native byte[] NewGroupMemberInfo_name_card_get(long j, NewGroupMemberInfo newGroupMemberInfo);

    public static final native void NewGroupMemberInfo_name_card_set(long j, NewGroupMemberInfo newGroupMemberInfo, byte[] bArr);

    public static final native long OfflinePushInfo_androidInfo_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_androidInfo_set(long j, OfflinePushInfo offlinePushInfo, long j2, AndroidOfflinePushInfo androidOfflinePushInfo);

    public static final native long OfflinePushInfo_apns_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_apns_set(long j, OfflinePushInfo offlinePushInfo, long j2, APNsInfo aPNsInfo);

    public static final native byte[] OfflinePushInfo_desc_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_desc_set(long j, OfflinePushInfo offlinePushInfo, byte[] bArr);

    public static final native byte[] OfflinePushInfo_ext_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_ext_set(long j, OfflinePushInfo offlinePushInfo, byte[] bArr);

    public static final native long OfflinePushInfo_flag_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_flag_set(long j, OfflinePushInfo offlinePushInfo, long j2);

    public static final native boolean OfflinePushInfo_isValid_get(long j, OfflinePushInfo offlinePushInfo);

    public static final native void OfflinePushInfo_isValid_set(long j, OfflinePushInfo offlinePushInfo, boolean z);

    public static final native String PROFILE_ALLOWTYPE_TAG_get();

    public static final native String PROFILE_BIRTHDAY_TAG_get();

    public static final native String PROFILE_CUSTOM_TAG_PREFIX_get();

    public static final native String PROFILE_FACEURL_TAG_get();

    public static final native String PROFILE_GENDER_TAG_get();

    public static final native String PROFILE_LANGUAGE_TAG_get();

    public static final native String PROFILE_LOCATION_TAG_get();

    public static final native String PROFILE_NICK_TAG_get();

    public static final native String PROFILE_SELFSIGNATURE_TAG_get();

    public static final native byte[] PairSession_first_get(long j, PairSession pairSession);

    public static final native void PairSession_first_set(long j, PairSession pairSession, byte[] bArr);

    public static final native int PairSession_second_get(long j, PairSession pairSession);

    public static final native void PairSession_second_set(long j, PairSession pairSession, int i);

    public static final native long PairVectorSession_capacity(long j, PairVectorSession pairVectorSession);

    public static final native void PairVectorSession_clear(long j, PairVectorSession pairVectorSession);

    public static final native boolean PairVectorSession_empty(long j, PairVectorSession pairVectorSession);

    public static final native long PairVectorSession_get(long j, PairVectorSession pairVectorSession, int i);

    public static final native void PairVectorSession_pushBack(long j, PairVectorSession pairVectorSession, long j2, PairSession pairSession);

    public static final native void PairVectorSession_reserve(long j, PairVectorSession pairVectorSession, long j2);

    public static final native void PairVectorSession_set(long j, PairVectorSession pairVectorSession, int i, long j2, PairSession pairSession);

    public static final native long PairVectorSession_size(long j, PairVectorSession pairVectorSession);

    public static final native String ProfileChangeElem_from_get(long j, ProfileChangeElem profileChangeElem);

    public static final native void ProfileChangeElem_from_set(long j, ProfileChangeElem profileChangeElem, String str);

    public static final native byte[] ProfileChangeElem_nick_get(long j, ProfileChangeElem profileChangeElem);

    public static final native void ProfileChangeElem_nick_set(long j, ProfileChangeElem profileChangeElem, byte[] bArr);

    public static final native long ProfileChangeElem_type_get(long j, ProfileChangeElem profileChangeElem);

    public static final native void ProfileChangeElem_type_set(long j, ProfileChangeElem profileChangeElem, long j2);

    public static final native void RunClosure_release(long j, RunClosure runClosure);

    public static final native void RunClosure_run(long j, RunClosure runClosure);

    public static final native long SNSProfileItemVec_capacity(long j, SNSProfileItemVec sNSProfileItemVec);

    public static final native void SNSProfileItemVec_clear(long j, SNSProfileItemVec sNSProfileItemVec);

    public static final native boolean SNSProfileItemVec_empty(long j, SNSProfileItemVec sNSProfileItemVec);

    public static final native long SNSProfileItemVec_get(long j, SNSProfileItemVec sNSProfileItemVec, int i);

    public static final native void SNSProfileItemVec_pushBack(long j, SNSProfileItemVec sNSProfileItemVec, long j2, SNSProfileItem sNSProfileItem);

    public static final native void SNSProfileItemVec_reserve(long j, SNSProfileItemVec sNSProfileItemVec, long j2);

    public static final native void SNSProfileItemVec_set(long j, SNSProfileItemVec sNSProfileItemVec, int i, long j2, SNSProfileItem sNSProfileItem);

    public static final native long SNSProfileItemVec_size(long j, SNSProfileItemVec sNSProfileItemVec);

    public static final native long SNSProfileItem_mpCustom_get(long j, SNSProfileItem sNSProfileItem);

    public static final native void SNSProfileItem_mpCustom_set(long j, SNSProfileItem sNSProfileItem, long j2, BytesMap bytesMap);

    public static final native long SNSProfileItem_mpProfiles_get(long j, SNSProfileItem sNSProfileItem);

    public static final native void SNSProfileItem_mpProfiles_set(long j, SNSProfileItem sNSProfileItem, long j2, BytesMap bytesMap);

    public static final native String SNSProfileItem_sIdentifier_get(long j, SNSProfileItem sNSProfileItem);

    public static final native void SNSProfileItem_sIdentifier_set(long j, SNSProfileItem sNSProfileItem, String str);

    public static final native String SNS_CUSTOM_TAG_PREFIX_get();

    public static final native String SNS_GROUP_TAG_get();

    public static final native String SNS_PENDENCY_ADD_SOURCE_TAG_get();

    public static final native String SNS_PENDENCY_ADD_WORDING_TAG_get();

    public static final native String SNS_RECOMMEND_1_TAG_get();

    public static final native String SNS_RECOMMEND_2_TAG_get();

    public static final native String SNS_RECOMMEND_3_TAG_get();

    public static final native String SNS_REMARK_TAG_get();

    public static final native void SdkReportItem_beginEvent(long j, SdkReportItem sdkReportItem);

    public static final native int SdkReportItem_code_get(long j, SdkReportItem sdkReportItem);

    public static final native void SdkReportItem_code_set(long j, SdkReportItem sdkReportItem, int i);

    public static final native byte[] SdkReportItem_desc_get(long j, SdkReportItem sdkReportItem);

    public static final native void SdkReportItem_desc_set(long j, SdkReportItem sdkReportItem, byte[] bArr);

    public static final native void SdkReportItem_endEvent(long j, SdkReportItem sdkReportItem);

    public static final native long SdkReportItem_end_time_ms_get(long j, SdkReportItem sdkReportItem);

    public static final native void SdkReportItem_end_time_ms_set(long j, SdkReportItem sdkReportItem, long j2);

    public static final native int SdkReportItem_event_get(long j, SdkReportItem sdkReportItem);

    public static final native void SdkReportItem_event_set(long j, SdkReportItem sdkReportItem, int i);

    public static final native long SdkReportItem_start_time_ms_get(long j, SdkReportItem sdkReportItem);

    public static final native void SdkReportItem_start_time_ms_set(long j, SdkReportItem sdkReportItem, long j2);

    public static final native long SessionUUIDVec_capacity(long j, SessionUUIDVec sessionUUIDVec);

    public static final native void SessionUUIDVec_clear(long j, SessionUUIDVec sessionUUIDVec);

    public static final native boolean SessionUUIDVec_empty(long j, SessionUUIDVec sessionUUIDVec);

    public static final native long SessionUUIDVec_get(long j, SessionUUIDVec sessionUUIDVec, int i);

    public static final native void SessionUUIDVec_pushBack(long j, SessionUUIDVec sessionUUIDVec, long j2, SessionUUID sessionUUID);

    public static final native void SessionUUIDVec_reserve(long j, SessionUUIDVec sessionUUIDVec, long j2);

    public static final native void SessionUUIDVec_set(long j, SessionUUIDVec sessionUUIDVec, int i, long j2, SessionUUID sessionUUID);

    public static final native long SessionUUIDVec_size(long j, SessionUUIDVec sessionUUIDVec);

    public static final native String SessionUUID_sid_get(long j, SessionUUID sessionUUID);

    public static final native void SessionUUID_sid_set(long j, SessionUUID sessionUUID, String str);

    public static final native int SessionUUID_type_get(long j, SessionUUID sessionUUID);

    public static final native void SessionUUID_type_set(long j, SessionUUID sessionUUID, int i);

    public static final native long Session_activeTime(long j, Session session);

    public static final native int Session_deleteLocalMsg__SWIG_0(long j, Session session, long j2, Msg msg, long j3, IDeleteLocalMsg iDeleteLocalMsg);

    public static final native int Session_deleteLocalMsg__SWIG_1(long j, Session session, long j2, IDeleteLocalMsg iDeleteLocalMsg);

    public static final native long Session_getDraft(long j, Session session);

    public static final native void Session_getLocalMsgs(long j, Session session, long j2, long j3, Msg msg, long j4, IGetMsgs iGetMsgs);

    public static final native void Session_getMsgs(long j, Session session, long j2, long j3, Msg msg, long j4, IGetMsgs iGetMsgs);

    public static final native void Session_getMsgsForward(long j, Session session, long j2, long j3, Msg msg, long j4, IGetMsgs iGetMsgs);

    public static final native long Session_getMsgsFromCache__SWIG_0(long j, Session session, long j2, long j3, Msg msg);

    public static final native long Session_getMsgsFromCache__SWIG_1(long j, Session session, long j2);

    public static final native long Session_getUndeletedMsgsFromCache__SWIG_0(long j, Session session, long j2, long j3, Msg msg);

    public static final native long Session_getUndeletedMsgsFromCache__SWIG_1(long j, Session session, long j2);

    public static final native boolean Session_hasDraft(long j, Session session);

    public static final native String Session_iconUrl(long j, Session session);

    public static final native String Session_identifier(long j, Session session);

    public static final native int Session_importMsg(long j, Session session, long j2, MsgVec msgVec);

    public static final native boolean Session_isValid(long j, Session session);

    public static final native long Session_msgUnread(long j, Session session);

    public static final native String Session_name(long j, Session session);

    public static final native void Session_reportReaded__SWIG_0(long j, Session session, long j2, Msg msg, long j3, ISetReadMsg iSetReadMsg);

    public static final native void Session_reportReaded__SWIG_1(long j, Session session, long j2, Msg msg);

    public static final native void Session_reportReaded__SWIG_2(long j, Session session);

    public static final native int Session_saveMsg(long j, Session session, long j2, Msg msg, String str, boolean z);

    public static final native void Session_sendCustomMsg(long j, Session session, long j2, Msg msg, long j3, ISendMsg iSendMsg, long j4);

    public static final native void Session_sendLikeMsg(long j, Session session, long j2, Msg msg, long j3, ISendMsg iSendMsg);

    public static final native void Session_sendMsg(long j, Session session, long j2, Msg msg, long j3, ISendMsg iSendMsg);

    public static final native void Session_sendRedPacketMsg(long j, Session session, long j2, Msg msg, long j3, ISendMsg iSendMsg);

    public static final native void Session_setDraft(long j, Session session, long j2, Draft draft);

    public static final native String Session_sid(long j, Session session);

    public static final native int Session_type(long j, Session session);

    public static final native String SetProfileOption_add_option_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_add_option_set(long j, SetProfileOption setProfileOption, String str);

    public static final native long SetProfileOption_birthday_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_birthday_set(long j, SetProfileOption setProfileOption, long j2);

    public static final native long SetProfileOption_custom_info_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_custom_info_set(long j, SetProfileOption setProfileOption, long j2, BytesMap bytesMap);

    public static final native byte[] SetProfileOption_face_url_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_face_url_set(long j, SetProfileOption setProfileOption, byte[] bArr);

    public static final native long SetProfileOption_flag_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_flag_set(long j, SetProfileOption setProfileOption, long j2);

    public static final native int SetProfileOption_gender_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_gender_set(long j, SetProfileOption setProfileOption, int i);

    public static final native long SetProfileOption_language_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_language_set(long j, SetProfileOption setProfileOption, long j2);

    public static final native byte[] SetProfileOption_location_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_location_set(long j, SetProfileOption setProfileOption, byte[] bArr);

    public static final native byte[] SetProfileOption_nick_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_nick_set(long j, SetProfileOption setProfileOption, byte[] bArr);

    public static final native byte[] SetProfileOption_self_signature_get(long j, SetProfileOption setProfileOption);

    public static final native void SetProfileOption_self_signature_set(long j, SetProfileOption setProfileOption, byte[] bArr);

    public static final native int SoundElem_business_id_get(long j, SoundElem soundElem);

    public static final native void SoundElem_business_id_set(long j, SoundElem soundElem, int i);

    public static final native int SoundElem_download_flag_get(long j, SoundElem soundElem);

    public static final native void SoundElem_download_flag_set(long j, SoundElem soundElem, int i);

    public static final native byte[] SoundElem_file_path_get(long j, SoundElem soundElem);

    public static final native void SoundElem_file_path_set(long j, SoundElem soundElem, byte[] bArr);

    public static final native int SoundElem_file_size_get(long j, SoundElem soundElem);

    public static final native void SoundElem_file_size_set(long j, SoundElem soundElem, int i);

    public static final native int SoundElem_file_time_get(long j, SoundElem soundElem);

    public static final native void SoundElem_file_time_set(long j, SoundElem soundElem, int i);

    public static final native String SoundElem_fileid_get(long j, SoundElem soundElem);

    public static final native void SoundElem_fileid_set(long j, SoundElem soundElem, String str);

    public static final native int SoundElem_taskid_get(long j, SoundElem soundElem);

    public static final native void SoundElem_taskid_set(long j, SoundElem soundElem, int i);

    public static final native long SoundElem_urls_get(long j, SoundElem soundElem);

    public static final native void SoundElem_urls_set(long j, SoundElem soundElem, long j2, StrVec strVec);

    public static final native boolean StatusManager_getUserStatus(long j, StatusManager statusManager, long j2, StrVec strVec, long j3, IStatusCallback iStatusCallback);

    public static final native void StatusManager_setUsedefinedData(long j, StatusManager statusManager, byte[] bArr, long j2, IStatusSetUserDefCallback iStatusSetUserDefCallback);

    public static final native long StrVec_capacity(long j, StrVec strVec);

    public static final native void StrVec_clear(long j, StrVec strVec);

    public static final native boolean StrVec_empty(long j, StrVec strVec);

    public static final native String StrVec_get(long j, StrVec strVec, int i);

    public static final native void StrVec_pushBack(long j, StrVec strVec, String str);

    public static final native void StrVec_reserve(long j, StrVec strVec, long j2);

    public static final native void StrVec_set(long j, StrVec strVec, int i, String str);

    public static final native long StrVec_size(long j, StrVec strVec);

    public static void SwigDirector_IApplyDownloadFileCallback_done(IApplyDownloadFileCallback iApplyDownloadFileCallback, long j) {
        iApplyDownloadFileCallback.done(new StrVec(j, false));
    }

    public static void SwigDirector_IApplyDownloadFileCallback_fail(IApplyDownloadFileCallback iApplyDownloadFileCallback, int i, String str) {
        iApplyDownloadFileCallback.fail(i, str);
    }

    public static void SwigDirector_IAvInviteCallBack_onAvInviteBuf(IAvInviteCallBack iAvInviteCallBack, byte[] bArr) {
        iAvInviteCallBack.onAvInviteBuf(bArr);
    }

    public static void SwigDirector_IBatchOprCallback_done(IBatchOprCallback iBatchOprCallback) {
        iBatchOprCallback.done();
    }

    public static void SwigDirector_IBatchOprCallback_fail(IBatchOprCallback iBatchOprCallback, int i, String str, long j) {
        iBatchOprCallback.fail(i, str, j == 0 ? null : new BatchOprDetailInfo(j, false));
    }

    public static void SwigDirector_IBatchOprCallback_setLogin(IBatchOprCallback iBatchOprCallback, String str, boolean z) {
        iBatchOprCallback.setLogin(str, z);
    }

    public static void SwigDirector_ICreateGroupCallback_done(ICreateGroupCallback iCreateGroupCallback, String str) {
        iCreateGroupCallback.done(str);
    }

    public static void SwigDirector_ICreateGroupCallback_fail(ICreateGroupCallback iCreateGroupCallback, int i, String str) {
        iCreateGroupCallback.fail(i, str);
    }

    public static void SwigDirector_IDeleteLocalMsg_done(IDeleteLocalMsg iDeleteLocalMsg) {
        iDeleteLocalMsg.done();
    }

    public static void SwigDirector_IDeleteLocalMsg_fail(IDeleteLocalMsg iDeleteLocalMsg, int i, String str) {
        iDeleteLocalMsg.fail(i, str);
    }

    public static boolean SwigDirector_IEnv_createThread(IEnv iEnv, long j) {
        return iEnv.createThread(j == 0 ? null : new ThreadEntry(j, true));
    }

    public static boolean SwigDirector_IEnv_httpRequest(IEnv iEnv, String str, int i, String str2, byte[] bArr, long j) {
        return iEnv.httpRequest(str, HttpMethod.swigToEnum(i), str2, bArr, j == 0 ? null : new EnvRequestClosure(j, false));
    }

    public static boolean SwigDirector_IEnv_runOnMainThread(IEnv iEnv, long j) {
        return iEnv.runOnMainThread(j == 0 ? null : new RunClosure(j, true));
    }

    public static boolean SwigDirector_IEnv_sSORequest__SWIG_0(IEnv iEnv, String str, String str2, byte[] bArr, long j, long j2) {
        return iEnv.sSORequest(str, str2, bArr, j == 0 ? null : new EnvRequestClosure(j, false), j2);
    }

    public static boolean SwigDirector_IEnv_sSORequest__SWIG_1(IEnv iEnv, String str, String str2, byte[] bArr, long j) {
        return iEnv.sSORequest(str, str2, bArr, j == 0 ? null : new EnvRequestClosure(j, false));
    }

    public static boolean SwigDirector_IEnv_uploadLogFile(IEnv iEnv, String str, long j) {
        return iEnv.uploadLogFile(str, new UploadLogFileOpt(j, false));
    }

    public static void SwigDirector_IFileTrans_done(IFileTrans iFileTrans, long j) {
        iFileTrans.done(new FileTransSuccParam(j, false));
    }

    public static void SwigDirector_IFileTrans_fail(IFileTrans iFileTrans, int i, String str) {
        iFileTrans.fail(i, str);
    }

    public static void SwigDirector_IFriendGroupCallback_done(IFriendGroupCallback iFriendGroupCallback, long j) {
        iFriendGroupCallback.done(new FriendGroupVec(j, false));
    }

    public static void SwigDirector_IFriendGroupCallback_fail(IFriendGroupCallback iFriendGroupCallback, int i, String str) {
        iFriendGroupCallback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipActionCallbackV2_done(IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, long j, long j2) {
        iFriendshipActionCallbackV2.done(j, new FriendProfileVec(j2, false));
    }

    public static void SwigDirector_IFriendshipActionCallbackV2_fail(IFriendshipActionCallbackV2 iFriendshipActionCallbackV2, int i, String str) {
        iFriendshipActionCallbackV2.fail(i, str);
    }

    public static void SwigDirector_IFriendshipActionCallback_done(IFriendshipActionCallback iFriendshipActionCallback, long j) {
        iFriendshipActionCallback.done(new FriendProfileVec(j, false));
    }

    public static void SwigDirector_IFriendshipActionCallback_fail(IFriendshipActionCallback iFriendshipActionCallback, int i, String str) {
        iFriendshipActionCallback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipCallback_done(IFriendshipCallback iFriendshipCallback) {
        iFriendshipCallback.done();
    }

    public static void SwigDirector_IFriendshipCallback_fail(IFriendshipCallback iFriendshipCallback, int i, String str) {
        iFriendshipCallback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipGetFriendV2Callback_done(IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, long j, long j2) {
        iFriendshipGetFriendV2Callback.done(new FriendMetaInfo(j, false), new FriendProfileVec(j2, false));
    }

    public static void SwigDirector_IFriendshipGetFriendV2Callback_fail(IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback, int i, String str) {
        iFriendshipGetFriendV2Callback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipGetFutureCallback_done(IFriendshipGetFutureCallback iFriendshipGetFutureCallback, long j, long j2) {
        iFriendshipGetFutureCallback.done(new FutureFriendMeta(j, false), new FriendFutureItemVec(j2, false));
    }

    public static void SwigDirector_IFriendshipGetFutureCallback_fail(IFriendshipGetFutureCallback iFriendshipGetFutureCallback, int i, String str) {
        iFriendshipGetFutureCallback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipPendencyCallback_done(IFriendshipPendencyCallback iFriendshipPendencyCallback, long j, long j2) {
        iFriendshipPendencyCallback.done(new FriendPendencyMeta(j, false), new FriendPendencyItemVec(j2, false));
    }

    public static void SwigDirector_IFriendshipPendencyCallback_fail(IFriendshipPendencyCallback iFriendshipPendencyCallback, int i, String str) {
        iFriendshipPendencyCallback.fail(i, str);
    }

    public static void SwigDirector_IFriendshipProxyListener_onAddFriendNotify(IFriendshipProxyListener iFriendshipProxyListener, long j) {
        iFriendshipProxyListener.onAddFriendNotify(new FriendProfileVec(j, false));
    }

    public static void SwigDirector_IFriendshipProxyListener_onAddFriendReq(IFriendshipProxyListener iFriendshipProxyListener, long j) {
        iFriendshipProxyListener.onAddFriendReq(new AddFriendReqVec(j, false));
    }

    public static void SwigDirector_IFriendshipProxyListener_onDeleteFriendNotify(IFriendshipProxyListener iFriendshipProxyListener, long j) {
        iFriendshipProxyListener.onDeleteFriendNotify(new StrVec(j, false));
    }

    public static void SwigDirector_IFriendshipProxyListener_onFriendProfileUpdate(IFriendshipProxyListener iFriendshipProxyListener, long j) {
        iFriendshipProxyListener.onFriendProfileUpdate(new FriendProfileVec(j, false));
    }

    public static void SwigDirector_IFriendshipProxyListener_onProxyStatusChange(IFriendshipProxyListener iFriendshipProxyListener, int i) {
        iFriendshipProxyListener.onProxyStatusChange(FriendProxyStatus.swigToEnum(i));
    }

    public static void SwigDirector_IGetMsgs_done(IGetMsgs iGetMsgs, long j) {
        iGetMsgs.done(new MsgVec(j, false));
    }

    public static void SwigDirector_IGetMsgs_fail(IGetMsgs iGetMsgs, int i, String str) {
        iGetMsgs.fail(i, str);
    }

    public static void SwigDirector_IGroupAssistantCallback_onGroupAdd(IGroupAssistantCallback iGroupAssistantCallback, long j) {
        iGroupAssistantCallback.onGroupAdd(new GroupCacheInfo(j, false));
    }

    public static void SwigDirector_IGroupAssistantCallback_onGroupDelete(IGroupAssistantCallback iGroupAssistantCallback, String str) {
        iGroupAssistantCallback.onGroupDelete(str);
    }

    public static void SwigDirector_IGroupAssistantCallback_onGroupUpdate(IGroupAssistantCallback iGroupAssistantCallback, long j) {
        iGroupAssistantCallback.onGroupUpdate(new GroupCacheInfo(j, false));
    }

    public static void SwigDirector_IGroupAssistantCallback_onMemberJoin(IGroupAssistantCallback iGroupAssistantCallback, String str, long j) {
        iGroupAssistantCallback.onMemberJoin(str, new GroupMemberInfoVec(j, false));
    }

    public static void SwigDirector_IGroupAssistantCallback_onMemberQuit(IGroupAssistantCallback iGroupAssistantCallback, String str, long j) {
        iGroupAssistantCallback.onMemberQuit(str, new StrVec(j, false));
    }

    public static void SwigDirector_IGroupAssistantCallback_onMemberUpdate(IGroupAssistantCallback iGroupAssistantCallback, String str, long j) {
        iGroupAssistantCallback.onMemberUpdate(str, new GroupMemberInfoVec(j, false));
    }

    public static void SwigDirector_IGroupGetPendencyCallback_done(IGroupGetPendencyCallback iGroupGetPendencyCallback, long j, long j2) {
        iGroupGetPendencyCallback.done(new GroupPendencyMeta(j, false), new GroupPendencyItemVec(j2, false));
    }

    public static void SwigDirector_IGroupGetPendencyCallback_fail(IGroupGetPendencyCallback iGroupGetPendencyCallback, int i, String str) {
        iGroupGetPendencyCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupInfoListCallbackV2_done(IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, long j, long j2) {
        iGroupInfoListCallbackV2.done(j, new GroupDetailInfoVec(j2, false));
    }

    public static void SwigDirector_IGroupInfoListCallbackV2_fail(IGroupInfoListCallbackV2 iGroupInfoListCallbackV2, int i, String str) {
        iGroupInfoListCallbackV2.fail(i, str);
    }

    public static void SwigDirector_IGroupInfoListCallback_done(IGroupInfoListCallback iGroupInfoListCallback, long j) {
        iGroupInfoListCallback.done(new GroupDetailInfoVec(j, false));
    }

    public static void SwigDirector_IGroupInfoListCallback_fail(IGroupInfoListCallback iGroupInfoListCallback, int i, String str) {
        iGroupInfoListCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupListCallback_done(IGroupListCallback iGroupListCallback, long j) {
        iGroupListCallback.done(new GroupBaseInfoVec(j, false));
    }

    public static void SwigDirector_IGroupListCallback_fail(IGroupListCallback iGroupListCallback, int i, String str) {
        iGroupListCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupMemberCallbackV2_done(IGroupMemberCallbackV2 iGroupMemberCallbackV2, long j, long j2) {
        iGroupMemberCallbackV2.done(j, new GroupMemberInfoVec(j2, false));
    }

    public static void SwigDirector_IGroupMemberCallbackV2_fail(IGroupMemberCallbackV2 iGroupMemberCallbackV2, int i, String str) {
        iGroupMemberCallbackV2.fail(i, str);
    }

    public static void SwigDirector_IGroupMemberCallback_done(IGroupMemberCallback iGroupMemberCallback, long j) {
        iGroupMemberCallback.done(new GroupMemberInfoVec(j, false));
    }

    public static void SwigDirector_IGroupMemberCallback_fail(IGroupMemberCallback iGroupMemberCallback, int i, String str) {
        iGroupMemberCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupMemberResultCallback_done(IGroupMemberResultCallback iGroupMemberResultCallback, long j) {
        iGroupMemberResultCallback.done(new GroupMemberResultVec(j, false));
    }

    public static void SwigDirector_IGroupMemberResultCallback_fail(IGroupMemberResultCallback iGroupMemberResultCallback, int i, String str) {
        iGroupMemberResultCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupNotifyCallback_done(IGroupNotifyCallback iGroupNotifyCallback) {
        iGroupNotifyCallback.done();
    }

    public static void SwigDirector_IGroupNotifyCallback_fail(IGroupNotifyCallback iGroupNotifyCallback, int i, String str) {
        iGroupNotifyCallback.fail(i, str);
    }

    public static void SwigDirector_IGroupTipsEventCallback_onGroupTipsEvent(IGroupTipsEventCallback iGroupTipsEventCallback, long j) {
        iGroupTipsEventCallback.onGroupTipsEvent(new GroupTipsElem(j, false));
    }

    public static void SwigDirector_IGroupUpdateCallback_onMembersUpdate(IGroupUpdateCallback iGroupUpdateCallback, long j) {
        iGroupUpdateCallback.onMembersUpdate(new GroupTipsElem(j, false));
    }

    public static void SwigDirector_IImageUploadCallback_done(IImageUploadCallback iImageUploadCallback, long j) {
        iImageUploadCallback.done(new ImageElem(j, false));
    }

    public static void SwigDirector_IImageUploadCallback_fail(IImageUploadCallback iImageUploadCallback, int i, String str) {
        iImageUploadCallback.fail(i, str);
    }

    public static void SwigDirector_IInit_done(IInit iInit) {
        iInit.done();
    }

    public static void SwigDirector_IInit_fail(IInit iInit, int i, String str) {
        iInit.fail(i, str);
    }

    public static void SwigDirector_ILogMsgCallback_onLogMsg(ILogMsgCallback iLogMsgCallback, String str, int i, String str2) {
        iLogMsgCallback.onLogMsg(str, LogLevel.swigToEnum(i), str2);
    }

    public static void SwigDirector_INotify_onMsgEvent(INotify iNotify, long j) {
        iNotify.onMsgEvent(new MsgVec(j, false));
    }

    public static void SwigDirector_INotify_onMsgUpdate(INotify iNotify, long j) {
        iNotify.onMsgUpdate(new MsgVec(j, false));
    }

    public static void SwigDirector_INotify_onRecvMsgReceipt(INotify iNotify, long j) {
        iNotify.onRecvMsgReceipt(new MsgReceiptVec(j, false));
    }

    public static void SwigDirector_INotify_onRefresh(INotify iNotify) {
        iNotify.onRefresh();
    }

    public static void SwigDirector_INotify_onRefreshConversation(INotify iNotify, long j) {
        iNotify.onRefreshConversation(new SessionUUIDVec(j, false));
    }

    public static void SwigDirector_INotify_onUploadProgress(INotify iNotify, long j, int i, int i2, int i3) {
        iNotify.onUploadProgress(new Msg(j, false), i, i2, i3);
    }

    public static void SwigDirector_INotify_onUserStatusChanged(INotify iNotify, long j) {
        iNotify.onUserStatusChanged(new UserStatus(j, false));
    }

    public static void SwigDirector_ISendMsg_done(ISendMsg iSendMsg) {
        iSendMsg.done();
    }

    public static void SwigDirector_ISendMsg_fail(ISendMsg iSendMsg, int i, String str) {
        iSendMsg.fail(i, str);
    }

    public static void SwigDirector_ISetReadMsg_done(ISetReadMsg iSetReadMsg) {
        iSetReadMsg.done();
    }

    public static void SwigDirector_ISetReadMsg_fail(ISetReadMsg iSetReadMsg, int i, String str) {
        iSetReadMsg.fail(i, str);
    }

    public static void SwigDirector_IStatusCallback_done(IStatusCallback iStatusCallback, long j) {
        iStatusCallback.done(new UserStatusVec(j, false));
    }

    public static void SwigDirector_IStatusCallback_fail(IStatusCallback iStatusCallback, int i, String str) {
        iStatusCallback.fail(i, str);
    }

    public static void SwigDirector_IStatusSetUserDefCallback_done(IStatusSetUserDefCallback iStatusSetUserDefCallback) {
        iStatusSetUserDefCallback.done();
    }

    public static void SwigDirector_IStatusSetUserDefCallback_fail(IStatusSetUserDefCallback iStatusSetUserDefCallback, int i, String str) {
        iStatusSetUserDefCallback.fail(i, str);
    }

    public static final native byte[] TextElem_content_get(long j, TextElem textElem);

    public static final native void TextElem_content_set(long j, TextElem textElem, byte[] bArr);

    public static final native void ThreadEntry_release(long j, ThreadEntry threadEntry);

    public static final native void ThreadEntry_run(long j, ThreadEntry threadEntry);

    public static final native long UpdateInfoOpt_customInfoTags_get(long j, UpdateInfoOpt updateInfoOpt);

    public static final native void UpdateInfoOpt_customInfoTags_set(long j, UpdateInfoOpt updateInfoOpt, long j2, BytesVec bytesVec);

    public static final native long UpdateInfoOpt_flag_get(long j, UpdateInfoOpt updateInfoOpt);

    public static final native void UpdateInfoOpt_flag_set(long j, UpdateInfoOpt updateInfoOpt, long j2);

    public static final native String UploadLogFileOpt_filePath_get(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native void UploadLogFileOpt_filePath_set(long j, UploadLogFileOpt uploadLogFileOpt, String str);

    public static final native byte[] UploadLogFileOpt_logId_get(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native void UploadLogFileOpt_logId_set(long j, UploadLogFileOpt uploadLogFileOpt, byte[] bArr);

    public static final native long UploadLogFileOpt_logSize_get(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native void UploadLogFileOpt_logSize_set(long j, UploadLogFileOpt uploadLogFileOpt, long j2);

    public static final native byte[] UploadLogFileOpt_relativePath_get(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native void UploadLogFileOpt_relativePath_set(long j, UploadLogFileOpt uploadLogFileOpt, byte[] bArr);

    public static final native String UploadLogFileOpt_tag_get(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native void UploadLogFileOpt_tag_set(long j, UploadLogFileOpt uploadLogFileOpt, String str);

    public static final native String UploadLogFileOpt_toString(long j, UploadLogFileOpt uploadLogFileOpt);

    public static final native boolean UserConfig_auto_report_get(long j, UserConfig userConfig);

    public static final native void UserConfig_auto_report_set(long j, UserConfig userConfig, boolean z);

    public static final native long UserConfig_frd_prxy_config_get(long j, UserConfig userConfig);

    public static final native void UserConfig_frd_prxy_config_set(long j, UserConfig userConfig, long j2, FriendshipProxyConfig friendshipProxyConfig);

    public static final native long UserConfig_grp_ass_config_get(long j, UserConfig userConfig);

    public static final native void UserConfig_grp_ass_config_set(long j, UserConfig userConfig, long j2, GroupAssistantConfig groupAssistantConfig);

    public static final native long UserConfig_notify_get(long j, UserConfig userConfig);

    public static final native void UserConfig_notify_set(long j, UserConfig userConfig, long j2, INotify iNotify);

    public static final native boolean UserConfig_read_receipt_get(long j, UserConfig userConfig);

    public static final native void UserConfig_read_receipt_set(long j, UserConfig userConfig, boolean z);

    public static final native boolean UserConfig_recent_contact_get(long j, UserConfig userConfig);

    public static final native boolean UserConfig_recent_contact_notify_get(long j, UserConfig userConfig);

    public static final native void UserConfig_recent_contact_notify_set(long j, UserConfig userConfig, boolean z);

    public static final native void UserConfig_recent_contact_set(long j, UserConfig userConfig, boolean z);

    public static final native boolean UserConfig_storage_get(long j, UserConfig userConfig);

    public static final native void UserConfig_storage_set(long j, UserConfig userConfig, boolean z);

    public static final native long UserStatusVec_capacity(long j, UserStatusVec userStatusVec);

    public static final native void UserStatusVec_clear(long j, UserStatusVec userStatusVec);

    public static final native boolean UserStatusVec_empty(long j, UserStatusVec userStatusVec);

    public static final native long UserStatusVec_get(long j, UserStatusVec userStatusVec, int i);

    public static final native void UserStatusVec_pushBack(long j, UserStatusVec userStatusVec, long j2, UserStatus userStatus);

    public static final native void UserStatusVec_reserve(long j, UserStatusVec userStatusVec, long j2);

    public static final native void UserStatusVec_set(long j, UserStatusVec userStatusVec, int i, long j2, UserStatus userStatus);

    public static final native long UserStatusVec_size(long j, UserStatusVec userStatusVec);

    public static final native long UserStatus_ddwTinyId_get(long j, UserStatus userStatus);

    public static final native void UserStatus_ddwTinyId_set(long j, UserStatus userStatus, long j2);

    public static final native String UserStatus_sUserId_get(long j, UserStatus userStatus);

    public static final native void UserStatus_sUserId_set(long j, UserStatus userStatus, String str);

    public static final native long UserStatus_stInstStatus_get(long j, UserStatus userStatus);

    public static final native void UserStatus_stInstStatus_set(long j, UserStatus userStatus, long j2, InstStatusVec instStatusVec);

    public static final native int VideoElem_business_id_get(long j, VideoElem videoElem);

    public static final native void VideoElem_business_id_set(long j, VideoElem videoElem, int i);

    public static final native byte[] VideoElem_imageId_get(long j, VideoElem videoElem);

    public static final native void VideoElem_imageId_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native int VideoElem_image_download_flag_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_download_flag_set(long j, VideoElem videoElem, int i);

    public static final native long VideoElem_image_height_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_height_set(long j, VideoElem videoElem, long j2);

    public static final native byte[] VideoElem_image_path_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_path_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native long VideoElem_image_size_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_size_set(long j, VideoElem videoElem, long j2);

    public static final native byte[] VideoElem_image_type_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_type_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native long VideoElem_image_urls_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_urls_set(long j, VideoElem videoElem, long j2, StrVec strVec);

    public static final native long VideoElem_image_width_get(long j, VideoElem videoElem);

    public static final native void VideoElem_image_width_set(long j, VideoElem videoElem, long j2);

    public static final native long VideoElem_taskid_get(long j, VideoElem videoElem);

    public static final native void VideoElem_taskid_set(long j, VideoElem videoElem, long j2);

    public static final native byte[] VideoElem_videoId_get(long j, VideoElem videoElem);

    public static final native void VideoElem_videoId_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native int VideoElem_video_download_flag_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_download_flag_set(long j, VideoElem videoElem, int i);

    public static final native long VideoElem_video_duration_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_duration_set(long j, VideoElem videoElem, long j2);

    public static final native byte[] VideoElem_video_path_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_path_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native long VideoElem_video_size_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_size_set(long j, VideoElem videoElem, long j2);

    public static final native byte[] VideoElem_video_type_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_type_set(long j, VideoElem videoElem, byte[] bArr);

    public static final native long VideoElem_video_urls_get(long j, VideoElem videoElem);

    public static final native void VideoElem_video_urls_set(long j, VideoElem videoElem, long j2, StrVec strVec);

    public static final native boolean checkFriendCustomTag(String str);

    public static final native boolean checkProfileCustomTag(String str);

    public static final native void delete_APNsInfo(long j);

    public static final native void delete_AddFriendReq(long j);

    public static final native void delete_AddFriendReqVec(long j);

    public static final native void delete_AndroidOfflinePushInfo(long j);

    public static final native void delete_ApplyDownloadFileReq(long j);

    public static final native void delete_BytesMap(long j);

    public static final native void delete_BytesMemberInfoMap(long j);

    public static final native void delete_BytesMemberInfoParser(long j);

    public static final native void delete_BytesProfileMap(long j);

    public static final native void delete_BytesProfileMapParser(long j);

    public static final native void delete_BytesVec(long j);

    public static final native void delete_BytesVecFetcher(long j);

    public static final native void delete_Context(long j);

    public static final native void delete_CustomElem(long j);

    public static final native void delete_Draft(long j);

    public static final native void delete_Elem(long j);

    public static final native void delete_ElemVec(long j);

    public static final native void delete_EnvRequestClosure(long j);

    public static final native void delete_ErrCodeManager(long j);

    public static final native void delete_ErrInfoVec(long j);

    public static final native void delete_FaceElem(long j);

    public static final native void delete_FileElem(long j);

    public static final native void delete_FileTransSuccParam(long j);

    public static final native void delete_FileTranser(long j);

    public static final native void delete_FriendChangeElem(long j);

    public static final native void delete_FriendChangeInfoVec(long j);

    public static final native void delete_FriendChangeUserInfo(long j);

    public static final native void delete_FriendFutureItem(long j);

    public static final native void delete_FriendFutureItemVec(long j);

    public static final native void delete_FriendGroup(long j);

    public static final native void delete_FriendGroupItem(long j);

    public static final native void delete_FriendGroupVec(long j);

    public static final native void delete_FriendMetaInfo(long j);

    public static final native void delete_FriendPendencyItem(long j);

    public static final native void delete_FriendPendencyItemVec(long j);

    public static final native void delete_FriendPendencyMeta(long j);

    public static final native void delete_FriendProfile(long j);

    public static final native void delete_FriendProfileUpdateItem(long j);

    public static final native void delete_FriendProfileVec(long j);

    public static final native void delete_FriendshipManager(long j);

    public static final native void delete_FriendshipProxy(long j);

    public static final native void delete_FriendshipProxyConfig(long j);

    public static final native void delete_FutureFriendMeta(long j);

    public static final native void delete_GetGroupBaseInfoOption(long j);

    public static final native void delete_GetGroupMemInfoOption(long j);

    public static final native void delete_GetGroupPendencyOption(long j);

    public static final native void delete_GetProfileOption(long j);

    public static final native void delete_GroupAssistant(long j);

    public static final native void delete_GroupAssistantConfig(long j);

    public static final native void delete_GroupBaseInfo(long j);

    public static final native void delete_GroupBaseInfoVec(long j);

    public static final native void delete_GroupCacheInfo(long j);

    public static final native void delete_GroupCacheInfoVec(long j);

    public static final native void delete_GroupDetailInfo(long j);

    public static final native void delete_GroupDetailInfoVec(long j);

    public static final native void delete_GroupManager(long j);

    public static final native void delete_GroupMemberInfoVec(long j);

    public static final native void delete_GroupMemberResultVec(long j);

    public static final native void delete_GroupMsgSeqInfo(long j);

    public static final native void delete_GroupPendencyItem(long j);

    public static final native void delete_GroupPendencyItemVec(long j);

    public static final native void delete_GroupPendencyMeta(long j);

    public static final native void delete_GroupReportElem(long j);

    public static final native void delete_GroupSelfInfo(long j);

    public static final native void delete_GroupSettings(long j);

    public static final native void delete_GroupTipsElem(long j);

    public static final native void delete_GroupTipsElem_GroupInfo(long j);

    public static final native void delete_GroupTipsElem_GroupInfoVec(long j);

    public static final native void delete_GroupTipsElem_MemberInfo(long j);

    public static final native void delete_GroupTipsElem_MemberInfoVec(long j);

    public static final native void delete_IApplyDownloadFileCallback(long j);

    public static final native void delete_IAvInviteCallBack(long j);

    public static final native void delete_IBatchOprCallback(long j);

    public static final native void delete_IBatchOprCallback_BatchOprDetailInfo(long j);

    public static final native void delete_IBatchOprCallback_BatchOprDetailInfo_ErrInfo(long j);

    public static final native void delete_ICreateGroupCallback(long j);

    public static final native void delete_IDeleteLocalMsg(long j);

    public static final native void delete_IEnv(long j);

    public static final native void delete_IFileTrans(long j);

    public static final native void delete_IFriendGroupCallback(long j);

    public static final native void delete_IFriendshipActionCallback(long j);

    public static final native void delete_IFriendshipActionCallbackV2(long j);

    public static final native void delete_IFriendshipCallback(long j);

    public static final native void delete_IFriendshipGetFriendV2Callback(long j);

    public static final native void delete_IFriendshipGetFutureCallback(long j);

    public static final native void delete_IFriendshipPendencyCallback(long j);

    public static final native void delete_IFriendshipProxyActionCallback(long j);

    public static final native void delete_IFriendshipProxyListener(long j);

    public static final native void delete_IGetMsgs(long j);

    public static final native void delete_IGroupAssistantCallback(long j);

    public static final native void delete_IGroupGetPendencyCallback(long j);

    public static final native void delete_IGroupInfoListCallback(long j);

    public static final native void delete_IGroupInfoListCallbackV2(long j);

    public static final native void delete_IGroupListCallback(long j);

    public static final native void delete_IGroupMemberCallback(long j);

    public static final native void delete_IGroupMemberCallbackV2(long j);

    public static final native void delete_IGroupMemberResultCallback(long j);

    public static final native void delete_IGroupNotifyCallback(long j);

    public static final native void delete_IGroupTipsEventCallback(long j);

    public static final native void delete_IGroupUpdateCallback(long j);

    public static final native void delete_IImageUploadCallback(long j);

    public static final native void delete_IInit(long j);

    public static final native void delete_ILogMsgCallback(long j);

    public static final native void delete_IMCoreUser(long j);

    public static final native void delete_INotify(long j);

    public static final native void delete_ISendMsg(long j);

    public static final native void delete_ISetReadMsg(long j);

    public static final native void delete_IStatusCallback(long j);

    public static final native void delete_IStatusSetUserDefCallback(long j);

    public static final native void delete_ImageElem(long j);

    public static final native void delete_InstStatus(long j);

    public static final native void delete_InstStatusVec(long j);

    public static final native void delete_LocationElem(long j);

    public static final native void delete_MapKeyFetcher(long j);

    public static final native void delete_MemberInfo(long j);

    public static final native void delete_MemberResult(long j);

    public static final native void delete_ModifyGroupBaseInfoOption(long j);

    public static final native void delete_ModifyGroupMemberInfoOption(long j);

    public static final native void delete_Msg(long j);

    public static final native void delete_MsgReceipt(long j);

    public static final native void delete_MsgReceiptVec(long j);

    public static final native void delete_MsgVec(long j);

    public static final native void delete_NewGroupInfo(long j);

    public static final native void delete_NewGroupMemVec(long j);

    public static final native void delete_NewGroupMemberInfo(long j);

    public static final native void delete_OfflinePushInfo(long j);

    public static final native void delete_PairSession(long j);

    public static final native void delete_PairVectorSession(long j);

    public static final native void delete_ProfileChangeElem(long j);

    public static final native void delete_RunClosure(long j);

    public static final native void delete_SNSProfileItem(long j);

    public static final native void delete_SNSProfileItemVec(long j);

    public static final native void delete_SdkReportItem(long j);

    public static final native void delete_Session(long j);

    public static final native void delete_SessionUUID(long j);

    public static final native void delete_SessionUUIDVec(long j);

    public static final native void delete_SetProfileOption(long j);

    public static final native void delete_SoundElem(long j);

    public static final native void delete_StatusManager(long j);

    public static final native void delete_StrVec(long j);

    public static final native void delete_TextElem(long j);

    public static final native void delete_ThreadEntry(long j);

    public static final native void delete_UpdateInfoOpt(long j);

    public static final native void delete_UploadLogFileOpt(long j);

    public static final native void delete_UserConfig(long j);

    public static final native void delete_UserStatus(long j);

    public static final native void delete_UserStatusVec(long j);

    public static final native void delete_VideoElem(long j);

    public static final native String friendGender2str(int i);

    public static final native long new_APNsInfo();

    public static final native long new_AddFriendReq();

    public static final native long new_AddFriendReqVec__SWIG_0();

    public static final native long new_AddFriendReqVec__SWIG_1(long j);

    public static final native long new_AndroidOfflinePushInfo();

    public static final native long new_ApplyDownloadFileReq();

    public static final native long new_BytesMap__SWIG_0();

    public static final native long new_BytesMap__SWIG_1(long j, BytesMap bytesMap);

    public static final native long new_BytesMemberInfoMap__SWIG_0();

    public static final native long new_BytesMemberInfoMap__SWIG_1(long j, BytesMemberInfoMap bytesMemberInfoMap);

    public static final native long new_BytesMemberInfoParser();

    public static final native long new_BytesProfileMapParser();

    public static final native long new_BytesProfileMap__SWIG_0();

    public static final native long new_BytesProfileMap__SWIG_1(long j, BytesProfileMap bytesProfileMap);

    public static final native long new_BytesVecFetcher(long j, BytesVec bytesVec);

    public static final native long new_BytesVec__SWIG_0();

    public static final native long new_BytesVec__SWIG_1(long j);

    public static final native long new_Context();

    public static final native long new_CustomElem();

    public static final native long new_Draft();

    public static final native long new_Elem();

    public static final native long new_ElemVec__SWIG_0();

    public static final native long new_ElemVec__SWIG_1(long j);

    public static final native long new_ErrCodeManager();

    public static final native long new_ErrInfoVec__SWIG_0();

    public static final native long new_ErrInfoVec__SWIG_1(long j);

    public static final native long new_FaceElem();

    public static final native long new_FileElem();

    public static final native long new_FileTransSuccParam();

    public static final native long new_FileTranser(long j);

    public static final native long new_FriendChangeElem();

    public static final native long new_FriendChangeInfoVec__SWIG_0();

    public static final native long new_FriendChangeInfoVec__SWIG_1(long j);

    public static final native long new_FriendChangeUserInfo();

    public static final native long new_FriendFutureItem();

    public static final native long new_FriendFutureItemVec__SWIG_0();

    public static final native long new_FriendFutureItemVec__SWIG_1(long j);

    public static final native long new_FriendGroup();

    public static final native long new_FriendGroupItem();

    public static final native long new_FriendGroupVec__SWIG_0();

    public static final native long new_FriendGroupVec__SWIG_1(long j);

    public static final native long new_FriendMetaInfo();

    public static final native long new_FriendPendencyItem();

    public static final native long new_FriendPendencyItemVec__SWIG_0();

    public static final native long new_FriendPendencyItemVec__SWIG_1(long j);

    public static final native long new_FriendPendencyMeta();

    public static final native long new_FriendProfile();

    public static final native long new_FriendProfileUpdateItem();

    public static final native long new_FriendProfileVec__SWIG_0();

    public static final native long new_FriendProfileVec__SWIG_1(long j);

    public static final native long new_FriendshipManager(String str);

    public static final native long new_FriendshipProxy();

    public static final native long new_FriendshipProxyConfig();

    public static final native long new_FutureFriendMeta();

    public static final native long new_GetGroupBaseInfoOption();

    public static final native long new_GetGroupMemInfoOption();

    public static final native long new_GetGroupPendencyOption();

    public static final native long new_GetProfileOption();

    public static final native long new_GroupAssistant(String str);

    public static final native long new_GroupAssistantConfig();

    public static final native long new_GroupBaseInfo();

    public static final native long new_GroupBaseInfoVec__SWIG_0();

    public static final native long new_GroupBaseInfoVec__SWIG_1(long j);

    public static final native long new_GroupCacheInfo();

    public static final native long new_GroupCacheInfoVec__SWIG_0();

    public static final native long new_GroupCacheInfoVec__SWIG_1(long j);

    public static final native long new_GroupDetailInfo();

    public static final native long new_GroupDetailInfoVec__SWIG_0();

    public static final native long new_GroupDetailInfoVec__SWIG_1(long j);

    public static final native long new_GroupManager(String str);

    public static final native long new_GroupMemberInfoVec__SWIG_0();

    public static final native long new_GroupMemberInfoVec__SWIG_1(long j);

    public static final native long new_GroupMemberResultVec__SWIG_0();

    public static final native long new_GroupMemberResultVec__SWIG_1(long j);

    public static final native long new_GroupMsgSeqInfo();

    public static final native long new_GroupPendencyItem();

    public static final native long new_GroupPendencyItemVec__SWIG_0();

    public static final native long new_GroupPendencyItemVec__SWIG_1(long j);

    public static final native long new_GroupPendencyMeta();

    public static final native long new_GroupReportElem();

    public static final native long new_GroupSelfInfo__SWIG_0();

    public static final native long new_GroupSelfInfo__SWIG_1(long j, MemberInfo memberInfo);

    public static final native long new_GroupSettings();

    public static final native long new_GroupTipsElem();

    public static final native long new_GroupTipsElem_GroupInfo();

    public static final native long new_GroupTipsElem_GroupInfoVec__SWIG_0();

    public static final native long new_GroupTipsElem_GroupInfoVec__SWIG_1(long j);

    public static final native long new_GroupTipsElem_MemberInfo();

    public static final native long new_GroupTipsElem_MemberInfoVec__SWIG_0();

    public static final native long new_GroupTipsElem_MemberInfoVec__SWIG_1(long j);

    public static final native long new_IApplyDownloadFileCallback();

    public static final native long new_IAvInviteCallBack();

    public static final native long new_IBatchOprCallback();

    public static final native long new_IBatchOprCallback_BatchOprDetailInfo();

    public static final native long new_IBatchOprCallback_BatchOprDetailInfo_ErrInfo();

    public static final native long new_ICreateGroupCallback();

    public static final native long new_IDeleteLocalMsg();

    public static final native long new_IEnv();

    public static final native long new_IFileTrans();

    public static final native long new_IFriendGroupCallback();

    public static final native long new_IFriendshipActionCallback();

    public static final native long new_IFriendshipActionCallbackV2();

    public static final native long new_IFriendshipCallback();

    public static final native long new_IFriendshipGetFriendV2Callback();

    public static final native long new_IFriendshipGetFutureCallback();

    public static final native long new_IFriendshipPendencyCallback();

    public static final native long new_IFriendshipProxyListener();

    public static final native long new_IGetMsgs();

    public static final native long new_IGroupAssistantCallback();

    public static final native long new_IGroupGetPendencyCallback();

    public static final native long new_IGroupInfoListCallback();

    public static final native long new_IGroupInfoListCallbackV2();

    public static final native long new_IGroupListCallback();

    public static final native long new_IGroupMemberCallback();

    public static final native long new_IGroupMemberCallbackV2();

    public static final native long new_IGroupMemberResultCallback();

    public static final native long new_IGroupNotifyCallback();

    public static final native long new_IGroupTipsEventCallback();

    public static final native long new_IGroupUpdateCallback();

    public static final native long new_IImageUploadCallback();

    public static final native long new_IInit();

    public static final native long new_ILogMsgCallback();

    public static final native long new_INotify();

    public static final native long new_ISendMsg();

    public static final native long new_ISetReadMsg();

    public static final native long new_IStatusCallback();

    public static final native long new_IStatusSetUserDefCallback();

    public static final native long new_ImageElem();

    public static final native long new_InstStatus();

    public static final native long new_InstStatusVec__SWIG_0();

    public static final native long new_InstStatusVec__SWIG_1(long j);

    public static final native long new_LocationElem();

    public static final native long new_MapKeyFetcher();

    public static final native long new_MemberInfo();

    public static final native long new_MemberResult();

    public static final native long new_ModifyGroupBaseInfoOption();

    public static final native long new_ModifyGroupMemberInfoOption();

    public static final native long new_MsgReceipt();

    public static final native long new_MsgReceiptVec__SWIG_0();

    public static final native long new_MsgReceiptVec__SWIG_1(long j);

    public static final native long new_MsgVec__SWIG_0();

    public static final native long new_MsgVec__SWIG_1(long j);

    public static final native long new_Msg__SWIG_0();

    public static final native long new_Msg__SWIG_1(long j);

    public static final native long new_NewGroupInfo();

    public static final native long new_NewGroupMemVec__SWIG_0();

    public static final native long new_NewGroupMemVec__SWIG_1(long j);

    public static final native long new_NewGroupMemberInfo();

    public static final native long new_OfflinePushInfo();

    public static final native long new_PairSession__SWIG_0();

    public static final native long new_PairSession__SWIG_1(byte[] bArr, int i);

    public static final native long new_PairSession__SWIG_2(long j, PairSession pairSession);

    public static final native long new_PairVectorSession__SWIG_0();

    public static final native long new_PairVectorSession__SWIG_1(long j);

    public static final native long new_ProfileChangeElem();

    public static final native long new_SNSProfileItem();

    public static final native long new_SNSProfileItemVec__SWIG_0();

    public static final native long new_SNSProfileItemVec__SWIG_1(long j);

    public static final native long new_SdkReportItem();

    public static final native long new_Session();

    public static final native long new_SessionUUID();

    public static final native long new_SessionUUIDVec__SWIG_0();

    public static final native long new_SessionUUIDVec__SWIG_1(long j);

    public static final native long new_SetProfileOption();

    public static final native long new_SoundElem();

    public static final native long new_StatusManager(String str);

    public static final native long new_StrVec__SWIG_0();

    public static final native long new_StrVec__SWIG_1(long j);

    public static final native long new_TextElem();

    public static final native long new_UpdateInfoOpt(long j);

    public static final native long new_UploadLogFileOpt();

    public static final native long new_UserConfig();

    public static final native long new_UserStatus();

    public static final native long new_UserStatusVec__SWIG_0();

    public static final native long new_UserStatusVec__SWIG_1(long j);

    public static final native long new_VideoElem();

    public static final native int str2FriendGender(String str);

    private static final native void swig_module_init();
}
