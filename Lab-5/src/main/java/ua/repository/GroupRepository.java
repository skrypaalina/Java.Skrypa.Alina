package ua.repository;
import ua.models.Group;
import java.util.*;
public class GroupRepository extends GenericRepository<Group>{
    public GroupRepository(){ super(Group::getId); }
    public List<Group> sortByTitle(){ return sort(Group.byTitle); }
    public List<Group> sortByCourse(){ return sort(Group.byCourse); }
}
