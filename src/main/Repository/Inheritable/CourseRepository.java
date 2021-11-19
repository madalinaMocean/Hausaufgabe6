package main.Repository.Inheritable;

import main.Model.Course;
import java.util.List;

public interface CourseRepository extends ICrudRepository<Integer,Course> {
    List<Course> findByName(String name);
    List<Course> findByCredits(int credits);
    List<Course> sortByCredits();
    List<Course> sortByMaxEnrollment();

}
