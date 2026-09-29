package domain.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum SortOption {
    DAY(Comparator.comparing((Activity activity) -> getDayIndex(activity.getDay()))
            .thenComparing(Activity::getTime, String.CASE_INSENSITIVE_ORDER)),
    TIME(Comparator.comparing(Activity::getTime, String.CASE_INSENSITIVE_ORDER)),
    TITLE_ASC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER)),
    TITLE_DESC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());

    private static final List<String> DAYS = Arrays.asList("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu");

    private static int getDayIndex(String day) {
        for (int i = 0; i < DAYS.size(); i++) {
            if (DAYS.get(i).equalsIgnoreCase(day)) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private final Comparator<Activity> comparator;

    SortOption(Comparator<Activity> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Activity> comparator() {
        return comparator;
    }
}
