package domain.repository;

import domain.entity.Todo;
import java.util.List;
import java.util.Optional;

/** Port kontrak penyimpanan data todo. */
public interface ITodoRepository {
    /** Mengambil semua todo. */
    List<Todo> findAll();

    /** Mencari satu todo berdasarkan ID. */
    Optional<Todo> findById(int id);

    /** Menyimpan todo baru. */
    Todo save(String title);

    /** Menghapus todo berdasarkan ID. */
    boolean deleteById(int id);

    /** Menyimpan perubahan todo yang sudah ada. */
    void update(Todo todo);
}