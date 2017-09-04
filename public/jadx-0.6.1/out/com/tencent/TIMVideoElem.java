package com.tencent;

public class TIMVideoElem extends TIMElem {
    private TIMSnapshot snapshot;
    private String snapshotPath;
    private long taskId;
    private TIMVideo video;
    private String videoPath;

    public TIMVideoElem() {
        this.taskId = 0;
        this.videoPath = "";
        this.snapshotPath = "";
        this.type = TIMElemType.Video;
    }

    public TIMSnapshot getSnapshotInfo() {
        return this.snapshot;
    }

    public String getSnapshotPath() {
        return this.snapshotPath;
    }

    public long getTaskId() {
        return this.taskId;
    }

    public int getUploadingProgress() {
        return (!IMCoreWrapper.get().isReady() || this.taskId == 0) ? 0 : TIMManager.getInstance().getCoreUser().getImageUploadProgrss(this.taskId);
    }

    public TIMVideo getVideoInfo() {
        return this.video;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setSnapshot(TIMSnapshot tIMSnapshot) {
        this.snapshot = tIMSnapshot;
    }

    public void setSnapshotPath(String str) {
        this.snapshotPath = str;
    }

    void setTaskId(long j) {
        this.taskId = j;
    }

    public void setVideo(TIMVideo tIMVideo) {
        this.video = tIMVideo;
    }

    public void setVideoPath(String str) {
        this.videoPath = str;
    }
}
