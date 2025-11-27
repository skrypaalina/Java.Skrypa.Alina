package ua.repository;
import ua.models.Group;
import ua.util.LoggerUtil;
import java.util.*;
import java.util.stream.Collectors;
public class GroupRepository extends GenericRepository<Group> {
    public GroupRepository(){ super(Group::getId); }
    public List<Group> findByTitleContains(String sub){
        LoggerUtil.info("findByTitleContains: " + sub);
        return storage.values().stream()
            .filter(g -> g.getTitle()!=null && g.getTitle().toLowerCase().contains(sub.toLowerCase()))
            .collect(Collectors.toList());
    }
    public List<Group> findByCourseRange(int min,int max){
        LoggerUtil.info("findByCourseRange: " + min + ".." + max);
        return storage.values().stream()
            .filter(g -> g.getCourse()>=min && g.getCourse()<=max)
            .collect(Collectors.toList());
    }
}
