package format.archive;

import com.qq.reader.common.c.a;
import java.io.File;
import java.util.List;

public class FileItem extends File {
    private static final long serialVersionUID = 1;
    public boolean isDirectory;
    public String mAbsolutePath;
    public String mArchivePath;
    public String mCompressType;
    public String mFileName;
    public long mFileSize;
    public long mFileTime;
    public int mId;
    public String mInterfolderPath;
    public int mSubFileNum;
    public File parentFile;

    public FileItem(String str) {
        super(str);
        this.mAbsolutePath = str;
    }

    public FileItem() {
        super("");
    }

    public File getParentFile() {
        return this.parentFile;
    }

    public void setParentFile(File file) {
        this.parentFile = file;
    }

    public String getAbsolutePath() {
        return this.mAbsolutePath;
    }

    public String getName() {
        return this.mFileName;
    }

    public String getPath() {
        return a.u + File.separator + this.mArchivePath.hashCode() + this.mInterfolderPath.hashCode() + this.mFileName;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public boolean isFile() {
        return !this.isDirectory;
    }

    public String[] list() {
        if (this.mSubFileNum == 0) {
            return null;
        }
        return new String[this.mSubFileNum];
    }

    public String getParent() {
        return "";
    }

    public File[] listFiles() {
        List a = a.a(this.mArchivePath, this.mInterfolderPath, false, this.mCompressType);
        File[] fileArr = new FileItem[a.size()];
        a.toArray(fileArr);
        return fileArr;
    }

    public boolean exists() {
        return true;
    }

    public long length() {
        return this.mFileSize;
    }
}
