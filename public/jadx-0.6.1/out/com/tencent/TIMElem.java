package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.CustomElem;
import com.tencent.imcore.Elem;
import com.tencent.imcore.FaceElem;
import com.tencent.imcore.FileElem;
import com.tencent.imcore.FriendChangeInfoVec;
import com.tencent.imcore.ImageElem;
import com.tencent.imcore.LocationElem;
import com.tencent.imcore.MsgElemType;
import com.tencent.imcore.SoundElem;
import com.tencent.imcore.StrVec;
import com.tencent.imcore.TextElem;
import com.tencent.imcore.VideoElem;
import com.tencent.imsdk.QLog;
import java.io.UnsupportedEncodingException;

public abstract class TIMElem {
    private static final String tag = "imsdk.TIMElem";
    protected String identifier = "";
    protected TIMElemType type = TIMElemType.Invalid;

    static TIMImageElem buildImageElem(Elem elem) {
        TIMImageElem buildImageElem;
        Throwable th;
        String exceptionInfo;
        TIMExceptionListener exceptionListener;
        TIMImageElem tIMImageElem = new TIMImageElem();
        try {
            String str = new String(elem.getSelf_identifier(), "utf-8");
            String str2 = new String(elem.getResource(), "utf-8");
            buildImageElem = buildImageElem(elem.getImage(), str);
            try {
                buildImageElem.setIdentifier(str);
                buildImageElem.setPath(str2);
                buildImageElem.setImageFormat(elem.getImage().getFormat());
                buildImageElem.setLevel(elem.getImage().getLevel());
            } catch (Throwable th2) {
                th = th2;
                exceptionInfo = IMFunc.getExceptionInfo(th);
                QLog.e(tag, 1, exceptionInfo);
                exceptionListener = TIMManager.getInstance().getExceptionListener();
                if (exceptionListener != null) {
                    exceptionListener.onException(exceptionInfo);
                }
                return buildImageElem;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            buildImageElem = tIMImageElem;
            th = th4;
            exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(tag, 1, exceptionInfo);
            exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
            return buildImageElem;
        }
        return buildImageElem;
    }

    static TIMImageElem buildImageElem(ImageElem imageElem, String str) {
        TIMImageElem tIMImageElem = new TIMImageElem();
        if (imageElem.getOrig_url() != null && imageElem.getOrig_url().length() > 0) {
            TIMImage tIMImage = new TIMImage();
            tIMImage.setType(TIMImageType.Original);
            tIMImage.setUuid(imageElem.getFileid());
            tIMImage.setHeight((long) imageElem.getPic_height());
            tIMImage.setWidth((long) imageElem.getPic_width());
            tIMImage.setSize((long) imageElem.getPic_size());
            tIMImage.setUrl(imageElem.getOrig_url());
            tIMImage.setIdentifier(str);
            tIMImageElem.getImageList().add(tIMImage);
        }
        if (imageElem.getThumb_url() != null && imageElem.getThumb_url().length() > 0) {
            tIMImage = new TIMImage();
            tIMImage.setType(TIMImageType.Thumb);
            tIMImage.setUuid(imageElem.getFileid());
            tIMImage.setHeight((long) imageElem.getPic_thumb_height());
            tIMImage.setWidth((long) imageElem.getPic_thumb_width());
            tIMImage.setSize((long) imageElem.getPic_thumb_size());
            tIMImage.setUrl(imageElem.getThumb_url());
            tIMImage.setIdentifier(str);
            tIMImageElem.getImageList().add(tIMImage);
        }
        if (imageElem.getLarge_url() != null && imageElem.getLarge_url().length() > 0) {
            tIMImage = new TIMImage();
            tIMImage.setType(TIMImageType.Large);
            tIMImage.setUuid(imageElem.getFileid());
            tIMImage.setHeight((long) imageElem.getPic_large_height());
            tIMImage.setWidth((long) imageElem.getPic_large_width());
            tIMImage.setSize((long) imageElem.getPic_large_size());
            tIMImage.setUrl(imageElem.getLarge_url());
            tIMImage.setIdentifier(str);
            tIMImageElem.getImageList().add(tIMImage);
        }
        tIMImageElem.setTaskId(imageElem.getTaskid());
        return tIMImageElem;
    }

    static TIMVideoElem buildVideoElem(Elem elem) {
        int i = 0;
        TIMVideoElem tIMVideoElem = new TIMVideoElem();
        try {
            String str = new String(elem.getSelf_identifier(), "utf-8");
            tIMVideoElem.setIdentifier(str);
            VideoElem video = elem.getVideo();
            if (video != null) {
                tIMVideoElem.setTaskId(video.getTaskid());
                TIMVideo tIMVideo = new TIMVideo();
                tIMVideo.setDuaration(video.getVideo_duration());
                tIMVideo.setSize(video.getVideo_size());
                if (!(video.getVideo_type() == null || video.getVideoId() == null)) {
                    tIMVideo.setType(new String(video.getVideo_type(), "utf-8"));
                    tIMVideo.setUuid(new String(video.getVideoId(), "utf-8"));
                }
                tIMVideo.setIdentifier(str);
                tIMVideo.setBusinessId((long) video.getBusiness_id());
                tIMVideo.setDownloadFlag(video.getVideo_download_flag());
                StrVec video_urls = video.getVideo_urls();
                for (int i2 = 0; ((long) i2) < video_urls.size(); i2++) {
                    tIMVideo.addUrl(video_urls.get(i2));
                }
                tIMVideoElem.setVideo(tIMVideo);
                TIMSnapshot tIMSnapshot = new TIMSnapshot();
                tIMSnapshot.setSize(video.getImage_size());
                tIMSnapshot.setHeight(video.getImage_height());
                tIMSnapshot.setWidth(video.getImage_width());
                if (!(video.getImageId() == null || video.getImage_type() == null)) {
                    tIMSnapshot.setUuid(new String(video.getImageId(), "utf-8"));
                    tIMSnapshot.setType(new String(video.getImage_type(), "utf-8"));
                }
                tIMSnapshot.setIdentifier(str);
                tIMSnapshot.setBusinessId((long) video.getBusiness_id());
                tIMSnapshot.setDownloadFlag(video.getImage_download_flag());
                StrVec image_urls = video.getImage_urls();
                while (((long) i) < image_urls.size()) {
                    tIMVideo.addUrl(image_urls.get(i));
                    i++;
                }
                tIMVideoElem.setSnapshot(tIMSnapshot);
                if (!(video.getImage_path() == null || video.getVideo_path() == null)) {
                    tIMVideoElem.setSnapshotPath(new String(video.getImage_path(), "utf-8"));
                    tIMVideoElem.setVideoPath(new String(video.getVideo_path(), "utf-8"));
                }
            }
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(tag, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
        }
        return tIMVideoElem;
    }

    protected static TIMElem convertFrom(Elem elem) {
        String str;
        int i = 0;
        TIMElem cfVar = new cf();
        String str2;
        try {
            MsgElemType type = elem.getType();
            if (type == MsgElemType.kInvalid) {
                return cfVar;
            }
            TIMElem tIMTextElem;
            if (type == MsgElemType.kText) {
                tIMTextElem = new TIMTextElem();
                str = "";
                tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                str2 = new String(elem.getText().getContent(), "utf-8");
                tIMTextElem.setText(str2);
                return tIMTextElem;
            } else if (type == MsgElemType.kPicNew) {
                return buildImageElem(elem);
            } else {
                StrVec urls;
                if (type == MsgElemType.kPttNew) {
                    tIMTextElem = new TIMSoundElem();
                    tIMTextElem.setUuid(elem.getSound().getFileid());
                    tIMTextElem.setDataSize((long) elem.getSound().getFile_size());
                    tIMTextElem.setDuration((long) elem.getSound().getFile_time());
                    tIMTextElem.setTaskId(elem.getSound().getTaskid());
                    tIMTextElem.setBusinessId((long) elem.getSound().getBusiness_id());
                    tIMTextElem.setDownloadFlag(elem.getSound().getDownload_flag());
                    urls = elem.getSound().getUrls();
                    while (((long) i) < urls.size()) {
                        tIMTextElem.addUrl(urls.get(i));
                        i++;
                    }
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                        tIMTextElem.setPath(new String(elem.getSound().getFile_path(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return tIMTextElem;
                } else if (type == MsgElemType.kFile) {
                    tIMTextElem = new TIMFileElem();
                    tIMTextElem.setUuid(elem.getFile().getFileid());
                    tIMTextElem.setFileSize(elem.getFile().getFile_size());
                    tIMTextElem.setTaskId(elem.getFile().getTaskid());
                    tIMTextElem.setBusinessId((long) elem.getFile().getBusiness_id());
                    tIMTextElem.setDownloadFlag(elem.getFile().getDownload_flag());
                    urls = elem.getFile().getUrls();
                    while (((long) i) < urls.size()) {
                        tIMTextElem.addUrl(urls.get(i));
                        i++;
                    }
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                        tIMTextElem.setFileName(new String(elem.getFile().getFile_name(), "utf-8"));
                        tIMTextElem.setPath(new String(elem.getFile().getFile_path(), "utf-8"));
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    return tIMTextElem;
                } else if (type == MsgElemType.kLocation) {
                    tIMTextElem = new TIMLocationElem();
                    tIMTextElem.setLongitude(elem.getLocation().getLongitude());
                    tIMTextElem.setLatitude(elem.getLocation().getLatitude());
                    str = "";
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                        str2 = new String(elem.getLocation().getDesc(), "utf-8");
                    } catch (UnsupportedEncodingException e22) {
                        e22.printStackTrace();
                        str2 = str;
                    }
                    tIMTextElem.setDesc(str2);
                    return tIMTextElem;
                } else if (type == MsgElemType.kCustom) {
                    tIMTextElem = new TIMCustomElem();
                    byte[] ext = elem.getCustom().getExt();
                    if (ext != null && ext.length > 0) {
                        tIMTextElem.setExt(ext);
                    }
                    tIMTextElem.setData(elem.getCustom().getData());
                    tIMTextElem.setSound(elem.getCustom().getSound());
                    tIMTextElem.setExt(elem.getCustom().getExt());
                    str = "";
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                        str2 = new String(elem.getCustom().getDesc(), "utf-8");
                    } catch (UnsupportedEncodingException e222) {
                        e222.printStackTrace();
                        str2 = str;
                    }
                    tIMTextElem.setDesc(str2);
                    return tIMTextElem;
                } else if (type == MsgElemType.kFace) {
                    tIMTextElem = new TIMFaceElem();
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                    } catch (UnsupportedEncodingException e2222) {
                        e2222.printStackTrace();
                    }
                    tIMTextElem.setData(elem.getFace().getBuf());
                    tIMTextElem.setIndex(elem.getFace().getIndex());
                    return tIMTextElem;
                } else if (type == MsgElemType.kGroupTips) {
                    tIMTextElem = new TIMGroupTipsElem(elem.getGroup_tips());
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                    } catch (UnsupportedEncodingException e22222) {
                        e22222.printStackTrace();
                    }
                    return tIMTextElem;
                } else if (type == MsgElemType.kGroupReport) {
                    tIMTextElem = new TIMGroupSystemElem(elem.getGroup_report());
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                    } catch (UnsupportedEncodingException e222222) {
                        e222222.printStackTrace();
                    }
                    return tIMTextElem;
                } else if (type == MsgElemType.kFriendChange) {
                    tIMTextElem = new TIMSNSSystemElem();
                    tIMTextElem.setSubType(elem.getFriend_change().getType());
                    FriendChangeInfoVec users = elem.getFriend_change().getUsers();
                    while (((long) i) < users.size()) {
                        TIMSNSChangeInfo tIMSNSChangeInfo = new TIMSNSChangeInfo(users.get(i));
                        tIMTextElem.setPendencyReportTimestamp(elem.getFriend_change().getDdwPendencyReportTimestamp());
                        tIMTextElem.setDecideReportTimestamp(elem.getFriend_change().getDdwDecideReportTimestamp());
                        tIMTextElem.setRecommendReportTimestamp(elem.getFriend_change().getDdwRecommendReportTimestamp());
                        tIMTextElem.getChangeInfoList().add(tIMSNSChangeInfo);
                        i++;
                    }
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                    } catch (UnsupportedEncodingException e2222222) {
                        e2222222.printStackTrace();
                    }
                    return tIMTextElem;
                } else if (type != MsgElemType.kProfileChange) {
                    return type == MsgElemType.kVideo ? buildVideoElem(elem) : cfVar;
                } else {
                    tIMTextElem = new TIMProfileSystemElem();
                    tIMTextElem.setSubType(elem.getProfile_change().getType());
                    tIMTextElem.setFromUser(elem.getProfile_change().getFrom());
                    str = "";
                    try {
                        tIMTextElem.setIdentifier(new String(elem.getSelf_identifier(), "utf-8"));
                        str2 = new String(elem.getProfile_change().getNick(), "utf-8");
                    } catch (UnsupportedEncodingException e22222222) {
                        e22222222.printStackTrace();
                        str2 = str;
                    }
                    tIMTextElem.setNickName(str2);
                    return tIMTextElem;
                }
            }
        } catch (UnsupportedEncodingException e222222222) {
            e222222222.printStackTrace();
            str2 = str;
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(tag, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener == null) {
                return cfVar;
            }
            exceptionListener.onException(exceptionInfo);
            return cfVar;
        }
    }

    protected Elem convertTo() {
        if (getType() == TIMElemType.Invalid) {
            return null;
        }
        Elem elem = new Elem();
        if (getType() == TIMElemType.Text) {
            elem.setType(MsgElemType.kText);
            TextElem textElem = new TextElem();
            try {
                textElem.setContent(((TIMTextElem) this).getText().getBytes("utf-8"));
                elem.setText(textElem);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (getType() == TIMElemType.Image) {
            elem.setType(MsgElemType.kPicNew);
            ImageElem imageElem = new ImageElem();
            TIMImageElem tIMImageElem = (TIMImageElem) this;
            if (tIMImageElem.getPath() == null) {
                return null;
            }
            imageElem.setLevel(tIMImageElem.getLevel());
            imageElem.setFormat(tIMImageElem.getImageFormat());
            try {
                elem.setResource(tIMImageElem.getPath().getBytes("utf-8"));
                elem.setImage(imageElem);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        } else if (getType() == TIMElemType.Sound) {
            elem.setType(MsgElemType.kPttNew);
            SoundElem soundElem = new SoundElem();
            TIMSoundElem tIMSoundElem = (TIMSoundElem) this;
            if (TextUtils.isEmpty(tIMSoundElem.getPath()) && tIMSoundElem.getData() == null) {
                return null;
            }
            if (tIMSoundElem.getData() != null) {
                elem.setResource(tIMSoundElem.getData());
            }
            soundElem.setFile_time((int) tIMSoundElem.getDuration());
            soundElem.setFile_size((int) tIMSoundElem.getDataSize());
            if (!TextUtils.isEmpty(tIMSoundElem.getPath())) {
                try {
                    soundElem.setFile_path(tIMSoundElem.getPath().getBytes("utf-8"));
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                }
            }
            elem.setSound(soundElem);
        } else if (getType() == TIMElemType.File) {
            elem.setType(MsgElemType.kFile);
            FileElem fileElem = new FileElem();
            TIMFileElem tIMFileElem = (TIMFileElem) this;
            if (TextUtils.isEmpty(tIMFileElem.getPath()) && tIMFileElem.getData() == null) {
                return null;
            }
            if (tIMFileElem.getData() != null) {
                elem.setResource(tIMFileElem.getData());
            }
            try {
                if (!TextUtils.isEmpty(tIMFileElem.getPath())) {
                    fileElem.setFile_path(tIMFileElem.getPath().getBytes("utf-8"));
                }
                if (!TextUtils.isEmpty(tIMFileElem.getFileName())) {
                    fileElem.setFile_name(tIMFileElem.getFileName().getBytes("utf-8"));
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            fileElem.setFile_size(tIMFileElem.getFileSize());
            elem.setFile(fileElem);
        } else if (getType() == TIMElemType.Custom) {
            elem.setType(MsgElemType.kCustom);
            CustomElem customElem = new CustomElem();
            TIMCustomElem tIMCustomElem = (TIMCustomElem) this;
            customElem.setData(tIMCustomElem.getData());
            customElem.setExt(tIMCustomElem.getExt());
            customElem.setSound(tIMCustomElem.getSound());
            try {
                customElem.setDesc(tIMCustomElem.getDesc().getBytes("utf-8"));
            } catch (Exception e42) {
                e42.printStackTrace();
            }
            elem.setCustom(customElem);
        } else if (getType() == TIMElemType.Location) {
            elem.setType(MsgElemType.kLocation);
            LocationElem locationElem = new LocationElem();
            TIMLocationElem tIMLocationElem = (TIMLocationElem) this;
            try {
                locationElem.setDesc(tIMLocationElem.getDesc().getBytes("utf-8"));
            } catch (Exception e422) {
                e422.printStackTrace();
            }
            locationElem.setLatitude(tIMLocationElem.getLatitude());
            locationElem.setLongitude(tIMLocationElem.getLongitude());
            elem.setLocation(locationElem);
        } else if (getType() == TIMElemType.Face) {
            elem.setType(MsgElemType.kFace);
            FaceElem faceElem = new FaceElem();
            TIMFaceElem tIMFaceElem = (TIMFaceElem) this;
            faceElem.setIndex(tIMFaceElem.getIndex());
            if (tIMFaceElem.getData() != null) {
                faceElem.setBuf(tIMFaceElem.getData());
            }
            elem.setFace(faceElem);
        } else if (getType() != TIMElemType.Video) {
            return null;
        } else {
            elem.setType(MsgElemType.kVideo);
            VideoElem videoElem = new VideoElem();
            TIMVideoElem tIMVideoElem = (TIMVideoElem) this;
            if (tIMVideoElem.getSnapshotPath() == null || tIMVideoElem.getSnapshotPath().length() == 0 || tIMVideoElem.getSnapshotInfo() == null || tIMVideoElem.getSnapshotInfo().getType() == null || tIMVideoElem.getVideoPath() == null || tIMVideoElem.getVideoPath().length() == 0 || tIMVideoElem.getVideoInfo() == null || tIMVideoElem.getVideoInfo().getType() == null) {
                return null;
            }
            try {
                videoElem.setVideo_path(tIMVideoElem.getVideoPath().getBytes("utf-8"));
                videoElem.setImage_path(tIMVideoElem.getSnapshotPath().getBytes("utf-8"));
                videoElem.setImage_type(tIMVideoElem.getSnapshotInfo().getType().getBytes("utf-8"));
                videoElem.setVideo_type(tIMVideoElem.getVideoInfo().getType().getBytes("utf-8"));
                elem.setResource(TIMManager.getInstance().getVideoCachePath().getBytes("utf-8"));
            } catch (UnsupportedEncodingException e32) {
                e32.printStackTrace();
            }
            videoElem.setImage_height(tIMVideoElem.getSnapshotInfo().getHeight());
            videoElem.setImage_width(tIMVideoElem.getSnapshotInfo().getWidth());
            videoElem.setVideo_duration(tIMVideoElem.getVideoInfo().getDuaration());
            elem.setVideo(videoElem);
        }
        return elem;
    }

    public TIMElemType getType() {
        return this.type;
    }

    protected void setIdentifier(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.identifier = str;
        }
    }
}
