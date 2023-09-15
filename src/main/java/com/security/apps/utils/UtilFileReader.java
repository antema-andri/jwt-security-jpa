package com.security.apps.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.apps.dto.BaseDto;

public class UtilFileReader {
	
	public static <T extends BaseDto> T readJson(String classpathFile, Class<T> dtoClass) throws IOException {
		byte[] jsonData=getBytes(classpathFile);
		ObjectMapper objectMapper=createObjectMapper();    
		return objectMapper.readValue(jsonData , dtoClass);
	}
	
	public static <T extends BaseDto> List<T> readJsonArray(String classpathFile, Class<T> dtoClass) throws IOException {
		byte[] jsonData=getBytes(classpathFile);
		ObjectMapper objectMapper=createObjectMapper();
		JavaType javaType=objectMapper.getTypeFactory().constructCollectionType(List.class, dtoClass);
		return objectMapper.readValue(jsonData , javaType);
	}
	
	public static byte[] getBytes(String classpathFile) throws IOException {
		File resource = new ClassPathResource(classpathFile).getFile();
		return Files.readAllBytes(resource.toPath());
	}
	
	public static ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
}
