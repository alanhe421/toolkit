package format.epub.common.c.a;

/* compiled from: StyleSheetUtil */
public class i {
    private static String[] a = new String[]{"inline", "block", "flex", "inline-block", "inline-flex", "inline-table", "list-item", "run-in", "table", "table-caption", "table-column-group", "table-header-group", "table-footer-group", "table-row-group", "table-cell", "table-column", "table-row", "none", "initial", "inherit"};

    public static int a(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        for (int length = a.length - 1; length >= 0; length--) {
            if (str.equals(a[length])) {
                return length;
            }
        }
        return -1;
    }
}
