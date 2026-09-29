package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements IGuestRepository {
    private final List<Guest> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Guest> findById(int id) {
        return data.stream().filter(g -> g.getId() == id).findFirst();
    }

    @Override
    public Guest save(String name, String purpose) {
        Guest g = new Guest(++idCounter, name, purpose);
        data.add(g);
        return g;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(g -> g.getId() == id);
    }

    @Override
    public void update(Guest guest) {
        replaceById(guest);
    }

    private void replaceById(Guest guest) {
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).getId() == guest.getId()) {
                data.set(index, guest);
                return;
            }
        }
        throw new IllegalArgumentException("Tamu tidak ditemukan: " + guest.getId());
    }
}
