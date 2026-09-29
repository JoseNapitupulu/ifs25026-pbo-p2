package domain.entity;

import java.util.Comparator;

/** Kriteria pengurutan transaksi. */
public enum TransactionSortOption {
    DESCRIPTION_ASC(Comparator.comparing(Transaction::getDescription, String.CASE_INSENSITIVE_ORDER)),
    AMOUNT_DESC(Comparator.comparing(Transaction::getAmount).reversed()),
    INCOME_FIRST(Comparator.comparing(Transaction::getType).reversed());

    private final Comparator<Transaction> comparator;

    TransactionSortOption(Comparator<Transaction> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transaction> comparator() {
        return comparator;
    }
}