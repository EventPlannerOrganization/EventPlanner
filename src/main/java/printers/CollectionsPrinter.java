package printers;


public class CollectionsPrinter {


    private CollectionsPrinter() {

    }

    public static void appendHorizontalLine(StringBuilder stringBuilder, int count) {
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(count));
    }


    public static String getMonthName(int month) {
        return switch (month) {
            case 1 -> "JANUARY";
            case 2 -> "FEBRUARY";
            case 3 -> "MARCH";
            case 4 -> "APRIL";
            case 5 -> "MAY";
            case 6 -> "JUNE";
            case 7 -> "JULY";
            case 8 -> "AUGUST";
            case 9 -> "SEPTEMBER";
            case 10 -> "OCTOBER";
            case 11 -> "NOVEMBER";
            case 12 -> "DECEMBER";
            default -> "";
        };
}}
