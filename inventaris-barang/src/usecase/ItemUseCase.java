package usecase;

import domain.entity.Item;
import domain.entity.SortOption;
import domain.repository.IItemRepository;
import java.util.List;
import java.util.Optional;

public class ItemUseCase {
    private final IItemRepository repository;

    public ItemUseCase(IItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Item addItem(String name, int quantity, String category) {
        return repository.save(name, quantity, category);
    }

    public boolean removeItem(int id) {
        return repository.deleteById(id);
    }

    public boolean updateItem(int id, Integer quantity) {
        Optional<Item> found = repository.findById(id);
        if (found.isEmpty()) {
            return false;
        }

        Item item = found.get();
        if (quantity != null) {
            item.changeQuantity(quantity);
        }

        repository.update(item);
        return true;
    }

    public List<Item> searchItems(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(i -> i.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public List<Item> sortItems(SortOption option) {
        return repository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
