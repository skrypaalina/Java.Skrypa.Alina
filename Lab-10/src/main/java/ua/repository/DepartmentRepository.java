package ua.repository;
import ua.model.Department;
public class DepartmentRepository extends ConcurrentRepository<Department> {
    public DepartmentRepository(){ super(Department::getId); }
}
