package ua.models;
import org.junit.jupiter.api.*;
import java.util.*;
public class GroupTest {
    @Test void comparableById(){
        Group g1=new Group(1,"X",1);
        Group g2=new Group(2,"Y",2);
        Assertions.assertTrue(g1.compareTo(g2)<0);
    }
    @Test void comparatorByTitle(){
        List<Group> l=Arrays.asList(new Group(2,"B",1), new Group(1,"A",1));
        l.sort(Group.byTitle);
        Assertions.assertEquals("A", l.get(0).getTitle());
    }
}
