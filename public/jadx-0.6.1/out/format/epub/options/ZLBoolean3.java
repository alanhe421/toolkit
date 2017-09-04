package format.epub.options;

public enum ZLBoolean3 {
    B3_FALSE("false"),
    B3_TRUE("true"),
    B3_UNDEFINED("undefined");
    
    public final String Name;

    private ZLBoolean3(String str) {
        this.Name = str;
    }

    public static ZLBoolean3 getByName(String str) {
        for (ZLBoolean3 zLBoolean3 : values()) {
            if (zLBoolean3.Name.equals(str)) {
                return zLBoolean3;
            }
        }
        return B3_UNDEFINED;
    }

    public static ZLBoolean3 b3Value(boolean z) {
        return z ? B3_TRUE : B3_FALSE;
    }
}
