package ua.serialization;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ua.exceptions.DataSerializationException;
import ua.util.LoggerUtil;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class Serializer {
    private final ObjectMapper json = new ObjectMapper();
    private final ObjectMapper yaml = new ObjectMapper(new YAMLFactory());
    public <T> void saveJson(Collection<T> list, Path path){
        try {
            Files.createDirectories(path.getParent()==null?path.getParent():path.getParent());
            json.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), list);
            LoggerUtil.info("Saved JSON to " + path);
        } catch (IOException e){
            throw new DataSerializationException("Failed to save JSON to " + path, e);
        }
    }
    public <T> List<T> loadJson(Path path, Class<T[]> arrClass){
        try {
            if(!Files.exists(path)) throw new DataSerializationException("File not found: " + path);
            T[] arr = json.readValue(path.toFile(), arrClass);
            LoggerUtil.info("Loaded JSON from " + path);
            return Arrays.asList(arr);
        } catch (IOException e){
            throw new DataSerializationException("Failed to load JSON from " + path, e);
        }
    }
    public <T> void saveYaml(Collection<T> list, Path path){
        try {
            Files.createDirectories(path.getParent()==null?path.getParent():path.getParent());
            yaml.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), list);
            LoggerUtil.info("Saved YAML to " + path);
        } catch (IOException e){
            throw new DataSerializationException("Failed to save YAML to " + path, e);
        }
    }
    public <T> List<T> loadYaml(Path path, Class<T[]> arrClass){
        try {
            if(!Files.exists(path)) throw new DataSerializationException("File not found: " + path);
            T[] arr = yaml.readValue(path.toFile(), arrClass);
            LoggerUtil.info("Loaded YAML from " + path);
            return Arrays.asList(arr);
        } catch (IOException e){
            throw new DataSerializationException("Failed to load YAML from " + path, e);
        }
    }
}
