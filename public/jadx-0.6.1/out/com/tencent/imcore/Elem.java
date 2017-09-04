package com.tencent.imcore;

public class Elem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Elem() {
        this(internalJNI.new_Elem(), true);
    }

    protected Elem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Elem elem) {
        return elem == null ? 0 : elem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_Elem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public CustomElem getCustom() {
        long Elem_custom_get = internalJNI.Elem_custom_get(this.swigCPtr, this);
        return Elem_custom_get == 0 ? null : new CustomElem(Elem_custom_get, false);
    }

    public FaceElem getFace() {
        long Elem_face_get = internalJNI.Elem_face_get(this.swigCPtr, this);
        return Elem_face_get == 0 ? null : new FaceElem(Elem_face_get, false);
    }

    public FileElem getFile() {
        long Elem_file_get = internalJNI.Elem_file_get(this.swigCPtr, this);
        return Elem_file_get == 0 ? null : new FileElem(Elem_file_get, false);
    }

    public FriendChangeElem getFriend_change() {
        long Elem_friend_change_get = internalJNI.Elem_friend_change_get(this.swigCPtr, this);
        return Elem_friend_change_get == 0 ? null : new FriendChangeElem(Elem_friend_change_get, false);
    }

    public GroupReportElem getGroup_report() {
        long Elem_group_report_get = internalJNI.Elem_group_report_get(this.swigCPtr, this);
        return Elem_group_report_get == 0 ? null : new GroupReportElem(Elem_group_report_get, false);
    }

    public GroupTipsElem getGroup_tips() {
        long Elem_group_tips_get = internalJNI.Elem_group_tips_get(this.swigCPtr, this);
        return Elem_group_tips_get == 0 ? null : new GroupTipsElem(Elem_group_tips_get, false);
    }

    public ImageElem getImage() {
        long Elem_image_get = internalJNI.Elem_image_get(this.swigCPtr, this);
        return Elem_image_get == 0 ? null : new ImageElem(Elem_image_get, false);
    }

    public LocationElem getLocation() {
        long Elem_location_get = internalJNI.Elem_location_get(this.swigCPtr, this);
        return Elem_location_get == 0 ? null : new LocationElem(Elem_location_get, false);
    }

    public ProfileChangeElem getProfile_change() {
        long Elem_profile_change_get = internalJNI.Elem_profile_change_get(this.swigCPtr, this);
        return Elem_profile_change_get == 0 ? null : new ProfileChangeElem(Elem_profile_change_get, false);
    }

    public byte[] getResource() {
        return internalJNI.Elem_resource_get(this.swigCPtr, this);
    }

    public byte[] getSelf_identifier() {
        return internalJNI.Elem_self_identifier_get(this.swigCPtr, this);
    }

    public SoundElem getSound() {
        long Elem_sound_get = internalJNI.Elem_sound_get(this.swigCPtr, this);
        return Elem_sound_get == 0 ? null : new SoundElem(Elem_sound_get, false);
    }

    public TextElem getText() {
        long Elem_text_get = internalJNI.Elem_text_get(this.swigCPtr, this);
        return Elem_text_get == 0 ? null : new TextElem(Elem_text_get, false);
    }

    public MsgElemType getType() {
        return MsgElemType.swigToEnum(internalJNI.Elem_type_get(this.swigCPtr, this));
    }

    public VideoElem getVideo() {
        long Elem_video_get = internalJNI.Elem_video_get(this.swigCPtr, this);
        return Elem_video_get == 0 ? null : new VideoElem(Elem_video_get, false);
    }

    public void setCustom(CustomElem customElem) {
        internalJNI.Elem_custom_set(this.swigCPtr, this, CustomElem.getCPtr(customElem), customElem);
    }

    public void setFace(FaceElem faceElem) {
        internalJNI.Elem_face_set(this.swigCPtr, this, FaceElem.getCPtr(faceElem), faceElem);
    }

    public void setFile(FileElem fileElem) {
        internalJNI.Elem_file_set(this.swigCPtr, this, FileElem.getCPtr(fileElem), fileElem);
    }

    public void setFriend_change(FriendChangeElem friendChangeElem) {
        internalJNI.Elem_friend_change_set(this.swigCPtr, this, FriendChangeElem.getCPtr(friendChangeElem), friendChangeElem);
    }

    public void setGroup_report(GroupReportElem groupReportElem) {
        internalJNI.Elem_group_report_set(this.swigCPtr, this, GroupReportElem.getCPtr(groupReportElem), groupReportElem);
    }

    public void setGroup_tips(GroupTipsElem groupTipsElem) {
        internalJNI.Elem_group_tips_set(this.swigCPtr, this, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
    }

    public void setImage(ImageElem imageElem) {
        internalJNI.Elem_image_set(this.swigCPtr, this, ImageElem.getCPtr(imageElem), imageElem);
    }

    public void setLocation(LocationElem locationElem) {
        internalJNI.Elem_location_set(this.swigCPtr, this, LocationElem.getCPtr(locationElem), locationElem);
    }

    public void setProfile_change(ProfileChangeElem profileChangeElem) {
        internalJNI.Elem_profile_change_set(this.swigCPtr, this, ProfileChangeElem.getCPtr(profileChangeElem), profileChangeElem);
    }

    public void setResource(byte[] bArr) {
        internalJNI.Elem_resource_set(this.swigCPtr, this, bArr);
    }

    public void setSelf_identifier(byte[] bArr) {
        internalJNI.Elem_self_identifier_set(this.swigCPtr, this, bArr);
    }

    public void setSound(SoundElem soundElem) {
        internalJNI.Elem_sound_set(this.swigCPtr, this, SoundElem.getCPtr(soundElem), soundElem);
    }

    public void setText(TextElem textElem) {
        internalJNI.Elem_text_set(this.swigCPtr, this, TextElem.getCPtr(textElem), textElem);
    }

    public void setType(MsgElemType msgElemType) {
        internalJNI.Elem_type_set(this.swigCPtr, this, msgElemType.swigValue());
    }

    public void setVideo(VideoElem videoElem) {
        internalJNI.Elem_video_set(this.swigCPtr, this, VideoElem.getCPtr(videoElem), videoElem);
    }
}
