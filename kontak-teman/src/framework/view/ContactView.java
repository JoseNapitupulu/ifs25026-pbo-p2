package framework.view;

import adapter.presenter.ContactPresenter;
import framework.util.ContactInputUtil;
import usecase.ContactUseCase;

/** Tampilan konsol untuk mengelola kontak teman. */
public class ContactView {
    private final ContactUseCase useCase;
    private final ContactPresenter presenter;

    public ContactView(ContactUseCase useCase, ContactPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    /** Menjalankan menu utama sampai user memilih keluar. */
    public void show() {
        boolean running = true;
        while (running) {
            presenter.show(useCase.all());
            printMenu();
            switch (ContactInputUtil.input("Pilih")) {
                case "1" -> addContact();
                case "2" -> searchContact();
                case "3" -> updateContact();
                case "4" -> removeContact();
                case "0" -> running = false;
                default -> presenter.msg("[!] Pilihan tidak valid!");
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu Kontak Teman:");
        System.out.println("1. Tambah kontak");
        System.out.println("2. Cari kontak");
        System.out.println("3. Ubah kontak");
        System.out.println("4. Hapus kontak");
        System.out.println("0. Keluar");
    }

    private void addContact() {
        String name = ContactInputUtil.input("Nama (x Jika Batal)");
        if (name.equalsIgnoreCase("x")) return;
        String phone = ContactInputUtil.input("Nomor telepon");
        String email = ContactInputUtil.input("Email");
        if (name.isBlank() || phone.isBlank() || !isValidEmail(email)) {
            presenter.msg("[!] Nama, nomor telepon, dan email valid wajib diisi!");
            return;
        }
        presenter.added(useCase.add(name, phone, email));
    }

    private void searchContact() {
        String keyword = ContactInputUtil.input("Kata kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) presenter.show(useCase.search(keyword));
    }

    private void removeContact() {
        Integer id = readId("ID kontak (x Jika Batal)");
        if (id == null) return;
        presenter.msg(useCase.remove(id) ? "Berhasil menghapus kontak." : "[!] Kontak tidak ditemukan!");
    }

    private void updateContact() {
        Integer id = readId("ID kontak (x Jika Batal)");
        if (id == null) return;
        String name = ContactInputUtil.input("Nama baru (kosongkan jika tidak berubah)");
        String phone = ContactInputUtil.input("Nomor baru (kosongkan jika tidak berubah)");
        String email = ContactInputUtil.input("Email baru (kosongkan jika tidak berubah)");
        if (!email.isBlank() && !isValidEmail(email)) {
            presenter.msg("[!] Email tidak valid!");
            return;
        }
        presenter.msg(useCase.update(id, name, phone, email) ? "Berhasil mengubah kontak." : "[!] Gagal mengubah kontak dengan ID: " + id + ".");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private Integer readId(String prompt) {
        String input = ContactInputUtil.input(prompt);
        if (input.equalsIgnoreCase("x")) return null;
        Integer id = ContactInputUtil.idValue(input);
        if (id == null) presenter.msg("[!] ID tidak valid!");
        return id;
    }
}
