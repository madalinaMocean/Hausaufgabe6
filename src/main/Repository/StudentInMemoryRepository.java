package main.Repository;

import main.Model.Course;
import main.Repository.Inheritable.AbstractRepository;
import main.Repository.Inheritable.StudentRepository;
import main.Model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentInMemoryRepository extends AbstractRepository< Integer, Student> implements StudentRepository {
    public StudentInMemoryRepository(){}

    @Override
    public List<Student> findByFirstName(String name) {
        return getAll().stream().filter(x->x.getFirstName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        return getAll().stream().filter(x->x.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Student> findByFullName(String firstName, String lastName) {
        return getAll().stream().filter(x->x.getFirstName().toLowerCase().contains(firstName.toLowerCase())).filter(x->x.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Student> sortByTotalCredits() {
        return getAll().stream()
                .sorted(Comparator.comparingInt(Student::totalCredits))
                .collect(Collectors.toList());
    }

}