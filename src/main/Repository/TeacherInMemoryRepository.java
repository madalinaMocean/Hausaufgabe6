package main.Repository;

import main.Repository.Inheritable.AbstractRepository;
import main.Repository.Inheritable.TeacherRepository;
import main.Model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherInMemoryRepository extends AbstractRepository< Integer, Teacher> implements TeacherRepository {
    public TeacherInMemoryRepository(){}

    @Override
    public List<Teacher> findByFirstName(String name) {
        return getAll().stream().filter(x->x.getFirstName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> findByLastName(String lastName) {
        return getAll().stream().filter(x->x.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> findByFullName(String firstName, String lastName) {
        return getAll().stream().filter(x->x.getFirstName().toLowerCase().contains(firstName.toLowerCase())).filter(x->x.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
    }
}
