package jadx.core.utils.files;

import com.android.dex.Dex;

public class DexFile {
    private final Dex dexBuf;
    private final InputFile inputFile;
    private final String name;

    public DexFile(InputFile inputFile, String name, Dex dexBuf) {
        this.inputFile = inputFile;
        this.name = name;
        this.dexBuf = dexBuf;
    }

    public String getName() {
        return this.name;
    }

    public Dex getDexBuf() {
        return this.dexBuf;
    }

    public InputFile getInputFile() {
        return this.inputFile;
    }

    public String toString() {
        return this.inputFile.toString() + (this.name.isEmpty() ? "" : ":" + this.name);
    }
}
