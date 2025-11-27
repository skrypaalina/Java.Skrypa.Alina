package ua.exceptions;
public class DataSerializationException extends RuntimeException {
    public DataSerializationException(String message, Throwable cause){ super(message, cause); }
    public DataSerializationException(String message){ super(message); }
}
