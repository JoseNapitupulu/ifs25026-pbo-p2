package adapter.repository;

import domain.entity.Todo;
import domain.repository.ITodoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Implementasi repository dengan penyimpanan in-memory berbasis List. */
public class TodoRepository implements ITodoRepository {
    private final List<Todo> data = new ArrayList<>();
    private int idCounter;
    /** Mengembalikan salinan daftar agar data internal tidak dapat diubah langsung. */
    public List<Todo> findAll() { return new ArrayList<>(data); }
    /** Mencari todo berdasarkan ID. */
    public Optional<Todo> findById(int id) { return data.stream().filter(todo -> todo.getId() == id).findFirst(); }
    /** Menyimpan todo baru dengan ID unik. */
    public Todo save(String title) { Todo todo = new Todo(++idCounter, title); data.add(todo); return todo; }
    /** Menghapus todo berdasarkan ID. */
    public boolean deleteById(int id) { return data.removeIf(todo -> todo.getId() == id); }
    /** Menyimpan perubahan entity yang mutable. */
    public void update(Todo todo) { }
}