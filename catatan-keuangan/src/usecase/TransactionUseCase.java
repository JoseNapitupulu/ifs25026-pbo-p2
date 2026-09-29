package usecase;
import domain.entity.*; import domain.repository.ITransactionRepository; import java.util.*; import java.util.stream.Collectors;
public class TransactionUseCase {
    private final ITransactionRepository repository;
    public TransactionUseCase(ITransactionRepository repository) { this.repository = repository; }
    public List<Transaction> getAll() { return repository.findAll(); }
    public Transaction add(String d, double a, TransactionType type) { return repository.save(d, a, type); }
    public boolean remove(int id) { return repository.deleteById(id); }
    public boolean update(int id, String d, Double a, TransactionType type) { Optional<Transaction> f = repository.findById(id); if (f.isEmpty()) return false; f.get().update(d, a, type); repository.update(f.get()); return true; }
    public List<Transaction> search(String k) { String key = k.toLowerCase(); return getAll().stream().filter(t -> t.getDescription().toLowerCase().contains(key)).collect(Collectors.toList()); }
    public List<Transaction> sort(TransactionSortOption option) { return getAll().stream().sorted(option.comparator()).collect(Collectors.toList()); }
    public double balance() { return getAll().stream().mapToDouble(t -> t.getType() == TransactionType.INCOME ? t.getAmount() : -t.getAmount()).sum(); }
}