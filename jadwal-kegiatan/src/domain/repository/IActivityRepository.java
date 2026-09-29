package domain.repository;
import domain.entity.Activity; import java.util.*;
public interface IActivityRepository { List<Activity> findAll(); Optional<Activity> findById(int id); Activity save(String title,String day,String time); boolean deleteById(int id); }