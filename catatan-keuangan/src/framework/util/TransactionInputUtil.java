package framework.util;

import java.util.Scanner;

/** Utility input khusus aplikasi Catatan Keuangan. */
public class TransactionInputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String input(String prompt) {
        System.out.print(prompt + ": ");
        return SCANNER.nextLine().trim();
    }

    public static Integer idValue(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    public static Double amount(String prompt) {
        return amountValue(input(prompt));
    }

    public static Double amountValue(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}