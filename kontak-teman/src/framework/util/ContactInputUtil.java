package framework.util;

import java.util.Scanner;

/** Utility input khusus aplikasi Kontak Teman. */
public class ContactInputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static String input(String prompt) { System.out.print(prompt + ": "); return SCANNER.nextLine().trim(); }
    public static Integer idValue(String value) { try { return Integer.valueOf(value); } catch (NumberFormatException e) { return null; } }
}