package adapter.repository;

import domain.entity.Contact;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository implements IContactRepository {
    private final List<Contact> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Contact> findById(int id) {
        return data.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public Contact save(String name, String phone, String email) {
        Contact contact = new Contact(++idCounter, name, phone, email);
        data.add(contact);
        return contact;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(c -> c.getId() == id);
    }

    @Override
    public void update(Contact contact) {
        replaceById(contact);
    }

    private void replaceById(Contact contact) {
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).getId() == contact.getId()) {
                data.set(index, contact);
                return;
            }
        }
        throw new IllegalArgumentException("Kontak tidak ditemukan: " + contact.getId());
    }
}
