package ua.util;
import org.junit.jupiter.api.*; import java.nio.file.*; import java.util.*;
public class FileReaderUtilTest{
 @Test void testReadLines() throws Exception{
   Path p=Path.of("test.txt"); Files.writeString(p,"a\nb");
   List<String> lines=FileReaderUtil.readLines("test.txt");
   Assertions.assertEquals(2,lines.size());
   Files.delete(p);
 }
}
