package domain.entity;

public class Contact {
    private final int id;
    private String name;
    private String phone;
    private String email;

    public Contact(int id, String name, String phone, String email) {
        validateText(name, "Nama kontak");
        validateText(phone, "Nomor telepon");
        validateText(email, "Email");
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void changeName(String name) {
        validateText(name, "Nama kontak");
        this.name = name;
    }

    public void changePhone(String phone) {
        validateText(phone, "Nomor telepon");
        this.phone = phone;
    }

    public void changeEmail(String email) {
        validateText(email, "Email");
        this.email = email;
    }

    private static void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
    }
}
