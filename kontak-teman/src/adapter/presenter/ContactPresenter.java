package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

public class ContactPresenter {
    private String format(Contact contact) {
        return String.format("%d | %s | %s | %s", contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
    }

    private void printList(List<Contact> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Contact contact : list) {
                System.out.println(format(contact));
            }
        }
    }

    public void showContacts(List<Contact> list) {
        printList(list, "Daftar Kontak:", "- Data kontak belum tersedia!");
    }

    public void showSearchResults(List<Contact> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Kontak tidak ditemukan!");
    }

    public void showSortedContacts(List<Contact> list) {
        printList(list, "Daftar Kontak (Terurut):", "- Data kontak belum tersedia!");
    }

    public void showAddSuccess(Contact contact) {
        System.out.printf("Berhasil menambah kontak: %s%n", format(contact));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus kontak.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus kontak dengan ID: %d.%n", id);
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah kontak.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah kontak dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }
}
