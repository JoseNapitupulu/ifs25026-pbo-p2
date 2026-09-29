package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {
    List<Transaction> findAll();

    Optional<Transaction> findById(int id);

    Transaction save(String description, double amount, TransactionType type);

    boolean deleteById(int id);

    void update(Transaction transaction);
}