package seedu.ui;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DisplayTable extends UI {

    public void printHeaderOfTwo(String index, String header) {
        String columnOfTwo = ("| %-10s|  %-82s|%n");
        printSplit();
        System.out.printf(columnOfTwo, index, header);
        printSplitOfTwo();
    }

    public void printBodyOfTwo(int index, String body) {
        String columnOfTwo = ("| %-10d|  %-82s|%n");
        String modifiedBody1 = extractLength(body, 50);
        System.out.printf(columnOfTwo, index, modifiedBody1);
        printSplitOfTwo();
    }


    public void printHeaderOfThree(String index, String header1, String header2) {
        String columnOfThree = ("| %-10s|  %-50s|  %-28s|%n");
        printSplit();
        System.out.printf(columnOfThree, index, header1, header2);
        printSplitOfThree();
    }

    public void printBodyOfThree(int index, String body1, String body2) {
        String columnOfThree = ("| %-10d|  %-50s|  %-28s|%n");
        String modifiedBody1 = extractLength(body1, 50);
        String modifiedBody2 = extractLength(body2, 25);
        System.out.printf(columnOfThree, index, modifiedBody1, modifiedBody2);
        printSplitOfThree();
    }

    public static void printBodyOfSix(ArrayList<String> description) {
        String columnOfSix = ("| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s|%n");
        System.out.printf(columnOfSix, description.get(0), description.get(1), description.get(2), description.get(3),
                description.get(4), description.get(5));
        printSplitOfSix();
    }

    private String extractLength(String string, int length) {
        int lengthString = string.length();
        if (lengthString < length) {
            return string;
        }
        return string.substring(0, length - 4) + "...";
    }

    /**
     * This prints the horizontal split for a 4 columns table.
     */
    public void printSplitOfFour() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(32).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(37).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(12).forEach(System.out::print);
        System.out.print("|\n");
    }

    public void printSplitOfThree() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(52).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(30).forEach(System.out::print);
        System.out.print("|\n");
    }

    public void printSplitOfTwo() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(84).forEach(System.out::print);
        System.out.print("|\n");
    }

    public static void printSplitOfSix() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(21).forEach(System.out::print);
        System.out.print("|\n");
    }

    public void printSplit() {
        Stream.generate(() -> "_").limit(97).forEach(System.out::print);
        System.out.print("\n");
    }

    /**
     * This prints the headers, index, header1, header2, and header 3
     * in order respectively.
     *
     * @param index   A String printed at row 1 column 1.
     * @param header1 A String printed at row 1 column 2.
     * @param header2 A String printed at row 1 column 3.
     * @param header3 A String printed at row 1 column 4.
     */
    public void printHeaderOfFour(int index, String header1,
                                         String header2, String header3) {
        String headerFormat = ("| %-10s|  %-30s|  %-35s|  %-10s|%n");
        printSplit();
        System.out.printf(headerFormat, index, header1, header2, header3);
        printSplitOfFour();
    }

    /**
     * This prints the headers, index, body1, body2, and body3
     * in order respectively.
     *
     * @param index A String printed at column 1.
     * @param body1 A String printed at column 2.
     * @param body2 A String printed at column 3.
     * @param body3 A String printed at column 4.
     */
    public void printBodyOfFour(int index, String body1,
                                String body2, String body3) {
        String columnOfFour = ("| %-10d|  %-30s|  %-35s|  %-10s|%n");
        System.out.printf(columnOfFour, index, body1, body2, body3);
        printSplitOfFour();
    }
}
