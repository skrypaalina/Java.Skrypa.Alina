package ua.repository;
import ua.model.Course;
public class CourseRepository extends ConcurrentRepository<Course> {
    public CourseRepository(){ super(Course::getId); }
}
