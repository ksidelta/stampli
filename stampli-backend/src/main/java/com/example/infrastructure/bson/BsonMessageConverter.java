package com.example.infrastructure.bson;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class BsonMessageConverter extends AbstractGenericHttpMessageConverter<Object> {
    ObjectMapper objectMapper;

    public BsonMessageConverter() {
        this(new ObjectMapper(new BsonFactory()));
    }

    public BsonMessageConverter(ObjectMapper objectMapper) {
        super(MediaType.parseMediaType("application/bson"));
        this.objectMapper = objectMapper;
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.createGenerator(outputMessage.getBody()).writeObject(o);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return this.objectMapper.createParser(inputMessage.getBody()).readValueAs(clazz);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if (!(type instanceof Class<?>)) {
            throw new HttpMessageNotReadableException("Must be a class");
        }
        return readInternal((Class<?>) type, inputMessage);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return super.canRead(clazz, mediaType);
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return super.canRead(type, contextClass, mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return super.getSupportedMediaTypes(clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return super.getSupportedMediaTypes();
    }
}
