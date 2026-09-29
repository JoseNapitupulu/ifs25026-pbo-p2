package domain.entity;

public class Item {
    private final int id;
    private String name;
    private int quantity;
    private String category;

    public Item(int id, String name, int quantity, String category) {
        validateText(name, "Nama barang");
        validateQuantity(quantity);
        validateText(category, "Kategori barang");
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }

    public void changeName(String name) {
        validateText(name, "Nama barang");
        this.name = name;
    }

    public void changeQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void changeCategory(String category) {
        validateText(category, "Kategori barang");
        this.category = category;
    }

    private static void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Jumlah stok harus lebih dari 0");
        }
    }
}
