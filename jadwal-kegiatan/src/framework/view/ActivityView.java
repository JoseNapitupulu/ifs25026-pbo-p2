package framework.view;

import adapter.presenter.ActivityPresenter;
import framework.util.ActivityInputUtil;
import usecase.ActivityUseCase;

/** Tampilan konsol untuk mengelola jadwal kegiatan. */
public class ActivityView {
    private final ActivityUseCase useCase;
    private final ActivityPresenter presenter;

    public ActivityView(ActivityUseCase useCase, ActivityPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    /** Menjalankan menu utama sampai user memilih keluar. */
    public void show() {
        boolean running = true;
        while (running) {
            presenter.show(useCase.all());
            printMenu();
            switch (ActivityInputUtil.input("Pilih")) {
                case "1" -> addActivity();
                case "2" -> searchActivity();
                case "3" -> updateActivity();
                case "4" -> removeActivity();
                case "0" -> running = false;
                default -> presenter.msg("[!] Pilihan tidak valid!");
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu Jadwal Kegiatan:");
        System.out.println("1. Tambah kegiatan");
        System.out.println("2. Cari kegiatan");
        System.out.println("3. Ubah kegiatan");
        System.out.println("4. Hapus kegiatan");
        System.out.println("0. Keluar");
    }

    private void addActivity() {
        String title = ActivityInputUtil.input("Judul (x Jika Batal)");
        if (title.equalsIgnoreCase("x")) return;
        String day = ActivityInputUtil.input("Hari");
        String time = ActivityInputUtil.input("Waktu");
        if (title.isBlank() || day.isBlank() || time.isBlank()) {
            presenter.msg("[!] Judul, hari, dan waktu wajib diisi!");
            return;
        }
        presenter.added(useCase.add(title, day, time));
    }

    private void searchActivity() {
        String keyword = ActivityInputUtil.input("Kata kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) presenter.show(useCase.search(keyword));
    }

    private void removeActivity() {
        Integer id = readId("ID kegiatan (x Jika Batal)");
        if (id == null) return;
        presenter.msg(useCase.remove(id) ? "Berhasil menghapus kegiatan." : "[!] Kegiatan tidak ditemukan!");
    }

    private void updateActivity() {
        Integer id = readId("ID kegiatan (x Jika Batal)");
        if (id == null) return;
        String title = ActivityInputUtil.input("Judul baru (kosongkan jika tidak berubah)");
        String day = ActivityInputUtil.input("Hari baru (kosongkan jika tidak berubah)");
        String time = ActivityInputUtil.input("Waktu baru (kosongkan jika tidak berubah)");
        presenter.msg(useCase.update(id, title, day, time) ? "Berhasil mengubah kegiatan." : "[!] Kegiatan tidak ditemukan!");
    }

    private Integer readId(String prompt) {
        String input = ActivityInputUtil.input(prompt);
        if (input.equalsIgnoreCase("x")) return null;
        Integer id = ActivityInputUtil.idValue(input);
        if (id == null) presenter.msg("[!] ID tidak valid!");
        return id;
    }
}
