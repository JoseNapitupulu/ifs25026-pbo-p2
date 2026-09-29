package adapter.repository;
import domain.entity.*; import domain.repository.ITransactionRepository; import java.util.*;
public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> data = new ArrayList<>(); private int nextId = 1;
    public List<Transaction> findAll() { return new ArrayList<>(data); }
    public Optional<Transaction> findById(int id) { return data.stream().filter(t -> t.getId() == id).findFirst(); }
    public Transaction save(String d, double a, TransactionType type) { Transaction t = new Transaction(nextId++, d, a, type); data.add(t); return t; }
    public boolean deleteById(int id) { return data.removeIf(t -> t.getId() == id); }
    public void update(Transaction transaction) { }
}