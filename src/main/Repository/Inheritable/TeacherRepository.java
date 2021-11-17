package main.Repository.Inheritable;

import main.Model.Teacher;
import java.util.List;

public interface TeacherRepository extends ICrudRepository<Integer,Teacher> {
    List<Teacher> findByFirstName(String name);
    List<Teacher> findByLastName(String lastName);
    List<Teacher> findByFullName(String firstName, String lastName);
}
