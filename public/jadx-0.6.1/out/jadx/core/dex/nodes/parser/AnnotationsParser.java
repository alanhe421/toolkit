package jadx.core.dex.nodes.parser;

import com.android.dex.Dex.Section;
import jadx.core.dex.attributes.annotations.Annotation;
import jadx.core.dex.attributes.annotations.Annotation.Visibility;
import jadx.core.dex.attributes.annotations.AnnotationsList;
import jadx.core.dex.attributes.annotations.MethodParameters;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.DecodeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnotationsParser {
    private static final Visibility[] VISIBILITIES = new Visibility[]{Visibility.BUILD, Visibility.RUNTIME, Visibility.SYSTEM};
    private final ClassNode cls;
    private final DexNode dex;

    public AnnotationsParser(ClassNode cls) {
        this.cls = cls;
        this.dex = cls.dex();
    }

    public void parse(int offset) throws DecodeException {
        int i;
        Section section = this.dex.openSection(offset);
        int classAnnotationsOffset = section.readInt();
        int fieldsCount = section.readInt();
        int annotatedMethodsCount = section.readInt();
        int annotatedParametersCount = section.readInt();
        if (classAnnotationsOffset != 0) {
            this.cls.addAttr(readAnnotationSet(classAnnotationsOffset));
        }
        for (i = 0; i < fieldsCount; i++) {
            this.cls.searchFieldById(section.readInt()).addAttr(readAnnotationSet(section.readInt()));
        }
        for (i = 0; i < annotatedMethodsCount; i++) {
            this.cls.searchMethodById(section.readInt()).addAttr(readAnnotationSet(section.readInt()));
        }
        for (i = 0; i < annotatedParametersCount; i++) {
            MethodNode mth = this.cls.searchMethodById(section.readInt());
            Section ss = this.dex.openSection(section.readInt());
            int size = ss.readInt();
            MethodParameters params = new MethodParameters(size);
            for (int j = 0; j < size; j++) {
                params.getParamList().add(readAnnotationSet(ss.readInt()));
            }
            mth.addAttr(params);
        }
    }

    private AnnotationsList readAnnotationSet(int offset) throws DecodeException {
        if (offset == 0) {
            return AnnotationsList.EMPTY;
        }
        Section section = this.dex.openSection(offset);
        int size = section.readInt();
        if (size == 0) {
            return AnnotationsList.EMPTY;
        }
        List<Annotation> list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(readAnnotation(this.dex, this.dex.openSection(section.readInt()), true));
        }
        return new AnnotationsList(list);
    }

    public static Annotation readAnnotation(DexNode dex, Section s, boolean readVisibility) throws DecodeException {
        EncValueParser parser = new EncValueParser(dex, s);
        Visibility visibility = null;
        if (readVisibility) {
            visibility = VISIBILITIES[s.readByte()];
        }
        int typeIndex = s.readUleb128();
        int size = s.readUleb128();
        Map<String, Object> values = new LinkedHashMap(size);
        for (int i = 0; i < size; i++) {
            values.put(dex.getString(s.readUleb128()), parser.parseValue());
        }
        ArgType type = dex.getType(typeIndex);
        Annotation annotation = new Annotation(visibility, type, values);
        if (type.isObject()) {
            return annotation;
        }
        throw new DecodeException("Incorrect type for annotation: " + annotation);
    }
}
