package com.testvue.testvue.localserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.testvue.testvue.enity.po.PageResult;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public JacksonRedisSerializer(ObjectMapper objectMapper, Class<T> clazz) {
        this.objectMapper = objectMapper;
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (IOException e) {
            throw new SerializationException("Could not serialize object", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        try {
            return objectMapper.readValue(bytes, clazz );
        } catch (IOException e) {
            throw new SerializationException("Could not deserialize object", e);
        }
    }
}

