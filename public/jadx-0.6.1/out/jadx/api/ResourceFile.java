package jadx.api;

import jadx.core.xmlgen.ResContainer;
import java.io.File;

public class ResourceFile {
    private final JadxDecompiler decompiler;
    private final String name;
    private final ResourceType type;
    private ZipRef zipRef;

    public static final class ZipRef {
        private final String entryName;
        private final File zipFile;

        public ZipRef(File zipFile, String entryName) {
            this.zipFile = zipFile;
            this.entryName = entryName;
        }

        public File getZipFile() {
            return this.zipFile;
        }

        public String getEntryName() {
            return this.entryName;
        }

        public String toString() {
            return "ZipRef{" + this.zipFile + ", '" + this.entryName + "'}";
        }
    }

    ResourceFile(JadxDecompiler decompiler, String name, ResourceType type) {
        this.decompiler = decompiler;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public ResourceType getType() {
        return this.type;
    }

    public ResContainer loadContent() {
        return ResourcesLoader.loadContent(this.decompiler, this);
    }

    void setZipRef(ZipRef zipRef) {
        this.zipRef = zipRef;
    }

    ZipRef getZipRef() {
        return this.zipRef;
    }

    public String toString() {
        return "ResourceFile{name='" + this.name + '\'' + ", type=" + this.type + "}";
    }
}
