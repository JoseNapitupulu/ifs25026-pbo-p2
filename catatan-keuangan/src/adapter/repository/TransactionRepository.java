package adapter.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Transaction> findById(int id) {
        return data.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
    }

    @Override
    public Transaction save(String description, double amount, TransactionType type) {
        Transaction transaction = new Transaction(nextId++, description, amount, type);
        data.add(transaction);
        return transaction;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(transaction -> transaction.getId() == id);
    }

    @Override
    public void update(Transaction transaction) {
        replaceById(transaction);
    }

    private void replaceById(Transaction transaction) {
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).getId() == transaction.getId()) {
                data.set(index, transaction);
                return;
            }
        }
        throw new IllegalArgumentException("Transaksi tidak ditemukan: " + transaction.getId());
    }
}