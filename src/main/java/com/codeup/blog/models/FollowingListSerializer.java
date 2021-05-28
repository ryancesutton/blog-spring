package com.codeup.blog.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

//FOR EXAMPLE PURPOSES ONLY IN CASE OF PROPERTY OF OBJECT IS LIST
public class FollowingListSerializer extends JsonSerializer <List<User>> {

    @Override
    public void serialize(List<User> followingList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (User user : followingList) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("user", user);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
