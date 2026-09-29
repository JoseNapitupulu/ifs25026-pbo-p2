package framework.util;

import java.util.Scanner;

public final class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        if (!SCANNER.hasNextLine()) {
            return "";
        }
        return SCANNER.nextLine().trim();
    }
}
