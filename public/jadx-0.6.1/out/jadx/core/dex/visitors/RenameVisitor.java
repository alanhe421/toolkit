package jadx.core.dex.visitors;

import jadx.api.IJadxArgs;
import jadx.core.Consts;
import jadx.core.codegen.TypeGen;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.exceptions.JadxException;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class RenameVisitor extends AbstractVisitor {
    private static final boolean CASE_SENSITIVE_FS = IOCase.SYSTEM.isCaseSensitive();
    private Deobfuscator deobfuscator;

    public void init(RootNode root) {
        IJadxArgs args = root.getArgs();
        String firstInputFileName = ((DexNode) root.getDexNodes().get(0)).getDexFile().getInputFile().getFile().getAbsolutePath();
        this.deobfuscator = new Deobfuscator(args, root.getDexNodes(), new File(FilenameUtils.getFullPathNoEndSeparator(firstInputFileName), FilenameUtils.getBaseName(firstInputFileName) + ".jobf"));
        if (args.isDeobfuscationOn()) {
            this.deobfuscator.execute();
        }
        checkClasses(root);
    }

    public boolean visit(ClassNode cls) throws JadxException {
        checkFields(cls);
        checkMethods(cls);
        for (ClassNode inner : cls.getInnerClasses()) {
            visit(inner);
        }
        return false;
    }

    private void checkClasses(RootNode root) {
        Set<String> clsNames = new HashSet();
        for (ClassNode cls : root.getClasses(true)) {
            checkClassName(cls);
            if (!CASE_SENSITIVE_FS) {
                ClassInfo classInfo = cls.getClassInfo();
                if (!clsNames.add(classInfo.getAlias().getFullPath().toLowerCase())) {
                    classInfo.rename(cls.dex(), classInfo.makeFullClsName(this.deobfuscator.getClsAlias(cls), true));
                    clsNames.add(classInfo.getAlias().getFullPath().toLowerCase());
                }
            }
        }
    }

    private void checkClassName(ClassNode cls) {
        ClassInfo classInfo = cls.getClassInfo();
        String clsName = classInfo.getAlias().getShortName();
        String newShortName = null;
        char firstChar = clsName.charAt(0);
        if (Character.isDigit(firstChar)) {
            newShortName = Consts.ANONYMOUS_CLASS_PREFIX + clsName;
        } else if (firstChar == '$') {
            newShortName = "C" + clsName;
        }
        if (newShortName != null) {
            classInfo.rename(cls.dex(), classInfo.makeFullClsName(newShortName, true));
        }
        if (classInfo.getAlias().getPackage().isEmpty()) {
            classInfo.rename(cls.dex(), "defpackage." + classInfo.makeFullClsName(classInfo.getAlias().getShortName(), true));
        }
    }

    private void checkFields(ClassNode cls) {
        Set<String> names = new HashSet();
        for (FieldNode field : cls.getFields()) {
            FieldInfo fieldInfo = field.getFieldInfo();
            if (!names.add(fieldInfo.getAlias())) {
                fieldInfo.setAlias(this.deobfuscator.makeFieldAlias(field));
            }
        }
    }

    private void checkMethods(ClassNode cls) {
        Set<String> names = new HashSet();
        for (MethodNode mth : cls.getMethods()) {
            if (!mth.contains(AFlag.DONT_GENERATE)) {
                MethodInfo methodInfo = mth.getMethodInfo();
                if (!names.add(makeMethodSignature(methodInfo))) {
                    methodInfo.setAlias(this.deobfuscator.makeMethodAlias(mth));
                }
            }
        }
    }

    private static String makeMethodSignature(MethodInfo methodInfo) {
        StringBuilder signature = new StringBuilder();
        signature.append(methodInfo.getAlias());
        signature.append('(');
        for (ArgType arg : methodInfo.getArgumentsTypes()) {
            signature.append(TypeGen.signature(arg));
        }
        signature.append(')');
        return signature.toString();
    }
}
