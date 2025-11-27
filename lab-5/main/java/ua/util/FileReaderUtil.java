package ua.util;
import java.nio.file.*; import java.util.*;
public class FileReaderUtil {
 public static List<String> readLines(String path){
   try{ return Files.readAllLines(Path.of(path)); }catch(Exception e){ throw new RuntimeException(e);}
 }
}
