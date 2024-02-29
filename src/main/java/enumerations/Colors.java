package enumerations;

public enum Colors {
    YELLOW("\u001B[33m"),
    GREEN("\u001B[32m"),
    RESET("\u001B[0m"),
    BACKGROUND_WHITE("\u001B[47m");

    private final String uniCodeValue;

    Colors(String uniCodeValue) {
        this.uniCodeValue = uniCodeValue;
    }

    public String getUniCodeValue() {
        return uniCodeValue;
    }
}
