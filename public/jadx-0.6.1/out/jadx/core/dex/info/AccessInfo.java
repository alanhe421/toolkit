package jadx.core.dex.info;

import com.android.dx.rop.code.AccessFlags;
import jadx.core.utils.files.FileUtils;

public class AccessInfo {
    private final int accFlags;
    private final AFType type;

    public enum AFType {
        CLASS,
        FIELD,
        METHOD
    }

    public AccessInfo(int accessFlags, AFType type) {
        this.accFlags = accessFlags;
        this.type = type;
    }

    public boolean containsFlag(int flag) {
        return (this.accFlags & flag) != 0;
    }

    public AccessInfo remove(int flag) {
        if (containsFlag(flag)) {
            return new AccessInfo(this.accFlags & (flag ^ -1), this.type);
        }
        return this;
    }

    public AccessInfo getVisibility() {
        return new AccessInfo(((this.accFlags & 1) | (this.accFlags & 4)) | (this.accFlags & 2), this.type);
    }

    public boolean isPublic() {
        return (this.accFlags & 1) != 0;
    }

    public boolean isProtected() {
        return (this.accFlags & 4) != 0;
    }

    public boolean isPrivate() {
        return (this.accFlags & 2) != 0;
    }

    public boolean isAbstract() {
        return (this.accFlags & 1024) != 0;
    }

    public boolean isInterface() {
        return (this.accFlags & 512) != 0;
    }

    public boolean isAnnotation() {
        return (this.accFlags & FileUtils.READ_BUFFER_SIZE) != 0;
    }

    public boolean isNative() {
        return (this.accFlags & 256) != 0;
    }

    public boolean isStatic() {
        return (this.accFlags & 8) != 0;
    }

    public boolean isFinal() {
        return (this.accFlags & 16) != 0;
    }

    public boolean isConstructor() {
        return (this.accFlags & 65536) != 0;
    }

    public boolean isEnum() {
        return (this.accFlags & 16384) != 0;
    }

    public boolean isSynthetic() {
        return (this.accFlags & 4096) != 0;
    }

    public boolean isBridge() {
        return (this.accFlags & 64) != 0;
    }

    public boolean isVarArgs() {
        return (this.accFlags & 128) != 0;
    }

    public boolean isSynchronized() {
        return (this.accFlags & 131104) != 0;
    }

    public boolean isTransient() {
        return (this.accFlags & 128) != 0;
    }

    public boolean isVolatile() {
        return (this.accFlags & 64) != 0;
    }

    public AFType getType() {
        return this.type;
    }

    public String makeString() {
        StringBuilder code = new StringBuilder();
        if (isPublic()) {
            code.append("public ");
        }
        if (isPrivate()) {
            code.append("private ");
        }
        if (isProtected()) {
            code.append("protected ");
        }
        if (isStatic()) {
            code.append("static ");
        }
        if (isFinal()) {
            code.append("final ");
        }
        if (isAbstract()) {
            code.append("abstract ");
        }
        if (isNative()) {
            code.append("native ");
        }
        switch (this.type) {
            case METHOD:
                if (isSynchronized()) {
                    code.append("synchronized ");
                }
                if (isBridge()) {
                    code.append("/* bridge */ ");
                    break;
                }
                break;
            case FIELD:
                if (isVolatile()) {
                    code.append("volatile ");
                }
                if (isTransient()) {
                    code.append("transient ");
                    break;
                }
                break;
            case CLASS:
                if ((this.accFlags & 2048) != 0) {
                    code.append("strict ");
                    break;
                }
                break;
        }
        if (isSynthetic()) {
            code.append("/* synthetic */ ");
        }
        return code.toString();
    }

    public String rawString() {
        switch (this.type) {
            case METHOD:
                return AccessFlags.methodString(this.accFlags);
            case FIELD:
                return AccessFlags.fieldString(this.accFlags);
            case CLASS:
                return AccessFlags.classString(this.accFlags);
            default:
                return "?";
        }
    }

    public String toString() {
        return "AccessInfo: " + this.type + " 0x" + Integer.toHexString(this.accFlags) + " (" + rawString() + ")";
    }
}
