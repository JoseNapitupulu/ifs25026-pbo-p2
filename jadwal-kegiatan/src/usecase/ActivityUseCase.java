package usecase;

import domain.entity.Activity;
import domain.entity.SortOption;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;

public class ActivityUseCase {
    private final IActivityRepository repository;

    public ActivityUseCase(IActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    public Activity addActivity(String title, String day, String time) {
        return repository.save(title, day, time);
    }

    public boolean removeActivity(int id) {
        return repository.deleteById(id);
    }

    public boolean updateActivity(int id, String title, String day, String time) {
        Optional<Activity> found = repository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Activity activity = found.get();
        if (title != null) {
            activity.changeTitle(title);
        }
        if (day != null) {
            activity.changeDay(day);
        }
        if (time != null) {
            activity.changeTime(time);
        }

        repository.update(activity);
        return true;
    }

    public List<Activity> searchActivities(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(a -> a.getTitle().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public List<Activity> sortActivities(SortOption option) {
        return repository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
