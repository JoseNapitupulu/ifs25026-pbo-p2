package framework.view;

import adapter.presenter.TransactionPresenter;
import domain.entity.TransactionSortOption;
import domain.entity.TransactionType;
import framework.util.InputUtil;
import usecase.TransactionUseCase;

/** Tampilan konsol untuk mengelola transaksi keuangan. */
public class TransactionView {
    private final TransactionUseCase useCase;
    private final TransactionPresenter presenter;

    public TransactionView(TransactionUseCase useCase, TransactionPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    /** Menjalankan menu utama sampai user memilih keluar. */
    public void show() {
        boolean running = true;
        while (running) {
            presenter.show(useCase.getAll());
            printMenu();
            switch (InputUtil.input("Pilih")) {
                case "1" -> addTransaction();
                case "2" -> searchTransaction();
                case "3" -> presenter.balance(useCase.balance());
                case "4" -> removeTransaction();
                case "5" -> updateTransaction();
                case "6" -> sortTransactions();
                case "0" -> running = false;
                default -> presenter.message("[!] Pilihan tidak dimengerti.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu Catatan Keuangan:");
        System.out.println("1. Tambah transaksi");
        System.out.println("2. Cari transaksi");
        System.out.println("3. Tampilkan saldo");
        System.out.println("4. Hapus transaksi");
        System.out.println("5. Ubah transaksi");
        System.out.println("6. Urutkan transaksi");
        System.out.println("0. Keluar");
    }

    private void addTransaction() {
        String description = InputUtil.input("Deskripsi (x Jika Batal)");
        if (description.equalsIgnoreCase("x")) {
            return;
        }
        Double amount = InputUtil.amount("Jumlah");
        TransactionType type = parseType(InputUtil.input("Tipe (i = pemasukan, e = pengeluaran)"));
        if (description.isBlank() || amount == null || type == null) {
            presenter.message("[!] Deskripsi, jumlah, dan tipe transaksi tidak valid!");
            return;
        }
        try {
            presenter.added(useCase.add(description, amount, type));
        } catch (IllegalArgumentException exception) {
            presenter.message("[!] Jumlah transaksi tidak valid!");
        }
    }

    private void searchTransaction() {
        String keyword = InputUtil.input("Kata kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) {
            presenter.show(useCase.search(keyword));
        }
    }

    private void removeTransaction() {
        Integer id = readId("ID transaksi (x Jika Batal)");
        if (id == null) return;
        if (useCase.remove(id)) {
            presenter.message("Berhasil menghapus transaksi.");
        } else {
            presenter.message("[!] Gagal menghapus transaksi dengan ID: " + id + ".");
        }
    }

    private void updateTransaction() {
        Integer id = readId("ID transaksi (x Jika Batal)");
        if (id == null) return;
        String description = InputUtil.input("Deskripsi baru (kosongkan jika tidak berubah)");
        String amountInput = InputUtil.input("Jumlah baru (kosongkan jika tidak berubah)");
        String typeInput = InputUtil.input("Tipe baru (i/e, kosongkan jika tidak berubah)");
        Double amount = amountInput.isBlank() ? null : InputUtil.amountValue(amountInput);
        TransactionType type = typeInput.isBlank() ? null : parseType(typeInput);
        if ((!amountInput.isBlank() && amount == null) || (!typeInput.isBlank() && type == null)) {
            presenter.message("[!] Jumlah atau tipe transaksi tidak valid!");
            return;
        }
        try {
            if (useCase.update(id, description, amount, type)) {
                presenter.message("Berhasil mengubah transaksi.");
            } else {
                presenter.message("[!] Gagal mengubah transaksi dengan ID: " + id + ".");
            }
        } catch (IllegalArgumentException exception) {
            presenter.message("[!] Jumlah transaksi tidak valid!");
        }
    }

    private void sortTransactions() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Jumlah terkecil");
        System.out.println("2. Jumlah terbesar");
        System.out.println("3. Pemasukan dulu");
        System.out.println("4. Pengeluaran dulu");
        String input = InputUtil.input("Pilih");
        TransactionSortOption option = switch (input) {
            case "1" -> TransactionSortOption.AMOUNT_ASC;
            case "2" -> TransactionSortOption.AMOUNT_DESC;
            case "3" -> TransactionSortOption.INCOME_FIRST;
            case "4" -> TransactionSortOption.EXPENSE_FIRST;
            default -> null;
        };
        if (option == null) {
            presenter.message("[!] Pilihan pengurutan tidak valid!");
        } else {
            presenter.show(useCase.sort(option));
        }
    }

    private TransactionType parseType(String input) {
        return switch (input.toLowerCase()) {
            case "i", "income", "pemasukan" -> TransactionType.INCOME;
            case "e", "expense", "pengeluaran" -> TransactionType.EXPENSE;
            default -> null;
        };
    }

    private Integer readId(String prompt) {
        String input = InputUtil.input(prompt);
        if (input.equalsIgnoreCase("x")) return null;
        Integer id = InputUtil.idValue(input);
        if (id == null) presenter.message("[!] ID tidak valid!");
        return id;
    }
}
