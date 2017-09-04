package jadx.api;

import java.io.File;

public interface IJadxArgs {
    boolean escapeUnicode();

    int getDeobfuscationMaxLength();

    int getDeobfuscationMinLength();

    File getOutDir();

    int getThreadsCount();

    boolean isCFGOutput();

    boolean isDeobfuscationForceSave();

    boolean isDeobfuscationOn();

    boolean isExportAsGradleProject();

    boolean isFallbackMode();

    boolean isRawCFGOutput();

    boolean isReplaceConsts();

    boolean isShowInconsistentCode();

    boolean isSkipResources();

    boolean isSkipSources();

    boolean isVerbose();

    boolean useSourceNameAsClassAlias();
}
