package domain.repository;
import domain.entity.Contact; import java.util.*;
public interface IContactRepository { List<Contact> findAll(); Optional<Contact> findById(int id); Contact save(String name,String phone,String email); boolean deleteById(int id); }