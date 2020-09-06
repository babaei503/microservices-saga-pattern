package ir.saeidbabaei.orderservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class Converter {

	private final ObjectMapper mapper;

    public String toJSON(final Object object) {
    	
        try {
        	
            return mapper.writeValueAsString(object);
            
        } catch (JsonProcessingException e) {
        	
            throw new IllegalArgumentException("Cannot convert " + object + " to json", e);
            
        }
    }

    public <T> T toObject(String json, Class<T> clazz) {
    	
        try {
        	
            return mapper.readValue(json, clazz);
            
        } catch (IOException e) {
        	
            throw new IllegalArgumentException("Cannot convert " + json + " to object type " + clazz.getSimpleName(), e);
            
        }
    }
}