package com.chen.sbbus;

import com.chen.sbbus.entity.Routes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;

public class MyObjectDeserializer //extends JsonDeserializer<Routes>
 {

//    @Override
//    public Routes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        JsonNode node = p.getCodec().readTree(p);
//        int id = node.get("id").asInt();
//        String start = node.get("start").asText();
//        String end = node.get("end").asText();
//        String stations = node.get("stations").asText();
//        return new Routes(id,start,end,stations);
//    }
}