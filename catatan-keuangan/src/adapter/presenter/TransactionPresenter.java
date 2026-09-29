package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class TransactionPresenter {
    private String format(Transaction transaction) {
        String type = transaction.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran";
        return String.format("%d | %s | %.2f | %s", transaction.getId(), transaction.getDescription(),
                transaction.getAmount(), type);
    }

    public void show(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("- Belum ada transaksi!");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(format(transaction));
        }
    }

    public void added(Transaction transaction) {
        System.out.println("Berhasil menambah transaksi: " + format(transaction));
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void balance(double balance) {
        System.out.printf("Saldo: %.2f%n", balance);
    }
}