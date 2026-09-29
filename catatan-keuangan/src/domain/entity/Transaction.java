package domain.entity;

public class Transaction {
    private final int id;
    private String description;
    private double amount;
    private TransactionType type;
    public Transaction(int id, String description, double amount, TransactionType type) {
        if (description == null || description.isBlank() || amount <= 0 || type == null) throw new IllegalArgumentException("Data transaksi tidak valid");
        this.id = id; this.description = description; this.amount = amount; this.type = type;
    }
    public int getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public void update(String description, Double amount, TransactionType type) {
        if (description != null && !description.isBlank()) this.description = description;
        if (amount != null) { if (amount <= 0) throw new IllegalArgumentException("Jumlah tidak valid"); this.amount = amount; }
        if (type != null) this.type = type;
    }
}