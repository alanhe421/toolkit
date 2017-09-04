package de.innosystec.unrar.rarfile;

public enum HostSystem {
    msdos((byte) 0),
    os2((byte) 1),
    win32((byte) 2),
    unix((byte) 3),
    macos((byte) 4),
    beos((byte) 5);
    
    private byte hostByte;

    public static HostSystem findHostSystem(byte b) {
        if (msdos.equals(b)) {
            return msdos;
        }
        if (os2.equals(b)) {
            return os2;
        }
        if (win32.equals(b)) {
            return win32;
        }
        if (unix.equals(b)) {
            return unix;
        }
        if (macos.equals(b)) {
            return macos;
        }
        if (beos.equals(b)) {
            return beos;
        }
        return null;
    }

    private HostSystem(byte b) {
        this.hostByte = b;
    }

    public boolean equals(byte b) {
        return this.hostByte == b;
    }

    public byte getHostByte() {
        return this.hostByte;
    }
}
