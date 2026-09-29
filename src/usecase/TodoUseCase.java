package usecase;

import domain.entity.SortOption;
import domain.entity.Todo;
import domain.repository.ITodoRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis aplikasi todo.
 * Tidak melakukan I/O — hanya memproses data dan mengembalikan hasil
 * ke layer presenter/view.
 */
public class TodoUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final ITodoRepository todoRepository;

    public TodoUseCase(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /** Mengambil semua todo yang tersedia. */
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    /** Menambahkan todo baru dan mengembalikan entity yang tersimpan. */
    public Todo addTodo(String title) {
        return todoRepository.save(title);
    }

    /** Menghapus todo berdasarkan ID. */
    public boolean removeTodo(int id) {
        return todoRepository.deleteById(id);
    }

    /**
     * Mengubah judul dan/atau status selesai todo.
     * Parameter {@code null} berarti field tersebut tidak diubah.
     *
     * @return true jika todo ditemukan dan diperbarui
     */
    public boolean updateTodo(int id, String title, Boolean finished) {
        Optional<Todo> found = todoRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Todo todo = found.get();

        // Hanya ubah field yang eksplisit diberikan (bukan null)
        if (title != null) {
            todo.changeTitle(title);
        }
        if (finished != null) {
            todo.changeFinished(finished);
        }

        todoRepository.update(todo);
        return true;
    }

    /** Mencari todo yang judulnya mengandung kata kunci (case-insensitive). */
    public List<Todo> searchTodos(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /** Mengurutkan todo sesuai kriteria {@link SortOption} yang dipilih. */
    public List<Todo> sortTodos(SortOption option) {
        return todoRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
