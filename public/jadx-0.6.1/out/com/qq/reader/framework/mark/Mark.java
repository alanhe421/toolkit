package com.qq.reader.framework.mark;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.imageloader.a.a.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.filebrowser.c;
import com.qq.reader.module.bookstore.qnative.b.b;
import java.io.Serializable;

public abstract class Mark implements Serializable {
    public static final int DESCRIPTIONLENGTH = 20;
    public static final String HEADPAGE_FLAG = "headpage";
    public static final int TYPE_AUTO_DOWNLOAD = 3;
    public static final int TYPE_AUTO_LOCAL = 1;
    public static final int TYPE_AUTO_ONLINE = 4;
    public static final int TYPE_CHAPTER_LOCAL = 2;
    public static final int TYPE_COMIC = 9;
    public static final int TYPE_MUSIC = 6;
    public static final int TYPE_RECOMMEND = 5;
    public static final int TYPE_TING = 8;
    public static final int TYPE_UNUSE = -1;
    public static final int TYPE_USER_LOCAL = 0;
    public static final int TYPE_USER_ONLINE = 7;
    private static final long serialVersionUID = -3433949296540821610L;
    protected String imageUri;
    protected String mAuthor = "";
    protected long mBookId;
    protected String mBookName = "";
    protected int mCategoryID = i.b;
    private int mChapterMarkLevel = 1;
    private int mCloudDelTag = 0;
    protected String mCoverPath;
    protected String mCoverUrl;
    private String mCpInfo;
    protected String mDataStr = "";
    protected long mDateTime = 0;
    protected String mDescriptionStr = "";
    private int mDiscount = 100;
    private String mDownloadUrl;
    protected int mEncoding = -1;
    protected long mFileLength = 0;
    protected b mHardCoverChecker;
    private boolean mHasNewContent;
    protected String mId;
    private int mIsFinished = 0;
    protected boolean mIsLastRead = false;
    private String mLastReadChapterName = "";
    private String mLastUpdateChapter = "";
    private long mLastUpdateTime;
    private String mLimitFreeEndTime;
    private String mNetChannel = "";
    protected long mOperateTime = 0;
    protected String mPercentStr = "";
    protected String mPinyinAuthor = "";
    protected String mPinyinAuthorAll = "";
    protected String mPinyinBookName = "";
    protected String mPinyinBookNameAll = "";
    private int mPrivateProperty = 1;
    protected long mReadTime = 0;
    private int mSortIndex;
    protected String mStarPointStr = null;
    protected long mStartPoint;
    private int mSynBook = 0;
    private int mTotalChapterCount;
    private float mTurePageBytes = -1.0f;
    private long mTurePageCurIndex = -1;
    private int mTurePageFont = -1;
    protected int mType = 0;

    public Mark setPrivateProperty(int i) {
        this.mPrivateProperty = i;
        return this;
    }

    public int getPrivateProperty() {
        return this.mPrivateProperty;
    }

    public String getLastReadChapterName() {
        return this.mLastReadChapterName;
    }

    public Mark setLastReadChapterName(String str) {
        this.mLastReadChapterName = str;
        return this;
    }

    public int getSynBook() {
        return this.mSynBook;
    }

    public Mark setSynBook(int i) {
        this.mSynBook = i;
        return this;
    }

    public Mark setDownloadInfo(String str) {
        if (this.mHardCoverChecker == null) {
            this.mHardCoverChecker = new b();
        }
        this.mHardCoverChecker.a(str);
        return this;
    }

    public b getHardCoverChecker() {
        return this.mHardCoverChecker;
    }

    public String getDownloadInfo() {
        if (this.mHardCoverChecker != null) {
            return this.mHardCoverChecker.j();
        }
        return "";
    }

    public boolean isHardCoverBook() {
        if (this.mHardCoverChecker != null) {
            return this.mHardCoverChecker.a();
        }
        return false;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    public Mark setBookId(long j) {
        this.mBookId = j;
        return this;
    }

    public long getBookId() {
        return this.mBookId;
    }

    public String getNetChannelId() {
        return this.mNetChannel;
    }

    public Mark setNetChannel(String str) {
        if (str != null) {
            this.mNetChannel = str;
        } else {
            this.mNetChannel = "";
        }
        return this;
    }

    public Mark setFinished(int i) {
        this.mIsFinished = i;
        return this;
    }

    public int getIsFinish() {
        return this.mIsFinished;
    }

    public Mark setHasNewContent(boolean z) {
        this.mHasNewContent = z;
        return this;
    }

    public boolean hasNewContent() {
        return this.mHasNewContent;
    }

    public int getType() {
        return this.mType;
    }

    public long getStartPoint() {
        return this.mStartPoint;
    }

    public Mark setStartPoint(long j) {
        this.mStartPoint = j;
        return this;
    }

    public String getStarPointStr() {
        return this.mStarPointStr;
    }

    public Mark setStarPointStr(String str) {
        this.mStarPointStr = str;
        return this;
    }

    public int getEncoding() {
        return this.mEncoding;
    }

    public Mark setEncoding(int i) {
        this.mEncoding = i;
        return this;
    }

    public String getDescriptionStr() {
        return this.mDescriptionStr;
    }

    public String getPercentStr() {
        if (this.mId != null && this.mId.length() > 0 && (this.mId.endsWith(".doc") || this.mId.endsWith(".docx") || this.mId.endsWith(".ppt") || this.mId.endsWith(".pptx") || this.mId.endsWith(".xlsx") || this.mId.endsWith(".xls"))) {
            return "";
        }
        if (this.mPercentStr.equals("0.0%")) {
            return "未读";
        }
        if (this.mPercentStr.equals("")) {
            return getStrExPercent();
        }
        return this.mPercentStr;
    }

    private String getStrExPercent() {
        return "第" + (this.mStartPoint + 1) + "页";
    }

    public String getDataStr() {
        return this.mDataStr;
    }

    public Mark setDescriptionStr(String str) {
        if (str != null) {
            this.mDescriptionStr = str;
        }
        return this;
    }

    public Mark setPercentStr(String str) {
        if (str != null) {
            this.mPercentStr = str;
        }
        return this;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public Mark setAuthor(String str) {
        if (str != null && str.equalsIgnoreCase("匿名")) {
            str = " ";
        }
        this.mAuthor = str;
        return this;
    }

    public boolean isLastRead() {
        return this.mIsLastRead;
    }

    public void setLastRead(boolean z) {
        this.mIsLastRead = z;
    }

    public Mark setReadTime(long j) {
        this.mReadTime = j;
        return this;
    }

    public long getReadTime() {
        return this.mReadTime;
    }

    public Mark setOperateTime(long j) {
        this.mOperateTime = j;
        return this;
    }

    public long getOperateTime() {
        return this.mOperateTime;
    }

    public long getFileLength() {
        return this.mFileLength;
    }

    public void setFileLength(long j) {
        this.mFileLength = j;
    }

    public String getBookName() {
        return this.mBookName;
    }

    public Mark setBookName(String str) {
        this.mBookName = str;
        return this;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.mDownloadUrl = str;
    }

    public void generatePinyin() {
        if (this.mPinyinBookName == null || this.mPinyinBookName.length() == 0) {
            this.mPinyinBookName = com.qq.reader.filebrowser.b.a(this.mBookName);
        }
        if (this.mPinyinBookNameAll == null || this.mPinyinBookNameAll.length() == 0) {
            this.mPinyinBookNameAll = c.a(this.mBookName, false);
        }
        if (this.mPinyinAuthor == null || this.mPinyinAuthor.length() == 0) {
            this.mPinyinAuthor = com.qq.reader.filebrowser.b.a(this.mAuthor);
        }
        if (this.mPinyinAuthorAll == null || this.mPinyinAuthorAll.length() == 0) {
            this.mPinyinAuthorAll = c.a(this.mAuthor, false);
        }
    }

    public String getBookShortName() {
        String str = "";
        if (this.mBookName == null) {
            return str;
        }
        int lastIndexOf = this.mBookName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return this.mBookName;
        }
        String substring = this.mBookName.substring(lastIndexOf);
        if (substring.contains("chm") || substring.contains("epub") || substring.contains("excel") || substring.contains("mp3") || substring.contains("pdf") || substring.contains("ppt") || substring.contains("rar") || substring.contains("teb") || substring.contains("txt") || substring.contains("umd") || substring.contains("word") || substring.contains("trial") || substring.contains("qteb") || substring.contains("zip")) {
            return this.mBookName.substring(0, lastIndexOf);
        }
        return this.mBookName;
    }

    public Mark setCategoryID(int i) {
        this.mCategoryID = i;
        return this;
    }

    public int getCategoryID() {
        return this.mCategoryID;
    }

    public Mark setTurePageBytes(float f) {
        this.mTurePageBytes = f;
        return this;
    }

    public float getTurePageBytes() {
        return this.mTurePageBytes;
    }

    public Mark setTurePageFont(int i) {
        this.mTurePageFont = i;
        return this;
    }

    public int getTurePageFont() {
        return this.mTurePageFont;
    }

    public Mark setTurePageCurIndex(long j) {
        this.mTurePageCurIndex = j;
        return this;
    }

    public long getTurePageCurIndex() {
        return this.mTurePageCurIndex;
    }

    public boolean equals(Object obj) {
        return getId().equals(((Mark) obj).getId());
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public String getImageURI() {
        if (this.mCoverUrl == null || this.mCoverUrl.length() == 0) {
            this.mCoverUrl = ao.g(getBookId());
            if (TextUtils.isEmpty(this.mCoverUrl)) {
                this.mCoverUrl = a.b(1, getBookShortName());
            }
        }
        return this.mCoverUrl;
    }

    public String getImagePath() {
        if (this.mCoverUrl == null || this.mCoverUrl.length() == 0) {
            this.mCoverUrl = ao.g(getBookId());
            if (TextUtils.isEmpty(this.mCoverUrl)) {
                this.mCoverUrl = a.b(1, getBookShortName());
            }
        }
        return this.mCoverUrl;
    }

    public Bitmap getBookLocalBitmap() {
        String imagePath = getImagePath();
        if (imagePath == null || imagePath.length() <= 0) {
            return null;
        }
        Options options = new Options();
        options.inPurgeable = true;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    public Drawable getBookDrawable() {
        String imagePath = getImagePath();
        if (imagePath == null || imagePath.length() <= 0) {
            return null;
        }
        return Drawable.createFromPath(getImagePath());
    }

    public long getLastUpdateTime() {
        return this.mLastUpdateTime;
    }

    public long getSortValue() {
        if (this.mLastUpdateTime == 0) {
            return Long.MIN_VALUE;
        }
        return this.mLastUpdateTime;
    }

    public Mark setLastUpdateTime(long j) {
        this.mLastUpdateTime = j;
        return this;
    }

    public String getLastUpdateChapter() {
        if (this.mLastUpdateChapter == null) {
            return "";
        }
        return this.mLastUpdateChapter;
    }

    public Mark setLastUpdateChapter(String str) {
        this.mLastUpdateChapter = str;
        return this;
    }

    public int getSortIndex() {
        return this.mSortIndex;
    }

    public Mark setSortIndex(int i) {
        this.mSortIndex = i;
        return this;
    }

    public Mark setCoverUrl(String str) {
        if (str == null) {
            this.mCoverUrl = "";
        } else {
            this.mCoverUrl = str;
        }
        return this;
    }

    public Mark setCpInfo(String str) {
        if (str == null) {
            this.mCpInfo = "";
        } else {
            this.mCpInfo = str;
        }
        return this;
    }

    public String getCpInfo() {
        return this.mCpInfo;
    }

    public void setPinyinBookName(String str) {
        this.mPinyinBookName = str;
    }

    public String getPinyinBookName() {
        return this.mPinyinBookName;
    }

    public String getPinyinBookNameAll() {
        return this.mPinyinBookNameAll;
    }

    public String getPinyinAuthor() {
        return this.mPinyinAuthor;
    }

    public String getPinyinAuthorAll() {
        return this.mPinyinAuthorAll;
    }

    public void setPinyinAuthor(String str) {
        this.mPinyinAuthor = str;
    }

    public boolean isFree() {
        String str = this.mPercentStr;
        if (str == null || !str.equals("1")) {
            return false;
        }
        return true;
    }

    public int getTotalChapterCount() {
        return this.mTotalChapterCount;
    }

    public Mark setTotalChapterCount(int i) {
        this.mTotalChapterCount = i;
        return this;
    }

    public int getChapterMarkLevel() {
        return this.mChapterMarkLevel;
    }

    public Mark setChapterMarkLevel(int i) {
        if (i > 1) {
            this.mChapterMarkLevel = i;
        }
        return this;
    }

    public String getLimitFreeEndTime() {
        return this.mLimitFreeEndTime;
    }

    public Mark setLimitFreeEndTime(String str) {
        this.mLimitFreeEndTime = str;
        return this;
    }

    public int getDiscount() {
        return this.mDiscount;
    }

    public Mark setDiscount(int i) {
        this.mDiscount = i;
        return this;
    }

    public boolean isLimitFree() {
        if (getDiscount() != 0) {
            return false;
        }
        if (System.currentTimeMillis() < ao.h(getLimitFreeEndTime()).longValue()) {
            return true;
        }
        return false;
    }

    public int getCloudDelTag() {
        return this.mCloudDelTag;
    }

    public Mark setCloudDelTag(int i) {
        this.mCloudDelTag = i;
        return this;
    }
}
