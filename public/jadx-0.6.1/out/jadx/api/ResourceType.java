package jadx.api;

public enum ResourceType {
    CODE(".dex", ".jar", ".class"),
    MANIFEST("AndroidManifest.xml"),
    XML(".xml"),
    ARSC(".arsc"),
    FONT(".ttf"),
    IMG(".png", ".gif", ".jpg"),
    LIB(".so"),
    UNKNOWN(new String[0]);
    
    private final String[] exts;

    private ResourceType(String... exts) {
        this.exts = exts;
    }

    public String[] getExts() {
        return this.exts;
    }

    public static ResourceType getFileType(String fileName) {
        for (ResourceType type : values()) {
            for (String ext : type.getExts()) {
                if (fileName.endsWith(ext)) {
                    return type;
                }
            }
        }
        return UNKNOWN;
    }

    public static boolean isSupportedForUnpack(ResourceType type) {
        switch (type) {
            case MANIFEST:
            case XML:
            case ARSC:
            case IMG:
                return true;
            default:
                return false;
        }
    }
}
