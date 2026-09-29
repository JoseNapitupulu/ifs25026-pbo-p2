package usecase;

import domain.entity.Transaction;
import domain.entity.TransactionSortOption;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.List;
import java.util.Optional;

public class TransactionUseCase {
    private final ITransactionRepository repository;

    public TransactionUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction add(String description, double amount, TransactionType type) {
        return repository.save(description, amount, type);
    }

    public boolean remove(int id) {
        return repository.deleteById(id);
    }

    public boolean update(int id, String description, Double amount, TransactionType type) {
        Optional<Transaction> found = repository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Transaction transaction = found.get();
        transaction.update(description, amount, type);
        repository.update(transaction);
        return true;
    }

    public List<Transaction> search(String keyword) {
        String normalizedKeyword = keyword.toLowerCase();
        return getAll().stream()
                .filter(transaction -> transaction.getDescription().toLowerCase().contains(normalizedKeyword))
                .toList();
    }

    public List<Transaction> sort(TransactionSortOption option) {
        return getAll().stream()
                .sorted(option.comparator())
                .toList();
    }

    public double balance() {
        return getAll().stream()
                .mapToDouble(transaction -> transaction.getType() == TransactionType.INCOME
                        ? transaction.getAmount()
                        : -transaction.getAmount())
                .sum();
    }
}