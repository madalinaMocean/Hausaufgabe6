package main.Repository.Inheritable;

import main.Model.Student;
import java.util.List;

public interface StudentRepository extends ICrudRepository<Integer,Student> {
    List<Student> findByFirstName(String name);
    List<Student> findByLastName(String lastName);
    List<Student> findByFullName(String firstName, String lastName);
    List<Student> sortByTotalCredits();
}
