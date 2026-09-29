package usecase;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Use case yang menangani logika bisnis jadwal kegiatan. */
public class ActivityUseCase {
	private final IActivityRepository repository;

	public ActivityUseCase(IActivityRepository repository) {
		this.repository = repository;
	}

	/** Mengambil seluruh kegiatan yang tersimpan. */
	public List<Activity> all() {
		return repository.findAll();
	}

	/** Menambahkan kegiatan baru. */
	public Activity add(String title, String day, String time) {
		return repository.save(title, day, time);
	}

	/** Menghapus kegiatan berdasarkan ID. */
	public boolean remove(int id) {
		return repository.deleteById(id);
	}

	/** Mengubah field kegiatan yang tidak kosong. */
	public boolean update(int id, String title, String day, String time) {
		Optional<Activity> found = repository.findById(id);
		if (found.isEmpty()) return false;

		found.get().update(title, day, time);
		return true;
	}

	/** Mencari kegiatan berdasarkan judul atau hari secara case-insensitive. */
	public List<Activity> search(String keyword) {
		String normalizedKeyword = keyword == null ? "" : keyword.toLowerCase();
		return all().stream()
				.filter(activity -> activity.getTitle().toLowerCase().contains(normalizedKeyword)
						|| activity.getDay().toLowerCase().contains(normalizedKeyword))
				.collect(Collectors.toList());
	}
}