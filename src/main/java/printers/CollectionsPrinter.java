package printers;


public class CollectionsPrinter {


    private CollectionsPrinter() {

    }

    public static void appendHorizontalLine(StringBuilder stringBuilder, int count) {
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(count));
    }
}
