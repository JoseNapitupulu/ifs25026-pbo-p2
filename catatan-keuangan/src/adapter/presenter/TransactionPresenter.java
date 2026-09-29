package adapter.presenter;
import domain.entity.*; import java.util.*;
public class TransactionPresenter {
    private String format(Transaction t) { return String.format("%d | %s | %.2f | %s", t.getId(), t.getDescription(), t.getAmount(), t.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran"); }
    public void show(List<Transaction> ts) { if (ts.isEmpty()) { System.out.println("- Belum ada transaksi!"); return; } ts.forEach(t -> System.out.println(format(t))); }
    public void added(Transaction t) { System.out.println("Berhasil menambah transaksi: " + format(t)); }
    public void message(String s) { System.out.println(s); }
    public void balance(double n) { System.out.printf("Saldo: %.2f%n", n); }
}