package jadx.core.dex.instructions.args;

public enum PrimitiveType {
    BOOLEAN("Z", "boolean"),
    CHAR("C", "char"),
    BYTE("B", "byte"),
    SHORT("S", "short"),
    INT("I", "int"),
    FLOAT("F", "float"),
    LONG("J", "long"),
    DOUBLE("D", "double"),
    OBJECT("L", "OBJECT"),
    ARRAY("[", "ARRAY"),
    VOID("V", "void");
    
    private final String longName;
    private final String shortName;

    private PrimitiveType(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getLongName() {
        return this.longName;
    }

    public static PrimitiveType getWidest(PrimitiveType a, PrimitiveType b) {
        return a.ordinal() > b.ordinal() ? a : b;
    }

    public static PrimitiveType getSmaller(PrimitiveType a, PrimitiveType b) {
        return a.ordinal() < b.ordinal() ? a : b;
    }

    public String toString() {
        return this.longName;
    }
}
