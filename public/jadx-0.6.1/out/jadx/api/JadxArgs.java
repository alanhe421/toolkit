package jadx.api;

import java.io.File;

public class JadxArgs implements IJadxArgs {
    private boolean cfgOutput = false;
    private int deobfuscationMaxLength = Integer.MAX_VALUE;
    private int deobfuscationMinLength = 0;
    private boolean escapeUnicode = false;
    private boolean exportAsGradleProject = false;
    private boolean fallbackMode = false;
    private boolean isDeobfuscationForceSave = false;
    private boolean isDeobfuscationOn = false;
    private boolean isSkipResources = false;
    private boolean isSkipSources = false;
    private boolean isVerbose = false;
    private File outDir = new File("jadx-output");
    private boolean rawCFGOutput = false;
    private boolean replaceConsts = true;
    private boolean showInconsistentCode = false;
    private int threadsCount = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
    private boolean useSourceNameAsClassAlias = false;

    public File getOutDir() {
        return this.outDir;
    }

    public void setOutDir(File outDir) {
        this.outDir = outDir;
    }

    public int getThreadsCount() {
        return this.threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public boolean isCFGOutput() {
        return this.cfgOutput;
    }

    public void setCfgOutput(boolean cfgOutput) {
        this.cfgOutput = cfgOutput;
    }

    public boolean isRawCFGOutput() {
        return this.rawCFGOutput;
    }

    public void setRawCFGOutput(boolean rawCFGOutput) {
        this.rawCFGOutput = rawCFGOutput;
    }

    public boolean isFallbackMode() {
        return this.fallbackMode;
    }

    public void setFallbackMode(boolean fallbackMode) {
        this.fallbackMode = fallbackMode;
    }

    public boolean isShowInconsistentCode() {
        return this.showInconsistentCode;
    }

    public void setShowInconsistentCode(boolean showInconsistentCode) {
        this.showInconsistentCode = showInconsistentCode;
    }

    public boolean isVerbose() {
        return this.isVerbose;
    }

    public void setVerbose(boolean verbose) {
        this.isVerbose = verbose;
    }

    public boolean isSkipResources() {
        return this.isSkipResources;
    }

    public void setSkipResources(boolean skipResources) {
        this.isSkipResources = skipResources;
    }

    public boolean isSkipSources() {
        return this.isSkipSources;
    }

    public void setSkipSources(boolean skipSources) {
        this.isSkipSources = skipSources;
    }

    public boolean isDeobfuscationOn() {
        return this.isDeobfuscationOn;
    }

    public void setDeobfuscationOn(boolean deobfuscationOn) {
        this.isDeobfuscationOn = deobfuscationOn;
    }

    public boolean isDeobfuscationForceSave() {
        return this.isDeobfuscationForceSave;
    }

    public void setDeobfuscationForceSave(boolean deobfuscationForceSave) {
        this.isDeobfuscationForceSave = deobfuscationForceSave;
    }

    public boolean useSourceNameAsClassAlias() {
        return this.useSourceNameAsClassAlias;
    }

    public void setUseSourceNameAsClassAlias(boolean useSourceNameAsClassAlias) {
        this.useSourceNameAsClassAlias = useSourceNameAsClassAlias;
    }

    public int getDeobfuscationMinLength() {
        return this.deobfuscationMinLength;
    }

    public void setDeobfuscationMinLength(int deobfuscationMinLength) {
        this.deobfuscationMinLength = deobfuscationMinLength;
    }

    public int getDeobfuscationMaxLength() {
        return this.deobfuscationMaxLength;
    }

    public void setDeobfuscationMaxLength(int deobfuscationMaxLength) {
        this.deobfuscationMaxLength = deobfuscationMaxLength;
    }

    public boolean escapeUnicode() {
        return this.escapeUnicode;
    }

    public void setEscapeUnicode(boolean escapeUnicode) {
        this.escapeUnicode = escapeUnicode;
    }

    public boolean isReplaceConsts() {
        return this.replaceConsts;
    }

    public void setReplaceConsts(boolean replaceConsts) {
        this.replaceConsts = replaceConsts;
    }

    public boolean isExportAsGradleProject() {
        return this.exportAsGradleProject;
    }

    public void setExportAsGradleProject(boolean exportAsGradleProject) {
        this.exportAsGradleProject = exportAsGradleProject;
    }
}
