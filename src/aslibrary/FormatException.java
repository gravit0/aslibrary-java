package aslibrary;

public class FormatException extends Exception {
    String format;

    public FormatException(String message) {
        super(message);
        this.format = null;
    }

    public FormatException(String message, String format) {
        super(message);
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
