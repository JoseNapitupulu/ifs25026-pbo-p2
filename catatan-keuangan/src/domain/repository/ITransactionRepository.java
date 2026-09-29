package domain.repository;
import domain.entity.*;
import java.util.*;
public interface ITransactionRepository {
    List<Transaction> findAll(); Optional<Transaction> findById(int id);
    Transaction save(String description, double amount, TransactionType type);
    boolean deleteById(int id); void update(Transaction transaction);
}