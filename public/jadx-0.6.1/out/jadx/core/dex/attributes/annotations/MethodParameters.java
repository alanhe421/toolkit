package jadx.core.dex.attributes.annotations;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class MethodParameters implements IAttribute {
    private final List<AnnotationsList> paramList;

    public MethodParameters(int paramCount) {
        this.paramList = new ArrayList(paramCount);
    }

    public List<AnnotationsList> getParamList() {
        return this.paramList;
    }

    public AType<MethodParameters> getType() {
        return AType.ANNOTATION_MTH_PARAMETERS;
    }

    public String toString() {
        return Utils.listToString(this.paramList);
    }
}
