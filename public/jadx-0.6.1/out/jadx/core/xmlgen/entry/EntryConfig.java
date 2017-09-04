package jadx.core.xmlgen.entry;

public class EntryConfig {
    private String country;
    private String language;

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLocale() {
        StringBuilder sb = new StringBuilder();
        if (this.language != null) {
            sb.append(this.language);
        }
        if (this.country != null) {
            sb.append("-r").append(this.country);
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getLocale());
        if (sb.length() != 0) {
            sb.insert(0, " [");
            sb.append(']');
        }
        return sb.toString();
    }
}
