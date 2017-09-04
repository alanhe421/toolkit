package com.qq.reader.readengine.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class IBook implements Serializable {
    public static final int TYPE_LOCAL = 0;
    public static final int TYPE_LOCAL_NEED_BUY = 2;
    public static final int TYPE_ONLINE = 1;
    public static List<b> mRemarksList = new ArrayList();
    public static List<b> mSearchList = new ArrayList();
    private static final long serialVersionUID = -1023439320188377494L;
    private int encrypted_flag = 2;
    private String imageUri;
    protected String mAuthor = "";
    private Drawable mAuthorIconDrawable;
    private String mAuthorId;
    private String mBookLocalId;
    protected String mBookName = "";
    private long mBookNetId = 0;
    protected String mBookPath = "";
    private a mBookVoteInfo;
    private Mark[] mChapterMarks = null;
    private String mCoverPath = null;
    private byte[] mCovers;
    public long mCurBufferPageIndex = -1;
    protected int mEncoding = -1;
    private String mEncodingStr = null;
    private boolean mHasRedpacket = false;
    private Bitmap mHeadPageBitmapRef;
    protected long mLength = 0;
    private MulitFile mMulitFile = null;
    private int mReadType = 0;
    protected String mSoSoUrl = "";
    public float mTruePageBytes = 0.0f;
    public int mTruePageFont = 0;
    public int mTurePageCmd = -1;

    public class a {
        boolean a;
        boolean b;
        boolean c;
        final /* synthetic */ IBook d;

        public a(IBook iBook, boolean z, boolean z2, boolean z3) {
            this.d = iBook;
            this.a = z;
            this.b = z2;
            this.c = z3;
        }

        public boolean a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }
    }

    public abstract boolean isAutoParserChapter();

    public String getImagePath() {
        if (this.mCoverPath == null) {
            this.mCoverPath = ao.g(this.mBookNetId);
            if (TextUtils.isEmpty(this.mCoverPath)) {
                this.mCoverPath = com.qq.reader.common.imageloader.a.a.a.b(1, getBookShortName());
            }
        }
        return this.mCoverPath;
    }

    public String getImageURI() {
        if (this.imageUri == null) {
            this.imageUri = ao.g(this.mBookNetId);
            if (TextUtils.isEmpty(this.imageUri)) {
                this.imageUri = com.qq.reader.common.imageloader.a.a.a.b(1, getBookShortName());
            }
        }
        return this.imageUri;
    }

    public String getBookPath() {
        return this.mBookPath;
    }

    public IBook setBookPath(String str) {
        this.mBookPath = str;
        return this;
    }

    public String getBookName() {
        return this.mBookName;
    }

    public String getBookShortName() {
        if (this.mBookName == null) {
            return null;
        }
        int lastIndexOf = this.mBookName.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return this.mBookName.substring(0, lastIndexOf);
        }
        return this.mBookName;
    }

    public IBook setBookName(String str) {
        if (str == null) {
            str = "";
        }
        this.mBookName = str;
        return this;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public void setAuthor(String str) {
        if (str == null) {
            str = "";
        }
        this.mAuthor = str;
    }

    public int getEncoding() {
        return this.mEncoding;
    }

    public void setEncoding(int i) {
        this.mEncoding = i;
    }

    public String getEncodingStr() {
        return this.mEncodingStr;
    }

    public void setEncodingStr(String str) {
        if (str == null) {
            str = "";
        }
        this.mEncodingStr = str;
    }

    public byte[] getCovers() {
        return this.mCovers;
    }

    public void setCovers(byte[] bArr) {
        this.mCovers = bArr;
    }

    public long getLength() {
        return this.mLength;
    }

    public void setLength(long j) {
        this.mLength = j;
    }

    public long getBookWordsNum() {
        if (this.mEncoding == 4) {
            return this.mLength / 3;
        }
        if (this.mEncoding == 1 || this.mEncoding == 8 || this.mEncoding == 14 || this.mEncoding == 12) {
            return this.mLength / 2;
        }
        return this.mLength;
    }

    public int getEncrypted_flag() {
        return this.encrypted_flag;
    }

    public void setEncrypted_flag(int i) {
        this.encrypted_flag = i;
    }

    public String getBookLocalId() {
        return this.mBookLocalId;
    }

    public void setBookLocalId(String str) {
        this.mBookLocalId = str;
    }

    public String getSoSoUrl() {
        return this.mSoSoUrl;
    }

    public void setSoSoUrl(String str) {
        this.mSoSoUrl = str;
    }

    public long getBookNetId() {
        return this.mBookNetId;
    }

    public void setBookNetId(long j) {
        this.mBookNetId = j;
    }

    public int getReadType() {
        return this.mReadType;
    }

    public void setReadType(int i) {
        this.mReadType = i;
    }

    public void createMulitFile(int i) {
        this.mMulitFile = new MulitFile(getBookPath(), i);
    }

    public int getFileCount() {
        if (this.mMulitFile == null) {
            return 1;
        }
        return this.mMulitFile.getListCount();
    }

    public MulitFile getMulitFile() {
        return this.mMulitFile;
    }

    public void setmMulitFile(MulitFile mulitFile) {
        this.mMulitFile = mulitFile;
    }

    public int getCurIndex() {
        if (this.mMulitFile == null) {
            return 0;
        }
        return this.mMulitFile.getIndex();
    }

    public synchronized void initFileList(OnlineTag onlineTag, boolean z) {
        this.mMulitFile.initFileList(onlineTag, z);
    }

    public synchronized void initFileList(OnlineTag onlineTag) {
        this.mMulitFile.initFileList(onlineTag);
    }

    public d getBookTailInfo() {
        if (this.mMulitFile != null) {
            return this.mMulitFile.getBookTailInfo();
        }
        return null;
    }

    public a getBookVoteInfo() {
        return this.mBookVoteInfo;
    }

    public void setBookVoteInfo(boolean z, boolean z2, boolean z3) {
        this.mBookVoteInfo = new a(this, z, z2, z3);
    }

    public Bitmap getHeadPageBitmap() {
        if (this.mHeadPageBitmapRef == null || this.mHeadPageBitmapRef.isRecycled()) {
            return null;
        }
        return this.mHeadPageBitmapRef;
    }

    public void setHeadPageBitmap(Bitmap bitmap) {
        this.mHeadPageBitmapRef = bitmap;
    }

    public String getAuthorIcon() {
        if (this.mMulitFile == null || this.mMulitFile.getBookTailInfo() == null) {
            return null;
        }
        return this.mMulitFile.getBookTailInfo().I();
    }

    public Drawable getAuthorIconDrawable() {
        return this.mAuthorIconDrawable;
    }

    public void setAuthorIconDrawable(Drawable drawable) {
        this.mAuthorIconDrawable = drawable;
    }

    public void setAuthorId(String str) {
        this.mAuthorId = str;
    }

    public String getAuthorId() {
        return this.mAuthorId;
    }

    public void setChapterMarks(Mark[] markArr) {
        this.mChapterMarks = markArr;
    }

    public String getChapterName(int i) {
        String chapterName;
        if (this.mReadType != 0 || this.mChapterMarks == null) {
            if (this.mReadType == 1) {
                OnlineChapter onlineChapter = (OnlineChapter) getMulitFile().getChapterInfo(i);
                if (onlineChapter != null) {
                    chapterName = onlineChapter.getChapterName();
                }
            }
            chapterName = null;
        } else {
            if (i > 0 && i <= this.mChapterMarks.length) {
                chapterName = this.mChapterMarks[i - 1].getDescriptionStr();
            }
            chapterName = null;
        }
        if (chapterName == null) {
            return "第" + i + "章";
        }
        return chapterName;
    }

    public void setHasRedpacket(boolean z) {
        this.mHasRedpacket = z;
    }

    public boolean hasRedpacket() {
        return this.mHasRedpacket;
    }
}
