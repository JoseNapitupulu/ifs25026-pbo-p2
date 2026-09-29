package domain.entity;

public class Guest {
    private final int id;
    private String name;
    private String purpose;

    public Guest(int id, String name, String purpose) {
        validateText(name, "Nama tamu");
        validateText(purpose, "Tujuan kunjungan");
        this.id = id;
        this.name = name;
        this.purpose = purpose;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPurpose() { return purpose; }

    public void changeName(String name) {
        validateText(name, "Nama tamu");
        this.name = name;
    }

    public void changePurpose(String purpose) {
        validateText(purpose, "Tujuan kunjungan");
        this.purpose = purpose;
    }

    private static void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
    }
}
