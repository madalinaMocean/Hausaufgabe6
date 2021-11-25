package main.Repository;

import main.Repository.Inheritable.AbstractRepository;
import main.Repository.Inheritable.CourseRepository;
import main.Model.Course;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseInMemoryRepository extends AbstractRepository< Integer, Course> implements CourseRepository {
    @Override
    public List<Course> findByName(String name) {
        return getAll().stream().filter(x->x.getCourseName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Course> findByCredits(int credits) {
        return getAll().stream().filter(x->x.getCredits()==credits).collect(Collectors.toList());
    }

    @Override
    public List<Course> sortByCredits(){
       return getAll().stream()
               .sorted(Comparator.comparingInt(Course::getCredits))
               .collect(Collectors.toList());
    }

    @Override
    public List<Course> sortByMaxEnrollment(){
        return getAll().stream()
                .sorted(Comparator.comparingInt(Course::getMaxEnrollment))
                .collect(Collectors.toList());

    }
}
