package ua.repository;
import ua.models.Group;
import java.util.*;
public class GroupRepository extends ConcurrentRepository<Group> {
    public GroupRepository(){ super(Group::getId); }
}
