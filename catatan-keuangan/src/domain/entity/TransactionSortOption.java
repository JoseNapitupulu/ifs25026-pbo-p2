package domain.entity;

import java.util.Comparator;

/** Kriteria pengurutan transaksi. */
public enum TransactionSortOption {
    AMOUNT_ASC(Comparator.comparingDouble(Transaction::getAmount)),
    AMOUNT_DESC(Comparator.comparing(Transaction::getAmount).reversed()),
    INCOME_FIRST(Comparator.comparing(transaction -> transaction.getType() != TransactionType.INCOME)),
    EXPENSE_FIRST(Comparator.comparing(transaction -> transaction.getType() != TransactionType.EXPENSE));

    private final Comparator<Transaction> comparator;

    TransactionSortOption(Comparator<Transaction> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transaction> comparator() {
        return comparator;
    }
}